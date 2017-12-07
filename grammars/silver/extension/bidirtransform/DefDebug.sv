grammar silver:extension:bidirtransform;

attribute ppDebug occurs on Def;

aspect default production 
top::Def ::= 
{
    top.ppDebug = "defaultDef";
}

aspect production typeDef
top::Def ::= d::EnvItem
{
top.ppDebug = "typeDef";
}

aspect production valueDef
top::Def ::= d::EnvItem
{
top.ppDebug = "valueDef";
}

aspect production attrDef
top::Def ::= d::EnvItem
{
top.ppDebug = "attrDef";
}

aspect production prodDclDef
top::Def ::= d::EnvItem
{
top.ppDebug = "prodDclDef";
}

aspect production paDef
top::Def ::= d::DclInfo
{
top.ppDebug = "paDef";
}

aspect production oDef
top::Def ::= d::DclInfo
{
top.ppDebug = "oDef";
}

