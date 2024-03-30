grammar flow;

nonterminal RSExpr with env1, env2, errors1, errors2;
flowtype RSExpr = errors1 {env1}, errors2 {env1, env2};

production copy1
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;

  top.errors1 = e.errors1;
  top.errors2 = false;
}

production copy12
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  e.env2 = top.env2;

  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}

production copy12From1
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  e.env2 = top.env1;

  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}

production copy12From2
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env2;
  e.env2 = top.env2;

  top.errors1 = false;
  top.errors2 = e.errors2;
}

production base
top::RSExpr ::=
{
  top.errors1 = null(top.env1);
  top.errors2 = null(top.env2);
}

production proj1
top::RSExpr ::= e::RSExpr
{
  top.errors1 = e.errors1;
  forwards to copy1(@e);
}

warnCode "missing remote equation" {
production proj2Missing
top::RSExpr ::= e::RSExpr
{
  top.errors2 = e.errors2;
  forwards to copy1(@e);
}
}

production proj12
top::RSExpr ::= e::RSExpr
{
  top.errors2 = e.errors2;
  forwards to copy12(@e);
}

warnCode "missing remote equation" {
production projNestedMissing
top::RSExpr ::= e::RSExpr
{
  top.errors2 = e.errors2;
  forwards to copy12(copy1(copy12(@e)));
}
}

production projNestedLocals
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = @e;
  local e2::RSExpr = copy12(@e1);
  local e3::RSExpr = copy12(copy1(@e2));

  e3.env1 = top.env1;
  e3.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = false;
}

warnCode "missing remote equation" {
production projNestedLocalsMissing
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = @e;
  local e2::RSExpr = copy12(@e1);
  local e3::RSExpr = copy12(copy1(@e2));

  e3.env1 = top.env1;
  e3.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}
}

production projNestedLocalsFwrd
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = @e;
  local e2::RSExpr = copy12(@e1);
  local e3::RSExpr = copy12(copy1(@e2));

  e2.env1 = "a" :: top.env1;
  forwards to @e3;
}

production proj1AvoidMissing
top::RSExpr ::= e::RSExpr
{
  top.errors2 = e.errors2;
  forwards to copy1(copy12From1(@e));
}

production incrementalDec
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  local e1::Expr = @e;
  e1.env2 = top.env2;

  top.errors1 = e1.errors1;
  top.errors2 = e.errors2;
}

warnCode "equation errors1 exceeds flow type with dependencies on flow:env2" {
production remoteExceeds
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = copy12(copy12From2(copy12(@e)));
  e1.env1 = top.env1;
  e1.env2 = top.env2;

  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}
}

warnCode "override equation may exceed a flow type with hidden transitive dependencies" {
production remoteExceedsOverride
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;

  local e1::RSExpr = copy12(copy12From2(copy12(@e)));
  e1.env1 = top.env1;
  e1.env2 = top.env2;

  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}
}

warnCode "equation errors1 exceeds flow type with dependencies on flow:env2" {
production uselessOverrideExceedsFT
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  local e1::RSExpr = @e;
  e1.env1 = top.env2;

  top.errors1 = e1.errors1;
  top.errors2 = false;
}
}

warnCode "override equation may exceed a flow type with hidden transitive dependencies" {
production uselessOverrideWithinFT
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env2;
  local e1::RSExpr = @e;
  e1.env1 = top.env1;

  top.errors1 = e1.errors1;
  top.errors2 = false;
}
}

warnCode "override equation may exceed a flow type with hidden transitive dependencies" {
production fwrdDecSiteExceedsFT
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env2;
  forwards to @e;
}
}

warnCode "override equation may exceed a flow type with hidden transitive dependencies" {
production projExceedsFT
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = copy12(@e);
  e1.env1 = top.env2;
  forwards to @e1;
}
}

warnCode "override equation may exceed a flow type with hidden transitive dependencies" {
production condDecExceedsFT
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env2;
  forwards to
    if top.env1 == [] then copy12(@e) else base();
}
}

production condDecDoesntExceedsFT
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env2;
  forwards to
    if top.env1 == [] then copy12From2(@e) else base();
}

warnCode "override equation may exceed a flow type with hidden transitive dependencies" {
production overrideNotInRefSet
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  e.env2 = top.env2;
  local e1::Expr = @e;
  e1.env2 = [];
  top.errors1 = e1.errors1;
  top.errors2 = e1.errors2;
}
}

dispatch UnaryOp = RSExpr ::= @e::RSExpr;

production implProd implements UnaryOp
top::RSExpr ::= @e::RSExpr
{
  e.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}

production dispatchOverrideKnownProd
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  e.env2 = top.env2;
  forwards to implProd(e); 
}

production dispatchOverrideUnknownProd
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  e.env2 = top.env2;
  local implProdRef::UnaryOp = implProd;
  forwards to implProdRef(e); 
}

warnCode "Access of syn attribute errors2 on e requires missing inherited attributes flow:env2 to be supplied" {
production anonDecSuppliedMissing
top::RSExpr ::= e::RSExpr
{
  e.env1 = [];
  local d::Decorated RSExpr with {env2} = decorate @e with {env2 = top.env2;};
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}
}

production anonDecOverrideOk
top::RSExpr ::= e::RSExpr
{
  e.env1 = [];
  e.env2 = top.env2;
  local d::Decorated RSExpr with {env2} = decorate @e with {env2 = top.env2;};
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}

warnCode "override equation may exceed a flow type with hidden transitive dependencies" {
production anonDecOverrideExceedsFT
top::RSExpr ::= e::RSExpr
{
  e.env1 = [];
  e.env2 = top.env1 ++ top.env2;
  local d::Decorated RSExpr with {env2} = decorate @e with {env2 = top.env2;};
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}
}

production projChain
top::RSExpr ::= e::RSExpr
{
  local foo::RSExpr = @e;
  forwards to copy1(@foo);
}

production projChain1Present
top::RSExpr ::= e::RSExpr
{
  top.errors1 = !null(e.env1);
  forwards to projChain(@e);
}

warnCode "missing remote equation" {
production projChain2Missing
top::RSExpr ::= e::RSExpr
{
  top.errors2 = !null(e.env2);
  forwards to projChain(@e);
}
}

production fwrdProdAttrThing
top::RSExpr ::= e::RSExpr
{
  top.errors1 = null(e.env1);

  forward fwrd = copy12(@e);

  forwards to if e.errors1 then base() else @fwrd;
}
