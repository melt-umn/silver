grammar silver:definition:core;

-- LHS type gives this to 'application' for "foo(...)" calls.
synthesized attribute applicationDispatcher :: (Expr ::= Decorated Expr  AppExprs  AnnoAppExprs);
-- LHS type gives this to 'access' for "foo.some" accesses.
-- (See DclInfo for the next step)
synthesized attribute accessHandler :: (Expr ::= Decorated Expr  Dot_t  Decorated QName);

synthesized attribute lengthDispatcher :: (Expr ::= Decorated Expr);
synthesized attribute appendDispatcher :: (Expr ::= Decorated Expr  Decorated Expr);

-- Used for poor man's type classes
synthesized attribute instanceEq :: Boolean;
synthesized attribute instanceOrd :: Boolean;
synthesized attribute instanceNum :: Boolean;
synthesized attribute instanceConvertible :: Boolean;

attribute applicationDispatcher, accessHandler, lengthDispatcher, appendDispatcher,
          instanceEq, instanceOrd, instanceNum, instanceConvertible occurs on TypeExp;

aspect default production
top::TypeExp ::=
{
  top.applicationDispatcher = errorApplication;
  top.accessHandler = errorAccessHandler;
  top.instanceEq = false;
  top.instanceOrd = false;
  top.instanceNum = false;
  top.instanceConvertible = false;
  top.lengthDispatcher = errorLength;
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
  top.accessHandler = undecoratedAccessHandler;
}

aspect production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.accessHandler = terminalAccessHandler;
}

aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.accessHandler = decoratedAccessHandler;
}

aspect production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp] namedParams::[NamedArgType]
{
  top.applicationDispatcher = functionApplication;
}
