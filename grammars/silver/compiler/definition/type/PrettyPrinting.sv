grammar silver:compiler:definition:type;


synthesized attribute typepp :: String occurs on PolyType, Context, Type;
autocopy attribute boundVariables :: [TyVar] occurs on Context, Type;

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

function prettyContext
String ::= c::Context
{
  c.boundVariables = c.freeVariables;
  return c.typepp;
}

function prettyContextWith
String ::= c::Context tvs::[TyVar]
{
  c.boundVariables = tvs;
  return c.typepp;
}
--------------------------------------------------------------------------------

aspect production monoType
top::PolyType ::= ty::Type
{
  top.typepp = ty.typepp;
}

aspect production polyType
top::PolyType ::= tvs::[TyVar] ty::Type
{
  top.typepp = ty.typepp;
  ty.boundVariables = tvs;
}

aspect production constraintType
top::PolyType ::= tvs::[TyVar] contexts::[Context] ty::Type
{
  top.typepp = implode(", ", map(prettyContextWith(_, tvs), contexts)) ++ " => " ++ ty.typepp;
  ty.boundVariables = tvs;
}

aspect production instContext
top::Context ::= cls::String t::Type
{
  top.typepp = cls ++ " " ++ t.typepp;
}

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

aspect production appType
top::Type ::= c::Type a::Type
{
  top.typepp = prettyTypeWith(top.baseType, top.boundVariables) ++
    if null(top.argTypes) then ""
    else "<" ++ implode(" ", map(prettyTypeWith(_, top.boundVariables), top.argTypes)) ++ ">";
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
top::Type ::= fn::String _ _
{
  top.typepp = fn;
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
  return if null(vn) || null(bv) then "V_" ++ toString(tv.extractTyVarRep)
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