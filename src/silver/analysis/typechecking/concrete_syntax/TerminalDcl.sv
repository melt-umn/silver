grammar silver:analysis:typechecking:concrete_syntax;
import silver:analysis:typechecking:core;
import silver:definition:core;
import silver:definition:concrete_syntax;

attribute typeErrors occurs on TerminalModifiers, TerminalModifier, TerminalKeywordModifier;

aspect  production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers {
  top.typeErrors = t.typeErrors ++ tm.typeErrors;
}

aspect production terminalKeywordModifierDefault
top::TerminalKeywordModifier ::= {
  top.typeErrors = [];
}


aspect production terminalModifierDefault
top::TerminalModifier ::=
{
  top.typeErrors = [];
}

aspect production terminalModifiersNone
top::TerminalModifiers ::= 
{
  top.typeErrors = tm.typeErrors;
}

aspect production terminalModifierSingle
top::TerminalModifiers ::= tm::TerminalModifier
{
  top.typeErrors = tm.typeErrors;
}

aspect production terminalModifiersCons
top::TerminalModifiers ::= h::TerminalModifier ',' t::TerminalModifiers
{
  top.typeErrors = h.typeErrors ++ t.typeErrors;
}
