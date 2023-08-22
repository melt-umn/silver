grammar silver:compiler:definition:type;

option silver:compiler:modification:ffi; -- foreign types
option silver:compiler:modification:list; -- list type

synthesized attribute kindrep :: Kind;
synthesized attribute freeVariables :: [TyVar];
synthesized attribute boundVars :: [TyVar];
synthesized attribute contexts :: [Context];
synthesized attribute typerep :: Type;
synthesized attribute monoType :: Type; -- Raises on error when we encounter a polyType and didn't expect one

{--
 - Represents a type, quantified over some type variables.
 -}
nonterminal PolyType with boundVars, contexts, typerep, monoType, kindrep;

flowtype PolyType = decorate {}, forward {};

abstract production monoType
top::PolyType ::= ty::Type
{
  top.boundVars = [];
  top.contexts = [];
  top.typerep = ty;
  top.monoType = ty;
  top.kindrep = ty.kindrep;
}

abstract production polyType
top::PolyType ::= bound::[TyVar] ty::Type
{
  top.boundVars = freshTyVars(bound);
  top.contexts = [];
  top.typerep = freshenTypeWith(ty, bound, top.boundVars);
  top.monoType = error("Expected a mono type but found a poly type!");
  top.kindrep = ty.kindrep;
}

abstract production constraintType
top::PolyType ::= bound::[TyVar] contexts::[Context] ty::Type
{
  top.boundVars = freshTyVars(bound);
  top.contexts = map(freshenContextWith(_, bound, top.boundVars), contexts);
  top.typerep = freshenTypeWith(ty, bound, top.boundVars);
  top.monoType = error("Expected a mono type but found a (constraint) poly type!");
  top.kindrep = ty.kindrep;
}

{--
 - Represents a constraint on a type, e.g. a type class instance
 -}

nonterminal Context with freeVariables;

abstract production instContext
top::Context ::= cls::String t::Type
{
  top.freeVariables = t.freeVariables;
}

abstract production inhOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), args ++ [ntty]));
}

abstract production synOccursContext
top::Context ::= attr::String args::[Type] atty::Type inhs::Type ntty::Type
{
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), args ++ [inhs, ntty]));
}

abstract production annoOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), args ++ [ntty]));
}

abstract production typeableContext
top::Context ::= t::Type
{
  top.freeVariables = t.freeVariables;
}

abstract production inhSubsetContext
top::Context ::= i1::Type i2::Type
{
  top.freeVariables = setUnionTyVars(i1.freeVariables, i2.freeVariables);
}

abstract production typeErrorContext
top::Context ::= msg::String
{
  top.freeVariables = [];
}

{--
 - Silver Type Representations.
 -}
nonterminal Type with kindrep, freeVariables;

flowtype Type = decorate {}, forward {};

{--
 - This is a (universally quantified) type variable.
 -}
abstract production varType
top::Type ::= tv::TyVar
{
  top.kindrep = tv.kindrep;
  top.freeVariables = [tv];
}

{--
 - This is an (existentially quantified) type variable, i.e. skolem constant.
 - Type are pretty much (exists sks. forall tys. type)
 -}
abstract production skolemType
top::Type ::= tv::TyVar
{
  top.kindrep = tv.kindrep;
  top.freeVariables = [tv];
}

{--
 - Represents the application of a constructor type.
 -}
abstract production appType
top::Type ::= c::Type a::Type
{
  top.kindrep =
    case c.kindrep of
    | arrowKind(_, k) -> k
    | _ -> starKind()
    end;
  top.freeVariables = setUnionTyVars(c.freeVariables, a.freeVariables);
}

{--
 - When an error message has **already** been reported, and we must supply a type,
 - and we wish to suppress further error messages, use errorType.
 -}
abstract production errorType
top::Type ::=
{
  top.kindrep = starKind();
  top.freeVariables = [];
}

{--
 - Integer type.
 -}
abstract production intType
top::Type ::=
{
  top.kindrep = starKind();
  top.freeVariables = [];
}

{--
 - Boolean type.
 -}
abstract production boolType
top::Type ::=
{
  top.kindrep = starKind();
  top.freeVariables = [];
}

{--
 - Float type.
 -}
abstract production floatType
top::Type ::=
{
  top.kindrep = starKind();
  top.freeVariables = [];
}

{--
 - String type.
 -}
abstract production stringType
top::Type ::=
{
  top.kindrep = starKind();
  top.freeVariables = [];
}

