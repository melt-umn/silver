grammar silver:analysis:typechecking:core;

attribute typeErrors, upSubst, downSubst, finalSubst occurs on Expr, ForwardInhs, ForwardInh, ForwardLHSExpr, ExprInhs, ExprInh, ExprLHSExpr, Exprs;

aspect production nestedExpr
top::Expr ::= '(' e::Expr ')'
{
  top.typeErrors := e.typeErrors;
  
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  top.typeErrors := [];
  
  top.upSubst = top.downSubst;
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.typeErrors := [];

  top.upSubst = top.downSubst;
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
  top.typeErrors := [];

  top.upSubst = top.downSubst;
}

aspect production productionReference
top::Expr ::= q::Decorated QName
{
  top.typeErrors := [];

  top.upSubst = top.downSubst;
}

aspect production functionReference
top::Expr ::= q::Decorated QName
{
  top.typeErrors := [];

  top.upSubst = top.downSubst;
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.typeErrors := [];

  top.upSubst = top.downSubst;
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.typeErrors := [];

  top.upSubst = top.downSubst;
}

aspect production productionApp
top::Expr ::= e::Expr '(' es::Exprs ')'
{
  e.downSubst = top.downSubst;
}

aspect production emptyProductionApp
top::Expr ::= e::Expr '(' ')'
{
  e.downSubst = top.downSubst;
}

aspect production productionApplicationDispatcher
top::Expr ::= e::Decorated Expr es::Exprs
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e.typeErrors ++ es.typeErrors;
  
  local attribute apparentTy :: TypeExp;
  apparentTy = productionTypeExp(e.typerep.outputType, getTypesExprs(es));
  
  -- initial dispatcher already set e
  es.downSubst = e.upSubst;
  errCheck1.downSubst = es.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e.typerep, apparentTy);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Production signature mismatch."
                         ++ "\nFunction type signature: " ++ errCheck1.leftpp
                         ++ "\nParameters provided for: " ++ errCheck1.rightpp)]
       else [];
}

aspect production functionApplicationDispatcher
top::Expr ::= e::Decorated Expr es::Exprs
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e.typeErrors ++ es.typeErrors;
  
  local attribute apparentTy :: TypeExp;
  apparentTy = functionTypeExp(e.typerep.outputType, getTypesExprs(es));
  
  -- initial dispatcher already set e
  es.downSubst = e.upSubst;
  errCheck1.downSubst = es.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e.typerep, apparentTy);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Function signature mismatch."
                         ++ "\nFunction type signature: " ++ errCheck1.leftpp
                         ++ "\nParameters provided for: " ++ errCheck1.rightpp)]
       else [];
}

aspect production errorApplicationDispatcher
top::Expr ::= e::Decorated Expr es::Exprs
{
  top.typeErrors := e.typeErrors; -- errors already raised in errors. no additional type checking possible.

  -- initial dispatcher already set e
  es.downSubst = e.upSubst;
  top.upSubst = es.upSubst;
}

aspect production attributeAccess
top::Expr ::= e::Expr '.' q::QName
{
  e.downSubst = top.downSubst;
}

aspect production errorAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.typeErrors := e.typeErrors;
  
  top.upSubst = e.upSubst;
}

aspect production decoratedAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.typeErrors <- e.typeErrors;
  
  top.upSubst = e.upSubst;
}

aspect production synDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.typeErrors := [];
}
aspect production inhDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.typeErrors := [];
}
aspect production errorDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.typeErrors := [];
}


aspect production terminalAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  -- TODO: this is a hacky way of dealing with terminals
  top.typeErrors <-
        if q.name == "lexeme" || q.name == "filename" || q.name == "line" || q.name == "column"
        then []
        else [err(q.location, q.name ++ " is not a terminal attribute")];

  top.typeErrors := e.typeErrors;
  
  top.upSubst = e.upSubst;
}


aspect production trueConst
top::Expr ::= 'true'
{
  top.typeErrors := [];
    
  top.upSubst = top.downSubst;
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.typeErrors := [];
    
  top.upSubst = top.downSubst;
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  errCheck1 = check(e1.typerep, boolTypeExp());
  errCheck2 = check(e2.typerep, boolTypeExp());
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(e1.location, "First operand to && must be type bool. Got instead type " ++ errCheck1.leftpp)]
       else [];
  top.typeErrors <-
       if errCheck2.typeerror
       then [err(e2.location, "First operand to && must be type bool. Got instead type " ++ errCheck2.leftpp)]
       else [];
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  errCheck1 = check(e1.typerep, boolTypeExp());
  errCheck2 = check(e2.typerep, boolTypeExp());
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(e1.location, "First operand to || must be type bool. Got instead type " ++ errCheck1.leftpp)]
       else [];
  top.typeErrors <-
       if errCheck2.typeerror
       then [err(e2.location, "First operand to || must be type bool. Got instead type " ++ errCheck2.leftpp)]
       else [];
}

