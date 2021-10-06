grammar silver:compiler:translation:java:core;

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  top.translation = s"new ${makeTerminalName(t.typerep.typeName)}(${es.translation}, (silver.core.NLocation)${el.translation})";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
