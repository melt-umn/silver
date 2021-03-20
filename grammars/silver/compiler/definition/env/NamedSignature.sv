grammar silver:compiler:definition:env;

{--
 - The fully named and fully type signature of a production (or function).
 - Includes full name of the production and names for all input and output elements (and annotations).
 -
 - TODO: we might want to remove the full name of the production from this, and make it just `Signature`?
 - It's not clear if this information really belongs here, or not.
 -}
nonterminal NamedSignature with fullName, contexts, inputElements, outputElement, namedInputElements, typeScheme, freeVariables, inputNames, inputTypes, typerep;

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
top::NamedSignature ::= fn::String ctxs::[Context] ie::[NamedSignatureElement] oe::NamedSignatureElement np::[NamedSignatureElement]
{
  top.fullName = fn;
  top.contexts = ctxs;
  top.inputElements = ie;
  top.outputElement = oe;
  top.namedInputElements = np;
  top.inputNames = map((.elementName), ie);
  top.inputTypes = map((.typerep), ie); -- Does anything actually use this? TODO: eliminate?
  local typerep::Type = appTypes(functionType(length(ie), map((.elementShortName), np)), top.inputTypes ++ map((.typerep), np) ++ [oe.typerep]);
  top.typeScheme = (if null(ctxs) then polyType else constraintType(_, ctxs, _))(top.freeVariables, typerep);
  top.freeVariables = setUnionTyVarsAll(typerep.freeVariables :: map((.freeVariables), ctxs));
  top.typerep = typerep; -- TODO: Only used by unifyNamedSignature.  Would be nice to eliminate, somehow.
}

{--
 - Used when an error occurs. e.g. aspecting a non-existant production.
 - Or, in contexts that have no valid signature, which maybe we should do something about...
 -}
abstract production bogusNamedSignature
top::NamedSignature ::= 
{
  forwards to namedSignature("_NULL_", [], [], bogusNamedSignatureElement(), []);
}

{--
 - Represents an elements of a signature, whether input, output, or annotation.
 -}
nonterminal NamedSignatureElement with elementName, typerep, elementShortName;

synthesized attribute elementName :: String;
synthesized attribute elementShortName :: String;

{--
 - Represents an element of the function/production signature.
 -}
abstract production namedSignatureElement
top::NamedSignatureElement ::= n::String ty::Type
{
  top.elementName = n;
  top.typerep = ty;

  -- When we convert from a SignatureElement to a functionType, we cut down to the short name only:
  top.elementShortName = 
    substring(lastIndexOf(":", n) + 1, length(n), n);
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
  | namedSignature(fn, ctxs, ie, oe, np) ->
      namedSignature(fn, ctxs, map(mapNamedSignatureElement(f, _), ie), mapNamedSignatureElement(f, oe), map(mapNamedSignatureElement(f, _), np))
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
  local s :: Substitution = zipVarsIntoSubstitution(ns.freeVariables, ns.typeScheme.boundVars);

  -- Apply the freshening within the signature's types
  return mapNamedSignature(performRenaming(_, s), ns);
}

function unifyNamedSignature
Substitution ::= ns1::NamedSignature ns2::NamedSignature
{
  local subst :: Substitution = unifyDirectional(ns1.typerep, ns2.typerep);
  return
    if !subst.failure then subst
    else errorSubstitution(ns1.typerep);
}

