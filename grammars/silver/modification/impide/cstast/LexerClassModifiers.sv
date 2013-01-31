grammar silver:modification:impide:cstast;

--import (see grammar-wide import in cstast.sv)

-- from TerminalModifiers
attribute fontAttr occurs on SyntaxLexerClassModifier, SyntaxLexerClassModifiers;

aspect production consLexerClassMod
top::SyntaxLexerClassModifiers ::= h::SyntaxLexerClassModifier  t::SyntaxLexerClassModifiers
{	
  top.fontAttr = if (h.fontAttr != "") 
                  then h.fontAttr 
                  else t.fontAttr;--only the first non-zero font declaration is effective
}

aspect production nilLexerClassMod
top::SyntaxLexerClassModifiers ::= 
{
  top.fontAttr = "";
}

aspect default production
top::SyntaxLexerClassModifier ::=
{
  top.fontAttr = "";
}

abstract production lexerClassFont
top::SyntaxLexerClassModifier ::= fontName::String
{
  top.cstErrors := [];
  top.unparses = ["font('" ++ fontName ++ "')"];

  top.fontAttr = makeCopperName(fontName);
}

