grammar lambda ;

import silver:langutil only pp ;
import silver:langutil:pp ;

synthesized attribute errors :: String ;
synthesized attribute ok :: Boolean ;

synthesized attribute type :: Type ;

inherited attribute envi :: [Pair<String Type>] ;
synthesized attribute envs :: [Pair<String Type>] ;

-- Root
nonterminal Root with pp, ok, type, envs, errors ;
abstract production root
p::Root ::= r::Expr
{
 p.pp = r.pp ;
 p.ok = case r.type of
             int() -> r.ok
           | arrow(t1, t2) -> false
           | type_err() -> false
        end ;
 p.errors = case r.type of
                 int() -> r.errors
               | _ -> "Final Type:" ++ printType(r.type) ++ "\n" ++ r.errors
            end ;
 p.type = r.type ;

 r.envi = [] ;
}

-- Expr
nonterminal Expr with pp, ok, type, envi, errors ;
abstract production expr_dcl
r::Expr ::= d::Decl r1::Expr r2::Expr
{
--TODO: FIX THIS
 local attribute synErrors :: String ;
 synErrors = d.errors ++ r1.errors ++ r2.errors ;

 r.pp = concat([text("let "), d.pp, text("="), r1.pp, text(" in "), r2.pp]) ;
 r.ok = d.ok
        && r1.ok
        && r2.ok 
        && eqType(r1.type, head(d.envs).snd) ;
 r.errors = if !eqType(r1.type, head(d.envs).snd)
            then "Declaration type and definition are mismatched\n" 
              ++ "\tDecl:" ++ printType(head(d.envs).snd) ++ "\n"
              ++ "\tDef :" ++ printType(r1.type) ++ "\n"
              ++ synErrors
            else synErrors ;
 r.type = r2.type ;

 r1.envi = [] ++ r.envi ;
 r2.envi = d.envs ++ r.envi ;
}

abstract production expr_expr_f
r::Expr ::= uf::Expr_funct
{
 r.pp = uf.pp ;
 r.ok = uf.ok ;
 r.type = uf.type ;
 r.errors = uf.errors ;

 uf.envi = r.envi ;
}

-- Expr_funct
nonterminal Expr_funct with pp, ok, type, envi, errors ;
abstract production expr_funct
mp::Expr_funct ::= mp1::Expr_funct e::Expr_arith
{
 local attribute synErrors :: String ;
 synErrors = mp1.errors ++ e.errors ;

 mp.pp = concat([mp1.pp, space(), e.pp]) ;
 mp.ok = case mp1.type of
           arrow(ta, tb) -> eqType(ta, e.type)
         | int() -> eqType(int(), e.type)
         end ;
 mp.type = case mp1.type of
             arrow(ta, tb) -> tb
           | int() -> type_err()
           | type_err() -> type_err()
           end ;
 mp.errors = case mp1.type of
               arrow(ta, tb) -> if !eqType(ta, e.type)
                                then "Incompatible types\n"
                                  ++ "\tMethod:" ++ printType(mp1.type) ++ "\n"
                                  ++ "\tInput :" ++ printType(e.type) ++ "\n"
                                  ++ synErrors
                                else synErrors
             | int() -> "Incompatible types\n"
                     ++ "\tMethod:int\n"
                     ++ "\tInput :" ++ printType(e.type) ++ "\n"
                     ++ synErrors
             | type_err() -> "Error already exists\n" ++ synErrors
             end ;

 mp1.envi = mp.envi ;
 e.envi = mp.envi ;
}

abstract production methodpassing_ex
mp::Expr_funct ::= e::Expr_arith
{
 mp.pp = e.pp ;
 mp.ok = e.ok ;
 mp.type = e.type ;
 mp.errors = e.errors ;
 
 e.envi = mp.envi ;
}

-- Expr_arith
nonterminal Expr_arith with pp, ok, type, envi, errors ;
abstract production expr_op
e::Expr_arith ::= e1::Expr_arith t::Term
{
 local attribute synErrors :: String ;
 synErrors = e1.errors ++ t.errors ;

 e.pp = concat([e1.pp, text("+"), t.pp]) ;
 e.ok = e1.ok 
        && t.ok 
        && (eqType(e1.type, int()) && eqType(t.type, int())) ;
 e.type = int() ;
 e.errors = if !eqType(e1.type, int())
            then "Invalid argument for binary operator:\n"
              ++ "\t" ++ printType(e1.type) ++ "\n"
              ++ synErrors
            else
              if !eqType(t.type, int())
              then "Invalid argument for binary operator:\n"
                ++ "\t" ++ printType(t.type) ++ "\n"
                ++ synErrors
              else synErrors ;

 e1.envi = e.envi ;
 t.envi = e.envi ;
}

abstract production expr_term
e::Expr_arith ::= t::Term
{
 e.pp = t.pp ;
 e.ok = t.ok ;
 e.type = t.type ;
 e.errors = t.errors ;

 t.envi = e.envi ;
}

