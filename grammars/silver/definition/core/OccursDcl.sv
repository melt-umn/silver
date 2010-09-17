grammar silver:definition:core;
import silver:definition:env;
import silver:util;

concrete production attributionDcl
top::AGDcl ::= 'attribute' a::QName '<' tlat::TypeList '>' 'occurs' 'on' nt::QName '<' tlnt::TypeList '>' ';'
{
  top.pp = "attribute " ++ a.pp ++ "<" ++ tlat.pp ++ "> occurs on " ++ nt.pp ++ "<" ++ tlnt.pp ++ ">;";
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  -- TODO SOMEDAY: relax the requirement that tlat be all type variables. (over-specified occurances e.g. 'ast Expr occurs on Expr_c')
  -- TODO SOMEDAY: relax the requirement that tlnt by all type variables. (partial occurences e.g. 'sum occurs on List Int')

  -- TODO: we should decide which location to use (a or nt) better somehow (not using top.location because that can't be trusted! ext:convenience may create these out of this air)
  top.defs = addOccursDcl(top.grammarName, a.location, 
                          nt.lookupType.fullName, a.lookupAttribute.fullName,
                          protontty, protoatty,
                          emptyDefs());

  -- binding errors in looking up these names.
  top.errors := a.lookupAttribute.errors ++ nt.lookupType.errors;
  
  -- Ensure that we're ONLY pairing up type variables, for now.
  -- This will error if any types (instead of type variables) appear in these lists.
  top.errors <- tlat.errorsTyVars ++ tlnt.errorsTyVars;
  
  -- Declare ONLY those type variables on the NT, (in TLAT and TLNT ONLY (that is, this does not belong in defs!))
  production attribute typingEnv :: Decorated Env;
  typingEnv = newScopeEnv( addNewLexicalTyVars(top.grammarName, top.location, tlnt.lexicalTypeVariables),
                           top.env);
  tlat.env = typingEnv;
  tlnt.env = typingEnv;
  top.errors <- if containsDuplicates(tlnt.lexicalTypeVariables)
                then [err(nt.location, "Duplicate type variable names listed in nonterminal")]
                else [];
  
  -- Make sure we get the number of tyvars correct for the NT
  top.errors <- if length(nt.lookupType.dclBoundVars) != length(tlnt)
                then [err(nt.location, nt.pp ++ " expects " ++ toString(length(nt.lookupType.dclBoundVars)) ++
                                       " type variables, but " ++ toString(length(tlnt)) ++ " were provided.")]
                else [];

  -- Make sure we get the number of tyvars correct for the ATTR
  top.errors <- if length(a.lookupAttribute.dclBoundVars) != length(tlat)
                then [err(a.location, a.pp ++ " expects " ++ toString(length(a.lookupAttribute.dclBoundVars)) ++
                                       " type variables, but " ++ toString(length(tlat)) ++ " were provided.")]
                else [];

  
  production attribute rewriteAndFreshenSubst :: Substitution;
  rewriteAndFreshenSubst = composeSubst(composeSubst(
                     -- nt's types -> local skolem types
                     zipVarsIntoSubstitution(nt.lookupType.dclBoundVars, tlnt.freeVariables),
                     -- at's type -> local skolem types
                     zipVarsIntoSubstitution(a.lookupAttribute.dclBoundVars, tlat.freeVariables)),
                   -- local skolem types -> fresh ty vars (non-skolem)
                   zipVarsIntoSubstitution(tlnt.freeVariables, freshTyVars(length(tlnt.freeVariables))));
                   
  production attribute protontty :: TypeExp;
  production attribute protoatty :: TypeExp;
  protontty = performSubstitution(nt.lookupType.typerep, rewriteAndFreshenSubst);
  protoatty = performSubstitution(a.lookupAttribute.typerep, rewriteAndFreshenSubst);
  
  {-  Look up nt and nt.bound
      Zip (nt.bound -> tlnt.types)
      Look up at and at.bound
      Zip (at.bound -> tlat.types)
      create a freshening substitution to get rid of skolems (Should only be those in tlnt)
      freshen both with this
      finally, put them in the environment.
      
      The end result should be a TyVars-only (no skolems, so unify works) pair of types, with shared
      type variables according to the occurs declaration.
      
      This code needs review from someone to make sure it all makes sense. Maybe just me, later, when I'm not sick and stuffy.
   -}

}

concrete production attributionDclEmpty
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  forwards to attributionDcl($1, a, '<', typeListNone(), '>', $3, $4, nt, '<', typeListNone(), '>', $6);
}

