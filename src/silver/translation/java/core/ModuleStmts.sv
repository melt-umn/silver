grammar silver:translation:java:core;
import silver:definition:concrete_syntax;
import silver:definition:core;


aspect production moduleStmtsNone 
top::ModuleStmts ::=
{
  top.javaClasses = [];
  top.initProd = "";
}

aspect production importsStmtsOne 
top::ModuleStmts ::= im::ImportsStmt
{
  top.javaClasses = [];
  top.initProd = "";
}

aspect production importsStmtsCons
top::ModuleStmts ::= h::ImportsStmt t::ModuleStmts
{
  top.javaClasses = t.javaClasses;
  top.initProd = t.initProd;
}

aspect production exportsStmtsOne 
top::ModuleStmts ::= ex::ExportsStmt
{
  top.javaClasses = [];
  top.initProd = "";
}

aspect production exportsStmtsCons
top::ModuleStmts ::= h::ExportsStmt t::ModuleStmts
{
  top.javaClasses = t.javaClasses;
  top.initProd = t.initProd;
}
