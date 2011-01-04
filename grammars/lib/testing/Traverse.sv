grammar lib:testing ;

function traverseDirectoriesAndPerform
IOVal<a> ::= startDir::String paths::[String] 
                f::Function(IOVal<a> ::= String IOVal<a>) 
                skipDir::Function(IOVal<Boolean> ::= String IO)
                ioIn::IOVal<a>
{
 return
   if   null(paths)
   then ioIn 
   else traverseDirectoriesAndPerform (
          startDir, newStrings.iovalue ++ tail(paths), f, skipDir, 
          f ( head(paths), ioval(newStrings.io, ioIn.iovalue) )  ) ;

 local newStrings :: IOVal < [String] > 
   = if   ! headIsDir.iovalue || skipIt.iovalue
     then ioval ( headIsDir.io, [ ] )
     else ioval ( dirContents.io,   -- add sorted list of dir contents to list
                  sortBy ( stringLte, 
                           prependAll ( head(paths), dirContents.iovalue ) ) ) ;

 local headIsDir::IOVal<Boolean> = isDirectory( head(paths), ioIn.io );
 local skipIt::IOVal<Boolean> = if headIsDir.iovalue 
                                then skipDir(head(paths), headIsDir.io)
                                else ioval(headIsDir.io, false) ;

 local dirContents::IOVal< [String] > = listContents ( head(paths), 
                                                       headIsDir.io ) ;
}

function prependAll
[String] ::= pre::String all::[String]
{
 return if null(all) then [ ]
        else [ pre ++ "/" ++ head(all) ] ++ prependAll(pre, tail(all)) ;
}

----------------------------------------------------------------------
----------------------------------------------------------------------

-- This isn't needed as dirContents does not report "." or ".." as
-- files in the directory.
function isSpecialFile
Boolean ::= name::String
{
 return endsWith("..",name) || endsWith(".",name) ;
}
