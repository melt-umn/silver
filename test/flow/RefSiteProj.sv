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

production fork
top::RSExpr ::= e1::RSExpr e2::RSExpr
{
  propagate env1, env2;
  top.errors1 = e1.errors1 || e2.errors1;
  top.errors2 = e1.errors2 || e2.errors2;
}

warnCode "child e of production flow:copy1" {
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

warnCode "Access of synthesized attribute errors2 on e requires missing inherited attribute(s) flow:env2 to be supplied to any of" {
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

warnCode "child e of production flow:copy1" {
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
  local e1::RSExpr = @e;
  e1.env2 = top.env2;

  top.errors1 = e.errors1;
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

warnCode "may exceed a flow type with hidden transitive dependencies" {
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

warnCode "may exceed a flow type with hidden transitive dependencies" {
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

warnCode "may exceed a flow type with hidden transitive dependencies" {
production fwrdDecSiteExceedsFT
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env2;
  forwards to @e;
}
}

warnCode "may exceed a flow type with hidden transitive dependencies" {
production projExceedsFT
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = copy12(@e);
  e1.env1 = top.env2;
  forwards to @e1;
}
}

warnCode "may exceed a flow type with hidden transitive dependencies" {
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

warnCode "may exceed a flow type with hidden transitive dependencies" {
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

warnCode "Duplicate equation for env2 on e in production flow:implProd" {
production implProd implements UnaryOp
top::RSExpr ::= @e::RSExpr
{
  e.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}
}

production dispatchOverrideKnownProd
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  e.env2 = top.env1 ++ top.env2;
  forwards to implProd(e); 
}

production dispatchOverrideUnknownProd
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  e.env2 = top.env1 ++ top.env2;
  local implProdRef::UnaryOp = implProd;
  forwards to implProdRef(e); 
}

warnCode "Access of synthesized attribute errors2 on e requires missing inherited attribute(s) flow:env2 to be supplied to child e of production flow:anonDecSuppliedMissing" {
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

warnCode "may exceed a flow type with hidden transitive dependencies" {
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

warnCode "child e of production flow:copy1" {
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

production shareLetBinding
top::RSExpr ::= e::RSExpr
{
  top.errors1 = e.errors1;
  forwards to
    let e1::Decorated RSExpr with {} = e
    in copy12(@e1)
    end;
}

production shareInLetBinding
top::RSExpr ::= e::RSExpr
{
  top.errors1 = e.errors1;
  forwards to
    let e1::RSExpr = copy12(@e)
    in copy12(e1)
    end;
}

wrongFlowCode "Cannot share a tree here" {
production shareInLetBindingNotDecSite
top::RSExpr ::= e::RSExpr
{
  top.errors1 = e.errors1;
  forwards to
    let e1::RSExpr = copy12(@e)
    in if hackUnparse(e1) == "" then ^e else base()
    end;
}
}

production condShareInLetBinding
top::RSExpr ::= e::RSExpr
{
  forwards to
    let e1::RSExpr = copy12(@e)
    in if null(top.env1) then copy12(e1) else base()
    end;
}

warnCode "Equation requires inherited attribute flow:env1 be supplied to child e of production flow:condShareInLetBindingDep" {
production condShareInLetBindingDep
top::RSExpr ::= e::RSExpr
{
  top.errors1 = e.errors1;
  forwards to
    let e1::RSExpr = copy12(@e)
    in if null(top.env1) then copy12(e1) else base()
    end;
}
}

wrongFlowCode "Cannot share a tree here" {
production shareInUnusedLetBinding
top::RSExpr ::= e::RSExpr
{
  top.errors1 = e.errors1;
  forwards to
    let e1::RSExpr = copy12(@e)
    in if null(top.env1) then ^e else base()
    end;
}
}

-- Not an ideal error message (the issue is the duplicate use of e1), but good enough.
wrongFlowCode "Cannot share a tree here" {
production duplicateShareLetBinding
top::RSExpr ::= e::RSExpr
{
  forwards to
    let e1::RSExpr = copy12(@e)
    in fork(e1, e1)
    end;
}
}