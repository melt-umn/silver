grammar silver:definition:concrete_syntax;


nonterminal ParserSpec with fullName, compiledGrammars, cstAst, moduleNames, unparse;

synthesized attribute cstAst :: SyntaxRoot;

-- TODO: the copper spec gen would like grammar name added to this!!
-- see: hackilyFindGrammarName

abstract production parserSpec
top::ParserSpec ::= l::Decorated Location  n::String  snt::String  grams::[String]
{
  top.fullName = n;
  top.moduleNames = grams;

  production attribute med :: ModuleExportedDefs;
  med = moduleExportedDefs(top.compiledGrammars, grams, []);
  med.importLocation = l;

  top.cstAst = cstRoot(n, snt, foldr_p(syntaxAppend, syntaxNone(), map_p(syntaxOne, med.syntaxAst)));
  
  top.unparse = "parser(" ++ l.unparse ++ "," ++ quoteString(n) ++ "," ++ quoteString(snt) ++ "," ++ unparseStrings(grams) ++ ")";
}

function unparseParser -- lol
String ::= p::ParserSpec
{
  return p.unparse;
}
