grammar silver:extension:polymorphism;

import core;
import silver:base;
import silver:analysis:typechecking;


-- attribute occurs on declarations
concrete production plmOccurs1
d::AGDcl ::= at::Attribute_kwd a::QName ps1::TypeParams oc::Occurs_kwd o::On_kwd 
	nt::QName ps2::TypeParams s::Semi_t
{
  d.location = "[line: " ++ toString(at.line) ++ ", column: " ++ 
	toString(at.column) ++ "] ";
  d.pp = "attribute " ++ a.name ++ ps1.pp ++ " occurs on " ++ nt.name ++ 
	ps2.pp ++ " ;" ; 

  
  d.defs = addPlmOccursDcl(afName, ps1.occVars, nfName, ps2.occVars, emptyDefs());
  d.globalDefs = addPlmOccursDcl(afName, ps1.occVars, nfName, ps2.occVars, emptyDefs());

  production attribute afNames :: [Decorated EnvItem];
  afNames = getFullNameDclOne(a.name, d.env);

  production attribute afName :: String;
  afName = if !null(afNames) then head(afNames).fullName else "FULL_NAME_NOT_DEFINED";

  production attribute nfNames :: [Decorated EnvItem];
  nfNames = getFullNameDclOne(nt.name, d.env);

  production attribute nfName :: String;
  nfName = if !null(nfNames) then head(nfNames).fullName else "FULL_NAME_NOT_DEFINED";

  local attribute attrs :: [Decorated EnvItem];
  attrs = getPlmAttrDcl(afName, d.env);

  local attribute tcs :: [Decorated EnvItem];
  tcs = getTypeConDcl(nfName, d.env);

  local attribute er1 :: String;
  er1 = if null(attrs)
	then d.location ++ "Error: Attribute '" ++ a.name ++ "' is not declared.\n" 
	else "";

  local attribute er2 :: String;
  er2 = if null(tcs)
	then d.location ++ "Error: Nonterminal  '" ++ nt.name ++ "' is not declared.\n" 
	else "";

  local attribute er3 :: String;
  er3 = if null(tcs) || head(attrs).arity == ps1.size
	then ""
	else d.location ++ "Error: Incorrect number of arguments for polymorphic"
		++ " attribute '" ++ a.name ++ "'.\n";

  local attribute er4 :: String;
  er4 = if null(tcs) || head(tcs).arity == ps2.size
	then ""
	else d.location ++ "Error: Incorrect number of arguments for polymorphic"
		++ " nonterminal '" ++ nt.name ++ "'.\n";

  -- check ps1 is a subset of ps2
  local attribute er5 :: String;
  er5 = if varsIn(ps1.vars, ps2.vars)
	then ""
	else d.location ++ "Error: Parameters for polymorphic attribute '" ++ a.name
		++ "' must be a subset of the parameters for the polymorphic nonterminal it "
		++ "decorates.\n"; 

  d.errors = er1 ++ er2 ++ ps2.errors;
  d.typeErrors = er3 ++ er4 ++ er5 ;

  forwards to attributionDcl(at, a'', oc, o, nt'', s)
	with { env = convertToMonoEnv(d.env);
	       globalEnv = convertToMonoEnv(d.globalEnv); } ; 
}

function varsIn
Boolean ::= vs1::[String] vs2::[String]
{
  return if null(vs1) then true
	 else if varIn(head(vs1), vs2)
	 then varsIn(tail(vs1), vs2)
	 else false;
}

function varIn
Boolean ::= v::String vs::[String]
{
  return if null(vs) then false
	 else if v == head(vs) then true
	 else varIn(v, tail(vs));
}


concrete production plmOccurs2
d::AGDcl ::= at::Attribute_kwd a::QName oc::Occurs_kwd o::On_kwd nt::QName 
	ps::TypeParams s::Semi_t 
{
  d.location = "[line: " ++ toString(at.line) ++ ", column: " ++ 
	toString(at.column) ++ "] ";
  d.pp = "attribute " ++ a.name ++ " occurs on " ++ nt.name ++ ps.pp ++ " ;" ;

  
  d.defs = addPlmOccursDcl(afName, [], nfName, ps.occVars, emptyDefs());
  d.globalDefs = addPlmOccursDcl(afName, [], nfName, ps.occVars, emptyDefs());

  production attribute afNames :: [Decorated EnvItem];
  afNames = getFullNameDclOne(a.name, d.env);

  production attribute afName :: String;
  afName = if !null(afNames) then head(afNames).fullName else "FULL_NAME_NOT_DEFINED";

  production attribute nfNames :: [Decorated EnvItem];
  nfNames = getFullNameDclOne(nt.name, d.env);

  production attribute nfName :: String;
  nfName = if !null(nfNames) then head(nfNames).fullName else "FULL_NAME_NOT_DEFINED";

  local attribute attrs :: [Decorated EnvItem];
  attrs = getAttributeDcl(afName, d.env);

  local attribute tcs :: [Decorated EnvItem];
  tcs = getTypeConDcl(nfName, d.env);

  local attribute er1 :: String;
  er1 = if null(attrs)
	then d.location ++ "Error: Attribute '" ++ a.name ++ "' is not declared.\n" 
	else "";

  local attribute er2 :: String;
  er2 = if null(tcs)
	then d.location ++ "Error: Nonterminal  '" ++ nt.name ++ "' is not declared.\n" 
	else "";

  local attribute er3 :: String;
  er3 = if null(tcs) || head(tcs).arity == ps.size
	then ""
	else d.location ++ "Error: Incorrect number of arguments for polymorphic"
		++ " nonterminal '" ++ nt.name ++ "'.\n";

  d.errors = er1 ++ er2 ++ ps.errors;
  d.typeErrors = er3;

  forwards to attributionDcl(at, a'', oc, o, nt'', s)
	with { env = convertToMonoEnv(d.env);
	       globalEnv = convertToMonoEnv(d.globalEnv); } ; 
}


