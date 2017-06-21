grammar testfile;

imports silver:testing;
imports lib:extcore;
imports silver:langutil;
imports silver:langutil:pp;
imports core:monad;

function main
IOVal<Integer> ::= args::[String] ioIn::IO
{
  return bitNot(bitAnd(system("silver --clean rhs", ioIn),
    bitAnd(system("silver --clean disamb", ioIn),
    bitAnd(system("silver --clean startMissing", ioIn),
    bitAnd(system("silver --clean prodMod", ioIn),
    bitAnd(system("silver --clean mda", ioIn),
    bitAnd(system("silver --clean missingLHS", ioIn),
    system("silver --clean terminalLHS", ioIn))))))));
}

function bitAnd
IOVal<Integer> ::= a::IOVal<Integer> b::IOVal<Integer>
{
  return if a.iovalue == 0 then a else b;
}

function bitNot
IOVal<Integer> ::= a::IOVal<Integer>
{
  return if a.iovalue == 0 
    then ioval(a.io, 1)
    else ioval(a.io, 0);
}