grammar silver:definition:type;

option silver:modification:ffi; -- foreign types

nonterminal TypeExp with freeVariables;

synthesized attribute freeVariables :: [TyVar];

{--
 - This is a (universally quantified) type variable.
 -}
abstract production varTypeExp
top::TypeExp ::= tv::TyVar
{
  top.freeVariables = [tv];
}

{--
 - This is an (existentially quantified) type variable, i.e. skolem constant.
 - Type are pretty much (exists sks. forall tys. type)
 -}
abstract production skolemTypeExp
top::TypeExp ::= tv::TyVar
{
  top.freeVariables = [tv];
}

{--
 - Integer type.
 -}
abstract production intTypeExp
top::TypeExp ::=
{
  top.freeVariables = [];
}

{--
 - Boolean type.
 -}
abstract production boolTypeExp
top::TypeExp ::=
{
  top.freeVariables = [];
}

{--
 - Float type.
 -}
abstract production floatTypeExp
top::TypeExp ::=
{
  top.freeVariables = [];
}

{--
 - String type.
 -}
abstract production stringTypeExp
top::TypeExp ::=
{
  top.freeVariables = [];
}

{--
 - An (undecorated) nonterminal type.
 - @param fn  The fully qualified name of the nonterminal.
 - @param params  The type parameters for that nonterminal.
 -}
abstract production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), params));
}

{--
 - A terminal type.
 - @param fn  The fully qualified name of the terminal.
 -}
abstract production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.freeVariables = [];
}

{--
 - A *decorated* nonterminal type.
 - @param te  MUST be a 'nonterminalTypeExp' (TODO: should probably just put that here)
 -}
abstract production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.freeVariables = te.freeVariables;
}

{--
 - An intermediate type. This *should* never appear as the type of a symbol,
 - etc. Rather, this is a helper type only used withing expressions.
 -
 - It represents a nonterminal that is *either* decorated or undecorated
 - (e.g. when referencing a child) but has not yet been specialized.
 - @param nt  MUST be a 'nonterminalTypeExp'
 - @param hidden  One of: (a) a type variable (b) 'nt' (c) 'decoratedTypeExp(nt)'
 -                representing state: unspecialized, undecorated, or decorated.
 -}

-- This will ONLY appear in the types of expressions, nowhere else!
abstract production ntOrDecTypeExp
top::TypeExp ::= nt::TypeExp  hidden::TypeExp
{
  top.freeVariables = case hidden of
                      | varTypeExp(_) -> nt.freeVariables
                      | _ -> hidden.freeVariables
                      end;
  
  -- If we never specialize, we're decorated.
  forwards to decoratedTypeExp(nt);
}

{--
 - Function type. (Whether production or function.)
 - @param out  The result type of the function
 - @param params  The (ordered) input types of the function
 - @param namedParams  Named parameters for this nonterminal.
 -        NOTE: These must always be *IN SORTED ORDER*
 -}
abstract production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp] namedParams::[NamedArgType]
{
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), 
    out :: params ++ map((.argType), namedParams)));
}

--------------------------------------------------------------------------------

nonterminal NamedArgType with argName, argType, typepp, boundVariables;

synthesized attribute argName :: String;
synthesized attribute argType :: TypeExp;

abstract production namedArgType
top::NamedArgType ::= s::String  ty::TypeExp
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
TypeExp ::=
{
  return varTypeExp(freshTyVar());
}

function freshType
TypeExp ::=
{
  return varTypeExp(freshTyVar());
}

function newSkolemConstant
TypeExp ::=
{
  return skolemTypeExp(freshTyVar());
}

