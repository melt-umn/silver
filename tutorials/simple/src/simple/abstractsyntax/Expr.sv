grammar simple:abstractsyntax;

imports silver:util:random;

{--
 - Names (identifiers) are useful to abstract as nonterminals, because we
 - frequently want to do similar things to them: look them up in the
 - environment, for example.
 -}
nonterminal Name with pp, name, location, env, lookup;

synthesized attribute lookup :: Maybe<Decorated TypeExpr>;
synthesized attribute name :: String;

abstract production name
n::Name ::= s::String
{
  n.name = s;
  n.pp = text(s);

  n.lookup = lookupValue(s, n.env);
}

instance Arbitrary Name {
  genArb = \ depth::Integer -> do {
    chars :: [Integer] <- randomShuffle(stringToChars("abcd"));
    loc :: Location <- genArb(depth);
    return name(charsToString(chars), location=loc);
  };
}

nonterminal Expr with pp, env, errors;

-- Constants
------------
abstract production intLit   
e::Expr ::= i::Integer
{
  e.pp = text(toString(i));
  e.errors := [];
}

abstract production floatLit 
e::Expr ::= f::Float
{
  e.pp = text(toString(f));
  e.errors := [];
}

abstract production boolLit   
e::Expr ::= b::Boolean
{
  e.pp = if b then pp"True" else pp"False";
  e.errors := [];
}

abstract production stringLit 
e::Expr ::= s::String
{
  e.pp = pp"\"${text(escapeString(s))}\"";
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

  e.errors :=
    case id.lookup of
    | just(_)   -> []
    | nothing() ->
        [err(id.location, s"variable \"${id.name}\" was not declared.")] 
    end;
}


-- Arithmetic Operations
------------------------
-- Only name declaration errors are computed here, thus we simply collect
-- the errors attributes from the children.
abstract production add 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} + ${r})";
  e.errors := l.errors ++ r.errors;
}
abstract production sub 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} - ${r})";
  e.errors := l.errors ++ r.errors;
}
abstract production mul 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} * ${r})";
  e.errors := l.errors ++ r.errors;
}
abstract production div 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} / ${r})";
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

abstract production eqOp
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} == ${r})";
  e.errors := l.errors ++ r.errors;
}

abstract production ltOp
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} < ${r})";
  e.errors := l.errors ++ r.errors;
}

abstract production neqOp
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} != ${r})";
  forwards to not (eqOp(l,r));
  -- e.errors is copied from the forwarded-to tree
  -- Similarly, type checking attributes defined TypeChecking.sv are
  -- automatically copied, as are other yet-to-be defined attributes.
}
abstract production lteOp
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} <= ${r})";
  forwards to or( ltOp(l,r), eqOp(l,r) );
}
abstract production gtOp
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} > ${r})";
  forwards to not(lteOp(l,r));
}
abstract production gteOp
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} >= ${r})";
  forwards to not(ltOp(l,r));
}


-- Logical Operators
--------------------
abstract production and 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} && ${r})";
  e.errors := l.errors ++ r.errors;
}
abstract production not 
e::Expr ::= ne::Expr 
{
  e.pp = pp"!(${ne})";
  e.errors := ne.errors;
}

-- Using De Morgan's Law we can use forwarding on "or".
abstract production or 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} || ${r})";
  forwards to not( and(not(l), not(r)) );
}


