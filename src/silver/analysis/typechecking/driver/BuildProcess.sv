grammar silver:analysis:typechecking:driver;
import silver:analysis:typechecking:core;
import silver:analysis:typechecking:command;
import silver:driver;
import silver:definition:env;
import silver:definition:core; --for foldMessages
import core;

aspect production run
top::RunUnit ::= iIn::IO args::String
{
  postOps <- if a.noTypeChecking then [] else [printAllTypeErrors(grammars)]; 
}

abstract production printAllTypeErrors
top::Unit ::= specs::[Decorated RootSpec]
{
  forwards to printAllTypeErrorsHelp(specs)
	with {
		ioIn = print("Checking Type Errors.\n", top.ioIn);
	};
}

abstract production printAllTypeErrorsHelp
top::Unit ::= specs::[Decorated RootSpec]
{
  local attribute es :: String;
  es = foldMessages(head(specs).typeErrors);

  local attribute i :: IO;
  i = if es == "" then top.ioIn else print("Type Errors for : " ++ head(specs).impliedName ++ "\n" ++ es ++ "\n\n", top.ioIn);

  local attribute recurse :: Unit;
  recurse = printAllTypeErrorsHelp(tail(specs));
  recurse.ioIn = i;

  top.io = if null(specs) then top.ioIn else recurse.io;

  top.code = if null(specs) || (es == "" && recurse.code == 0)
	     then 0 
	     else -1;

  top.order = 1;
}