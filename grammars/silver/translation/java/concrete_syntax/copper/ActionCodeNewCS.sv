grammar silver:translation:java:concrete_syntax:copper;

import silver:definition:core;
import silver:definition:env;
import silver:translation:java:core;

import silver:analysis:typechecking:core;

terminal Pluck_kwd 'pluck' lexer classes {KEYWORD};

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
  local attribute isPA :: Boolean;
  isPA = isParserAttribute(fName, top.env);

  fwdoverride <- if (top.actionCodeType.isDisambigGroupAction && in_sig)
                 || (!top.actionCodeType.isSemanticBlock && (q.name == "lexeme"
                                                         || q.name == "line"
                                                         || q.name == "column"
                                                         || q.name == "filename"))
                 
                 || isPA
                 then [true] else [];
  
  fwd <- if top.actionCodeType.isDisambigGroupAction && in_sig then [pluckTermReference(q)] else 
         if !top.actionCodeType.isSemanticBlock && (q.name == "lexeme"
                                                 || q.name == "line"
                                                 || q.name == "column"
                                                 || q.name == "filename") then [actionTerminalReference(q)] else
         if isPA then [parserAttributeReference(q)] else
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

  top.errors := [];
  top.warnings := [];

  top.typerep = head(vals).typerep;
  top.isAppReference = false;
  top.appReference = "";
  top.translation = makeCopperName(fName);

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

aspect production lhsExprOne
top::LHSExpr ::= id::Name
{
  top.isParserLHS = isParserAttribute(fName, top.env);
}

aspect production lhsExprTwo
top::LHSExpr ::= id::Name '.' q::QName
{
  top.isParserLHS = false;
  top.errors <- if isParserAttribute(fName1, top.env) then [err(top.location, "Parser action block are imperative, not declarative. You cannot modify the attributes of " ++ id.name ++ ". If you are trying to set inherited attributes, use 'decorate " ++ id.name ++ " with { ... }' when you create it. (Note that then the type should be Decorated.)")] else [];
}

aspect production attributeDef
top::ProductionStmt ::= lhs::LHSExpr '=' e::Expr ';'
{
  fwd <- if lhs.isParserLHS then [parserAttributeDef(lhs,e)] else
         if (lhs.nodeName == "filename" ||
             lhs.nodeName == "line" ||
             lhs.nodeName == "column") then [terminalAttributeDef(lhs,e)] else
         [];
}

abstract production parserAttributeDef
top::ProductionStmt ::= lhs::Decorated LHSExpr e::Decorated Expr
{
  top.setupInh = "";
  top.translation = makeCopperName(lhs.nodeName) ++ " = " ++ e.translation ++ ";\n";
  top.errors := (if top.actionCodeType.isSemanticBlock
                then [err(lhs.location, "Assignment to parser attributes only permitted in parser action blocks")]
                else [])
                ++ lhs.errors ++ e.errors;
}

abstract production terminalAttributeDef
top::ProductionStmt ::= lhs::Decorated LHSExpr e::Decorated Expr
{
  local attribute memberfunc :: String;
  memberfunc = if lhs.nodeName == "filename" then "setFileName" else
               if lhs.nodeName == "line" then "setLine" else
               if lhs.nodeName == "column" then "setColumn" else
               error("unknown assignment to terminal attribute: " ++ lhs.nodeName);

  top.setupInh = "";
  top.translation = "virtualLocation." ++ memberfunc ++ "(" ++ e.translation
                     ++ (if lhs.nodeName == "filename" then ".toString()" else "") ++ ");\n";
  top.errors := (if top.actionCodeType.isSemanticBlock
                then [err(lhs.location, "Assignment to location attributes only permitted in parser action blocks")]
                else [])
                ++ lhs.errors ++ e.errors;
}


