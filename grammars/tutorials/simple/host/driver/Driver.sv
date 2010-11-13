grammar tutorials:simple:host:driver ;

import tutorials:simple:terminals ;
import tutorials:simple:concretesyntax ;
import tutorials:simple:abstractsyntax ;

function driver
IO ::= args::String io_in::IO 
       the_parser::Function(ParseResult<Root_c>::=String String)
{
  production attribute isF :: IOVal<Boolean>;
  isF = isFile(args, io_in);
   
  production attribute text :: IOVal<String>;
  text = readFile(args, isF.io);

  local attribute result :: ParseResult<Root_c>;
  result = the_parser(text.iovalue, args);

  local attribute r_cst :: Root_c ;
  r_cst = result.parseTree ;

-- local attribute write_c_io :: IO ;
-- write_c_io = writeFile("output.c", r_cst.c_code, text.io ) ;

  local attribute r_ast :: Root ;
  r_ast = r_cst.ast_Root ;

  local attribute print_success :: IO;
  print_success = 
    print( "Command line arguments: " ++ args ++
           "\n\n" ++
           "CST pretty print: \n" ++ r_cst.pp ++
           "\n\n" ++ 
           "AST pretty print: \n" ++ r_ast.pp ++
           "\n\n" ++
           "Errors: " ++
           (if null(r_ast.errors)  then " No semantic errors!\n" 
            else "\n" ++
                 foldr (stringConcat, "", r_ast.errors) 
           )
           , text.io );

  local attribute print_failure :: IO;
  print_failure =
    print("Encountered a parse error:\n" ++ result.parseErrors ++ "\n", text.io);

  return if   ! isF.iovalue 
         then error ("\n\nFile \"" ++ args ++ "\" not found.\n")
         else
         if   result.parseSuccess 
         then print_success 
         else print_failure;
}

