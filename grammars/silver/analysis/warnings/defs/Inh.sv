grammar silver:analysis:warnings:defs;

synthesized attribute warnMissingInh :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.warnMissingInh = false;
}
abstract production warnMissingInhFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnMissingInh = true;
  forwards to rest;
}
aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("--warn-missing-inh", flag(warnMissingInhFlag))];
}


-- TOTALLY INCOMPLETE, but let's kick this off!

aspect production synDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  local myFlow :: EnvTree<Pair<String String>> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).flowTypes;

  top.errors <- 
    if null(e.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    then
      case e of
      | childReference(lq) ->
          let inhs :: [String] = filter(isEquationPresent(lookupInh(top.signature.fullName, lq.lookupValue.fullName, _, top.flowEnv), _), inhDepsForSyn(q.lookupAttribute.fullName, e.typerep.typeName, myFlow))
           in if null(inhs) then []
              else [wrn(top.location, "Access of syn attribute " ++ q.pp ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
          end
      | lhsReference(lq) -> []
      | localReference(lq) -> []
      | forwardReference(lq) -> []
      | _ -> []
    end
    else [];
}

function inhDepsForSyn
[String] ::= syn::String  nt::String  flow::EnvTree<Pair<String String>>
{
  return lookupAllBy(stringEq, syn, searchEnvTree(nt, flow));
}

function isEquationPresent
Boolean ::= f::([FlowDef] ::= String)  attr::String
{
  return !null(f(attr));
}
