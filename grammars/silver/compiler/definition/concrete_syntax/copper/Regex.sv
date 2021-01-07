grammar silver:compiler:definition:concrete_syntax:copper;

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Regex
type Regex foreign;

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.EmptyStringRegex
function emptyStringRegex
Regex ::=
{
  return error("copper FFI function");
} foreign {
  "java" : return "new edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.EmptyStringRegex()";
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ChoiceRegex
function choiceRegex
Regex ::= subexps::[Regex]
{
  return error("copper FFI function");
} foreign {
  "java" : return "new edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ChoiceRegex().addSubexps(new java.util.ArrayList<edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Regex>(new common.javainterop.ConsCellCollection(%subexps%)))";
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ConcatenationRegex
function concatenationRegex
Regex ::= subexps::[Regex]
{
  return error("copper FFI function");
} foreign {
  "java" : return "new edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ConcatenationRegex().addSubexps(new java.util.ArrayList<edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Regex>(new common.javainterop.ConsCellCollection(%subexps%)))";
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.KleeneStarRegex
function kleeneStarRegex
Regex ::= r::Regex
{
  return error("copper FFI function");
} foreign {
  "java" : return "new edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.KleeneStarRegex((edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Regex) %r%)";
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CharacterSetRegex
function characterSetRegex
Regex ::= cs::CharSet
{
  return error("copper FFI function");
} foreign {
  -- "java" : return "common.rawlib.RawTreeSet.isEmpty((java.util.TreeSet<Object>)%s%)";
  "java" : return "new edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CharacterSetRegex()"; -- TODO
}

nonterminal CharSet;

-- This should always be called with a single-char string.
abstract production singleChar
top::CharSet ::= c::String
{}

abstract production invertCharSet
top::CharSet ::= inner::CharSet
{}

abstract production charRange
top::CharSet ::= lower::String upper::String
{}

abstract production unionCharSets
top::CharSet ::= l::CharSet  r::CharSet
{}
