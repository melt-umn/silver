grammar edu:umn:cs:melt:tutorial:expr:exts:ctrans ;
 export edu:umn:cs:melt:tutorial:expr:exts:ctrans ;

import edu:umn:cs:melt:tutorial:expr:host ;

synthesized attribute c_trans :: String ;

attribute c_trans occurs on Root, Expr ;

-- To compute c_trans, a new AST is created in which all
-- let-expressions are lifted to the top level.  It is easier to
-- generate C code for these lifted expressions since there is no
-- nested scoping.
--
-- For example,
--   let x = 1 in
--   let y = 2 in
--   let z = let x = 2 in  x + y end
--   in  x + y + z + 100
--   end end end
--
-- is converted to
--
-- let x1 = 1 in 
-- let y2 = 2 in 
-- let x3 = 2 in
-- let z4 = (x3 + y2) 
-- in (((x1 + y2) + z4) + 100) 
-- end end end end

-- The lifted AST is stored in the following attributes:
synthesized attribute lifted_Dcls :: [ Dcl ] ;
synthesized attribute lifted_Expr :: Expr ;
synthesized attribute lifted_Root :: Root ;

attribute lifted_Dcls, lifted_Expr occurs on Expr ;
attribute lifted_Dcls, lifted_Root occurs on Root ;

nonterminal Dcl ;
attribute ident occurs on Dcl ;
synthesized attribute expr  :: Expr occurs on Dcl ;
synthesized attribute type  :: TypeExpr occurs on Dcl ;


-- These two productions hold the declaration information needed to
-- build the flattend let expresssions at the outer most scope.
abstract production dcl
d::Dcl ::= n::Id_t  e::Expr
{
 d.ident = n ; 
 d.expr = e ;
 d.type = error ("No type epxression for untyped declaration of " ++ 
                 n.lexeme ++ " at line " ++ toString(n.line) ++
                 " col " ++ toString(n.column) );
}

abstract production dcl_typed
d::Dcl ::= n::Id_t t::TypeExpr e::Expr
{
 d.ident = n ; 
 d.expr = e ;
 d.type = t ;
}

-- Root --
----------

aspect production root
r::Root ::= e::Expr
{
 r.lifted_Root = root( flatten_expr(e.lifted_Dcls, e.lifted_Expr) ) ;
 r.c_trans = "#include <stdio.h> \n\n" ++
             "int main(int argv, char** argc) { \n" ++
             e.c_trans ++ 
             "\n" ++ 
             "}\n\n" ;
}

function flatten_expr
Expr ::= dcls::[Dcl]  body::Expr
{
 return if   null(dcls)
        then body
        else case  head(dcls)  of
               dcl(id,e) 
               => let_expr ( id, e, 
                             flatten_expr ( tail(dcls), body ) )

             | dcl_typed(id,t,e) 
               => let_expr_typed (id, t, e, 
                                  flatten_expr ( tail(dcls), body ) )
             end ;
}

-- Expr --
----------

aspect production let_expr
l::Expr ::= id::Id_t v::Expr body::Expr
{
 l.lifted_Dcls = v.lifted_Dcls ++ 
                 [ dcl( unique_Id_dcl(id), v.lifted_Expr) ] ++
                 body.lifted_Dcls ;
 l.lifted_Expr = body.lifted_Expr ;
 l.c_trans = "/* Error - typed let must be used to generate C code. */ " ;
-- l.errors <- [ mk_error (id.line, id.column, 
--               "Types are needed to generate C code, " ++ 
--               "use a typed let expression. " )
--             ] ;
}

aspect production let_expr_typed
l::Expr ::= id::Id_t t::TypeExpr v::Expr body::Expr
{
 l.lifted_Dcls = v.lifted_Dcls ++ 
                 [ dcl_typed( unique_Id_dcl(id), t, v.lifted_Expr) ] ++ 
                 body.lifted_Dcls ;
 l.lifted_Expr = body.lifted_Expr ;
 l.c_trans 
  = t.c_trans ++ " " ++ id.lexeme ++ " = " ++ v.c_trans ++ " ; \n" ++
    case body of
      let_expr_typed(_,_,_,_) => body.c_trans 
    | _ => "printf (\"\\n" ++ body.typerep.printf_format ++ "\\n\", " ++ 
           body.c_trans ++ ");\n"
    end ;
}

aspect production idRef
e::Expr ::= id::Id_t
{
 e.lifted_Dcls = [ ] ;
 e.lifted_Expr = idRef( unique_Id_ref(id, res.binding.ident) );
 e.c_trans = id.lexeme ; 
}

