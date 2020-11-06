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
import silver:analysis:warnings:flow only warnMissingInh;

terminal Flowtype 'flowtype' lexer classes {KEYWORD};

concrete production flowtypeDcl
top::AGDcl ::= 'flowtype' nt::QName '=' specs::FlowSpecs ';'
{
  top.unparse = "flowtype " ++ nt.unparse ++ " = " ++ specs.unparse ++ ";";
  top.errors :=
    if nt.lookupType.found
    then specs.errors
    else nt.lookupType.errors;
  top.flowDefs :=
    if nt.lookupType.found
    then specs.flowDefs
    else [];

  -- This is only ever used for its name, really, so no need to freshen
  specs.onNt = nt.lookupType.typerep;
}

concrete production flowtypeAttrDcl
top::AGDcl ::= 'flowtype' attr::FlowSpec 'on' nts::NtList ';'
{
  top.unparse = "flowtype " ++ attr.unparse ++ " on " ++ nts.unparse ++ ";";
  top.errors := nts.errors;
  top.flowDefs := nts.flowDefs;
  
  nts.flowSpecSpec = attr;
}


nonterminal FlowSpecs with config, location, grammarName, errors, env, unparse, onNt, flowDefs, compiledGrammars, flowEnv;

propagate errors, flowDefs on FlowSpecs;

concrete production oneFlowSpec
top::FlowSpecs ::= h::FlowSpec
{
  top.unparse = h.unparse;
}
concrete production snocFlowSpec
top::FlowSpecs ::= h::FlowSpecs  ','  t::FlowSpec
{
  top.unparse = h.unparse ++ ", " ++ t.unparse;
}

nonterminal FlowSpec with config, location, grammarName, errors, env, unparse, onNt, flowDefs, compiledGrammars, flowEnv;

autocopy attribute onNt :: Type;

propagate errors on FlowSpec;

concrete production flowSpecDcl
top::FlowSpec ::= attr::FlowSpecId  '{' inhs::FlowSpecInhs '}'
{
  top.unparse = attr.unparse ++ " {" ++ inhs.unparse ++ "}";
  
  top.errors <-
    if !attr.found ||
       isExportedBy(top.grammarName, [attr.authorityGrammar], top.compiledGrammars)
    then []
    else [err(attr.location, "flow type for " ++ attr.name ++ " must be exported by " ++ attr.authorityGrammar)];

  top.errors <-
    if attr.found &&
       length(filter(stringEq(attr.synName, _), getSpecifiedSynsForNt(top.onNt.typeName, top.flowEnv))) > 1
    then [err(attr.location, "duplicate specification of flow type for " ++ attr.name ++ " on " ++ top.onNt.typeName)]
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
    else [err(attr.location, attr.name ++ " is an extension synthesized attribute, and must contain at least the forward flow type. It is missing " ++ implode(", ", missingFt))];
  
  -- We want to put the spec in even if there are errors in 'inhs' so that
  -- we can look up specs from inhs.
  top.flowDefs :=
    if !attr.found then []
    else [specificationFlowDef(top.onNt.typeName, attr.synName, inhs.inhList)];
}

nonterminal FlowSpecId with config, location, grammarName, errors, env, unparse, onNt, synName, authorityGrammar, found, name;

synthesized attribute synName :: String;
synthesized attribute authorityGrammar :: String;

propagate errors on FlowSpecId;

concrete production qnameSpecId
top::FlowSpecId ::= syn::QNameAttrOccur
{
  top.name = syn.name;
  top.unparse = syn.unparse;
  top.synName = syn.attrDcl.fullName;
  top.authorityGrammar = syn.dcl.sourceGrammar;
  top.found = syn.found && syn.attrDcl.isSynthesized;
  
  syn.attrFor = top.onNt;
  
  top.errors <-
    if !syn.found || syn.attrDcl.isSynthesized then []
    else [err(syn.location, syn.name ++ " is not a synthesized attribute, and so cannot have a flow type")];
}

