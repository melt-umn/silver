grammar silver:testing:bin ;

function traverseDirectoriesAndPerform
IO<a> ::= startDir::String paths::[String] 
                f::(IO<a> ::= String IO<a>) 
                skipDir::(IO<Boolean> ::= String)
                defaultVal::IO<a> -- default value becomes f(head(path)), so need to have type IO<a>
                --ioIn::IOVal<a>
{
  return
    if null(paths)
      then ^defaultVal
      else do {

        {-
          Thought links were a bug, they seem not to be.  It maybe the 
              size of the Java stack that was the problem.
          local bashValueForTrue::Integer = 0 ;
          local headIsLink_Bash::IOVal<Integer> 
            = system("[ -L " ++ head(paths) ++ " ];", ioIn.io ); 
          local headIsLink::IOVal<Boolean> 
            = d ioval(headIsLink_Bash.io, headIsLink_Bash.iovalue == bashValueForTrue) ;
          -} 
        let headIsLink :: Boolean = false;   -- maybe add later.

        headIsDir :: Boolean <- isDirectory( head(paths) );

        dirContents :: [String] <- listContents ( head(paths) ) ;

        skipIt :: Boolean <-
              if   endsWith("/generated", head(paths))
              then pure(true)
              else
              if   headIsLink 
              then pure(true)
              else
              if   headIsDir 
              then skipDir(head(paths))
              else pure(false);

        let newStrings :: [String] =
              (if   ! headIsDir || skipIt
                then []
                else sort ( prependAll ( head(paths), dirContents ) ) );  -- add sorted list of dir contents to list

        traverseDirectoriesAndPerform (
          startDir, newStrings ++ tail(paths), f, skipDir, f ( head(paths), ^defaultVal )
        );

      };
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
