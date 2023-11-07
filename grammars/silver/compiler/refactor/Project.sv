grammar silver:compiler:refactor;

imports silver:util:cmdargs;
imports silver:compiler:driver;

imports silver:compiler:definition:core;
imports silver:compiler:definition:type;
imports silver:compiler:definition:env;
imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:flow:syntax;

imports silver:compiler:analysis:typechecking:core;

imports silver:compiler:modification:let_fix;
imports silver:compiler:modification:lambda_fn;
imports silver:compiler:modification:collection;
imports silver:compiler:modification:primitivepattern;
imports silver:compiler:modification:ffi;
imports silver:compiler:modification:copper;
imports silver:compiler:modification:defaultattr;
imports silver:compiler:modification:list;
imports silver:compiler:modification:copper_mda;

imports silver:compiler:extension:concisefunctions;

imports silver:rewrite;
imports silver:langutil:unparse;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  prodChildLayout <- [
    ("silver:compiler:extension:concisefunctions:shortFunctionDcl", 1, " "),
    ("silver:compiler:extension:concisefunctions:shortFunctionDcl", 3, "\n  ")
  ];
}

aspect production terminalAST
top::AST ::= _ _ _
{
  termPreLayout <- [
    ("silver:compiler:definition:core:Equal_t", " ")
  ];
  termPostLayout <- [
    ("silver:compiler:definition:core:Global_kwd", " "),
    ("silver:compiler:extension:concisefunctions:Fun_kwd", " "),
    ("silver:compiler:definition:core:Comma_t", " "),
    ("silver:compiler:definition:core:Equal_t", " ")
  ];
}
