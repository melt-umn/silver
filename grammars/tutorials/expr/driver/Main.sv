grammar tutorials:expr:driver ;

import tutorials:expr:abstractsyntax ;
import tutorials:expr:concretesyntax ;
import tutorials:expr:terminals ;

function driver 
IO ::= args::String exprParser::Function(Root_c ::= String) top::IO
{

 production attribute isF :: IOBoolean;
 isF = isFile(args, top);
  
 production attribute text :: IOString;
 text = readFile(args, isF.io);

 production attribute r_cst :: Root_c ;
 r_cst = exprParser(text.sValue) ;

 production attribute r_ast :: Root ;
 r_ast = r_cst.ast_Root ;

 -- Print pp attribute on concrete syntax tree (optionally)
 local attribute tasks :: [ IO_Action ] ;
 tasks = 
  [ 
    print_String("CST pretty print: \n" ++ 
                 "================= \n" ++
                 r_cst.pp ++ "\n\n")
    ,
    print_String("AST pretty print: \n" ++ 
                 "================= \n" ++
                 r_ast.pp ++ "\n\n")
    ,

    if   null(r_ast.errors)
    then skip_io_action()
    else error("\nErrors on AST: \n" ++ 
               "============== \n" ++
               concat(r_ast.errors) ++ "\n")
    ,
    print_String("Type on AST: \n" ++ 
                 "============ \n" ++
                 r_ast.typerep.pp ++ "\n\n")
    ,

    write_String(basefilename(args) ++ ".hs", r_ast.haskell )

  ] ;

 production attribute more_tasks :: [ IO_Action ] with ++ ;
 more_tasks := [ ] ;

 production attribute tasks_io :: IO_Action ;
 tasks_io = fold_io_actions(tasks ++ more_tasks);
 tasks_io.ioIn = text.io ;

 return  -- top.ioOut 
   if   ! isF.bValue 
     then error ("\n\nFile \"" ++ args ++ "\" not found.\n")
     else tasks_io.ioOut  ;
}


abstract production print_String
t::IO_Action ::= str::String
{ t.ioOut = print (str, t.ioIn);    }

abstract production print_Integer
t::IO_Action ::= i::Integer
{ t.ioOut = print (toString(i), t.ioIn);    }


abstract production write_String
t::IO_Action ::= filename::String str::String
{ t.ioOut = writeFile(filename, str, t.ioIn);    }


nonterminal IO_Action with ioIn, ioOut ;

function fold_io_actions
IO_Action ::= tasks::[IO_Action]
{
 return if null(tasks)
        then skip_io_action()
        else io_action_sequence(head(tasks), 
                                fold_io_actions(tail(tasks))) ;
}

abstract production skip_io_action
task::IO_Action ::= 
{
 task.ioOut = task.ioIn ;
}

abstract production io_action_sequence
task::IO_Action ::= t1::IO_Action t2::IO_Action
{
 task.ioOut = t2.ioOut ;
 t1.ioIn = task.ioIn ;
 t2.ioIn = t1.ioOut ;
}




function basefilename
String ::= filename::String
{
 return  base_file_name ;

 local attribute base_file_name :: String ;
 base_file_name = substring(0, dot_position, filename) ;

 -- The nonsense below is needed to handle filenames that have a dot before
 -- the suffix dot, as in "../demos/hello.xc"
 -- There is no String reverse function, or lastIndexOf function to do this
 -- properly.

 local attribute dot_position :: Integer ;
 dot_position =      if substring(l-2, l-1, filename) == "." then l-2
                else if substring(l-3, l-2, filename) == "." then l-3
                else if substring(l-4, l-3, filename) == "." then l-4
                else if substring(l-5, l-4, filename) == "." then l-5
                else if substring(l-6, l-5, filename) == "." then l-6
                else 0 ;

 local attribute l :: Integer ;
 l = length(filename);
}


function concat
String ::= strs::[String]
{
 return if null(strs)
        then ""
        else head(strs) ++ "\n" ++  concat(tail(strs)) ;
}
