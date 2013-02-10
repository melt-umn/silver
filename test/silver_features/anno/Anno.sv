
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

wrongCode "'asfd' is not appropriate" {
  global aasdf :: AnnoNT = anAnnoNT(asfd=2);
}

wrongCode "expected Integer" {
  global aasdf :: AnnoNT = anAnnoNT(what="wrong type");
}

abstract production moreAnnoNT
top::AnnoNT ::= s::String
{
}

global fun2 :: (AnnoNT ::= String Integer) = moreAnnoNT(_, what=_);
global fun3 :: (AnnoNT ::= Integer) = moreAnnoNT("hi", what=_);
global fun4 :: (AnnoNT ::= String) = moreAnnoNT(_, what=7);

global nt3 :: AnnoNT = fun2("hi", 5);
global nt4 :: AnnoNT = fun3(6);
global nt5 :: AnnoNT = fun4("str");

equalityTest ( nt3.what, 5, Integer, silver_tests ) ;
equalityTest ( nt4.what, 6, Integer, silver_tests ) ;
equalityTest ( nt5.what, 7, Integer, silver_tests ) ;

wrongCode "Missing named parameters" {
  global aasdf :: AnnoNT = moreAnnoNT("tmp");
}
wrongCode "Too few arguments provided " {
  global aasdf :: AnnoNT = moreAnnoNT(what=2);
}

-- Let's ensure we can still pattern match alright
global grabstr :: String = case nt5 of moreAnnoNT(s) -> s end;
equalityTest ( grabstr, "str", String, silver_tests ) ;


