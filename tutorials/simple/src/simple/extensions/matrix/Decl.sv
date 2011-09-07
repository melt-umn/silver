grammar simple:extensions:matrix;

concrete production declMatrix_C
d::cst:Decl ::= 'Matrix' id::cst:Id '[' r::cst:Expr ',' c::cst:Expr ']' ri::cst:Id ',' ci::cst:Id '=' e::cst:Expr ';'
{
  d.unparse = "Matrix " ++ id.lexeme ++ "[" ++ r.unparse ++ ", " ++ c.unparse ++ "] " ++ ri.lexeme ++ "," ++ ci.lexeme ++ " = " ++ e.unparse ++ ";\n";
  d.ast = declMatrix(cst:name(id), r.ast, c.ast, cst:name(ri), cst:name(ci), e.ast);
}

abstract production declMatrix
d::Decl ::= id::Name r::Expr c::Expr ri::Name ci::Name v::Expr
{
  d.pp = concat([text("Matrix "), id.pp, brackets(concat([r.pp, text(", "), c.pp])), space(), ri.pp, text(", "), ci.pp, text(" ="), 
                 groupnest(3, concat([line(), v.pp, semi()]))]);
  d.defs = addBinding(id.name, decorate typeExprMatrix() with {}, emptyEnv());
  d.errors := r.errors;
  d.errors <- case r.type of
                integerType() -> []
              | t -> [err(locUnknown(), show(100,r.pp) ++ " is supposed to be Integer type")]
              end;
  d.errors <- c.errors;
  d.errors <- case c.type of
                integerType() -> []
              | t -> [err(locUnknown(), show(100,c.pp) ++ " is supposed to be Integer type")]
              end;
  d.errors <- v.errors;
  d.errors <- if isNumeric(v.type)
              then []
              else [err(locUnknown(), show(100,v.pp) ++ " is supposed to be numeric type")];
  
  v.env = addBinding(ri.name, decorate typeExprInteger() with {},
           addBinding(ci.name, decorate typeExprInteger() with {},
            d.env));
  
  -- May require using --std=c99
  d.c_code = 
   "double " ++ id.name ++ "[" ++ r.c_code ++ "][" ++ c.c_code ++ "];\n" ++
   "for(int " ++ ri.name ++ " = 0; " ++ ri.name ++ " < " ++ r.c_code ++ "; " ++ ri.name ++ "++) {\n" ++
   " for(int " ++ ci.name ++ " = 0; " ++ ci.name ++ " < " ++ c.c_code ++ "; " ++ ci.name ++ "++) {\n" ++
   "  " ++ id.name ++ "[" ++ ri.name ++ "][" ++ ci.name ++ "] = " ++ v.c_code ++ ";\n" ++
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
  t.pp = text("Matrix");
  t.type = matrixType();
  t.c_code = "struct matrix";
}

abstract production matrixType
t::Type ::=   
{
  t.pp = text("Matrix");
}

