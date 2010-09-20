grammar silver:analysis:typechecking:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName '<' tlat::TypeList '>' 'occurs' 'on' nt::QName '<' tlnt::TypeList '>' ';'
{
  local attribute occursCheck :: [Decorated DclInfo];
  occursCheck = getOccursDcl(a.lookupAttribute.fullName, nt.lookupType.fullName, top.env);
  
  top.typeErrors := if length(occursCheck) > 1
                   then [err(a.location, "attribute '" ++ a.name ++ "' already occurs on '" ++ nt.name ++ "'.")]
                   else [];
  
  -- TODO: some thought needs to be done on when to check for mismatching lengths.  right now, we're doing that in errors, but maybe it should be here?
  
  -- TODO: we should ensure either the attribute or the nonterminal is in this grammar.
}

