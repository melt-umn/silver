grammar silver:definition:flow:syntax;

imports silver:definition:core;
imports silver:definition:flow:ast;
imports silver:definition:flow:driver only FlowType, inhDepsForSyn;
imports silver:definition:env;
imports silver:definition:type;
imports silver:driver:util only isExportedBy;
imports silver:util:raw:treeset as set;

-- unfortunate...
import silver:analysis:warnings only warnAll;
import silver:analysis:warnings:defs only warnMissingInh;

terminal Flowtype 'flowtype' lexer classes {KEYWORD};

concrete production flowtypeDcl
top::AGDcl ::= 'flowtype' nt::QName '=' specs::FlowSpecs ';'
{
  top.pp = "flowtype " ++ nt.pp ++ " = " ++ specs.pp ++ ";";
  top.errors :=
    if nt.lookupType.found
    then specs.errors
    else nt.lookupType.errors;
  top.flowDefs =
    if nt.lookupType.found
    then specs.flowDefs
    else [];

  specs.onNt = nt.lookupType.typerep;
}

concrete production flowtypeAttrDcl
top::AGDcl ::= 'flowtype' attr::FlowSpec 'on' nts::NtList ';'
{
  top.pp = "flowtype " ++ attr.pp ++ " on " ++ nts.pp ++ ";";
  top.errors := nts.errors;
  top.flowDefs = nts.flowDefs;
  
  nts.flowSpecSpec = attr;
}


nonterminal FlowSpecs with config, location, grammarName, errors, env, pp, onNt, flowDefs, compiledGrammars, flowEnv;

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

nonterminal FlowSpec with config, location, grammarName, errors, env, pp, onNt, flowDefs, compiledGrammars, flowEnv;

autocopy attribute onNt :: Type;

concrete production flowSpecDcl
top::FlowSpec ::= attr::FlowSpecId  '{' inhs::FlowSpecInhs '}'
{
  top.pp = attr.pp ++ " {" ++ inhs.pp ++ "}";
  top.errors := attr.errors ++ inhs.errors;
  
  top.errors <-
    if !attr.found ||
       isExportedBy(top.grammarName, [attr.authorityGrammar], top.compiledGrammars)
    then []
    else [err(attr.location, "flow type for " ++ attr.pp ++ " must be exported by " ++ attr.authorityGrammar)];

  top.errors <-
    if attr.found &&
       length(filter(stringEq(attr.synName, _), getSpecifiedSynsForNt(top.onNt.typeName, top.flowEnv))) > 1
    then [err(attr.location, "duplicate specification of flow type for " ++ attr.pp ++ " on " ++ top.onNt.typeName)]
    else [];

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local missingFt :: [String] =
    set:toList(set:removeAll(inhs.inhList, inhDepsForSyn("forward", top.onNt.typeName, myFlow)));

  top.errors <-
    if !attr.found ||
       !(top.config.warnAll || top.config.warnMissingInh) || -- we don't want to compute flow graphs unless told to
       isExportedBy(attr.authorityGrammar, [hackGramFromFName(top.onNt.typeName)], top.compiledGrammars) ||
       null(missingFt)
    then []
    else [err(attr.location, attr.pp ++ " is an extension synthesized attribute, and must contain at least the forward flow type. It is missing " ++ implode(", ", missingFt))];
  
  -- We want to put the spec in even if there are errors in 'inhs' so that
  -- we can look up specs from inhs.
  top.flowDefs = 
    if !attr.found then []
    else [specificationFlowDef(top.onNt.typeName, attr.synName, inhs.inhList)];
}

nonterminal FlowSpecId with config, location, grammarName, errors, env, pp, onNt, synName, authorityGrammar, found;

synthesized attribute synName :: String;
synthesized attribute authorityGrammar :: String;

