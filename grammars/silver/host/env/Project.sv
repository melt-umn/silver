grammar silver:host:env;

-- concrete syntax
exports silver:definition:env:env_parser;
exports silver:definition:concrete_syntax:ast:env_parser;
exports silver:definition:regex;
exports silver:definition:flow:env_parser;

-- symbols
exports silver:definition:env;

--We wish regex to remain a generic grammar, so we resolve the conflict here!
import silver:definition:regex;
import silver:definition:env:env_parser;

-- should be escaped
disambiguate RegexChar_t, RegExprDelim
{
  pluck RegExprDelim;
}
-- For now, preserve existing behavior. Whitespace is allowed in regex, and ignored.
-- Escape it if you want it.
disambiguate RegexChar_t, WS
{
  pluck WS;
}
