grammar silver:analysis:typechecking:core;

attribute upSubst, downSubst, finalSubst occurs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoExpr, AnnoAppExprs;

aspect production errorExpr
top::Expr ::= e::[Message]
{
  top.upSubst = top.downSubst;
}

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
}

aspect production functionApplication
top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs
{
  es.downSubst = top.downSubst;
  anns.downSubst = es.upSubst;
  forward.downSubst = anns.upSubst;
  es.finalSubst = es.upSubst;
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

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  errCheck1 = checkDecorated(e.typerep);
  
  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute forward being accessed from an undecorated type.")]
    else [];
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
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep, boolType());
  local errCheck2::TypeCheck = check(if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep, boolType());

  top.errors <-
       if errCheck1.typeerror
       then [err(e1.location, "First operand to && must be type bool or Monad(bool).  Got instead type " ++ errCheck1.leftpp)]
       else [];
  top.errors <-
       if errCheck2.typeerror
       then [err(e2.location, "First operand to && must be type bool or Monad(bool).  Got instead type " ++ errCheck2.leftpp)]
       else [];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to && must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck2.leftpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck2.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep, boolType());
  local errCheck2::TypeCheck = check(if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep, boolType());

  top.errors <-
       if errCheck1.typeerror
       then [err(e1.location, "First operand to || must be type bool or Monad(bool).  Got instead type " ++ errCheck1.leftpp)]
       else [];
  top.errors <-
       if errCheck2.typeerror
       then [err(e2.location, "First operand to || must be type bool or Monad(bool).  Got instead type " ++ errCheck2.leftpp)]
       else [];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to || must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck2.leftpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck2.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;
}

aspect production not
top::Expr ::= '!' e::Expr
{
  e.downSubst = top.downSubst;

  local errCheck::TypeCheck = check(if isMonad(e.typerep)
                                    then monadInnerType(e.typerep)
                                    else e.typerep, boolType());

  top.errors <-
       if errCheck.typeerror
       then [err(e.location, "Operand to ! must be type bool or Monad(bool).  Got instead type " ++ errCheck.leftpp)]
       else [];

  errCheck.finalSubst = top.finalSubst;
  errCheck.downSubst = e.upSubst;
  top.upSubst = errCheck.upSubst;
}

aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to > must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to > must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to < must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to < must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to < must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to < must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;
}


aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to >= must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to >= must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to >= must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to >= must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;
}


aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to <= must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to <= must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to <= must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to <= must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;
}


aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to > must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to > must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;
}


aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to > must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to > must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;
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
  top.upSubst = pairMonadsMatch2.snd;
  local attribute pairMonadsMatch1::Pair<Boolean Substitution>;
  pairMonadsMatch1 = if isMonad(e1.typerep) && isMonad(e2.typerep)
                     then monadsMatch(e1.typerep, e2.typerep, errCheck2.upSubst)
                     else pair(true, errCheck2.upSubst);
  local attribute pairMonadsMatch2::Pair<Boolean Substitution>;
  pairMonadsMatch2 = if isMonad(e2.typerep) && isMonad(e3.typerep)
                     then monadsMatch(e2.typerep, e3.typerep, pairMonadsMatch1.snd)
                     else pair(true, pairMonadsMatch1.snd);
  local attribute pairMonadsMatch3::Pair<Boolean Substitution>;
  pairMonadsMatch3 = if isMonad(e1.typerep) && isMonad(e3.typerep)
                     then monadsMatch(e1.typerep, e3.typerep, pairMonadsMatch2.snd)
                     else pair(true, pairMonadsMatch2.snd);

  errCheck1 = check(if isMonad(e2.typerep)
                    then monadInnerType(e2.typerep)
                    else e2.typerep,
                    if isMonad(e3.typerep)
                    then monadInnerType(e3.typerep)
                    else e3.typerep);
  errCheck2 = check(if isMonad(e1.typerep)
                    then monadInnerType(e1.typerep)
                    else e1.typerep, boolType());
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Then and else branch must have the same type or have one be a monad of the type of the other. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if errCheck2.typeerror
       then [err(e1.location, "Condition must have the type Boolean or be a monad of Boolean. Instead it is " ++ errCheck2.leftpp)]
       else [];
  top.errors <-
       if pairMonadsMatch1.fst
       then []
       else [err(top.location, "Condition and then branch have different monad types, but they must be the same.  They are " ++ errCheck2.leftpp ++ " and " ++ errCheck1.leftpp)];
  top.errors <-
       if pairMonadsMatch2.fst
       then []
       else [err(top.location, "Then and else branches have different monad types, but they must be the same.  They are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];
  top.errors <-
       if pairMonadsMatch3.fst
       then []
       else [err(top.location, "Condition and else branch have different monad types, but they must be the same.  They are " ++ errCheck2.leftpp ++ " and " ++ errCheck1.rightpp)];
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
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to + must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to + must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to + must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to + must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;
}

aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to - must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to - must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to - must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to - must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to * must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to * must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to * must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to * must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to / must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to / must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to / must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to / must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;
}
aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to % must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to % must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to % must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to % must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;
}
aspect production neg
top::Expr ::= '-' e::Expr
{
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;

  top.errors <-
       if isMonad(e.typerep)
       then if performSubstitution(monadInnerType(e.typerep), top.finalSubst).instanceNum
            then [err(e.location, "Operand to unary - must be concrete types Integer or Float or a monad of these.  Got instead type " ++ prettyType(performSubstitution(monadInnerType(e.typerep), top.finalSubst)))]
            else []
       else if performSubstitution(e.typerep, top.finalSubst).instanceNum
            then [err(e.location, "Operand to unary - must be concrete types Integer or Float or a monad of these.  Got instead type " ++ prettyType(performSubstitution(e.typerep, top.finalSubst)))]
            else [];
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.upSubst = top.downSubst;
}

-- Already merged into silver:definition:core
--aspect production plusPlus

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
       then [err(top.location, lhs.name ++ " has expected type " ++ errCheck1.leftpp
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

