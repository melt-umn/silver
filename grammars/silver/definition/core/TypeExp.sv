grammar silver:definition:core;

-- LHS type gives this to 'application' for "foo(...)" calls.
synthesized attribute applicationDispatcher :: (Expr ::= Decorated Expr  AppExprs  AnnoAppExprs  Location);
-- LHS type gives this to 'access' for "foo.some" accesses.
-- (See DclInfo for the next step)
synthesized attribute accessHandler :: (Expr ::= Decorated Expr  Decorated QNameAttrOccur  Location);

synthesized attribute lengthDispatcher :: (Expr ::= Decorated Expr  Location);
synthesized attribute appendDispatcher :: (Expr ::= Decorated Expr  Decorated Expr  Location);

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
  top.applicationDispatcher = errorApplication(_, _, _, location=_);
  top.accessHandler = errorAccessHandler(_, _, location=_);
  top.instanceEq = false;
  top.instanceOrd = false;
  top.instanceNum = false;
  top.instanceConvertible = false;
  top.lengthDispatcher = errorLength(_, location=_);
  top.appendDispatcher = errorPlusPlus(_, _, location=_);
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
  top.lengthDispatcher = stringLength(_, location=_);
  top.appendDispatcher = stringPlusPlus(_, _, location=_);
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.accessHandler = undecoratedAccessHandler(_, _, location=_);
}

aspect production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.accessHandler = terminalAccessHandler(_, _, location=_);
}

aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.accessHandler = decoratedAccessHandler(_, _, location=_);
}

aspect production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp] namedParams::[NamedArgType]
{
  -- TODO: We don't seem to use this. Perhaps we should remove it?
  top.applicationDispatcher = functionApplication(_, _, _, location=_);
}

