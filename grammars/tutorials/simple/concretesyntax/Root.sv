grammar tutorials:simple:concretesyntax ;

import tutorials:simple:terminals ;
import tutorials:simple:abstractsyntax ;

synthesized attribute pp :: String ;
synthesized attribute c_code :: String ;

nonterminal Root_c with pp, c_code ;

--synthesized attribute ast_Root :: Root occurs on Root_c ;


concrete production root_c
r::Root_c ::= ds::Decls_c 
{
 r.pp = ds.pp ;

 r.c_code = "#include<stdio.h>\n " ++
            ds.c_code ;
}
