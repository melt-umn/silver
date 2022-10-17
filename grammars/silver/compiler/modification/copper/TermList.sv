grammar silver:compiler:modification:copper;

-- This structure mirrors the structure of TermPrecList.
-- TermPrecList notes that it is poorly structured, perhaps
-- specifically with refernce how abstract / concrete is deliniated.
-- if TermPrecList has its structure refactored, so should TermList.

nonterminal TermList with config, grammarName, unparse, location, termList, defs, errors, env;

monoid attribute termList :: [String];

propagate config, grammarName, env, errors, termList on TermList;

concrete production termListOne
terms::TermList ::= t::QName
{
  forwards to termList(t,termListNull(location=t.location), location=t.location);
} action {
  insert semantic token IdType_t at t.baseNameLoc;
}

concrete production termListCons
terms::TermList ::= t::QName ',' terms_tail::TermList
{
  forwards to termList(t,terms_tail,location=terms.location);
} action {
  insert semantic token IdType_t at t.baseNameLoc;
}


abstract production termList
top::TermList ::= h::QName t::TermList
{
  top.unparse =
   if t.unparse == ""
   then h.unparse
   else h.unparse ++ ", " ++ t.unparse;

  production fName::String = h.lookupType.dcl.fullName;

  top.termList <- [fName];
  
  -- Itd be nice if we didnt need this guard
  top.defs := if null(h.lookupType.dcls) then t.defs 
              else pluckTermDef(top.grammarName, h.location, fName) :: t.defs;
  
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
  top.defs := [];
}
