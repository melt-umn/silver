grammar silver:compiler:refactor;

synthesized attribute refactorExplicitNew :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.refactorExplicitNew = false;
}
abstract production refactorExplicitNewFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.refactorExplicitNew = true;
  forwards to refactorFlag(@rest);
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [ flagSpec(name="--refactor-explicit-new", paramString=nothing(),
               help="Insert an explicit call to new() at all instances of implcit undecoration",
               flagParser=flag(refactorExplicitNewFlag))
           ];
}

aspect production childReference
top::Expr ::= @q::QName
{
  top.transforms <-
    if top.config.refactorExplicitNew
    && isDecorable(q.lookupValue.typeScheme.monoType, top.env) && !top.finalType.isDecorated
    then
      rule on Expr of
      | baseExpr(q1) when q1.name == q.name && q1.nameLoc == q.nameLoc ->
        Silver_Expr { new($QName{q1}) }
      end
    else fail();
}

aspect production localReference
top::Expr ::= @q::QName
{
  top.transforms <-
    if top.config.refactorExplicitNew
    && isDecorable(q.lookupValue.typeScheme.monoType, top.env) && !top.finalType.isDecorated
    then
      rule on Expr of
      | baseExpr(q1) when q1.name == q.name && q1.nameLoc == q.nameLoc ->
        Silver_Expr { new($QName{q1}) }
      end
    else fail();
}
