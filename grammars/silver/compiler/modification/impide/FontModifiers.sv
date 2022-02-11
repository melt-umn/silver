grammar silver:compiler:modification:impide;

-- terminal's font in IDE
terminal Font_kwd  'font' lexer classes {KEYWORD};

concrete production terminalModifierFont
top::TerminalModifier ::= 'font' '=' id::QName
{
  top.unparse = "font = " ++ id.name;

  top.terminalModifiers := [termFont(id.lookupFont.fullName)];
  top.errors := id.lookupFont.errors;
  propagate silver:compiler:modification:copper:lexerClasses;
}

-- Allows fonts on lexer classes, too!
concrete production lexerClassModifierFont
top::LexerClassModifier ::= 'font' '=' id::QName
{
  top.unparse = "font = " ++ id.name;

  top.lexerClassModifiers :=
    [ lexerClassFont(id.lookupFont.fullName, location=top.location,
        sourceGrammar=top.grammarName)
    ];
  top.errors := id.lookupFont.errors;
}

