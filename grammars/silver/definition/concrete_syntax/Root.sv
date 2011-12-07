grammar silver:definition:concrete_syntax;

synthesized attribute syntaxAst :: [SyntaxDcl];
synthesized attribute parserSpecs :: [ParserSpec];

attribute syntaxAst, parserSpecs occurs on Root, AGDcls, AGDcl;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.syntaxAst = ags.syntaxAst;
  top.parserSpecs = ags.parserSpecs;
}

aspect production agDclsOne
top::AGDcls ::= ag::AGDcl
{
  top.syntaxAst = ag.syntaxAst;
  top.parserSpecs = ag.parserSpecs;
}
aspect production agDclsCons
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.syntaxAst = h.syntaxAst ++ t.syntaxAst;
  top.parserSpecs = h.parserSpecs ++ t.parserSpecs;
}
aspect production agDclsAppend
top::AGDcls ::= ag1::AGDcls ag2::AGDcls
{
  top.syntaxAst = ag1.syntaxAst ++ ag2.syntaxAst;
  top.parserSpecs = ag1.parserSpecs ++ ag2.parserSpecs;
}

aspect production agDclDefault
top::AGDcl ::=
{
  top.syntaxAst = [];
  top.parserSpecs = [];
}
aspect production agDclAppend
top::AGDcl ::= ag1::AGDcl ag2::AGDcl
{
  top.syntaxAst = ag1.syntaxAst ++ ag2.syntaxAst;
  top.parserSpecs = ag1.parserSpecs ++ ag2.parserSpecs;
}

