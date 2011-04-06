grammar exist_patt;


nonterminal T;

abstract production foo
top::T ::= a::a  f::Function(String ::= a)
{
}

function i2s
String ::= i::Integer
{ 
  return toString(i);
}

function main
IOVal<Integer> ::= args::[String] ioin::IO
{

  local attribute t::T;
  t = foo( 3, i2s );
  
  local attribute q::String;
  q = match t return String with
        foo(x,y) -> y(x)
        else -> "fail"
      end;

  return ioval(print(q++"\n",ioin), 0);
}


