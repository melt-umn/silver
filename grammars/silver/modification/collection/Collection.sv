grammar silver:modification:collection;

import silver:definition:type:syntax;
import silver:extension:list;
import silver:util;

--import silver:analysis:typechecking:core;

nonterminal NameOrBOperator with config, location, grammarName, errors, env, pp, operation, operatorForType;
nonterminal Operation with unparse;

synthesized attribute operation :: Operation;
inherited attribute operatorForType :: TypeExp;

concrete production nameOperator
top::NameOrBOperator ::= q::QName
{
  top.pp = q.pp;

  top.operation = case q.lookupValue.dcl of
                  | funDcl(_,_,_) -> functionOperation(q.lookupValue.fullName)
                  | prodDcl(_,_,_) -> productionOperation(q.lookupValue.fullName)
                  | _ -> error("INTERNAL ERROR: operation attribute demanded for non-function or production.")
                  end;

  top.errors := q.lookupValue.errors;
  
  local checkOperationType :: TypeCheck =
    check(freshenCompletely(q.lookupValue.typerep),
      functionTypeExp(top.operatorForType, [top.operatorForType, top.operatorForType], []));
  checkOperationType.downSubst = emptySubst();
  checkOperationType.finalSubst = checkOperationType.upSubst;
  
  local operationErrors :: [Message] =
    if !checkOperationType.typeerror then []
    else [err(top.location, q.pp ++ " must be of type " ++ checkOperationType.rightpp ++
            " instead it is of type " ++ checkOperationType.leftpp)];
  
  top.errors <- if !null(q.lookupValue.errors) then [] else
    case q.lookupValue.dcl of
    | funDcl(_,_,_) -> operationErrors
    | prodDcl(_,_,_) -> operationErrors
    | _ -> [err(top.location, q.pp ++ " is not a valid operator for collections.")]
    end;
}

concrete production plusplusOperator
top::NameOrBOperator ::= '++'
{
  top.pp = "++";

  top.operation = case top.operatorForType of
                  | stringTypeExp() -> plusPlusOperationString()
                  | listTypeExp(_) -> plusPlusOperationList()
                  | _ -> error("INTERNAL ERROR: operation attribute demanded for ++ that isn't string or list.")
                  end;
  top.errors := case top.operatorForType of
                | stringTypeExp() -> []
                | listTypeExp(_) -> []
                | _ -> [err(top.location, "++ operator will only work for collections of type list or String")]
                end;
}

concrete production borOperator
top::NameOrBOperator ::= '||'
{
  top.pp = "||";

  top.operation = borOperation();
  top.errors := case top.operatorForType of
                | boolTypeExp() -> []
                | _ -> [err(top.location, "|| operator will only work for collections of type Boolean")]
                end;
}
concrete production bandOperator
top::NameOrBOperator ::= '&&'
{
  top.pp = "&&";

  top.operation = bandOperation();
  top.errors := case top.operatorForType of
                | boolTypeExp() -> []
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
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeList '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "synthesized attribute " ++ a.name ++ tl.pp ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = [synColDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, q.operation)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors := te.errors ++ q.errors ++ tl.errors ++ tl.errorsTyVars;

  top.errors <-
        if length(getAttrDclAll(fName, top.env)) > 1
        then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  q.operatorForType = te.typerep;

  forwards to attributeDclSyn($1, $2, a, tl, $5, te, $9, location=top.location);
}

concrete production collectionAttributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeList '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "inherited attribute " ++ a.name ++ tl.pp ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = [inhColDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, q.operation)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors := te.errors ++ q.errors ++ tl.errors ++ tl.errorsTyVars;

  top.errors <-
        if length(getAttrDclAll(fName, top.env)) > 1
        then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  q.operatorForType = te.typerep;

  forwards to attributeDclInh($1, $2, a, tl, $5, te, $9, location=top.location);
}


