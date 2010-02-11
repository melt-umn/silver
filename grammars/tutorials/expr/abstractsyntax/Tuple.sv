grammar tutorials:expr:abstractsyntax ;

import tutorials:expr:terminals ;

abstract production tupleType
tr::TypeRep ::= l::TypeRep  r::TypeRep
{
 tr.pp = "(" ++ l.pp ++ "," ++ r.pp ++ ")" ;
 tr.is_equal = case tr.check_for_equal of
                   tupleType(ltr,rtr)
                   -> equal_types(l,ltr) && equal_types(r,rtr)
               | _ -> false end ;
}

abstract production tupleTypeExpr
te::TypeExpr ::= l::TypeExpr c::Comma_t r::TypeExpr
{
 te.pp = "(" ++ l.pp ++ "," ++ r.pp ++ ")" ;
 te.typerep = tupleType(l.typerep, r.typerep);
} 

abstract production tuple
e::Expr ::= l::Expr r::Expr
{
 e.pp = "(" ++ l.pp ++ " , " ++ r.pp ++ ")";
 e.typerep = tupleType(l.typerep, r.typerep);
 e.errors := l.errors ++ r.errors ;
 e.haskell = "(" ++ l.haskell ++ " , " ++ r.haskell ++ ")";
}

abstract production fst
e::Expr ::= f::Fst_t t::Expr
{
 e.pp = "fst(" ++ t.pp ++ ")" ;
 e.typerep = case t.typerep of
               tupleType(l,r) -> new( l )
             | _ -> errorType()
             end ;
 e.errors := case t.typerep of
               tupleType(_,_) -> [ ::String ]
             | _ -> [ mk_error(f.line, f.column, 
                               "requires a tuple-type argument") ]
             end 
             ++ t.errors ;
 e.haskell = "(fst " ++ t.haskell ++ ")" ;
}

abstract production snd
e::Expr ::= s::Snd_t t::Expr
{
 e.pp = "snd(" ++ t.pp ++ ")" ;
 e.typerep = case t.typerep of
               tupleType(l,r) -> new(r)
             | _ -> errorType()
             end ;
 e.errors := case t.typerep of
               tupleType(_,_) -> [ ::String ]
             | _ -> [ mk_error(s.line, s.column, 
                               "requires a tuple-type argument") ]
             end 
             ++ t.errors ;
 e.haskell = "(snd " ++ t.haskell ++ ")" ;
}
