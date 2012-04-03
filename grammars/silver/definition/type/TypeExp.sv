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

--------------------------------------------------------------------------------

abstract production varTypeExp
top::TypeExp ::= tv::TyVar
{
  top.freeVariables = [tv];
}

abstract production skolemTypeExp
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

abstract production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.freeVariables = setUnionTyVarsAll(mapFreeVariables(params));
}

abstract production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.freeVariables = [];
}

abstract production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.freeVariables = te.freeVariables;
}

-- This will ONLY appear in the types of expressions, nowhere else!
abstract production ntOrDecTypeExp
top::TypeExp ::= nt::TypeExp  hidden::TypeExp
{
  top.freeVariables = case hidden of
                        varTypeExp(_) -> nt.freeVariables
                      | _ -> hidden.freeVariables
                      end;
  
  -- If we never specialize, we're decorated.
  forwards to decoratedTypeExp(nt);
}

abstract production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.freeVariables = setUnionTyVarsAll(mapFreeVariables(out :: params));
}

--------------------------------------------------------------------------------

-- TODO: this is dumb
function mapFreeVariables
[[TyVar]] ::= tes::[TypeExp]
{
  return if null(tes) then [] else head(tes).freeVariables :: mapFreeVariables(tail(tes));
}

