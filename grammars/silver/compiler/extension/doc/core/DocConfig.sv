grammar silver:compiler:extension:doc:core;

nonterminal DocConfigSetting;
synthesized attribute fileScope::Boolean occurs on DocConfigSetting;

abstract production splitConfig
top::DocConfigSetting ::= v::Boolean
{
	top.fileScope = false;
}

abstract production weightConfig
top::DocConfigSetting ::= v::Integer
{
	top.fileScope = true;
}

abstract production grammarWeightConfig
top::DocConfigSetting ::= v::Integer
{
	top.fileScope = false;
}

abstract production grammarTitleConfig
top::DocConfigSetting ::= v::String
{
	top.fileScope = false;
}

abstract production titleConfig
top::DocConfigSetting ::= v::String
{
	top.fileScope = true;
}

abstract production collapseConfig
top::DocConfigSetting ::= v::Boolean
{
	top.fileScope = false;
}

abstract production grammarNoDocsConfig
top::DocConfigSetting ::= v::Boolean
{
	top.fileScope = false;
}

abstract production fileNoDocsConfig
top::DocConfigSetting ::= v::Boolean
{
	top.fileScope = true;
}


-- a grammar with @excludeGrammar containing file(s) with @excludeFile false will
-- only emit docs for that file(s)
function doesExcludeFile
Boolean ::= args::[DocConfigSetting]
{
	return case args of
		   | fileNoDocsConfig(v)::_ -> v
		   | grammarNoDocsConfig(true)::_ -> true
		   | _::r -> doesExcludeFile(r)
		   | [] -> false
		   end;
}

function getTitle
String ::= args::[DocConfigSetting] fallback::String
{
	return case args of
		   | titleConfig(x)::_ -> x
		   | grammarTitleConfig(x)::_ -> x
		   | _::r -> getTitle(r, fallback)
		   | [] -> fallback
		   end;
}

function getWeight
Integer ::= args::[DocConfigSetting]
{
	return case args of
		   | weightConfig(x)::_ -> x
		   | grammarWeightConfig(x)::_ -> x
		   | _::r -> getWeight(r)
		   | [] -> 0
		   end;
}