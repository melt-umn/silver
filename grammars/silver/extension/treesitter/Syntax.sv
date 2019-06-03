grammar silver:extension:treesitter;

import silver:modification:impide:cstast; -- used so we can aspect syntaxFont
import silver:modification:impide:spec; -- used so we can aspect syntaxFont

nonterminal TreesitterRoot;
nonterminal TreesitterRules;
nonterminal TreesitterRule;

synthesized attribute treesitterGrammarJs :: String occurs on TreesitterRoot;
synthesized attribute emptyStringNonterminals :: [String] occurs on TreesitterRules;
synthesized attribute tsExtras :: [String] occurs on TreesitterRules;

synthesized attribute precAssocEntries :: [Pair<String Pair<Integer String>>] occurs on TreesitterRules, TreesitterRule;
synthesized attribute modifiedProdsForEmptyString :: TreesitterRule;
autocopy attribute precAssocEnv :: [Pair<String Pair<Integer String>>] occurs on TreesitterRules, TreesitterRule;
synthesized attribute tsRoot :: TreesitterRoot occurs on SyntaxRoot;
synthesized attribute tsDcls :: TreesitterRules occurs on Syntax;
synthesized attribute tsDcl :: TreesitterRule occurs on SyntaxDcl;
synthesized attribute numRules :: Integer occurs on TreesitterRules;

synthesized attribute dclName :: String occurs on SyntaxDcl;

synthesized attribute tsRep :: String occurs on TreesitterRule, TreesitterRules;
-- necessary because the first "rule" in the grammar.js file is assumed to be
-- the start rule
synthesized attribute startNtRep :: String occurs on TreesitterRules;
autocopy attribute startNt :: String occurs on TreesitterRules;

autocopy attribute emptyStringTerminals :: [String] occurs on TreesitterRules, TreesitterRule;
synthesized attribute emptyStringTerminalContribs :: [String] occurs on TreesitterRules;

function getTreesitterRulesBy
TreesitterRules ::= func::(Boolean ::= TreesitterRule) rules::TreesitterRules
{
  return
  case rules of
  | nilTreesitterRules() -> nilTreesitterRules()
  | consTreesitterRules(rule, rest) ->
      if func(rule) then
        consTreesitterRules(rule, getTreesitterRulesBy(func, rest))
      else
        getTreesitterRulesBy(func, rest)
  end;
}

function isTreesitterNonterminal
Boolean ::= rule::TreesitterRule
{
  return
  case rule of
  | treesitterNonterminal(_, _, _) -> true
  | _ -> false
  end;
}

function isTreesitterTerminal
Boolean ::= rule::TreesitterRule
{
  return
  case rule of
  | treesitterTerminal(_, _, _) -> true
  | _ -> false
  end;
}

abstract production treesitterRoot
top::TreesitterRoot ::= name::String startnt::String rules::TreesitterRules
{
  rules.startNt = startnt;
  rules.emptyStringTerminals = rules.emptyStringTerminalContribs;
  rules.precAssocEnv = rules.precAssocEntries;
  top.treesitterGrammarJs = 
s"""
module.exports = grammar({
    name: "${name}",
    extras: $$ => [
      ${implode(",\n      ", rules.tsExtras)}
    ],

  // conflicts will be added by another program.
  // for that program to work it assumes the line is exactly this.
  // DO NOT MODIFY
  conflicts: $$ => 

  rules: {
    // start rule for the grammar
    ${rules.startNtRep},

    ${rules.tsRep}
  }
});""";
}

function isStartNonterminal
Boolean ::= rule::TreesitterRule startNt::String
{
  return 
  case rule of
  | treesitterNonterminal(name, _, _) -> stringEq(name, startNt)
  | _ -> false
  end;
}

abstract production consTreesitterRules
top::TreesitterRules ::= hd::TreesitterRule tl::TreesitterRules
{
  -- may need to separate nonterminals and terminals
  top.tsRep = 
    if isStartNonterminal(hd, top.startNt) then tl.tsRep
    else if stringEq(hd.tsRep, "") then tl.tsRep
    else
      case tl of
      | nilTreesitterRules() -> hd.tsRep -- no trailing comma
      | _ -> hd.tsRep ++ ",\n\n" ++ tl.tsRep
      end;

  top.startNtRep =
    if isStartNonterminal(hd, top.startNt) 
    then hd.tsRep
    else tl.startNtRep;

  top.numRules = 1 + tl.numRules;
  top.emptyStringNonterminals =
    case hd of
    | treesitterNonterminal(name, _, mods) -> 
      if mods.canProduceEmptyString then
        name :: tl.emptyStringNonterminals
      else
        tl.emptyStringNonterminals
    | _ -> tl.emptyStringNonterminals
    end;

  top.emptyStringTerminalContribs =
    case hd of
    | treesitterTerminal(name, regex, _) ->
      if stringEq(regex.regString, "") then
        name :: tl.emptyStringTerminalContribs
      else
        tl.emptyStringTerminalContribs
    | _ -> tl.emptyStringTerminalContribs
    end;

  top.tsExtras = 
    case hd of
    | treesitterTerminal(name, _, mods) ->
      if mods.ignored then TsDeclToIdentifier(TsDeclToIgnoreDecl(name))::tl.tsExtras
      else tl.tsExtras
    | _ -> tl.tsExtras
    end;
  top.precAssocEntries = append(hd.precAssocEntries, tl.precAssocEntries);
}

