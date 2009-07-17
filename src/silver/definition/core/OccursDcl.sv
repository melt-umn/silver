grammar silver:definition:core;
import silver:definition:env;

concrete production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nt.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [::String];

  top.defs = addOccursDcl(afName, nfName, emptyDefs());

  local attribute er1 :: [Decorated Message];
  er1 = if null(attrs)
	then [err(top.location, "Attribute '" ++ a.name ++ "' is not declared.")]
	else [::Decorated Message];

  local attribute er2 :: [Decorated Message];
  er2 = if null(types)
	then [err(top.location, "Nonterminal '" ++ nt.name ++ "' is not declared.")] 
	else [::Decorated Message];

  local attribute er3 :: [Decorated Message];
  er3 = if length(nfNames) > 1
	then [err(top.location, "Nonterminal '" ++ nt.name ++ "' has multiple delarations.")] 
	else [::Decorated Message];

  local attribute er4 :: [Decorated Message];
  er4 = if length(afNames) > 1
	then [err(top.location, "Attribute '" ++ a.name ++ "' has multiple delarations.")] 
	else [::Decorated Message];

  top.errors := er1 ++ er2 ++ er3 ++ er4;

  production attribute afNames :: [Decorated EnvItem];
  afNames = getFullNameDcl(a.name, top.env);

  production attribute afName :: String;
  afName = if !null(afNames) then head(afNames).fullName else a.name;

  production attribute nfNames :: [Decorated EnvItem];
  nfNames = getFullNameDcl(nt.name, top.env);

  production attribute nfName :: String;
  nfName = if !null(nfNames) then head(nfNames).fullName else nt.name;

  production attribute attrs :: [Decorated EnvItem];
  attrs = getAttributeDcl(afName, top.env);

  local attribute types :: [Decorated EnvItem];
  types = getTypeDcl(nfName, top.env);
}
