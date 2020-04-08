grammar silver:extension:strategyattr;

inherited attribute givenGenName::String;

concrete production strategyAttributeDcl_c
top::AGDcl ::= 'strategy' 'attribute' a::Name '=' e::StrategyExpr_c ';'
{
  top.unparse = "strategy attribute " ++ a.unparse ++ "=" ++ e.unparse ++ ";";
  e.givenGenName = a.name;
  forwards to strategyAttributeDcl(a, [], e.ast, location=top.location);
}

closed nonterminal StrategyExpr_c with location, givenGenName, unparse, ast<StrategyExpr>;

concrete productions top::StrategyExpr_c
| 'id'
{
  top.unparse = "id";
  top.ast = id(genName=top.givenGenName, location=top.location);
}
| 'fail'
{
  top.unparse = "fail";
  top.ast = fail(genName=top.givenGenName, location=top.location);
}
| s1::StrategyExpr_c '<*' s2::StrategyExpr_c
{
  top.unparse = s"(${s1.unparse} <* ${s2.unparse})";
  top.ast = sequence(s1.ast, s2.ast, genName=top.givenGenName, location=top.location);
  s1.givenGenName = top.givenGenName;
  s2.givenGenName = top.givenGenName ++ "_cont";
}
| s1::StrategyExpr_c '<+' s2::StrategyExpr_c
{
  top.unparse = s"(${s1.unparse} <+ ${s2.unparse})";
  top.ast = choice(s1.ast, s2.ast, genName=top.givenGenName, location=top.location);
  s1.givenGenName = top.givenGenName ++ "_left";
  s2.givenGenName = top.givenGenName ++ "_right";
}
| 'all' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"all(${s.unparse})";
  top.ast = allTraversal(s.ast, genName=top.givenGenName, location=top.location);
  s.givenGenName = top.givenGenName ++ "_item";
}
| 'some' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"some(${s.unparse})";
  top.ast = someTraversal(s.ast, genName=top.givenGenName, location=top.location);
  s.givenGenName = top.givenGenName ++ "_item";
}
| 'one' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"one(${s.unparse})";
  top.ast = oneTraversal(s.ast, genName=top.givenGenName, location=top.location);
  s.givenGenName = top.givenGenName ++ "_item";
}
| 'rec' n::Name Arrow_t s::StrategyExpr_c
{
  top.unparse = s"rec ${n.name} -> (${s.unparse})";
  top.ast = rec(n, s.ast, genName=top.givenGenName, location=top.location);
  s.givenGenName = top.givenGenName ++ "_" ++ n.name;
}
| 'rule' 'on' id::Name '::' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "rule on " ++ id.unparse ++ "::" ++ ty.unparse ++ " of " ++ ml.unparse ++ " end";
  top.ast = rewriteRule(id, ty, ml, genName=top.givenGenName, location=top.location);
}
| 'rule' 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "rule on " ++ ty.unparse ++ " of " ++ ml.unparse ++ " end";
  top.ast = rewriteRule(name("top", top.location), ty, ml, genName=top.givenGenName, location=top.location);
}
| id::StrategyQName
{
  top.unparse = id.ast.unparse;
  top.ast = nameRef(id.ast, genName=top.givenGenName, location=top.location);
}
| '(' s::StrategyExpr_c ')'
{
  top.unparse = s"(${s.unparse})";
  top.ast = s.ast;
  s.givenGenName = top.givenGenName;
}
| 'try' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"try(${s.unparse})";
  top.ast = try(s.ast, genName=top.givenGenName, location=top.location);
  s.givenGenName = top.givenGenName ++ "_try";
}

nonterminal StrategyQName with location, ast<QName>;
concrete productions top::StrategyQName
| id::StrategyName_t
{ top.ast = qNameId(name(id.lexeme, id.location), location=top.location); }
| id::StrategyName_t ':' qn::StrategyQName
{ top.ast = qNameCons(name(id.lexeme, id.location), $2, qn.ast, location=top.location); }