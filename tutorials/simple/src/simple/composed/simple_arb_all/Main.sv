grammar simple:composed:simple_arb_all;

imports simple:host;
imports simple:arb:driver;

generator generate :: simple:abstractsyntax:Root {
  simple:host;
  simple:extensions:do_while;
  simple:extensions:repeat_until;
  simple:extensions:implication;
}

function main 
IOVal<Integer> ::= args::[String] io_in::IO
{
  return ioval(arbDriver(args, io_in, generate), 0);
}
