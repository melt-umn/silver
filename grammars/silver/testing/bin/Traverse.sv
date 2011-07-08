grammar silver:testing:bin ;

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

 {- Thought links were a bug, they seem not to be.  It maybe the 
    size of the Java stack that was the problem.
 local bashValueForTrue::Integer = 0 ;
 local headIsLink_Bash::IOVal<Integer> 
   = system("[ -L " ++ head(paths) ++ " ];", ioIn.io ); 
 local headIsLink::IOVal<Boolean> 
  = d ioval(headIsLink_Bash.io, headIsLink_Bash.iovalue == bashValueForTrue) ;
 -}
 local headIsLink::IOVal<Boolean> = ioval(ioIn.io, false) ;  -- maybe add later.

 local headIsDir::IOVal<Boolean> = isDirectory( head(paths), headIsLink.io );

 local skipIt::IOVal<Boolean> 
  = if   endsWith("/generated",head(paths))
    then ioval(headIsLink.io, true) 
    else
    if   headIsLink.iovalue 
    then ioval(headIsLink.io, true) 
    else
    if   headIsDir.iovalue 
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
