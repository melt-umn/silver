grammar silver:extension:doc:core;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.docs := [bodilessDclCommentItem("function", id.name, ns.pp, id.location.filename)];
}

concrete production docFunctionDcl
top::AGDcl ::= comment::DclComment 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.docs := [dclCommentItem("function", id.name, ns.pp, id.location.filename, comment)];

  forwards to functionDcl('function', id, ns, body, location=top.location);
}

concrete production noDocFunctionDcl
top::AGDcl ::= noDoc::NoDclComment_t 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.docs := [];
  forwards to functionDcl('function', id, ns, body, location=top.location);
}
