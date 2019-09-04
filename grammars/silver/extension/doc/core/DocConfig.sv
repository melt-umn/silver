grammar silver:extension:doc:core;

synthesized attribute header :: String;
synthesized attribute splitFiles :: String;
synthesized attribute noDoc :: Boolean;
synthesized attribute warnings :: String;
nonterminal DocConfigs with header, splitFiles, noDoc, warnings;
nonterminal DocConfig with header, splitFiles, noDoc;

concrete production config
top::AGDcl ::= '{@config' items::DocConfigs '@}'
{
  top.docs := [];
  top.docsHeader = items.header;
  top.docsSplit = items.splitFiles;
  top.docsNoDoc = items.noDoc;
  forwards to emptyAGDcl(location=top.location);
}

concrete production consConfigs
top::DocConfigs ::= c::DocConfig rest::DocConfigs
{
  local headerWarnings :: String = if c.header != "" && rest.header != ""
								   then "Multiple header definitions in documentation configuration."
								   else "";

  local splitFilesWarnings :: String = if c.header != "" && rest.header != ""
								   then "Multpile split-files definitions in documentation configuration."
								   else "";

  top.header = case pair(c.header, rest.header) of
				| pair("", h) -> h
				| pair(h, _) -> h
			   end;

  top.splitFiles = case pair(c.splitFiles, rest.splitFiles) of
					| pair("", s) -> s
					| pair(s, _) -> s
				   end;

  top.noDoc = c.noDoc || rest.noDoc;
  top.warnings = headerWarnings ++ splitFilesWarnings ++ rest.warnings;
}

concrete production nilConfigs
top::DocConfigs ::=
{
  top.header = "";
  top.splitFiles = "";
  top.noDoc = false;
}

concrete production headerConfig
top::DocConfig ::= 'header' ':' value::ConfigValue_t
{
  top.header = cleanDocValue(value.lexeme);
  top.splitFiles = "";
  top.noDoc = false;
}

concrete production splitFilesConfig
top::DocConfig ::= 'split-files' ':' value::ConfigValue_t
{
  top.header = "";
  top.splitFiles = cleanDocValue(value.lexeme);
  top.noDoc = false;
}

concrete production noDocConfig
top::DocConfig ::= 'no-doc' ':' value::ConfigValue_t
{
  top.header = "";
  top.splitFiles = "";
  top.noDoc = if "true" == value.lexeme
			  then true
			  else false;
}

function cleanDocValue
String ::= s::String
{
  return substitute("\\n", "\n", substitute("\"", "", substitute("\\t", "\t", s)));
}


