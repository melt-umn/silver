grammar silver:definition:type;

option silver:modification:ffi; -- foreign types

{--
 - Misnamed: Silver Type Representations.
 - (From Type you'd expect this is syntax, not representations.)
 -}
nonterminal Type with freeVariables;

synthesized attribute freeVariables :: [TyVar];

{--
 - This is a (universally quantified) type variable.
 -}
abstract production varType
top::Type ::= tv::TyVar
{
  top.freeVariables = [tv];
}

{--
 - This is an (existentially quantified) type variable, i.e. skolem constant.
 - Type are pretty much (exists sks. forall tys. type)
 -}
abstract production skolemType
top::Type ::= tv::TyVar
{
  top.freeVariables = [tv];
}

{--
 - Integer type.
 -}
abstract production intType
top::Type ::=
{
  top.freeVariables = [];
}

{--
 - Boolean type.
 -}
abstract production boolType
top::Type ::=
{
  top.freeVariables = [];
}

{--
 - Float type.
 -}
abstract production floatType
top::Type ::=
{
  top.freeVariables = [];
}

{--
 - String type.
 -}
abstract production stringType
top::Type ::=
{
  top.freeVariables = [];
}

{--
 - An (undecorated) nonterminal type.
 - @param fn  The fully qualified name of the nonterminal.
 - @param params  The type parameters for that nonterminal.
 -}
abstract production nonterminalType
top::Type ::= fn::String params::[Type]
{
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), params));
}

{--
 - A terminal type.
 - @param fn  The fully qualified name of the terminal.
 -}
abstract production terminalType
top::Type ::= fn::String
{
  top.freeVariables = [];
}

{--
 - A *decorated* nonterminal type.
 - @param te  MUST be a 'nonterminalType' (TODO: should probably just put that here)
 -}
abstract production decoratedType
top::Type ::= te::Type
{
  top.freeVariables = te.freeVariables;
}

{--
 - An intermediate type. This *should* never appear as the type of a symbol,
 - etc. Rather, this is a helper type only used withing expressions.
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
 - @param out  The result type of the function
 - @param params  The (ordered) input types of the function
 - @param namedParams  Named parameters for this nonterminal.
 -        NOTE: These must always be *IN SORTED ORDER*
 -}
abstract production functionType
top::Type ::= out::Type params::[Type] namedParams::[NamedArgType]
{
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), 
    out :: params ++ map((.argType), namedParams)));
}

--------------------------------------------------------------------------------

nonterminal NamedArgType with argName, argType, typepp, boundVariables;

synthesized attribute argName :: String;
synthesized attribute argType :: Type;

abstract production namedArgType
top::NamedArgType ::= s::String  ty::Type
{
  top.typepp = "; " ++ s ++ "::" ++ ty.typepp;
  top.argName = s;
  top.argType = ty;
}

function namedArgTypeLte
Boolean ::= a::NamedArgType  b::NamedArgType
{
  return a.argName <= b.argName;
}

function extractNamedArg
Pair<Maybe<NamedArgType> [NamedArgType]> ::= n::String  l::[NamedArgType]
{
  local recurse :: Pair<Maybe<NamedArgType> [NamedArgType]> =
    extractNamedArg(n, tail(l));

  return if null(l) then pair(nothing(), [])
  else if head(l).argName == n then pair(just(head(l)), tail(l))
  else pair(recurse.fst, head(l) :: recurse.snd);
}

function findNamedArgType
Integer ::= s::String l::[NamedArgType] z::Integer
{
  return if null(l) then -1
  else if s == head(l).argName then z
  else findNamedArgType(s, tail(l), z+1);
}

--------------------------------------------------------------------------------

nonterminal TyVar ;

-- In essence, this should be 'private' to this file.
synthesized attribute extractTyVarRep :: Integer occurs on TyVar;

abstract production tyVar
top::TyVar ::= i::Integer
{
  top.extractTyVarRep = i;
}

function freshTyVar
TyVar ::=
{
  return tyVar(genInt());
}

function tyVarEqual
Boolean ::= tv1::TyVar tv2::TyVar
{
  return tv1.extractTyVarRep == tv2.extractTyVarRep;
}

function errorType
Type ::=
{
  return varType(freshTyVar());
}

function freshType
Type ::=
{
  return varType(freshTyVar());
}

function newSkolemConstant
Type ::=
{
  return skolemType(freshTyVar());
}

