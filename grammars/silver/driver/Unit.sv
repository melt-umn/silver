grammar silver:driver;

{-
 - A short guide to error codes:
 - Negative = configuration/installation error
 - 1-19 = command line/start up error
 - 20+ = Normal use error (errors in spec)
 - 127 = "abnormal" success (e.g. printed version string, quit now)
 - 0 = success of course
 -}


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

