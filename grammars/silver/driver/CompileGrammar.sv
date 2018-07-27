grammar silver:driver;

{--
 - Hunts down a grammar and obtains its symbols, either by building or from an interface file.
 -}
function compileGrammar
IOVal<Maybe<RootSpec>> ::=
  svParser::SVParser
  benv::BuildEnv
  grammarName::String
  clean::Boolean
  ioin::IO
{
  -- the grammar path ':' replaced by '/'
  local gramPath :: String = grammarToPath(grammarName);

  -- the location (if found) of the grammar
  local grammarLocation :: IOVal<Maybe<String>> = findGrammarLocation(gramPath, benv.grammarPath, ioin);

  -- the list of silver files for the grammar
  local files :: IOVal<[String]> = listSilverFiles(grammarLocation.iovalue.fromJust, grammarLocation.io);

  -- Modification times of the respective files
  local ifaceTime :: IOVal<Maybe<Integer>> = isValidInterface(benv.silverGen ++ "src/" ++ gramPath ++ "Silver.svi", files.io);
  local grammarTime :: IOVal<Integer> = fileTimes(grammarLocation.iovalue.fromJust, files.iovalue, ifaceTime.io);

  -- Decide to compile a grammar or find an interface file
  local pr :: IO = print("Compiling " ++ grammarName ++ "\n\t[" ++ grammarLocation.iovalue.fromJust ++ "]\n\t[" ++ renderFileNames(files.iovalue, 0) ++ "]\n", grammarTime.io);
  local gramCompile :: IOVal<Pair<[Root] [ParseError]>> =
    compileFiles(svParser, grammarLocation.iovalue.fromJust, files.iovalue, pr);

  local ifaceCompile :: IOVal<Either<String GrammarProperties>> =
    compileInterface(grammarName, benv.silverGen ++ "src/" ++ gramPath, grammarTime.io);
  
  -- Not being clean, valid interface file, newer than the grammar source
  local useInterface :: Boolean = !clean && ifaceTime.iovalue.isJust && ifaceTime.iovalue.fromJust > grammarTime.iovalue;
  
  local join :: IO =
    if useInterface then
      case ifaceCompile.iovalue of
      | right(_) -> ifaceCompile.io
      | left(msg) ->
        -- Do something weird. Demand interface parser finishes, print.
        -- Then return the normal compile io token branch... Hey, works!
        unsafeTrace(gramCompile.io,
          print("\n\tFailed to deserialize interface file!\n" ++ msg ++
                "\n\tRecovering by parsing grammar....\n", ifaceCompile.io))
      end
    else gramCompile.io;
  
  local rs :: RootSpec =
    if useInterface && ifaceCompile.iovalue.isRight then
      interfaceRootSpec(ifaceCompile.iovalue.fromRight, ifaceTime.iovalue.fromJust)
    else if null(gramCompile.iovalue.snd) then
      grammarRootSpec(foldRoot(gramCompile.iovalue.fst), grammarName, grammarLocation.iovalue.fromJust, grammarTime.iovalue)
    else
     errorRootSpec(gramCompile.iovalue.snd, grammarName, grammarLocation.iovalue.fromJust, grammarTime.iovalue);
  
  return if !grammarLocation.iovalue.isJust || null(files.iovalue) then
    ioval(grammarLocation.io, nothing())
  else
    ioval(join, just(rs));
}

function foldRoot
Grammar ::= l::[Root]
{
  return foldr(consGrammar, nilGrammar(), l);
}

{--
 - Determined whether a file name should be considered a Silver source file.
 -}
function isValidSilverFile
Boolean ::= f::String
{
  return endsWith(".sv", f) && !startsWith(".", f);
}
function listSilverFiles
IOVal<[String]> ::= dir::String  ioin::IO
{
  local files :: IOVal<[String]> = listContents(dir, ioin);

  return ioval(files.io, filter(isValidSilverFile, files.iovalue));
}

{--
 - Determines interface modification time.
 -}
function isValidInterface
IOVal<Maybe<Integer>> ::= file::String  ioin::IO
{
  local hasInterface :: IOVal<Boolean> = isFile(file, ioin);
  local modTime :: IOVal<Integer> = fileTime(file, hasInterface.io);

  return if hasInterface.iovalue then ioval(modTime.io, just(modTime.iovalue)) else ioval(hasInterface.io, nothing());
}

{--
 - Determines the maximum modification time of all files in a directory.
 - Including the directory itself, to detect file deletions.
 -}
function fileTimes
IOVal<Integer> ::= dir::String is::[String] i::IO 
{
  local ft :: IOVal<Integer> = fileTime(dir ++ head(is), i);
  local rest :: IOVal<Integer> = fileTimes(dir, tail(is), ft.io);

  return if null(is)
         then fileTime(dir, i) -- check the directory itself. Catches deleted files.
         else if ft.iovalue > rest.iovalue
              then ioval(rest.io, ft.iovalue)
              else rest;
}

-- A crude approximation of line wrapping
function renderFileNames
String ::= files::[String]  depth::Integer
{
  return
    if null(files) then "" else
    if depth >= 7 then "\n\t " ++ renderFileNames(files, 0) else
    head(files) ++
    if null(tail(files)) then "" else " " ++ renderFileNames(tail(files), depth + 1);
}

