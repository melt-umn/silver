grammar silver:compiler:extension:scopegraphs2;

--

production edgeSpecNoType
top::AGDcl ::= label::String
{
  forwards to Silver_AGDcl {
    inherited attribute foo::Integer;
  };
}

production edgeSpecWithType
top::AGDcl ::= label::String te::TypeExpr
{
  forwards to Silver_AGDcl {
    inherited attribute foo::Integer;
  };
}