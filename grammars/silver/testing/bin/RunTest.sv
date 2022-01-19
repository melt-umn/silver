grammar silver:testing:bin ;

import silver:testing;

nonterminal TestingResults with msg, numTests, numPassed, numFailed ;

abstract production testingResults
tr::TestingResults ::= nf::Integer
{ tr.numFailed = nf ; }

function runTest
IOVal<TestingResults> ::= absoluteFilePath::String ioIn::IOVal<TestingResults>
{
 local isDir :: IOVal<Boolean> = isDirectoryT( absoluteFilePath, ioIn.io );
 local isF   :: IOVal<Boolean> = isFileT(absoluteFilePath, ioIn.io);
 local skip  :: IOVal<Boolean> = isFileT(dirNameInFilePathT(absoluteFilePath) ++
                                        "/tests.skip", ioIn.io);
 local text  :: IOVal<String>  = readFileT(absoluteFilePath, isF.io);

 local parseResult :: ParseResult<Run> = parse(text.iovalue, absoluteFilePath);
 local r_cst :: Run = parseResult.parseTree ;
 
 local parseFailure :: IOVal<TestingResults> =
   ioval (
     printT ("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" ++
            "Parsing of .test file \n   " ++ absoluteFilePath ++ "\n" ++
            "failed.\n" ++ parseResult.parseErrors ++ "\n" ++
            "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" ,
            text.io) ,
     testingResults ( ioIn.iovalue.numFailed + 1 ) ) ;

 -- Inh. attrs. for the test.
 r_cst.ioInput = ioval (text.io, ioIn.iovalue.numFailed) ;
 r_cst.testFileName = fileNameInFilePathT(absoluteFilePath) ;
 r_cst.testFileDir = dirNameInFilePathT(absoluteFilePath) ;
 
 local testResult :: IOVal<TestingResults> 
   = ioval ( r_cst.ioResult.io, testingResults (r_cst.ioResult.iovalue) ) ;

 local attribute msgAfter :: IOVal<TestingResults> ;
 msgAfter = ioval ( testResult.io, 
                    testingResults (testResult.iovalue.numFailed + 
                                    ioIn.iovalue.numFailed ) ) ;

 return if   ! endsWith(".test", absoluteFilePath) || isDir.iovalue
             || skip.iovalue 
        then ioIn  -- nothing to do
        else 
        if   ! isF.iovalue
        then ioval (exitT ( 4, printT("\n\nFile \"" ++ absoluteFilePath ++
                                    "\" not found.\n",
                         isF.io ) ) , testingResults(999) ) 
        else
        if   ! parseResult.parseSuccess 
        then parseFailure
        else msgAfter ;
}


function prettyDirName
String ::= dn::String
{
 return if   length(dn) < 70
        then dn
        else substring(0,69,dn) ++ "\n   " ++
             prettyDirName(substring(69,length(dn),dn)) ;
}



function dirSkip
IOVal<Boolean> ::= absoluteFilePath::String ioIn::IOToken
{
 return isFileT ( absoluteFilePath ++ "/tests.skip", ioIn ) ;
}
