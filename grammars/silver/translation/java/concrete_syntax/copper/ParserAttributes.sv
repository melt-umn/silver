grammar silver:translation:java:concrete_syntax:copper;
import silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax;

import silver:translation:java:core;

import silver:analysis:typechecking:core;
import silver:analysis:typechecking;

concrete production attributeDclParser
top::AGDcl ::= 'parser' 'attribute' a::Name '::' te::Type 'action' acode::ActionCode_c ';'
{
  top.location = loc(top.file, $1.line, $1.column);
  top.pp = "parser attribute " ++ a.name ++ " :: " ++ te.pp ++ " ;" ;

  top.moduleNames = [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addParserAttrDcl(top.grammarName, a.location, fName, te.typerep, emptyDefs());

  top.errors <- if length(getValueDclAll(fName, top.env)) > 1
                then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
                else [];

  top.errors := te.errors ++ acode.errors;
  top.warnings := acode.warnings;
  
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];
  -- see ParserAttrSpec.sv for the parser spec

  acode.signature = namedNamedSignature(top.grammarName ++ ":" ++ a.name);
  acode.blockContext = actionContext();
  acode.env = newScopeEnv(acode.defs, top.env);

  -- No effect on ordinary semantic translation stuff
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";
}


abstract production parserAttributeReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp; 
  top.location = q.location;

  top.errors := if !top.blockContext.permitActions
                then [err(top.location, "References to parser attributes can only be made in action blocks")]
                else [];

  top.typerep = q.lookupValue.typerep;

  top.isAppReference = false;
  top.appReference = "";
  top.translation = makeCopperName(q.lookupValue.fullName);

  top.upSubst = top.downSubst;
}

abstract production parserAttributeValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  top.warnings := [];
  top.errors := e.errors ++
               (if !top.blockContext.permitActions
                then [err(val.location, "Assignment to parser attributes only permitted in parser action blocks")]
                else []);

  e.expected = expected_type(val.lookupValue.typerep);  

  top.setupInh := "";
  top.translation = makeCopperName(val.lookupValue.fullName) ++ " = " ++ e.translation ++ ";\n";

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;

  errCheck1 = check(e.typerep, val.lookupValue.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Value " ++ val.name ++ " has type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
       else [];
}

abstract production parserAttributeDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if !top.blockContext.permitActions
                then [err(q.location, "Parser attributes can only be used in action blocks")]
                else [err(q.location, "Parser action blocks are imperative, not declarative. You cannot modify the attributes of " ++ q.name ++ ". If you are trying to set inherited attributes, you should use 'decorate ... with { ... }' when you create it.")];
  top.typerep = q.lookupValue.typerep;
}

