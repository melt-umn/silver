grammar tutorials:simple:abstractsyntax ;

nonterminal Decl with pp, env, defs, errors ;
nonterminal TypeExpr with pp, type ;
nonterminal Type with pp ;

-- Declarations
abstract production decl d::Decl ::= t::TypeExpr id::Id_t 
{ d.pp = t.pp ++ " " ++ id.lexeme ++ " ; \n" ; 
  d.defs = addBinding(id.lexeme, t, emptyEnv());
  d.errors := [ ] ;
}

-- Type Expressions
abstract production typeExprInteger t::TypeExpr ::=  
{ t.pp = "Integer" ;  
  t.type = integerType() ;
}
abstract production typeExprFloat t::TypeExpr ::=  
{ t.pp = "Float" ; 
  t.type = floatType() ;
}
abstract production typeExprBoolean t::TypeExpr ::=  
{ t.pp = "Boolean" ; 
  t.type = booleanType() ;
}


-- Types
{- Typically, type expressions (TypeExpr) are not used in the process
   of type checking as it is often necessary to compute a standard
   representation of types.  For example, languages that let
   programmers define their own named type need to resolve these names
   before doing type checking.

   Thus, we introduce a Type nonterminal for this purpose.  Each type
   expression is then decorated by the Type that is represented by the
   type expressions.
-}
abstract production integerType  t::Type ::=   
{ t.pp = "Integer" ; }
abstract production floatType    t::Type ::=   
{ t.pp = "Float" ; }
abstract production booleanType  t::Type ::=   
{ t.pp = "Boolean" ; }
abstract production errorType    t::Type ::=   
{ t.pp = "Erroneous Type" ; }



--abstract production typeExprChar t::TypeExpr ::=  { }
--abstract production typeExprString t::TypeExpr ::=  { }

--abstract production typeExprPointer t::TypeExpr ::= pt::TypeExpr { }
--abstract production typeExprArray t::TypeExpr ::= et::TypeExpr { }
