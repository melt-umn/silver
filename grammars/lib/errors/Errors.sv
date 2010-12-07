grammar lib:errors ;

-- The 'errors' attribute is used to propogate error messages
-- up the AST.  
-- It is a collection attribute so that aspect productions can
-- add new error messages to existing language constructs, as 
-- needed. 
-- It type is [ Error ], where Error is a nonterminal type that
-- consists of an error message and location information.

synthesized attribute errors :: [ Error ] with ++ ;

function mkNoError
[Error] ::=
{ return [ ] ;   }

function mkError
[Error] ::= msg::String
{ return [ msgError(msg) ] ;  }

function mkErrorLoc
[Error] ::= l::Location msg::String 
{ return [ msgErrorLoc(l,msg) ] ;  }

function mkErrorLinCol
[Error] ::= l::Integer c::Integer msg::String 
{ return [ msgErrorLoc(mkLocation(l,c),msg) ] ;   }

function mkErrorIf
[Error] ::= msg::String b::Boolean
{ return if b then [ msgError(msg) ] else [ ] ;   }


nonterminal Error with message ;
synthesized attribute message :: String ;

abstract production msgError
e::Error ::= msg::String
{
 e.message = msg ;
}

abstract production msgErrorLoc
e::Error ::=  l::Location msg::String
{
 e.message = "Error: " ++ l.str_location ++ "\n" ++ format_msg(msg) ;
}


function displayErrors
String ::= errs::[ Error ]
{
 return if null(errs) 
        then ""
        else head(errs).message ++ 
             (if null(tail(errs)) then "" else "\n") ++ 
             displayErrors(tail(errs)) ;
}


function format_msg
String ::= str::String
{
 return if   length(str) == 0 
        then ""
        else
        if   isSpace(str)
        then "" 
        else 
        if   first_newline_index == -1
        then padding ++ str
        else padding ++ substring(0, first_newline_index, str)
          ++ (if   ! isSpace(rest)
              then "\n" ++ format_msg(rest)
              else "") ;

 local attribute first_newline_index :: Integer ; 
 first_newline_index = indexOf("\n", str);

 local attribute rest :: String ;
 rest = substring(first_newline_index + 1, length(str), str) ;

 local attribute padding :: String ;
 padding = "       " ;
}



{-

We sometimes forward to an "erroneous construct" if some error
condition occurs on a production that needs to forward to something.
The production below is one that should be parameterized by the
nonterminal on the lhs.

abstract production erroneous_Expr
expr::Expr ::= err_tree::Expr errs::[Error] {
 expr.pp = "/* ERRONEOUS EXPRESSION */ " ++ err_tree.pp ;
 expr.host_Expr = error ("From erroneous_Expr on " ++ expr.pp ++ " :\n" ++ displayErrors (errs)); 
 expr.errors := errs ;
 expr.typerep = errorType (errs);
}

-}
