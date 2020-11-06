grammar silver:definition:concrete_syntax:ast;

imports silver:definition:concrete_syntax only productionName;

monoid attribute productionPrecedence :: Maybe<Integer> with nothing(), orElse;
-- acode from terminal modifiers
monoid attribute customLayout :: Maybe<[String]> with nothing(), orElse;
monoid attribute productionOperator :: Maybe<String> with nothing(), orElse;

{--
 - Modifiers for productions.
 -}
nonterminal SyntaxProductionModifiers with cstEnv, cstErrors, acode, productionPrecedence, customLayout, productionOperator, productionName;

propagate cstErrors, acode, productionPrecedence, customLayout, productionOperator
  on SyntaxProductionModifiers;

abstract production consProductionMod
top::SyntaxProductionModifiers ::= h::SyntaxProductionModifier  t::SyntaxProductionModifiers
{}

abstract production nilProductionMod
top::SyntaxProductionModifiers ::= 
{}


{--
 - Modifiers for productions.
 -}
nonterminal SyntaxProductionModifier with cstEnv, cstErrors, acode, productionPrecedence, customLayout, productionOperator, productionName;

aspect default production
top::SyntaxProductionModifier ::=
{
  -- Empty values as defaults
  propagate cstErrors, acode, productionPrecedence, customLayout, productionOperator;
}

{--
 - The precedence for the production. (Resolves reduce/reduce conflicts.)
 -}
abstract production prodPrecedence
top::SyntaxProductionModifier ::= lvl::Integer
{
  top.productionPrecedence := just(lvl);
}
{--
 - The terminal this production uses for shift/reduce conflict resolution.
 - By default, the last terminal in the production? TODO
 -}
abstract production prodOperator
top::SyntaxProductionModifier ::= term::String
{
  local termRef :: [Decorated SyntaxDcl] = searchEnvTree(term, top.cstEnv);
  
  top.cstErrors := if !null(termRef) then [] 
                   else ["Terminal " ++ term ++ " was referenced but " ++
                         "this grammar was not included in this parser. (Referenced from operator clause on production " ++ top.productionName ++ ")"];
  top.productionOperator := just(xmlCopperRef(head(termRef)));
}
{--
 - The action to perform when this production is REDUCEd.
 -}
abstract production prodAction
top::SyntaxProductionModifier ::= acode::String
{
  top.acode := acode;
}
{--
 - The layout for this production.
 -}
abstract production prodLayout
top::SyntaxProductionModifier ::= terms::[String]
{
  local termRefs :: [[Decorated SyntaxDcl]] = lookupStrings(terms, top.cstEnv);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Terminal " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from layout clause on production " ++ top.productionName ++ ")"],
                   zipWith(pair, terms, termRefs));

  top.customLayout := just(terms);
}
