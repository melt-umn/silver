grammar silver:compiler:translation:java:core;

attribute className, prodLocalCountName occurs on BlockContext;

{--
 - The name of the class for the current context.
 - e.g. "silver.definition.core.PbaseExpr"
 -
 - Valid to access only within productions/functions.
 - (used by equations. exprs, but only in flowDefs, which shouldn't be accessed outside productions)
 - (Also used by production action blocks to access children)
 -}
synthesized attribute className :: String;
{--
 - The name to access a production's local count.
 - e.g. "silver.definition.core.Init.count_local__ON__silver_definition_core_PbaseExpr"
 -
 - Also valid only in prod/func. Used only by local declarations.
 -}
synthesized attribute prodLocalCountName :: String;

aspect default production
top::BlockContext ::=
{
  top.className = error("context does not have a className");
  top.prodLocalCountName = error("prodLocalCountName in context without locals");
}

aspect production functionContext
top::BlockContext ::= sig::NamedSignature  _
{
  top.className = makeProdName(top.fullName);
  top.prodLocalCountName =
    makeName(top.sourceGrammar) ++ ".Init.count_local__ON__" ++ makeIdName(top.fullName);
}

aspect production productionContext
top::BlockContext ::= sig::NamedSignature  _
{
  top.className = makeProdName(top.fullName);
  top.prodLocalCountName =
    makeName(top.sourceGrammar) ++ ".Init.count_local__ON__" ++ makeIdName(top.fullName);
}

aspect production globalExprContext
top::BlockContext ::= _ _ _ _
{
}

