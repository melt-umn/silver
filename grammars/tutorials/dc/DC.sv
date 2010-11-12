grammar tutorials:dc ;

{--
 - "Pretty print" - unparse the tree to a string
 -}
synthesized attribute pp :: String ;

-- Concrete Syntax --
---------------------

{--
 - The abstract syntax tree.  This is a "higher order attribute."
 -}
synthesized attribute ast_Root :: Root;

-- Method #1 for declaring than an attribute occurs on a nonterminal: 'with'
nonterminal Root_c with pp, ast_Root;

-- 'concrete' means when this grammar is used to generate a parser, this 
-- production will be sent to the parser generator.
concrete production root_c
r::Root_c ::= e::Expr_c 
{
  r.pp = e.pp;
  r.ast_Root = root(e.ast_Expr); -- 'root' is the abstract production below.
}

-- Abstract Syntax --
---------------------

{--
 - The type of the abstract syntax tree for Root_c.
 -}
nonterminal Root;

-- Method #2 for declaring than an attribute occurs on a nonterminal: 
-- the 'occurs on' declaration
attribute pp occurs on Root; -- "stand alone" occurs.

-- Attribute declarations and occurs on declrations can be combined.
synthesized attribute value :: Integer occurs on Root;

-- 'abstract' is not 'concrete'.  The parser generator will not be
-- told about this production.
abstract production root
r::Root ::= e::Expr
{
  r.pp = e.pp ;
  r.value = e.value ;
}
