
autocopy attribute isRuleRoot :: Boolean occurs on Expr;
autocopy attribute originsRules :: [Expr] occurs on Expr, AppExprs, AppExpr;
 -- ^Needs to occur on anything that can "come between" a ProdStmt and an Expr

function makeRuleLocNote
Expr ::= attr::Decorated QNameAttrOccur
{
	return Silver_Expr{silver:extension:otx:childruntime:ruleLocNote(
		$Expr{makeStringExpr(attr.name, attr.location)},
		$Expr{makeStringExpr(hackUnparse(attr.location), attr.location)})};
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= lhs::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  e.isRuleRoot = true;
  e.originsRules = [makeRuleLocNote(attr)];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= lhs::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  e.isRuleRoot = true;
  e.originsRules = [makeRuleLocNote(attr)];
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

terminal ErDebug_t  'erdebug^' precedence=50;

concrete production erDebugProd
top::Expr ::= ErDebug_t '(' e::Expr ')'
{
	e.downSubst = top.downSubst;
	local dbginfo :: String = "ER debug info for " ++ hackUnparse(e) ++":\\n" ++
		"er: "++toString(e.isRuleRoot);
	forwards to stringConst(terminal(String_t, "\"" ++ dbginfo ++ "\"", top.location), location=top.location);
}