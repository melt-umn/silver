grammar silver:modification:defaultattr;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax;
--import silver:analysis:typechecking:core;
import silver:translation:java;

import silver:definition:flow:driver only ProductionGraph, FlowType, constructDefaultProductionGraph; -- for the "oh no again!" hack below
import silver:driver:util only RootSpec; -- ditto

terminal Default_kwd 'default' lexer classes {KEYWORD, RESERVED};

concrete production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' 
               lhs::Name '::' te::TypeExpr '::=' body::ProductionBody 
{
  top.unparse = "aspect default production\n" ++ lhs.unparse ++ "::" ++ te.unparse ++ " ::=\n" ++ body.unparse;

  top.defs := [];

  production namedSig :: NamedSignature = 
    namedSignature(top.grammarName ++ ":default" ++ te.typerep.typeName, [], [],
      namedSignatureElement(lhs.name, te.typerep),
      annotationsForNonterminal(te.typerep, top.env));

  propagate errors, flowDefs;

  top.errors <-
    if te.typerep.kindArity > 0
    then [err(te.location, s"Type ${te.unparse} is not fully applied, it has kind arity ${toString(te.typerep.kindArity)}")]
    else [];

  local fakedDefs :: [Def] =
    [defaultLhsDef(top.grammarName, lhs.location, lhs.name, te.typerep)];
  
  local sigDefs :: [Def] =
    addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, te.lexicalTypeVariables);

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructDefaultProductionGraph(namedSig, body.flowDefs, top.env, myProds, myFlow);


  body.env = newScopeEnv(fakedDefs ++ sigDefs, top.env);
  body.frame = defaultAspectContext(namedSig, myFlowGraph, sourceGrammar=top.grammarName);

  body.downSubst = emptySubst();

  top.setupInh := body.setupInh; -- Probably should be empty?
  top.initProd := "\t\t//ASPECT DEFAULT PRODUCTION for " ++ te.unparse ++ "\n" ++ body.translation;
  top.valueWeaving := body.valueWeaving; -- Probably should be empty?
}

function defaultLhsDef
Def ::= sg::String sl::Location fn::String ty::Type
{
  return valueDef(defaultEnvItem(defaultLhsDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}
abstract production defaultLhsDcl
top::DclInfo ::= fn::String ty::Type
{
  top.fullName = fn;

  top.typeScheme = monoType(ty);
  
  top.refDispatcher = lhsReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_); -- TODO: be smarter about the error message
  top.defLHSDispatcher = defaultLhsDefLHS(_, location=_);
}

abstract production defaultLhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = !existingProblems && top.defLHSattr.attrDcl.isSynthesized;
  
  local existingProblems :: Boolean = !top.defLHSattr.found || top.typerep.isError;
  
  top.errors :=
    if existingProblems || top.found then []
    else [err(q.location, "Cannot define inherited attribute '" ++ top.defLHSattr.name ++ "' on the lhs '" ++ q.name ++ "'")];
  
  top.typerep = q.lookupValue.typeScheme.monoType;

  top.translation = makeNTName(top.frame.lhsNtName) ++ ".defaultSynthesizedAttributes";
}

abstract production defaultAspectContext
top::BlockContext ::= sig::NamedSignature  g::ProductionGraph
{
  top.fullName = sig.fullName;
  top.lhsNtName = sig.outputElement.typerep.typeName;
  top.signature = sig;
  top.flowGraph = g;
}

