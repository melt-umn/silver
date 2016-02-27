grammar silver:extension:doc:core;

synthesized attribute header :: String;
synthesized attribute splitFiles :: String;
nonterminal DocConfigs with header, splitFiles;
nonterminal DocConfig with header, splitFiles;
nonterminal ConfigItem with header, splitFiles;

abstract production configItem
top::ConfigItem ::= c::DocConfigs
{
  top.header = c.header;
  top.splitFiles = c.splitFiles;
}

concrete production config
top::AGDcls ::= '{#' items::DocConfigs '#}'
{
  top.docs := [configDocItem(configItem(items))];
  forwards to nilAGDcls(location=top.location);
}

concrete production consConfigItems
top::DocConfigs ::= c::DocConfig rest::DocConfigs
{
  top.header = if "" != c.header then c.header else rest.header;
  top.splitFiles = if "" != c.splitFiles then c.splitFiles else rest.splitFiles;
}

concrete production nilConfigItems
top::DocConfigs ::=
{
  top.header = "";
  top.splitFiles = "";
}

concrete production headerConfigItem
top::DocConfig ::= 'header' ':' value::ConfigValue_t
{
  top.header = cleanDocValue(value.lexeme);
  top.splitFiles = "";
}

concrete production splitFilesConfigItem
top::DocConfig ::= 'split-files' ':' value::ConfigValue_t
{
  top.header = "";
  top.splitFiles = cleanDocValue(value.lexeme);
}

function cleanDocValue
String ::= s::String
{
  return replace("\\n", "\n", replace("\"", "", replace("\\t", "\t", s)));
}

-- TODO: Find a more efficient way to do string replacement
function replace
String ::= needle::String rep::String haystack::String
{
  return implode(rep, explode(needle, haystack));
}

