grammar simple:abstractsyntax ;

imports simple:terminals ;

nonterminal Root with pp, errors ;

synthesized attribute pp :: String ;

inherited   attribute env  :: Env<Decorated TypeExpr>;
synthesized attribute defs :: Env<Decorated TypeExpr>;

synthesized attribute errors :: [String] with ++;
synthesized attribute type :: Type ;

abstract production rootStmt
r::Root ::= s::Stmt
{
  r.pp = "main {\n" ++ s.pp ++ "\n}\n\n" ;
  s.env = emptyEnv() ;
  r.errors := s.errors ;
}



