grammar silver:compiler:modification:collection;

import silver:compiler:definition:type:syntax;
import silver:compiler:modification:list;

--import silver:compiler:analysis:typechecking:core;
import silver:compiler:driver:util;
import silver:compiler:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph;
import silver:compiler:translation:java:core;

nonterminal NameOrBOperator with config, location, grammarName, compiledGrammars, flowEnv, productionFlowGraphs, errors, env, unparse, operation, operatorForType;
propagate config, grammarName, compiledGrammars, flowEnv, productionFlowGraphs, env on NameOrBOperator;

nonterminal Operation with compareTo, isEqual;
propagate compareTo, isEqual on Operation excluding functionOperation;

synthesized attribute operation :: Operation;
inherited attribute operatorForType :: Type;

concrete production exprOperator
top::NameOrBOperator ::= e::Expr
{
  top.unparse = e.unparse;

  top.operation = functionOperation(e, e.translation, false);

  top.errors := e.errors;
  
  local checkOperationType :: TypeCheck =
    check(e.typerep, appTypes(functionType(2, []), [top.operatorForType, top.operatorForType, top.operatorForType]));
  
  e.downSubst = emptySubst();
  checkOperationType.downSubst = e.upSubst;
  checkOperationType.finalSubst = checkOperationType.upSubst;
  e.finalSubst = checkOperationType.finalSubst;
  
  top.errors <-
    if !checkOperationType.typeerror then []
    else [err(top.location, e.unparse ++ " must be of type " ++ checkOperationType.rightpp ++
            " instead it is of type " ++ checkOperationType.leftpp)];
  
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(e.flowDefs, top.env, myProds, myFlow);

  e.frame = bogusContext(myFlowGraph, sourceGrammar=top.grammarName);
  e.originRules = [];
  e.isRoot = false;
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = false;
}

concrete production plusplusOperator
top::NameOrBOperator ::= '++'
{
  top.unparse = "++";

  top.operation = case top.operatorForType of
                  | stringType() -> plusPlusOperationString()
                  | listType(_) -> plusPlusOperationList()
                  | _ -> error("INTERNAL ERROR: operation attribute demanded for ++ that isn't string or list.")
                  end;
  top.errors := case top.operatorForType of
                | stringType() -> []
                | listType(_) -> []
                | _ -> [err(top.location, "++ operator will only work for collections of type list or String")]
                end;
}

concrete production borOperator
top::NameOrBOperator ::= '||'
{
  top.unparse = "||";

  top.operation = borOperation();
  top.errors := case top.operatorForType of
                | boolType() -> []
                | _ -> [err(top.location, "|| operator will only work for collections of type Boolean")]
                end;
}
concrete production bandOperator
top::NameOrBOperator ::= '&&'
{
  top.unparse = "&&";

  top.operation = bandOperation();
  top.errors := case top.operatorForType of
                | boolType() -> []
                | _ -> [err(top.location, "&& operator will only work for collections of type Boolean")]
                end;
}

concrete production addOperator
top::NameOrBOperator ::= '+'
{
  top.unparse = "+";

  top.operation = addOperation();
  top.errors := case top.operatorForType of
                | intType() -> []
                | _ -> [err(top.location, "+ operator will only work for collections of type Integer")]
                end;
}

concrete production mulOperator
top::NameOrBOperator ::= '*'
{
  top.unparse = "*";

  top.operation = addOperation();
  top.errors := case top.operatorForType of
                | intType() -> []
                | _ -> [err(top.location, "* operator will only work for collections of type Integer")]
                end;
}

-- This would be much nicer if we could pass the Decorated Expr here,
-- but this nonterminal must be serializable as part of the environment.
abstract production functionOperation
top::Operation ::= e::Expr eTrans::String trackConstruction::Boolean
{ top.isEqual =
    case top.compareTo of
    | functionOperation(_, et, tc) -> et == eTrans && tc == trackConstruction
    | _ -> false
    end;
}
abstract production plusPlusOperationString
top::Operation ::= 
{}
abstract production plusPlusOperationList
top::Operation ::= 
{}
abstract production borOperation
top::Operation ::= 
{}
abstract production bandOperation
top::Operation ::= 
{}
abstract production addOperation
top::Operation ::= 
{}
abstract production mulOperation
top::Operation ::= 
{}

