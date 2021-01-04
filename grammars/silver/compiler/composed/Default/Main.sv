grammar silver:compiler:composed:Default;

import silver:compiler:host;

parser svParse::Root {
  silver:compiler:host;
}

function main 
IOVal<Integer> ::= args::[String] ioin::IO
{
  return cmdLineRun(args, svParse, ioin);
}
