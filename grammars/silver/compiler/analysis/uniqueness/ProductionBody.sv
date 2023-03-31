grammar silver:compiler:analysis:uniqueness;

attribute uniqueRefs occurs on ProductionBody, ProductionStmts, ProductionStmt;
propagate uniqueRefs on ProductionBody, ProductionStmts, ProductionStmt;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.errors <-
    if any(map((.isUniqueDecorated), namedSig.inputTypes)) && null(body.undecorateExpr)
    then [err(top.location, s"Production '${id.name}' has a unique reference in its signature but no 'undecorates to'.")]
    else [];
}

aspect production attachNoteStmt
top::ProductionStmt ::= 'attachNote' note::Expr ';'
{
  top.errors <- uniqueContextErrors(note.uniqueRefs);
  note.exprDecSite = nothing();
}
aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.errors <-
    if any(map((.isUniqueDecorated), top.frame.signature.inputTypes)) then []
    else map(\ r::(String, UniqueRefSite) -> err(r.2.sourceLocation,
        s"Unique reference to ${r.1} taken outside of a unique context. " ++
        s"The return of ${top.frame.fullName} is not a unique context as this function has no unique parameters."),
      e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  e.exprDecSite = just(forwardDecSite());
}
aspect production undecoratesTo
top::ProductionStmt ::= 'undecorates' 'to' e::Expr ';'
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production errorValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  e.exprDecSite = nothing();
}
aspect production localValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  e.exprDecSite =
    if isDecorable(finalType(e), top.env)
    then just(localDecSite(val.lookupValue.fullName))
    else nothing();
}

-- Modifications
aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  {- <- -} e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  {- <- -} e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production pluckDef
top::ProductionStmt ::= 'pluck' e::Expr ';'
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production printStmt
top::ProductionStmt ::= 'print' e::Expr ';'
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production parserAttributeValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}
aspect production pushTokenStmt
top::ProductionStmt ::= 'pushToken' '(' val::QName ',' lexeme::Expr ')' ';'
{
  top.errors <- uniqueContextErrors(lexeme.uniqueRefs);
  lexeme.exprDecSite = nothing();
}
aspect production insertSemanticTokenStmt
top::ProductionStmt ::= 'insert' 'semantic' 'token' n::QNameType 'at' loc::Expr ';'
{
  top.errors <- uniqueContextErrors(loc.uniqueRefs);
  loc.exprDecSite = nothing();
}
aspect production ifElseStmt
top::ProductionStmt ::= 'if' '(' condition::Expr ')' th::ProductionStmt 'else' el::ProductionStmt
{
  top.errors <- uniqueContextErrors(condition.uniqueRefs);
  condition.exprDecSite = nothing();
}
aspect production termAttrValueValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
  e.exprDecSite = nothing();
}

synthesized attribute refSiteName::String occurs on DefLHS;
aspect refSiteName on top::DefLHS of
| childDefLHS(q) -> top.frame.fullName ++ ":" ++ q.lookupValue.fullName
| localDefLHS(q) -> q.lookupValue.fullName
-- These aren't used by the analysis, but doesn't hurt to include them:
| lhsDefLHS(q) -> top.frame.fullName ++ ":" ++ q.lookupValue.fullName
| forwardDefLHS(q) -> top.frame.fullName ++ ":forward"
| defaultLhsDefLHS(q) -> top.frame.fullName ++ ":" ++ q.lookupValue.fullName
| parserAttributeDefLHS(q) -> top.frame.fullName ++ ":" ++ q.lookupValue.fullName
| errorDefLHS(q) -> top.frame.fullName ++ ":" ++ q.name
end;
