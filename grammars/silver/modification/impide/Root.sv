grammar silver:modification:impide;

import silver:driver:util;

-- All we're doing here is propagating the information up from lower down the ASTs

-- RootSpec is the representation of a grammar
-- Root is a file
-- AGDcls is the "declarations" portion of a file (vs import statements, etc)
-- AGDcl is one declaration

attribute ideSpecs occurs on RootSpec, Root, AGDcls, AGDcl, GrammarPart, Grammar;

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
top::RootSpec ::= g::Grammar  _
{
  top.ideSpecs = g.ideSpecs;
}

aspect production grammarPart
top::GrammarPart ::= r::Root  fn::String
{
  top.ideSpecs = r.ideSpecs;
}

aspect production nilGrammar
top::Grammar ::=
{
  top.ideSpecs = [];
}

aspect production consGrammar
top::Grammar ::= h::GrammarPart  t::Grammar
{
  top.ideSpecs = h.ideSpecs ++ t.ideSpecs;
}

