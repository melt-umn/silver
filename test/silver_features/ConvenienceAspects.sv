
aspect isLValue on Expr of
| declRefExpr(_) -> true
| arraySubscriptExpr(lhs, _) -> lhs.isLValue
| memberExpr(lhs, _, _) -> lhs.isLValue
| _ -> false
end;
