grammar silver:extension:treesitter;

imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:concrete_syntax;
imports silver:definition:concrete_syntax:ast;
imports silver:definition:regex;

{-- Throughout the code the following abbreviations mean
  ts : Treesitter
--}

function getModifiedCopperXML
String ::= parsername::String startnt::String s::Syntax conflicts::[Pair<String [String]>] terminalPrefixes::[Pair<String String>]
{
  -- remove empty string terminals just as we do in Treesitter and also
  -- removes disambigaution functions and moves terminals involved into
  -- nonterminals to bring ambiguity to parse level instead of lexer level
  local modifiedGrammarForConflicts :: Syntax = createModifiedGrammar(s, conflicts);
  local modRoot :: SyntaxRoot = cstRoot("ModifiedGrammarForTreesitter", startnt, modifiedGrammarForConflicts, terminalPrefixes);
  return modRoot.xmlCopper;
}

{-- ASPECT PRODUCTIONS --}
aspect production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  terminalPrefixes::[Pair<String String>]
{

  production grammar_to_use :: Syntax = 
    if top.demoSpec then
      newNonterminalsForBridgeProductions(s2, s2.cstEnv) 
    else s2;
  -- transform the grammar to remove productions that can produce the empty string
  grammar_to_use.classTerminals = s2.classTerminals;
  grammar_to_use.cstEnv = s2.cstEnv;

  top.modifiedXMLCopper = 
    -- used so we don't infinitely recurse
    if stringEq(parsername, "ModifiedGrammarForTreesitter") then
      ""
    else
      getModifiedCopperXML(parsername, startnt, grammar_to_use, grammar_to_use.terminalConflicts, terminalPrefixes);

  -- the 'normalized' version from production attribute 's2'.  This groups productions with
  -- the same left hand side together as subdcls on nonterminals.

  -- the empty string environment keeps track of which nonterminals can produce
  -- the empty string. This is used to modify the grammar such that only the
  -- nonterminal that can produce the empty string is the start nonterminal.
  -- This is required for Treesitter grammars.
  -- The environment is a list of nonterminal rule, true/false pairs where
  -- true means the nonterminal can produce the emptty string.
  local attribute transformed_grammar :: TreesitterRules =
    transformEmptyStringRules(grammar_to_use.tsDcls);

  top.tsRoot = 
    treesitterRoot(top.lang, toTsDeclaration(startnt), transformed_grammar, terminalPrefixes);

  -- The tree sitter grammar.
  top.jsTreesitter = top.tsRoot.treesitterGrammarJs;
}

function isStartNonTerminalRule
Boolean ::= startnt::String rule::String
{
  return startsWith(startnt, rule);
}

function removeEmptyStringTerminals
TreesitterRules ::= gram::TreesitterRules emptyStrTerminals::[String]
{
  return 
  case gram of
  | nilTreesitterRules() -> gram
  | consTreesitterRules(rule, rest) ->
    case rule of
    | treesitterTerminal(_, regex, _) ->
      if stringEq(regex.regString, "")
      then removeEmptyStringTerminals(rest, emptyStrTerminals)
      else consTreesitterRules(rule, removeEmptyStringTerminals(rest, emptyStrTerminals))
    | treesitterNonterminal(name, prods, mods)  -> 
        consTreesitterRules(
          treesitterNonterminal(name, removeEmptyStringTerminalsFromProductions(prods, emptyStrTerminals), mods),
          removeEmptyStringTerminals(rest, emptyStrTerminals))
    end
  end;
}


function removeEmptyStringTerminalsFromProductions
TreesitterRules ::= prods::TreesitterRules emptyStrRules::[String]
{
  return
  case prods of 
  | nilTreesitterRules() -> prods
  | consTreesitterRules(rule, rest) ->
    case rule of 
    | treesitterProduction(out, input, mods) ->
        consTreesitterRules(
          treesitterProduction(out, filter(isNotInEmptyStringList(emptyStrRules, _), input), mods),
          removeEmptyStringTerminalsFromProductions(rest, emptyStrRules))
    | _ -> 
      consTreesitterRules(rule, removeEmptyStringTerminalsFromProductions(rest, emptyStrRules))
    end
  end;
}
{--
 - The name of the language specified by this Tree-sitter grammar.
 -}
