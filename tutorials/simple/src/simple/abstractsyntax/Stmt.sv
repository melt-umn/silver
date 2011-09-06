grammar simple:abstractsyntax;

nonterminal Stmt with pp, env, defs, errors;

-- Decides how best to pretty print a statement following if/while/etc
function ppblock
Document ::= s::Stmt
{
  return case s of
           block(_) -> cat(text(" "), s.pp)
         | _ -> cat(cat(nest(3, realLine()), s.pp), line())
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
  s.pp = cat(cat(cat(text("{"), group(cat(nest(3, cat(line(), body.pp)), line()))), text("}")), line());
  s.defs = emptyEnv();
  s.errors := body.errors;
}

abstract production seq
s::Stmt ::= s1::Stmt s2::Stmt 
{
  s.pp = cat(s1.pp, s2.pp);
  s.defs = appendEnv (s1.defs, s2.defs);
  s.errors := s1.errors ++ s2.errors;

  s2.env = appendEnv(s1.defs, s.env);
}

abstract production printStmt
s::Stmt ::= e::Expr 
{
  s.pp = cat(cat(cat(text("print("), e.pp), text(")")), cat(text(";"), line()));
  s.defs = emptyEnv();
  s.errors := e.errors;
}

abstract production skip
s::Stmt ::= 
{
  s.pp = cat(text(";"), line());
  s.defs = emptyEnv();
  s.errors := [ ];
}

abstract production while
s::Stmt ::= c::Expr b::Stmt 
{
  s.pp = cat(cat(cat(text("while("), c.pp), text(")")), ppblock(b));
  s.defs = emptyEnv();
  s.errors := c.errors ++ b.errors;
}

abstract production ifthen
s::Stmt ::= c::Expr t::Stmt 
{
  s.pp = cat(cat(cat(text("if("), c.pp), text(")")), ppblock(t));
  s.defs = emptyEnv();
  s.errors := c.errors ++ t.errors;
}

abstract production ifelse
s::Stmt ::= c::Expr t::Stmt e::Stmt 
{
  s.pp = cat(
             cat(cat(cat(text("if("), c.pp), text(")")), ppblock(t))
             ,
             cat(cat(realLine(), text("else")), ppblock(e)));
  s.defs = emptyEnv();
  s.errors := c.errors ++ t.errors ++ e.errors;
}

abstract production assignment
s::Stmt ::= id::Name e::Expr 
{
  s.pp = cat(cat(cat(id.pp, text(" = ")), e.pp), cat(text(";"), line()));
  s.defs = emptyEnv();
  s.errors := case id.lookup of
                just(_)   -> []
              | nothing() -> [err(id.location, "variable \"" ++ id.name ++ "\" was not declared.")] 
              end;
  s.errors <- e.errors;
}

