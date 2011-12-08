grammar silver:definition:core:env_parser;

import silver:definition:env;
import silver:definition:env:env_parser;

import silver:definition:core;

aspect production parserRootSpec
top::RootSpec ::= p::IRootSpecParts
{
  top.interface= true;
}

