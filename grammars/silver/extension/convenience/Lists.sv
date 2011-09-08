grammar silver:extension:convenience;

nonterminal QNameWithTL with pp,qnwtQN, qnwtTL;
synthesized attribute qnwtQN :: QName;
synthesized attribute qnwtTL :: BracketedOptTypeList;

concrete production qNameWithTL
top::QNameWithTL ::= qn::QName tl::BracketedOptTypeList
{
  top.pp = qn.pp ++ tl.pp;
  top.qnwtQN = qn;
  top.qnwtTL = tl;
}

{- QNames2 is needed because we would otherwise have a syntactic ambiguity with
   the ordinary declarations. QNames2 requires 2 or more QNames, so that if they
   list just one, then it goes to the ordinary, non-convenience extension form.
 -}
 
nonterminal QNames2 with pp, qnames;
nonterminal QNames with pp, qnames;

synthesized attribute qnames :: [QNameWithTL];

concrete production qNames2Two
top::QNames2 ::= id1::QNameWithTL ',' id2::QNameWithTL
{
  top.pp = id1.pp ++ ", " ++ id2.pp ;
  top.qnames = [id1, id2];
}

concrete production qNames2Cons
top::QNames2 ::= id1::QNameWithTL ',' id2::QNames2
{
  top.pp = id1.pp ++ ", " ++ id2.pp ;
  top.qnames = [id1] ++ id2.qnames;
}


concrete production qNamesSingle
top::QNames ::= id::QNameWithTL
{
  top.pp = id.pp;
  top.qnames = [id];
}

concrete production qNamesCons
top::QNames ::= id1::QNameWithTL ',' id2::QNames
{
  top.pp = id1.pp ++ ", " ++ id2.pp ;
  top.qnames = [id1] ++ id2.qnames;
}

--------------------------------------------------------------------------------

function makeOccursDcls
AGDcl ::= l::Integer c::Integer ats::[QNameWithTL] nts::[QNameWithTL]
{
  return if null(ats) 
	 then agDclNone()
	 else agDclAppend(makeOccursDclsHelp(l, c, head(ats), nts), makeOccursDcls(l, c, tail(ats), nts));
}

function makeOccursDclsHelp
AGDcl ::= l::Integer c::Integer at::QNameWithTL nts::[QNameWithTL]
{
  return if null(nts) 
	 then agDclNone()
	 else agDclAppend(
	        attributionDcl(attr_kwd, at.qnwtQN, at.qnwtTL, occurs_kwd, on_kwd, head(nts).qnwtQN, head(nts).qnwtTL, ';'),
		makeOccursDclsHelp(l, c, at, tail(nts)));

  local attribute attr_kwd :: Attribute_kwd ;
  attr_kwd = terminal ( Attribute_kwd, "attribute", l, c);

  local attribute occurs_kwd :: Occurs_kwd ;
  occurs_kwd = terminal ( Occurs_kwd, "occurs", l, c);
 
  local attribute on_kwd :: On_kwd ;
  on_kwd = terminal ( On_kwd, "on", l, c);
}



{- TEMPORARILY DISABLE NAMES and NAMES2

synthesized attribute ids :: [Name];

nonterminal Names2 with pp, ids;
concrete production id2Single
top::Names2 ::= id::Name ',' id2::Name
{
  top.pp = id.name ++ ", " ++ id2.name;
  top.ids = [id, id2];
}

concrete production id2Cons
top::Names2 ::= id1::Name ',' id2::Names2
{
  top.pp = id1.name ++ ", " ++ id2.pp ;
  top.ids = [id1] ++ id2.ids;
}

nonterminal Names with pp, ids;
concrete production idSingle
top::Names ::= id::Name
{
  top.pp = id.name;
  top.ids = [id];
}

concrete production idCons
top::Names ::= id1::Name ',' id2::Names
{
  top.pp = id1.name ++ ", " ++ id2.pp ;
  top.ids = [id1] ++ id2.ids;
}

function qualifyNames
[QName] ::= i::[Name]
{
  return if null(i) then [] else [qNameId(head(i))] ++ qualifyNames(tail(i));
}

-}

