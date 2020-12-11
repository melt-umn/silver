grammar silver:definition:core;

concrete production instanceDcl
top::AGDcl ::= 'instance' cl::OptConstraintList id::QName ty::TypeExpr '{' body::InstanceBody '}'
{
  top.unparse = s"instance ${cl.unparse}${id.unparse} ${ty.unparse}\n{\n${body.unparse}\n}"; 

  production fName :: String = id.lookupType.fullName;
  production supers::[Context] = cl.contexts;
  production boundVars::[TyVar] = setUnionTyVarsAll(ty.freeVariables :: map((.freeVariables), supers));
  
  top.defs := [instDef(top.grammarName, id.location, fName, boundVars, cl.contexts, ty.typerep)];
  
  top.errors <- id.lookupType.errors;
  top.errors <-
    if id.lookupType.dcl.isClass then []
    else [err(id.location, id.name ++ " is not a type class.")];

  production attribute headDefs :: [Def] with ++;
  headDefs := cl.defs;
  headDefs <- [instDef(top.grammarName, id.location, fName, boundVars, [], ty.typerep)];
  
  body.env = newScopeEnv(headDefs, top.env);
  body.className = id.lookupType.fullName;
  body.expectedClassMembers = id.lookupType.dcl.classMembers;
}

autocopy attribute className::String;
inherited attribute expectedClassMembers::[String];

nonterminal InstanceBody with
  config, grammarName, env, defs, location, unparse, errors, compiledGrammars, className, expectedClassMembers;
nonterminal InstanceBodyItem with
  config, grammarName, env, defs, location, unparse, errors, compiledGrammars, className, expectedClassMembers, fullName;

propagate defs, errors on InstanceBody, InstanceBodyItem;

concrete production consInstanceBody
top::InstanceBody ::= h::InstanceBodyItem t::InstanceBody
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;
  
  t.expectedClassMembers = removeBy(stringEq, h.fullName, top.expectedClassMembers);
}
concrete production nilInstanceBody
top::InstanceBody ::= 
{
  top.unparse = "";

  top.errors <-
    map(
      \ m::String -> err(top.location, s"Missing instance member ${m} for class ${top.className}"),
      top.expectedClassMembers);
}

concrete production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  top.unparse = s"${id.name} = ${e.unparse};";
  
  top.errors <- id.lookupValue.errors;
  top.errors <-
    if containsBy(stringEq, top.fullName, top.expectedClassMembers) then []
    else [err(id.location, s"Unexpected instance member ${id.name} for class ${top.className}")]; 
  
  top.fullName = id.lookupValue.fullName;
}

-- TODO: Defaults
