grammar silver:compiler:definition:core;

import silver:compiler:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph;

concrete production typeClassDcl
top::AGDcl ::= 'class' cl::ConstraintList '=>' id::QNameType var::TypeExpr '{' body::ClassBody '}'
{
  top.unparse = s"class ${cl.unparse} => ${id.unparse} ${var.unparse}\n{\n${body.unparse}\n}"; 

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production tv :: TyVar =
    case var.typerep.freeVariables of
    | v :: _ -> v
    | _ -> freshTyVar(0)
    end;
  production supers::[Context] = cl.contexts; -- *Direct* super classes only, not transitive
  production boundVars::[TyVar] = [tv];
  
  top.defs := classDef(top.grammarName, id.location, fName, supers, tv, var.typerep.kindArity, body.classMembers) :: body.defs;
  
  -- id *should* be just a Name, but it has to be a QNameType to avoid a reduce/reduce conflict
  top.errors <-
    if indexOf(":", id.name) == -1 then []
    else [err(id.location, "Class name must be unqualified.")];

  -- Here we ensure that the type is just a type *variable*
  top.errors <- var.errorsTyVars;
  
  -- Redefinition check of the name
  top.errors <- 
    if length(getTypeDclAll(fName, top.env)) > 1 
    then [err(id.location, "Type '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <-
    if isLower(substring(0,1,id.name))
    then [err(id.location, "Types must be capitalized. Invalid class name " ++ id.name)]
    else [];
    
  top.errors <-
    if contains(fName, catMaybes(map((.contextClassName), transitiveSuperContexts(top.env, var.typerep, [], fName))))
    then [err(top.location, "Cycle exists in superclass relationships.")]
    else [];

  production attribute headPreDefs :: [Def] with ++;
  headPreDefs := [];

  production attribute headDefs :: [Def] with ++;
  headDefs := cl.defs;
  headDefs <- [currentInstDef(top.grammarName, id.location, fName, var.typerep)];

  cl.instanceHead = nothing();
  cl.constraintSigName = nothing();
  cl.env = newScopeEnv(headPreDefs, top.env);
  
  var.env = cl.env;
  
  body.env = newScopeEnv(headDefs, cl.env);
  body.classHead = instContext(fName, var.typerep);
}

concrete production typeClassDclNoCL
top::AGDcl ::= 'class' id::QNameType var::TypeExpr '{' body::ClassBody '}'
{
  top.unparse = s"${id.unparse} ${var.unparse}\n{\n${body.unparse}\n}";

  forwards to typeClassDcl($1, nilConstraint(location=top.location), '=>', id, var, $4, body, $6, location=top.location);
}

autocopy attribute classHead::Context;

nonterminal ClassBody with
  config, grammarName, env, defs, location, unparse, errors, lexicalTypeVariables, lexicalTyVarKinds, classHead, compiledGrammars, classMembers;
nonterminal ClassBodyItem with
  config, grammarName, env, defs, location, unparse, errors, lexicalTypeVariables, lexicalTyVarKinds, classHead, compiledGrammars, classMembers;

propagate defs, errors,lexicalTypeVariables, lexicalTyVarKinds on ClassBody, ClassBodyItem;

concrete production consClassBody
top::ClassBody ::= h::ClassBodyItem t::ClassBody
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;
  top.classMembers = h.classMembers ++ t.classMembers;
}
concrete production nilClassBody
top::ClassBody ::= 
{
  top.unparse = "";
  top.classMembers = [];
}

concrete production classBodyItem
top::ClassBodyItem ::= id::Name '::' ty::TypeExpr ';'
{
  top.unparse = s"${id.name} :: ${ty.unparse};";
  
  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production boundVars :: [TyVar] =
    setUnionTyVars(top.classHead.freeVariables, ty.typerep.freeVariables);
  top.classMembers = [pair(fName, pair(ty.typerep, false))];
  
  top.defs <- [classMemberDef(top.grammarName, top.location, fName, boundVars, top.classHead, ty.typerep)];

  top.errors <-
    if length(getValueDclAll(fName, top.env)) > 1
    then [err(id.location, "Value '" ++ fName ++ "' is already bound.")]
    else [];
}

concrete production defaultClassBodyItem
top::ClassBodyItem ::= id::Name '::' ty::TypeExpr '=' e::Expr ';'
{
  top.unparse = s"${id.name} :: ${ty.unparse} = ${e.unparse};";
  
  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production boundVars :: [TyVar] =
    setUnionTyVars(top.classHead.freeVariables, ty.typerep.freeVariables);
  top.classMembers = [pair(fName, pair(ty.typerep, true))];
  
  e.isRoot = true;
  e.originRules = [];

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(e.flowDefs, top.env, myProds, myFlow);

  e.frame = globalExprContext(myFlowGraph, sourceGrammar=top.grammarName);
  
  top.defs <- [classMemberDef(top.grammarName, top.location, fName, boundVars, top.classHead, ty.typerep)];

  top.errors <-
    if length(getValueDclAll(fName, top.env)) > 1
    then [err(id.location, "Value '" ++ fName ++ "' is already bound.")]
    else [];
}

-- TODO: Defaults
