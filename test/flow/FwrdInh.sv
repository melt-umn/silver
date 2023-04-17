grammar flow;

nonterminal FIExpr with env1, env2, errors1;
flowtype FIExpr = forward {env1, env2}, errors1 {env1};

warnCode "In production flow:fwrdEqSiteExceedsFT, the implicit copy equation for flow:errors1 (due to forwarding) would exceed the attribute's flow type with dependencies on flow:env2" {
production fwrdEqSiteExceedsFT
top::FIExpr ::= e::FIExpr
{
  forward.env1 = top.env2;
  forwards to e;
}
}
