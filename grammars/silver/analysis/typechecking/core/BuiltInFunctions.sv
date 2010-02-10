grammar silver:analysis:typechecking:core;
import silver:definition:env;
import silver:definition:core;

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.typeErrors = e.typeErrors;      
}

aspect production unknownLength
top::Expr ::= e::Decorated Expr
{
  local attribute er :: [Decorated Message];
  er = [err(top.location, "operand to 'length(..)' is not compatible.")];

  top.typeErrors = er ++ e.typeErrors;      
}

aspect production indexOfFunction
top::Expr ::= 'indexOf' '(' e1::Expr ',' e2::Expr ')'
{
  top.typeErrors = er1 ++ er2 ++ e1.typeErrors ++ e2.typeErrors;

  local attribute er1 :: [Decorated Message];
  er1 = if (e1.typerep.typeName == "String") 
	then []
	else [err(top.location, "First operand to 'indexof(...)' must be of type String.")];

  local attribute er2 :: [Decorated Message];
  er2 = if (e2.typerep.typeName == "String") 
	then []
	else [err(top.location, "Second operand to 'indexof(...)' must be of type String.")];
}
aspect production subStringFunction
top::Expr ::= 'substring' '(' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.typeErrors = er1 ++ er2 ++ er3 ++ e1.typeErrors ++ e2.typeErrors ++ e3.typeErrors;

  local attribute er1 :: [Decorated Message];
  er1 = if (e1.typerep.typeName == "Integer") 
	then []
	else [err(top.location, "First operand to 'substring(...)' must be of type Integer.")];

  local attribute er2 :: [Decorated Message];
  er2 = if (e2.typerep.typeName == "Integer") 
	then []
	else [err(top.location, "Second operand to 'substring(...)' must be of type Integer.")];

  local attribute er3 :: [Decorated Message];
  er3 = if (e3.typerep.typeName == "String") 
	then []
	else [err(top.location, "Third operand to 'substring(...)' must be of type String.")];


}
aspect production errorFunction
top::Expr ::= 'error' '(' e::Expr ')'
{
  local attribute er :: [Decorated Message];
  er = if (e.typerep.typeName == "String") 
       then []
       else [err(top.location, "operand to 'error(..)' must be of type String.")];

  top.typeErrors = er ++ e.typeErrors;      
}
aspect production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
  local attribute er :: [Decorated Message];
  er = if (e.typerep.typeName == "String") || (e.typerep.typeName == "Float") 
       then []
       else [err(top.location, "operand to 'toInt(..)' must be of type String or Float.")];

  top.typeErrors = er ++ e.typeErrors;      
}
aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  local attribute er :: [Decorated Message];
  er = if (e.typerep.typeName == "String") || (e.typerep.typeName == "Integer") 
       then []
       else [err(top.location, "operand to 'toFloat(..)' must be of type String or Integer.")];

  top.typeErrors = er ++ e.typeErrors;      
}
aspect production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  local attribute er :: [Decorated Message];
  er = if (e.typerep.typeName == "Integer") ||
	  (e.typerep.typeName == "Float")  ||
          (e.typerep.typeName == "Boolean") 
       then []
       else [err(top.location, "operand to 'toString(..)' must be of type Integer, Float or Boolean.")];

  top.typeErrors = er ++ e.typeErrors;      
}

aspect production isDigitFunction
top::Expr ::= 'isDigit' '(' e::Expr ')'
{
  local attribute er :: [Decorated Message];
  er = if e.typerep.typeName == "String"
       then []
       else [err(top.location, "operand to 'isDigit(..)' must be of type String.")];

  top.typeErrors = er ++ e.typeErrors;      
}

aspect production isAlphaFunction
top::Expr ::= 'isAlpha' '(' e::Expr ')'
{
  local attribute er :: [Decorated Message];
  er = if e.typerep.typeName == "String"
       then []
       else [err(top.location, "operand to 'isAlpha(..)' must be of type String.")];

  top.typeErrors = er ++ e.typeErrors;      
}

aspect production isSpaceFunction
top::Expr ::= 'isSpace' '(' e::Expr ')'
{
  local attribute er :: [Decorated Message];
  er = if e.typerep.typeName == "String"
       then []
       else [err(top.location, "operand to 'isSpace(..)' must be of type String.")];

  top.typeErrors = er ++ e.typeErrors;      
}

aspect production isLowerFunction
top::Expr ::= 'isLower' '(' e::Expr ')'
{
  local attribute er :: [Decorated Message];
  er = if e.typerep.typeName == "String"
       then []
       else [err(top.location, "operand to 'isLower(..)' must be of type String.")];

  top.typeErrors = er ++ e.typeErrors;      
}

aspect production isUpperFunction
top::Expr ::= 'isUpper' '(' e::Expr ')'
{
  local attribute er :: [Decorated Message];
  er = if e.typerep.typeName == "String"
       then []
       else [err(top.location, "operand to 'isUpper(..)' must be of type String.")];

  top.typeErrors = er ++ e.typeErrors;      
}


aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  local attribute er :: [Decorated Message];
  er = if (e.typerep.isDecorated) 
       then []
       else [err(top.location, "operand to 'new(..)' must be a Decorated Nonterminal.")];

  top.typeErrors = er ++ e.typeErrors;      
}
aspect production terminalFunction
top::Expr ::= 'terminal' '(' t::Type ',' e::Expr ')'
{
  local attribute er :: [Decorated Message];
  er = if (e.typerep.typeName == "String") 
       then []
       else [err(top.location, "operand to 'terminal(..)' must be of type String.")];

  top.typeErrors = er ++ e.typeErrors;      
}
aspect production terminalFunction2
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  local attribute er1 :: [Decorated Message];
  er1 = if (t.typerep.isTerminal) 
        then []
        else [err(top.location, "First operand to 'terminal(..)' must be a Terminal.")];

  local attribute er2 :: [Decorated Message];
  er2 = if (e1.typerep.typeName == "String") 
        then []
        else [err(top.location, "Second operand to 'terminal(..)' must be of type String.")];

  local attribute er3 :: [Decorated Message];
  er3 = if (e2.typerep.typeName == "Integer") 
        then []
        else [err(top.location, "Third operand to 'terminal(..)' must be of type Integer.")];

  local attribute er4 :: [Decorated Message];
  er4 = if (e3.typerep.typeName == "Integer") 
        then []
        else [err(top.location, "Fourth operand to 'terminal(..)' must be of type Integer.")];

  top.typeErrors = er1 ++ er2 ++ er3 ++ er4 ++ e1.typeErrors ++ e2.typeErrors ++ e3.typeErrors;      
}
