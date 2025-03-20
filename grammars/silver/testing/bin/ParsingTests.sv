grammar silver:testing:bin ;

import silver:testing;

---- Move thest to the testing library ----

abstract production parseOnlyTestAfterCPP
t::Test ::= fn::String parseF::(ParseResult<a> ::= String String)
{

  local msg :: IOVal<Maybe<String>> = 
    evalIO (
      do {
        let cppCommand :: String
        = -- "cpp -P " ++ fn ++ " | tail -n +3 > " ++ fn ++ ".cpp" ;
          "cpp " ++ fn ++ " > " ++ fn ++ ".cpp" ;
        -- even the -P option to cpp leaves 2 blanks lines, so we also
        -- use tail to remove these blank lines

        exists    :: Boolean <- isFile(fn);
        mkCPPfile :: Integer <- system(cppCommand);
        text      :: String <- readFile(fn++".cpp");
        let pr    :: ParseResult<a> = parseF(text, fn);

        return (
          if   ! exists
          then just("File \"" ++ fn ++ "\" not found.\n")
          else
          if   ! mkCPPfile == 0
          then just("The cpp process failed with error code " ++ 
               toString(mkCPPfile) ++ "\n" ++
               "The cpp command was:\n" ++ cppCommand ++ "\n")
          else
          if   ! pr.parseSuccess
          then just("Parser error: " ++ pr.parseErrors ++ "\n")
          else nothing()
        );
      },
      t.ioIn
    );

  t.pass = !msg.iovalue.isJust;

  t.msg = if msg.iovalue.isJust then msg.iovalue.fromJust else "";

  t.ioOut = msg.io;

}


abstract production parseOnlyTest
t::Test ::= fn::String parseF::(ParseResult<a> ::= String String)
{
 local msg :: IOVal<Maybe<String>> = 
    evalIO (
        do {
          exists  :: Boolean <- isFile(fn);
          text    :: String <- readFile(fn);
          let pr  :: ParseResult<a> = parseF(text, fn);

          return (
            if   ! exists
            then just("File \"" ++ fn ++ "\" not found.\n")
            else
            if   ! pr.parseSuccess
            then just("Parser error: " ++ pr.parseErrors ++ "\n")
            else nothing()
          );
        },
        t.ioIn
    );

  t.pass = !msg.iovalue.isJust;

  t.msg = if msg.iovalue.isJust then msg.iovalue.fromJust else "";

  t.ioOut = msg.io;

}

abstract production parseFailTest
t::Test ::= fn::String parseF::(ParseResult<a> ::= String String)
{
  local msg :: IOVal<Maybe<String>> = 
    evalIO (
        do {
          exists  :: Boolean <- isFile(fn);
          text    :: String <- readFile(fn);
          let pr  :: ParseResult<a> = parseF(text, fn);

          return (
             if   ! exists
            then just("File \"" ++ fn ++ "\" not found.\n")
            else
            if   pr.parseSuccess
            then just("Error: File should not be parseable.")
            else nothing()
          );
        },
        t.ioIn
    );

  t.pass = !msg.iovalue.isJust;

  t.msg = if msg.iovalue.isJust then msg.iovalue.fromJust else "";

  t.ioOut = msg.io;

}

