grammar xrobots:host:driver ;

import xrobots:terminals ;
import xrobots:concretesyntax ;
--import tutorials:xrobots:abstractsyntax ;

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

--  local attribute r_ast :: Root ;
 -- r_ast = r_cst.ast_Root ;

  local attribute print_success :: IO;
  print_success = 
    print( "Command line arguments: " ++ args ++
           "\n\n" ++
           "CST pretty print: \n" ++ r_cst.pp ++
           "\n\n" ++ 
--           "AST pretty print: \n" ++ r_ast.pp ++
  --         "\n\n" ++
          "Errors: " -- ++
      --     (if null(r_ast.errors)  then " No semantic errors!\n" 
        --    else "\n" ++
          --       implode("", r_ast.errors) 
          -- )
           , text.io );

  local attribute write_success :: IO ;
  write_success =
	writeFile ("output.c", r_cst.pp, print_success );
   -- writeFile ( "output.c", r_ast.c_code, print_success ) ;

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

