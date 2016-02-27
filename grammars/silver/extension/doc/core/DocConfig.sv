grammar silver:extension:doc:core;

synthesized attribute header :: String;
synthesized attribute splitFiles :: String;
synthesized attribute configPairs :: [Pair<String String>];
nonterminal DocConfigs with configPairs;
nonterminal DocConfig with configPairs;

nonterminal ConfigItem with header, splitFiles;

abstract production configItem
top::ConfigItem ::= header::String splitFiles::String
{
  top.header = header;
  top.splitFiles = splitFiles;
}

concrete production config
top::AGDcls ::= '{#' items::DocConfigs '#}'
{
  top.docs := [configDocItem(configItem(firstValue("header", items.configPairs), firstValue("split-files", items.configPairs)))];

  forwards to nilAGDcls(location=top.location);
}

concrete production consConfigItems
top::DocConfigs ::= c::DocConfig rest::DocConfigs
{
  top.configPairs = c.configPairs ++ rest.configPairs;
}

concrete production nilConfigItems
top::DocConfigs ::=
{
  top.configPairs = [];
}

concrete production headerConfigItem
top::DocConfig ::= 'header' ':' value::ConfigValue_t
{
  top.configPairs = [pair("header", cleanDocValue(value.lexeme))];
}

concrete production splitFilesConfigItem
top::DocConfig ::= 'split-files' ':' value::ConfigValue_t
{
  top.configPairs = [pair("split-files", cleanDocValue(value.lexeme))];
}

function cleanDocValue
String ::= s::String
{
  return replace("\\n", "\n", replace("\"", "", replace("\\t", "\t", s)));
}

function replace
String ::= needle::String rep::String haystack::String
{
  return implode(rep, explode(needle, haystack));
}

function firstValue
String ::= key::String pairs::[Pair<String String>]
{
  return case pairs of
	 | [] -> ""
	 | pair(k, v) :: rest -> if key == k then v else firstValue(key, rest)
	 end;
}

