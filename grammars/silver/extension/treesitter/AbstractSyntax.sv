
synthesized attribute atomName :: String;
attribute atomName occurs on SyntaxLexerClassModifier, SyntaxLexerClassModifiers, SyntaxTerminalModifier, SyntaxTerminalModifiers;
{-- SYNTAX LEXER CLASS MODIFIERS PRODUCTIONS --}
abstract production lexerClassAtomName
top::SyntaxLexerClassModifier ::= atomName::String
{
  top.cstErrors := [];

  top.atomName = atomName;
}

aspect production consLexerClassMod
top::SyntaxLexerClassModifiers ::= h::SyntaxLexerClassModifier  t::SyntaxLexerClassModifiers
{
  -- only the first non-zero font declaration is effective
  top.atomName =
    if h.atomName != ""
    then h.atomName
    else t.atomName;
}

aspect production nilLexerClassMod
top::SyntaxLexerClassModifiers ::=
{
  top.atomName = "";
}

aspect default production
top::SyntaxLexerClassModifier ::=
{
  top.atomName = "";
}

{-- TERMINAL MODIFIERS PRODUCTIONS --}
abstract production terminalAtomName
top::SyntaxTerminalModifier ::= atomName::String
{
  top.cstErrors := [];

  top.atomName = atomName;
}

aspect production consTerminalMod
top::SyntaxTerminalModifiers ::= h::SyntaxTerminalModifier t::SyntaxTerminalModifiers
{
  top.atomName =
    if h.atomName != ""
    then h.atomName
    else t.atomName;
}

aspect production nilTerminalMod
top::SyntaxTerminalModifiers ::=
{
  top.atomName = "";
}

aspect default production
top::SyntaxTerminalModifier ::=
{
  top.atomName = "";
}
