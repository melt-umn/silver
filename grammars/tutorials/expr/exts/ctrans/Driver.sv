grammar edu:umn:cs:melt:tutorial:expr:exts:ctrans ;

import edu:umn:cs:melt:tutorial:expr:host ;
import core ;
import edu:umn:cs:melt:tutorial:expr:driver ;

aspect production driver
top::Main ::= args::String  
              ext_parser::Production(Root_c::=String)
              host_parser::Production(Root_c::=String)
{

 local attribute r_lifted :: Root ;
 r_lifted = r_ast.lifted_Root ;

 more_tasks <- 
  [ print_String("Lifted AST pretty print: \n" ++ 
                 "======================== \n" ++
                 r_lifted.pp ++ "\n\n") ,

    write_String(basefilename(args) ++ ".c" , r_lifted.c_trans )

  ] ;

}
