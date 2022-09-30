grammar silver:compiler:modification:copper;

terminal Lexer_kwd   'lexer'   lexer classes {KEYWORD};
terminal Extends_kwd 'extends' lexer classes {MODIFIER};

terminal IdLexerClass_t '' lexer classes {IDENTIFIER, lsp:Class};
terminal IdLexerClassDcl_t '' lexer classes {IDENTIFIER, lsp:Class, lsp:Declaration};

concrete production lexerClassDclEmpty
top::AGDcl ::= 'lexer' 'class' id::Name ';'
{
  forwards to lexerClassDecl($1, $2, id, lexerClassModifiersNone(location=$4.location), $4, location=top.location);
} action {
  insert semantic token IdLexerClassDcl_t at id.location;
}

concrete production lexerClassDecl
top::AGDcl ::= 'lexer' 'class' id::Name modifiers::LexerClassModifiers ';'
{
  top.unparse = "lexer class " ++ id.name ++ modifiers.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs := [lexerClassDef(top.grammarName, id.location, fName, modifiers.superClasses)];

  top.errors <- if length(getLexerClassDcl(fName, top.env)) > 1
                then [err(id.location, "Lexer class '" ++ fName ++ "' is already bound.")]
                else [];	

  top.errors := modifiers.errors;
  
  top.syntaxAst :=
    [ syntaxLexerClass(fName, 
        foldr(consLexerClassMod, nilLexerClassMod(), modifiers.lexerClassModifiers),
        location=top.location, sourceGrammar=top.grammarName)];
} action {
  insert semantic token IdLexerClassDcl_t at id.location;
}

nonterminal LexerClassModifiers with config, location, unparse, lexerClassModifiers, superClasses, errors, env, grammarName, compiledGrammars, flowEnv;
closed nonterminal LexerClassModifier with config, location, unparse, lexerClassModifiers, superClasses, errors, env, grammarName, compiledGrammars, flowEnv;

monoid attribute lexerClassModifiers :: [SyntaxLexerClassModifier];

propagate config, grammarName, compiledGrammars, env, flowEnv, errors on LexerClassModifiers, LexerClassModifier;
propagate lexerClassModifiers, superClasses on LexerClassModifiers;

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

aspect default production
top::LexerClassModifier ::=
{
  top.superClasses := [];
}

concrete production lexerClassModifierExtends
top::LexerClassModifier ::= 'extends' cls::LexerClasses
{
  top.unparse = "extends " ++ cls.unparse;

  top.lexerClassModifiers :=
    [ lexerClassExtends(cls.lexerClasses, location=top.location,
        sourceGrammar=top.grammarName)
    ];
  top.superClasses := cls.lexerClasses;
}

concrete production lexerClassModifierDominates
top::LexerClassModifier ::= 'dominates' terms::TermPrecs
{
  top.unparse = "dominates " ++ terms.unparse;

  top.lexerClassModifiers :=
    [ lexerClassDominates(terms.precTermList, location=top.location,
        sourceGrammar=top.grammarName)
    ];
}

concrete production lexerClassModifierSubmitsTo
top::LexerClassModifier ::= 'submits' 'to' terms::TermPrecs
{
  top.unparse = "submits to " ++ terms.unparse;

  top.lexerClassModifiers :=
    [ lexerClassSubmits(terms.precTermList, location=top.location,
        sourceGrammar=top.grammarName)
    ];
}

concrete production lexerClassModifierDisambiguate
top::LexerClassModifier ::= 'disambiguate' acode::ActionCode_c
{
  top.unparse = "disambiguate " ++ acode.unparse;

  top.lexerClassModifiers :=
    [ lexerClassDisambiguate(acode.actionCode, location=top.location,
        sourceGrammar=top.grammarName)
    ];
  
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(acode.flowDefs, top.env, myProds, myFlow);

  acode.env = newScopeEnv(disambiguationClassActionVars ++ acode.defs, top.env);
  acode.frame = disambiguationContext(myFlowGraph, sourceGrammar=top.grammarName);
}
