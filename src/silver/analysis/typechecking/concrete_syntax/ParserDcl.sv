grammar silver:analysis:typechecking:concrete_syntax;
import silver:analysis:typechecking:core;
import silver:definition:core;
import silver:definition:concrete_syntax;

attribute typeErrors occurs on ParserDcl;
aspect production parserDcl
top::AGDcl ::= p::ParserDcl{
  top.typeErrors =  p.typeErrors;
}

aspect production parserStmt
top::ParserDcl ::= 'parser' n::Name '::' t::Type '{' m::ModuleStmtList '}' {
  top.typeErrors = [];
}
