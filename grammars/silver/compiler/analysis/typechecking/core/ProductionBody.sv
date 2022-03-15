grammar silver:compiler:analysis:typechecking:core;


attribute upSubst, downSubst, finalSubst occurs on ProductionStmt, ForwardInhs, ForwardInh, ForwardLHSExpr;
propagate upSubst, downSubst on ProductionStmt, ForwardInhs, ForwardInh, ForwardLHSExpr
  excluding productionStmtAppend, attachNoteStmt, forwardsTo, forwardInh, undecoratesTo, returnDef, synthesizedAttributeDef, inheritedAttributeDef, localValueDef;

{--
 - These need an initial state only due to aspects (I think? maybe not. Investigate someday.)
 - They otherwise confine their contexts to each individual Stmt.
 -}
attribute downSubst occurs on ProductionBody, ProductionStmts;
-- downSubst is NOT propagated here - we give ever stmt the same downSubst, rather than threading like usual

aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  stmts.downSubst = top.downSubst;
}

aspect production productionStmtsNil
top::ProductionStmts ::= 
{
}

aspect production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmt
{
  h.downSubst = top.downSubst;

  t.downSubst = top.downSubst;
  t.finalSubst = t.upSubst;
}

aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  -- We treat this as though each is independent here as well.
  h.downSubst = top.downSubst;
  h.finalSubst = h.upSubst;

  t.downSubst = top.downSubst;
  t.finalSubst = t.upSubst;
  
  top.upSubst = error("Shouldn't ever be needed anywhere. (Should only ever be fed back here as top.finalSubst)");
  -- Of course, this means do not use top.finalSubst here!
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  
  thread downSubst, upSubst on top, e, errCheck1, top;
  
  errCheck1 = check(e.typerep, top.frame.signature.outputElement.typerep);
  top.errors <- if errCheck1.typeerror
                then [err(e.location, "Forward's expected type is " ++ errCheck1.rightpp ++ ", but the actual type supplied is " ++ errCheck1.leftpp)]
                else [];
}

aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, lhs, e, errCheck1, top;
  
  errCheck1 = check(lhs.typerep, e.typerep);
  top.errors <- 
       if errCheck1.typeerror
       then [err(e.location, lhs.name ++ " has expected type " ++ errCheck1.leftpp
                              ++ ", but the expression has type " ++ errCheck1.rightpp)]
       else [];
}

aspect production undecoratesTo
top::ProductionStmt ::= 'undecorates' 'to' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  
  thread downSubst, upSubst on top, e, errCheck1, top;
  
  errCheck1 = check(e.typerep, top.frame.signature.outputElement.typerep);
  top.errors <- if errCheck1.typeerror
                then [err(e.location, "Undecorates's expected type is " ++ errCheck1.rightpp ++ ", but the actual type supplied is " ++ errCheck1.leftpp)]
                else [];
}

aspect production attachNoteStmt
top::ProductionStmt ::= 'attachNote' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, e, errCheck1, top;
  
  errCheck1 = check(e.typerep, nonterminalType("silver:core:OriginNote", [], false));
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Origin note must have type silver:core:OriginNote, but the expression has actual type " ++ errCheck1.leftpp)]
       else [];
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  
  thread downSubst, upSubst on top, e, errCheck1, top;
  
  errCheck1 = check(e.typerep, top.frame.signature.outputElement.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Expected return type is " ++ errCheck1.rightpp ++ ", but the expression has actual type " ++ errCheck1.leftpp)]
       else [];
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, e, errCheck1, top;

  errCheck1 = check(attr.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
    else [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, e, errCheck1, top;

  errCheck1 = check(attr.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
    else [];
}

aspect production errorAttributeDef
top::ProductionStmt ::= msg::[Message] dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  propagate downSubst, upSubst;
}

aspect production childDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
  top.errors <- if isDecorable(top.typerep, top.env) then []
                else [err(top.location, s"Inherited attributes can only be defined on (undecorated) nonterminal and partially decorated types, not ${prettyType(top.typerep)}.")];
}

aspect production localDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
  top.errors <- if isDecorable(top.typerep, top.env) then []
                else [err(top.location, s"Inherited attributes can only be defined on (undecorated) nonterminal and partially decorated types, not ${prettyType(top.typerep)}.")];
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::TypeExpr ';'
{
  top.errors <- te.errorsKindStar;
}

aspect production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::TypeExpr ';'
{
  top.errors <- te.errorsKindStar;
}

aspect production localValueDef
top::ProductionStmt ::= val::PartiallyDecorated QName  e::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, e, errCheck1, top;

  errCheck1 = check(e.typerep, val.lookupValue.typeScheme.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Local " ++ val.name ++ " has type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
       else [];
}
