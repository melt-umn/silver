grammar silver:compiler:extension:testing;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;
import silver:compiler:modification:collection;
import silver:compiler:extension:list;

import silver:compiler:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph; -- for the "oh no again!" hack below
import silver:compiler:driver:util only RootSpec; -- ditto

--import silver:compiler:analysis:typechecking:core;


terminal EqualityTest_t 'equalityTest' lexer classes {KEYWORD};

concrete production equalityTest2_p
ag::AGDcl ::= kwd::'equalityTest' 
              '(' value::Expr ',' expected::Expr ',' 
                  valueType::TypeExpr ',' testSuite::Name ')' ';'
{
  ag.unparse = "equalityTest (" ++ value.unparse ++ "," ++ expected.unparse ++ ",\n" ++ 
          "              " ++ valueType.unparse ++ ", " ++ testSuite.unparse ++ ");\n";

  local attribute errCheck1 :: TypeCheck; 
  local attribute errCheck2 :: TypeCheck; 
  local attribute errCheck3 :: TypeCheck; 
  errCheck1 = check(value.typerep, expected.typerep);
  errCheck2 = check(value.typerep, valueType.typerep);
  errCheck3 = check(expected.typerep, valueType.typerep);

  production attribute localErrors::[Message] with ++;
  localErrors := value.errors ++ expected.errors ++ valueType.errors;
  localErrors <-
    if !errCheck1.typeerror then []
    else [err(value.location, "Type of first and second expressions in equalityTest do not match. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];
  localErrors <-
    if !errCheck2.typeerror then []
    else [err(value.location, "Type of initial expression does not match specified type (3rd argument). Instead they are " ++ errCheck2.leftpp ++ " and " ++ errCheck2.rightpp)];
  localErrors <-
    if !errCheck3.typeerror then []
    else [err(value.location, "Type of second expression does not match specified type (3rd argument). Instead they are " ++ errCheck3.leftpp ++ " and " ++ errCheck3.rightpp)];

  local eqCtx::Context = instContext("silver:core:Eq", valueType.typerep);
  eqCtx.env = ag.env;
  eqCtx.contextLoc = valueType.location;
  eqCtx.contextSource = "equalityTest";
  localErrors <- eqCtx.contextErrors;

  value.downSubst = emptySubst();
  thread downSubst, upSubst on value, expected, errCheck1, errCheck2, errCheck3;
  
  value.finalSubst = errCheck3.upSubst;
  expected.finalSubst = errCheck3.upSubst;
  errCheck1.finalSubst = errCheck3.upSubst;
  errCheck2.finalSubst = errCheck3.upSubst;
  errCheck3.finalSubst = errCheck3.upSubst;

  -- TODO: one of those type error checks above is redundant

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(ag.grammarName, ag.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(ag.grammarName, ag.compiledGrammars)).productionFlowGraphs;

  value.frame = bogusContext(constructAnonymousGraph(value.flowDefs, ag.env, myProds, myFlow), sourceGrammar=ag.grammarName);
  expected.frame = bogusContext(constructAnonymousGraph(expected.flowDefs, ag.env, myProds, myFlow), sourceGrammar=ag.grammarName);
  
  value.isRoot = true;
  expected.isRoot = true;
  value.originRules = [];
  expected.originRules = [];

{- Causes some circularities with the environment. TODO
  forwards to if !errCheck1.typeerror && !errCheck2.typeerror && !errCheck3.typeerror
              then appendAGDcl(absProdCS, aspProdCS)
              else emptyAGDcl();
-}
  ag.errors := if null(localErrors) then forward.errors else localErrors;

  forwards to appendAGDcl(absProdCS, aspProdCS, location=ag.location);

{-
  local absProdCS :: AGDcl = asAGDcl (
   "abstract production " ++ testName ++ "\n" ++
   "t::Test ::= \n" ++
   "{ \n" ++
   "  local attribute value :: %%%Type valueType;  \n" ++
   "  value =  %%%Expr value; \n" ++
   "  local attribute expected :: %%%Type valueType;  \n" ++
   "  expected = %%%Expr expected; \n"  ++
   "  t.msg = \"Test at " ++ ag.location.unparse ++ " failed. \\n\" ++ \n" ++ 
   "          \"Checking that expression \\n\" ++ \n" ++
   "          \"   " ++ stringifyString(value.unparse) ++ "\" ++ \n" ++
   "          \"\\nshould be same as expression \\n\" ++ \n" ++
   "          \"   " ++ stringifyString(expected.unparse) ++ "\\n\" ++ \n" ++
   "          \"Actual value: \\n   \" ++ \n" ++
   "          %%%Expr toStringValueExpr ++ \"\\n\" ++ \n" ++
   "          \"Expected value: \\n   \" ++ \n" ++
   "          %%%Expr toStringExpectedExpr ++ \"\\n\" ++ \n" ++
   "         \"\";\n" ++
   "  t.pass = %%%Expr equalityTestCode; \n" ++ 
   "  forwards to defTest(); \n" ++
   "}" ,
   cons_CS_env("value", wrapExpr(value), 
   cons_CS_env("expected", wrapExpr(expected), 
   cons_CS_env("valueType", wrapType(valueType), 
   cons_CS_env("testSuite", wrapName(testSuite),
   cons_CS_env("toStringValueExpr", 
     wrapExpr( fromMaybe(error("TypeNotSupportedInternalError") ,toStringValueExpr)),
   cons_CS_env("toStringExpectedExpr",
     wrapExpr( fromMaybe(error("TypeNotSupportedInternalError") ,toStringExpectedExpr)),
   cons_CS_env("equalityTestCode",
     wrapExpr( fromMaybe(error("TypeNotSupportedInternalError") ,equalityTestExpr)) ,
   empty_CS_env()))))))) , 3 );
-}

  -- TODO: BUG: FIXME: these names should be mangled. I ran into 't' being shadowed in a test I wrote!
  local tref :: Name = name("t", ag.location);
  local testNameref :: Name = name(testName, ag.location);
  local valueref :: Name = name("value", ag.location);
  local expectedref :: Name = name("expected", ag.location);
  local msgref :: Name = name("msg", ag.location);
  local passref :: Name = name("pass", ag.location);
  
  -- TODO: Rewrite as Silver_AGDcl { ... }
  local absProdCS :: AGDcl =
    productionDcl('abstract', 'production', testNameref,
      productionSignature(
        nilConstraint(location=ag.location), '=>',
        productionLHS(tref, '::',
          nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "Test", ag.location), location=ag.location), location=ag.location), location=ag.location),
        '::=', productionRHSNil(location=ag.location), location=ag.location),
      productionBody('{', foldl(productionStmtsSnoc(_, _, location=ag.location), productionStmtsNil(location=ag.location), [
        localAttributeDcl('local', 'attribute', valueref, '::', valueType, ';', location=ag.location),
        valueEq(qNameId(valueref, location=valueref.location), '=', value, ';', location=ag.location),
        localAttributeDcl('local', 'attribute', expectedref, '::', valueType, ';', location=ag.location),
        valueEq(qNameId(expectedref, location=expectedref.location), '=', expected, ';', location=ag.location),
        attributeDef(concreteDefLHS(qNameId(tref, location=tref.location), location=tref.location), '.', qNameAttrOccur(qNameId(msgref, location=msgref.location), location=ag.location), '=',
          foldStringExprs([
            strCnst("Test at " ++ ag.location.unparse ++ " failed.\nChecking that expression\n   " ++
              stringifyString(value.unparse) ++ "\nshould be same as expression\n   " ++
              stringifyString(expected.unparse) ++ "\nActual value:\n   "),
            Silver_Expr { silver:testing:showTestValue(value) },
            strCnst("\nExpected value: \n   "),
            Silver_Expr { silver:testing:showTestValue(expected) },
            strCnst("\n")]), ';', location=ag.location),
        attributeDef(concreteDefLHS(qNameId(tref, location=tref.location), location=tref.location), '.', qNameAttrOccur(qNameId(passref, location=passref.location), location=ag.location), '=',
           Silver_Expr { value == expected }, ';', location=ag.location),
        forwardsTo('forwards', 'to', mkStrFunctionInvocation(ag.location, "defTest", []), ';', location=ag.location)]), '}', location=ag.location), location=ag.location);

