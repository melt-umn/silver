grammar simple:abstractsyntax;

nonterminal Stmt with pp, env, defs, errors;

-- Decides how best to pretty print a statement following if/while/etc
function ppblock
Document ::= s::Stmt
{
  return case s of
           block(_) -> cat(text(" "), s.pp) -- the block will do it itself.
         | _ -> nestlines(3, s.pp)
         end;
}

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
  s.pp = braces(nestlines(3, body.pp));
  s.defs = emptyEnv();
  s.errors := body.errors;
}

abstract production seq
s::Stmt ::= s1::Stmt s2::Stmt 
{
  s.pp = cat(cat(s1.pp, line()), s2.pp);
  s.defs = appendEnv (s1.defs, s2.defs);
  s.errors := s1.errors ++ s2.errors;

  s2.env = appendEnv(s1.defs, s.env);
}

abstract production printStmt
s::Stmt ::= e::Expr 
{
  s.pp = concat([text("print"), parens(e.pp), semi()]);
  s.defs = emptyEnv();
  s.errors := e.errors;
}

abstract production skip
s::Stmt ::= 
{
  s.pp = semi();
  s.defs = emptyEnv();
  s.errors := [ ];
}

abstract production while
s::Stmt ::= c::Expr b::Stmt 
{
  s.pp = concat([text("while"), parens(c.pp), ppblock(b)]);
  s.defs = emptyEnv();
  s.errors := c.errors ++ b.errors;
}

abstract production ifthen
s::Stmt ::= c::Expr t::Stmt 
{
  s.pp = concat([text("if"), parens(c.pp), ppblock(t)]);
  s.defs = emptyEnv();
  s.errors := c.errors ++ t.errors;
}

abstract production ifelse
s::Stmt ::= c::Expr t::Stmt e::Stmt 
{
  s.pp = concat([text("if"), parens(c.pp), ppblock(t),
                 text("else"), ppblock(e)]);
  s.defs = emptyEnv();
  s.errors := c.errors ++ t.errors ++ e.errors;
}

abstract production assignment
s::Stmt ::= id::Name e::Expr 
{
  s.pp = concat([id.pp, text(" = "), e.pp, semi()]);
  s.defs = emptyEnv();
  s.errors := case id.lookup of
                just(_)   -> []
              | nothing() -> [err(id.location, "variable \"" ++ id.name ++ "\" was not declared.")] 
              end;
  s.errors <- e.errors;
}

