grammar silver:definition:env;

nonterminal NamedSignature with inputElements, outputElement, fullName, unparse, boundVariables, inputNames, inputTypes, typerep, namedInputElements;
nonterminal NamedSignatureElement with typerep, elementName, unparse, boundVariables, toNamedArgType;

synthesized attribute inputElements :: [NamedSignatureElement];
synthesized attribute outputElement :: NamedSignatureElement;
synthesized attribute namedInputElements :: [NamedSignatureElement];
synthesized attribute inputNames :: [String];

synthesized attribute elementName :: String;
synthesized attribute toNamedArgType :: NamedArgType;


-- inputTypes from the types grammar.

-- TODO Make named signatures... not named.
-- It seems to be largely redundant information.
-- The name portion can be moved into 'context' instead of 'signature'

{--
 - Represents the signature of a function or production.
 - @param fn  The full name
 - @param ie  The input elements
 - @param oe  The output element
 - @param np  Named parameters that are part of the signature.
 -}
abstract production namedSignature
top::NamedSignature ::= fn::String ie::[NamedSignatureElement] oe::NamedSignatureElement np::[NamedSignatureElement]
{
  top.fullName = fn;
  top.inputElements = ie;
  top.outputElement = oe;
  top.namedInputElements = np;
  top.inputNames = map((.elementName), ie);
  top.inputTypes = map((.typerep), ie); -- Does anything actually use this? TODO: eliminate?
  top.typerep = functionTypeExp(oe.typerep, top.inputTypes, map((.toNamedArgType), np));
  
  oe.boundVariables = top.boundVariables;
  top.unparse = "signature('" ++ fn ++ "', " ++ unparseSignatureElements(ie, top.boundVariables) ++ ", " ++ oe.unparse ++ ", " ++ unparseSignatureElements(np, top.boundVariables) ++ ")";
}

{--
 - Represents the signature of something without parameters.
 - Used for action code. i.e. Stuff that uses ProductionStmt, but
 - isn't in a production/function.
 -}
abstract production namedNamedSignature
top::NamedSignature ::= fn::String
{
  top.unparse = error("Bogus signatures should never make it into interface files!");
  forwards to namedSignature(fn, [], bogusNamedSignatureElement(), []);
}

{--
 - Used ONLY when an error occurs. e.g. aspecting a non-existant production.
 -}
abstract production bogusNamedSignature
top::NamedSignature ::= 
{
  top.unparse = error("Bogus signatures should never make it into interface files!");
  forwards to namedSignature("_NULL_", [], bogusNamedSignatureElement(), []);
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

  -- When we convert from a SignatureElement to a NamedArg, we cut down to the short name only:
  local annoShortName :: String =
    substring(lastIndexOf(":", n) + 1, length(n), n);
  
  top.toNamedArgType = namedArgType(annoShortName, ty);

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
  forwards to namedSignatureElement("__SV_BOGUS_ELEM", errorType());
}

----------------

function namedSignatureElementLte
Boolean ::= a::NamedSignatureElement  b::NamedSignatureElement
{
  return a.elementName <= b.elementName;
}

-- This is a big of an awful pile. Related to annotations, for now.
function findNamedSigElem
Integer ::= s::String l::[NamedSignatureElement] z::Integer
{
  return if null(l) then -1
  else if s == head(l).elementName then z
  else findNamedSigElem(s, tail(l), z+1);
}

