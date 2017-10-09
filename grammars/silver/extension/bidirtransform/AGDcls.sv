grammar silver:extension:bidirtransform;

imports silver:definition:core;
imports silver:extension:convenience;
imports silver:definition:type:syntax;

terminal Rewrite_kwd   'rewrite'   lexer classes {KEYWORD,RESERVED};
terminal As_kwd        'as'        lexer classes {KEYWORD,RESERVED};
terminal Transform_kwd 'transform' lexer classes {KEYWORD,RESERVED};
terminal Into_kwd      'into'      lexer classes {KEYWORD,RESERVED};

-- Syntax example
-- 
-- rewrite mul(const(1), X) as X;
-- rewrite neg(X) as sub(0, X);
--
-- transform add(l,r) into add_c(l,'+',r);
-- transform sub(l,r) into sub_c(l,'-',r);
-- transform mul(l,r) into mul_c(l,'*',r);
-- transform neg(e) into neg_c('-', e);
-- transform const(i) into const_c(terminal(IntLit_t, toString(i)));
--
-- -- maybe?
--
-- obtain Expr_c from t::Term_c with exprTerm(t);
-- obtain Term_c from f::Factor_c with termFactor(f);
-- obtain Factor_c from e::Expr_c with nested_c('(',e,')');


concrete production rewriteAGDcl
ag::AGDcl ::= 'rewrite' prd::nestedAbstractProductionWithArgs 'as' rwrt::nestedAbstractProductionWithArgs
{

}

concrete production transformAGDcl
ag::AGDcl ::= 'transform' prd::singleAbstractProduction 'into' rwrt::nestedConcreteProductionWithArgs
{

}

