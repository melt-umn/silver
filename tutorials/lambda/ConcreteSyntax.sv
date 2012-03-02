grammar lambda ;

nonterminal Root_c with pp,ast<Root>;

synthesized attribute ast_Type::Type;
nonterminal TypeList_c with pp,ast<Type>;
nonterminal TypeItem_c with pp,ast<Type>;

synthesized attribute ast_Expr::Expr;
nonterminal Expr_c with pp,ast<Expr>;

synthesized attribute ast_Term::Term;
nonterminal Term_c with pp,ast<Term>;

synthesized attribute ast_Factor::Factor;
nonterminal Factor_c with pp,ast<Factor>;

synthesized attribute ast_Expr_funct::Expr_funct;
nonterminal Expr_funct_c with pp,ast<Expr_funct>;

synthesized attribute ast_Expr_arith::Expr_arith;
nonterminal Expr_arith_c with pp,ast<Expr_arith>;

-- Root
concrete productions r::Root_c
 | e::Expr_c { r.pp = e.pp;
               r.ast = root(e.ast); }

-- Expressions
concrete productions e::Expr_c
 | e1::Expr_funct_c { e.pp = e1.pp;
                      e.ast = expr_expr_f(e1.ast); }
 | 'let' ic::Id_t ':' ty::TypeList_c '=' e1::Expr_c 'in' e2::Expr_c
                    { e.pp = concat([text("let"), text(ic.lexeme), text(":"), ty.pp, text("="), e1.pp, text("in"), e2.pp]); 
                      e.ast = expr_let(ic.lexeme, ty.ast, e1.ast, e2.ast); }
 | 'lambda' i::Id_t ':' ty::TypeList_c '.' e1::Expr_c
                    { e.pp = concat([text("lambda"), text(i.lexeme), text(":"), ty.pp, text("."), e1.pp]);
                      e.ast = expr_lambda(i.lexeme, ty.ast, e1.ast); }

-- Function expressions
concrete productions e::Expr_funct_c
 | e1::Expr_funct_c e2::Expr_arith_c { e.pp = cat(e1.pp, e2.pp);
                                       e.ast = expr_funct(e1.ast, e2.ast); }
 | e1::Expr_arith_c                  { e.pp = e1.pp;
                                       e.ast = methodpassing_ex(e1.ast); }

-- Arithmetic expressions
concrete productions e::Expr_arith_c
 | e1::Expr_arith_c '+' t::Term_c { e.pp = concat([e1.pp, text("+"), t.pp]);
                                    e.ast = expr_add(e1.ast, t.ast); }
 | e1::Expr_arith_c '-' t::Term_c { e.pp = concat([e1.pp, text("-"), t.pp]);
                                    e.ast = expr_sub(e1.ast, t.ast); }
 | t::Term_c                      { e.pp = t.pp;
                                    e.ast = expr_term(t.ast); }

-- Term
concrete productions t::Term_c
 | t1::Term_c '*' f::Factor_c { t.pp = concat([t1.pp, text("*"), f.pp]);
                                t.ast = term_mul(t1.ast, f.ast); }
 | t1::Term_c '/' f::Factor_c { t.pp = concat([t1.pp, text("/"), f.pp]);
                                t.ast = term_div(t1.ast, f.ast); }
 | f::Factor_c                { t.pp = f.pp;
                                t.ast = term_factor(f.ast); }

-- Factor
concrete productions e::Factor_c
 | '(' inner::Expr_c ')' { e.pp = parens(inner.pp);
                           e.ast = factor_parens(inner.ast); }
 | i::Id_t               { e.pp = text(i.lexeme);
                           e.ast = factor_id(i.lexeme); }
 | i::IntLit_t           { e.pp = text(i.lexeme);
                           e.ast = factor_int(i.lexeme); }

-- Types
concrete productions tl::TypeList_c
 | ti::TypeItem_c '->' tl1::TypeList_c { tl.pp = concat([ti.pp, text("->"), tl1.pp]);
                                         tl.ast = arrow(ti.ast,tl1.ast); }
 | ti::TypeItem_c                      { tl.pp = ti.pp;
                                         tl.ast = ti.ast; }

concrete productions ti::TypeItem_c
 | 'int'                  { ti.pp = text("int");
                            ti.ast = int(); }
 | '(' tl::TypeList_c ')' { ti.pp = parens(tl.pp);
                            ti.ast = tl.ast; }

