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
top::NamedSignature ::= fn::String ctxs::Contexts ie::NamedSignatureElements oe::NamedSignatureElement np::NamedSignatureElements
{
  top.fullName = fn;
  top.contexts = ctxs.contexts;
  top.inputElements = ie.elements;
  top.outputElement = oe;
  top.namedInputElements = np.elements;
  top.inputNames = ie.elementNames;
  top.inputTypes = ie.elementTypes; -- Does anything actually use this? TODO: eliminate?
  local typerep::Type = appTypes(functionType(length(ie.elements), np.elementShortNames), ie.elementTypes ++ np.elementTypes ++ [oe.typerep]);
  top.typeScheme = (if null(ctxs.contexts) then polyType else constraintType(_, ctxs.contexts, _))(top.freeVariables, typerep);
  top.freeVariables = setUnionTyVars(ctxs.freeVariables, typerep.freeVariables);
  top.typerep = typerep; -- TODO: Only used by unifyNamedSignature.  Would be nice to eliminate, somehow.
  
  ctxs.boundVariables = top.freeVariables;
  ie.boundVariables = top.freeVariables;
  oe.boundVariables = top.freeVariables;
  np.boundVariables = top.freeVariables;
}

{--
 - Used when an error occurs. e.g. aspecting a non-existant production.
 - Or, in contexts that have no valid signature, which maybe we should do something about...
 -}
abstract production bogusNamedSignature
top::NamedSignature ::= 
{
  forwards to namedSignature("_NULL_", nilContext(), nilNamedSignatureElement(), bogusNamedSignatureElement(), nilNamedSignatureElement());
}

{--
 - Represents a collection of NamedSignatureElements
 -}
nonterminal NamedSignatureElements with elements, elementNames, elementShortNames, elementTypes, freeVariables, boundVariables;

synthesized attribute elements::[NamedSignatureElement];
synthesized attribute elementNames::[String];
synthesized attribute elementShortNames::[String];
synthesized attribute elementTypes::[Type];

abstract production consNamedSignatureElement
top::NamedSignatureElements ::= h::NamedSignatureElement t::NamedSignatureElements
{
  top.elements = h :: t.elements;
  top.elementNames = h.elementName :: t.elementNames;
  top.elementShortNames = h.elementShortName :: t.elementShortNames;
  top.elementTypes = h.typerep :: t.elementTypes;
  top.freeVariables = setUnionTyVars(h.freeVariables, t.freeVariables);
}

abstract production nilNamedSignatureElement
top::NamedSignatureElements ::=
{
  top.elements = [];
  top.elementNames = [];
  top.elementShortNames = [];
  top.elementTypes = [];
  top.freeVariables = [];
}

global foldNamedSignatureElements::(NamedSignatureElements ::= [NamedSignatureElement]) =
  foldr(consNamedSignatureElement, nilNamedSignatureElement(), _);

{--
 - Represents an elements of a signature, whether input, output, or annotation.
 -}
nonterminal NamedSignatureElement with elementName, elementShortName, typerep, freeVariables, boundVariables;

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
  top.freeVariables = ty.freeVariables;

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

function findNamedSigElemType
Type ::= n::String l::[NamedSignatureElement]
{
  local elems::NamedSignatureElements = foldNamedSignatureElements(l);
  return fromMaybe(errorType(), lookup(n, zipWith(pair, elems.elementNames, elems.elementTypes)));
}

--------------

attribute substitution, flatRenamed occurs on NamedSignature, Contexts, NamedSignatureElements, NamedSignatureElement;
propagate flatRenamed on NamedSignature, Contexts, NamedSignatureElements, NamedSignatureElement;

-- "Freshens" all the signature's type variables with new skolem constants,
-- to avoid type vars from interface files clashing with new ones from genInt()
function freshenNamedSignature
NamedSignature ::= ns::NamedSignature
{
  ns.substitution = zipVarsAndTypesIntoSubstitution(ns.freeVariables, map(skolemType, ns.typeScheme.boundVars));
  return ns.flatRenamed;
}

function unifyNamedSignature
Substitution ::= ns1::NamedSignature ns2::NamedSignature
{
  local subst :: Substitution = unifyDirectional(ns1.typerep, ns2.typerep);
  return
    if !subst.failure then subst
    else errorSubstitution(ns1.typerep);
}

