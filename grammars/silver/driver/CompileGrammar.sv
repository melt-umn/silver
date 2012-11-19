grammar silver:driver;

import silver:util:raw:treemap as rtm;

{--
 - Hunts down a grammar and obtains its symbols, either by building or from an interface file.
 -}
function compileGrammar
IOVal<Maybe<RootSpec>> ::=
  svParser::SVParser
  sviParser::SVIParser
  grammarName::String
  grammarPath::[String]
  genPath::String
  clean::Boolean
  ioin::IO
{
  --the grammar path ':' replaced by '/'
  local gramPath :: String = grammarToPath(grammarName);

  -- the location (if found) of the grammar
  local grammarLocation :: IOVal<Maybe<String>> = findGrammarLocation(gramPath, grammarPath, ioin);

  -- the list of silver files for the grammar
  local files :: IOVal<[String]> = listSilverFiles(grammarLocation.iovalue.fromJust, grammarLocation.io);

  -- Modification times of the respective files
  local ifaceTime :: IOVal<Maybe<Integer>> = isValidInterface(genPath ++ "src/" ++ gramPath ++ "Silver.svi", files.io);
  local grammarTime :: IOVal<Integer> = fileTimes(grammarLocation.iovalue.fromJust, files.iovalue, ifaceTime.io);

  local pr :: IO = print("Compiling Grammar: " ++ grammarName ++ "\n", grammarTime.io);
  
  local gramCompile :: IOVal<Grammar> = compileFiles(svParser, grammarLocation.iovalue.fromJust, files.iovalue, pr);
  local ifaceCompile :: IOVal<ParseResult<IRoot>> = compileInterface(sviParser, genPath ++ "src/" ++ gramPath, pr);
  
  -- Not being clean, valid interface file, newer than the grammar source
  local useInterface :: Boolean = !clean && ifaceTime.iovalue.isJust && ifaceTime.iovalue.fromJust > grammarTime.iovalue;
  
  local join :: IO =
    if useInterface then
      if ifaceCompile.iovalue.parseSuccess then
        ifaceCompile.io
      else
        print("\n\tFailed to parse interface file!\n" ++ ifaceCompile.iovalue.parseErrors ++
                 "\n\tRecovering by parsing grammar....\n", gramCompile.io)
    else gramCompile.io;
  
  local rs :: RootSpec =
    if useInterface && ifaceCompile.iovalue.parseSuccess then
      interfaceRootSpec(ifaceCompile.iovalue.parseTree)
    else
      grammarRootSpec(gramCompile.iovalue, grammarName);
  
  return if !grammarLocation.iovalue.isJust then
    ioval(grammarLocation.io, nothing())
  else
    ioval(join, just(rs));
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
  local attribute files :: IOVal<[String]>;
  files = listContents(dir, ioin);

  return ioval(files.io, filter(isValidSilverFile, files.iovalue));
}

{--
 - Determines interface modification time.
 -}
function isValidInterface
IOVal<Maybe<Integer>> ::= file::String  ioin::IO
{
  local attribute hasInterface :: IOVal<Boolean>;
  hasInterface = isFile(file, ioin);

  local attribute modTime :: IOVal<Integer>;
  modTime = fileTime(file, hasInterface.io);

  return if hasInterface.iovalue then ioval(modTime.io, just(modTime.iovalue)) else ioval(hasInterface.io, nothing());
}

{--
 - Determines the maximum modification time of all files in a directory.
 - Including the directory itself, to detect file deletions.
 -}
function fileTimes
IOVal<Integer> ::= dir::String is::[String] i::IO 
{
  local attribute ft :: IOVal<Integer>;
  ft = fileTime(dir ++ head(is), i);

  local attribute rest :: IOVal<Integer>;
  rest = fileTimes(dir, tail(is), ft.io);

  return if null(is)
         then fileTime(dir, i) -- check the directory itself. Catches deleted files.
         else if ft.iovalue > rest.iovalue
              then ioval(rest.io, ft.iovalue)
              else rest;
}

