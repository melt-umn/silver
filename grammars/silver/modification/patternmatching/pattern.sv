grammar silver:modification:patternmatching;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax only typerepType;
import silver:analysis:typechecking;
import silver:analysis:typechecking:core only upSubst, downSubst, finalSubst;
import silver:modification:let_fix;

terminal Case_kwd 'case' lexer classes {KEYWORD};
terminal Of_kwd 'of' lexer classes {KEYWORD};
terminal Arrow_kwd '->' precedence = 7;
terminal Vbar_kwd '|' precedence = 3;
terminal Opt_Vbar_t /\|?/ ; -- optional Coq-style vbar.

-- The head pattern of a match rule
synthesized attribute headPattern :: Decorated Pattern;
-- Turns PatternList into [Pattern]
synthesized attribute patternList :: [Decorated Pattern];
-- Turns MRuleList into [MatchRule]
synthesized attribute matchRuleList :: [Decorated MatchRule];


-- MR | ...
nonterminal MRuleList with pp, env, file, matchRuleList;
-- P -> E
nonterminal MatchRule with pp, env, file, location, headPattern;

-- P , ...
nonterminal PatternList with pp, patternList, env, file;

concrete production caseExpr_c
top::Expr ::= 'case' es::Exprs 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.pp = "case " ++ es.pp ++ " of " ++ ml.pp ++ " end";
  top.location = loc(top.file, $1.line, $1.column);
  
  -- introduce the failure case here.
  forwards to 
    caseExpr(top.location, es.rawExprs, ml.matchRuleList, 
      productionApp(baseExpr(qName(top.location, "core:error")),
        '(', exprsSingle(stringConst(terminal(String_t, 
          "\"Error: pattern match failed at " ++ top.grammarName ++ " " ++ top.location.unparse ++ "\\n\""))), ')'));
}

abstract production caseExpr
top::Expr ::= locat:: Location es::[Expr] ml::[Decorated MatchRule] failExpr::Expr
{
  top.location = locat;

  -- 4 cases: no patterns left, all constructors, all variables, or mixed con/var.
  forwards to
    case ml of
    | matchRule(_, [], e) :: _ -> e
    | _ -> if null(varRules) then allConCase
           else if null(prodRules) then allVarCase
           else mixedCase
    end;
  
  top.errors <-
    case ml of
    -- are there multiple match rules, with no patterns left to distinguish between them?
    | matchRule(_, [], e) :: _ :: _ -> [err(locat, "Pattern has overlapping cases!")]
    | _ -> []
    end;
    
  -- TODO: problem: check patternlist size and size of 'es'!
       
--  top.errors <- unsafeTrace([], 
--     print(top.pp ++ "\n\n", unsafeIO()));

  local attribute partMRs :: Pair<[Decorated MatchRule] [Decorated MatchRule]>;
  partMRs = partition(isVarMatchRule, ml);
  local varRules :: [Decorated MatchRule] = partMRs.fst;
  local prodRules ::[Decorated MatchRule] = partMRs.snd;
  
  {--
   - All constructors? Then do a real primitive match.
   -}
  local attribute allConCase :: Expr;
  allConCase = matchPrimitive(locat, head(es),
                              typerepType(errorType()),
                              allConCaseTransform(tail(es), failExpr, groupMRules(prodRules)),
                              failExpr);
  
  {--
   - All variables? Just push a let binding inside each branch.
   -}
  local attribute allVarCase :: Expr;
  allVarCase = caseExpr(locat, tail(es),
                        allVarCaseTransform(head(es), errorType(), ml),
                        failExpr);
  
  {--
   - Mixed con/var? Partition, and push the vars into the "fail" branch.
   - Use a let for it, to avoid code duplication!
   -}
  local attribute freshFailName :: String;
  freshFailName = "__fail_" ++ toString(genInt());
  local attribute mixedCase :: Expr;
  mixedCase = makeLet(top.location,
                freshFailName, errorType(), caseExpr(locat, es, varRules, failExpr),
                caseExpr(locat, es, prodRules, baseExpr(qName(top.location, freshFailName))));
}

concrete production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.pp = m.pp;
  
  top.matchRuleList = [m];
}

concrete production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.pp = h.pp ++ " | " ++ t.pp;
  
  top.matchRuleList = h :: t.matchRuleList;
}

concrete production matchRule_c
top::MatchRule ::= pt::PatternList '->' e::Expr
{
  forwards to matchRule(loc(top.file, $2.line, $2.column), pt.patternList, e);
}

