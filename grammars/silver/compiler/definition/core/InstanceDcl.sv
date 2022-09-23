grammar silver:compiler:definition:core;

import silver:compiler:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph;

concrete production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  top.unparse = s"instance ${cl.unparse} => ${id.unparse} ${ty.unparse}\n{\n${body.unparse}\n}"; 

  production fName :: String = id.lookupType.fullName;
  production boundVars::[TyVar] = ty.freeVariables;
  production dcl::TypeDclInfo = id.lookupType.dcl;
  dcl.givenInstanceType = ty.typerep;
  
  production superContexts::Contexts =
    foldContexts(if id.lookupType.found && !foldContexts(cl.contexts).isTypeError then dcl.superContexts else []);
  superContexts.env = body.env;
  superContexts.config = top.config;
  superContexts.grammarName = top.grammarName;
  superContexts.compiledGrammars = top.compiledGrammars;

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = constructAnonymousGraph(body.flowDefs, top.env, myProds, myFlow);
  superContexts.frame = globalExprContext(fName, foldContexts(body.frameContexts), ty.typerep, myFlowGraph, sourceGrammar=top.grammarName);

  top.defs := [instDef(top.grammarName, id.location, fName, boundVars, cl.contexts, ty.typerep, body.definedMembers)];

  top.errors <- id.lookupType.errors;
  top.errors <-
    if !id.lookupType.found || dcl.isClass then []
    else [err(id.location, id.name ++ " is not a type class.")];
  top.errors <-
    if !ty.typerep.isError && length(getInstanceDcl(fName, ty.typerep, top.env)) > 1
    then [err(id.location, "Overlapping instances exist for " ++ id.unparse ++ " " ++ ty.unparse)]
    else [];
  top.errors <-
    case ty.typerep of
    -- Default instance, must be exported by the class declaration
    | skolemType(_) when id.lookupType.found && !isExportedBy(top.grammarName, [dcl.sourceGrammar], top.compiledGrammars) ->
      [wrn(top.location, "Orphaned default instance declaration for " ++ fName)]
    -- Regular instance, must be exported by the class or type declaration
    | t when id.lookupType.found &&
        !isExportedBy(
          top.grammarName,
          dcl.sourceGrammar :: map(\ d::TypeDclInfo -> d.sourceGrammar, getTypeDcl(t.typeName, top.env)),
          top.compiledGrammars) ->
      [wrn(top.location, s"Orphaned instance declaration for ${fName} ${prettyType(t)}")]
    | _ -> []
    end;
  
  cl.constraintPos = instancePos(instContext(fName, ty.typerep), boundVars);

  production attribute headPreDefs :: [Def] with ++;
  headPreDefs := [];

  production attribute headDefs :: [Def] with ++;
  headDefs := cl.defs;
  headDefs <- [currentInstDef(top.grammarName, id.location, fName, ty.typerep)];
  
  cl.env = newScopeEnv(headPreDefs, top.env);
  id.env = cl.env;
  ty.env = cl.env;
  
  body.env = occursEnv(cl.occursDefs, newScopeEnv(headDefs, cl.env));
  body.className = id.lookupType.fullName;
  body.instanceType = ty.typerep; 
  body.expectedClassMembers = if id.lookupType.found then dcl.classMembers else [];
  body.frameContexts = superContexts.contexts ++ cl.contexts;
} action {
  insert semantic token IdTypeClass_t at id.baseNameLoc;
}

concrete production instanceDclNoCL
top::AGDcl ::= 'instance' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  top.unparse = s"instance ${id.unparse} ${ty.unparse}\n{\n${body.unparse}\n}"; 

  forwards to instanceDcl($1, nilConstraint(location=top.location), '=>', id, ty, $4, body, $6, location=top.location);
} action {
  insert semantic token IdTypeClass_t at id.baseNameLoc;
}

inherited attribute className::String;
inherited attribute instanceType::Type;
inherited attribute expectedClassMembers::[Pair<String Boolean>];

nonterminal InstanceBody with
  config, grammarName, env, defs, location, unparse, errors, compiledGrammars, className, instanceType, frameContexts, expectedClassMembers, definedMembers;
nonterminal InstanceBodyItem with
  config, grammarName, env, defs, location, unparse, errors, compiledGrammars, className, instanceType, frameContexts, expectedClassMembers, fullName;

propagate 
  config, grammarName, env, compiledGrammars, className, instanceType,
  defs, flowDefs, errors on InstanceBody, InstanceBodyItem;

concrete production consInstanceBody
top::InstanceBody ::= h::InstanceBodyItem t::InstanceBody
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;
  top.definedMembers = h.fullName :: t.definedMembers;

  h.expectedClassMembers = top.expectedClassMembers;
  t.expectedClassMembers =
    filter(\ m::Pair<String Boolean> -> m.fst != h.fullName, top.expectedClassMembers);
}
concrete production nilInstanceBody
top::InstanceBody ::= 
{
  top.unparse = "";
  top.definedMembers = [];

  top.errors <-
    flatMap(
      \ m::Pair<String Boolean> ->
        if m.snd then [] else [err(top.location, s"Missing instance member ${m.fst} for class ${top.className}")],
      top.expectedClassMembers);
}

concrete production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  top.unparse = s"${id.name} = ${e.unparse};";

  production typeScheme::PolyType = id.lookupValue.typeScheme;
  production memberSkolemVars::[TyVar] = freshTyVars(typeScheme.boundVars);
  production instSubst::Substitution =
    case typeScheme.contexts of
    -- Current class context is the first context on the member's type scheme
    | instContext(cls, ty) :: _ when cls == top.className ->
      composeSubst(
        unify(ty, top.instanceType),
        -- Skolemize all the other type vars that didn't get instantiated by the instance head
        zipVarsIntoSkolemizedSubstitution(typeScheme.boundVars, memberSkolemVars))
    | _ -> emptySubst() -- Fall back in case of errors
    end;
  production memberContexts::[Context] =
    case typeScheme.contexts of
    | _ :: cs -> map(performContextSubstitution(_, instSubst), cs)
    | _ -> []
    end;
  production boundVars::[TyVar] = top.instanceType.freeVariables ++ memberSkolemVars;

  top.errors <- id.lookupValue.errors;
  top.errors <-
    if !id.lookupValue.found || lookup(top.fullName, top.expectedClassMembers).isJust then []
    else [err(id.location, s"Unexpected instance member ${id.name} for class ${top.className}")]; 

  top.fullName = id.lookupValue.fullName;

  local cmDefs::[Def] =
    flatMap(
      \ c::Context -> c.contextMemberDefs(boundVars, top.grammarName, top.location),
      memberContexts);
  local cmOccursDefs::[OccursDclInfo] =
    flatMap(
      \ c::Context -> c.contextMemberOccursDefs(boundVars, top.grammarName, top.location),
      memberContexts);
  e.env =
    newScopeEnv(
      cmDefs ++ flatMap(transitiveSuperDefs(top.env, top.instanceType, [], _), flatMap((.instList), cmDefs)),
      occursEnv(
        cmOccursDefs ++ flatMap(transitiveSuperOccursDefs(top.env, top.instanceType, [], _), flatMap((.instList), cmDefs)),
        top.env));
  e.originRules = [];
  e.isRoot = true;

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = constructAnonymousGraph(e.flowDefs, top.env, myProds, myFlow);

  e.frame = globalExprContext(top.fullName, foldContexts(top.frameContexts), typeScheme.typerep, myFlowGraph, sourceGrammar=top.grammarName);
} action {
  insert semantic token IdTypeClassMember_t at id.baseNameLoc;
}
