grammar silver:extension:implicit_monads;

attribute monadRewritten<ProductionBody>, merrors, mDownSubst occurs on ProductionBody;
attribute monadRewritten<ProductionStmts>, merrors, mDownSubst occurs on ProductionStmts;
attribute monadRewritten<ProductionStmt>, merrors, mDownSubst occurs on ProductionStmt;



aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  top.merrors := stmts.merrors;

  top.monadRewritten = productionBody('{', stmts.monadRewritten, '}', location=top.location);
}


aspect default production
top::ProductionStmts ::=
{
  top.merrors := [];
  top.monadRewritten = error("monadRewritten must be defined on all ProductionStmts");
}

aspect production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmt
{
  top.merrors := h.merrors ++ t.merrors;
  top.monadRewritten = productionStmtsSnoc(h.monadRewritten, t.monadRewritten, location=top.location);
}

----------

aspect default production
top::ProductionStmt ::=
{
  top.merrors := [];
  top.monadRewritten = error("monadRewritten must be defined on all ProductionStmt");
}

aspect production productionStmtsNil
top::ProductionStmts ::= 
{
  top.merrors := [];
  top.monadRewritten = productionStmtsNil(location=top.location);
}

aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  top.merrors := h.merrors ++ t.merrors;
  top.monadRewritten = productionStmtAppend(h.monadRewritten, t.monadRewritten, location=top.location);
}

--------------------------------------------------------------------------------

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.merrors := e.merrors;
  top.monadRewritten = returnDef('return', e.monadRewritten, ';', location=top.location);
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::TypeExpr ';'
{
  top.merrors := [];
  top.monadRewritten = localAttributeDcl('local', 'attribute', a, '::', te, ';', location=top.location);
}

aspect production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::TypeExpr ';'
{
  top.merrors := [];
  top.monadRewritten = productionAttributeDcl('production', 'attribute', a, '::', te, ';', location=top.location);
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.merrors := e.merrors;
  top.monadRewritten = forwardsTo('forwards', 'to', e.monadRewritten, ';', location=top.location);
}

-- explicitly leaving out forwardsToWith here because it should be fine for forwarding here

--- TODO This should walk through inh and do monad rewriting, maybe?
aspect production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.merrors := [];
  top.monadRewritten = forwardingWith('forwarding', 'with', '{', inh, '}', ';', location=top.location);
}

-- TODO This one should probably have the actual monadRewritten like
{-aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  top.merrors := e.merrors;
  top.monadRewritten = forwardInh(lhs, '=', e.monadRewritten, top.location);
}

aspect production forwardInhsOne
top::ForwardInhs ::= lhs::ForwardInh
{
  top.merrors := [];
  top.monadRewritten = top;
}

aspect production forwardInhsCons
top::ForwardInhs ::= lhs::ForwardInh rhs::ForwardInhs
{
  top.merrors := [];
  top.monadRewritten = top;
}

aspect production forwardLhsExpr
top::ForwardLHSExpr ::= q::QNameAttrOccur
{
  top.merrors := [];
  top.monadRewritten = top;
}-}

aspect production attributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  e.downSubst = top.downSubst;
  top.merrors := e.merrors;

  local emr::Expr = e.monadRewritten;
  emr.env = top.env; emr.frame = top.frame; emr.grammarName = top.grammarName;
  emr.config = top.config; emr.compiledGrammars = top.compiledGrammars;
  emr.downSubst = top.downSubst;
  --top.monadRewritten =
  local mr::ProductionStmt = if isMonad(attr.typerep)
                       then if isMonad(e.mtyperep) || isError(e.mtyperep)
                            then attributeDef(dl, '.', attr, '=', e.monadRewritten, ';', location=top.location)
                            else attributeDef(dl, '.', attr, '=',
                                   Silver_Expr {
                                     $Expr {monadReturn(attr.typerep, top.location)}
                                      ($Expr {e.monadRewritten})
                                   }, ';', location=top.location)
                       else attributeDef(dl, '.', attr, '=', e.monadRewritten, ';', location=top.location);
  top.monadRewritten = mr; --unsafeTrace(mr, print("\n\n\n" ++ prettyType(attr.typerep) ++ "; " ++
                           --          prettyType(performSubstitution(e.mtyperep, emr.upSubst)) ++ "; " ++
                           --          prettyType(performSubstitution(emr.typerep, emr.upSubst)) ++ "\n\n" ++
                           --          mr.unparse ++ "\n\n\n", unsafeIO()));
}

aspect production errorAttributeDef
top::ProductionStmt ::= msg::[Message] dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.merrors := msg;
  top.monadRewritten = errorAttributeDef(msg, dl, attr, e, location=top.location);
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.merrors := e.merrors;

  top.monadRewritten = if isMonad(attr.typerep)
                       then if isMonad(e.mtyperep) || isError(e.mtyperep)
                            then synthesizedAttributeDef(dl, attr, e.monadRewritten, location=top.location)
                            else synthesizedAttributeDef(dl, attr,
                                   Silver_Expr {
                                     $Expr {monadReturn(attr.typerep, top.location)}
                                      ($Expr {e.monadRewritten})
                                   }, location=top.location)
                       else synthesizedAttributeDef(dl, attr, e.monadRewritten, location=top.location);
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.merrors := e.merrors;

  top.monadRewritten = if isMonad(attr.typerep)
                       then if isMonad(e.mtyperep) || isError(e.mtyperep)
                            then inheritedAttributeDef(dl, attr, e.monadRewritten, location=top.location)
                            else inheritedAttributeDef(dl, attr,
                                   Silver_Expr {
                                     $Expr {monadReturn(attr.typerep, top.location)}
                                      ($Expr {e.monadRewritten})
                                   }, location=top.location)
                       else inheritedAttributeDef(dl, attr, e.monadRewritten, location=top.location);
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  -- val is already valid here
  top.merrors := e.merrors;

  --top.monadRewritten =
  local mr::ProductionStmt = if isMonad(val.lookupValue.typerep)
                       then if isMonad(e.mtyperep) || isError(e.mtyperep)
                            then localValueDef(val, e.monadRewritten, location=top.location)
                            else localValueDef(val,
                                   Silver_Expr {
                                     $Expr {monadReturn(val.lookupValue.typerep, top.location)}
                                      ($Expr {e.monadRewritten})
                                   }, location=top.location)
                       else localValueDef(val, e.monadRewritten, location=top.location);
  top.monadRewritten = mr; --unsafeTrace(mr, print("\n\n\n" ++ mr.unparse ++ "\n\n\n", unsafeIO()));
}

