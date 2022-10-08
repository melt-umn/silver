grammar silver:compiler:modification:copper;

terminal Dominates_t 'dominates' lexer classes {MODIFIER};
terminal Submits_t   'submits'   lexer classes {MODIFIER};
terminal Classes_kwd 'classes'   lexer classes {MODIFIER};

monoid attribute lexerClasses :: [String];
attribute lexerClasses occurs on TerminalModifier, TerminalModifiers;
propagate lexerClasses on TerminalModifiers, TerminalModifier;

concrete production terminalModifierDominates
top::TerminalModifier ::= 'dominates' terms::TermPrecs
{
  top.unparse = "dominates { " ++ terms.unparse ++ " } ";

  top.terminalModifiers := [termDominates(terms.precTermList)];
  propagate errors;
}

concrete production terminalModifierSubmitsTo
top::TerminalModifier ::= 'submits' 'to' terms::TermPrecs
{
  top.unparse = "submits to { " ++ terms.unparse ++ " } " ;

  top.terminalModifiers := [termSubmits(terms.precTermList)];
  propagate errors;
}

concrete production terminalModifierClassSpec
top::TerminalModifier ::= 'lexer' 'classes' cl::LexerClasses
{
  top.unparse = "lexer classes { " ++ cl.unparse ++ " } " ;

  top.terminalModifiers := [termClasses(cl.lexerClasses)];
  propagate errors;
}

concrete production terminalModifierActionCode
top::TerminalModifier ::= 'action' acode::ActionCode_c
{
  top.unparse = "action " ++ acode.unparse;

  top.terminalModifiers := [termAction(acode.actionCode)];

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(acode.flowDefs, top.env, myProds, myFlow);

  acode.frame = actionContext(myFlowGraph, sourceGrammar=top.grammarName);
  acode.env = newScopeEnv(terminalActionVars ++ acode.defs, top.env);
  
  propagate errors;
}

monoid attribute precTermList :: [String];

nonterminal TermPrecs with config, grammarName, unparse, location, precTermList, errors, env;
propagate errors, precTermList on TermPrecs;

concrete production termPrecsOne
top::TermPrecs ::= t::QName
{
  forwards to termPrecs(termPrecList(t,termPrecListNull(location=t.location), location=t.location), location=top.location);
} action {
  insert semantic token IdType_t at t.baseNameLoc;
}

concrete production termPrecsList
top::TermPrecs ::= '{' terms::TermPrecList '}'
{
  forwards to termPrecs(terms,location=top.location);
}

abstract production termPrecs
top::TermPrecs ::= terms::TermPrecList
{
  top.unparse = s"{${terms.unparse}}";
}

nonterminal TermPrecList with config, grammarName, unparse, location, precTermList, errors, env;
propagate errors, precTermList on TermPrecList;

abstract production termPrecList
top::TermPrecList ::= h::QName t::TermPrecList
{
  top.unparse = if t.unparse == ""
             then h.unparse
             else h.unparse ++ ", " ++ t.unparse;

  production fName::String = if null(h.lookupType.dcls) then h.lookupLexerClass.dcl.fullName else h.lookupType.dcl.fullName;

  top.precTermList <- [fName];
  
  -- Since we're looking it up in two ways, do the errors ourselves
  top.errors <- if null(h.lookupType.dcls) && null(h.lookupLexerClass.dcls)
                then [err(h.location, "Undeclared terminal or lexer class '" ++ h.name ++ "'.")]
                else if length(h.lookupType.dcls) + length(h.lookupLexerClass.dcls) > 1
                then [err(h.location, "Ambiguous reference to terminal or lexer class '" ++ h.name ++ "'. Possibilities are:\n" ++
                            printPossibilities(h.lookupType.dcls) ++ if !null(h.lookupLexerClass.dcls) then ", " ++ printPossibilities(h.lookupLexerClass.dcls) else "")]
                else [];
}

abstract production termPrecListNull
top::TermPrecList ::=
{
  top.unparse = "";
}

concrete production termPrecListOne
top::TermPrecList ::= t::QName
{
  forwards to termPrecList(t, termPrecListNull(location=top.location), location=top.location);
} action {
  insert semantic token IdType_t at t.baseNameLoc;
}

concrete production termPrecListCons
top::TermPrecList ::= t::QName ',' terms_tail::TermPrecList
{
  forwards to termPrecList(t, terms_tail,location=top.location);
} action {
  insert semantic token IdType_t at t.baseNameLoc;
}

nonterminal LexerClasses with location, config, unparse, lexerClasses, errors, env;
propagate errors, lexerClasses on LexerClasses;

concrete production lexerClassesOne
top::LexerClasses ::= n::QName
{
  forwards to lexerClasses(lexerClassListMain(n, lexerClassListNull(location=top.location), location=top.location), location=top.location);
} action {
  insert semantic token IdLexerClassDcl_t at n.baseNameLoc;
}

concrete production lexerClassesList
top::LexerClasses ::= '{' cls::LexerClassList '}'
{
   forwards to lexerClasses(cls,location=top.location);
}

abstract production lexerClasses
top::LexerClasses ::= cls::LexerClassList
{
  top.unparse = s"{${cls.unparse}}";
}

nonterminal LexerClassList with location, config, unparse, lexerClasses, errors, env;
propagate errors, lexerClasses on LexerClassList;

concrete production lexerClassListOne
top::LexerClassList ::= n::QName
{
  forwards to lexerClassListMain(n,lexerClassListNull(location=n.location), location=n.location);
} action {
  insert semantic token IdLexerClassDcl_t at n.baseNameLoc;
}

concrete production lexerClassListCons
top::LexerClassList ::= n::QName ',' cl_tail::LexerClassList
{
  forwards to lexerClassListMain(n,cl_tail,location=top.location);
} action {
  insert semantic token IdLexerClassDcl_t at n.baseNameLoc;
}


abstract production lexerClassListMain
top::LexerClassList ::= n::QName t::LexerClassList
{
  top.unparse = if t.unparse == ""
          then n.unparse
          else n.unparse ++ ", " ++ t.unparse;

  top.errors <- n.lookupLexerClass.errors;

  top.lexerClasses <- [n.lookupLexerClass.dcl.fullName];
}

abstract production lexerClassListNull
cl::LexerClassList ::=
{
  cl.unparse = "";
}

