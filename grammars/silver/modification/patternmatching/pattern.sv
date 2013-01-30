grammar silver:modification:patternmatching;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax only typerepType;
--import silver:analysis:typechecking:core only upSubst, downSubst, finalSubst;
import silver:modification:let_fix;

terminal Case_kwd 'case' lexer classes {KEYWORD};
terminal Of_kwd 'of' lexer classes {KEYWORD};
terminal Arrow_kwd '->' precedence = 7;
terminal Vbar_kwd '|' precedence = 3;
terminal Opt_Vbar_t /\|?/ ; -- optional Coq-style vbar.

-- The head pattern of a match rule
synthesized attribute headPattern :: Decorated Pattern;
-- Whether the head pattern of a match rule is a variable binder or not
synthesized attribute isVarMatchRule :: Boolean;
-- Turns PatternList into [Pattern]
synthesized attribute patternList :: [Decorated Pattern];
-- Turns MRuleList into [MatchRule]
synthesized attribute matchRuleList :: [Decorated MatchRule];


-- MR | ...
nonterminal MRuleList with config, pp, signature, env, file, matchRuleList, errors;
-- P -> E
nonterminal MatchRule with config, pp, signature, env, file, location, headPattern, errors, isVarMatchRule;

-- P , ...
nonterminal PatternList with config, pp, patternList, env, file, errors;

{- NOTE ON ERRORS: #HACK2012
 -
 - All of the real error checking should be done in PrimitiveMatch.sv on the
 - more primitive form of pattern matching. BUT, there are a few
 - kinds of errors that the pattern matching compiler will OBSCURE
 - and so we must check for them here.
 -
 - ANY error on MRuleList, MatchRule, PatternList, or Pattern should
 - be accompanied by a comment explaining why it's there, and not on
 - primitive match.
 -}


concrete production caseExpr_c
top::Expr ::= 'case' es::Exprs 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  -- TODO: causes problems with ring. Investigate!
  top.pp = "case " ++ es.pp ++ " of " ++ ml.pp ++ " end";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors <- ml.errors;
  
  -- TODO: this is the only use of .rawExprs. FIXME
  -- introduce the failure case here.
  forwards to 
    caseExpr(top.location, es.rawExprs, ml.matchRuleList, 
      mkFunctionInvocation(baseExpr(qName(top.location, "core:error")),
        [stringConst(terminal(String_t, 
          "\"Error: pattern match failed at " ++ top.grammarName ++ " " ++ top.location.unparse ++ "\\n\""))]),
      freshType());
}

abstract production caseExpr
top::Expr ::= locat::Location es::[Expr] ml::[Decorated MatchRule] failExpr::Expr retType::TypeExp
{
  top.pp = error("Internal error: pretty of intermediate data structure");
  top.location = locat;

  -- 4 cases: no patterns left, all constructors, all variables, or mixed con/var.
  -- errors cases: more patterns no scrutinees, more scrutinees no patterns, no scrutinees multiple rules
  forwards to
    case ml of
    | matchRule(_, [], e) :: _ -> e -- valid or error case
    | _ -> if null(es) then failExpr -- error case
           else if null(varRules) then allConCase
           else if null(prodRules) then allVarCase
           else mixedCase
    end;
  -- TODO: BUG: we're using the left of patterns in the first match rule as a guide here
  -- which means we run into serious problems if not all match rules agree on the length
  -- of the pattern list. We don't report some errors related to not having enough
  -- variable binders
  
  top.errors <-
    case ml of
    -- are there multiple match rules, with no patterns left to distinguish between them?
    | matchRule(_, [], e) :: _ :: _ -> [err(locat, "Pattern has overlapping cases!")]
    -- Is there just one rule but uhhh, we've got multiple expressions!?
    | matchRule(_, [], _) :: [] -> if null(es) then [] else [err(locat, "Fewer that expected patterns in pattern list")]
    | _ -> if null(es) then [err(locat, "More than expected patterns in pattern list")] else []
    end;
    
  -- TODO: problem: check patternlist size and size of 'es'!
       
--  top.errors <- unsafeTrace([], 
--     print(top.pp ++ "\n\n", unsafeIO()));

  local attribute partMRs :: Pair<[Decorated MatchRule] [Decorated MatchRule]>;
  partMRs = partition((.isVarMatchRule), ml);
  local varRules :: [Decorated MatchRule] = partMRs.fst;
  local prodRules ::[Decorated MatchRule] = partMRs.snd;
  
  {--
   - All constructors? Then do a real primitive match.
   -}
  local attribute allConCase :: Expr;
  allConCase = matchPrimitive(locat, head(es),
                              typerepType(retType),
                              allConCaseTransform(tail(es), failExpr, retType, groupMRules(prodRules)),
                              failExpr);
  
  {--
   - All variables? Just push a let binding inside each branch.
   -}
  local attribute allVarCase :: Expr;
  allVarCase = caseExpr(locat, tail(es),
                        allVarCaseTransform(head(es), freshType(){-whatever the first expression's type is?-}, ml),
                        failExpr, retType);
  
  {--
   - Mixed con/var? Partition, and push the vars into the "fail" branch.
   - Use a let for it, to avoid code duplication!
   -}
  local attribute freshFailName :: String;
  freshFailName = "__fail_" ++ toString(genInt());
  local attribute mixedCase :: Expr;
  mixedCase = makeLet(top.location,
                freshFailName, retType, caseExpr(locat, es, varRules, failExpr, retType),
                caseExpr(locat, es, prodRules, baseExpr(qName(top.location, freshFailName)), retType));
}

concrete production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.pp = m.pp;
  top.errors := m.errors;  

  top.matchRuleList = [m];
}

concrete production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.pp = h.pp ++ " | " ++ t.pp;
  top.errors := h.errors ++ t.errors;
  
  top.matchRuleList = h :: t.matchRuleList;
}

