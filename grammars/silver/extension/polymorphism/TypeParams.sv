grammar silver:extension:polymorphism;

import silver:base;
import silver:analysis:typechecking;


-----------------------------
-- Type Parameters
-----------------------------

--synthesized attribute size :: Integer ;
synthesized attribute vars :: [String] ;
synthesized attribute attVars :: [String] ;
synthesized attribute prodVars :: [String] ;
synthesized attribute occVars :: [String] ;
synthesized attribute toTypeArgs :: TypeArgs;
synthesized attribute toArgList :: TypeArgList;

nonterminal TypeParams with {size, pp, location, errors, attVars, prodVars, 
	occVars, vars, defs, env, toTypeArgs} ;
nonterminal ParamList with {size, pp, location, errors, attVars, prodVars, 
	occVars, vars, defs, env, toArgList} ;

concrete production typeParams
ps::TypeParams ::= l::DLT_t pl::ParamList g::DGT_t
{
  ps.pp = "<<" ++ pl.pp ++ ">>";
  ps.location = "[line: " ++ toString(l.line) ++ ", column: " ++ toString(l.column) ++ "] ";

  ps.size = pl.size;  
  ps.errors = pl.errors;

  ps.attVars = pl.attVars;
  ps.prodVars = pl.prodVars;
  ps.occVars = pl.occVars;
  ps.vars = pl.vars;
  ps.defs = pl.defs;

  ps.toTypeArgs = typeArgs(l, pl.toArgList, g);
}


concrete production paramListOne
pl::ParamList ::= v::Id_t
{
  pl.pp = v.lexeme;
  pl.location = "[line: " ++ toString(v.line) ++ ", column: " ++ toString(v.column) ++ "] ";

  pl.size = 1;
  pl.errors = er1;

  pl.attVars = [ "_ATT_" ++ v.lexeme ];
  pl.prodVars = [ "_PROD_" ++ v.lexeme ];
  pl.occVars = [ "_OCC_" ++ v.lexeme ];
  pl.vars = [ v.lexeme ];
  pl.defs = addTypeVarDcl(v.lexeme, emptyDefs());

  pl.toArgList = typeArgListOne(varType(v.lexeme));

  local attribute er1 :: String;
  er1 = if !null(getFullNameDcl(v.lexeme, pl.env))
	then "Error: Name '" ++ v.lexeme ++ "' is already bound.\n"
	else "";
}


concrete production paramListMore
pl::ParamList ::= pl1::ParamList c::Comma_t v::Id_t
{
  pl.pp = pl1.pp ++ ", " ++ v.lexeme ;
  pl.location = "[line: " ++ toString(c.line) ++ ", column: " ++ toString(c.column) ++ "] ";

  pl.size = pl1.size + 1 ;
  pl.errors = pl1.errors ++ er1 ++ er2;

  pl.attVars = pl1.attVars +++ [ "_ATT_" ++ v.lexeme ] ;
  pl.prodVars = pl1.prodVars +++ [ "_PROD_" ++ v.lexeme ];
  pl.occVars = pl1.occVars +++ [ "_OCC_" ++ v.lexeme ] ;
  pl.vars = pl1.vars +++ [ v.lexeme ];
  pl.defs = appendDefs(pl1.defs, addTypeVarDcl(v.lexeme, emptyDefs()));

  pl.toArgList = typeArgListMore(pl1.toArgList, c, varType(v.lexeme));

  local attribute er1 :: String;
  er1 = if varIn(v.lexeme, pl1.vars)
	then "Error: Type variable '" ++ v.lexeme ++ "appears more than once in the parameters.\n"
	else "";

  local attribute er2 :: String;
  er2 = if !null(getFullNameDcl(v.lexeme, pl.env))
	then "Error: Name '" ++ v.lexeme ++ "' is already bound.\n"
	else "";
}



