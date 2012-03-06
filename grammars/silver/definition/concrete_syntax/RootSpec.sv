grammar silver:definition:concrete_syntax;

import silver:util;

attribute syntaxAst, parserSpecs occurs on RootSpec, ModuleExportedDefs;

aspect function unparseRootSpec
String ::= r::Decorated RootSpec
{
  unparses <- ["syntax [" ++ implode(",\n ", foldr_p(consSyntax, nilSyntax(), r.syntaxAst).unparses) ++ "]"];
  unparses <- ["parsers [" ++ implode(",\n ", map(unparseParser, r.parserSpecs)) ++ "]"];
}

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.syntaxAst = [];
  top.parserSpecs = [];
}
aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.syntaxAst = c1.syntaxAst;
  top.parserSpecs = c1.parserSpecs;
}
aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec  c2::Decorated RootSpec
{
  top.syntaxAst = c1.syntaxAst ++ c2.syntaxAst;
  top.parserSpecs = c1.parserSpecs ++ c2.parserSpecs;
}

aspect production moduleExportedDefs
top::ModuleExportedDefs ::= l::Location  compiled::[Decorated RootSpec]  need::[String]  seen::[String]
{
  top.syntaxAst = if null(need) || null(rs) then [] else (head(rs).syntaxAst ++ recurse.syntaxAst);
  top.parserSpecs = if null(need) || null(rs) then [] else (head(rs).parserSpecs ++ recurse.parserSpecs);
}

