grammar silver:modification:collection:env_parser;
import silver:modification:collection;
import silver:definition:env;
import silver:definition:env:parser;
import silver:definition:core only grammarName, location;

terminal SynColTerm 'syncol' lexer classes {C_1};
terminal InhColTerm 'inhcol' lexer classes {C_1};
terminal LocColTerm 'loccol' lexer classes {C_1};
terminal PlusPlusTerm '++' lexer classes {C_1};

nonterminal aOperation with operation;

concrete production operationName
top::aOperation ::= n::Name {
  top.operation = nameOperation(n.aname);
}

concrete production operationPlusPlus
top::aOperation ::= '++' {
  top.operation = plusPlusOperation();
}


concrete production aDclInfoSynthesizedCol
top::aDclInfo ::= 'syncol' '(' l::aLocation ',' fn::Name ',' t::aTypeRep ',' o::aOperation ')'
{
  top.defs = addSynColDcl(top.grammarName, l.location, fn.aname, t.typerep, o.operation, emptyDefs());
}

concrete production aDclInfoInheritedCol
top::aDclInfo ::= 'inhcol' '(' l::aLocation ',' fn::Name ',' t::aTypeRep ',' o::aOperation ')'
{
  top.defs = addInhColDcl(top.grammarName, l.location, fn.aname, t.typerep, o.operation, emptyDefs());
}

concrete production aDclInfoLocalCol
top::aDclInfo ::= 'loccol' '(' l::aLocation ',' fn::Name ',' t::aTypeRep ',' o::aOperation ')'
{
  top.defs = addLocalColDcl(top.grammarName, l.location, fn.aname, t.typerep, o.operation, emptyDefs());
}

