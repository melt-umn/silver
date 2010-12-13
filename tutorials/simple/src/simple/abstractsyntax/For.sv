grammar simple:abstractsyntax ;

abstract production for
s::Stmt ::= i::Id_t lower::Expr upper::Expr body::Stmt
{
  forwards to
    {- i = lower ;
           while (i <= upper) {
              body ;
              i = i + 1 ;
           }
     -}
     seq ( assignment(i, lower) ,
           while ( lte(varRef(i), upper) ,
             block(
                seq(body, 
                    assignment(i, add(varRef(i),
                                  intLit(terminal(IntegerLiteral_t, "1"))
                              )
                   )
                  )
                  )
                 )
         ) ;
}
