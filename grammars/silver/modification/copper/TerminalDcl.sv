grammar silver:modification:copper;

terminal Class_kwd   'class'     ; --lexer classes {KEYWORD};
terminal Dominates_t 'dominates' ; --lexer classes {KEYWORD};
terminal Submits_t   'submits'   ; --lexer classes {KEYWORD};
terminal Classes_kwd 'classes'   ; --lexer classes {KEYWORD};

concrete production terminalModifierDominates
top::TerminalModifier ::= 'dominates' '{' terms::TermPrecList '}'
{
  top.pp = "dominates { " ++ terms.pp ++ " } ";
  top.location = loc(top.file, $1.line, $1.column);

  top.terminalModifiers = [termDominates(terms.precTermList)];
  top.errors := terms.errors;
}

concrete production terminalModifierSubmitsTo
top::TerminalModifier ::= 'submits' 'to' '{' terms::TermPrecList  '}'
{
  top.pp = "submits to { " ++ terms.pp ++ " } " ;
  top.location = loc(top.file, $1.line, $1.column);

  top.terminalModifiers = [termSubmits(terms.precTermList)];
  top.errors := terms.errors;
}

concrete production terminalModifierClassSpec
top::TerminalModifier ::= 'lexer' 'classes' '{' cl::ClassList '}'
{
  top.pp = "lexer classes { " ++ cl.pp ++ " } " ;
  top.location = loc(top.file, $1.line, $1.column);

  top.terminalModifiers = [termClasses(cl.lexerClasses)];
  top.errors := cl.errors;
}

concrete production terminalModifierActionCode
top::TerminalModifier ::= 'action' acode::ActionCode_c
{
  top.pp = "action " ++ acode.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.terminalModifiers = [termAction(acode.actionCode)];

  acode.blockContext = actionContext();
  acode.env = newScopeEnv(addTerminalAttrDefs(acode.defs), top.env);

  -- TODO: better name than this dummy one?
  acode.signature = decorate namedNamedSignature(top.grammarName ++ ":__ta" ++ toString($1.line)) with {};
  
  top.errors := acode.errors;
}

nonterminal TermPrecList with config, grammarName, pp, location, precTermList, defs, errors, env, file;

synthesized attribute precTermList :: [String];

-- The rest of this file is written quite sillily. It'll be automatically fixed when we get a proper ast/cst split

concrete production termPrecListOne
terms::TermPrecList ::= t::QName
{
   forwards to termPrecList(t,termPrecListNull());
}

concrete production termPrecListCons
terms::TermPrecList ::= t::QName ',' terms_tail::TermPrecList
{
   forwards to termPrecList(t,terms_tail);
}


abstract production termPrecList
top::TermPrecList ::= h::QName t::TermPrecList
{
  top.pp = if t.pp == ""
             then h.pp
             else h.pp ++ ", " ++ t.pp;

  top.location = h.location;

  production attribute fName :: String;
  fName = if null(h.lookupType.dcls) then h.lookupLexerClass.dcl.fullName else h.lookupType.dcl.fullName;

  top.precTermList = [fName] ++ t.precTermList ;

  -- This is just for disambiguation groups. TODO: remove and make it separate concrete syntax!
  top.defs = if null(h.lookupType.dcls) then t.defs
             else addPluckTermDcl(top.grammarName, h.location, h.lookupType.dcl.fullName, t.defs);

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
  top.defs = emptyDefs();
  top.pp = "";
  top.location = loc("termPrecListNull", -1, -1);
  top.errors := [];
}


function addTerminalAttrDefs
Defs ::= tailDefs::Defs
{
  -- TODO: no grammar or location? how to deal with this?
  return addTermAttrValueDcl("DBGtav", loc("DBGtav.sv", -1, -1), "lexeme", stringTypeExp(),
         addTermAttrValueDcl("DBGtav", loc("DBGtav.sv", -1, -1), "filename", stringTypeExp(),
         addTermAttrValueDcl("DBGtav", loc("DBGtav.sv", -1, -1), "line", intTypeExp(),
         addTermAttrValueDcl("DBGtav", loc("DBGtav.sv", -1, -1), "column", intTypeExp(),
         tailDefs))));
}


nonterminal ClassList with config, pp, lexerClasses, errors, env, file;

synthesized attribute lexerClasses :: [String];

concrete production lexerClassesOne
cl::ClassList ::= n::QName
{
  forwards to lexerClassesMain(n,lexerClassesNull());
}

concrete production lexerClassesCons
cl::ClassList ::= n::QName ',' cl_tail::ClassList
{
  forwards to lexerClassesMain(n,cl_tail);
}


abstract production lexerClassesMain
top::ClassList ::= n::QName t::ClassList
{
  top.pp = if t.pp == ""
          then n.pp
          else n.pp ++ ", " ++ t.pp;

  top.errors := n.lookupLexerClass.errors ++ t.errors;

  top.lexerClasses = [n.lookupLexerClass.dcl.fullName] ++ t.lexerClasses;
}

abstract production lexerClassesNull
cl::ClassList ::=
{
  cl.pp = "";
  cl.errors := [];
  cl.lexerClasses = [];
}

