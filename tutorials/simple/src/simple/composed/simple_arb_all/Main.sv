grammar simple:composed:simple_arb_all;

imports simple:host;
imports simple:arb:driver;

generator generate :: simple:concretesyntax:Root {
  simple:host;
  simple:extensions:do_while;
  simple:extensions:repeat_until;
  simple:extensions:implication;
}

function main 
IOVal<Integer> ::= args::[String] io_in::IOToken
{
  return ioval(arbDriver(args, io_in, generate), 0);
}
