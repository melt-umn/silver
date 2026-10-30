grammar silver_features;

-- Sharing a translation attribute of a translation attribute (at any depth), or sharing a translation
-- attribute through a production signature, registers the decoration site of the shared tree statically.
-- So the shared tree gets the inherited attributes supplied by its decoration site even when it is
-- demanded before the site is decorated.

inherited attribute tasEnv::String;
synthesized attribute tasOut::String;
synthesized attribute tasAOut::String;
synthesized attribute tasEnvOut::String;

-- Only the decoration site supplies tasEnv.
nonterminal TasZ with tasEnv, tasOut;
production tasZ
top::TasZ ::= n::String
{ top.tasOut = n ++ "(" ++ top.tasEnv ++ ")"; }

-- The productions with translation attributes also demand them internally.
translation attribute tasA::TasZ;
nonterminal TasY with tasEnv, tasA, tasOut, tasEnvOut;
production tasY
top::TasY ::= n::String
{
  top.tasA = tasZ(n ++ ".a");
  top.tasOut = "y[" ++ top.tasA.tasOut ++ "]";
  top.tasEnvOut = "y(" ++ top.tasEnv ++ ")[" ++ top.tasA.tasOut ++ "]";
}

translation attribute tasB::TasY;
nonterminal TasX with tasA, tasB, tasOut, tasAOut;
production tasX
top::TasX ::= n::String
{
  top.tasA = tasZ(n ++ ".a");
  top.tasB = tasY(n ++ ".b");
  top.tasOut = "x[" ++ top.tasB.tasOut ++ "]";
  top.tasAOut = "x[" ++ top.tasA.tasOut ++ "]";
}
-- Gets its translation attributes from its forward.
production tasXFwd
top::TasX ::= n::String
{
  forwards to tasX(n);
}

translation attribute tasC::TasX;
nonterminal TasV with tasC, tasOut;
production tasV
top::TasV ::= n::String
{
  top.tasC = tasX(n ++ ".c");
  top.tasOut = "v[" ++ top.tasC.tasOut ++ "]";
}

-- tasTransOut demands the shared translation directly, tasIndirectOut through the tree it occurs on.
synthesized attribute tasTransOut::String;
synthesized attribute tasIndirectOut::String;
nonterminal TasP with tasOut, tasTransOut, tasIndirectOut;

-- The decoration sites.
production tasSite
top::TasP ::= c::TasZ
{
  c.tasEnv = "site";
  top.tasOut = "site[" ++ c.tasOut ++ "]";
}
production tasSigSite
top::TasP ::= @c::TasZ
{
  c.tasEnv = "sigSite";
  top.tasOut = "sigSite[" ++ c.tasOut ++ "]";
}

