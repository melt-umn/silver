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
top::ProductionStmt ::= 'pluck' e::Expr c4::Semi_t
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

aspect production baseExpr
top::Expr ::= q::QName
{
  fwd <- if top.actionCodeType.isDisambigGroupAction && in_sig then [pluckTermReference(q)] else 
         if !top.actionCodeType.isSemanticBlock && (q.name == "lexeme"
                                                 || q.name == "line"
                                                 || q.name == "column"
                                                 || q.name == "filename") then [actionTerminalReference(q)] else
         if isParserAttribute(fName, top.env) then [parserAttributeReference(q)] else
         if (in_locals && !top.actionCodeType.isSemanticBlock) then [localParserAttributeReference(q)] else
         if (in_sig && !top.actionCodeType.isSemanticBlock) then [parserChildReference(q)] else
         [];
}

abstract production pluckTermReference
top::Expr ::= q::QName
{
  top.pp = q.pp; 
  top.location = q.location;

  --the full name of the identifier
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

  top.errors := [];
  top.warnings := [];

  top.typerep = topTypeRep(); -- TODO: BUG: Need a real type here (AnyTerminalType or something)
  top.isAppReference = false;
  top.appReference = "";
  top.translation = makeCopperName(fName);
  -- top.typeErrors =
}

abstract production actionTerminalReference
top::Expr ::= q::QName
{
  top.pp = q.pp; 
  top.location = q.location;

  top.errors := [];
  top.warnings := [];

  top.typerep = if q.name == "lexeme" || q.name == "filename" then stringTypeRep() else integerTypeRep();
  top.isAppReference = false;
  top.appReference = "";

  top.translation = if q.name == "lexeme" then "new common.StringCatter(lexeme)" else
                    if q.name == "line" then "virtualLocation.getLine()" else
                    if q.name == "column" then "virtualLocation.getColumn()" else
                    if q.name == "filename" then "new common.StringCatter(virtualLocation.getFileName())" else
                    error("unknown actionTerminalReference " ++ q.name); -- should never be called, but here for safety

  top.typeErrors = [];
}

abstract production parserAttributeReference
top::Expr ::= q::QName
{
  top.pp = q.pp; 
  top.location = q.location;

  --the full name of the identifier
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

  production attribute vals :: [Decorated EnvItem];
  vals = getValueDcl(fName, top.env);

  top.errors := if top.actionCodeType.isSemanticBlock
                then [err(top.location, "References to parser attributes can only be made in action blocks")]
                else [];
  top.warnings := [];

  top.typerep = head(vals).typerep;
  top.isAppReference = false;
  top.appReference = "";
  top.translation = makeCopperName(fName);

  top.typeErrors = [];
}

abstract production localParserAttributeReference
top::Expr ::= q::QName
{
  forwards to parserAttributeReference(q);
}

abstract production parserChildReference
top::Expr ::= q::QName
{
  top.pp = q.pp; 
  top.location = q.location;

  --the full name of the identifier
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

  production attribute vals :: [Decorated EnvItem];
  vals = getValueDcl(fName, top.env);

  top.errors := [];
  top.warnings := [];

  top.typerep = head(vals).typerep;
  top.isAppReference = false;
  top.appReference = "";
  top.translation = "((" ++ head(vals).typerep.transType ++ ")((common.Node)RESULT).getChild(" ++ makeClassName(top.signature.fullName) ++ ".i_" ++ fName ++ "))";

  top.typeErrors = [];
}

concrete production printStmt
top::ProductionStmt ::= 'print' c3::Expr c4::Semi_t
{
  top.pp = "print " ++ c3.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.translation = "System.err.println(" ++ c3.translation ++ ");\n";

  top.defs = emptyDefs();

  top.warnings := [];
  top.errors := (if top.actionCodeType.isSemanticBlock
               then [err(top.location, "'print' statement allowed only in parser action blocks. You may be looking for print(String,IO) :: IO.")]
               else [])
               ++ c3.errors;

  top.typeErrors = if c3.typerep.typeName == "String"
		   then []
		   else [err(top.location, "Parameter to 'print' must be of type String.")];

  c3.expected = expected_default();
}


synthesized attribute isParserLHS :: Boolean occurs on LHSExpr;
synthesized attribute isLocalParserLHS :: Boolean occurs on LHSExpr;

aspect production lhsExprOne
top::LHSExpr ::= id::Name
{
  top.isParserLHS = isParserAttribute(fName, top.env);
  top.isLocalParserLHS = !top.isParserLHS && !top.actionCodeType.isSemanticBlock;
}

aspect production lhsExprTwo
top::LHSExpr ::= id::Name '.' q::QName
{
  top.isParserLHS = false;
  top.isLocalParserLHS = false;
  top.errors <- if isParserAttribute(fName1, top.env) then [err(top.location, "Parser action block are imperative, not declarative. You cannot modify the attributes of " ++ id.name ++ ". If you are trying to set inherited attributes, use 'decorate " ++ id.name ++ " with { ... }' when you create it. (Note that then the type should be Decorated.)")] else [];
}

aspect production attributeDef
top::ProductionStmt ::= lhs::LHSExpr '=' e::Expr ';'
{
  fwd <- if lhs.isParserLHS then [parserAttributeDef(lhs,e)] else
         if (lhs.nodeName == "filename" ||
             lhs.nodeName == "line" ||
             lhs.nodeName == "column") then [terminalAttributeDef(lhs,e)] else
         if lhs.isLocalParserLHS then [localParserAttributeDef(lhs,e)] else
         [];
}

-- Note: AttributeDef already <- in lhs.errors and e.errors.  So we don't need to include them in these.
abstract production parserAttributeDef
top::ProductionStmt ::= lhs::Decorated LHSExpr e::Decorated Expr
{
  top.setupInh := "";
  top.translation = makeCopperName(lhs.nodeName) ++ " = " ++ e.translation ++ ";\n";
  top.errors := (if top.actionCodeType.isSemanticBlock
                then [err(lhs.location, "Assignment to parser attributes only permitted in parser action blocks")]
                else []);
}

-- This is the same, but for modularity purposes is separate
abstract production localParserAttributeDef
top::ProductionStmt ::= lhs::Decorated LHSExpr e::Decorated Expr
{
  forwards to parserAttributeDef(lhs,e);
}

abstract production terminalAttributeDef
top::ProductionStmt ::= lhs::Decorated LHSExpr e::Decorated Expr
{
  local attribute memberfunc :: String;
  memberfunc = if lhs.nodeName == "filename" then "setFileName" else
               if lhs.nodeName == "line" then "setLine" else
               if lhs.nodeName == "column" then "setColumn" else
               error("unknown assignment to terminal attribute: " ++ lhs.nodeName);

  top.setupInh := "";
  top.translation = "virtualLocation." ++ memberfunc ++ "(" ++ e.translation
                     ++ (if lhs.nodeName == "filename" then ".toString()" else "") ++ ");\n";
  top.errors := (if top.actionCodeType.isSemanticBlock
                then [err(lhs.location, "Assignment to location attributes only permitted in parser action blocks")]
                else []);
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


