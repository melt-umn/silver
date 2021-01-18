
nonterminal DocConfiguration;
synthesized attribute splitByFile::Boolean occurs on DocConfiguration;
synthesized attribute weight::Integer occurs on DocConfiguration;
synthesized attribute title::Maybe<String> occurs on DocConfiguration;
synthesized attribute collapseChildren::Boolean occurs on DocConfiguration;
synthesized attribute noDocs::Boolean occurs on DocConfiguration;

abstract production nilConfig
top::DocConfiguration ::=
{
	top.splitByFile = false;
	top.weight = 0;
	top.title = nothing();
	top.collapseChildren = true;
	top.noDocs = false;
}

abstract production splitConfig
top::DocConfiguration ::= v::Boolean r::DocConfiguration
{
	top.splitByFile = v;
	top.weight = r.weight;
	top.title = r.title;
	top.collapseChildren = r.collapseChildren;
	top.noDocs = r.noDocs;
}

abstract production weightConfig
top::DocConfiguration ::= v::Integer r::DocConfiguration
{
	top.splitByFile = r.splitByFile;
	top.weight = v;
	top.title = r.title;
	top.collapseChildren = r.collapseChildren;
	top.noDocs = r.noDocs;
}

abstract production titleConfig
top::DocConfiguration ::= v::String r::DocConfiguration
{
	top.splitByFile = r.splitByFile;
	top.weight = r.weight;
	top.title = just(v);
	top.collapseChildren = r.collapseChildren;
	top.noDocs = r.noDocs;
}

abstract production collapseConfig
top::DocConfiguration ::= v::Boolean r::DocConfiguration
{
	top.splitByFile = r.splitByFile;
	top.weight = r.weight;
	top.title = r.title;
	top.collapseChildren = v;
	top.noDocs = r.noDocs;
}

abstract production noDocsConfig
top::DocConfiguration ::= v::Boolean r::DocConfiguration
{
	top.splitByFile = r.splitByFile;
	top.weight = r.weight;
	top.title = r.title;
	top.collapseChildren = r.collapseChildren;
	top.noDocs = v;
}

-- abstract production newGrammarConfig
-- top::DocConfiguration ::= r::DocConfiguration
-- {
-- 	top.splitByFile = nilConfig().splitByFile;
-- 	top.weight = nilConfig().weight;
-- 	top.title = nilConfig().title;
-- 	top.collapseChildren = nilConfig().collapseChildren;
-- 	top.noDocs = r.noDocs;
-- }

abstract production newFileConfig
top::DocConfiguration ::= r::DocConfiguration
{
	top.splitByFile = r.splitByFile;
	top.weight = nilConfig().weight;
	top.title = nilConfig().title;
	top.collapseChildren = r.collapseChildren;
	top.noDocs = r.noDocs;
}