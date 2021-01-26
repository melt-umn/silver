grammar simple:concretesyntax;

{--
 - An expression, concrete syntax.
 -}
closed nonterminal Expr with unparse, location, ast<ast:Expr>;

{- The productions given below for binary operators are ambiguous but
   traditional operator precedence and associativity specifications
   are provided in the terminals:Terminals.sv file and are used by
   Copper to resolve the resulting conflicts in the parse table.

   The dc tutorial provides an expression grammar in which
   these types of specifications are not used and the operator
   precedence and associativity is encoded in the grammar itself by
   introducing additional nonterminals.
-}


concrete productions e::Expr
 |  '(' e1::Expr ')'  { e.unparse = "(" ++ e1.unparse ++ ")";
                        e.ast = e1.ast; }
-- Logical Operations
---------------------
{- In the specification for the logical operations we use just the
   constant regex of the operator terminal symbol instead of the name
   of its terminal (which would also be allowed).  This often makes
   for rules that are easier to read. -}
 | l::Expr '&&' r::Expr  { e.unparse = "(" ++  l.unparse ++ " && " ++ r.unparse ++ ")";
                           e.ast = ast:and(l.ast, r.ast); }
 | l::Expr '||' r::Expr  { e.unparse = "(" ++  l.unparse ++ " || " ++ r.unparse ++ ")";
                           e.ast = ast:or(l.ast, r.ast); }
 | '!' ne::Expr          { e.unparse = "( !" ++  ne.unparse ++ ")";
                           e.ast = ast:not(ne.ast); }

-- Relational Operations
------------------------
{- In these productions we've provide a name for the operator terminal
   symbol with the constant regex.  We can then use this name to request
   its lexeme and thus make all assignments to unparse be the same.  -}

 | l::Expr op::'==' r::Expr  { e.unparse = "(" ++  l.unparse ++ " " ++ op.lexeme ++ " " ++ r.unparse ++ ")";
                               e.ast = ast:eqOp(l.ast, r.ast); }
 | l::Expr op::'!=' r::Expr  { e.unparse = "(" ++  l.unparse ++ " " ++ op.lexeme ++ " " ++ r.unparse ++ ")";
                               e.ast = ast:neqOp(l.ast, r.ast); }
 | l::Expr op::'<'  r::Expr  { e.unparse = "(" ++  l.unparse ++ " " ++ op.lexeme ++ " " ++ r.unparse ++ ")";
                               e.ast = ast:ltOp(l.ast, r.ast); }
 | l::Expr op::'<=' r::Expr  { e.unparse = "(" ++  l.unparse ++ " " ++ op.lexeme ++ " " ++ r.unparse ++ ")";
                               e.ast = ast:lteOp(l.ast, r.ast); }
 | l::Expr op::'>'  r::Expr  { e.unparse = "(" ++  l.unparse ++ " " ++ op.lexeme ++ " " ++ r.unparse ++ ")";
                               e.ast = ast:gtOp(l.ast, r.ast); }
 | l::Expr op::'>=' r::Expr  { e.unparse = "(" ++  l.unparse ++ " " ++ op.lexeme ++ " " ++ r.unparse ++ ")";
                               e.ast = ast:gteOp(l.ast, r.ast); }

-- Arithmetic Operations
------------------------
{- Since the specifications of unparse for the relational operations are
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
   re-use of attribute definitions. -}


 | l::Expr op::'+' r::Expr  { e.unparse = "(" ++  l.unparse ++ " " ++ op.lexeme ++ " " ++ r.unparse ++ ")";
                              e.ast = ast:add(l.ast, r.ast); }
 | l::Expr op::'-' r::Expr  { e.unparse = "(" ++  l.unparse ++ " " ++ op.lexeme ++ " " ++ r.unparse ++ ")";
                              e.ast = ast:sub(l.ast, r.ast); }
 | l::Expr op::'*' r::Expr  { e.unparse = "(" ++  l.unparse ++ " " ++ op.lexeme ++ " " ++ r.unparse ++ ")";
                              e.ast = ast:mul(l.ast, r.ast); }
 | l::Expr op::'/' r::Expr  { e.unparse = "(" ++  l.unparse ++ " " ++ op.lexeme ++ " " ++ r.unparse ++ ")";
                              e.ast = ast:div(l.ast, r.ast); }


-- Variable reference

 | id::Id  { e.unparse = id.lexeme;
             e.ast = ast:varRef(name(id)); }

-- Literals

 | l::IntegerLiteral  { e.unparse = l.lexeme; 
                        e.ast = ast:intLit(l.lexeme); }
 | l::FloatLiteral    { e.unparse = l.lexeme;
                        e.ast = ast:floatLit(l.lexeme); }
 | l::BooleanLiteral  { e.unparse = l.lexeme;
                        e.ast = ast:boolLit(l.lexeme); }
 | l::StringLiteral   { e.unparse = l.lexeme;
                        e.ast = ast:stringLit(l.lexeme); }

