grammar silver:compiler:driver;

import silver:reflect;
import silver:reflect:nativeserialize;

{--
 - Find an interface file, if it exists, and it's valid (parsable and modification time is newer).
 -
 - @param grammarName    The grammar we're looking for an interface file for
 - @param silverHostGen  The search path to look for interface files within
 - @param grammarTime    The newest modification time of the source files, to compare against
 -}
function compileInterface
IOVal<Maybe<RootSpec>> ::= grammarName::String  silverHostGen::[String]  grammarTime::Integer  ioin::IO
{
  local gramPath :: String = grammarToPath(grammarName);

  -- IO Step 1: Find the interface file, if any
  local gen :: IOVal<Maybe<String>> = findInterfaceLocation(gramPath, silverHostGen, ioin);
  
  local file :: String = gen.iovalue.fromJust ++ "src/" ++ gramPath ++ "Silver.svi";

  -- IO Step 2: See if it's new enough
  local modTime :: IOVal<Integer> = fileTime(file, gen.io);
  
  -- IO Step 3: Let's say so, and parse it
  local pr :: IO = print("Found " ++ grammarName ++ "\n\t[" ++ file ++ "]\n", modTime.io);
  local text :: IOVal<ByteArray> = readByteFile(file, pr);

  local ir :: Either<String InterfaceItems> = nativeDeserialize(text.iovalue);
  
  -- IO Step 4: Perhaps complain it failed to parse
  local pr2 :: IO =
    case ir of
    | right(i) ->
      if !null(i.interfaceErrors)
      then
        print("\n\tErrors unpacking interface file:\n  " ++ implode("\n  ", i.interfaceErrors) ++
              "\n\tRecovering by parsing grammar....\n", text.io)
      else text.io
    | left(msg) ->
        print("\n\tFailed to deserialize interface file!\n" ++ msg ++
              "\n\tRecovering by parsing grammar....\n", text.io)
    end;

  local rs :: RootSpec = interfaceRootSpec(ir.fromRight, modTime.iovalue, gen.iovalue.fromJust);

  return
    if !gen.iovalue.isJust then
      -- Didn't find one. Stop short, return nothing.
      ioval(gen.io, nothing())
    else if modTime.iovalue <= grammarTime then
      -- Interface file is too old, stop short, return nothing.
      ioval(modTime.io, nothing())
    else if ir.isLeft || !null(ir.fromRight.interfaceErrors) then
      -- Deserialization failed, return nothing.
      ioval(pr2, nothing())
    else ioval(pr2, just(rs));
}

{--
 - Takes a grammar name (already converted to a path) and searches for Silver.svi
 -}
function findInterfaceLocation
IOVal<Maybe<String>> ::= gramPath::String searchPaths::[String] ioin::IO
{
  local inPath :: String = head(searchPaths);
  local exists :: IOVal<Boolean> = isFile(inPath ++ "src/" ++ gramPath ++ "Silver.svi", ioin);
  
  return 
    if null(searchPaths) then ioval(ioin, nothing())
    else if exists.iovalue then ioval(exists.io, just(inPath))
    else findInterfaceLocation(gramPath, tail(searchPaths), exists.io);
}

