grammar silver:analysis:binding:driver;
import silver:driver;
import silver:util:cmdargs;

import silver:definition:core;
import silver:definition:env;

synthesized attribute noBindingChecking :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.noBindingChecking = false;
}
abstract production nobindingFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.noBindingChecking = true;
  forwards to rest;
}
aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("--xb", flag(nobindingFlag))];
  -- omitting from descriptions deliberately!
  
  postOps <- if a.noBindingChecking then [] else [printAllBindingErrors(grammars)]; 
}

abstract production printAllBindingErrors
top::Unit ::= specs::[Decorated RootSpec]
{
  forwards to printAllBindingErrorsHelp(specs)
	with {
		ioIn = print("Checking For Errors.\n", top.ioIn);
	};
}

abstract production printAllBindingErrorsHelp
top::Unit ::= specs::[Decorated RootSpec]
{
  local attribute es :: [Message];
  es = head(specs).errors;

  local attribute i :: IO;
  i = if null(es)
      then top.ioIn
      else print("Errors for : " ++ head(specs).declaredName ++ " :\n" ++ foldMessages(es) ++ "\n\n", top.ioIn);

  local attribute recurse :: Unit;
  recurse = printAllBindingErrorsHelp(tail(specs));
  recurse.ioIn = i;

  top.io = if null(specs) then top.ioIn else recurse.io;

  top.code = if null(specs) || (!containsErrors(es, false) && recurse.code == 0)
	     then 0
	     else 20;

  top.order = 0;
}

