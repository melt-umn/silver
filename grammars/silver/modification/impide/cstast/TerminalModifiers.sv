grammar silver:modification:impide:cstast;

--import (see grammar-wide import in cstast.sv)

synthesized attribute submitsNXML :: String;
synthesized attribute dominatesNXML :: String;
synthesized attribute lexerclassesNXML :: String;
attribute submitsNXML occurs on SyntaxTerminalModifier, SyntaxTerminalModifiers;
attribute dominatesNXML occurs on SyntaxTerminalModifier, SyntaxTerminalModifiers;
attribute lexerclassesNXML occurs on SyntaxTerminalModifier, SyntaxTerminalModifiers;

aspect production consTerminalMod
top::SyntaxTerminalModifiers ::= h::SyntaxTerminalModifier  t::SyntaxTerminalModifiers
{	
  top.dominatesNXML = h.dominatesNXML ++ t.dominatesNXML;
  top.submitsNXML = h.submitsNXML ++ t.submitsNXML;
  top.lexerclassesNXML = h.lexerclassesNXML ++ t.lexerclassesNXML;
}

aspect production nilTerminalMod
top::SyntaxTerminalModifiers ::= 
{
  top.dominatesNXML = "";
  top.submitsNXML = "";
  top.lexerclassesNXML = "";
}

aspect production termIgnore
top::SyntaxTerminalModifier ::=
{
  top.dominatesNXML = "";
  top.submitsNXML = "";
  top.lexerclassesNXML = "";
}

aspect production termPrecedence
top::SyntaxTerminalModifier ::= lvl::Integer
{
  top.dominatesNXML = "";
  top.submitsNXML = "";
  top.lexerclassesNXML = "";
}

aspect production termAssociation
top::SyntaxTerminalModifier ::= direction::String
{
  top.dominatesNXML = "";
  top.submitsNXML = "";
  top.lexerclassesNXML = "";
}

aspect production termClasses
top::SyntaxTerminalModifier ::= cls::[String]
{
  top.dominatesNXML = implode("\n\t\t\t\t\t", map(getclassDomContribsNXML, clsRefs));
  top.submitsNXML = implode("\n\t\t\t\t\t", map(getclassSubContribsNXML, clsRefs));
  top.lexerclassesNXML = makeCopperName(head(cls));
}

aspect production termSubmits
top::SyntaxTerminalModifier ::= sub::[String]
{
  top.dominatesNXML = "";
  top.submitsNXML = implode("\n\t\t\t\t\t", map(nxmlCopperElementRef, map(createPairForSyntaxDcl, map(head, subRefs))));
  top.lexerclassesNXML = "";
}

aspect production termDominates
top::SyntaxTerminalModifier ::= dom::[String]
{
  top.dominatesNXML = implode("\n\t\t\t\t\t", map(nxmlCopperElementRef, map(createPairForSyntaxDcl, map(head, domRefs))));
  top.submitsNXML = "";
  top.lexerclassesNXML = "";
}

aspect production termAction
top::SyntaxTerminalModifier ::= acode::String
{
  top.dominatesNXML = "";
  top.submitsNXML = "";
  top.lexerclassesNXML = "";
}

