grammar silver:extension:doc:extra;

{- This grammar is just a bunch of imports. It is run by the generate-documentation script, and just builds documentation. The jar it produces is just thrown away. -}

import core;

--import ide;

import lib:errors;
import lib:extcore;
import lib:system;
import lib:xml;

import silver:analysis;
import silver:analysis:typechecking:core;
import silver:analysis:warnings;
import silver:analysis:warnings:flow;
import silver:analysis:warnings:exporting;

import silver:composed;
import silver:composed:Default;
--import silver:composed:extendedorigins;
--import silver:composed:idetest;

import silver:definition;
import silver:definition:concrete_syntax;
import silver:definition:concrete_syntax:ast;
import silver:definition:core;
import silver:definition:env;
import silver:definition:flow:ast;
import silver:definition:flow:driver;
import silver:definition:flow:env;
import silver:definition:regex;
import silver:definition:type;
import silver:definition:type:syntax;

import silver:driver;
import silver:driver:util;

import silver:extension;
import silver:extension:auto_ast;
import silver:extension:convenience;
import silver:extension:deprecation;
import silver:extension:doc;
import silver:extension:doc:core;
import silver:extension:doc:driver;
import silver:extension:easyterminal;
--import silver:extension:extendedorigins;
import silver:extension:functorattrib;
import silver:extension:list;
import silver:extension:list:java;
import silver:extension:patternmatching;
import silver:extension:templating;
import silver:extension:templating:syntax;
import silver:extension:testing;
import silver:extension:treegen;

import silver:host;

import silver:langutil;
import silver:langutil:pp;

import silver:modification;
import silver:modification:autocopyattr;
import silver:modification:autocopyattr:convenience;
import silver:modification:autocopyattr:java;
import silver:modification:collection;
import silver:modification:collection:java;
import silver:modification:copper;
import silver:modification:copper_mda;
import silver:modification:defaultattr;
import silver:modification:ffi;
import silver:modification:ffi:java;
import silver:modification:ffi:util;
import silver:modification:impide;
import silver:modification:impide:cstast;
import silver:modification:impide:spec;
import silver:modification:lambda_fn;
import silver:modification:lambda_fn:java;
import silver:modification:let_fix;
import silver:modification:let_fix:java;
import silver:modification:primitivepattern;
import silver:modification:typedecl;

import silver:support:monto;

import silver:testing;
--import silver:testing:bin; --Do we want this?

import silver:translation:java;
import silver:translation:java:core;
import silver:translation:java:driver;
import silver:translation:java:type;

import silver:util;
import silver:util:cmdargs;
import silver:util:command;
import silver:util:deque;
import silver:util:fixedmap;
import silver:util:treemap;
import silver:util:raw:graph;
import silver:util:raw:treemap;
import silver:util:raw:treeset;

{@comment Dummy main function that does nothing @link[dummyFunction] @}
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

