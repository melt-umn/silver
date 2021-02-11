@{-
  - @config fileSplit
  - @config weight 100
  - @config title "The Second File"
  -
  - Docs for sub in Second.sv
  -}
function sub
Integer ::= a::Integer b::Integer
{
	return add(a, -b);
}