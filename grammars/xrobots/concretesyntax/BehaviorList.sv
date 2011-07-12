grammar xrobots:concretesyntax ;

import xrobots:terminals ;
import xrobots:abstractsyntax ;


nonterminal BehaviorList_c with pp;
synthesized attribute ast_BehaviorList :: 
                          BehaviorList occurs on BehaviorList_c ;



concrete production behaviorlist2_c
bl::BehaviorList_c ::=  b::Behavior_c bl_rest::BehaviorList_c  
{
 bl.pp = b.pp ++ "\n" ++ bl_rest.pp ;
 bl.ast_BehaviorList = behaviorlist_cons(b.ast_Behavior, 
                                          bl_rest.ast_BehaviorList);
}

concrete production behaviorList_empty_c
bl::BehaviorList_c ::= 
{
 bl.pp = "" ;
 bl.ast_BehaviorList = behaviorlist_empty() ;
}


