grammar silver:modification:copper;

-- This structure mirrors the structure of TermPrecList.
-- TermPrecList notes that it is poorly structured, perhaps
-- specifically with refernce how abstract / concrete is deliniated.
-- if TermPrecList has its structure refactored, so should TermList.

synthesized attribute qnames :: [QName];
nonterminal TermList with config, grammarName, unparse, location, qnames, termList, defs, errors, env;

synthesized attribute termList :: [String];

concrete production termListOne
terms::TermList ::= t::QName
{
   forwards to termList(t,termListNull(location=t.location), location=t.location);
}

concrete production termListCons
terms::TermList ::= t::QName ',' terms_tail::TermList
{
   forwards to termList(t,terms_tail,location=terms.location);
}


abstract production termList
top::TermList ::= h::QName t::TermList
{
  top.unparse = if t.unparse == ""
             then h.unparse
             else h.unparse ++ ", " ++ t.unparse;

  top.qnames = h :: t.qnames;

  production fName::String = h.lookupType.dcl.fullName;

  top.termList = [fName] ++ t.termList ;
  
  -- Itd be nice if we didnt need this guard
  top.defs = if null(h.lookupType.dcls) then t.defs 
             else pluckTermDef(top.grammarName, h.location, fName) :: t.defs;
  
  top.errors := t.errors;
  -- Since were looking it up in two ways, do the errors ourselves
  -- Todo: Consider: should this report a different error if the element of the list 
  -- exists but is not a terminal, i.e "Expected a terminal but got a _____"?
  top.errors <- if null(h.lookupType.dcls)
                then [err(h.location, "Undeclared terminal '" ++ h.name ++ "'.")]
                else if length(h.lookupType.dcls) > 1
                then [err(h.location, "Ambiguous reference to terminal '" ++ h.name ++ "'. Possibilities are:\n" ++ printPossibilities(h.lookupType.dcls))]
                else [];
}

abstract production termListNull
top::TermList ::=
{
  top.unparse = "";
  top.qnames = [];
  top.termList = [];
  top.defs = [];
  top.errors := [];
}
