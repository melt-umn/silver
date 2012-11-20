grammar silver:definition:env;

nonterminal NamedSignature with inputElements, outputElement, fullName, unparse, boundVariables, inputNames, inputTypes, typerep;
nonterminal NamedSignatureElement with typerep, elementName, unparse, boundVariables;

synthesized attribute elementName :: String;
synthesized attribute inputElements :: [NamedSignatureElement];
synthesized attribute outputElement :: NamedSignatureElement;

synthesized attribute inputNames :: [String];
-- inputTypes from the types grammar.

{--
 - Represents the signature of a function or production.
 -}
abstract production namedSignature
top::NamedSignature ::= fn::String ie::[NamedSignatureElement] oe::NamedSignatureElement
{
  top.unparse = "signature('" ++ fn ++ "', " ++ unparseSignatureElements(ie, top.boundVariables) ++ ", " ++ oe.unparse ++ ")";
  top.fullName = fn;
  top.inputElements = ie;
  top.outputElement = oe;
  top.inputNames = map((.elementName), ie);
  top.inputTypes = map((.typerep), ie);
  top.typerep = functionTypeExp(oe.typerep, top.inputTypes);
  
  oe.boundVariables = top.boundVariables;
}

{--
 - Represents the signature of something without parameters.
 - Used for action code. i.e. Stuff that uses ProductionStmt, but
 - isn't in a production/function.
 -}
abstract production namedNamedSignature
top::NamedSignature ::= fn::String
{
  forwards to namedSignature(fn, [], bogusNamedSignatureElement());
}

{--
 - Used ONLU when an error occurs. e.g. aspecting a non-existant production.
 -}
abstract production bogusNamedSignature
top::NamedSignature ::= 
{
  top.unparse = error("Bogus signatures should never make it into interface files!");
  top.fullName = "_NULL_";
  top.inputElements = [];
  top.outputElement = bogusNamedSignatureElement();
  top.inputNames = [];
  top.inputTypes = [];
  top.typerep = errorType();
}

------------------------
-- NamedSignatureElement

{--
 - Represents an element of the function/production signature.
 -}
abstract production namedSignatureElement
top::NamedSignatureElement ::= n::String ty::TypeExp
{
  top.unparse = "element('" ++ n ++ "', " ++ ty.unparse ++ ")";
  top.elementName = n;
  top.typerep = ty;

  ty.boundVariables = top.boundVariables; -- explicit to make sure it errors if we can't  
}

{--
 - A bogus output element, because an error occurred, or because
 - There is no output type.
 -}
abstract production bogusNamedSignatureElement
top::NamedSignatureElement ::=
{
  top.unparse = error("Bogus signature elements should never make it into interface files!");
  top.elementName = "__SV_BOGUS_ELEM";
  top.typerep = errorType();
}

