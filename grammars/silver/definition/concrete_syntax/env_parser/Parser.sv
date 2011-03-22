grammar silver:definition:concrete_syntax:env_parser;

import silver:definition:env;
import silver:definition:env:env_parser;

import silver:definition:concrete_syntax hiding Ignore_kwd, Precedence_kwd, Association_kwd, Left_kwd, Association_kwd, Right_kwd, Precedence_kwd, Operator_kwd;
import silver:definition:regex hiding RegexRBrack_t, RegexLBrack_t, RegexLParen_t, RegexRParen_t; -- TODO: a bit of a hack?

import silver:definition:core only compiledGrammars;


terminal TerminalsTerm 'terminals' lexer classes {C_1};
terminal NonterminalsTerm 'nonterminals' lexer classes {C_1};
terminal RulesTerm 'rules' lexer classes {C_1};
terminal ParserTerm 'parser' lexer classes {C_1};

attribute terminalDcls, nonTerminalDcls, ruleDcls, parserDcls occurs on IRootSpecParts, IRootSpecPart;

aspect production parserRootSpec
top::RootSpec ::= p::IRootSpecParts _{
  top.parserDcls = p.parserDcls;
  top.terminalDcls = p.terminalDcls;
  top.nonTerminalDcls = p.nonTerminalDcls;
  top.ruleDcls = p.ruleDcls;
}

aspect production aRoot1
top::IRootSpecParts ::= r::IRootSpecPart{
  top.terminalDcls = r.terminalDcls;
  top.nonTerminalDcls = r.nonTerminalDcls;
  top.ruleDcls = r.ruleDcls;
  top.parserDcls = r.parserDcls;
}

aspect production aRoot2
top::IRootSpecParts ::= r1::IRootSpecPart r2::IRootSpecParts{
  top.terminalDcls = r1.terminalDcls ++ r2.terminalDcls;
  top.nonTerminalDcls = r1.nonTerminalDcls ++ r2.nonTerminalDcls;
  top.ruleDcls = r1.ruleDcls ++ r2.ruleDcls;
  top.parserDcls = r1.parserDcls ++ r2.parserDcls;
}

aspect production aRootSpecDefault
top::IRootSpecPart ::= {
  top.terminalDcls = [];
  top.nonTerminalDcls = [];
  top.ruleDcls = [];
  top.parserDcls = [];
}

concrete production aRootTerminals
top::IRootSpecPart ::= t::TerminalsTerm s::ITerminalSpecs {
  top.terminalDcls = s.terminalDcls;
  forwards to aRootSpecDefault();
}

nonterminal ITerminalSpecs with terminalDcls;
nonterminal ITerminalSpec with terminalDcls;
nonterminal ITerminalSpecInner with terminalDcls;

concrete production aTerminalSpecNone
top::ITerminalSpecs ::= '[' ']' {
  top.terminalDcls = [];
}

concrete production aTerminalSpecOne
top::ITerminalSpecs ::= '[' s::ITerminalSpecInner ']' {
  top.terminalDcls = s.terminalDcls;
}

concrete production aTerminalSpecInnerOne
top::ITerminalSpecInner ::= s::ITerminalSpec {
  top.terminalDcls = s.terminalDcls;
}

concrete production aTerminalSpecInnerCons
top::ITerminalSpecInner ::= s1::ITerminalSpec ',' s2::ITerminalSpecInner {
  top.terminalDcls = s1.terminalDcls ++ s2.terminalDcls;
}

concrete production aTerminalSpecDef
top::ITerminalSpec ::= '(' n::Name ','  m::ITerminalModifiers ',' '/' r::Regex_R '/' ')'{
  top.terminalDcls = [terminalSpec(n.aname, m.terminalModifiers, r)];
}

nonterminal ITerminalModifiers with terminalModifiers;
nonterminal ITerminalModifiersInner with terminalModifiers;
nonterminal ITerminalModifierSpec with terminalModifiers;

concrete production aTerminalModifiersNone
top::ITerminalModifiers ::= '[' ']' {
  top.terminalModifiers = [];
}

concrete production aTerminalModifiersOne
top::ITerminalModifiers ::= '[' d::ITerminalModifiersInner ']' {
  top.terminalModifiers = d.terminalModifiers;
}

concrete production aTerminalModifiersInnerOne
top::ITerminalModifiersInner ::= d::ITerminalModifierSpec {
  top.terminalModifiers = d.terminalModifiers;
}

concrete production aTerminalModifierInnersCons
top::ITerminalModifiersInner ::= d1::ITerminalModifierSpec ',' d2::ITerminalModifiersInner {
  top.terminalModifiers = d1.terminalModifiers ++ d2.terminalModifiers;
}

terminal IgnoreTerm 'ignore' lexer classes {C_1};
concrete production aTerminalModifierSpecIgnore
top::ITerminalModifierSpec ::= 'ignore' {
  top.terminalModifiers = [ignoreTerminalModifierSpec()];
}


terminal PrecedenceTerm 'precedence' lexer classes {C_1};
concrete production aTerminalModifierSpecPrecedence
top::ITerminalModifierSpec ::= 'precedence' n::Num_t {
  top.terminalModifiers = [precedenceTerminalModifierSpec(toInt(n.lexeme))];
}

