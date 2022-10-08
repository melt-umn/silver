grammar silver:compiler:definition:concrete_syntax:copper;

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Regex
type Regex foreign;

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.EmptyStringRegex
function emptyStringRegex
Regex ::=
{
  return error("copper FFI function");
} foreign {
  "java": return "new edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.EmptyStringRegex()";
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ChoiceRegex
function choiceRegex
Regex ::= subexps::[Regex]
{
  return error("copper FFI function");
} foreign {
  "java": return "new edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ChoiceRegex().addSubexps(new java.util.ArrayList<edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Regex>(new common.javainterop.ConsCellCollection<>(%subexps%)))";
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ConcatenationRegex
function concatenationRegex
Regex ::= subexps::[Regex]
{
  return error("copper FFI function");
} foreign {
  "java": return "new edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ConcatenationRegex().addSubexps(new java.util.ArrayList<edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Regex>(new common.javainterop.ConsCellCollection<>(%subexps%)))";
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.KleeneStarRegex
function kleeneStarRegex
Regex ::= r::Regex
{
  return error("copper FFI function");
} foreign {
  "java": return "new edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.KleeneStarRegex((edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Regex) %r%)";
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CharacterSetRegex
function characterSetRegex
Regex ::= cs::CharSet
{
  return error("copper FFI function");
} foreign {
  "java": return "%cs%";
}

-- common.copperutil.CharSet
type CharSet foreign;

-- This should always be called with a single-char string.
function singleChar
CharSet ::= c::String
{
  return error("copper FFI function");
} foreign {
  "java": return "common.CopperUtil.makeSingleChar(%c%.toString())";
}

function invertCharSet
CharSet ::= inner::CharSet
{
  return error("copper FFI function");
} foreign {
  "java": return "((edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CharacterSetRegex)%inner%).invert()";
}

function charRange
CharSet ::= lower::String  upper::String
{
  return error("copper FFI function");
} foreign {
  "java": return "common.CopperUtil.makeCharRange(%lower%.toString(), %upper%.toString())";
}

function unionCharSets
CharSet ::= l::CharSet  r::CharSet
{
  return error("copper FFI function");
} foreign {
  "java": return "edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CharacterSetRegex.union((edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CharacterSetRegex)%l%, (edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CharacterSetRegex)%r%)";
}
