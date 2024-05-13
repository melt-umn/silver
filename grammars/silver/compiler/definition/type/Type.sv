grammar silver:compiler:definition:type;

option silver:compiler:modification:ffi; -- foreign types
option silver:compiler:modification:list; -- list type

imports silver:compiler:definition:env only NamedSignature, fullName, outputElement;

synthesized attribute kindrep :: Kind;
synthesized attribute freeVariables :: [TyVar];
synthesized attribute boundVars :: [TyVar];
synthesized attribute contexts :: [Context];
synthesized attribute typerep :: Type;
synthesized attribute monoType :: Type; -- Raises on error when we encounter a polyType and didn't expect one

{--
 - Represents a type, quantified over some type variables.
 -}
tracked nonterminal PolyType with boundVars, contexts, typerep, monoType;

flowtype PolyType = decorate {}, forward {};

abstract production monoType
top::PolyType ::= ty::Type
{
  top.boundVars = [];
  top.contexts = [];
  top.typerep = new(ty);
  top.monoType = new(ty);
}

abstract production polyType
top::PolyType ::= bound::[TyVar] ty::Type
{
  top.boundVars = freshTyVars(bound);
  top.contexts = [];
  top.typerep = freshenTypeWith(new(ty), bound, top.boundVars);
  top.monoType = error("Expected a mono type but found a poly type!");
}

abstract production constraintType
top::PolyType ::= bound::[TyVar] contexts::[Context] ty::Type
{
  top.boundVars = freshTyVars(bound);
  top.contexts = map(freshenContextWith(_, bound, top.boundVars), contexts);
  top.typerep = freshenTypeWith(new(ty), bound, top.boundVars);
  top.monoType = error("Expected a mono type but found a (constraint) poly type!");
}

{--
 - Represents a constraint on a type, e.g. a type class instance
 -}

tracked nonterminal Context with freeVariables;

abstract production instContext
top::Context ::= cls::String t::Type
{
  top.freeVariables = t.freeVariables;
}

abstract production inhOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), args) ++ [ntty.freeVariables]);
}

abstract production synOccursContext
top::Context ::= attr::String args::[Type] atty::Type inhs::Type ntty::Type
{
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), args) ++ [inhs.freeVariables, ntty.freeVariables]);
}

abstract production annoOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), args) ++ [ntty.freeVariables]);
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
tracked nonterminal Type with kindrep, freeVariables;

flowtype Type = decorate {}, forward {};

{--
 - This is a (universally quantified) type variable.
 -}
abstract production varType
top::Type ::= tv::TyVar
{
  top.kindrep = tv.kind;
  top.freeVariables = [tv];
}

{--
 - This is an (existentially quantified) type variable, i.e. skolem constant.
 - Type are pretty much (exists sks. forall tys. type)
 -}
abstract production skolemType
top::Type ::= tv::TyVar
{
  top.kindrep = tv.kind;
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
    | arrowKind(_, k) -> new(k)
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
 - A *decorated* nonterminal type.
 - Represents a reference with at least some set of provided inherited attributes,
 - cannot be decorated with additional attributes.
 - @param te  MUST be a 'nonterminalType' or 'varType'/'skolemType'
 - @param i  MUST have kind InhSet
 -}
abstract production decoratedType
top::Type ::= te::Type i::Type
{
  top.kindrep = starKind();
  top.freeVariables = setUnionTyVars(te.freeVariables, i.freeVariables);
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

abstract production dispatchType
top::Type ::= ns::NamedSignature
{
  top.kindrep = starKind();
  top.freeVariables = [];
}

--------------------------------------------------------------------------------

annotation varId :: Integer;
annotation kind :: Kind;

data TyVar = tyVar | tyVarNamed n::String
  with varId, kind;

instance Eq TyVar {
  -- Shouldn't need to compare kinds here, since all type vars have a unique id.
  eq = \ x::TyVar y::TyVar -> x.varId == y.varId; --&& x.kind == y.kind;
}

global freshTyVar::(TyVar ::= Kind) = \ k::Kind -> tyVar(kind=k, varId=genInt());
global freshTyVarNamed::(TyVar ::= String Kind) = \ n::String k::Kind -> tyVarNamed(n, kind=k, varId=genInt());

fun freshType Type ::= = varType(freshTyVar(starKind()));

fun newSkolemConstant Type ::= = skolemType(freshTyVar(starKind()));

fun freshInhSet Type ::= = varType(freshTyVar(inhSetKind()));
