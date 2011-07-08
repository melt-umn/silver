grammar performance;

imports silver:testing ;
imports lib:extcore ;

mainTestSuite performance_tests ;

-- TODO: need a way to check that this compiles in less than some time

global ll :: [String] = ["a"];
global ss :: String = "a";

equalityTest ( ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ 
               ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll ++ ll,
               ["a", "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  
               "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a",  "a"], [String], performance_tests ) ;

equalityTest ( ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ 
               ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss ++ ss,
               "aaaaaaaaaaaaaaaaaaaaaaaaaaaa", String, performance_tests ) ;

