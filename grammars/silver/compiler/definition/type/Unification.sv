grammar silver:compiler:definition:type;

inherited attribute unifyWith :: Type occurs on Type;
synthesized attribute unify :: Substitution occurs on Type;

--------------------------------------------------------------------------------
aspect production varType
top::Type ::= tv::TyVar
{
  top.unify = 
    case top.unifyWith of
    | varType(j) ->
        if tv == j
        then emptySubst()
        else subst(tv, top.unifyWith)
    | t when t.kindArity == tv.kindArity ->
        if contains(tv, top.unifyWith.freeVariables)
        then errorSubst("Infinite type! Tried to unify with " ++ prettyType(top.unifyWith))
        else subst(tv, top.unifyWith)
    | t -> errorSubst("Kind mismatch!  Tried to unify with " ++ prettyType(top.unifyWith))
    end;
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.unify = 
    case top.unifyWith of
    | skolemType(otv) ->
        if tv == otv
        then emptySubst()
        else errorSubst("Tried to unify skolem constant with incompatible skolem constant")
    | _ -> errorSubst("Tried to unify skolem constant with " ++ prettyType(top.unifyWith))
    end;
}

aspect production appType
top::Type ::= c::Type a::Type
{
  top.unify = 
    case top.unifyWith of
    | appType(c1, a1) ->
      let unifyC :: Substitution = unify(c, c1)
      in composeSubst(unifyC, unify(performSubstitution(a, unifyC), performSubstitution(a1, unifyC)))
      end
    | _ -> errorSubst("Tried to unify application of " ++ prettyType(c) ++ " with " ++ prettyType(top.unifyWith))
    end;
}

aspect production errorType
top::Type ::=
{
  top.unify = emptySubst(); -- report no additional errors
}

aspect production intType
top::Type ::=
{
  top.unify = 
    case top.unifyWith of
    | intType() -> emptySubst()
    | _ -> errorSubst("Tried to unify Integer with " ++ prettyType(top.unifyWith))
    end;
}

aspect production boolType
top::Type ::=
{
  top.unify = 
    case top.unifyWith of
    | boolType() -> emptySubst()
    | _ -> errorSubst("Tried to unify Boolean with " ++ prettyType(top.unifyWith))
    end;
}

aspect production floatType
top::Type ::=
{
  top.unify = 
    case top.unifyWith of
    | floatType() -> emptySubst()
    | _ -> errorSubst("Tried to unify Float with " ++ prettyType(top.unifyWith))
    end;
}

aspect production stringType
top::Type ::=
{
  top.unify = 
    case top.unifyWith of
    | stringType() -> emptySubst()
    | _ -> errorSubst("Tried to unify Boolean with " ++ prettyType(top.unifyWith))
    end;
}

aspect production terminalIdType
top::Type ::=
{
  top.unify = 
    case top.unifyWith of
    | terminalIdType() -> emptySubst()
    | _ -> errorSubst("Tried to unify TerminalId with " ++ prettyType(top.unifyWith))
    end;
}

aspect production nonterminalType
top::Type ::= fn::String k::Integer tracked::Boolean
{
  top.unify = 
    case top.unifyWith of
    | nonterminalType(ofn, ok, otracked) ->
        if fn == ofn
        then if k == ok
          then if tracked!=otracked 
            then error("Internal Error: Mismatching trackedness for " ++ fn ++ " when unifying. Try rebuilding with --clean. \nSee https://github.com/melt-umn/silver/pull/333 and https://github.com/melt-umn/silver/issues/36 .")
            else emptySubst()
          else error("kind mismatch during unification for " ++ prettyType(top) ++ " and " ++ prettyType(top.unifyWith)) -- Should be impossible
        else errorSubst("Tried to unify conflicting nonterminal types " ++ fn ++ " and " ++ ofn)
    | ntOrDecType(_, _) -> errorSubst("nte-nodte: try again")
    | _ -> errorSubst("Tried to unify nonterminal type " ++ fn ++ " with " ++ prettyType(top.unifyWith))
    end;
}

aspect production terminalType
top::Type ::= fn::String
{
  top.unify = 
    case top.unifyWith of
    | terminalType(ofn) ->
        if fn == ofn
        then emptySubst()
        else errorSubst("Tried to unify conflicting terminal types " ++ fn ++ " and " ++ ofn)
    | _ -> errorSubst("Tried to unify terminal type " ++ fn ++ " with " ++ prettyType(top.unifyWith))
    end;
}

aspect production decoratedType
top::Type ::= te::Type
{
  top.unify = 
    case top.unifyWith of
    | decoratedType(ote) -> unify(te, ote)
    | ntOrDecType(_,_) -> errorSubst("dte-nodte: try again")
    | _ -> errorSubst("Tried to unify decorated type with " ++ prettyType(top.unifyWith))
    end;
}

