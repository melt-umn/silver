grammar core;

synthesized attribute parseSuccess :: Boolean;
synthesized attribute parseErrors :: String;
synthesized attribute parseTree<a> :: a;

nonterminal ParseResult<a> with parseSuccess, parseErrors, parseTree<a>;

abstract production parseFailed
top::ParseResult<a> ::= e::String
{
  top.parseSuccess = false;
  top.parseErrors = e;
  top.parseTree = error("Demanded parse tree when parsing failed! With errors: " ++ e);
}

abstract production parseSucceeded
top::ParseResult<a> ::= t::a
{
  top.parseSuccess = true;
  top.parseErrors = error("Demanded parse errors, but parsing succeeded!");
  top.parseTree = t;
}


-- This function is not to be considered part of the standard library. It may disappear in the future.
-- It exists because parsers used to behave this way, so it's a compatibility with older versions of Silver.

-- Just extract the tree, or die immediately, printing the parse errors. (without a stack trace, unlike error())
function parseTreeOrDieWithoutStackTrace
a ::= pr::ParseResult<a>
{
  return unsafeTrace(pr.parseTree, if pr.parseSuccess then unsafeIO() else exit(-1, print(pr.parseErrors, unsafeIO())));
}

