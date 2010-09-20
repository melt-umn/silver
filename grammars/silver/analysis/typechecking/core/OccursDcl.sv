grammar silver:analysis:typechecking:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  local attribute occursCheck :: [Decorated DclInfo];
  occursCheck = getOccursDcl(a.lookupAttribute.fullName, nt.lookupType.fullName, top.env);
  
  top.typeErrors := if length(occursCheck) > 1
                   then [err(a.location, "attribute '" ++ a.name ++ "' already occurs on '" ++ nt.name ++ "'.")]
                   else [];
  -- TODO: we should ensure either the attribute or the nonterminal is in this grammar.
}

