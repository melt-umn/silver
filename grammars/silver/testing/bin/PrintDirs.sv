grammar silver:testing:bin;

function printDirs
IOToken ::= paths::[String] ioIn::IOToken
{
 return traverseDirectoriesAndPerform
           ( "", paths, printNL, doNotSkip, ioval(ioIn,0) ).io ;
}

function printNL
IOVal<a> ::= absoluteFilePath::String ioIn::IOVal<a>
{ return ioval( printT (absoluteFilePath ++ "\n", ioIn.io), ioIn.iovalue ) ;
}

function doNotSkip
IOVal<Boolean> ::= absoluteFilePath::String ioIn::IOToken
{
 return ioval(ioIn,false) ;
}

