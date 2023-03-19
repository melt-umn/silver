grammar silver:compiler:analysis:uniqueness;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:flow:ast;
imports silver:compiler:definition:flow:syntax;
imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:translation:java:core only finalType;
imports silver:compiler:driver:util;

imports silver:compiler:modification:let_fix;
imports silver:compiler:modification:lambda_fn;
imports silver:compiler:modification:primitivepattern;
imports silver:compiler:modification:copper;
imports silver:compiler:modification:copper_mda;
imports silver:compiler:modification:collection;
imports silver:compiler:modification:defaultattr;
imports silver:compiler:modification:ffi;

