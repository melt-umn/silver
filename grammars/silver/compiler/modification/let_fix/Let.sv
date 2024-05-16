grammar silver:compiler:modification:let_fix;

import silver:compiler:definition:flow:ast only VertexType, FlowVertex;
import silver:util:treeset as ts;

--- Concrete Syntax for lets
--------------------------------------------------------------------------------

terminal Let_kwd 'let' lexer classes {KEYWORD,RESERVED};
terminal In_kwd 'in' lexer classes {KEYWORD,RESERVED};

concrete production letp_c
top::Expr ::= 'let' la::LetAssigns 'in' e::Expr 'end'
{
  top.unparse = "let " ++ la.unparse ++ " in " ++ e.unparse ++ " end";

  forwards to letp(la.letAssignExprs, e);
}

tracked nonterminal LetAssigns with unparse, letAssignExprs;

synthesized attribute letAssignExprs :: AssignExpr;

concrete production assignsListCons
top::LetAssigns ::= ae::AssignExpr ',' list::LetAssigns
{
  top.unparse = ae.unparse ++ ", " ++ list.unparse;
  top.letAssignExprs = appendAssignExpr(ae, list.letAssignExprs);
}
concrete production assignListSingle 
top::LetAssigns ::= ae::AssignExpr
{
  top.unparse = ae.unparse;
  top.letAssignExprs = ae;
}

--------------------------------------------------------------------------------
--- Abstract Syntax for lets
--------------------------------------------------------------------------------

abstract production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  top.unparse = "let " ++ la.unparse ++ " in " ++ e.unparse ++ " end";
  top.freeVars := ts:removeAll(la.boundNames, e.freeVars);
  
  propagate config, grammarName, compiledGrammars, frame, errors, originRules;
  e.isRoot = false;
  
  top.typerep = e.typerep;

  propagate downSubst, upSubst, finalSubst;
  
  -- Semantics for the moment is these are not mutually recursive,
  -- so la does NOT get new environment, only e. Thus, la.defs can depend on downSubst...
  la.env = top.env;
  e.env = newScopeEnv(la.defs, top.env);
}

monoid attribute boundNames::[String];

tracked nonterminal AssignExpr with config, grammarName, env, compiledGrammars, 
                            unparse, defs, errors, boundNames, freeVars, upSubst, 
                            downSubst, finalSubst, frame, originRules;

flowtype AssignExpr =
  decorate {grammarName, env, flowEnv, downSubst, finalSubst, frame, compiledGrammars, config, bodyDecSites},
  upSubst {decorate}, defs {decorate};

propagate config, grammarName, compiledGrammars, frame, env, errors, defs, finalSubst, originRules on AssignExpr;

abstract production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.unparse = a1.unparse ++ ", " ++ a2.unparse;
  top.freeVars := a1.freeVars ++ ts:removeAll(a1.boundNames, a2.freeVars);

  propagate boundNames, downSubst, upSubst;
}

-- TODO: Well, okay, so this isn't really abstract syntax...
concrete production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  top.unparse = id.unparse ++ " :: " ++ t.unparse ++ " = " ++ e.unparse;
  propagate freeVars;
  top.boundNames := [id.name];
  
  -- Right now some things (pattern matching) abuse us by giving type variables
  -- for `t`. So we want to do a little inference before we stuff this into
  -- our DclInfo in `defs` because we expect variables in the env to have
  -- explicit types. We can't use `finalSubst` here because that requires
  -- having completed type inference which requires `defs` which we're defining.
  nondecorated local semiTy::Type = performSubstitution(t.typerep, top.upSubst);
  production fName :: String = toString(genInt()) ++ ":" ++ id.name;

  -- Using finalTy here, so our defs requires we have downSubst...
  -- references to this def want to know if its decorated, to enable the
  -- auto-undecorate feature, so that's why we bother substituting.
  -- (er, except that we're starting with t, which is a Type... must be because we fake these
  -- in e.g. the pattern matching code, so type variables might appear there?)
  top.defs <- [lexicalLocalDef(top.grammarName, id.nameLoc, fName, semiTy, e.flowVertexInfo, e.flowDeps)];
  
  -- TODO: At present, this isn't working properly, because the local scope is
  -- whatever scope encloses the real local scope... hrmm!
  top.errors <- 
    if length(getValueDclInScope(id.name, top.env)) > 1
    then [errFromOrigin(id, "Value '" ++ id.name ++ "' is already bound.")]
    else [];

  top.errors <- t.errorsKindStar;

  thread downSubst, upSubst on top, e, errCheck1, top;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  errCheck1 = check(e.typerep, t.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [errFromOrigin(id, "Value " ++ id.name ++ " declared with type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
    else [];

  e.isRoot = false;
}

abstract production lexicalLocalReference implements Reference
top::Expr ::= @q::QName  fi::Maybe<VertexType>  fd::[FlowVertex]
{
  top.unparse = q.unparse;
  top.errors := [];
  top.freeVars := ts:fromList([q.name]);

  top.typerep = q.lookupValue.typeScheme.monoType;

  propagate downSubst, upSubst;
}

