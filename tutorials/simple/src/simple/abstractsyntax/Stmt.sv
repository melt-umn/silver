grammar simple:abstractsyntax ;

nonterminal Stmt with pp, env, defs, errors ;

abstract production declStmt
s::Stmt ::= d::Decl 
{
  s.pp = d.pp ;
  d.env = s.env ;
  s.defs = d.defs ;
  s.errors := d.errors ;
}

abstract production block
s::Stmt ::= body::Stmt 
{
  s.pp = "{\n" ++ body.pp ++ "}\n" ;
  body.env = s.env ;
  s.defs = emptyEnv() ;
  s.errors := body.errors ;
}

abstract production seq
s::Stmt ::= s1::Stmt s2::Stmt 
{
  s.pp = s1.pp ++ s2.pp ;
  s1.env = s.env ; 
  s2.env = appendEnv(s1.defs, s.env) ;
  s.defs = appendEnv (s1.defs, s2.defs) ;
  s.errors := s1.errors ++ s2.errors ;
}

abstract production printStmt
s::Stmt ::= e::Expr 
{
  s.pp = "print (" ++ e.pp ++ ") ;\n" ;
  e.env = s.env ; 
  s.defs = emptyEnv() ;
  s.errors := e.errors ;
}

abstract production skip
s::Stmt ::= 
{
  s.pp = "; \n" ;
  s.defs = emptyEnv() ;
  s.errors := [ ];
}

abstract production while
s::Stmt ::= c::Expr b::Stmt 
{
  s.pp = "while ( " ++ c.pp ++ " )\n" ++ b.pp ;
  c.env = s.env ;
  b.env = s.env ;
  s.defs = emptyEnv() ;
  s.errors := c.errors ++ b.errors ;
}

abstract production ifthen
s::Stmt ::= c::Expr t::Stmt 
{
  s.pp = "if ( " ++ c.pp ++ " )\n" ++ t.pp ;
  c.env = s.env ;
  t.env = s.env ;
  s.defs = emptyEnv() ;
  s.errors := c.errors ++ t.errors ;
}

abstract production ifelse
s::Stmt ::= c::Expr t::Stmt e::Stmt 
{
  s.pp = "if ( " ++ c.pp ++ " )\n" ++ t.pp ++ "else \n" ++ e.pp ;
  c.env = s.env ;
  t.env = s.env ;
  e.env = s.env ;
  s.defs = emptyEnv() ;
  s.errors := c.errors ++ t.errors ++ e.errors ;
}

abstract production assignment
s::Stmt ::= id::Id_t e::Expr 
{
  s.pp = id.lexeme ++ " = " ++ e.pp ++ "; \n" ;
  e.env = s.env ;
  s.defs = emptyEnv() ;
  s.errors := case declTypeExpr of
                just(_)   -> [ ]
              | nothing() -> [ "Line " ++ toString(id.line) ++ ": variable \""
                               ++ id.lexeme ++ "\" not declared.\n" ] 
              end ;
  s.errors <- e.errors ;

  local attribute declTypeExpr :: Maybe<Decorated TypeExpr> ;
  declTypeExpr = lookup(id.lexeme, s.env) ;
}

