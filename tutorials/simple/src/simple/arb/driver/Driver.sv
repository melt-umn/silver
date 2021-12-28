grammar simple:arb:driver;

import silver:langutil;
import silver:langutil:pp as pp;
import simple:abstractsyntax as ast;
import silver:reflect;

function arbDriver
IO ::= args::[String]
       io_in::IO 
       generator::(RandomGen<ast:Root> ::= Integer)
{
  local depth::Integer = toInteger(head(args));
  local r_ast :: ast:Root = runRandomGen(generator(depth));

  local print_success :: IO = 
    print( "AST: \n" ++ pp:show(100, reflect(new(r_ast))) ++
           "\n\n" ++
           "AST pretty print: \n" ++ pp:show(100, r_ast) ++
           "\n\n" ++
           "Errors: " ++
           (if null(r_ast.errors) then " No semantic errors!\n" 
            else "\n" ++
                 messagesToString(r_ast.errors) ++ "\n"
           )
           , io_in );

  local write_success :: IO =
    writeFile("output.c", r_ast.ast:c_code, print_success);

  return if null(args) || length(head(args)) == 0 || !isDigit(head(args))
         then error("Expected a depth to generate\n")
         else write_success;
}

