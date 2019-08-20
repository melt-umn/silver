grammar silver:definition:concrete_syntax;

aspect production noWrapperNonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs ';'
{
  top.syntaxAst = [syntaxNonterminal(nonterminalType(fName, tl.types), nilSyntax())];  
}


