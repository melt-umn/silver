grammar silver:extension:testing;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:modification:collection;
import silver:extension:list;

--import silver:analysis:typechecking:core;

import lib:extcore;

terminal EqualityTest_t 'equalityTest' lexer classes {KEYWORD};
concrete production equalityTest2_p
ag::AGDcl ::= kwd::'equalityTest' 
              '(' value::Expr ',' expected::Expr ',' 
                  valueType::Type ',' testSuite::Name ')' ';'
{
  ag.pp = "equalityTest (" ++ value.pp ++ "," ++ expected.pp ++ ",\n" ++ 
          "              " ++ valueType.pp ++ ", " ++ testSuite.pp ++ ");\n";
  ag.location = loc(ag.file, kwd.line, kwd.column);
  ag.errors := case equalityTestExpr of
               | just(_) -> []
               | nothing() -> 
                   [err(ag.location, "Type \"" ++ valueType.pp ++ "\" not suported on equality tests.")]
               end;

  local attribute errCheck1 :: TypeCheck; 
  local attribute errCheck2 :: TypeCheck; 
  local attribute errCheck3 :: TypeCheck; 
  errCheck1 = check(value.typerep, expected.typerep);
  errCheck2 = check(value.typerep, valueType.typerep);
  errCheck3 = check(expected.typerep, valueType.typerep);

  ag.errors <-
    if !errCheck1.typeerror then []
    else [err(value.location, "Type of first and second experssions in equalityTest do not match. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  ag.errors <-
    if !errCheck1.typeerror then []
    else [err(value.location, "Type of initial expression does not match specified type (3rd argument). Instead they are " ++
                               errCheck2.leftpp ++ " and " ++ errCheck2.rightpp)];

  ag.errors <-
    if !errCheck1.typeerror then []
    else [err(value.location, "Type of second expression does not match specified type (3rd argument). Instead they are " ++
                               errCheck3.leftpp ++ " and " ++ errCheck3.rightpp)];

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

  value.signature = bogusNamedSignature();
  expected.signature = bogusNamedSignature();
  value.blockContext = globalExprContext();
  expected.blockContext = globalExprContext();
  

  ag.errors <- forward.errors;

{- Causes some circularities with the environment. TODO
  forwards to if !errCheck1.typeerror && !errCheck2.typeerror && !errCheck3.typeerror
              then appendAGDcl(absProdCS, aspProdCS)
              else emptyAGDcl();
-}

  forwards to appendAGDcl(absProdCS, aspProdCS);

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
   "          \"   " ++ stringifyString(value.pp) ++ "\" ++ \n" ++
   "          \"\\nshould be same as expression \\n\" ++ \n" ++
   "          \"   " ++ stringifyString(expected.pp) ++ "\\n\" ++ \n" ++
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

  local tref :: Name = nameIdLower(terminal(IdLower_t, "t"));
  local testNameref :: Name = nameIdLower(terminal(IdLower_t, testName));
  local valueref :: Name = nameIdLower(terminal(IdLower_t, "value"));
  local expectedref :: Name = nameIdLower(terminal(IdLower_t, "expected"));
  local msgref :: Name = nameIdLower(terminal(IdLower_t, "msg"));
  local passref :: Name = nameIdLower(terminal(IdLower_t, "pass"));
  
  local absProdCS :: AGDcl =
    productionDcl('abstract', 'production', testNameref,
      productionSignature(
        productionLHS(tref, '::',
          nominalType(qNameUpperId(terminal(IdUpper_t, "Test")), botlNone())),
        '::=', productionRHSNil()),
      defaultProductionBody(foldr(productionStmtsCons, productionStmtsNone(), [
        localAttributeDcl('local', 'attribute', valueref, '::', valueType, ';'),
        valueEq(qNameId(valueref), '=', value, ';'),
        localAttributeDcl('local', 'attribute', expectedref, '::', valueType, ';'),
        valueEq(qNameId(expectedref), '=', expected, ';'),
        attributeDef(concreteDefLHS(qNameId(tref)), '.', qNameId(msgref), '=',
          foldStringExprs([
            strCnst("Test at " ++ ag.location.unparse ++ " failed.\nChecking that expression\n   " ++
              stringifyString(value.pp) ++ "\nshould be same as expression\n   " ++
              stringifyString(expected.pp) ++ "\nActual value:\n   "),
            toStringValueExpr.fromJust,
            strCnst("\nExpected value: \n   "),
            toStringExpectedExpr.fromJust,
            strCnst("\n")]), ';'),
        attributeDef(concreteDefLHS(qNameId(tref)), '.', qNameId(passref), '=',
           equalityTestExpr.fromJust, ';'),
        forwardsTo('forwards', 'to', prodFuncCall("defTest", []), ';')])));

{-
  local aspProdCS :: AGDcl = asAGDcl (
   "aspect production %%%Name testSuite \n" ++
   "t ::=  \n" ++
   "{ testsToPerform <- [ " ++ testName ++ "() ]; } " ,
   cons_CS_env("testSuite", wrapName(testSuite), empty_CS_env()) , 4 );
-}

  local aspProdCS :: AGDcl =
    aspectProductionDcl('aspect', 'production', qNameId(testSuite),
      aspectProductionSignature(
        aspectProductionLHSId(tref),
          '::=', aspectRHSElemNil()),
      defaultProductionBody(
        productionStmts(
          valContainsAppend(
            qNameId(nameIdLower(terminal(IdLower_t, "testsToPerform"))),
            '<-',
            fullList('[',
              exprsSingle(
                emptyProductionApp(
                  baseExpr(qNameId(testNameref)), '(', ')')),
              ']'),
            ';'))));



  -- If valueType is a base type (Integer, Float, etc.) or a List whose
  -- element type is a base type, then we can check for equality.
  -- With curried functions we could handle nested lists, but not now.
  local equalityTestExpr :: Maybe<Expr> =
    mkEqualityTestExprCS(valueType);

  local toStringValueExpr :: Maybe<Expr> =
    mkToStringExprCS (valueType, "value");
  local toStringExpectedExpr :: Maybe<Expr> =
    mkToStringExprCS (valueType, "expected");

  local testName :: String = "generatedTest" ++ "_" ++ 
                            replaceChars(".","_",kwd.filename) ++ "_" ++ 
                            toString(kwd.line) ++ "_" ++ 
                            toString(genInt());
}

-- Oh, boy... this whole pile of code is awful

function functionNameForBaseTypesCS
Maybe<String> ::= valueType::Type prefix::String
{ return
   case valueType of
   | integerType(_) -> just(prefix ++ "Integer")
   | floatType(_)   -> just(prefix ++ "Float")
   | stringType(_)  -> just(prefix ++ "String")
   | booleanType(_) -> just(prefix ++ "Boolean")
   | _ -> nothing()
   end;
}

function mkToStringExprCS
Maybe<Expr> ::= valueType::Type  exprName::String
{ return
   case functionNameForBaseTypesCS(valueType, "toStringFrom") of
   | just(btt) -> just(prodFuncCall(btt, [mkNameExpr(exprName)]))
   | nothing() -> 
       case valueType of
       | listType(_,elemType,_) ->
           case functionNameForBaseTypesCS(elemType,"toStringFrom") of
           | just(btt) ->
               just(prodFuncCall("toStringFromList", [mkNameExpr(btt), mkNameExpr(exprName)]))
           | _ -> nothing()
           end
       | _ -> nothing()
       end 
   end;
}

function mkEqualityTestExprCS
Maybe<Expr> ::= valueType::Type
{ return
   case functionNameForBaseTypesCS(valueType, "equals") of
   | just(btt) -> just(prodFuncCall(btt, [mkNameExpr("value"), mkNameExpr("expected")]))
   | nothing() -> 
       case valueType of
       | listType(_,elemType,_) ->
           case functionNameForBaseTypesCS(elemType, "equals") of
           | just(btt) ->
               just(prodFuncCall("equalsList", [mkNameExpr(btt), mkNameExpr("value"), mkNameExpr("expected")]))
           | _ -> nothing()
           end
       | _ -> nothing()
       end 
   end;
}

