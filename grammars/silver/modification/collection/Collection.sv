grammar silver:modification:collection;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:extension:list;
import silver:util;

import silver:analysis:typechecking:core;

nonterminal NameOrBOperator with location, grammarName, file, errors, env, pp, operation, operatorForType;
nonterminal Operation with unparse;

synthesized attribute operation :: Operation;
inherited attribute operatorForType :: TypeExp;

concrete production nameOperator
top::NameOrBOperator ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.operation = case q.lookupValue.dcl of
                    funDcl(_,_,_) -> functionOperation(q.lookupValue.fullName)
                  | prodDcl(_,_,_) -> productionOperation(q.lookupValue.fullName)
                  | _ -> error("INTERNAL ERROR: operation attribute demanded for non-function or production.")
                  end;

  top.errors := q.lookupValue.errors;
  
  -- TODO: this is a complete mess.  refactor it someday, please!
  top.errors <- 
     case q.lookupValue.dcl of
       funDcl(_,_,_) -> 
          if unify(freshenCompletely(q.lookupValue.typerep), functionTypeExp(top.operatorForType, [top.operatorForType, top.operatorForType])).failure
          then [err(top.location, q.pp ++ " must be of type " ++ prettyType(functionTypeExp(top.operatorForType, [top.operatorForType, top.operatorForType])) ++ " instead it is of type " ++ prettyType(q.lookupValue.typerep))]
          else []
                               
     | prodDcl(_,_,_) ->
          if unify(freshenCompletely(q.lookupValue.typerep), functionTypeExp(top.operatorForType, [top.operatorForType, top.operatorForType])).failure
          then [err(top.location, q.pp ++ " must be of type " ++ prettyType(functionTypeExp(top.operatorForType, [top.operatorForType, top.operatorForType])) ++ " instead it is of type " ++ prettyType(q.lookupValue.typerep))]
          else []
                               
     | _ -> [err(top.location, q.pp ++ " is of type " ++ prettyType(q.lookupValue.typerep) ++ " and is not a valid operator for collections.")]
     end;
}

concrete production plusplusOperator
top::NameOrBOperator ::= '++'
{
  top.pp = "++";
  top.location = loc(top.file, $1.line, $1.column);
  top.operation = case top.operatorForType of
                    stringTypeExp() -> plusPlusOperationString()
                  | listTypeExp(_) -> plusPlusOperationList()
                  | _ -> error("INTERNAL ERROR: operation attribute demanded for ++ that isn't string or list.")
                  end;
  top.errors := case top.operatorForType of
                  stringTypeExp() -> []
                | listTypeExp(_) -> []
                | _ -> [err(top.location, "++ operator will only work for collections of type list or String")]
                end;
}

concrete production borOperator
top::NameOrBOperator ::= '||'
{
  top.pp = "||";
  top.location = loc(top.file, $1.line, $1.column);
  top.operation = borOperation();
  top.errors := case top.operatorForType of
                  boolTypeExp() -> []
                | _ -> [err(top.location, "|| operator will only work for collections of type Boolean")]
                end;
}
concrete production bandOperator
top::NameOrBOperator ::= '&&'
{
  top.pp = "&&";
  top.location = loc(top.file, $1.line, $1.column);
  top.operation = bandOperation();
  top.errors := case top.operatorForType of
                  boolTypeExp() -> []
                | _ -> [err(top.location, "&& operator will only work for collections of type Boolean")]
                end;
}

abstract production functionOperation
top::Operation ::= s::String
{
  top.unparse = "fun('" ++ s ++ "')";
}
abstract production productionOperation
top::Operation ::= s::String
{
  top.unparse = "prod('" ++ s ++ "')";
}
abstract production plusPlusOperationString
top::Operation ::= 
{
  top.unparse = "++string";
}
abstract production plusPlusOperationList
top::Operation ::= 
{
  top.unparse = "++list";
}
abstract production borOperation
top::Operation ::= 
{
  top.unparse = "||";
}
abstract production bandOperation
top::Operation ::= 
{
  top.unparse = "&&";
}

--- Declarations ---------------------------------------------------------------
concrete production collectionAttributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name botl::BracketedOptTypeList '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "synthesized attribute " ++ a.name ++ botl.pp ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  production attribute tl :: Decorated TypeList;
  tl = botl.typelist;

  top.defs = addSynColDcl(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, q.operation, emptyDefs());

--------
  botl.env = newScopeEnv( addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTypeVariables),
                        top.env);
  te.env = tl.env;
  top.errors <- if containsDuplicates(tl.lexicalTypeVariables)
                then [err(top.location, "Duplicate type variable names listed")]
                else [];
  top.errors <- tl.errorsTyVars;