concrete production forwardSpecId
top::FlowSpecId ::= 'forward'
{
  top.name = "forward";
  top.unparse = top.name;
  top.synName = "forward";
  top.authorityGrammar = hackGramFromFName(top.onNt.typeName);
  top.found = true;
}

concrete production decorateSpecId
top::FlowSpecId ::= 'decorate'
{
  top.name = "decorate";
  top.unparse = top.name;
  top.synName = "decorate";
  top.authorityGrammar = hackGramFromFName(top.onNt.typeName);
  top.found = true;
}


nonterminal FlowSpecInhs with config, location, grammarName, errors, env, unparse, onNt, inhList, flowEnv;

propagate errors on FlowSpecInhs;

concrete production nilFlowSpecInhs
top::FlowSpecInhs ::=
{
  top.unparse = "";
  top.inhList = [];
}
concrete production oneFlowSpecInhs
top::FlowSpecInhs ::= h::FlowSpecInh
{
  top.unparse = h.unparse;
  top.inhList = h.inhList;
}
concrete production consFlowSpecInhs
top::FlowSpecInhs ::= h::FlowSpecInh  ','  t::FlowSpecInhs
{
  top.unparse = h.unparse ++ ", " ++ t.unparse;
  top.inhList = h.inhList ++ t.inhList;
}

nonterminal FlowSpecInh with config, location, grammarName, errors, env, unparse, onNt, inhList, flowEnv;

synthesized attribute inhList :: [String];

propagate errors on FlowSpecInh;

concrete production flowSpecInh
top::FlowSpecInh ::= inh::QNameAttrOccur
{
  top.unparse = inh.unparse;
  top.inhList = if inh.found then [inh.attrDcl.fullName] else [];
  
  inh.attrFor = top.onNt;

  top.errors <-
    if !inh.found || inh.attrDcl.isInherited then []
    else [err(inh.location, inh.name ++ " is not an inherited attribute and so cannot be within a flow type")];
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
  top.unparse = "decorate";
  
  local specs :: [Pair<String [String]>] = getFlowTypeSpecFor(top.onNt.typeName, top.flowEnv);
  local decSpec :: Maybe<[String]> = lookupBy(stringEq, "decorate", specs);
  
  top.errors <-
    if decSpec.isJust then []
    else [err(top.location, s"to use 'decorate' in a flow type for nonterminal ${top.onNt.typeName}, 'decorate' must also have an explicit flow type")];
  
  top.inhList = fromMaybe([], decSpec);
}


nonterminal NtList with config, location, grammarName, errors, env, unparse, flowSpecSpec, flowDefs, compiledGrammars, flowEnv;

propagate errors, flowDefs on NtList;

concrete production nilNtList
top::NtList ::=
{
  top.unparse = "";
}
concrete production oneNtList
top::NtList ::= h::NtName
{
  top.unparse = h.unparse;
}
concrete production consNtList
top::NtList ::= h::NtName  ','  t::NtList
{
  top.unparse = h.unparse ++ ", " ++ t.unparse;
}

nonterminal NtName with config, location, grammarName, errors, env, unparse, flowSpecSpec, flowDefs, compiledGrammars, flowEnv;

autocopy attribute flowSpecSpec :: FlowSpec;

concrete production ntName
top::NtName ::= nt::QName
{
  top.unparse = nt.unparse;
  top.errors :=
    if nt.lookupType.found
    then myCopy.errors
    else nt.lookupType.errors;
  
  top.flowDefs :=
    if nt.lookupType.found
    then myCopy.flowDefs
    else [];
  
  local myCopy :: FlowSpec = top.flowSpecSpec;
  myCopy.config = top.config;
  myCopy.grammarName = top.grammarName;
  myCopy.env = top.env;
  myCopy.compiledGrammars = top.compiledGrammars;
  myCopy.flowEnv = top.flowEnv;
  
  -- This is only ever used for its name, really, so no need to freshen
  myCopy.onNt = nt.lookupType.typerep;
}

