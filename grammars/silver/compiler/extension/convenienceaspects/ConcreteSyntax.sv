grammar silver:compiler:extension:convenienceaspects;


concrete production convenienceAspects_c
top::AGDcl ::= 'aspect' attr::QName 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  local attrDcl::DclInfo = attr.lookupAttribute.dcl;


  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(ml.aspectDcls);
  -- local trees::String = implode(",\n\n",(map(\agdcl::AGDcl -> agdcl.unparse, fwrd)));

  forwards to unsafeTrace(fwrd, print(fwrd.unparse ++ "\n\n", unsafeIO()));
}
