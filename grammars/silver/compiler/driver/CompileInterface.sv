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
MaybeT<IO RootSpec> ::= grammarName::String  silverHostGen::[String]  grammarTime::Maybe<Integer>
{
  local gramPath :: String = grammarToPath(grammarName);

  return do {
    -- IO Step 1: Find the interface file, if any
    gen :: String <- findInterfaceLocation(gramPath, silverHostGen);
    let file :: String = gen ++ "src/" ++ gramPath ++ "Silver.svi";

    -- IO Step 2: Let's say so, and parse it
    lift(eprintln("Found " ++ grammarName ++ "\n\t[" ++ file ++ "]"));
    text :: ByteArray <- lift(readBinaryFile(file));
    let ir :: Either<String InterfaceItems> = nativeDeserialize(text);

    -- IO Step 3: Perhaps complain it failed to deserialize
    case ir of
    | left(msg) ->
      do {
        lift(eprintln(
          "\n\tFailed to deserialize interface file!\n" ++ msg ++
          "\n\tRecovering by parsing grammar...."));
        empty;
      }
    | right(i) when !null(i.interfaceErrors) ->
      do {
        lift(eprintln(
          "\n\tErrors unpacking interface file:\n  " ++ implode("\n  ", i.interfaceErrors) ++
          "\n\tRecovering by parsing grammar...."));
        empty;
      }
    | right(i) ->
      case grammarTime of
      -- Fail if the grammar sources are newer than the ones used to build the interface file
      | just(t) when t > i.maybeGrammarTime.fromJust -> empty
      | _ -> pure(interfaceRootSpec(i, gen))
      end
    end;
  };
}

{--
 - Takes a grammar name (already converted to a path) and searches for Silver.svi
 -}
function findInterfaceLocation
MaybeT<IO String> ::= gramPath::String searchPaths::[String]
{
  return
    case searchPaths of
    | [] -> empty
    | h :: t -> do {
        exists :: Boolean <- lift(isFile(h ++ "src/" ++ gramPath ++ "Silver.svi"));
        if exists then pure(h) else findInterfaceLocation(gramPath, t);
      }
    end;
}
