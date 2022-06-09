grammar silver:compiler:driver;

{--
 - Eat the stream `need` and produce the output stream of (maybe, if found) `RootSpec`s.
 -
 - @param benv   The compiler configuration, including search paths
 - @param need   A **stream** of grammars to compile.
 - @param clean  If true, ignore interface files entirely.
 -}
function compileGrammars
IO<[Maybe<RootSpec>]> ::=
  svParser::SVParser
  benv::BuildEnv
  need::[String]
  clean::Boolean
{
  return
    case need of
    | [] -> pure([])
    | h :: t -> do {
        -- Build the first grammar in the need list.
        now::Maybe<RootSpec> <- compileGrammar(svParser, benv, h, clean).run;
        -- Recurse for the rest of the grammars needed.
        recurse::[Maybe<RootSpec>] <- compileGrammars(svParser, benv, t, clean);
        return now :: recurse;
      }
    end;
}

