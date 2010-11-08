grammar silver:driver;
import silver:definition:core;
import silver:definition:env;
import silver:util;
import silver:util:command;

-- TODO: this file is kind of random.  Merge into BuildProcess and refactor, please.

--units of work.
nonterminal Unit with ioIn, io, code, order;
synthesized attribute code :: Integer;
synthesized attribute order :: Integer;
inherited attribute ioIn :: IO;
synthesized attribute ioOut :: IO;

aspect production run
top::RunUnit ::= iIn::IO args::String
{
  preOps <- if a.displayVersion then [printVersion()] else [];
  postOps <- [doInterfaces(depAnalysis.compiledList, silvergen)];
}

abstract production printVersion
top::Unit ::= 
{
  top.order = 0;
  top.io = print("Silver Version 0.8 pre-release\n", top.ioIn);
  top.code = -1;
}

abstract production doInterfaces
top::Unit ::= u::[Decorated RootSpec] genPath::String
{
  top.order = 3;
  top.io = writeInterfaces(print("Writing updated interface files\n", top.ioIn), u, genPath);
  top.code = 0;
}

function writeInterfaces
IO ::= iIn::IO r::[Decorated RootSpec] genPath::String
{
  return if null(r) then iIn else writeInterfaces(writeInterface(iIn, head(r), genPath), tail(r), genPath);
}

function writeInterface
IO ::= iIn::IO r::Decorated RootSpec genPath::String
{
  production attribute pathName :: String;
  pathName = genPath ++ "src/" ++ substitute("/", ":", r.impliedName) ++ "/";

  production attribute mkio :: IO;
  mkio = mkdir(pathName, iIn).io;
  
  return writeFile(pathName ++ "Silver.svi",
                   r.unparse,
                   print("\t[" ++ r.impliedName ++ "]\n", mkio));
}
