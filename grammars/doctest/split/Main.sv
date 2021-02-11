
@@{-
   - @config fileSplit
   - @config grammarWeight -10
   - @config title "This is the Main file"
   - @config noToc
   -}

@{-
  -
  - See also @link[sub], which is implemented in terms of this function.
  - 
  - @param a the first number to add
  - @param b the second number to add
  - @return the value of a + b (possibly plus bias)
  - @prodattr bias a list of integers, the sum of which is added to every addition
  - @warning NOTE: see the bias prod attr. An extension may have changed the behavior of addition.
  -
  - Here is some discussion of why it is a good idea to allow extensible addition:
  - - It was a good idea at the time
  - - And now the java translation depends on it
  -}
function add
Integer ::= a::Integer b::Integer
{
  production attribute bias::[Integer] with ++;
  bias := [];

  return a + b + foldl((\x::Integer y::Integer -> x + y), 0, bias);
}

@@{-Another doc in Main.sv-}

@@{-@config hide

something hidden-}