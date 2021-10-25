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
