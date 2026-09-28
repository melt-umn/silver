grammar flow;

-- Sharing a translation attribute whose flow type includes inherited attributes on its
-- own tree must not be reported as a potential cycle: supplying those attributes at the
-- site depends on the shared tree's value, and the value's flow type on those attributes,
-- but that path only restates the flow type.

nonterminal SCExpr;
nonterminal SCTarget;
inherited attribute scEnv::Integer occurs on SCExpr, SCTarget;
synthesized attribute scVal::Integer occurs on SCExpr, SCTarget;
translation attribute scTrans::SCTarget occurs on SCExpr;
flowtype scVal {scEnv} on SCExpr, SCTarget;
flowtype scTrans {scEnv, scTrans.scEnv} on SCExpr;

abstract production scTgtLit
top::SCTarget ::= i::Integer
{
  top.scVal = i + top.scEnv;
}

abstract production scTgtPair
top::SCTarget ::= a::SCTarget b::SCTarget
{
  a.scEnv = top.scEnv;
  b.scEnv = top.scEnv;
  top.scVal = a.scVal + b.scVal;
}

abstract production scLit
top::SCExpr ::= i::Integer
{
  top.scVal = i + top.scEnv;
  top.scTrans = scTgtLit(i);
}

noWarnCode "a cycle may exist" {
abstract production scPair
top::SCExpr ::= a::SCExpr b::SCExpr
{
  a.scEnv = top.scEnv;
  b.scEnv = top.scEnv;
  top.scVal = a.scVal + b.scVal;
  top.scTrans = scTgtPair(@a.scTrans, @b.scTrans);
}
}
