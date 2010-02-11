grammar tutorials:expr:abstractsyntax ;

import tutorials:expr:terminals ;

autocopy attribute env :: [ Binding ] ;
attribute env occurs on Expr ;

abstract production let_expr
l::Expr ::= id::Id_t v::Expr body::Expr
{
 l.pp = "let " ++ id.lexeme ++ " = " ++ v.pp ++ " in " ++ body.pp ++ " end" ;
 l.value = body.value ;
 l.errors := v.errors ++ body.errors ;

 body.env = [ id_binding ] ++ l.env ;
 v.env = l.env ;

 production attribute id_binding :: Binding ;
 id_binding = bind_value(id, v.value) ;
}

abstract production idRef
e::Expr ::= id::Id_t
{
 e.pp = id.lexeme ;
 e.value = if   ! res.found
           then -1
           else res.binding.value ;

 e.errors := if res.found then [ ] 
             else [ mk_error (id.line, id.column, "identifier " ++ id.lexeme
                                                  ++ " not declared." ) ] ;

 production attribute res :: EnvRes ;
 res = lookup (id.lexeme, e.env) ;
}

aspect production root
r::Root ::= e::Expr
{
 e.env = [ ] ;
}


function mk_error
String ::= l::Integer c::Integer msg::String
{ 
 return "Error (line " ++ toString(l) ++ ", column " ++ toString(c) ++ "): "
        ++ msg ;
}
