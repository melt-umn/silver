grammar silver:extension:autocopyattr;

import silver:util;
import silver:definition:core;
import silver:definition:env;

terminal AutoCopy_kwd 'autocopy' lexer classes {KEYWORD};

concrete production autoCopyDcl
top::AGDcl ::= 'autocopy' nt::QName '.' a::QName 'on' p::QName ';'{
  top.defs = emptyDefs();
  top.moduleNames = [];
  forwards to autoCopyDclDefault(nt,a,p);
}

abstract production autoCopyDclDefault
top::AGDcl ::= nt::QName a::QName p::QName{

  production attribute nfNames :: [Decorated EnvItem];
  nfNames = getFullNameDcl(nt.name, top.env);

  production attribute nfName :: String;
  nfName = if !null(nfNames) then head(nfNames).fullName else nt.name;

  production attribute afNames :: [Decorated EnvItem];
  afNames = getFullNameDcl(a.name, top.env);

  production attribute afName :: String;
  afName = if !null(afNames) then head(afNames).fullName else a.name;

  production attribute pfNames :: [Decorated EnvItem];
  pfNames = getFullNameDcl(p.name, top.env);

  production attribute pfName :: String;
  pfName = if !null(pfNames) then head(pfNames).fullName else a.name;

  production attribute attrs :: [Decorated EnvItem];
  attrs = getAttributeDcl(afName, top.env);

  production attribute isInh :: Boolean;
  isInh = isInherited(afName, top.env);

  production attribute types :: [Decorated EnvItem];
  types = getTypeDcl(nfName, top.env);

  production attribute prods :: [Decorated EnvItem];
  prods = getProductionDcl(pfName, top.env);
  
  -- TODO: This ignores errors reported in the forwarded-to production.
  -- This is due to a bug with collection attributes that's not trivial to fix
  top.errors := [];
  
  local attribute eqTargets :: [Integer];
  eqTargets = filterAttrOccursOn(1,
				 afName,
				 getTypeNamesSignature(head(prods).namedSignature.inputElements),
				 top.env);

  forwards to if head(prods).namedSignature.outputElement.typerep.typeName != nfName || null(eqTargets)
		then agDclNone()
		else aspectProductionDcl(
				'aspect', 
				'production', 
				p, 
				makeAspectSignature(head(prods).namedSignature), 
				productionBody('{',
				               makeAutoCopyBody(
				                  a.name,
				                  eqTargets),
				               '}')
				 );
}
function filterAttrOccursOn
[Integer] ::= i::Integer attr::String nts::[String] e::Decorated Env
{
  return if null(nts) then []
                      else if doesOccurOn(attr, head(nts), e)
                              then [i] ++ filterAttrOccursOn(i+1, attr, tail(nts), e)
                              else        filterAttrOccursOn(i+1, attr, tail(nts), e);
}
function makeAutoCopyBody
ProductionStmts ::= a::String s::[Integer]{
  return if null(s) then productionStmtsNone()
		    else productionStmtsCons(
				productionStmtAttributeDef(attributeDef(
					lhsExprTwo(nameId(terminal(Id_t, "c" ++ toString(head(s)))), '.', qNameId(nameId(terminal(Id_t, a)))), 
					'=', 
					attributeAccess(baseExpr(qNameId(nameId(terminal(Id_t, "top")))), '.', qNameId(nameId(terminal(Id_t, a)))), ';')), 
				makeAutoCopyBody(a, tail(s)));
			
}

function makeAspectSignature
AspectProductionSignature ::= s::Decorated NamedSignature{
  return if null(s.inputElements) then aspectProductionSignatureEmptyRHS(aspectProductionLHSNone('_'), '::=')
	 else aspectProductionSignature(aspectProductionLHSId(nameId(terminal(Id_t, "top"))), '::=', makeAspectSignatureRHS(1, length(s.inputElements)));

}

function makeAspectSignatureRHS
AspectRHS ::= i::Integer max::Integer{
  return if i == max then aspectRHSElem(aspectRHSElemId(nameId(terminal(Id_t, "c" ++ toString(i)))))
	 	     else aspectRHSElemCons(aspectRHSElemId(nameId(terminal(Id_t, "c" ++ toString(i)))), makeAspectSignatureRHS(i+1, max));
}

