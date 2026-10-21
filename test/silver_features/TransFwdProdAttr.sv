grammar silver_features;

-- A forward production attribute that is shared as the forward becomes the forward when the production's forward is
-- evaluated, so its translation attributes get the production's inherited attributes on them, as any forward's do.
-- Each test demands an attribute through the forward before anything evaluates the production's own translation.

inherited attribute tfaK::String;
synthesized attribute tfaV::String;
nonterminal TfaT with tfaK, tfaV;
production tfaLeaf
top::TfaT ::= n::String
{ top.tfaV = n ++ "(" ++ top.tfaK ++ ")"; }

translation attribute tfaTr::TfaT;
synthesized attribute tfaS::String;
nonterminal TfaN with tfaTr, tfaS;

-- tfaS uses the inherited attribute of the translation.
production tfaBase
top::TfaN ::= n::String
{
  top.tfaTr = tfaLeaf(n);
  top.tfaS = top.tfaTr.tfaV;
}

-- Shared as the forward.
production tfaFwd
top::TfaN ::= n::String
{
  forward x = tfaBase(n);
  forwards to @x;
}

-- Shared as the forward in one branch.
production tfaCond
top::TfaN ::= b::Boolean n::String
{
  forward x = tfaBase(n);
  forwards to if b then @x else tfaBase("other");
}

-- A chain of forward production attributes.
production tfaChain
top::TfaN ::= n::String
{
  forward x = tfaBase(n);
  forward y = @x;
  forwards to @y;
}

-- A child shared into a forward production attribute.
production tfaSharedChild
top::TfaN ::= c::TfaN
{
  forward x = @c;
  forwards to @x;
}

-- A matched production that gives its child the translation's inherited attribute.
inherited attribute tfaK3::String;
synthesized attribute tfaS3::String;
nonterminal TfaW with tfaK3, tfaS3;
production tfaWLeaf
top::TfaW ::=
{ top.tfaS3 = top.tfaK3; }

production tfaMatched
top::TfaN ::= w::TfaW
{
  top.tfaTr = tfaLeaf("m");
  top.tfaS = "";
  w.tfaK3 = top.tfaTr.tfaV;
}
production tfaFwdMatched
top::TfaN ::=
{
  forward x = tfaMatched(tfaWLeaf());
  forwards to @x;
}

synthesized attribute tfaOut::String;
nonterminal TfaRoot with tfaOut;

production tfaUse
top::TfaRoot ::= c::TfaN
{
  c.tfaTr.tfaK = "k";
  top.tfaOut = c.tfaS;
}

-- Matching follows the forward to tfaMatched.
production tfaMatch
top::TfaRoot ::= c::TfaN
{
  c.tfaTr.tfaK = "k";
  top.tfaOut = case c of tfaMatched(w) -> w.tfaS3 | _ -> "none" end;
}

equalityTest(decorate tfaUse(tfaFwd("a")) with {}.tfaOut, "a(k)", String, silver_tests);
equalityTest(decorate tfaUse(tfaCond(true, "a")) with {}.tfaOut, "a(k)", String, silver_tests);
equalityTest(decorate tfaUse(tfaCond(false, "a")) with {}.tfaOut, "other(k)", String, silver_tests);
equalityTest(decorate tfaUse(tfaChain("a")) with {}.tfaOut, "a(k)", String, silver_tests);
equalityTest(decorate tfaUse(tfaSharedChild(tfaBase("a"))) with {}.tfaOut, "a(k)", String, silver_tests);
equalityTest(decorate tfaMatch(tfaFwdMatched()) with {}.tfaOut, "m(k)", String, silver_tests);
