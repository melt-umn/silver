grammar silver:compiler:definition:core;

terminal Annotation_kwd 'annotation' lexer classes {KEYWORD};

disambiguate Annotation_kwd, IdLower_t { pluck Annotation_kwd; }

concrete production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "annotation " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  production fName :: String = top.grammarName ++ ":" ++ a.name;

  top.defs := [annoDef(top.grammarName, a.nameLoc, fName, tl.freeVariables, te.typerep)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [errFromOrigin(a, "The name '" ++ fName ++ "' is already bound.")]
    else [];
  top.errors <-
    if indexOf(":", a.name) == -1 then []
    else [errFromOrigin(a, "The name '" ++ a.name ++ "' must not be qualified.")];

  top.errors <- tl.errorsTyVars;
}


