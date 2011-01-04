grammar edu:umn:cs:melt:testing;

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

{-

function printDirs
IO ::= paths::[String] ioIn::IO
{
 return 
   if   null(paths)
   then ioIn 
   else if    headIsDir.iovalue -- && ! isSpecialFile(head(paths))
        then  print_isDir 
        else  print_isNotDir ;
 
 local attribute print_isDir :: IO ;
 print_isDir =
   printDirs( sortBy ( stringLte, 
                       prependAll ( head(paths), dirContents.iovalue ) ) ++
              tail(paths) , 
              print( "d.." ++ head(paths) ++ "\n", dirContents.io ) ) ;
 local attribute print_isNotDir :: IO ;
 print_isNotDir =
   printDirs( tail(paths) , 
              print( "..." ++ head(paths) ++ "\n", headIsDir.io ) ) ;

 local attribute headIsDir :: IOVal<Boolean> ;
 headIsDir = isDirectory( head(paths), ioIn );

 local attribute dirContents :: IOVal< [String] > ;
 dirContents = listContents ( head(paths), headIsDir.io ) ;
}


function traverseDirectoriesAndPerform -- DEPRECATED
IO ::= startDir::String paths::[String] f::Function(IO ::= String IO) ioIn::IO
{
 return 
   if   null(paths)
   then ioIn 
   else traverseDirectoriesAndPerform (
          startDir, newStrings.iovalue ++ tail(paths), f, 
          f ( head(paths), newStrings.io )  ) ;

 local newStrings :: IOVal < [String] > 
   = if   ! headIsDir.iovalue
     then ioval ( headIsDir.io, [ ] )
     else ioval ( dirContents.io,
                  sortBy ( stringLte, 
                           prependAll ( head(paths), dirContents.iovalue ) ) ) ;

 local headIsDir::IOVal<Boolean> = isDirectory( head(paths), ioIn );
 local dirContents::IOVal< [String] > = listContents ( head(paths), headIsDir.io ) ;
}
-}
