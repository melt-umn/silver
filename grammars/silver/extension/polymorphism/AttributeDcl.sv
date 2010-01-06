grammar silver:extension:polymorphism;

import silver:base;
import silver:analysis:typechecking;

------------------------------------------
-- Polymorphically Typed Attribute Declaration
------------------------------------------

-- attribute type declarations
concrete production plmAttrDclInh
d::AGDcl ::= i::Inherited_kwd at::Attribute_kwd a::Id_t ps::TypeParams
	 ht::HasType_t t::Type s::Semi_t
{
  d.pp = "inherited attribute " ++ a.lexeme ++ ps.pp ++ " :: " ++ t.pp ++ " ;" ;
  d.location = "[line: " ++ toString(i.line) ++ ", column: " ++ 
	toString(i.column) ++ "] ";

  production attribute fName :: String;
  fName = d.grammarName.name ++ ":" ++ a.lexeme;

  local attribute type :: Decorated TypeRep ;
  type = quantifyType(ps.attVars, toTypeRepWithAttVars(t.typerep)) ;  

  d.defs = addPlmAttrDcl(fName, ps.size, type, 
		addInheritedDcl(fName,
			addFullNameDcl(a.lexeme, fName, emptyDefs()))) ;
  d.globalDefs = addPlmAttrDcl(fName, ps.size, type,
			addInheritedDcl(fName, emptyDefs()));

  local attribute er1 :: String;
  er1 = if length(getFullNameDcl(a.lexeme, d.env)) > 1
       then d.location ++ "Error: Name '" ++ a.lexeme ++ "' is already bound.\n"
       else "";	

  local attribute er2 :: String;
  er2 = if length(getPlmAttrDcl(fName, d.env)) > 1
       then d.location ++ "Error: Attribute '" ++ fName ++ "' is already bound.\n"
       else "";	

--  local attribute er3 :: String;
--  er3 = if isClosedType(type) 
--	then ""  
--	else d.location ++ "Error: (Some) type variable(s) in the type of attribute "
--		++ a.lexeme ++ " does not appear in its parameters.\n";

  d.errors = er1 ++ er2 ++ ps.errors ++ t.errors;
  d.typeErrors = "";

  t.env = appendDefsEnv(ps.defs, d.env);

  forwards to attributeDclInh(i, at, a, ht, t'', s);
} 

concrete production plmAttrDclSyn
d::AGDcl ::= i::Synthesized_kwd at::Attribute_kwd a::Id_t ps::TypeParams 
	ht::HasType_t t::Type s::Semi_t
{
  d.pp = "inherited attribute " ++ a.lexeme ++ ps.pp ++ " :: " ++ t.pp ++ " ;" ;
  d.location = "[line: " ++ toString(i.line) ++ ", column: " ++ 
	toString(i.column) ++ "] ";

  production attribute fName :: String;
  fName = d.grammarName.name ++ ":" ++ a.lexeme;

  local attribute type :: Decorated TypeRep ;
  type = quantifyType(ps.attVars, toTypeRepWithAttVars(t.typerep)) ;  

  d.defs = addPlmAttrDcl(fName, ps.size, type, 
		addSynthesizedDcl(fName,
			addFullNameDcl(a.lexeme, fName, emptyDefs()))) ;
  d.globalDefs = addPlmAttrDcl(fName, ps.size, type,
			addSynthesizedDcl(fName, emptyDefs()));


  local attribute er1 :: String;
  er1 = if length(getFullNameDcl(a.lexeme, d.env)) > 1
       then d.location ++ "Error: Name '" ++ a.lexeme ++ "' is already bound.\n"
       else "";	

  local attribute er2 :: String;
  er2 = if length(getPlmAttrDcl(fName, d.env)) > 1
       then d.location ++ "Error: Attribute '" ++ fName ++ "' is already bound.\n"
       else "";	

--  local attribute er3 :: String;
--  er3 = if isClosedType(type) 
--	then ""  
--	else d.location ++ "Error: (Some) type variable(s) in the type of attribute "
--		++ a.lexeme ++ " does not appear in its parameters.\n";

  d.errors = er1 ++ er2 ++ ps.errors ++ t.errors;
  d.typeErrors = "";

  t.env = appendDefsEnv(ps.defs, d.env);

  forwards to attributeDclSyn(i, at, a, ht, t'', s);
}





