grammar silver:compiler:definition:core;

-- LHS type gives this to 'application' for "foo(...)" calls.
synthesized attribute applicationDispatcher :: (Expr ::= Decorated! Expr  Decorated! AppExprs  Decorated! AnnoAppExprs  Location);
-- LHS type gives this to 'access' for "foo.some" accesses.
-- (See DclInfo for the next step)
synthesized attribute accessHandler :: (Expr ::= Decorated! Expr  Decorated! QNameAttrOccur  Location);

-- Used for poor man's type classes
-- TODO: Finish removing these and replace with real type classes
synthesized attribute instanceNum :: Boolean;

attribute applicationDispatcher, accessHandler, instanceNum occurs on Type;

aspect default production
top::Type ::=
{
  top.applicationDispatcher = errorApplication(_, _, _, location=_);
  top.accessHandler = errorAccessHandler(_, _, location=_);
  top.instanceNum = false;
}

aspect production errorType
top::Type ::=
{
  -- Allow these, to suppress raising additional unnecessary errors.
  top.instanceNum = true;
}

aspect production appType
top::Type ::= c::Type a::Type
{
  top.applicationDispatcher = c.applicationDispatcher;
  top.accessHandler = c.accessHandler;
  top.instanceNum = c.instanceNum;
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
}

aspect production floatType
top::Type ::=
{
  top.instanceNum = true;
}

aspect production nonterminalType
top::Type ::= fn::String _ data::Boolean _
{
  top.accessHandler =
    if data
    then dataAccessHandler(_, _, location=_)
    else undecoratedAccessHandler(_, _, location=_);
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

aspect production uniqueDecoratedType
top::Type ::= te::Type _
{
  top.accessHandler = decoratedAccessHandler(_, _, location=_);
}

aspect production functionType
top::Type ::= _ _
{
  top.applicationDispatcher = functionApplication(_, _, _, location=_);
}

