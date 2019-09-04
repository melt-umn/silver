grammar silver:modification:copper;

terminal Dominates_t 'dominates' lexer classes {KEYWORD};
terminal Submits_t   'submits'   lexer classes {KEYWORD};
terminal Classes_kwd 'classes'   lexer classes {KEYWORD};

synthesized attribute lexerClasses :: [String] occurs on TerminalModifier, TerminalModifiers;

aspect production terminalModifiersNone
top::TerminalModifiers ::=
{
  top.lexerClasses = [];
}
aspect production terminalModifierSingle
top::TerminalModifiers ::= tm::TerminalModifier
{
  top.lexerClasses = tm.lexerClasses;
}
aspect production terminalModifiersCons
top::TerminalModifiers ::= h::TerminalModifier ',' t::TerminalModifiers
{
  top.lexerClasses = h.lexerClasses ++ t.lexerClasses;
}

aspect default production
top::TerminalModifier ::=
{
  top.lexerClasses = [];
}

concrete production terminalModifierDominates
top::TerminalModifier ::= 'dominates' '{' terms::TermPrecList '}'
{
  top.unparse = "dominates { " ++ terms.unparse ++ " } ";

  top.terminalModifiers = [termDominates(terms.precTermList)];
  top.errors := terms.errors;
}

concrete production terminalModifierSubmitsTo
top::TerminalModifier ::= 'submits' 'to' '{' terms::TermPrecList  '}'
{
  top.unparse = "submits to { " ++ terms.unparse ++ " } " ;

  top.terminalModifiers = [termSubmits(terms.precTermList)];
  top.errors := terms.errors;
}

concrete production terminalModifierClassSpec
top::TerminalModifier ::= 'lexer' 'classes' '{' cl::ClassList '}'
{
  top.unparse = "lexer classes { " ++ cl.unparse ++ " } " ;

  top.terminalModifiers = [termClasses(cl.lexerClasses)];
  top.lexerClasses = cl.lexerClasses;
  top.errors := cl.errors;
}

concrete production terminalModifierActionCode
top::TerminalModifier ::= 'action' acode::ActionCode_c
{
  top.unparse = "action " ++ acode.unparse;

  top.terminalModifiers = [termAction(acode.actionCode)];

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(acode.flowDefs, top.env, myProds, myFlow);

  acode.frame = actionContext(myFlowGraph);
  acode.env = newScopeEnv(terminalActionVars ++ acode.defs, top.env);
  
  top.errors := acode.errors;
}

nonterminal TermPrecList with config, grammarName, unparse, location, precTermList, errors, env;

synthesized attribute precTermList :: [String];

concrete production termPrecListOne
terms::TermPrecList ::= t::QName
{
   forwards to termPrecList(t,termPrecListNull(location=t.location), location=t.location);
}

concrete production termPrecListCons
terms::TermPrecList ::= t::QName ',' terms_tail::TermPrecList
{
   forwards to termPrecList(t,terms_tail,location=terms.location);
}

abstract production termPrecList
top::TermPrecList ::= h::QName t::TermPrecList
{
  top.unparse = if t.unparse == ""
             then h.unparse
             else h.unparse ++ ", " ++ t.unparse;

  production fName::String = if null(h.lookupType.dcls) then h.lookupLexerClass.dcl.fullName else h.lookupType.dcl.fullName;

  top.precTermList = [fName] ++ t.precTermList ;

  top.errors := t.errors;
  
  -- Since we're looking it up in two ways, do the errors ourselves
  top.errors <- if null(h.lookupType.dcls) && null(h.lookupLexerClass.dcls)
                then [err(h.location, "Undeclared terminal or lexer class '" ++ h.name ++ "'.")]
                else if length(h.lookupType.dcls ++ h.lookupLexerClass.dcls) > 1
                then [err(h.location, "Ambiguous reference to terminal or lexer class '" ++ h.name ++ "'. Possibilities are:\n" ++ printPossibilities(h.lookupType.dcls ++ h.lookupLexerClass.dcls))]
                else [];
}

abstract production termPrecListNull
top::TermPrecList ::=
{
  top.precTermList = [];
  top.unparse = "";
  top.errors := [];
}

nonterminal ClassList with location, config, unparse, lexerClasses, errors, env;

concrete production lexerClassesOne
top::ClassList ::= n::QName
{
  forwards to lexerClassesMain(n,lexerClassesNull(location=n.location), location=n.location);
}

concrete production lexerClassesCons
top::ClassList ::= n::QName ',' cl_tail::ClassList
{
  forwards to lexerClassesMain(n,cl_tail,location=top.location);
}


abstract production lexerClassesMain
top::ClassList ::= n::QName t::ClassList
{
  top.unparse = if t.unparse == ""
          then n.unparse
          else n.unparse ++ ", " ++ t.unparse;

  top.errors := n.lookupLexerClass.errors ++ t.errors;

  top.lexerClasses = [n.lookupLexerClass.dcl.fullName] ++ t.lexerClasses;
}

abstract production lexerClassesNull
cl::ClassList ::=
{
  cl.unparse = "";
  cl.errors := [];
  cl.lexerClasses = [];
}

