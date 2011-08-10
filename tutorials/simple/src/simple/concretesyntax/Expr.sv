grammar simple:concretesyntax;

{--
 - An expression, concrete syntax.
 -}
nonterminal Expr with pp, ast<ast:Expr>;

{- The productions given below for binary operators are ambiguous but
   traditional operator precedence and associativity specifications
   are provided in the terminals:Terminals.sv file and are used by
   Copper to resolve the resulting conflicts in the parse table.

   The dc tutorial provides an expression grammar in which
   these types of specifications are not used and the operator
   precedence and associativity is encoded in the grammar itself by
   introducing additional nonterminals.
-}


-- Logical Operations
---------------------
{- In the specification for the logical operations we use just the
   constant regex of the operator terminal symbol instead of the name
   of its terminal (which would also be allowed).  This often makes
   for rules that are easier to read.  
-}
concrete production and
e::Expr ::= l::Expr '&&' r::Expr
{
  e.pp = "(" ++  l.pp ++ " && " ++ r.pp ++ ")";
  e.ast = ast:and(l.ast, r.ast);
}
concrete production or
e::Expr ::= l::Expr '||' r::Expr 
{
  e.pp = "(" ++  l.pp ++ " || " ++ r.pp ++ ")";
  e.ast = ast:or(l.ast, r.ast);
}
concrete production not
e::Expr ::= '!' ne::Expr
{
  e.pp = "( !" ++  ne.pp ++ ")";
  e.ast = ast:not(ne.ast);
}


-- Relational Operations
------------------------
{- In these productions we've provide a name for the operator terminal
   symbol with the constant regex.  We can then use this name to request
   its lexeme and thus make all assignments to pp be the same.  
-}
concrete production eq
e::Expr ::= l::Expr op::'==' r::Expr
{
  e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
  e.ast = ast:eq(l.ast, r.ast);
}
concrete production neq
e::Expr ::= l::Expr op::'!=' r::Expr 
{
  e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
  e.ast = ast:neq(l.ast, r.ast);
}
concrete production lt
e::Expr ::= l::Expr op::'<'  r::Expr 
{
  e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
  e.ast = ast:lt(l.ast, r.ast);
}
concrete production lte
e::Expr ::= l::Expr op::'<=' r::Expr 
{
  e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
  e.ast = ast:lte(l.ast, r.ast);
}
concrete production gt
e::Expr ::= l::Expr op::'>'  r::Expr 
{
  e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
  e.ast = ast:gt(l.ast, r.ast);
}
concrete production gte
e::Expr ::= l::Expr op::'>=' r::Expr 
{
  e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
  e.ast = ast:gte(l.ast, r.ast);
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
   Silver called "forwarding" will allow us to do this type of
   re-use of attribute definitions.
-}
concrete production add
e::Expr ::= l::Expr op::'+' r::Expr
{
  e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
  e.ast = ast:add(l.ast, r.ast);
}
concrete production sub
e::Expr ::= l::Expr op::'-' r::Expr
{
  e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
  e.ast = ast:sub(l.ast, r.ast);
}
concrete production mul
e::Expr ::= l::Expr op::'*' r::Expr
{
  e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
  e.ast = ast:mul(l.ast, r.ast);
}
concrete production div
e::Expr ::= l::Expr op::'/' r::Expr
{
  e.pp = "(" ++  l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
  e.ast = ast:div(l.ast, r.ast);
}


-- Variable reference
concrete production varRef
e::Expr ::= id::term:Id
{
  e.pp = id.lexeme;
  e.ast = ast:varRef(name(id));
}


-- Literals
concrete production intLit
e::Expr ::= l::term:IntegerLiteral
{
  e.pp = l.lexeme; 
  e.ast = ast:intLit(loc(l.filename, l.line, l.column), l.lexeme);
}
concrete production floatLit
e::Expr ::= l::term:FloatLiteral
{
  e.pp = l.lexeme;
  e.ast = ast:floatLit(loc(l.filename, l.line, l.column), l.lexeme);
}
concrete production boolLit
e::Expr ::= l::term:BooleanLiteral
{
  e.pp = l.lexeme;
  e.ast = ast:boolLit(loc(l.filename, l.line, l.column), l.lexeme);
}
concrete production stringLit
e::Expr ::= l::term:StringLiteral
{
  e.pp = l.lexeme;
  e.ast = ast:stringLit(loc(l.filename, l.line, l.column), l.lexeme);
}
