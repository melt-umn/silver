grammar implicit_monads;


implicit synthesized attribute i_state_out::State<Integer Integer>;
implicit inherited attribute i_state_in::State<Integer Integer>;

implicit synthesized attribute i_unit_out::Unit<Integer>;
implicit inherited attribute i_unit_in::Unit<Integer>;

nonterminal MonadTest with
   i_state_out, i_state_in, i_unit_out, i_unit_in;

nonterminal TurnAround_M with
   i_state_out, i_state_in, i_unit_out, i_unit_in;

production turnAround_M
top::TurnAround_M ::=
{
  top.i_state_out = top.i_state_in;
  top.i_unit_out = top.i_unit_in;
}



--Test case_any only works with alternative monads
wrongCode "not an instance of" {
  production test_case_any_no_alt1
  top::MonadTest ::=
  {
    top.i_state_out = 
        case_any 3, 4 of
        | 3, 4 -> 3
        | 5, 6 -> 6
        | 7, 8 -> 8
        | _, _ -> 9
        end;
  }
}
wrongCode "not an instance of" {
  production test_case_any_no_alt2
  top::MonadTest ::= c::TurnAround_M
  {
    c.i_state_in = 
        case_any 3, 4 of
        | 3, 4 -> 3
        | 5, 6 -> 6
        | 7, 8 -> 8
        | _, _ -> 9
        end;
  }
}



--Test user-defined monads can be used implicitly
production test_user_monad1
top::MonadTest ::=
{
  top.i_unit_out = top.i_unit_in + 5;
}
production test_user_monad2
top::MonadTest ::= c::TurnAround_M
{
  implicit c.i_unit_in = ;
  top.i_unit_out = 2 * c.i_unit_out;
}

--these are just to test that it actually evaluates
equalityTest(decorate test_user_monad1() with {i_unit_in=unitA();}.i_unit_out, unitA(),
             Unit<Integer>, implicit_monad_tests);
equalityTest(test_user_monad2(turnAround_M()).i_unit_out, unitA(),
             Unit<Integer>, implicit_monad_tests);



nonterminal Unit<a>;

production unitA
top::Unit<a> ::=
{ }

instance Functor Unit {
  map = \ f::(b ::= a) m::Unit<a> -> unitA();
}

instance Apply Unit {
  ap = \ mf::Unit<(b ::= a)> m::Unit<a> -> unitA();
}

instance Applicative Unit {
  pure = \ a -> unitA();
}

instance Bind Unit {
  bind = \ m::Unit<a> fn::(Unit<b> ::= a) -> unitA();
}

instance Monad Unit {}

instance MonadFail Unit {
  fail = \ _ -> unitA();
}

instance Eq Unit<a> {
  eq = \ _ _ -> true;
}

