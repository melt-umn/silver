grammar silver:definition:core;

synthesized attribute doDecorate :: Boolean;
synthesized attribute applicationDispatcher :: Production (Expr ::= Decorated Expr Exprs);
synthesized attribute accessDispatcher :: Production (Expr ::= Decorated Expr Dot_t Decorated QName);

-- Used for poor man's type classes
synthesized attribute instanceEq :: Boolean;
synthesized attribute instanceOrd :: Boolean;
synthesized attribute instanceNum :: Boolean;
synthesized attribute instanceConvertible :: Boolean;

attribute doDecorate, applicationDispatcher, accessDispatcher, instanceEq, instanceOrd, instanceNum occurs on TypeExp;

aspect production defaultTypeExp
top::TypeExp ::=
{
  top.doDecorate = false;
  top.applicationDispatcher = errorApplicationDispatcher;
  top.accessDispatcher = errorAccessDispatcher;
  top.instanceEq = false;
  top.instanceOrd = false;
  top.instanceNum = false;
  top.instanceConvertible = false;
}

aspect production intTypeExp
top::TypeExp ::=
{
  top.instanceEq = true;
  top.instanceOrd = true;
  top.instanceNum = true;
  top.instanceConvertible = true;
}

aspect production boolTypeExp
top::TypeExp ::=
{
  top.instanceEq = true;
}

aspect production floatTypeExp
top::TypeExp ::=
{
  top.instanceEq = true;
  top.instanceOrd = true;
  top.instanceNum = true;
  top.instanceConvertible = true;
}

aspect production stringTypeExp
top::TypeExp ::=
{
  top.instanceEq = true;
  top.instanceOrd = true;
  top.instanceConvertible = true;
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
