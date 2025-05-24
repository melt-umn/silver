grammar silver:compiler:extension:nanopass;

imports silver:util:treeset as ts;
imports silver:reflect;

imports silver:compiler:driver:util;
imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:flow:env;
imports silver:compiler:analysis:typechecking:core;
imports silver:compiler:extension:autoattr;
imports silver:compiler:extension:patternmatching;
imports silver:compiler:modification:list;
imports silver:compiler:modification:let_fix;
imports silver:compiler:modification:lambda_fn;
imports silver:compiler:modification:collection;
