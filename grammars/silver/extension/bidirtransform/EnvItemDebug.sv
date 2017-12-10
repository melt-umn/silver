grammar silver:extension:bidirtransform;

attribute ppDebug occurs on EnvItem;
attribute prodNamedSig occurs on EnvItem;

aspect default production 
top::EnvItem ::= 
{
    top.ppDebug = "defaultEnvItem";
    top.prodNamedSig = [];
}

aspect production renamedEnvItem
ei::EnvItem ::= newname::String di::DclInfo
{
    ei.ppDebug = "(" ++ newname ++ ") " ++ di.ppDebug;
    ei.prodNamedSig = di.prodNamedSig;    
} 

aspect production fullNameEnvItem
ei::EnvItem ::= di::DclInfo
{
    ei.ppDebug = di.ppDebug;
    ei.prodNamedSig = di.prodNamedSig;
}

aspect production onlyRenamedEnvItem
ei::EnvItem ::= newname::String di::DclInfo
{
    ei.ppDebug = "(" ++ newname ++ ") " ++ di.ppDebug;    
    ei.prodNamedSig = di.prodNamedSig;    
}