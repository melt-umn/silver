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
top::ParseResult<a> ::= e::ParseError
{
  top.parseSuccess = false;
  top.parseErrors = e.parseErrors;
  top.parseTree = error("Demanded parse tree when parsing failed! With errors: " ++ e.parseErrors);
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


{--
 - Representation of a parser error.
 -}
nonterminal ParseError with parseErrors;

{--
 - This production as currently designed matches up exactly with what copper raises in its syntax error exception.
 -
 - @param diagnosticString  An un-pretty but convenient way of printing out this parser error.
 - @param location  The location (filename, line, column, index, etc) of the parser error
 - @param expectedNames  The display names of the expected terminals
 - @param matchedNames  The display names of what the parser matched
 -}
abstract production syntaxError
top::ParseError ::=
  diagnosticString::String
  location::Location
  expectedNames::[String]
  matchedNames::[String]
{
  top.parseErrors = diagnosticString;
}

{--
 - This production accomodates an unknown type of parser error.
 -
 - @param diagnosticString  A string describing the error
 - @param file  The filename the error occured on. No other location information is available.
 -}
abstract production unknownParseError
top::ParseError ::=
  diagnosticString::String
  file::String
{
  top.parseErrors = diagnosticString;
}

