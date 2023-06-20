grammar silver:compiler:analysis:typechecking:core;

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.errors <- te.errorsKindStar;
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.errors <- te.errorsKindStar;
}

aspect production attributeDclInhTrans
top::AGDcl ::= 'inherited' 'translation' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.errors <- te.errorsKindStar;

  local checkNT::TypeCheck = checkNonterminal(top.env, false, te.typerep);
  checkNT.downSubst = emptySubst();
  checkNT.finalSubst = emptySubst();
  
  top.errors <-
    if checkNT.typeerror
    then [err(top.location, "Translation attribute type must be a nonterminal.  Instead it is of type " ++ checkNT.leftpp)]
    else [];
}

aspect production attributeDclSynTrans
top::AGDcl ::= 'synthesized' 'translation' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.errors <- te.errorsKindStar;

  local checkNT::TypeCheck = checkNonterminal(top.env, false, te.typerep);
  checkNT.downSubst = emptySubst();
  checkNT.finalSubst = emptySubst();
  
  top.errors <-
    if checkNT.typeerror
    then [err(top.location, "Translation attribute type must be a nonterminal.  Instead it is of type " ++ checkNT.leftpp)]
    else [];
}

