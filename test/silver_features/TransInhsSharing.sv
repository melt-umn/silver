grammar silver_features;

-- A decoration site of a shared tree may supply inherited attributes to the tree's translation attributes,
-- or share them.  These reach the translation attribute trees even if they were created before the site was
-- decorated, and they never reach other instances of the production that owns the shared tree.

inherited attribute tisEnv::String;
inherited attribute tisEnv2::String;
synthesized attribute tisOut::String;
synthesized attribute tisOut2::String;
synthesized attribute tisName::String;

-- tisName does not depend on any inherited attributes, so demanding it only creates the tree.
nonterminal TisZ with tisEnv, tisEnv2, tisOut, tisOut2, tisName;
production tisZ
top::TisZ ::= n::String
{
  top.tisName = n;
  top.tisOut = n ++ "(" ++ top.tisEnv ++ ")";
  top.tisOut2 = n ++ "(" ++ top.tisEnv2 ++ ")";
}

translation attribute tisA::TisZ;
nonterminal TisY with tisEnv, tisA, tisOut, tisName;
production tisY
top::TisY ::= n::String
{
  top.tisA = tisZ(n ++ ".a");
  top.tisName = n;
  top.tisOut = n ++ "(" ++ top.tisEnv ++ ")";
}

translation attribute tisB::TisY;
nonterminal TisX with tisA, tisB, tisName, tisOut;
production tisX
top::TisX ::= n::String
{
  top.tisA = tisZ(n ++ ".a");
  top.tisB = tisY(n ++ ".b");
  top.tisName = n;
  top.tisOut = "x[" ++ top.tisA.tisOut ++ "]";
}
-- Gets its translation attributes from its forward.
production tisXFwd
top::TisX ::= n::String
{
  forwards to tisX(n);
}
-- The forward is x only if b, so x only gets a forward parent once the forward is decorated.
production tisCondFwd
top::TisX ::= b::Boolean x::TisX
{
  top.tisName = x.tisA.tisName;
  forwards to if b then @x else tisX("other");
}

-- The decoration sites.
nonterminal TisP with tisOut;
production tisSite
top::TisP ::= tag::String c::TisZ
{
  c.tisEnv = "site" ++ tag;
  top.tisOut = "site[" ++ c.tisOut ++ "]";
}
production tisNoSite
top::TisP ::=
{ top.tisOut = "noSite"; }

-- Sites for an X, that supply inherited attributes to the translation attributes of their child
-- or share them, and demand them directly.
production tisEnvSite
top::TisP ::= c::TisX
{
  c.tisA.tisEnv = "envSite";
  top.tisOut = "envSite[" ++ c.tisA.tisOut ++ "]";
}
production tisEnv2Site
top::TisP ::= tag::String c::TisX
{
  c.tisA.tisEnv2 = "site" ++ tag;
  top.tisOut = "env2Site[" ++ c.tisA.tisOut2 ++ "]";
}
production tisShareSite
top::TisP ::= c::TisX
{
  local s::TisP = tisSite("", @c.tisA);
  top.tisOut = "shareSite[" ++ c.tisA.tisOut ++ ", " ++ s.tisOut ++ "]";
}
production tisNestedShareSite
top::TisP ::= tag::String c::TisX
{
  local s::TisP = tisSite(tag, @c.tisB.tisA);
  top.tisOut = "nestedShareSite[" ++ c.tisB.tisA.tisOut ++ ", " ++ s.tisOut ++ "]";
}
-- Supplies inherited attributes to the translation attribute of its child, and demands them through the child.
production tisEnvOutSite
top::TisP ::= c::TisX
{
  c.tisA.tisEnv = "envOutSite";
  top.tisOut = "envOutSite[" ++ c.tisOut ++ "]";
}

-- Sites that share their child, or its translation attribute, at another site that supplies inherited attributes
-- to the translation attribute of the shared tree.
production tisReshareSite
top::TisP ::= c::TisX
{
  local s::TisP = tisEnvSite(@c);
  top.tisOut = "reshareSite[" ++ c.tisA.tisOut ++ ", " ++ s.tisOut ++ "]";
}
production tisYEnvSite
top::TisP ::= c::TisY
{
  c.tisEnv = "yEnvSite";
  c.tisA.tisEnv = "yEnvSite";
  top.tisOut = "yEnvSite[" ++ c.tisOut ++ "]";
}
production tisNestedReshareSite
top::TisP ::= c::TisX
{
  local s::TisP = tisYEnvSite(@c.tisB);
  top.tisOut = "nestedReshareSite[" ++ c.tisB.tisA.tisOut ++ ", " ++ s.tisOut ++ "]";
}

-- tisName creates a translation attribute tree of x (or of x.b), tisOut decorates the site.
nonterminal TisR with tisOut, tisName;

