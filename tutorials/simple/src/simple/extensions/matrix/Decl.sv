grammar simple:extensions:matrix;

concrete production declMatrix_C
d::cst:Decl ::= 'Matrix' id::cst:Id '[' r::cst:Expr ',' c::cst:Expr ']' ri::cst:Id ',' ci::cst:Id '=' e::cst:Expr ';'
{
  d.pp = "Matrix " ++ id.lexeme ++ "[" ++ r.pp ++ ", " ++ c.pp ++ "] " ++ ri.lexeme ++ "," ++ ci.lexeme ++ " = " ++ e.pp ++ ";\n";
  d.ast = declMatrix(cst:name(id), r.ast, c.ast, cst:name(ri), cst:name(ci), e.ast);
}

abstract production declMatrix
d::Decl ::= id::Name r::Expr c::Expr ri::Name ci::Name v::Expr
{
  d.pp = "Matrix " ++ id.pp ++ "[" ++ r.pp ++ ", " ++ c.pp ++ "] " ++ ri.pp ++ "," ++ ci.pp ++ " = " ++ v.pp ++ ";\n";
  d.defs = addBinding(id.pp, decorate typeExprMatrix() with {}, emptyEnv());
  d.errors := r.errors;
  d.errors <- case r.type of
                integerType() -> []
              | t -> [err(locUnknown(), r.pp ++ " is supposed to be Integer type")]
              end;
  d.errors <- c.errors;
  d.errors <- case c.type of
                integerType() -> []
              | t -> [err(locUnknown(), c.pp ++ " is supposed to be Integer type")]
              end;
  d.errors <- v.errors;
  d.errors <- if isNumeric(v.type)
              then []
              else [err(locUnknown(), v.pp ++ " is supposed to be numeric type")];
  
  v.env = addBinding(ri.pp, decorate typeExprInteger() with {},
           addBinding(ci.pp, decorate typeExprInteger() with {},
            d.env));
  
  d.c_code = 
   "double " ++ id.pp ++ "[" ++ r.c_code ++ "][" ++ c.c_code ++ "];\n" ++
   "for(int " ++ ri.pp ++ " = 0; " ++ ri.pp ++ " < " ++ r.c_code ++ "; " ++ ri.pp ++ "++) {\n" ++
   " for(int " ++ ci.pp ++ " = 0; " ++ ci.pp ++ " < " ++ c.c_code ++ "; " ++ ci.pp ++ "++) {\n" ++
   "  " ++ id.pp ++ "[" ++ ri.pp ++ "][" ++ ci.pp ++ "] = " ++ v.c_code ++ ";\n" ++
   " }\n" ++
   "}\n";
}


-- Don't permit normal declarations
--concrete production typeExprMatrix_c
--t::cst:TypeExpr ::= 'Matrix' 
--{
--  t.pp = "Matrix";
--  t.ast = typeExprMatrix(); 
--}

abstract production typeExprMatrix
t::TypeExpr ::=  
{
  t.pp = "Matrix";  
  t.type = matrixType();
  t.c_code = "struct matrix";
}

abstract production matrixType
t::Type ::=   
{
  t.pp = "Matrix";
}

