grammar silver:definition:concrete_syntax;

aspect production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  top.syntaxAst = [syntaxNonterminal(nonterminalTypeExp(fName, tl.types), nilSyntax())];  
}


