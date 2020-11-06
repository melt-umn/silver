grammar silver:definition:type;

import silver:util;

synthesized attribute typepp :: String occurs on Type;
autocopy attribute boundVariables :: [TyVar] occurs on Type;

function prettyType
String ::= te::Type
{
  te.boundVariables = te.freeVariables;
  return te.typepp;
}

function prettyTypeWith
String ::= te::Type tvs::[TyVar]
{
  te.boundVariables = tvs;
  return te.typepp;
}
--------------------------------------------------------------------------------
aspect production varType
top::Type ::= tv::TyVar
{
  top.typepp = findAbbrevFor(tv, top.boundVariables);
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.typepp = findAbbrevFor(tv, top.boundVariables);
}

aspect production errorType
top::Type ::=
{
  top.typepp = "<err>"; -- probably shouldn't ever get printed?
}

aspect production intType
top::Type ::=
{
  top.typepp = "Integer";
}

aspect production boolType
top::Type ::=
{
  top.typepp = "Boolean";
}

aspect production floatType
top::Type ::=
{
  top.typepp = "Float";
}

aspect production stringType
top::Type ::=
{
  top.typepp = "String";
}

aspect production terminalIdType
top::Type ::=
{
  top.typepp = "TerminalId";
}

aspect production nonterminalType
top::Type ::= fn::String params::[Type]
{
  top.typepp = fn ++ if !null(params) then "<" ++ implode(" ", mapTypePP(params, top.boundVariables)) ++ ">" else "";
}

aspect production terminalType
top::Type ::= fn::String
{
  top.typepp = fn;
}

aspect production decoratedType
top::Type ::= te::Type
{
  top.typepp = "Decorated " ++ te.typepp;
}

aspect production ntOrDecType
top::Type ::= nt::Type  hidden::Type
{
-- Sometimes useful for debugging.
--  top.typepp = "Undecorable " ++ nt.typepp ++ "{" ++ prettyTypeWith(hidden, []) ++ "}";
}

aspect production functionType
top::Type ::= out::Type params::[Type] namedParams::[NamedArgType]
{
  top.typepp = "(" ++ out.typepp ++ " ::= " ++ implode(" ", mapTypePP(params, top.boundVariables)) ++
    (if null(namedParams) then ")" else mapNamedPP(namedParams, top.boundVariables) ++ ")");
}

--------------------------------------------------------------------------------
function findAbbrevFor
String ::= tv::TyVar  bv::[TyVar]
{
  return findAbbrevHelp(tv, bv, ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"]);
}

function findAbbrevHelp
String ::= tv::TyVar  bv::[TyVar]  vn::[String]
{
  local attribute tvi :: Integer;
  tvi = case tv of tyVar(i) -> i end;
  
  return if null(vn) || null(bv) then "V_" ++ toString(tvi)
         else if tyVarEqual(tv, head(bv))
              then head(vn)
              else findAbbrevHelp(tv, tail(bv), tail(vn));
}

-- TODO: oh crap is this stupid
function mapTypePP
[String] ::= tes::[Type] bv::[TyVar]
{
  local fst :: Type = head(tes);
  fst.boundVariables = bv;
  
  return if null(tes) then []
         else fst.typepp :: mapTypePP(tail(tes), bv);
}
-- This is crummy:
function mapNamedPP
String ::= tes::[NamedArgType] bv::[TyVar]
{
  local fst :: NamedArgType = head(tes);
  fst.boundVariables = bv;
  
  return if null(tes) then ""
         else fst.typepp ++ mapNamedPP(tail(tes), bv);
}
