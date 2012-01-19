grammar lambda ;

imports silver:langutil only pp, ast;
imports silver:langutil:pp;

function driver
IOVal<Integer> ::= args::[String]
                   parse::Function(ParseResult<Root_c> ::= String String)
                   driverIO::IO
{
 production filename::String = head(args) ;
 local fileExists::IOVal<Boolean> = isFile(filename, driverIO);
 local text::IOVal<String> = readFile(filename,fileExists.io);
 local result::ParseResult<Root_c> = parse(text.iovalue, filename);
 local r_cst::Root_c = result.parseTree ;
 production r_ast::Root = r_cst.ast ;

 local print_failure::IO
  = print("parse failed.\n" ++ result.parseErrors ++ "\n", text.io);
 
 {- Display pp unless it is "turned on" by some aspect of the driver
  - production contributing the value 'true' to the collection
  - attribute displayPP.
  -}
 production attribute displayPrettyPrint :: Boolean with || ;
 displayPrettyPrint := false ;

 {- Display errors if it is "turned on" by some aspect of the driver
  - production contributing the value 'true' to the collection
  - attribute displayErrors.
  -}
 production attribute displayErrors :: Boolean with || ;
 displayErrors := true ;

 production attribute tasks::[Task] with ++ ;
 tasks :=
   [ printPPTask(filename, r_cst)
     , writePPTask(filename, r_ast) 
     , printErrorsTask(filename, r_ast)];

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
t::Task ::= filename::String r_cst::Decorated Root_c
{ t.tioOut = print("Pretty print of program in \"" ++ filename ++ "\":\n" ++
                   r_cst.pp.result ++ "\n\n" ++ "CST:\n" ++ r_cst.ast.pp.result ++ "\n\n", t.tioIn) ;
}
abstract production writePPTask
t::Task ::= filename::String r_ast::Decorated Root
{ t.tioOut = writeFile(filenamePP, r_ast.pp.result, t.tioIn) ;
  local filenamePP::String = substring(0, length(filename)-7, filename) ++ "_pp.lambda" ;
}
abstract production printErrorsTask
t::Task ::= filename::String r_ast::Decorated Root
{ t.tioOut = print("Errors of program in \"" ++ filename ++ "\":\n" ++
                   r_ast.errors ++ 
                   "\n\n", t.tioIn )  ;
}
abstract production writeErrorsTask
t::Task ::= filename::String r_ast::Decorated Root
{ t.tioOut = writeFile(filenameErrors,  
                       r_ast.errors ++ "\n\n",
                       t.tioIn) ;
  local filenameErrors::String = substring(0, length(filename)-3, filename) ++ ".errors" ;
}

abstract production concatTasks
t::Task ::= ts::[Task]
{ t.tioOut = if null(ts) then t.tioIn else rest.tioOut ;

  local first::Task = head(ts) ;
  first.tioIn = t.tioIn ;

  local rest::Task = concatTasks( tail(ts) ) ;
  rest.tioIn = first.tioOut ;
}



