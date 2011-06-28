grammar patt;

nonterminal LCE with value;
synthesized attribute value::Boolean;

abstract production lcUnit
e::LCE ::= 
{
  e.value = true;
}

abstract production lcAbs
e::LCE ::= v::String e1::LCE
{
  e.value = true;
}

abstract production lcApp
e::LCE ::= fun::LCE arg::LCE
{
  e.value = false;
}


function beta
LCE ::= e::LCE
{
  return case e of 
           lcUnit() -> e
         | lcAbs(v, e1) -> e
         | lcApp(lcAbs(v, e1), e2) -> betaContrived(e1)  -- subst is easy when you don't have variables!! hahaha!
         | lcApp(e1, e2) -> beta(lcApp(beta(e1), e2))
         end;
}
function betaContrived
LCE ::= e::Decorated LCE
{
  return beta(new(e));
}

equalityTest ( (beta(lcUnit() )).value, true, Boolean, pat_tests ) ;
equalityTest ( (beta(lcAbs("x",lcUnit()) )).value, true, Boolean, pat_tests ) ;
equalityTest ( (beta(lcApp(lcAbs("x",lcUnit()),lcUnit()) )).value, true, Boolean, pat_tests ) ;
equalityTest ( (beta(lcApp(lcApp(lcAbs("x",lcAbs("x",lcUnit())),lcUnit()),lcUnit()) )).value, true, Boolean, pat_tests ) ;


