grammar silver:driver;

{-
 - A short guide to error codes:
 - Negative = configuration/installation error
 - 1-19 = command line/start up error
 - 20+ = Normal use error (errors in spec)
 - 127 = "abnormal" success (e.g. printed version string, quit now)
 - 0 = success of course
 -}

{- Orders:
 - 0: parsing errors
 - 1: binding errors
 - 3: interfaces
 - 4: classes
 - 5: copper_mda
 - 6: buildxml
 - 7: impide
 -}

aspect production compilation
top::Compilation ::= g::Grammars r::Grammars buildGrammar::String silverHome::String silverGen::String
{
  top.postOps <- [printAllParsingErrors(grammars ++ r.grammarList)];
  top.postOps <- [doInterfaces(grammarsToTranslate, silverGen)] ++
    map(touchIface(_, silverGen), r.grammarList);
  top.postOps <- if top.config.noBindingChecking then [] else
    [printAllBindingErrors(grammars ++ r.grammarList)]; 
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
  local pathName :: String =
    genPath ++ "src/" ++ grammarToPath(r.declaredName);

  local mkiotest :: IOVal<Boolean> =
    isDirectory(pathName, iIn);
  
  local mkio :: IOVal<Boolean> =
    if mkiotest.iovalue
    then mkiotest
    else mkdir(pathName, mkiotest.io);
  
  local pr :: IO =
    if mkio.iovalue
    then print("\t[" ++ r.declaredName ++ "]\n", mkio.io)
    else exit(-5, print("\nUnrecoverable Error: Unable to create directory: " ++ pathName ++ "\nWarning: if some interface file writes were successful, but others not, Silver's temporaries are in an inconsistent state. Use the --clean flag next run.\n\n", mkio.io));
  
  local rm :: IO = deleteStaleData(pr, genPath, r.declaredName);
  
  local wr :: IO = writeFile(pathName ++ "Silver.svi", unparseRootSpec(r), rm);
  
  return wr;
}

abstract production touchIface
top::Unit ::= r::Decorated RootSpec genPath::String
{
  top.io = touchFile(genPath ++ "src/" ++ grammarToPath(r.declaredName) ++ "Silver.svi", top.ioIn);
  top.code = 0;
  top.order = 3;
}

function deleteStaleData
IO ::= iIn::IO genPath::String gram::String
{
  local srcPath :: String = genPath ++ "src/" ++ grammarToPath(gram);
  local binPath :: String = genPath ++ "bin/" ++ grammarToPath(gram);
  
  local srcFiles :: IOVal<[String]> = listContents(srcPath, iIn);
  local binFiles :: IOVal<[String]> = listContents(binPath, srcFiles.io);
  
  return deleteStaleDataFiles( deleteStaleDataFiles(binFiles.io, binPath, binFiles.iovalue), srcPath, srcFiles.iovalue);
         
}
function deleteStaleDataFiles
IO ::= iIn::IO path::String files::[String]
{
  local isf :: IOVal<Boolean> = isFile(path ++ head(files), iIn);
  
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
  local es :: [Message] = head(specs).errors;

  local i :: IO =
    if null(es)
    then top.ioIn
    else print("Errors for : " ++ head(specs).declaredName ++ " :\n" ++ foldMessages(es) ++ "\n\n", top.ioIn);

  local recurse :: Unit = printAllBindingErrorsHelp(tail(specs));
  recurse.ioIn = i;

  top.io = if null(specs) then top.ioIn else recurse.io;

  top.code = 
    if null(specs) || (!containsErrors(es, head(specs).config.warnError) && recurse.code == 0)
    then 0
    else 20;

  top.order = 1;
}

abstract production printAllParsingErrors
top::Unit ::= specs::[Decorated RootSpec]
{
  local errs :: [Message] = foldr(append, [], map((.parsingErrors), specs));

  top.io = if null(errs) then top.ioIn else print(foldMessages(errs), top.ioIn);
  top.order = 0;
  top.code = if null(errs) then 0 else 21;
}

