grammar simple:concretesyntax;

{- The use of "matched" and "unmatched" statement nonterminals (below) is to
 - remove the dangling-else ambiguity from the grammar.
 - When adding constructs such as while loops that end with
 - a statement two versions are needed, one for matched statements and
 - one for unmatched statements.  Explanation of this can be found in
 - Aho & Ullman's Principles of Compiler Design, beginning on page 174
 - in the 1977 edition.  
 -}

-- A possibly empty sequence of statements
closed nonterminal Stmts with unparse, location, ast<ast:Stmt>;

concrete productions ss::Stmts
 | s::Stmt rest::Stmts  { ss.unparse = s.unparse ++ rest.unparse;
                          ss.ast = case rest of
                            stmtNone() -> s.ast
                          | _ -> ast:seq(s.ast, rest.ast)
                          end; }
 (stmtNone) |           { ss.unparse = "";
                          ss.ast = ast:skip(); }


-- A non-empty statement. (Either semicolon or closing brace terminated)
closed nonterminal Stmt with unparse, location, ast<ast:Stmt>;

concrete productions s::Stmt
 | um::StmtUnMatched  { s.unparse = um.unparse;
                        s.ast = um.ast; }
 | m::StmtMatched     { s.unparse = m.unparse;
                        s.ast = m.ast; }
 | d::Decl            { s.unparse = d.unparse;
                        s.ast = ast:declStmt(d.ast); }


-- A matched statement.
closed nonterminal StmtMatched with unparse, location, ast<ast:Stmt>;

concrete productions s::StmtMatched
 | '{' ss::Stmts '}'  { s.unparse = "{\n" ++ ss.unparse ++ "}\n";
                        s.ast = ast:block(ss.ast); }
 | id::Id '='  value::Expr ';'  { s.unparse = id.lexeme ++ " = " ++ value.unparse ++ ";\n";
                                  s.ast = ast:assignment(name(id), value.ast); }
 | ';'                          { s.unparse = "; \n";
                                  s.ast = ast:skip(); }
 | 'print' '(' e::Expr ')' ';'  { s.unparse = "print (" ++ e.unparse ++ ");\n";
                                  s.ast = ast:printStmt(e.ast); }
 | 'while' '(' c::Expr ')' body::StmtMatched
     { s.unparse = "while (" ++ c.unparse ++ ") \n" ++ body.unparse;
       s.ast = ast:while(c.ast, body.ast); }


-- An unmatched statement
closed nonterminal StmtUnMatched with unparse, location, ast<ast:Stmt>;

concrete productions s::StmtUnMatched 
 | 'while' '(' c::Expr ')' body::StmtUnMatched
     { s.unparse = "while (" ++ c.unparse ++ ") \n" ++ body.unparse;
       s.ast = ast:while(c.ast, body.ast); }
 | 'if' '(' c::Expr ')' t::Stmt
     { s.unparse = "if (" ++ c.unparse ++ ") \n" ++ t.unparse;
       s.ast = ast:ifthen(c.ast, t.ast); }
 | 'if' '(' c::Expr ')' t::StmtMatched 'else' e::StmtUnMatched
     { s.unparse = "if (" ++ c.unparse ++ ") \n" ++ t.unparse ++ "else\n" ++ e.unparse;
       s.ast = ast:ifelse(c.ast, t.ast, e.ast); }
 | 'if' '(' c::Expr ')' t::StmtMatched 'else' e::StmtMatched
     { s.unparse = "if (" ++ c.unparse ++ ") \n" ++ t.unparse ++ "else\n" ++ e.unparse;
       s.ast = ast:ifelse(c.ast, t.ast, e.ast); }
