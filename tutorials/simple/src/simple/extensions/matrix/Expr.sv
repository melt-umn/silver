grammar simple:extensions:matrix;

concrete production matrixRef_c
e::cst:Expr ::= id::cst:Id '[' r::cst:Expr ',' c::cst:Expr ']'
{
  e.unparse = id.lexeme;
  e.ast = matrixRef(cst:name(id), r.ast, c.ast);
}

abstract production matrixRef
e::Expr ::= id::Name r::Expr c::Expr
{
  e.pp = cat(id.pp, brackets(concat([r.pp, text(", "), c.pp])));

  e.type = floatType();

  e.errors := case id.lookup of
                just(typeExprMatrix()) -> []
              | just(t) -> [err(id.location, "variable \"" ++ id.name ++ "\" is not a Matrix but a " ++ show(100, t.pp))] 
              | nothing() -> [err(id.location, "variable \"" ++ id.name ++ "\" was not declared.")] 
              end;
  e.errors <- r.errors ++ c.errors;
  e.errors <- case r.type of
                integerType() -> []
              | t -> [err(locUnknown(), show(100, r.pp) ++ " is supposed to be Integer type")]
              end;
  e.errors <- case c.type of
                integerType() -> []
              | t -> [err(locUnknown(), show(100, c.pp) ++ " is supposed to be Integer type")]
              end;

  e.c_code = id.name ++ "[" ++ r.c_code ++ "][" ++ c.c_code ++ "]";
}

