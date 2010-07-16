grammar silver:definition:concrete_syntax:env_parser;

import silver:definition:env;
import silver:definition:env:parser;

import silver:definition:concrete_syntax hiding Ignore_kwd, Precedence_kwd, Association_kwd, Left_kwd, Association_kwd, Right_kwd, Precedence_kwd, Operator_kwd;
import silver:definition:regex hiding RegexRBrack_t, RegexLBrack_t, RegexLParen_t, RegexRParen_t; -- TODO: a bit of a hack?

import silver:definition:core only compiledGrammars;


terminal TerminalsTerm 'terminals' lexer classes {C_1};
terminal NonterminalsTerm 'nonterminals' lexer classes {C_1};
terminal RulesTerm 'rules' lexer classes {C_1};
terminal ParserTerm 'parser' lexer classes {C_1};

attribute terminalDcls, nonTerminalDcls, ruleDcls, parserDcls occurs on aRootSpecParts, aRootSpecPart;

aspect production parserRootSpec
top::RootSpec ::= p::aRootSpecParts _{
  top.parserDcls = p.parserDcls;
  top.terminalDcls = p.terminalDcls;
  top.nonTerminalDcls = p.nonTerminalDcls;
  top.ruleDcls = p.ruleDcls;
}

aspect production aRoot1
top::aRootSpecParts ::= r::aRootSpecPart{
  top.terminalDcls = r.terminalDcls;
  top.nonTerminalDcls = r.nonTerminalDcls;
  top.ruleDcls = r.ruleDcls;
  top.parserDcls = r.parserDcls;
}

aspect production aRoot2
top::aRootSpecParts ::= r1::aRootSpecPart r2::aRootSpecParts{
  top.terminalDcls = r1.terminalDcls ++ r2.terminalDcls;
  top.nonTerminalDcls = r1.nonTerminalDcls ++ r2.nonTerminalDcls;
  top.ruleDcls = r1.ruleDcls ++ r2.ruleDcls;
  top.parserDcls = r1.parserDcls ++ r2.parserDcls;
}

aspect production aRootSpecDefault
top::aRootSpecPart ::= {
  top.terminalDcls = [];
  top.nonTerminalDcls = [];
  top.ruleDcls = [];
  top.parserDcls = [];
}

concrete production aRootTerminals
top::aRootSpecPart ::= t::TerminalsTerm s::aTerminalSpecs {
  top.terminalDcls = s.terminalDcls;
  forwards to aRootSpecDefault();
}

nonterminal aTerminalSpecs, aTerminalSpec, aTerminalSpecInner with terminalDcls;

concrete production aTerminalSpecNone
top::aTerminalSpecs ::= '[' ']' {
  top.terminalDcls = [];
}

concrete production aTerminalSpecOne
top::aTerminalSpecs ::= '[' s::aTerminalSpecInner ']' {
  top.terminalDcls = s.terminalDcls;
}

concrete production aTerminalSpecInnerOne
top::aTerminalSpecInner ::= s::aTerminalSpec {
  top.terminalDcls = s.terminalDcls;
}

concrete production aTerminalSpecInnerCons
top::aTerminalSpecInner ::= s1::aTerminalSpec ',' s2::aTerminalSpecInner {
  top.terminalDcls = s1.terminalDcls ++ s2.terminalDcls;
}

concrete production aTerminalSpecDef
top::aTerminalSpec ::= '(' n::Name ','  m::aTerminalModifiers ',' '/' r::Regex_R '/' ')'{
  top.terminalDcls = [terminalSpec(n.aname, m.terminalModifiers, r)];
}

nonterminal aTerminalModifiers with terminalModifiers;
nonterminal aTerminalModifiersInner with terminalModifiers;
nonterminal aTerminalModifierSpec with terminalModifiers;

concrete production aTerminalModifiersNone
top::aTerminalModifiers ::= '[' ']' {
  top.terminalModifiers = [];
}

concrete production aTerminalModifiersOne
top::aTerminalModifiers ::= '[' d::aTerminalModifiersInner ']' {
  top.terminalModifiers = d.terminalModifiers;
}

concrete production aTerminalModifiersInnerOne
top::aTerminalModifiersInner ::= d::aTerminalModifierSpec {
  top.terminalModifiers = d.terminalModifiers;
}

concrete production aTerminalModifierInnersCons
top::aTerminalModifiersInner ::= d1::aTerminalModifierSpec ',' d2::aTerminalModifiersInner {
  top.terminalModifiers = d1.terminalModifiers ++ d2.terminalModifiers;
}

terminal IgnoreTerm 'ignore' lexer classes {C_1};
concrete production aTerminalModifierSpecIgnore
top::aTerminalModifierSpec ::= 'ignore' {
  top.terminalModifiers = [ignoreTerminalModifierSpec()];
}


terminal PrecedenceTerm 'precedence' lexer classes {C_1};
concrete production aTerminalModifierSpecPrecedence
top::aTerminalModifierSpec ::= 'precedence' n::number {
  top.terminalModifiers = [precedenceTerminalModifierSpec(toInt(n.lexeme))];
}

