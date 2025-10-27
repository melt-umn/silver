grammar silver:testing:bin ;

import silver:testing;

function main
IO<Integer> ::= args::[String]
{ 
  local attribute initDirs :: [ String ] ;
  initDirs = map(cleanDirName, args) ;

  return do {
    startDir <- cwd();
    runTests :: TestingResults <- traverseDirectoriesAndPerform
                    ( startDir, initDirs, runTest, dirSkip, pure(testingResults(0)) );
    print("============================================================\n" ++
           (if   runTests.numFailed == 0
            then "All tests passed. \n"
            else toString(runTests.numFailed) ++ 
                 " tests failed. \n") ++
           "============================================================\n");
    return runTests.numFailed;
  };
}

function cleanDirName
String ::= dirName::String
{ return  if   endsWith("/",dirName)
          then substring(0, length(dirName)-1, dirName) 
          else 
          if   endsWith(" ",dirName)
          then error ("The current implementation of the test harness \n" ++
                      "that the arguments (\"args\") are separated by a \n" ++
                      "single space and thus no leading or trailing \n" ++
                      "spaces exist in the exploded list of arguments that \n" ++
                      "represent directory names.  But something when wrong \n" ++
                      "somewhere as a directory name now ends with a sapce. \n"
                     )
          else dirName ;
}