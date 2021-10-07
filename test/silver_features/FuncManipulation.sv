-- Function manipulations

-----------------------------
-- Basic function application

function zeroArgFunction
Integer ::=
{
  return 0;
}

function twoArgFunction
Integer ::= s::String  i::Integer
{
  return i;
}

wrongCode "Undeclared value" {
  global nop::Integer = oneArgFunction(1);
}

wrongCode "Too many arguments" {
  global nop::Integer = zeroArgFunction(1);
}

wrongCode "Too few arguments" {
  global nop::Integer = twoArgFunction();
}

wrongCode "Too few arguments" {
  global nop::Integer = twoArgFunction("s");
}

wrongCode "Too many arguments" {
  global nop::Integer = twoArgFunction("s",2,2);
}

wrongCode "expected Integer but argument is of type String" {
  global nop::Integer = twoArgFunction("s","w");
}

wrongCode "expected String but argument is of type Integer" {
  global nop::Integer = twoArgFunction(1,2);
}

-- Works applying indirectly
equalityTest( head([zeroArgFunction])(), 0, Integer, silver_tests ) ;
equalityTest( head([twoArgFunction])("s", 1), 1, Integer, silver_tests ) ;

---------------------------
-- Attribute sections (.pp)

nonterminal Section;

synthesized attribute sec1 :: Integer occurs on Section;
synthesized attribute sec2<a> :: a; attribute sec2<String> occurs on Section;
inherited attribute sec3 :: Integer occurs on Section; -- inh

synthesized attribute sec_inv1 :: String; -- no occurs

abstract production section
top::Section ::=
{
  top.sec1 = 2;
  top.sec2 = "hi";
}

equalityTest( (.sec1)(decorate section() with {}), 2, Integer, silver_tests );
equalityTest( (.sec1)(section()), 2, Integer, silver_tests );

global sections :: [Section] = [section(), section()];
global decSections :: [Decorated Section with {sec3}] =
  [decorate section() with {sec3 = 42;}, decorate section() with {sec3 = 221;}];

equalityTest( map((.sec1), sections), [2,2], [Integer], silver_tests );
equalityTest( map((.sec2), sections), ["hi", "hi"], [String], silver_tests );
equalityTest( map((.sec1), decSections), [2,2], [Integer], silver_tests );
equalityTest( map((.sec2), decSections), ["hi", "hi"], [String], silver_tests );
equalityTest( map((.sec3), decSections), [42,221], [Integer], silver_tests );

equalityTest( map((.fst), [(1, "a"), (2, "b")]), [1,2], [Integer], silver_tests );
equalityTest( map((.fromRight), [right(1), right(2)]), [1,2], [Integer], silver_tests );

wrongCode "The attribute section (.fromLeft) has an ambiguous inferred output type a, where a is unspecialized" {
  global b::Boolean = null(map((.fromLeft), [right(1), right(2)]));
}

wrongCode "does not occur on" {
  global s :: [String] = map((.sec_inv1), sections);
}

wrongCode "'x' has type a and cannot have attributes." {
  global s :: [Integer] = map((.sec1), []);
}
wrongCode "Attribute section (.sec2) has inferred output type Integer that does not match the attribute's type String" {
  global s :: [Integer] = map((.sec2), sections);
}

synthesized attribute sec5<a> :: a;
nonterminal SectionPoly<a> with sec5<a>;
production sectionPoly
top::SectionPoly<a> ::= x::a
{ top.sec5 = x; }

global requireSP :: (SectionPoly<a> ::= SectionPoly<a>) = id;

-- Here the check that the output type is unambiguous is the only error:
wrongCode "The attribute section (.sec5) has an ambiguous inferred output type silver_features:SectionPoly<a>, where a is unspecialized" {
  global sp::String = (.sec5)(requireSP((.sec5)(sectionPoly(sectionPoly(42)))));
}

-------------------------------
-- Partial function application

global onePartFun :: (Integer ::= Integer) = twoArgFunction("s",_);

equalityTest( onePartFun(3), 3, Integer, silver_tests );

function addTwo
Integer ::= a::Integer  b::Integer
{
  return a + b;
}

equalityTest( addTwo(addTwo(1,_)(2),_)(addTwo(_,3)(4)), 10, Integer, silver_tests ) ;

global invoker :: (Integer ::= String) = twoArgFunction(_,genInt());

-- If the arg passed in invoker is getting re-evaluated, this will result in something like
-- [0,2,3,4,5] [1,1,1,1,1]
equalityTest( map(invoker, repeat("", 5)), repeat(invoker(""), 5), [Integer], silver_tests ) ;

nonterminal FuncManipNT with fmeval;
synthesized attribute fmeval :: Integer;

abstract production fmadd
top::FuncManipNT ::= a::Integer b::Integer
{
  top.fmeval = a + b;
}

-- There was a bug where the types for productions NodeFactories were too
-- specific (the production node type, not nonterminal node type),
-- not permitted because generics are invariant.
global fmeOne :: (FuncManipNT ::= Integer) = fmadd(2,_);
global fmeTwo :: (FuncManipNT ::= Integer) = fmadd(_,3);

equalityTest( fmeOne(3).fmeval, 5, Integer, silver_tests );
equalityTest( fmeTwo(3).fmeval, 6, Integer, silver_tests );


