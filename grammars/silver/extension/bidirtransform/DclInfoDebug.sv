grammar silver:extension:bidirtransform;

import silver:definition:regex;

attribute ppDebug occurs on DclInfo;
attribute prodNamedSig occurs on DclInfo;
attribute absProdNamedSig occurs on DclInfo;
attribute cncProdNamedSig occurs on DclInfo;

aspect default production 
top::DclInfo ::= 
{
    top.ppDebug = "defaultDclInfo";
    top.prodNamedSig = [];
    top.absProdNamedSig = [];
    top.cncProdNamedSig = [];
}
aspect production childDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.ppDebug = "childDcl " ++ fn; 
}
aspect production lhsDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.ppDebug = "lhsDcl " ++ fn; 
}
aspect production localDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.ppDebug = "localDcl " ++ fn; 
}
aspect production forwardDcl
top::DclInfo ::= sg::String sl::Location ty::Type
{
  top.ppDebug = "forwardDcl";
}
aspect production prodDcl
top::DclInfo ::= sg::String sl::Location ns::NamedSignature isAbstract::Boolean
{
  top.ppDebug = "prodDcl";
  top.prodNamedSig = [ns];
  top.absProdNamedSig = if isAbstract then [ns] else [];
  top.cncProdNamedSig = if !isAbstract then [ns] else [];
}
aspect production funDcl
top::DclInfo ::= sg::String sl::Location ns::NamedSignature
{
  top.ppDebug = "funDcl";
}
aspect production globalValueDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.ppDebug = "globalValueDcl " ++ fn; 
}
aspect production ntDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type closed::Boolean
{
  top.ppDebug = "ntDcl " ++ fn; 
}
aspect production termDcl
top::DclInfo ::= sg::String sl::Location fn::String regex::Regex
{
  top.ppDebug = "termDcl " ++ fn; 
}
aspect production lexTyVarDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.ppDebug = "lexTyVarDcl " ++ fn; 
}
aspect production synDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.ppDebug = "synDcl " ++ fn; 
}
aspect production inhDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.ppDebug = "inhDcl " ++ fn; 
}
aspect production annoDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.ppDebug = "annoDcl " ++ fn; 
}
aspect production paDcl
top::DclInfo ::= sg::String sl::Location ns::NamedSignature dcls::[Def]
{
  top.ppDebug = "paDcl";
}
aspect production occursDcl
top::DclInfo ::= sg::String sl::Location fnnt::String fnat::String ntty::Type atty::Type
{
  top.ppDebug = "occursDcl " ++ fnnt ++ " " ++ fnat; 
}
aspect production annoInstanceDcl
top::DclInfo ::= sg::String sl::Location fnnt::String fnat::String ntty::Type atty::Type
{
  top.ppDebug = "annoInstanceDcl " ++ fnnt ++ " " ++ fnat; 
}