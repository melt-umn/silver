grammar silver:compiler:driver;

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
  local gramPath :: String = grammarToPath(grammarName);

  -- IO Step 1: Look for the grammar's source files
  local grammarLocation :: IOVal<Maybe<String>> = findGrammarLocation(gramPath, benv.grammarPath, ioin);

  -- IO Step 2: List those files, and obtain their newest modification time
  local files :: IOVal<[String]> = listSilverFiles(grammarLocation.iovalue.fromJust, grammarLocation.io);
  local grammarTime :: IOVal<Integer> = fileTimes(grammarLocation.iovalue.fromJust, files.iovalue, files.io);

  -- IO Step 3: Let's look for a valid interface file
  local ifaceCompile :: IOVal<Maybe<RootSpec>> =
    if clean then
      -- We just skip this search if it's a clean build
      ioval(grammarTime.io, nothing())
    else
      compileInterface(grammarName, benv.silverHostGen, grammarTime.iovalue, grammarTime.io);

  -- IO Step 4: Build the grammar, and say so
  local pr :: IO =
    print("Compiling " ++ grammarName ++ "\n\t[" ++ grammarLocation.iovalue.fromJust ++ "]\n\t[" ++ renderFileNames(files.iovalue, 0) ++ "]\n", ifaceCompile.io);
  
  local gramCompile :: IOVal<Pair<[Root] [ParseError]>> =
    compileFiles(svParser, grammarLocation.iovalue.fromJust, files.iovalue, pr);

  local rs :: RootSpec =
    if null(gramCompile.iovalue.snd) then
      grammarRootSpec(foldRoot(gramCompile.iovalue.fst), grammarName, grammarLocation.iovalue.fromJust, grammarTime.iovalue, benv.silverGen)
    else
      errorRootSpec(gramCompile.iovalue.snd, grammarName, grammarLocation.iovalue.fromJust, grammarTime.iovalue, benv.silverGen);
  
  return
    if !grammarLocation.iovalue.isJust then
      -- No grammar found!
      ioval(grammarLocation.io, nothing())
    else if null(files.iovalue) then
      -- Grammar had no files!
      ioval(files.io, nothing())
    else if ifaceCompile.iovalue.isJust then
      -- Found a valid interface file! Stop short, and return that
      ifaceCompile
    else
      -- Return the compiled grammar
      ioval(gramCompile.io, just(rs));
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
  return any(map(endsWith(_, f), [".sv", ".sv.md"])) && !startsWith(".", f);
}
function listSilverFiles
IOVal<[String]> ::= dir::String  ioin::IO
{
  local files :: IOVal<[String]> = listContents(dir, ioin);

  return ioval(files.io, filter(isValidSilverFile, files.iovalue));
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

{--
 - Takes a grammar name (already converted to a path) and searches the grammar
 - path for the first directory that matches.
 -}
function findGrammarLocation
IOVal<Maybe<String>> ::= path::String searchPaths::[String] iIn::IO
{
  local exists :: IOVal<Maybe<String>> =
    findGrammarInLocation(path, head(searchPaths), iIn);

  return 
    if null(searchPaths) then ioval(iIn, nothing())
    else if exists.iovalue.isJust then exists
    else findGrammarLocation(path, tail(searchPaths), exists.io);
}

{--
 - Looks to see if the grammar can be found in 'inPath'
 - Tries (in order) for edu:umn:cs
 - edu/umn/cs/
 - edu.umn/cs/
 - edu.umn.cs/
 -}
function findGrammarInLocation
IOVal<Maybe<String>> ::= gram::String inPath::String iIn::IO
{
  -- Find the first / in the grammar name (turned path) we're looking for.
  local idx :: Integer = indexOf("/", gram);
  
  -- Replace the first / with a .
  local nextGram :: String = substring(0, idx, gram) ++ "." ++ substring(idx + 1, length(gram), gram);
  
  local exists :: IOVal<Boolean> = isDirectory(inPath ++ gram, iIn);
  
  return 
    if idx == -1 then ioval(iIn, nothing())
    else if exists.iovalue then ioval(exists.io, just(inPath ++ gram))
    else findGrammarInLocation(nextGram, inPath, exists.io);
}

