grammar silver:composed:extendedorigins;

import silver:host;

parser svParse::Root {
  silver:host;
  silver:extension:extendedorigins;
}

function main 
IOVal<Integer> ::= args::[String] ioin::IO
{
  return cmdLineRun(args, svParse, ioin);
}
