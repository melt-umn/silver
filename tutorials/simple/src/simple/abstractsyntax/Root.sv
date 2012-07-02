grammar simple:abstractsyntax;

imports silver:langutil;
imports silver:langutil:pp;

nonterminal Root with pp, errors;

autocopy attribute env :: Decorated Env;
synthesized attribute defs :: [Def];
synthesized attribute type :: Type;

abstract production rootStmt
r::Root ::= s::Stmt
{
  r.pp = cat(text("main() "), braces(nestlines(3, s.pp)));
  s.env = emptyEnv();
  r.errors := s.errors;
}



