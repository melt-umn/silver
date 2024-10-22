grammar silver:testing:bin ;

{-
 run "java -jar ../....."
-}

terminal Run_t 'run' ;
terminal Fail_t 'fail' ;
terminal Skip_t 'skip' ;
terminal Colon_t ':' ;
terminal Test_t 'test' ;
terminal Suite_t 'suite' ;
terminal Jar_t   /[a-zA-Z_\-\.]+\.jar/ ;

terminal Command_t /[\"]([^\"]|[\\][\"])*[\"]/ ;
terminal CommandAlt_t /.*/ submits to {LineComment, BlockComment, WhiteSpace};  -- to follow "run:"

ignore terminal LineComment  /[\/][\/].*/ ;
ignore terminal BlockComment 
                /[\/][\*]([^\*]|[\r\n]|([\*]+([^\*\/]|[\r\n])))*[\*]+[\/]/ ;
ignore terminal WhiteSpace   /[\r\n\t\ ]+/ ;

synthesized attribute ioResult :: IOVal<Integer> ;
inherited attribute ioInput :: IOVal<Integer> ;
inherited attribute testFileName :: String ;
inherited attribute testFileDir :: String ;

nonterminal Run with testFileName, testFileDir, ioInput, ioResult ;

parser parse::Run { silver:testing:bin ; }


concrete production skipRun
r::Run ::= 'skip' skiprun::Run
{ 
 r.ioResult = ioval (r.ioInput.io, 0) ;
}

nonterminal OptionalFail ;
synthesized attribute fail::Boolean occurs on OptionalFail ;
concrete production noFail
f::OptionalFail ::= 
{ f.fail = false ; }
concrete production doFail
f::OptionalFail ::= 'fail'
{ f.fail = true ; }

concrete production run_alternate
r::Run ::= f::OptionalFail run_kwd::'run' ':' rest::CommandAlt_t
{
 forwards to 
   run(@f, run_kwd, terminal(Command_t, "\"" ++ rest.lexeme ++ "\"") ) ;
}

concrete production run
r::Run ::= f::OptionalFail 'run' c::Command_t
{
 local msgBefore :: IOToken  =
  printT ("............................................................\n" ++
         "Test \n  " ++ r.testFileName ++ " in directory \n  " ++
         prettyDirName(r.testFileDir) ++ "\n", r.ioInput.io ) ;

 local cmd :: String = substring(1,length(c.lexeme)-1,c.lexeme) ;

 local cmdResult :: IOVal<Integer> 
   = systemT ("cd " ++ r.testFileDir ++ ";" ++
             "rm -f " ++ r.testFileName ++ ".output ; " ++ 
             cmd ++ " >& " ++ r.testFileName ++ ".output"
             , msgBefore ) ;

 r.ioResult =
   if   (cmdResult.iovalue == 0 && ! f.fail) ||
        (cmdResult.iovalue != 0 && f.fail) 
   then ioval( printT( "passed (rc = 0).\n", cmdResult.io), 0 )
   else ioval( printT( "failed (rc = " ++ toString(cmdResult.iovalue) ++ ").\n",
                      cmdResult.io),
               1 ) ;
}

concrete production runTestSuite
ts::Run ::= 'test' 'suite' jar::Jar_t
{
 local msgBefore :: IOToken  =
  printT ("............................................................\n" ++
         "Test Suite jar \"" ++ jar.lexeme ++ "\" in \n  " ++ 
         ts.testFileName ++ " in directory \n  " ++
         prettyDirName(ts.testFileDir) ++ "\n", ts.ioInput.io ) ;

 -- probably should check that jar file by this name exists

 local testSuiteResults :: IOVal<Integer> 
   = systemT ("cd " ++ ts.testFileDir ++ ";" ++
             "rm -f " ++ ts.testFileName ++ ".output ; " ++ 
             " java -Xss30M -jar " ++ jar.lexeme ++
             " >& " ++ ts.testFileName ++ ".output" ,
             msgBefore ) ;

 local afterMsg :: IOToken
   = printT ( if testSuiteResults.iovalue == 0
             then "all tests passed (rc = 0).\n"
             else toString(testSuiteResults.iovalue) ++ 
             if testSuiteResults.iovalue == 1
             then " test in suite failed.\n" 
             else " tests in suite failed.\n" ,
             testSuiteResults.io ) ;

 ts.ioResult = ioval( afterMsg, testSuiteResults.iovalue ) ;
}





{-
function runCommandOnFile
IOToken ::= absoluteFilePath::String ioIn::IOToken
{
 return runCommandOnFileRC(absoluteFilePath, ioval(ioIn,0) ) .io ;
}


function runCommandOnFile
IOToken ::= absoluteFilePath::String ioIn::IOToken
{
 local isDir :: IOVal<Boolean> = isDirectoryT( absoluteFilePath, ioIn );
 local isF   :: IOVal<Boolean> = isFileT(absoluteFilePath, ioIn);
 local text  :: IOVal<String>  = readFileT(absoluteFilePath, isF.io);

 local parseResult :: ParseResult<Run> = parse(text.iovalue, absoluteFilePath);
 local r_cst :: Run = parseResult.parseTree ;
 
 local parseFailure :: IOToken =
   printT ("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" ++
          "Parsing of .test file \n   " ++ absoluteFilePath ++ "\n" ++
          "failed.\n" ++ parseResult.parseErrors ++ "\n" ++
          "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" ,
          text.io);


 r_cst.ioInput = text.io ;
 r_cst.testFileName = fileNameInFilePath(absoluteFilePath) ;
 r_cst.testFileDir = dirNameInFilePath(absoluteFilePath) ;
 
 local testResult :: IOVal<Integer> = r_cst.ioResult ;

 local attribute msgAfter :: IOToken ;
 msgAfter = 
   printT ((if   testResult.iovalue == 0
           then "passed (rc == 0)."
           else "failed (rc == " ++ toString(testResult.iovalue) ++ ").") ++
         "\n" ,
         testResult.io );

 return if   ! endsWith(".test", absoluteFilePath) || isDir.iovalue
        then ioIn
        else 
        if   ! isF.iovalue 
        then error ("\n\nFile \"" ++ absoluteFilePath ++ "\" not found.\n")
        else
        if   ! parseResult.parseSuccess 
        then parseFailure
        else msgAfter ;
}
-}
