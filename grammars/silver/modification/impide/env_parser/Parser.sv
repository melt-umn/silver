grammar silver:modification:impide:env_parser;

import silver:modification:impide;
import silver:modification:impide:cstast;
import silver:definition:env;
import silver:definition:env:env_parser;
import silver:definition:core only grammarName, location, env;

import silver:definition:concrete_syntax:ast:env_parser;

terminal FontTerm 'font' lexer classes {C_1};

concrete production aDclInfoFont
top::IDclInfo ::= 'font' '(' l::ILocation ',' fn::IName ')'
{
  top.defs = [fontDef(top.grammarName, l.alocation, fn.aname)];
}

concrete production aLexerClassModifierFont
top::ILexerClassModifier ::= 'font' '(' n::IName ')'
{
  top.lexerClassModifiers = [lexerClassFont(n.aname)];
}

concrete production aTerminalModifierFont
top::ITerminalModifier ::= 'font' '(' n::IName ')'
{
  top.terminalModifiers = [termFont(n.aname)];
}

-- TODO We're still missing interface parts for the actualy syntaxFont declarations!!

