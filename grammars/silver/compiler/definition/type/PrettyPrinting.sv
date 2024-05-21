grammar silver:compiler:definition:type;

import silver:langutil:pp;

synthesized attribute typepp :: String occurs on PolyType, Context, Type, Kind;
inherited attribute boundVariables :: [TyVar] occurs on Context, Type;

propagate boundVariables on Context, Type;

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

instance Show Type {
  pp = compose(text, prettyType);
}

instance Show Kind {
  pp = compose(text, prettyKind);
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

aspect production inhOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.typepp = s"attribute ${attr}${if null(args) then "" else s"<${implode(" ", map(prettyTypeWith(_, top.boundVariables), args))}>"} occurs on ${ntty.typepp}";
}

aspect production synOccursContext
top::Context ::= attr::String args::[Type] atty::Type inhs::Type ntty::Type
{
  top.typepp = s"attribute ${attr}${if null(args) then "" else s"<${implode(" ", map(prettyTypeWith(_, top.boundVariables), args))}>"} ${inhs.typepp} occurs on ${ntty.typepp}";
}

aspect production annoOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.typepp = s"annotation ${attr}${if null(args) then "" else s"<${implode(" ", map(prettyTypeWith(_, top.boundVariables), args))}>"} occurs on ${ntty.typepp}";
}

aspect production typeableContext
top::Context ::= t::Type
{
  top.typepp = "runtimeTypeable " ++ t.typepp;
}

aspect production inhSubsetContext
top::Context ::= i1::Type i2::Type
{
  top.typepp = i1.typepp ++ " subset " ++ i2.typepp;
}

aspect production typeErrorContext
top::Context ::= msg::String
{
  top.typepp = "typeError \"" ++ escapeString(msg) ++ "\"";
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
         (if null(namedParams) then "" else ";") ++
         concat(
           zipWith(\ np::String t::Type -> s" ${np}::${prettyTypeWith(t, top.boundVariables)}", namedParams, drop(params, top.argTypes)) ++
           map(\ np::String -> s" ${np}::_", drop(length(top.argTypes) - params, namedParams))) ++ ")" ++
         if length(top.argTypes) <= params + length(namedParams) + 1 then ""
         else "<" ++ implode(" ", map(prettyTypeWith(_, top.boundVariables), drop(params + length(namedParams) + 1, top.argTypes))) ++ ">"
    | _ -> prettyTypeWith(top.baseType, top.boundVariables) ++
      if null(top.argTypes) then ""
      else "<" ++ implode(" ", map(prettyTypeWith(_, top.boundVariables), top.argTypes)) ++
        replicate(max(length(top.baseType.kindrep.argKinds) - length(top.argTypes), 0), " _") ++ ">"
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
top::Type ::= fn::String _ _ _
{
  top.typepp = fn;
}

aspect production terminalType
top::Type ::= fn::String
{
  top.typepp = fn;
}

aspect production inhSetType
top::Type ::= inhs::[String]
{
  -- Elide the grammar name when it is repeated
  -- e.g. {silver:compiler:definition:env:env, :config, :isRoot}
  top.typepp =
    s"{${implode(", ",
      flatMap(
        \ is::[String] -> head(is) :: map(\ i::String -> ":" ++ last(explode(":", i)), tail(is)),
        groupBy(\ i1::String i2::String -> init(explode(":", i1)) == init(explode(":", i2)), inhs)))}}";
}

aspect production decoratedType
top::Type ::= t::Type i::Type
{
  top.typepp = s"Decorated ${t.typepp} with ${i.typepp}";
}

aspect production ntOrDecType
top::Type ::= nt::Type inhs::Type hidden::Type
{
-- Sometimes useful for debugging.
--  top.typepp = "Undecorable " ++ nt.typepp ++ " with  " ++ inhs.typepp ++ " specialized " ++ prettyTypeWith(hidden, []);
}

aspect production functionType
top::Type ::= params::Integer namedParams::[String]
{
  top.typepp = s"(_ ::=${replicate(params, " _") }${if null(namedParams) then "" else "; " ++ implode("::_; ", namedParams) ++ "::_"})";
}

aspect production dispatchType
top::Type ::= ns::NamedSignature
{
  top.typepp = ns.fullName;
}

aspect production starKind
top::Kind ::=
{
  top.typepp = "*";
}

aspect production inhSetKind
top::Kind ::=
{
  top.typepp = "InhSet";
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
  local named::[TyVar] = filter(\ tv::TyVar -> case tv of tyVarNamed(_) -> true | _ -> false end, bv);
  local anon::[TyVar] = filter(\ tv::TyVar -> case tv of tyVarNamed(_) -> false | _ -> true end, bv);
  return findAbbrevHelp(tv, named ++ anon, ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"], []);
}

fun findAbbrevHelp String ::= tv::TyVar  bv::[TyVar]  vn::[String] assigned::[String] =
  case bv, vn of
  | tyVarNamed(n) :: tbv, _ when !contains(n, assigned) ->
    if tv == head(bv) then n else findAbbrevHelp(tv, tbv, vn, n :: assigned)
  | hbv :: tbv, hvn :: tvn ->
    if contains(hvn, assigned)
    then findAbbrevHelp(tv, bv, tvn, assigned)
    else if tv == hbv then hvn else findAbbrevHelp(tv, tbv, tvn, hvn :: assigned)
  | _, _ ->
    case positionOf(tv, bv) of
    | i when i > 0 && !contains("a" ++ toString(i), assigned) -> "a" ++ toString(i)
    | _ -> "V_" ++ toString(tv.varId)
    end
end;
