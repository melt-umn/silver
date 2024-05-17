grammar silver:compiler:definition:env;

{--
 - The fully named and fully type signature of a production (or function).
 - Includes full name of the production and names for all input and output elements (and annotations).
 -
 - TODO: we might want to remove the full name of the production from this, and make it just `Signature`?
 - It's not clear if this information really belongs here, or not.
 -}
data nonterminal NamedSignature with
  fullName, contexts, inputElements, outputElement, namedInputElements, freeVariables,
  inputNames, inputTypes, typeScheme, dclTypeScheme, typerep, freshenNamedSignature;

-- The type scheme for the signature as written, without sharing.
synthesized attribute dclTypeScheme :: PolyType;
-- typeScheme has all shared children changed to Decorated types.

synthesized attribute inputElements :: [NamedSignatureElement];
synthesized attribute outputElement :: NamedSignatureElement;
synthesized attribute namedInputElements :: [NamedSignatureElement];
synthesized attribute inputNames :: [String];
-- inputTypes comes from the types grammar.

-- "Freshens" all the signature's type variables with new skolem constants,
-- to avoid type vars from interface files clashing with new ones from genInt()
synthesized attribute freshenNamedSignature::NamedSignature;

@{-
 - Represents the signature of a production (or function).
 - @param fn   The full name
 - @param ctxs The type constraint contexts
 - @param ie   The input elements
 - @param oe   The output element
 - @param np   Named parameters (or annotations)
 -}
abstract production namedSignature
top::NamedSignature ::= fn::String ctxs::Contexts ie::NamedSignatureElements oe::NamedSignatureElement np::NamedSignatureElements
{
  top.fullName = fn;
  top.contexts = ctxs.contexts;
  top.inputElements = ie.elements;
  top.outputElement = new(oe);
  top.namedInputElements = np.elements;
  top.inputNames = ie.elementNames;
  top.inputTypes = ie.elementTypes; -- Does anything actually use this? TODO: eliminate?
  local typerep::Type = appTypes(functionType(length(ie.elements), np.elementShortNames), ie.elementTypes ++ np.elementTypes ++ [oe.typerep]);
  top.typeScheme = (if null(ctxs.contexts) then polyType else constraintType(_, ctxs.contexts, _))(top.freeVariables, new(typerep));
  nondecorated local dclType::Type = appTypes(functionType(length(ie.elements), np.elementShortNames), ie.elementDclTypes ++ np.elementDclTypes ++ [oe.elementDclType]);
  top.dclTypeScheme = (if null(ctxs.contexts) then polyType else constraintType(_, ctxs.contexts, _))(top.freeVariables, dclType);
  top.freeVariables = setUnionTyVars(ctxs.freeVariables, typerep.freeVariables);
  top.typerep = new(typerep); -- TODO: Only used by unifyNamedSignature.  Would be nice to eliminate, somehow.
  
  ctxs.boundVariables = top.freeVariables;
  ie.boundVariables = top.freeVariables;
  oe.boundVariables = top.freeVariables;
  np.boundVariables = top.freeVariables;

  top.freshenNamedSignature = namedSignature(fn, ctxs.flatRenamed, ie.flatRenamed, oe.flatRenamed, np.flatRenamed); 
  local freshSubst::Substitution = zipVarsAndTypesIntoSubstitution(top.freeVariables, map(skolemType, top.typeScheme.boundVars));
  ctxs.substitution = freshSubst;
  ie.substitution = freshSubst;
  oe.substitution = freshSubst;
  np.substitution = freshSubst;
}

@{-
 - Represents the signature of a global (or class member).
 - @param fn   The full name
 - @param ctxs The type constraint contexts
 - @param ty   The type of the global
 -}
abstract production globalSignature
top::NamedSignature ::= fn::String ctxs::Contexts ty::Type
{
  top.fullName = fn;
  top.contexts = ctxs.contexts;
  top.inputElements = error("Not a production or function");
  top.outputElement = error("Not a production or function");
  top.namedInputElements = error("Not a production or function");
  top.inputNames = error("Not a production or function");
  top.inputTypes = ty.inputTypes; -- Does anything actually use this? TODO: eliminate?
  top.typeScheme = (if null(ctxs.contexts) then polyType else constraintType(_, ctxs.contexts, _))(top.freeVariables, new(ty));
  top.dclTypeScheme = top.typeScheme;
  top.freeVariables = setUnionTyVars(ctxs.freeVariables, ty.freeVariables);
  top.typerep = new(ty);
  
  top.freshenNamedSignature = globalSignature(fn, ctxs.flatRenamed, ty.flatRenamed); 
  local freshSubst::Substitution = zipVarsAndTypesIntoSubstitution(top.freeVariables, map(skolemType, top.typeScheme.boundVars));
  ctxs.substitution = freshSubst;
  ty.substitution = freshSubst;
}

