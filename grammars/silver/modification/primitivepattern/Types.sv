grammar silver:modification:primitivepattern;

import silver:modification:ffi only foreignType; -- so we cover foreignType with the 'refine' hack below. TODO

{--
 - Turns the existential variables of a production type into skolem constants,
 - and freshen the rest.
 - e.g. (?a -> ?b -> F ?a) becomes (?c -> !d -> F ?c)
 - This is done so we can just unify the scrutinee type an go, no hairy details!
 - (This is used for *non-gadt* productions.)
 -}
function skolemizeProductionType
Type ::= te::Type
{
  local attribute existentialVars :: [TyVar];
  existentialVars = removeTyVars(te.freeVariables, te.outputType.freeVariables);
  
  local attribute skolemize :: Substitution;
  skolemize = composeSubst(
    zipVarsIntoSkolemizedSubstitution(existentialVars, freshTyVars(length(existentialVars))),
    zipVarsIntoSubstitution(te.outputType.freeVariables, freshTyVars(length(te.outputType.freeVariables))));
  
  return performRenaming(te, skolemize);
}

{--
 - (This is used for *gadt* productions.)
 - wat? why? well, one skolem constant is as good as another, and we're here INTRODUCING
 - new variables, and we need to make them skolem constants.
 -
 - Here's the example, suppose we have 'arrow :: T<a> -> T<b> -> T<A<a b>>'
 - and we do 'case (::TypeExpr<c>) of arrow(...)' we're going to refine 
 - the c to A<a b>, but there's a HUGE HUGE PROBLEM THERE because we can't
 - allow a and b to be unified together later on, because we have no idea what
 - types they are!  So a and b MUST wind up as different skolem constants,
 - not as type variables, despite appearing in the 'output type'.
 -
 - So my solution right now is to skolemize the entire type, and I *think* this
 - works just fine... for now.  The reason is that we're going OutsideIn, so
 - type checking should be 'completed'.  That is, there should be
 - *** NO TYPE VARIABLES AT ALL *** in the scrutineeType anymore.
 - Either they got unified with some skolem constant, got unified with some type
 - or an error should have been raised somewhere.  (Even once we add real inference
 - this should be the case, since all free type variables should end up unified with
 - some skolem constant upon generalization of an expression...)
 -
 - TODO: what about nontermination / truely useless ones?
 -     case error("lol") of eq() -> "umm" | unit() -> "lol" end
 -   is a-okay with the type checker, but that's because of the TODO in prodPatternGadt.
 -   Could there be any other issues?
 -
 - And since we're just doing a 'refine' afterwards, well... one skolem constant
 - is as good as another, as far as correctness goes, anyway...
 -}
function fullySkolemizeProductionType
Type ::= te::Type
{
  local attribute skolemize :: Substitution;
  skolemize = zipVarsIntoSkolemizedSubstitution(te.freeVariables, freshTyVars(length(te.freeVariables)));
  
  return performRenaming(te, skolemize);
}



--- This is unification, EXCEPT that skolem constants behave like type variables!

inherited attribute refineWith :: Type occurs on Type;
synthesized attribute refine :: Substitution occurs on Type;

aspect production varType
top::Type ::= tv::TyVar
{
  top.refine = 
    case top.refineWith of
    | varType(j) ->
        if tyVarEqual(tv, j)
        then emptySubst()
        else subst(tv, top.refineWith)
    | _ -> if containsTyVar(tv, top.refineWith.freeVariables)
           then errorSubst("Infinite type! Tried to refine with " ++ prettyType(top.refineWith))
           else subst(tv, top.refineWith)
    end;
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.refine = 
    case top.refineWith of
    | skolemType(j) -> 
        if tyVarEqual(tv, j)
        then emptySubst()
        else subst(tv, top.refineWith)
    | _ -> if containsTyVar(tv, top.refineWith.freeVariables)
           then errorSubst("Infinite type! Tried to refine with " ++ prettyType(top.refineWith))
           else subst(tv, top.refineWith)
    end;
}
 
aspect production errorType
top::Type ::=
{
  top.refine = emptySubst();
}

aspect production intType
top::Type ::=
{
  top.refine = 
    case top.refineWith of
    | intType() -> emptySubst()
    | _ -> errorSubst("Tried to refine Integer with " ++ prettyType(top.refineWith))
    end;
}

aspect production boolType
top::Type ::=
{
  top.refine = 
    case top.refineWith of
    | boolType() -> emptySubst()
    | _ -> errorSubst("Tried to refine Boolean with " ++ prettyType(top.refineWith))
    end;
}

aspect production floatType
top::Type ::=
{
  top.refine = 
    case top.refineWith of
    | floatType() -> emptySubst()
    | _ -> errorSubst("Tried to refine Float with " ++ prettyType(top.refineWith))
    end;
}

