grammar silver:extension:bidirtransform;

attribute ppDebug occurs on EnvItem;
attribute prodNamedSig occurs on EnvItem;
attribute absProdNamedSig occurs on EnvItem;
attribute cncProdNamedSig occurs on EnvItem;

aspect default production 
top::EnvItem ::= 
{
    top.ppDebug = "defaultEnvItem";
    top.prodNamedSig = [];
    top.absProdNamedSig = [];
    top.cncProdNamedSig = [];
}

aspect production renamedEnvItem
ei::EnvItem ::= newname::String di::DclInfo
{
    ei.ppDebug = "(" ++ newname ++ ") " ++ di.ppDebug;
    ei.prodNamedSig = di.prodNamedSig;    
    ei.absProdNamedSig = di.absProdNamedSig;    
    ei.cncProdNamedSig = di.cncProdNamedSig;    
} 

aspect production fullNameEnvItem
ei::EnvItem ::= di::DclInfo
{
    ei.ppDebug = di.ppDebug;
    ei.prodNamedSig = di.prodNamedSig;
    ei.absProdNamedSig = di.absProdNamedSig;
    ei.cncProdNamedSig = di.cncProdNamedSig;
}

aspect production onlyRenamedEnvItem
ei::EnvItem ::= newname::String di::DclInfo
{
    ei.ppDebug = "(" ++ newname ++ ") " ++ di.ppDebug;    
    ei.prodNamedSig = di.prodNamedSig;    
    ei.absProdNamedSig = di.absProdNamedSig;    
    ei.cncProdNamedSig = di.cncProdNamedSig;    
}