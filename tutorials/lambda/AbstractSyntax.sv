grammar lambda;

-- String used to hold informational error messages
synthesized attribute errors :: String;

-- Holds type description
synthesized attribute type :: Type;

-- List of pairs to hold variables and their type
inherited attribute envi :: [Pair<String Type>];
synthesized attribute envs :: [Pair<String Type>];

nonterminal Root       with pp, type, envs, errors;
nonterminal Expr       with pp, type, envi, errors;
nonterminal Expr_funct with pp, type, envi, errors;
nonterminal Expr_arith with pp, type, envi, errors;
nonterminal Term       with pp, type, envi, errors;
nonterminal Factor     with pp, type, envi, errors;
nonterminal Type       with pp, errors;

-- Root
abstract production root
p::Root ::= r::Expr
{
 p.pp = r.pp;
 p.type = r.type;

 r.envi = [];

 p.errors = case r.type of
                 int() -> r.errors
               | _ -> "Final Type:" ++ r.type.pp.result ++ "\n" ++ r.errors
            end;
}


-- Expr
abstract production expr_let
e::Expr ::= id::String t::Type e1::Expr e2::Expr
{
 local attribute synErrors :: String;
 synErrors = t.errors ++ e1.errors ++ e2.errors;

 e.pp = concat([text("let "), text(id), text(":"), t.pp, text("="), e1.pp, text(" in "), e2.pp]);
 e.type = e2.type;

 e1.envi = [] ++ e.envi;
 e2.envi = [pair(id, t)];

 e.errors = if !eqType(e1.type, t)
            then "Declaration type and definition are mismatched\n" 
              ++ "\tDecl:" ++ t.pp.result ++ "\n"
              ++ "\tDef :" ++ e1.type.pp.result ++ "\n"
              ++ synErrors
            else synErrors;
}

abstract production expr_lambda
e::Expr ::= id::String tl::Type e1::Expr
{
 local attribute synErrors :: String;
 synErrors = tl.errors ++ e1.errors;

 e.pp = concat([text("lambda "), text(id), text(":"), tl.pp, text("."), e1.pp]);
 e.type = arrow(tl, e1.type);

 e1.envi = [pair(id, tl)] ++ e.envi;

 e.errors = case tl of
              type_err() -> id ++ " is type_err\n"
            | _ -> ""
            end ++ 
            case e1.type of
              type_err() -> e1.pp.result ++ " is type_err\n"
            | _ -> ""
            end ++ synErrors;
}

abstract production expr_expr_f
r::Expr ::= uf::Expr_funct
{
 r.pp = uf.pp ;
 r.type = uf.type ;

 uf.envi = r.envi ;

 r.errors = uf.errors ;
}


-- Expr_funct
abstract production expr_funct
mp::Expr_funct ::= mp1::Expr_funct e::Expr_arith
{
 local attribute synErrors :: String;
 synErrors = mp1.errors ++ e.errors;

 mp.pp = concat([mp1.pp, space(), e.pp]);
 mp.type = case mp1.type of
             arrow(ta, tb) -> tb
           | int() -> type_err()
           | type_err() -> type_err()
           end ;

 mp1.envi = mp.envi;
 e.envi = mp.envi;

 mp.errors = case mp1.type of
               arrow(ta, tb) -> if !eqType(ta, e.type)
                                then "Incompatible types\n"
                                  ++ "\tMethod:" ++ mp1.type.pp.result ++ "\n"
                                  ++ "\tInput :" ++ e.type.pp.result ++ "\n"
                                  ++ synErrors
                                else synErrors
             | int() -> "Incompatible types\n"
                     ++ "\tMethod:int\n"
                     ++ "\tInput :" ++ e.type.pp.result ++ "\n"
                     ++ synErrors
             | type_err() -> "Error already exists\n" ++ synErrors
             end;
}

abstract production methodpassing_ex
mp::Expr_funct ::= e::Expr_arith
{
 mp.pp = e.pp;
 mp.type = e.type;

 e.envi = mp.envi;

 mp.errors = e.errors;
}