concrete production collectionAttributeDclProd
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "production attribute " ++ a.name ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;

  top.productionAttributes = [localColDef(top.grammarName, a.location, fName, te.typerep, q.operation)];

  production attribute fName :: String;
  fName = top.signature.fullName ++ ":local:" ++ a.name;

  top.defs = [];

  top.errors := te.errors ++ q.errors;

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1
        then [err(a.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  q.operatorForType = te.typerep;
 
  forwards to productionAttributeDcl($1, $2, a, $4, te, $8, location=top.location);
}

--- The use semantics ----------------------------------------------------------

-- ERROR ON ATTRIBUTE DEFS:
abstract production errorCollectionDefDispatcher
top::ProductionStmt ::= dl::Decorated DefLHS  q::Decorated QNameAttrOccur  e::Expr
{
  top.errors <- [err(top.location, "The ':=' and '<-' operators can only be used for collections. " ++ q.pp ++ " is not a collection.")];

  forwards to errorAttributeDef(dl, q, e, location=top.location);
}
abstract production errorColNormalAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  q::Decorated QNameAttrOccur  e::Expr
{
  top.errors <- [err(top.location, q.pp ++ " is a collection attribute, and you must use ':=' or '<-', not '='.")];

  forwards to errorAttributeDef(dl, q, e, location=top.location);
}
-- ERROR ON VALUE DEFS:
abstract production errorCollectionValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.errors <- [err(top.location, "The ':=' and '<-' operators can only be used for collections. " ++ val.pp ++ " is not a collection.")];
  
  -- TODO: this production also produces an error message, so we'll produce two errors for one flaw.
  -- We don't want to use := for the errors, because we'd miss any errors in e, and we don't want to repeat
  -- it because that will produce duplicate trees.
  forwards to errorValueDef(val, e, location=top.location);
}
abstract production errorColNormalValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.errors <- [err(top.location, val.pp ++ " is a collection attribute, and you must use ':=' or '<-', not '='.")];
  
  -- TODO: same problem
  forwards to errorValueDef(val, e, location=top.location);
}

-- NON-ERRORS for PRODUCTIONS

abstract production baseCollectionValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.pp = "\t" ++ val.pp ++ " := " ++ e.pp ++ ";";

  e.downSubst = top.downSubst;
  -- the real type checking is done by the forward, but we must ensure things are tied up nicely
  -- otherwise we don't specialize ntOrDecs in OUR e
  forward.downSubst = unifyCheck(val.lookupValue.typerep, e.typerep, e.upSubst);
  
  forwards to localValueDef(val, e, location=top.location);
}
abstract production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.pp = "\t" ++ val.pp ++ " <- " ++ e.pp ++ ";";

  e.downSubst = top.downSubst;
  -- the real type checking is done by the forward, but we must ensure things are tied up nicely
  -- otherwise we don't specialize ntOrDecs in OUR e
  forward.downSubst = unifyCheck(val.lookupValue.typerep, e.typerep, e.upSubst);
  
  forwards to localValueDef(val, e, location=top.location);
}

-- NON-ERRORS for SYN ATTRS

abstract production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " := " ++ e.pp ++ ";";

  top.errors := e.errors;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst; 

  errCheck1 = check(attr.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
    else [];
}
abstract production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " <- " ++ e.pp ++ ";";

  top.errors := e.errors;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst; 

  errCheck1 = check(attr.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
    else [];
}

-- NON-ERRORS for INHERITED ATTRS

abstract production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " := " ++ e.pp ++ ";";

  top.errors := e.errors;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst; 

  errCheck1 = check(attr.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
    else [];
}
abstract production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " <- " ++ e.pp ++ ";";

  top.errors := e.errors;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst; 

  errCheck1 = check(attr.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
    else [];
}

-- The use syntax --------------------------------------------------------------

terminal Contains_t      '<-' lexer classes {SPECOP};
terminal BaseContains_t  ':=' lexer classes {SPECOP};

concrete production attrContainsAppend
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '<-' e::Expr ';'
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " <- " ++ e.pp ++ ";";

  top.errors := dl.errors ++ attr.errors ++ forward.errors;

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes = [];
  top.defs = [];
  
  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to if !null(attr.errors)
              then errorAttributeDef(dl, attr, e, location=top.location)
              else attr.attrDcl.attrAppendDefDispatcher(dl, attr, e, top.location);
}

concrete production attrContainsBase
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur ':=' e::Expr ';'
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " := " ++ e.pp ++ ";";

  top.errors := dl.errors ++ attr.errors ++ forward.errors;

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes = [];
  top.defs = [];
  
  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to if !null(attr.errors)
              then errorAttributeDef(dl, attr, e, location=top.location)
              else attr.attrDcl.attrBaseDefDispatcher(dl, attr, e, top.location);
}

concrete production valContainsAppend
top::ProductionStmt ::= val::QName '<-' e::Expr ';'
{
  top.pp = val.pp ++ " <- " ++ e.pp ++ ";";
  
  top.errors <- val.lookupValue.errors;

  top.productionAttributes = [];
  top.defs = [];
  
  forwards to if null(val.lookupValue.dcls)
              then errorValueDef(val, e, location=top.location)
              else val.lookupValue.dcl.appendDefDispatcher(val, e, top.location);
}

concrete production valContainsBase
top::ProductionStmt ::= val::QName ':=' e::Expr ';'
{
  top.pp = val.pp ++ " := " ++ e.pp ++ ";";

  top.errors <- val.lookupValue.errors;

  top.productionAttributes = [];
  top.defs = [];
  
  forwards to if null(val.lookupValue.dcls)
              then errorValueDef(val, e, location=top.location)
              else val.lookupValue.dcl.baseDefDispatcher(val, e, top.location);
}

