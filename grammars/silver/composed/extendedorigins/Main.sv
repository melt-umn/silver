grammar silver:composed:extendedorigins;

import silver:host;
import silver:translation:java;
import silver:driver;

--import silver:extension:doc;
import silver:analysis:warnings:flow;
import silver:analysis:warnings:exporting;

parser svParse::Root {
  silver:host;

  silver:extension:convenience;
  silver:extension:list;
  silver:extension:easyterminal;
  silver:extension:deprecation;
  silver:extension:testing;
  silver:extension:auto_ast;
  silver:extension:templating;
  silver:extension:patternmatching;
  silver:extension:extendedorigins;
--  silver:extension:concreteSyntaxForTrees ;
  -- doc?

  silver:modification:let_fix;
  silver:modification:collection;
  silver:modification:primitivepattern;
  silver:modification:autocopyattr;
  silver:modification:ffi;
  silver:modification:typedecl;
  silver:modification:copper;
  silver:modification:defaultattr;
  
  -- slight hacks, for the moment
  silver:modification:copper_mda;
  silver:modification:impide;
}

function main 
IOVal<Integer> ::= args::[String] ioin::IO
{
  return cmdLineRun(args, svParse, ioin);
}
