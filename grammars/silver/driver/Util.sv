grammar silver:driver;

{--
 - Turns a grammar name into a path, including trailing slash.
 -}
function grammarToPath
String ::= g::String
{
  return substitute(":", "/", g) ++ "/";
}

{--
 - Sorts a list of Units by priority order. (small to large)
 -}
function unitMergeSort
[Unit] ::= c1::[Unit]
{
  return sortBy(unitLTE, c1);
}
function unitLTE
Boolean ::= l::Unit r::Unit
{
  return l.order <= r.order;
}

{--
 - Determines whether an interface file is newer or older than the grammar.
 -}
function isValidInterface
IOVal<Boolean> ::= iIn::IO ifacefile::String grammarPath::String fs::[String]
{
  local attribute hasInterface :: IOVal<Boolean>;
  hasInterface = isFile(ifacefile, iIn);

  local attribute modTime :: IOVal<Integer>;
  modTime = fileTime(ifacefile, hasInterface.io);

  local attribute maxTime :: IOVal<Integer>;
  maxTime = fileTimes(modTime.io, grammarPath, fs);

  return if !hasInterface.iovalue then ioval(hasInterface.io, false) else ioval(maxTime.io, modTime.iovalue > maxTime.iovalue);
}

{--
 - Determines the maximum modification time of all files in a directory.
 - Including the directory itself, to detect file deletions.
 -}
function fileTimes
IOVal<Integer> ::= i::IO dir::String is::[String]
{
  local attribute ft :: IOVal<Integer>;
  ft = fileTime(dir ++ head(is), i);

  local attribute rest :: IOVal<Integer>;
  rest = fileTimes(ft.io, dir, tail(is));

  return if null(is)
         then fileTime(dir, i) -- check the directory itself. Catches deleted files.
         else if ft.iovalue > rest.iovalue
              then ioval(rest.io, ft.iovalue)
              else rest;
}

{--
 - Ensures a string ends with a forward slash. Safe to use if it already has one.
 -}
function endWithSlash
String ::= s::String
{
  return if endsWith("/", s) then s else s ++ "/";
}

{--
 - Determined whether a file name should be considered a Silver source file.
 -}
function isValidSilverFile
Boolean ::= f::String
{
  return endsWith(".sv", f) && !startsWith(".", f);
}

{--
 - Takes a grammar name (already converted to a path) and searches the grammar
 - path for the first directory that matches.
 -}
function findGrammarLocation
IOVal<Maybe<String>> ::= path::String searchPaths::[String] iIn::IO
{
  local attribute exists :: IOVal<Boolean>;
  exists = isDirectory(head(searchPaths) ++ path, iIn);

  return if null(searchPaths)
         then ioval(iIn, nothing())
         else if exists.iovalue
              then ioval(exists.io, just(head(searchPaths) ++ path))
              else findGrammarLocation(path, tail(searchPaths), exists.io);
}

{--
 - Returns a pair, suitable for building an environment
 -}
function grammarPairing
Pair<String Decorated RootSpec> ::= r::Decorated RootSpec
{
  return pair(r.declaredName, r);
}
