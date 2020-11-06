grammar silver:modification:impide;

-- terminal's font in IDE
terminal Font_kwd  'font' lexer classes {KEYWORD};

concrete production terminalModifierFont
top::TerminalModifier ::= 'font' '=' id::QName
{
  top.unparse = "font = " ++ id.name;

  top.terminalModifiers := [termFont(id.lookupFont.fullName)];
  top.errors := id.lookupFont.errors;
  propagate silver:modification:copper:lexerClasses;
}

-- Allows fonts on lexer classes, too!
concrete production lexerClassModifierFont
top::LexerClassModifier ::= 'font' '=' id::QName
{
  top.unparse = "font = " ++ id.name;

  top.lexerClassModifiers := [lexerClassFont(id.lookupFont.fullName)];
  top.errors := id.lookupFont.errors;
}