aspect production ntOrDecType
top::Type ::= nt::Type  hidden::Type
{
  -- If were being asked to unify, then we know hidden is still a type variable,
  -- since we shouldn't be unifying with anything but fully-substituted types.
  -- And we kill off this type once hidden is specialized.
  top.unify =
    case top.unifyWith.baseType of
    | decoratedType(ote) ->
        -- Ensure compatibility between Decorated nonterminal types, then specialize ourselves
        unifyAllShortCircuit([ote, top.unifyWith],
                             [nt,  hidden])
    | nonterminalType(_, _, _) ->
        -- Ensure compatibility between nonterminal types, then specialize ourselves
        unifyAllShortCircuit([top.unifyWith, top.unifyWith],
                             [nt,            hidden])
    | ntOrDecType(ont1, ohidden1) ->
        -- Ensure compatibility between nonterminal types, then merge our specializations
        unifyAllShortCircuit([ont1, ohidden1],
                             [nt,   hidden])
    | _ -> errorSubst("Tried to unify decorated type with " ++ prettyType(top.unifyWith))
    end;
}

aspect production functionType
top::Type ::= out::Type params::[Type] namedParams::[NamedArgType]
{
  top.unify = 
    case top.unifyWith of
    | functionType(oo, op, onp) -> unifyFunctions(out :: params, oo :: op, namedParams, onp)
    | _ -> errorSubst("Tried to unify function type with " ++ prettyType(top.unifyWith))
    end;
}

--------------------------------------------------------------------------------

function unify
Substitution ::= te1::Type te2::Type
{
  local leftward :: Substitution = te1.unify;
  te1.unifyWith = te2;
  
  local rightward :: Substitution = te2.unify;
  te2.unifyWith = te1;
  
  return if null(leftward.substErrors)
         then leftward   -- arbitrary choice if both work, but if they are confluent, it's okay
         else rightward; -- arbitrary choice of errors. Non-confluent!!
}

function unifyCheck
Substitution ::= te1::Type te2::Type s::Substitution
{
  return composeSubst(ignoreFailure(s), unify(performSubstitution(te1, s), performSubstitution(te2, s)));
}

-- This function is meant to produce a simple rewriting FROM `fromte` to `tote`
-- suitable for use with `performRenaming` (vs `performSubstitution`).
-- Basically, it's supposed to structurally rewrite type variables from
-- stale variables in the environment, to contextually valid variables/types.
-- e.g. (v1 ::= v1 v2) U (int ::= int v1)
-- should yield: v1 -> int, v2 -> v1.
-- Rewriting should apply this without `v2` becoming `int`. (As normal subst would do.)
-- TODO this code is obviously implemented in a fragile way.
function unifyDirectional
Substitution ::= fromte::Type tote::Type
{
  -- Currently, this is built on the assumption that the unification will not fail.
  -- Therefore, for now we will FRAGILEY just call unify 
  -- This is a possible source of bugs/unexpected behavior?
  return unify(fromte, tote);
}

function unifyAll
Substitution ::= te1::[Type] te2::[Type]
{
  local first :: Substitution = unify(head(te1), head(te2));
  
  return if null(te1) && null(te2)
         then emptySubst()
         else if null(te1) || null(te2)
         then errorSubst("Internal error: unifying mismatching numbers")
         else composeSubst(first, unifyAll( mapSubst(tail(te1), first),
                                            mapSubst(tail(te2), first) ));
}

function unifyAllShortCircuit
Substitution ::= te1::[Type] te2::[Type]
{
  local first :: Substitution = unify(head(te1), head(te2));
  
  return if null(te1) && null(te2)
         then emptySubst()
         else if null(te1) || null(te2)
         then errorSubst("Internal error: unifying mismatching numbers")
         else if first.failure
         then first -- terminate recursion!
         else composeSubst(first, unifyAllShortCircuit( mapSubst(tail(te1), first),
                                                        mapSubst(tail(te2), first) ));
}

function unifyAllNamed
Substitution ::= te1::[NamedArgType]  te2::[NamedArgType]
{
  local first :: Substitution = unify(head(te1).argType, head(te2).argType);
  
  return if null(te1) && null(te2)
         then emptySubst()
         else if null(te1) || null(te2)
         then errorSubst("Internal error: unifying mismatching numbers")
         else if head(te1).argName != head(te2).argName -- additionally check names
         then errorSubst("Mismatching named parameters")
         else composeSubst(first, unifyAllNamed( mapNamedSubst(tail(te1), first),
                                                 mapNamedSubst(tail(te2), first) ));  
}

function unifyFunctions
Substitution ::= te1::[Type]  te2::[Type]  n1::[NamedArgType]  n2::[NamedArgType]
{
  local first :: Substitution = unifyAll(te1, te2);
  local second :: Substitution = unifyAllNamed(mapNamedSubst(n1, first), mapNamedSubst(n2, first));
  
  return composeSubst(first, second);
}

