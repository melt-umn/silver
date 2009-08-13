grammar silver:definition:concrete_syntax;
import silver:util;
import silver:definition:env;
import silver:definition:core;

nonterminal RuleSpec with ruleLHS, ruleRHSSpec, unparse;
nonterminal RHSSpec with ruleRHS, ruleName, parserPrecedence, ruleGrammarName, unparse, productionModifiers;

synthesized attribute ruleGrammarName :: String;
synthesized attribute ruleName :: String;
synthesized attribute ruleLHS :: String;
synthesized attribute ruleRHS :: [String];
synthesized attribute ruleRHSSpec :: [Decorated RHSSpec];

synthesized attribute productionModifiers :: [Decorated ProductionModifierSpec];

function ruleSpec
Decorated RuleSpec ::= l::String ns::[Decorated RHSSpec]{
  return decorate i_ruleSpec(l, ns) with {};
}

abstract production i_ruleSpec
top::RuleSpec ::= l::String ns::[Decorated RHSSpec]
{
  top.unparse = "('" ++ l ++ "', [" ++ foldRHSSpec(ns) ++ "])";
  top.ruleLHS = l;
  top.ruleRHSSpec = ns;
}

function foldRHSSpec
String::= l::[Decorated RHSSpec]{
  return if null(l) then "" else (head(l).unparse ++ (if null(tail(l)) then "" else ", " ++ foldRHSSpec(tail(l))));
}


function rhsSpec
Decorated RHSSpec ::= gn::String fn::String ns::[String]  pm::[Decorated ProductionModifierSpec]
{
  return decorate i_rhsSpec(gn, fn, ns, pm) with {};
}

abstract production i_rhsSpec
top::RHSSpec ::= gn::String fn::String ns::[String] pm::[Decorated ProductionModifierSpec]
{
  top.unparse = "('" ++ gn ++ "','" ++ fn ++ "',["  ++(if null(ns) then "" else "'" ++ folds("','", ns) ++ "'") ++ "],[" ++ foldProductionModifiers(pm) ++  "])";
  top.ruleGrammarName = gn;
  top.ruleName = fn;
  top.ruleRHS = ns;
  top.parserPrecedence = findParserPrecedence(pm);

  top.productionModifiers = pm;
}

function foldProductionModifiers
String ::= l::[Decorated ProductionModifierSpec]
{
  return if null(l) then "" else head(l).unparse ++ (if null(tail(l)) then "" else ", " ++ foldProductionModifiers(tail(l)));
}


function findParserPrecedence
Integer ::= l::[Decorated ProductionModifierSpec]{
  return if null(l) then 0 else if head(l).parserPrecedence != 0 then head(l).parserPrecedence else findParserPrecedence(tail(l));
}

nonterminal ProductionModifierSpec with unparse, parserPrecedence;

abstract production defaultProductionModifierSpec
top::ProductionModifierSpec ::={
  top.unparse = "";
  top.parserPrecedence = 0;
}


function precedenceProductionModifierSpec
Decorated ProductionModifierSpec ::= i::Integer{
  return decorate i_precedenceProductionModifierSpec(i) with {};
}

abstract production i_precedenceProductionModifierSpec
top::ProductionModifierSpec ::= i::Integer{
  top.unparse = "precedence " ++ toString(i);
  top.parserPrecedence = i;
  forwards to defaultProductionModifierSpec();
}

function mergeRules
[Decorated RuleSpec] ::= c1::[Decorated RuleSpec]
{
  return if null(c1)
	 then []
	 else mergeRuleSpec(head(c1), mergeRules(tail(c1)));
}

function mergeRuleSpec
[Decorated RuleSpec] ::= c1::Decorated RuleSpec c2::[Decorated RuleSpec]
{
  return if null(c2) then [c1]
         else if head(c2).ruleLHS == c1.ruleLHS
	      then cons(ruleSpec(c1.ruleLHS, head(c2).ruleRHSSpec ++ c1.ruleRHSSpec), tail(c2))
	      else cons(head(c2), mergeRuleSpec(c1, tail(c2)));
}

