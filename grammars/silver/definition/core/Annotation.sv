grammar silver:definition:core;

terminal Annotation_kwd 'annotation' lexer classes {KEYWORD};
terminal Annotate_kwd 'annotate' lexer classes {KEYWORD};

concrete production annotationDcl
top::AGDcl ::= 'annotation' a::Name tl::BracketedOptTypeList '::' te::Type ';'
{
  top.pp = "annotation " ++ a.pp ++ tl.pp ++ " :: " ++ te.pp ++ ";";
  top.location = $1.location;

  production fName :: String = top.grammarName ++ ":" ++ a.name;

  top.defs = [annoDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [err(top.location, "The name '" ++ fName ++ "' is already bound.")]
    else [];	

  top.errors := te.errors ++ tl.errors ++ tl.errorsTyVars;
}

-- See OccursDcl 'attributionDcl' for the original
concrete production annotateDcl
top::AGDcl ::= 'annotate' nt::QName nttl::BracketedOptTypeList 'with' at::QName attl::BracketedOptTypeList ';'
{
  top.pp = "annotate " ++ nt.pp ++ nttl.pp ++ " with " ++ at.pp ++ attl.pp ++ ";";

  -- TODO: this location is highly unreliable.
  top.location = $1.location;

  top.defs = [
    annoInstanceDef(top.grammarName, at.location,
      nt.lookupType.fullName, at.lookupAttribute.fullName,
      protontty, protoatty)];

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
  
  top.errors <- if length(occursCheck) > 1
                then [err(at.location, "Annotation '" ++ at.name ++ "' already occurs on '" ++ nt.name ++ "'.")]
                else [];

  top.errors <- if !nt.lookupType.typerep.isDecorable
                then [err(nt.location, nt.name ++ " is not a nonterminal. Annotations can only occur on nonterminals.")]
                else [];

  top.errors <-
    if !null(nt.lookupType.errors) ||
       contains(top.grammarName, computeOptionalDeps([nt.lookupType.dcl.sourceGrammar], top.compiledGrammars)) then []
    else [err(top.location, "Annotations for a nonterminal be in a module exported by the nonterminal's declaring module.")];
}

