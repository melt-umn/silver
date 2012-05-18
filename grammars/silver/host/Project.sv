grammar silver:host;

-- concrete syntax from these grammars
exports silver:definition:core;
exports silver:definition:concrete_syntax;
exports silver:definition:type:syntax;
exports silver:definition:regex;

-- symbols
exports silver:analysis:binding:driver;
exports silver:analysis:typechecking:core;

--We wish regex to remain a generic grammar, so we resolve the conflict here!
import silver:definition:regex;
import silver:definition:core;

-- Regexes end with /. Escape it if you want it.
disambiguate RegexChar_t, Divide_t
{
  pluck Divide_t;
}
-- For now, preserve existing behavior. Whitespace is allowed in regex, and ignored.
-- Escape it if you want it.
disambiguate RegexChar_t, WhiteSpace
{
  pluck WhiteSpace;
}
