grammar simple:arb:driver;

import silver:langutil;
import silver:langutil:pp as pp;
import simple:abstractsyntax as ast;
import simple:concretesyntax as cst;
import silver:reflect;
import silver:util:random;

function arbDriver
IOToken ::= args::[String]
       io_in::IOToken 
       generator::(RandomGen<cst:Root> ::= Integer Integer)
{
  local depth :: Integer = toInteger(head(args));
  
  local r_cst :: IOVal<cst:Root> = runRandomGenT(generator(3, depth), io_in);

  --local r_ast :: IOVal<ast:Root> = runRandomGenT(generator(3, depth), io_in);
  local r_ast :: ast:Root = r_cst.iovalue.ast;

  local print_success :: IOToken = 
    printT( "AST: \n" ++ pp:show(100, reflect(^r_ast)) ++
           "\n\n" ++
           "AST pretty print: \n" ++ pp:show(100, r_ast) ++
           "\n\n" ++
           "Errors: " ++
           (if null(r_ast.errors) then " No semantic errors!\n" 
            else "\n" ++
                 messagesToString(r_ast.errors) ++ "\n"
           )
           , r_cst.io );

  local write_success :: IOToken =
    writeFileT("output.c", r_ast.ast:c_code, print_success);

  return if null(args) || length(head(args)) == 0 || !isDigit(head(args))
         then error("Expected a depth to generate\n")
         else write_success;
}

