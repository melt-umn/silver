grammar tutorials:expr:abstractsyntax ;

import tutorials:expr:terminals ;

abstract production let_expr_typed
l::Expr ::= id::Id_t t::TypeExpr v::Expr body::Expr
{
 l.pp = "let " ++ id.lexeme ++ ":" ++ t.pp ++ " = " ++ v.pp ++
        " in " ++ body.pp ++ " end" ;
 l.value = body.value ;
 l.errors := v.errors ++ body.errors ;
 l.typerep = body.typerep ;

 body.env = [ id_binding ] ++ l.env ;
 v.env = l.env ;

 production attribute id_binding :: Binding ;
 id_binding = bind_type(id, t.typerep, v.value) ;


 -- rely on type inference in Haskell, we won't compute
 -- the haskell attribute on type expressions.
 l.haskell = "( let " ++ id.lexeme ++ " = " ++ v.haskell ++
              " in "  ++ body.haskell ++ " )";
}


aspect production idRef
e::Expr ::= id::Id_t
{
 e.typerep = if   ! res.found
             then intType()
             else res.binding.typerep ;
}


attribute typerep occurs on Binding ;

abstract production bind_type
b::Binding ::= id::Id_t t::TypeRep v::Integer
{
 b.ident = id ;
 b.value = v ;
 b.typerep = t ;
}

aspect production bind_value
b::Binding ::= id::Id_t v::Integer
{
 b.typerep = intType();
}

aspect production let_expr
l::Expr ::= id::Id_t v::Expr body::Expr
{
 l.errors <- case v.typerep of
               intType() -> [ ::String ]
             | _ -> [ mk_error(id.line, id.column,
                               "Values in declarations of untyped lets must be of type integer.") ] 
             end ; 
}
