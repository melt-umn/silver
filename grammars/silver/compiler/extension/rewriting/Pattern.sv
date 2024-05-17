grammar silver:compiler:extension:rewriting;

synthesized attribute transform<a>::a;

attribute transform<Strategy> occurs on MRuleList, MatchRule;

{-
 - "Polymorphic" rules are ones in which the LHS matches some well-typed terms
 - that are not permitted by the type of the rule, due to the presence of type
 - variables - such rules require an additional run-time type check before
 - attempting to match.  For example,
 -   rule on Pair<a a> of
 -     pair(x, y) -> pair(y, x)
 -   end
 - matches only pairs where the parameters are the same type; this rule must
 - fail when applied to pair(3, "a") because performing the rewrite would lead
 - to the construction of a type-unsafe tree.  More generally, a rule is
 - polymorphic if the type of a variable/wildcard pattern depends on the rule
 - type.
 - 
 - Since type inference is done by the primitive match extension, detailed
 - type information is not available here, and so we can "cheat" by being
 - slightly more liberal with our analysis, for potentially a slight penalty to
 - performance (by performing superfluous type checks) - a pattern might be
 - polymorphic if either of the following hold:
 -   * It is a variable/wildcard pattern
 -   * A constructor pattern has a polymorphic pattern as an argument
 -   corresponding to a parameter containing type variables that also occur in
 -   the constructor's output type (note that this means existential type
 -   variables and GADT productions with fully concrete output types are OK, as
 -   their expected output type cannot affect the type of the arguments)
 -}
synthesized attribute isPolymorphic :: Boolean occurs on MRuleList, MatchRule, PatternList, Pattern, NamedPatternList, NamedPattern;
inherited attribute typeHasUniversalVars :: Boolean occurs on Pattern;
inherited attribute typesHaveUniversalVars :: [Boolean] occurs on PatternList;
inherited attribute namedTypesHaveUniversalVars :: [(String, Boolean)] occurs on NamedPatternList, NamedPattern;

synthesized attribute wrappedMatchRuleList :: [AbstractMatchRule] occurs on MRuleList, MatchRule;

inherited attribute decRuleExprsIn::[(String, Decorated Expr with {decorate, decSiteVertexInfo, boundVars})] occurs on MRuleList, MatchRule;
inherited attribute ruleIndex::Integer occurs on MRuleList, MatchRule;

propagate decRuleExprsIn on MRuleList;
propagate namedTypesHaveUniversalVars on NamedPatternList, NamedPattern;

aspect production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.transform = m.transform;
  top.isPolymorphic = m.isPolymorphic;
  top.wrappedMatchRuleList = m.wrappedMatchRuleList;
  m.ruleIndex = top.ruleIndex;
}

aspect production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.transform = h.transform <+ t.transform;
  top.isPolymorphic = h.isPolymorphic || t.isPolymorphic;
  top.wrappedMatchRuleList = h.wrappedMatchRuleList ++ t.wrappedMatchRuleList;
  h.ruleIndex = top.ruleIndex;
  t.ruleIndex = top.ruleIndex + 1;
}

aspect production matchRule_c
top::MatchRule ::= pt::PatternList _ e::Expr
{
  -- Awful hack: pattern match type checking is happens on the forward "primitive match".
  -- However, we are translating on the pattern matching extension syntax,
  -- so we need the Decorated Expr here.
  -- Solution: extract the Decorated Exprs from the case expression and compute
  -- the translation on them.
  top.transform =
    rewriteRule(
      pt.firstTransform,
      case lookup(toString(top.ruleIndex), top.decRuleExprsIn) of
      | just(e) -> e.transform
      | nothing() -> error("Failed to find decorated RHS " ++ toString(top.ruleIndex))
      end);
  
  top.isPolymorphic = head(pt.patternList).patternIsVariable || pt.isPolymorphic;
  pt.typesHaveUniversalVars = [true];
  
  top.wrappedMatchRuleList =
    [matchRule(
      pt.patternList, nothing(),
      hackWrapKey(toString(top.ruleIndex), new(e)))];
}

aspect production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr _ e::Expr
{
  top.transform =
    require(
      pt.firstTransform,
      case lookup(toString(top.ruleIndex) ++ "_cond", top.decRuleExprsIn) of
      | just(e) -> e.transform
      | nothing() -> error("Failed to find decorated RHS " ++ toString(top.ruleIndex) ++ "_cond")
      end) <*
    rewriteRule(
      pt.firstTransform,
      case lookup(toString(top.ruleIndex), top.decRuleExprsIn) of
      | just(e) -> e.transform
      | nothing() -> error("Failed to find decorated RHS " ++ toString(top.ruleIndex))
      end);
  
  top.isPolymorphic = head(pt.patternList).patternIsVariable || pt.isPolymorphic;
  pt.typesHaveUniversalVars = [true];
  
  top.wrappedMatchRuleList =
    [matchRule(
      pt.patternList,
      just((hackWrapKey(toString(top.ruleIndex) ++ "_cond", new(cond)), nothing())),
      hackWrapKey(toString(top.ruleIndex), new(e)))];
}

