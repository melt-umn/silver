grammar silver:compiler:analysis:uniqueness;

imports silver:compiler:definition:type;

attribute isUnique occurs on Type;

aspect isUnique on Type of
| partiallyDecoratedType(_, _) -> true
| _ -> false
end;
