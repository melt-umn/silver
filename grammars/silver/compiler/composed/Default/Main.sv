grammar silver:compiler:composed:Default;

import silver:compiler:host;

parser svParse::Root {
  silver:compiler:host;
}

-- TODO: Change to a concise function
function main 
IOVal<Integer> ::= args::[String] ioin::IOToken
{
  return evalIO(cmdLineRun(args, svParse), ioin);
}
