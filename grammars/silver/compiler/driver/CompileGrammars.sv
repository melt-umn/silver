grammar silver:compiler:driver;

{--
 - Eat the stream `need` and produce the output stream of (maybe, if found) `RootSpec`s.
 -
 - @param benv   The compiler configuration, including search paths
 - @param need   A **stream** of grammars to compile.
 - @param clean  If true, ignore interface files entirely.
 -}
function compileGrammars
IOVal<[Maybe<RootSpec>]> ::=
  svParser::SVParser
  benv::BuildEnv
  need::[String]
  clean::Boolean
  ioin::IOToken
{
  local grammarName :: String = head(need);
  
  -- Build the first gramamr in the need list.
  local now :: IOVal<Maybe<RootSpec>> =
    compileGrammar(svParser, benv, grammarName, clean, ioin);

  -- Recurse for the rest of the grammars needed.
  local recurse :: IOVal<[Maybe<RootSpec>]> =
    compileGrammars(svParser, benv, tail(need), clean, now.io);

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

