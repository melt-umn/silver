grammar lib:langproc:errors ;

nonterminal Error ;
synthesized attribute msg::String occurs on Error ;

abstract production mkError
e::Error ::= m::String 
{ e.msg = m ;
}

abstract production mkWarning
e::Error ::= m::String
{ e.msg = m ;
}

function showErrors
String ::= errs::[Error]
{ return if null(errs)
         then ""
         else head(errs).msg ++ "\n" ++ showErrors(tail(errs)) ;
}

function getErrors
[Error] ::= errs::[Error]
{ return if null(errs)
         then [ ] 
         else case head(errs) of
                mkError(_) -> [ head(errs) ]
              | _ -> [ ] end
              ++ getErrors(tail(errs)) ;
}

function getWarnings
[Error] ::= errs::[Error]
{ return if null(errs)
         then [ ] 
         else case head(errs) of
                mkWarning(_) -> [ head(errs) ]
              | _ -> [ ] end
              ++ getWarnings(tail(errs)) ;
}
