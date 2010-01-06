grammar silver:translation:java:concrete_syntax:copper;
import silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;

import silver:translation:java:core;

import silver:analysis:typechecking:core;

--terminal Parser_kwd 'parser' lexer precedence = 5;

concrete production attributeDclParser
top::AGDcl ::= 'parser' 'attribute' a::Name '::' te::Type 'action' acode::ActionCode_c ';'
{
  top.location = loc(top.file, $1.line, $1.column);
  top.pp = "parser attribute " ++ a.name ++ " :: " ++ te.pp ++ " ;" ;

  top.parserDcls = [];
  top.moduleNames = [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addValueDcl(fName, te.typerep,
             addParserAttrDcl(fName, 
	     addFullNameDcl(a.name, fName,  emptyDefs())));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
       then [err(top.location, "Name '" ++ a.name ++ "' is already bound.")]
       else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getAttributeDclOne(fName, top.env)) > 1
       then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
       else [];	

  top.errors := er1 ++ er2 ++ te.errors ++ acode.errors;
  top.typeErrors = acode.typeErrors; -- Finalize
  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];

  acode.actionCodeType = parserAttrActionType();
  acode.signature = decorate namedSignatureDefault() with {}; -- TODO HACK
  acode.signatureEnv = emptyEnv();
  acode.localsEnv = toEnv(acode.defs);


  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initAspect := "";
  top.postInit := "";
}

----------------------
-- environment

synthesized attribute isParserAttrDeclaration :: Boolean occurs on EnvItem;

function addParserAttrDcl
Decorated Defs ::= n::String e::Decorated Defs
{
  return consDefs(parserAttrEnvItem(n), e);
}

function parserAttrEnvItem
Decorated EnvItem ::= n::String
{
  return decorate i_parserAttrEnvItem(n) with {};
}

abstract production i_parserAttrEnvItem
top::EnvItem ::= n::String
{
  top.itemName = n;
  top.unparse = "parserAttr('" ++ n ++ "')";
  top.isParserAttrDeclaration = true;

  forwards to i_defaultEnvItem();
}

aspect production i_defaultEnvItem
top::EnvItem ::= 
{
  top.isParserAttrDeclaration = false;
}

function isParserAttribute
Boolean ::= search::String e::Decorated Env
{
  return isParserAttributeHelp(search, getDcls(e.restTree));
}

function isParserAttributeHelp
Boolean ::= search::String e::[Decorated EnvItem]
{
  local attribute recurse :: Boolean;
  recurse = isParserAttributeHelp(search, tail(e));

  return if null(e) then false
         else if (head(e).isParserAttrDeclaration && head(e).itemName == search)
	      then true
	      else recurse;
}
