grammar silver:modification:collection;
import silver:definition:core;
import silver:definition:env;

nonterminal NameOrBOperator with location, grammarName, file, warnings, errors, env, pp, operation;
nonterminal Operation with unparse; -- TODO: we should put a typerep on here, and use it to double check shit works.

synthesized attribute operation :: Operation;

concrete production nameOperator
top::NameOrBOperator ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;
  top.operation = nameOperation(q.lookupValue.fullName); 

  top.errors := q.lookupValue.errors;
  top.warnings := [];
}

concrete production plusplusOperator
top::NameOrBOperator ::= '++'
{
  top.pp = "++";
  top.location = loc(top.file, $1.line, $1.column);
  top.operation = plusPlusOperation();
  top.errors := []; -- check string/list somehow?
  top.warnings := [];
}

abstract production nameOperation
top::Operation ::= s::String
{
  top.unparse = "'" ++ s ++ "'";
}

abstract production plusPlusOperation
top::Operation ::= 
{
  top.unparse = "++";
}

--- Declarations

concrete production collectionAttributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "synthesized attribute " ++ a.name ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addSynColDcl(top.grammarName, a.location, fName, te.typerep, q.operation, emptyDefs());

  top.errors <-
        if length(getAttrDcl(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := te.errors ++ q.errors;
  top.warnings := [];

  forwards to attributeDclSyn($1, $2, a, $4, te, $8);
}

concrete production collectionAttributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "inherited attribute " ++ a.name ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addInhColDcl(top.grammarName, a.location, fName, te.typerep, q.operation, emptyDefs());

  top.errors <-
        if length(getAttrDcl(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := te.errors ++ q.errors;
  top.warnings := [];

  forwards to attributeDclInh($1, $2, a, $4, te, $8);
}


concrete production collectionAttributeDclProd
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "production attribute " ++ a.name ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.signature.fullName ++ ":local:" ++ a.name;

  top.productionAttributes = addPaDcl(top.grammarName, a.location, top.signature.fullName,
                                localCollectionDcl(top.grammarName, a.location, fName, te.typerep, q.operation),
                                emptyDefs());

  top.defs = emptyDefs(); -- addLocalColDcl(top.grammarName, a.location, fName, te.typerep, q.operation, emptyDefs());

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := te.errors ++ q.errors;
  top.warnings := [];
 
  forwards to productionAttributeDcl($1, $2, a, $4, te, $8);
}

--- The use semantics

abstract production errorCollectionDefDispatcher
top::ProductionStmt ::= dl::DefLHS '.' q::Decorated QName '=' e::Expr
{
  top.errors <- [err(loc(top.file, $4.line, $4.column), "The ':=' and '<-' operators can only be used for collections. " ++ q.pp ++ " is not a collection.")];

  forwards to errorAttributeDef(dl,$2,q,$4,e);
}
abstract production errorColNormalAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' q::Decorated QName '=' e::Expr
{
  top.errors <- [err(loc(top.file, $4.line, $4.column), q.pp ++ " is a collection attribute, and you must use ':=' or '<-', not '='.")];

  forwards to errorAttributeDef(dl,$2,q,$4,e);
}
abstract production errorCollectionValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.errors <- [err(loc(top.file, $2.line, $2.column), "The ':=' and '<-' operators can only be used for collections. " ++ val.pp ++ " is not a collection.")];
  
  -- TODO: this production also produces an error message, so we'll produce two errors for one flaw.
  -- We don't want to use := for the errors, because we'd miss any errors in e, and we don't want to repeat
  -- it because that will produce duplicate trees.
  forwards to errorValueDef(val, $2, e);
}
abstract production errorColNormalValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.errors <- [err(loc(top.file, $2.line, $2.column), val.pp ++ " is a collection attribute, and you must use ':=' or '<-', not '='.")];
  
  -- TODO: same problem
  forwards to errorValueDef(val, $2, e);
}

