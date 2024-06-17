grammar silver:compiler:extension:testing;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;
import silver:compiler:definition:flow:env;
import silver:compiler:analysis:typechecking:core;
import silver:compiler:modification:collection;
import silver:compiler:modification:list;
import silver:compiler:metatranslation only makeName;

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
    else [errFromOrigin(value, "Type of first and second expressions in equalityTest do not match. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];
  localErrors <-
    if !errCheck2.typeerror then []
    else [errFromOrigin(value, "Type of initial expression does not match specified type (3rd argument). Instead they are " ++ errCheck2.leftpp ++ " and " ++ errCheck2.rightpp)];
  localErrors <-
    if !errCheck3.typeerror then []
    else [errFromOrigin(value, "Type of second expression does not match specified type (3rd argument). Instead they are " ++ errCheck3.leftpp ++ " and " ++ errCheck3.rightpp)];

  local eqCtx::Context = instContext("silver:core:Eq", valueType.typerep);
  eqCtx.env = ag.env;
  eqCtx.contextLoc = getParsedOriginLocationOrFallback(valueType);
  eqCtx.contextSource = "equalityTest";
  eqCtx.frame = value.frame;
  eqCtx.config = ag.config;
  eqCtx.grammarName = ag.grammarName;
  eqCtx.compiledGrammars = ag.compiledGrammars;
  localErrors <- eqCtx.contextErrors;

  thread downSubst, upSubst on expected, errCheck1, errCheck2, errCheck3;
  
  errCheck1.finalSubst = errCheck3.upSubst;
  errCheck2.finalSubst = errCheck3.upSubst;
  errCheck3.finalSubst = errCheck3.upSubst;

  -- TODO: one of those type error checks above is redundant

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(ag.grammarName, ag.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(ag.grammarName, ag.compiledGrammars)).productionFlowGraphs;

{- Causes some circularities with the environment. TODO
  forwards to if !errCheck1.typeerror && !errCheck2.typeerror && !errCheck3.typeerror
              then appendAGDcl(absProdCS, aspProdCS)
              else emptyAGDcl();
-}
  ag.errors := if null(localErrors) then forward.errors else localErrors;

  forwards to appendAGDcl(@absProdCS, @aspProdCS);

  -- TODO: BUG: FIXME: these names should be mangled. I ran into 't' being shadowed in a test I wrote!
  nondecorated local tref::Name = name("t");
  nondecorated local valueref::Name = name("value");
  nondecorated local expectedref::Name = name("expected");
  
  local absProdCS::AGDcl = Silver_AGDcl {
    abstract production $name{testName}
    $Name{tref}::Test ::=
    {
      nondecorated local attribute $Name{valueref} :: $TypeExpr{@valueType};
      $Name{valueref} = $Expr{@value};
      nondecorated local attribute $Name{expectedref} :: $TypeExpr{new(valueType)};
      $Name{expectedref} = $Expr{@expected};
      $Name{tref}.msg =
        "Test at " ++ $Expr{makeStringConst(getParsedOriginLocationOrFallback(ag).unparse)} ++ " failed.\n" ++
        "Checking that expression\n   " ++ $Expr{makeStringConst(value.unparse)} ++ "\n" ++
        "should be same as expression\n   " ++ $Expr{makeStringConst(expected.unparse)} ++ "\n" ++
        "Actual value:\n   " ++ silver:core:genericShow($Name{valueref}) ++ "\n" ++
        "Expected value: \n   " ++ silver:core:genericShow($Name{expectedref}) ++ "\n";
      $Name{tref}.pass = $Name{valueref} == $Name{expectedref};
    }
  };

  local aspProdCS::AGDcl = Silver_AGDcl {
    aspect production $Name{@testSuite}
    $Name{tref} ::= 
    {
      testsToPerform <- [ $name{testName}() ];
    }
  };

  local testName :: String = "generatedTest" ++ "_" ++ 
                            substitute(":","_",ag.grammarName) ++ "_" ++ 
                            substitute(".","_",kwd.filename) ++ "_" ++ 
                            toString(kwd.line) ++ "_" ++ 
                            toString(kwd.column);
}

fun makeStringConst Expr ::= s::String =
  stringConst(terminal(String_t, "\"" ++ escapeString(s) ++ "\"", getParsedOriginLocationOrFallback(ambientOrigin())));

