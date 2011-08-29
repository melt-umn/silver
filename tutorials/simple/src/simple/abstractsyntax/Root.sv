grammar simple:abstractsyntax;

imports silver:langutil;

nonterminal Root with pp, errors;

autocopy attribute env :: Env<Decorated TypeExpr>;
synthesized attribute defs :: Env<Decorated TypeExpr>;
synthesized attribute type :: Type;

abstract production rootStmt
r::Root ::= s::Stmt
{
  r.pp = "main() {\n" ++ s.pp ++ "\n}\n";
  s.env = emptyEnv();
  r.errors := s.errors;
}