concrete production matchRule_c
top::MatchRule ::= pt::PatternList '->' e::Expr
{
  top.pp = pt.pp ++ " -> " ++ e.pp;
  -- UNCOMMENT if no longer forwarding to matchRule #HACK2012
  --top.errors <- pt.errors;

  forwards to matchRule(loc(top.file, $2.line, $2.column), pt.patternList, e);
}

abstract production matchRule
top::MatchRule ::= l::Location pl::[Decorated Pattern] e::Expr
{
  top.pp = implode(", ", map((.pp), pl)) ++ " -> " ++ e.pp;
  top.errors := foldr(append,[],map((.errors), pl));
  top.location = l;

  top.headPattern = head(pl);
  -- Here we return true if we have no patterns: essentially, claim missing
  -- rules are '_'
  top.isVarMatchRule = null(pl) || head(pl).patternIsVariable;
}

concrete production patternList_one
top::PatternList ::= p::Pattern
{
  top.pp = p.pp;
  top.errors := p.errors;

  top.patternList = [p];
}
concrete production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
  top.pp = p.pp ++ ", " ++ ps1.pp;
  top.errors := p.errors ++ ps1.errors;

  top.patternList = p :: ps1.patternList;
}

terminal Epsilon_For_Location //;
-- lol, dangling comma bug TODO
concrete production patternList_nil
top::PatternList ::= Epsilon_For_Location
{
  top.pp = "";
  top.errors := [];

  top.patternList = [];
}

----------------------------------------------------
-- Added Functions
----------------------------------------------------

function patternListVars
Name ::= p::Decorated Pattern
{
  local n :: String =
    case p of
    | varPattern(pvn) -> "__sv_pv_" ++ toString(genInt()) ++ "_" ++ pvn.name
    | h -> "__sv_tmp_pv_" ++ toString(genInt())
    end;
  return nameIdLower(terminal(IdLower_t, n, p.location.line, p.location.column));
}
function convStringsToVarBinders
VarBinders ::= s::[Name] l::Location
{
  return if null(s) then nilVarBinder(terminal(Epsilon_For_Location, "", l.line, l.column))
         else if null(tail(s)) then oneVarBinder(varVarBinder(head(s)))
         else consVarBinder(varVarBinder(head(s)), ',', convStringsToVarBinders(tail(s), l));
}
function convStringsToExprs
[Expr] ::= s::[Name] tl::[Expr]
{
  return if null(s) then tl
         else baseExpr(qNameId(head(s))) :: convStringsToExprs(tail(s), tl);
}

