grammar simple:abstractsyntax ;

abstract production for
s::Stmt ::= i::Name lower::Expr upper::Expr body::Stmt
{
  -- provide a nicer pretty printing, but nothing else.
  s.pp = pp"for(${i.pp} = ${lower.pp} to ${upper.pp})${ppblock(body)}";

  forwards to
    {- i = lower ;
           while(i <= upper) {
              body ;
              i = i + 1 ;
           }
     -}
     seq(
       assignment(i, lower),
       while(lteOp(varRef(i), upper),
         block(
           seq(body, 
             assignment(i, add(varRef(i), intLit("1")))))));
}
