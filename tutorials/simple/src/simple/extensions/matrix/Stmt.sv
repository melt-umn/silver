grammar simple:extensions:matrix;

{-
concrete production assignmentMatrix_c
s::cst:StmtMatched ::= id::cst:Id '[' row::cst:Expr ',' col::cst:Expr ']' '=' value::cst:Expr ';' 
{
  s.unparse = id.lexeme ++ "[" ++ row.unparse ++ ", " ++ col.unparse ++ "] = " ++ value.unparse ++ ";\n";
  s.ast = assignmentMatrix(cst:name(id), row.ast, col.ast, value.ast); 
}

abstract production assignmentMatrix
s::Stmt ::= id::Name r::Expr c::Expr e::Expr 
{
  s.pp = id.pp ++ " = " ++ e.pp ++ "; \n";
  s.defs = emptyEnv();
  s.errors := case id.lookup of
                just(typeExprMatrix()) -> []
              | just(t) -> [err(id.location, "variable \"" ++ id.pp ++ "\" is not a Matrix but a " ++ t.pp)] 
              | nothing() -> [err(id.location, "variable \"" ++ id.pp ++ "\" was not declared.")] 
              end;
  s.errors <- r.errors ++ c.errors;
  s.errors <- case r.type of
                integerType() -> []
              | t -> [err(locUnknown(), r.pp ++ " is supposed to be Integer type")]
              end;
  s.errors <- case c.type of
                integerType() -> []
              | t -> [err(locUnknown(), c.pp ++ " is supposed to be Integer type")]
              end;
  s.errors <- e.errors;
  s.errors <- case e.type of
                floatType() -> []
              | t -> [err(locUnknown(), c.pp ++ " is supposed to be Float type")]
              end;
  
  s.c_code = "set_matrix(&" ++ id.pp ++ ", " ++ r.c_code ++ ", " ++ c.c_code ++ ", " ++ e.c_code ++ ");\n";
}
---}
