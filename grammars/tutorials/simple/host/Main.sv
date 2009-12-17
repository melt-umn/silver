grammar tutorials:simple:host ;

import tutorials:simple:terminals ;
import tutorials:simple:concretesyntax ;
import tutorials:simple:abstractsyntax ;

parser parse :: Root_c { tutorials:simple:concretesyntax; tutorials:simple:terminals;} 

function main
IO ::= args::String io_in::IO
{
 production attribute isF :: IOBoolean;
 isF = isFile(args, io_in);
  
 production attribute text :: IOString;
 text = readFile(args, isF.io);

 local attribute r_cst::Root_c ;
 r_cst = parse(text.sValue) ;

 local attribute write_c_io :: IO ;
 write_c_io = writeFile("output.c", r_cst.c_code, text.io ) ;

 return
   if   ! isF.bValue 
   then error ("\n\nFile \"" ++ args ++ "\" not found.\n")
   else print ( "Command line arguments (should be a file name): " ++
                args ++ "\n\n" ++
                "Pretty print (pp) on the concrete sysntax tree: \n" ++
                r_cst.pp ++ "\n\n" ,
                write_c_io ) ; 
}
