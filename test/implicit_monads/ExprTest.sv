grammar implicit_monads;


{-
  This is to test rewriting for all the expressions, not for testing
  different monads.  Therefore we will use Maybe here for everything,
  and test other monads elsewhere.
-}

restricted synthesized attribute const::String;

nonterminal Const with const;

production constC
top::Const ::=
{ top.const = "s"; }


function stringID
String ::= s::String
{ return s; }


restricted inherited attribute down::String;
restricted synthesized attribute up::String;

nonterminal TurnAround with down, up;

production turnAround
top::TurnAround ::=
{
  top.up = top.down;
}



implicit synthesized attribute s_out::Maybe<String>;
implicit synthesized attribute i_out::Maybe<Integer>;
implicit synthesized attribute b_out::Maybe<Boolean>;
implicit synthesized attribute f_out::Maybe<Float>;

nonterminal ExprTest with s_out, i_out, b_out, f_out;

production test_application_monad_arg
top::ExprTest ::= arg::Maybe<String>
{
  top.s_out = stringID(arg);
}

production test_application_monad_fun
top::ExprTest ::= f::Maybe<(String ::= String)>
{
  top.s_out = f("s");
}

equalityTest(test_application_monad_arg(just("s")).s_out, just("s"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_application_monad_arg(nothing()).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_application_monad_fun(just(stringID)).s_out, just("s"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_application_monad_fun(nothing()).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);


production test_access_monad_tree
top::ExprTest ::= tree::Maybe<Const>
{
  top.s_out = tree.const;
}

equalityTest(test_access_monad_tree(just(constC())).s_out, just("s"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_access_monad_tree(nothing()).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);



production test_decorate_monad_tree
top::ExprTest ::= tree::Maybe<TurnAround>
{
  top.s_out = decorate tree with {down = "s";}.up;
}

equalityTest(test_decorate_monad_tree(just(turnAround())).s_out, just("s"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_decorate_monad_tree(nothing()).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);



production test_ifelse_monad_cond1
top::ExprTest ::= cond::Maybe<Boolean>
{
  top.s_out = if cond then "t" else "f";
}
production test_ifelse_monad_cond2
top::ExprTest ::= cond::Maybe<Boolean>
{
  top.s_out = if cond then just("t") else "f";
}
production test_ifelse_monad_cond3
top::ExprTest ::= cond::Maybe<Boolean>
{
  top.s_out = if cond then "t" else just("f");
}
production test_ifelse_monad_cond4
top::ExprTest ::= cond::Maybe<Boolean>
{
  top.s_out = if cond then just("t") else just("f");
}

equalityTest(test_ifelse_monad_cond1(just(true)).s_out, just("t"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifelse_monad_cond1(just(false)).s_out, just("f"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifelse_monad_cond1(nothing()).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);
--
equalityTest(test_ifelse_monad_cond2(just(true)).s_out, just("t"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifelse_monad_cond2(just(false)).s_out, just("f"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifelse_monad_cond2(nothing()).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);
--
equalityTest(test_ifelse_monad_cond3(just(true)).s_out, just("t"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifelse_monad_cond3(just(false)).s_out, just("f"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifelse_monad_cond3(nothing()).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);
--
equalityTest(test_ifelse_monad_cond4(just(true)).s_out, just("t"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifelse_monad_cond4(just(false)).s_out, just("f"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifelse_monad_cond4(nothing()).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);

production test_ifelse_monad_b1
top::ExprTest ::= cond::Boolean
{
  top.s_out = if cond then just("t") else "f";
}
production test_ifelse_monad_b2
top::ExprTest ::= cond::Boolean
{
  top.s_out = if cond then "t" else just("f");
}

equalityTest(test_ifelse_monad_b1(true).s_out, just("t"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifelse_monad_b1(false).s_out, just("f"),
             Maybe<String>, implicit_monad_tests);
--
equalityTest(test_ifelse_monad_b2(true).s_out, just("t"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifelse_monad_b2(false).s_out, just("f"),
             Maybe<String>, implicit_monad_tests);



production test_ifthen_monad_cond1
top::ExprTest ::= cond::Maybe<Boolean>
{
  top.s_out = if cond then "t" end;
}
production test_ifthen_monad_cond2
top::ExprTest ::= cond::Maybe<Boolean>
{
  top.s_out = if cond then just("t") end;
}

equalityTest(test_ifthen_monad_cond1(just(true)).s_out, just("t"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifthen_monad_cond1(just(false)).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifthen_monad_cond1(nothing()).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);
--
equalityTest(test_ifthen_monad_cond2(just(true)).s_out, just("t"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifthen_monad_cond2(just(false)).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifthen_monad_cond2(nothing()).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);

production test_ifthen_monad_b
top::ExprTest ::= cond::Boolean
{
  top.s_out = if cond then just("t") end;
}

equalityTest(test_ifthen_monad_b(true).s_out, just("t"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifthen_monad_b(false).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);

production test_ifthen_no_monad
top::ExprTest ::= cond::Boolean
{
  top.s_out = if cond then "t" end;
}

equalityTest(test_ifthen_no_monad(true).s_out, just("t"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_ifthen_no_monad(false).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);



production test_and_1
top::ExprTest ::= b1::Maybe<Boolean> b2::Boolean
{
  top.b_out = b1 && b2;
}
production test_and_2
top::ExprTest ::= b1::Boolean b2::Maybe<Boolean>
{
  top.b_out = b1 && b2;
}
production test_and_3
top::ExprTest ::= b1::Maybe<Boolean> b2::Maybe<Boolean>
{
  top.b_out = b1 && b2;
}

equalityTest(test_and_1(just(true), true).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_and_1(just(false), true).b_out, just(false),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_and_1(nothing(), true).b_out, nothing(),
             Maybe<Boolean>, implicit_monad_tests);
--
equalityTest(test_and_2(false, just(true)).b_out, just(false),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_and_2(true, just(false)).b_out, just(false),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_and_2(true, nothing()).b_out, nothing(),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_and_2(false, nothing()).b_out, just(false),
             Maybe<Boolean>, implicit_monad_tests);
--
equalityTest(test_and_3(just(true), just(true)).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_and_3(just(false), just(true)).b_out, just(false),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_and_3(nothing(), just(true)).b_out, nothing(),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_and_3(just(true), nothing()).b_out, nothing(),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_and_3(just(false), nothing()).b_out, just(false),
             Maybe<Boolean>, implicit_monad_tests);



production test_or_1
top::ExprTest ::= b1::Maybe<Boolean> b2::Boolean
{
  top.b_out = b1 || b2;
}
production test_or_2
top::ExprTest ::= b1::Boolean b2::Maybe<Boolean>
{
  top.b_out = b1 || b2;
}
production test_or_3
top::ExprTest ::= b1::Maybe<Boolean> b2::Maybe<Boolean>
{
  top.b_out = b1 || b2;
}

equalityTest(test_or_1(just(true), true).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_or_1(just(false), true).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_or_1(just(false), false).b_out, just(false),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_or_1(nothing(), true).b_out, nothing(),
             Maybe<Boolean>, implicit_monad_tests);
--
equalityTest(test_or_2(false, just(true)).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_or_2(true, just(false)).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_or_2(true, nothing()).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_or_2(false, nothing()).b_out, nothing(),
             Maybe<Boolean>, implicit_monad_tests);
--
equalityTest(test_or_3(just(true), just(true)).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_or_3(just(false), just(true)).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_or_3(nothing(), just(true)).b_out, nothing(),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_or_3(just(true), nothing()).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_or_3(just(false), nothing()).b_out, nothing(),
             Maybe<Boolean>, implicit_monad_tests);



production test_not
top::ExprTest ::= b::Maybe<Boolean>
{
  top.b_out = ! b;
}

equalityTest(test_not(just(true)).b_out, just(false),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_not(just(false)).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_not(nothing()).b_out, nothing(),
             Maybe<Boolean>, implicit_monad_tests);



production test_plus1
top::ExprTest ::= i1::Maybe<Integer> i2::Integer
{
  top.i_out = i1 + i2;
}
production test_plus2
top::ExprTest ::= i1::Integer i2::Maybe<Integer>
{
  top.i_out = i1 + i2;
}
production test_plus3
top::ExprTest ::= i1::Maybe<Integer> i2::Maybe<Integer>
{
  top.i_out = i1 + i2;
}

equalityTest(test_plus1(just(1), 3).i_out, just(4),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_plus1(nothing(), 3).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
--
equalityTest(test_plus2(1, just(3)).i_out, just(4),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_plus2(1, nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
--
equalityTest(test_plus3(just(1), just(3)).i_out, just(4),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_plus3(nothing(), just(3)).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_plus3(just(1), nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_plus3(nothing(), nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);



production test_minus1
top::ExprTest ::= i1::Maybe<Integer> i2::Integer
{
  top.i_out = i1 - i2;
}
production test_minus2
top::ExprTest ::= i1::Integer i2::Maybe<Integer>
{
  top.i_out = i1 - i2;
}
production test_minus3
top::ExprTest ::= i1::Maybe<Integer> i2::Maybe<Integer>
{
  top.i_out = i1 - i2;
}

equalityTest(test_minus1(just(1), 3).i_out, just(-2),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_minus1(nothing(), 3).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
--
equalityTest(test_minus2(1, just(3)).i_out, just(-2),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_minus2(1, nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
--
equalityTest(test_minus3(just(1), just(3)).i_out, just(-2),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_minus3(nothing(), just(3)).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_minus3(just(1), nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_minus3(nothing(), nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);



production test_multiply1
top::ExprTest ::= i1::Maybe<Integer> i2::Integer
{
  top.i_out = i1 * i2;
}
production test_multiply2
top::ExprTest ::= i1::Integer i2::Maybe<Integer>
{
  top.i_out = i1 * i2;
}
production test_multiply3
top::ExprTest ::= i1::Maybe<Integer> i2::Maybe<Integer>
{
  top.i_out = i1 * i2;
}

equalityTest(test_multiply1(just(1), 3).i_out, just(3),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_multiply1(nothing(), 3).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
--
equalityTest(test_multiply2(1, just(3)).i_out, just(3),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_multiply2(1, nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
--
equalityTest(test_multiply3(just(1), just(3)).i_out, just(3),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_multiply3(nothing(), just(3)).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_multiply3(just(1), nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_multiply3(nothing(), nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);



production test_divide1
top::ExprTest ::= i1::Maybe<Integer> i2::Integer
{
  top.i_out = i1 / i2;
}
production test_divide2
top::ExprTest ::= i1::Integer i2::Maybe<Integer>
{
  top.i_out = i1 / i2;
}
production test_divide3
top::ExprTest ::= i1::Maybe<Integer> i2::Maybe<Integer>
{
  top.i_out = i1 / i2;
}

equalityTest(test_divide1(just(10), 3).i_out, just(3),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_divide1(nothing(), 3).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
--
equalityTest(test_divide2(10, just(3)).i_out, just(3),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_divide2(10, nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
--
equalityTest(test_divide3(just(10), just(3)).i_out, just(3),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_divide3(nothing(), just(3)).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_divide3(just(10), nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_divide3(nothing(), nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);



production test_modulus1
top::ExprTest ::= i1::Maybe<Integer> i2::Integer
{
  top.i_out = i1 % i2;
}
production test_modulus2
top::ExprTest ::= i1::Integer i2::Maybe<Integer>
{
  top.i_out = i1 % i2;
}
production test_modulus3
top::ExprTest ::= i1::Maybe<Integer> i2::Maybe<Integer>
{
  top.i_out = i1 % i2;
}

equalityTest(test_modulus1(just(10), 3).i_out, just(1),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_modulus1(nothing(), 3).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
--
equalityTest(test_modulus2(10, just(3)).i_out, just(1),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_modulus2(10, nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
--
equalityTest(test_modulus3(just(10), just(3)).i_out, just(1),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_modulus3(nothing(), just(3)).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_modulus3(just(10), nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_modulus3(nothing(), nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);



production test_negate
top::ExprTest ::= i::Maybe<Integer>
{
  top.i_out = - i;
}

equalityTest(test_negate(just(10)).i_out, just(-10),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_negate(nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);



production test_let1
top::ExprTest ::= i::Maybe<Integer>
{
  top.i_out = let x::Integer = i in x end;
}
production test_let2
top::ExprTest ::=
{
  top.i_out = let x::Integer = 3 in x end;
}

equalityTest(test_let1(just(3)).i_out, just(3),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_let1(nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
--
equalityTest(test_let2().i_out, just(3),
             Maybe<Integer>, implicit_monad_tests);



production test_length
top::ExprTest ::= s::Maybe<String>
{
  top.i_out = length(s);
}

equalityTest(test_length(just("s")).i_out, just(1),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_length(nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);



production test_toInteger
top::ExprTest ::= s::Maybe<String>
{
  top.i_out = toInteger(s);
}

equalityTest(test_toInteger(just("3")).i_out, just(3),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_toInteger(nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);



production test_toBoolean
top::ExprTest ::= s::Maybe<String>
{
  top.b_out = toBoolean(s);
}

equalityTest(test_toBoolean(just("true")).b_out, just(true),
             Maybe<Boolean>, implicit_monad_tests);
equalityTest(test_toBoolean(nothing()).b_out, nothing(),
             Maybe<Boolean>, implicit_monad_tests);



production test_toFloat
top::ExprTest ::= s::Maybe<String>
{
  top.f_out = toFloat(s);
}

equalityTest(test_toFloat(just("0.0")).f_out, just(0.0),
             Maybe<Float>, implicit_monad_tests);
equalityTest(test_toFloat(nothing()).f_out, nothing(),
             Maybe<Float>, implicit_monad_tests);



production test_toString
top::ExprTest ::= s::Maybe<Integer>
{
  top.s_out = toString(s);
}

equalityTest(test_toString(just(3)).s_out, just("3"),
             Maybe<String>, implicit_monad_tests);
equalityTest(test_toString(nothing()).s_out, nothing(),
             Maybe<String>, implicit_monad_tests);



production test_case1
top::ExprTest ::= s::Maybe<String>
{
  top.i_out =
      case s of
      | "a" -> 5
      | "b" -> 6
      end;
}

equalityTest(test_case1(just("a")).i_out, just(5),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_case1(just("b")).i_out, just(6),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_case1(just("c")).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);

production test_case2
top::ExprTest ::= s::Maybe<String> i::Integer
{
  top.i_out =
      case s, i of
      | "a", _ -> 5
      | "b", 5 -> 6
      end;
}

equalityTest(test_case2(just("a"), 3).i_out, just(5),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_case2(just("b"), 5).i_out, just(6),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_case2(just("b"), 3).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_case2(just("c"), 0).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);



--The point here is nesting, which is clearer if we use this syntax rather than (_, _, _)
production test_case_nested
top::ExprTest ::= p::Maybe<Pair<String Pair<Integer Integer>>>
{
  top.i_out =
      case p of
      | pair("a", pair(0, 1)) -> 3
      | pair("b", pair(_, 0)) -> 4
      | _ -> 5
      end;
}

equalityTest(test_case_nested(just(pair("a", pair(0, 1)))).i_out, just(3),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_case_nested(just(pair("b", pair(0, 0)))).i_out, just(4),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_case_nested(just(pair("a", pair(0, 0)))).i_out, just(5),
             Maybe<Integer>, implicit_monad_tests);
equalityTest(test_case_nested(nothing()).i_out, nothing(),
             Maybe<Integer>, implicit_monad_tests);



implicit synthesized attribute i_out_lst::[Integer];
attribute i_out_lst occurs on ExprTest;

production test_case_any1
top::ExprTest ::= p::Pair<String Integer>
{
  top.i_out_lst =
      case_any p of
      | pair("a", 0) -> [3]
      | pair("b", 1) -> [4]
      | _ -> 5
      end;
}
production test_case_any2
top::ExprTest ::= p::[Pair<String Integer>]
{
  top.i_out_lst =
      case_any p of
      | pair("a", 0) -> 3
      | pair("b", 1) -> 4
      | _ -> 5
      end;
}

equalityTest(test_case_any1(pair("a", 0)).i_out_lst, [3, 5],
             [Integer], implicit_monad_tests);
equalityTest(test_case_any1(pair("b", 1)).i_out_lst, [4, 5],
             [Integer], implicit_monad_tests);
equalityTest(test_case_any1(pair("c", 2)).i_out_lst, [5],
             [Integer], implicit_monad_tests);
--
equalityTest(test_case_any2([pair("a", 0)]).i_out_lst, [3, 5],
             [Integer], implicit_monad_tests);
equalityTest(test_case_any2([pair("b", 1)]).i_out_lst, [4, 5],
             [Integer], implicit_monad_tests);
equalityTest(test_case_any2([pair("c", 2)]).i_out_lst, [5],
             [Integer], implicit_monad_tests);
equalityTest(test_case_any2([]).i_out_lst, [],
             [Integer], implicit_monad_tests);

