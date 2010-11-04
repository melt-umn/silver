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

