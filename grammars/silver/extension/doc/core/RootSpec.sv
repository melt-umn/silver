grammar silver:extension:doc:core;

import silver:driver:util;

attribute genFiles occurs on RootSpec;

aspect production interfaceRootSpec
top::RootSpec ::= _ _
{
  top.genFiles := [];
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _
{
  top.genFiles := [];
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  _ _ _
{
  top.genFiles := [pair("index.md", implode("\n\n", g.docs))];
}
