grammar silver:compiler:extension:testing;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;
import silver:compiler:modification:collection;
import silver:compiler:modification:list;

-- Expression creating functions
-- TODO: These are duplicates of existing helpers, are obsolete with concrete syntax quotation,
-- or otherwise don't belong here!

-- Create an Expr from a String.
fun mkNameExpr Expr ::= name::String = baseExpr(qName(name));

-- fold a list of expressions(Expr) into a single "++"-separated Expr
fun foldStringExprs Expr ::= es::[Expr] =
  if null(es)
  then stringConst(terminal(String_t, "\"\""))
  else plusPlus(head(es), '++', foldStringExprs(tail(es)));

-- Create an Expr that is a string constant from a string.
fun strCnst Expr ::= s::String = stringConst(terminal(String_t, "\"" ++ escapeString(s) ++ "\""));

-- Create an attribute reference from two names. as in "n.a"
fun attrAcc Expr ::= n::String a::String = access(mkNameExpr(n), '.', qNameAttrOccur(qName(a)));
