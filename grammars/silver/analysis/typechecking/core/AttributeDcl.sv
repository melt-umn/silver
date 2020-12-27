grammar silver:analysis:typechecking:core;

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.errors <-
    if te.typerep.kindArity > 0
    then [err(te.location, s"Type ${te.unparse} is not fully applied, it has kind arity ${toString(te.typerep.kindArity)}")]
    else [];
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.errors <-
    if te.typerep.kindArity > 0
    then [err(te.location, s"Type ${te.unparse} is not fully applied, it has kind arity ${toString(te.typerep.kindArity)}")]
    else [];
}

