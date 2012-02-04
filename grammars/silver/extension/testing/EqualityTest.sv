grammar silver:extension:testing;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:modification:collection ;
import silver:extension:list ;

import silver:analysis:typechecking;
import silver:analysis:typechecking:core;

import lib:extcore ;

terminal EqualityTest_t 'equalityTest' lexer classes {KEYWORD} ;
concrete production equalityTest2_p
ag::AGDcl ::= kwd::'equalityTest' 
              '(' value::Expr ',' expected::Expr ',' 
                  valueType::Type ',' testSuite::Name ')' ';'
{
 ag.pp = "equalityTest (" ++ value.pp ++ "," ++ expected.pp ++ ",\n" ++ 
         "              " ++ valueType.pp ++ ", " ++ testSuite.pp ++ ") ;\n" ;
 ag.location = loc(ag.file, kwd.line, kwd.column);
 ag.errors := case equalityTestExpr of
                just(_) -> [ ]
              | nothing() -> [err(ag.location, "Type \"" ++ valueType.pp ++ 
                                             "\" not suported on equality tests.")]
              end ;
 ag.errors <- [ ] ; -- check that value and expected are of the same type 

  local attribute errCheck1 :: TypeCheck; 
  errCheck1.finalSubst = expected.finalSubst;
  errCheck1.downSubst = emptySubst();

  errCheck1 = check(value.typerep, expected.typerep);

  ag.errors <-
       if errCheck1.typeerror
       then [err(value.location, "Type of first and second experssions in equalityTest do not match. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];


  ag.errors <-
       if errCheck2.typeerror
       then [err(value.location, "Type of initial expression does not match specified type (3rd argument). Instead they are " ++
                                  errCheck2.leftpp ++ " and " ++ errCheck2.rightpp)]
       else [];

  ag.errors <-
       if errCheck3.typeerror
       then [err(value.location, "Type of second expression does not match specified type (3rd argument). Instead they are " ++
                                  errCheck3.leftpp ++ " and " ++ errCheck3.rightpp)]
       else [];

  value.env = ag.env ;
  expected.env = ag.env ;
  valueType.env = ag.env ;

  value.downSubst = emptySubst();
  expected.downSubst = value.upSubst ;

  value.finalSubst = expected.upSubst;
  expected.finalSubst = expected.upSubst;


  local attribute errCheck2 :: TypeCheck; 
  errCheck2.finalSubst = value.finalSubst;
  errCheck2.downSubst = emptySubst();
  errCheck2 = check(value.typerep, valueType.typerep);

  local attribute errCheck3 :: TypeCheck; 
  errCheck3.finalSubst = expected.finalSubst;
  errCheck3.downSubst = emptySubst();
  errCheck3 = check(expected.typerep, valueType.typerep);



  ag.errors <- forward.errors ;



 forwards to appendAGDcl(absProdCS, aspProdCS);
 local absProdCS :: AGDcl = asAGDcl (
   "abstract production " ++ testName ++ "\n" ++
   "t::Test ::= \n" ++
   "{ \n" ++
   "  local attribute value :: %%%Type valueType ;  \n" ++
   "  value =  %%%Expr value ; \n" ++
   "  local attribute expected :: %%%Type valueType ;  \n" ++
   "  expected = %%%Expr expected ; \n"  ++

--   "  local attribute valueAsString :: String ;  \n" ++
--   "  valueAsString =  %%%Expr toStringValueExpr ; \n" ++
--   "  local attribute expectedAsString :: String ;  \n" ++
--   "  expectedAsString =  %%%Expr toStringExpectedExpr ; \n" ++

   "  t.msg = \"Test at " ++ ag.location.unparse ++ " failed. \\n\" ++ \n" ++ 
   "          \"Checking that expression \\n\" ++ \n" ++
   "          \"   " ++ stringifyString(value.pp) ++ "\" ++ \n" ++
   "          \"\\nshould be same as expression \\n\" ++ \n" ++
   "          \"   " ++ stringifyString(expected.pp) ++ "\\n\" ++ \n" ++
--   "          \"Equality Test Expr: \\n\" ++ \n" ++
--   "          \"   " ++ stringifyString(eqTestExpr.pp) ++ "\\n\" ++ \n" ++
   "          \"Actual value: \\n   \" ++ \n" ++
   "          %%%Expr toStringValueExpr ++ \"\\n\" ++ \n" ++
   "          \"Expected value: \\n   \" ++ \n" ++
   "          %%%Expr toStringExpectedExpr ++ \"\\n\" ++ \n" ++
--   "         \"valueAsString: \\n\" ++ \n" ++
--   "         valueAsString ++ \"\\n\" ++ \n" ++
--   "         \"exptectedAsString: \\n\" ++ \n" ++
--   "         expectedAsString ++ \n" ++
   "         \"\" ;\n" ++
   "  t.pass = %%%Expr equalityTestCode ; \n" ++ 
   "  forwards to defTest() ; \n" ++
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
   empty_CS_env()))))))) , 3 ) ;

 local attribute eqTestExpr :: Expr ;
 eqTestExpr =  fromMaybe(error("TypeNotSupportedInternalError") ,equalityTestExpr ) ;

 local aspProdCS :: AGDcl = asAGDcl (
   "aspect production %%%Name testSuite \n" ++
   "top ::=  \n" ++
   "{ testsToPerform <- [ " ++ testName ++ "() ] ; } " ,
   cons_CS_env("testSuite", wrapName(testSuite), empty_CS_env()) , 4 ) ;

 -- If valueType is a base type (Integer, Float, etc.) or a List whose
 -- element type is a base type, then we can check for equality.
 -- With curried functions we could handle nested lists, but not now.
 local equalityTestExpr :: Maybe<Expr> =
   mkEqualityTestExprCS(valueType, value, expected) ;

 local toStringValueExpr :: Maybe<Expr> =
   mkToStringExprCS (valueType, value, "value") ;
 local toStringExpectedExpr :: Maybe<Expr> =
   mkToStringExprCS (valueType, expected, "expected") ;

 local testName :: String = "generatedTest" ++ "_" ++ 
                            replaceChars(".","_",kwd.filename) ++ "_" ++ 
                            toString(kwd.line) ++ "_" ++ 
                            toString(genInt()) ;
}

