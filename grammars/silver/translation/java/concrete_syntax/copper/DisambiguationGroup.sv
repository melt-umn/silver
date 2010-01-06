grammar silver:translation:java:concrete_syntax:copper;
import silver:translation:java:core;

import silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;
import silver:analysis:typechecking:core;

terminal Disambiguation_kwd 'disambiguate' lexer classes {KEYWORD};

-- Separate 'TermPrecList' syntax if proper type-checking is made possible.
concrete production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermPrecList acode::ActionCode_c
{
  top.pp = "disambiguate " ++ terms.pp ++ " " ++ acode.actionCode;
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  top.defs = emptyDefs();

  top.errors := acode.errors ++ terms.errors;
  top.typeErrors = acode.typeErrors ++ terms.typeErrors;

--from definition:concrete_syntax
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];			   
  top.ruleDcls = [];

--from translation:core
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initAspect := "";
  top.postInit := "";

  acode.env = appendDefsEnv(appendDefs(acode.defs,terms.defs),top.env); -- terminal attrs?

  acode.signature = decorate namedSignatureDefault() with {}; -- TODO HACK
  acode.signatureEnv = toEnv(terms.defs);
  acode.localsEnv = toEnv(acode.defs);

  acode.actionCodeType = disambigGroupActionType();
}
