grammar silver:definition:type;

nonterminal Substitution with substList, substErrors, failure;

synthesized attribute substList :: [Pair<TyVar TypeExp>];
synthesized attribute substErrors :: [String];
synthesized attribute failure :: Boolean; -- this is a bad hack to work around unify being unable to return a pair

--------------------------------------------------------------------------------

abstract production goodSubst
top::Substitution ::= sublst::[Pair<TyVar TypeExp>]
{
  top.substList = sublst;
  top.substErrors = [];
  top.failure = false;
}

abstract production badSubst
top::Substitution ::= sublst::[Pair<TyVar TypeExp>] errs::[String]
{
  top.substList = sublst;
  top.substErrors = errs;
  top.failure = true;
}

function emptySubst
Substitution ::=
{
  return goodSubst([]);
}
function errorSubst
Substitution ::= e::String
{
  return badSubst([], [e]);
}
function subst
Substitution ::= tv::TyVar te::TypeExp
{
  return goodSubst([pair(tv,te)]);
}
function composeSubst
Substitution ::= s1::Substitution s2::Substitution
{
  -- TODO: once we have case-lists fix this notation.
  return case s1 of
           goodSubst(s1l) -> case s2 of
                               goodSubst(s2l) -> goodSubst(s1l++s2l)
                             | badSubst(s2l, s2e) -> badSubst(s1l++s2l, s2e)
                             end
         | badSubst(s1l, s1e) -> case s2 of
                                   goodSubst(s2l) -> badSubst(s1l++s2l,s1e)
                                 | badSubst(s2l, s2e) -> badSubst(s1l++s2l, s1e++s2e)
                                 end
         end;
           
}

function ignoreFailure
Substitution ::= s::Substitution
{
  return case s of
           goodSubst(_) -> s
         | badSubst(sl,_) -> goodSubst(sl)
         end;
}

--------------------------------------------------------------------------------

function findSubst
Maybe<TypeExp> ::= tv::TyVar s::Substitution
{
  return lookupBy(tyVarEqual, tv, s.substList);
}

--------------------------------------------------------------------------------

-- These are for ordinary tyvar substitutions.
autocopy attribute substitution :: Substitution occurs on TypeExp;
synthesized attribute substituted :: TypeExp occurs on TypeExp;

aspect production varTypeExp
top::TypeExp ::= tv::TyVar
{
  -- Important: we recursively substitute, until no more substitutions happen!
  -- This also means the substitution list must not be circular!

  -- Perform one iteration of substitution
  local attribute partialsubst :: Maybe<TypeExp>;
  partialsubst = findSubst(tv, top.substitution);
  
  -- recursively substitute only if we changed!
  top.substituted = if partialsubst.isJust
                    then performSubstitution(partialsubst.fromJust , top.substitution )
                    else top;
}

aspect production skolemTypeExp
top::TypeExp ::= tv::TyVar
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
  
  local attribute partialsubst :: Maybe<TypeExp>;
  partialsubst = findSubst(tv, top.substitution);
  
  -- recursively substitute only if we changed!
  top.substituted = if partialsubst.isJust
                    then performSubstitution(partialsubst.fromJust , top.substitution )
                    else top;
}

aspect production intTypeExp
top::TypeExp ::=
{
  top.substituted = top;
}

aspect production boolTypeExp
top::TypeExp ::=
{
  top.substituted = top;
}

aspect production floatTypeExp
top::TypeExp ::=
{
  top.substituted = top;
}

aspect production stringTypeExp
top::TypeExp ::=
{
  top.substituted = top;
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.substituted = nonterminalTypeExp(fn, mapSubst(params, top.substitution));
}

aspect production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.substituted = top;
}

aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.substituted = decoratedTypeExp(te.substituted);
}

aspect production ntOrDecTypeExp
top::TypeExp ::= nt::TypeExp  hidden::TypeExp
{
  -- We rely very carefully on eliminating ourselves once we've specialized!
  -- Note: we're matching on hidden.subsituted, not just hidden. Important!
  top.substituted =
    case hidden.substituted of
      varTypeExp(_) -> ntOrDecTypeExp(nt.substituted, hidden.substituted)
    | _             -> hidden.substituted
    end;
}

aspect production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp] namedParams::[NamedArgType]
{
  top.substituted = functionTypeExp(out.substituted, mapSubst(params, top.substitution), mapNamedSubst(namedParams, top.substitution));
}

--------------------------------------------------------------------------------

function performSubstitution
TypeExp ::= te::TypeExp s::Substitution
{
  te.substitution = s;
  return te.substituted;
}

function mapSubst
[TypeExp] ::= tes::[TypeExp] s::Substitution
{
  return if null(tes) then []
         else performSubstitution(head(tes), s) :: mapSubst(tail(tes), s);
}

function mapNamedSubst
[NamedArgType] ::= np::[NamedArgType] s::Substitution
{
  return case np of
         | [] -> []
         | namedArgType(n, t) :: rest ->
             namedArgType(n, performSubstitution(t, s)) :: mapNamedSubst(rest, s)
         end;
}

--------------------------------------------------------------------------------

function freshTyVars
[TyVar] ::= n::Integer
{
  return if n > 0 then freshTyVar() :: freshTyVars(n-1)
         else [];
}

function zipVarsIntoSubstitution
Substitution ::= original::[TyVar] sub::[TyVar]
{
  -- once we have "productions are subtypes of functions" then make this just map 'varTypeExp' and call the other one below
  return if null(original) || null(sub) then emptySubst()
         else composeSubst( subst(head(original), varTypeExp(head(sub))), zipVarsIntoSubstitution(tail(original), tail(sub)));
}

function zipVarsIntoSkolemizedSubstitution
Substitution ::= original::[TyVar] sub::[TyVar]
{
  -- once we have "productions are subtypes of functions" then make this just map 'varTypeExp' and call the other one below
  return if null(original) || null(sub) then emptySubst()
         else composeSubst( subst(head(original), skolemTypeExp(head(sub))), zipVarsIntoSkolemizedSubstitution(tail(original), tail(sub)));
}


function zipVarsAndTypesIntoSubstitution
Substitution ::= original::[TyVar] sub::[TypeExp]
{
  return if null(original) || null(sub) then emptySubst()
         else composeSubst( subst(head(original), head(sub)), zipVarsAndTypesIntoSubstitution(tail(original), tail(sub)));
}

function freshenTypeExp
TypeExp ::= te::TypeExp tvs::[TyVar]
{
  return freshenTypeExpWith(te, tvs, freshTyVars(length(tvs)));
}

function freshenTypeExpWith
TypeExp ::= te::TypeExp tvs::[TyVar] ntvs::[TyVar]
{
  return performSubstitution(te, zipVarsIntoSubstitution(tvs, ntvs));
}

-- This function is an artifact of the fact that we ONLY do generalization at the top level, so we don't have (un)bound variables.
function freshenCompletely
TypeExp ::= te::TypeExp
{
  return freshenTypeExp(te, te.freeVariables);
}

