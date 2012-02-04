grammar silver:modification:copper;

terminal Lexer_kwd 'lexer' lexer classes {KEYWORD};

abstract production lexerClassDclFull
top::AGDcl ::= id::Name subs::TermPrecList doms::TermPrecList
{
  top.pp = "lexer class " ++ id.name ++ ";";
  top.location = id.location;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs = addLexerClassDcl(top.grammarName, id.location, fName,
             emptyDefs());

  top.errors <- if length(getLexerClassDcl(fName, top.env)) > 1
                then [err(top.location, "Lexer class '" ++ fName ++ "' is already bound.")]
                else [];	

  top.errors := subs.errors ++ doms.errors;
  
  top.syntaxAst = [syntaxLexerClass(fName, doms.precTermList, subs.precTermList)];

  forwards to defaultAGDcl();
}

concrete production lexerClassDclConcrete
top::AGDcl ::= 'lexer' 'class' id::Name ';'
{
  forwards to lexerClassDclFull(id, termPrecListNull(), termPrecListNull());
}

concrete production lexerClassDclSubmits
top::AGDcl ::= 'lexer' 'class' id::Name 'submits' 'to' t::TermPrecList ';'
{
  forwards to lexerClassDclFull(id, t, termPrecListNull());
}

concrete production lexerClassDclDominates
top::AGDcl ::= 'lexer' 'class' id::Name 'dominates' t::TermPrecList ';'
{
  forwards to lexerClassDclFull(id, termPrecListNull(), t);
}

concrete production lexerClassDclBoth1
top::AGDcl ::= 'lexer' 'class' id::Name 'dominates' t1::TermPrecList 'submits' 'to' t2::TermPrecList ';'
{
  forwards to lexerClassDclFull(id, t1, t2);
}

concrete production lexerClassDclBoth2
top::AGDcl ::= 'lexer' 'class' id::Name 'submits' 'to' t1::TermPrecList 'dominates' t2::TermPrecList ';'
{
  forwards to lexerClassDclFull(id, t2, t1);
}

