grammar silver:compiler:definition:concrete_syntax:ast;

imports silver:compiler:definition:core only nonterminalName;

{--
 - Modifiers for nonterminals.
 -}

nonterminal SyntaxNonterminalModifiers with compareTo, isEqual, cstEnv, cstErrors, customLayout, nonterminalName;

propagate compareTo, isEqual, cstEnv, cstErrors, customLayout, nonterminalName on SyntaxNonterminalModifiers;

abstract production consNonterminalMod
top::SyntaxNonterminalModifiers ::= h::SyntaxNonterminalModifier  t::SyntaxNonterminalModifiers
{}

abstract production nilNonterminalMod
top::SyntaxNonterminalModifiers ::= 
{}


{--
 - Modifiers for nonterminals.
 -}
nonterminal SyntaxNonterminalModifier with compareTo, isEqual, cstEnv, cstErrors, customLayout, nonterminalName;

propagate compareTo, isEqual on SyntaxNonterminalModifier;

aspect default production
top::SyntaxNonterminalModifier ::=
{
  -- Empty values as defaults
  propagate cstErrors, customLayout;
}

{--
 - The layout for this nonterminal.
 -}
abstract production ntLayout
top::SyntaxNonterminalModifier ::= terms::[String]
{
  local termRefs :: [[Decorated SyntaxDcl]] = lookupStrings(terms, top.cstEnv);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Terminal " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from layout clause on nonterminal " ++ top.nonterminalName ++ ")"],
                   zip(terms, termRefs));

  top.customLayout := just(terms);
}

