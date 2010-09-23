grammar silver:composed:minimal;

import silver:host;
import silver:host:env;
import silver:translation:java;
import silver:driver;
import silver:util:command;

parser rParse::Root {
  silver:host;

  silver:modification:ffi;

  silver:translation:java:concrete_syntax:copper;
}

parser cParse::Command {
  silver:util:command;
}

parser iParse::aRootSpec {
  silver:host:env;

  silver:translation:java:concrete_syntax:copper:env_parser;
}

function main 
IO ::= args::String i::IO {
  -- please note that run in BuildProcess.sv will call exit(), so we may not "get back here"
  return (decorate run(i, args) with {rParser = rParse; cParser = cParse; iParser = iParse;}).io;
}
