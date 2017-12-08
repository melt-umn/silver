grammar silver:extension:bidirtransform;

attribute ppDebug occurs on EnvItem;

aspect default production 
top::EnvItem ::= 
{
    top.ppDebug = "defaultEnvItem";
}

aspect production renamedEnvItem
ei::EnvItem ::= newname::String di::DclInfo
{
    ei.ppDebug = "(" ++ newname ++ ") " ++ di.ppDebug;
} 

aspect production fullNameEnvItem
ei::EnvItem ::= di::DclInfo
{
    ei.ppDebug = di.ppDebug;
}

aspect production onlyRenamedEnvItem
ei::EnvItem ::= newname::String di::DclInfo
{
    ei.ppDebug = "(" ++ newname ++ ") " ++ di.ppDebug;    
}