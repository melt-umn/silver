grammar hello;

fun main IO<Integer> ::= largs::[String] =
  do {
    print("Hello, world!\n");
    return 0;
  };