grammar simple:abstractsyntax;

nonterminal Stmt with pp, env, defs, errors;

-- Decides how best to pretty print a statement following if/while/etc
function ppblock
Document ::= s::Stmt
{
  return case s of
         | block(_) -> pp" ${s.pp}" -- the block will do it itself.
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
  s.pp = pp"{${nestlines(3, body.pp)}}";
  s.defs = [];
  s.errors := body.errors;
}

abstract production seq
s::Stmt ::= s1::Stmt s2::Stmt 
{
  s.pp = pp"${s1.pp}${line()}${s2.pp}";
  s.defs = s1.defs ++ s2.defs;
  s.errors := s1.errors ++ s2.errors;

  s2.env = addEnv(s1.defs, s.env);
}

abstract production printStmt
s::Stmt ::= e::Expr 
{
  s.pp = pp"print(${e.pp});";
  s.defs = [];
  s.errors := e.errors;
}

abstract production skip
s::Stmt ::= 
{
  s.pp = pp";";
  s.defs = [];
  s.errors := [];
}

abstract production while
s::Stmt ::= c::Expr b::Stmt 
{
  s.pp = pp"while(${c.pp})${ppblock(b)}";
  s.defs = [];
  s.errors := c.errors ++ b.errors;
}

abstract production ifthen
s::Stmt ::= c::Expr t::Stmt 
{
  s.pp = pp"if(${c.pp})${ppblock(t)}";
  s.defs = [];
  s.errors := c.errors ++ t.errors;
}

abstract production ifelse
s::Stmt ::= c::Expr t::Stmt e::Stmt 
{
  s.pp = pp"if(${c.pp})${ppblock(t)}else${ppblock(e)}";
  s.defs = [];
  s.errors := c.errors ++ t.errors ++ e.errors;
}

abstract production assignment
s::Stmt ::= id::Name e::Expr 
{
  s.pp = pp"${id.pp} = ${e.pp};";
  s.defs = [];
  s.errors := case id.lookup of
                just(_)   -> []
              | nothing() -> [err(id.location, "variable \"" ++ id.name ++ "\" was not declared.")] 
              end;
  s.errors <- e.errors;
}

