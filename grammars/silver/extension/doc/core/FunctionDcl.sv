grammar silver:extension:doc:core;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.docs := [bodilessCommentItem("function", id.name, ns.pp, id.location.filename)];
}

concrete production docFunctionDcl
top::AGDcl ::= comment::DocComment 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.docs := [commentItem("function", id.name, ns.pp, id.location.filename, comment)];

  forwards to functionDcl('function', id, ns, body, location=top.location);
}

concrete production noDocFunctionDcl
top::AGDcl ::= noDoc::NoDocComment_t 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.docs := [];
  forwards to functionDcl('function', id, ns, body, location=top.location);
}