-- x is shared conditionally, so its decoration site is only known once the site is decorated.
production tisCondEnv
top::TisR ::= b::Boolean x::TisX
{
  local site::TisP = if b then tisEnvSite(@x) else tisNoSite();
  top.tisName = x.tisA.tisName;
  top.tisOut = site.tisOut;
}
production tisCondShare
top::TisR ::= b::Boolean x::TisX
{
  local site::TisP = if b then tisShareSite(@x) else tisNoSite();
  top.tisName = x.tisA.tisName;
  top.tisOut = site.tisOut;
}
production tisCondNestedShare
top::TisR ::= b::Boolean x::TisX
{
  local site::TisP = if b then tisNestedShareSite("", @x) else tisNoSite();
  top.tisName = x.tisB.tisA.tisName;
  top.tisOut = site.tisOut;
}
-- Only x.b is created before the site, x.b.a is created after.
production tisCondNestedShareMid
top::TisR ::= b::Boolean x::TisX
{
  local site::TisP = if b then tisNestedShareSite("", @x) else tisNoSite();
  top.tisName = x.tisB.tisName;
  top.tisOut = site.tisOut;
}

-- The site shares x (or x.b) again, so x (or x.b) also gets a decoration site when the site is decorated.
production tisCondReshare
top::TisR ::= b::Boolean x::TisX
{
  local site::TisP = if b then tisReshareSite(@x) else tisNoSite();
  top.tisName = x.tisA.tisName;
  top.tisOut = site.tisOut;
}
production tisCondNestedReshare
top::TisR ::= b::Boolean x::TisX
{
  local site::TisP = if b then tisNestedReshareSite(@x) else tisNoSite();
  top.tisName = x.tisB.tisA.tisName;
  top.tisOut = site.tisOut;
}
-- The site demands the translation attribute of x through x, which gets it from its forward.
production tisFwdShare
top::TisR ::= x::TisX
{
  local site::TisP = tisEnvOutSite(@x);
  top.tisName = x.tisName;
  top.tisOut = site.tisOut;
}

-- x's production supplies an inherited attribute to a translation attribute of x,
-- and shares x at a site that supplies another inherited attribute to it or shares its translation attribute.
production tisOverride
top::TisR ::= tag::String x::TisX
{
  x.tisA.tisEnv = "owner" ++ tag;
  local site::TisP = tisEnv2Site(tag, @x);
  top.tisName = x.tisA.tisName;
  top.tisOut = site.tisOut ++ " " ++ x.tisA.tisOut;
}
production tisOverrideNested
top::TisR ::= tag::String x::TisX
{
  x.tisB.tisEnv = "owner" ++ tag;
  local site::TisP = tisNestedShareSite(tag, @x);
  top.tisName = x.tisB.tisA.tisName;
  top.tisOut = site.tisOut ++ " " ++ x.tisB.tisOut;
}

-- x is shared unconditionally, so x's production can demand what the site supplies to x.a.
production tisViaEnvSite
top::TisR ::= x::TisX
{
  local site::TisP = tisEnvSite(@x);
  top.tisName = x.tisA.tisOut;
  top.tisOut = site.tisOut;
}
production tisViaShareSite
top::TisR ::= x::TisX
{
  local site::TisP = tisShareSite(@x);
  top.tisName = x.tisA.tisOut;
  top.tisOut = site.tisOut;
}
-- The translation attribute of the forward is this production's own, so it gets what the parent supplies to that.
production tisFwdOut
top::TisX ::= n::String
{
  top.tisOut = "fwdOut[" ++ forward.tisA.tisOut ++ "]";
  forwards to tisX(n);
}

-- The translation attribute created before or after the site is decorated.
-- These take a decorated tree, as each access to an undecorated parameter decorates it anew.
fun tisTransFirst String ::= r::Decorated TisR = r.tisName ++ " ; " ++ r.tisOut;
fun tisSiteFirst String ::= r::Decorated TisR = r.tisOut ++ " ; " ++ r.tisName;

equalityTest(tisTransFirst(decorate tisCondEnv(true, tisX("x")) with {}), "x.a ; envSite[x.a(envSite)]", String, silver_tests);
equalityTest(tisSiteFirst(decorate tisCondEnv(true, tisX("x")) with {}), "envSite[x.a(envSite)] ; x.a", String, silver_tests);
equalityTest(tisTransFirst(decorate tisCondEnv(true, tisXFwd("x")) with {}), "x.a ; envSite[x.a(envSite)]", String, silver_tests);
equalityTest(tisTransFirst(decorate tisCondEnv(false, tisX("x")) with {}), "x.a ; noSite", String, silver_tests);

equalityTest(tisTransFirst(decorate tisCondShare(true, tisX("x")) with {}), "x.a ; shareSite[x.a(site), site[x.a(site)]]", String, silver_tests);
equalityTest(tisSiteFirst(decorate tisCondShare(true, tisX("x")) with {}), "shareSite[x.a(site), site[x.a(site)]] ; x.a", String, silver_tests);
equalityTest(tisTransFirst(decorate tisCondShare(true, tisXFwd("x")) with {}), "x.a ; shareSite[x.a(site), site[x.a(site)]]", String, silver_tests);

