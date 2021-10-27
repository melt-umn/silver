grammar silver:compiler:modification:defaultattr;

import silver:compiler:definition:core;
import silver:compiler:definition:origins;
import silver:compiler:definition:env;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;
--import silver:compiler:analysis:typechecking:core;
import silver:compiler:translation:java;

import silver:compiler:definition:flow:driver only ProductionGraph, FlowType, constructDefaultProductionGraph; -- for the "oh no again!" hack below
import silver:compiler:driver:util only RootSpec; -- ditto

terminal Default_kwd 'default' lexer classes {KEYWORD, RESERVED};

concrete production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' 
               lhs::Name '::' te::TypeExpr '::=' body::ProductionBody 
{
  top.unparse = "aspect default production\n" ++ lhs.unparse ++ "::" ++ te.unparse ++ " ::=\n" ++ body.unparse;

  top.defs := [];

  production namedSig :: NamedSignature = 
    namedSignature(top.grammarName ++ ":default" ++ te.typerep.typeName,
      nilContext(), nilNamedSignatureElement(),
      namedSignatureElement(lhs.name, te.typerep),
      foldNamedSignatureElements(annotationsForNonterminal(te.typerep, top.env)));

  propagate errors, flowDefs;

  top.errors <- te.errorsKindStar;
  top.errors <-
    case te of
    -- LHS must be either NT or NT<a b ...> where a b ... are all ty vars
    | appTypeExpr(_, tl) -> tl.errorsTyVars
    | _ -> []
    end;

  local checkNT::TypeCheck = checkNonterminal(top.env, false, te.typerep);
  checkNT.downSubst = emptySubst();
  checkNT.finalSubst = emptySubst();

  top.errors <-
    if checkNT.typeerror
    then [err(top.location, "Default production LHS type must be a nonterminal.  Instead it is of type " ++ checkNT.leftpp)]
    else [];

  local fakedDefs :: [Def] =
    [defaultLhsDef(top.grammarName, lhs.location, lhs.name, te.typerep)];
  
  local sigDefs :: [Def] = addNewLexicalTyVars(top.grammarName, top.location, te.lexicalTyVarKinds, te.lexicalTypeVariables);

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructDefaultProductionGraph(namedSig, body.flowDefs, top.env, myProds, myFlow);

  te.env = newScopeEnv(sigDefs, top.env);

  body.env = newScopeEnv(fakedDefs, te.env);
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
top::ValueDclInfo ::= fn::String ty::Type
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
  top.originsContextSource = useContextLhsAndRules();
}
