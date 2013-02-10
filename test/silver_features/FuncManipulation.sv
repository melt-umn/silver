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

synthesized attribute sec_valid :: Integer occurs on Section;
synthesized attribute sec_inv<a> :: a; attribute sec_inv<String> occurs on Section;
synthesized attribute sec_inv2 :: String; -- no occurs
inherited attribute sec_inv3 :: Integer occurs on Section; -- inh

abstract production section
top::Section ::=
{
  top.sec_valid = 2;
  top.sec_inv = "hi";
}

equalityTest( (.sec_valid)(decorate section() with {}), 2, Integer, silver_tests );
equalityTest( (.sec_valid)(section()), 2, Integer, silver_tests );

global sections :: [Section] = [section(), section()];

equalityTest( map((.sec_valid), sections), [2,2], [Integer], silver_tests );

wrongCode "attribute sections currently do not work with parameterized attributes" {
 -- Valid, but for the moment does not work! TODO
 global s :: [String] = map((.sec_inv), sections);
}

wrongCode "does not occur on" {
 global s :: [String] = map((.sec_inv2), sections);
}

wrongCode "Only synthesized attributes are currently supported" {
 -- Valid, but for the moment does not work! TODO
 global s :: [Integer] = map((.sec_inv3), sections);
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


