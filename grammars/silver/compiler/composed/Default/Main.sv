grammar silver:compiler:composed:Default;

import silver:compiler:host;

parser svParse::Root {
  silver:compiler:host;
}

function main 
IOVal<Integer> ::= args::[String] ioin::IOToken
{
  return cmdLineRun(args, svParse, ioin);
}
