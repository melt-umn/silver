grammar lambda ;

import silver:langutil;
import silver:langutil:pp;

synthesized attribute ast_Root::Root;
nonterminal Root_c with pp,ast_Root;

synthesized attribute ast_Type::Type;
nonterminal TypeList_c with pp,ast_Type;
nonterminal TypeItem_c with pp,ast_Type;

synthesized attribute ast_Expr::Expr;
nonterminal Expr_c with pp,ast_Expr;

synthesized attribute ast_Term::Term;
nonterminal Term_c with pp,ast_Term;

synthesized attribute ast_Factor::Factor;
nonterminal Factor_c with pp,ast_Factor;

synthesized attribute ast_Expr_funct::Expr_funct;
nonterminal Expr_funct_c with pp,ast_Expr_funct;

synthesized attribute ast_Expr_arith::Expr_arith;
nonterminal Expr_arith_c with pp,ast_Expr_arith;

concrete productions r::Root_c
 | e::Expr_c { r.pp = e.pp;
               r.ast_Root = root(e.ast_Expr); }

concrete productions e::Expr_c
 | e1::Expr_funct_c { e.pp = e1.pp;
                      e.ast_Expr = expr_expr_f(e1.ast_Expr_funct); }
 | 'let' ic::Id_t ':' ty::TypeList_c '=' e1::Expr_c 'in' e2::Expr_c
                    { e.pp = concat([text("let"), text(ic.lexeme), text(":"), ty.pp, text("="), e1.pp, text("in"), e2.pp]); }
 | 'lambda' i::Id_t ':' ty::TypeList_c '.' e1::Expr_c
                    { e.pp = concat([text("lambda"), text(i.lexeme), text(":"), ty.pp, text("."), e1.pp]); }

concrete productions e::Expr_funct_c
 | e1::Expr_funct_c e2::Expr_arith_c { e.pp = cat(e1.pp, e2.pp); } --TODO
 | e1::Expr_arith_c                  { e.pp = e1.pp;
                                       e.ast_Expr_funct = methodpassing_ex(e1.ast_Expr_arith); }

concrete productions e::Expr_arith_c
 | e1::Expr_arith_c '+' t::Term_c { e.pp = concat([e1.pp, text("+"), t.pp]); }
 | e1::Expr_arith_c '-' t::Term_c { e.pp = concat([e1.pp, text("-"), t.pp]); }
 | t::Term_c                      { e.pp = t.pp;
                                    e.ast_Expr_arith = expr_term(t.ast_Term); }

concrete productions t::Term_c
 | t1::Term_c '*' f::Factor_c { t.pp = concat([t1.pp, text("*"), f.pp]); }
 | t1::Term_c '/' f::Factor_c { t.pp = concat([t1.pp, text("/"), f.pp]); }
 | f::Factor_c                { t.pp = f.pp;
                                t.ast_Term = term_factor(f.ast_Factor); }

concrete productions e::Factor_c
 | '(' inner::Expr_c ')' { e.pp = parens(inner.pp);
                           e.ast_Factor = factor_parens(inner.ast_Expr); }
 | i::Id_t { e.pp = text(i.lexeme);
             e.ast_Factor = factor_id(i.lexeme); }
 | i::IntLit_t { e.pp = text(i.lexeme);
                 e.ast_Factor = factor_int(i.lexeme); }

-- Types
concrete productions tl::TypeList_c
 | ti::TypeItem_c '->' tl1::TypeList_c { tl.pp = concat([ti.pp, text("->"), tl1.pp]);
                                         tl.ast_Type = arrow(ti.ast_Type,tl1.ast_Type); }
 | ti::TypeItem_c { tl.pp = ti.pp;
                    tl.ast_Type = ti.ast_Type; }

concrete productions ti::TypeItem_c
 | 'int' { ti.pp = text("int");
           ti.ast_Type = int(); }
 | '(' tl::TypeList_c ')' { ti.pp = parens(tl.pp);
                            ti.ast_Type = tl.ast_Type; }
{-
concrete productions ty::Type_c
 | t1::Type_c '->' t2::Type_c { ty.pp = concat([t1.pp, text("->"), t2.pp]);
                                ty.ast_Type = arrow(t1,t2); }
 | 'int' { ty.pp = text("int");
           ty.ast_Type = int(); }

 | 'int' '->' t::Type_c { ty.pp = concat([text("int"), text("->"), t.pp]); }
 | i::Integer_t         { ty.pp = text("int"); }
 | '(' t::Type_c ')' '->' 'int' { ty.pp = cat(parens(t.pp), text("->int")); }
 | '(' t::Type_c ')'            { ty.pp = parens(t.pp); }
-}


