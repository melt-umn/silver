grammar silver:testing:bin ;

import silver:testing;

function main
IOVal<Integer> ::= args::[String] ioIn::IO
{ return
   -- if true then printDirs(initDirs, ioIn) else
   -- uncomment above line to just experiment with the traverse function
   -- when used for printing directories.
   ioval(
    print( "============================================================\n" ++
           (if   runTests.iovalue.numFailed == 0
            then "All tests passed. \n"
            else toString(runTests.iovalue.numFailed) ++ 
                 " tests failed. \n") ++
           "============================================================\n"
         , runTests.io ) 
       , runTests.iovalue.numFailed  )
  ;

 local runTests :: IOVal<TestingResults> 
  = traverseDirectoriesAndPerform
      ( startDir.iovalue, initDirs, runTest, dirSkip,
        ioval(startDir.io, testingResults(0) ) );

 local startDir :: IOVal<String> = cwd(ioIn) ;

 local attribute initDirs :: [ String ] ;
 initDirs = map(cleanDirName, args) ; -- was  explode(" ",args)) ;
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
