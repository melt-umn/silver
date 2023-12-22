grammar silver:compiler:modification:concisefunctions;

imports silver:compiler:definition:core;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:type;
imports silver:compiler:definition:env;
imports silver:compiler:definition:flow:env;
imports silver:compiler:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph;
imports silver:compiler:analysis:typechecking:core;
imports silver:compiler:driver:util;

exports silver:compiler:modification:concisefunctions:java with silver:compiler:translation:java:core;
