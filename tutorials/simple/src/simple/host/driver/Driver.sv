grammar simple:host:driver;

import silver:langutil;
import silver:langutil:pp as pp;
import simple:concretesyntax as cst;
import simple:abstractsyntax as ast;

function driver
IO ::= args::[String]
       io_in::IO 
       the_parser::(ParseResult<cst:Root> ::= String String)
{
  local filename :: String = if null(args) then "" else head(args);
  
  local isF :: IOVal<Boolean> = isFile(filename, io_in);
   
  local text :: IOVal<String> = readFile(filename, isF.io);

  local result :: ParseResult<cst:Root> = the_parser(text.iovalue, filename);

  local r_cst :: cst:Root = result.parseTree;

  local r_ast :: ast:Root = r_cst.ast;

  local print_success :: IO = 
    print( "Command line arguments: " ++ filename ++
           "\n\n" ++
           "CST pretty print: \n" ++ r_cst.unparse ++
           "\n\n" ++ 
           "AST pretty print: \n" ++ pp:show(100, r_ast.pp) ++
           "\n\n" ++
           "Errors: " ++
           (if null(r_ast.errors) then " No semantic errors!\n" 
            else "\n" ++
                 messagesToString(r_ast.errors) ++ "\n"
           )
           , text.io );

  local write_success :: IO =
    writeFile("output.c", r_ast.ast:c_code, print_success);

  local print_failure :: IO =
    print("Encountered a parse error:\n" ++ result.parseErrors ++ "\n", text.io);

  return if !isF.iovalue 
         then error("\n\nFile \"" ++ filename ++ "\" not found.\n")
         else
         if result.parseSuccess 
         then write_success 
         else print_failure;
}

