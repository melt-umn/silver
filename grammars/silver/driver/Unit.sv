grammar silver:driver;
import silver:definition:core;
import silver:definition:env;
import silver:util;
import silver:util:command;


--units of work.
nonterminal Unit with ioIn, io, code, order;
synthesized attribute code :: Integer;
synthesized attribute order :: Integer;

aspect production run
top::RunUnit ::= iIn::IO args::String
{
  preOps <- if a.displayVersion then [printVersion()] else [];
  postOps <- [doInterfaces(unit.compiledList ++ reUnit.compiledList ++ condUnit.compiledList, silvergen)];
}

abstract production printVersion
top::Unit ::= 
{
  top.order = 0;
  top.io = print("Silver Version 0.7 pre release\n", top.ioIn);
  top.code = -1;
}

abstract production doInterfaces
top::Unit ::= u::[Decorated RootSpec] genPath::String
{
  top.order = 3;
  top.io = writeInterfaces(top.ioIn, u, genPath);
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
  local attribute pathName :: String;
  pathName = genPath ++ "src/" ++ substitute("/", ":", r.impliedName) ++ "/";

  local attribute mkio :: IO;
  mkio = mkdir(pathName, iIn).io;

  return writeFile(pathName ++ "Silver.svi",
                   r.unparse,
                   print("Writing interface for grammar " ++ r.impliedName ++ "\n\t[" ++ pathName ++ "Silver.svi]\n", mkio));
}
