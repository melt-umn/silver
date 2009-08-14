grammar silver:translation:java:concrete_syntax:copper;

import core;

import silver:definition:core;
import silver:definition:env;

import silver:analysis:typechecking:core;

import silver:extension:list;

terminal Pluck_kwd 'pluck' lexer classes {KEYWORD};

concrete production pluckDef
top::ProductionStmt ::= 'pluck' e::Expr c4::Semi_t
{
  top.pp = "pluck " ++ e.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.actionCode = "return " ++ e.actionCode ++ ";\n";
  top.actionErrors = [];
  top.actionTypeErrors = [];

  top.defs = emptyDefs();

  top.warnings := [];
  top.errors := if top.actionCodeType.isSemanticBlock
               then [err(top.location, "Construct 'pluckDef' allowed only in disambiguation-group parser actions.")]
               else [];

  top.typeErrors = [];

  e.expected = expected_default();
}

concrete production printStmt
top::ProductionStmt ::= 'print' c3::Expr c4::Semi_t
{
  top.pp = "print " ++ c3.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.actionCode = "System.err.println(" ++ c3.actionCode ++ ");\n";
  top.actionErrors = [];
  top.actionTypeErrors = [];

  top.defs = emptyDefs();

  top.warnings := [];
  top.errors := if top.actionCodeType.isSemanticBlock
               then [err(top.location, "Construct 'print' allowed only in parser actions.")]
               else [];

  top.typeErrors = if c3.typerep.typeName == "String"
		   then []
		   else [err(top.location, "Parameter to 'print' must be of type String.")];

  c3.expected = expected_default();
}

terminal ListContains_kwd 'listContains' lexer classes {KEYWORD};

concrete production listContainsExpr
top::Expr ::= 'listContains' '(' e1::Expr ',' e2::Expr ')'
{
  top.pp = "list( " ++ e1.pp ++ ","  ++ e2.pp ++ " )";
  top.location = loc(top.file, $1.line, $1.column);

  top.typeErrors = 
       if !e2.typerep.isList
       then [err(top.location, "Second argument to 'contains' must be a list, is '" ++ e2.typerep.typeName ++ ".")]
       else if !e2.typerep.listComponent.typeEquals(e2.typerep.listComponent,e1.typerep).bValue
            then [err(top.location, "Type of element and list must be the same, are '" ++ e1.typerep.typeName ++ "' and '" ++ e2.typerep.listComponent.typeName ++ "'.")]
	    else [];

  top.actionCode = "listContains ( " ++ e1.actionCode ++ ", " ++ e2.actionCode ++ " ) ";
  top.actionErrors = [];
  top.actionTypeErrors = [];

  top.typerep = booleanTypeRep();

  top.warnings := [];
  top.errors := if top.actionCodeType.isSemanticBlock
               then [err(top.location, "Construct 'contains' allowed only in parser actions.")]
               else [];

}
