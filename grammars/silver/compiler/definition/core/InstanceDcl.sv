grammar silver:compiler:definition:core;

import silver:compiler:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph;

concrete production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  top.unparse = s"instance ${cl.unparse} => ${id.unparse} ${ty.unparse}\n{\n${body.unparse}\n}"; 

  production fName :: String = id.lookupType.fullName;
  production boundVars::[TyVar] = ty.freeVariables;
  production dcl::DclInfo = id.lookupType.dcl;
  dcl.givenInstanceType = ty.typerep;
  
  production superContexts::Contexts = foldContexts(if id.lookupType.found then dcl.superContexts else []);
  superContexts.env = body.env;
  
  top.defs := [instDef(top.grammarName, id.location, fName, boundVars, cl.contexts, ty.typerep)];
  
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
          dcl.sourceGrammar :: map(\ d::DclInfo -> d.sourceGrammar, getTypeDcl(t.typeName, top.env)),
          top.compiledGrammars) ->
      [wrn(top.location, s"Orphaned instance declaration for ${fName} ${prettyType(t)}")]
    | _ -> []
    end;
  
  cl.instanceHead = just(instContext(fName, ty.typerep));
  cl.constraintSigName = nothing();

  production attribute headPreDefs :: [Def] with ++;
  headPreDefs := [];

  production attribute headDefs :: [Def] with ++;
  headDefs := cl.defs;
  headDefs <- [currentInstDef(top.grammarName, id.location, fName, ty.typerep)];
  
  cl.env = newScopeEnv(headPreDefs, top.env);
  ty.env = cl.env;
  
  body.env = newScopeEnv(headDefs, cl.env);
  body.className = id.lookupType.fullName;
  body.expectedClassMembers = if id.lookupType.found then dcl.classMembers else [];
}

concrete production instanceDclNoCL
top::AGDcl ::= 'instance' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  top.unparse = s"instance ${id.unparse} ${ty.unparse}\n{\n${body.unparse}\n}"; 

  forwards to instanceDcl($1, nilConstraint(location=top.location), '=>', id, ty, $4, body, $6, location=top.location);
}

autocopy attribute className::String;
inherited attribute expectedClassMembers::[Pair<String Pair<Type Boolean>>];

nonterminal InstanceBody with
  config, grammarName, env, defs, flowEnv, location, unparse, errors, compiledGrammars, className, expectedClassMembers;
nonterminal InstanceBodyItem with
  config, grammarName, env, defs, flowEnv, location, unparse, errors, compiledGrammars, className, expectedClassMembers, fullName;

propagate defs, errors on InstanceBody, InstanceBodyItem;

concrete production consInstanceBody
top::InstanceBody ::= h::InstanceBodyItem t::InstanceBody
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;

  h.expectedClassMembers = top.expectedClassMembers;
  t.expectedClassMembers =
    filter(\ m::Pair<String Pair<Type Boolean>> -> m.fst != h.fullName, top.expectedClassMembers);
}
concrete production nilInstanceBody
top::InstanceBody ::= 
{
  top.unparse = "";

  top.errors <-
    flatMap(
      \ m::Pair<String Pair<Type Boolean>> ->
        if m.snd.snd then [] else [err(top.location, s"Missing instance member ${m.fst} for class ${top.className}")],
      top.expectedClassMembers);
}

concrete production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  top.unparse = s"${id.name} = ${e.unparse};";

  top.errors <- id.lookupValue.errors;
  top.errors <-
    if !id.lookupValue.found || lookupBy(stringEq, top.fullName, top.expectedClassMembers).isJust then []
    else [err(id.location, s"Unexpected instance member ${id.name} for class ${top.className}")]; 

  top.fullName = id.lookupValue.fullName;

  e.originRules = [];
  e.isRoot = true;

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(e.flowDefs, top.env, myProds, myFlow);

  e.frame = globalExprContext(myFlowGraph, sourceGrammar=top.grammarName);
}
