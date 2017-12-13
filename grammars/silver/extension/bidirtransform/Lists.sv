grammar silver:extension:bidirtransform;

nonterminal QNameList with pp, qList, errors, location;

synthesized attribute qList :: [QName];

concrete production qNameListSingle
top::QNameList ::= id::QName
{
  top.pp = id.pp;
  top.qList = [id];
  top.errors := id.errors;
}

concrete production qNameListCons
top::QNameList ::= id1::QName ',' id2::QNameList
{
  top.pp = id1.pp ++ ", " ++ id2.pp ;
  top.qList = [id1] ++ id2.qList;
  top.errors := id1.errors ++ id2.errors; 
}

abstract production qNameListEmpty
top::QNameList ::=
{
  top.pp = "";
  top.qList = [];
  top.errors := [];
}