grammar silver:compiler:extension:convenienceaspects;

closed nonterminal ConvenienceAspects_c with location, unparse, ast<AGDcl>;

concrete productions top::ConvenienceAspects_c
| 'aspect' attr::QName 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "aspect " ++ attr.unparse ++ " on " ++ ty.unparse ++ " of " ++ ml.unparse ++ " end";
  top.ast = convenienceAspects(attr, ty, ml, location=top.location);
}