--- Declarations ---------------------------------------------------------------
concrete production collectionAttributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{
  top.unparse = "synthesized attribute " ++ a.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " with " ++ q.unparse ++ " ;" ;
  propagate config, grammarName, compiledGrammars, grammarDependencies, errors;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;
  
  q.operatorForType = te.typerep;
  q.env = top.env;
  
  top.defs := [synColDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, q.operation)];
  
  top.errors <- tl.errorsTyVars;
  top.errors <- te.errorsKindStar;

  top.errors <-
        if length(getAttrDclAll(fName, top.env)) > 1
        then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];
}

concrete production collectionAttributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{
  top.unparse = "inherited attribute " ++ a.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " with " ++ q.unparse ++ " ;" ;
  propagate config, grammarName, compiledGrammars, grammarDependencies, errors;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;
  
  q.operatorForType = te.typerep;
  q.env = top.env;

  top.defs := [inhColDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, q.operation)];
  
  top.errors <- tl.errorsTyVars;
  top.errors <- te.errorsKindStar;

  top.errors <-
        if length(getAttrDclAll(fName, top.env)) > 1
        then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];
}


concrete production collectionAttributeDclProd
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{
  top.unparse = "production attribute " ++ a.name ++ " :: " ++ te.unparse ++ " with " ++ q.unparse ++ " ;" ;
  propagate config, grammarName, compiledGrammars, env, flowEnv;

  top.productionAttributes := [localColDef(top.grammarName, a.location, fName, te.typerep, q.operation)];

  production attribute fName :: String;
  fName = top.frame.fullName ++ ":local:" ++ top.grammarName ++ ":" ++ a.name;

  top.defs := [];

  q.operatorForType = te.typerep;
  top.errors <- q.errors;
 
  forwards to productionAttributeDcl($1, $2, a, $4, te, $8, location=top.location);
}

--- The use semantics ----------------------------------------------------------

-- ERROR ON VALUE DEFS:
abstract production errorCollectionValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  undecorates to valContainsBase(val, ':=', e, ';', location=top.location);
  top.errors <- [err(top.location, "The ':=' and '<-' operators can only be used for collections. " ++ val.name ++ " is not a collection.")];
  
  -- TODO: this production also produces an error message, so we'll produce two errors for one flaw.
  -- We don't want to use := for the errors, because we'd miss any errors in e, and we don't want to repeat
  -- it because that will produce duplicate trees.
  forwards to errorValueDef(val, e, location=top.location);
}
abstract production errorColNormalValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  undecorates to valueEq(val, '=', e, ';', location=top.location);
  top.errors <- [err(top.location, val.name ++ " is a collection attribute, and you must use ':=' or '<-', not '='.")];
  
  -- TODO: same problem
  forwards to errorValueDef(val, e, location=top.location);
}

-- NON-ERRORS for PRODUCTIONS

abstract production baseCollectionValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  undecorates to valContainsBase(val, ':=', e, ';', location=top.location);
  top.unparse = "\t" ++ val.unparse ++ " := " ++ e.unparse ++ ";";
  propagate config, grammarName, compiledGrammars, frame, env, finalSubst, originRules, flowEnv;

  e.isRoot = false;

  e.downSubst = top.downSubst;
  -- the real type checking is done by the forward, but we must ensure things are tied up nicely
  -- otherwise we don't specialize ntOrDecs in OUR e
  forward.downSubst = unifyCheck(val.lookupValue.typeScheme.monoType, e.typerep, e.upSubst);
  
  -- TODO: We actually don't want reference site flow projections in e,
  -- since we don't actually know the entire tree in which it is decorated.
  -- This probably shouldn't be a forwarding production...
  forwards to localValueDef(val, e, location=top.location);
}
abstract production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  undecorates to valContainsAppend(val, '<-', e, ';', location=top.location);
  top.unparse = "\t" ++ val.unparse ++ " <- " ++ e.unparse ++ ";";
  propagate config, grammarName, compiledGrammars, frame, env, finalSubst, originRules, flowEnv;

  e.isRoot = false;

  e.downSubst = top.downSubst;
  -- the real type checking is done by the forward, but we must ensure things are tied up nicely
  -- otherwise we don't specialize ntOrDecs in OUR e
  forward.downSubst = unifyCheck(val.lookupValue.typeScheme.monoType, e.typerep, e.upSubst);
  
  forwards to localValueDef(val, e, location=top.location);
}