inherited attribute lang :: String occurs on SyntaxRoot;
inherited attribute demoSpec :: Boolean occurs on SyntaxRoot;
{--
 - Translation of a CST AST to Tree-sitter Javascript.
 -}
synthesized attribute jsTreesitter :: String occurs on SyntaxRoot;
synthesized attribute modifiedXMLCopper :: String occurs on SyntaxRoot;
synthesized attribute serializedTsRoot :: String occurs on SyntaxRoot;
-- TODO: Why is SyntaxRoot closed?  Needs a default.
aspect default production
top::SyntaxRoot ::=
{
  top.tsRoot = error("Shouldn't happen");
  top.serializedTsRoot = error("Shouldn't happen");
  top.jsTreesitter = error("Shouldn't happen");
  top.modifiedXMLCopper = error("Shouldn't happen");
}


{-- EMPTY STRING NONTERMINALS GRAMMAR MODIFICATION FUNCTIONS --}
function computeEmptyStringRules
[String] ::= gram::TreesitterRules
{
  return
  case gram of 
  | nilTreesitterRules() -> []
  | consTreesitterRules(rule, rest) ->
    case rule of
    | treesitterNonterminal(name, prods, _) ->
        if canNonterminalProduceEmptyString(prods) then
          name :: computeEmptyStringRules(rest)
        else
          computeEmptyStringRules(rest)
    | _ -> computeEmptyStringRules(rest)
    end
  end;
}

function canNonterminalProduceEmptyString
Boolean ::= prods::TreesitterRules
{
  return
  case prods of
  | nilTreesitterRules() -> false
  | consTreesitterRules(rule, rest) ->
    case rule of
    | treesitterProduction(_, inputs, _) -> 
        length(inputs) == 0 || canNonterminalProduceEmptyString(rest)
    | _ -> canNonterminalProduceEmptyString(rest)
    end
  end;
}

{--
  Transforms the grammar so that no nonterminals can produce the empty string
  @param oldGrammar the original grammar
  @return the new grammar with no nonterminals that can produce the empty string
--}
function transformEmptyStringRules
TreesitterRules ::= oldGrammar::TreesitterRules
{
  local attribute emptyStringNonterminals :: [String] = computeEmptyStringRules(oldGrammar);
  return 
  -- no rules can produce the empty string except maybe the start rule
  if null(emptyStringNonterminals) then oldGrammar
  else
    -- call to transform the next rule
    transformEmptyStringRules(
      emptyStringGrammarModification(head(emptyStringNonterminals), oldGrammar));
}


{--
  Modifies the oldGrammar so that the emptyRule can no longer produce the empty
  string but the grammar is equivalent
  @param emptyRule The rule that can produce the empty string.
    This should be given as treesitter declaration (e.g. List_c not $.List_c)
  @return the modified grammar. 
--}
function emptyStringGrammarModification
TreesitterRules ::= emptyRule::String oldGrammar::TreesitterRules
{
  return
  case oldGrammar of
  | nilTreesitterRules() -> nilTreesitterRules()
  | consTreesitterRules(rule, rest) ->
    case rule of
    -- rule that produced the empty string remove all empty productions
    | treesitterNonterminal(name, _, _) -> 
      if stringEq(name, emptyRule) then
        consTreesitterRules(refactorForEmptyRule(
          TsDeclToIdentifier(emptyRule), removeEmptyProductions(rule)),
          emptyStringGrammarModification(emptyRule, rest))
      else
        consTreesitterRules(refactorForEmptyRule(TsDeclToIdentifier(emptyRule), rule), 
                            emptyStringGrammarModification(emptyRule, rest))
    | _ -> consTreesitterRules(rule, emptyStringGrammarModification(emptyRule, rest))
    end
  end;
}

function removeEmptyProductions
TreesitterRule ::= nt::TreesitterRule
{
  return case nt of
  | treesitterNonterminal(name ,subdcls, mods) ->
      treesitterNonterminal(name, filterOutEmptyProductions(subdcls), mods)
  | _ -> error("Shouldn't happen")
  end;
}

