grammar silver:translation:java:core;

aspect production errorLength
top::Expr ::= e::Decorated Expr
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.translation = "Integer.valueOf(((common.StringCatter)" ++ e.translation ++ ").length())";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
  top.translation = case finalType(e) of
                    | intTypeExp() -> e.translation
                    | floatTypeExp() -> "((Float)" ++ e.translation ++ ").intValue()"
                    | stringTypeExp() -> "Integer.valueOf(" ++ e.translation ++ ".toString())"
                    | t -> error("INTERNAL ERROR: no toInt translation for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}
aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.translation = case finalType(e) of
                    | intTypeExp() -> "((Integer)" ++ e.translation ++ ").floatValue()"
                    | floatTypeExp() -> e.translation
                    | stringTypeExp() -> "Float.valueOf(" ++ e.translation ++ ".toString())"
                    | t -> error("INTERNAL ERROR: no toFloat translation for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}
aspect production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.translation = "new common.StringCatter(String.valueOf(" ++ e.translation ++ "))";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ ".undecorate())";
  
  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::Type ',' es::Expr ',' el::Expr ')'
{
  top.translation = "new common.TerminalRecord(" ++ es.translation ++ ", " ++ el.translation ++ ")";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

