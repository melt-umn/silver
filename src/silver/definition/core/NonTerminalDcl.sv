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

  top.moduleNames = [::String];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs = addTypeDcl(fName, ntTypeRep(fName), 
	     addFullNameDcl(id.name, fName,  emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(id.name, top.env)) > 1 
        then [err(top.location, "Name '" ++ id.pp ++ "' is already bound.")]
        else [::Decorated Message];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getTypeDclOne(fName, top.env)) > 1 
       then [err(top.location, "Type '" ++ fName ++ "' is already bound.")]
       else [::Decorated Message];	

  top.errors := er1 ++ er2;
}

concrete production closedNonterminalDcl
top::AGDcl ::= 'closed' 'nonterminal' id::Name ';'
{

  top.pp = "closed nonterminal " ++ id.pp ++ ";";
  top.location = id.location;

  production attribute extraDcls :: AGDcl with agDclAppend;
  extraDcls := defaultClosedNonterminalDcl(id);

  forwards to extraDcls;
}

abstract production defaultClosedNonterminalDcl
top::AGDcl ::=  id::Name
{
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs = addTypeDcl(fName, ntTypeRep(fName),
	     addCloseDcl(fName,		
	     addFullNameDcl(id.name, fName, emptyDefs())));

  forwards to defaultNonterminalDcl(id);
}

concrete production closeNonterminalDcl
top::AGDcl ::= 'close' 'nonterminal' q::QName ';'
{
  top.pp = "close " ++ q.pp++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [::String];

  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

  --whether it is bound to a value
  production attribute types :: [Decorated EnvItem];
  types = getTypeDcl(fName, top.env);

  local attribute er1 :: [Decorated Message];
  er1 = if null(types)
	then [err(top.location, "Type '" ++ q.pp ++ "' is not declared." )]
	else [::Decorated Message];

  top.errors := er1;

  top.defs = addCloseDcl(q.name,  emptyDefs());
}