-- x.b.a shared at a local.
production tasShareNested
top::TasP ::= x::TasX
{
  local site::TasP = tasSite(@x.tasB.tasA);
  top.tasOut = site.tasOut;
  top.tasTransOut = x.tasB.tasA.tasOut;
  top.tasIndirectOut = x.tasB.tasOut;
}
-- The same, but x.b.a is demanded indirectly through x.
-- If x forwards, x.b comes from the forward, whose own equations may demand x.b.a first.
production tasShareNestedViaX
top::TasP ::= x::TasX
{
  local site::TasP = tasSite(@x.tasB.tasA);
  top.tasOut = site.tasOut;
  top.tasTransOut = x.tasB.tasA.tasOut;
  top.tasIndirectOut = x.tasOut;
}
-- x.b.a shared at the forward.
production tasShareNestedFwd
top::TasP ::= x::TasX
{
  top.tasTransOut = x.tasB.tasA.tasOut;
  top.tasIndirectOut = x.tasB.tasOut;
  forwards to tasSite(@x.tasB.tasA);
}
-- x.b.a shared at a local, with an equation supplying an inherited attribute to x.b before or after the site.
production tasShareNestedInhBefore
top::TasP ::= x::TasX
{
  x.tasB.tasEnv = "inh";
  local site::TasP = tasSite(@x.tasB.tasA);
  top.tasOut = site.tasOut;
  top.tasTransOut = x.tasB.tasA.tasOut;
  top.tasIndirectOut = x.tasB.tasEnvOut;
}
production tasShareNestedInhAfter
top::TasP ::= x::TasX
{
  local site::TasP = tasSite(@x.tasB.tasA);
  top.tasOut = site.tasOut;
  top.tasTransOut = x.tasB.tasA.tasOut;
  top.tasIndirectOut = x.tasB.tasEnvOut;
  x.tasB.tasEnv = "inh";
}
-- l.b.a shared at a local, where l is also a local.
production tasShareNestedLocal
top::TasP ::= n::String
{
  local l::TasX = tasX(n);
  local site::TasP = tasSite(@l.tasB.tasA);
  top.tasOut = site.tasOut;
  top.tasTransOut = l.tasB.tasA.tasOut;
  top.tasIndirectOut = l.tasB.tasOut;
}
-- v.c.b.a shared at a local.
production tasShareTriple
top::TasP ::= v::TasV
{
  local site::TasP = tasSite(@v.tasC.tasB.tasA);
  top.tasOut = site.tasOut;
  top.tasTransOut = v.tasC.tasB.tasA.tasOut;
  top.tasIndirectOut = v.tasOut;
}
-- x.a shared through the signature of the forward.
production tasShareSig
top::TasP ::= x::TasX
{
  top.tasTransOut = x.tasA.tasOut;
  top.tasIndirectOut = x.tasAOut;
  forwards to tasSigSite(x.tasA);
}
-- x.b.a shared through the signature of the forward.
production tasShareSigNested
top::TasP ::= x::TasX
{
  top.tasTransOut = x.tasB.tasA.tasOut;
  top.tasIndirectOut = x.tasB.tasOut;
  forwards to tasSigSite(x.tasB.tasA);
}

-- The shared translation demanded before or after its decoration site.
-- These take a decorated tree, as each access to an undecorated parameter decorates it anew.
fun tasTransFirst String ::= p::Decorated TasP = p.tasTransOut ++ " ; " ++ p.tasOut;
fun tasIndirectFirst String ::= p::Decorated TasP = p.tasIndirectOut ++ " ; " ++ p.tasOut;
fun tasSiteFirst String ::= p::Decorated TasP = p.tasOut ++ " ; " ++ p.tasTransOut ++ " ; " ++ p.tasIndirectOut;

equalityTest(tasTransFirst(decorate tasShareNested(tasX("x")) with {}), "x.b.a(site) ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasIndirectFirst(decorate tasShareNested(tasX("x")) with {}), "y[x.b.a(site)] ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareNested(tasX("x")) with {}), "site[x.b.a(site)] ; x.b.a(site) ; y[x.b.a(site)]", String, silver_tests);

equalityTest(tasTransFirst(decorate tasShareNested(tasXFwd("x")) with {}), "x.b.a(site) ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasIndirectFirst(decorate tasShareNested(tasXFwd("x")) with {}), "y[x.b.a(site)] ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareNested(tasXFwd("x")) with {}), "site[x.b.a(site)] ; x.b.a(site) ; y[x.b.a(site)]", String, silver_tests);

