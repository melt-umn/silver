grammar silver:definition:core;

terminal Annotation_kwd 'annotation' lexer classes {KEYWORD};

concrete production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  forwards to annotationCreate(a,tl,te,nothing());
}

concrete production annotationDefault
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ',' 'default' 'to' e::Expr ';'
{
  forwards to annotationCreate(a,tl,te,just(e));
}

concrete production annotationCreate
top::AGDcl ::= a::QName tl::BracketedOptTypeExprs te::TypeExpr e::Maybe<Expr>
{
  top.pp = "annotation " ++ a.pp ++ tl.pp ++ " :: " ++ te.pp ++ ";";

  production fName :: String = top.grammarName ++ ":" ++ a.name;

  top.defs = [annoDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, e)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [err(a.location, "The name '" ++ fName ++ "' is already bound.")]
    else [];
  top.errors <-
    if indexOf(":", a.name) == -1 then []
    else [err(a.location, "The name '" ++ a.name ++ "' must not be qualified.")];

  top.errors := te.errors ++ tl.errors ++ tl.errorsTyVars;
}