-- NON-ERRORS for SYN ATTRS

abstract production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  undecorates to attrContainsBase(dl, '.', attr, ':=', e, ';', location=top.location);
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " := " ++ e.unparse ++ ";";
  propagate config, grammarName, compiledGrammars, frame, env, finalSubst, originRules;

  top.errors := e.errors;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, e, errCheck1, top;

  e.isRoot = false;

  errCheck1 = check(attr.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
    else [];
}
abstract production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  undecorates to attrContainsAppend(dl, '.', attr, '<-', e, ';', location=top.location);
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " <- " ++ e.unparse ++ ";";
  propagate config, grammarName, compiledGrammars, frame, env, finalSubst, originRules;

  top.errors := e.errors;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, e, errCheck1, top;

  e.isRoot = false;

  errCheck1 = check(attr.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
    else [];
}

-- NON-ERRORS for INHERITED ATTRS

abstract production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  undecorates to attrContainsBase(dl, '.', attr, ':=', e, ';', location=top.location);
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " := " ++ e.unparse ++ ";";
  propagate config, grammarName, compiledGrammars, frame, env, finalSubst, originRules;

  top.errors := e.errors;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, e, errCheck1, top;

  e.isRoot = false;

  errCheck1 = check(attr.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
    else [];
}
abstract production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  undecorates to attrContainsAppend(dl, '.', attr, '<-', e, ';', location=top.location);
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " <- " ++ e.unparse ++ ";";
  propagate config, grammarName, compiledGrammars, frame, env, finalSubst, originRules;

  top.errors := e.errors;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, e, errCheck1, top;

  e.isRoot = false;

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
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " <- " ++ e.unparse ++ ";";
  propagate config, grammarName, compiledGrammars, frame, env, finalSubst, originRules, flowEnv;

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes := [];
  top.defs := [];
  
  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to
    (if !dl.found || !attr.found
     then errorAttributeDef(dl.errors ++ attr.errors, _, _, _, location=_)
     else attr.attrDcl.attrAppendDefDispatcher)(dl, attr, e, top.location);
}

concrete production attrContainsBase
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur ':=' e::Expr ';'
{
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " := " ++ e.unparse ++ ";";
  propagate config, grammarName, compiledGrammars, frame, env, finalSubst, originRules, flowEnv;

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes := [];
  top.defs := [];
  
  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to
    (if !dl.found || !attr.found
     then errorAttributeDef(dl.errors ++ attr.errors, _, _, _, location=_)
     else attr.attrDcl.attrBaseDefDispatcher)(dl, attr, e, top.location);
}

concrete production valContainsAppend
top::ProductionStmt ::= val::QName '<-' e::Expr ';'
{
  top.unparse = val.unparse ++ " <- " ++ e.unparse ++ ";";
  propagate compiledGrammars, frame, env, finalSubst, originRules;
  
  top.errors <- val.lookupValue.errors;

  top.productionAttributes := [];
  top.defs := [];
  
  forwards to
    (if null(val.lookupValue.dcls)
     then errorValueDef(_, _, location=_)
     else val.lookupValue.dcl.appendDefDispatcher)(val, e, top.location);
}

concrete production valContainsBase
top::ProductionStmt ::= val::QName ':=' e::Expr ';'
{
  top.unparse = val.unparse ++ " := " ++ e.unparse ++ ";";
  propagate compiledGrammars, frame, env, finalSubst, originRules;

  top.errors <- val.lookupValue.errors;

  top.productionAttributes := [];
  top.defs := [];
  
  forwards to
    (if null(val.lookupValue.dcls)
     then errorValueDef(_, _, location=_)
     else val.lookupValue.dcl.baseDefDispatcher)(val, e, top.location);
}