{--
 - Terminal identifier type.
 - This isn't a foreign type, since we want equality checking.
 - TODO: Revisit this once we have type classes.
 -}
abstract production terminalIdType
top::Type ::=
{
  top.kindrep = starKind();
  top.freeVariables = [];
}

{--
 - An (undecorated) nonterminal type.
 - Note that this is the *unapplied* type constructor for a nonterminal type;
 - e.g. `Pair<String Integer>` would be represented as
 - `apType(apType(nonterminalType("silver:core:Pair", [starKind(), starKind()], true, false), stringType()), integerType())`.
 -
 - @param fn  The fully qualified name of the nonterminal.
 - @param k  The number type parameters for that nonterminal.
 - @param data  Is this a data nonterminal.
 - @param tracked  Is this NT tracked.
 -}
abstract production nonterminalType
top::Type ::= fn::String ks::[Kind] data::Boolean tracked::Boolean
{
  top.kindrep = foldr(arrowKind, starKind(), ks);
  top.freeVariables = [];
}

{--
 - A terminal type.
 - @param fn  The fully qualified name of the terminal.
 -}
abstract production terminalType
top::Type ::= fn::String
{
  top.kindrep = starKind();
  top.freeVariables = [];
}

{--
 - A type tagging a reference that is unique.
 -}
abstract production uniqueType
top::Type ::=
{
  top.kindrep = uniquenessKind();
  top.freeVariables = [];
}

{--
 - A type tagging a reference that is not unique.
 -}
abstract production nonUniqueType
top::Type ::=
{
  top.kindrep = uniquenessKind();
  top.freeVariables = [];
}

{--
 - A type-level inherited attribute set.
 - @param inhs  The (sorted) list of fully-qualified inherited attribute names. 
 -}
abstract production inhSetType
top::Type ::= inhs::[String]
{
  top.kindrep = inhSetKind();
  top.freeVariables = [];
}

{--
 - The type constructor for *decorated* nonterminal types.
 - Represents a reference with at least some set of provided inherited attributes,
 - cannot be decorated with additional attributes.
 - This has kind Uniqueness -> InhSet -> * -> *; it expects a uniqueness, reference set,
 - and nonterminal type.
 -}
abstract production decoratedType
top::Type ::=
{
  top.kindrep = arrowKind(uniquenessKind(), arrowKind(inhSetKind(), arrowKind(starKind(), starKind())));
  top.freeVariables = [];
}

{--
 - Function type. (Whether production or function.)
 - Note that this is the *unapplied* type constructor for a function type,
 - and argument types are provided before the result type;
 - e.g. `(Integer ::= String Boolean)` would be represented as
 - `apType(apType(apType(functionType(3, []), stringType()), booleanType()), integerType())`.
 -
 - @param params  The number input types of the function
 - @param namedParams  The names of named parameters for this function.
 -        NOTE: These must always be *IN SORTED ORDER*
 -}
abstract production functionType
top::Type ::= params::Integer namedParams::[String]
{
  top.kindrep = constructorKind(params + length(namedParams) + 1);
  top.freeVariables = [];
}

--------------------------------------------------------------------------------

nonterminal TyVar with kindrep, compareTo, isEqual;
propagate compareTo, isEqual on TyVar;

-- In essence, this should be 'private' to this file.
synthesized attribute extractTyVarRep :: Integer occurs on TyVar;

abstract production tyVar
top::TyVar ::= k::Kind i::Integer
{
  top.kindrep = k;
  top.extractTyVarRep = i;
}

abstract production tyVarNamed
top::TyVar ::= k::Kind i::Integer n::String
{
  forwards to tyVar(k, i);
}

function freshTyVar
TyVar ::= k::Kind
{
  return tyVar(k, genInt());
}

function freshTyVarNamed
TyVar ::= k::Kind n::String
{
  return tyVarNamed(k, genInt(), n);
}

function freshType
Type ::=
{
  return varType(freshTyVar(starKind()));
}

function newSkolemConstant
Type ::=
{
  return skolemType(freshTyVar(starKind()));
}

function freshInhSet
Type ::=
{
  return varType(freshTyVar(inhSetKind()));
}

function makeDecoratedType
Type ::= u::Type i::Type nt::Type
{
  return appType(appType(appType(decoratedType(), u), i), nt);
}

function freshDecoratedType
Type ::= nt::Type
{
  return makeDecoratedType(varType(freshTyVar(uniquenessKind())), freshInhSet(), nt);
}
