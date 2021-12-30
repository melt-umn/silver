grammar simple:arb;

imports simple:host;
imports simple:arb:driver;

generator generate :: simple:concretesyntax:Root {
  simple:host;
}

function main 
IOVal<Integer> ::= args::[String] io_in::IO
{
  return ioval(arbDriver(args, io_in, generate), 0);
}
