grammar simple:concretesyntax;

{- We'd prefer our abstract syntax not ever need to know about terminals, at all.
 - This is simply a helper function to convert terminals into ast names.
 -}


function name
ast:Name ::= id::term:Id
{
  return ast:name(loc(id.filename, id.line, id.column), id.lexeme);
}
