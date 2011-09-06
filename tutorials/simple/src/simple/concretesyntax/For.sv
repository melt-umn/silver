grammar simple:concretesyntax;

concrete production forMatched
s::StmtMatched ::= 'for' '(' id::term:Id '=' lower::Expr 'to' 
                             upper::Expr ')' body::StmtMatched  
{
  s.unparse = "for (" ++ id.lexeme ++ " = " ++ lower.unparse ++ " to " ++ upper.unparse ++ ")"
           ++ "\n" ++ body.unparse;
  s.ast = ast:for(name(id), lower.ast, upper.ast, body.ast); 
}

concrete production forUnMatched
s::StmtUnMatched ::= 'for' '(' id::term:Id '=' lower::Expr 'to' 
                               upper::Expr ')' body::StmtUnMatched  
{
  s.unparse = "for (" ++ id.lexeme ++ " = " ++ lower.unparse ++ " to " ++ upper.unparse ++ ")"
           ++ "\n" ++ body.unparse;
  s.ast = ast:for(name(id), lower.ast, upper.ast, body.ast); 
}
