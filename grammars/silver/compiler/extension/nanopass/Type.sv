grammar silver:compiler:extension:nanopass;

attribute isIncluded occurs on PolyType, Context, Type;

aspect isIncluded on top::PolyType of
| monoType(ty) -> ty.isIncluded
| polyType(_, ty) -> ty.isIncluded
| constraintType(_, c, ty) -> all(map((.isIncluded), c)) && ty.isIncluded
end;

aspect isIncluded on top::Context of
| instContext(cls, t) -> isTypeIncluded(_, cls) && t.isIncluded
| inhOccursContext(attr, args, _, t) ->
  isAttributeIncluded(_, attr) && all(map((.isIncluded), args)) && t.isIncluded
| synOccursContext(attr, args, _, i, t) ->
  isAttributeIncluded(_, attr) && all(map((.isIncluded), args)) && i.isIncluded && t.isIncluded
| annoOccursContext(attr, args, _, t) ->
  isAttributeIncluded(_, attr) && all(map((.isIncluded), args)) && t.isIncluded
| typeableContext(t) -> t.isIncluded
| inhSubsetContext(i1, i2) -> i1.isIncluded && i2.isIncluded
| typeErrorContext(_) -> tt
end;

aspect isIncluded on top::Type of
| nonterminalType(fn, _, _, _) -> isTypeIncluded(_, fn)
| dispatchType(ns) -> isTypeIncluded(_, ns.fullName)
| appType(c, a) -> c.isIncluded && a.isIncluded
| decoratedType(n, _) -> n.isIncluded
| _ -> tt
end;
