grammar silver:modification:let_fix;

--- Concrete Syntax for lets
--------------------------------------------------------------------------------

terminal Let_kwd 'let' lexer classes {KEYWORD};
terminal In_kwd 'in' lexer classes {KEYWORD};
terminal End_kwd 'end' lexer classes {KEYWORD};

concrete production letp_c
top::Expr ::= 'let' la::LetAssigns 'in' e::Expr 'end'
{
  top.location = loc(top.file, $1.line, $1.column);

  forwards to letp(top.location, la.letAssignExprs, e);
}

nonterminal LetAssigns with letAssignExprs;

synthesized attribute letAssignExprs :: AssignExpr;

concrete production assignsListCons
top::LetAssigns ::= ae::AssignExpr ',' list::LetAssigns
{
  top.letAssignExprs = appendAssignExpr(ae, list.letAssignExprs);
}
concrete production assignListSingle 
top::LetAssigns ::= ae::AssignExpr
{
  top.letAssignExprs = ae;
}

--------------------------------------------------------------------------------
--- Abstract Syntax for lets
--------------------------------------------------------------------------------

abstract production letp
top::Expr ::= l:: Location  la::AssignExpr  e::Expr
{
  top.pp = "let " ++ la.pp ++ " in " ++ e.pp ++ " end";
  top.location = l;
  
  top.errors := la.errors ++ e.errors;
  
  top.typerep = e.typerep;
    
  la.downSubst = top.downSubst;
  e.downSubst = la.upSubst;
  top.upSubst = e.upSubst;
  
  e.env = newScopeEnv(la.defs, top.env);

  forwards to defaultExpr();
}

nonterminal AssignExpr with file, grammarName, env, signature, 
                            pp, defs, errors, upSubst, 
                            downSubst, finalSubst, blockContext;

abstract production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.pp = a1.pp ++ ", " ++ a2.pp;
  top.defs = appendDefs(a1.defs, a2.defs);
  top.errors := a1.errors ++ a2.errors;
  
  a1.downSubst = top.downSubst;
  a2.downSubst = a1.upSubst;
  top.upSubst = a2.upSubst;
}

-- TODO: Well, okay, so this isn't really abstract syntax...
concrete production assignExpr
top::AssignExpr ::= id::Name '::' t::Type '=' e::Expr
{
  production attribute fName :: String;
  fName = top.signature.fullName ++ ":local:" ++ id.name;

  top.pp = id.name ++ " :: " ++ t.pp ++ " = " ++ e.pp;
  top.defs = addLexicalLocalDcl(top.grammarName, id.location, fName, t.typerep, emptyDefs());
  top.errors := t.errors ++ e.errors;
  
  top.errors <- if length(getValueDclInScope(fName, top.env)) > 1
                then [err(id.location, "Value '" ++ fName ++ "' is already bound.")]
                else [];

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
  
  top.typerep = if q.lookupValue.typerep.isDecorated
                --then ntOrDecTypeExp(q.lookupValue.typerep.decoratedType, errorType(){-fresh tyvar-})
                then ntOrDecTypeExp(q.lookupValue.typerep.decoratedType, freshType(){-fresh tyvar-}) -- #HACK2012 Issue 4
                else q.lookupValue.typerep;

  top.upSubst = top.downSubst;
  
  forwards to defaultExpr();
}

