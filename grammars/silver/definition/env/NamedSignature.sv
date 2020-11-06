grammar silver:definition:env;

{--
 - The fully named and fully type signature of a production (or function).
 - Includes full name of the production and names for all input and output elements (and annotations).
 -
 - TODO: we might want to remove the full name of the production from this, and make it just `Signature`?
 - It's not clear if this information really belongs here, or not.
 -}
nonterminal NamedSignature with fullName, inputElements, outputElement, namedInputElements, typerep, inputNames, inputTypes;

synthesized attribute inputElements :: [NamedSignatureElement];
synthesized attribute outputElement :: NamedSignatureElement;
synthesized attribute namedInputElements :: [NamedSignatureElement];
synthesized attribute inputNames :: [String];
-- inputTypes comes from the types grammar.

{--
 - Represents the signature of a production (or function).
 - @param fn  The full name
 - @param ie  The input elements
 - @param oe  The output element
 - @param np  Named parameters (or annotations)
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

{--
 - Represents an elements of a signature, whether input, output, or annotation.
 -}
nonterminal NamedSignatureElement with elementName, typerep, toNamedArgType;

synthesized attribute elementName :: String;
synthesized attribute toNamedArgType :: NamedArgType;

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

