grammar silver:modification:patternmatching;
import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:analysis:typechecking:core;
import silver:analysis:typechecking;
import silver:modification:let_fix;
import silver:extension:list; -- Oh no, this is a hack! TODO

terminal Case_kwd 'case' lexer classes {KEYWORD};
terminal Of_kwd 'of' lexer classes {KEYWORD};
terminal Arrow_kwd '->' precedence = 7 ;
terminal Vbar_kwd '|' precedence = 3 ;

-- TODO: all of these can be removed if transform var case doesn't want them anymore.
autocopy attribute patternListTypes :: [ TypeExp ] ;
autocopy attribute patternListScrutinees :: [Expr];

-- Whether or not the head pattern is a variable or wildcard
synthesized attribute patternIsVariable :: Boolean;
-- The head pattern of a rule or pattern list.
synthesized attribute headPattern :: Decorated Pattern;
synthesized attribute patternListEmpty :: Boolean;
-- The production a pattern matches on, if it's a prod pattern.
synthesized attribute patternProduction :: Maybe<String>;
synthesized attribute patternVariableName :: Maybe<String>;
synthesized attribute patternSubPatternList :: Decorated PatternList;
synthesized attribute varTailPatternList :: PatternList;

synthesized attribute patternListVars :: [String];


-- These could possibly be replaced with partition & stuff
synthesized attribute prodRules :: [Decorated MatchRule];
synthesized attribute varRules :: [Decorated MatchRule];

synthesized attribute transformVarCase<a> :: a;



-- MR | ...
nonterminal MRuleList with pp, grammarName, env, file, location, signature, downSubst, finalSubst,
                           patternListTypes, patternListScrutinees, blockContext, prodRules, varRules,
                           transformVarCase<MRuleList> ;
-- P -> E
nonterminal MatchRule with pp, grammarName, env, file, location, signature, downSubst, finalSubst,
                           patternListTypes, patternListScrutinees, blockContext, headPattern, prodRules, varRules,
                           transformVarCase<MatchRule> ;

-- prod(PL) | int | string | bool | ...
nonterminal Pattern with pp, grammarName, env, file, location, signature,
                         blockContext, headPattern, patternIsVariable, patternProduction,
                         patternVariableName, patternListVars, patternSubPatternList ;
-- P , ...
nonterminal PatternList with pp, grammarName, env, file, location, signature,
                             blockContext, headPattern,
                             varTailPatternList, patternListVars, patternListEmpty ;

concrete production caseExpr_c
top::Expr ::= 'case' es::Exprs 'of' ml::MRuleList 'end'
{
  -- We're going to do FUNNY THINGS with types here.
  -- We want to know what type we should return, and we need to know this "directionally"
  -- for GADTs and for just general decoratedness/etc to work out.
  -- So, we're going to report a type variable here:
  top.typerep = errorType(); -- TODO BUG: toString/AttrAccess on case ... end now broken!!
  -- Then, we're going to thread typing contexts in a screwed up fashion:
  es.downSubst = top.downSubst;
  top.upSubst = es.upSubst; -- YEAH, we're sending it up already.
  -- Now, after all the other checking is done, go bother with this:
  forward.downSubst = top.finalSubst;
  -- And finally, let's set their finalSubst(s)
  forward.finalSubst = forward.upSubst;
  -- Now, we can obtain our return type, usually:
  local attribute returnType :: TypeExp;
  returnType = performSubstitution(top.typerep, top.finalSubst);
  -- Hack over. In the distant future, force type information DOWN the tree, instead of accumulating it up. TODO


  -- introduce the failure case here.
  forwards to caseExpr(loc(top.file, $1.line, $1.column), returnType, exprsDecorated(es.exprs), ml, 
                productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t,"core:error")))), '(', 
                  exprsSingle(stringConst(terminal(String_t, "\"Error: pattern match failed.\\n\""))), ')'));
}

