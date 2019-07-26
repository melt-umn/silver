grammar silver:extension:implicit_monads;

attribute monadRewritten<ProductionBody>, merrors occurs on ProductionBody;
attribute monadRewritten<ProductionStmts>, merrors occurs on ProductionStmts;
attribute monadRewritten<ProductionStmt>, merrors occurs on ProductionStmt;


attribute monadRewritten<Expr>, merrors, mtyperep occurs on Expr;
aspect default production
top::Expr ::=
{
  top.merrors := [];
  top.mtyperep = errorType();
  top.monadRewritten = top;
}



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
  top.monadRewritten = top;
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
  top.monadRewritten = top;
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
  top.monadRewritten = top;
}

aspect production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::TypeExpr ';'
{
  top.merrors := [];
  top.monadRewritten = top;
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
  top.monadRewritten = top;
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
  {-
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes = [];
  top.defs = [];
  
  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;
  
  local problems :: [Message] =
    if attr.found && attr.attrDcl.isAnnotation
    then [err(attr.location, attr.name ++ " is an annotation, which are supplied to productions as arguments, not defined as equations.")]
    else dl.errors ++ attr.errors;

  forwards to
    -- oddly enough we may have no errors and need to forward to error production:
    -- consider "production foo  top::DoesNotExist ::= { top.errors = ...; }"
    -- where top is a valid reference to a type that is an error type
    -- so there is an error elsewhere
    if !dl.found || !attr.found || !null(problems)
    then errorAttributeDef(problems, dl, attr, e, location=top.location)
    else attr.attrDcl.attrDefDispatcher(dl, attr, e, top.location);
  -}
  e.downSubst = top.downSubst;
  top.merrors := e.merrors;

  top.monadRewritten = if isMonad(attr.typerep)
                       then if isMonad(e.mtyperep) || isError(e.mtyperep)
                            then attributeDef(dl, '.', attr, '=', e.monadRewritten, ';', location=top.location)
                            else attributeDef(dl, '.', attr, '=',
                                   Silver_Expr {
                                     $Expr {monadReturn(attr.typerep, top.location)}
                                      ($Expr {e.monadRewritten})
                                   }, ';', location=top.location)
                       else attributeDef(dl, '.', attr, '=', e.monadRewritten, ';', location=top.location);
}

aspect production errorAttributeDef
top::ProductionStmt ::= msg::[Message] dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.merrors := msg;
  top.monadRewritten = top;
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

  top.monadRewritten = if isMonad(val.lookupValue.typerep)
                       then if isMonad(e.mtyperep) || isError(e.mtyperep)
                            then localValueDef(val, e.monadRewritten, location=top.location)
                            else localValueDef(val,
                                   Silver_Expr {
                                     $Expr {monadReturn(val.lookupValue.typerep, top.location)}
                                      ($Expr {e.monadRewritten})
                                   }, location=top.location)
                       else localValueDef(val, e.monadRewritten, location=top.location);
}

