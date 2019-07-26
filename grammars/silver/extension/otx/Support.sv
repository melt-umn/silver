
abstract production otxShuckValueImpl
top::Expr ::= arg::Expr
{
  top.unparse = "otxShuckValueImpl(" ++ arg.unparse ++ ")";
  arg.downSubst = top.downSubst; --Needs to be provided here so we can ask it's typerep to forward to

  forwards to case arg.typerep of
    | decoratedType(_) -> newFunction('new', '(', arg, ')', location=top.location)
    | _ -> arg
  end;
}

function makeStringExpr
Expr ::= s::String l::Location
{
  return stringConst(terminal(String_t, "\"" ++ substitute("\"", "\\\"", s) ++ "\"", l), location=l);
}

function listExprOfExprList
Expr ::= xs::[Expr]
{
  return case xs of 
    | [] -> Silver_Expr{[]}
    | x::rest -> Silver_Expr{$Expr{x}::$Expr{listExprOfExprList(rest)}}
  end;
}

function boolExprOfBool
Expr ::= x::Boolean
{
  return case x of
    | true -> Silver_Expr{true}
    | false -> Silver_Expr{false}
  end;
}