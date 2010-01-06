grammar silver:extension:polymorphism;

import silver:base;
import silver:analysis:typechecking;

---------------------------
-- Polymorphic Productions
---------------------------


concrete production plmProdDcl
d::AGDcl ::= a::Abstract_kwd p::Production_kwd  pd::Id_t ps::TypeParams
	s::PlmProductionSignature body::ProductionBody
{
  d.pp = "abstract production " ++ pd.lexeme ++  ps.pp ++ "\n" ++ 
	     s.pp  ++ "  \n{\n" ++ body.pp ++ "\n}";
  d.location = "[line: " ++ toString(a.line) ++ ", column: " ++ toString(a.column) ++ "] ";  

  production attribute fName :: String;
  fName = d.grammarName.name ++ ":" ++ pd.lexeme;
  local attribute type :: Decorated TypeRep ;
  type = quantifyType(ps.prodVars, 
	prodTypeRep(toTypeRepsWithProdVars(tail(s.sigTypes)), 
		toTypeRepWithProdVars(head(s.sigTypes))));

  d.defs = addValueDcl(fName, toMonoTypeRep(type), 
	     addPlmProdDcl(fName, ps.size, head(s.sigNames), tail(s.sigNames), type, 
	     addFullNameDcl(pd.lexeme, fName,
	     addProdAttsDcl(fName, body.prodDefs, emptyDefs()))));
  d.globalDefs = addValueDcl(fName, toMonoTypeRep(type),
	         addPlmProdDcl(fName, ps.size, head(s.sigNames), tail(s.sigNames), type, 
	         emptyDefs())); 
	     

  local attribute er1 :: String;
  er1 = if length(getFullNameDcl(pd.lexeme, d.env)) > 1
       then d.location ++ "Error: Name '" ++ pd.lexeme ++ "' is already bound.\n"
       else "";	

  local attribute er2 :: String;
  er2 = if length(getValueDcl(fName, d.env)) > 1
       then d.location ++ "Error: Value '" ++ fName ++ "' is already bound.\n"
       else "";	

  -- TODO: check type vars in ps and left hand nonterminal are the same

  d.errors = er1 ++ er2 ++ ps.errors ++ s.errors ++ body.errors;
  d.typeErrors = body.typeErrors ;

  s.env = appendDefsEnv(ps.defs, d.env);

  body.env = appendDefsEnv(
	appendDefs(ps.defs, 
		appendDefs(body.defs, 
			appendDefs(s.defs, addThisDcl(fName, emptyDefs())))), 
	d.env);

  body.signatureEnv = toEnv(s.defs);
  body.localsEnv = toEnv(body.defs);

  body.signatureNames = s.sigNames;
  body.signatureTypes = s.sigTypes;
  

  -- body needs rewrite?
  
  forwards to productionDcl(a, p, pd, s.nSig, body'') 
	with {env = s.env;};
}


synthesized attribute nSig :: ProductionSignature;
attribute nSig occurs on PlmProductionSignature;

nonterminal PlmProductionSignature with {pp, location, sigNames, sigTypes, defs,
	env, errors, vars } ;

concrete production plmProdSig
p::PlmProductionSignature ::= lhs::PlmProductionLHS c::CCEQ_t rhs::ProductionRHS
{
  p.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  p.location = lhs.location;

  p.sigNames = lhs.sigNames +++ rhs.sigNames;
  p.sigTypes = lhs.sigTypes +++ rhs.sigTypes;

  p.defs = appendDefs(lhs.defs, rhs.defs);
  p.errors = lhs.errors ++ rhs.errors;

  rhs.cnt = 0;

  p.vars = lhs.vars ;

  p.nSig = productionSignature(lhs.nLHS, c, rhs'');

}

concrete production plmProdSigEmptyRHS
p::PlmProductionSignature ::= lhs::PlmProductionLHS kwd::CCEQ_t
{
  p.pp = lhs.pp ++ " ::=";
  p.location = lhs.location;

  p.defs = lhs.defs;
  p.errors = lhs.errors;

  p.sigNames = lhs.sigNames;
  p.sigTypes = lhs.sigTypes;

  p.vars = lhs.vars;

  p.nSig = productionSignatureEmptyRHS(lhs.nLHS, kwd);
}


synthesized attribute nLHS :: ProductionLHS;
attribute nLHS occurs on PlmProductionLHS;

nonterminal PlmProductionLHS with {pp, location, sigNames, sigTypes, defs, env, 
	errors, vars } ;

concrete production plmProdLHS
p::PlmProductionLHS ::= id::Id_t h::HasType_t tc::QName ps::TypeParams
{
  p.pp = id.lexeme ++ "::" ++ tc.pp ++ ps.pp;
  p.location = "[line: " ++ toString(id.line) ++ ", column: " ++ toString(id.column) ++ "] ";  

  production attribute fName :: String;
  fName = id.lexeme;

  local attribute type1 :: Decorated TypeRep;
  type1 = if !null(tcTypeConDcl) 
	 then constructedTypeRep(head(tcTypeConDcl).itemName, varsToTypeVarReps(ps.vars))
	 else topTypeRep();

  p.defs = addValueDcl(fName, type1, 
	     addFullNameDcl(id.lexeme, fName,  emptyDefs()));

  local attribute er1 :: String;
  er1 = if length(getFullNameDcl(id.lexeme, p.env)) > 1
       then p.location ++ "Error: Name '" ++ id.lexeme ++ "' is already bound.\n"
       else "";	

  local attribute er2 :: String;
  er2 = if length(getValueDcl(fName, p.env)) > 1
       then p.location ++ "Error: Value '" ++ fName ++ "' is already bound.\n"
       else "";	


  local attribute tcFNameDcl :: [Decorated EnvItem];
  local attribute tcTypeConDcl :: [Decorated EnvItem];
  local attribute type :: Decorated TypeRep ;

  tcFNameDcl = getFullNameDcl(tc.name, p.env) ;
  tcTypeConDcl = if !null(tcFNameDcl) then getTypeConDcl(head(tcFNameDcl).fullName, p.env)
		 else [];
-- type = if !null(tcTypeConDcl) 
--	 then constructedTypeRep(head(tcTypeConDcl).itemName, varsToTypeVarReps(ps.prodVars))
--	 else topTypeRep();

  local attribute er3 :: String;
  er3 = if length(tcFNameDcl) == 0
	then p.location ++ "Error: Type constructor '" ++ tc.name 
		++ "' is not declared.\n"
        else if head(tcTypeConDcl).arity != ps.size
	then p.location ++ "Error: Type constructor '" ++ tc.name
		++ "' does not have correct number of type parameters.\n"
	else "";


  p.errors = er1 ++ er2 ++ er3 ++ ps.errors;

  p.sigNames = [id.lexeme];
  p.sigTypes = [type1];

  p.vars = ps.prodVars ;

  p.nLHS = productionLHS(productionLHSElem(id, h, constructedType(tc'', ps.toTypeArgs))) ;
}