aspect production autoCopyDclDefault
top::AGDcl ::= nt::QName a::QName p::QName{
  local attribute er1 :: [Decorated Message];
  er1 = if null(attrs)
	then [err(top.location, "Attribute '" ++ a.name ++ "' is not declared.")]
	else [];

  local attribute er2 :: [Decorated Message];
  er2 = if null(types)
	then [err(top.location, "Nonterminal '" ++ nt.name ++ "' is not declared.")] 
	else [];

  local attribute er3 :: [Decorated Message];
  er3 = if length(nfNames) > 1
	then [err(top.location, "Nonterminal '" ++ nt.name ++ "' has multiple delarations.")] 
	else [];

  local attribute er4 :: [Decorated Message];
  er4 = if length(afNames) > 1
	then [err(top.location, "Attribute '" ++ a.name ++ "' has multiple delarations.")] 
	else [];

  local attribute er5 :: [Decorated Message];
  er5 = if null(prods)
	then [err(top.location, "Production '" ++ p.name ++ "' is not declared.")] 
	else [];

  local attribute er6 :: [Decorated Message];
  er6 = if length(pfNames) > 1
	then [err(top.location, "Production '" ++ p.name ++ "' has multiple delarations.")] 
	else [];

  local attribute er7 :: [Decorated Message];
  er7 = if !isInh
	then [err(top.location, "Attribute '" ++ a.name ++ "' is not an inherited attribute.")] 
	else [];

  top.errors <- er1 ++ er2 ++ er3 ++ er4 ++ er5 ++ er6 ++ er7;
}


--Extension on Extension
concrete production autoCopyDclAll
top::AGDcl ::= 'autocopy' nt::QName '.' a::QName 'on' p::QName '*' ';'{

  top.defs = emptyDefs();
  top.moduleNames = [];

  local attribute allNames :: [Decorated EnvItem];
  allNames = getAllFullNameDcls(top.env);

  local attribute grammarProds :: [String];  
  grammarProds = getProductionsForGrammar(p.name, allNames, [], top.env);

  forwards to makeAutoCopies(nt, a, grammarProds);
}

terminal Except_kwd 'except' lexer classes {KEYWORD};
concrete production autoCopyDclAllExcept
top::AGDcl ::= 'autocopy' nt::QName '.' a::QName 'on' p::QName '*' 'except' n::NameList ';'{

  top.defs = emptyDefs();
  top.moduleNames = [];

  local attribute allNames :: [Decorated EnvItem];
  allNames = getAllFullNameDcls(top.env);

  local attribute grammarProds :: [String];  
  grammarProds = getProductionsForGrammar(p.name, allNames, lookupFullNames(n.names, top.env), top.env);

  forwards to makeAutoCopies(nt, a, grammarProds);
}


---


function getProductionsForGrammar
[String] ::= g::String e::[Decorated EnvItem] ex::[String] theEnv::Decorated Env{

  local attribute ei::[Decorated EnvItem];
  ei = getProductionDcl(head(e).fullName, theEnv);
  
  return if null(e) 
	then [::String] 
	else if !null(ei) && startsWith(g, head(ei).itemName) && !contains(head(ei).itemName, ex)
	     then ([head(e).itemName] ++ getProductionsForGrammar(g, tail(e), ex, theEnv))
	     else getProductionsForGrammar(g, tail(e), ex, theEnv);
}

function makeAutoCopies
AGDcl ::= nt::QName a::QName p::[String]{
  return if null(p) then agDclNone()
         else agDclAppend(autoCopyDclDefault(nt, a, qNameId(nameId(terminal(Id_t, head(p))))), makeAutoCopies(nt, a, tail(p)));
}

function lookupFullNames 
[String] ::= s::[String] e::Decorated Env {

  local attribute i :: [Decorated EnvItem];
  i = getFullNameDclOne(head(s), e);

  return if null(s) then []
	 else if null(i) then lookupFullNames(tail(s), e)
	 else [head(i).fullName] ++ lookupFullNames(tail(s), e);
}
