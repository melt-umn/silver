grammar silver:modification:collection:env_parser;
import silver:modification:collection;
import silver:definition:env;
import silver:definition:env:env_parser;
import silver:definition:core only grammarName, location, env;

terminal SynColTerm 'syncol' lexer classes {C_1};
terminal InhColTerm 'inhcol' lexer classes {C_1};
terminal LocColTerm 'loccol' lexer classes {C_1};

terminal PlusPlusStr '++string' lexer classes {C_1};
terminal PlusPlusLst '++list' lexer classes {C_1};
terminal BOrTerm '||' lexer classes {C_1};
terminal PAndTerm '&&' lexer classes {C_1};

nonterminal IOperation with operation;

concrete production operationNameFun
top::IOperation ::= 'fun' '(' n::IName ')'
{
  top.operation = functionOperation(n.aname);
}
concrete production operationNameProd
top::IOperation ::= 'prod' '(' n::IName ')'
{
  top.operation = productionOperation(n.aname);
}
concrete production operationPlusPlusStr
top::IOperation ::= '++string'
{
  top.operation = plusPlusOperationString();
}
concrete production operationPlusPlusLst
top::IOperation ::= '++list'
{
  top.operation = plusPlusOperationList();
}
concrete production operationBOr
top::IOperation ::= '||'
{
  top.operation = borOperation();
}
concrete production operationBAnd
top::IOperation ::= '&&'
{
  top.operation = bandOperation();
}


concrete production aDclInfoSynthesizedCol
top::IDclInfo ::= 'syncol' '(' l::ILocation ',' fn::IName ',' td::ITyVarDcls ',' t::ITypeRep ',' o::IOperation ')'
{
  t.env = newScopeEnv(td.defs, top.env);
  
  top.defs = [synColDef(top.grammarName, l.location, fn.aname, td.tyvars, t.typerep, o.operation)];
}

concrete production aDclInfoInheritedCol
top::IDclInfo ::= 'inhcol' '(' l::ILocation ',' fn::IName ',' td::ITyVarDcls ',' t::ITypeRep ',' o::IOperation ')'
{
  t.env = newScopeEnv(td.defs, top.env);
  
  top.defs = [inhColDef(top.grammarName, l.location, fn.aname, td.tyvars, t.typerep, o.operation)];
}

concrete production aDclInfoLocalCol
top::IDclInfo ::= 'loccol' '(' l::ILocation ',' fn::IName ',' t::ITypeRep ',' o::IOperation ')'
{
  top.defs = [localColDef(top.grammarName, l.location, fn.aname, t.typerep, o.operation)];
}

