grammar silver:modification:copper;

aspect production actionCode_c
top::ActionCode_c ::= '{' stmts::ProductionStmts '}'
{
    stmts.defaultInheritedAnnos = [];
}