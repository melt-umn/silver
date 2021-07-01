import silver:reflect:nativeserialize;

nonterminal NT;

abstract production foo
top::NT ::= z::Integer f::Float b::Boolean s::String
{}

abstract production bar
top::NT ::= a::NT b::NT
{}

function main 
IOVal<Integer> ::= largs::[String] ioin::IO
{
  local v :: NT = bar(foo(1, 0.5, false, "AAA"), foo(2, 0.25, true, "BBB"));
  local r :: Either<ByteArray String> = nativeSerialize(new(v));

  local p1 :: IO = if r.isLeft
               then print("OK\n", writeByteFile("out.svb", r.fromLeft, ioin))
               else print(r.fromRight ++ "\n", ioin);

  local res :: Either<NT String> = nativeDeserialize(r.fromLeft);
  local p2 :: IO = if r.isLeft then print(hackUnparse(res) ++ "\n", p1)
                   else p1;

  return ioval(p2, if r.isLeft then 0 else 1);
}

