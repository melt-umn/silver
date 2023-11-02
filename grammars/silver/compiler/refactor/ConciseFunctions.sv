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
    | productionBody(_, productionStmtsSnoc(productionStmtsNil(), returnDef(_, e, _)), _) ->
      rule on AGDcl of
      | a when getParsedOriginLocation(a) == getParsedOriginLocation(top) ->
        Silver_AGDcl {
          -- TODO: Change this to actually be the right translation when concise functions are finished
          global $Name{id} :: a = $Expr{new(e)};
        }
      end
    | _ -> fail()
    end;
}
