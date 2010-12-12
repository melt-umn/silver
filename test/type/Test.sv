grammar type;

import silver:definition:type;
import edu:umn:cs:melt:testing;
import silver:util;

-- convenience
global tyva :: TypeVar = freshTyVar();
global tyvb :: TypeVar = freshTyVar();
global tyvc :: TypeVar = freshTyVar();
global tya :: TypeExp = varTypeExp(tyva);
global tyb :: TypeExp = varTypeExp(tyvb);
global tyc :: TypeExp = varTypeExp(tyvc);
function getFresh
TypeExp ::=
{
  return varTypeExp(freshTyVar());
}

function main 
IOVal<Integer> ::= largs::[String] i::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  local attribute testResults :: TestSuite ;
  testResults = testsAsNT ( [ test_1(), test_2(), test_3(), test_4(), test_5()
                ] ) ;
  testResults.ioIn = i;

  return ioval(print (
                    "Test results: \n" ++
                    testResults.msg ++ "\n\n" ++ 
                    "Passed " ++ toString (testResults.numPassed) ++
                    " tests out of " ++ 
                    toString (testResults.numTests) ++ "\n\n"
                   , testResults.ioOut ), 0);
}

--------------------------------------------------------------------------------
-- Pretty printing and free variables tests

abstract production test_1
t::Test ::=
{
 t.pass = result == expected ;
 t.msg
   = "\n test_1 failed.\n" ++ 
     "Pretty print resulted in: " ++ result ++ "\n" ++
     "Expected: " ++ expected ++ "\n";

 local attribute te::TypeExp ;
 te = functionTypeExp(nonterminalTypeExp("List", [tyb]), [nonterminalTypeExp("List", [tya]), functionTypeExp(tyb, [tya])]);

 local attribute result::String;
 result = prettyType(te);
 
 local attribute expected::String;
 expected = "Function(List<a> ::= List<b> Function(a ::= b))";

 forwards to defTest();
}

abstract production test_2
t::Test ::=
{
 t.pass = result == expected ;
 t.msg
   = "\n test_2 failed.\n" ++ 
     "Pretty print resulted in: " ++ result ++ "\n" ++
     "Expected: " ++ expected ++ "\n";

 local attribute te::TypeExp ;
 te = productionTypeExp(nonterminalTypeExp("Pair", [tya, tyb]), [tya, tyb]);

 local attribute result::String;
 result = prettyType(te);
 
 local attribute expected::String;
 expected = "Production(Pair<a b> ::= a b)";

 forwards to defTest();
}

abstract production test_3
t::Test ::=
{
 t.pass = result == expected ;
 t.msg
   = "\n test_3 failed.\n" ++ 
     "Pretty print resulted in: " ++ result ++ "\n" ++
     "Expected: " ++ expected ++ "\n";

 local attribute te::TypeExp ;
 te = productionTypeExp(decoratedTypeExp(nonterminalTypeExp("WTF", [tya, tyb])), [boolTypeExp(), intTypeExp(), floatTypeExp(), stringTypeExp()]);

 local attribute result::String;
 result = prettyType(te);
 
 local attribute expected::String;
 expected = "Production(Decorated WTF<a b> ::= Boolean Integer Float String)";

 forwards to defTest();
}

abstract production test_4
t::Test ::=
{
 t.pass = result == expected ;
 t.msg
   = "\n test_4 failed.\n" ++ 
     "Pretty print resulted in: " ++ result ++ "\n" ++
     "Expected: " ++ expected ++ "\n";

 local attribute te::TypeExp ;
 te = productionTypeExp(decoratedTypeExp(nonterminalTypeExp("WTF", [tya])), [functionTypeExp(tyb,[tyc]), nonterminalTypeExp("FOO", [getFresh()]), decoratedTypeExp(nonterminalTypeExp("BAR", [getFresh()])) ]);

 local attribute result::String;
 result = prettyType(te);
 
 local attribute expected::String;
 expected = "Production(Decorated WTF<a> ::= Function(b ::= c) FOO<d> Decorated BAR<e>)";

 forwards to defTest();
}

--------------------------------------------------------------------------------
-- Unification and substitution testing

abstract production test_5
t::Test ::=
{
 t.pass = result == expected ;
 t.msg
   = "\n test_5 failed.\n" ++ 
     "Pretty print resulted in: " ++ result ++ "\n" ++
     "Expected: " ++ expected ++ "\n";

 local attribute te1::TypeExp ;
 te1 = functionTypeExp(nonterminalTypeExp("List", [tyb]), [nonterminalTypeExp("List", [tya]), functionTypeExp(tyb, [tya])]);
 
 local attribute te2::TypeExp ;
 te2 = functionTypeExp(nonterminalTypeExp("List", [getFresh()]), [nonterminalTypeExp("List", [getFresh()]), functionTypeExp(boolTypeExp(), [intTypeExp()])]);
 
 local attribute s::Substitution;
 s = unify(te1,te2);

 local attribute result::String;
 result = prettyType(performSubstitution(te1,s)) ++ " || "
       ++ prettyType(performSubstitution(te2,s)) ++ " || "
       ++ folds(", ", s.substErrors);
 
 local attribute expected::String;
 expected = "Function(List<Boolean> ::= List<Integer> Function(Boolean ::= Integer)) || Function(List<Boolean> ::= List<Integer> Function(Boolean ::= Integer)) || ";

 forwards to defTest();
}



