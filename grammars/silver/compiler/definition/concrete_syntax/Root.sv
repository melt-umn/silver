grammar silver:compiler:definition:concrete_syntax;

monoid attribute syntaxAst :: [SyntaxDcl];
monoid attribute parserSpecs :: [ParserSpec];

attribute syntaxAst, parserSpecs occurs on Root, AGDcls, AGDcl;
flowtype syntaxAst {decorate} on Root, AGDcls, AGDcl;
flowtype parserSpecs {decorate} on Root, AGDcls, AGDcl;
propagate syntaxAst, parserSpecs on Root, AGDcls;

aspect default production
top::AGDcl ::=
{
  propagate syntaxAst, parserSpecs;
}

aspect production appendAGDcl
top::AGDcl ::= ag1::AGDcl ag2::AGDcl
{
  propagate syntaxAst, parserSpecs;
}
