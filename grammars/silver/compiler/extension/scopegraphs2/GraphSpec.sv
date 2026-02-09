grammar silver:compiler:extension:scopegraphs2;


-- Scope graph identified by the edge labels within it

production graphSpec
top::AGDcl ::= ident::String qns::FlowSpecInhs
{
  forwards to Silver_AGDcl {
    type $Name{name(ident)} = 
      $TypeExpr{inhSetTypeExpr(terminal(InhSetLCurly_t, "{"), @qns, '}')};
  };
}
