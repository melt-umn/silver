grammar silver:translation:java:concrete_syntax:copper;
import silver:definition:concrete_syntax;


--synthesized attribute isTokenTerminal :: Boolean;
--synthesized attribute isOperator :: Boolean;

--attribute isTokenTerminal occurs on TerminalSpec;
--attribute isOperator occurs on TerminalSpec;

--TODO remove this
--aspect production i_terminalSpec
--top::TerminalSpec ::= fn::String t::Decorated TerminalKeywordModifier tm::Decorated TerminalModifiers reg::Decorated RegExpr
--{
--  top.isTokenTerminal = (tm.lexerPrecedence != 0);
--  top.isOperator = (tm.parserPrecedence != 0);
--}