abstract production caseExpr
top::Expr ::= locat::Decorated Location returnType::TypeExp es::Exprs ml::MRuleList failExpr::Expr
{
  --top.pp = "case " ++ es.pp ++ " of " ++ ml.pp ++ " end";  
  top.location = locat;

  top.typerep = returnType;
  
  es.downSubst = top.downSubst;
  forward.downSubst = es.upSubst;
  errCheck1.downSubst = forward.upSubst;
  top.upSubst = errCheck1.upSubst;
  -- Necessary, in particular, to check if the expression has the right type when we're looking at
  -- no patterns left, so we just forward to the expr.
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  errCheck1 = check(top.typerep, forward.typerep);

  top.errors <- if errCheck1.typeerror
                then [err(forward.location, "pattern expression should have type " ++ errCheck1.leftpp ++ " instead it has type " ++ errCheck1.rightpp)]
                else [];
  
  -- TODO: this shouldn't be needed anymore
  ml.downSubst = top.downSubst;
  
  ml.patternListTypes = map(ensureDecoratedType, getTypesExprsSubst(es.exprs)); -- subst somehow?
  ml.patternListScrutinees = map(ensureDecoratedExpr, es.exprs);
  
  forwards to {- if type is not nt then ... else -}
          case ml of
            mRuleList_one(matchRule(p, _, e)) -> if p.patternListEmpty then e else normalCase
          | mRuleList_cons(matchRule(p, _, e),_,_) -> if p.patternListEmpty then e else normalCase
          end;
  
  top.errors <-
       case ml of
          mRuleList_cons(matchRule(p, _, e),_,_) -> if p.patternListEmpty then 
             [err(locat, "Pattern has overlapping cases!")] else []
       | _ -> []
       end;
       
--  top.errors <- unsafeTrace([], 
--     print(top.pp ++ "\n\n", unsafeIO()));
  
  local attribute normalCase :: Expr;
  normalCase = 
              if null(ml.varRules)
              then matchPrimitive(locat, new(head(es.exprs)),
                                  typerepType(returnType),
                                    head(ml.patternListTypes).primPatternGenerator(returnType, tail(es.exprs), failExpr, groupMRules(ml.prodRules)),
                                  failExpr)
              else if null(ml.prodRules)
              then allVarCase
              else mixedCase;
  
  local attribute headExpr :: Expr;
  headExpr = new(head(es.exprs));
  local attribute tailExprs :: Exprs;
  tailExprs = exprsDecorated(tail(es.exprs));
  local attribute allVarCase :: Expr;
  allVarCase = caseExpr(locat, returnType, tailExprs, ml.transformVarCase, failExpr);
  
  local attribute freshFailName :: String;
  freshFailName = "__fail_" ++ toString(genInt());
  local attribute mixedCase :: Expr;
  mixedCase = makeLet(freshFailName, returnType, 
                             caseExpr(locat, returnType, es, regenerateMatchRuleList(ml.varRules), failExpr),
                             caseExpr(locat, returnType, es, regenerateMatchRuleList(ml.prodRules),
                              baseExpr(qNameId(nameIdLower(terminal(IdLower_t, freshFailName))))));
}

concrete production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.pp = m.pp;
  top.location = m.location;
  m.downSubst = top.downSubst;
  
  top.varRules = m.varRules;
  top.prodRules = m.prodRules;
  
  top.transformVarCase = mRuleList_one(m.transformVarCase);
}

concrete production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.pp = h.pp ++ " | " ++ t.pp;
  top.location = h.location;

  h.downSubst = top.downSubst;
  t.downSubst = top.downSubst;
  
  top.varRules = h.varRules ++ t.varRules;
  top.prodRules = h.prodRules ++ t.prodRules;
  
  top.transformVarCase = mRuleList_cons(h.transformVarCase, $2, t.transformVarCase);
}

concrete production matchRule
top::MatchRule ::= pt::PatternList '->' e::Expr
{
  top.pp = pt.pp ++ " -> " ++ e.pp;
  top.location = e.location;

  e.downSubst = top.downSubst;
  
  top.headPattern = pt.headPattern;
  top.varRules = if top.headPattern.patternIsVariable then [top] else [];
  top.prodRules = if top.headPattern.patternIsVariable then [] else [top];
  
  top.transformVarCase = matchRule(pt.varTailPatternList, $2, 
                             case pt.headPattern.patternVariableName of
                               just(pvn) -> makeLet(pvn, head(top.patternListTypes), head(top.patternListScrutinees), e)
                             | nothing() -> e
                             end);
}


concrete production patternList_one
top::PatternList ::= p::Pattern
{
  top.pp = p.pp;
  top.location = p.location;

  top.headPattern = p;
  top.patternListEmpty = false;
  top.varTailPatternList = patternList_nil(terminal(Epsilon_For_Location, "", top.location.line, top.location.column));
  top.patternListVars = [case p.patternVariableName of
                           just(f) -> "__sv_sc_" ++ toString(genInt()) ++ f
                         | _ -> "__sv_tmp_pv_" ++ toString(genInt())
                         end];
}