function allConCaseTransform
PrimPatterns ::= restExprs::[Expr]  failCase::Expr  retType::TypeExp  mrs::[[Decorated MatchRule]]
{
  -- okay, so we're looking at mrs groups by production.
  -- So what we want to do is, for each list in mrs,
  -- generate a PrimPattern on the production that is that group.
  -- Then, push ALL the match rules into a case underneath that.
  
  -- TODO: head(head(mrs)).location is probably not the correct thing to use here??
  
  local attribute names :: [Name];
  names = map(patternListVars, head(head(mrs)).headPattern.patternSubPatternList);

  local attribute subcase :: Expr;
  subcase =  caseExpr(head(head(mrs)).location,
                      convStringsToExprs(names, restExprs),
                      tailNestedPatternTransform(head(mrs)),
                      failCase, retType);

  local attribute fstPat :: PrimPattern;
  fstPat = case head(head(mrs)).headPattern of
           | prodAppPattern(qn,_,_,_) -> prodPattern(qn, '(', convStringsToVarBinders(names, head(head(mrs)).location), ')', '->', subcase)
           | intPattern(it) -> integerPattern(it, '->', subcase)
           | strPattern(it) -> stringPattern(it, '->', subcase)
           | truePattern(_) -> booleanPattern("true", '->', subcase)
           | falsePattern(_) -> booleanPattern("false", '->', subcase)
           | nilListPattern(_,_) -> nilPattern(subcase)
           | consListPattern(h,_,t) -> conslstPattern(head(names), head(tail(names)), subcase)
           end;
  
  return if null(tail(mrs)) then onePattern(fstPat)
         else consPattern(fstPat, '|', allConCaseTransform(restExprs, failCase, retType, tail(mrs)));
}

function tailNestedPatternTransform
[Decorated MatchRule] ::= lst::[Decorated MatchRule]
{
  -- TODO: this is a bit hacky, and potentially unnecessary... what with the redecorating and all.
  local attribute fst :: MatchRule;
  fst = case head(lst) of
          matchRule(l, pl,e) -> matchRule(l, head(pl).patternSubPatternList ++ tail(pl), e)
        end;
  fst.env = head(lst).env;
  fst.file = head(lst).file;
  fst.signature = head(lst).signature;
  fst.config = head(lst).config;
  
  return if null(lst) then []
         else fst :: tailNestedPatternTransform(tail(lst));
}

function allVarCaseTransform
[Decorated MatchRule] ::= headExpr::Expr  headType::TypeExp  lst::[Decorated MatchRule]
{
  -- TODO: this is a bit hacky, and potentially unnecessary... what with the redecorating and all.
  local attribute fst :: MatchRule;
  fst = case head(lst) of
          matchRule(l, pl, e) -> matchRule(l, tail(pl), 
                             case head(pl).patternVariableName of
                               just(pvn) -> makeLet(head(lst).location, pvn, headType, headExpr, e)
                             | nothing() -> e
                             end)
        end;
  fst.env = head(lst).env;
  fst.file = head(lst).file;
  fst.signature = head(lst).signature;
  fst.config = head(lst).config;

  return if null(lst) then []
         else fst :: allVarCaseTransform(headExpr, headType, tail(lst));
}

function makeLet
Expr ::= l::Location s::String t::TypeExp e::Expr o::Expr
{
  return letp(l, assignExpr(nameIdLower(terminal(IdLower_t, s)), '::', typerepType(t), '=', e), o);
}

-- Please note that this function is now specifically for decorating with intention to access a forward!
function ensureDecoratedExpr
Expr ::= e::Decorated Expr
{
  local attribute et :: TypeExp;
  et = performSubstitution(e.typerep, e.upSubst);

  return if et.isDecorable
         then decorateExprWithIntention(e.location, exprRef(e), exprInhsEmpty(), ["forward"])
         else exprRef(e);
}
function ensureDecoratedType
TypeExp ::= e::Decorated Expr
{
  local attribute et :: TypeExp;
  et = performSubstitution(e.typerep, e.upSubst);

  return if et.isDecorable
         then decoratedTypeExp(et)
         else et;
}
function mruleEqForGrouping
Boolean ::= a::Decorated MatchRule b::Decorated MatchRule
{
  return a.headPattern.patternSortKey == b.headPattern.patternSortKey;
}
function mruleLTEForSorting
Boolean ::= a::Decorated MatchRule b::Decorated MatchRule
{
  return a.headPattern.patternSortKey <= b.headPattern.patternSortKey;
}
function groupMRules
[[Decorated MatchRule]] ::= l::[Decorated MatchRule]
{
  return groupBy(mruleEqForGrouping, sortBy(mruleLTEForSorting, l));
}


