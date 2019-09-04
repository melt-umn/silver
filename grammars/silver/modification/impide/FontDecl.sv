grammar silver:modification:impide;

terminal ImpFont_t 'temp_imp_ide_font' lexer classes {KEYWORD,RESERVED};
terminal Color_kwd 'color' lexer classes {KEYWORD};
terminal Bold_kwd 'bold' lexer classes {KEYWORD};
terminal Italic_kwd 'italic' lexer classes {KEYWORD};

concrete production fontDecl
top::AGDcl ::= 'temp_imp_ide_font' id::Name 'color' '(' r::Int_t ',' g::Int_t ',' b::Int_t ')' fontStyles::FontStyles ';'
{
  top.unparse = "temp_imp_ide_font " ++ id.name ++ " color(" ++ r.lexeme ++ ", " ++ g.lexeme ++ ", " ++ b.lexeme ++ ")" ++ fontStyles.unparse ++ ";\n";
  
  production fName :: String = top.grammarName ++ ":" ++ id.name;

  top.defs = [fontDef(top.grammarName, top.location, fName)];
  
  top.errors := if length(getFontDcl(fName, top.env)) > 1
                then [err(id.location, "Font style '" ++ fName ++ "' is already bound.")]
                else [];

  top.syntaxAst = [syntaxFont(
                   fName, 
                   font(makeColor(toInteger(r.lexeme),toInteger(g.lexeme),toInteger(b.lexeme)), 
                        fontStyles.isBold, 
                        fontStyles.isItalic)
		  )];

  -- TODO: this forward is bs
  forwards to emptyAGDcl(location=top.location);
}

nonterminal FontStyles with isBold, isItalic, unparse;

synthesized attribute isBold :: Boolean;
synthesized attribute isItalic :: Boolean;

concrete production consFontStylesDcl
top::FontStyles ::= h::FontStyle t::FontStyles
{
  top.unparse = h.unparse ++ t.unparse;
  top.isBold = h.isBold || t.isBold;
  top.isItalic = h.isItalic || t.isItalic;
}
concrete production nilFontStylesDcl
top::FontStyles ::= 
{
  top.unparse = "";
  top.isBold = false;
  top.isItalic = false;
}

nonterminal FontStyle with unparse, isBold, isItalic;

concrete production fontStyleBoldDcl
top::FontStyle ::= 'bold'
{
  top.unparse = " bold";
  top.isBold = true;
  top.isItalic = false;
}
concrete production fontStyleItalicDcl
top::FontStyle ::= 'italic'
{
  top.unparse = " italic";
  top.isBold = false;
  top.isItalic = true;
}

-- temp_imp_ide_font KeywordFont color(255,0,0) bold;

