grammar silver:modification:impide:cstast;

--import (see grammar-wide import in cstast.sv)

synthesized attribute fontAttr :: String;
synthesized attribute fontAttrFromClass :: String;

attribute fontAttr occurs on SyntaxTerminalModifier, SyntaxTerminalModifiers;
attribute fontAttrFromClass occurs on SyntaxTerminalModifier, SyntaxTerminalModifiers;

aspect production consTerminalMod
top::SyntaxTerminalModifiers ::= h::SyntaxTerminalModifier  t::SyntaxTerminalModifiers
{
  -- only the first non-zero font declaration is effective
  top.fontAttr =
    if h.fontAttr != ""
    then h.fontAttr 
    else t.fontAttr;
  top.fontAttrFromClass =
    if h.fontAttrFromClass != ""
    then h.fontAttrFromClass
    else t.fontAttrFromClass;
}

aspect production nilTerminalMod
top::SyntaxTerminalModifiers ::= 
{
  top.fontAttr = "";
  top.fontAttrFromClass = "";
}

aspect default production
top::SyntaxTerminalModifier ::=
{
  top.fontAttr = "";
  top.fontAttrFromClass = "";
}

aspect production termClasses
top::SyntaxTerminalModifier ::= cls::[String]
{
  top.fontAttrFromClass = dumbExtractFont(allClsRefs);
  
}
function dumbExtractFont
String ::= d::[Decorated SyntaxDcl]
{
  return if null(d) then ""
  else case d of
       | syntaxLexerClass(_, mods)::rest -> if mods.fontAttr != "" then mods.fontAttr else dumbExtractFont(rest)
       | _ -> error("We should only have lexer classes here")
       end;
}

-- terminal's font in IDE
abstract production termFont
top::SyntaxTerminalModifier ::= fontName::String
{
  top.cstErrors := [];

  top.fontAttr = makeCopperName(fontName);
}

