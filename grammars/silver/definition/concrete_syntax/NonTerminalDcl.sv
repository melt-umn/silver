grammar silver:definition:concrete_syntax;

aspect production noWrapperNonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeExprs ';'
{
  top.syntaxAst = [syntaxNonterminal(nonterminalType(fName, tl.types), nilSyntax())];  
}


