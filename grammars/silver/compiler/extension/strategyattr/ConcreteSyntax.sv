grammar silver:compiler:extension:strategyattr;

inherited attribute givenGenName::String;

concrete production partialStrategyAttributeDcl
top::AGDcl ::= 'partial' 'strategy' 'attribute' a::Name '=' e::StrategyExpr_c ';'
{
  top.unparse = "strategy attribute " ++ a.unparse ++ "=" ++ e.unparse ++ ";";
  e.givenGenName = a.name;
  forwards to strategyAttributeDcl(false, @a, [], [], e.ast);
}

concrete production totalStrategyAttributeDcl
top::AGDcl ::= 'strategy' 'attribute' a::Name '=' e::StrategyExpr_c ';'
{
  top.unparse = "strategy attribute " ++ a.unparse ++ "=" ++ e.unparse ++ ";";
  e.givenGenName = a.name;
  forwards to strategyAttributeDcl(true, @a, [], [], e.ast);
}

closed tracked nonterminal StrategyExpr_c with givenGenName, unparse, ast<StrategyExpr>;

concrete productions top::StrategyExpr_c
| 'id'
{
  top.unparse = "id";
  top.ast = id(genName=top.givenGenName);
}
| 'fail'
{
  top.unparse = "fail";
  top.ast = fail(genName=top.givenGenName);
}
| s1::StrategyExpr_c '<*' s2::StrategyExpr_c
{
  top.unparse = s"(${s1.unparse} <* ${s2.unparse})";
  top.ast = sequence(s1.ast, s2.ast, genName=top.givenGenName);
  s1.givenGenName = top.givenGenName ++ "_fst";
  s2.givenGenName = top.givenGenName ++ "_snd";
}
| s1::StrategyExpr_c '<+' s2::StrategyExpr_c
{
  top.unparse = s"(${s1.unparse} <+ ${s2.unparse})";
  top.ast = choice(s1.ast, s2.ast, genName=top.givenGenName);
  s1.givenGenName = top.givenGenName ++ "_left";
  s2.givenGenName = top.givenGenName ++ "_right";
}
| 'all' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"all(${s.unparse})";
  top.ast = allTraversal(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_all_arg";
}
| 'some' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"some(${s.unparse})";
  top.ast = someTraversal(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_some_arg";
}
| 'one' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"one(${s.unparse})";
  top.ast = oneTraversal(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_one_arg";
}
| id::StrategyQName '(' s::StrategyExprs_c ')'
{
  top.unparse = s"${id.ast.unparse}(${s.unparse})";
  top.ast = prodTraversal(id.ast, s.ast, genName=top.givenGenName);
  s.index = 1;
  s.givenGenName = top.givenGenName ++ "_" ++ id.ast.name;
}
| 'rec' n::Name Arrow_t s::StrategyExpr_c
{
  top.unparse = s"rec ${n.name} -> (${s.unparse})";
  top.ast = recComb(new(n), s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName;
}
| 'rule' 'on' id::Name '::' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "rule on " ++ id.unparse ++ "::" ++ ty.unparse ++ " of " ++ ml.unparse ++ " end";
  top.ast = rewriteRule(new(id), new(ty), new(ml), genName=top.givenGenName);
}
| 'rule' 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "rule on " ++ ty.unparse ++ " of " ++ ml.unparse ++ " end";
  top.ast = rewriteRule(name("top"), new(ty), new(ml), genName=top.givenGenName);
}
| id::StrategyQName
{
  top.unparse = id.ast.unparse;
  top.ast = nameRef(id.ast, genName=top.givenGenName);
}
| '(' s::StrategyExpr_c ')'
{
  top.unparse = s"(${s.unparse})";
  top.ast = s.ast;
  s.givenGenName = top.givenGenName;
}
| 'printTerm'
{
  top.unparse = s"printTerm";
  top.ast = printTerm(genName=top.givenGenName);
}
| 'try' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"try(${s.unparse})";
  top.ast = try(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_try_arg";
}
| 'repeat' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"repeat(${s.unparse})";
  top.ast = repeatS(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_repeat_arg";
}
| 'reduce' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"reduce(${s.unparse})";
  top.ast = reduce(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_reduce_arg";
}
| 'bottomUp' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"bottomUp(${s.unparse})";
  top.ast = bottomUp(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_bottomUp_arg";
}
| 'topDown' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"topDown(${s.unparse})";
  top.ast = topDown(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_topDown_arg";
}
| 'downUp' '(' s1::StrategyExpr_c ',' s2::StrategyExpr_c ')'
{
  top.unparse = s"downUp(${s1.unparse}, ${s2.unparse})";
  top.ast = downUp(s1.ast, s2.ast, genName=top.givenGenName);
  s1.givenGenName = top.givenGenName ++ "_downUp_arg1";
  s2.givenGenName = top.givenGenName ++ "_downUp_arg2";
}
| 'allBottomUp' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"allBottomUp(${s.unparse})";
  top.ast = allBottomUp(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_allBottomUp_arg";
}
| 'allTopDown' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"allTopDown(${s.unparse})";
  top.ast = allTopDown(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_allTopDown_arg";
}
| 'allDownUp' '(' s1::StrategyExpr_c ',' s2::StrategyExpr_c ')'
{
  top.unparse = s"allDownUp(${s1.unparse}, ${s2.unparse})";
  top.ast = allDownUp(s1.ast, s2.ast, genName=top.givenGenName);
  s1.givenGenName = top.givenGenName ++ "_allDownUp_arg1";
  s2.givenGenName = top.givenGenName ++ "_allDownUp_arg2";
}
| 'someBottomUp' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"someBottomUp(${s.unparse})";
  top.ast = someBottomUp(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_someBottomUp_arg";
}
| 'someTopDown' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"someTopDown(${s.unparse})";
  top.ast = someTopDown(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_someTopDown_arg";
}
| 'someDownUp' '(' s1::StrategyExpr_c ',' s2::StrategyExpr_c ')'
{
  top.unparse = s"someDownUp(${s1.unparse}, ${s2.unparse})";
  top.ast = someDownUp(s1.ast, s2.ast, genName=top.givenGenName);
  s1.givenGenName = top.givenGenName ++ "_someDownUp_arg1";
  s2.givenGenName = top.givenGenName ++ "_someDownUp_arg2";
}
| 'onceBottomUp' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"onceBottomUp(${s.unparse})";
  top.ast = onceBottomUp(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_onceBottomUp_arg";
}
| 'onceTopDown' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"onceTopDown(${s.unparse})";
  top.ast = onceTopDown(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_onceTopDown_arg";
}
| 'onceDownUp' '(' s1::StrategyExpr_c ',' s2::StrategyExpr_c ')'
{
  top.unparse = s"onceDownUp(${s1.unparse}, ${s2.unparse})";
  top.ast = onceDownUp(s1.ast, s2.ast, genName=top.givenGenName);
  s1.givenGenName = top.givenGenName ++ "_onceDownUp_arg1";
  s2.givenGenName = top.givenGenName ++ "_onceDownUp_arg2";
}
| 'innermost' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"innermost(${s.unparse})";
  top.ast = innermost(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_innermost_arg";
}
| 'outermost' '(' s::StrategyExpr_c ')'
{
  top.unparse = s"outermost(${s.unparse})";
  top.ast = outermost(s.ast, genName=top.givenGenName);
  s.givenGenName = top.givenGenName ++ "_outermost_arg";
}

inherited attribute index::Integer;

tracked nonterminal StrategyExprs_c with index, givenGenName, unparse, ast<StrategyExprs>;
concrete productions top::StrategyExprs_c
| h::StrategyExpr_c ',' t::StrategyExprs_c
{
  top.unparse = h.unparse ++ ", " ++ t.unparse;
  top.ast = consStrategyExpr(h.ast, t.ast);
  h.givenGenName = top.givenGenName ++ "_arg" ++ toString(top.index);
  t.givenGenName = top.givenGenName;
  t.index = top.index + 1;
}
| h::StrategyExpr_c
{
  top.unparse = h.unparse;
  top.ast = consStrategyExpr(h.ast, nilStrategyExpr());
  h.givenGenName = top.givenGenName ++ "_arg" ++ toString(top.index);
}
|
{
  top.unparse = "";
  top.ast = nilStrategyExpr();
}

tracked nonterminal StrategyQName with ast<QName>;
concrete productions top::StrategyQName
(strategyQNameOne) | id::StrategyName_t
{ top.ast = qNameId(name(id.lexeme)); }
(strategyQNameCons) | id::StrategyName_t ':' qn::StrategyQName
{ top.ast = qNameCons(name(id.lexeme), $2, qn.ast); }