{--
 - Used when an error occurs. e.g. aspecting a non-existant production.
 - Or, in contexts that have no valid signature, which maybe we should do something about...
 -}
fun bogusNamedSignature NamedSignature ::= =
  namedSignature("_NULL_", nilContext(), nilNamedSignatureElement(), bogusNamedSignatureElement(), nilNamedSignatureElement());

{--
  - Represents a collection of NamedSignatureElements
  -}
nonterminal NamedSignatureElements with elements, elementNames, elementShortNames, elementTypes, elementDclTypes, freeVariables, boundVariables;
propagate boundVariables on NamedSignatureElements;

synthesized attribute elements::[NamedSignatureElement];
synthesized attribute elementNames::[String];
synthesized attribute elementShortNames::[String];
synthesized attribute elementTypes::[Type];
synthesized attribute elementDclTypes::[Type];

abstract production consNamedSignatureElement
top::NamedSignatureElements ::= h::NamedSignatureElement t::NamedSignatureElements
{
  top.elements = h :: t.elements;
  top.elementNames = h.elementName :: t.elementNames;
  top.elementShortNames = h.elementShortName :: t.elementShortNames;
  top.elementTypes = h.typerep :: t.elementTypes;
  top.elementDclTypes = h.elementDclType :: t.elementDclTypes;
  top.freeVariables = setUnionTyVars(h.freeVariables, t.freeVariables);
}

abstract production nilNamedSignatureElement
top::NamedSignatureElements ::=
{
  top.elements = [];
  top.elementNames = [];
  top.elementShortNames = [];
  top.elementTypes = [];
  top.elementDclTypes = [];
  top.freeVariables = [];
}

global foldNamedSignatureElements::(NamedSignatureElements ::= [NamedSignatureElement]) =
  foldr(consNamedSignatureElement, nilNamedSignatureElement(), _);

{--
 - Represents an elements of a signature, whether input, output, or annotation.
 -}
nonterminal NamedSignatureElement with elementName, elementShortName, elementShared, elementDclType, typerep, freeVariables, boundVariables;
propagate boundVariables on NamedSignatureElement;

synthesized attribute elementName :: String;
synthesized attribute elementDclType :: Type;
synthesized attribute elementShortName :: String;
synthesized attribute elementShared :: Boolean;

{--
 - Represents an element of the function/production signature.
 -}
abstract production namedSignatureElement
top::NamedSignatureElement ::= n::String ty::Type shared::Boolean
{
  top.elementName = n;
  top.elementDclType = new(ty);
  top.typerep = if shared then decoratedType(new(ty), inhSetType([])) else new(ty);
  top.elementShared = shared;
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
  forwards to namedSignatureElement("__SV_BOGUS_ELEM", errorType(), false);
}

----------------

function namedSignatureElementLte
Boolean ::= a::NamedSignatureElement  b::NamedSignatureElement
{
  return a.elementName <= b.elementName;
}

-- This is a big of an awful pile. Related to annotations, for now.
fun findNamedSigElem
Integer ::= s::String l::[NamedSignatureElement] z::Integer =
  if null(l) then -1
  else if s == head(l).elementName then z
  else findNamedSigElem(s, tail(l), z+1);

fun lookupSignatureInputElem
NamedSignatureElement ::= s::String ns::NamedSignature =
  case lookup(s, zip(ns.inputNames, ns.inputElements)) of
  | just(e) -> e
  | nothing() -> bogusNamedSignatureElement()
  end;

--------------

attribute substitution, flatRenamed occurs on Contexts, NamedSignatureElements, NamedSignatureElement;
propagate substitution, flatRenamed on Contexts, NamedSignatureElements, NamedSignatureElement;

function unifyNamedSignature
Substitution ::= ns1::NamedSignature ns2::NamedSignature
{
  local subst :: Substitution = unifyDirectional(ns1.typerep, ns2.typerep);
  return
    if !subst.failure then subst
    else errorSubstitution(ns1.typerep);
}

instance Eq NamedSignature {
  eq = \ ns1::NamedSignature ns2::NamedSignature ->
    ns1.fullName == ns2.fullName &&
    ns1.typeScheme == ns2.typeScheme;
}
