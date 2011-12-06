grammar silver:definition:concrete_syntax:ast;

synthesized attribute productionPrecedence :: Maybe<Integer>;
-- acode from terminal modifiers
synthesized attribute customLayout :: Maybe<String>;
synthesized attribute productionOperator :: Maybe<String>;

{--
 - Modifiers for productions.
 -}
nonterminal SyntaxProductionModifiers with cstEnv, cstErrors, acode, productionPrecedence, customLayout, productionOperator, unparses;

abstract production consProductionMod
top::SyntaxProductionModifiers ::= h::SyntaxProductionModifier  t::SyntaxProductionModifiers
{
  top.cstErrors := h.cstErrors ++ t.cstErrors;
  top.acode = h.acode ++ t.acode;
  top.customLayout = orElse(h.customLayout, t.customLayout);
  top.productionOperator = orElse(h.productionOperator, t.productionOperator);
  top.productionPrecedence = orElse(h.productionPrecedence, t.productionPrecedence);
  top.unparses = h.unparses ++ t.unparses;
}

abstract production nilProductionMod
top::SyntaxProductionModifiers ::= 
{
  top.cstErrors := [];
  top.acode = "";
  top.customLayout = nothing();
  top.productionOperator = nothing();
  top.productionPrecedence = nothing();
  top.unparses = [];
}


{--
 - Modifiers for productions.
 -}
nonterminal SyntaxProductionModifier with cstEnv, cstErrors, acode, productionPrecedence, customLayout, productionOperator, unparses;

{--
 - The precedence for the production. (Resolves reduce/reduce conflicts.)
 -}
abstract production prodPrecedence
top::SyntaxProductionModifier ::= lvl::Integer
{
  top.cstErrors := [];
  top.acode = "";
  top.customLayout = nothing();
  top.productionOperator = nothing();
  top.productionPrecedence = just(lvl); -- the interesting one
  top.unparses = ["prec(" ++ toString(lvl) ++ ")"];
}
{--
 - The terminal this production uses for shift/reduce conflict resolution.
 - By default, the last terminal in the production? TODO
 -}
abstract production prodOperator
top::SyntaxProductionModifier ::= term::String
{
  -- TODO look up that term and error
  top.cstErrors := [];
  top.acode = "";
  top.customLayout = nothing();
  top.productionOperator = just(term); -- the interesting one
  top.productionPrecedence = nothing();
  top.unparses = ["oper(" ++ quoteString(term) ++ ")"];
}
{--
 - The action to perform when this production is REDUCEd.
 -}
abstract production prodAction
top::SyntaxProductionModifier ::= acode::String
{
  top.cstErrors := [];
  top.acode = acode; -- the interesting one
  top.customLayout = nothing();
  top.productionOperator = nothing();
  top.productionPrecedence = nothing();
  top.unparses = ["acode(\"" ++ escapeString(acode) ++ "\")"];
}
{--
 - The layout for this production.
 -}
abstract production prodLayout
top::SyntaxProductionModifier ::= terms::[String]
{
  -- TODO: look up those terms and error
  top.cstErrors := [];
  top.acode = "";
  top.customLayout = just(implode("", map(xmlCopperTermRef, terms))); -- the interesting one
  top.productionOperator = nothing();
  top.productionPrecedence = nothing();
  top.unparses = ["layout(" ++ unparseStrings(terms) ++ ")"];
}

