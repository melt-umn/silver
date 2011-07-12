grammar xrobots:concretesyntax ;

import xrobots:terminals ;
import xrobots:abstractsyntax ;

nonterminal Behavior_c with pp;

synthesized attribute ast_Behavior:: Behavior occurs on Behavior_c;


{--
concrete production behavior_c
d::Dec_c ::= init::OptInit_c bt::Behavior_t n::Id_t pl::OptionalParamDecList_c
             '{' dl::DecList_c entry::Entry_c ex::Exit_c '}'
{
 d.pp = "A Behavior\n" ++ pl.pp ++ dl.pp ++ entry.pp ++ ex.pp;
-- d.ast_Dec = behavior (n, pl.ast_DecList, dl.ast_DecList, 
  --                     entry.ast_OnEntry, ucab.ast_UCABBlock, exit.ast_OnExit, 
    --                   init.isInit_c);
}
--}

concrete production behavior_c
d::Behavior_c ::= init::OptInit_c bt::Behavior_t n::Id_t
		  pl::OptionalParamDecList_c
               '{' 
		 dl::DecList_c 
		 bl::BehaviorList_c 
		 entry::Entry_c
		 trans::TransList_c 
		 ex::Exit_c '}'
{
 d.pp = "A Behavior\n" ++ pl.pp ++ dl.pp ++ bl.pp  ++ entry.pp ++ ex.pp;
 d.ast_Behavior =
              behavior (n, 
			pl.ast_DecList, 
			dl.ast_DecList, 
			bl.ast_BehaviorList,
			entry.ast_Entry,  
			trans.ast_TransList,
			ex.ast_Exit
                       );
}

nonterminal OptInit_c with isInit_c ;
synthesized attribute isInit_c :: Boolean ;

concrete production init_modifier
oi::OptInit_c ::= 'Initial'
{ oi.isInit_c = true ;  
}

concrete production no_init_modifier
oi::OptInit_c ::= 
{ oi.isInit_c = false ;  
}

