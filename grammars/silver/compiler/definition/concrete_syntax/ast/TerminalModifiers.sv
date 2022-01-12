grammar silver:compiler:definition:concrete_syntax:ast;

import silver:compiler:definition:concrete_syntax:copper as copper;

monoid attribute dominatesXML :: String;
monoid attribute submitsXML :: String;
monoid attribute lexerclassesXML :: String;
monoid attribute ignored :: Boolean with false, ||;
monoid attribute marking :: Boolean with false, ||;
monoid attribute acode :: String;
monoid attribute opPrecedence :: Maybe<Integer> with nothing(), orElse;
monoid attribute opAssociation :: Maybe<String> with nothing(), orElse; -- TODO type?
monoid attribute prefixSeperator :: Maybe<String> with nothing(), orElse;
monoid attribute prefixSeperatorToApply :: Maybe<String> with nothing(), orElse;
monoid attribute prettyName :: Maybe<String> with nothing(), orElse;
autocopy attribute terminalName :: String;

monoid attribute dominates_ :: [copper:ElementReference] with [], ++;
monoid attribute submits_ :: [copper:ElementReference] with [], ++;
monoid attribute lexerClasses :: [copper:ElementReference] with [], ++;

{--
 - Modifiers for terminals.
 -}
nonterminal SyntaxTerminalModifiers with cstEnv, cstErrors, classTerminalContribs, superClasses, subClasses, dominatesXML,
  submitsXML, ignored, acode, lexerclassesXML, opPrecedence, opAssociation, prefixSeperator, prefixSeperatorToApply, componentGrammarMarkingTerminals,
  marking, terminalName, prettyName, dominates_, submits_, lexerClasses;

propagate cstErrors, classTerminalContribs, dominatesXML,
    submitsXML, ignored, acode, lexerclassesXML, opPrecedence, opAssociation, prefixSeperator, prefixSeperatorToApply,
    marking, prettyName, dominates_, submits_, lexerClasses
  on SyntaxTerminalModifiers;

abstract production consTerminalMod
top::SyntaxTerminalModifiers ::= h::SyntaxTerminalModifier  t::SyntaxTerminalModifiers
{  
  top.cstErrors <-
    if h.prefixSeperator.isJust && t.prefixSeperator.isJust
    then ["Multiple prefix separators for terminal " ++ top.terminalName]
    else [];
}

abstract production nilTerminalMod
top::SyntaxTerminalModifiers ::= 
{}



{--
 - Modifiers for terminals.
 -}
closed nonterminal SyntaxTerminalModifier with cstEnv, cstErrors,
  classTerminalContribs, superClasses, subClasses, dominatesXML, submitsXML,
  dominates_, submits_, lexerClasses, ignored, acode, lexerclassesXML,
  opPrecedence, opAssociation, prefixSeperator, prefixSeperatorToApply,
  componentGrammarMarkingTerminals, marking, terminalName, prettyName;

{- We default ALL attributes, so we can focus only on those that are interesting in each case... -}
aspect default production
top::SyntaxTerminalModifier ::=
{
  -- Empty values as defaults
  propagate cstErrors, classTerminalContribs, dominatesXML, submitsXML,
    dominates_, submits_, lexerClasses, ignored, acode, lexerclassesXML,
    opPrecedence, opAssociation, prefixSeperator, prefixSeperatorToApply,
    marking, prettyName;
}

{--
 - If present, it's an ignore terminal, otherwise ordinary terminal.
 - Copper has no notion of an ignore terminal, this is translated away.
 -}
abstract production termIgnore
top::SyntaxTerminalModifier ::=
{
  top.ignored := true;
}
{--
 - If present, this is a Marking terminal. In the default translation,
 - this does nothing.
 -}
abstract production termMarking
top::SyntaxTerminalModifier ::=
{
  top.marking := true;
}
{--
 - The terminal's precedence. (Resolves shift/reduce conflicts)
 -}
abstract production termPrecedence
top::SyntaxTerminalModifier ::= lvl::Integer
{
  top.opPrecedence := just(lvl);
}
{--
 - The terminal's association. Either left, right, or nonassoc. TODO: a type?
 -}
abstract production termAssociation
top::SyntaxTerminalModifier ::= direction::String
{
  top.opAssociation := just(direction);
}
{--
 - The terminal's "pretty name". Used for error messages.
 -}
abstract production termPrettyName
top::SyntaxTerminalModifier ::= prettyName::String
{
  top.prettyName := just(prettyName);
}
{--
 - The terminal's lexer classes.
 -}
