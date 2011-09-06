grammar simple:host:driver;

import silver:langutil;
import silver:langutil:pp;
import simple:concretesyntax as cst;
import simple:abstractsyntax as ast;

function driver
IO ::= args::String io_in::IO 
       the_parser::Function(ParseResult<cst:Root>::=String String)
{
  production attribute isF :: IOVal<Boolean>;
  isF = isFile(args, io_in);
   
  production attribute text :: IOVal<String>;
  text = readFile(args, isF.io);

  local attribute result :: ParseResult<cst:Root>;
  result = the_parser(text.iovalue, args);

  local attribute r_cst :: cst:Root;
  r_cst = result.parseTree;

-- local attribute write_c_io :: IO;
-- write_c_io = writeFile("output.c", r_cst.c_code, text.io );

  local attribute r_ast :: ast:Root;
  r_ast = r_cst.ast;

  local attribute print_success :: IO;
  print_success = 
    print( "Command line arguments: " ++ args ++
           "\n\n" ++
           "CST pretty print: \n" ++ r_cst.unparse ++
           "\n\n" ++ 
           "AST pretty print: \n" ++ show(100, r_ast.pp) ++
           "\n\n" ++
           "Errors: " ++
           (if null(r_ast.errors)  then " No semantic errors!\n" 
            else "\n" ++
                 implode("", ppMessages(r_ast.errors)) ++ "\n"
           )
           , text.io );

  local attribute write_success :: IO;
  write_success =
    writeFile ( "output.c", r_ast.ast:c_code, print_success );

  local attribute print_failure :: IO;
  print_failure =
    print("Encountered a parse error:\n" ++ result.parseErrors ++ "\n", text.io);

  return if   ! isF.iovalue 
         then error ("\n\nFile \"" ++ args ++ "\" not found.\n")
         else
         if   result.parseSuccess 
         then write_success 
         else print_failure;
}

