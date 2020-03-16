grammar silver:definition:concrete_syntax;

monoid attribute syntaxAst :: [SyntaxDcl] with [], ++;
monoid attribute parserSpecs :: [ParserSpec] with [], ++;

attribute syntaxAst, parserSpecs occurs on Root, AGDcls, AGDcl;
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