concrete production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
  top.pp = ps1.pp ++ ", " ++ p.pp;
  top.location = p.location;

  top.headPattern = p;
  top.patternListEmpty = false;
  top.varTailPatternList = ps1;
  top.patternListVars = [case p.patternVariableName of
                           just(f) -> "__sv_sc_" ++ toString(genInt()) ++ f
                         | _ -> "__sv_tmp_pv_" ++ toString(genInt())
                         end] ++ ps1.patternListVars;
}

terminal Epsilon_For_Location //;
-- lol, dangling comma bug TODO
concrete production patternList_nil
top::PatternList ::= Epsilon_For_Location
{
  top.pp = "";
  top.location = loc(top.file, $1.line, $1.column);

  top.headPattern = error("empty pattern list consulted regarding its first pattern");
  top.patternListEmpty = true;
  top.varTailPatternList = error("tailed (var) pattern list that is empty.");
  top.patternListVars = [];
}

--------------------------------------------------------------------------------


concrete production prodAppPattern
p::Pattern ::= prod::QName '(' ps::PatternList ')'
{
  p.pp = prod.pp ++ "(" ++ ps.pp ++ ")" ;
  p.location = prod.location;

  p.patternIsVariable = false;
  p.patternProduction = just(prod.lookupValue.fullName);
  p.patternVariableName = nothing();
  p.patternSubPatternList = ps;
} 

concrete production wildcPattern
p::Pattern ::= '_'
{
  p.pp = "_" ;
  p.location = loc(p.file, $1.line, $1.column);

  p.patternIsVariable = true;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
}

concrete production varPattern
p::Pattern ::= v::Name
{
  p.pp = v.name;
  p.location = v.location;

  p.patternIsVariable = true;
  p.patternProduction = nothing();
  p.patternVariableName = just(v.name);
}

----------------------------------------------------
-- Added Functions
----------------------------------------------------

