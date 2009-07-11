grammar silver:extension:convenience;
import silver:definition:core;


synthesized attribute qnames :: [QName];
synthesized attribute ids :: [Name];


nonterminal QNames2 with pp, qnames;
concrete production qNames2Two
top::QNames2 ::= id1::QName ',' id2::QName
{
  top.pp = id1.name ++ ", " ++ id2.name ;
  top.qnames = [id1'', id2''];
}

concrete production qNames2Cons
top::QNames2 ::= id1::QName ',' id2::QNames2
{
  top.pp = id1.name ++ ", " ++ id2.pp ;
  top.qnames = [id1''] ++ id2.qnames;
}

nonterminal QNames with pp, qnames;
concrete production qNamesSingle
top::QNames ::= id::QName
{
  top.pp = id.pp;
  top.qnames = [id''];
}

concrete production qNamesCons
top::QNames ::= id1::QName ',' id2::QNames
{
  top.pp = id1.pp ++ ", " ++ id2.pp ;
  top.qnames = [id1''] ++ id2.qnames;
}

nonterminal Names2 with pp, ids;
concrete production id2Single
top::Names2 ::= id::Name ',' id2::Name
{
  top.pp = id.name ++ ", " ++ id2.name;
  top.ids = [id'', id2''];
}

concrete production id2Cons
top::Names2 ::= id1::Name ',' id2::Names2
{
  top.pp = id1.name ++ ", " ++ id2.pp ;
  top.ids = [id1''] ++ id2.ids;
}

nonterminal Names with pp, ids;
concrete production idSingle
top::Names ::= id::Name
{
  top.pp = id.name;
  top.ids = [id''];
}

concrete production idCons
top::Names ::= id1::Name ',' id2::Names
{
  top.pp = id1.name ++ ", " ++ id2.pp ;
  top.ids = [id1''] ++ id2.ids;
}

function qualifyNames
[QName] ::= i::[Name]
{
  return if null(i) then [] else [qNameId(head(i))] ++ qualifyNames(tail(i));
}

function makeOccursDcls
AGDcl ::= l::Integer c::Integer ats::[QName] nts::[QName]
{
  return if null(ats) 
	 then agDclNone()
	 else agDclAppend(makeOccursDclsHelp(l, c, head(ats), nts), makeOccursDcls(l, c, tail(ats), nts));
}

function makeOccursDclsHelp
AGDcl ::= l::Integer c::Integer at::QName nts::[QName]
{
  return if null(nts) 
	 then agDclNone()
	 else agDclAppend(attributionDcl(attr_kwd, at, occurs_kwd, on_kwd, head(nts), semi_t),
		       makeOccursDclsHelp(l, c, at, tail(nts)));

  local attribute attr_kwd :: Attribute_kwd ;
  attr_kwd = terminal ( Attribute_kwd, "attribute", l, c);

  local attribute occurs_kwd :: Occurs_kwd ;
  occurs_kwd = terminal ( Occurs_kwd, "occurs", l, c);
 
  local attribute on_kwd :: On_kwd ;
  on_kwd = terminal ( On_kwd, "on", l, c);

  local attribute semi_t :: Semi_t ;
  semi_t = terminal ( Semi_t, ";", l, c);
}

function makeNTDcls
AGDcl ::= l::Integer c::Integer nts::[Name]
{
  return if null(nts) 
	 then agDclNone()
	 else agDclAppend(nonterminalDcl(terminal(NonTerminal_kwd, "nontermial", l, c), head(nts), terminal(Semi_t, ";", l, c)),
		       makeNTDcls(l, c, tail(nts)));
}

function makeClosedNTDcls
AGDcl ::= l::Integer c::Integer nts::[Name]
{
  return if null(nts) 
	 then agDclNone()
	 else agDclAppend(closedNonterminalDcl(terminal(Closed_kwd, "closed", l, c),
					    terminal(NonTerminal_kwd, "nontermial", l, c),
					    head(nts),
					    terminal(Semi_t, ";", l, c)),
		       makeNTDcls(l, c, tail(nts)));
}

function makeSynDcls
AGDcl ::= l::Integer c::Integer t::Type atts::[Name]
{
  return if null(atts)
	 then agDclNone()
	 else agDclAppend(attributeDclSyn(terminal(Synthesized_kwd, "synthesized", l, c),
				       terminal(Attribute_kwd, "attribute", l, c),
			      	       head(atts),
			      	       terminal(HasType_t, "::", l, c),
			               t,
			               terminal(Semi_t, ";", l, c)),
		       makeSynDcls(l, c, t, tail(atts)));
}

function makeInhDcls
AGDcl ::= l::Integer c::Integer t::Type atts::[Name]
{
  return if null(atts)
	 then agDclNone()
	 else agDclAppend(attributeDclInh(terminal(Inherited_kwd, "inherited", l, c),
				       terminal(Attribute_kwd, "attribute", l, c),
			      	       head(atts),
			      	       terminal(HasType_t, "::", l, c),
			               t,
			               terminal(Semi_t, ";", l, c)),
		       makeInhDcls(l, c, t, tail(atts)));
}
