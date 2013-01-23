grammar silver:modification:typedecl;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:definition:env;
imports silver:util;

terminal Type_t 'type' ; -- lexer classes { KEYWORD };

concrete production typeDecl
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeList '=' te::Type ';'
{
  top.pp = "type " ++ id.pp ++ tl.pp ++ "=" ++ te.pp ++ ";";
  top.location = id.location;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;
  
  top.defs = [typeAliasDef(top.grammarName, id.location, fName, tl.freeVariables, te.typerep)];

  top.errors := tl.errors ++ te.errors ++ tl.errorsTyVars;
  
  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;
  
  -- Redefinition check of the name
  top.errors <- 
       if length(getTypeDclAll(fName, top.env)) > 1 
       then [err(top.location, "Type '" ++ fName ++ "' is already bound.")]
       else [];

  top.errors <-
       if isLower(substring(0,1,id.name))
       then [err(id.location, "Types must be capitalized. Invalid nonterminal name " ++ id.name)]
       else [];
}



function typeAliasDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  return typeDef(defaultEnvItem(typeDcl(sg,sl,fn,bound,ty)));
}
abstract production typeDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables ++ bound; -- explicit to make sure it errors if we can't
  top.unparse = "type(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(bound, ty.boundVariables) ++ ", " ++ ty.unparse ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;
}


