grammar silver:definition:core;

abstract production defaultAttributionDcl
top::AGDcl ::= at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  top.unparse = "attribute " ++ at.unparse ++ attl.unparse ++ " occurs on " ++ nt.unparse ++ nttl.unparse ++ ";";

  -- TODO: this location is highly unreliable.

  -- We must unconditionally emit the occurs def in order to signal to the
  -- environment mechanism that we're in a different namespace than
  -- the types/attributes.
  top.occursDefs = [
    (if !at.lookupAttribute.dcl.isAnnotation then occursDcl else annoInstanceDcl)(
      top.grammarName, at.location,
      nt.lookupType.fullName, at.lookupAttribute.fullName,
      protontty, protoatty)];

  -- binding errors in looking up these names.
  top.errors := nt.lookupType.errors ++
    attl.errors ++ nttl.errors ++
    -- The nonterminal type list is strictly type VARIABLES only
    nttl.errorsTyVars;
  
  nttl.initialEnv = top.env;
  attl.env = nttl.envBindingTyVars;
  nttl.env = nttl.envBindingTyVars;
  
  -- Make sure we get the number of tyvars correct for the NT
  top.errors <-
    if length(nt.lookupType.dclBoundVars) != length(nttl.types)
    then [err(nt.location, nt.name ++ " expects " ++ toString(length(nt.lookupType.dclBoundVars)) ++
                           " type variables, but " ++ toString(length(nttl.types)) ++ " were provided.")]
    else [];

  -- Make sure we get the number of tyvars correct for the ATTR
  top.errors <-
    if length(at.lookupAttribute.dclBoundVars) != length(attl.types)
    then [err(at.location, at.name ++ " expects " ++ toString(length(at.lookupAttribute.dclBoundVars)) ++
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
  -- 2: Rewrite the tyvars of type #3 to the types of type #4.
  -- 3: Rewrite our local tyvars to fresh variables.
  
  -- Thus, we apply this to type #2 and #4, and get our goal.
  
  -- This is perfectly correct, but it can probably be simplified with some invariants
  -- on what appears in the environment.
  
  -- This renames the vars from the environment
  local rewrite_from :: Substitution =
    composeSubst(
      -- nt's env types -> local skolem types  (vars -> vars)
      zipVarsIntoSubstitution(nt.lookupType.dclBoundVars, nttl.freeVariables),
      -- at's env types -> local skolem types  (vars -> types)
      zipVarsAndTypesIntoSubstitution(at.lookupAttribute.dclBoundVars, attl.types));
  
  local rewrite_to :: Substitution =
    zipVarsIntoSubstitution(nttl.freeVariables, freshTyVars(length(nttl.freeVariables)));
  
  -- These have to be two separate renamings, because the second renaming replaces names getting substituted in by the first renaming.
  production attribute protontty :: Type;
  production attribute protoatty :: Type;
  protontty = performRenaming(performRenaming(nt.lookupType.typerep, rewrite_from), rewrite_to);
  protoatty = performRenaming(performRenaming(at.lookupAttribute.typerep, rewrite_from), rewrite_to);
  
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
    if !nt.lookupType.found || !at.lookupAttribute.found || !at.lookupAttribute.dcl.isAnnotation ||
       isExportedBy(top.grammarName, [nt.lookupType.dcl.sourceGrammar], top.compiledGrammars) then []
    else [err(top.location, "Annotations for a nonterminal must be in a module exported by the nonterminal's declaring grammar.")];
}

abstract production errorAttributionDcl
top::AGDcl ::= msg::[Message] at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  top.unparse = "attribute " ++ at.unparse ++ attl.unparse ++ " occurs on " ++ nt.unparse ++ nttl.unparse ++ ";";
  top.occursDefs = [];
  top.errors := msg;
  
  nttl.initialEnv = top.env;
  attl.env = nttl.envBindingTyVars;
  nttl.env = nttl.envBindingTyVars;
  
  -- Decorate everything else to still check for errors
  top.errors <-
    -- binding errors in looking up these names.
    nt.lookupType.errors ++
    attl.errors ++ nttl.errors ++
    -- The nonterminal type list is strictly type VARIABLES only
    nttl.errorsTyVars;
  
  -- Make sure we get the number of tyvars correct for the NT
  top.errors <-
    if length(nt.lookupType.dclBoundVars) != length(nttl.types)
    then [err(nt.location, nt.name ++ " expects " ++ toString(length(nt.lookupType.dclBoundVars)) ++
                           " type variables, but " ++ toString(length(nttl.types)) ++ " were provided.")]
    else [];
}

concrete production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  top.unparse = "attribute " ++ at.unparse ++ attl.unparse ++ " occurs on " ++ nt.unparse ++ nttl.unparse ++ ";";
  
  -- Workaround for circular dependency due to dispatching on env:
  -- Nothing used to build the env namespaces on which we dispatch can depend on
  -- the forward here.
  -- Attribution (occurs) defs, which obviously must depend on this forward, are
  -- specified by a seperate occursDefs attribute.
  -- Attribution dispatch productions should only define occursDefs (i.e. no new
  -- nonterminals, productions, attributes, etc.)
  top.defs = [];
  top.moduleNames = [];
  
  forwards to
    (if !at.lookupAttribute.found
     then errorAttributionDcl(at.lookupAttribute.errors, _, _, _, _, location=_)
     else at.lookupAttribute.dcl.attributionDispatcher)(at, attl, nt, nttl, top.location);
}

concrete production annotateDcl
top::AGDcl ::= 'annotation' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  forwards to attributionDcl('attribute', at, attl, $4, $5, nt, nttl, $8, location=top.location);
}

