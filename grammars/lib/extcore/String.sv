grammar lib:extcore ;

{- We have a need for various "toString" functions.

   EVW proposes that we name functions by a common prefix indicating
   the computation followed by the name of the type of the values
   involved.

   For printing, toStringTYPE - toStringInteger, toStringTypeExpr, etc.

   We need functions for many of the built-in types. These are included below.
-}

-- toString functions
function toStringFromInteger
String ::= v::Integer
{ return toString(v) ; }

function toStringFromFloat
String ::= v::Float
{ return toString(v) ; }

function toStringFromBoolean
String ::= v::Boolean
{ return if v then "true" else "false" ; }

function toStringFromString
String ::= v::String
{ return v ; }

function toStringFromList
String ::= toStr::Function(String ::= a) xs::[a]
{ return "[" ++ toStringFromListHelper(toStr, xs) ++ "]" ; }

function toStringFromListHelper
String ::= toStr::Function(String ::= a) xs::[a]
{ return if  null(xs) 
         then ""
         else toStr(head(xs)) ++
              if null(tail(xs))
              then ""
              else ", " ++ toStringFromListHelper(toStr, tail(xs)) ;
}

function stripWhiteSpace
String ::= s::String
{ return implode ("", stripWhiteSpaceHelper(explode("",s))) ; }

function stripWhiteSpaceHelper
[String] ::= ss::[String]
{ return if   null(ss) 
         then [ ]
         else 
         if   hd==" " || hd=="\n" || hd=="\t"
         then stripWhiteSpaceHelper(tail(ss)) 
         else hd :: stripWhiteSpaceHelper(tail(ss)) ;

  local attribute hd::String ;
  hd = head(ss) ;
}

function replaceChars
String ::= toReplace::String replaceWith::String str::String
{
 return implode ("", replaceCharsHelper(toReplace, replaceWith, explode("",str)) ) ;
}

function replaceCharsHelper
[String] ::= toReplace::String replaceWith::String chars::[String]
{ return
   if   null(chars)
   then [ ]
   else
   if   head(chars) == toReplace
   then replaceWith :: replaceCharsHelper (toReplace, replaceWith, tail(chars))
   else head(chars) :: replaceCharsHelper (toReplace, replaceWith, tail(chars)) ;
}

--function stripExtraWhiteSpace
--String ::= s::String
--{ return implode ("", stripExtraWhiteSpaceHelper(explode("",s))) ; }

function stripExtraWhiteSpace 
String ::= str::String 
{ return implode ("", stripExtraWhiteSpaceHelper(
                           woLeadingOrEndingWhiteSpace)) ; 

  local attribute woLeadingOrEndingWhiteSpace :: [String] ;
  woLeadingOrEndingWhiteSpace 
    = reverse((dropWhile(isSpace,
        reverse(dropWhile(isSpace, explode("",str)))))) ;
}

function stripExtraWhiteSpaceHelper
[String] ::= ss::[String]
{ return if   null(ss) 
         then [ ]
         else 
         if   hd==" " || hd=="\n" || hd=="\t"
         then (if null(tail(ss))
               then [ ] 
               else (if   nxt==" " || nxt=="\n" || nxt=="\t"
                     then stripExtraWhiteSpaceHelper(tail(ss)) -- drop hd
                     else " " :: stripExtraWhiteSpaceHelper(tail(ss))
                          -- replace hd with " "
                    )
              )
         else hd :: stripExtraWhiteSpaceHelper(tail(ss)) ;

  local attribute hd::String ;
  hd = head(ss) ;

  local attribute nxt::String ;
  nxt = head(tail(ss)) ;
}

function isNotWhiteSpace
Boolean ::= str::String
{ return ! isSpace(str) ; }




function addLineNumbers
String ::= code::String
{ return addLineNums(1, 2, lines) ;
  local lines::[String] = explode("\n",code) ;
}

function addLineNums
String ::= next::Integer width::Integer lines::[String]
{ return if null(lines)
         then ""
         else pad ++ ln ++ ": " ++ head(lines) ++ "\n" ++
              addLineNums(next+1, width, tail(lines)) ;
  local ln::String = toString(next); 
  local pad::String = implode("", repeat(" ", width - length(ln)) ) ;
}


