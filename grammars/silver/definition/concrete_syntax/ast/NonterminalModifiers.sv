grammar silver:definition:concrete_syntax:ast;

imports silver:definition:core only nonterminalName;

{--
 - Modifiers for nonterminals.
 -}

synthesized attribute tracked :: Boolean;

nonterminal SyntaxNonterminalModifiers with cstEnv, cstErrors, customLayout, nonterminalName, tracked;

abstract production consNonterminalMod
top::SyntaxNonterminalModifiers ::= h::SyntaxNonterminalModifier  t::SyntaxNonterminalModifiers
{
  top.cstErrors := h.cstErrors ++ t.cstErrors;
  top.customLayout = orElse(h.customLayout, t.customLayout);
  top.tracked = h.tracked || t.tracked;
}

abstract production nilNonterminalMod
top::SyntaxNonterminalModifiers ::= 
{
  top.cstErrors := [];
  top.customLayout = nothing();
  top.tracked = false;
}


{--
 - Modifiers for nonterminals.
 -}
nonterminal SyntaxNonterminalModifier with cstEnv, cstErrors, customLayout, nonterminalName, tracked;

aspect default production
top::SyntaxNonterminalModifier ::=
{
  top.cstErrors := [];
  top.customLayout = nothing();
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
                   zipWith(pair, terms, termRefs));

  top.customLayout = just(terms);
}


abstract production tracked
top::SyntaxNonterminalModifier ::=
{
  top.tracked = true;
}