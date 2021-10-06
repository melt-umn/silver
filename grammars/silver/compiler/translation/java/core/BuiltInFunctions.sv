grammar silver:compiler:translation:java:core;

aspect production errorLength
top::Expr ::= e::Decorated Expr
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.translation = s"Integer.valueOf(((common.StringCatter)${e.translation}).length())";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  top.translation = s"new ${makeTerminalName(t.typerep.typeName)}(${es.translation}, (silver.core.NLocation)${el.translation})";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