-- Term
nonterminal Term with pp, ok, type, envi, errors ;
abstract production term_op
t::Term ::= t1::Term f::Factor
{
 local attribute synErrors :: String ;
 synErrors = t1.errors ++ f.errors ;

 t.pp = concat([t1.pp, text("*"), f.pp]) ;
 t.ok = t1.ok 
        && f.ok 
        && eqType(t1.type, int()) 
        && eqType(f.type, int()) ;
 t.type = int() ;
 t.errors = if !eqType(t1.type, int())
            then "Incompatible parameter for binary operator\n"
              ++ "\t" ++ printType(t1.type) ++ "\n"
              ++ synErrors
            else
              if !eqType(f.type, int()) 
              then "Incompatible parameter for binary operator\n"
                ++ "\t" ++ printType(f.type) ++ "\n"
                ++ synErrors
              else synErrors ;

 t1.envi = t.envi ;
 f.envi = t.envi ;
}

abstract production term_factor
t::Term ::= f::Factor
{
 t.pp = f.pp ;
 t.ok = f.ok ;
 t.type = f.type ;
 t.errors = f.errors ;

 f.envi = t.envi ;
}

-- Factor
nonterminal Factor with pp, ok, type, envi, errors ;
abstract production factor_id
f::Factor ::= id::String
{
 f.pp = text(id) ;
 f.ok = isin(id, f.envi) ;
 f.type = if f.ok
          then getType(id, f.envi)
          else type_err() ;
 f.errors = if !isin(id, f.envi)
            then "Unknown identifier: " ++ id ++ "\n"
            else "" ;
}

abstract production factor_int
f::Factor ::= num::String
{
 f.pp = text(num) ;
 f.ok = isDigit(num) ;
 f.type = int();
 f.errors = if !isDigit(num)
            then "Not an integer: " ++ num ++ "\n"
            else "" ;
}

abstract production factor_parens
f::Factor ::= r::Expr
{
 f.pp = parens(r.pp) ;
 f.ok = r.ok ;
 f.type = r.type ;
 f.errors = r.errors ;
 
 r.envi = f.envi ;
}

-- Type
nonterminal Type with pp, ok, errors ;
abstract production arrow
t::Type ::= t1::Type t2::Type
{
 t.pp = concat([parens(t1.pp), text("->"), parens(t2.pp)]) ;
 t.ok = t1.ok && t2.ok ;
 t.errors = t1.errors ++ t2.errors ;
}

abstract production int
t::Type ::=
{
 t.pp = text("int") ;
 t.ok = true ;
 t.errors = "" ;
}

abstract production type_err
t::Type ::=
{
 t.pp = text("TYPE ERROR") ;
 t.ok = false ;
 t.errors = "TYPE ERROR" ;
}

-- Var
nonterminal Var with pp, ok, envs, type, errors ;
abstract production var
v::Var ::= d::Decl
{
 v.pp = cat(text("\\"), d.pp) ;
 v.ok = d.ok ;
 v.envs = d.envs ;
 v.type = d.type ;
 v.errors = d.errors ;
}

-- Decl
nonterminal Decl with pp, ok, envs, type, errors ;
abstract production decl
d::Decl ::= id::String t::Type
{
 d.pp = concat([text(id), text(":"), t.pp]) ;
 d.ok = t.ok ;
 d.envs = [pair(id, t)] ;
 d.type = t ;
 d.errors = t.errors ;
}

-- SymbolTable functions --
function isin
Boolean ::= s::String symboltable::[Pair<String Type>]
{
 return if length(symboltable) == 0
        then false
        else
          if head(symboltable).fst == s
          then true
          else isin(s, tail(symboltable)) ;
}

function getType
Type ::= s::String symboltable::[Pair<String Type>]
{
 return if length(symboltable) == 0
        then type_err()
        else
          if head(symboltable).fst == s
          then head(symboltable).snd
          else getType(s, tail(symboltable)) ;
}

function envString
String ::= st::[Pair<String Type>]
{
 return if null(st)
        then ""
        else head(st).fst ++ ":" ++ printType(head(st).snd) ++ "|" ++ envString(tail(st)) ;
}

function printType
String ::= t::Type
{
  return case t of
           arrow(ta, tb) -> "(" ++ printType(ta) ++ "->" ++ printType(tb) ++ ")"
         | int() -> "int"
         | type_err() -> "ERROR"
         end ;
}

function eqType
Boolean ::= t1::Type t2::Type
{
 return case t1 of
          arrow(ta, tb) -> case t2 of 
                             arrow(tc, td) -> eqType(ta, tc) && eqType(tb, td) 
                           | int() -> false 
                           | type_err() -> false
                           end
        | int() -> case t2 of 
                     arrow(tc, td) -> false
                   | int() -> true
                   | type_err() -> false
                   end
        end ;
}

function eqPair
Boolean ::= p1::Pair<String Type> p2::Pair<String Type>
{
 return if p1.fst == p2.fst
        then eqType(p1.snd, p2.snd)
        else false ;
}

function eqST
Boolean ::= st1::[Pair<String Type>] st2::[Pair<String Type>]
{
 return if length(st1) == 0
           && length(st2) == 0
        then true
        else
          if eqPair(head(st1), head(st2))
          then eqST(tail(st1), tail(st2))
          else false ;
}