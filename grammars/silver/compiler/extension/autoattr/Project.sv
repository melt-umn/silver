grammar silver:compiler:extension:autoattr;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:flow:ast;
imports silver:compiler:definition:flow:env;
imports silver:compiler:analysis:typechecking:core;
imports silver:compiler:modification:collection;
imports silver:compiler:modification:let_fix;
imports silver:compiler:extension:patternmatching;
imports silver:compiler:metatranslation;

exports silver:compiler:extension:autoattr:convenience;
