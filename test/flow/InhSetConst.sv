grammar flow;

nonterminal ISCExpr with env1;

production iscZero
top::ISCExpr ::=
{}

production iscOp
top::ISCExpr ::= e::ISCExpr
{
  e.env1 = top.env1;
  forwards to iscDispatch(e);
}

inhset OpInhs := {env1};

production iscDispatch
top::ISCExpr ::= e::Decorated ISCExpr with OpInhs
{}

attribute env2 occurs on ISCExpr;

warnCode "Equation has transitive dependency on child e's inherited attribute for flow:env2" {
  inhset OpInhs <- {env2};
}

function mkISCE
Decorated ISCExpr with OpInhs ::=
{
  production res::ISCExpr = iscOp(iscZero());
  res.env1 = [];
  return res;
}

-- This is just a type error
wrongCode "Declaration of global decInhsetRes with type Decorated flow:ISCExpr with flow:OpInhs has initialization expression with type Decorated flow:ISCExpr with {flow:env1, :env2}" {
  global decInhsetRes::Decorated ISCExpr with OpInhs = decorate iscZero() with {env1 = []; env2 = [];};
}

wrongCode "flow:OpInhs is not a subset of {flow:env1, :env2} (arising from the use of castRef)" {
  global castToDecInhsetConst::Decorated ISCExpr with OpInhs = castRef(decorate iscZero() with {env1 = []; env2 = [];});
}

global castFromDecInhsetConst::Decorated ISCExpr with {env1, env2} = castRef(mkISCE());

-- TODO: This should probably be ok?
wrongCode "flow:OpInhs is not a subset of flow:OpInhs (arising from the use of castRef)" {
  global castSameDecInhsetConst::Decorated ISCExpr with OpInhs = castRef(mkISCE());
}
