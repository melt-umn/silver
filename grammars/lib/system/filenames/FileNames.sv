grammar lib:system:filenames ;

-- Functions for manipulating file names
------------------------------------------------------------

-- retrun the base of the filename by removing any extension that
-- appears after the last dot (".") in the name.
function baseFileName
String ::= path::String
{
 return if   dotIndex == -1
        then path
        else reverseString(substring(dotIndex+1,length(path),pr)) ;

 local attribute dotIndex::Integer ;
 dotIndex = indexOf(".", pr) ;

 local attribute pr::String ;
 pr = reverseString(path) ;
}

-- return the extension of a filename
function fileNameExt
String ::= path::String
{ return if   dotIndex == -1
         then ""
         else reverseString(substring(0,dotIndex,pr)) ;
   local attribute dotIndex::Integer ;
   dotIndex = indexOf(".", pr) ;
   local attribute pr::String ;
   pr = reverseString(path) ;
}


-- support functions for reversing a string
function reverseString
String ::= s::String
{ return implode(reverseStringList(explode(s))) ;
}

function reverseStringList
[String] ::= ss::[String]
{
 return if   null(ss)
        then []
        else reverseStringList (tail(ss)) ++ [ head(ss) ] ;
}

-- convert a string into a list of sinlge character strings
function explode
[String] ::= s::String
{ return if   length(s) == 0
         then []
         else [ substring (0,1,s) ] ++
              explode ( substring(1,length(s),s) ) ;
}

-- concat a list of strings into a single string
function implode
String ::= ss::[String]
{ return if   null(ss)
         then ""
         else head(ss) ++ implode(tail(ss)) ;
}