abstract production nilTreesitterRules
top::TreesitterRules ::=
{
  top.tsRep = "";
  top.startNtRep = "";
  top.numRules = 0;
  top.emptyStringNonterminals = [];
  top.emptyStringTerminalContribs = [];
  top.precAssocEntries = [];
  top.tsExtras = [];
}

abstract production noEquivalentTreesitterDcl
top::TreesitterRule ::= 
{
  top.tsRep = "";
  top.precAssocEntries = [];
}

abstract production treesitterTerminal
top::TreesitterRule ::= name::String r::Regex mods::SyntaxTerminalModifiers 
{
  local attribute ts_lhs :: String = 
    if mods.ignored then -- TODO: do we want to ignore these?
      TsDeclToIgnoreDecl(name)
    else
      name;
  local attribute ts_rhs :: String =
    if r.stringLiteral then
      s""" "${removeAllEscapesForStringLiteral(r.regString)}" """
    else 
    -- treesitter does not escape spaces unlike silver
      s"""/${removeEscapesNotNecessaryForTreesitterRegexs(r.regString)}/""";
  top.tsRep =
    if containsBy(stringEq, name, top.emptyStringTerminals) then
      ""
    else 
      s"""${ts_lhs}: $$ => ${ts_rhs}""";
  local attribute precAssoc :: Maybe<Pair<Integer String>> = getPrecAssocInfo(mods);
  top.precAssocEntries = 
    if precAssoc.isJust then
      [pair(ts_lhs, precAssoc.fromJust)]
    else
      [];
}

function isNotInEmptyStringList
Boolean ::= emptyStrList :: [String] input::String
{
  return !containsBy(stringEq, input, emptyStrList);
}

-- inputs should be tressiter identifiers
abstract production treesitterProduction
top::TreesitterRule ::= outputNT::String inputs::[String] mods::Decorated SyntaxProductionModifiers
{ 
  local attribute emptyTerminalIdentifiers :: [String] = map(TsDeclToIdentifier, top.emptyStringTerminals);
  local attribute inputNoEmptyTerminals :: [String] =
    filter(isNotInEmptyStringList(emptyTerminalIdentifiers, _), inputs);

  top.precAssocEntries = [];
  local attribute precAssocFromTerminal :: Maybe<Pair<Integer String>> = 
    lookupByList(stringEq, top.precAssocEnv, map(TsIdentifierToDecl, inputs));
  local attribute prec :: Maybe<Integer> = 
    orElse(mods.treesitterProductionPrec, fstFromMaybe(precAssocFromTerminal));
  local attribute assoc :: Maybe<String> =
    sndFromMaybe(precAssocFromTerminal);
  local attribute prodBeforePrec :: String = 
    if length(inputNoEmptyTerminals) > 1 then
      s"""seq(${implode(", ", inputNoEmptyTerminals)})"""
    else
      s"""${implode(", ", inputNoEmptyTerminals)}""";
  local attribute prodBeforeSpacing :: String = 
    -- if both precedence and associativity are specified
    if prec.isJust && prec.fromJust != 0 && assoc.isJust && !stringEq(assoc.fromJust, "none") then 
      s"""prec.${assoc.fromJust}(${toString(prec.fromJust)}, ${prodBeforePrec})"""
    -- just precedence is specified and associativty is not
    else if prec.isJust && prec.fromJust != 0 then 
      s"""prec(${toString(prec.fromJust)}, ${prodBeforePrec})"""
    -- just associativty is specified
    else if assoc.isJust && !stringEq(assoc.fromJust, "none") then
      s"""prec.${assoc.fromJust}(${prodBeforePrec})"""
    else
      prodBeforePrec;

  top.tsRep = prodBeforeSpacing;
}

abstract production treesitterNonterminal
top::TreesitterRule ::= name::String prods::TreesitterRules mods::TreesitterNonterminalModifiers
{
  top.precAssocEntries = [];
  local attribute ts_rhs :: String = 
    if prods.numRules > 1 then
      s"""choice(${prods.tsRep})"""
    else
      prods.tsRep;
  top.tsRep =
    s"""${name}: $$ => ${ts_rhs}""";
}

{--
  info for precedence and associativity of terminals for treesitter. This exists
  because we need to transfer precedence of terminals such as '+' to rules that
  include them in treesitter aka
    terminal Plus_t '+' precedence = 3, association = left
      in treesitter would look like
    add_expr: $ => prec.left(3, seq($.expr, $.Plus_t, $.expr))
  format for this is (terminal_name, (precedence, associativity)) where
  0 precedence means no precedence and associativity can be
  'left', 'right', 'none' so the above example would be
    ("Plus_t", (3, "left"))
--}

