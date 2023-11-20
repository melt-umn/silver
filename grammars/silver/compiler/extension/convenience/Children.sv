grammar silver:compiler:extension:convenience;

terminal Children_kwd '$' lexer classes {LITERAL};

concrete production childrenRef
top::Expr ::= '$' e::Int_t
{
  top.unparse = "$" ++ e.lexeme;
  
  local ref :: String =
    -- for failure, pretend to be the identifier $x, even though that's not a legal identifier.
    -- Will error with "undefined value $x", which is reasonable.
    fromMaybe("$" ++ e.lexeme, 
      -- TODO: horrible method of detemining whether we're not in an appropriate context for $x 
      if top.frame.signature.outputElement.elementName == "__SV_BOGUS_ELEM" -- TODO hack!
      then nothing()
      else
        findChild(toInteger(e.lexeme),
          [top.frame.signature.outputElement.elementName] ++ top.frame.signature.inputNames));

  forwards to baseExpr(qName(ref));
}

fun findChild Maybe<String> ::= i::Integer s::[String] =
  if null(s) then nothing() else if i == 0 then just(head(s)) else findChild(i-1, tail(s));
