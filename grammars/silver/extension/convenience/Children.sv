grammar silver:extension:convenience;

terminal Children_kwd '$' lexer classes {LITERAL};

-- TODO: BUG: global Foo :: Integer = $1; -- CRASH
-- Similarly, $99 -- CRASH

-- What we SHOULD do here is translate this to a baseExpr(qName("$#"))
-- and alter productions to make them put the $# for their children in the environment.
-- That way, the access to signature is properly guarded.

concrete production childrenRef
top::Expr ::= '$' e::Int_t
{
  top.pp = "$" ++ e.lexeme;
  top.location = $1.location;

  forwards to baseExpr(qName(top.location,
    findChild(toInt(e.lexeme), 
      [top.signature.outputElement.elementName] ++ top.signature.inputNames)));
}

function findChild
String ::= i::Integer s::[String]
{
  return if null(s) then "NO_FOUND" else if i == 0 then head(s) else findChild(i-1, tail(s));
}
