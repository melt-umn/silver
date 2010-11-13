grammar core;

{--
 - true if parsing successfully produced a syntax tree. false if a parse error occurred.
 -}
synthesized attribute parseSuccess :: Boolean;
{--
 - A string containing the parse errors reported by copper.  The format is unspecified, yet.
 -}
synthesized attribute parseErrors :: String;
{--
 - The parse tree, if parsing was successful.
 -}
synthesized attribute parseTree<a> :: a;

{--
 - A container type for the result of calling a parser.
 -
 - @param a  The start nonterminal type.
 -}
nonterminal ParseResult<a> with parseSuccess, parseErrors, parseTree<a>;

{--
 - Parse failure constructor.
 -
 - @param e  The error string reported by the parser.
 -}
abstract production parseFailed
top::ParseResult<a> ::= e::String
{
  top.parseSuccess = false;
  top.parseErrors = e;
  top.parseTree = error("Demanded parse tree when parsing failed! With errors: " ++ e);
}

{--
 - Parse success constructor.
 -
 - @param t  The syntax tree returned by the parser.
 -}
abstract production parseSucceeded
top::ParseResult<a> ::= t::a
{
  top.parseSuccess = true;
  top.parseErrors = error("Demanded parse errors, but parsing succeeded!");
  top.parseTree = t;
}


{--
 - Make parsers behave like they used to in previous versions of Silver.
 -
 - Exits and prints parse errors if parsing fails, without a stack trace.
 -
 - @deprecated
 - @param pr  The ParseResult returned by the parser
 - @return  The syntax tree reported by the parser.  Does not return if parsing fails.
 -}
function parseTreeOrDieWithoutStackTrace
a ::= pr::ParseResult<a>
{
  return unsafeTrace(pr.parseTree, if pr.parseSuccess then unsafeIO() else exit(-1, print(pr.parseErrors ++ "\n\n", unsafeIO())));
}

