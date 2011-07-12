grammar xrobots:abstractsyntax;


import xrobots:terminals;

nonterminal Type with pp, htrans, rows, cols, paramTypes,
                         is_equal, check_for_equal, arrayElementType;


 
abstract production intType
tr::Type ::= 
{
 tr.pp               = "int" ;
 tr.is_equal         = case tr.check_for_equal of
                        intType() -> true 
                        | _       -> false 
                       end ;
 tr.htrans           = "XR_Int"; 
 tr.arrayElementType = errorType();
 tr.paramTypes       = [];
 tr.rows             = 0;
 tr.cols             = 0;
}

abstract production errorType
tr::Type ::= 
{
 tr.pp               = "error" ;
 tr.is_equal         = case tr.check_for_equal of
                        errorType() -> true 
                        | _         -> false 
                       end ;
 tr.htrans           = ""; 
 tr.arrayElementType = errorType();
 tr.paramTypes       = [];
 tr.rows             = 0;
 tr.cols             = 0;
}





  
abstract production floatType
tr::Type ::= 
{
 tr.pp               = "float" ;
 tr.is_equal         = case tr.check_for_equal of
                         floatType() -> true 
                       | _           -> false end ;
 tr.htrans           = "XR_Float";
 tr.paramTypes       = [];
 tr.arrayElementType = errorType();
 tr.rows             =0;
 tr.cols             =0;
}

  
abstract production boolType
tr::Type ::= 
{
 tr.pp               = "Bool" ;
 tr.is_equal         = case tr.check_for_equal of
                         boolType() -> true 
                       | _           -> false end ;
 tr.htrans           = "XRBool";
 tr.paramTypes       = [];
 tr.arrayElementType = errorType();
 tr.rows             =0;
 tr.cols             =0;
}

  
abstract production behaviorType
tr::Type ::= trList::[Type]
{
 tr.pp               = "Behavior(" ++ print_trList(trList) ++ ")" ;
 tr.is_equal         = case tr.check_for_equal of
                         behaviorType(tl) ->
                                        if equal_typerepList(tl, trList)
					then true
					else false
                       | _                    -> false end ;
 tr.htrans           = "XR_Behavior [" ++ hs_trList(trList) ++ "]";
 tr.paramTypes       = [];
 tr.arrayElementType = errorType();
 tr.rows             =0;
 tr.cols             =0;
}
  
abstract production arrayType
tr::Type ::= oftype::Type
{
 tr.pp               = "Array(" ++ oftype.pp ++ ")" ;
 tr.is_equal         = case tr.check_for_equal of
                          arrayType(ot)->
                                        if equal_types(oftype, ot)
					then true
					else false
                       | _             -> false
                       end ;
 tr.htrans           = "XRArray_Type" ++ oftype.htrans;
 tr.paramTypes       = [];
 tr.arrayElementType = oftype;
 tr.rows             =0;
 tr.cols             =0;
}

    
abstract production refType
tr::Type ::= oftype::Type
{
 tr.pp               = "Ref(" ++ oftype.pp ++ ")" ;
 tr.is_equal         = case tr.check_for_equal of
                          refType(ot)->
                                        if equal_types(oftype, ot)
					then true
					else false
                       | _             -> false
                       end ;
 tr.htrans           = "XR_RefType " ++ oftype.htrans;
 tr.paramTypes       = [];
 tr.arrayElementType = errorType();
 tr.rows             =0;
 tr.cols             =0;
}

function equal_typerepList --return a list of errors
Boolean ::= tl1::[Type] tl2::[Type]
{
	return if null(tl1) && null(tl2)
		then true	
		else if null(tl1)
		     then false
		     else if null(tl2)
			  then false
			  else if equal_types(head(tl1), head(tl2))
			       then equal_typerepList(tail(tl1), tail(tl2))
					else false;
}


function print_trList
String ::= tl::[Type]
{
	return if null(tl)
		then ""
		else  if null(tail(tl)) 
			then head(tl).pp
			else head(tl).pp ++ ", " ++ print_trList(tail(tl));
}

  
function hs_trList
String ::= tl::[Type]
{
	return if null(tl)
		then ""
		else  if null(tail(tl)) 
			then head(tl).htrans
			else head(tl).htrans ++ ", " ++ hs_trList(tail(tl));
}

function equal_types --return error if not equal
Boolean ::= t1::Type t2::Type
{
 return t1.is_equal ;
 t1.check_for_equal = t2 ;
}