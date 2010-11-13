grammar tutorials:simple:abstractsyntax ;

nonterminal Decl with pp ;
nonterminal TypeExpr with pp ;

abstract production decl d::Decl ::= t::TypeExpr id::Id_t 
{ d.pp = t.pp ++ " " ++ id.lexeme ++ " ; \n" ; 
}

abstract production typeExprInteger t::TypeExpr ::=  
{ t.pp = "Integer" ;  }
abstract production typeExprFloat t::TypeExpr ::=  
{ t.pp = "Float" ; }


--abstract production typeExprChar t::TypeExpr ::=  { }
--abstract production typeExprString t::TypeExpr ::=  { }

--abstract production typeExprPointer t::TypeExpr ::= pt::TypeExpr { }
--abstract production typeExprArray t::TypeExpr ::= et::TypeExpr { }
