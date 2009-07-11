--grammar silver:definition:concrete_syntax;
import silver:definition:env;

synthesized attribute nonTerminalName :: String;

nonterminal NonTerminalSpec with nonTerminalName, unparse;

function nonTerminalSpec
Decorated NonTerminalSpec ::= fn::String {
  return decorate i_nonTerminalSpec(fn) with {};
}

abstract production i_nonTerminalSpec
top::NonTerminalSpec ::= fn::String
{
  top.nonTerminalName = fn;
  top.unparse = "'" ++ fn ++ "'";
}
