grammar silver:modification:impide;

import silver:modification:impide:cstast;
import silver:modification:copper;

-- terminal's font in IDE
terminal Font_kwd  'font';

concrete production terminalModifierFont
top::TerminalModifier ::= 'font' '=' fontName::IdLower_t
{
  top.pp = "font = " ++ fontName.lexeme;
  top.location = loc(top.file, $1.line, $1.column);

  top.terminalModifiers = [termFont(fontName.lexeme)];
  top.errors := [];
}

-- Allows fonts on lexer classes, too!
concrete production lexerClassModifierFont
top::LexerClassModifier ::= 'font' '=' fontName::IdLower_t
{
  top.pp = "font = " ++ fontName.lexeme;
  top.location = loc(top.file, $1.line, $1.column);

  top.lexerClassModifiers = [lexerClassFont(fontName.lexeme)];
  top.errors := [];
}

