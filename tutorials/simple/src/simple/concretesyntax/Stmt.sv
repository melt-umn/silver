grammar simple:concretesyntax;

{- The use of "matched" and "unmatched" statement nonterminals (below) is to
 - remove the dangling-else ambiguity from the grammar.
 - When adding constructs such as while loops that end with
 - a statement two versions are needed, one for matched statements and
 - one for unmatched statements.  Explanation of this can be found in
 - Aho & Ullman's Principles of Compiler Design, beginning on page 174
 - in the 1977 edition.  
 -}


{--
 - A possibly empty sequence of statements
 -}
nonterminal Stmts with pp, ast<ast:Stmt>;

concrete production stmtCons
ss::Stmts ::= s::Stmt rest::Stmts
{
  ss.pp = s.pp ++ rest.pp;
  ss.ast = ast:seq(s.ast, rest.ast); 
}

concrete production stmtNone
ss::Stmts ::= 
{
  ss.pp = "";
  ss.ast = ast:skip(); 
}

{--
 - A non-empty statement. (Either semicolon or closing brace terminated)
 -}
nonterminal Stmt with pp, ast<ast:Stmt>;

concrete production unmatchedStmt  
s::Stmt ::= um::StmtUnMatched 
{
  s.pp = um.pp;
  s.ast = um.ast;
}

concrete production matchedStmt
s::Stmt ::= m::StmtMatched 
{
  s.pp = m.pp;
  s.ast = m.ast; 
}

concrete production declStmt
s::Stmt ::= d::Decl
{
  s.pp = d.pp;
  s.ast = ast:declStmt(d.ast);
}

{--
 - A matched statement.
 -}
nonterminal StmtMatched with pp, ast<ast:Stmt>;

concrete production block 
s::StmtMatched ::= '{' ss::Stmts '}' 
{
  s.pp = "{\n" ++ ss.pp ++ "}\n";
  s.ast = ast:block(ss.ast); 
}

concrete production assignment 
s::StmtMatched ::= id::term:Id '='  value::Expr ';' 
{
  s.pp = id.lexeme ++ " = " ++ value.pp ++ ";\n";
  s.ast = ast:assignment(name(id), value.ast); 
}

concrete production skip
s::StmtMatched ::= ';' 
{
  s.pp = "; \n";
  s.ast = ast:skip(); 
}

concrete production printStmt
s::StmtMatched ::= 'print' '(' e::Expr ')' ';' 
{
  s.pp = "print (" ++ e.pp ++ ");\n";
  s.ast = ast:printStmt(e.ast); 
}

concrete production whileMatched 
s::StmtMatched ::= 'while' '(' c::Expr ')' body::StmtMatched  
{
  s.pp = "while (" ++ c.pp ++ ") \n" ++ body.pp;
  s.ast = ast:while(c.ast, body.ast); 
}

{--
 - An unmatched statement
 -}
nonterminal StmtUnMatched with pp, ast<ast:Stmt>;

concrete production whileUnMatched 
s::StmtUnMatched ::= 'while' '(' c::Expr ')' body::StmtUnMatched  
{
  s.pp = "while (" ++ c.pp ++ ") \n" ++ body.pp;
  s.ast = ast:while(c.ast, body.ast); 
}

concrete production ifthen 
s::StmtUnMatched ::= 'if' '(' c::Expr ')' t::Stmt 
{
  s.pp = "if (" ++ c.pp ++ ") \n" ++ t.pp;
  s.ast = ast:ifthen(c.ast, t.ast); 
}

concrete production ifthenUnMatched 
s::StmtUnMatched ::= 'if' '(' c::Expr ')' t::StmtMatched 
                     'else' e::StmtUnMatched 
{
  s.pp = "if (" ++ c.pp ++ ") \n" ++ t.pp ++ "else\n" ++ e.pp;
  s.ast = ast:ifelse(c.ast, t.ast, e.ast); 
}

concrete production ifthenMatched  
s::StmtMatched ::= 'if' '(' c::Expr ')' t::StmtMatched 
                   'else' e::StmtMatched 
{
  s.pp = "if (" ++ c.pp ++ ") \n" ++ t.pp ++ "else\n" ++ e.pp;
  s.ast = ast:ifelse(c.ast, t.ast, e.ast); 
}


