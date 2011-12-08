grammar silver:definition:concrete_syntax;

aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name botl::BracketedOptTypeList
{
  top.syntaxAst = [syntaxNonterminal(nonterminalTypeExp(fName, tl.types), nilSyntax())];  
}


