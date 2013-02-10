
import silver_features;
import silver:testing;
import lib:extcore;

nonterminal AnnoNT; -- our target of abuse!

annotation what :: Integer;

annotate AnnoNT with what;

abstract production anAnnoNT
top::AnnoNT ::=
{
}

global nt1 :: AnnoNT = anAnnoNT(what=1);
global dnt1 :: Decorated AnnoNT = decorate nt1 with {};

-- this applies, and accesses, an annotation's value
equalityTest ( nt1.what, 1, Integer, silver_tests ) ;
-- Check it works from decorated values
equalityTest ( dnt1.what, 1, Integer, silver_tests ) ;


global intFun :: (AnnoNT ::= Integer) = anAnnoNT(what=_);
global nt2 :: AnnoNT = intFun(2);

equalityTest ( nt2.what, 2, Integer, silver_tests ) ;


function ordinaryFun
AnnoNT ::=
{
  return anAnnoNT(what=3);
}

wrongCode "has initialization expression with type" {
  -- anAnnoNT :: (AnnoNT::= ; what::Integer)
  global aasdf :: (AnnoNT::=) = anAnnoNT;
}

wrongCode "Missing named parameters" {
  global aasdf :: AnnoNT = anAnnoNT();
}

wrongCode "'what' is not appropriate" {
  global aasdf :: AnnoNT = ordinaryFun(what=2);
}

wrongCode "expected Integer" {
  global aasdf :: AnnoNT = anAnnoNT(what="wrong type");
}


