grammar silver:host;

-- concrete syntax from these grammars
exports silver:definition:core;
exports silver:definition:concrete_syntax;
exports silver:definition:type:io;
exports silver:definition:type:syntax;
exports silver:definition:regex;

-- symbols
exports silver:analysis:binding:driver;
exports silver:analysis:typechecking:driver;
exports silver:analysis:typechecking:core;
exports silver:analysis:typechecking:concrete_syntax;

--We wish regex to remain a generic grammar, so we resolve the conflict here!
import silver:definition:regex;
import silver:definition:core;

disambiguate RegexChar_t, Divide_t
{
  pluck Divide_t;
}
