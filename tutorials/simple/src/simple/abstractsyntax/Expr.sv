grammar simple:abstractsyntax;

{--
 - Names (identifiers) are useful to abstract as nonterminals, because we
 - frequently want to do similar things to them: look them up in the
 - environment, for example.
 -}
nonterminal Name with pp, name, location, env, lookup;

synthesized attribute lookup :: Maybe<Decorated TypeExpr>;
synthesized attribute name :: String;

abstract production name
n::Name ::= l::Location s::String
{
  n.name = s;
  n.pp = text(s);
  n.location = l;

  n.lookup = lookup(s, n.env);
}

nonterminal Expr with pp, env, errors;

-- Constants
------------
abstract production intLit   
e::Expr ::= l::Location s::String
{
  e.pp = text(s);
  e.errors := [];
}

abstract production floatLit 
e::Expr ::= l::Location s::String
{
  e.pp = text(s);
  e.errors := [];
}

abstract production boolLit   
e::Expr ::= l::Location s::String
{
  e.pp = text(s);
  e.errors := [];
}

abstract production stringLit 
e::Expr ::= l::Location s::String
{
  e.pp = text(s);
  e.errors := [];
}

-- Variable Reference
---------------------
{- Variable references need to be looked up in the environment.  Doing
   so return a Maybe<Decorated TypeExpr> - an optional reference back
   to the decorated type expression tree in the variable declaration.

   If the variable is not defined, this is a "nothing" value and an
   error message is generated.  The error message is stored in a
   "local attribute".  These are similar to local variables in other
   languages and are only visible in this production.

   The "production attribute" declTypeExpr is similar to a local
   attribute but its value is also visible in aspects on this
   production.  In TypeChecking.sv we reference this production
   attribute to extract the type of the variable.  
-}
abstract production varRef  
e::Expr ::= id::Name
{
  e.pp = id.pp;

  e.errors := case id.lookup of
                just(_)   -> []
              | nothing() -> [err(id.location, "variable \"" ++ id.name ++ "\" was not declared.")] 
              end;
}


function ppoperator
Document ::= d1::Document op::String d2::Document
{ return parens(cat(cat(d1, text(" " ++ op ++ " ")), d2));
}

-- Arithmetic Operations
------------------------
-- Only name declaration errors are computed here, thus we simply collect
-- the errors attributes from the children.
abstract production add 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, "+", r.pp);
  e.errors := l.errors ++ r.errors;
}
abstract production sub 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, "-", r.pp);
  e.errors := l.errors ++ r.errors;
}
abstract production mul 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, "*", r.pp);
  e.errors := l.errors ++ r.errors;
}
abstract production div 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, "/", r.pp);
  e.errors := l.errors ++ r.errors; 
}

-- Relational Operators
-----------------------
{- It wold be nice if we didn't have to define all the attributes for
   all of the relational and logical operators.  For example, we know
   "a != b" is equivalent to "! (a=b)" and that "a <= b" is equivalent
   to "a < b || a == b".

   Forwarding allows us to take advantage of these equalities.  Any
   attribute that is not explicitly defined on the production is
   implicitly defined by retrieving that value of that attribute from
   the tree that is "forwarded to".  Inherited attributes on the
   forward-to tree a copied from the "forwarding" tree.  

   Using forwarding need only define all of the attributes on eq and
   lt.  The rest (neq, lte, gt, gte) can be handled by forwarding.  
-}

abstract production eq 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, "==", r.pp);
  e.errors := l.errors ++ r.errors;
}

abstract production lt 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, "<", r.pp);
  e.errors := l.errors ++ r.errors;
}

abstract production neq 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, "!=", r.pp);
  forwards to not (eq(l,r));
  -- e.errors is copied from the forwarded-to tree
  -- Similarly, type checking attributes defined TypeChecking.sv are
  -- automatically copied, as are other yet-to-be defined attributes.
}
abstract production lte 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, "<=", r.pp);
  forwards to or( lt(l,r), eq(l,r) );
}
abstract production gt 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, ">", r.pp);
  forwards to not(lte(l,r));
}
abstract production gte 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, ">=", r.pp);
  forwards to not(lt(l,r));
}


-- Logical Operators
--------------------
abstract production and 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, "&&", r.pp);
  e.errors := l.errors ++ r.errors;
}
abstract production not 
e::Expr ::= ne::Expr 
{
  e.pp = cat(text("!"), parens(ne.pp));
  e.errors := ne.errors;
}

-- Using De Morgan's Law we can use forwarding on "or".
abstract production or 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, "||", r.pp);
  forwards to not( and(not(l), not(r)) );
}


