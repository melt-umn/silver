grammar silver:compiler:extension:doc:core;

{-
 - Represents a single setting (key = value) of a doc configuration option.
 - Some are file-scope, and some are grammar-scope (see @link[fileScope].)
 -}
nonterminal DocConfigSetting;

{-
 - Is this @link[DocConfigSetting] local to the file (e.g. @@title) or to the
 - grammar (e.g. @@grammarTitle)?
 -}
synthesized attribute fileScope::Boolean occurs on DocConfigSetting;

abstract production splitConfig
top::DocConfigSetting ::= v::Boolean
{
  top.fileScope = false;
}

abstract production fileSplitConfig
top::DocConfigSetting ::= v::Boolean
{
  top.fileScope = true;
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

function getFileTitle
String ::= args::[DocConfigSetting] fallback::String
{
  return case args of
       | titleConfig(x)::_ -> x
       | _::r -> getFileTitle(r, fallback)
       | [] -> fallback
       end;
}

function getGrammarTitle
String ::= args::[DocConfigSetting] fallback::String
{
  return case args of
       | grammarTitleConfig(x)::_ -> x
       | _::r -> getGrammarTitle(r, fallback)
       | [] -> fallback
       end;
}

function getFileWeight
Integer ::= args::[DocConfigSetting]
{
  return case args of
       | weightConfig(x)::_ -> x
       | _::r -> getFileWeight(r)
       | [] -> 0
       end;
}

function getGrammarWeight
Integer ::= args::[DocConfigSetting]
{
  return case args of
       | grammarWeightConfig(x)::_ -> x
       | _::r -> getGrammarWeight(r)
       | [] -> 0
       end;
}

function getSplit
Boolean ::= args::[DocConfigSetting]
{
  return case args of
       | fileSplitConfig(v)::_ -> v
       | splitConfig(true)::_ -> true
       | _::r -> getSplit(r)
       | [] -> false
       end;
}

function getToc
Boolean ::= args::[DocConfigSetting]
{
  return case args of
       | tocConfig(v)::_ -> v
       | _::r -> getToc(r)
       | [] -> false
       end;
}