--------

  top.errors <-
        if length(getAttrDclAll(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  q.operatorForType = te.typerep;
  top.errors := te.errors ++ q.errors ++ tl.errors;

  forwards to attributeDclSyn($1, $2, a, botl, $5, te, $9);
}

concrete production collectionAttributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name botl::BracketedOptTypeList '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "inherited attribute " ++ a.name ++ botl.pp ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  production attribute tl :: Decorated TypeList;
  tl = botl.typelist;

  top.defs = addInhColDcl(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, q.operation, emptyDefs());

--------
  botl.env = newScopeEnv( addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTypeVariables),
                        top.env);
  te.env = tl.env;
  top.errors <- if containsDuplicates(tl.lexicalTypeVariables)
                then [err(top.location, "Duplicate type variable names listed")]
                else [];
  top.errors <- tl.errorsTyVars;
--------

  top.errors <-
        if length(getAttrDclAll(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  q.operatorForType = te.typerep;
  top.errors := te.errors ++ q.errors ++ tl.errors;

  forwards to attributeDclInh($1, $2, a, botl, $5, te, $9);
}


concrete production collectionAttributeDclProd
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "production attribute " ++ a.name ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = addLocalColDcl(top.grammarName, a.location, fName, te.typerep, q.operation, emptyDefs());

  production attribute fName :: String;
  fName = top.signature.fullName ++ ":local:" ++ a.name;

  top.defs = emptyDefs();

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  q.operatorForType = te.typerep;
  top.errors := te.errors ++ q.errors;
  top.warnings := [];
 
  forwards to productionAttributeDcl($1, $2, a, $4, te, $8);
}

--- The use semantics ----------------------------------------------------------

-- ERROR ON ATTRIBUTE DEFS:
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
-- ERROR ON VALUE DEFS:
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

-- NON-ERRORS for PRODUCTIONS

abstract production baseCollectionValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " := " ++ e.pp ++ ";";

  e.downSubst = top.downSubst;
  -- the real type checking is done by the forward, but we must ensure things are tied up nicely
  -- otherwise we don't specialize ntOrDecs in OUR e
  forward.downSubst = unifyCheck(val.lookupValue.typerep, e.typerep, e.upSubst);
  
  forwards to localValueDef(val, $2, e);
}
abstract production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " <- " ++ e.pp ++ ";";

  e.downSubst = top.downSubst;
  -- the real type checking is done by the forward, but we must ensure things are tied up nicely
  -- otherwise we don't specialize ntOrDecs in OUR e
  forward.downSubst = unifyCheck(val.lookupValue.typerep, e.typerep, e.upSubst);
  
  forwards to localValueDef(val, $2, e);
}

-- NON-ERRORS for SYN ATTRS

abstract production synBaseColAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " := " ++ e.pp ++ ";";

  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(attr, dl.typerep);

  e.downSubst = top.downSubst;
  -- the real type checking is done by the forward, but we must ensure things are tied up nicely
  -- otherwise we don't specialize ntOrDecs in OUR e
  forward.downSubst = unifyCheck(occursCheck.typerep, e.typerep, e.upSubst);

  dl.isSynthesizedDefinition = false;
  
  forwards to synthesizedAttributeDef(dl, $2, attr, $4, e);
}
abstract production synAppendColAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " <- " ++ e.pp ++ ";";

  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(attr, dl.typerep);

  e.downSubst = top.downSubst;
  -- the real type checking is done by the forward, but we must ensure things are tied up nicely
  -- otherwise we don't specialize ntOrDecs in OUR e
  forward.downSubst = unifyCheck(occursCheck.typerep, e.typerep, e.upSubst);

  dl.isSynthesizedDefinition = false;
  
  forwards to synthesizedAttributeDef(dl, $2, attr, $4, e);
}

-- NON-ERRORS for INHERITED ATTRS

abstract production inhBaseColAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " := " ++ e.pp ++ ";";

  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(attr, dl.typerep);

  e.downSubst = top.downSubst;
  -- the real type checking is done by the forward, but we must ensure things are tied up nicely
  -- otherwise we don't specialize ntOrDecs in OUR e
  forward.downSubst = unifyCheck(occursCheck.typerep, e.typerep, e.upSubst);

  dl.isSynthesizedDefinition = false;
  
  forwards to inheritedAttributeDef(dl, $2, attr, $4, e);
}
abstract production inhAppendColAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " <- " ++ e.pp ++ ";";

  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(attr, dl.typerep);

  e.downSubst = top.downSubst;
  -- the real type checking is done by the forward, but we must ensure things are tied up nicely
  -- otherwise we don't specialize ntOrDecs in OUR e
  forward.downSubst = unifyCheck(occursCheck.typerep, e.typerep, e.upSubst);

  dl.isSynthesizedDefinition = false;
  
  forwards to inheritedAttributeDef(dl, $2, attr, $4, e);
}

-- The use syntax --------------------------------------------------------------

terminal Contains_t   '<-';
terminal BaseContains_t   ':=';

concrete production attrContainsAppend
top::ProductionStmt ::= dl::DefLHS '.' attr::QName '<-' e::Expr ';'
{
  top.errors <- attr.lookupAttribute.errors;

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

