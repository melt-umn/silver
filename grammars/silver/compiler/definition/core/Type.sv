grammar silver:compiler:definition:core;

-- LHS type gives this to 'application' for "foo(...)" calls.
synthesized attribute applicationDispatcher :: (Expr ::= Decorated Expr  Decorated AppExprs  Decorated AnnoAppExprs  Location);
-- LHS type gives this to 'access' for "foo.some" accesses.
-- (See DclInfo for the next step)
synthesized attribute accessHandler :: (Expr ::= Decorated Expr  Decorated QNameAttrOccur  Location);

synthesized attribute lengthDispatcher :: (Expr ::= Decorated Expr  Location);
synthesized attribute appendDispatcher :: (Expr ::= Decorated Expr  Decorated Expr  Location);

-- Used for poor man's type classes
-- TODO: Finish removing these and replace with real type classes
synthesized attribute instanceNum :: Boolean;
synthesized attribute instanceConvertible :: Boolean;

attribute applicationDispatcher, accessHandler, lengthDispatcher,
          instanceNum, instanceConvertible occurs on Type;

aspect default production
top::Type ::=
{
  top.applicationDispatcher = errorApplication(_, _, _, location=_);
  top.accessHandler = errorAccessHandler(_, _, location=_);
  top.instanceNum = false;
  top.instanceConvertible = false;
  top.lengthDispatcher = errorLength(_, location=_);
}

aspect production errorType
top::Type ::=
{
  -- Allow these, to suppress raising additional unnecessary errors.
  top.instanceNum = true;
  top.instanceConvertible = true;
}

aspect production appType
top::Type ::= c::Type a::Type
{
  top.applicationDispatcher = c.applicationDispatcher;
  top.accessHandler = c.accessHandler;
  top.instanceNum = c.instanceNum;
  top.instanceConvertible = c.instanceConvertible;
  top.lengthDispatcher = c.lengthDispatcher;
}

aspect production skolemType
top::Type ::= _
{
  top.accessHandler = undecoratedAccessHandler(_, _, location=_);
}

aspect production intType
top::Type ::=
{
  top.instanceNum = true;
  top.instanceConvertible = true;
}

aspect production boolType
top::Type ::=
{
  top.instanceConvertible = true;
}

aspect production floatType
top::Type ::=
{
  top.instanceNum = true;
  top.instanceConvertible = true;
}

aspect production stringType
top::Type ::=
{
  top.instanceConvertible = true;
  top.lengthDispatcher = stringLength(_, location=_);
}

aspect production nonterminalType
top::Type ::= fn::String _ _
{
  top.accessHandler = undecoratedAccessHandler(_, _, location=_);
}

aspect production terminalType
top::Type ::= fn::String
{
  top.accessHandler = terminalAccessHandler(_, _, location=_);
}

aspect production decoratedType
top::Type ::= te::Type _
{
  top.accessHandler = decoratedAccessHandler(_, _, location=_);
}

aspect production functionType
top::Type ::= _ _
{
  top.applicationDispatcher = functionApplication(_, _, _, location=_);
}

