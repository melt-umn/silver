grammar silver:compiler:extension:tuple;

imports silver:compiler:modification:list;

tracked nonterminal ListOfTypeExprs with unparse, te_translation;

-- Used to convert the comma-separated list of TypeExprs 
-- that make up the tuple type expression into a 
-- pair type expression:
synthesized attribute te_translation :: TypeExpr;

-- A list of the types that make up any given tuple:
synthesized attribute tupleElems :: [Type] occurs on Type;

-- One can think of any single type t as a single element tuple type (t)
-- Default production assigns the tupleElems for (t) as [t]; a
-- list containing just the type of t
-- This default avoids aspecting other types
aspect default production
top::Type ::=
{
  top.tupleElems = [top];
}

-- For any Pair (or nested Pair) type, accumulate its tupleElems
-- as the fst and snd types that make up that Pair
aspect production appType
top::Type ::= c::Type a::Type
{
  top.tupleElems =
    -- c.argTypes should only have a single element
    case c.baseType of
    | nonterminalType("silver:core:Pair", [starKind(), starKind()], true, false) -> c.argTypes ++ a.tupleElems
    | _ -> [top]
    end;

}

-- Aspect productions needed to avoid discarding 
-- the forwarding list type when we extract tupleElems
aspect production listType
top::Type ::= _
{
  top.tupleElems = [top];
}

aspect production listCtrType
top::Type ::=
{
  top.tupleElems = [top];
}

-- accepts a [Type] (will be tupleElems here)
-- prints tuples w/ correct type syntax
-- forwards to Pair type
abstract production tupleType
top::Type ::= ts::[Type]
{
  top.defaultSpecialization = top;

  -- to avoid transforming away the tupleType and turning it back 
  -- into a chain of Pairs when performing substitutions
  top.substituted = tupleType(map (\ t::Type -> decorate t with {substitution = top.substitution;}.substituted, ts));
  top.flatRenamed = tupleType(map (\ t::Type -> decorate t with {substitution = top.substitution;}.flatRenamed, ts));

  -- elements of ts need the boundVariables from the top because typepp attribute has a dependency on boundVariables
  top.typepp = 
    if length(ts) == 1
    then forward.typepp
    else "(" ++ implode(", ", map(prettyTypeWith(_, top.boundVariables), ts)) ++ ")";
  
  forwards to case ts of
    | [] -> nonterminalType("silver:core:Unit", [], true, false)
    | [t] -> t
    | t1::t1s -> appType(appType(nonterminalType("silver:core:Pair", [starKind(), starKind()], true, false), t1), tupleType(t1s))
    end;

}

-- Tuple TypeExpr

concrete production emptyTupleTypeExpr
top::TypeExpr ::= '(' ')'
{
  top.unparse = "()";
  top.typerep = tupleType([]);
  forwards to Silver_TypeExpr { silver:core:Unit };
}

concrete production tupleTypeExpr
top::TypeExpr ::= '(' tes::ListOfTypeExprs ')'
{
  top.unparse = "(" ++ tes.unparse ++ ")";
  top.typerep = tupleType(forward.typerep.tupleElems);
  forwards to tes.te_translation;
}

concrete production tupleTypeExpr2
top::ListOfTypeExprs ::= te1::TypeExpr ',' te2::TypeExpr
{
  top.unparse = te1.unparse ++ "," ++ te2.unparse;
  top.te_translation = Silver_TypeExpr {silver:core:Pair<$TypeExpr{te1} $TypeExpr{te2}>};
}

concrete production tupleTypeExprn
top::ListOfTypeExprs ::= te::TypeExpr ',' tes::ListOfTypeExprs
{
  top.unparse = te.unparse ++ "," ++ tes.unparse;
  top.te_translation = Silver_TypeExpr {silver:core:Pair<$TypeExpr{te} $TypeExpr{tes.te_translation}>};
}
