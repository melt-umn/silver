
function makeArgs
Exprs ::= loc::Location names::[String]
{
  return case names of
    [] -> exprsEmpty()
  | n :: rest -> exprsCons(baseExpr(qName(loc, n)), makeArgs(loc, rest))
  end;
}
{-
concrete production propagate
top::ProductionStmt ::= 'propagate' a::Name ';'
{
  top.errors <- if !top.blockContext.permitProductionAttributes
                then [err(top.location, "Production attributes are not valid in this context.")]
                else [];

  local topName::QName = qName(top.location, top.signature.op.elementName);
  local inputs::Exprs = makeArgs(top.location, map((.elementName), top.signature.inputElements));

  forwards to 
    attributeDef(
      concreteDefLHS(topName),
      '.',
      qNameAttrOccur(_),
      '=',
      applicationExpr(functionReference(_), applicationExpr(inputs)),
      location=top.location);
}
-}