terminal AssociationTerm 'association' lexer classes {C_1};
terminal LeftTerm 'left' lexer classes {C_1};
terminal RightTerm 'right' lexer classes {C_1};

concrete production aTerminalModifierSpecAssocationLeft
top::ITerminalModifierSpec ::= 'association' 'left' {
  top.terminalModifiers = [associationTerminalModifierSpec("left")];
}

concrete production aTerminalModifierSpecAssocationRight
top::ITerminalModifierSpec ::= 'association' 'right' {
  top.terminalModifiers = [associationTerminalModifierSpec("right")];
}

concrete production aRootNonterminals
top::IRootSpecPart ::= n::NonterminalsTerm ns::INames{
  top.nonTerminalDcls = makeNonTerminals(ns.names);
  forwards to aRootSpecDefault();
}

function makeNonTerminals
[Decorated NonTerminalSpec] ::= ns::[String]{
  return if null(ns) then [] else cons(nonTerminalSpec(head(ns)), makeNonTerminals(tail(ns)));
}

concrete production aRootRules
top::IRootSpecPart ::= n::RulesTerm r::IRuleSpecs{
  top.ruleDcls = r.ruleDcls; 
  forwards to aRootSpecDefault();
}

nonterminal IRuleSpecs with ruleDcls;
nonterminal IRuleSpecInner with ruleDcls;
nonterminal IRuleSpec with ruleDcls;

concrete production aRuleSpecNone
top::IRuleSpecs ::= '[' ']' {
  top.ruleDcls = [];
}

concrete production aRuleSpecOne
top::IRuleSpecs ::= '[' s::IRuleSpecInner ']' {
  top.ruleDcls = s.ruleDcls;
}

concrete production aRuleSpecInnerOne
top::IRuleSpecInner ::= d::IRuleSpec {
  top.ruleDcls = d.ruleDcls;
}

concrete production aRuleSpecInnersCons
top::IRuleSpecInner ::= d1::IRuleSpec ',' d2::IRuleSpecInner {
  top.ruleDcls = d1.ruleDcls ++ d2.ruleDcls;
}

concrete production aRuleSpecDef
top::IRuleSpec ::= '(' n::Name ',' rhs::IRHSSpecs ')' {
  top.ruleDcls = [ruleSpec(n.aname, rhs.ruleRHSSpec)];
}

nonterminal IRHSSpecs with ruleRHSSpec;
nonterminal IRHSSpecInner with ruleRHSSpec;
nonterminal IRHSSpec with ruleRHSSpec;

concrete production aRHSSpecNone
top::IRHSSpecs ::= '[' ']' {
  top.ruleRHSSpec = [];
}

concrete production aRHSSpecOne
top::IRHSSpecs ::= '[' s::IRHSSpecInner ']' {
  top.ruleRHSSpec = s.ruleRHSSpec;
}

concrete production aRHSSpecInnerOne
top::IRHSSpecInner ::= d::IRHSSpec {
  top.ruleRHSSpec = d.ruleRHSSpec;
}

concrete production aRHSSpecInnersCons
top::IRHSSpecInner ::= d1::IRHSSpec ',' d2::IRHSSpecInner {
  top.ruleRHSSpec = d1.ruleRHSSpec ++ d2.ruleRHSSpec;
}

concrete production aRHSSpecDef
top::IRHSSpec ::= '(' gn::Name ',' fn::Name ',' ns::INames ',' pm::IProductionModifiers ')' {
  top.ruleRHSSpec = [rhsSpec(gn.aname, fn.aname, ns.names, pm.productionModifiers)];
}

nonterminal IProductionModifiers with productionModifiers;
nonterminal IProductionModifiersInner with productionModifiers;
nonterminal IProductionModifierSpec with productionModifiers;

concrete production aProductionModifiersNone
top::IProductionModifiers ::= '[' ']' {
  top.productionModifiers = [];
}

concrete production aProductionModifiersOne
top::IProductionModifiers ::= '[' d::IProductionModifiersInner ']' {
  top.productionModifiers = d.productionModifiers;
}

concrete production aProductionModifiersInnerOne
top::IProductionModifiersInner ::= d::IProductionModifierSpec {
  top.productionModifiers = d.productionModifiers;
}

concrete production aProductionModifierInnersCons
top::IProductionModifiersInner ::= d1::IProductionModifierSpec ',' d2::IProductionModifiersInner {
  top.productionModifiers = d1.productionModifiers ++ d2.productionModifiers;
}

concrete production aProductionModifierSpecPrecedence
top::IProductionModifierSpec ::= 'precedence' n::Num_t {
  top.productionModifiers = [precedenceProductionModifierSpec(toInt(n.lexeme))];
}

terminal OperatorTerm 'operator' lexer classes {C_1};
concrete production aProductionModifierSpecOperator
top::IProductionModifierSpec ::= 'operator' n::Name {
  top.productionModifiers = [operatorProductionModifierSpec(n.aname)];
}


concrete production aRootParsers
top::IRootSpecPart ::= t::ParserTerm n::Name ',' s::Name ',' gs::INames {
  top.parserDcls = [parserSpecFromList(loc(t.filename,t.line,t.column), n.aname, s.aname, gs.names, top.compiledGrammars)];
  forwards to aRootSpecDefault();
}
