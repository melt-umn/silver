grammar silver:compiler:extension:doc:core;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.docs := [bodilessDclCommentItem("function", top.grammarName, id.name, ns.unparse, id.location)];

  top.docDcls := [pair(top.grammarName ++ ":" ++ id.name, docDclInfo(id.name, id.location, nameToPath(top.grammarName ++ ":" ++ id.name)))];
}

concrete production docFunctionDcl
top::AGDcl ::= comment::DocComment_t 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.docs := [dclCommentItem("function", top.grammarName, id.name, ns.unparse, id.location, comment)];

  top.docDcls := [pair(top.grammarName ++ ":" ++ id.name, docDclInfo(id.name, id.location, nameToPath(top.grammarName ++ ":" ++ id.name)))];

  forwards to functionDcl('function', id, ns, body, location=top.location);
}
