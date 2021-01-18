grammar silver:compiler:extension:doc:core;

import silver:compiler:driver:util;

attribute genFiles occurs on RootSpec;

aspect production interfaceRootSpec
top::RootSpec ::= _ _ _
{
  top.genFiles := [];
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _ _
{
  top.genFiles := [];
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  _ _ _ _
{
  top.genFiles := toOneFile(g.grammarName, g.upDocConfig, g.docs);

  g.docEnv = add(g.docDcls, empty(compareString));
  g.downDocConfig = g.upDocConfig;
}

function toOneFile
[Pair<String String>] ::= grammarName::String grammarConfig::[DocConfigSetting] comments::[CommentItem]
{
	return if null(comments) then [] else
		[pair("index.md", s"""---
title: ${getTitle(grammarConfig, grammarName)}
weight: ${toString(getWeight(grammarConfig))}
geekdocCollapseSection: true
---

{{< toc >}}

""" ++
			implode("\n\n\n\n", map((.body), filter((.doEmit), comments))))];
}