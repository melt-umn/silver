grammar silver:compiler:definition:type:syntax;

tracked nonterminal KindExpr with config, grammarName, errors, env, unparse, kindrep;

propagate errors on KindExpr; -- TODO: Are errors even possible here?

concrete production starKindExpr
top::KindExpr ::= '*'
{
  top.unparse = "*";
  top.kindrep = starKind();
}

concrete production inhSetKindExpr
top::KindExpr ::= 'InhSet'
{
  top.unparse = "InhSet";
  top.kindrep = inhSetKind();
}

concrete production arrowKindExpr
top::KindExpr ::= k1::KindExpr '->' k2::KindExpr
{
  top.unparse = s"${k1.unparse} -> ${k2.unparse}";
  top.kindrep = arrowKind(k1.kindrep, k2.kindrep);
}

concrete production parenKindExpr
top::KindExpr ::= '(' k::KindExpr ')'
{
  top.unparse = s"(${k.unparse})";
  forwards to @k;
}
