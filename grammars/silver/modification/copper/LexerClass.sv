grammar silver:modification:copper;

terminal Lexer_kwd   'lexer'   lexer classes {KEYWORD};
terminal Class_kwd   'class'   lexer classes {KEYWORD};
terminal Extends_kwd 'extends' lexer classes {KEYWORD};

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

  top.defs := [lexerClassDef(top.grammarName, id.location, fName)];

  top.errors <- if length(getLexerClassDcl(fName, top.env)) > 1
                then [err(id.location, "Lexer class '" ++ fName ++ "' is already bound.")]
                else [];	

  top.errors := modifiers.errors;
  
  top.syntaxAst := [syntaxLexerClass(fName, 
    foldr(consLexerClassMod, nilLexerClassMod(), modifiers.lexerClassModifiers))];
}

nonterminal LexerClassModifiers with config, location, unparse, lexerClassModifiers, errors, env, grammarName, compiledGrammars, flowEnv;
closed nonterminal LexerClassModifier with config, location, unparse, lexerClassModifiers, errors, env, grammarName, compiledGrammars, flowEnv;

monoid attribute lexerClassModifiers :: [SyntaxLexerClassModifier] with [], ++;

propagate errors on LexerClassModifiers, LexerClassModifier;
propagate lexerClassModifiers on LexerClassModifiers;

abstract production lexerClassModifiersNone
top::LexerClassModifiers ::= 
{
  top.unparse = "";
}
concrete production lexerClassModifierSingle
top::LexerClassModifiers ::= tm::LexerClassModifier
{
  top.unparse = tm.unparse;
}
concrete production lexerClassModifiersCons
top::LexerClassModifiers ::= h::LexerClassModifier ',' t::LexerClassModifiers
{
  top.unparse = h.unparse ++ " " ++ t.unparse;
}

concrete production lexerClassModifierExtends
top::LexerClassModifier ::= 'extends' cls::LexerClasses
{
  top.unparse = "extends " ++ cls.unparse;

  top.lexerClassModifiers := [lexerClassExtends(cls.lexerClasses)];
}

concrete production lexerClassModifierDominates
top::LexerClassModifier ::= 'dominates' terms::TermPrecs
{
  top.unparse = "dominates " ++ terms.unparse;

  top.lexerClassModifiers := [lexerClassDominates(terms.precTermList)];
}

concrete production lexerClassModifierSubmitsTo
top::LexerClassModifier ::= 'submits' 'to' terms::TermPrecs
{
  top.unparse = "submits to " ++ terms.unparse;

  top.lexerClassModifiers := [lexerClassSubmits(terms.precTermList)];
}

concrete production lexerClassModifierDisambiguate
top::LexerClassModifier ::= 'disambiguate' acode::ActionCode_c
{
  top.unparse = "disambiguate " ++ acode.unparse;

  top.lexerClassModifiers := [lexerClassDisambiguate(acode.actionCode)];
  
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(acode.flowDefs, top.env, myProds, myFlow);

  acode.env = newScopeEnv(disambiguationClassActionVars ++ acode.defs, top.env);
  acode.frame = disambiguationContext(myFlowGraph);
}