aspect production matchRuleWhenMatches_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr 'matches' p::Pattern _ e::Expr
{
  top.transform =
    require(
      pt.firstTransform,
      matchASTExpr(
        case lookup(toString(top.ruleIndex) ++ "_cond", top.decRuleExprsIn) of
        | just(e) -> e.transform
        | nothing() -> error("Failed to find decorated RHS " ++ toString(top.ruleIndex) ++ "_cond")
        end,
        p.transform, booleanASTExpr(true), booleanASTExpr(false))) <*
    rewriteRule(
      pt.firstTransform,
      case lookup(toString(top.ruleIndex), top.decRuleExprsIn) of
      | just(e) -> e.transform
      | nothing() -> error("Failed to find decorated RHS " ++ toString(top.ruleIndex))
      end);
  
  top.isPolymorphic = head(pt.patternList).patternIsVariable || pt.isPolymorphic;
  pt.typesHaveUniversalVars = [true];
  
  top.wrappedMatchRuleList =
    [matchRule(
      pt.patternList,
      just((hackWrapKey(toString(top.ruleIndex) ++ "_cond", new(cond)), just(p))),
      hackWrapKey(toString(top.ruleIndex), new(e)))];
}

abstract production hackWrapKey
top::Expr ::= key::String e::Expr
{
  top.unparse = s"key(${key}, ${e.unparse})";
  top.decRuleExprs = [(key, forward)];
  forwards to @e;
}

aspect production caseExpr_c
top::Expr ::= 'case' es::Exprs 'of' _ ml::MRuleList 'end'
{
  ml.ruleIndex = 0;
}

attribute transform<ASTPatterns> occurs on PatternList;
synthesized attribute firstTransform::ASTPattern occurs on PatternList;

aspect production patternList_one
top::PatternList ::= p::Pattern
{
  top.transform = consASTPattern(p.transform, nilASTPattern());
  top.firstTransform = p.transform;
  top.isPolymorphic = p.isPolymorphic;
  p.typeHasUniversalVars =
    case top.typesHaveUniversalVars of
    | h :: _ -> h
    | _ -> false
    end;
}
aspect production patternList_more
top::PatternList ::= p::Pattern ',' ps::PatternList
{
  top.transform = consASTPattern(p.transform, ps.transform);
  top.firstTransform = p.transform;
  top.isPolymorphic = p.isPolymorphic || ps.isPolymorphic;
  p.typeHasUniversalVars =
    case top.typesHaveUniversalVars of
    | h :: _ -> h
    | _ -> false
    end;
  ps.typesHaveUniversalVars =
    case top.typesHaveUniversalVars of
    | _ :: t -> t
    | _ -> []
    end;
}

aspect production patternList_nil
top::PatternList ::=
{
  top.transform = nilASTPattern();
  top.firstTransform = error("Empty pattern list");
  top.isPolymorphic = false;
}

attribute transform<NamedASTPatterns> occurs on NamedPatternList;

aspect production namedPatternList_one
top::NamedPatternList ::= p::NamedPattern
{
  top.transform = consNamedASTPattern(p.transform, nilNamedASTPattern());
  top.isPolymorphic = p.isPolymorphic;
}
aspect production namedPatternList_more
top::NamedPatternList ::= p::NamedPattern ',' ps::NamedPatternList
{
  top.transform = consNamedASTPattern(p.transform, ps.transform);
  top.isPolymorphic = p.isPolymorphic || ps.isPolymorphic;
}

aspect production namedPatternList_nil
top::NamedPatternList ::=
{
  top.transform = nilNamedASTPattern();
  top.isPolymorphic = false;
}

attribute transform<NamedASTPattern> occurs on NamedPattern;

aspect production namedPattern
top::NamedPattern ::= qn::QName '=' p::Pattern
{
  top.transform = namedASTPattern(qn.lookupAttribute.fullName, p.transform);
  top.isPolymorphic = p.isPolymorphic;
  p.typeHasUniversalVars =
    fromMaybe(
      -- Should be an internal error, but error checking for annotation patterns is broken,
      -- so we might demand a transform from a pattern that mentions an annotation that the
      -- nonterminal type doesn't have.
      -- See the comment on the silver:compiler:extension:patternmatching:namedPattern production.
      false,
      lookup(last(explode(":", qn.name)), top.namedTypesHaveUniversalVars));
}

attribute transform<ASTPattern> occurs on Pattern;

