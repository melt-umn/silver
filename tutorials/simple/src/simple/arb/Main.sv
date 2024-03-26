grammar simple:arb;

imports simple:host;
imports simple:arb:driver;

generator generate :: simple:concretesyntax:Root {
  simple:host;
}

fun main
IOVal<Integer> ::= args::[String] io_in::IOToken =
  ioval(arbDriver(args, io_in, generate), 0);
