imports silver:testing;
imports silver:reflect;
imports silver:langutil;

function identityHashCode
Integer ::= nt::a
{
  return error("Not impl");
} foreign {
  "java" : return "(System.identityHashCode(%nt%))";
}

nonterminal Nat;
synthesized attribute val :: Integer occurs on Nat;
synthesized attribute plusOne :: Nat occurs on Nat;
synthesized attribute minusOne :: Nat occurs on Nat;

abstract production z
top::Nat ::=
{
  top.val = 0;
  top.plusOne = s(top);
  top.minusOne = error("minusOne of z()");
}

abstract production s
top::Nat ::= a::Nat
{
  top.val = a.val + 1;
  top.plusOne = s(a.plusOne);
  top.minusOne = new(a);
}

nonterminal CST;
synthesized attribute transform::CST occurs on CST;

terminal Foo_t 'foo';
ignore terminal Space_t ' ';

concrete production foo
top::CST ::= 'foo'
{
	top.transform = bar();
}

abstract production bar
top::CST ::=
{
	top.transform = attachNote logicalLocationNote(txtLoc("bar")) on bar() end;
}

parser cstParse::CST {
	force_origins;
}