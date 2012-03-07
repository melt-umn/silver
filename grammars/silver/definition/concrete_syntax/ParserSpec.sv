grammar silver:definition:concrete_syntax;

{--
 - An abstract representation of a parser declaration.
 -}
nonterminal ParserSpec with 
  sourceGrammar, sourceLocation, fullName,
  compiledGrammars,
  cstAst, moduleNames, unparse;

{--
 - Given compiledGrammars, gives back the SyntaxRoot representing this parser.
 -}
synthesized attribute cstAst :: SyntaxRoot;

abstract production parserSpec
top::ParserSpec ::= sl::Location  sg::String  fn::String  snt::String  grams::[String]
{
  top.sourceLocation = sl;
  top.sourceGrammar = sg;
  top.fullName = fn;
  top.moduleNames = grams;

  production attribute med :: ModuleExportedDefs;
  med = moduleExportedDefs(sl, top.compiledGrammars, grams, []);

  top.cstAst = cstRoot(fn, snt, foldr_p(consSyntax, nilSyntax(), med.syntaxAst));
  
  top.unparse = "parser(" ++ sl.unparse ++ "," ++ quoteString(sg) ++ "," ++ quoteString(fn) ++ "," ++ quoteString(snt) ++ "," ++ unparseStrings(grams) ++ ")";
}

function unparseParser -- lol
String ::= p::ParserSpec
{
  return p.unparse;
}
