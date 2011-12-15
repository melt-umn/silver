grammar lambda ;

import lambda ;

function driver
IOVal<Integer> ::= args::[String]
                   parse::Function(ParseResult<Program_c> ::= String String)
                   driverIO::IO
{
 production filename::String = head(args) ;
 local fileExists::IOVal<Boolean> = isFile(filename, driverIO);
 local text::IOVal<String> = readFile(filename,fileExists.io);
 local result::ParseResult<Root_c> = parse(text.iovalue, filename);
 local p_cst::Root_c = result.parseTree ;
 production p_ast::Root = p_cst.ast_Program ;

 local print_failure::IO
  = print("parse failed.\n" ++ result.parseErrors ++ "\n", text.io);
 
 -- Display pp unless it is "turned on" by some aspect of the driver
 -- production contributing the value 'true' to the collection
 -- attribute displayPP.
 production attribute displayPrettyPrint :: Boolean with || ;
 displayPrettyPrint := false ;

 -- Display errors if it is "turned on" by some aspect of the driver
 -- production contributing the value 'true' to the collection
 -- attribute displayErrors.
 production attribute displayErrors :: Boolean with || ;
 displayErrors := true ;

 production attribute tasks::[Task] with ++ ;
 tasks :=
--   (if displayPrettyPrint then [ printPPTask(filename,p_ast) ] else [ ]) ++
--  (if displayErrors then [ printErrorsTask(filename,p_ast) ] else [ ]) ++
   [ printPPTask(filename, p_cst)
     , writePPTask(filename, p_ast) 
     , printErrorsTask(filename, p_ast)
     --, displayErrorLineNum(p_ast)
   ]
   ;

 local allTasks :: Task = concatTasks(tasks) ;
 allTasks.tioIn = text.io ;

 return
  if   null(args)
  then ioval (print ("No required command line arguments provided.\n", driverIO), 1)
  else
  if   ! fileExists.iovalue
  then ioval (print ("File \"" ++ filename ++ "\" not found.\n\n",
              fileExists.io ) , 1 )
  else
  if   ! result.parseSuccess 
  then ioval( print_failure, 1 )
  else ioval( allTasks.tioOut, 0 ) ;
}

nonterminal Task with tioIn, tioOut ;
inherited attribute tioIn :: IO ;
synthesized attribute tioOut :: IO ;

abstract production printPPTask
t::Task ::= filename::String p_cst::Decorated Program_c
{ t.tioOut = print("Pretty print of program in \"" ++ filename ++ "\":\n" ++
                   p_cst.pp ++ "\n\n" ++ "CST:\n" ++ p_cst.ast_Program.pp ++ "\n\n", t.tioIn) ;
}
abstract production writePPTask
t::Task ::= filename::String p_ast::Decorated Program
{ t.tioOut = writeFile(filenamePP, p_ast.pp, t.tioIn) ;
  local filenamePP::String = substring(0, length(filename)-4, filename) ++ "_pp.lambda" ;
}
abstract production printErrorsTask
t::Task ::= filename::String p_ast::Decorated Program
{ t.tioOut = print("Errors of program in \"" ++ filename ++ "\":\n" ++
                   p_ast.errors ++ 
                   "\n\n", t.tioIn )  ;
}
abstract production writeErrorsTask
t::Task ::= filename::String p_ast::Decorated Program
{ t.tioOut = writeFile(filenameErrors,  
                       p_ast.errors ++ "\n\n",
                       t.tioIn) ;
  local filenameErrors::String = substring(0, length(filename)-3, filename) ++ ".errors" ;
}
--abstract production displayErrorLineNum
--t::Task ::= p_ast::Decorated Root
--{ t.tioOut = if   null(p_ast.errors)
--             then t.tioIn
--             else print( toString(head(p_ast.errors).location.line) ++ "\n\n", t.tioIn )  ;
--}


abstract production concatTasks
t::Task ::= ts::[Task]
{ t.tioOut = if null(ts) then t.tioIn else rest.tioOut ;

  local first::Task = head(ts) ;
  first.tioIn = t.tioIn ;

  local rest::Task = concatTasks( tail(ts) ) ;
  rest.tioIn = first.tioOut ;
}



