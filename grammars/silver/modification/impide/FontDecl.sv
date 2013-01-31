grammar silver:modification:impide;

terminal ImpFont_t 'temp_imp_ide_font' lexer classes {KEYWORD};
terminal Color_kwd 'color' ;
terminal Bold_kwd 'bold' ;
terminal Italic_kwd 'italic' ;

concrete production fontDcl
top::AGDcl ::= 'temp_imp_ide_font' fontName::IdLower_t 'color' '(' r::Int_t ',' g::Int_t ',' b::Int_t ')' fontStyles::FontStyles ';'
{
  top.pp = "temp_imp_ide_font " ++ fontName.lexeme ++ " color(" ++ r.lexeme ++ ", " ++ g.lexeme ++ ", " ++ b.lexeme ++ ")" ++ fontStyles.pp ++ "\n";
  top.location = loc(top.file, $1.line, $1.column);

  top.defs = [];
  
  top.errors := [];--TODO: add errors later

  top.syntaxAst = [syntaxFont(
                   fontName.lexeme, 
                   font(makeColor(toInt(r.lexeme),toInt(g.lexeme),toInt(b.lexeme)), 
                        fontStyles.isBold, 
                        fontStyles.isItalic)
		  )];

  forwards to emptyAGDcl();
}

-- isBold etc are from IdeSpec.sv
nonterminal FontStyles with isBold, isItalic, pp;

concrete production consFontStylesDcl
top::FontStyles ::= fontStyle::FontStyle fontStyles::FontStyles
{
  top.isBold = fontStyle.isBold || fontStyles.isBold;
  top.isItalic = fontStyle.isItalic || fontStyles.isItalic;

  top.pp = (if top.isBold then "<bold>" else "") ++ (if top.isItalic then "<italic>" else "");
}
concrete production nilFontStylesDcl
top::FontStyles ::= 
{
  top.isBold = false;
  top.isItalic = false;
}

nonterminal FontStyle with isBold, isItalic;

concrete production fontStyleBoldDcl
top::FontStyle ::= 'bold'
{
  top.isBold = true;
  top.isItalic = false;
}
concrete production fontStyleItalicDcl
top::FontStyle ::= 'italic'
{
  top.isBold = false;
  top.isItalic = true;
}

-- temp_imp_ide_font KeywordFont color(255,0,0) bold;

