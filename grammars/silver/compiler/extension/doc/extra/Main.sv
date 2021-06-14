grammar silver:compiler:extension:doc:extra;

import silver:xml;

import silver:compiler:analysis:typechecking:core;
import silver:compiler:analysis:warnings;
import silver:compiler:analysis:warnings:flow;
import silver:compiler:analysis:warnings:exporting;

import silver:compiler:composed:Default;

import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:concrete_syntax:ast;
import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:flow:ast;
import silver:compiler:definition:flow:driver;
import silver:compiler:definition:flow:env;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;

import silver:compiler:driver;
import silver:compiler:driver:util;

-- Individual extensions built by silver:compiler:composed:Default

import silver:compiler:host;

import silver:langutil;
import silver:langutil:pp;

-- Individual modifications built by silver:compiler:composed:Default

import silver:testing;

import silver:compiler:translation:java;
import silver:compiler:translation:java:core;
import silver:compiler:translation:java:driver;
import silver:compiler:translation:java:type;

import silver:regex;

import silver:util:cmdargs;
import silver:util:deque as dq;
import silver:util:treemap;
import silver:util:graph;
import silver:util:treemap;
import silver:util:treeset;

import silver:xml;


@@{- This grammar is just a bunch of imports. It is run by the generate-documentation script, and just builds documentation. The jar it produces is just thrown away. -}

@@{- ## Example top-level doc comment -}

@{- Dummy main function that does nothing. Example link: @link[dummyFunction] -}
function main
IOVal<Integer> ::= args::[String] ioIn::IO
{
  return ioval(ioIn, 0);
}

@{- Also a dummy function that does nothing -}
function dummyFunction
Integer ::=
{
  return 1;
}
