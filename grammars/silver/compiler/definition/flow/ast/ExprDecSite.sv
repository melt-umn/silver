grammar silver:compiler:definition:flow:ast;

imports silver:compiler:analysis:uniqueness;

synthesized attribute decSiteFlowVertexInfo::VertexType occurs on ExprDecSite;

aspect production localDecSite
top::ExprDecSite ::= fName::String
{
  top.decSiteFlowVertexInfo = localVertexType(fName);
}

aspect production forwardDecSite
top::ExprDecSite ::=
{
  top.decSiteFlowVertexInfo = forwardVertexType;
}

aspect production anonDecSite
top::ExprDecSite ::= x::String
{
  top.decSiteFlowVertexInfo = anonVertexType(x);
}

aspect production subtermDecSite
top::ExprDecSite ::= parent::ExprDecSite prodName::String sigName::String
{
  top.decSiteFlowVertexInfo = subtermVertexType(parent, prodName, sigName);
}
