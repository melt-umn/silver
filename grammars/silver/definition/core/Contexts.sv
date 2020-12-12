grammar silver:definition:core;

function contextErrors
[Message] ::= env::Decorated Env loc::Location source::String c::Context
{
  c.env = env;
  c.contextLoc = loc;
  c.contextSource = source;
  return c.contextErrors;
}

synthesized attribute contextErrors::[Message] occurs on Context;
inherited attribute contextLoc::Location occurs on Context;
inherited attribute contextSource::String occurs on Context;

aspect production instContext
top::Context ::= cls::String t::Type
{
  -- Duplicates are checked at the instance declaration
  top.contextErrors =
    if null(top.resolved)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(top)} (arising from ${top.contextSource})")]
    else flatMap(contextErrors(top.env, top.contextLoc, top.contextSource, _), requiredContexts);
}