abstract production termClasses
top::SyntaxTerminalModifier ::= cls::[String]
{
  production allCls :: [String] = unions(cls :: lookupStrings(cls, top.superClasses));
  local allClsRefsL :: [[Decorated SyntaxDcl]] = lookupStrings(allCls, top.cstEnv);
  production allClsRefs :: [Decorated SyntaxDcl] =
    flatMap(\ sds::[Decorated SyntaxDcl] -> if null(sds) then [] else [head(sds)], allClsRefsL);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Lexer Class " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from lexer class on terminal " ++ top.terminalName ++ ")"],
                   zipWith(pair, allCls, allClsRefsL)); 
  top.classTerminalContribs := map(pair(_, top.terminalName), allCls);
  -- We "translate away" lexer classes dom/sub, by moving that info to the terminals (here)
  top.dominatesXML := implode("", map((.classDomContribsXML), allClsRefs));
  top.submitsXML := implode("", map((.classSubContribsXML), allClsRefs));
  top.lexerclassesXML := implode("", map(xmlCopperRef, allClsRefs));

  top.dominates_ := flatMap((.domContribs), allClsRefs);
  top.submits_ := flatMap((.subContribs), allClsRefs);
  top.lexerClasses := map((.copperElementReference), allClsRefs);
  
  local termSeps :: [Maybe<String>] = map((.prefixSeperator), allClsRefs);
  top.prefixSeperator := foldr(orElse, nothing(), termSeps);
  top.cstErrors <-
    if length(catMaybes(termSeps)) > 1
    then ["Multiple prefix separators for terminal " ++ top.terminalName]
    else [];
}
{--
 - The submits list for the terminal. Either lexer classes or terminals.
 -}
abstract production termSubmits
top::SyntaxTerminalModifier ::= sub::[String]
{
  production allSubs :: [String] = unions(sub :: lookupStrings(sub, top.subClasses));
  production subRefs :: [[Decorated SyntaxDcl]] = lookupStrings(allSubs, top.cstEnv);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Terminal / Lexer Class " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from submit clause on terminal " ++ top.terminalName ++ ")"],
                   zipWith(pair, sub, subRefs)); 
  top.submitsXML := implode("", map(xmlCopperRef, map(head, subRefs)));
  top.submits_ := flatMap((.subContribs), map(head, subRefs));
}
{--
 - The dominates list for the terminal. Either lexer classes or terminals.
 -}
abstract production termDominates
top::SyntaxTerminalModifier ::= dom::[String]
{
  production allDoms :: [String] = unions(dom :: lookupStrings(dom, top.subClasses));
  production domRefs :: [[Decorated SyntaxDcl]] = lookupStrings(allDoms, top.cstEnv);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Terminal / Lexer Class " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from dominates clause on terminal " ++ top.terminalName ++ ")"],
                   zipWith(pair, dom, domRefs)); 
  top.dominatesXML := implode("", map(xmlCopperRef, map(head, domRefs)));
  top.dominates_ := flatMap((.domContribs), map(head, domRefs));
}
{--
 - The action to take whenever this terminal is SHIFTed.
 -}
abstract production termAction
top::SyntaxTerminalModifier ::= acode::String
{
  top.acode := acode;
}
{--
 - The prefix separator to use for the terminal.
 - Doesn't seem super useful, but support this on terminals too for consistency
 -}
abstract production termPrefixSeperator
top::SyntaxTerminalModifier ::= sep::String
{
  top.prefixSeperator := just(sep);
}
{--
 - The terminals/grammars prefixed by this terminal, for which to use their separator.
 -}
abstract production termUsePrefixSeperatorFor
top::SyntaxTerminalModifier ::= terms::[String] grams::[String]
{
  production allTerms :: [String] = terms ++ concat(concat(lookupStrings(grams, top.componentGrammarMarkingTerminals)));

  production termRefs :: [[Decorated SyntaxDcl]] = lookupStrings(allTerms, top.cstEnv);
  top.prefixSeperatorToApply :=
    case termRefs of
    | [] -> nothing()
    | [ref] :: _ -> ref.prefixSeperator
    | _ -> error("Lookup failure not caught during error checking")
    end;
  
  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Terminal " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from use prefix seperator for clause for terminal)"],
                   zipWith(pair, terms, termRefs));
  
  top.cstErrors <-
    flatMap(
      \ s::Decorated SyntaxDcl ->
        if !s.prefixSeperator.isJust
        then ["Terminal " ++ s.fullName ++ " does not define a prefix separator, and must use an explicit terminal to define a prefix."]
        else [],
      map(head, termRefs));
  
  {- TODO: This really should be some sort of warning, not an error, I think.
  top.cstErrors <-
    if null(allTerms)
    then [top.terminalName ++ " does not prefix any terminals"]
    else [];
  -}
  
  local distinctSepTermRefs :: [Decorated SyntaxDcl] =
    nubBy(
      \ s1::Decorated SyntaxDcl s2::Decorated SyntaxDcl ->
        case s1.prefixSeperator, s2.prefixSeperator of
        | just(ps1), just(ps2) -> ps1 == ps2
        | _, _ -> false
        end,
      map(head, termRefs));
  top.cstErrors <-
    if length(distinctSepTermRefs) > 1
    then ["Terminals " ++ implode(", ", map((.fullName), distinctSepTermRefs)) ++
          " have different prefix separators, so their prefixes must be specified seperately"]
    else [];
}

