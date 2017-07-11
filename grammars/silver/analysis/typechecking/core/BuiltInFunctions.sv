grammar silver:analysis:typechecking:core;

aspect production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  e.downSubst = top.downSubst;
  forward.downSubst= e.upSubst;
}
aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.upSubst = top.downSubst;
}
aspect production errorLength
top::Expr ::= e::Decorated Expr
{
  top.upSubst = top.downSubst;
}

aspect production toIntFunction
top::Expr ::= 'toInt' '(' e1::Expr ')'
{
  e1.downSubst = top.downSubst;
  top.upSubst = e1.upSubst;
  
  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceConvertible
       then []
       else [err(top.location, "Operand to toInt must be concrete types String, Integer, or Float.  Instead it is of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e1::Expr ')'
{
  e1.downSubst = top.downSubst;
  top.upSubst = e1.upSubst;
  
  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceConvertible
       then []
       else [err(top.location, "Operand to toFloat must be concrete types String, Integer, or Float.  Instead it is of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production toStringFunction
top::Expr ::= 'toString' '(' e1::Expr ')'
{
  e1.downSubst = top.downSubst;
  top.upSubst = e1.upSubst;
  
  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceConvertible
       then []
       else [err(top.location, "Operand to toString must be concrete types String, Integer, or Float.  Instead it is of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production newFunction
top::Expr ::= 'new' '(' e1::Expr ')'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  errCheck1.downSubst = e1.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = checkDecorated(e1.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operand to new must be a decorated nonterminal.  Instead it is of type " ++ errCheck1.leftpp)]
       else [];
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::Type ',' es::Expr ',' el::Expr ')'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  es.downSubst = top.downSubst;
  el.downSubst = es.upSubst;
  errCheck1.downSubst = el.upSubst;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  errCheck1 = check(es.typerep, stringTypeExp());
  errCheck2 = check(el.typerep, nonterminalTypeExp("core:Location", []));
  top.errors <-
       if errCheck1.typeerror
       then [err(es.location, "Second operand to 'terminal(type,lexeme,location)' must be a String, instead it is " ++ errCheck1.leftpp)]
       else [];
  top.errors <-
       if errCheck2.typeerror
       then [err(el.location, "Third operand to 'terminal(type,lexeme,location)' must be a Location, instead it is " ++ errCheck2.leftpp)]
       else [];
  
  top.errors <-
        if (t.typerep.isTerminal) 
        then []
        else [err(t.location, "First operand to 'terminal(type,lexeme,location)' must be a Terminal, instead it is " ++ prettyType(t.typerep))];
}

