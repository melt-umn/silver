
-- IDEALLY, this nonterminal would not have to be parameterized by a!
nonterminal Bool<a> with not<a>, and<a>, or<a>, ifTrue<a>, ifFalse<a>, condValue<a>;

-- The elimination form for booleans
inherited attribute ifTrue<a> :: a;
inherited attribute ifFalse<a> :: a;
synthesized attribute condValue<a> :: a;

-- Some operators on booleans
synthesized attribute not<a> :: Bool<a>;
synthesized attribute and<a> :: Function(Bool<a> ::= Bool<a>);
synthesized attribute or<a> :: Function(Bool<a> ::= Bool<a>);

-- The introduction forms for booleans

abstract production t
top::Bool<a> ::=
{
  top.condValue = top.ifTrue;

  top.not = f();
  top.and = transSame;
  top.or = transT;
}

abstract production f
top::Bool<a> ::=
{
  top.condValue = top.ifFalse;

  top.not = t();
  top.and = transF;
  top.or = transSame;
}

-- Transformations ( IDEALLY, these would be local to the above constructors! )
-- (And in practice for more complex types, would need closures!)

abstract production transT
top::Bool<a> ::= i::Bool<a>
{
  forwards to t();
}
abstract production transF
top::Bool<a> ::= i::Bool<a>
{
  forwards to f();
}
abstract production transSame
top::Bool<a> ::= i::Bool<a>
{
  forwards to i;
}
abstract production transOpposite
top::Bool<a> ::= i::Bool<a>
{
  forwards to i.not;
}


-- A simple test case.

function main
IOVal<Integer> ::= args::[String] ioin::IO
{
  local attribute v :: String;
  v = decorate
        t().and(f().or(t().and(f().not)))  -- t && (f || (t && !f)) === t
      with {
        ifTrue = "True!";
        ifFalse = "False!";
      }.condValue;
  
  local attribute iop :: IO;
  iop = print(v ++ "\n", ioin);
  
  return ioval(iop, 0);
}

