grammar silver:compiler:extension:nanopass;

attribute isIncluded occurs on Type;
aspect isIncluded on top::Type of
| nonterminalType(fn, _, _, _) -> isTypeIncluded(_, fn)
| dispatchType(ns) -> isTypeIncluded(_, ns.fullName)
| appType(c, _) -> c.isIncluded
| decoratedType(n, _) -> n.isIncluded
| _ -> tt
end;
