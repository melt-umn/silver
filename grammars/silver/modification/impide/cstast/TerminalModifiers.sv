grammar silver:modification:impide:cstast;

--import (see grammar-wide import in cstast.sv)

synthesized attribute submitsNXML :: String;
synthesized attribute dominatesNXML :: String;
synthesized attribute lexerclassesNXML :: String;
synthesized attribute fontAttr :: String;
synthesized attribute fontAttrFromClass :: String;

attribute submitsNXML occurs on SyntaxTerminalModifier, SyntaxTerminalModifiers;
attribute dominatesNXML occurs on SyntaxTerminalModifier, SyntaxTerminalModifiers;
attribute lexerclassesNXML occurs on SyntaxTerminalModifier, SyntaxTerminalModifiers;
attribute fontAttr occurs on SyntaxTerminalModifier, SyntaxTerminalModifiers;
attribute fontAttrFromClass occurs on SyntaxTerminalModifier, SyntaxTerminalModifiers;

aspect production consTerminalMod
top::SyntaxTerminalModifiers ::= h::SyntaxTerminalModifier  t::SyntaxTerminalModifiers
{	
  top.dominatesNXML = h.dominatesNXML ++ t.dominatesNXML;
  top.submitsNXML = h.submitsNXML ++ t.submitsNXML;
  top.lexerclassesNXML = h.lexerclassesNXML ++ t.lexerclassesNXML;
  top.fontAttr = if (h.fontAttr != "") 
                  then h.fontAttr 
                  else t.fontAttr;--only the first non-zero font declaration is effective
  top.fontAttrFromClass = if h.fontAttrFromClass != "" then h.fontAttrFromClass else t.fontAttrFromClass;
}

aspect production nilTerminalMod
top::SyntaxTerminalModifiers ::= 
{
  top.dominatesNXML = "";
  top.submitsNXML = "";
  top.lexerclassesNXML = "";
  top.fontAttr = "";
  top.fontAttrFromClass = "";
}

aspect default production
top::SyntaxTerminalModifier ::=
{
  top.dominatesNXML = "";
  top.submitsNXML = "";
  top.lexerclassesNXML = "";
  top.fontAttr = "";
  top.fontAttrFromClass = "";
}

aspect production termClasses
top::SyntaxTerminalModifier ::= cls::[String]
{
  top.dominatesNXML = implode("\n\t\t\t\t\t", map(getclassDomContribsNXML, clsRefs));
  top.submitsNXML = implode("\n\t\t\t\t\t", map(getclassSubContribsNXML, clsRefs));
  top.lexerclassesNXML = makeCopperName(head(cls));
  top.fontAttrFromClass = dumbExtractFont(clsRefs);
  
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

aspect production termSubmits
top::SyntaxTerminalModifier ::= sub::[String]
{
  top.submitsNXML = implode("\n\t\t\t\t\t", map(nxmlCopperElementRef, map(createPairForSyntaxDcl, map(head, subRefs))));
}

aspect production termDominates
top::SyntaxTerminalModifier ::= dom::[String]
{
  top.dominatesNXML = implode("\n\t\t\t\t\t", map(nxmlCopperElementRef, map(createPairForSyntaxDcl, map(head, domRefs))));
}

-- terminal's font in IDE
abstract production termFont
top::SyntaxTerminalModifier ::= fontName::String
{
  top.cstErrors := [];
  top.unparses = ["font('" ++ fontName ++ "')"];

  top.fontAttr = fontName;
}
