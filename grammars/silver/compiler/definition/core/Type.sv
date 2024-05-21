grammar silver:compiler:definition:core;

-- LHS type gives this to 'application' for "foo(...)" calls.
synthesized attribute applicationDispatcher :: (Expr ::= Decorated! Expr  Decorated! AppExprs  Decorated! AnnoAppExprs);
-- LHS type gives this to 'access' for "foo.some" accesses.
-- (See DclInfo for the next step)
synthesized attribute accessHandler :: (Expr ::= Decorated! Expr  Decorated! QNameAttrOccur);

attribute applicationDispatcher, accessHandler occurs on Type;

aspect default production
top::Type ::=
{
  top.applicationDispatcher = errorApplication;
  top.accessHandler = errorAccessHandler;
}

aspect production appType
top::Type ::= c::Type a::Type
{
  top.applicationDispatcher = c.applicationDispatcher;
  top.accessHandler = c.accessHandler;
}

aspect production skolemType
top::Type ::= _
{
  top.accessHandler = undecoratedAccessHandler;
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

aspect production dispatchType
top::Type ::= _
{
  top.applicationDispatcher = dispatchApplication;
}

