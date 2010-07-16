grammar silver:translation:java:concrete_syntax:copper;

import silver:definition:core;
import silver:definition:env;
import silver:translation:java:core;
import silver:translation:java:env;

import silver:analysis:typechecking:core;

terminal Pluck_kwd 'pluck' lexer classes {KEYWORD};
terminal Print_kwd 'print' lexer classes {KEYWORD};

concrete production namePrint
top::Name ::= 'print'
{ forwards to nameId(terminal(Id_t, "print", $1)); }

concrete production namePluck
top::Name ::= 'pluck'
{ forwards to nameId(terminal(Id_t, "pluck", $1)); }


concrete production pluckDef
top::ProductionStmt ::= 'pluck' e::Expr ';'
{
  top.pp = "pluck " ++ e.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.translation = "return " ++ e.translation ++ ";\n";

  top.defs = emptyDefs();

  top.warnings := [];
  top.errors := (if !top.actionCodeType.isDisambigGroupAction
               then [err(top.location, "'pluck' allowed only in disambiguation-group parser actions.")]
               else [])
               ++ e.errors;

  -- TODO: figure out wtf is going on with type here! (needs to be a terminal, plus one of the ones in the disgroup)
  top.typeErrors = [];

  e.expected = expected_default();
}

concrete production printStmt
top::ProductionStmt ::= 'print' e::Expr ';'
{
  top.pp = "print " ++ e.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.translation = "System.err.println(" ++ e.translation ++ ");\n";

  top.defs = emptyDefs();

  top.warnings := [];
  top.errors := (if top.actionCodeType.isSemanticBlock
               then [err(top.location, "'print' statement allowed only in parser action blocks. You may be looking for print(String,IO) :: IO.")]
               else [])
               ++ e.errors;

  top.typeErrors = if e.typerep.typeName == "String"
		   then []
		   else [err(top.location, "Parameter to 'print' must be of type String.")];

  e.expected = expected_default();
}

aspect production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.errors <- if !top.actionCodeType.isSemanticBlock
                then [err(top.location, "Cannot forward in an action block.")]
                else [];
}

aspect production forwardsToWith
top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  top.errors <- if !top.actionCodeType.isSemanticBlock
                then [err(top.location, "Cannot forward in an action block.")]
                else [];
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.errors <- if !top.actionCodeType.isSemanticBlock
                then [err(top.location, "Cannot forward in an action block.")]
                else [];
}

aspect production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type ';'
{
  top.errors <- if !top.actionCodeType.isSemanticBlock
                then [err(top.location, "Cannot declare production attributes in an action block. Only locals.")]
                else [];
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  -- TODO see ugly hack in ActionCode.sv
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.errors <- if !top.actionCodeType.isSemanticBlock
                then if top.actionCodeType.isDisambigGroupAction
                     then [err(top.location, "Cannot return in a disambiguation function. (Are you looking for 'pluck'?)")]
                     else if top.actionCodeType.isParserAttrAction
                     then [err(top.location, "Cannot return in an action block. (To initialize parser attributes, assign to them.)")]
                     else [err(top.location, "Cannot return in an action block.")]
                else [];
}


