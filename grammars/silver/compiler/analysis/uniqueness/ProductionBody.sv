grammar silver:compiler:analysis:uniqueness;

attribute uniqueRefs occurs on ProductionBody, ProductionStmts, ProductionStmt;
propagate uniqueRefs on ProductionBody, ProductionStmts, ProductionStmt;

aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  top.errors <-
    if top.frame.permitForward && any(map((.isUniqueDecorated), top.frame.signature.inputTypes)) && null(stmts.undecorateExpr)
    then [err(top.location, "Production has unique reference in its signature but no 'undecorates to'.")]
    else [];
}

aspect production attachNoteStmt
top::ProductionStmt ::= 'attachNote' note::Expr ';'
{
  top.errors <- uniqueContextErrors(note.uniqueRefs);
}
aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
}
aspect production undecoratesTo
top::ProductionStmt ::= 'undecorates' 'to' e::Expr ';'
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
}
aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
}
aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
}
aspect production parserAttributeValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
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
