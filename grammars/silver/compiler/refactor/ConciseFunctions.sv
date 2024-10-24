grammar silver:compiler:refactor;

synthesized attribute refactorConciseFunctions :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.refactorConciseFunctions = false;
}
abstract production refactorConciseFunctionsFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.refactorConciseFunctions = true;
  forwards to refactorFlag(@rest);
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [ flagSpec(name="--refactor-concise-functions", paramString=nothing(),
               help="Replace simple production-style functions with concise function syntax",
               flagParser=flag(refactorConciseFunctionsFlag))
           ];
}

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.transforms <-
    case body of
    | productionBody(_, productionStmtsSnoc(productionStmtsNil(), returnDef(_, e, _)), _)
      when top.config.refactorConciseFunctions && !e.hasImplicitDec ->
      rule on AGDcl of
      | a when getParsedOriginLocation(a) == getParsedOriginLocation(top) ->
        Silver_AGDcl {
          fun $Name{^id} $FunctionSignature{^ns} = $Expr{^e};
        }
      end
    | _ -> fail()
    end;
}

aspect production functionDclFFI
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}'
{
  -- This is a forwarding prod, we don't want to rewrite FFI functions like normal ones
  top.transforms := ns.transforms <+ body.transforms <+ ffidefs.transforms;
}

monoid attribute hasImplicitDec::Boolean with false, || occurs on
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AnnoAppExprs, AnnoExpr, AppExpr, AssignExpr, PrimPatterns, PrimPattern;
propagate hasImplicitDec on
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AnnoAppExprs, AnnoExpr, AppExpr, AssignExpr, PrimPatterns, PrimPattern;
aspect hasImplicitDec on top::Expr using <- of
| childReference(q) -> isDecorable(q.lookupValue.typeScheme.monoType, top.env) && top.finalType.isDecorated
end;
