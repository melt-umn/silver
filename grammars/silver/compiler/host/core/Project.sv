grammar silver:compiler:host:core;

{-
 - This file contains exports for the "core" Silver host language, excluding
 - all extensions and modifications.
 - Note that the "default" host version of Silver specified in silver:compiler:host is
 - still required to build this.
 -}

-- concrete syntax from these grammars
exports silver:compiler:definition:core;
exports silver:compiler:definition:concrete_syntax;
exports silver:compiler:definition:type:syntax;
exports silver:compiler:definition:flow:syntax;
exports silver:regex:concrete_syntax;

-- symbols
exports silver:compiler:analysis:typechecking:core;

--We wish regex to remain a generic grammar, so we resolve the conflict here!
-- Regexes end with /. Escape it if you want it.
disambiguate RegexChar_t, Divide_t
{
  pluck Divide_t;
}