{-- SYNTAX PRODUCTIONS --}
aspect production nilSyntax
top::Syntax ::=
{
  top.tsDcls = nilTreesitterRules();
}

aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  top.tsDcls = 
    case s1.tsDcl of
    | noEquivalentTreesitterDcl() -> s2.tsDcls
    | _ -> consTreesitterRules(s1.tsDcl, s2.tsDcls)
    end;
}

{-- SYNTAX DCL PRODUCTIONS --}
aspect production syntaxNonterminal
top::SyntaxDcl ::= t::Type subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
  top.dclName = t.typeName;
  local attribute mods :: TreesitterNonterminalModifiers = 
    if canProduceEmptyString(subdcls) then 
      consTSNonterminalMod(ntProducesEmptyStringMod(), nilTSNonterminalMod())
    else
      nilTSNonterminalMod();
  top.tsDcl = treesitterNonterminal(toTsDeclaration(t.typeName), subdcls.tsDcls, mods);
}

aspect production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex modifiers::SyntaxTerminalModifiers
{
  top.dclName = n;
  top.tsDcl = treesitterTerminal(toTsDeclaration(n), regex, modifiers);
}

aspect production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature  modifiers::SyntaxProductionModifiers
{
  top.dclName = ns.fullName;
  top.tsDcl = treesitterProduction(ns.outputElement.typerep.typeName,
    map(productionElemToTsIdentifier, ns.inputElements), 
      decorate modifiers with {cstEnv = top.cstEnv;});
}

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  top.dclName = n;
  top.tsDcl = noEquivalentTreesitterDcl();
}

aspect production syntaxDisambiguationGroup
top::SyntaxDcl ::= n::String terms::[String] applicableToSubsets::Boolean acode::String
{
  top.dclName = n;
  top.tsDcl = noEquivalentTreesitterDcl();
}

function canProduceEmptyString
Boolean ::= prods::Syntax
{
  return case prods of 
  | nilSyntax() -> false
  | consSyntax(prod, rest) ->
      case prod of
      | syntaxProduction(ns, _) -> if length(ns.inputElements) == 0 then true
                                   else canProduceEmptyString(rest)
      | _ -> canProduceEmptyString(rest)
      end
  end;
}

aspect production syntaxFont
top::SyntaxDcl ::= fontName::String fnt::Font -- this will likely eventually need to be removed
{
  top.dclName = fontName;
  top.tsDcl = noEquivalentTreesitterDcl();
}

aspect production syntaxParserAttribute
top::SyntaxDcl ::= n::String ty::Type acode::String
{
  top.dclName = n;
  top.tsDcl = noEquivalentTreesitterDcl();
}

aspect production syntaxParserAttributeAspect
top::SyntaxDcl ::= n::String acode::String
{
  top.dclName = n;
  top.tsDcl = noEquivalentTreesitterDcl();
}

function syntaxToDclList
[SyntaxDcl] ::= s::Syntax
{
  return case s of
  | nilSyntax() -> []
  | consSyntax(dcl, rest) -> dcl :: syntaxToDclList(rest)
  end;
}

function dclListToSyntax
Syntax ::= dcls::[SyntaxDcl]
{
  return foldr(consSyntax, nilSyntax(), dcls);
}

function appendSyntax
Syntax ::= s1::Syntax s2::Syntax
{
  return
  case s1 of 
  | nilSyntax() -> s2
  | consSyntax(dcl, rest) -> appendSyntax(rest, consSyntax(dcl, s2))
  end;
}

{-- IS/HAS FUNCTIONS --}

function isTerminal
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxTerminal(_, _, _) -> true
  | _ -> false
  end;
}

function isIgnoreTerminal
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxTerminal(_, _, modifiers) -> modifiers.ignored
  | _ -> false
  end;
}

function isNonterminal
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxNonterminal(_, _) -> true
  | _ -> false
  end;
}

function isProduction
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxProduction(_, _) -> true
  | _ -> false
  end;
}

function isLexerClass
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxLexerClass(_, _) -> true
  | _ -> false
  end;
}

function isSyntaxDisambiguationGroup
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxDisambiguationGroup(_, _, _, _) -> true
  | _ -> false
  end;
}

function hasDisambiguationFunction
Boolean ::= declaration::Decorated SyntaxDcl
{
  return 
  case declaration of
  | syntaxDisambiguationGroup(_, _, _, _) -> true
  | syntaxLexerClass(_, mods) -> length(mods.disambiguationClasses) > 0
  | _ -> false
  end;
}

function dclsHaveSameName
Boolean ::= dcl1::SyntaxDcl dcl2::SyntaxDcl
{
  return stringEq(dcl1.dclName, dcl2.dclName);
}
