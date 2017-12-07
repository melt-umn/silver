grammar silver:extension:bidirtransform;

import silver:definition:regex;

attribute ppDebug occurs on DclInfo;

aspect default production 
top::DclInfo ::= 
{
    top.ppDebug = "defaultDclInfo";
}

aspect production childDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.ppDebug = "childDcl";
}
aspect production lhsDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.ppDebug = "lhsDcl";
}
aspect production localDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.ppDebug = "localDcl";
}
aspect production forwardDcl
top::DclInfo ::= sg::String sl::Location ty::Type
{
  top.ppDebug = "forwardDcl";
}
aspect production prodDcl
top::DclInfo ::= sg::String sl::Location ns::NamedSignature
{
  top.ppDebug = "prodDcl";
}
aspect production funDcl
top::DclInfo ::= sg::String sl::Location ns::NamedSignature
{
  top.ppDebug = "funDcl";
}
aspect production globalValueDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.ppDebug = "globalValueDcl";
}
aspect production ntDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type closed::Boolean
{
  top.ppDebug = "ntDcl";
}
aspect production termDcl
top::DclInfo ::= sg::String sl::Location fn::String regex::Regex
{
  top.ppDebug = "termDcl";
}
aspect production lexTyVarDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.ppDebug = "lexTyVarDcl";
}
aspect production synDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.ppDebug = "synDcl";
}
aspect production inhDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.ppDebug = "inhDcl";
}
aspect production annoDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.ppDebug = "annoDcl";
}
aspect production paDcl
top::DclInfo ::= sg::String sl::Location ns::NamedSignature dcls::[Def]
{
  top.ppDebug = "paDcl";
}
aspect production occursDcl
top::DclInfo ::= sg::String sl::Location fnnt::String fnat::String ntty::Type atty::Type
{
  top.ppDebug = "occursDcl";
}
aspect production annoInstanceDcl
top::DclInfo ::= sg::String sl::Location fnnt::String fnat::String ntty::Type atty::Type
{
  top.ppDebug = "annoInstanceDcl";
}