grammar silver:extension:astconstruction;

imports silver:reflect;
imports silver:metatranslation;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  directAntiquoteProductions <-
    ["silver:extension:astconstruction:antiquoteAST"];
  patternAntiquoteProductions <-
    ["silver:extension:astconstruction:antiquotePatternAST"];
}
