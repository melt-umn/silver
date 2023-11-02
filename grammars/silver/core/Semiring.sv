grammar silver:core;

@{- Provides operator overloads for the `+` and `*` operators.
  -
  - Laws:
  -
  - * Commutative monoid under addition:
  -   * Associativity: `add(add(x, y), z) = add(x, add(y, z))`
  -   * Left Identity: `add(zero, x) = x`
  -   * Right Identity: `add(x, zero) = x`
  -   * Commutativity: `add(x, y) = add(y, x)`
  - * Monoid under multiplication:
  -   * Associativity: `mul(mul(x, y), z) = mul(x, mul(y, z))`
  -   * Left Identity: `mul(one, x) = x`
  -   * Right Identity: `mul(x, one) = x`
  - * Multiplication distributes over addition:
  -   * Left Distributivity: `mul(x, add(y, z)) = add(mul(x, y), mul(x, z))`
  -   * Right Distributivity: `mul(add(x, y), z) = add(mul(x, z), mul(y, z))`
  - * Left Annihilation: `mul(zero, x) = zero`
  - * Right Annihilation: `mul(x, zero) = zero`
  -}
class Semiring a {
  @{- The function corresponding to the `+` operator. -}
  add :: (a ::= a a);
  @{- The value corresponding to the `0` constant. -}
  zero :: a;
  @{- The function corresponding to the `*` operator. -}
  mul :: (a ::= a a);
  @{- The value corresponding to the `1` constant. -}
  one :: a;
}

instance Semiring Integer {
  add = addInteger;
  zero = 0;
  mul = mulInteger;
  one = 1;
}

function addInteger
Integer ::= a::Integer b::Integer
{
  return error("Foreign function");
} foreign {
  "java": return "(%a% + (int)%b%)";
}

function mulInteger
Integer ::= a::Integer b::Integer
{
  return error("Foreign function");
} foreign {
  "java": return "(%a% * (int)%b%)";
}

instance Semiring Float {
  add = addFloat;
  zero = 0.0;
  mul = mulFloat;
  one = 1.0;
}

function addFloat
Float ::= a::Float b::Float
{
  return error("Foreign function");
} foreign {
  "java": return "(%a% + (float)%b%)";
}

function mulFloat
Float ::= a::Float b::Float
{
  return error("Foreign function");
} foreign {
  "java": return "(%a% * (float)%b%)";
}

instance Semiring Boolean {
  add = disj;
  zero = false;
  mul = conj;
  one = true;
}

@{- Converts a non-negative integer into an arbitrary semiring. -}
function fromNonnegativeInteger
Semiring a => a ::= n::Integer
{
  local fromN2::a = fromNonnegativeInteger(n / 2);
  return
    if n == 0 then
      zero
    else if n % 2 == 0 then
      add(fromN2, fromN2)
    else
      add(add(fromN2, one), fromN2);
}

@{- Compute the sum of a list. -}
global sum :: Semiring a => (a ::= [a]) = foldr(add, zero, _);

@{- Compute the product of a list. -}
global product :: Semiring a => (a ::= [a]) = foldr(mul, one, _);
