grammar silver:extension:treegen;

import silver:modification:let_fix;
import silver:extension:patternmatching;
import silver:modification:primitivepattern;
import silver:definition:flow:env;

terminal Eq_t 'Eq' lexer classes {KEYWORD};

concrete production deriveEqagdcl
top::AGDcl ::= 'derive' 'Eq' 'on' names::QNames ';'
{
  -- bug: stupid hack. Find some other way to fix this, maybe?
  top.flowDefs = [];

  forwards to 
    foldr(
      appendAGDcl(_, _, location=top.location),
      emptyAGDcl(location=top.location),
      map(
        deriveEqOn(_, top.env, top.flowEnv, top.location),
        map((.qnwtQN), names.qnames)));
}

function nonforwardingProd
Boolean ::= d::DclInfo  fe::Decorated FlowEnv
{
  return null(lookupFwd(d.fullName, fe));
}

function deriveEqOn
AGDcl ::= id::QName  env::Decorated Env  fenv::Decorated FlowEnv  l::Location
{
  id.env = env;
  
  local prods :: [DclInfo] = filter(nonforwardingProd(_, fenv), getProdsForNt(id.lookupType.fullName, env));

  local sig :: FunctionSignature =
    functionSignature(
      functionLHS(typerepType(boolTypeExp(), location=l), location=l),
      '::=',
      productionRHSCons(
        productionRHSElem(name("l", l), '::', typerepType(id.lookupType.typerep, location=l), location=l),
        productionRHSCons(
          productionRHSElem(name("r", l), '::', typerepType(id.lookupType.typerep, location=l), location=l),
          productionRHSNil(location=l), location=l), location=l),
      location=l);

  local body :: ProductionBody =
    productionBody('{', foldl(productionStmtsSnoc(_, _, location=l), productionStmtsNil(location=l), stmts), '}', location=l);
  
  local stmts :: [ProductionStmt] =
    [
      returnDef('return', caseExpr_c('case', exprsCons(baseExpr(qName(l, "l"), location=l), ',', exprsSingle(baseExpr(qName(l, "r"), location=l), location=l), location=l), 'of', terminal(Opt_Vbar_t, "|"), ml, 'end', location=l), ';', location=l)
    ];
  
  local ml :: MRuleList = foldmrl(map(generateCheckEqMRuleList(_, l), prods));

  return
    functionDcl(
      'function',
      name(getCheckEqName(id.lookupType.typerep), l),
      sig,
      body, location=l);
}


function foldmrl
MRuleList ::= l::[MatchRule]
{
  return if null(l) then error("doh")
  else if null(tail(l)) then mRuleList_one(head(l), location=head(l).location)
  else mRuleList_cons(head(l), '|', foldmrl(tail(l)), location=head(l).location);
}

function foldpattlist
PatternList ::= l::[Pattern]
{
  return if null(l) then patternList_nil(location=loc("generated",-1,-1,-1,-1,-1,-1))
  else if null(tail(l)) then patternList_one(head(l), location=head(l).location)
  else patternList_more(head(l), ',', foldpattlist(tail(l)), location=head(l).location);
}


function generateCheckEqMRuleList
MatchRule ::= prod::DclInfo  l::Location
{
  local children :: [TypeExp] = prod.typerep.inputTypes;
  
  local lchildren :: [Name] = genIds("l", 0, length(children), l);
  local rchildren :: [Name] = genIds("r", 0, length(children), l);
  
  local lchildpatt :: PatternList = foldpattlist(map(varPattern(_, location=l), lchildren));
  local rchildpatt :: PatternList = foldpattlist(map(varPattern(_, location=l), rchildren));
  
  local lpatt :: Pattern = prodAppPattern(qName(l, prod.fullName), '(', lchildpatt, ')', location=l);
  local rpatt :: Pattern = prodAppPattern(qName(l, prod.fullName), '(', rchildpatt, ')', location=l);
  
  -- match on prod(a1, a2, a3...), prod(b1, b2, b3...) -> checkEq(a1, b1) && chechEq(a2, b2) && ...
  return
    matchRule_c(
      foldpattlist([lpatt, rpatt]),
      '->',
      zipCheckEqCalls(children, lchildren, rchildren),
      location=l);
}

function zipCheckEqCalls
Expr ::= t::[TypeExp]  l::[Name]  r::[Name]
{
  local c :: Expr = callCheckEq(head(t), head(l).location, [baseExpr(qNameId(head(l), location=head(l).location), location=head(l).location), baseExpr(qNameId(head(r), location=head(l).location), location=head(r).location)]);
  
  return if null(t) then trueConst('true', location=loc("<generated>",-1,-1,-1,-1,-1,-1))
  else if null(tail(t)) then
    c
  else
    and(c, '&&', zipCheckEqCalls(tail(t), tail(l), tail(r)), location=head(l).location);
}


function genIds
[Name] ::= side::String  index::Integer  total::Integer  l::Location
{
  return if index == total then [] else
    name(side ++ toString(index), l) :: genIds(side, index + 1, total, l);
}



function callCheckEq
Expr ::= te::TypeExp  l::Location  es::[Expr]
{
  return mkStrFunctionInvocation(l, getCheckEqName(te), es);
}

function getCheckEqName
String ::= te::TypeExp
{
  return "checkEq" ++ te.idNameForGenArb;
}




