grammar tutorials:simple:concretesyntax ;

nonterminal Expr_c with pp ;

synthesized attribute ast_Expr :: Expr occurs on Expr_c ;

{- The productions given below for binary operators are ambiguous but
   traditional operator precedence and associativity specifications
   are provided in the terminals:Terminals.sv file and are used by
   Copper to resolve the resulting conflicts in the parse table.

   The grammar tutorials:dc provide an expression grammar in which
   these types of specifications are not used and the operator
   precedence and associativity is encoded in the grammar itself by
   introducing additional nonterminasl.
-}

-- Logical Operations
---------------------
{- In the specification for the logical operations we use just the
   constant regex of the operator terminal symbol instead of the name
   of its terminal (which would also be allowed).  This often makes
   for rules that are easier to read.  
-}
concrete production and_c  e::Expr_c ::= l::Expr_c '&&' r::Expr_c
{ e.pp = "(" ++  l.pp ++ " && " ++ r.pp ++ ")" ;
  e.ast_Expr = and(l.ast_Expr, r.ast_Expr) ;
}
concrete production or_c e::Expr_c ::= l::Expr_c '||' r::Expr_c 
{ e.pp = "(" ++  l.pp ++ " || " ++ r.pp ++ ")" ;
  e.ast_Expr = or(l.ast_Expr, r.ast_Expr) ;
}
concrete production not_c  e::Expr_c ::= '!' ne::Expr_c
{ e.pp = "( !" ++  ne.pp ++ ")" ;
  e.ast_Expr = not(ne.ast_Expr) ;
}

-- Relational Operations
------------------------
{- In these productions we've provide a name for the operator terminal
   symbol with the constant regex.  We can then use this name to request
   its lexeme and thus make all assignments to pp be the same.  
-}
concrete production eq_c  e::Expr_c ::= l::Expr_c op::'==' r::Expr_c
{ e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
  e.ast_Expr = eq(l.ast_Expr, r.ast_Expr) ;
}
concrete production neq_c e::Expr_c ::= l::Expr_c op::'!=' r::Expr_c 
{ e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
  e.ast_Expr = neq(l.ast_Expr, r.ast_Expr) ;
}
concrete production lt_c  e::Expr_c ::= l::Expr_c op::'<'  r::Expr_c 
{ e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
  e.ast_Expr = lt(l.ast_Expr, r.ast_Expr) ;
}
concrete production lte_c e::Expr_c ::= l::Expr_c op::'<=' r::Expr_c 
{ e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
  e.ast_Expr = lte(l.ast_Expr, r.ast_Expr) ;
}
concrete production gt_c  e::Expr_c ::= l::Expr_c op::'>'  r::Expr_c 
{ e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
  e.ast_Expr = gt(l.ast_Expr, r.ast_Expr) ;
}
concrete production gte_c e::Expr_c ::= l::Expr_c op::'>=' r::Expr_c 
{ e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
  e.ast_Expr = gte(l.ast_Expr, r.ast_Expr) ;
}


-- Arithmetic Operations
------------------------
{- Since the specifications of pp for the relational operations are
   all the same, it suggests that it would be nice to write this
   definition just once and somehow reuse it for all.  Something like
   writing a method in a superclass so that all subclasses can use it.

   In the current version of Silver, we cannot since each terminal
   defines its own distinct type and there is no generic
   superclass-like notion of terminal that would allow us to treat
   each operator terminal as some generic terminal type with a lexeme
   attribute.

   In other cases we will see where an attribute grammar extension in
   Silver callded "forwarding" will all use to just this type of
   re-use of attribute definitions.
-}
concrete production add_c  e::Expr_c ::= l::Expr_c op::'+' r::Expr_c
{ e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
  e.ast_Expr = add(l.ast_Expr, r.ast_Expr) ;
}
concrete production sub_c  e::Expr_c ::= l::Expr_c op::'-' r::Expr_c
{ e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
  e.ast_Expr = sub(l.ast_Expr, r.ast_Expr) ;
}
concrete production mul_c  e::Expr_c ::= l::Expr_c op::'*' r::Expr_c
{ e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
  e.ast_Expr = mul(l.ast_Expr, r.ast_Expr) ;
}
concrete production div_c  e::Expr_c ::= l::Expr_c op::'/' r::Expr_c
{ e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
  e.ast_Expr = div(l.ast_Expr, r.ast_Expr) ;
}


-- Variable reference
concrete production varRef_c
e::Expr_c ::= id::Id_t
{ e.pp = id.lexeme ;
  e.ast_Expr = varRef(id) ;
}
