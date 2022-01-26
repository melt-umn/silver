grammar silver:compiler:modification:impide;

terminal ImpFont_t 'temp_imp_ide_font' lexer classes {KEYWORD,RESERVED};
terminal Color_kwd 'color' lexer classes {KEYWORD};
terminal Bold_kwd 'bold' lexer classes {KEYWORD};
terminal Italic_kwd 'italic' lexer classes {KEYWORD};

concrete production fontDecl
top::AGDcl ::= 'temp_imp_ide_font' id::Name 'color' '(' r::Int_t ',' g::Int_t ',' b::Int_t ')' fontStyles::FontStyles ';'
{
  top.unparse = "temp_imp_ide_font " ++ id.name ++ " color(" ++ r.lexeme ++ ", " ++ g.lexeme ++ ", " ++ b.lexeme ++ ")" ++ fontStyles.unparse ++ ";\n";
  
  production fName :: String = top.grammarName ++ ":" ++ id.name;
  
  top.errors := if length(getFontDcl(fName, top.env)) > 1
                then [err(id.location, "Font style '" ++ fName ++ "' is already bound.")]
                else [];

  -- TODO: Add a way to set this via forward
  top.syntaxAst :=
    [ syntaxFont(fName,
        font(makeColor(toInteger(r.lexeme),toInteger(g.lexeme),toInteger(b.lexeme)), 
        fontStyles.isBold, fontStyles.isItalic), location=top.location,
        sourceGrammar=top.grammarName)
    ];

  propagate moduleNames, jarName;

  forwards to defsAGDcl([fontDef(top.grammarName, top.location, fName)], location=top.location);
}

monoid attribute isBold :: Boolean with false, ||;
monoid attribute isItalic :: Boolean with false, ||;

nonterminal FontStyles with isBold, isItalic, unparse;
propagate isBold, isItalic on FontStyles, FontStyle;

concrete production consFontStylesDcl
top::FontStyles ::= h::FontStyle t::FontStyles
{
  top.unparse = h.unparse ++ t.unparse;
}
concrete production nilFontStylesDcl
top::FontStyles ::= 
{
  top.unparse = "";
}

closed nonterminal FontStyle with unparse, isBold, isItalic;

aspect default production
top::FontStyle ::=
{
  propagate isBold, isItalic;
}

concrete production fontStyleBoldDcl
top::FontStyle ::= 'bold'
{
  top.unparse = " bold";
  top.isBold <- true;
}
concrete production fontStyleItalicDcl
top::FontStyle ::= 'italic'
{
  top.unparse = " italic";
  top.isItalic <- true;
}

-- temp_imp_ide_font KeywordFont color(255,0,0) bold;

