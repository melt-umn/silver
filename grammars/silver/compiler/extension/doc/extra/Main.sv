grammar silver:compiler:extension:doc:extra;

{- This grammar is just a bunch of imports. It is run by the generate-documentation script, and just builds documentation. The jar it produces is just thrown away. -}


--import ide;

import silver:xml;

import silver:compiler:analysis;
import silver:compiler:analysis:typechecking:core;
import silver:compiler:analysis:warnings;
import silver:compiler:analysis:warnings:flow;
import silver:compiler:analysis:warnings:exporting;

import silver:compiler:composed;
import silver:compiler:composed:Default;
--import silver:compiler:composed:extendedorigins;
--import silver:compiler:composed:idetest;

import silver:compiler:definition;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:concrete_syntax:ast;
import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:flow:ast;
import silver:compiler:definition:flow:driver;
import silver:compiler:definition:flow:env;
import silver:compiler:definition:regex;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;

import silver:compiler:driver;
import silver:compiler:driver:util;

import silver:compiler:extension;
-- Individual extensions built by silver:compiler:composed:Default

import silver:compiler:host;

import silver:langutil;
import silver:langutil:pp;

import silver:compiler:modification;
-- Individual modifications built by silver:compiler:composed:Default

import silver:testing;
--import silver:testing:bin; --Do we want this?

import silver:compiler:translation:java;
import silver:compiler:translation:java:core;
import silver:compiler:translation:java:driver;
import silver:compiler:translation:java:type;

import silver:util:cmdargs;
import silver:util:deque;
import silver:util:treemap;
import silver:util:graph;
import silver:util:treemap;
import silver:util:treeset;

@{- Dummy main function that does nothing @link[dummyFunction] -}
function main
IOVal<Integer> ::= args::[String] ioIn::IO
{
  return ioval(ioIn, 0);
}

function dummyFunction
Integer ::=
{
  return 1;
}

