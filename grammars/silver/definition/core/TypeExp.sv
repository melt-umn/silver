grammar silver:definition:core;

synthesized attribute applicationDispatcher :: (Expr ::= Decorated Expr  AppExprs);
synthesized attribute accessDispatcher :: (Expr ::= Decorated Expr  Dot_t  Decorated QName);

synthesized attribute lengthDispatcher :: (Expr ::= Decorated Expr);
synthesized attribute appendDispatcher :: (Expr ::= Decorated Expr  Decorated Expr);

-- Used for poor man's type classes
synthesized attribute instanceEq :: Boolean;
synthesized attribute instanceOrd :: Boolean;
synthesized attribute instanceNum :: Boolean;
synthesized attribute instanceConvertible :: Boolean;

attribute applicationDispatcher, accessDispatcher, lengthDispatcher, appendDispatcher,
          instanceEq, instanceOrd, instanceNum, instanceConvertible occurs on TypeExp;

aspect default production
top::TypeExp ::=
{
  top.applicationDispatcher = errorApplication;
  top.accessDispatcher = errorAccessDispatcher;
  top.instanceEq = false;
  top.instanceOrd = false;
  top.instanceNum = false;
  top.instanceConvertible = false;
  top.lengthDispatcher = unknownLength;
  top.appendDispatcher = errorPlusPlus;
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
  top.lengthDispatcher = stringLength;
  top.appendDispatcher = stringPlusPlus;
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
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
  top.applicationDispatcher = functionApplication;
}
