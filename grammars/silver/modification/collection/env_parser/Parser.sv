grammar silver:modification:collection:env_parser;
import silver:modification:collection;
import silver:definition:env;
import silver:definition:env:parser;
import silver:definition:core only grammarName, location, env;

terminal SynColTerm 'syncol' lexer classes {C_1};
terminal InhColTerm 'inhcol' lexer classes {C_1};
terminal LocColTerm 'loccol' lexer classes {C_1};

terminal PlusPlusStr '++string' lexer classes {C_1};
terminal PlusPlusLst '++list' lexer classes {C_1};

nonterminal aOperation with operation;

concrete production operationNameFun
top::aOperation ::= 'fun' '(' n::Name ')'
{
  top.operation = functionOperation(n.aname);
}
concrete production operationNameProd
top::aOperation ::= 'prod' '(' n::Name ')'
{
  top.operation = productionOperation(n.aname);
}
concrete production operationPlusPlusStr
top::aOperation ::= '++string'
{
  top.operation = plusPlusOperationString();
}
concrete production operationPlusPlusLst
top::aOperation ::= '++list'
{
  top.operation = plusPlusOperationList();
}


concrete production aDclInfoSynthesizedCol
top::aDclInfo ::= 'syncol' '(' l::aLocation ',' fn::Name ',' td::aTyVarDcls ',' t::aTypeRep ',' o::aOperation ')'
{
  t.env = newScopeEnv(td.defs, top.env);
  
  top.defs = addSynColDcl(top.grammarName, l.location, fn.aname, td.tyvars, t.typerep, o.operation, emptyDefs());
}

concrete production aDclInfoInheritedCol
top::aDclInfo ::= 'inhcol' '(' l::aLocation ',' fn::Name ',' td::aTyVarDcls ',' t::aTypeRep ',' o::aOperation ')'
{
  t.env = newScopeEnv(td.defs, top.env);
  
  top.defs = addInhColDcl(top.grammarName, l.location, fn.aname, td.tyvars, t.typerep, o.operation, emptyDefs());
}

concrete production aDclInfoLocalCol
top::aDclInfo ::= 'loccol' '(' l::aLocation ',' fn::Name ',' t::aTypeRep ',' o::aOperation ')'
{
  top.defs = addLocalColDcl(top.grammarName, l.location, fn.aname, t.typerep, o.operation, emptyDefs());
}

