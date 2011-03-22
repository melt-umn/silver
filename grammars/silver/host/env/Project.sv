grammar silver:host:env;

-- concrete syntax
exports silver:definition:env:env_parser;
exports silver:definition:core:env_parser;
exports silver:definition:concrete_syntax:env_parser;
exports silver:definition:regex;

-- symbols
exports silver:definition:env;

--We wish regex to remain a generic grammar, so we resolve the conflict here!
import silver:definition:regex;
import silver:definition:env:env_parser;

disambiguate RegexChar_t, RegExprDelim
{
  pluck RegExprDelim;
}
