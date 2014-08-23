grammar silver:driver;

{--
 - Beginning with those in 'need', chase down and compile all grammars necessary to build those grammars in 'need'
 -
 - @param grammarPath   The search path (i.e. grammar path)
 - @param needStream    The list of needed grammars.
 - @param seen    The list of already compiled grammars. (initially [], most likely.)
 - @param clean   If true, ignore interface files entirely.
 - @param silverGen The generated directory path. (i.e. where src/ and bin/ are)
 -}
function compileGrammars
IOVal<[Maybe<RootSpec>]> ::=
  svParser::SVParser
  sviParser::SVIParser
  benv::BuildEnv
  need::[String]
  clean::Boolean
  ioin::IO
{
  local grammarName :: String = head(need);
  
  -- Build the first gramamr in the need list.
  local now :: IOVal<Maybe<RootSpec>> =
    compileGrammar(svParser, sviParser, benv, grammarName, clean, ioin);

  -- Recurse for the rest of the grammars needed.
  local recurse :: IOVal<[Maybe<RootSpec>]> =
    compileGrammars(svParser, sviParser, benv, tail(need), clean, now.io);

  return 
    if null(need) then
      ioval(ioin, [])
    else
      ioval(recurse.io, unsafeTrace(now.iovalue, now.io) :: recurse.iovalue);
  -- A short note about that unsafeTrace:
  -- Unfortunately, Silver lacks any way to indicate strictness in the types, and
  -- as a consequence, writing 'now.iovalue :: ...' means we can construct this
  -- list without actually forcing the IO action intended by 'now', since we
  -- never need to evaluate 'now' at all!
  -- So we use unsafeTrace to first eval the IO action (on now.io) and then return
  -- the 'now.iovalue' thunk.
}

