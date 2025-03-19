grammar silver:testing:bin;

function printDirs
IO<Unit> ::= paths::[String]
{
 return traverseDirectoriesAndPerform
           ( "", paths, printNL, doNotSkip, pure(()) );
}

function printNL
IO<Unit> ::= absoluteFilePath::String dummy::IO<Unit>
{ return print(absoluteFilePath ++ "\n") ;
}

function doNotSkip
IO<Boolean> ::= absoluteFilePath::String
{
 return pure(false) ;
}

