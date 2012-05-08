grammar silver:driver;

{-
 - A short guide to error codes:
 - Negative = configuration/installation error
 - 1-19 = command line/start up error
 - 20+ = Normal use error (errors in spec)
 - 127 = "abnormal" success (e.g. printed version string, quit now)
 - 0 = success of course
 -}
closed nonterminal Unit with ioIn, io, code, order;

synthesized attribute code :: Integer;
synthesized attribute order :: Integer;
inherited attribute ioIn :: IO;
synthesized attribute ioOut :: IO;


aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  preOps <- [checkSilverHome(silverhome), checkSilverGen(silvergen)];
  preOps <- if a.displayVersion then [printVersion()] else [];
  postOps <- [doInterfaces(grammarsToTranslate, silvergen)];
}

function runAll
IOVal<Integer> ::= i::IO l::[Unit]
{
  local attribute now :: Unit;
  now = head(l);
  now.ioIn = i;

  return  if null(l) 
	  then ioval(i, 0)
	  else if now.code != 0
	       then ioval(now.io, now.code)
	       else runAll(now.io, tail(l));
}

abstract production checkSilverHome
top::Unit ::= s::String
{
  local attribute problem :: Boolean;
  problem = s == "/";

  top.io = if problem then print("Missing SILVER_HOME. Installation problem?\n",top.ioIn) else top.ioIn;
  top.code = if problem then -1 else 0;
  top.order = 0;
}

abstract production checkSilverGen
top::Unit ::= s::String
{
  local attribute problem :: Boolean;
  problem = s == "/";

  top.io = if problem then print("Missing SILVER_GEN or -G <path>. A location to store intermediate files is necessary.\n",top.ioIn) else top.ioIn;
  top.code = if problem then -2 else 0;
  top.order = 0;
}

abstract production printVersion
top::Unit ::= 
{
  top.order = 0;
  top.io = print("Silver Version 0.3.6-dev\n", top.ioIn);
  top.code = 127;
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
  local attribute pathName :: String;
  pathName = genPath ++ "src/" ++ grammarToPath(r.declaredName);

  local attribute mkiotest :: IOVal<Boolean>;
  mkiotest = isDirectory(pathName, iIn);
  
  local attribute mkio :: IOVal<Boolean>;
  mkio = if mkiotest.iovalue
         then mkiotest
         else mkdir(pathName, mkiotest.io);
  
  local attribute pr :: IO;
  pr = if mkio.iovalue
       then print("\t[" ++ r.declaredName ++ "]\n", mkio.io)
       else exit(-5, print("\nUnrecoverable Error: Unable to create directory: " ++ pathName ++ "\nWarning: if some interface file writes were successful, but others not, Silver's temporaries are in an inconsistent state. Use the --clean flag next run.\n\n", mkio.io));
  
  local attribute rm :: IO;
  rm = deleteStaleData(pr, genPath, r.declaredName);
  
  local attribute wr :: IO;
  wr = writeFile(pathName ++ "Silver.svi", unparseRootSpec(r), rm);
  
  return wr;
}

function deleteStaleData
IO ::= iIn::IO genPath::String gram::String
{
  local attribute srcPath :: String;
  srcPath = genPath ++ "src/" ++ grammarToPath(gram);

  local attribute binPath :: String;
  binPath = genPath ++ "bin/" ++ grammarToPath(gram);
  
  local attribute srcFiles :: IOVal<[String]>;
  srcFiles = listContents(srcPath, iIn);
  
  local attribute binFiles :: IOVal<[String]>;
  binFiles = listContents(binPath, srcFiles.io);
  
  return deleteStaleDataFiles( deleteStaleDataFiles( binFiles.io, binPath, binFiles.iovalue), srcPath, srcFiles.iovalue);
         
}
function deleteStaleDataFiles
IO ::= iIn::IO path::String files::[String]
{
  local attribute isf :: IOVal<Boolean>;
  isf = isFile(path ++ head(files), iIn);
  
  return if null(files) then iIn
         else if !isf.iovalue then deleteStaleDataFiles(isf.io, path, tail(files))
         else deleteStaleDataFiles( deleteFile(path ++ head(files), isf.io).io, path, tail(files));
}


