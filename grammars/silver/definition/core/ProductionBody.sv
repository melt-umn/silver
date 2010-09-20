grammar silver:definition:core;

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
  fName = top.signature.fullName ++ ":local:" ++ a.name;

  top.defs = addLocalDcl(top.grammarName, a.location, fName, te.typerep, emptyDefs());

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := te.errors;
  top.warnings := [];
}

concrete production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type ';'
{
  top.pp = "\tproduction attribute " ++ a.pp ++ "::" ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = addPaDcl(top.grammarName, a.location, top.signature.fullName,
                                localDcl(top.grammarName, a.location, fName, te.typerep),
                                emptyDefs());

  production attribute fName :: String;
  fName = top.signature.fullName ++ ":local:" ++ a.name;

  top.defs = emptyDefs(); -- addLocalDcl(top.grammarName, a.location, fName, te.typerep, emptyDefs());

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := te.errors;
  top.warnings := [];
}

concrete production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.pp = "\tforwards to " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = addPaDcl(top.grammarName, top.location, top.signature.fullName,
                                forwardDcl(top.grammarName, top.location, top.signature.outputElement.typerep),
                                emptyDefs());

  top.defs = addForwardDcl(top.grammarName, top.location, top.signature.outputElement.typerep, emptyDefs());

  top.errors := e.errors;
  top.warnings := [];

  e.expected = expected_undecorated();
}

concrete production forwardsToWith
top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  top.pp = "\tforwards to " ++ e.pp ++ " with {" ++ inh.pp ++ "};";
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = addPaDcl(top.grammarName, top.location, top.signature.fullName,
                                forwardDcl(top.grammarName, top.location, top.signature.outputElement.typerep),
                                emptyDefs());

  top.defs = addForwardDcl(top.grammarName, top.location, top.signature.outputElement.typerep, emptyDefs());

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
  
  production attribute fwdDcls :: [Decorated DclInfo];
  fwdDcls = getValueDcl("forward", top.env);

  top.errors := if null(fwdDcls)
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
top::ProductionStmt ::= dl::DefLHS '.' attr::QName '=' e::Expr ';'
{
  top.errors <- attr.lookupAttribute.errors;
  top.warnings := [];

  top.productionAttributes = emptyDefs();
  top.defs = emptyDefs();

  forwards to if null(attr.lookupAttribute.dcls)
              then errorAttributeDef(dl, $2, attr, $4, e)
              else attr.lookupAttribute.dcl.attrDefDispatcher(dl, $2, attr, $4, e);
}

abstract production errorAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  e.expected = expected_type(attr.lookupAttribute.typerep);
  
  top.errors := e.errors;
  -- no special error message, as the only way to get here is via bad lookup
  -- also, don't go into dl, since we don't have an inh/syn to give it.
  -- TODO: this design is a bit busted!
  -- future improvements require us to dispatch on LHS first, attribute second.
  -- fix that shit. ugh. I just designed it this way, too :( later...
}

abstract production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  e.expected = expected_type(attr.lookupAttribute.typerep);
  dl.isSynthesizedDefinition = true;
  
  top.errors := dl.errors ++ e.errors;
}

abstract production inheritedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  e.expected = expected_type(attr.lookupAttribute.typerep);
  dl.isSynthesizedDefinition = false;
  
  top.errors := dl.errors ++ e.errors;
}

inherited attribute isSynthesizedDefinition :: Boolean occurs on DefLHS; -- true = syn, false = inh

concrete production concreteDefLHS
top::DefLHS ::= q::QName
{
  top.errors := q.lookupValue.errors;
  
  forwards to if null(q.lookupValue.dcls)
              then errorDefLHS(q)
              else q.lookupValue.dcl.defLHSDispatcher(q);
}

abstract production childDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if top.isSynthesizedDefinition
                then [err(q.location, "Cannot define synthesized attribute on child " ++ q.pp)]
                else [];
  top.typerep = q.lookupValue.typerep;
}

abstract production lhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if top.isSynthesizedDefinition
                then [err(q.location, "Cannot define inherited attribute on " ++ q.pp)]
                else [];
  top.typerep = q.lookupValue.typerep;
}

abstract production localDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if top.isSynthesizedDefinition
                then [err(q.location, "Cannot define synthesized attribute on local " ++ q.pp)]
                else [];
  top.typerep = q.lookupValue.typerep;
}

abstract production forwardDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if top.isSynthesizedDefinition
                then [err(q.location, "Cannot define synthesized attribute on forward")]
                else [];
  top.typerep = q.lookupValue.typerep;
}

abstract production errorDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := [err(q.location, "Cannot define attributes on " ++ q.pp)];
  top.typerep = q.lookupValue.typerep;
}

concrete production valueDef
top::ProductionStmt ::= val::QName '=' e::Expr ';'
{
  top.errors <- val.lookupValue.errors;

  top.productionAttributes = emptyDefs();
  top.defs = emptyDefs();
  
  forwards to if null(val.lookupValue.dcls)
              then errorValueDef(val, $2, e)
              else val.lookupValue.dcl.defDispatcher(val, $2, e);
}

abstract production errorValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := [err(val.location, val.pp ++ " cannot be assigned to.")] ++ e.errors;
  top.warnings := [];

  e.expected = expected_type(val.lookupValue.typerep);  
}

abstract production localValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  -- TODO: we need a redefinition check here!
  
  top.errors := e.errors;
  top.warnings := [];

  e.expected = expected_type(val.lookupValue.typerep);  
}

