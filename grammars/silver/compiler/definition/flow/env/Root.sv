grammar silver:compiler:definition:flow:env;

attribute flowDefs, flowEnv occurs on Root, AGDcls, AGDcl, Grammar;
propagate flowDefs on Root, AGDcls, AGDcl, Grammar;

aspect default production
top::AGDcl ::=
{
  top.flowDefs := [];
}
