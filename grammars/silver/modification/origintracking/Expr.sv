
imports silver:modification:primitivepattern only PrimPattern, PrimPatterns;
imports silver:extension:patternmatching only MatchRule, AbstractMatchRule, PatternList, MRuleList;

autocopy attribute isRuleRoot :: Boolean occurs on Expr, AppExprs, AppExpr, AnnoAppExprs;
autocopy attribute originsRules :: [Expr] occurs on Expr, AppExprs, AppExpr, AnnoAppExprs;
 -- ^Needs to occur on anything that can "come between" a ProdStmt and an Expr



attribute isRuleRoot, originsRules occurs on
	PrimPattern, PrimPatterns, AssignExpr, MatchRule, AbstractMatchRule, PatternList, MRuleList;
 -- AssignExpr is from let_fix, Patterns stuff from primitivepatters


function makeRuleLocNote
Expr ::= dec::Decorated ProductionStmt name::String loc::Location
{
	local prod :: String = dec.frame.fullName;
	local ntType :: String = dec.frame.lhsNtName;
	return Silver_Expr{silver:modification:origintracking:childruntime:ruleLocNote(
		$Expr{makeStringExpr(name, loc)},
		$Expr{makeStringExpr(prod, loc)},
		$Expr{makeStringExpr(ntType, loc)},
		$Expr{makeStringExpr(dec.grammarName, dec.location)},
		$Expr{locationExprOfLocation(loc)})};
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= lhs::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  e.isRuleRoot = true;
  e.originsRules = [makeRuleLocNote(top, attr.name, attr.location)];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= lhs::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  e.isRuleRoot = true;
  e.originsRules = [makeRuleLocNote(top, attr.name, attr.location)];
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName e::Expr
{
  e.isRuleRoot = true;
  e.originsRules = [makeRuleLocNote(top, "(local) "++val.name, val.location)];
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  e.isRuleRoot = true;
  e.originsRules = [makeRuleLocNote(top, "(return-value)", e.location)];
}

-- aspect default production
-- top::Expr ::=
-- {
--   top.isRuleRoot = false;
-- }

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  e1.isRuleRoot = false;
  -- autocopy to e1 and e2
}

aspect production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
	e1.isRuleRoot = false;
	e2.isRuleRoot = false;
}

aspect production functionApplication 
top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs
{
	es.isRuleRoot = false;
	anns.isRuleRoot = false;
}
--is also production application
--Setting isRuleRoot on LHS (e) shouldn't ever matter since it should never
-- be exposed in a manner that would let you inspect it's origins info


terminal ErDebug_t  'erdebug^' precedence=50;

concrete production erDebugProd
top::Expr ::= ErDebug_t '(' e::Expr ')'
{
	e.downSubst = top.downSubst;
	local dbginfo :: String = "ER debug info for " ++ hackUnparse(e) ++":\\n" ++
		"er: "++toString(e.isRuleRoot);
	forwards to stringConst(terminal(String_t, "\"" ++ dbginfo ++ "\"", top.location), location=top.location);
}