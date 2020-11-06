grammar silver:extension:testing;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:modification:collection;
import silver:extension:list;

import silver:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph; -- for the "oh no again!" hack below
import silver:driver:util only RootSpec; -- ditto

--import silver:analysis:typechecking:core;

import lib:extcore;

terminal EqualityTest_t 'equalityTest' lexer classes {KEYWORD};

concrete production equalityTest2_p
ag::AGDcl ::= kwd::'equalityTest' 
              '(' value::Expr ',' expected::Expr ',' 
                  valueType::TypeExpr ',' testSuite::Name ')' ';'
{
  ag.unparse = "equalityTest (" ++ value.unparse ++ "," ++ expected.unparse ++ ",\n" ++ 
          "              " ++ valueType.unparse ++ ", " ++ testSuite.unparse ++ ");\n";

  ag.errors := case equalityTestExpr of
               | just(_) -> []
               | nothing() -> 
                   [err(valueType.location, "Type \"" ++ valueType.unparse ++ "\" not suported on equality tests.")]
               end;

  local attribute errCheck1 :: TypeCheck; 
  local attribute errCheck2 :: TypeCheck; 
  local attribute errCheck3 :: TypeCheck; 
  errCheck1 = check(value.typerep, expected.typerep);
  errCheck2 = check(value.typerep, valueType.typerep);
  errCheck3 = check(expected.typerep, valueType.typerep);

  ag.errors <-
    if !errCheck1.typeerror then []
    else [err(value.location, "Type of first and second expressions in equalityTest do not match. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  ag.errors <-
    if !errCheck2.typeerror then []
    else [err(value.location, "Type of initial expression does not match specified type (3rd argument). Instead they are " ++ errCheck2.leftpp ++ " and " ++ errCheck2.rightpp)];

  ag.errors <-
    if !errCheck3.typeerror then []
    else [err(value.location, "Type of second expression does not match specified type (3rd argument). Instead they are " ++ errCheck3.leftpp ++ " and " ++ errCheck3.rightpp)];

  value.downSubst = emptySubst();
  expected.downSubst = value.upSubst;
  errCheck1.downSubst = expected.upSubst;
  errCheck2.downSubst = errCheck1.upSubst;
  errCheck3.downSubst = errCheck2.upSubst;
  
  value.finalSubst = errCheck3.upSubst;
  expected.finalSubst = errCheck3.upSubst;
  errCheck1.finalSubst = errCheck3.upSubst;
  errCheck2.finalSubst = errCheck3.upSubst;
  errCheck3.finalSubst = errCheck3.upSubst;

  -- TODO: one of those type error checks above is redundant

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(ag.grammarName, ag.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(ag.grammarName, ag.compiledGrammars)).productionFlowGraphs;

  value.frame = globalExprContext(constructAnonymousGraph(value.flowDefs, ag.env, myProds, myFlow));
  expected.frame = globalExprContext(constructAnonymousGraph(expected.flowDefs, ag.env, myProds, myFlow));
  

  ag.errors <- forward.errors;

{- Causes some circularities with the environment. TODO
  forwards to if !errCheck1.typeerror && !errCheck2.typeerror && !errCheck3.typeerror
              then appendAGDcl(absProdCS, aspProdCS)
              else emptyAGDcl();
-}

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
  
  local absProdCS :: AGDcl =
    productionDcl('abstract', 'production', testNameref,
      productionSignature(
        productionLHS(tref, '::',
          nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "Test", ag.location), location=ag.location), botlNone(location=ag.location), location=ag.location), location=ag.location),
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
            toStringValueExpr.fromJust,
            strCnst("\nExpected value: \n   "),
            toStringExpectedExpr.fromJust,
            strCnst("\n")]), ';', location=ag.location),
        attributeDef(concreteDefLHS(qNameId(tref, location=tref.location), location=tref.location), '.', qNameAttrOccur(qNameId(passref, location=passref.location), location=ag.location), '=',
           equalityTestExpr.fromJust, ';', location=ag.location),
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



  -- If valueType is a base type (Integer, Float, etc.) or a List whose
  -- element type is a base type, then we can check for equality.
  -- With curried functions we could handle nested lists, but not now.
  local equalityTestExpr :: Maybe<Expr> =
    mkEqualityTestExprCS(valueType, ag.location);

  local toStringValueExpr :: Maybe<Expr> =
    mkToStringExprCS(valueType, "value", ag.location);
  local toStringExpectedExpr :: Maybe<Expr> =
    mkToStringExprCS(valueType, "expected", ag.location);

  local testName :: String = "generatedTest" ++ "_" ++ 
                            replaceChars(".","_",kwd.filename) ++ "_" ++ 
                            toString(kwd.line) ++ "_" ++ 
                            toString(genInt());
}

-- Oh, boy... this whole pile of code is awful

function functionNameForBaseTypesCS
Maybe<String> ::= valueType::TypeExpr prefixS::String
{ return
   case valueType of
   | integerTypeExpr(_) -> just(prefixS ++ "Integer")
   | floatTypeExpr(_)   -> just(prefixS ++ "Float")
   | stringTypeExpr(_)  -> just(prefixS ++ "String")
   | booleanTypeExpr(_) -> just(prefixS ++ "Boolean")
   | _ -> nothing()
   end;
}

function mkToStringExprCS
Maybe<Expr> ::= valueType::TypeExpr  exprName::String  l::Location
{
  return
    case functionNameForBaseTypesCS(valueType, "toStringFrom") of
    | just(btt) -> just(mkStrFunctionInvocation(l, btt, [mkNameExpr(exprName, l)]))
    | nothing() -> 
        case valueType of
        | listTypeExpr(_,elemType,_) ->
            case functionNameForBaseTypesCS(elemType,"toStringFrom") of
            | just(btt) ->
                just(mkStrFunctionInvocation(l, "toStringFromList", [mkNameExpr(btt, l), mkNameExpr(exprName, l)]))
            | _ -> nothing()
            end
        | _ -> nothing()
        end 
    end;
}

function mkEqualityTestExprCS
Maybe<Expr> ::= valueType::TypeExpr  l::Location
{
  return
    case functionNameForBaseTypesCS(valueType, "equals") of
    | just(btt) -> just(mkStrFunctionInvocation(l, btt, [mkNameExpr("value", l), mkNameExpr("expected", l)]))
    | nothing() -> 
        case valueType of
        | listTypeExpr(_,elemType,_) ->
            case functionNameForBaseTypesCS(elemType, "equals") of
            | just(btt) ->
                just(mkStrFunctionInvocation(l, "equalsList", [mkNameExpr(btt, l), mkNameExpr("value", l), mkNameExpr("expected", l)]))
            | _ -> nothing()
            end
        | _ -> nothing()
        end 
    end;
}

