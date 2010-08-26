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

  top.defs = addNtDcl(top.grammarName, id.location, fName, nonterminalTypeExp(fName, []), emptyDefs()); -- TODO []

  top.errors :=
       if length(getTypeDcl(fName, top.env)) > 1 
       then [err(top.location, "Type '" ++ fName ++ "' is already bound.")]
       else [];
}

