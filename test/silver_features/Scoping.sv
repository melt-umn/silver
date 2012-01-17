
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

-- we have an attribute called "loc", which now works.
abstract production rightCodeNaming
top::Scope ::= loc::String
{
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

