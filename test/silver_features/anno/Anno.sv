
import silver_features;

nonterminal AnnoNT; -- our target of abuse!

annotation what :: Integer;

annotate AnnoNT with what;


abstract production anAnnoNT
top::AnnoNT ::=
{
}

function ordinaryFun
AnnoNT ::=
{
  return anAnnoNT(what=1);
}

global intFun :: (AnnoNT ::= Integer) = anAnnoNT(what=_);
global nt1 :: AnnoNT = intFun(1);

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



