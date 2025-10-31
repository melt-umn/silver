grammar flow;

nonterminal FIExpr with env1, env2, errors1;
flowtype FIExpr = forward {env1, env2}, errors1 {env1};

warnCode "In production flow:fwrdEqSiteExceedsFT, the implicit copy equation for flow:errors1 (due to forwarding) would exceed the attribute's flow type with dependencies on flow:env2" {
production fwrdEqSiteExceedsFT
top::FIExpr ::= e::FIExpr
{
  forward.env1 = top.env2;
  forwards to ^e;
}
}

production fie
top::FIExpr ::= 
{
  top.errors1 = null(top.env1);
}

nonterminal FIExtExpr with errors1, toFIE;
translation attribute toFIE::FIExpr;

production fieExt
top::FIExtExpr ::=
{
  top.toFIE = fie();
  top.errors1 = false;
}

warnCode "Equation requires inherited attribute flow:env1 on forward.flow:toFIE, however the copy equation from forwarding is suppressed" {
production fieExtFwrd
top::FIExtExpr ::= e::FIExtExpr
{
  top.toFIE = @e.toFIE;
  top.errors1 = null(forward.toFIE.env1);
  forwards to fieExt();
}
}