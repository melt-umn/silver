grammar silver:modification:copper;

terminal Lexer_kwd 'lexer' lexer classes {KEYWORD};
terminal Class_kwd 'class' lexer classes {KEYWORD};

concrete production lexerClassDclEmpty
top::AGDcl ::= 'lexer' 'class' id::Name ';'
{
  forwards to lexerClassDecl($1, $2, id, lexerClassModifiersNone(location=$4.location), $4, location=top.location);
}

concrete production lexerClassDecl
top::AGDcl ::= 'lexer' 'class' id::Name modifiers::LexerClassModifiers ';'
{
  top.unparse = "lexer class " ++ id.name ++ modifiers.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs = [lexerClassDef(top.grammarName, id.location, fName)];

  top.errors <- if length(getLexerClassDcl(fName, top.env)) > 1
                then [err(id.location, "Lexer class '" ++ fName ++ "' is already bound.")]
                else [];	

  top.errors := modifiers.errors;
  
  top.syntaxAst = [syntaxLexerClass(fName, 
    foldr(consLexerClassMod, nilLexerClassMod(), modifiers.lexerClassModifiers))];
}

nonterminal LexerClassModifiers with config, location, unparse, lexerClassModifiers, errors, env, grammarName, compiledGrammars, flowEnv;
nonterminal LexerClassModifier with config, location, unparse, lexerClassModifiers, errors, env, grammarName, compiledGrammars, flowEnv;

synthesized attribute lexerClassModifiers :: [SyntaxLexerClassModifier];

abstract production lexerClassModifiersNone
top::LexerClassModifiers ::= 
{
  top.unparse = "";

  top.lexerClassModifiers = [];
  top.errors := [];
}
concrete production lexerClassModifierSingle
top::LexerClassModifiers ::= tm::LexerClassModifier
{
  top.unparse = tm.unparse;

  top.lexerClassModifiers = tm.lexerClassModifiers;
  top.errors := tm.errors; 
}
concrete production lexerClassModifiersCons
top::LexerClassModifiers ::= h::LexerClassModifier  t::LexerClassModifiers
{
  top.unparse = h.unparse ++ " " ++ t.unparse;

  top.lexerClassModifiers = h.lexerClassModifiers ++ t.lexerClassModifiers;
  top.errors := h.errors ++ t.errors;
}

concrete production lexerClassModifierDominates
top::LexerClassModifier ::= 'dominates' terms::TermPrecList
{
  top.unparse = "dominates " ++ terms.unparse;

  top.lexerClassModifiers = [lexerClassDominates(terms.precTermList)];
  top.errors := terms.errors;
}

concrete production lexerClassModifierSubmitsTo
top::LexerClassModifier ::= 'submits' 'to' terms::TermPrecList
{
  top.unparse = "submits to " ++ terms.unparse;

  top.lexerClassModifiers = [lexerClassSubmits(terms.precTermList)];
  top.errors := terms.errors;
}

