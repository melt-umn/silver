
import silver_features;

nonterminal AnnoNT; -- our target of abuse!

annotation what :: Integer;

annotate AnnoNT with what;


abstract production anAnnoNT
top::AnnoNT ::=
{
}


wrongCode "has initialization expression with type" {
  -- anAnnoNT :: (AnnoNT::= ; what::Integer)
  global aasdf :: (AnnoNT::=) = anAnnoNT;
}

wrongCode "already occurs onasfasgsg" {
  global aasdf :: AnnoNT = anAnnoNT();
}