aspect production prodAppPattern_named
top::Pattern ::= prod::QName '(' ps::PatternList ',' nps::NamedPatternList ')'
{
  top.transform =
    prodCallASTPattern(prod.lookupValue.fullName, ps.transform, nps.transform);
  top.isPolymorphic = ps.isPolymorphic || nps.isPolymorphic;
  
  local prodType::Type = prod.lookupValue.typeScheme.typerep;
  local outputFreeVars::[TyVar] = prodType.outputType.freeVariables;
  ps.typesHaveUniversalVars =
    map(
      \ t::Type -> !null(intersect(outputFreeVars, t.freeVariables)),
      prodType.inputTypes);
  nps.namedTypesHaveUniversalVars =
    map(
      \ t::Pair<String Type> ->
        (t.fst, !null(intersect(outputFreeVars, t.snd.freeVariables))),
      prodType.namedTypes);
} 

aspect production wildcPattern
top::Pattern ::= '_'
{
  top.transform = wildASTPattern();
  top.isPolymorphic = top.typeHasUniversalVars;
}

aspect production varPattern
top::Pattern ::= v::Name
{
  top.transform = varASTPattern(v.name);
  top.isPolymorphic = top.typeHasUniversalVars;
}

aspect production errorPattern
top::Pattern ::= msg::[Message]
{
  top.transform = error("transform undefined in the presence of errors");
  top.isPolymorphic = false;
}

aspect production intPattern
top::Pattern ::= num::Int_t
{
  top.transform = integerASTPattern(toInteger(num.lexeme));
  top.isPolymorphic = false;
}

aspect production fltPattern
top::Pattern ::= num::Float_t
{
  top.transform = floatASTPattern(toFloat(num.lexeme));
  top.isPolymorphic = false;
}

aspect production strPattern
top::Pattern ::= str::String_t
{
  top.transform = stringASTPattern(unescapeString(substring(1, length(str.lexeme) - 1, str.lexeme)));
  top.isPolymorphic = false;
}

aspect production truePattern
top::Pattern ::= 'true'
{
  top.transform = booleanASTPattern(true);
  top.isPolymorphic = false;
}

aspect production falsePattern
top::Pattern ::= 'false'
{
  top.transform = booleanASTPattern(false);
  top.isPolymorphic = false;
}

aspect production nilListPattern
top::Pattern ::= '[' ']'
{
  top.transform = nilListASTPattern();
  top.isPolymorphic = top.typeHasUniversalVars;
}

aspect production consListPattern
top::Pattern ::= hp::Pattern '::' tp::Pattern
{
  top.transform = consListASTPattern(hp.transform, tp.transform);
  
  -- Slight special case optimization for lists:
  -- :: has type ([a] ::= a [a]), so only polymorphic if both args are as well. 
  top.isPolymorphic = hp.isPolymorphic && tp.isPolymorphic;
  hp.typeHasUniversalVars = top.typeHasUniversalVars;
  tp.typeHasUniversalVars = top.typeHasUniversalVars;
}

-- Primitive pattern stuff
aspect production onePattern
top::PrimPatterns ::= p::PrimPattern
{
  top.decRuleExprs = p.decRuleExprs;
}
aspect production consPattern
top::PrimPatterns ::= p::PrimPattern '|' ps::PrimPatterns
{
  top.decRuleExprs = p.decRuleExprs ++ ps.decRuleExprs;
}

aspect production prodPatternNormal
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
  e.boundVars = top.boundVars ++ ns.varBindings;
}

aspect production prodPatternGadt
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
  e.boundVars = top.boundVars ++ ns.varBindings;
}

aspect production integerPattern
top::PrimPattern ::= i::Int_t _ e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}
aspect production floatPattern
top::PrimPattern ::= f::Float_t _ e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}
aspect production stringPattern
top::PrimPattern ::= s::String_t _ e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}
aspect production booleanPattern
top::PrimPattern ::= i::String _ e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}
aspect production nilPattern
top::PrimPattern ::= e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}
aspect production conslstPattern
top::PrimPattern ::= h::Name t::Name e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}

synthesized attribute varBindings::[Pair<String Boolean>] occurs on VarBinders, VarBinder;

aspect production oneVarBinder
top::VarBinders ::= v::VarBinder
{
  top.varBindings = v.varBindings;
}
aspect production consVarBinder
top::VarBinders ::= v::VarBinder ',' vs::VarBinders
{
  top.varBindings = v.varBindings ++ vs.varBindings;
}
aspect production nilVarBinder
top::VarBinders ::=
{
  top.varBindings = [];
}

aspect production varVarBinder
top::VarBinder ::= n::Name
{
  top.varBindings = [(n.name, performSubstitution(top.bindingType, top.finalSubst).isDecorated)];
}
aspect production ignoreVarBinder
top::VarBinder ::= '_'
{
  top.varBindings = [];
}
