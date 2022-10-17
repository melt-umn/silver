grammar simple:abstractsyntax;

nonterminal Stmt with pp, env, defs, errors;
flowtype Stmt = pp {}, defs {env}, errors {env};
propagate env on Stmt excluding seq;
propagate errors on Stmt;

-- Decides how best to pretty print a statement following if/while/etc
function ppblock
Document ::= s::Decorated Stmt with {}
{
  return case s of
         | block(_) -> pp" ${s} " -- the block will do it itself.
         | _ -> nestlines(3, s.pp)
         end;
}

abstract production declStmt
s::Stmt ::= d::Decl 
{
  s.pp = d.pp;
  s.defs = d.defs;
}

abstract production block
s::Stmt ::= body::Stmt 
{
  s.pp = pp"{${nestlines(3, body.pp)}}";
  s.defs = [];
}

abstract production seq
s::Stmt ::= s1::Stmt s2::Stmt 
{
  s.pp = pp"${s1}${line()}${s2}";
  s.defs = s1.defs ++ s2.defs;

  s1.env = s.env;
  s2.env = addEnv(s1.defs, s.env);
}

abstract production printStmt
s::Stmt ::= e::Expr 
{
  s.pp = pp"print(${e});";
  s.defs = [];
}

abstract production skip
s::Stmt ::= 
{
  s.pp = pp";";
  s.defs = [];
}

abstract production while
s::Stmt ::= c::Expr b::Stmt 
{
  s.pp = pp"while(${c})${ppblock(b)}";
  s.defs = [];
}

abstract production ifthen
s::Stmt ::= c::Expr t::Stmt 
{
  s.pp = pp"if(${c})${ppblock(t)}";
  s.defs = [];
}

abstract production ifelse
s::Stmt ::= c::Expr t::Stmt e::Stmt 
{
  s.pp = pp"if(${c})${ppblock(t)}else${ppblock(e)}";
  s.defs = [];
}

abstract production assignment
s::Stmt ::= id::Name e::Expr 
{
  s.pp = pp"${id} = ${e};";
  s.defs = [];
  s.errors <-
    case id.lookup of
    | just(_)   -> []
    | nothing() ->
        [err(id.location, s"variable \"${id.name}\" was not declared.")] 
    end;
}

