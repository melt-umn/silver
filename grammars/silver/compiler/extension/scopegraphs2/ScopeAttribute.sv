grammar silver:compiler:extension:scopegraphs2;

--

production scopeAttribute
top::AGDcl ::= sg::IdUpper_t lab::String name::String
{
  forwards to Silver_AGDcl {
    inherited attribute foo::Integer;
  };
}