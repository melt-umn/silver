{- No tyvars -}
fun adder Integer ::= x::Integer y::Integer = x + y;

equalityTest(adder(1, 2), 3, Integer, silver_tests);
equalityTest(adder(1, -2), -1, Integer, silver_tests);

{- Multiple tyvars -}
fun foldr b ::= f::(b ::= a b)  i::b  l::[a] =
  if null(l) then i else f(head(l), foldr(f, i, tail(l)));

equalityTest(foldr(adder, 0, [1,2,3,4]), 10, Integer, silver_tests);
equalityTest(foldr(adder, 0, []), 0, Integer, silver_tests);

{- Multiple tyvars with a constraint -}
fun find Eq a => b ::= lst::[(a, b)] key::a otherwise::b = 
  case lst of
    [] -> otherwise
  | h::t -> if fst(h) == key then snd(h) else find (t, key, otherwise)
  end;

global mapLst::[(String, Integer)] = [("a", 1), ("b", 2), ("c", 3), ("d", 4)];
equalityTest(find (mapLst, "c", -1), 3, Integer, silver_tests);
equalityTest(find (mapLst, "e", -1), -1, Integer, silver_tests);


nonterminal TestBinding;
synthesized attribute key::String occurs on TestBinding;
synthesized attribute val::Integer occurs on TestBinding;
inherited attribute inhKey::String occurs on TestBinding;

abstract production testBinding
top::TestBinding ::= s::String i::Integer
{
  top.key = s;
  top.val = i;
}

{- Occurs on constraints -}
fun getKey attribute key i occurs on a => String ::= thing::Decorated a with i = thing.key;
fun getInhKey attribute inhKey occurs on a => String ::= thing::Decorated a with i = thing.inhKey;
fun getVal attribute val i occurs on a => Integer ::= thing::Decorated a with i = thing.val;

global bnd::Decorated TestBinding = decorate testBinding ("a", 5) with {inhKey = "key";};

equalityTest(getVal(bnd), 5, Integer, silver_tests);
equalityTest(getKey(bnd), "a", String, silver_tests);
equalityTest(getInhKey(bnd), "key", String, silver_tests);

{- subset constraint -}
fun getInhKey2 {inhKey} subset i => String ::= x::Decorated TestBinding with i = x.inhKey;

equalityTest(getInhKey2(bnd), "key", String, silver_tests);