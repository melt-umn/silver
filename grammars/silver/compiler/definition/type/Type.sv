grammar silver:compiler:definition:type;

option silver:compiler:modification:ffi; -- foreign types

synthesized attribute kindArity :: Integer;
synthesized attribute freeVariables :: [TyVar];
synthesized attribute boundVars :: [TyVar];
synthesized attribute contexts :: [Context];
synthesized attribute typerep :: Type;
synthesized attribute monoType :: Type; -- Raises on error when we encounter a polyType and didn't expect one

{--
 - Represents a type, quantified over some type variables.
 -}
nonterminal PolyType with boundVars, contexts, typerep, monoType;

abstract production monoType
top::PolyType ::= ty::Type
{
  top.boundVars = [];
  top.contexts = [];
  top.typerep = ty;
  top.monoType = ty;
}

abstract production polyType
top::PolyType ::= bound::[TyVar] ty::Type
{
  top.boundVars = freshTyVars(bound);
  top.contexts = [];
  top.typerep = freshenTypeWith(ty, bound, top.boundVars);
  top.monoType = error("Expected a mono type but found a poly type!");
}

abstract production constraintType
top::PolyType ::= bound::[TyVar] contexts::[Context] ty::Type
{
  top.boundVars = freshTyVars(bound);
  top.contexts = map(freshenContextWith(_, bound, top.boundVars), contexts);
  top.typerep = freshenTypeWith(ty, bound, top.boundVars);
  top.monoType = error("Expected a mono type but found a (constraint) poly type!");
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

{--
 - Silver Type Representations.
 -}
nonterminal Type with kindArity, freeVariables, tracked;
synthesized attribute tracked :: Boolean;

aspect default production
top::Type ::=
{
  top.tracked = false;
}

{--
 - This is a (universally quantified) type variable.
 -}
abstract production varType
top::Type ::= tv::TyVar
{
  top.kindArity = tv.kindArity;
  top.freeVariables = [tv];
}

{--
 - This is an (existentially quantified) type variable, i.e. skolem constant.
 - Type are pretty much (exists sks. forall tys. type)
 -}
abstract production skolemType
top::Type ::= tv::TyVar
{
  top.kindArity = tv.kindArity;
  top.freeVariables = [tv];
}

{--
 - Represents the application of a constructor type.
 -}
abstract production appType
top::Type ::= c::Type a::Type
{
  top.kindArity = if c.kindArity > 0 then c.kindArity - 1 else 0;
  top.freeVariables = setUnionTyVars(c.freeVariables, a.freeVariables);
  top.tracked = c.tracked;
}

{--
 - When an error message has **already** been reported, and we must supply a type,
 - and we wish to suppress further error messages, use errorType.
 -}
abstract production errorType
top::Type ::=
{
  top.kindArity = 0;
  top.freeVariables = [];
}

{--
 - Integer type.
 -}
abstract production intType
top::Type ::=
{
  top.kindArity = 0;
  top.freeVariables = [];
}

{--
 - Boolean type.
 -}
abstract production boolType
top::Type ::=
{
  top.kindArity = 0;
  top.freeVariables = [];
}

{--
 - Float type.
 -}
abstract production floatType
top::Type ::=
{
  top.kindArity = 0;
  top.freeVariables = [];
}

{--
 - String type.
 -}
abstract production stringType
top::Type ::=
{
  top.kindArity = 0;
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
  top.kindArity = 0;
  top.freeVariables = [];
}

{--
 - An (undecorated) nonterminal type.
 - Note that this is the *unapplied* type constructor for a nonterminal type;
 - e.g. `Pair<String Integer>` would be represented as
 - `apType(apType(nonterminalType("silver:core:Pair", 2, false), stringType()), integerType())`.
 -
 - @param fn  The fully qualified name of the nonterminal.
 - @param k  The number type parameters for that nonterminal.
 - @param tracked  Might this NT be tracked.
 -}
abstract production nonterminalType
top::Type ::= fn::String k::Integer tracked::Boolean
{
  top.kindArity = k;
  top.freeVariables = [];
  top.tracked = tracked;
}

{--
 - A terminal type.
 - @param fn  The fully qualified name of the terminal.
 -}
abstract production terminalType
top::Type ::= fn::String
{
  top.kindArity = 0;
  top.freeVariables = [];
}

{--
 - A *decorated* nonterminal type.
 - @param te  MUST be a 'nonterminalType' or 'varType'/'skolemType'
 -}
abstract production decoratedType
top::Type ::= te::Type
{
  top.kindArity = 0;
  top.freeVariables = te.freeVariables;
}

{--
 - An intermediate type. This *should* never appear as the type of a symbol,
 - etc. Rather, this is a helper type only used within expressions.
 -
 - It represents a nonterminal that is *either* decorated or undecorated
 - (e.g. when referencing a child) but has not yet been specialized.
 - @param nt  MUST be a 'nonterminalType'
 - @param hidden  One of: (a) a type variable (b) 'nt' (c) 'decoratedType(nt)'
 -                representing state: unspecialized, undecorated, or decorated.
 -}

-- This will ONLY appear in the types of expressions, nowhere else!
abstract production ntOrDecType
top::Type ::= nt::Type  hidden::Type
{
  top.freeVariables = case hidden of
                      | varType(_) -> nt.freeVariables
                      | _ -> hidden.freeVariables
                      end;
  
  -- If we never specialize, we're decorated.
  forwards to decoratedType(nt);
}

{--
 - Function type. (Whether production or function.)
 - Note that this is the *unapplied* type constructor for a nonterminal type,
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
  top.kindArity = params + length(namedParams) + 1;
  top.freeVariables = [];
}

--------------------------------------------------------------------------------

nonterminal TyVar with kindArity;

-- In essence, this should be 'private' to this file.
synthesized attribute extractTyVarRep :: Integer occurs on TyVar;

abstract production tyVar
top::TyVar ::= k::Integer i::Integer
{
  top.kindArity = k;
  top.extractTyVarRep = i;
}

function freshTyVar
TyVar ::= k::Integer
{
  return tyVar(k, genInt());
}

function freshType
Type ::=
{
  return varType(freshTyVar(0));
}

function newSkolemConstant
Type ::=
{
  return skolemType(freshTyVar(0));
}

-- TODO: Replace with propagated default instance
instance Eq TyVar {
  eq = \ tv1::TyVar tv2::TyVar -> tv1.kindArity == tv2.kindArity && tv1.extractTyVarRep == tv2.extractTyVarRep;
}

