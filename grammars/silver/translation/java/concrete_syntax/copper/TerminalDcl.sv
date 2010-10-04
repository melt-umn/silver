grammar silver:translation:java:concrete_syntax:copper;
import silver:analysis:typechecking;
import silver:analysis:typechecking:core;
import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:translation:java:core;
import silver:definition:type;
import silver:definition:type:syntax;
--import silver:definition:regex; TODO nix this line?

--terminal Prefix_kwd 'prefix' lexer classes {KEYWORD}; -- TODO: not currently used
terminal Class_kwd   'class'     ; --lexer classes {KEYWORD};
terminal Dominates_t 'dominates' ; --lexer classes {KEYWORD};
terminal Submits_t   'submits'   ; --lexer classes {KEYWORD};
terminal Classes_kwd 'classes'   ; --lexer classes {KEYWORD};

-- Concrete Syntax for new Copper --
concrete production terminalModifierDominates
top::TerminalModifier ::= 'dominates' '{' terms::TermPrecList '}'
{
  top.pp = "dominates { " ++ terms.pp ++ " } " ;

  top.terminalModifiers = [dominatesTerminalModifierSpec(terms.precTermList)];

  top.errors := terms.errors;

  forwards to terminalModifierDefault();
}

concrete production terminalModifierSubmitsTo
top::TerminalModifier ::= 'submits' 'to' '{' terms::TermPrecList  '}'
{
  top.pp = "submits to { " ++ terms.pp ++ " } " ;

  top.terminalModifiers = [submitsToTerminalModifierSpec(terms.precTermList)];

  top.errors := terms.errors;

  forwards to terminalModifierDefault();
}

synthesized attribute precTermList :: [String];
nonterminal TermPrecList with grammarName, pp, location, precTermList, defs, errors, env, file;

-- The reason these forward is that it's easier to avoid code duplication with cons and nil,
-- while the grammar has to enforce at least one element.

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
  top.defs = addPluckTermDcl(top.grammarName, h.location, h.lookupType.dcl.fullName, t.defs);

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

concrete production terminalModifierActionCode
top::TerminalModifier ::= 'action' acode::ActionCode_c
{
  top.pp = "action " ++ acode.actionCode;

  top.terminalModifiers = [actionCodeTerminalModifierSpec(acode.actionCode)];

  acode.actionCodeType = terminalActionType();
  acode.env = newScopeEnv(addTerminalAttrDefs(acode.defs), top.env);

  -- TODO: better name than this dummy one?
  acode.signature = namedNamedSignature(top.grammarName ++ ":__ta" ++ toString($1.line));
  
  top.errors := acode.errors;

  forwards to terminalModifierDefault();
}

concrete production terminalModifierClassSpec
top::TerminalModifier ::= 'lexer' 'classes' '{' cl::ClassList '}'
{
  top.pp = "lexer classes { " ++ cl.pp ++ " } " ;

  top.terminalModifiers = [lexerClassesTerminalModifierSpec(cl.lexerClasses)] ++ cl.terminalModifiers;
  top.errors := cl.errors;

  forwards to terminalModifierDefault();
}

nonterminal ClassList with pp, lexerClasses, errors, env, file, terminalModifiers;

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

  -- Neither of these things should be demanded if there are errors, right?
  top.lexerClasses = [n.lookupLexerClass.dcl.fullName] ++ t.lexerClasses;
  
  -- wtf? This seems buggy... How are lexer classes handled by copper? TODO
  -- Is it just that lexer class declarations don't get to have dominates/submits in copper, so we translate it away here?
  top.terminalModifiers = [submitsToTerminalModifierSpec(n.lookupLexerClass.dcl.submitsTo),
                           dominatesTerminalModifierSpec(n.lookupLexerClass.dcl.termDominates)] ++ t.terminalModifiers;
}

abstract production lexerClassesNull
cl::ClassList ::=
{
  cl.terminalModifiers = [];
  cl.pp = "";
  cl.errors := [];
  cl.lexerClasses = [];
}


abstract production termAttrValueReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp; 
  top.location = q.location;

  top.errors := []; -- Should only ever be in scope in action blocks
  top.warnings := [];

  top.typerep = q.lookupValue.typerep;

  top.isAppReference = false;
  top.appReference = "";

  -- Yeah, it's a big if/then/else block, but these are all very similar and related.
  top.translation = if q.name == "lexeme" then "new common.StringCatter(lexeme)" else
                    if q.name == "line" then "virtualLocation.getLine()" else
                    if q.name == "column" then "virtualLocation.getColumn()" else
                    if q.name == "filename" then "new common.StringCatter(virtualLocation.getFileName())" else
                    error("unknown actionTerminalReference " ++ q.name); -- should never be called, but here for safety

  top.upSubst = top.downSubst;
}

abstract production termAttrValueValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e.errors; -- should only be in scope when its valid to use them
  top.warnings := [];

  e.expected = expected_type(val.lookupValue.typerep);  

  local attribute memberfunc :: String;
  memberfunc = if val.name == "filename" then "setFileName" else
               if val.name == "line" then "setLine" else
               if val.name == "column" then "setColumn" else
               error("unknown assignment to terminal attribute: " ++ val.name);

  top.setupInh := "";
  top.translation = "virtualLocation." ++ memberfunc ++ "(" ++ e.translation
                     ++ (if val.name == "filename" then ".toString()" else "") ++ ");\n";

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;

  errCheck1 = check(e.typerep, val.lookupValue.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Value " ++ val.name ++ " has type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
       else [];
}

