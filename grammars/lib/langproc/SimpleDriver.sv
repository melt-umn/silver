grammar lib:langproc ;

function simpleDriver
IOVal<Integer> ::= filename::String
 the_parser::(ParseResult<a> ::= String String) 
 processTree::(IOVal<Integer> ::= a IO)
 driverIO::IO
{
 local attribute fileExists :: IOVal<Boolean>;
 fileExists = isFile(filename, driverIO);

 local attribute text :: IOVal<String>;
 text = readFile(filename,fileExists.io);

 local attribute result :: ParseResult<a>;
 result = the_parser(text.iovalue, filename);

 local attribute print_failure :: IO;
 print_failure =
    print("Encountered parse errors:\n" ++ result.parseErrors ++ "\n", text.io);

 return if   ! fileExists.iovalue
        then ioval (print ("File \"" ++ filename ++ "\" not found.\n\n",
                    fileExists.io ) , 1 )
        else
        if   ! result.parseSuccess 
        then ioval(print_failure, 1 )
        else processTree(result.parseTree, text.io ) ;
}



function getAndParseFile
IOVal<Maybe<a>> ::= filename::String 
                    the_parser::(ParseResult<a> ::= String String) getIO::IO
{
 local attribute fileExists :: IOVal<Boolean>;
 fileExists = isFile(filename, getIO);

 local attribute text :: IOVal<String>;
 text = readFile(filename,fileExists.io);

 local attribute result :: ParseResult<a>;
 result = the_parser(text.iovalue, filename);

 local attribute print_failure :: IO;
 print_failure =
    print("Encountered parse errors:\n" ++ result.parseErrors ++ "\n", text.io);

 return if   ! fileExists.iovalue
        then ioval (print ("File \"" ++ filename ++ "\" not found.\n\n",
                    fileExists.io ) , nothing() )
        else
        if   ! result.parseSuccess 
        then ioval(print_failure, nothing() )
        else ioval(text.io, just(result.parseTree) ) ;
}
