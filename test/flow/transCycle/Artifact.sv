grammar flow:transCycle;

-- A cycle in translation attribute occurrences is an error.  With flow warnings enabled,
-- the flow analysis must not attempt inference on it: a production constructing itself in
-- a translation attribute of its own nonterminal type would otherwise make inference nest
-- translation attribute vertex types without bound, and never terminate.
-- This is a separate grammar because the error affects the flow analysis of the whole grammar.
--
-- The productions come before the attribute declarations on purpose: wrongFlowCode stops
-- looking as soon as it finds the expected error, so the productions' flow checks, which are
-- what force the flow analysis, must come before the declaration that raises it.

wrongFlowCode "Cycle in translation attributes" {
nonterminal SGExpr;

abstract production sgLit
top::SGExpr ::= i::Integer
{
  top.sgVal = i + top.sgEnv;
  top.sgTrans = sgLit(i);
}

abstract production sgNode
top::SGExpr ::= a::SGExpr b::SGExpr
{
  a.sgEnv = top.sgEnv;
  b.sgEnv = top.sgEnv;
  top.sgVal = a.sgVal + b.sgVal;
  top.sgTrans = sgNode(new(a.sgTrans), new(b.sgTrans));
}

inherited attribute sgEnv::Integer occurs on SGExpr;
synthesized attribute sgVal::Integer occurs on SGExpr;
translation attribute sgTrans::SGExpr occurs on SGExpr;
}
