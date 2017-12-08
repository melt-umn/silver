grammar silver:extension:bidirtransform;

attribute ppDebug occurs on Def, Defs;

aspect default production 
top::Def ::= 
{
  top.ppDebug = "defaultDef";
}

aspect production typeDef
top::Def ::= d::EnvItem
{
  top.ppDebug = "typeDef: " ++ d.ppDebug;
}

aspect production valueDef
top::Def ::= d::EnvItem
{
  top.ppDebug = "valueDef: " ++ d.ppDebug;
}

aspect production attrDef
top::Def ::= d::EnvItem
{
  top.ppDebug = "attrDef: " ++ d.ppDebug;
}

aspect production prodDclDef
top::Def ::= d::EnvItem
{
  top.ppDebug = "prodDclDef: " ++ d.ppDebug;
}

aspect production paDef
top::Def ::= d::DclInfo
{
  top.ppDebug = "paDef: " ++ d.ppDebug;
}

aspect production oDef
top::Def ::= d::DclInfo
{
  top.ppDebug = "oDef: " ++ d.ppDebug;
}

aspect production nilDefs 
top::Defs ::= 
{
  top.ppDebug = "nilDefs";
}

aspect production consDefs 
top::Defs ::= e1::Def e2::Defs
{
  top.ppDebug = "consDefs(" ++ e1.ppDebug ++ "," ++ e2.ppDebug ++ ")";
}