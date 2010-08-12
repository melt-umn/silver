grammar silver:definition:type;

nonterminal TypeExp with freeVariables;
nonterminal TyVar ;

synthesized attribute freeVariables :: [TyVar];

--------------------------------------------------------------------------------
abstract production tyVar
top::TyVar ::= i::Integer
{
}

function freshTyVar
TyVar ::=
{
  return tyVar(genInt());
}

function tyVarEqual
Boolean ::= tv1::TyVar tv2::TyVar
{
  local attribute ti1::Integer;
  ti1 = case tv1 of tyVar(i) -> i end;
  
  local attribute ti2::Integer;
  ti2 = case tv2 of tyVar(j) -> j end;
  
  return ti1 == ti2;
}
--------------------------------------------------------------------------------

abstract production varTypeExp
top::TypeExp ::= tv::TyVar
{
  top.freeVariables = [tv];
}

abstract production intTypeExp
top::TypeExp ::=
{
  top.freeVariables = [];
}

abstract production boolTypeExp
top::TypeExp ::=
{
  top.freeVariables = [];
}

abstract production floatTypeExp
top::TypeExp ::=
{
  top.freeVariables = [];
}

abstract production stringTypeExp
top::TypeExp ::=
{
  top.freeVariables = [];
}

-- e.g. nonterminal (reference) and terminal types
abstract production nominalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.freeVariables = setUnionTyVarsAll(mapFreeVariables(params));
}

-- e.g. decorated nonterminal types
abstract production decoratedTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.freeVariables = setUnionTyVarsAll(mapFreeVariables(params));
}

-- TODO: bug: we shouldn't have to use new() here :

abstract production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.freeVariables = setUnionTyVarsAll(mapFreeVariables(new(out) :: params));
}

abstract production productionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.freeVariables = setUnionTyVarsAll(mapFreeVariables(new(out) :: params));
}

-- TODO: IO type.  Anything else?  Lists?

--------------------------------------------------------------------------------

-- TODO: this is dumb
function mapFreeVariables
[[TyVar]] ::= tes::[TypeExp]
{
  return if null(tes) then [] else head(tes).freeVariables :: mapFreeVariables(tail(tes));
}

