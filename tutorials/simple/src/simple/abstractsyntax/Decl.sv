grammar simple:abstractsyntax;

{--
 - Declaration statement ast
 -}
nonterminal Decl with pp, env, defs, errors;

abstract production decl
d::Decl ::= t::TypeExpr id::Name 
{
  d.pp = concat([t.pp, space(), id.pp, semi()]);
  d.defs = addBinding(id.name, t, emptyEnv());
  d.errors := [];
}

{--
 - Type expression ast
 -}
nonterminal TypeExpr with pp, type;

abstract production typeExprInteger
t::TypeExpr ::=  
{
  t.pp = text("Integer");  
  t.type = integerType();
}
abstract production typeExprFloat
t::TypeExpr ::=  
{
  t.pp = text("Float"); 
  t.type = floatType();
}
abstract production typeExprBoolean
t::TypeExpr ::=  
{
  t.pp = text("Boolean"); 
  t.type = booleanType();
}
abstract production typeExprString
t::TypeExpr ::=  
{
  t.pp = text("String"); 
  t.type = stringType();
}

{- Typically, type expressions (TypeExpr) are not used in the process
   of type checking as it is often necessary to compute a standard
   representation of types.  For example, languages that let
   programmers define their own named type need to resolve these names
   before doing type checking.

   Thus, we introduce a Type nonterminal for this purpose.  Each type
   expression is then decorated by the Type that is represented by the
   type expressions.
-}
nonterminal Type with pp;

abstract production integerType
t::Type ::=   
{
  t.pp = text("Integer");
}
abstract production floatType
t::Type ::=   
{
  t.pp = text("Float");
}
abstract production booleanType
t::Type ::=   
{
  t.pp = text("Boolean");
}
abstract production stringType
t::Type ::=   
{
  t.pp = text("String");
}
abstract production errorType
t::Type ::=   
{
  t.pp = text("Erroneous Type");
}

