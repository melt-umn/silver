grammar silver:definition:flow:env;

attribute flowDefs, flowEnv occurs on Root, AGDcls, AGDcl;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.flowDefs = ags.flowDefs;
}

aspect production nilAGDcls
top::AGDcls ::=
{
  top.flowDefs = [];
}
aspect production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

aspect default production
top::AGDcl ::=
{
  top.flowDefs = [];
}
aspect production appendAGDcl
top::AGDcl ::= ag1::AGDcl ag2::AGDcl
{
  top.flowDefs = ag1.flowDefs ++ ag2.flowDefs;
}

