
-- aspect unparse on silver:compiler:definition:core:Expr of
-- | errorReference(_, _) -> "test test"
-- | _ -> "unparse test"
-- end;


synthesized attribute value :: Integer;

nonterminal FooExpr with  value;

abstract production addfoo
sum::FooExpr ::= l::FooExpr r::FooExpr
{
 sum.value = l.value + r.value ;
}

synthesized attribute foopp :: String;
attribute foopp occurs on FooExpr;

aspect production addfoo
top::FooExpr ::= _ _
{ top.foopp = "test test";}

aspect default production
top::FooExpr ::=
{ top.foopp = "unparse test";}

-- silver:compiler:definition:core:aspectProductionDcl('aspect', 'production',
-- silver:compiler:definition:core:qNameId(silver:compiler:definition:core:nameIdLower('addfoo')),
-- silver:compiler:definition:core:aspectProductionSignature(silver:compiler:definition:core:aspectProductionLHSTyped(silver:compiler:definition:core:nameIdLower('top'),
-- '::',
-- silver:compiler:definition:type:syntax:nominalTypeExpr(silver:compiler:definition:core:qNameTypeId('FooExpr'))),
-- '::=',
-- silver:compiler:definition:core:aspectRHSElemCons(silver:compiler:definition:core:aspectRHSElemNone('_'),
-- silver:compiler:definition:core:aspectRHSElemCons(silver:compiler:definition:core:aspectRHSElemNone('_'),
-- silver:compiler:definition:core:aspectRHSElemNil())))
-- silver:compiler:definition:core:productionBody('{',
-- silver:compiler:definition:core:productionStmtsSnoc(silver:compiler:definition:core:productionStmtsNil(),
-- silver:compiler:definition:core:attributeDef(silver:compiler:definition:core:concreteDefLHS(silver:compiler:definition:core:qNameId(silver:compiler:definition:core:nameIdLower('top'))),
-- '.',
-- silver:compiler:definition:core:qNameAttrOccur(silver:compiler:definition:core:qNameId(silver:compiler:definition:core:nameIdLower('foopp'))),
-- '=', silver:compiler:definition:core:stringConst('"test test"'), ';')), '}'))