equalityTest(tasIndirectFirst(decorate tasShareNestedViaX(tasX("x")) with {}), "x[y[x.b.a(site)]] ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareNestedViaX(tasX("x")) with {}), "site[x.b.a(site)] ; x.b.a(site) ; x[y[x.b.a(site)]]", String, silver_tests);
equalityTest(tasIndirectFirst(decorate tasShareNestedViaX(tasXFwd("x")) with {}), "x[y[x.b.a(site)]] ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareNestedViaX(tasXFwd("x")) with {}), "site[x.b.a(site)] ; x.b.a(site) ; x[y[x.b.a(site)]]", String, silver_tests);

equalityTest(tasTransFirst(decorate tasShareNestedFwd(tasX("x")) with {}), "x.b.a(site) ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasIndirectFirst(decorate tasShareNestedFwd(tasX("x")) with {}), "y[x.b.a(site)] ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareNestedFwd(tasX("x")) with {}), "site[x.b.a(site)] ; x.b.a(site) ; y[x.b.a(site)]", String, silver_tests);

equalityTest(tasTransFirst(decorate tasShareNestedFwd(tasXFwd("x")) with {}), "x.b.a(site) ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasIndirectFirst(decorate tasShareNestedFwd(tasXFwd("x")) with {}), "y[x.b.a(site)] ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareNestedFwd(tasXFwd("x")) with {}), "site[x.b.a(site)] ; x.b.a(site) ; y[x.b.a(site)]", String, silver_tests);

equalityTest(tasTransFirst(decorate tasShareNestedInhBefore(tasX("x")) with {}), "x.b.a(site) ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasIndirectFirst(decorate tasShareNestedInhBefore(tasX("x")) with {}), "y(inh)[x.b.a(site)] ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareNestedInhBefore(tasX("x")) with {}), "site[x.b.a(site)] ; x.b.a(site) ; y(inh)[x.b.a(site)]", String, silver_tests);

equalityTest(tasTransFirst(decorate tasShareNestedInhAfter(tasX("x")) with {}), "x.b.a(site) ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasIndirectFirst(decorate tasShareNestedInhAfter(tasX("x")) with {}), "y(inh)[x.b.a(site)] ; site[x.b.a(site)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareNestedInhAfter(tasX("x")) with {}), "site[x.b.a(site)] ; x.b.a(site) ; y(inh)[x.b.a(site)]", String, silver_tests);

equalityTest(tasTransFirst(decorate tasShareNestedLocal("l") with {}), "l.b.a(site) ; site[l.b.a(site)]", String, silver_tests);
equalityTest(tasIndirectFirst(decorate tasShareNestedLocal("l") with {}), "y[l.b.a(site)] ; site[l.b.a(site)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareNestedLocal("l") with {}), "site[l.b.a(site)] ; l.b.a(site) ; y[l.b.a(site)]", String, silver_tests);

equalityTest(tasTransFirst(decorate tasShareTriple(tasV("v")) with {}), "v.c.b.a(site) ; site[v.c.b.a(site)]", String, silver_tests);
equalityTest(tasIndirectFirst(decorate tasShareTriple(tasV("v")) with {}), "v[x[y[v.c.b.a(site)]]] ; site[v.c.b.a(site)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareTriple(tasV("v")) with {}), "site[v.c.b.a(site)] ; v.c.b.a(site) ; v[x[y[v.c.b.a(site)]]]", String, silver_tests);

equalityTest(tasTransFirst(decorate tasShareSig(tasX("x")) with {}), "x.a(sigSite) ; sigSite[x.a(sigSite)]", String, silver_tests);
equalityTest(tasIndirectFirst(decorate tasShareSig(tasX("x")) with {}), "x[x.a(sigSite)] ; sigSite[x.a(sigSite)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareSig(tasX("x")) with {}), "sigSite[x.a(sigSite)] ; x.a(sigSite) ; x[x.a(sigSite)]", String, silver_tests);

equalityTest(tasTransFirst(decorate tasShareSigNested(tasX("x")) with {}), "x.b.a(sigSite) ; sigSite[x.b.a(sigSite)]", String, silver_tests);
equalityTest(tasIndirectFirst(decorate tasShareSigNested(tasX("x")) with {}), "y[x.b.a(sigSite)] ; sigSite[x.b.a(sigSite)]", String, silver_tests);
equalityTest(tasSiteFirst(decorate tasShareSigNested(tasX("x")) with {}), "sigSite[x.b.a(sigSite)] ; x.b.a(sigSite) ; y[x.b.a(sigSite)]", String, silver_tests);