equalityTest(tisTransFirst(decorate tisCondNestedShare(true, tisX("x")) with {}), "x.b.a ; nestedShareSite[x.b.a(site), site[x.b.a(site)]]", String, silver_tests);
equalityTest(tisSiteFirst(decorate tisCondNestedShare(true, tisX("x")) with {}), "nestedShareSite[x.b.a(site), site[x.b.a(site)]] ; x.b.a", String, silver_tests);
equalityTest(tisTransFirst(decorate tisCondNestedShare(true, tisXFwd("x")) with {}), "x.b.a ; nestedShareSite[x.b.a(site), site[x.b.a(site)]]", String, silver_tests);

equalityTest(tisTransFirst(decorate tisCondNestedShareMid(true, tisX("x")) with {}), "x.b ; nestedShareSite[x.b.a(site), site[x.b.a(site)]]", String, silver_tests);
equalityTest(tisTransFirst(decorate tisCondNestedShareMid(true, tisXFwd("x")) with {}), "x.b ; nestedShareSite[x.b.a(site), site[x.b.a(site)]]", String, silver_tests);

equalityTest(tisTransFirst(decorate tisCondReshare(true, tisX("x")) with {}), "x.a ; reshareSite[x.a(envSite), envSite[x.a(envSite)]]", String, silver_tests);
equalityTest(tisSiteFirst(decorate tisCondReshare(true, tisX("x")) with {}), "reshareSite[x.a(envSite), envSite[x.a(envSite)]] ; x.a", String, silver_tests);
equalityTest(tisTransFirst(decorate tisCondNestedReshare(true, tisX("x")) with {}), "x.b.a ; nestedReshareSite[x.b.a(yEnvSite), yEnvSite[x.b(yEnvSite)]]", String, silver_tests);
equalityTest(tisSiteFirst(decorate tisCondNestedReshare(true, tisX("x")) with {}), "nestedReshareSite[x.b.a(yEnvSite), yEnvSite[x.b(yEnvSite)]] ; x.b.a", String, silver_tests);

-- The translation attribute of the forward is created before the forward.
equalityTest(tisTransFirst(decorate tisFwdShare(tisCondFwd(true, tisX("x"))) with {}), "x.a ; envOutSite[x[x.a(envOutSite)]]", String, silver_tests);
equalityTest(tisSiteFirst(decorate tisFwdShare(tisCondFwd(true, tisX("x"))) with {}), "envOutSite[x[x.a(envOutSite)]] ; x.a", String, silver_tests);
equalityTest(tisTransFirst(decorate tisFwdShare(tisCondFwd(false, tisX("x"))) with {}), "x.a ; envOutSite[x[other.a(envOutSite)]]", String, silver_tests);

-- Two instances of the same production, each with its own site.
equalityTest(
  tisSiteFirst(decorate tisOverride("1", tisX("x")) with {}) ++ " | " ++
  tisSiteFirst(decorate tisOverride("2", tisX("x")) with {}),
  "env2Site[x.a(site1)] x.a(owner1) ; x.a | env2Site[x.a(site2)] x.a(owner2) ; x.a",
  String, silver_tests);
equalityTest(
  tisTransFirst(decorate tisOverrideNested("1", tisX("x")) with {}) ++ " | " ++
  tisTransFirst(decorate tisOverrideNested("2", tisX("x")) with {}),
  "x.b.a ; nestedShareSite[x.b.a(site1), site[x.b.a(site1)]] x.b(owner1) | x.b.a ; nestedShareSite[x.b.a(site2), site[x.b.a(site2)]] x.b(owner2)",
  String, silver_tests);
equalityTest(
  tisSiteFirst(decorate tisOverrideNested("1", tisX("x")) with {}) ++ " | " ++
  tisSiteFirst(decorate tisOverrideNested("2", tisX("x")) with {}),
  "nestedShareSite[x.b.a(site1), site[x.b.a(site1)]] x.b(owner1) ; x.b.a | nestedShareSite[x.b.a(site2), site[x.b.a(site2)]] x.b(owner2) ; x.b.a",
  String, silver_tests);

-- Demanded by x's production from a site that x is shared at.
equalityTest(tisTransFirst(decorate tisViaEnvSite(tisX("x")) with {}), "x.a(envSite) ; envSite[x.a(envSite)]", String, silver_tests);
equalityTest(tisSiteFirst(decorate tisViaEnvSite(tisX("x")) with {}), "envSite[x.a(envSite)] ; x.a(envSite)", String, silver_tests);
equalityTest(tisTransFirst(decorate tisViaEnvSite(tisXFwd("x")) with {}), "x.a(envSite) ; envSite[x.a(envSite)]", String, silver_tests);
equalityTest(tisTransFirst(decorate tisViaShareSite(tisX("x")) with {}), "x.a(site) ; shareSite[x.a(site), site[x.a(site)]]", String, silver_tests);
equalityTest(decorate tisEnvOutSite(tisFwdOut("x")) with {}.tisOut, "envOutSite[fwdOut[x.a(envOutSite)]]", String, silver_tests);
