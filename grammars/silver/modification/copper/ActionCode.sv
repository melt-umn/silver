grammar silver:modification:copper;

terminal Action_kwd 'action' lexer classes {KEYWORD};

concrete production concreteProductionDclAction
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody 'action' acode::ActionCode_c
{
  top.unparse = forward.unparse ++ "action " ++ acode.unparse;

  production fName :: String = top.grammarName ++ ":" ++ id.name;

  top.syntaxAst = [
    syntaxProduction(ns.namedSignature,
      foldr(consProductionMod, nilProductionMod(), 
        prodAction(acode.actionCode) :: pm.productionModifiers))];

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(acode.flowDefs, top.env, myProds, myFlow);

  ns.signatureName = fName;
  acode.frame = reduceActionContext(ns.namedSignature, myFlowGraph);
  acode.env = newScopeEnv(productionActionVars ++ acode.defs ++ ns.actionDefs, top.env);

  top.errors <- acode.errors;

  -- note that we're not merging the typing contexts between action blocks and productions
  -- this seems reasonable since inference should never have effects across this border...

  forwards to concreteProductionDcl($1, $2, id, ns, pm, body, location=top.location);
}


nonterminal ActionCode_c with location,config,unparse,actionCode,env,defs,grammarName,errors,frame, compiledGrammars, flowEnv, flowDefs;

synthesized attribute actionCode :: String;

concrete production actionCode_c
top::ActionCode_c ::= '{' stmts::ProductionStmts '}'
{
  top.unparse = "{\n" ++ stmts.unparse ++ "}\n";
  top.defs = flatMap(hackTransformLocals, stmts.defs);
  top.flowDefs = stmts.flowDefs;

  top.actionCode = sflatMap(hacklocaldeclarations, stmts.defs) ++ stmts.translation;

  top.errors := stmts.errors;
  top.errors <- if top.frame.permitPluck && !stmts.containsPluck then
    [err(top.location, "Disambiguation function without pluck")] else [];
  
  stmts.downSubst = emptySubst();
}


-- Support code to check the validity of disambiguation blocks. True if any elements
-- contained in the snoc-list (so this statement or before) are a pluck. Handles
-- raising errors if there are statements after a pluck.
synthesized attribute containsPluck :: Boolean occurs on ProductionStmts;

aspect production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmt
{
  local immediateChildIsPluck :: Boolean = case t of
    | pluckDef(_, _, _) -> true
    | _ -> false
  end;

  top.containsPluck = immediateChildIsPluck || h.containsPluck;

  top.errors <- if top.frame.permitPluck && h.containsPluck then [err(t.location, "Statement after pluck")] else [];
}

aspect production productionStmtsNil
top::ProductionStmts ::=
{
  top.containsPluck = false;
}

-- TODO hacky. ideally we'd do this where local attributes are declared, not here.
function hacklocaldeclarations
String ::= d::Def
{
  return d.dcl.typerep.transType ++ " " ++ makeCopperName(d.dcl.fullName) ++ ";\n";
}

function hackTransformLocals
[Def] ::= d::Def
{
  return case d.dcl of
         | localDcl(sg,sl,fn,ty) -> [parserLocalDef(sg,sl,fn,ty)]
         | _ -> [] -- TODO: possibly error??
         end;
}

--------------------------------------------------------------------------------
-- Making children available in production action blocks

-- We don't care about the LHS.

synthesized attribute actionDefs :: [Def] occurs on ProductionSignature, ProductionRHS, ProductionRHSElem;

aspect production productionSignature
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
  top.actionDefs = rhs.actionDefs;
}

aspect production productionRHSNil
top::ProductionRHS ::= 
{
  top.actionDefs = [];
}

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.actionDefs = h.actionDefs ++ t.actionDefs;
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  top.actionDefs = [actionChildDef(top.grammarName, t.location, id.name, t.typerep)];
}

