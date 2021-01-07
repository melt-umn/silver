grammar silver:compiler:extension:treegen;

terminal Derive_t 'derive' lexer classes {KEYWORD};

terminal Arbitrary_t 'Arbitrary' lexer classes {KEYWORD};


concrete production deriveagdcl
top::AGDcl ::= 'derive' 'Arbitrary' 'on' names::QNames ';'
{
  forwards to 
    foldr(
      appendAGDcl(_, _, location=top.location),
      emptyAGDcl(location=top.location),
      map(
        deriveArbitraryOn(_, top.env),
        map((.qnwtQN), names.qnames)));
}



function prodDclInfoNumChildLte
Boolean ::= l::DclInfo  r::DclInfo
{
  return l.typeScheme.arity <= r.typeScheme.arity;
}
function prodDclInfoNumChildEq
Boolean ::= l::DclInfo  r::DclInfo
{
  return l.typeScheme.arity == r.typeScheme.arity;
}

-- splits where operator becomes false in list
function takeWhile2
[a] ::= f::(Boolean ::= a a)  l::[a]
{
  return if null(l) then []
  else if null(tail(l)) then l
  else if f(head(l), head(tail(l))) then head(l) :: takeWhile2(f, tail(l))
  else [head(l)];
}

-- create a function called 'generateArbitraryID'
function deriveArbitraryOn
AGDcl ::= id::QName  env::Decorated Env
{
  local l :: Location = id.location;

  id.env = env;
  
  local prods :: [DclInfo] = 
    sortBy(prodDclInfoNumChildLte,
      getKnownProds(id.lookupType.fullName, env));
  
  local num_lowest_arity :: Integer = length(takeWhile2(prodDclInfoNumChildEq, prods));
  
  local sig :: FunctionSignature =
    functionSignature(
      nilConstraint(location=l), '=>',
      functionLHS(typerepTypeExpr(id.lookupType.typeScheme.typerep, location=l), location=l),
      '::=',
      productionRHSCons(
        productionRHSElem(name("current__depth", l), '::', typerepTypeExpr(intType(), location=l), location=l),
        productionRHSNil(location=l), location=l),
      location=l);
  
  local body :: ProductionBody =
    productionBody('{', foldl(productionStmtsSnoc(_, _, location=l), productionStmtsNil(location=l), stmts), '}', location=l);
  
  local stmts :: [ProductionStmt] =
    [
      shortLocalDecl('local', name("pval", l), '::', typerepTypeExpr(floatType(), location=l), '=', 
        ifThenElse(
          'if', gtOp(baseExpr(qName(l, "current__depth"), location=l), '>', intConst(terminal(Int_t, "12"), location=l), location=l),
          'then', multiply(
            mkStrFunctionInvocation(l, "genRand", []), '*',
            floatConst(terminal(Float_t, toString(toFloat(num_lowest_arity)/toFloat(length(prods)))), location=l), location=l),
          'else', mkStrFunctionInvocation(l, "genRand", []),
          location=l),
        ';', location=l),
      returnDef('return', generateExprChain(0, prods, length(prods), l), ';', location=l)
    ];
  
  return
    functionDcl(
      'function',
      name(getGenArbName(id.lookupType.typeScheme.typerep), l),
      sig,
      body, location=l);
{-

We got the id 'Expr' incoming.

We should look up 'Expr' and get a list of productions, from that we get the probabilities.

We then map deriveGenerateOn over the list of productions and generate a big if-then-else tree based on the genRand()

-}
}

function generateExprChain
Expr ::= index::Integer  lst::[DclInfo]  total::Integer  l::Location
{
  return if null(lst) then error("no productions for nonterminal at " ++ l.filename ++ ":" ++ toString(l.line) ++ "." ++ toString(l.column))
  else if null(tail(lst)) then
    deriveGenerateOn(head(lst), l)
  else
    -- yield "if pval < '(index+1)/total' then 'deriveGenerateOn' else generateExprChain..."
    ifThenElse(
      'if', ltOp(baseExpr(qName(l, "pval"), location=l), '<', floatConst(terminal(Float_t, toString(toFloat(index+ 1) / toFloat(total))), location=l), location=l),
      'then', deriveGenerateOn(head(lst), l),
      'else', generateExprChain(index + 1, tail(lst), total, l),
      location=l);
}

-- construct a production, calling 'generateArbitraryID' for each child
function deriveGenerateOn
Expr ::= id::DclInfo  l::Location
{
  local annos :: [Pair<String Expr>] =
    if null(id.typeScheme.typerep.namedTypes) then
      []
    else
      -- we just erroneously assume the annotation must be location, for now
      [pair("location", mkStrFunctionInvocation(l, "bogusLoc", []))];

  return
    mkFullFunctionInvocation(
      l,
      baseExpr(qName(l, id.fullName), location=l),
      map(callGenArb(_, l), id.typeScheme.typerep.inputTypes),
      annos);
}

-- Call generateArbitraryID
function callGenArb
Expr ::= te::Type  l::Location
{
  return mkStrFunctionInvocation(l, getGenArbName(te), [plus(baseExpr(qName(l, "current__depth"), location=l), '+', intConst(terminal(Int_t, "1"), location=l), location=l)]);
}

-- Map a type to its ID name for use in 'generateArbitraryID'
function getGenArbName
String ::= te::Type
{
  return "generateArbitrary" ++ te.idNameForGenArb;
}


