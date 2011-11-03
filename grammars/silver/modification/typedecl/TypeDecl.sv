grammar silver:modification:typedecl;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:definition:env;
imports silver:util;

terminal Type_t 'type' ; -- lexer classes { KEYWORD };

concrete production typeDecl
top::AGDcl ::= 'type' id::Name botl::BracketedOptTypeList '=' te::Type ';'
{
  top.pp = "type " ++ id.pp ++ botl.pp ++ "=" ++ te.pp ++ ";";
  top.location = id.location;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;
  
  production attribute tl :: Decorated TypeList;
  tl = botl.typelist;

  top.defs = addTypeDcl(top.grammarName, id.location, fName, tl.freeVariables, te.typerep, emptyDefs());

  top.errors := tl.errors ++ te.errors;
  
  botl.env = newScopeEnv(addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTypeVariables),
                         top.env);
  te.env = botl.env;
  
  top.errors <- tl.errorsTyVars;
  top.errors <- if containsDuplicates(tl.lexicalTypeVariables)
                then [err(top.location, "Duplicate type variable names listed")]
                else [];
  
  -- Redefinition check of the name
  top.errors <- 
       if length(getTypeDcl(fName, top.env)) > 1 
       then [err(top.location, "Type '" ++ fName ++ "' is already bound.")]
       else [];

  top.errors <-
       if isLower(substring(0,1,id.name))
       then [err(id.location, "Types must be capitalized. Invalid nonterminal name " ++ id.name)]
       else [];

  forwards to agDclDefault();
}



function addTypeDcl
Defs ::= sg::String sl::Decorated Location fn::String bound::[TyVar] ty::TypeExp defs::Defs
{
  return consTypeDef(defaultEnvItem(decorate typeDcl(sg,sl,fn,bound,ty) with {}), defs);
}
abstract production typeDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String bound::[TyVar] ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables ++ bound; -- explicit to make sure it errors if we can't
  top.unparse = "type(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(bound, ty.boundVariables) ++ ", " ++ ty.unparse ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;
  forwards to defaultDcl();
}


