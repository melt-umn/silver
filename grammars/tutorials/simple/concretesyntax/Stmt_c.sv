grammar tutorials:simple:concretesyntax ;

nonterminal Stmt_c with pp, ast_Stmt ;
nonterminal Stmts_c with pp, ast_Stmt ;
nonterminal StmtMatched_c with pp, ast_Stmt ;
nonterminal StmtUnMatched_c with pp, ast_Stmt ;

{- The use of "matched" and "unmatched" statement nonterminals is to
   remove the dangling-else ambiguity from the perhaps expected
   grammar.  When adding constructs such as while lools that end with
   a statement two versions are needed, one for matched statements and
   one for unmatched statements.  Explanation of this can be found in
   Aho & Ullmans Principles of Compiler Design, beginning on page 174
   in the 1977 edition.  
-}

synthesized attribute ast_Stmt :: Stmt ;

concrete production unmatchedStmt_c  
s::Stmt_c ::= um::StmtUnMatched_c 
{ s.pp = um.pp ;
  s.ast_Stmt = um.ast_Stmt ; }

concrete production matchedStmt_c
s::Stmt_c ::= m::StmtMatched_c 
{ s.pp = m.pp ;
  s.ast_Stmt = m.ast_Stmt ; }

concrete production block_c 
s::StmtMatched_c ::= '{' ss::Stmts_c '}' 
{ s.pp = "{\n" ++ ss.pp ++ "}\n" ;
  s.ast_Stmt = ss.ast_Stmt ; }

concrete production assignment_c 
s::StmtMatched_c ::= id::Id_t '='  value::Expr_c ';' 
{ s.pp = id.lexeme ++ " = " ++ value.pp ++ " ; \n" ;
  s.ast_Stmt = assignment (id, value.ast_Expr) ; }

concrete production skip_c
s::StmtMatched_c ::= ';' 
{ s.pp = "; \n" ;
  s.ast_Stmt = skip() ; }

concrete production print_c
s::StmtMatched_c ::= 'print' '(' e::Expr_c ')' ';' 
{ s.pp = "print (" ++ e.pp ++ ") ; \n" ;
  s.ast_Stmt = printStmt ( e.ast_Expr ) ; }

concrete production whileMatched_c 
s::StmtMatched_c ::= 'while' '(' c::Expr_c ')' body::StmtMatched_c  
{ s.pp = "while (" ++ c.pp ++ ") \n" ++ body.pp ;
  s.ast_Stmt = while (c.ast_Expr, body.ast_Stmt) ; }

concrete production whileUnMatched_c 
s::StmtUnMatched_c ::= 'while' '(' c::Expr_c ')' body::StmtUnMatched_c  
{ s.pp = "while (" ++ c.pp ++ ") \n" ++ body.pp ;
  s.ast_Stmt = while (c.ast_Expr, body.ast_Stmt) ; }

concrete production ifthen_c 
s::StmtUnMatched_c ::= 'if' '(' c::Expr_c ')' t::Stmt_c 
{ s.pp = "if (" ++ c.pp ++ ") \n" ++ t.pp ;
  s.ast_Stmt = ifthen (c.ast_Expr, t.ast_Stmt) ; }

concrete production ifthenUnMatched_c 
s::StmtUnMatched_c ::= 'if' '(' c::Expr_c ')' t::StmtMatched_c 
                       'else' e::StmtUnMatched_c 
{ s.pp = "if (" ++ c.pp ++ ") \n" ++ t.pp ++ "else\n" ++ e.pp ;
  s.ast_Stmt = ifelse (c.ast_Expr, t.ast_Stmt, e.ast_Stmt) ; }

concrete production ifthenMatched_c  
s::StmtMatched_c ::= 'if' '(' c::Expr_c ')' t::StmtMatched_c 
                     'else' e::StmtMatched_c 
{ s.pp = "if (" ++ c.pp ++ ") \n" ++ t.pp ++ "else\n" ++ e.pp ;
  s.ast_Stmt = ifelse (c.ast_Expr, t.ast_Stmt, e.ast_Stmt) ; }

concrete production stmtNone_c
ss::Stmts_c ::= 
{ ss.pp = "" ;
  ss.ast_Stmt = skip() ; }

concrete production stmtCons_c
ss::Stmts_c ::= s::Stmt_c rest::Stmts_c
{ ss.pp = s.pp ++ rest.pp ;
  ss.ast_Stmt = seq (s.ast_Stmt, rest.ast_Stmt) ; }

concrete production declStmt_c s::Stmt_c ::= d::Decl_c
{ s.pp = d.pp ;
  s.ast_Stmt = declStmt(d.ast_Decl) ;
}
