grammar silver:modification:let_fix;

terminal Let_kwd 'let' lexer classes {KEYWORD};
terminal In_kwd 'in' lexer classes {KEYWORD};
terminal End_kwd 'end' lexer classes {KEYWORD};

nonterminal LetAssigns with pp, file, grammarName, defs, env, signature, errors, downSubst, upSubst, finalSubst, blockContext;
nonterminal AssignExpr with pp, file, grammarName, defs, env, signature, errors, downSubst, upSubst, finalSubst, blockContext;

concrete production nameLet
top::Name ::= 'let'
{
  forwards to nameIdLower(terminal(IdLower_t, "let", $1));
}

--TODO remove end keyword
concrete production letp
top::Expr ::= 'let' la::LetAssigns 'in' e::Expr 'end'
{
  top.errors <- la.errors;
    
  la.downSubst = top.downSubst;
  forward.downSubst = la.upSubst;
  
  forward.env = newScopeEnv(la.defs, top.env);

  forwards to e;
}

concrete production assigns
top::LetAssigns ::= ae::AssignExpr ',' list::LetAssigns
{
  top.pp = ae.pp ++ ", " ++ list.pp;
  top.defs = appendDefs(ae.defs, list.defs);
  top.errors := ae.errors ++ list.errors;
  
  ae.downSubst = top.downSubst;
  list.downSubst = ae.upSubst;
  top.upSubst = list.upSubst;
}

concrete production assignListSingle 
top::LetAssigns ::= ae::AssignExpr
{
  top.pp = ae.pp;
  top.defs = ae.defs;
  top.errors := ae.errors;
  
  ae.downSubst = top.downSubst;
  top.upSubst = ae.upSubst;
}

concrete production assignExpr
top::AssignExpr ::= id::Name '::' t::Type '=' e::Expr
{
  production attribute fName :: String;
  fName = top.signature.fullName ++ ":local:" ++ id.name;

  top.pp = id.name ++ " = " ++ e.pp;
  top.defs = addLexicalLocalDcl(top.grammarName, id.location, fName, t.typerep, emptyDefs());
  top.errors := t.errors ++ e.errors;
  
  top.errors <- if length(getValueDclAll(fName, top.env)) > 1
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
                then ntOrDecTypeExp(q.lookupValue.typerep.decoratedType, errorType(){-fresh tyvar-})
                else q.lookupValue.typerep;

  top.upSubst = top.downSubst;
  
  forwards to defaultExpr();
}

