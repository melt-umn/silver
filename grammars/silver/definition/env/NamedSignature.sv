grammar silver:definition:env;

import silver:definition:type;

nonterminal NamedSignature with inputElements, outputElement, fullName, unparse, boundVariables;
nonterminal NamedSignatureElement with typerep, elementName, unparse, boundVariables;

synthesized attribute elementName :: String;
synthesized attribute inputElements :: [Decorated NamedSignatureElement];
synthesized attribute outputElement :: Decorated NamedSignatureElement;


function getTypesSignature
[TypeExp] ::= ns::[Decorated NamedSignatureElement]
{
 return if null(ns) then [] else [head(ns).typerep] ++ getTypesSignature(tail(ns));  
}

function getNamesSignature
[String] ::= ns::[Decorated NamedSignatureElement]
{
 return if null(ns) then [] else [head(ns).elementName] ++ getNamesSignature(tail(ns));  
}



function namedNamedSignature
Decorated NamedSignature ::= fn::String
{
  return decorate i_namedSignature(fn, [], decorate namedSignatureElementDefault() with {}) with {};
}

function namedSignatureDcl
Decorated NamedSignature ::= fn::String ie::[Decorated NamedSignatureElement] oe::Decorated NamedSignatureElement
{
  return decorate i_namedSignature(fn, ie, oe) with {};
}

abstract production i_namedSignature
top::NamedSignature ::= fn::String ie::[Decorated NamedSignatureElement] oe::Decorated NamedSignatureElement
{
  top.unparse = "signature('" ++ fn ++ "', " ++ unparseSignatureElements(ie, top.boundVariables) ++ ", " ++ decorate new(oe) with {boundVariables = top.boundVariables;}.unparse ++ ")";
  top.fullName = fn;
  top.inputElements = ie;
  top.outputElement = oe;
}

abstract production namedSignatureDefault
top::NamedSignature ::= 
{
  top.unparse = "signature"; -- TODO: is this ever legal? can we do an internal error instead?
  top.fullName = "_NULL_";
  top.inputElements = [];
  top.outputElement = decorate namedSignatureElementDefault() with {};
}

function namedSignatureElement
Decorated NamedSignatureElement ::= n::String tr::TypeExp
{
  return decorate i_namedSignatureElement(n, tr) with {};
}

abstract production i_namedSignatureElement
top::NamedSignatureElement ::= n::String ty::TypeExp
{
  ty.boundVariables = top.boundVariables; -- explicit to make sure it errors if we can't  
  top.unparse = "element('" ++ n ++ "', " ++ ty.unparse ++ ")"; -- TODO @#$#$%

  top.elementName = n;
  top.typerep = ty;
}

abstract production namedSignatureElementDefault
top::NamedSignatureElement ::=
{
  top.unparse = "element"; -- TODO: can we do an internal error instead?
  top.elementName = "_NULL_";
  top.typerep = errorType();
}