function functionNameForBaseTypesCS
Maybe<String> ::= valueType::Type prefix::String
{ return
   case valueType of
     integerType(_) -> just(prefix ++ "Integer")
   | floatType(_)   -> just(prefix ++ "Float")
   | stringType(_)  -> just(prefix ++ "String")
   | booleanType(_) -> just(prefix ++ "Boolean")
   | _ -> nothing()
   end ;
}

function mkToStringExprCS
Maybe<Expr> ::= valueType::Type expr::Expr exprName::String
{ return
   case functionNameForBaseTypesCS(valueType, "toStringFrom") of
     just(btt) -> just ( asExpr( btt ++ "( " ++ exprName ++ ")",
                                 empty_CS_env(),6))
   | nothing() -> 
       case valueType of
         listType(_,elemType,_) 
           -> case functionNameForBaseTypesCS(elemType,"toStringFrom") of
                just(btt) ->
                      just ( asExpr( "toStringFromList ( " ++ btt ++ ", " ++
                                                          exprName ++ ")", 
                                    empty_CS_env(),7))
                                  
              | _ -> nothing()
              end
       | _ -> nothing()
       end 
   end ;
}

function mkEqualityTestExprCS
Maybe<Expr> ::= valueType::Type value::Expr expected::Expr
{ return
   case functionNameForBaseTypesCS(valueType, "equals") of
     just(btt) -> just ( asExpr( btt ++ "(value, expected)", empty_CS_env(),7))

   | nothing() -> 
       case valueType of
         listType(_,elemType,_) 
           -> case functionNameForBaseTypesCS(elemType,"equals") of
                just(btt) -> -- "equalsList(btt, value, expected)"
                      just ( asExpr( "equalsList(" ++ btt ++ ", value, expected)", 
                                    empty_CS_env(),7))
              | _ -> nothing()
              end
       | _ -> nothing()
       end 
   end ;
}

-- create a production
function mkProductionExpr
Expr ::= prefix::String typeName::String
{ return mkNameExpr(prefix ++ typeName) ;  }

-- Think about resurecting this when the concrete syntax stuff doesn't require passing in the explicit CS_env mess.
function functionForBaseTypesCS
Maybe<Expr> ::= valueType::Type prefix::String
{
 return
   case valueType of
--     integerType(_) -> just( mkProductionExpr(prefix, "Integer"))
     integerType(_) -> just( asExpr("Integer", empty_CS_env(), 5) )
   | floatType(_) -> just( mkProductionExpr(prefix, "Float"))
   | stringType(_) -> just( mkProductionExpr(prefix, "String"))
   | booleanType(_) -> just( mkProductionExpr(prefix, "Boolean"))
   | _ -> nothing()
   end ;
}


