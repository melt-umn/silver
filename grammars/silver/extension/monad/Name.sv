-- Exact copy of Name, but productions have higher precedence.
-- This is needed to avoid a parse conflict with '::' as both the type and cons operator
-- Expr -> QName -> Name which can be followed by ::.  This is a shift-reduce conflict.  
-- Using MName instead creates a reduce/reduce conflict with QName/Name which is resolved by setting
-- the production precedence.  

nonterminal MName with config, grammarName, location, unparse, name;

synthesized attribute name :: String;

concrete production mNameIdLower
top::MName ::= id::IdLower_t
precedence=2
{
  top.name = id.lexeme;
  top.unparse = id.lexeme;
}
concrete production mNameIdUpper
top::MName ::= id::IdUpper_t
precedence=2
{
  top.name = id.lexeme;
  top.unparse = id.lexeme;
}

function nameFromMName
Name ::= n::MName
{
  return name(n.name, n.location);
}

function mName
MName ::= n::String l::Location
{
  return mNameIdLower(terminal(IdLower_t, n, l), location=l);
}

