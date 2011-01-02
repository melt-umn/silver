grammar silver:definition:core:env_parser;

import silver:definition:env;
import silver:definition:env:parser;

import silver:definition:core;

aspect production parserRootSpec
top::RootSpec ::= p::IRootSpecParts _
{
  top.interface= true;
}

