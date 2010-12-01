grammar silver:translation:java:concrete_syntax:copper;

import silver:definition:core;
import silver:definition:env;
import silver:translation:java:core;
import silver:translation:java:type;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:analysis:typechecking:core;
import silver:analysis:typechecking;

terminal Pluck_kwd 'pluck' lexer classes {KEYWORD};
terminal Print_kwd 'print' lexer classes {KEYWORD};

concrete production namePrint
top::Name ::= 'print'
{ forwards to nameIdLower(terminal(IdLower_t, "print", $1)); }

concrete production namePluck
top::Name ::= 'pluck'
{ forwards to nameIdLower(terminal(IdLower_t, "pluck", $1)); }


concrete production pluckDef
top::ProductionStmt ::= 'pluck' e::Expr ';'
{
  top.pp = "pluck " ++ e.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.translation = "return " ++ e.translation ++ ";\n";

  top.defs = emptyDefs();

  top.warnings := [];
  top.errors := (if !top.blockContext.permitPluck
               then [err(top.location, "'pluck' allowed only in disambiguation-group parser actions.")]
               else [])
               ++ e.errors;

  -- TODO: figure out wtf is going on with type here! (needs to be a terminal, plus one of the ones in the disgroup)

  e.expected = expected_default();
  
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}

concrete production printStmt
top::ProductionStmt ::= 'print' e::Expr ';'
{
  top.pp = "print " ++ e.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.translation = "System.err.println(" ++ e.translation ++ ");\n";

  top.defs = emptyDefs();

  top.warnings := [];
  top.errors := (if !top.blockContext.permitActions
               then [err(top.location, "'print' statement allowed only in parser action blocks. You may be looking for print(String,IO) :: IO.")]
               else [])
               ++ e.errors;

  e.expected = expected_default();
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e.typerep, stringTypeExp());
  top.errors <-
       if errCheck1.typeerror
       then [err(e.location, "print expects a string, instead it recieved a " ++ errCheck1.leftpp)]
       else [];
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  -- TODO see ugly hack in ActionCode.sv
}


