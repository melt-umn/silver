grammar silver:definition:core;

synthesized attribute doDecorate :: Boolean;
synthesized attribute applicationDispatcher :: Production (Expr ::= Decorated Expr Exprs);
synthesized attribute accessDispatcher :: Production (Expr ::= Decorated Expr Dot_t Decorated QName);

attribute doDecorate, applicationDispatcher, accessDispatcher occurs on TypeExp;

aspect production defaultTypeExp
top::TypeExp ::=
{
  top.doDecorate = false;
  top.applicationDispatcher = errorApplicationDispatcher;
  top.accessDispatcher = errorAccessDispatcher;
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.doDecorate = true;
  top.accessDispatcher = undecoratedAccessDispatcher;
}

aspect production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.accessDispatcher = terminalAccessDispatcher;
}

aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.accessDispatcher = decoratedAccessDispatcher;
}

aspect production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.applicationDispatcher = functionApplicationDispatcher;
}

aspect production productionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.applicationDispatcher = productionApplicationDispatcher;
}
