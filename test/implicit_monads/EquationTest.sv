grammar implicit_monads;


implicit synthesized attribute i_eq_out::[Integer];
implicit inherited attribute i_eq_in::[Integer];

restricted synthesized attribute r_eq_out::Integer;
restricted inherited attribute r_eq_in::Integer;

synthesized attribute u_eq_out::Integer;
inherited attribute u_eq_in::Integer;

implicit synthesized attribute no_fail_out::State<Integer Integer>;
implicit inherited attribute no_fail_in::State<Integer Integer>;

nonterminal EquationTest with
   i_eq_out, i_eq_in, r_eq_out, r_eq_in, u_eq_out, u_eq_in,
   no_fail_in, no_fail_out;

nonterminal TurnAround_Eq with
   i_eq_out, i_eq_in, r_eq_out, r_eq_in, u_eq_out, u_eq_in,
   no_fail_in, no_fail_out;
production turnAround_Eq
top::TurnAround_Eq ::=
{
  top.i_eq_out = top.i_eq_in;
  top.r_eq_out = top.r_eq_in;
  top.u_eq_out = top.u_eq_in;
  top.no_fail_out = top.no_fail_in;
}



--Test empty equations work with failure monads
production test_empty_eq1
top::EquationTest ::=
{
  implicit top.i_eq_out = ;
}
production test_empty_eq2
top::EquationTest ::= c::TurnAround_Eq
{
  implicit c.i_eq_in = ;
  top.i_eq_out = c.i_eq_out;
}

equalityTest(test_empty_eq1().i_eq_out, [],
             [Integer], implicit_monad_tests);
equalityTest(test_empty_eq2(turnAround_Eq()).i_eq_out, [],
             [Integer], implicit_monad_tests);



--Test empty equations only work with failure monads
wrongCode "not an instance of MonadFail" {
  production test_empty_eq_no_fail
  top::EquationTest ::=
  {
    implicit top.no_fail_out = ;
  }
}
wrongCode "not an instance of MonadFail" {
  production test_empty_eq_no_fail
  top::EquationTest ::= c::TurnAround_Eq
  {
    implicit c.no_fail_in = ;
  }
}



--test restricted flowing into implicit
production test_restricted_impeq1
top::EquationTest ::=
{
  top.r_eq_out = 3;
  top.i_eq_out = top.r_eq_out;
}
production test_restricted_impeq2
top::EquationTest ::=
{
  top.i_eq_out = top.r_eq_in;
}
production test_restricted_impeq3
top::EquationTest ::= c::TurnAround_Eq
{
  top.r_eq_out = 4;
  c.i_eq_in = top.r_eq_out;
  top.i_eq_out = c.i_eq_out;
}
production test_restricted_impeq4
top::EquationTest ::= c::TurnAround_Eq
{
  c.i_eq_in = top.r_eq_in;
  top.i_eq_out = c.i_eq_out;
}

equalityTest(test_restricted_impeq1().i_eq_out, [3],
             [Integer], implicit_monad_tests);
equalityTest(decorate test_restricted_impeq2() with {r_eq_in = 4;}.i_eq_out, [4],
             [Integer], implicit_monad_tests);
equalityTest(test_restricted_impeq3(turnAround_Eq()).i_eq_out, [4],
             [Integer], implicit_monad_tests);
equalityTest(decorate test_restricted_impeq4(turnAround_Eq()) with {r_eq_in = 5;}.i_eq_out, [5],
             [Integer], implicit_monad_tests);



--test implicit flowing into unrestricted
production test_implicit_unreq1
top::EquationTest ::=
{
  top.i_eq_out = 3;
  top.u_eq_out = head(top.i_eq_out);
}
production test_implicit_unreq2
top::EquationTest ::=
{
  top.u_eq_out = head(top.i_eq_in);
}
production test_implicit_unreq3
top::EquationTest ::= c::TurnAround_Eq
{
  top.i_eq_out = 4;
  c.u_eq_in = head(top.i_eq_out);
  top.u_eq_out = c.u_eq_out;
}
production test_implicit_unreq4
top::EquationTest ::= c::TurnAround_Eq
{
  c.u_eq_in = head(top.i_eq_in);
  top.u_eq_out = c.u_eq_out;
}

equalityTest(test_implicit_unreq1().u_eq_out, 3,
             Integer, implicit_monad_tests);
