grammar simple:extensions:matrix;

concrete production matrixRef_c
e::cst:Expr ::= id::cst:Id '[' r::cst:Expr ',' c::cst:Expr ']'
{
  e.pp = id.lexeme;
  e.ast = matrixRef(cst:name(id), r.ast, c.ast);
}

abstract production matrixRef  
e::Expr ::= id::Name r::Expr c::Expr
{
  e.pp = id.pp ++ "[" ++ r.pp ++ ", " ++ c.pp ++ "]";

  e.type = floatType();

  e.errors := case id.lookup of
                just(typeExprMatrix()) -> []
              | just(t) -> [err(id.location, "variable \"" ++ id.pp ++ "\" is not a Matrix but a " ++ t.pp)] 
              | nothing() -> [err(id.location, "variable \"" ++ id.pp ++ "\" was not declared.")] 
              end;
  e.errors <- r.errors ++ c.errors;
  e.errors <- case r.type of
                integerType() -> []
              | t -> [err(locUnknown(), r.pp ++ " is supposed to be Integer type")]
              end;
  e.errors <- case c.type of
                integerType() -> []
              | t -> [err(locUnknown(), c.pp ++ " is supposed to be Integer type")]
              end;

  e.c_code = id.pp ++ "[" ++ r.c_code ++ "][" ++ c.c_code ++ "]";
}

