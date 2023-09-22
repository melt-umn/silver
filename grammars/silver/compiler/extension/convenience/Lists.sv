grammar silver:compiler:extension:convenience;

tracked nonterminal QNameWithTL with unparse,qnwtQN, qnwtTL;
synthesized attribute qnwtQN :: QName;
synthesized attribute qnwtTL :: BracketedOptTypeExprs;

concrete production qNameWithTL
top::QNameWithTL ::= qn::QName tl::BracketedOptTypeExprs
{
  top.unparse = qn.unparse ++ tl.unparse;
  top.qnwtQN = qn;
  top.qnwtTL = tl;
}

{- QNames2 is needed because we would otherwise have a syntactic ambiguity with
   the ordinary declarations. QNames2 requires 2 or more QNames, so that if they
   list just one, then it goes to the ordinary, non-convenience extension form.
 -}
 
tracked nonterminal QNames2 with unparse, qnames;
tracked nonterminal QNames with unparse, qnames;

synthesized attribute qnames :: [QNameWithTL];

concrete production qNames2Two
top::QNames2 ::= id1::QNameWithTL ',' id2::QNameWithTL
{
  top.unparse = id1.unparse ++ ", " ++ id2.unparse ;
  top.qnames = [id1, id2];
}

concrete production qNames2Cons
top::QNames2 ::= id1::QNameWithTL ',' id2::QNames2
{
  top.unparse = id1.unparse ++ ", " ++ id2.unparse ;
  top.qnames = [id1] ++ id2.qnames;
}


concrete production qNamesSingle
top::QNames ::= id::QNameWithTL
{
  top.unparse = id.unparse;
  top.qnames = [id];
}

concrete production qNamesCons
top::QNames ::= id1::QNameWithTL ',' id2::QNames
{
  top.unparse = id1.unparse ++ ", " ++ id2.unparse ;
  top.qnames = [id1] ++ id2.qnames;
}

--------------------------------------------------------------------------------

function makeOccursDcls
AGDcl ::= l::Location ats::[QNameWithTL] nts::[QNameWithTL]
{
  return if null(ats) 
	 then emptyAGDcl()
	 else appendAGDcl(makeOccursDclsHelp(l, head(ats), nts), makeOccursDcls(l, tail(ats), nts));
}

function makeOccursDclsHelp
AGDcl ::= l::Location at::QNameWithTL nts::[QNameWithTL]
{
  return if null(nts) 
	 then emptyAGDcl()
	 else appendAGDcl(
	        attributionDcl('attribute', at.qnwtQN, at.qnwtTL, 'occurs', 'on', head(nts).qnwtQN, head(nts).qnwtTL, ';'),
		makeOccursDclsHelp(l, at, tail(nts)));
}



{- TEMPORARILY DISABLE NAMES and NAMES2

synthesized attribute ids :: [Name];

tracked nonterminal Names2 with unparse, ids;
concrete production id2Single
top::Names2 ::= id::Name ',' id2::Name
{
  top.unparse = id.name ++ ", " ++ id2.name;
  top.ids = [id, id2];
}

concrete production id2Cons
top::Names2 ::= id1::Name ',' id2::Names2
{
  top.unparse = id1.name ++ ", " ++ id2.unparse ;
  top.ids = [id1] ++ id2.ids;
}

tracked nonterminal Names with unparse, ids;
concrete production idSingle
top::Names ::= id::Name
{
  top.unparse = id.name;
  top.ids = [id];
}

concrete production idCons
top::Names ::= id1::Name ',' id2::Names
{
  top.unparse = id1.name ++ ", " ++ id2.unparse ;
  top.ids = [id1] ++ id2.ids;
}

function qualifyNames
[QName] ::= i::[Name]
{
  return if null(i) then [] else qNameId(head(i), location=head(i).location) :: qualifyNames(tail(i));
}

-}

