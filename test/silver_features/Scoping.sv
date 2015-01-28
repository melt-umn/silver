
-- Test a bug where x should in fact refer to the *outer* x.
-- note: this test may become obsolete when we change let semantics to be recursive.
equalityTest(let x :: Integer = 1 in let x :: Integer = x in x end end, 1, Integer, silver_tests);

nonterminal Scope;

wrongCode "Value 'top' is already bound." {
 abstract production foo
 top::Scope ::= top::String
 {
 }
}

wrongCode "hi' is already bound." {
 abstract production foo
 top::Scope ::=
 {
  local attribute hi::String;
  production attribute hi :: String;
 }
}

-- two bars, which is perfectly okay!
abstract production rightCodeScoping
top::Scope ::= s::String
{
  local attribute hi1::String;
  hi1 = let barAgain :: String = "asdf" in barAgain end;
  
  local attribute hi2::String;
  hi2 = case rightCodeScoping("Huh") of
         rightCodeScoping(barAgain) -> barAgain
         | _ -> "oh"
        end;
}

-- we can shadow using children names
abstract production rightCodeNaming
top::Scope ::= rightCodeScoping::String -- shadowing the above production
{
}

-- we can rename children properly, and get the appropriate types and so forth
aspect production rightCodeNaming
rightCodeScoping::Scope ::= top::String
{
  local sc::Scope = rightCodeScoping;
  local tp::String = top;
}



wrongCode "Undeclared attribute 'DOESNOTEXIST'." {
 function foo
 Decorated Scope ::=
 {
  return decorate new(foo()) with { DOESNOTEXIST = 2; };
 }
}

wrongCode "Undeclared attribute 'DOESNOTEXIST'." {
 function foo
 Decorated Scope ::= s::Scope
 {
  s.DOESNOTEXIST = 2;
  return s;
 }
}

