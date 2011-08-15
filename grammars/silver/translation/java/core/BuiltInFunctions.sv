grammar silver:translation:java:core;

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.translation = "Integer.valueOf(((common.StringCatter)" ++ e.translation ++ ").length())";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
  top.translation = case finalType(e) of
                      intTypeExp() -> e.translation
                    | floatTypeExp() -> "((Float)" ++ e.translation ++ ").intValue()"
                    | stringTypeExp() -> "Integer.valueOf(" ++ e.translation ++ ".toString())"
                    | t -> error("INTERNAL ERROR: no toInt translation for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}
aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.translation = case finalType(e) of
                      intTypeExp() -> "((Integer)" ++ e.translation ++ ").floatValue()"
                    | floatTypeExp() -> e.translation
                    | stringTypeExp() -> "Float.valueOf(" ++ e.translation ++ ".toString())"
                    | t -> error("INTERNAL ERROR: no toFloat translation for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}
aspect production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.translation = "(new common.StringCatter(String.valueOf(" ++ e.translation ++ ")))";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.translation = "((" ++ e.translation ++ ").undecorate())";
  
  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production terminalFunction
top::Expr ::= 'terminal' '(' t::Type ',' e::Expr ')'
{
  top.translation = "(new common.TerminalRecord(" ++ e.translation ++ ".toString(), -1, -1))";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.translation = "(new common.TerminalRecord(" ++ e1.translation ++ ".toString(), " ++ e2.translation ++ ", " ++ e3.translation ++ "))";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production terminalFunctionInherited
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ')'
{
  top.translation = "(new common.TerminalRecord(" ++ e1.translation ++ ".toString(), " ++ e2.translation ++ "))";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}
