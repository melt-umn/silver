grammar tutorials:xrobots:concretesyntax ;

import tutorials:xrobots:terminals ;
--import tutorials:xrobots:abstractsyntax ;

concrete production behavior_c
d::Dec_c ::= init::OptInit_c bt::Behavior_t n::Id_t pl::OptionalParamDecList_c
             '{' dl::DecList_c entry::OnEntry_c ucab::UCABBlock_c ex::OnExit_c '}'
{
 d.pp = "A Behavior\n" ++ pl.pp ++ dl.pp ++ entry.pp ++ ucab.pp ++ ex.pp;
-- d.ast_Dec = behavior (n, pl.ast_DecList, dl.ast_DecList, 
  --                     entry.ast_OnEntry, ucab.ast_UCABBlock, exit.ast_OnExit, 
    --                   init.isInit_c);
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

