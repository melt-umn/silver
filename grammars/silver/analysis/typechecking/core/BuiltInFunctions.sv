grammar silver:analysis:typechecking:core;
import silver:definition:env;
import silver:definition:core;

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.typeErrors := e.typeErrors;      
}

aspect production unknownLength
top::Expr ::= e::Decorated Expr
{
  top.typeErrors <- [err(e.location, "operand to 'length(..)' is not compatible.")];
  top.typeErrors := e.typeErrors;      
}

aspect production errorFunction
top::Expr ::= 'error' '(' e::Expr ')'
{
  top.typeErrors <-
       if (e.typerep.typeName == "String") 
       then []
       else [err(top.location, "operand to 'error(..)' must be of type String.")];

  top.typeErrors := e.typeErrors;      
}
aspect production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
  top.typeErrors <-
       if (e.typerep.typeName == "String") || (e.typerep.typeName == "Float") 
       then []
       else [err(top.location, "operand to 'toInt(..)' must be of type String or Float.")];

  top.typeErrors := e.typeErrors;      
}
aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.typeErrors <-
       if (e.typerep.typeName == "String") || (e.typerep.typeName == "Integer") 
       then []
       else [err(top.location, "operand to 'toFloat(..)' must be of type String or Integer.")];

  top.typeErrors := e.typeErrors;      
}
aspect production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.typeErrors <-
       if (e.typerep.typeName == "Integer") ||
	  (e.typerep.typeName == "Float")  ||
          (e.typerep.typeName == "Boolean") 
       then []
       else [err(top.location, "operand to 'toString(..)' must be of type Integer, Float or Boolean.")];

  top.typeErrors := e.typeErrors;      
}

aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.typeErrors <-
       if (e.typerep.isDecorated) 
       then []
       else [err(top.location, "operand to 'new(..)' must be a Decorated Nonterminal.")];

  top.typeErrors := e.typeErrors;      
}

aspect production terminalFunction
top::Expr ::= 'terminal' '(' t::Type ',' e::Expr ')'
{
  top.typeErrors <-
        if (t.typerep.isTerminal) 
        then []
        else [err(top.location, "First operand to 'terminal(type,lexeme)' must be a Terminal.")];

  top.typeErrors <-
       if (e.typerep.typeName == "String") 
       then []
       else [err(top.location, "Second operand to 'terminal(type,lexeme)' must be of type String.")];

  top.typeErrors := e.typeErrors;      
}

aspect production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.typeErrors <-
        if (t.typerep.isTerminal) 
        then []
        else [err(top.location, "First operand to 'terminal(type,lexeme,line,column)' must be a Terminal.")];

  top.typeErrors <-
        if (e1.typerep.typeName == "String") 
        then []
        else [err(top.location, "Second operand to 'terminal(type,lexeme,line,column)' must be of type String.")];

  top.typeErrors <-
        if (e2.typerep.typeName == "Integer") 
        then []
        else [err(top.location, "Third operand to 'terminal(type,lexeme,line,column)' must be of type Integer.")];

  top.typeErrors <-
        if (e3.typerep.typeName == "Integer") 
        then []
        else [err(top.location, "Fourth operand to 'terminal(type,lexeme,line,column)' must be of type Integer.")];

  top.typeErrors := e1.typeErrors ++ e2.typeErrors ++ e3.typeErrors;      
}

aspect production terminalFunctionInherited
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ')'
{
  top.typeErrors <-
        if (t.typerep.isTerminal) 
        then []
        else [err(top.location, "First operand to 'terminal(type,lexeme,parent)' must be a Terminal.")];

  top.typeErrors <-
        if (e1.typerep.typeName == "String") 
        then []
        else [err(top.location, "Second operand to 'terminal(type,lexeme,parent)' must be of type String.")];
  
  top.typeErrors <-
        if (e2.typerep.isTerminal) 
        then []
        else [err(top.location, "Third operand to 'terminal(type,lexeme,parent)' must be a Terminal.")];
 
  top.typeErrors := e1.typeErrors ++ e2.typeErrors;
}
