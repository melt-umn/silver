grammar silver:compiler:extension:astconstruction;

imports silver:reflect;
imports silver:compiler:metatranslation;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  directAntiquoteProductions <-
    ["silver:compiler:extension:astconstruction:antiquoteAST"];
  patternAntiquoteProductions <-
    ["silver:compiler:extension:astconstruction:antiquotePatternAST"];
}
