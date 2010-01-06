grammar silver:extension:polymorphism;

import silver:base;
import silver:analysis:typechecking;

--------------------------------------
-- Polymorphic Nonterminal Declaration
--------------------------------------

concrete production plmNontermDcl
d::AGDcl ::= n::NonTerminal_kwd tc::Id_t ps::TypeParams s::Semi_t
{
  d.location = "[line: " ++ toString(tc.line) ++ ", column: " ++ 
	toString(tc.column) ++ "] ";
  d.pp = "nonterminal " ++ tc.lexeme ++ ps.pp ++ ";" ;
  

  production attribute fName :: String;
  fName = d.grammarName.name ++ ":" ++ tc.lexeme;

  local attribute dcl :: Decorated AGDcl;
  dcl = decorate nonterminalDcl(n, tc, s) 
	with {env = e;
	      globalEnv = ge; 
	      grammarName = d.grammarName; };

  d.defs = addTypeConDcl(fName, ps.size, dcl.defs);

  d.globalDefs = addTypeConDcl(fName, ps.size, dcl.globalDefs);

  local attribute er1 :: String;
  er1 = if length(getFullNameDcl(tc.lexeme, d.env)) > 1 
       then d.location ++ "Error: Name '" ++ tc.lexeme ++ "' is already bound.\n"
       else "";	

  local attribute er2 :: String;
  er2 = if length(getTypeConDcl(fName, d.env)) +
	   length(getTypeDcl(fName, d.env)) > 2
       then d.location ++ "Error: Type '" ++ fName ++ "' is already bound.\n"
       else "";	

  d.errors = er1 ++ er2 ++ ps.errors;
  d.typeErrors = "";

  -- translation to base language
  local attribute ge::Decorated Env ;
  ge = convertToMonoEnv(d.globalEnv);

  local attribute e::Decorated Env ;
  e = convertToMonoEnv(d.env);
  
  forwards to nonterminalDcl(n, tc, s) 
	with {env = e;
	      globalEnv = ge;
	      grammarName = d.grammarName;} ;
}
