grammar silver:compiler:definition:concrete_syntax:copper;

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.GrammarElement
type GrammarElement foreign;

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.DisambiguationFunction

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Nonterminal
function nonterminal_
GrammarElement ::= id::String  pp::String  type_::String
{
  return error("copper FFI function");
} foreign {
  "java" : return "common.CopperUtil.makeNonTerminal(%id%.toString(), %pp%.toString(), %type_%.toString())";
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.OperatorClass

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ParserAttribute

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Production

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Terminal
function terminal_
GrammarElement ::= id::String  pp::String  regex::Regex  hasPrecedence::Boolean
    precedence_::Integer  hasAssociativity::Boolean associativity::String
    type_::String  code::String  classes_::[String] hasPrefix::Boolean
    prefix_::String submits_::[String]  dominates_::[String]
{
  return error("copper FFI function");
} {- foreign {
  "java" : return "common.CopperUtil.makeTerminalClass(%id%.toString())";
} -}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.TerminalClass
function terminalClass
GrammarElement ::= id::String
{
  return error("copper FFI function");
} foreign {
  "java" : return "common.CopperUtil.makeTerminalClass(%id%.toString())";
}
