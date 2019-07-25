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

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.merrors := e.merrors;

  top.monadRewritten = top; local foo::ProductionStmt = if isMonad(attr.typerep)
                       then if isMonad(e.mtyperep)
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

  top.monadRewritten = top; local foo::ProductionStmt = if isMonad(attr.typerep)
                       then if isMonad(e.mtyperep)
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

  top.monadRewritten = top; local foo::ProductionStmt = if isMonad(val.lookupValue.typerep)
                       then if isMonad(e.mtyperep)
                            then localValueDef(val, e.monadRewritten, location=top.location)
                            else localValueDef(val,
                                   Silver_Expr {
                                     $Expr {monadReturn(val.lookupValue.typerep, top.location)}
                                      ($Expr {e.monadRewritten})
                                   }, location=top.location)
                       else localValueDef(val, e.monadRewritten, location=top.location);
}

