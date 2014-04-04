grammar silver:modification:impide;

import silver:driver;

{--
 - This attribute will by accumulated up on each 
 -}
synthesized attribute ideSpecs :: [IdeSpec] occurs on RootSpec, Root, AGDcls, AGDcl, Grammar;

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

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  _ _ _
{
  top.ideSpecs = g.ideSpecs;
}
aspect production interfaceRootSpec
top::RootSpec ::= _ _
{
  top.ideSpecs = []; -- TODO: This, of course, means we're forgetting ide specs in interfaces
}
aspect production errorRootSpec
top::RootSpec ::= _ _ _ _
{
  top.ideSpecs = [];
}

aspect production nilGrammar
top::Grammar ::=
{
  top.ideSpecs = [];
}

aspect production consGrammar
top::Grammar ::= h::Root  t::Grammar
{
  top.ideSpecs = h.ideSpecs ++ t.ideSpecs;
}

