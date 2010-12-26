grammar silver:extension:testing;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:modification:collection ;
import silver:extension:list ;

{- Equality tests compare two expressions to see if they evalute to
   equal values.

   The tests specify these two expressions, their types, and the
   TestSuite to which they should be added.  
 
   These tests have the form:
     equalityTest ( value, expected, Type, testSuiteProduciton ) ;

   Samples:
     equalityTest ( 1+2, 3, Integer, core_tests ) ;
     equalityTest ( removeBy (equalsInteger, 2, [1,2,3]), [1,3],
                    [Integer], core_tests ) ;
 -}

terminal EqualityTest_t 'equalityTest' lexer classes {KEYWORD} ;


concrete production equalityTest_p
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
 ag.errors <- forward.errors ;


 forwards to ft ; -- if null(ag.errors) then ft else agDclNone() ;

 -- If valueType is a base type (Integer, Float, etc.) or a List whose
 -- element type is a base type, then we can check for equality.
 -- With curried functions we could handle nested lists, but not now.
 local equalityTestExpr :: Maybe<Expr> =
   mkEqualityTestExpr(valueType, value, expected) ;

 local toStringValueExpr :: Maybe<Expr> =
   mkToStringExpr (valueType, value) ;
 local toStringExpectedExpr :: Maybe<Expr> =
   mkToStringExpr (valueType, expected) ;

 local attribute ft :: AGDcl ;
 ft =
  agDclAppend
  (
   productionDcl
     ( 'abstract', 'production',
        nameIdLower ( terminal(IdLower_t, testName) ) ,
        productionSignatureEmptyRHS (
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
  ,
   aspectProductionDcl 
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
  ) ;

 local expectedName :: Name = nameIdLower( terminal(IdLower_t, "expected" )) ;
 local valueName :: Name = nameIdLower( terminal(IdLower_t, "value" )) ;
 local tName :: Name = nameIdLower( terminal(IdLower_t, "t" )) ;
 local msgName :: Name = nameIdLower( terminal(IdLower_t, "msg" )) ;
 local passName :: Name = nameIdLower( terminal(IdLower_t, "pass" )) ;
 local testName :: String = "generatedTest" ++ "_" ++ toString(genInt()) ;
}


function mkToStringExpr
Maybe<Expr> ::= valueType::Type expr::Expr
{ return
   case functionForBaseTypes(valueType, "toString") of
     just(btt) -> just( -- "bbt (expr)"
                        productionApp( new(btt) , '(', exprsSingle(expr), ')' 
                       ) )
   | nothing() -> 
       case valueType of
         listType(_,elemType,_) 
           -> case functionForBaseTypes(new(elemType),"toString") of
                just(btt) -> -- "toStringList(btt, value, expected)"
                             just( productionApp( 
                                     mkProductionExpr("toString", "List"), '(',
                                     exprsCons ( new(btt), ',', 
                                      exprsSingle ( expr ) ) ,
                                     ')' ) )
              | _ -> nothing()
              end
       | _ -> nothing()
       end 
   end ;
   -- ToDo: The "new"s above should not be required. There is something wrong with
   --       pattern matching as of Dec 6.
}

function mkEqualityTestExpr
Maybe<Expr> ::= valueType::Type value::Expr expected::Expr
{ return
   case functionForBaseTypes(valueType, "equals") of
     just(btt) -> just( 
                    -- "bbt (value, expected)"
                    productionApp(
                      new(btt) , '(', exprsCons ( value, ',', exprsSingle ( expected ) ), ')' 
                    ) )
   | nothing() -> 
       case valueType of
         listType(_,elemType,_) 
           -> case functionForBaseTypes(new(elemType),"equals") of
                just(btt) -> -- "equalsList(btt, value, expected)"
                             just( productionApp( 
                                     mkProductionExpr("equals", "List"), '(',
                                     exprsCons ( new(btt), ',', 
                                      exprsCons ( value, ',' ,
                                       exprsSingle ( expected ) ) ) ,
                                     ')' ) )
              | _ -> nothing()
              end
       | _ -> nothing()
       end 
   end ;
   -- ToDo: The "new"s above should not be required. There is something wrong with
   --       pattern matching as of Dec 6.
}

function functionForBaseTypes
Maybe<Expr> ::= valueType::Type prefix::String
{
 return
   case valueType of
     integerType(_) -> just( mkProductionExpr(prefix, "Integer"))
   | floatType(_) -> just( mkProductionExpr(prefix, "Float"))
   | stringType(_) -> just( mkProductionExpr(prefix, "String"))
   | booleanType(_) -> just( mkProductionExpr(prefix, "Boolean"))
   | _ -> nothing()
   end ;
}



-- create a production
function mkProductionExpr
Expr ::= prefix::String typeName::String
{ return mkNameExpr(prefix ++ typeName) ;  }



{-
terminal EqualityTestEqShow_t 'equalityTestEqShow' lexer classes {KEYWORD} ;

concrete production equalityTestEqShow_p
ag::AGDcl ::= kwd::'equalityTestEqShow' 
              '(' value::Expr ',' expected::Expr ',' 
                  eqFunc::Expr ',' toStrFunc::Expr ')' ';'
{
 ag.pp = "equalityTestEqSho (" ++ value.pp ++ "," ++ expected.pp ++ ",\n" ++ 
         "                   " ++ eqFunc.pp ++ ", " ++ toStrFunc.pp ++ ") ;\n" ;

 forwards to ft ;

 -- If valueType is a base type (Integer, Float, etc.) or a List whose
 -- element type is a base type, then we can check for equality.
 -- With curried functions we could handle nested lists, but not now.
 local equalityTestExpr :: Maybe<Expr> =
   mkEqualityTestExpr(valueType, value, expected) ;

 local toStringValueExpr :: Maybe<Expr> =
   mkToStringExpr (valueType, value) ;
 local toStringExpectedExpr :: Maybe<Expr> =
   mkToStringExpr (valueType, expected) ;

 local attribute ft :: AGDcl ;
 ft =
  agDclAppend
  (
   productionDcl
     ( 'abstract', 'production',
        nameIdLower ( terminal(IdLower_t, testName) ) ,
        productionSignatureEmptyRHS (
          productionLHS ( nameIdLower (terminal(IdLower_t, "t")), '::',
                          nominalType ( qNameUpperId (terminal(IdUpper_t,"Test")) )
                        ) ,
          '::=' )
        ,

        defaultProductionBody (

          productionStmtsCons (
            localAttributeDcl ('local', 'attribute', valueName, '::', valueType, ';' ) ,
          productionStmtsCons (
            valueDef ( qNameId(valueName), '=', value, ';' ) ,

          productionStmtsCons (
            localAttributeDcl ('local', 'attribute', expectedName, '::', valueType, ';' ) ,
          productionStmtsCons (
            valueDef ( qNameId(expectedName), '=', expected, ';' ) ,

          -- t.msg = "FAIL" ;
          productionStmtsCons (
            attributeDef ( concreteDefLHS(qNameId(tName)), '.',
                           qNameId(msgName), '=',

                           foldStringExprs (
                            [
                             strCnst ("Test at ") ,
                             strCnst (ag.location.unparse),
                             strCnst (" failed. \\n") ,
                             strCnst ("Checking that expression \\n   ") ,
                             strCnst (value.pp) ,
                             strCnst ("\\nshould be same as expression \\n   ") ,
                             strCnst (expected.pp) ,
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
  ,
   aspectProductionDcl 
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
  ) ;

 local expectedName :: Name = nameIdLower( terminal(IdLower_t, "expected" )) ;
 local valueName :: Name = nameIdLower( terminal(IdLower_t, "value" )) ;
 local tName :: Name = nameIdLower( terminal(IdLower_t, "t" )) ;
 local msgName :: Name = nameIdLower( terminal(IdLower_t, "msg" )) ;
 local passName :: Name = nameIdLower( terminal(IdLower_t, "pass" )) ;
 local testName :: String = "generatedTest" ++ "_" ++ toString(genInt()) ;
}
-}

