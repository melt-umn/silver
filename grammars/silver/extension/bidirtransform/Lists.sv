grammar silver:extension:bidirtransform;

nonterminal QNameList with pp, qList, location;

synthesized attribute qList :: [QName];

concrete production qNameListSingle
top::QNameList ::= id::QName
{
  top.pp = id.pp;
  top.qList = [id];
}

concrete production qNameListCons
top::QNameList ::= id1::QName ',' id2::QNameList
{
  top.pp = id1.pp ++ ", " ++ id2.pp ;
  top.qList = [id1] ++ id2.qList;
}
