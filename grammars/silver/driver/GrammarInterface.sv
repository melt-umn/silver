grammar silver:driver;

nonterminal IOInterface with io, interfaces, sviParser;

abstract production compileInterface
top::IOInterface ::= iIn::IO f::String genPath::String
{
  local attribute modTime :: IOVal<Integer>;
  modTime = fileTime(genPath ++ f, iIn);

  local attribute i :: IO;
  i = print("\t[" ++ genPath ++ f ++ "]\n", modTime.io);

  local attribute text :: IOVal<String>;
  text = readFile(genPath ++ f, i);

  local attribute ir :: IRoot;
  ir = top.sviParser(text.iovalue, f).parseTree; -- I'm assuming that interface files never parse error, so we aren't making this pretty.

  local attribute inf :: Interface; 
  inf = fullInterface(modTime.iovalue, f, genPath, ir.spec);

  top.interfaces = [inf];
  top.io = text.io;
}

-- TODO: this type isn't properly used. at all. we should remove 
nonterminal Interface with rSpec, lastModified, interfaceFile, interfaceLocation;

synthesized attribute lastModified :: Integer;
synthesized attribute interfaceFile :: String;
synthesized attribute interfaceLocation :: String;

abstract production fullInterface
top::Interface ::= i::Integer f::String l::String r::Decorated RootSpec
{
  top.lastModified = i;
  top.interfaceFile = f;
  top.interfaceLocation = l;
  top.rSpec = r;
}

function getSpecs
[Decorated RootSpec] ::= s::[Decorated Interface]
{
  return map((.rSpec), s);
}