function patternListAppend
PatternList ::= l::PatternList r::PatternList
{
  return case l of
           patternList_one(p) -> patternList_more(p, ',', r)
         | patternList_more(p,c,ps) -> patternList_more(p, c, patternListAppend(ps, r))
         | patternList_nil(_) -> r
         end;
}
function patternListTail
PatternList ::= l::PatternList
{
  return case l of
           patternList_one(p) -> patternList_nil(terminal(Epsilon_For_Location, "", p.location.line, p.location.column))
         | patternList_more(p,_,ps) -> ps
         | patternList_nil(_) -> error("tail of nil pattern list")
         end;
}
function convStringsToVarBinders
VarBinders ::= s::[String] l::Decorated Location
{
  local attribute f::VarBinder;
  f = varVarBinder(nameIdLower(terminal(IdLower_t, head(s), l.line, l.column)));
  return if null(s) then nilVarBinder(terminal(Epsilon_For_Location, "", l.line, l.column))
         else if null(tail(s)) then oneVarBinder(f)
         else consVarBinder(f, ',', convStringsToVarBinders(tail(s), l));
}
function convStringsToExprs
Exprs ::= s::[String] tl::[Decorated Expr]
{
  local attribute f::Expr;
  f = baseExpr(qNameId(nameIdLower(terminal(IdLower_t, head(s)))));
  return if null(s) then exprsDecorated(tl)
         else exprsCons(f, ',', convStringsToExprs(tail(s), tl));
}
function allConCaseTransform -- a primPatternGenerator
PrimPatterns ::= returnType::TypeExp  restExprs::[Decorated Expr]  failCase::Expr  mrs::[[Decorated MatchRule]]
{
  -- okay, so we're looking at mrs groups by production.
  -- So what we want to do is, for each list in mrs,
  -- generate a PrimPattern on the production that is that group.
  -- Then, push ALL the match rules into a case underneath that.
  
  -- pick out the first mrule of the first list, and get the name (for the list)
  local attribute prodname :: QName;
  prodname = qNameId(nameIdLower(terminal(IdLower_t, head(head(mrs)).headPattern.patternProduction.fromJust)));
  
  -- pick names for
  local attribute names :: [String];
  names = head(head(mrs)).headPattern.patternSubPatternList.patternListVars;
  
  local attribute fstPat :: PrimPattern;
  fstPat = prodPattern(prodname, '(', convStringsToVarBinders(names, head(head(mrs)).location), ')', '->',
                         caseExpr(head(head(mrs)).location, returnType,
                                  convStringsToExprs(names, restExprs),
                                  tailNestedPatternTransform(head(mrs)),
                                  failCase));
  
  return if null(tail(mrs)) then onePattern(fstPat)
         else consPattern(fstPat, '|', allConCaseTransform(returnType, restExprs, failCase, tail(mrs)));
}
function tailNestedPatternTransform
MRuleList ::= mrl::[Decorated MatchRule]
{
  -- map tailRule mrl
  -- Take off head pattern, jam sub patterns onto start, go!
  -- allExprs and failCase are already set for us to use
  local attribute f :: MatchRule;
  f = case head(mrl) of
        matchRule(p,t,e) -> matchRule(patternListAppend(new(p.headPattern.patternSubPatternList),
                                                        patternListTail(p)), t, e)
      end;
  
  return if null(tail(mrl)) then mRuleList_one(f)
         else mRuleList_cons(f, '|', tailNestedPatternTransform(tail(mrl)));
}
function allIntCaseTransform -- a primPatternGenerator
PrimPatterns ::= returnType::TypeExp  restExprs::[Decorated Expr]  failCase::Expr  mrs::[[Decorated MatchRule]]
{
  local attribute fstPat :: PrimPattern;
  fstPat = integerPattern(case head(head(mrs)).headPattern of 
                            intPattern(it) -> it
                          end, '->', 
                         caseExpr(head(head(mrs)).location, returnType,
                                  convStringsToExprs([], restExprs),
                                  tailNestedPatternTransform(head(mrs)),
                                  failCase));
  
  return if null(tail(mrs)) then onePattern(fstPat)
         else consPattern(fstPat, '|', allIntCaseTransform(returnType, restExprs, failCase, tail(mrs)));  
}
function allStrCaseTransform -- a primPatternGenerator
PrimPatterns ::= returnType::TypeExp  restExprs::[Decorated Expr]  failCase::Expr  mrs::[[Decorated MatchRule]]
{
  local attribute fstPat :: PrimPattern;
  fstPat = stringPattern(case head(head(mrs)).headPattern of 
                            strPattern(it) -> it
                          end, '->', 
                         caseExpr(head(head(mrs)).location, returnType,
                                  convStringsToExprs([], restExprs),
                                  tailNestedPatternTransform(head(mrs)),
                                  failCase));
  
  return if null(tail(mrs)) then onePattern(fstPat)
         else consPattern(fstPat, '|', allStrCaseTransform(returnType, restExprs, failCase, tail(mrs)));  
}
function allBoolCaseTransform -- a primPatternGenerator
PrimPatterns ::= returnType::TypeExp  restExprs::[Decorated Expr]  failCase::Expr  mrs::[[Decorated MatchRule]]
{
  local attribute fstPat :: PrimPattern;
  fstPat = booleanPattern(case head(head(mrs)).headPattern of 
                            truePattern(_) -> "true"
                          | falsePattern(_) -> "false"
                          end, '->', 
                         caseExpr(head(head(mrs)).location, returnType,
                                  convStringsToExprs([], restExprs),
                                  tailNestedPatternTransform(head(mrs)),
                                  failCase));
  
  return if null(tail(mrs)) then onePattern(fstPat)
         else consPattern(fstPat, '|', allBoolCaseTransform(returnType, restExprs, failCase, tail(mrs)));  
}
function allListCaseTransform -- a primPatternGenerator
PrimPatterns ::= returnType::TypeExp  restExprs::[Decorated Expr]  failCase::Expr  mrs::[[Decorated MatchRule]]
{
  local attribute names :: [String];
  names = head(head(mrs)).headPattern.patternSubPatternList.patternListVars;

  local attribute subcase :: Expr;
  subcase =  caseExpr(head(head(mrs)).location, returnType,
                      convStringsToExprs(names, restExprs),
                      tailNestedPatternTransform(head(mrs)),
                      failCase);
  local attribute fstPat :: PrimPattern;
  fstPat = case head(head(mrs)).headPattern of
             nilListPattern(_,_) ->    nilPattern(subcase)
           | consListPattern(h,_,t) -> conslstPattern(head(names), head(tail(names)), subcase)
           end;
  
  return if null(tail(mrs)) then onePattern(fstPat)
         else consPattern(fstPat, '|', allListCaseTransform(returnType, restExprs, failCase, tail(mrs)));
}
function regenerateMatchRuleList
MRuleList ::= mr::[Decorated MatchRule]
{
  return if null(tail(mr)) then mRuleList_one(new(head(mr)))
         else mRuleList_cons(new(head(mr)), '|', regenerateMatchRuleList(tail(mr)));
}
function makeLet
Expr ::= s::String t::TypeExp e::Expr o::Expr
{
  return letp('let', assignListSingle(assignExpr(nameIdLower(terminal(IdLower_t, s)), '::', typerepType(t), '=', e)), 'in', o, 'end');
}
function toAssigns
LetAssigns ::= ls1::[AssignExpr]
{
  return if length(ls1) == 1 then assignListSingle(head(ls1))
         else assigns(head(ls1), terminal(Comma_t, ","), toAssigns(tail(ls1))) ;
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
TypeExp ::= t::TypeExp
{
  return if t.isDecorable
         then decoratedTypeExp(t)
         else t;
}
function getTypesExprsSubst
[TypeExp] ::= es::[Decorated Expr]
{
  return if null(es) then [] else [performSubstitution(head(es).typerep, head(es).upSubst)] ++ getTypesExprs(tail(es));
}
function myor
Boolean ::= a::Boolean b::Boolean
{ return a || b; }
function mruleEqForGrouping
Boolean ::= a::Decorated MatchRule b::Decorated MatchRule
{
  production attribute rv :: Boolean with myor;
  rv := case a.headPattern, b.headPattern of
          prodAppPattern(aqn,_,_,_), prodAppPattern(bqn,_,_,_) -> aqn.name == bqn.name
        | intPattern(an), intPattern(bn) -> toInt(an.lexeme) == toInt(bn.lexeme)
        | strPattern(astr), strPattern(bstr) -> astr.lexeme == bstr.lexeme
        | falsePattern(_), falsePattern(_) -> true
        | truePattern(_),  truePattern(_)  -> true
        | nilListPattern(_,_),    nilListPattern(_,_)    -> true
        | consListPattern(_,_,_), consListPattern(_,_,_) -> true
        | _, _ -> false
        end;
  rv <- (a.headPattern.patternIsVariable && b.headPattern.patternIsVariable);
  return rv;
}
function mruleLTEForSorting
Boolean ::= a::Decorated MatchRule b::Decorated MatchRule
{
  production attribute rv :: Boolean with myor;
  rv := case a.headPattern, b.headPattern of
          prodAppPattern(aqn,_,_,_), prodAppPattern(bqn,_,_,_) -> aqn.name <= bqn.name
        | intPattern(an), intPattern(bn) -> toInt(an.lexeme) <= toInt(bn.lexeme)
        | strPattern(astr), strPattern(bstr) -> astr.lexeme <= bstr.lexeme
        | falsePattern(_), falsePattern(_) -> true
        | falsePattern(_), truePattern(_)  -> true
        | truePattern(_),  truePattern(_)  -> true
        | nilListPattern(_,_),    nilListPattern(_,_)    -> true
        | nilListPattern(_,_),    consListPattern(_,_,_) -> true
        | consListPattern(_,_,_), consListPattern(_,_,_) -> true
        | _, _ -> false
        end;
  rv <- (a.headPattern.patternIsVariable && b.headPattern.patternIsVariable);
  return rv;
}
function groupMRules
[[Decorated MatchRule]] ::= l::[Decorated MatchRule]
{
  return groupBy(mruleEqForGrouping, sortBy(mruleLTEForSorting, l));
}

----------- Type Stuff

synthesized attribute primPatternGenerator :: Function(PrimPatterns ::= TypeExp [Decorated Expr]  Expr  [[Decorated MatchRule]]);

attribute primPatternGenerator occurs on TypeExp;

function failPrimPatternGen
PrimPatterns ::= returnType::TypeExp  restExprs::[Decorated Expr]  failCase::Expr  mrs::[[Decorated MatchRule]]
{ return error("INTERNAL ERROR: tried to generate primitive pattern matches for an unsupported scrutinee type. " ++ head(head(mrs)).pp);
}
aspect production defaultTypeExp
top::TypeExp ::=
{
  -- known includes: var/skolem/prod/fun/terminal
  top.primPatternGenerator = failPrimPatternGen;
}

aspect production intTypeExp
top::TypeExp ::=
{
  top.primPatternGenerator = allIntCaseTransform;
}

aspect production boolTypeExp
top::TypeExp ::=
{
  top.primPatternGenerator = allBoolCaseTransform;
}

aspect production floatTypeExp
top::TypeExp ::=
{
}

aspect production stringTypeExp
top::TypeExp ::=
{
  top.primPatternGenerator = allStrCaseTransform;
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
}

aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.primPatternGenerator = allConCaseTransform;
}

aspect production listTypeExp
top::TypeExp ::= el::TypeExp
{
  top.primPatternGenerator = allListCaseTransform;
}


