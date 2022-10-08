grammar silver:compiler:modification:let_fix;

import silver:compiler:definition:flow:ast only ExprVertexInfo, FlowVertex;
import silver:util:treeset as ts;

--- Concrete Syntax for lets
--------------------------------------------------------------------------------

terminal Let_kwd 'let' lexer classes {KEYWORD,RESERVED};
terminal In_kwd 'in' lexer classes {KEYWORD,RESERVED};

concrete production letp_c
top::Expr ::= 'let' la::LetAssigns 'in' e::Expr 'end'
{
  top.unparse = "let " ++ la.unparse ++ " in " ++ e.unparse ++ " end";

  forwards to letp(la.letAssignExprs, e, location=top.location);
}

nonterminal LetAssigns with unparse, location, letAssignExprs;

synthesized attribute letAssignExprs :: AssignExpr;

concrete production assignsListCons
top::LetAssigns ::= ae::AssignExpr ',' list::LetAssigns
{
  top.unparse = ae.unparse ++ ", " ++ list.unparse;
  top.letAssignExprs = appendAssignExpr(ae, list.letAssignExprs, location=top.location);
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
  
  propagate errors;
  
  top.typerep = e.typerep;

  propagate downSubst, upSubst;
  
  -- Semantics for the moment is these are not mutually recursive,
  -- so la does NOT get new environment, only e. Thus, la.defs can depend on downSubst...
  e.env = newScopeEnv(la.defs, top.env);
}

monoid attribute boundNames::[String];

nonterminal AssignExpr with location, config, grammarName, env, compiledGrammars, 
                            unparse, defs, errors, boundNames, freeVars, upSubst, 
                            downSubst, finalSubst, frame, isRoot, originRules;

propagate errors, defs on AssignExpr;

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
  local semiTy :: Type = performSubstitution(t.typerep, top.upSubst);
  production fName :: String = toString(genInt()) ++ ":" ++ id.name;

  -- Using finalTy here, so our defs requires we have downSubst...
  -- references to this def want to know if its decorated, to enable the
  -- auto-undecorate feature, so that's why we bother substituting.
  -- (er, except that we're starting with t, which is a Type... must be because we fake these
  -- in e.g. the pattern matching code, so type variables might appear there?)
  top.defs <- [lexicalLocalDef(top.grammarName, id.location, fName, semiTy, e.flowVertexInfo, e.flowDeps)];
  
  -- TODO: At present, this isn't working properly, because the local scope is
  -- whatever scope encloses the real local scope... hrmm!
  top.errors <- 
    if length(getValueDclInScope(id.name, top.env)) > 1
    then [err(id.location, "Value '" ++ id.name ++ "' is already bound.")]
    else [];

  top.errors <- t.errorsKindStar;

  thread downSubst, upSubst on top, e, errCheck1, top;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  errCheck1 = check(e.typerep, t.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(id.location, "Value " ++ id.name ++ " declared with type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
    else [];
}

abstract production lexicalLocalReference
top::Expr ::= q::PartiallyDecorated QName  fi::ExprVertexInfo  fd::[FlowVertex]
{
  top.unparse = q.unparse;
  top.errors := [];
  top.freeVars := ts:fromList([q.name]);
  
  -- We're adding the "unusual" behavior that types like "Decorated Foo" in LETs
  -- will auto-undecorate if you want a Foo.
  
  -- (The usual behavior is a declared Foo, but value is Decorated Foo, can
  --  be used either way.)
  
  -- A note about possible unexpected behavior here: if q.lookupValue.typerep
  -- is itself a ntOrDecType, which is only possible if for generated 'let'
  -- expressions that use a type variable as their type, then this ntOrDecType
  -- we're generating here means we're NOT propagating the information about the
  -- "actual usage" backwards to expression.
  -- i.e.  "let x :: a = someLocal in wantsUndecorated(x) end"
  --       will mean "let x = decorated version of someLocal in wantsUndecorated(x.undecorate())"
  --       and not "let x = undecorated someLocal in wantsUndecorated(x)"

  top.typerep = 
    case q.lookupValue.typeScheme.monoType of
    | ntOrDecType(t, i, _) -> ntOrDecType(t, i, freshType())
    | decoratedType(t, i) -> ntOrDecType(t, i, freshType())
    | partiallyDecoratedType(t, i) -> ntOrDecType(t, i, freshType())
    | t -> t
    end;

  propagate downSubst, upSubst;
}

