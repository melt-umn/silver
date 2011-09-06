grammar simple:abstractsyntax;

imports silver:langutil;
imports silver:langutil:pp;

nonterminal Root with pp, errors;

autocopy attribute env :: Env<Decorated TypeExpr>;
synthesized attribute defs :: Env<Decorated TypeExpr>;
synthesized attribute type :: Type;

abstract production rootStmt
r::Root ::= s::Stmt
{
  r.pp = cat(cat(text("main() {"), cat(nest(3, cat(line(), s.pp)), line())), text("}"));
  s.env = emptyEnv();
  r.errors := s.errors;
}



