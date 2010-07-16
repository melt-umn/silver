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

  top.moduleNames = [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addParserAttrDcl(top.grammarName, a.location, fName, te.typerep, emptyDefs());

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclAll(fName, top.env)) > 1
       then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
       else [];

  top.errors := er2 ++ te.errors ++ acode.errors;
  top.typeErrors = acode.typeErrors;
  
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];
  -- see ParserAttrSpec.sv for the parser spec

  acode.signature = namedNamedSignature(top.grammarName ++ ":" ++ a.name);
  acode.actionCodeType = parserAttrActionType();
  acode.env = newScopeEnv(acode.defs, top.env);

  -- No effect on ordinary semantic translation stuff
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initAspect := "";
  top.postInit := "";
}


abstract production parserAttributeReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp; 
  top.location = q.location;

  top.errors := if top.actionCodeType.isSemanticBlock
                then [err(top.location, "References to parser attributes can only be made in action blocks")]
                else [];
  top.warnings := [];

  top.typerep = q.lookupValue.typerep;

  top.isAppReference = false;
  top.appReference = "";
  top.translation = makeCopperName(q.lookupValue.fullName);

  top.typeErrors = [];
}

abstract production parserAttributeValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e.errors ++
               (if top.actionCodeType.isSemanticBlock
                then [err(val.location, "Assignment to parser attributes only permitted in parser action blocks")]
                else []);
  top.warnings := [];

  e.expected = expected_type(val.lookupValue.typerep);  

  top.setupInh := "";
  top.translation = makeCopperName(val.lookupValue.fullName) ++ " = " ++ e.translation ++ ";\n";
}

abstract production parserAttributeDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if top.actionCodeType.isSemanticBlock
                then [err(q.location, "Parser attributes can only be used in action blocks")]
                else [err(q.location, "Parser action blocks are imperative, not declarative. You cannot modify the attributes of " ++ q.name ++ ". If you are trying to set inherited attributes, you should use 'decorate ... with { ... }' when you create it.")];
  top.typerep = q.lookupValue.typerep;
}

