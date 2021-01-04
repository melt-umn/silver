grammar silver:compiler:modification:impide:spec;

--imports silver:compiler:definition:core;
imports silver:compiler:definition:env;

imports silver:compiler:definition:concrete_syntax;
--imports silver:compiler:modification:copper;

--imports silver:compiler:definition:concrete_syntax:ast;
--imports silver:compiler:modification:impide:cstast;
imports silver:compiler:definition:concrete_syntax:ast;
imports silver:compiler:modification:impide:cstast;

--imports silver:compiler:modification:impide only getPropertyGenerator, getPropertyProvider;

imports silver:compiler:translation:java:core only makeProdName, makeParserName, makeName;

imports silver:compiler:driver:util only grammarToPath;

