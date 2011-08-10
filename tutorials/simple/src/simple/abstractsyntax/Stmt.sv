grammar simple:abstractsyntax;

nonterminal Stmt with pp, env, defs, errors;

abstract production declStmt
s::Stmt ::= d::Decl 
{
  s.pp = d.pp;
  s.defs = d.defs;
  s.errors := d.errors;
}

abstract production block
s::Stmt ::= body::Stmt 
{
  s.pp = "{\n" ++ body.pp ++ "}\n";
  s.defs = emptyEnv();
  s.errors := body.errors;
}

abstract production seq
s::Stmt ::= s1::Stmt s2::Stmt 
{
  s.pp = s1.pp ++ s2.pp;
  s.defs = appendEnv (s1.defs, s2.defs);
  s.errors := s1.errors ++ s2.errors;

  s2.env = appendEnv(s1.defs, s.env);
}

abstract production printStmt
s::Stmt ::= e::Expr 
{
  s.pp = "print (" ++ e.pp ++ ");\n";
  s.defs = emptyEnv();
  s.errors := e.errors;
}

abstract production skip
s::Stmt ::= 
{
  s.pp = "; \n";
  s.defs = emptyEnv();
  s.errors := [ ];
}

abstract production while
s::Stmt ::= c::Expr b::Stmt 
{
  s.pp = "while ( " ++ c.pp ++ " )\n" ++ b.pp;
  s.defs = emptyEnv();
  s.errors := c.errors ++ b.errors;
}

abstract production ifthen
s::Stmt ::= c::Expr t::Stmt 
{
  s.pp = "if ( " ++ c.pp ++ " )\n" ++ t.pp;
  s.defs = emptyEnv();
  s.errors := c.errors ++ t.errors;
}

abstract production ifelse
s::Stmt ::= c::Expr t::Stmt e::Stmt 
{
  s.pp = "if ( " ++ c.pp ++ " )\n" ++ t.pp ++ "else \n" ++ e.pp;
  s.defs = emptyEnv();
  s.errors := c.errors ++ t.errors ++ e.errors;
}

abstract production assignment
s::Stmt ::= id::Name e::Expr 
{
  s.pp = id.pp ++ " = " ++ e.pp ++ "; \n";
  s.defs = emptyEnv();
  s.errors := case id.lookup of
                just(_)   -> []
              | nothing() -> [err(id.location, "variable \"" ++ id.pp ++ "\" was not declared.")] 
              end;
  s.errors <- e.errors;
}

