grammar silver:definition:core;

concrete production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeList 'occurs' 'on' nt::QName nttl::BracketedOptTypeList ';'
{
  top.pp = "attribute " ++ at.pp ++ attl.pp ++ " occurs on " ++ nt.pp ++ nttl.pp ++ ";";

  -- TODO: this location is highly unreliable.

  -- We must unconditionally emite the 'oDef' in order to signal to the
  -- environment mechanism that we're in a different namespace than
  -- the types/attributes.
  top.defs = [
    oDef((if !null(at.lookupAttribute.errors) || !at.lookupAttribute.dcl.isAnnotation then occursDcl else annoInstanceDcl)(
      top.grammarName, at.location,
      nt.lookupType.fullName, at.lookupAttribute.fullName,
      protontty, protoatty))];

  -- binding errors in looking up these names.
  top.errors := at.lookupAttribute.errors ++ nt.lookupType.errors ++
    attl.errors ++ nttl.errors ++
    -- The nonterminal type list is strictly type VARIABLES only
    nttl.errorsTyVars;
  
  nttl.initialEnv = top.env;
  attl.env = nttl.envBindingTyVars;
  nttl.env = nttl.envBindingTyVars;
  
  -- Make sure we get the number of tyvars correct for the NT
  top.errors <- if length(nt.lookupType.dclBoundVars) != length(nttl.types)
                then [err(nt.location, nt.pp ++ " expects " ++ toString(length(nt.lookupType.dclBoundVars)) ++
                                       " type variables, but " ++ toString(length(nttl.types)) ++ " were provided.")]
                else [];

  -- Make sure we get the number of tyvars correct for the ATTR
  top.errors <- if length(at.lookupAttribute.dclBoundVars) != length(attl.types)
                then [err(at.location, at.pp ++ " expects " ++ toString(length(at.lookupAttribute.dclBoundVars)) ++
                                       " type variables, but " ++ toString(length(attl.types)) ++ " were provided.")]
                else [];

  -- We have 4 types.
  -- 1: A type, from env, for the nonterminal
  -- 2: A type, from syntax, for the nonterminal
  -- 3: A type, from env, for the attribute
  -- 4: A type, from syntax, for the attribute.
  
  -- Our goal is to be able to take a (unknown) nonterminal type
  -- and yield the appropriate attribute type it corresponds to.
  
  -- To that end, we want two things:
  -- 1: A type that we can unify with some nonterminal type.
  -- 2: A type that, under than unification, will be the resulting attribute type.

  -- So we generate three substitutions:
  -- 1: Rewrite the tyvars of type #1 to the types of type #2.
  -- 2: Rewrite the tyvars of type #4 to the types of type #4.
  -- 3: Rewrite our local tyvars to fresh variables.
  
  -- Thus, we apply this to type #2 and #4, and get our goal.
  
  -- This is perfectly correct, but it can probably be simplified with some invariants
  -- on what appears in the environment.
  
  production attribute rewriteAndFreshenSubst :: Substitution;
  rewriteAndFreshenSubst = 
    composeSubst(composeSubst(
      -- nt's env types -> local skolem types  (vars -> vars)
      zipVarsIntoSubstitution(nt.lookupType.dclBoundVars, nttl.freeVariables),
      -- at's env types -> local skolem types  (vars -> types)
      zipVarsAndTypesIntoSubstitution(at.lookupAttribute.dclBoundVars, attl.types)),
      -- local skolem types -> fresh ty vars (non-skolem)
      zipVarsIntoSubstitution(nttl.freeVariables, freshTyVars(length(nttl.freeVariables))));
  
  production attribute protontty :: TypeExp;
  production attribute protoatty :: TypeExp;
  protontty = performSubstitution(nt.lookupType.typerep, rewriteAndFreshenSubst);
  protoatty = performSubstitution(at.lookupAttribute.typerep, rewriteAndFreshenSubst);
  
  -- Now, finally, make sure we're not "redefining" the occurs.
  production attribute occursCheck :: [DclInfo];
  occursCheck = getOccursDcl(at.lookupAttribute.fullName, nt.lookupType.fullName, top.env);
  
  top.errors <-
    if length(occursCheck) > 1
    then [err(at.location, "Attribute '" ++ at.name ++ "' already occurs on '" ++ nt.name ++ "'.")]
    else [];

  top.errors <-
    if !nt.lookupType.typerep.isDecorable
    then [err(nt.location, nt.name ++ " is not a nonterminal. Attributes can only occur on nonterminals.")]
    else [];
                
  top.errors <-
    if !null(nt.lookupType.errors ++ at.lookupType.errors) || !at.lookupAttribute.dcl.isAnnotation || 
       contains(top.grammarName, computeOptionalDeps([nt.lookupType.dcl.sourceGrammar], top.compiledGrammars)) then []
    else [err(top.location, "Annotations for a nonterminal must be in a module exported by the nonterminal's declaring grammar.")];
}

concrete production annotateDcl
top::AGDcl ::= 'annotation' at::QName attl::BracketedOptTypeList 'occurs' 'on' nt::QName nttl::BracketedOptTypeList ';'
{
  forwards to attributionDcl('attribute', at, attl, $4, $5, nt, nttl, $8, location=top.location);
}

