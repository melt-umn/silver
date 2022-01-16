grammar silver:compiler:definition:flow:env;

attribute flowDefs, refDefs, specDefs, flowEnv occurs on Root, AGDcls, AGDcl, Grammar;
flowtype flowDefs {decorate} on Root, AGDcls, AGDcl, Grammar;
flowtype refDefs {decorate} on Root, AGDcls, AGDcl, Grammar;
flowtype specDefs {decorate} on Root, AGDcls, AGDcl, Grammar;
propagate flowDefs, refDefs, specDefs on Root, AGDcls, AGDcl, Grammar;

aspect default production
top::AGDcl ::=
{
  top.flowDefs := [];
  top.refDefs := [];
  top.specDefs := [];
}
