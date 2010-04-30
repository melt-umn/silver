grammar silver:definition:core;
import silver:definition:env;

concrete production emptyProductionBodySemi
top::ProductionBody ::= ';'
{
  top.pp = ";";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to emptyProductionBody() ;
}

concrete production emptyProductionBodyCurly
top::ProductionBody ::= '{' '}'
{
  top.pp = "{}";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to emptyProductionBody() ;
}

abstract production emptyProductionBody
top::ProductionBody ::=
{ 
  top.pp = "";
  top.location = loc(top.file, -1, -1);

  forwards to productionBody('{', productionStmtsNone(), '}') ;
}

concrete production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  top.pp = "{" ++ stmts.pp ++ "}";
  top.location = loc(top.file, $1.line, $1.column);

  production attribute extraStmts :: ProductionStmts with productionStmtsAppend ;
  extraStmts := stmts;

  forwards to defaultProductionBody(extraStmts) ;
}

abstract production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{

  top.pp = stmts.pp;
  top.location = stmts.location;
  top.defs = stmts.defs;

  top.productionAttributes = stmts.productionAttributes;

  top.errors := stmts.errors;
  top.warnings := [];
}

abstract production productionStmtsNone
top::ProductionStmts ::= 
{
  top.pp = "";
  top.location = loc(top.file, -1, -1);
  top.defs = emptyDefs();

  top.productionAttributes = emptyDefs();

  top.errors := [];
  top.warnings := [];
}

concrete production productionStmts
top::ProductionStmts ::= stmt::ProductionStmt
{
  top.pp = stmt.pp;
  top.location = stmt.location;

  top.productionAttributes = stmt.productionAttributes;
 
  top.defs = stmt.defs;
  top.errors := stmt.errors;
  top.warnings := [];
}

concrete production productionStmtsCons
top::ProductionStmts ::= h::ProductionStmt t::ProductionStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;
  top.productionAttributes = appendDefs(h.productionAttributes, t.productionAttributes);

  top.defs = appendDefs(h.defs, t.defs);
  top.errors := h.errors ++ t.errors;
  top.warnings := [];
}

abstract production productionStmtsAppend
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;
  top.defs = appendDefs(h.defs, t.defs);

  top.productionAttributes = appendDefs(h.productionAttributes, t.productionAttributes);

  top.errors := h.errors ++ t.errors;
  top.warnings := [];
}

concrete production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.pp = "\treturn " ++ e.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = emptyDefs();

  top.defs = emptyDefs();
  top.errors := e.errors;
  top.warnings := [];

  e.expected = expected_type(top.signature.outputElement.typerep);
}

concrete production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  top.pp = "\tlocal attribute " ++ a.pp ++ "::" ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = emptyDefs();

  production attribute fName :: String;
  fName = top.signature.fullName ++ ":l_" ++ a.name;

  top.defs = addValueDcl(fName, te.typerep, 
	     addFullNameDcl(a.name, fName,  emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
        then [err(top.location, "Name '" ++ a.name ++ "' is already bound.")]
        else [];

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclOne(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := er1 ++ er2 ++ te.errors;
  top.warnings := [];
}

concrete production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type ';'
{
  top.pp = "\tproduction attribute " ++ a.pp ++ "::" ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = top.defs;

  production attribute fName :: String;
  fName = top.signature.fullName ++ ":l_" ++ a.name;

  top.defs = addValueDcl(fName, te.typerep, 
	     addFullNameDcl(a.name, fName,  emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
        then [err(top.location, "Name '" ++ a.name ++ "' is already bound.")]
        else [];

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclOne(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := er1 ++ er2 ++ te.errors;
  top.warnings := [];
}

concrete production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.pp = "\tforwards to " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = top.defs;

  production attribute fName :: String;
  fName = top.signature.fullName ++ ":l_forward";

  top.defs = addValueDcl(fName, top.signature.outputElement.typerep, 
	     addFullNameDcl("forward", fName,  emptyDefs()));

  top.errors := e.errors;
  top.warnings := [];

  e.expected = expected_undecorated();
}

concrete production forwardsToWith
top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  top.pp = "\tforwards to " ++ e.pp ++ " with {" ++ inh.pp ++ "};";
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = top.defs;

  production attribute fName :: String;
  fName = top.signature.fullName ++ ":l_forward";

  top.defs = addValueDcl(fName, top.signature.outputElement.typerep, 
	     addFullNameDcl("forward", fName,  emptyDefs()));

  top.errors := e.errors ++ inh.errors;
  top.warnings := [];

  e.expected = expected_undecorated();
}

concrete production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.pp = "\tforwarding with {" ++ inh.pp ++ "};";
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = emptyDefs();
  top.defs = emptyDefs();
  
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl("forward", top.env);

  top.errors := if null(fNames)
                then [err(top.location, "'forwarding with' clause for a production that does not forward!")]
                else [];
  top.warnings := [];
}

concrete production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  top.pp = lhs.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := lhs.errors ++ e.errors;
  top.warnings := [];

  e.expected = expected_type(lhs.typerep);
}

concrete production forwardInhsOne
top::ForwardInhs ::= lhs::ForwardInh
{
  top.pp = lhs.pp;
  top.location = lhs.location;
  top.errors := lhs.errors;
  top.warnings := [];
}

concrete production forwardInhsCons
top::ForwardInhs ::= lhs::ForwardInh rhs::ForwardInhs
{
  top.pp = lhs.pp ++ " " ++ rhs.pp;
  top.location = lhs.location;
  top.errors := lhs.errors ++ rhs.errors;
  top.warnings := [];
}

concrete production forwardLhsExpr
top::ForwardLHSExpr ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.errors := q.lookupAttribute.errors;
  top.warnings := [];
  top.typerep = q.lookupAttribute.typerep;
}

concrete production attributeDef
top::ProductionStmt ::= val::QName '.' attr::QName '=' e::Expr ';'
{
  top.pp = "\t" ++ val.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  top.productionAttributes = emptyDefs();
  top.defs = emptyDefs();

  top.errors <- val.lookupValue.errors ++ attr.lookupAttribute.errors ++ e.errors ++ if length(fwd) > 1 then [err(top.location,"Ambiguous definition dispatch.")] else []; -- TODO: better error messages
  top.warnings := [];

  e.expected = expected_type(attr.lookupAttribute.typerep);
  
  -- TODO: this whole override thing needs massaging to look nice. It's ugly right now.
  production attribute fwd :: [ProductionStmt] with ++;
  fwd := [];
  
  forwards to if null(fwd) then normalAttributeDef(val, $2, attr, $4, e) else head(fwd);
}

abstract production normalAttributeDef
top::ProductionStmt ::= val::QName '.' attr::QName '=' e::Decorated Expr
{
  top.errors := [];
}

concrete production valueDef
top::ProductionStmt ::= val::QName '=' e::Expr ';'
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  top.productionAttributes = emptyDefs();
  top.defs = emptyDefs();

  top.errors <- val.lookupValue.errors ++ e.errors ++ if length(fwd) > 1 then [err(top.location,"Ambiguous assignment.")] else []; -- TODO: better error messages
  top.warnings := [];

  e.expected = expected_type(val.lookupValue.typerep);
  
  -- TODO: this whole override thing needs massaging to look nice. It's ugly right now.
  production attribute fwd :: [ProductionStmt] with ++;
  fwd := [];
  
  forwards to if null(fwd) then normalValueDef(val, $2, e) else head(fwd);
}

abstract production normalValueDef
top::ProductionStmt ::= val::QName '=' e::Decorated Expr
{
  top.errors := [];
}

