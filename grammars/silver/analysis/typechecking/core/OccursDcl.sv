grammar silver:analysis:typechecking:core;
import silver:definition:core;
import silver:definition:env;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  local attribute num :: Integer;
  num = length(filterEnvItems(keepOccurs(afName, nfName), getOccursDcls(top.env)));

  top.typeErrors = if num > 1
	           then [err(top.location, "Attribute '" ++ a.name ++ "' already decorates '" ++ nt.name ++ "'")] 
		   else [];
}


abstract production keepOccurs
top::EnvFilter ::= a::String nt::String
{
  top.keep = top.inEnvItem.isOccursDeclaration
	  && top.inEnvItem.itemName == a
	  && top.inEnvItem.decoratesName == nt;
}
