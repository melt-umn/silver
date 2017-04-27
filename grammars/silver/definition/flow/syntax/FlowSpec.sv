grammar silver:definition:flow:syntax;

imports silver:definition:core;
imports silver:definition:flow:ast;
imports silver:definition:env;
imports silver:definition:type;

terminal Flowtype 'flowtype' lexer classes {KEYWORD};

concrete production flowtypeDcl
top::AGDcl ::= 'flowtype' nt::QName '=' specs::FlowSpecs ';'
{
  top.pp = "flowtype " ++ nt.pp ++ " = " ++ specs.pp ++ ";";
  top.errors :=
    if null(nt.lookupType.errors)
    then specs.errors
    else nt.lookupType.errors;
  top.flowDefs =
    if null(nt.lookupType.errors)
    then specs.flowDefs
    else [];

  specs.onNt = nt.lookupType.typerep;
}

concrete production flowtypeAttrDcl
top::AGDcl ::= 'flowtype' attr::FlowSpec 'on' nts::NtList ';'
{
  top.pp = "";
  top.errors := [];
  top.flowDefs = [];
}


nonterminal FlowSpecs with config, location, grammarName, errors, env, pp, onNt, flowDefs;

concrete production oneFlowSpec
top::FlowSpecs ::= h::FlowSpec
{
  top.pp = h.pp;
  top.errors := h.errors;
  top.flowDefs = h.flowDefs;
}
concrete production snocFlowSpec
top::FlowSpecs ::= h::FlowSpecs  ','  t::FlowSpec
{
  top.pp = h.pp ++ ", " ++ t.pp;
  top.errors := h.errors ++ t.errors;
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

nonterminal FlowSpec with config, location, grammarName, errors, env, pp, onNt, flowDefs;

concrete production flowSpecDcl
top::FlowSpec ::= attr::FlowSpecId  '{' inhs::FlowSpecInhs '}'
{
  top.pp = attr.pp ++ " {" ++ inhs.pp ++ "}";
  top.errors := attr.errors ++ inhs.errors;
  
  -- TODO: check for pre-existing flowtype def
  
  top.flowDefs = [specificationFlowDef(top.onNt.typeName, attr.synName, inhs.inhList)];
}

nonterminal FlowSpecId with config, location, grammarName, errors, env, pp, onNt, synName;

synthesized attribute synName :: String;

concrete production qnameSpecId
top::FlowSpecId ::= syn::QNameAttrOccur
{
  top.pp = syn.pp;
  top.errors := syn.errors;
  top.synName = syn.dcl.fullName;
  
  syn.attrFor = top.onNt;
}

concrete production forwardSpecId
top::FlowSpecId ::= 'forward'
{
  top.pp = "forward";
  top.errors := [];
  top.synName = "forward";
}

concrete production decorateSpecId
top::FlowSpecId ::= 'decorate'
{
  top.pp = "decorate";
  top.errors := [];
  top.synName = "decorate";
}


nonterminal FlowSpecInhs with config, location, grammarName, errors, env, pp, onNt, inhList;

concrete production nilFlowSpecInhs
top::FlowSpecInhs ::=
{
  top.pp = "";
  top.errors := [];
  top.inhList = [];
}
concrete production oneFlowSpecInhs
top::FlowSpecInhs ::= h::FlowSpecInh
{
  top.pp = h.pp;
  top.errors := h.errors;
  top.inhList = h.inhList;
}
concrete production consFlowSpecInhs
top::FlowSpecInhs ::= h::FlowSpecInh  ','  t::FlowSpecInhs
{
  top.pp = h.pp ++ ", " ++ t.pp;
  top.errors := h.errors ++ t.errors;
  top.inhList = h.inhList ++ t.inhList;
}

nonterminal FlowSpecInh with config, location, grammarName, errors, env, pp, onNt, inhList;

autocopy attribute onNt :: TypeExp;
synthesized attribute inhList :: [String];

concrete production flowSpecInh
top::FlowSpecInh ::= inh::QNameAttrOccur
{
  top.pp = inh.pp;
  top.errors := inh.errors;
  top.inhList = if null(inh.errors) then [inh.dcl.fullName] else [];
  
  inh.attrFor = top.onNt;
}

nonterminal NtList with config, location, grammarName, errors, env, pp, flowSpecSpec, flowDefs;

concrete production nilNtList
top::NtList ::=
{
  top.pp = "";
  top.errors := [];
  top.flowDefs = [];
}
concrete production oneNtList
top::NtList ::= h::NtName
{
  top.pp = h.pp;
  top.errors := h.errors;
  top.flowDefs = h.flowDefs;
}
concrete production consNtList
top::NtList ::= h::NtName  ','  t::NtList
{
  top.pp = h.pp ++ ", " ++ t.pp;
  top.errors := h.errors ++ t.errors;
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

nonterminal NtName with config, location, grammarName, errors, env, pp, flowSpecSpec, flowDefs;

autocopy attribute flowSpecSpec :: FlowSpec;

concrete production ntName
top::NtName ::= nt::QName
{
  top.pp = nt.pp;
  top.errors :=
    if null(nt.lookupType.errors)
    then myCopy.errors
    else nt.lookupType.errors;
  
  top.flowDefs =
    if null(nt.lookupType.errors)
    then myCopy.flowDefs
    else [];
  
  local myCopy :: FlowSpec = top.flowSpecSpec;
  myCopy.config = top.config;
  myCopy.grammarName = top.grammarName;
  myCopy.env = top.env;
  
  myCopy.onNt = nt.lookupType.typerep;
}

