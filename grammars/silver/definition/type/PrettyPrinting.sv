grammar silver:definition:type;

import silver:util;

synthesized attribute typepp :: String occurs on TypeExp;
autocopy attribute boundVariables :: [TyVar] occurs on TypeExp;

function prettyType
String ::= te::TypeExp
{
  te.boundVariables = te.freeVariables;
  return te.typepp;
}

function prettyTypeWith
String ::= te::TypeExp tvs::[TyVar]
{
  te.boundVariables = tvs;
  return te.typepp;
}
--------------------------------------------------------------------------------
aspect production varTypeExp
top::TypeExp ::= tv::TyVar
{
  top.typepp = findAbbrevFor(tv, top.boundVariables);
}

aspect production skolemTypeExp
top::TypeExp ::= tv::TyVar
{
  top.typepp = findAbbrevFor(tv, top.boundVariables);
}

aspect production intTypeExp
top::TypeExp ::=
{
  top.typepp = "Integer";
}

aspect production boolTypeExp
top::TypeExp ::=
{
  top.typepp = "Boolean";
}

aspect production floatTypeExp
top::TypeExp ::=
{
  top.typepp = "Float";
}

aspect production stringTypeExp
top::TypeExp ::=
{
  top.typepp = "String";
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.typepp = fn ++ if !null(params) then "<" ++ folds(" ", mapTypePP(params, top.boundVariables)) ++ ">" else "";
}

aspect production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.typepp = fn;
}

aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.typepp = "Decorated " ++ te.typepp;
}

aspect production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.typepp =   "Function(" ++ out.typepp ++ " ::= " ++ folds(" ", mapTypePP(params, top.boundVariables)) ++ ")" ;
}

aspect production productionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.typepp = "Production(" ++ out.typepp ++ " ::= " ++ folds(" ", mapTypePP(params, top.boundVariables)) ++ ")" ;
}

--------------------------------------------------------------------------------
function findAbbrevFor
String ::= tv::TyVar  bv::[TyVar]
{
  -- TODO: temporary while our type variables are ticked
  return findAbbrevHelp(tv, bv, ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"]);
}

function findAbbrevHelp
String ::= tv::TyVar  bv::[TyVar]  vn::[String]
{
  return if null(vn) || null(bv) then "V_" ++ toString(case tv of tyVar(i) -> i end)
         else if tyVarEqual(tv, head(bv))
              then head(vn)
              else findAbbrevHelp(tv, tail(bv), tail(vn));
}

-- TODO: oh crap is this stupid
function mapTypePP
[String] ::= tes::[TypeExp] bv::[TyVar]
{
  local attribute fst :: TypeExp;
  fst = head(tes);
  fst.boundVariables = bv;
  
  return if null(tes) then []
         else fst.typepp :: mapTypePP(tail(tes), bv);
}
