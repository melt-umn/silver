grammar silver:extension:bidirtransform;

abstract production fakeAspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.pp = "aspect production " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp;

  top.defs = [];

  production namedSig :: NamedSignature = ns.namedSignature;

  production attribute realSig :: NamedSignature;
  realSig = if null(id.lookupValue.errors)
            then id.lookupValue.dcl.namedSignature
            else bogusNamedSignature();

  -- Making sure we're aspecting a production is taken care of by type checking.

  top.errors := id.lookupValue.errors ++ ns.errors ++ body.errors;

  production attribute sigDefs :: [Def] with ++;
  sigDefs := ns.defs;

  ns.signatureName = id.lookupValue.fullName;
  ns.env = newScopeEnv(sigDefs, top.env);  
  ns.realSignature = if null(id.lookupValue.dcls) then [] else [realSig.outputElement] ++ realSig.inputElements;

  local attribute prodAtts :: [Def];
  prodAtts = if null(id.lookupValue.errors)
             then defsFromPADcls(getProdAttrs(id.lookupValue.fullName, top.env), namedSig)
             else [];

  production myFlowGraph :: ProductionGraph = 
  findProductionGraph(id.lookupValue.fullName, myGraphs);

  body.env = newScopeEnv(body.defs ++ sigDefs, newScopeEnv(prodAtts, top.env));
  body.frame = aspectProductionContext(namedSig, myFlowGraph); -- graph from flow:env
}

aspect production fakeAspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  top.setupInh := body.setupInh;
  top.initProd := "\t\t//ASPECT PRODUCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;
  top.valueWeaving := body.valueWeaving;
}

aspect production fakeAspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  top.docs := [bodilessDclCommentItem("aspect production", id.name, ns.pp, id.location.filename)];
}

aspect production fakeAspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
    body.prodOutput = ns.namedSignature.outputElement;
}

aspect production fakeAspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = makeSet(ns.lexicalTypeVariables);
  
  sigDefs <- addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, allLexicalTyVars);
}

aspect production fakeAspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  -- TODO: bit of a hack, isn't it?
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  {-- Used by core to send down with .frame -}
  production myFlowGraph :: ProductionGraph = 
    findProductionGraph(id.lookupValue.fullName, myGraphs);

  top.flowDefs = body.flowDefs;
}

aspect production fakeAspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.foldableRanges = [body.location];
}

aspect production fakeAspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = ns.finalSubst;

  errCheck1 = check(id.lookupValue.typerep, namedSig.typerep);
  top.errors <-
        if errCheck1.typeerror
        then [err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.\nExpected: "
                                ++ errCheck1.leftpp ++ "\nActual: "
                                ++ errCheck1.rightpp)]
        else 
        -- dcl is potentially not found, accessing it can crash.
        -- so check on dcls for this.
        case id.lookupValue.dcls of
        | prodDcl (_, _, _) :: _ -> [ ]
        | funDcl  (_, _, _) :: _ -> [err(top.location, "Production aspect for '" ++ id.name ++ "' should be a 'function' aspect instead.") ]
        | _ -> [ ] 
        end ;

  ns.downSubst = emptySubst();
  errCheck1.downSubst = ns.upSubst;
  body.downSubst = errCheck1.upSubst;
  
  ns.finalSubst = errCheck1.upSubst;
}