concrete production qnameSpecId
top::FlowSpecId ::= syn::QNameAttrOccur
{
  top.pp = syn.pp;
  top.errors := syn.errors;
  top.synName = syn.attrDcl.fullName;
  top.authorityGrammar = syn.dcl.sourceGrammar;
  top.found = syn.found && syn.attrDcl.isSynthesized;
  
  syn.attrFor = top.onNt;
  
  top.errors <-
    if !syn.found || syn.attrDcl.isSynthesized then []
    else [err(syn.location, syn.pp ++ " is not a synthesized attribute, and so cannot have a flow type")];
}

concrete production forwardSpecId
top::FlowSpecId ::= 'forward'
{
  top.pp = "forward";
  top.errors := [];
  top.synName = "forward";
  top.authorityGrammar = hackGramFromFName(top.onNt.typeName);
  top.found = true;
}

concrete production decorateSpecId
top::FlowSpecId ::= 'decorate'
{
  top.pp = "decorate";
  top.errors := [];
  top.synName = "decorate";
  top.authorityGrammar = hackGramFromFName(top.onNt.typeName);
  top.found = true;
}


nonterminal FlowSpecInhs with config, location, grammarName, errors, env, pp, onNt, inhList, flowEnv;

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

nonterminal FlowSpecInh with config, location, grammarName, errors, env, pp, onNt, inhList, flowEnv;

synthesized attribute inhList :: [String];

concrete production flowSpecInh
top::FlowSpecInh ::= inh::QNameAttrOccur
{
  top.pp = inh.pp;
  top.errors := inh.errors;
  top.inhList = if inh.found then [inh.attrDcl.fullName] else [];
  
  inh.attrFor = top.onNt;

  top.errors <-
    if !inh.found || inh.attrDcl.isInherited then []
    else [err(inh.location, inh.pp ++ " is not an inherited attribute and so cannot be within a flow type")];
}

{--
 - Inherit a flow spec from another flow spec.
 -
 - 1. This is exclusively for other things given explicit specifications:
 -    (a) by design: we want things documented in the code.
 -    (b) because it dramatically simplifies the implementation.
 -        We can do everything here, and not have to worry about having
 -        to somehow make this work in the inference process. (Which would
 -        be kinda tricky: probably we'd need to remove this attribute
 -        from the normal 'infer' process EXCEPT on the phantom production,
 -        where we'd stash the info given here as edges...)
 - 2. I'm only implementing 'decorate' and not 'forward'/syns.
 -    It's the only version demanded so far, let's wait until there's
 -    motivation to consider that extension.
 -}
concrete production flowSpecDec
top::FlowSpecInh ::= 'decorate'
{
  top.pp = "decorate";
  
  local specs :: [Pair<String [String]>] = getFlowTypeSpecFor(top.onNt.typeName, top.flowEnv);
  local decSpec :: Maybe<[String]> = lookupBy(stringEq, "decorate", specs);
  
  top.errors :=
    if decSpec.isJust then []
    else [err(top.location, s"to use 'decorate' in a flow type for nonterminal ${top.onNt.typeName}, 'decorate' must also have an explicit flow type")];
  
  top.inhList = fromMaybe([], decSpec);
}


nonterminal NtList with config, location, grammarName, errors, env, pp, flowSpecSpec, flowDefs, compiledGrammars, flowEnv;

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

nonterminal NtName with config, location, grammarName, errors, env, pp, flowSpecSpec, flowDefs, compiledGrammars, flowEnv;

autocopy attribute flowSpecSpec :: FlowSpec;

concrete production ntName
top::NtName ::= nt::QName
{
  top.pp = nt.pp;
  top.errors :=
    if nt.lookupType.found
    then myCopy.errors
    else nt.lookupType.errors;
  
  top.flowDefs =
    if nt.lookupType.found
    then myCopy.flowDefs
    else [];
  
  local myCopy :: FlowSpec = top.flowSpecSpec;
  myCopy.config = top.config;
  myCopy.grammarName = top.grammarName;
  myCopy.env = top.env;
  myCopy.compiledGrammars = top.compiledGrammars;
  myCopy.flowEnv = top.flowEnv;
  
  myCopy.onNt = nt.lookupType.typerep;
}