function filterOutEmptyProductions
TreesitterRules ::= prods::TreesitterRules
{
  return
  case prods of
  | nilTreesitterRules() -> nilTreesitterRules()
  | consTreesitterRules(prod, rest) ->
      case prod of
      | treesitterProduction(_, inputs, _) ->
          if length(inputs) == 0 then filterOutEmptyProductions(rest)
          else consTreesitterRules(prod, filterOutEmptyProductions(rest))
      | _ -> error("Shouldn't happen")
      end
  end;
}

{--
  @param emptyRule The rule that can produce the empty string.
    This should be given as a treesitter identifier (e.g. $.List_c not List_c)
  @param oldProductions The old productions of the nonterminal

  @return the new productions for the nonterminal adding productions so that
    all productions that have elements that can produce the empty string are
    removed in a newly added production.
    e.g. if old productions were [ [$.notEmpty, $.canBeEmpty] ] the function
      would return [ [$.notEmpty], [$.notEmpty, $.canBeEmpty] ]
--}
function refactorForEmptyRule
TreesitterRule ::= emptyRule::String nt::TreesitterRule
{
  return
    case nt of
    | treesitterNonterminal(name, subdcls, mods) -> 
        treesitterNonterminal(name, refactorProductions(emptyRule, subdcls), mods)
    | _ -> error("Shouldn't happen")
    end;
}

function refactorProductions
TreesitterRules ::= emptyRule::String prods::TreesitterRules
{
  return case prods of
  | nilTreesitterRules() -> nilTreesitterRules()
  | consTreesitterRules(rule, rest) -> 
      foldr(consTreesitterRules, refactorProductions(emptyRule, rest), refactorProduction(emptyRule, rule))
  end;
}

function refactorProduction
[TreesitterRule] ::= emptyRule::String prod::TreesitterRule
{
  return 
  case prod of
  | treesitterProduction(_, inputs, _) ->
      if containsBy(stringEq, emptyRule, inputs) then
        accountForEmptyRuleWithinProd(emptyRule, prod)
      else
        [prod]
  | _ -> error("Shouldn't happen")
  end;
}

{--
  Creates a list of new productions from an original production where every
  instance of the emptyRule in the production is both included and excluded
  @param emptyRule The rule that can produce the empty string.
    This should be given as a treesitter identifier (e.g. $.List_c not List_c)
  @param prod The production - the list of inputs to the production
  @return The list of new productions that are equivalent to the original production
  assuming the rule that used to be able to produce the empty string no longer can.
--}
function accountForEmptyRuleWithinProd
[TreesitterRule] ::= emptyRule::String prod::TreesitterRule
{
  return
    case prod of
    | treesitterProduction(output, inputs, mods) ->
        map(treesitterProduction(output, _, mods), buildDifferentInputElements(emptyRule, inputs))
    | _ -> error("Shouldn't happen")
    end;
}

function buildDifferentInputElements
[[String]] ::= emptyRule::String originalInputs::[String]
{
  return 
  if null(originalInputs) then [ [] ]
  else if (stringEq(emptyRule, head(originalInputs))) then
    -- split by including the empty rule and excluding the empty rule
    append(
      consToAll(head(originalInputs), buildDifferentInputElements(emptyRule, tail(originalInputs))),
      buildDifferentInputElements(emptyRule, tail(originalInputs)))
  else
    consToAll(head(originalInputs), buildDifferentInputElements(emptyRule, tail(originalInputs)));
}

{-- GETTER FUNCTIONS --}
-- creates a pair of the precedence and associativity from the terminal modifiers
function getPrecAssocInfo
Maybe<Pair<Integer String>> ::= modifiers::SyntaxTerminalModifiers
{
  return
  if (modifiers.opAssociation.isJust && modifiers.opPrecedence.isJust) then
    just(pair(modifiers.opPrecedence.fromJust, modifiers.opAssociation.fromJust))
  else if modifiers.opAssociation.isJust then -- no precedence specified
    just(pair(0, modifiers.opAssociation.fromJust))
  else if modifiers.opPrecedence.isJust then -- no associativity specified
    just(pair(modifiers.opPrecedence.fromJust, "none"))
  else  -- no precedence or associativity specified
    nothing();
}


function hasUsefulPrecAssocInfo
Boolean ::= info::Pair<Integer String>
{
  return info.fst != 0 || info.snd != "none";
}


