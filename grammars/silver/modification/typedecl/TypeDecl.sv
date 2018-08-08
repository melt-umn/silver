grammar silver:modification:typedecl;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:definition:env;

terminal Type_t 'type' lexer classes {KEYWORD};

concrete production typeDecl
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs '=' te::TypeExpr ';'
{
  top.unparse = "type " ++ id.unparse ++ tl.unparse ++ "=" ++ te.unparse ++ ";";

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
       then [err(id.location, "Type '" ++ fName ++ "' is already bound.")]
       else [];

  top.errors <-
       if isLower(substring(0,1,id.name))
       then [err(id.location, "Types must be capitalized. Invalid nonterminal name " ++ id.name)]
       else [];
}



function typeAliasDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return typeDef(defaultEnvItem(typeDcl(sg,sl,fn,bound,ty)));
}
abstract production typeDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  top.dclBoundVars = bound;
}