-- Expr_arith
abstract production expr_add
e::Expr_arith ::= e1::Expr_arith t::Term
{
 local attribute synErrors :: String;
 synErrors = e1.errors ++ t.errors;

 e.pp = concat([e1.pp, text("+"), t.pp]);
 e.type = int();

 e1.envi = e.envi;
 t.envi = e.envi;

 e.errors = if !eqType(e1.type, int())
            then "Invalid argument for binary operator:\n"
              ++ "\t" ++ e1.type.pp.result ++ "\n"
              ++ synErrors
            else
              if !eqType(t.type, int())
              then "Invalid argument for binary operator:\n"
                ++ "\t" ++ t.type.pp.result ++ "\n"
                ++ synErrors
              else synErrors;
}

abstract production expr_sub
e::Expr_arith ::= e1::Expr_arith t::Term
{
 e.pp = concat([e1.pp, text("-"), t.pp]);

 -- All other functionality is identical to expr_add
 forwards to expr_add(e1, t);
}

abstract production expr_term
e::Expr_arith ::= t::Term
{
 e.pp = t.pp;
 e.type = t.type;

 t.envi = e.envi;

 e.errors = t.errors;
}


-- Term
abstract production term_mul
t::Term ::= t1::Term f::Factor
{
 local attribute synErrors :: String;
 synErrors = t1.errors ++ f.errors;

 t.pp = concat([t1.pp, text("*"), f.pp]);
 t.type = int();

 t1.envi = t.envi;
 f.envi = t.envi;

 t.errors = if !eqType(t1.type, int())
            then "Incompatible parameter for binary operator\n"
              ++ "\t" ++ t1.type.pp.result ++ "\n"
              ++ synErrors
            else
              if !eqType(f.type, int()) 
              then "Incompatible parameter for binary operator\n"
                ++ "\t" ++ f.type.pp.result ++ "\n"
                ++ synErrors
              else synErrors;
}

abstract production term_div
t::Term ::= t1::Term f::Factor
{
 t.pp = concat([t1.pp, text("/"), f.pp]);

 -- All other functionality is identical to term_mul
 forwards to term_mul(t1,f);
}

abstract production term_factor
t::Term ::= f::Factor
{
 t.pp = f.pp;
 t.type = f.type;

 f.envi = t.envi;

 t.errors = f.errors;
}


-- Factor
abstract production factor_id
f::Factor ::= id::String
{
 f.pp = text(id);
 f.type = getType(id, f.envi);

 f.errors = if !isin(id, f.envi)
            then "Unknown identifier: " ++ id ++ "\n"
            else "";
}

abstract production factor_int
f::Factor ::= num::String
{
 f.pp = text(num);
 f.type = int();

 f.errors = if !isDigit(num)
            then "Not an integer: " ++ num ++ "\n"
            else "";
}

abstract production factor_parens
f::Factor ::= r::Expr
{
 f.pp = parens(r.pp);
 f.type = r.type;

 r.envi = f.envi;

 f.errors = r.errors;
}


-- Type
abstract production arrow
t::Type ::= t1::Type t2::Type
{
 t.pp = concat([parens(t1.pp), text("->"), parens(t2.pp)]);

 t.errors = t1.errors ++ t2.errors;
}

abstract production int
t::Type ::=
{
 t.pp = text("int");

 t.errors = "";
}

abstract production type_err
t::Type ::=
{
 t.pp = text("TYPE ERROR");

 t.errors = "TYPE ERROR";
}


-- Binding functions --
function isin
Boolean ::= s::String symboltable::[Pair<String Type>]
{
 return if length(symboltable) == 0
        then false
        else
          if head(symboltable).fst == s
          then true
          else isin(s, tail(symboltable));
}

function getType
Type ::= s::String symboltable::[Pair<String Type>]
{
 return if length(symboltable) == 0
        then type_err()
        else
          if head(symboltable).fst == s
          then head(symboltable).snd
          else getType(s, tail(symboltable));
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
        end;
}
