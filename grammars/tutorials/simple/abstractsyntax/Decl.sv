grammar tutorials:simple:abstractsyntax ;

nonterminal Decl with pp, env, defs, errors ;
nonterminal TypeExpr with pp ;

-- Declarations
abstract production decl d::Decl ::= t::TypeExpr id::Id_t 
{ d.pp = t.pp ++ " " ++ id.lexeme ++ " ; \n" ; 
  d.defs = addBinding(id.lexeme, t, emptyEnv());
  d.errors := [ ] ;
}

-- Type Expressions
abstract production typeExprInteger t::TypeExpr ::=  
{ t.pp = "Integer" ;  
}
abstract production typeExprFloat t::TypeExpr ::=  
{ t.pp = "Float" ; 
}




--abstract production typeExprChar t::TypeExpr ::=  { }
--abstract production typeExprString t::TypeExpr ::=  { }

--abstract production typeExprPointer t::TypeExpr ::= pt::TypeExpr { }
--abstract production typeExprArray t::TypeExpr ::= et::TypeExpr { }
