
abstract production originShuckValueImpl
top::Expr ::= arg::Expr
{
  top.unparse = "originShuckValueImpl(" ++ arg.unparse ++ ")";
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

function makeIntExpr
Expr ::= i::Integer l::Location
{
  return intConst(terminal(Int_t, toString(i), l), location=l);
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

function locationExprOfLocation
Expr ::= x::Location
{
  return case x of
    | loc(filename, line, column, endLine, endColumn, index, endIndex) ->
      Silver_Expr{loc(
        $Expr{makeStringExpr(filename, x)},
        $Expr{makeIntExpr(line, x)},
        $Expr{makeIntExpr(column, x)},
        $Expr{makeIntExpr(endLine, x)},
        $Expr{makeIntExpr(endColumn, x)},
        $Expr{makeIntExpr(index, x)},
        $Expr{makeIntExpr(endIndex, x)})}
    | txtLoc(text) ->
      Silver_Expr{txtLoc($Expr{makeStringExpr(text, x)})}
    | builtinLoc(mod) ->
      Silver_Expr{builtinLoc($Expr{makeStringExpr(mod, x)})}
    | bogusLoc() ->
      Silver_Expr{bogusLoc()}
  end;
}

function mkLhsRef
Expr ::= top::Decorated Expr --need .frame anno
{
  return baseExpr(
      qNameId(
        name(top.frame.signature.outputElement.elementName,
      top.location), location=top.location), location=top.location);
}