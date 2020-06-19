grammar silver:extension:castmatching;

{-
	cast o to
	| Expr intExpr(x) -> x
	| Expr stringExpr(x) -> toInt(x)
	| OtherThing foo(x) -> x+1
	| _ -> 0
	end;

	case (reify(anyAST(o)), reify(anyAST(o))) of
	| right(intExpr(x)), _ -> x
	| right(stringExpr(x)), _ -> toInt(x)
	| _, right(foo(x)) -> x+1
	| _, _ -> 0
-}

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:extension:patternmatching;
imports silver:modification:let_fix;

terminal Cast_kwd 'cast' lexer classes {KEYWORD,RESERVED};
terminal To_kwd 'to' lexer classes {KEYWORD,RESERVED};

function typeEq
Boolean ::= x::Type y::Type
{
	return prettyType(x)==prettyType(y);
}

function makeReifyAnyASTs
Exprs ::= e::Expr l::Location i::Integer
{
	return if i==0
		   then exprsEmpty(location=l)
		   else exprsCons(Silver_Expr {reify(anyAST($Expr{e}))}, ',', makeReifyAnyASTs(e, l, i-1), location=l);
}

function joinPatterns
PatternList ::= l::Location p::[Pattern]
{
	return case p of
		   | [] -> error("no empty pattern list!")
		   | [x] -> patternList_one(x, location=l)
		   | x::xs ->  patternList_more(x, ',', joinPatterns(l, xs), location=l)
		   end;
}

{-
 - _, _, x   total=3 position=2 actualRule=x nrW=2 after=0 before=2
 - _, x, _   total=3 position=1 actualRule=x nrW=2 after=1 before=1
 - x, _      total=2 position=0 actualRule=x nrW=1 after=1 before=0
 - x         total=1 position=0 actualRule=x
 -}

function makeActualRule
MatchRule ::= l::Location total::Integer position::Integer actualRule::Pattern expr::Expr
{
	local nrWildcards :: Integer = total - 1;
	local wildcardsAfter :: Integer = nrWildcards - position;
	local wildcardsBefore :: Integer = nrWildcards - wildcardsAfter;
	local wildcard::Pattern = wildcPattern('_', location=l);

	local pattern::PatternList = joinPatterns(l, concat([
			reproduce(wildcardsBefore, [wildcard]),
			[prodAppPattern(qName(l, "core:right"), '(', joinPatterns(l, [actualRule]), ')', location=l)],
			reproduce(wildcardsAfter, [wildcard])]));

	return matchRule_c(pattern, '->', expr, location=l);
}

function makeRules
MRuleList ::= l::Location possibleTypes::[Type] branches::[Decorated CastExprBranch] last::MRuleList
{
	return case branches of
		   | [] -> last
		   | x::xs -> mRuleList_cons(makeActualRule(l, length(possibleTypes), 
		   	                          positionOf(typeEq, x.branchType, possibleTypes), x.branchPattern, x.branchExpr),
		   	                          '|', makeRules(l, possibleTypes, xs, last), location=l)
		   end;
}

concrete production castExpr_c
top::Expr ::= 'cast' castThis::Expr 'to' branches::CastExprBranches '|' '_' '->' fallback::Expr 'end'
{
	branches.downSubst = top.downSubst;
	fallback.downSubst = branches.upSubst;
	top.upSubst = fallback.upSubst;
	branches.finalSubst = top.finalSubst;
	fallback.finalSubst = top.finalSubst;

	local possibleTypes :: [Type] = branches.branchTypes;
	local nrTypes :: Integer = length(possibleTypes);

	top.errors := if nrTypes == 0 then [err(top.location, "Need at least one case")] else forward.errors;

	local exprs :: Exprs = makeReifyAnyASTs(castThis, top.location, nrTypes);

	local fallback_rule :: MRuleList = mRuleList_one(matchRule_c(
		joinPatterns(top.location, reproduce(nrTypes, [wildcPattern('_', location=top.location)])),
		'->', fallback, location=top.location), location=top.location);

	local rules :: MRuleList = makeRules(top.location, possibleTypes, branches.branches, fallback_rule);

	local fwd :: Expr = caseExpr_c('case', exprs, 'of', terminal(Opt_Vbar_t, "|"), rules, 'end', location=top.location);

	top.errors <- [wrn(top.location, fwd.unparse)];

	forwards to fwd;
}

concrete production castExprNoFallback_c
top::Expr ::= 'cast' castThis::Expr 'to' branches::CastExprBranches 'end'
{
	forwards to castExpr_c($1, $2, $3, $4, '|', '_', '->',
		Silver_Expr{error("unhandled failure case in cast expression at "++
			$Expr{stringConst(terminal(String_t, "\""++top.location.unparse++"\""), location=top.location)})}, $5, location=top.location);
}

nonterminal CastExprBranch with env, downSubst, upSubst, finalSubst;
synthesized attribute branchType::Type occurs on CastExprBranch;
synthesized attribute branchPattern::Pattern occurs on CastExprBranch;
synthesized attribute branchExpr::Expr occurs on CastExprBranch;

concrete production aCastExprBranch_c
top::CastExprBranch ::= '|' t::TypeExpr p::Pattern '->' e::Expr
{
	e.downSubst = top.downSubst;
	top.upSubst = e.upSubst;
	e.finalSubst = e.finalSubst;

	top.branchType = performSubstitution(t.typerep, top.finalSubst);
	top.branchPattern = p;
	top.branchExpr = e;
}

nonterminal CastExprBranches with env, downSubst, upSubst, finalSubst;
synthesized attribute branches :: [Decorated CastExprBranch] occurs on CastExprBranches;
synthesized attribute branchTypes :: [Type] occurs on CastExprBranches;

concrete production nilCastExprBranches_c
top::CastExprBranches ::=
{
	top.upSubst = top.downSubst;

	top.branches = [];
	top.branchTypes = [];
}

concrete production snocCastExprBranches_c
top::CastExprBranches ::= r::CastExprBranches b::CastExprBranch 
{
	r.downSubst = top.downSubst;
	b.downSubst = r.upSubst;
	top.upSubst = b.upSubst;
	r.finalSubst = top.finalSubst;
	b.finalSubst = top.finalSubst;

	top.branches = [b] ++ r.branches;
	top.branchTypes = if containsBy(typeEq, b.branchType, r.branchTypes)
					  then r.branchTypes
					  else [b.branchType] ++ r.branchTypes;
}
