grammar silver:extension:bidirtransform;

attribute ppDebug occurs on Def, Defs;
synthesized attribute filteredProdDefs::Defs occurs on Defs;
synthesized attribute isProdDef::Boolean occurs on Def;

aspect default production 
top::Def ::= 
{
  top.ppDebug = "defaultDef";
  top.isProdDef = false;
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
  top.isProdDef = true;
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
  top.filteredProdDefs = top;
}

aspect production consDefs 
top::Defs ::= e1::Def e2::Defs
{
  top.ppDebug = "consDefs(" ++ e1.ppDebug ++ "," ++ e2.ppDebug ++ ")";
  top.filteredProdDefs = if e1.isProdDef then consDefs(e1, e2.filteredProdDefs) 
    else e2.filteredProdDefs;
}