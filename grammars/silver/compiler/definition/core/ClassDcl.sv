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
    | _ -> freshTyVar(starKind())
    end;
  production supers::[Context] = cl.contexts; -- *Direct* super classes only, not transitive
  production boundVars::[TyVar] = [tv];
  
  top.defs := classDef(top.grammarName, id.location, fName, supers, tv, var.typerep.kindrep, body.classMembers) :: body.defs;
  
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

  cl.constraintPos = classPos(fName, var.freeVariables);
  cl.env = newScopeEnv(headPreDefs, top.env);
  
  id.env = cl.env;
  var.env = cl.env;
  
  body.env = occursEnv(cl.occursDefs, newScopeEnv(headDefs, cl.env));
  body.constraintEnv = cl.env;
  body.classHead = instContext(fName, var.typerep);
  body.frameContexts = supers;
} action {
  insert semantic token IdTypeClassDcl_t at id.baseNameLoc;
}

concrete production typeClassDclNoCL
top::AGDcl ::= 'class' id::QNameType var::TypeExpr '{' body::ClassBody '}'
{
  top.unparse = s"class ${id.unparse} ${var.unparse}\n{\n${body.unparse}\n}";

  forwards to typeClassDcl($1, nilConstraint(location=top.location), '=>', @id, @var, $4, @body, $6, location=top.location);
} action {
  insert semantic token IdTypeClassDcl_t at id.baseNameLoc;
}

inherited attribute classHead::Context;
inherited attribute constraintEnv::Env;
inherited attribute frameContexts::[Context];  -- Only used for computing frame in members

nonterminal ClassBody with
  config, grammarName, env, defs, location, unparse, errors, lexicalTypeVariables, lexicalTyVarKinds, classHead, constraintEnv, frameContexts, compiledGrammars, classMembers;
nonterminal ClassBodyItem with
  config, grammarName, env, defs, location, unparse, errors, lexicalTypeVariables, lexicalTyVarKinds, classHead, constraintEnv, frameContexts, compiledGrammars, classMembers;

propagate
  config, grammarName, errors, lexicalTypeVariables, lexicalTyVarKinds, classHead, constraintEnv, frameContexts, compiledGrammars
  on ClassBody, ClassBodyItem;
propagate env, defs on ClassBody;

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
  forwards to constraintClassBodyItem(@id, $2, nilConstraint(location=top.location), '=>', @ty, $4, location=top.location);
} action {
  insert semantic token IdTypeClassMemberDcl_t at id.location;
}

concrete production constraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr ';'
{
  top.unparse = s"${id.name} :: ${cl.unparse} => ${ty.unparse};";
  
  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production boundVars :: [TyVar] =
    setUnionTyVarsAll(top.classHead.freeVariables :: map((.freeVariables), cl.contexts) ++ [ty.typerep.freeVariables]);
  top.classMembers = [(fName, false)];
  
  cl.constraintPos =
    case top.classHead of
    | instContext(cls, _) -> classMemberPos(cls, boundVars)
    | _ -> error("Class head is not an instContext")
    end;
  cl.env = top.constraintEnv;

  ty.env = top.env;
  
  top.defs := [classMemberDef(top.grammarName, top.location, fName, boundVars, top.classHead, cl.contexts, ty.typerep)];

  top.errors <-
    if length(getValueDclAll(fName, top.env)) > 1
    then [err(id.location, "Value '" ++ fName ++ "' is already bound.")]
    else [];
} action {
  insert semantic token IdTypeClassMemberDcl_t at id.location;
}

concrete production defaultClassBodyItem
top::ClassBodyItem ::= id::Name '::' ty::TypeExpr '=' e::Expr ';'
{
  forwards to defaultConstraintClassBodyItem(@id, $2, nilConstraint(location=top.location), '=>', @ty, $4, @e, $6, location=top.location);
} action {
  insert semantic token IdTypeClassMemberDcl_t at id.location;
}

concrete production defaultConstraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr '=' e::Expr ';'
{
  top.unparse = s"${id.name} :: ${cl.unparse} => ${ty.unparse} = ${e.unparse};";
  
  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production boundVars :: [TyVar] =
    setUnionTyVarsAll(top.classHead.freeVariables :: map((.freeVariables), cl.contexts) ++ [ty.typerep.freeVariables]);
  top.classMembers = [(fName, true)];
  
  cl.constraintPos =
    case top.classHead of
    | instContext(cls, _) -> classMemberPos(cls, boundVars)
    | _ -> error("Class head is not an instContext")
    end;
  cl.env = top.constraintEnv;

  ty.env = top.env;
  
  e.isRoot = true;
  e.originRules = [];

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = constructAnonymousGraph(e.flowDefs, top.env, myProds, myFlow);

  e.frame = globalExprContext(fName, foldContexts(top.frameContexts ++ cl.contexts), ty.typerep, myFlowGraph, sourceGrammar=top.grammarName);
  e.env = occursEnv(cl.occursDefs, newScopeEnv(cl.defs, top.env));
  
  top.defs := [classMemberDef(top.grammarName, top.location, fName, boundVars, top.classHead, cl.contexts, ty.typerep)];

  top.errors <-
    if length(getValueDclAll(fName, top.env)) > 1
    then [err(id.location, "Value '" ++ fName ++ "' is already bound.")]
    else [];
} action {
  insert semantic token IdTypeClassMemberDcl_t at id.location;
}

-- TODO: Defaults
