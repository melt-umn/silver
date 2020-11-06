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
          instanceEq, instanceOrd, instanceNum, instanceConvertible occurs on Type;

aspect default production
top::Type ::=
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

aspect production errorType
top::Type ::=
{
  -- Allow these, to suppress raising additional unnecessary errors.
  top.instanceEq = true;
  top.instanceOrd = true;
  top.instanceNum = true;
  top.instanceConvertible = true;
}

aspect production intType
top::Type ::=
{
  top.instanceEq = true;
  top.instanceOrd = true;
  top.instanceNum = true;
  top.instanceConvertible = true;
}

aspect production boolType
top::Type ::=
{
  top.instanceEq = true;
  top.instanceConvertible = true;
}

aspect production floatType
top::Type ::=
{
  top.instanceEq = true;
  top.instanceOrd = true;
  top.instanceNum = true;
  top.instanceConvertible = true;
}

aspect production stringType
top::Type ::=
{
  top.instanceEq = true;
  top.instanceOrd = true;
  top.instanceConvertible = true;
  top.lengthDispatcher = stringLength(_, location=_);
  top.appendDispatcher = stringPlusPlus(_, _, location=_);
}

aspect production terminalIdType
top::Type ::=
{
  top.instanceEq = true;
  top.instanceOrd = true;
}

aspect production nonterminalType
top::Type ::= fn::String params::[Type]
{
  top.accessHandler = undecoratedAccessHandler(_, _, location=_);
}

aspect production terminalType
top::Type ::= fn::String
{
  top.accessHandler = terminalAccessHandler(_, _, location=_);
}

aspect production decoratedType
top::Type ::= te::Type
{
  top.accessHandler = decoratedAccessHandler(_, _, location=_);
}

aspect production functionType
top::Type ::= out::Type params::[Type] namedParams::[NamedArgType]
{
  -- TODO: We don't seem to use this. Perhaps we should remove it?
  top.applicationDispatcher = functionApplication(_, _, _, location=_);
}