{-
  local aspProdCS :: AGDcl = asAGDcl (
   "aspect production %%%Name testSuite \n" ++
   "t ::=  \n" ++
   "{ testsToPerform <- [ " ++ testName ++ "() ]; } " ,
   cons_CS_env("testSuite", wrapName(testSuite), empty_CS_env()) , 4 );
-}

  local aspProdCS :: AGDcl =
    aspectProductionDcl('aspect', 'production', qNameId(testSuite, location=ag.location),
      aspectProductionSignature(
        aspectProductionLHSId(tref, location=ag.location),
          '::=', aspectRHSElemNil(location=ag.location), location=ag.location),
      productionBody('{',
        productionStmtsSnoc(
          productionStmtsNil(location=ag.location),
          valContainsAppend(
            qName(ag.location, "testsToPerform"),
            '<-',
            fullList('[',
              exprsSingle(
                applicationEmpty(
                  baseExpr(qNameId(testNameref, location=ag.location), location=ag.location), '(', ')', location=ag.location), location=ag.location),
              ']', location=ag.location),
            ';', location=ag.location), location=ag.location), '}', location=ag.location), location=ag.location);

  local testName :: String = "generatedTest" ++ "_" ++ 
                            substitute(".","_",kwd.filename) ++ "_" ++ 
                            toString(kwd.line) ++ "_" ++ 
                            toString(genInt());
}

