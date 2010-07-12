grammar lib:errors ;

synthesized attribute location :: Location ;

nonterminal Location with lin, col, loc_filename, str_location ;

synthesized attribute lin :: Integer ;
synthesized attribute col :: Integer ;
synthesized attribute loc_filename :: String ;
synthesized attribute str_location :: String ;

abstract production mkLocation
loc::Location ::=  l::Integer c::Integer 
{
 loc.lin = l ;
 loc.col = c ;
 loc.loc_filename = "<unknown>" ;
 loc.str_location = "line: " ++ toString(l) ++ " column: " ++ toString(c) ++ 
                    " file: " ++ loc.loc_filename ++ "." ;
}


abstract production mkLocationFile
loc::Location ::=  l::Integer c::Integer fn::String
{
 loc.lin = l ;
 loc.col = c ;
 loc.loc_filename = fn ;
 loc.str_location = "line: " ++ toString(l) ++ " column: " ++ toString(c) ++ 
                    " file: " ++ loc.loc_filename ++ "." ;
}


{-

Should location be defined on all terminals ?

In ableC we have the following function for the Id_t terminal.

function location_Id
Location ::= id::Identifier_t
{ return mkLocation2 (id.line,id.column,id.filename) ; }

-}
