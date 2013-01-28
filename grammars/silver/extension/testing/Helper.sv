grammar silver:extension:testing;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:modification:collection ;
import silver:extension:list ;

function mkName
Name ::= name::String
{ return nameIdLower( terminal(IdLower_t, name)) ;  }

-- Expression creating functions

-- Create an Expr from a String.
function mkNameExpr
Expr ::= name::String
{ return baseExpr( qNameId( nameIdLower( terminal(IdLower_t, name)))) ;  }

-- fold a list of expressions (Expr) into a single "++"-separated Expr
function foldStringExprs 
Expr ::= es::[Expr]
{
 return if null(es)
        then stringConst (terminal(String_t,"\"\""))
        else plusPlus ( head(es), '++', foldStringExprs(tail(es)) ) ;
}

-- Create an Expr that is a string constant from a string.
function strCnst
Expr ::= s::String
{ return stringConst (terminal(String_t, "\"" ++ stringifyString(s) ++ "\"")) ; }

-- Create a production or function call from a String (the name) and list
-- of Expr arguments.
function prodFuncCall
Expr ::= name::String args::[Expr]
{ 
  return mkFunctionInvocation(mkNameExpr(name), args);
}

-- Fold a list of expressions (Expr) into a Exprs nonterminal.
function foldExprs
Exprs ::= es::[Expr]
{ return if null(es) then  exprsEmpty()
         else if null(tail(es))
              then exprsSingle(head(es))
              else exprsCons ( head(es), ',', foldExprs(tail(es))) ;
}

-- Create an attribute reference from two names. as in "n.a"
function attrAcc
Expr ::= n::String a::String
{ return  
   access ( mkNameExpr(n), '.', qName(loc("??",-1,-1), a) ) ; 
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

