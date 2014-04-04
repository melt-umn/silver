grammar silver:analysis:typechecking:core;

attribute upSubst, downSubst, finalSubst occurs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoExpr, AnnoAppExprs;

aspect production errorReference
top::Expr ::= msg::[Message]  q::Decorated QName
{
  top.upSubst = top.downSubst;
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  top.upSubst = top.downSubst;
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.upSubst = top.downSubst;
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
  top.upSubst = top.downSubst;
}

aspect production productionReference
top::Expr ::= q::Decorated QName
{
  top.upSubst = top.downSubst;
}

aspect production functionReference
top::Expr ::= q::Decorated QName
{
  top.upSubst = top.downSubst;
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.upSubst = top.downSubst;
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.upSubst = top.downSubst;
}

aspect production application
top::Expr ::= e::Expr '(' es::AppExprs ',' anns::AnnoAppExprs ')'
{
  e.downSubst = top.downSubst;
  forward.downSubst = e.upSubst;
  
  --es.downSubst = top.downSubst; -- TODO REMOVE THIS (it's garbage related to bugs in pretty printing, afaict)
}

aspect production functionApplication
top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs
{
  es.downSubst = top.downSubst;
  anns.downSubst = es.upSubst;
  forward.downSubst = anns.upSubst;
}

aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.upSubst = top.downSubst;
}

aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.upSubst = top.downSubst;
}

aspect production errorApplication
top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs
{
  es.downSubst = top.downSubst;
  anns.downSubst = es.upSubst;
  top.upSubst = anns.upSubst;
}

aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.upSubst = top.downSubst;
}

aspect production access
top::Expr ::= e::Expr '.' q::QNameAttrOccur
{
  e.downSubst = top.downSubst;
  forward.downSubst = e.upSubst;
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.upSubst = top.downSubst;
}

aspect production undecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  -- We might have gotten here via a 'ntOrDec' type. So let's make certain we're UNdecorated,
  -- ensuring that type's specialization, otherwise we could end up in trouble!
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  errCheck1 = checkNonterminal(e.typerep);

  -- TECHNICALLY, I think the current implementation makes this impossible,
  -- But let's leave it since it's the right thing to do.
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Access of " ++ q.name ++ " from a decorated type.")]
    else [];
  
  errCheck1.downSubst = top.downSubst;
  top.upSubst = errCheck1.upSubst;
}

aspect production accessBouncer
top::Expr ::= target::(Expr ::= Decorated Expr  Decorated QNameAttrOccur  Location) e::Expr  q::Decorated QNameAttrOccur
{
  e.downSubst = top.downSubst;
  forward.downSubst = e.upSubst;
}

aspect production decoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  -- We might have gotten here via a 'ntOrDec' type. So let's make certain we're decorated,
  -- ensuring that type's specialization, otherwise we could end up in trouble!
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  errCheck1 = checkDecorated(e.typerep);

  -- TECHNICALLY, I think the current implementation makes this impossible,
  -- But let's leave it since it's the right thing to do.
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ q.name ++ " being accessed from an undecorated type.")]
    else [];
  
  errCheck1.downSubst = top.downSubst;
  top.upSubst = errCheck1.upSubst;
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.upSubst = top.downSubst;
}
aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.upSubst = top.downSubst;
}
aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.upSubst = top.downSubst;
}


aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.upSubst = top.downSubst;
}
aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.upSubst = top.downSubst;
}



aspect production trueConst
top::Expr ::= 'true'
{
  top.upSubst = top.downSubst;
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.upSubst = top.downSubst;
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  errCheck1 = check(e1.typerep, boolTypeExp());
  errCheck2 = check(e2.typerep, boolTypeExp());
  top.errors <-
       if errCheck1.typeerror
       then [err(e1.location, "First operand to && must be type bool. Got instead type " ++ errCheck1.leftpp)]
       else [];
  top.errors <-
       if errCheck2.typeerror
       then [err(e2.location, "First operand to && must be type bool. Got instead type " ++ errCheck2.leftpp)]
       else [];
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  errCheck1 = check(e1.typerep, boolTypeExp());
  errCheck2 = check(e2.typerep, boolTypeExp());
  top.errors <-
       if errCheck1.typeerror
       then [err(e1.location, "First operand to || must be type bool. Got instead type " ++ errCheck1.leftpp)]
       else [];
  top.errors <-
       if errCheck2.typeerror
       then [err(e2.location, "First operand to || must be type bool. Got instead type " ++ errCheck2.leftpp)]
       else [];
}

aspect production not
top::Expr ::= '!' e1::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  errCheck1.downSubst = e1.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, boolTypeExp());
  top.errors <-
       if errCheck1.typeerror
       then [err(e1.location, "Operand to ! must be type bool. Got instead type " ++ errCheck1.leftpp)]
       else [];
}

aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to > must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
       then []
       else [err(top.location, "Operands to > must be concrete types Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to < must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
       then []
       else [err(top.location, "Operands to < must be concrete types Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}


aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to >= must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
       then []
       else [err(top.location, "Operands to >= must be concrete types Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}


aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to <= must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
       then []
       else [err(top.location, "Operands to <= must be concrete types Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}


aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to == must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceEq
       then []
       else [err(top.location, "Operands to == must be concrete types Boolean, Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}


aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to != must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceEq
       then []
       else [err(top.location, "Operands to != must be concrete types Boolean, Integer, Float, or String.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}


aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  e3.downSubst = e2.upSubst;
  errCheck1.downSubst = e3.upSubst;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  errCheck1 = check(e2.typerep, e3.typerep);
  errCheck2 = check(e1.typerep, boolTypeExp());
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Then and else branch must have the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if errCheck2.typeerror
       then [err(e1.location, "Condition must have the type Boolean. Instead it is " ++ errCheck2.leftpp)]
       else [];
}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.upSubst = top.downSubst;
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.upSubst = top.downSubst;
} 

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to + must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceNum
       then []
       else [err(top.location, "Operands to + must be concrete types Integer or Float.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to - must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceNum
       then []
       else [err(top.location, "Operands to - must be concrete types Integer or Float.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to * must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceNum
       then []
       else [err(top.location, "Operands to * must be concrete types Integer or Float.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to / must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceNum
       then []
       else [err(top.location, "Operands to / must be concrete types Integer or Float.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}
aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to % must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];

  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceNum
       then []
       else [err(top.location, "Operands to % must be concrete types Integer or Float.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}
aspect production neg
top::Expr ::= '-' e1::Expr
{
  e1.downSubst = top.downSubst;
  top.upSubst = e1.upSubst;
  
  top.errors <-
       if performSubstitution(e1.typerep, top.finalSubst).instanceNum
       then []
       else [err(top.location, "Operand to unary - must be concrete types Integer or Float.  Instead it is of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.upSubst = top.downSubst;
}

aspect production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
  production attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  forward.downSubst = errCheck1.upSubst;
  -- upSubst defined via forward :D
  
  errCheck1 = check(e1.typerep, e2.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to ++ must be the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
}

aspect production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.upSubst = top.downSubst;
}

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.upSubst = top.downSubst;
}


aspect production exprsEmpty
top::Exprs ::=
{
  top.upSubst = top.downSubst;
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  top.upSubst = e2.upSubst;
}

aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  inh.downSubst = errCheck1.upSubst;
  top.upSubst = inh.upSubst;

  errCheck1 = checkNonterminal(e.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operand to decorate must be a nonterminal.  Instead it is of type " ++ errCheck1.leftpp)]
       else [];
}

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e1::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  errCheck1.downSubst = e1.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(lhs.typerep, e1.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, lhs.pp ++ " has expected type " ++ errCheck1.leftpp
                              ++ ", but the expression has type " ++ errCheck1.rightpp)]
       else [];
}

aspect production exprInhsEmpty
top::ExprInhs ::= 
{
  top.upSubst = top.downSubst;
}

aspect production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  lhs.downSubst = top.downSubst;
  top.upSubst = lhs.upSubst;
}

aspect production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  lhs.downSubst = top.downSubst;
  inh.downSubst = lhs.upSubst;
  top.upSubst = inh.upSubst;
}

aspect production missingAppExpr
top::AppExpr ::= '_'
{
  top.upSubst = top.downSubst;
}
aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e.typerep, top.appExprTyperep);
  top.errors <-
    if !errCheck1.typeerror then []
    else [err(top.location, "Argument " ++ toString(top.appExprIndex+1) ++ " of function '" ++
            top.appExprApplied ++ "' expected " ++ errCheck1.rightpp ++
            " but argument is of type " ++ errCheck1.leftpp)];  
}
aspect production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  es.downSubst = top.downSubst;
  e.downSubst = es.upSubst;
  top.upSubst = e.upSubst;
}
aspect production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}
aspect production emptyAppExprs
top::AppExprs ::=
{
  top.upSubst = top.downSubst;
}

aspect production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}
aspect production snocAnnoAppExprs
top::AnnoAppExprs ::= es::AnnoAppExprs ',' e::AnnoExpr
{
  es.downSubst = top.downSubst;
  e.downSubst = es.upSubst;
  top.upSubst = e.upSubst;
}
aspect production oneAnnoAppExprs
top::AnnoAppExprs ::= e::AnnoExpr
{
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}
aspect production emptyAnnoAppExprs
top::AnnoAppExprs ::=
{
  top.upSubst = top.downSubst;
}


aspect production exprRef
top::Expr ::= e::Decorated Expr
{
  -- See documentation for major restriction on use of exprRef.
  -- Essentially, the referred expression MUST have already been type checked.
  top.upSubst = top.downSubst;
}