equalityTest(decorate test_implicit_unreq2() with {i_eq_in = [4];}.u_eq_out, 4,
             Integer, implicit_monad_tests);
equalityTest(test_implicit_unreq3(turnAround_Eq()).u_eq_out, 4,
             Integer, implicit_monad_tests);
equalityTest(decorate test_implicit_unreq4(turnAround_Eq()) with {i_eq_in = [5];}.u_eq_out, 5,
             Integer, implicit_monad_tests);



--test restricted flowing into unrestricted
production test_restricted_unreq1
top::EquationTest ::=
{
  top.r_eq_out = 3;
  top.u_eq_out = top.r_eq_out;
}
production test_restricted_unreq2
top::EquationTest ::=
{
  top.u_eq_out = top.r_eq_in;
}
production test_restricted_unreq3
top::EquationTest ::= c::TurnAround_Eq
{
  top.r_eq_out = 4;
  c.u_eq_in = top.r_eq_out;
  top.u_eq_out = c.u_eq_out;
}
production test_restricted_unreq4
top::EquationTest ::= c::TurnAround_Eq
{
  c.u_eq_in = top.r_eq_in;
  top.u_eq_out = c.u_eq_out;
}

equalityTest(test_restricted_unreq1().u_eq_out, 3,
             Integer, implicit_monad_tests);
equalityTest(decorate test_restricted_unreq2() with {r_eq_in = 4;}.u_eq_out, 4,
             Integer, implicit_monad_tests);
equalityTest(test_restricted_unreq3(turnAround_Eq()).u_eq_out, 4,
             Integer, implicit_monad_tests);
equalityTest(decorate test_restricted_unreq4(turnAround_Eq()) with {r_eq_in = 5;}.u_eq_out, 5,
             Integer, implicit_monad_tests);



--test implicit not flowing into restricted
wrongCode "must be restricted" {
  production test_implicit_reseq1
  top::EquationTest ::=
  {
    top.r_eq_out = top.i_eq_in;
  }
}
wrongCode "must be restricted" {
  production test_implicit_reseq2
  top::EquationTest ::= c::TurnAround_Eq
  {
    top.r_eq_out = c.i_eq_out;
  }
}
wrongCode "must be restricted" {
  production test_implicit_reseq3
  top::EquationTest ::= c::TurnAround_Eq
  {
    c.r_eq_in = top.i_eq_in;
  }
}
wrongCode "must be restricted" {
  production test_implicit_reseq4
  top::EquationTest ::= c::TurnAround_Eq
  {
    c.r_eq_in = top.i_eq_out;
  }
}



--test unrestricted not flowing into restricted
wrongCode "must be restricted" {
  production test_unrestricted_reseq1
  top::EquationTest ::=
  {
    top.r_eq_out = top.u_eq_in;
  }
}
wrongCode "must be restricted" {
  production test_unrestricted_reseq2
  top::EquationTest ::= c::TurnAround_Eq
  {
    top.r_eq_out = c.u_eq_out;
  }
}
wrongCode "must be restricted" {
  production test_unrestricted_reseq3
  top::EquationTest ::= c::TurnAround_Eq
  {
    c.r_eq_in = top.u_eq_in;
  }
}
wrongCode "must be restricted" {
  production test_unrestricted_reseq4
  top::EquationTest ::= c::TurnAround_Eq
  {
    c.r_eq_in = top.u_eq_out;
  }
}



--test unrestricted not flowing into implicit
wrongCode "must be either implicit or restricted" {
  production test_unrestricted_impeq1
  top::EquationTest ::=
  {
    top.i_eq_out = top.u_eq_in;
  }
}
wrongCode "must be either implicit or restricted" {
  production test_unrestricted_impeq2
  top::EquationTest ::= c::TurnAround_Eq
  {
    top.i_eq_out = c.u_eq_out;
  }
}
wrongCode "must be either implicit or restricted" {
  production test_unrestricted_impeq3
  top::EquationTest ::= c::TurnAround_Eq
  {
    c.i_eq_in = top.u_eq_in;
  }
}
wrongCode "must be either implicit or restricted" {
  production test_unrestricted_impeq4
  top::EquationTest ::= c::TurnAround_Eq
  {
    c.i_eq_in = top.u_eq_out;
  }
}

