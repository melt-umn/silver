grammar silver:compiler:definition:type;

{--
 - A representation of: (1) tyvar->type substitutions. (2) success/failure of unification
 -
 - History note: This code was written before Silver had polymorphism (it is
 - what gave Silver polymorphism!) and so is incredibly archaic.
 -
 - TODO: Separate success/failure of unification from this type.
 -       Consider unify returning Maybe<Substitution> or Pair<Boolean Substitution> depending.
 - TODO: More efficient type representations than a assoc-list, somehow.
 -}
data nonterminal Substitution with substList, substErrors, failure;

synthesized attribute substList :: [Pair<TyVar Type>];
synthesized attribute substErrors :: [String];
synthesized attribute failure :: Boolean; -- this is a bad hack to work around unify being unable to return a pair

--------------------------------------------------------------------------------

abstract production goodSubst
top::Substitution ::= sublst::[Pair<TyVar Type>]
{
  top.substList = sublst;
  top.substErrors = [];
  top.failure = false;
}

abstract production badSubst
top::Substitution ::= sublst::[Pair<TyVar Type>] errs::[String]
{
  top.substList = sublst;
  top.substErrors = errs;
  top.failure = true;
}

fun emptySubst Substitution ::= = goodSubst([]);
fun errorSubst Substitution ::= e::String = badSubst([], [e]);
fun subst Substitution ::= tv::TyVar te::Type = goodSubst([(tv,te)]);
fun composeSubst Substitution ::= s1::Substitution s2::Substitution =
  case s1, s2 of
  | goodSubst(s1l), goodSubst(s2l) ->  goodSubst(s1l ++ s2l)
  | goodSubst(s1l), badSubst(s2l, s2e) ->  badSubst(s1l ++ s2l, s2e)
  | badSubst(s1l, s1e), goodSubst(s2l) ->  badSubst(s1l ++ s2l, s1e)
  | badSubst(s1l, s1e), badSubst(s2l, s2e) ->  badSubst(s1l ++ s2l, s1e ++ s2e)
  end;

fun ignoreFailure Substitution ::= s::Substitution =
  case s of
  | goodSubst(_) -> s
  | badSubst(sl,_) -> goodSubst(sl)
  end;

--------------------------------------------------------------------------------

fun findSubst Maybe<Type> ::= tv::TyVar s::Substitution = lookup(tv, s.substList);

--------------------------------------------------------------------------------

-- These are for ordinary tyvar substitutions.
inherited attribute substitution :: Substitution occurs on Context, Type;
functor attribute substituted occurs on Context, Type;
-- These are for flat, non-recursive replacement of tyvars with something else directly
functor attribute flatRenamed occurs on Context, Type;

propagate substitution on Context, Type;
propagate substituted, flatRenamed on Context, Type
  excluding inhOccursContext, synOccursContext, annoOccursContext, varType, skolemType;

aspect production inhOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.substituted = inhOccursContext(attr, map(performSubstitution(_, top.substitution), args), atty.substituted, ntty.substituted);
  top.flatRenamed = inhOccursContext(attr, map(performRenaming(_, top.substitution), args), atty.flatRenamed, ntty.flatRenamed);
}

aspect production synOccursContext
top::Context ::= attr::String args::[Type] atty::Type inhs::Type ntty::Type
{
  top.substituted = synOccursContext(attr, map(performSubstitution(_, top.substitution), args), atty.substituted, inhs.substituted, ntty.substituted);
  top.flatRenamed = synOccursContext(attr, map(performRenaming(_, top.substitution), args), atty.flatRenamed, inhs.flatRenamed, ntty.flatRenamed);
}

aspect production annoOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.substituted = annoOccursContext(attr, map(performSubstitution(_, top.substitution), args), atty.substituted, ntty.substituted);
  top.flatRenamed = annoOccursContext(attr, map(performRenaming(_, top.substitution), args), atty.flatRenamed, ntty.flatRenamed);
}

