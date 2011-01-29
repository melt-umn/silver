grammar lib:langproc:tests ;

synthesized attribute transform_v3_Expr :: Expr occurs on Expr ;
synthesized attribute transform_v3_Root :: Root occurs on Root ;

autocopy attribute rwrule_v3_Expr  :: RewriteRule_v3<Decorated Expr>
  occurs on Expr, Root ;

-- A rewrite rule that rewrites variables x and a with a suffix.

abstract production addSuffix_v3
r::RewriteRule_v3<Decorated Expr> ::= suffix::String
{
 r.match_v3 = pieces.isJust ;
 r.rewrite_v3 = case pieces of
                  just(id) -> idRef( terminal(Id_t, id.lexeme ++ "_" ++ suffix,
                                              id.line, id.column) ) 
                | _ -> error ("accessing rewrite on failed match")
                end ;
  local attribute pieces::Maybe<Id_t> ;
  pieces = case r.tree_to_match_v3 of 
             idRef(idx) -> (if (idx.lexeme == "x" || idx.lexeme == "a")
--                               && ! null(r.tree_to_match_v3.rwrules_Expr)
                            then just(idx) else nothing())
           | _ -> nothing()
           end ;
}



aspect production idRef
e::Expr ::= id::Id_t
{
 e.transform_Expr = new ( applyRewriteRule_v3( e.rwrule_v3_Expr, 
                          decorate e with { rwrule_v3_Expr = e.rwrule_v3_Expr ; } ,
                          decorate e with { rwrule_v3_Expr = e.rwrule_v3_Expr ; } 
                         ) 
                        ) ;
}


-- not used above ...
function applyRewriteRule_v3_Expr
Decorated Expr ::= rr::RewriteRule_v3<Decorated Expr> tomatch::Decorated Expr nomatch::Decorated Expr
{ return if rr.match_v3 then rr.rewrite_v3 else nomatch ;
  rr.tree_to_match_v3 = tomatch ;
}

