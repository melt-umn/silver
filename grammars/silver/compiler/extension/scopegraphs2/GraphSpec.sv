grammar silver:compiler:extension:scopegraphs2;

--

production graphSpec
top::AGDcl ::= ident::String qns::QNames
{
  forwards to Silver_AGDcl {
    inherited attribute foo::Integer;
  };
}
