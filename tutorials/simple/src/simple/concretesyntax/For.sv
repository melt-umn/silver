grammar simple:concretesyntax;

concrete production forMatched
s::StmtMatched ::= 'for' '(' id::term:Id '=' lower::Expr 'to' 
                             upper::Expr ')' body::StmtMatched  
{
  s.pp = "for (" ++ id.lexeme ++ " = " ++ lower.pp ++ " to " ++ upper.pp ++ ")"
           ++ "\n" ++ body.pp;
  s.ast = ast:for(name(id), lower.ast, upper.ast, body.ast); 
}

concrete production forUnMatched
s::StmtUnMatched ::= 'for' '(' id::term:Id '=' lower::Expr 'to' 
                               upper::Expr ')' body::StmtUnMatched  
{
  s.pp = "for (" ++ id.lexeme ++ " = " ++ lower.pp ++ " to " ++ upper.pp ++ ")"
           ++ "\n" ++ body.pp;
  s.ast = ast:for(name(id), lower.ast, upper.ast, body.ast); 
}