-- TODO: these next six are copy & pastes from what they forward to... ugly!
abstract production baseCollectionValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " := " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  -- TODO: we need a redefinition check here!
  
  top.errors := e.errors;
  top.warnings := [];

  e.expected = expected_type(val.lookupValue.typerep);  

  forwards to localValueDef(val, $2, e);
}
abstract production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " <- " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  -- TODO: we need a redefinition check here!
  
  top.errors := e.errors;
  top.warnings := [];

  e.expected = expected_type(val.lookupValue.typerep);  

  forwards to localValueDef(val, $2, e);
}
abstract production synBaseColAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " := " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  e.expected = expected_type(attr.lookupAttribute.typerep);
  dl.isSynthesizedDefinition = true;
  
  top.errors := dl.errors ++ e.errors;

  forwards to synthesizedAttributeDef(dl, $2, attr, $4, e);
}
abstract production synAppendColAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " <- " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  e.expected = expected_type(attr.lookupAttribute.typerep);
  dl.isSynthesizedDefinition = true;
  
  top.errors := dl.errors ++ e.errors;

  forwards to synthesizedAttributeDef(dl, $2, attr, $4, e);
}
abstract production inhBaseColAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " := " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  e.expected = expected_type(attr.lookupAttribute.typerep);
  dl.isSynthesizedDefinition = false;
  
  top.errors := dl.errors ++ e.errors;

  forwards to inheritedAttributeDef(dl, $2, attr, $4, e);
}
abstract production inhAppendColAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " <- " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  e.expected = expected_type(attr.lookupAttribute.typerep);
  dl.isSynthesizedDefinition = false;
  
  top.errors := dl.errors ++ e.errors;

  forwards to inheritedAttributeDef(dl, $2, attr, $4, e);
}

-- The use syntax

terminal Contains_t   '<-';
terminal BaseContains_t   ':=';

concrete production attrContainsAppend
top::ProductionStmt ::= dl::DefLHS '.' attr::QName '<-' e::Expr ';'
{
  top.errors <- attr.lookupAttribute.errors;
  top.warnings := [];

  top.productionAttributes = emptyDefs();
  top.defs = emptyDefs();

  forwards to if null(attr.lookupAttribute.dcls)
              then errorAttributeDef(dl, $2, attr, terminal(Equal_t, "<-", $4), e)
              else attr.lookupAttribute.dcl.attrAppendDefDispatcher(dl, $2, attr, terminal(Equal_t, "<-", $4), e);
}

concrete production attrContainsBase
top::ProductionStmt ::= dl::DefLHS '.' attr::QName ':=' e::Expr ';'
{
  top.errors <- attr.lookupAttribute.errors;
  top.warnings := [];

  top.productionAttributes = emptyDefs();
  top.defs = emptyDefs();

  forwards to if null(attr.lookupAttribute.dcls)
              then errorAttributeDef(dl, $2, attr, terminal(Equal_t, ":=", $4), e)
              else attr.lookupAttribute.dcl.attrBaseDefDispatcher(dl, $2, attr, terminal(Equal_t, ":=", $4), e);
}

concrete production valContainsAppend
top::ProductionStmt ::= val::QName '<-' e::Expr ';'
{
  top.errors <- val.lookupValue.errors;

  top.productionAttributes = emptyDefs();
  top.defs = emptyDefs();
  
  forwards to if null(val.lookupValue.dcls)
              then errorValueDef(val, terminal(Equal_t, "<-", $2), e)
              else val.lookupValue.dcl.appendDefDispatcher(val, terminal(Equal_t, "<-", $2), e);
}

concrete production valContainsBase
top::ProductionStmt ::= val::QName ':=' e::Expr ';'
{
  top.errors <- val.lookupValue.errors;

  top.productionAttributes = emptyDefs();
  top.defs = emptyDefs();
  
  forwards to if null(val.lookupValue.dcls)
              then errorValueDef(val, terminal(Equal_t, ":=", $2), e)
              else val.lookupValue.dcl.baseDefDispatcher(val, terminal(Equal_t, ":=", $2), e);
}

