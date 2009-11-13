grammar edu:umn:cs:melt:tutorial:expr:exts:extended_let ;

import edu:umn:cs:melt:tutorial:expr:host ;


-- extended let --
------------------

--------------------------------------------------
-- Concrete Syntax                              --
--------------------------------------------------

concrete production let_expr_ext_c
l::Expr_c ::= 'let' vd::VarDecl_c ',' vds::VarDecls_c 'in' body::Expr_c 'end'
{
 -- we ensure that two or more var decls are used for this production

 l.pp = "let " ++ vd.pp ++ ", " ++ vds.pp ++
        " in " ++ body.pp ++ " end" ;
 l.ast_Expr = let_expr_ext (var_decl_cons(vd.ast_VarDecl, vds.ast_VarDecls), 
                            body.ast_Expr);
}

nonterminal VarDecl_c with pp, ast_VarDecl ;

synthesized attribute ast_VarDecl :: VarDecl ;

concrete production var_decl_c
vd::VarDecl_c ::= n::Id_t ':' t::TypeExpr_c '=' v::Expr_c
{
 vd.pp = n.lexeme ++ ":" ++ t.pp ++ " = " ++ v.pp ;
 vd.ast_VarDecl = var_decl(n,t.ast_TypeExpr,v.ast_Expr) ;
}

nonterminal VarDecls_c with pp, ast_VarDecls ;

synthesized attribute ast_VarDecls :: VarDecls ;

concrete production var_decl_one_c
vds::VarDecls_c ::= vd::VarDecl_c
{
 vds.pp = vd.pp ;
 vds.ast_VarDecls = var_decl_one(vd.ast_VarDecl);
}

concrete production var_decl_cons_c
vds::VarDecls_c ::= vd::VarDecl_c ',' vds_tail::VarDecls_c
{
 vds.pp = vd.pp ++ ", " ++ vds_tail.pp ;
 vds.ast_VarDecls = var_decl_cons(vd.ast_VarDecl, vds_tail.ast_VarDecls);
}


--------------------------------------------------
-- Abstract Syntax                             --
--------------------------------------------------
-- "let x = 1 , y = 2, z = 3 in x + y + z end"
-- forwards to
-- "let x = 1 in let y = 2 in let z = 3 in x + y + z end end end"

abstract production let_expr_ext
l::Expr ::= vds::VarDecls  body::Expr
{
 l.pp = "let " ++ vds.pp ++ " in " ++ body.pp ++ " end" ;
 forwards to vds.var_decl_let ;

 vds.var_decl_body = body ;
}

synthesized attribute var_decl_let :: Expr ;
inherited attribute var_decl_body :: Expr ;

nonterminal VarDecl with pp, var_decl_let, var_decl_body ;

abstract production var_decl
vd::VarDecl ::= n::Id_t t::TypeExpr v::Expr
{
 vd.pp = n.lexeme ++ ":" ++ t.pp ++  " = " ++ v.pp ;
 vd.var_decl_let = let_expr_typed (n, t, v, vd.var_decl_body ) ;
}

nonterminal VarDecls with pp, var_decl_let, var_decl_body ;

abstract production var_decl_one
vds::VarDecls ::= vd::VarDecl
{
 vds.pp = vd.pp ;
 vds.var_decl_let = vd.var_decl_let ;
 vd.var_decl_body = vds.var_decl_body ;
}

abstract production var_decl_cons
vds::VarDecls ::= vd::VarDecl  vds_tail::VarDecls
{
 vds.pp = vd.pp ++ ", " ++ vds_tail.pp ;
 vd.var_decl_body = vds_tail.var_decl_let ;
 vds_tail.var_decl_body = vds.var_decl_body ;
 vds.var_decl_let = vd.var_decl_let ;
}




