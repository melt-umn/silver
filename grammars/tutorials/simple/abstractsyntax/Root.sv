grammar tutorials:simple:abstractsyntax ;

imports tutorials:simple:terminals ;

nonterminal Root with pp, errors ;

synthesized attribute pp :: String ;

inherited   attribute env  :: Env<Decorated TypeExpr>;
synthesized attribute defs :: Env<Decorated TypeExpr> ;

synthesized attribute errors :: [String] with ++;

concrete production rootStmt
r::Root ::= s::Stmt
{ r.pp = "main {\n" ++ s.pp ++ "\n}\n\n" ;
  s.env = emptyEnv() ;
  r.errors := s.errors ;
}



