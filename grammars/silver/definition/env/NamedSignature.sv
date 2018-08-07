grammar silver:definition:env;

nonterminal NamedSignature with inputElements, outputElement, fullName, inputNames, inputTypes, typerep, namedInputElements;
nonterminal NamedSignatureElement with typerep, elementName, toNamedArgType;

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
  top.typerep = functionType(oe.typerep, top.inputTypes, map((.toNamedArgType), np));
}

{--
 - Used when an error occurs. e.g. aspecting a non-existant production.
 - Or, in contexts that have no valid signature, which maybe we should do something about...
 -}
abstract production bogusNamedSignature
top::NamedSignature ::= 
{
  forwards to namedSignature("_NULL_", [], bogusNamedSignatureElement(), []);
}

------------------------
-- NamedSignatureElement

{--
 - Represents an element of the function/production signature.
 -}
abstract production namedSignatureElement
top::NamedSignatureElement ::= n::String ty::Type
{
  top.elementName = n;
  top.typerep = ty;

  -- When we convert from a SignatureElement to a NamedArg, we cut down to the short name only:
  local annoShortName :: String =
    substring(lastIndexOf(":", n) + 1, length(n), n);
  
  top.toNamedArgType = namedArgType(annoShortName, ty);
}

{--
 - A bogus output element, because an error occurred, or because
 - There is no output type.
 -}
abstract production bogusNamedSignatureElement
top::NamedSignatureElement ::=
{
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

--------------

-- Applies function to constituent types
function mapNamedSignature
NamedSignature ::= f::(Type ::= Type)  ns::NamedSignature
{
  return case ns of
  | namedSignature(fn, ie, oe, np) ->
      namedSignature(fn, map(mapNamedSignatureElement(f, _), ie), mapNamedSignatureElement(f, oe), map(mapNamedSignatureElement(f, _), np))
  end;
}
-- Ditto, for elements
function mapNamedSignatureElement
NamedSignatureElement ::= f::(Type ::= Type)  nse::NamedSignatureElement
{
  return case nse of
  | namedSignatureElement(n, ty) ->
      namedSignatureElement(n, f(ty))
  end;
}

-- Freshens all the signature's types
function freshenNamedSignature
NamedSignature ::= ns::NamedSignature
{
  local fvs :: [TyVar] = ns.typerep.freeVariables;
  local s :: Substitution = zipVarsIntoSubstitution(fvs, freshTyVars(length(fvs)));

  -- Apply the freshening within the signature's types
  return mapNamedSignature(performRenaming(_, s), ns);
}

