grammar silver:translation:java:concrete_syntax:copper;
import silver:analysis:typechecking:core;
import silver:analysis:typechecking:concrete_syntax;
import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;

terminal Class_kwd 'class' lexer classes {KEYWORD};
terminal Prefix_kwd 'prefix' lexer classes {KEYWORD};

terminal Dominates_t 'dominates' lexer classes {KEYWORD};
terminal Submits_t 'submits' lexer classes {KEYWORD};

terminal Classes_kwd 'classes' lexer classes {KEYWORD};

-- Concrete Syntax for new Copper --
concrete production terminalModifierDominates
top::TerminalModifier ::= 'dominates' '{' terms::TermPrecList '}'
{
  top.pp = "dominates { " ++ terms.pp ++ " } " ;

  top.terminalModifiers = [dominatesTerminalModifierSpec(terms.precTermList)];

  top.errors = terms.errors;
  top.typeErrors = terms.typeErrors;

  forwards to terminalModifierDefault();
}

concrete production terminalModifierSubmitsTo
top::TerminalModifier ::= 'submits' 'to' '{' terms::TermPrecList  '}'
{
  top.pp = "submits to { " ++ terms.pp ++ " } " ;

  top.terminalModifiers = [submitsToTerminalModifierSpec(terms.precTermList)];

  top.errors = terms.errors;
  top.typeErrors = terms.typeErrors;

  forwards to terminalModifierDefault();
}

synthesized attribute precTermList :: [String];
nonterminal TermPrecList with pp, location, precTermList, defs, errors, typeErrors, env, file;

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

  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(h.name,top.env);

  production attribute fName :: String;
  fName = if null(fNames) then "_NULL_" else head(fNames).fullName;

  production attribute typeItem :: [Decorated EnvItem];
  typeItem = getTypeDcl(fName, top.env);

  production attribute classItem :: [Decorated EnvItem];
  classItem = getLexerClassDcl(fName, top.env);

  production attribute isValidName :: Boolean;
  isValidName = (!null(typeItem) && head(typeItem).typerep.isTerminal) || !null(classItem);

  top.precTermList = if !isValidName
                     then t.precTermList
                     else [fName] ++ t.precTermList ;

  top.defs = addValueDcl(fName, termTypeRep(h.name, ""), 
	     addFullNameDcl(h.name, fName,
             t.defs));

  top.errors := if null(fNames)
                then [err(h.location, "'" ++ h.name ++ "' is not declared.")] ++ t.errors
                else t.errors;

  top.typeErrors = if !isValidName
                    then [err(h.location, "'" ++ h.name ++ "' is not a terminal or a lexer class.")] ++ t.typeErrors
                    else t.typeErrors;
  
}

function addTerminalAttrDefs
Decorated Defs ::= tailDefs::Decorated Defs
{
   return addFullNameDcl("lexeme","lexeme",
           addValueDcl("lexeme",stringTypeRep(),
            addFullNameDcl("filename","filename",
             addValueDcl("filename",stringTypeRep(),
              addFullNameDcl("line","line",
               addValueDcl("line",integerTypeRep(),
                addFullNameDcl("column","column",
                 addValueDcl("column",integerTypeRep(),
                  tailDefs))))))));
}

concrete production terminalModifierActionCode
top::TerminalModifier ::= 'action' acode::ActionCode_c
{
  top.pp = "action " ++ acode.actionCode;

  top.terminalModifiers = [actionCodeTerminalModifierSpec(acode.actionCode)];

  acode.actionCodeType = terminalActionType();
  acode.env = appendDefsEnv(addTerminalAttrDefs(acode.defs),top.env);

  acode.signatureEnv = emptyEnv();
  acode.localsEnv = toEnv(acode.defs);

  acode.signature = error("No signature for terminal action code.");

  forwards to terminalModifierDefault();
}

abstract production termPrecListNull
terms::TermPrecList ::=
{
   terms.precTermList = [];
   terms.defs = emptyDefs();
   terms.pp = "";
   terms.location = loc("termPrecListNull", -1, -1);
   terms.errors = [];
   terms.typeErrors = [];
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

  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(n.name, top.env);

  production attribute fName :: String;
  fName = if null(fNames) then "_NULL_" else head(fNames).fullName;

  production attribute typeItem :: [Decorated EnvItem];
  typeItem = getLexerClassDcl(fName, top.env);

  top.lexerClasses = if null(typeItem)
                     then t.lexerClasses
                     else [fName] ++ t.lexerClasses;

  local attribute e1 :: [Decorated Message];
  e1 = if null(typeItem)
       then [err(n.location, "Lexer class '" ++ n.name ++ "' is not defined.")]
       else [];

  top.errors := e1 ++ t.errors;
  top.terminalModifiers = (if null(typeItem) then [] else [submitsToTerminalModifierSpec(head(typeItem).submitsTo), dominatesTerminalModifierSpec(head(typeItem).termDominates)]) ++ t.terminalModifiers;

}

abstract production lexerClassesNull
cl::ClassList ::=
{
  cl.terminalModifiers = [];
  cl.pp = "";
  cl.errors := [];
  cl.lexerClasses = [];
}

