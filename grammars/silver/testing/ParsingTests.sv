grammar silver:testing;

abstract production parseOnlyTestAfterCPP
t::Test ::= fn::String parseF::(ParseResult<a> ::= String String)
{

  local act :: EitherT<String IO ()> = do {
    exists    :: Boolean <- lift(isFile(fn));
    unless(exists, fail("File \"" ++ fn ++ "\" not found.\n"));

    let cppCommand :: String
    = -- "cpp -P " ++ fn ++ " | tail -n +3 > " ++ fn ++ ".cpp" ;
        "cpp " ++ fn ++ " > " ++ fn ++ ".cpp" ;
    -- even the -P option to cpp leaves 2 blanks lines, so we also
    -- use tail to remove these blank lines

    mkCPPfile :: Integer <- lift(system(cppCommand));
    unless(mkCPPfile == 0, fail(
      "The cpp process failed with error code " ++ 
      toString(mkCPPfile) ++ "\n" ++
      "The cpp command was:\n" ++ cppCommand ++ "\n"));

    text      :: String <- lift(readFile(fn++".cpp"));
    let pr    :: ParseResult<a> = parseF(text, fn);

    unless(pr.parseSuccess, fail("Parser error: " ++ pr.parseErrors ++ "\n"));
  };

  local msg :: IOVal<Either<String ()>> = evalIO(act.run, t.ioIn);

  t.pass = msg.iovalue.isLeft;

  t.msg = fromLeft(msg.iovalue, "");

  t.ioOut = msg.io;

}


abstract production parseOnlyTest
t::Test ::= fn::String parseF::(ParseResult<a> ::= String String)
{
  local act :: EitherT<String IO ()> = do {
    exists  :: Boolean <- lift(isFile(fn));
    unless(exists, fail("File \"" ++ fn ++ "\" not found.\n"));

    text    :: String <- lift(readFile(fn));
    let pr  :: ParseResult<a> = parseF(text, fn);
    unless(pr.parseSuccess, fail("Parser error: " ++ pr.parseErrors ++ "\n"));
  };

  local msg :: IOVal<Either<String ()>> = evalIO(act.run, t.ioIn);

  t.pass = msg.iovalue.isLeft;

  t.msg = fromLeft(msg.iovalue, "");

  t.ioOut = msg.io;

}

abstract production parseFailTest
t::Test ::= fn::String parseF::(ParseResult<a> ::= String String)
{
  local act :: EitherT<String IO ()> = do {
    exists  :: Boolean <- lift(isFile(fn));

    unless(exists, fail("File \"" ++ fn ++ "\" not found.\n"));

    text    :: String <- lift(readFile(fn));
    let pr  :: ParseResult<a> = parseF(text, fn);
    unless(pr.parseSuccess, fail("Error: File should not be parseable."));
  };

  local msg :: IOVal<Either<String ()>> = evalIO(act.run, t.ioIn);

  t.pass = msg.iovalue.isLeft;

  t.msg = fromLeft(msg.iovalue, "");

  t.ioOut = msg.io;

}

