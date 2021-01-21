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
  top.genFiles := if getSplit(g.upDocConfig) 
  				  then toSplitFiles(g, g.upDocConfig, [])
  				  else formatFile("_index.md", 
  				  	              getGrammarTitle(g.upDocConfig, lastPart(g.grammarName)),
  				  	              getGrammarWeight(g.upDocConfig), getCollapse(g.upDocConfig),
  				  	              length(g.upDocConfig) == 0, s"{{< toc-tree >}}\n\nIn grammar `${g.grammarName}`: {{< toc >}}",
  				  	              g.docs);

  g.docEnv = tm:add(g.docDcls, tm:empty());
  g.downDocConfig = g.upDocConfig;
  -- g.docEnv = tm:add(g.docDcls, tm:empty());
}

function toSplitFiles
[Pair<String String>] ::= g::Decorated Grammar grammarConf::[DocConfigSetting] soFar::[Pair<String String>]
{
	return case g of
		   | consGrammar(this, rest) ->
	   			toSplitFiles(rest, grammarConf, formatFile(
		   			substitute(".sv", ".md", this.location.filename),
		   			getFileTitle(this.localDocConfig, substitute(".sv", "", this.location.filename)),
		   			getFileWeight(this.localDocConfig), false, true,
		   			s"In file `${this.location.filename}`: "++(if getToc(this.localDocConfig) then "{{< toc >}}" else ""), 
		   			this.docs) ++ soFar)
		   | nilGrammar() -> if length(soFar) == 0 && length(grammarConf) == 0 then []
		   					 else formatFile("_index.md", getGrammarTitle(grammarConf, lastPart(g.grammarName)),
		   					 	getGrammarWeight(grammarConf), getCollapse(grammarConf),
		   					 	false, s"This Grammar is documented in individual files. Contents of `${g.grammarName}`: {{< toc-tree >}}", []) ++ soFar
		   end;
}

function formatFile
[Pair<String String>] ::= fileName::String title::String weight::Integer
                          collapse::Boolean skipIfEmpty::Boolean pfxText::String
                          comments::[CommentItem]
{
	local realDocs::[CommentItem] = filter((.doEmit), comments);
	return if length(realDocs) == 0 && skipIfEmpty then [] else [pair(fileName, s"""---
title: ${title}
weight: ${toString(weight)}
geekdocCollapseSection: ${toString(collapse)}
---

${pfxText}

${implode("\n\n\n\n", map((.body), realDocs))}
""")];
}

function lastPart
String ::= s::String
{
	return last(explode(":", s));
}