{-
--  appendAGDcl
--  (
  local absProd::AGDcl =
   productionDcl
     ( 'abstract', 'production',
        nameIdLower ( terminal(IdLower_t, testName) ) ,
        productionSignatureEmptyRHS (
          -- prodLHS ,
          productionLHS ( nameIdLower (terminal(IdLower_t, "t")), '::',
                          nominalType ( qNameUpperId (terminal(IdUpper_t,"Test")) )
                        ) ,
          '::=' )
        ,

        defaultProductionBody (

          productionStmtsCons (
            localAttributeDcl ('local', 'attribute', valueName, '::', valueType, ';' ) ,
          productionStmtsCons (
            valueDef ( qNameId(valueName), 
                       terminal(Equal_t, "=", value.location.line, value.location.column),
                       value, ';' ) ,

          productionStmtsCons (
            localAttributeDcl ('local', 'attribute', expectedName, '::', valueType, ';' ) ,
          productionStmtsCons (
            valueDef ( qNameId(expectedName), 
                       terminal(Equal_t, "=", expected.location.line, expected.location.column),
                       expected, ';' ) ,

          -- t.msg = "FAIL" ;
          productionStmtsCons (
            attributeDef ( concreteDefLHS(qNameId(tName)), '.',
                           qNameId(msgName), '=',

                           foldStringExprs (
                            [
                             strCnst ("Test at ") ,
                             strCnst (ag.location.unparse),
                             strCnst (" failed. \\n") ,
                             strCnst ("1. Checking that expression \\n   ") ,
                             strCnst (stringifyString(value.pp)) ,
                             strCnst ("\\nshould be same as expression \\n   ") ,
                             strCnst (stringifyString(expected.pp)) ,
                             strCnst ("\\n") ,
                             strCnst ("Actual value: \\n   ") ,
                             fromMaybe(error("TypeNotSupportedInternalError") ,toStringValueExpr) ,
                             strCnst ("\\n"),
                             strCnst ("Expected value: \\n   ") ,
                             fromMaybe(error("TypeNotSupportedInternalError") ,toStringExpectedExpr) ,
                             strCnst ("\\n")
                            ] ) ,
                           ';' ) ,

          -- t.pass = equalsInteger (value, expected) ; 
          productionStmtsCons (
            attributeDef ( concreteDefLHS(qNameId(tName)), '.',
                           qNameId(passName), '=',
                           fromMaybe(error("TypeNotSupportedInternalError") ,equalityTestExpr), ';' ) ,

          productionStmts( 
             forwardsTo ('forwards', 'to', 
                         emptyProductionApp ( baseExpr( qNameId( 
                           nameIdLower( terminal(IdLower_t, "defTest")))), '(', ')' ) ,
                         ';') )
          )))))) -- 1 close paren for each productionStmtCons
        )
     )
  ;

  local aspProd :: AGDcl =
   asAGDcl ( "aspect production %%%Name testSuite \n" ++
             "top ::=  \n" ++
             "{ testsToPerform <- [ " ++ testName ++ "() ] ; }" ,
             cons_CS_env("testSuite", wrapName(testSuite), empty_CS_env()) , 3
           ) ;

 local valueName :: Name = nameIdLower( terminal(IdLower_t, "value" )) ;
 local tName :: Name = nameIdLower( terminal(IdLower_t, "t" )) ;
 local msgName :: Name = nameIdLower( terminal(IdLower_t, "msg" )) ;
 local passName :: Name = nameIdLower( terminal(IdLower_t, "pass" )) ;
 local expectedName :: Name = nameIdLower( terminal(IdLower_t, "expected" )) ;

-}


{-  NOT WORKING
   Aspectproductiondcl 
     ( 'aspect', 'production', qNameId ( testSuite ) ,
       aspectProductionSignatureEmptyRHS 
         ( aspectProductionLHSId( nameIdLower ( terminal(IdLower_t, "top") ) ) ,
           '::='  ) ,

       defaultProductionBody ( 
         productionStmts ( 
           valContainsAppend ( 
             qNameId ( nameIdLower ( terminal(IdLower_t, "testsToPerform" ) ) ) ,
             '<-',  
             -- [ generatedTest() ]
             fullList (
               '[',
               exprsSingle ( 
                   emptyProductionApp (
                     baseExpr (
                       qNameId (
                         nameIdLower (terminal(IdLower_t, testName ) ) ) ) , 
                     '(', ')'  ) ) ,
               ']' ) ,
             ';' ) ) )
     ) 
-}

--  ) ;



