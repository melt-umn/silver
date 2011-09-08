grammar silver:extension:convenience;

terminal Children_kwd '$';


concrete production childrenRef
top::Expr ::= '$' e::Int_t {
  forwards to baseExpr(qNameId(nameIdLower(terminal(IdLower_t, findChild(toInt(e.lexeme), [top.signature.outputElement.elementName] ++ getNamesSignature(top.signature.inputElements))))));

}

function findChild
String ::= i::Integer s::[String]{
 return if null(s) then "NO_FOUND" else if i == 0 then head(s) else findChild(i-1, tail(s));
}
