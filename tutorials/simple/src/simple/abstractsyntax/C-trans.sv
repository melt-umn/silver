grammar simple:abstractsyntax;

synthesized attribute c_code :: String
  occurs on Root, Stmt, Decl, Expr, TypeExpr;

---------------------------------------------------------------------------
-- Root, aspect over the production in Root.sv
aspect production rootStmt
r::Root ::= s::Stmt
{
  r.c_code = "#include <stdio.h>\n\n" ++
             "int main() {\n " ++
             s.c_code ++
             "}\n\n";
}

---------------------------------------------------------------------------
-- Statements, aspects over productions in Stmt.sv
aspect production declStmt
s::Stmt ::= d::Decl 
{
  s.c_code = d.c_code;
}

aspect production block
s::Stmt ::= body::Stmt 
{
  s.c_code = "{\n" ++ body.c_code ++ "}\n";
}

aspect production seq
s::Stmt ::= s1::Stmt s2::Stmt 
{
  s.c_code = s1.c_code ++ s2.c_code;
}

aspect production printStmt
s::Stmt ::= e::Expr 
{
  s.c_code = "printf (\"" ++ 
             case e.type of
               integerType() -> "%d"
             | floatType()   -> "%f"
             | booleanType() -> "%d"
             | stringType()  -> "%s"
             end ++
             "\", " ++ e.c_code ++ "); \n";
}

aspect production skip
s::Stmt ::= 
{
  s.c_code = "";
}

aspect production while
s::Stmt ::= c::Expr b::Stmt 
{
  s.c_code = "while ( " ++ c.c_code ++ " )\n" ++ b.c_code;
}

aspect production ifthen
s::Stmt ::= c::Expr t::Stmt 
{
  s.c_code = "if ( " ++ c.c_code ++ " )\n" ++ t.c_code;
}

aspect production ifelse
s::Stmt ::= c::Expr t::Stmt e::Stmt 
{
  s.c_code = "if ( " ++ c.c_code ++ " )\n" ++ t.c_code ++ 
             "else \n" ++ e.c_code;
}

aspect production assignment
s::Stmt ::= id::Name e::Expr 
{
  s.c_code = id.name ++ " = " ++ e.c_code ++ "; \n";
}

---------------------------------------------------------------------------
-- Declarations and Type Expressions, aspects over productions in Decl.sv
aspect production decl
d::Decl ::= t::TypeExpr id::Name 
{
  d.c_code = t.c_code ++ " " ++ id.name ++ "; \n";
}

aspect production typeExprInteger
t::TypeExpr ::=  
{
  t.c_code = "int";
}
aspect production typeExprFloat
t::TypeExpr ::=  
{
  t.c_code = "double";
}
aspect production typeExprBoolean
t::TypeExpr ::=  
{
  t.c_code = "int";
}
aspect production typeExprString
t::TypeExpr ::=  
{
  t.c_code = "char *";
}

---------------------------------------------------------------------------
{- Expressions, aspects over productions in Expr.sv

Note that we do not define the c_code attribute for those relational
and logical operations that (umm that WHAT? hahahaha)
-}
aspect production intLit
e::Expr ::= l::Location s::String
{
  e.c_code = s;
}
aspect production floatLit
e::Expr ::= l::Location s::String
{
  e.c_code = s;
}
aspect production boolLit
e::Expr ::= l::Location s::String
{
  e.c_code = s;
}
aspect production stringLit
e::Expr ::= l::Location s::String
{
  e.c_code = s;
}

aspect production varRef
e::Expr ::= id::Name
{
  e.c_code = id.name;  
}

aspect production add
e::Expr ::= l::Expr r::Expr 
{
  e.c_code = "(" ++  l.c_code ++ " + " ++ r.c_code ++ ")";  
}
aspect production sub
e::Expr ::= l::Expr r::Expr 
{
  e.c_code = "(" ++  l.c_code ++ " - " ++ r.c_code ++ ")";  
}
aspect production mul
e::Expr ::= l::Expr r::Expr 
{
  e.c_code = "(" ++  l.c_code ++ " * " ++ r.c_code ++ ")";  
}
aspect production div
e::Expr ::= l::Expr r::Expr 
{
  e.c_code = "(" ++  l.c_code ++ " / " ++ r.c_code ++ ")";  
}

aspect production eq
e::Expr ::= l::Expr r::Expr 
{
  e.c_code = "(" ++  l.c_code ++ " == " ++ r.c_code ++ ")";  
}
aspect production lt
e::Expr ::= l::Expr r::Expr 
{
  e.c_code = "(" ++  l.c_code ++ " < " ++ r.c_code ++ ")";  
}

aspect production and
e::Expr ::= l::Expr r::Expr 
{
  e.c_code = "(" ++  l.c_code ++ " && " ++ r.c_code ++ ")";  
}
aspect production not
e::Expr ::= ne::Expr 
{
  e.c_code = "( !" ++  ne.c_code ++ ")";  
}


