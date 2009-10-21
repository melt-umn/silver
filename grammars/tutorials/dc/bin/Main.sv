grammar tutorials:dc:bin ;

import tutorials:dc ;
--syntax edu:umn:cs:melt:tutorial:dc ;

import core ;

parser parse :: Root_c { tutorials:dc; } 

function main
IO ::= args::String top::IO
{
  local attribute r_cst :: Root_c ;
  r_cst = parse(args) ;

  local attribute r_ast :: Root ;
  r_ast = r_cst.ast_Root ;

  return 
    print( "Command line expression: " ++ args ++
           "\n\n" ++
           "CST pretty print: " ++ r_cst.pp ++
           "\n\n" ++ 
           "AST pretty print: " ++ r_ast.pp ++
           "\n\n" ++
           "Value: " ++ toString(r_ast.value) ++
           "\n\n" 
           ,  top );
}



