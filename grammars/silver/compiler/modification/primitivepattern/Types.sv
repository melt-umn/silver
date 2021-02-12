grammar silver:compiler:modification:primitivepattern;

import silver:compiler:modification:ffi only foreignType; -- so we cover foreignType with the 'refine' hack below. TODO

{--
 - Turns the existential variables of a production type into skolem constants,
 - and freshen the rest.
 - e.g. (?a -> ?b -> F ?a) becomes (?c -> !d -> F ?c)
 - This is done so we can just unify the scrutinee type an go, no hairy details!
 - (This is used for *non-gadt* productions.)
 -}
function skolemizeProductionType
Pair<[Context] Type> ::= te::PolyType
{
  local existentialVars :: [TyVar] = removeAll(te.typerep.outputType.freeVariables, te.boundVars);
  
  local skolemize :: Substitution = composeSubst(
    zipVarsIntoSkolemizedSubstitution(existentialVars, freshTyVars(existentialVars)),
    zipVarsIntoSubstitution(te.typerep.outputType.freeVariables, freshTyVars(te.typerep.outputType.freeVariables)));
  
  return pair(map(performContextRenaming(_, skolemize), te.contexts), performRenaming(te.typerep, skolemize));
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
Pair<[Context] Type> ::= te::PolyType
{
  local skolemize :: Substitution = zipVarsIntoSkolemizedSubstitution(te.boundVars, freshTyVars(te.boundVars));
  
  return pair(map(performContextRenaming(_, skolemize), te.contexts), performRenaming(te.typerep, skolemize));
}



--- This is unification, EXCEPT that skolem constants behave like type variables!

inherited attribute refineWith :: Type occurs on Type;
synthesized attribute refine :: Substitution occurs on Type;

aspect production varType
top::Type ::= tv::TyVar
{
  top.refine = 
    case top.refineWith of
    | varType(j) when j.kindrep == tv.kindrep ->
        if tv == j
        then emptySubst()
        else subst(tv, top.refineWith)
    | t when t.kindrep == tv.kindrep ->
        if contains(tv, t.freeVariables)
        then errorSubst("Infinite type! Tried to refine with " ++ prettyType(t))
        else subst(tv, t)
    | t -> errorSubst("Kind mismatch!  Tried to unify with " ++ prettyType(top.unifyWith))
    end;
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.refine = 
    case top.refineWith of
    | skolemType(j) when j.kindrep == tv.kindrep -> 
        if tv == j
        then emptySubst()
        else subst(tv, top.refineWith)
    | t when t.kindrep == tv.kindrep ->
        if contains(tv, t.freeVariables)
        then errorSubst("Infinite type! Tried to refine with " ++ prettyType(t))
        else subst(tv, t)
    | t -> errorSubst("Kind mismatch!  Tried to unify with " ++ prettyType(top.unifyWith))
    end;
}

aspect production appType
top::Type ::= c::Type a::Type
{
  top.refine =
    case top.refineWith of
    | appType(c1, a1) ->
      let refineC :: Substitution = refine(c, c1)
      in composeSubst(refineC, refine(performSubstitution(a, refineC), performSubstitution(a1, refineC)))
      end
    | _ -> errorSubst("Tried to refine " ++ prettyType(top) ++ " with " ++ prettyType(top.refineWith))
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
top::Type ::= fn::String ks::[Kind] _
{
  top.refine = 
    case top.refineWith of
    | nonterminalType(ofn, oks, _) ->
        if fn == ofn && ks == oks
        then emptySubst()
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
top::Type ::= params::Integer namedParams::[String]
{
  top.refine = 
    case top.refineWith of
    | functionType(op, onp) when params == op && namedParams == onp -> emptySubst()
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
         | decoratedType(t1), decoratedType(t2) ->
           case t1.baseType, t2.baseType of
           | nonterminalType(n1, _, _), nonterminalType(n2, _, _) when n1 == n2 ->
             refineAll(t1.argTypes, t2.argTypes)
           | _, _ -> emptySubst()
           end
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


--------
synthesized attribute contextPatternDef::(Def ::= String Location String) occurs on Context;

aspect production instContext
top::Context ::= cls::String t::Type
{
  top.contextPatternDef = instPatternConstraintDef(_, _, cls, t, _);
}

abstract production instPatternConstraintDcl
top::DclInfo ::= fnTC::String ty::Type fnProd::String 
{
  top.fullName = fnTC;
  top.typeScheme = monoType(ty);
}
function instPatternConstraintDef
Def ::= sg::String  sl::Location  fn::String  ty::Type fnProd::String
{
  return tcInstDef(instPatternConstraintDcl(fn,ty,fnProd,sourceGrammar=sg,sourceLocation=sl));
}
