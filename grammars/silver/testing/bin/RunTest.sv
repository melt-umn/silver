grammar silver:testing:bin ;

import silver:testing;

nonterminal TestingResults with msg, numTests, numPassed, numFailed ;

abstract production testingResults
tr::TestingResults ::= nf::Integer
{ tr.numFailed = nf ; }

function runTest
IO<TestingResults> ::= absoluteFilePath::String defaultVal::IO<TestingResults>
{
  return do {
    isDir :: Boolean <- isDirectory(absoluteFilePath);
    isF   :: Boolean <- isFile(absoluteFilePath);
    skip  :: Boolean <- isFile(dirNameInFilePath(absoluteFilePath) ++ "/tests.skip");

    if  ! endsWith(".test", absoluteFilePath) || isDir || skip 
        then ^defaultVal  -- nothing to do
    else 
        if  ! isF
            then do {
                print ("\n\nFile \"" ++ absoluteFilePath ++ "\" not found.\n"); 
                exit(4);
                return testingResults(999);
            }
        else 
            do {
                text  :: String  <- readFile(absoluteFilePath);

                let parseResult :: ParseResult<Run> = parse(text, absoluteFilePath);
                let r_cst :: Run = parseResult.parseTree;

                defaultTestResult :: TestingResults <- ^defaultVal;

                r_cst_result :: Integer <- decorate r_cst with {
                    testFileName = fileNameInFilePath(absoluteFilePath) ;
                    testFileDir = dirNameInFilePath(absoluteFilePath) ;
                }.ioResult;

                let testResult :: TestingResults = testingResults (r_cst_result);
                let msgAfter :: TestingResults = testingResults (testResult.numFailed + 
                                                    defaultTestResult.numFailed );

                if   ! parseResult.parseSuccess 
                then do {
                    print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" ++
                    "Parsing of .test file \n   " ++ absoluteFilePath ++ "\n" ++
                    "failed.\n" ++ parseResult.parseErrors ++ "\n" ++
                    "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    return testingResults(defaultTestResult.numFailed + 1);
                }
                else pure(msgAfter);
            };
  };
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
IO<Boolean> ::= absoluteFilePath::String
{
 return isFile ( absoluteFilePath ++ "/tests.skip" ) ;
}