aspect production varType
top::Type ::= tv::TyVar
{
  -- Important: we recursively substitute, until no more substitutions happen!
  -- This also means the substitution list must not be circular!

  -- Perform one iteration of substitution
  local partialsubst :: Maybe<Type> =
    case findSubst(tv, top.substitution) of
    | just(s) when s.kindrep != tv.kind -> error("Kind mismatch in applying substitution!")
    | ps -> ps
    end;
  
  -- recursively substitute only if we changed!
  top.substituted =
    if partialsubst.isJust
    then performSubstitution(partialsubst.fromJust, top.substitution)
    else new(top);
  top.flatRenamed =
    if partialsubst.isJust
    then partialsubst.fromJust
    else new(top);
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  -- This may be counter intuitive! I don't know!
  
  -- I'm allowing Skolem constants to be subtituted for.
  -- Now, the "real" behavior of Skolem constants is all in unification:
  -- there, they behave as you would expect.  However, once we quantify over the
  -- "Skolem constant type variables", they should sort of go back to behaving
  -- like ordinary type variables. So to get this behavior, we allow them to be
  -- substituted.
  
  -- The only way we can construct a substitution for one though is by non-unification
  -- means.  (And there's only one way to do that: by quantifying over it.)
  
  -- (See the only non-unification place where subst(...) is called directly at the bottom of this file.)
  
  local partialsubst :: Maybe<Type> =
    case findSubst(tv, top.substitution) of
    | just(s) when s.kindrep != tv.kind -> nothing()
    | ps -> ps
    end;
  
  -- recursively substitute only if we changed!
  top.substituted =
    if partialsubst.isJust
    then performSubstitution(partialsubst.fromJust, top.substitution)
    else new(top);
  top.flatRenamed =
    if partialsubst.isJust
    then partialsubst.fromJust
    else new(top);
}

--------------------------------------------------------------------------------

function performContextSubstitution
Context ::= c::Context s::Substitution
{
  c.substitution = s;
  return c.substituted;
}

function performSubstitution
Type ::= te::Type s::Substitution
{
  te.substitution = s;
  return te.substituted;
}

fun mapSubst [Type] ::= tes::[Type] s::Substitution = map(performSubstitution(_, s), tes);

----

function performContextRenaming
Context ::= c::Context s::Substitution
{
  c.substitution = s;
  return c.flatRenamed;
}

function performRenaming
Type ::= te::Type s::Substitution
{
  te.substitution = s;
  return te.flatRenamed;
}

fun mapRenameSubst [Type] ::= tes::[Type] s::Substitution = map(performRenaming(_, s), tes);

--------------------------------------------------------------------------------

-- Generate fresh type vars with the same kinds as tvs
fun freshTyVars [TyVar] ::= tvs::[TyVar] = map(freshTyVar, map((.kind), tvs));

fun zipVarsIntoSubstitution Substitution ::= original::[TyVar] sub::[TyVar] =
  if null(original) || null(sub) then emptySubst()
  else composeSubst(subst(head(original), varType(head(sub))),
         zipVarsIntoSubstitution(tail(original), tail(sub)));

fun zipVarsIntoSkolemizedSubstitution Substitution ::= original::[TyVar] sub::[TyVar] =
  if null(original) || null(sub) then emptySubst()
  else composeSubst(subst(head(original), skolemType(head(sub))),
         zipVarsIntoSkolemizedSubstitution(tail(original), tail(sub)));


fun zipVarsAndTypesIntoSubstitution Substitution ::= original::[TyVar] sub::[Type] =
  if null(original) || null(sub) then emptySubst()
  else composeSubst(subst(head(original), head(sub)),
         zipVarsAndTypesIntoSubstitution(tail(original), tail(sub)));

fun freshenType Type ::= te::Type tvs::[TyVar] = freshenTypeWith(te, tvs, freshTyVars(tvs));

fun freshenContextWith Context ::= c::Context tvs::[TyVar] ntvs::[TyVar] =
  performContextRenaming(c, zipVarsIntoSubstitution(tvs, ntvs));

fun freshenTypeWith Type ::= te::Type tvs::[TyVar] ntvs::[TyVar] =
  performRenaming(te, zipVarsIntoSubstitution(tvs, ntvs));

function errorSubstitution
Substitution ::= t::Type
{
  return zipVarsAndTypesIntoSubstitution(t.freeVariables, repeat(errorType(), length(t.freeVariables)));
}
