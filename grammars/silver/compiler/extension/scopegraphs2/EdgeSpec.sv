grammar silver:compiler:extension:scopegraphs2;

--

production edgeSpecNoType
top::AGDcl ::= label::String sg::IdUpper_t
{
  local edgeDcls::AGDcl = appendAGDcl (
    dclInhProd(label, sg),
    edgeLabelProd(label, sg)
    -- todo
  );

  forwards to @edgeDcls;
}

production edgeSpecWithType
top::AGDcl ::= label::String sg::IdUpper_t te::TypeExpr
{
  local edgeDcls::AGDcl = appendAGDcl(
    dclDatumProd(label, ^te),
    appendAGDcl(
      edgeLabelProd(label, sg),
      dclInhProd(label, sg)
    )
    -- todo
  );

  forwards to @edgeDcls;
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
top::AGDcl ::= label::String sg::IdUpper_t
{
  forwards to Silver_AGDcl {
    inherited attribute $Name{name(label)}::[Decorated Scope with $TypeExpr{nominalTypeExpr(qNameTypeId(sg))}]
      occurs on Scope;
  };
}

production edgeLabelProd
top::AGDcl ::= label::String sg::IdUpper_t
{
  forwards to Silver_AGDcl {
    production $Name{name("label_" ++ label)}
    top::Label<$TypeExpr{nominalTypeExpr(qNameTypeId(sg))}> ::=
    {
      top.label = $Expr{stringConst(terminal(String_t, "\"" ++ label ++ "\""))};
      top.demand = \s::Decorated Scope with $TypeExpr{nominalTypeExpr(qNameTypeId(sg))} -> s.$QName{qName(label)};
    }
  };
}