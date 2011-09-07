grammar simple:extensions:expr_if;

imports silver:langutil;
imports silver:langutil:pp;
imports simple:concretesyntax as cst;
imports simple:abstractsyntax;

terminal Then 'then' lexer classes { KEYWORDS };
terminal Else 'else' lexer classes { KEYWORDS }, precedence = 4;

concrete production ifthenelse_c
e::cst:Expr ::= 'if' c::cst:Expr 'then' t::cst:Expr Else e1::cst:Expr
{
  e.unparse = "if " ++ c.unparse ++ " then " ++ t.unparse ++ " else " ++ e1.unparse;
  e.ast = ifthenelse(c.ast, t.ast, e1.ast);
}

abstract production ifthenelse
e::Expr ::= c::Expr t::Expr e1::Expr
{
  e.pp = parens(box(concat([text("if "), c.pp, line(), 
                            text("then "), t.pp, line(), 
                            text("else "), e1.pp])));
  e.type = t.type;
  e.errors := c.errors ++ t.errors ++ e1.errors;
  e.errors <- if show(100,t.type.pp) == show(100,e1.type.pp) then []  -- TODO: this is the WORST wrong way to do this!!
              else [err(locUnknown(), "if with condition " ++ show(100,c.pp) ++ " has mismatching branch types")];
  e.errors <- case c.type of
                booleanType() -> []
              | _ -> [err(locUnknown(), "if has non-boolean condition " ++ show(100,c.pp))]
              end;

  e.c_code = "(" ++ c.c_code ++ " ? " ++ t.c_code ++ " : " ++ e1.c_code ++ ")";
}
