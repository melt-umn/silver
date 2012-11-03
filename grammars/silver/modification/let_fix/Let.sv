grammar silver:modification:let_fix;

--- Concrete Syntax for lets
--------------------------------------------------------------------------------

terminal Let_kwd 'let' lexer classes {KEYWORD};
terminal In_kwd 'in' lexer classes {KEYWORD};
terminal End_kwd 'end' lexer classes {KEYWORD};

concrete production letp_c
top::Expr ::= 'let' la::LetAssigns 'in' e::Expr 'end'
{
  top.pp = "let " ++ la.pp ++ " in " ++ e.pp ++ " end";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to letp(top.location, la.letAssignExprs, e);
}

nonterminal LetAssigns with pp, letAssignExprs;

synthesized attribute letAssignExprs :: AssignExpr;

concrete production assignsListCons
top::LetAssigns ::= ae::AssignExpr ',' list::LetAssigns
{
  top.pp = ae.pp ++ ", " ++ list.pp;
  top.letAssignExprs = appendAssignExpr(ae, list.letAssignExprs);
}
concrete production assignListSingle 
top::LetAssigns ::= ae::AssignExpr
{
  top.pp = ae.pp;
  top.letAssignExprs = ae;
}

--------------------------------------------------------------------------------
--- Abstract Syntax for lets
--------------------------------------------------------------------------------

abstract production letp
top::Expr ::= l::Location  la::AssignExpr  e::Expr
{
  top.pp = "let " ++ la.pp ++ " in " ++ e.pp ++ " end";
  top.location = l;
  
  top.errors := la.errors ++ e.errors;
  
  top.typerep = e.typerep;
    
  la.downSubst = top.downSubst;
  e.downSubst = la.upSubst;
  top.upSubst = e.upSubst;
  
  -- Semantics for the moment is these are not mutually recursive,
  -- so la does NOT get new environment, only e. Thus, la.defs can depend on downSubst...
  e.env = newScopeEnv(la.defs, top.env);
}

nonterminal AssignExpr with config, file, grammarName, env, compiledGrammars, signature, 
                            pp, defs, errors, upSubst, 
                            downSubst, finalSubst, blockContext;

abstract production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.pp = a1.pp ++ ", " ++ a2.pp;
  top.defs = a1.defs ++ a2.defs;
  top.errors := a1.errors ++ a2.errors;
  
  a1.downSubst = top.downSubst;
  a2.downSubst = a1.upSubst;
  top.upSubst = a2.upSubst;
}

-- TODO: Well, okay, so this isn't really abstract syntax...
concrete production assignExpr
top::AssignExpr ::= id::Name '::' t::Type '=' e::Expr
{
  top.pp = id.pp ++ " :: " ++ t.pp ++ " = " ++ e.pp;
  
  -- Using finalTy here, so our defs requires we have downSubst...
  -- The reason we're putting the type in the environment AFTER inference is so that
  -- wonky things don't happen with the auto-dedecorate behavior in lexicalLocalReference
  top.defs = [lexicalLocalDef(top.grammarName, id.location, id.name, finalTy)];
  
  top.errors := t.errors ++ e.errors;
  
  -- TODO: At present, this isn't working properly, because the local scope is
  -- whatever scope encloses the real local scope... hrmm!
  top.errors <- if length(getValueDclInScope(id.name, top.env)) > 1
                then [err(id.location, "Value '" ++ id.name ++ "' is already bound.")]
                else [];

  production attribute finalTy :: TypeExp;
  finalTy = performSubstitution(t.typerep, errCheck1.upSubst);

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;

  errCheck1 = check(e.typerep, t.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(id.location, "Value " ++ id.name ++ " declared with type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
       else [];
}

abstract production lexicalLocalReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  top.errors := [];
  
  -- We're adding the "unusual" behavior that types like "Decorated Foo" in LETs
  -- will auto-undecorate if you want a Foo.
  
  -- (The usual behavior is a declared Foo, but value is Decorated Foo, can
  --  be used either way.)
  
  -- A note about possible unexpected behavior here: if q.lookupValue.typerep
  -- is itself a ntOrDecTypeExp, which is only possible if for generated 'let'
  -- expressions that use a type variable as their type, then this ntOrDecTypeExp
  -- we're generating here means we're NOT propagating the information about the
  -- "actual usage" backwards to expression.
  -- i.e.  "let x :: a = someLocal in wantsUndecorated(x) end"
  --       will mean "let x = decorated version of someLocal in wantsUndecorated(x.undecorate())"
  --       and not "let x = undecorated someLocal in wantsUndecorated(x)"
  
  top.typerep = if q.lookupValue.typerep.isDecorated
                then ntOrDecTypeExp(q.lookupValue.typerep.decoratedType, freshType())
                else q.lookupValue.typerep;

  top.upSubst = top.downSubst;
}

