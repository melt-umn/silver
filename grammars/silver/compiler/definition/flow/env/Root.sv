grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:type:syntax;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:modification:defaultattr;
import silver:compiler:modification:collection;
import silver:compiler:modification:copper;

attribute flowDefs, refDefs, specDefs, flowEnv occurs on Root, AGDcls, AGDcl, Grammar;
flowtype flowDefs {decorate} on Root, AGDcls, AGDcl, Grammar;
flowtype refDefs {decorate} on Root, AGDcls, AGDcl, Grammar;
flowtype specDefs {decorate} on Root, AGDcls, AGDcl, Grammar;
propagate flowDefs, refDefs, specDefs, flowEnv on Root, AGDcls, AGDcl, Grammar;

aspect default production
top::AGDcl ::=
{
  top.flowDefs := [];
  top.refDefs := [];
  top.specDefs := [];
}

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  e.decSiteVertexInfo = nothing();
}
aspect production defaultConstraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr '=' e::Expr ';'
{
  e.decSiteVertexInfo = nothing();
}
aspect production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  e.decSiteVertexInfo = nothing();
}
