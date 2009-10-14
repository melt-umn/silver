grammar silver:translation:java:core;
import silver:translation:java:env;
import silver:definition:core;
import silver:definition:env;

aspect production unknownLength
top::Expr ::= e::Decorated Expr
{
  top.translation = "-1";
}

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.translation = "(new Integer(((common.StringCatter)" ++ e.translation ++ ").length()))";
}

aspect production indexOfFunction
top::Expr ::= 'indexOf' '(' e1::Expr ',' e2::Expr ')'
{
  top.translation ="(new Integer((" ++ e2.translation ++ ".toString().indexOf( " ++ e1.translation ++ ".toString()))))";
}
aspect production subStringFunction
top::Expr ::= 'substring' '(' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.translation = "(new common.StringCatter(" ++ e3.translation  ++ ".toString().substring(" ++ e1.translation  ++ ", " ++ e2.translation ++ ")))";
}
aspect production errorFunction
top::Expr ::= 'error' '(' e::Expr ')'
{
  top.translation = "(common.Util.error(" ++ e.translation ++ ".toString()))";
}
aspect production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
  top.translation = if e.typerep.isInteger then e.translation
		    else if e.typerep.isFloat then "(((" ++ e.typerep.transType ++ ")" ++ e.translation ++ ").intValue())"
		    else if e.typerep.isString then "(Float.valueOf(" ++ e.translation ++ ".toString()).intValue())"
		    else "(new Float(" ++ e.translation ++ ".toString()).intValue())";
}
aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.translation = if e.typerep.isFloat then e.translation
		    else if e.typerep.isInteger then "(((" ++ e.typerep.transType ++ ")" ++ e.translation ++ ").floatValue())"
		    else if e.typerep.isString then "(Float.valueOf(" ++ e.translation ++ ".toString()))"
		    else "(new Float(" ++ e.translation ++ ".toString()))";
}
aspect production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.translation = "(new common.StringCatter(String.valueOf(" ++ e.translation ++ ")))";
}

aspect production isDigitFunction
top::Expr ::= 'isDigit' '(' e::Expr ')'
{
  top.translation = "(common.Util.isDigit(" ++ e.translation ++ ".toString()))";
}

--TODO Charactoer().is...  Make sure the length is one or greater for digit.
aspect production isAlphaFunction
top::Expr ::= 'isAlpha' '(' e::Expr ')'
{
  top.translation = "(common.Util.isAlpha(" ++ e.translation ++ ".toString()))";
}

aspect production isSpaceFunction
top::Expr ::= 'isSpace' '(' e::Expr ')'
{
  top.translation = "(common.Util.isSpace(" ++ e.translation ++ ".toString()))";
}

aspect production isLowerFunction
top::Expr ::= 'isLower' '(' e::Expr ')'
{
  top.translation = "(common.Util.isLower(" ++ e.translation ++ ".toString()))";
}

aspect production isUpperFunction
top::Expr ::= 'isUpper' '(' e::Expr ')'
{
  top.translation = "(common.Util.isUpper(" ++ e.translation ++ ".toString()))";
}

aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.translation = "((" ++ e.translation ++ ").undecorate())";
}
aspect production terminalFunction
top::Expr ::= 'terminal' '(' t::Type ',' e::Expr ')'
{
  top.translation = "(new common.Terminal(" ++ e.translation ++ ".toString(), -1, -1))";
}
aspect production terminalFunction2
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.translation = "(new common.Terminal(" ++ e1.translation ++ ".toString(), " ++ e2.translation ++ ", " ++ e3.translation ++ "))";
}