aspect production add
sum::Expr ::= l::Expr  op::Plus_t  r::Expr
{
 sum.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 sum.lifted_Expr = add(l.lifted_Expr, op, r.lifted_Expr);
 sum.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
}

aspect production sub
dff::Expr ::= l::Expr  op::Dash_t  r::Expr
{
 dff.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 dff.lifted_Expr = sub(l.lifted_Expr, op, r.lifted_Expr);
 dff.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
}

aspect production mul
prd::Expr ::= l::Expr  op::Star_t  r::Expr
{
 prd.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 prd.lifted_Expr = mul(l.lifted_Expr, op, r.lifted_Expr);
 prd.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
}

aspect production div
quo::Expr ::= l::Expr  op::Slash_t  r::Expr
{
 quo.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 quo.lifted_Expr = div(l.lifted_Expr, op, r.lifted_Expr);
 quo.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
}

aspect production integerConstant
e::Expr ::= i::IntLit_t
{
 e.lifted_Dcls = [ ] ; 
 e.lifted_Expr = integerConstant(i);
 e.c_trans = i.lexeme ;
}

aspect production lessthan
e::Expr ::= l::Expr op::LT_t r::Expr
{
 e.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 e.lifted_Expr = lessthan(l.lifted_Expr, op, r.lifted_Expr);
 e.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
}

aspect production greaterthan
e::Expr ::= l::Expr op::GT_t r::Expr
{
 e.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 e.lifted_Expr = greaterthan(l.lifted_Expr, op, r.lifted_Expr);
 e.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
}

aspect production equals
e::Expr ::= l::Expr op::EqEq_t r::Expr
{
 e.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 e.lifted_Expr = equals(l.lifted_Expr, op, r.lifted_Expr);
 e.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
}

aspect production and
e::Expr ::= l::Expr op::And_t r::Expr
{
 e.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 e.lifted_Expr = and(l.lifted_Expr, op, r.lifted_Expr);
 e.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
}

aspect production not
e::Expr ::=  op::Not_t  n::Expr
{
 e.lifted_Dcls = n.lifted_Dcls ;
 e.lifted_Expr = not(op, n.lifted_Expr);
 e.c_trans = "(" ++ op.lexeme ++ " " ++ n.c_trans ++ ")";
}

aspect production cond
e::Expr ::= c::Expr q::Quest_t then_expr::Expr 
            cln::Colon_t else_expr::Expr
{
 e.lifted_Dcls = c.lifted_Dcls ++ then_expr.lifted_Dcls ++ 
                 else_expr.lifted_Dcls ;
 e.lifted_Expr = cond(c.lifted_Expr, q, then_expr.lifted_Expr, cln, 
                      else_expr.lifted_Expr);
 e.c_trans = "( " ++ c.c_trans ++ " ? " ++ 
             then_expr.c_trans ++ " : " ++ 
             else_expr.c_trans ++ " )" ;
}

aspect production trueConstant
e::Expr ::= t::True_t
{
 e.lifted_Dcls = [ ] ; 
 e.lifted_Expr = trueConstant(t);
 e.c_trans = "1" ;
}

aspect production falseConstant
e::Expr ::= f::False_t
{
 e.lifted_Dcls = [ ] ; 
 e.lifted_Expr = falseConstant(f);
 e.c_trans = "0" ;
}

aspect production tuple
e::Expr ::= l::Expr r::Expr
{
 e.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 e.lifted_Expr = tuple(l.lifted_Expr, r.lifted_Expr);
}

aspect production fst
e::Expr ::= f::Fst_t t::Expr
{
 e.lifted_Dcls = t.lifted_Dcls ;
 e.lifted_Expr = fst(f,t.lifted_Expr);
}

aspect production snd
e::Expr ::= s::Snd_t t::Expr
{
 e.lifted_Dcls = t.lifted_Dcls ;
 e.lifted_Expr = snd(s,t.lifted_Expr);
}


-- Utility functions for creating unique names for lifted identifiers.
-- Since we are flattening the scoped let expressions, names that were
-- in separate scopes are now combined.  We use the line and column
-- number of the identifier declaration to create unique name for each
-- identifier.

function unique_Id_dcl
Id_t ::= dcl_id::Id_t 
{ return unique_Id_ref(dcl_id, dcl_id) ;
}

function unique_Id_ref
Id_t ::= ref_id::Id_t  dcl_id::Id_t
{ return
    terminal(Id_t, 
             ref_id.lexeme ++ "_" ++ toString(dcl_id.line) ++ 
                              "_" ++ toString(dcl_id.column) ,
             ref_id.line, ref_id.column);
}