abstract production matchRule
top::MatchRule ::= l:: Location pl::[Decorated Pattern] e::Expr
{
  top.pp = implode(", ", map(getPatternPP, pl)) ++ " -> " ++ e.pp;
  top.location = e.location;

  top.headPattern = head(pl);
}

concrete production patternList_one
top::PatternList ::= p::Pattern
{
  top.pp = p.pp;
  top.patternList = [p];
}
concrete production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
  top.pp = ps1.pp ++ ", " ++ p.pp;
  top.patternList = p :: ps1.patternList;
}

terminal Epsilon_For_Location //;
-- lol, dangling comma bug TODO
concrete production patternList_nil
top::PatternList ::= Epsilon_For_Location
{
  top.pp = "";
  top.patternList = [];
}

----------------------------------------------------
-- Added Functions
----------------------------------------------------

function isVarMatchRule
Boolean ::= mr::Decorated MatchRule
{
  return mr.headPattern.patternIsVariable;
}
function getPatternPP
String ::= p::Decorated Pattern
{
  return p.pp;
}
function patternListVars
[String] ::= p::[Decorated Pattern]
{
  return case p of
  | [] -> []
  | varPattern(pvn)::t -> ["__sv_sc_" ++ toString(genInt()) ++ pvn.name] ++ patternListVars(t)
  | h::t -> ["__sv_tmp_pv_" ++ toString(genInt())] ++ patternListVars(t)
  end;
}
function convStringsToVarBinders
VarBinders ::= s::[String] l:: Location
{
  local attribute f::VarBinder;
  f = varVarBinder(nameIdLower(terminal(IdLower_t, head(s), l.line, l.column)));
  return if null(s) then nilVarBinder(terminal(Epsilon_For_Location, "", l.line, l.column))
         else if null(tail(s)) then oneVarBinder(f)
         else consVarBinder(f, ',', convStringsToVarBinders(tail(s), l));
}
function convStringsToExprs
[Expr] ::= s::[String] tl::[Expr] l:: Location
{
  return if null(s) then tl
         else baseExpr(qName(l, head(s))) :: convStringsToExprs(tail(s), tl, l);
}

function allConCaseTransform
PrimPatterns ::= restExprs::[Expr]  failCase::Expr  mrs::[[Decorated MatchRule]]
{
  -- okay, so we're looking at mrs groups by production.
  -- So what we want to do is, for each list in mrs,
  -- generate a PrimPattern on the production that is that group.
  -- Then, push ALL the match rules into a case underneath that.
  
  local attribute names :: [String];
  names = patternListVars(head(head(mrs)).headPattern.patternSubPatternList);

  local attribute subcase :: Expr;
  subcase =  caseExpr(head(head(mrs)).location,
                      convStringsToExprs(names, restExprs, head(head(mrs)).location),
                      tailNestedPatternTransform(head(mrs)),
                      failCase);

  local attribute fstPat :: PrimPattern;
  fstPat = case head(head(mrs)).headPattern of
             prodAppPattern(qn,_,_,_) -> prodPattern(qn, '(', convStringsToVarBinders(names, head(head(mrs)).location), ')', '->', subcase)
           | intPattern(it) -> integerPattern(it, '->', subcase)
           | strPattern(it) -> stringPattern(it, '->', subcase)
           | truePattern(_) -> booleanPattern("true", '->', subcase)
           | falsePattern(_) -> booleanPattern("false", '->', subcase)
           | nilListPattern(_,_) -> nilPattern(subcase)
           | consListPattern(h,_,t) -> conslstPattern(head(names), head(tail(names)), subcase)
           end;
  
  return if null(tail(mrs)) then onePattern(fstPat)
         else consPattern(fstPat, '|', allConCaseTransform(restExprs, failCase, tail(mrs)));
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

  return if null(lst) then []
         else fst :: allVarCaseTransform(headExpr, headType, tail(lst));
}

function makeLet
Expr ::= l:: Location s::String t::TypeExp e::Expr o::Expr
{
  return letp(l, assignExpr(nameIdLower(terminal(IdLower_t, s)), '::', typerepType(t), '=', e), o);
}

function ensureDecoratedExpr
Expr ::= e::Decorated Expr
{
  local attribute et :: TypeExp;
  et = performSubstitution(e.typerep, e.upSubst);

  return if et.isDecorable
         then decorateExprWithEmpty('decorate', new(e), 'with', '{', '}')
         else new(e);
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

