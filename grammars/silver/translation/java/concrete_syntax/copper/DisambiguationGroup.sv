grammar silver:translation:java:concrete_syntax:copper;
import silver:translation:java:core;

import silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;
import silver:analysis:typechecking:core;
import silver:definition:type;
import silver:definition:type:syntax;

terminal Disambiguation_kwd 'disambiguate' lexer classes {KEYWORD};

-- Separate 'TermPrecList' syntax if proper type-checking is made possible.
concrete production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermPrecList acode::ActionCode_c
{
  top.pp = "disambiguate " ++ terms.pp ++ " " ++ acode.actionCode;
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  top.defs = emptyDefs();

  top.warnings := acode.warnings;
  top.errors := acode.errors ++ terms.errors;

--from definition:concrete_syntax
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];

--from translation:core
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";

  acode.env = newScopeEnv(appendDefs(acode.defs,terms.defs), top.env); -- terminal attrs?

  -- Give the group a name, deterministically, based on line number
  acode.signature = namedNamedSignature(top.grammarName ++ ":__disam" ++ toString($1.line));

  acode.blockContext = disambiguationContext();
}

abstract production pluckTerminalReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp; 
  top.location = q.location;

  top.errors := []; -- Should only be referenceable from a context where its valid.

  top.typerep = errorType(); -- TODO: BUG: Need a real type here (AnyTerminalType or something)
  
  top.isAppReference = false;
  top.appReference = "";
  
  top.translation = makeCopperName(q.lookupValue.fullName); -- Value right here?
  
  top.upSubst = top.downSubst;
}

