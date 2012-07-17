grammar silver:modification:impide;

-- All we're doing here is propagating the information up from lower down the ASTs

-- RootSpec is the representation of a grammar
-- Root is a file
-- AGDcls is the "declarations" portion of a file (vs import statements, etc)
-- AGDcl is one declaration

attribute ideSpecs occurs on RootSpec, Root, AGDcls, AGDcl;

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.ideSpecs = [];
}
aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.ideSpecs = c1.ideSpecs;
}
aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec  c2::Decorated RootSpec
{
  top.ideSpecs = c1.ideSpecs ++ c2.ideSpecs;
}


aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.ideSpecs = ags.ideSpecs;
}


aspect production nilAGDcls
top::AGDcls ::=
{
  top.ideSpecs = [];
}
aspect production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.ideSpecs = h.ideSpecs ++ t.ideSpecs;
}


aspect default production
top::AGDcl ::=
{
  top.ideSpecs = [];
}
aspect production appendAGDcl
top::AGDcl ::= ag1::AGDcl ag2::AGDcl
{
  top.ideSpecs = ag1.ideSpecs ++ ag2.ideSpecs;
}


