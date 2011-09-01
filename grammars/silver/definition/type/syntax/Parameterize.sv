grammar silver:definition:type:syntax;


synthesized attribute typeHandler :: Production(Type ::= Decorated QNameUpper BracketedOptTypeList) occurs on TypeExp;

aspect production defaultTypeExp
top::TypeExp ::=
{
  top.typeHandler = nominalTypeNormal;
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.typeHandler = nominalTypeNonterminal;
}