aspect production stringType
top::Type ::=
{
  top.refine = 
    case top.refineWith of
    | stringType() -> emptySubst()
    | _ -> errorSubst("Tried to refine Boolean with " ++ prettyType(top.refineWith))
    end;
}

aspect production terminalIdType
top::Type ::=
{
  top.refine = 
    case top.refineWith of
    | terminalIdType() -> emptySubst()
    | _ -> errorSubst("Tried to refine TerminalId with " ++ prettyType(top.refineWith))
    end;
}

aspect production nonterminalType
top::Type ::= fn::String params::[Type] tracked::Boolean
{
  top.refine = 
    case top.refineWith of
    | nonterminalType(ofn, op, _) ->
        if fn == ofn
        then refineAll( params, op )
        else errorSubst("Tried to refine conflicting nonterminal types " ++ fn ++ " and " ++ ofn)
    | _ -> errorSubst("Tried to refine nonterminal type " ++ fn ++ " with " ++ prettyType(top.refineWith))
    end;
}

aspect production terminalType
top::Type ::= fn::String
{
  top.refine = 
    case top.refineWith of
    | terminalType(ofn) ->
        if fn == ofn
        then emptySubst()
        else errorSubst("Tried to refine conflicting terminal types " ++ fn ++ " and " ++ ofn)
    | _ -> errorSubst("Tried to refine terminal type " ++ fn ++ " with " ++ prettyType(top.refineWith))
    end;
}

aspect production decoratedType
top::Type ::= te::Type
{
  top.refine = 
    case top.refineWith of
    | decoratedType(ote) -> refine(te, ote)
    | _ -> errorSubst("Tried to refine decorated type with " ++ prettyType(top.refineWith))
    end;
}

aspect production functionType
top::Type ::= out::Type params::[Type] namedParams::[NamedArgType]
{
  top.refine = 
    case top.refineWith of
    | functionType(oo, op, onp) -> refineAll(out :: params ++ map((.argType), namedParams), oo :: op ++ map((.argType), onp))
    | _ -> errorSubst("Tried to refine function type with " ++ prettyType(top.refineWith))
    end;
}

aspect production foreignType
top::Type ::= fn::String  transType::String  params::[Type]
{
  top.refine = 
    case top.refineWith of
    | foreignType(ofn, _, op) -> 
        if fn == ofn
        then refineAll( params, op )
        else errorSubst("Tried to refine conflicting foreign types " ++ fn ++ " and " ++ ofn)
    | _ -> errorSubst("Tried to refine foreign type " ++ fn ++ " with " ++ prettyType(top.refineWith))
    end;
}

{--
 - Produces substitutions that may involve skolem constants, as well as free variables
 - for constructors.
 -
 - @param scrutineeType  The decorated type of the value being examined. Should not be a type variable!
 - @param constructorType  The decorated type of the production's product (i.e. the type it constructs)
 -}
function produceRefinement
Substitution ::= scrutineeType::Type  constructorType::Type
{
  -- only do refinement if they're the same type constructor.
  -- If you look at the type rules, you'll notice they're requiring "T" be the same,
  -- and this refinement only happens on the parameters (i.e. fmgu(T p = T a))
  return case scrutineeType, constructorType of
         | decoratedType(nonterminalType(n1, p1, _)), decoratedType(nonterminalType(n2,p2, _))
            -> if n1 == n2 then refineAll(p1,p2) else emptySubst()
         | _, _ -> emptySubst()
         end;
}

function refine
Substitution ::= te1::Type te2::Type
{
  local attribute leftward :: Substitution;
  leftward = te1.refine;
  te1.refineWith = te2;
  
  local attribute rightward :: Substitution;
  rightward = te2.refine;
  te2.refineWith = te1;
  
  return if null(leftward.substErrors)
         then leftward   -- arbitrary choice if both work, but if they are confluent, it's okay
         else rightward; -- arbitrary choice of errors. Non-confluent!!
}
function refineAll
Substitution ::= te1::[Type] te2::[Type]
{
  local attribute first :: Substitution;
  first = refine(head(te1), head(te2));
  
  return if null(te1) && null(te2)
         then emptySubst()
         else if null(te1) || null(te2)
         then errorSubst("Internal error: refineing mismatching numbers")
         else composeSubst(first, refineAll( mapSubst(tail(te1), first),
                                            mapSubst(tail(te2), first) ));
}


--------
function isOnlyTyVars
Boolean ::= ls::[Type]
{
  return case ls of
         | [] -> true
         | varType(_) :: t -> isOnlyTyVars(t)
         | skolemType(_) :: t -> isOnlyTyVars(t)
         | _ -> false
         end;
}