aspect production not
top::Expr ::= '!' e1::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors;
  
  e1.downSubst = top.downSubst;
  errCheck1.downSubst = e1.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, boolTypeExp());
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(e1.location, "Operand to ! must be type bool. Got instead type " ++ errCheck1.leftpp)]
       else [];
}

aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to > must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.typeErrors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
       then []
       else [err(top.location, "Operands to > must be concrete types Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to < must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.typeErrors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
       then []
       else [err(top.location, "Operands to < must be concrete types Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}


aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to >= must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.typeErrors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
       then []
       else [err(top.location, "Operands to >= must be concrete types Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}


aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to <= must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.typeErrors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
       then []
       else [err(top.location, "Operands to <= must be concrete types Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}


aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to == must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.typeErrors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceEq
       then []
       else [err(top.location, "Operands to == must be concrete types Boolean, Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}


aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to != must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.typeErrors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceEq
       then []
       else [err(top.location, "Operands to != must be concrete types Boolean, Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}


aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  e3.downSubst = e2.upSubst;
  errCheck1.downSubst = e3.upSubst;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  errCheck1 = check(e2.typerep, e3.typerep);
  errCheck2 = check(e1.typerep, boolTypeExp());
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Then and else branch must have the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.typeErrors <-
       if errCheck2.typeerror
       then [err(e1.location, "Condition must have the type Boolean. Instead it is " ++ errCheck1.leftpp)]
       else [];
}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.typeErrors := [];
  
  top.upSubst = top.downSubst;
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.typeErrors := [];
  
  top.upSubst = top.downSubst;
} 

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to + must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.typeErrors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceNum
       then []
       else [err(top.location, "Operands to + must be concrete types Integer or Float.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to - must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.typeErrors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceNum
       then []
       else [err(top.location, "Operands to - must be concrete types Integer or Float.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to * must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.typeErrors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceNum
       then []
       else [err(top.location, "Operands to * must be concrete types Integer or Float.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to / must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.typeErrors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceNum
       then []
       else [err(top.location, "Operands to / must be concrete types Integer or Float.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}
aspect production neg
top::Expr ::= '-' e1::Expr
{
  top.typeErrors := e1.typeErrors;
  
  e1.downSubst = top.downSubst;
  top.upSubst = e1.upSubst;
  
  top.typeErrors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceNum
       then []
       else [err(top.location, "Operand to unary - must be concrete types Integer or Float.  Instead it is of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.typeErrors := [];
  
  top.upSubst = top.downSubst;
}

aspect production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors <- e1.typeErrors ++ e2.typeErrors;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to ++ must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  
  top.typeErrors <-
       if null(handler) && !errCheck1.typeerror -- TODO This is a busted way to do this.
       then [err(top.location, "Operands to ++ must be concatenable.  Got instead types " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
       
}

aspect production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.typeErrors := [];
}

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.typeErrors := [];
}


aspect production exprsEmpty
top::Exprs ::=
{
  top.typeErrors := [];
  
  top.upSubst = top.downSubst;
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.typeErrors := e.typeErrors;
  
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
  
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  top.upSubst = e2.upSubst;
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.typeErrors := e.typeErrors ++ inh.typeErrors;

  e.downSubst = top.downSubst;
  inh.downSubst = e.upSubst;
  top.upSubst = inh.upSubst;

  top.typeErrors <-
       if (e.typerep.isDecorable)
       then []
       else [err(top.location, "Operand to 'decorate/with' must have a non-terminal type. Instead it is " ++ prettyType(e.typerep))] ;
}

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e1::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e1.typeErrors;

  e1.downSubst = top.downSubst;
  errCheck1.downSubst = e1.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(lhs.typerep, e1.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, lhs.pp ++ " has expected type " ++ errCheck1.leftpp
                              ++ ", but the expression has type " ++ errCheck1.rightpp)]
       else [];
}

aspect production exprInhsEmpty
top::ExprInhs ::= 
{
  top.typeErrors := [];
  
  top.upSubst = top.downSubst;
}

aspect production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.typeErrors := lhs.typeErrors;
  
  lhs.downSubst = top.downSubst;
  top.upSubst = lhs.upSubst;
}

aspect production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.typeErrors := lhs.typeErrors ++ inh.typeErrors;
  
  lhs.downSubst = top.downSubst;
  inh.downSubst = lhs.upSubst;
  top.upSubst = inh.upSubst;
}

