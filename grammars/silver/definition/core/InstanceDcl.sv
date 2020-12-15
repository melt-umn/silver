grammar silver:definition:core;

concrete production instanceDcl
top::AGDcl ::= 'instance' cl::OptConstraintList id::QName ty::TypeExpr '{' body::InstanceBody '}'
{
  top.unparse = s"instance ${cl.unparse}${id.unparse} ${ty.unparse}\n{\n${body.unparse}\n}"; 

  production fName :: String = id.lookupType.fullName;
  production boundVars::[TyVar] = ty.freeVariables;
  production dcl::DclInfo = id.lookupType.dcl;
  dcl.givenInstanceType = ty.typerep;
  
  top.defs :=
    if ty.typerep.isError then []
    else [instDef(top.grammarName, id.location, fName, boundVars, cl.contexts, ty.typerep)];
  
  top.errors <- id.lookupType.errors;
  top.errors <-
    if dcl.isClass then []
    else [err(id.location, id.name ++ " is not a type class.")];
  top.errors <-
    if length(getInstanceDcl(fName, ty.typerep, top.env)) > 1
    then [err(id.location, "Overlapping instances exist for " ++ id.unparse ++ " " ++ ty.unparse)]
    else [];
  -- TODO: Orphaned instance check
  
  cl.instanceHead = just(instContext(fName, ty.typerep));

  production attribute headDefs :: [Def] with ++;
  headDefs := cl.defs;
  headDefs <- [instDef(top.grammarName, id.location, fName, boundVars, [], ty.typerep)];
  -- TODO: put members in env as a different kind of def?
  
  cl.env = newScopeEnv(headDefs, top.env);
  
  body.env = cl.env;
  body.className = id.lookupType.fullName;
  body.expectedClassMembers = id.lookupType.dcl.classMembers;
}

autocopy attribute className::String;
inherited attribute expectedClassMembers::[Pair<String Type>];

nonterminal InstanceBody with
  config, grammarName, env, defs, location, unparse, errors, compiledGrammars, className, expectedClassMembers;
nonterminal InstanceBodyItem with
  config, grammarName, env, defs, location, unparse, errors, compiledGrammars, className, expectedClassMembers, fullName;

propagate defs, errors on InstanceBody, InstanceBodyItem;

concrete production consInstanceBody
top::InstanceBody ::= h::InstanceBodyItem t::InstanceBody
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;
  
  t.expectedClassMembers =
    filter(\ m::Pair<String Type> -> m.fst != h.fullName, top.expectedClassMembers);
}
concrete production nilInstanceBody
top::InstanceBody ::= 
{
  top.unparse = "";

  top.errors <-
    map(
      \ m::Pair<String Type> -> err(top.location, s"Missing instance member ${m.fst} for class ${top.className}"),
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
}
