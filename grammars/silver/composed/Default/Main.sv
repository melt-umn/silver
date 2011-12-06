grammar silver:composed:Default;

import silver:host;
import silver:host:env;
import silver:translation:java;
import silver:driver;

--import silver:extension:doc;
--import silver:analysis:warnings;

parser rParse::Root {
  silver:host;

  silver:extension:convenience;

  silver:extension:list;
  silver:extension:easyterminal;

  silver:extension:deprecation;
  silver:extension:testing;
--  silver:extension:concreteSyntaxForTrees ;
  -- doc?

  silver:modification:let_fix;
  silver:modification:collection;
  silver:modification:patternmatching;
  silver:modification:autocopyattr;
  silver:modification:autocopyattr:convenience; -- TODO: we need a condition export, perhaps? Why didn't we include this?
  silver:modification:ffi;
  silver:modification:typedecl;
  silver:modification:copper;
}

parser iParse::IRootSpec {
  silver:host:env;

  silver:modification:collection:env_parser;
  silver:modification:autocopyattr:env_parser;
  silver:modification:ffi:env_parser;
  silver:modification:typedecl:env_parser;
  silver:modification:copper:env_parser;
  
  silver:extension:list:env_parser;
}

function main 
IOVal<Integer> ::= args::[String] i::IO
{
  -- please note that run in BuildProcess.sv will call exit(), so we may not "get back here"
  return ioval((decorate run(i, args) with {rParser = rParse; iParser = iParse;}).io,
               0);
}
