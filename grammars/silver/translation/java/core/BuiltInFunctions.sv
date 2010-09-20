grammar silver:translation:java:core;

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

aspect production errorFunction
top::Expr ::= 'error' '(' e::Expr ')'
{
  -- The fact that we don't put a transType here is MURDER. TODO BUG OHGOD UGLY
  -- This is a hack to try to avoid bugs
  
  local attribute casttt :: String;
  casttt = case top.expected of
             expected_type(t) -> "(" ++ t.transType ++ ")"
           | _                -> "" end; -- HOPE IT WORKS!
	
  top.translation = "(" ++ casttt ++ "common.Util.error(" ++ e.translation ++ ".toString()))";
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

aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.translation = "((" ++ e.translation ++ ").undecorate())";
}

aspect production terminalFunction
top::Expr ::= 'terminal' '(' t::Type ',' e::Expr ')'
{
  top.translation = "(new common.TerminalRecord(" ++ e.translation ++ ".toString(), -1, -1))";
}

aspect production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.translation = "(new common.TerminalRecord(" ++ e1.translation ++ ".toString(), " ++ e2.translation ++ ", " ++ e3.translation ++ "))";
}

aspect production terminalFunctionInherited
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ')'
{
  top.translation = "(new common.TerminalRecord(" ++ e1.translation ++ ".toString(), " ++ e2.translation ++ "))";
}
