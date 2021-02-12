grammar silver:compiler:definition:type;


synthesized attribute typepp :: String occurs on PolyType, Context, Type, Kind;
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

function prettyKind
String ::= k::Kind
{
  return k.typepp;
}

--------------------------------------------------------------------------------

aspect production monoType
top::PolyType ::= ty::Type
{
  top.typepp = ty.typepp;
  ty.boundVariables = [];
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

aspect production typeableContext
top::Context ::= t::Type
{
  top.typepp = "runtimeTypeable " ++ t.typepp;
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
  top.typepp =
    case c.baseType of
    | functionType(params, namedParams) -> "(" ++
        (if length(top.argTypes) > params + length(namedParams)
         then prettyTypeWith(head(drop(params + length(namedParams), top.argTypes)), top.boundVariables)
         else "_") ++ " ::= " ++
         implode(" ", map(prettyTypeWith(_, top.boundVariables), take(params, top.argTypes))) ++
         (if length(top.argTypes) < params then replicate(params - length(top.argTypes), " _") else "") ++
         concat(
           zipWith(\ np::String t::Type -> s"; ${np}::${prettyTypeWith(t, top.boundVariables)}", namedParams, drop(params, top.argTypes)) ++
           map(\ np::String -> s"; ${np}::_", drop(length(top.argTypes) - (params + length(namedParams)), namedParams))) ++ ")" ++
         if length(top.argTypes) <= params + length(namedParams) + 1 then ""
         else "<" ++ implode(" ", map(prettyTypeWith(_, top.boundVariables), drop(params + length(namedParams) + 1, top.argTypes))) ++ ">"
    | _ -> prettyTypeWith(top.baseType, top.boundVariables) ++
      if null(top.argTypes) then ""
      else "<" ++ implode(" ", map(prettyTypeWith(_, top.boundVariables), top.argTypes)) ++
        replicate(max(length(top.argTypes) - length(top.baseType.kindrep.argKinds), 0), " _") ++ ">"
    end;
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
top::Type ::= params::Integer namedParams::[String]
{
  top.typepp = s"(_ ::=${replicate(params, " _") }${if null(namedParams) then "" else "; " ++ implode("::_; ", namedParams) ++ "::_"})";
}

aspect production starKind
top::Kind ::=
{
  top.typepp = "*";
}

aspect production arrowKind
top::Kind ::= k1::Kind k2::Kind
{
  top.typepp =
    case k1 of
    | arrowKind(_, _) -> s"(${k1.typepp}) -> ${k2.typepp}"
    | _ -> s"${k1.typepp} -> ${k2.typepp}"
    end;
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
  return
    case bv, vn of
    | hbv :: tbv, hvn :: tvn -> if tv == hbv then hvn else findAbbrevHelp(tv, tbv, tvn)
    | _, _ ->
      case positionOf(tv, bv) of
      | -1 -> "V_" ++ toString(tv.extractTyVarRep)
      | i -> "a" ++ toString(i)
      end
  end;
}
