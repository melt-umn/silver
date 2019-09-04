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

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
  top.translation = case finalType(e) of
                    | intType() -> e.translation
                    | floatType() -> "Integer.valueOf(((Float)" ++ e.translation ++ ").intValue())"
                    | stringType() -> "Integer.valueOf(" ++ e.translation ++ ".toString())"
                    | t -> error("INTERNAL ERROR: no toInt translation for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.translation = case finalType(e) of
                    | intType() -> "Float.valueOf(((Integer)" ++ e.translation ++ ").floatValue())"
                    | floatType() -> e.translation
                    | stringType() -> "Float.valueOf(" ++ e.translation ++ ".toString())"
                    | t -> error("INTERNAL ERROR: no toFloat translation for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
aspect production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.translation = "new common.StringCatter(String.valueOf(" ++ e.translation ++ "))";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ ".undecorate())";
  
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  top.translation = "new " ++ makeTerminalName(t.typerep.typeName) ++ "(" ++ es.translation ++ ", (core.NLocation)" ++ el.translation ++ ")";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
