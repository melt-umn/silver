grammar simple:concretesyntax;

-- Read Stmt.sv before this file.

-- We can introduce new production separately from the rest.
-- This is a preview of what extensions look like.

concrete productions s::StmtMatched
 | 'for' '(' id::Id '=' lower::Expr 'to' upper::Expr ')' body::StmtMatched
     { s.unparse = "for (" ++ id.lexeme ++ " = " ++ lower.unparse ++ " to " ++ upper.unparse ++ ")"
                   ++ "\n" ++ body.unparse;
       s.ast = ast:for(name(id), lower.ast, upper.ast, body.ast); }

concrete productions s::StmtUnMatched
 | 'for' '(' id::Id '=' lower::Expr 'to' upper::Expr ')' body::StmtUnMatched
     { s.unparse = "for (" ++ id.lexeme ++ " = " ++ lower.unparse ++ " to " ++ upper.unparse ++ ")"
                   ++ "\n" ++ body.unparse;
       s.ast = ast:for(name(id), lower.ast, upper.ast, body.ast); }

