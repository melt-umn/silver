grammar lib:langproc:errors ;

nonterminal Error ;
synthesized attribute msg::String occurs on Error ;

abstract production mkError
e::Error ::= m::String 
{ e.msg = m ;
}

function showErrors
String ::= errs::[Error]
{
 return if null(errs)
        then ""
        else head(errs).msg ++ "\n" ++ showErrors(tail(errs)) ;
}
