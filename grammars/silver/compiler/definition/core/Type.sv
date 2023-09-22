grammar silver:compiler:definition:core;

-- LHS type gives this to 'application' for "foo(...)" calls.
synthesized attribute applicationDispatcher :: (Expr ::= Decorated! Expr  Decorated! AppExprs  Decorated! AnnoAppExprs);
-- LHS type gives this to 'access' for "foo.some" accesses.
-- (See DclInfo for the next step)
synthesized attribute accessHandler :: (Expr ::= Decorated! Expr  Decorated! QNameAttrOccur);

-- Used for poor man's type classes
-- TODO: Finish removing these and replace with real type classes
synthesized attribute instanceNum :: Boolean;

attribute applicationDispatcher, accessHandler, instanceNum occurs on Type;

aspect default production
top::Type ::=
{
  top.applicationDispatcher = errorApplication;
  top.accessHandler = errorAccessHandler;
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
  top.accessHandler = undecoratedAccessHandler;
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
    then dataAccessHandler(_, _)
    else undecoratedAccessHandler(_, _);
}

aspect production terminalType
top::Type ::= fn::String
{
  top.accessHandler = terminalAccessHandler;
}

aspect production decoratedType
top::Type ::= te::Type _
{
  top.accessHandler = decoratedAccessHandler;
}

aspect production uniqueDecoratedType
top::Type ::= te::Type _
{
  top.accessHandler = decoratedAccessHandler;
}

aspect production functionType
top::Type ::= _ _
{
  top.applicationDispatcher = functionApplication;
}