terminal AssociationTerm 'association' lexer classes {C_1};
terminal LeftTerm 'left' lexer classes {C_1};
terminal RightTerm 'right' lexer classes {C_1};

concrete production aTerminalModifierSpecAssocationLeft
top::aTerminalModifierSpec ::= 'association' 'left' {
  top.terminalModifiers = [associationTerminalModifierSpec("left")];
}

concrete production aTerminalModifierSpecAssocationRight
top::aTerminalModifierSpec ::= 'association' 'right' {
  top.terminalModifiers = [associationTerminalModifierSpec("right")];
}

concrete production aRootNonterminals
top::aRootSpecPart ::= n::NonterminalsTerm ns::aNames{
  top.nonTerminalDcls = makeNonTerminals(ns.names);
  forwards to aRootSpecDefault();
}

function makeNonTerminals
[Decorated NonTerminalSpec] ::= ns::[String]{
  return if null(ns) then [] else cons(nonTerminalSpec(head(ns)), makeNonTerminals(tail(ns)));
}

concrete production aRootRules
top::aRootSpecPart ::= n::RulesTerm r::aRuleSpecs{
  top.ruleDcls = r.ruleDcls; 
  forwards to aRootSpecDefault();
}

nonterminal aRuleSpecs, aRuleSpecInner, aRuleSpec with ruleDcls;

concrete production aRuleSpecNone
top::aRuleSpecs ::= '[' ']' {
  top.ruleDcls = [];
}

concrete production aRuleSpecOne
top::aRuleSpecs ::= '[' s::aRuleSpecInner ']' {
  top.ruleDcls = s.ruleDcls;
}

concrete production aRuleSpecInnerOne
top::aRuleSpecInner ::= d::aRuleSpec {
  top.ruleDcls = d.ruleDcls;
}

concrete production aRuleSpecInnersCons
top::aRuleSpecInner ::= d1::aRuleSpec ',' d2::aRuleSpecInner {
  top.ruleDcls = d1.ruleDcls ++ d2.ruleDcls;
}

concrete production aRuleSpecDef
top::aRuleSpec ::= '(' n::Name ',' rhs::aRHSSpecs ')' {
  top.ruleDcls = [ruleSpec(n.aname, rhs.ruleRHSSpec)];
}

nonterminal aRHSSpecs, aRHSSpecInner, aRHSSpec with ruleRHSSpec;

concrete production aRHSSpecNone
top::aRHSSpecs ::= '[' ']' {
  top.ruleRHSSpec = [];
}

concrete production aRHSSpecOne
top::aRHSSpecs ::= '[' s::aRHSSpecInner ']' {
  top.ruleRHSSpec = s.ruleRHSSpec;
}

concrete production aRHSSpecInnerOne
top::aRHSSpecInner ::= d::aRHSSpec {
  top.ruleRHSSpec = d.ruleRHSSpec;
}

concrete production aRHSSpecInnersCons
top::aRHSSpecInner ::= d1::aRHSSpec ',' d2::aRHSSpecInner {
  top.ruleRHSSpec = d1.ruleRHSSpec ++ d2.ruleRHSSpec;
}

concrete production aRHSSpecDef
top::aRHSSpec ::= '(' gn::Name ',' fn::Name ',' ns::aNames ',' pm::aProductionModifiers ')' {
  top.ruleRHSSpec = [rhsSpec(gn.aname, fn.aname, ns.names, pm.productionModifiers)];
}

nonterminal aProductionModifiers with productionModifiers;
nonterminal aProductionModifiersInner with productionModifiers;
nonterminal aProductionModifierSpec with productionModifiers;

concrete production aProductionModifiersNone
top::aProductionModifiers ::= '[' ']' {
  top.productionModifiers = [];
}

concrete production aProductionModifiersOne
top::aProductionModifiers ::= '[' d::aProductionModifiersInner ']' {
  top.productionModifiers = d.productionModifiers;
}

concrete production aProductionModifiersInnerOne
top::aProductionModifiersInner ::= d::aProductionModifierSpec {
  top.productionModifiers = d.productionModifiers;
}

concrete production aProductionModifierInnersCons
top::aProductionModifiersInner ::= d1::aProductionModifierSpec ',' d2::aProductionModifiersInner {
  top.productionModifiers = d1.productionModifiers ++ d2.productionModifiers;
}

concrete production aProductionModifierSpecPrecedence
top::aProductionModifierSpec ::= 'precedence' n::number {
  top.productionModifiers = [precedenceProductionModifierSpec(toInt(n.lexeme))];
}

terminal OperatorTerm 'operator' lexer classes {C_1};
concrete production aProductionModifierSpecOperator
top::aProductionModifierSpec ::= 'operator' n::Name {
  top.productionModifiers = [operatorProductionModifierSpec(n.aname)];
}


concrete production aRootParsers
top::aRootSpecPart ::= t::ParserTerm n::Name ',' s::Name ',' gs::aNames {
  top.parserDcls = [parserSpecFromList(n.aname,s.aname,gs.names, top.compiledGrammars)];
  forwards to aRootSpecDefault();
}
