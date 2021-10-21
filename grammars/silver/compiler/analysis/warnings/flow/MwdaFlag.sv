grammar silver:compiler:analysis:warnings:flow;

synthesized attribute runMwda :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.runMwda = false;
}
abstract production mwdaFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.runMwda = true;
  forwards to rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [("--mwda", flag(mwdaFlag))];
  flagdescs <- ["\t--mwda  : report MWDA violations as errors"];
}

abstract production mwdaWrn
top::Message ::= l::Location m::String runMwda::Boolean
{
  forwards to
    if runMwda
    then err(l, m)
    else wrn(l, m);
}
