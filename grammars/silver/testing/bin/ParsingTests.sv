grammar silver:testing:bin ;

import silver:testing;

---- Move thest to the testing library ----

abstract production parseOnlyTestAfterCPP
t::Test ::= fn::String parseF::Function(ParseResult<a> ::= String String)
{
 local exists::IOVal<Boolean> = isFile(fn, t.ioIn);
 local cppCommand :: String
   = -- "cpp -P " ++ fn ++ " | tail -n +3 > " ++ fn ++ ".cpp" ;
     "cpp " ++ fn ++ " > " ++ fn ++ ".cpp" ;
   -- even the -P option to cpp leaves 2 blanks lines, so we also
   -- use tail to remove these blank lines
 local mkCPPfile::IOVal<Integer> = system (cppCommand, exists.io ) ;
 local text::IOVal<String> = readFile(fn++".cpp", mkCPPfile.io);
 local pr::ParseResult<a> = parseF(text.iovalue,fn) ;

 t.pass = exists.iovalue && mkCPPfile.iovalue == 0 && pr.parseSuccess ;

 t.msg = if   ! exists.iovalue
         then "File \"" ++ fn ++ "\" not found.\n"
         else
         if   ! mkCPPfile.iovalue == 0
         then "The cpp process failed with error code " ++ 
              toString(mkCPPfile.iovalue) ++ "\n" ++
              "The cpp command was:\n" ++ cppCommand ++ "\n"
         else
         if   ! pr.parseSuccess
         then "Parser error: " ++ pr.parseErrors ++ "\n"
         else "" ;

 t.ioOut = if   ! exists.iovalue
           then exists.io 
           else text.io ;
}


abstract production parseOnlyTest
t::Test ::= fn::String parseF::Function(ParseResult<a> ::= String String)
{
 local exists::IOVal<Boolean> = isFile(fn, t.ioIn);
 local text::IOVal<String> = readFile(fn, exists.io);
 local pr::ParseResult<a> = parseF(text.iovalue,fn) ;

 local result :: Maybe<String> 
  = if   ! exists.iovalue
    then just("File \"" ++ fn ++ "\" not found.\n")
    else
    if   ! pr.parseSuccess
    then just ("Parser error: " ++ pr.parseErrors ++ "\n")
    else nothing() ;

 t.pass = exists.iovalue && pr.parseSuccess ;

 t.msg = if   ! exists.iovalue
         then "File \"" ++ fn ++ "\" not found.\n"
         else
         if   ! pr.parseSuccess
         then "Parser error: " ++ pr.parseErrors ++ "\n"
         else "" ;

 t.ioOut = if   ! exists.iovalue
           then exists.io 
           else text.io ;
}

abstract production parseFailTest
t::Test ::= fn::String parseF::Function(ParseResult<a> ::= String String)
{
 local exists::IOVal<Boolean> = isFile(fn, t.ioIn);
 local text::IOVal<String> = readFile(fn, exists.io);
 local pr::ParseResult<a> = parseF(text.iovalue,fn) ;

 t.pass = exists.iovalue && ! pr.parseSuccess ;

 t.msg = if   ! exists.iovalue
         then "File \"" ++ fn ++ "\" not found.\n"
         else
         if   pr.parseSuccess
         then "Error: File should not be parseable."
         else "" ;

 t.ioOut = if   ! exists.iovalue
           then exists.io 
           else text.io ;
}

