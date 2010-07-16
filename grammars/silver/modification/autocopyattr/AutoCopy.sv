grammar silver:modification:autocopyattr;

import silver:util;
import silver:definition:core;
import silver:definition:env;

terminal AutoCopy_kwd 'autocopy' lexer classes {KEYWORD};

concrete production autocopyAttributeDcl
top::AGDcl ::= 'autocopy' 'attribute' a::Name '::' t::Type ';'
{
  top.pp = "autocopy attribute " ++ a.name ++ " :: " ++ t.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addAutocopyDcl(top.grammarName, a.location, fName, t.typerep, emptyDefs());

  forwards to attributeDclInh(terminal(Inherited_kwd, "inherited", $1), $2, a, $4, t, $6);
}

