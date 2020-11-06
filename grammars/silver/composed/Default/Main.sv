grammar silver:composed:Default;

import silver:host;

parser svParse::Root {
  silver:host;
}

function main 
IOVal<Integer> ::= args::[String] ioin::IO
{
  return cmdLineRun(args, svParse, ioin);
}
