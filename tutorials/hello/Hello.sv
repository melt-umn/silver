grammar hello;

fun main IO<Integer> ::= largs::[String] =
  do {
    print("Hello, World!\n");
    return 0;
  };
