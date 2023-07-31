grammar silver:compiler:modification:copper;

imports silver:langutil:lsp as lsp;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:definition:concrete_syntax:ast;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:flow:env;

imports silver:compiler:modification:list;

imports silver:compiler:analysis:typechecking:core;
imports silver:compiler:analysis:uniqueness;

imports silver:compiler:translation:java:core;
imports silver:compiler:translation:java:type;
