grammar silver:testing:bin;

function printDirs
IO ::= paths::[String] ioIn::IO
{
 return traverseDirectoriesAndPerform
           ( "", paths, printNL, doNotSkip, ioval(ioIn,0) ).io ;
}

function printNL
IOVal<a> ::= absoluteFilePath::String ioIn::IOVal<a>
{ return ioval( print (absoluteFilePath ++ "\n", ioIn.io), ioIn.iovalue ) ; 
}

function doNotSkip
IOVal<Boolean> ::= absoluteFilePath::String ioIn::IO
{
 return ioval(ioIn,false) ;
}

