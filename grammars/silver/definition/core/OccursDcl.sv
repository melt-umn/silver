grammar silver:definition:core;

concrete production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeList 'occurs' 'on' nt::QName nttl::BracketedOptTypeList ';'
{
  top.pp = "attribute " ++ at.pp ++ attl.pp ++ " occurs on " ++ nt.pp ++ nttl.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  -- TODO: we should decide which location to use (a or nt) better somehow (not using top.location because that can't be trusted! ext:convenience may create these out of thin air)
  top.defs = addOccursDcl(top.grammarName, at.location, 
                          nt.lookupType.fullName, at.lookupAttribute.fullName,
                          protontty, protoatty,
                          emptyDefs());

  -- binding errors in looking up these names.
  top.errors := at.lookupAttribute.errors ++ nt.lookupType.errors;
  
  -- TODO SOMEDAY: relax the requirement that nt.typelist be all type variables. (partial occurences e.g. 'sum occurs on List Int')
  top.errors <- attl.typelist.errors{-nonTyVars-} ++ nttl.typelist.errorsTyVars;
  -- We are permitting "over-specified occurrences" hence nonTyVars
  
  -- Declare ONLY those type variables on the NT, (in TLAT and TLNT ONLY (that is, this does not belong in defs!))
  production attribute typingEnv :: Decorated Env;
  typingEnv = newScopeEnv( addNewLexicalTyVars(top.grammarName, top.location, nttl.typelist.lexicalTypeVariables),
                           top.env);
  attl.env = typingEnv;
  nttl.env = typingEnv;
  top.errors <- if containsDuplicates(nttl.typelist.lexicalTypeVariables)
                then [err(nt.location, "Duplicate type variable names listed in nonterminal")]
                else [];
  
  -- Make sure we get the number of tyvars correct for the NT
  top.errors <- if length(nt.lookupType.dclBoundVars) != length(nttl.typelist.types)
                then [err(nt.location, nt.pp ++ " expects " ++ toString(length(nt.lookupType.dclBoundVars)) ++
                                       " type variables, but " ++ toString(length(nttl.typelist.types)) ++ " were provided.")]
                else [];

  -- Make sure we get the number of tyvars correct for the ATTR
  top.errors <- if length(at.lookupAttribute.dclBoundVars) != length(attl.typelist.types)
                then [err(at.location, at.pp ++ " expects " ++ toString(length(at.lookupAttribute.dclBoundVars)) ++
                                       " type variables, but " ++ toString(length(attl.typelist.types)) ++ " were provided.")]
                else [];

  -- Create two substitutions that translate the types listed in the env
  -- for the nt and at TO the type we write down here.
  -- THEN, include a subsitution from the skolems constants (that are here)
  -- TO fresh type variables that we generate.
  production attribute rewriteAndFreshenSubst :: Substitution;
  rewriteAndFreshenSubst = composeSubst(composeSubst(
                     -- nt's env types -> local skolem types  (okay because we only permit tyvars!!)
                     zipVarsIntoSubstitution(nt.lookupType.dclBoundVars, nttl.typelist.freeVariables),
                     -- at's env types -> local skolem types  (AndTypes because over-spec means it's not just free vars!)
                     zipVarsAndTypesIntoSubstitution(at.lookupAttribute.dclBoundVars, attl.typelist.types)),
                   -- local skolem types -> fresh ty vars (non-skolem)  (here we're just tyvars and skolems again...)
                   zipVarsIntoSubstitution(nttl.typelist.freeVariables, freshTyVars(length(nttl.typelist.freeVariables))));
                   
  production attribute protontty :: TypeExp;
  production attribute protoatty :: TypeExp;
  protontty = performSubstitution(nt.lookupType.typerep, rewriteAndFreshenSubst);
  protoatty = performSubstitution(at.lookupAttribute.typerep, rewriteAndFreshenSubst);
  
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

  -- Now, finally, make sure we're not "redefining" the occurs.
  production attribute occursCheck :: [Decorated DclInfo];
  occursCheck = getOccursDcl(at.lookupAttribute.fullName, nt.lookupType.fullName, top.env);
  
  top.errors <- if length(occursCheck) > 1
                then [err(at.location, "Attribute '" ++ at.name ++ "' already occurs on '" ++ nt.name ++ "'.")]
                else [];

  top.errors <- if !nt.lookupType.typerep.isDecorable
                then [err(nt.location, nt.name ++ " is not a nonterminal. Attributes can only occur on nonterminals.")]
                else [];

  forwards to agDclDefault();
}

