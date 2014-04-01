
parser PAparse :: UseDcls {
  copper_features;
}

-- Piggy backing a test of escape characters in copper XML here.
-- These affect this test not at all, as long as copper doesn't report parse errors on its input
terminal QuoteLiteral '"'; -- Separate test. Ensure it's escaped properly in the XML


synthesized attribute unknownsused :: [String];
nonterminal UseDcl with unknownsused;
nonterminal UseDcls with unknownsused;
terminal KnownTerm /[a-z][a-z]*/;
terminal UnknownTerm /[a-z][a-z]*/;
terminal BangTerm '!';
ignore terminal Space ' ';

concrete production oneud
top::UseDcls ::= h::UseDcl
{
  top.unknownsused = h.unknownsused;
}
concrete production consud
top::UseDcls ::= h::UseDcl t::UseDcls
{
  top.unknownsused = h.unknownsused ++ t.unknownsused;
}
concrete production unUse
top::UseDcl ::= l::UnknownTerm
{
  top.unknownsused = [l.lexeme];
}
concrete production kUse
top::UseDcl ::= l::KnownTerm
{
  top.unknownsused = [];
}
concrete production dDcl
top::UseDcl ::= '!' l::UnknownTerm
{
  top.unknownsused = [];
}
action {
  knownlist = l.lexeme :: knownlist;
}

disambiguate KnownTerm, UnknownTerm {
  pluck if containsBy(stringEq, lexeme, knownlist) then KnownTerm else UnknownTerm;
}


-- Putting this below everything on purpose, to check if it's initialized first
parser attribute knownlist :: [String]  action {
  knownlist = [];
};

-- THE TESTS

equalityTest ( PAparse("!foo foo", "asdf").parseSuccess, true, Boolean, copper_tests ) ;
equalityTest ( PAparse("!foo foo", "").parseTree.unknownsused, [], [String], copper_tests ) ;

equalityTest ( PAparse("!foo bar", "").parseSuccess, true, Boolean, copper_tests ) ;
equalityTest ( PAparse("!foo bar", "").parseTree.unknownsused, ["bar"], [String], copper_tests ) ;

equalityTest ( PAparse("!foo foo bar", "").parseTree.unknownsused, ["bar"], [String], copper_tests ) ;
equalityTest ( PAparse("!foo bar foo", "").parseTree.unknownsused, ["bar"], [String], copper_tests ) ;
equalityTest ( PAparse("!foo f o o", "").parseTree.unknownsused, ["f", "o", "o"], [String], copper_tests ) ;
equalityTest ( PAparse("!foo !bar foo bar foo", "").parseTree.unknownsused, [], [String], copper_tests ) ;
equalityTest ( PAparse("!foo !bar foo foo", "").parseTree.unknownsused, [], [String], copper_tests ) ;
equalityTest ( PAparse("!foo !bar foo bar foo baz", "").parseTree.unknownsused, ["baz"], [String], copper_tests ) ;

