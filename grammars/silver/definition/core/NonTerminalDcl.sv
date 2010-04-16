grammar silver:definition:core;
import silver:definition:env;

concrete production nonterminalDcl
top::AGDcl ::= 'nonterminal' id::Name ';'
{
  production attribute extraDcls :: AGDcl with agDclAppend;
  extraDcls := defaultNonterminalDcl(id);

  forwards to extraDcls;
}

abstract production defaultNonterminalDcl
top::AGDcl ::= id::Name
{
  top.pp = "nonterminal " ++ id.pp ++ ";";
  top.location = id.location;

  top.moduleNames = [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs = addTypeDcl(fName, ntTypeRep(fName), 
	     addFullNameDcl(id.name, fName,  emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(id.name, top.env)) > 1 
        then [err(top.location, "Name '" ++ id.pp ++ "' is already bound.")]
        else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getTypeDclOne(fName, top.env)) > 1 
       then [err(top.location, "Type '" ++ fName ++ "' is already bound.")]
       else [];	

  top.errors := er1 ++ er2;
}

