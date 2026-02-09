grammar silver:compiler:extension:scopegraphs2;

--

production edgeSpecNoType
top::AGDcl ::= label::String sg::IdUpper_t
{
  forwards to Silver_AGDcl {
    inherited attribute $Name{name(label)}::Decorated Scope with $TypeExpr{nominalTypeExpr(qNameTypeId(sg))}
      occurs on Scope;
  };
}

production edgeSpecWithType
top::AGDcl ::= label::String sg::IdUpper_t te::TypeExpr
{
  local edgeDcls::AGDcl = appendAGDcl(
    dclDatumProd(label, ^te),
    dclInhProd(label, sg, ^te)
    -- more todo
  );

  forwards to @edgeDcls;

  top.errors := if !null(te.errors) then te.errors else edgeDcls.errors;
}

--

production dclDatumProd
top::AGDcl ::= label::String te::TypeExpr
{
  forwards to Silver_AGDcl {
    production $Name{name("datum_" ++ label)}
    top::Datum ::= d::$TypeExpr{^te}
    {}
  };
}

production dclInhProd
top::AGDcl ::= label::String sg::IdUpper_t te::TypeExpr
{
  forwards to Silver_AGDcl {
    inherited attribute $Name{name(label)}::Decorated Scope with $TypeExpr{nominalTypeExpr(qNameTypeId(sg))}
      occurs on Scope;
  };
}