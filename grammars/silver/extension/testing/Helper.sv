grammar silver:extension:testing;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:modification:collection;
import silver:extension:list;

-- Expression creating functions

-- Create an Expr from a String.
function mkNameExpr
Expr ::= name::String  l::Location
{ 
  return baseExpr(qName(l, name), location=l);
}

-- fold a list of expressions(Expr) into a single "++"-separated Expr
function foldStringExprs 
Expr ::= es::[Expr]
{
 return if null(es)
        then stringConst(terminal(String_t, "\"\""), location=bogusLocation())
        else plusPlus(head(es), '++', foldStringExprs(tail(es)), location=head(es).location);
}

-- Create an Expr that is a string constant from a string.
function strCnst
Expr ::= s::String
{
  return stringConst(terminal(String_t, "\"" ++ stringifyString(s) ++ "\""), location=bogusLocation());
}

-- Create an attribute reference from two names. as in "n.a"
function attrAcc
Expr ::= n::String a::String l::Location
{
  return  
    access(mkNameExpr(n, l), '.', qNameAttrOccur(qName(l, a), location=l), location=l);
}

-- replace " characters with two: \ and "
function stringifyString
String ::= s::String
{
  -- be sure to escape backslashes first!
  return substitute("\n", "\\n",
          substitute("\t", "\\t",
           substitute("\"", "\\\"",
            substitute("\\",  "\\\\", s))));
}

