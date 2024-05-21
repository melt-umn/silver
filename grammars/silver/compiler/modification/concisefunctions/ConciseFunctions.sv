grammar silver:compiler:modification:concisefunctions;

import silver:util:treeset as ts;

terminal Fun_kwd 'fun' lexer classes {KEYWORD};

{--
 - Concise function declarations - these are conceptually similar to globals with lambda expressions
 - @param id The name of the concise function
 - @param ns The signature of the function
 - @param e The expression that serves as the body of the function
 -}
concrete production shortFunctionDcl
top::AGDcl ::= 'fun' id::Name ns::FunctionSignature '=' e::Expr ';'
{
  top.unparse = "fun " ++ id.unparse ++ ns.unparse ++ " = " ++ e.unparse ++ ";";

  propagate grammarName, compiledGrammars, config, errors;
  
  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production namedSig :: NamedSignature = ns.namedSignature;

  top.defs := [shortFunDef(top.grammarName, id.nameLoc, namedSig)];

  local errCheck1 :: TypeCheck = check(e.typerep, namedSig.outputElement.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [errFromOrigin(e, s"Expected result type is ${errCheck1.rightpp}, but the expression has type ${errCheck1.leftpp}.")]
    else [];

  e.downSubst = emptySubst();
  errCheck1.downSubst = e.upSubst;

  e.finalSubst = errCheck1.upSubst;
  errCheck1.finalSubst = errCheck1.upSubst;

  top.errors <-
    if length(getValueDclAll(fName, top.env)) > 1
    then [errFromOrigin(id, "Value '" ++ fName ++ "' is already bound.")]
    else [];

  production attribute sigDefs :: [Def] with ++;
  sigDefs := ns.shortFunctionDefs;

  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = nub(ns.lexicalTypeVariables);
  sigDefs <- addNewLexicalTyVars(top.grammarName, ns.lexicalTyVarKinds, allLexicalTyVars);

  ns.signatureName = fName;
  ns.env = newScopeEnv(sigDefs, top.env);

  e.env = occursEnv(ns.shortFunctionOccursDefs, newScopeEnv(sigDefs ++ ns.shortFunctionConstraintDefs, top.env));

  e.frame = functionContext(namedSig, myFlowGraph, sourceGrammar=top.grammarName);
  e.originRules = [];
  e.isRoot = true;
}

monoid attribute shortFunctionDefs::[Def] occurs on FunctionSignature, ProductionRHS, ProductionRHSElem;
synthesized attribute shortFunctionConstraintDefs::[Def] occurs on FunctionSignature;
synthesized attribute shortFunctionOccursDefs::[OccursDclInfo] occurs on FunctionSignature;
propagate shortFunctionDefs on FunctionSignature, ProductionRHS;

aspect production functionSignature
top::FunctionSignature ::= cl::ConstraintList '=>' lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  -- Need to override constraintPos
  production clGlobal::ConstraintList = new(cl);
  clGlobal.env = top.env;
  clGlobal.flowEnv = top.flowEnv;
  clGlobal.grammarName = top.grammarName;
  clGlobal.constraintPos = globalPos(top.namedSignature.freeVariables, sourceGrammar=top.grammarName);

  top.shortFunctionConstraintDefs = clGlobal.defs;
  top.shortFunctionOccursDefs = clGlobal.occursDefs;
}

aspect shortFunctionDefs on top::ProductionRHSElem using := of
| productionRHSElem(_, id, _, t) -> [shortFunParamDef(top.grammarName, id.nameLoc, id.name, t.typerep)]
| productionRHSElemType(_, _) -> []
end;

abstract production shortFunParamReference
top::Expr ::= q::Decorated! QName
{
  undecorates to baseExpr(q);
  top.unparse = q.unparse;

  propagate errors;
  top.freeVars := ts:fromList([q.name]);
  
  top.typerep = q.lookupValue.typeScheme.monoType;

  propagate downSubst, upSubst;
}
