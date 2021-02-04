grammar patt;


--Integers
warnCode "not exhaustive" {
  function fun_integer
  String ::=
  {
    return case 0 of
           | 0 -> "0"
           | 2 -> "2"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_integer_complete
  String ::=
  {
    return case 0 of
           | 0 -> "1"
           | 2 -> "2"
           | _ -> "other"
           end;
  }
}


--Floats
warnCode "not exhaustive" {
  function fun_float
  String ::=
  {
    return case 1.0 of
           | 0.0 -> "0.0"
           | 1.0 -> "1.0"
           | 99.9 -> "99.9"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_float_complete
  String ::=
  {
    return case 1.0 of
           | 0.0 -> "0.0"
           | 1.0 -> "1.0"
           | _ -> "other"
           end;
  }
}


--Strings
warnCode "not exhaustive" {
  function fun_string
  String ::=
  {
    return case "x" of
           | "" -> ""
           | "aaa" -> "aaa"
           | "*" -> "*"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_string_complete
  String ::=
  {
    return case "x" of
           | "" -> ""
           | "aaa" -> "aaa"
           | x -> "other"
           end;
  }
}


--Booleans
warnCode "not exhaustive" {
  function fun_only_true
  String ::=
  {
    return case true of
           | true -> "true"
           end;
  }
}

warnCode "not exhaustive" {
  function fun_only_false
  String ::=
  {
    return case true of
           | false -> "false"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_bool_constructors_complete
  String ::=
  {
    return case true of
           | true -> "true"
           | false -> "false"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_bool_var_complete
  String ::=
  {
    return case true of
           | false -> "false"
           | var -> "other"
           end;
  }
}


--Lists
warnCode "not exhaustive" {
  function fun_list_no_nil
  String ::=
  {
    return case [1, 2] of
           | 1::2::_ -> "1"
           | 1::_ -> "2"
           | _::_ -> "3"
           end;
  }
}

warnCode "not exhaustive" {
  function fun_list_no_cons
  String ::=
  {
    return case [1, 2] of
           | [] -> "nil"
           end;
  }
}

warnCode "not exhaustive" {
  function fun_list_no_general_list
  String ::=
  {
    return case [1, 2] of
           | [] -> "nil"
           | [_] -> "1"
           | 1::_::_ -> "2"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_list_complete
  String ::=
  {
    return case [1, 2] of
           | [] -> "nil"
           | 1::_::[] -> "1"
           | _::_ -> "any"
           end;
  }
}


--Maybe
warnCode "not exhaustive" {
  function fun_maybe_no_nothing
  String ::=
  {
    return case nothing() of
           | just(0) -> "just 0"
           | just(x) -> "just x"
           end;
  }
}

warnCode "not exhaustive" {
  function fun_maybe_no_just
  String ::=
  {
    return case nothing() of
           | nothing() -> "nothing"
           end;
  }
}

warnCode "not exhaustive" {
  function fun_maybe_no_general_just
  String ::=
  {
    return case nothing() of
           | nothing() -> "nothing"
           | just(0) -> "just 0"
           | just(-1) -> "just -1"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_maybe_complete_constructors
  String ::=
  {
    return case nothing() of
           | nothing() -> "nothing"
           | just(15) -> "just 15"
           | just(_) -> "other"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_maybe_complete_var
  String ::=
  {
    return case nothing() of
           | nothing() -> "nothing"
           | just(15) -> "just 15"
           | _ -> "other"
           end;
  }
}


--Closed Nonterminal
closed nonterminal Closed;

abstract production closed1
top::Closed ::=
{ }

abstract production closed2
top::Closed ::= c::Closed
{ }

warnCode "not exhaustive" {
  function fun_closed_no_default
  String ::=
  {
    return case closed1() of
           | closed1() -> "1"
           | closed2(closed1()) -> "2"
           | closed2(_) -> "3"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_closed_complete
  String ::=
  {
    return case closed1() of
           | closed1() -> "1"
           | closed2(closed1()) -> "2"
           | closed2(_) -> "3"
           | _ -> "other"
           end;
  }
}


--Forwarding Nonterminal
nonterminal Abstract;

abstract production abstract1
top::Abstract ::=
{ }

abstract production abstract2
top::Abstract ::= a::Abstract
{ }

abstract production abstract3
top::Abstract ::= a1::Abstract a2::Abstract
{ }

abstract production abstract4
top::Abstract ::= a::Abstract
{ forwards to abstract3(a, a); }

warnCode "not exhaustive" {
  function fun_nonterminal_missing_1
  String ::=
  {
    return case abstract1() of
           | abstract2(_) -> "1"
           | abstract3(_, abstract1()) -> "2"
           | abstract3(_, _) -> "3"
           end;
  }
}

warnCode "not exhaustive" {
  function fun_nonterminal_missing_2
  String ::=
  {
    return case abstract1() of
           | abstract1() -> "1"
           | abstract4(abstract1()) -> "2"
           | abstract3(_, _) -> "3"
           end;
  }
}

warnCode "not exhaustive" {
  function fun_nonterminal_missing_3
  String ::=
  {
    return case abstract1() of
           | abstract2(_) -> "1"
           | abstract1() -> "2"
           | abstract4(_) -> "3"
           end;
  }
}

warnCode "not exhaustive" {
  function fun_nonterminal_child_combination
  String ::=
  {
    return case abstract1() of
           | abstract1() -> "0"
           | abstract2(_) -> "1"
           | abstract3(_, abstract1()) -> "2"
           | abstract3(abstract1(), _) -> "3"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_nonterminal_complete_var
  String ::=
  {
    return case abstract1() of
           | abstract1() -> "0"
           | abstract2(_) -> "1"
           | _ -> "3"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_nonterminal_complete_constructors
  String ::=
  {
    return case abstract1() of
           | abstract1() -> "0"
           | abstract2(abstract1()) -> "1"
           | abstract2(abstract2(_)) -> "2"
           | abstract2(abstract3(_, _)) -> "3"
           | abstract3(_, _) -> "4"
           end;
  }
}


--When Clauses
warnCode "not exhaustive" {
  function fun_when_clauses
  String ::=
  {
    return case true of
           | true when 5 > 3 -> "1"
           | false -> "2"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_when_clauses_complete
  String ::=
  {
    return case true of
           | true when 5 > 3 -> "1"
           | false -> "2"
           | true -> "3"
           end;
  }
}


--Multiple Patterns
warnCode "not exhaustive" {
  function fun_multiple_basic
  String ::=
  {
    return case 1, 2, 3 of
           | 1, 2, 3 -> "1"
           | 1, _, 3 -> "2"
           | _, _, 4 -> "3"
           end;
  }
}

warnCode "not exhaustive" {
  function fun_multiple_combination
  String ::=
  {
    return case 1, 2, 3 of
           | 1, _, _ -> "1"
           | _, 2, _ -> "2"
           | _, _, 3 -> "3"
           end;
  }
}

noWarnCode "not exhaustive" {
  function fun_multiple_complete
  String ::=
  {
    return case 1, 2, 3 of
           | 1, _, _ -> "1"
           | _, 2, 3 -> "2"
           | _, _, _ -> "3"
           end;
  }
}

noWarnCode "not exhaustive" {
   function fun_test
   String ::=
   {
     return
       case just(1), just(2) of
       | nothing(), nothing() -> ""
       | just(0), nothing() -> ""
       | just(expected), just(actual) -> "Incorrect return type, expected "
       | nothing(), just(actual) -> "Unexpected return"
       | just(expected), nothing() -> "Expected return value, but found valueless return"
       end;
   }
}

