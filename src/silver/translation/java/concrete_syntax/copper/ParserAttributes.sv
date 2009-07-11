grammar silver:translation:java:concrete_syntax:copper;
import silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;

--terminal Parser_kwd 'parser' lexer precedence = 5;

attribute actionCode occurs on RHSSpec;

function rhsSpecAction
Decorated RHSSpec ::= gn::String fn::String ns::[String] pm::[Decorated ProductionModifierSpec] acode::String
{
  return decorate i_rhsSpecAction(gn, fn, ns, pm, acode) with {};
}

abstract production i_rhsSpecAction
top::RHSSpec ::= gn::String fn::String ns::[String] pm::[Decorated ProductionModifierSpec] acode::String
{
   top.actionCode = acode;
   forwards to i_rhsSpec(gn, fn,ns,pm);
}

aspect production i_rhsSpec
top::RHSSpec ::= gn::String fn::String ns::[String] pm::[Decorated ProductionModifierSpec]
{
   top.actionCode = "";
}

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

  local attribute er3 :: [Decorated Message];
  er3 = if !te.typerep.isParserAttrType
        then [err(top.location, "Type '" ++ te.typerep.typeName ++ "' not valid for a parser attribute.")]
        else [];

  top.errors := er1 ++ er2 ++ er3 ++ te.errors;

  acode.actionCodeType = parserAttrActionType();


  -- FIXME
  acode.env = appendDefsEnv(addFullNameDcl(a.name,fName,
                             addValueDcl(fName,te.typerep,
                              addThisDcl(fName,acode.defs))),top.env);
}
