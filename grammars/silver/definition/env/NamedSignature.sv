grammar silver:definition:env;

nonterminal NamedSignature with inputElements, outputElement, fullName, unparse;
nonterminal NamedSignatureElement with typerep, elementName, realName, fullName, unparse;
synthesized attribute realName :: String;
synthesized attribute elementName :: String;
synthesized attribute inputElements :: [Decorated NamedSignatureElement];
synthesized attribute outputElement :: Decorated NamedSignatureElement;


function getTypesSignature
[Decorated TypeRep] ::= ns::[Decorated NamedSignatureElement]{
 return if null(ns) then [] else [head(ns).typerep] ++ getTypesSignature(tail(ns));  
}

function getNamesSignature
[String] ::= ns::[Decorated NamedSignatureElement]{
 return if null(ns) then [] else [head(ns).elementName] ++ getNamesSignature(tail(ns));  
}

function getTypeNamesSignature
[String] ::= ns::[Decorated NamedSignatureElement]{
 return if null(ns) then [] else [head(ns).typerep.typeName] ++ getTypeNamesSignature(tail(ns));  
}

function getFullNamesSignature
[String] ::= ns::[Decorated NamedSignatureElement]{
 return if null(ns) then [] else [head(ns).fullName] ++ getFullNamesSignature(tail(ns));  
}

function getRealNamesSignature
[String] ::= ns::[Decorated NamedSignatureElement]{
 return if null(ns) then [] else [head(ns).fullName] ++ getFullNamesSignature(tail(ns));  
}

function unparseSignatureElements
String ::= s::[Decorated NamedSignatureElement]{
  return "[" ++ unparseSignatureElementsHelp(s) ++ "]";
}

function unparseSignatureElementsHelp
String ::= s::[Decorated NamedSignatureElement]{
  return if null(s) then "" else head(s).unparse ++ (if null(tail(s)) then "" else (", " ++ unparseSignatureElementsHelp(tail(s))));
}

function namedSignatureDcl
Decorated NamedSignature ::= fn::String ie::[Decorated NamedSignatureElement] oe::Decorated NamedSignatureElement{
  return decorate i_namedSignature(fn, ie, oe) with {};
}
abstract production i_namedSignature
top::NamedSignature ::= fn::String ie::[Decorated NamedSignatureElement] oe::Decorated NamedSignatureElement {
  top.unparse = "signature('" ++ fn ++ "', " ++ unparseSignatureElements(ie) ++ ", " ++ oe.unparse ++ ")";
  top.fullName = fn;
  top.inputElements = ie;
  top.outputElement = oe;
}

abstract production namedSignatureDefault
top::NamedSignature ::= {
  top.unparse = "signature";
  top.fullName = "_NULL_";
  top.inputElements = [];
  top.outputElement = decorate namedSignatureElementDefault() with {};
}

function namedSignatureElement
Decorated NamedSignatureElement ::= n::String rn::String fn::String tr::Decorated TypeRep{
  return decorate i_namedSignatureElement(n, rn, fn, tr) with {};
}

abstract production i_namedSignatureElement
top::NamedSignatureElement ::= n::String rn::String fn::String tr::Decorated TypeRep{
  top.unparse = "element('" ++ n ++ "', '" ++ rn ++ "', '" ++ fn ++ "', " ++ tr.unparse ++ ")";

  top.elementName = n;
  top.realName = rn;
  top.fullName = fn;
  top.typerep = tr;
}

abstract production namedSignatureElementDefault
top::NamedSignatureElement ::= {
  top.unparse = "element";
  top.elementName = "_NULL_";
  top.realName = "_NULL_";
  top.fullName = "_NULL_";
  top.typerep = topTypeRep();
}
