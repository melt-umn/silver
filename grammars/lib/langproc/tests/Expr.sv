grammar lib:langproc:tests ;

nonterminal Expr with pp, rename_Expr, 
  transform_Expr, rwrule_Expr, rwrule_Root,     -- passing down single rules
  transforms_Expr, rwrules_Expr, rwrules_Root ; -- passing down a list of rules
nonterminal Root with pp, rename_Root, 
  transform_Root, rwrule_Expr, rwrule_Root ,
  transforms_Root, rwrules_Expr, rwrules_Root ;

synthesized attribute pp :: String ;
synthesized attribute rename_Expr :: Expr ;
synthesized attribute rename_Root :: Root ;

synthesized attribute transform_Expr :: Expr ;
synthesized attribute transform_Root :: Root ;

synthesized attribute transforms_Expr :: Expr ;
synthesized attribute transforms_Root :: Root ;

autocopy attribute rwrule_Root  :: RewriteRule_v2<Decorated Root  Root>  ;
autocopy attribute rwrule_Expr  :: RewriteRule_v2<Decorated Expr  Expr>  ;

autocopy attribute rwrules_Root  :: [ RewriteRule_v2<Decorated Root  Root> ] ;
autocopy attribute rwrules_Expr  :: [ RewriteRule_v2<Decorated Expr  Expr> ] ;

terminal Id_t /[a-zA-Z]([a-zA-Z0-9_])*/ ;

-- A rewrite rule that rewrites variables x and a with a suffix.
abstract production addSuffix_v2
r::RewriteRule_v2<Decorated Expr Expr> ::= suffix::String
{ r.match_v2 = pieces.isJust ;
  r.rewrite_v2 = case pieces of
                  just(id) -> idRef( terminal(Id_t, id.lexeme ++ "_" ++ suffix,
                                              id.line, id.column) ) 
                | _ -> error ("accessing rewrite on failed match")
                end ;
  local attribute pieces::Maybe<Id_t> ;
  pieces = case r.dtree_v2 of 
             idRef(idx) -> (if (idx.lexeme == "x" || idx.lexeme == "a")
                               --&& ! null(r.dtree_v2.rwrules_Expr)
                            then just(idx) else nothing())
           | _ -> nothing()
           end ;
}

synthesized attribute rename1_Expr :: Expr occurs on Expr ;
synthesized attribute rename2_Expr :: Expr occurs on Expr ;
synthesized attribute rename3_Expr :: Expr occurs on Expr ;
synthesized attribute rename4_Expr :: Expr occurs on Expr ;

abstract production idRef
e::Expr ::= id::Id_t
{ e.pp = id.lexeme ;
  e.rename_Expr = e.rename1_Expr ;

-- This compiles - but 'e' does not have its inh. attrs.
  e.rename1_Expr = applyRewriteRule_v2( addSuffix_v2("XX"), 
                    decorate e with {}, idRef(id)) ;

-- This compiles as well - it seems to convert 'e' to a decorated tree. 
  e.rename2_Expr = applyRewriteRule_v2( addSuffix_v2("XX"), e, idRef(id)) ;

-- This one fails to compile - but rw1 and addSuffix_v2("XX") have same type.
-- How is this different from the rename2 case above?
--  e.rename3_Expr = applyRewriteRule_v2( rw1, e, idRef(id)) ;

  local attribute rw1 :: RewriteRule_v2<Decorated Expr  Expr> ;
  rw1 = addSuffix_v2("XX") ;

-- This fails, for similar reasons?
--  e.rename4_Expr = applyRewriteRule_v2( e.rwrule_Expr, e, idRef(id)) ;

  local attribute rw2 :: RewriteRule_v2<Decorated Expr  Expr> ;
  rw2 = e.rwrule_Expr ;


-- No reason to read below here...


-- Not OK, but why?  The error reported by Silver complains about the 2nd parameter
-- but the use above is exactly the same in the second parameter.
--   e.transform_Expr = applyRewriteRule_v2( e.rwrule_Expr, e, idRef(id)) ;

-- This one is OK, but why do I have to decorate e?  shouldn't its type be 
-- Decorated Expr?
--   e.transform_Expr = applyRewriteRule_v2( e.rwrule_Expr, decorate e with {}, idRef(id)) ;

  e.transforms_Expr = idRef(id) ;
}

abstract production add
e::Expr ::= l::Expr r::Expr
{ e.pp = "(" ++ l.pp ++ " + " ++ r.pp ++ ")" ;
  local temp::String = foo(e) ++ bar(treeDec(e, e), e) ++ bar(treeDecExpr(e,e),e) ;

  e.rename_Expr = add(l.rename_Expr, r.rename_Expr) ;
  e.transform_Expr = add(l.transform_Expr, r.transform_Expr) ;
  e.transforms_Expr = add(l.transforms_Expr, r.transforms_Expr) ;

--  e.transform_Expr
--   = applyRewriteRule_v2 ( e.rwrule_Expr, l,
--                           add(l.transform_Expr, r.transform_Expr ) ) ;
--  local attribute e2 :: Decorated Expr ; 
--  e2 = e ;
}

abstract production mul
e::Expr ::= l::Expr r::Expr
{ e.pp = "(" ++ l.pp ++ " * " ++ r.pp ++ ")" ;
  e.rename_Expr = mul(l.rename_Expr, r.rename_Expr) ;
  e.transform_Expr = mul(l.transform_Expr, r.transform_Expr) ;
  e.transforms_Expr = mul(l.transforms_Expr, r.transforms_Expr) ;
}

abstract production root
r::Root ::= e::Expr
{ r.pp = e.pp ;
  r.rename_Root = root(e.rename_Expr) ;
  r.transform_Root = root(e.transform_Expr) ;
  r.transforms_Root = root(e.transforms_Expr) ;
}



function simpleRenameExpr
Expr ::= de::Decorated Expr
{ return if ! null(de.rwrules_Expr)
         then idRef(terminal(Id_t,"NEW"))
         else new(de) ;

  local attribute isId :: Boolean ;
  isId = case de of
           idRef(_) -> true 
         | _ -> false end ;
}
