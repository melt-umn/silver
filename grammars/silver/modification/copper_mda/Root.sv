grammar silver:modification:copper_mda;

synthesized attribute mdaSpecs :: [MdaSpec] occurs on Root, AGDcls, AGDcl, RootSpec;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.mdaSpecs = ags.mdaSpecs;
}

aspect production nilAGDcls
top::AGDcls ::=
{
  top.mdaSpecs = [];
}
aspect production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.mdaSpecs = h.mdaSpecs ++ t.mdaSpecs;
}

aspect default production
top::AGDcl ::=
{
  top.mdaSpecs = [];
}
aspect production appendAGDcl
top::AGDcl ::= ag1::AGDcl ag2::AGDcl
{
  top.mdaSpecs = ag1.mdaSpecs ++ ag2.mdaSpecs;
}

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.mdaSpecs = [];
}
aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.mdaSpecs = c1.mdaSpecs;
}
aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec  c2::Decorated RootSpec
{
  top.mdaSpecs = c1.mdaSpecs ++ c2.mdaSpecs;
}

