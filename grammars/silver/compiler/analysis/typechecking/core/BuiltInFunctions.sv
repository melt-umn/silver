grammar silver:compiler:analysis:typechecking:core;

aspect production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  propagate upSubst, downSubst;
}

aspect production toBooleanFunction
top::Expr ::= 'toBoolean' '(' e1::Expr ')'
{
  top.errors <-
    if performSubstitution(e1.typerep, top.finalSubst).instanceConvertible
    then []
    else [err(top.location, "Operand to toBoolean must be concrete types String, Integer, Float, or Boolean.  Instead it is of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production toIntegerFunction
top::Expr ::= 'toInteger' '(' e1::Expr ')'
{
  top.errors <-
    if performSubstitution(e1.typerep, top.finalSubst).instanceConvertible
    then []
    else [err(top.location, "Operand to toInteger must be concrete types String, Integer, Float, or Boolean.  Instead it is of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e1::Expr ')'
{
  
  top.errors <-
    if performSubstitution(e1.typerep, top.finalSubst).instanceConvertible
    then []
    else [err(top.location, "Operand to toFloat must be concrete types String, Integer, Float, or Boolean.  Instead it is of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production toStringFunction
top::Expr ::= 'toString' '(' e1::Expr ')'
{
  top.errors <-
    if performSubstitution(e1.typerep, top.finalSubst).instanceConvertible
    then []
    else [err(top.location, "Operand to toString must be concrete types String, Integer, Float, or Boolean.  Instead it is of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

function containsSkolem
Boolean ::= ty::Type
{
  return case ty of
         | skolemType(_) -> true
         | appType(c, a) -> containsSkolem(c) || containsSkolem(a)
         | decoratedType(ty) -> containsSkolem(ty)
         | _ -> false
         end;
}

aspect production newFunction
top::Expr ::= 'new' '(' e1::Expr ')'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, e1, errCheck1, top;
  
  errCheck1 = checkDecorated(e1.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Operand to new must be a decorated nonterminal.  Instead it is of type " ++ errCheck1.leftpp)]
    else [];
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, es, el, errCheck1, errCheck2, top;
  
  errCheck1 = check(es.typerep, stringType());
  errCheck2 = check(el.typerep, nonterminalType("silver:core:Location", 0, false));
  top.errors <-
    if errCheck1.typeerror
    then [err(es.location, "Second operand to 'terminal(type,lexeme,location)' must be a String, instead it is " ++ errCheck1.leftpp)]
    else [];

  top.errors <-
    if errCheck2.typeerror
    then [err(el.location, "Third operand to 'terminal(type,lexeme,location)' must be a Location, instead it is " ++ errCheck2.leftpp)]
    else [];
  
  top.errors <-
    if t.typerep.isTerminal || t.typerep.isError
    then []
    else [err(t.location, "First operand to 'terminal(type,lexeme,location)' must be a Terminal type, instead it is " ++ prettyType(t.typerep))];
}

