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
  top.pp = "lexer class " ++ id.name ++ modifiers.pp ++ ";";

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

nonterminal LexerClassModifiers with config, location, pp, lexerClassModifiers, errors, env, grammarName, compiledGrammars, flowEnv;
nonterminal LexerClassModifier with config, location, pp, lexerClassModifiers, errors, env, grammarName, compiledGrammars, flowEnv;

synthesized attribute lexerClassModifiers :: [SyntaxLexerClassModifier];

abstract production lexerClassModifiersNone
top::LexerClassModifiers ::= 
{
  top.pp = "";

  top.lexerClassModifiers = [];
  top.errors := [];
}
concrete production lexerClassModifierSingle
top::LexerClassModifiers ::= tm::LexerClassModifier
{
  top.pp = tm.pp;

  top.lexerClassModifiers = tm.lexerClassModifiers;
  top.errors := tm.errors; 
}
concrete production lexerClassModifiersCons
top::LexerClassModifiers ::= h::LexerClassModifier  t::LexerClassModifiers
{
  top.pp = h.pp ++ " " ++ t.pp;

  top.lexerClassModifiers = h.lexerClassModifiers ++ t.lexerClassModifiers;
  top.errors := h.errors ++ t.errors;
}

concrete production lexerClassModifierDominates
top::LexerClassModifier ::= 'dominates' terms::TermPrecList
{
  top.pp = "dominates " ++ terms.pp;

  top.lexerClassModifiers = [lexerClassDominates(terms.precTermList)];
  top.errors := terms.errors;
}

concrete production lexerClassModifierSubmitsTo
top::LexerClassModifier ::= 'submits' 'to' terms::TermPrecList
{
  top.pp = "submits to " ++ terms.pp;

  top.lexerClassModifiers = [lexerClassSubmits(terms.precTermList)];
  top.errors := terms.errors;
}

