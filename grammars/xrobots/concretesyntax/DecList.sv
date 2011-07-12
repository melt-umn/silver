grammar xrobots:concretesyntax ;

import xrobots:terminals ;
import xrobots:abstractsyntax ;

-- We want different concrete syntax for parameters to a behavior and
-- declarations inside a behavior.  The first type are separated by
-- commas, the second type are terminated by semicolons (unless they
-- are behaviors).   We use the same abstract syntax however.

nonterminal DecList_c with pp;
synthesized attribute ast_DecList :: DecList occurs on DecList_c ;

nonterminal OptionalParamDecList_c with pp, ast_DecList ;
nonterminal ParamDecList_c with pp, ast_DecList ;


concrete production declist2_c
dl::DecList_c ::=  d::Dec_c dl_rest::DecList_c  
{
 dl.pp = d.pp ++ "\n" ++ dl_rest.pp ;
 dl.ast_DecList = declist_cons(d.ast_Dec, dl_rest.ast_DecList);
}

concrete production decList_empty_c
dl::DecList_c ::= 
{
 dl.pp = "" ;
 dl.ast_DecList = declist_empty() ;
}


concrete production paramList_some_c
opl::OptionalParamDecList_c ::= '(' pl::ParamDecList_c ')'
{
 opl.pp = pl.pp ;
 opl.ast_DecList = pl.ast_DecList ;
}

concrete production paramList_none_c
opl::OptionalParamDecList_c ::= 
{
 opl.pp = "" ;
 opl.ast_DecList = declist_empty() ;
}

concrete production paramList_cons_c
pl::ParamDecList_c ::= d::PrimDec_c  ','  pl_rest::ParamDecList_c
{
 pl.pp = d.pp ++ ", " ++ pl_rest.pp ;
 pl.ast_DecList = declist_cons(d.ast_Dec, pl_rest.ast_DecList);
}

concrete production paramList_one_c
pl::ParamDecList_c ::= d::PrimDec_c
{
 pl.pp = d.pp ;
 pl.ast_DecList = declist_cons (d.ast_Dec, declist_empty()) ;
}
