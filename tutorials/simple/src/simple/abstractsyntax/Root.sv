grammar simple:abstractsyntax;

imports silver:langutil;
imports silver:langutil:pp;

nonterminal Root with pp, errors;

inherited attribute env :: Decorated Env;
synthesized attribute defs :: [Def];
synthesized attribute type :: Type;

abstract production rootStmt
r::Root ::= s::Stmt
{
  r.pp = pp"main() {${nestlines(3, s.pp)}}";
  s.env = emptyEnv();
  r.errors := s.errors;
}



