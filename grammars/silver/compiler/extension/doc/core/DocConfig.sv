
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

abstract production tocConfig
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