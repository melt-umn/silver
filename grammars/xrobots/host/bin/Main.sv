grammar xrobots:host:bin ;

import xrobots:host ;
--import tutorials:xrobots:host:driver ;
import xrobots:concretesyntax;
import xrobots:abstractsyntax;


parser parse :: Root_c {
  xrobots:concretesyntax;
  xrobots:abstractsyntax;			
  xrobots:terminals;
}

{--
 - main :: Function( IO ::= String IO ) is the entry point for silver programs.
 -
 - Note that 'IO' is something that should be considered 'the state of the 
 - world' and each value used only once.
 -}
function main 
IOVal<Integer> ::= largs::[String] ioin::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  local attribute contents:: IOVal<String> ;
  contents = readFile(head(largs), ioin);

  local attribute result :: ParseResult<Root_c>;
  result = parse(contents.iovalue, head(largs));

  local attribute r_cst :: Root_c ;
  r_cst = result.parseTree ;

  local attribute r_ast :: Root ;
  r_ast = r_cst.ast_Root ;

  local attribute print_success :: IO;
  print_success =  {---
    print( "Command line expression: " ++ args ++
           "\n\n" ++
            "CST pretty print:\n " ++ r_cst.pp ++
           "\n\n" ++
           "AST pretty print:\n " ++ r_ast.pp ++
           "\n\n"  ++
	   "Haskell code: \n" ++ r_ast.htrans ++
           "\n\n" -- ++
	   -- ++
           --"AST better pretty print: " ++ r_ast.bpp ++
           --"\n\n" ++
           --"Value: " ++ toString(r_ast.value) ++
           --"\n\n" 
           , ioin )  ++ ---}
       writeFile(
        "/project/melt3/People/sst/steve/code/haskell_v2/generated/Program.hs", 
        r_ast.htrans, 
        ioin);

  local attribute semantic_errors :: IO;
  semantic_errors = print(print_errors(r_ast.errors), ioin);
 
  local attribute print_failure :: IO;
  print_failure =
    print("Encountered a parse error:\n" ++ result.parseErrors ++ "\n", ioin);

  return ioval(if result.parseSuccess 
	       then if length(r_ast.errors) == 0
	            then print_success
	            else semantic_errors
	       else print_failure,
               0);
}



