grammar tutorials:simple:abstractsyntax ;

imports tutorials:simple:terminals ;

nonterminal Root with pp ;

synthesized attribute pp :: String ;

concrete production rootStmt
r::Root ::= s::Stmt
{
 r.pp = "main {\n" ++ -- s.pp ++
            "\n}\n\n" ;
}



