grammar silver:extension:doc:core;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  local commentItem::CommentItem = bodilessDclCommentItem("function", id.name, ns.pp, id.location.filename);
  top.docs := [commentItem];

  local dclInfo::DclInfo = head(getValueDclAll(id.name, top.env));
  top.docDcls := [pair(dclInfo.fullName, functionDocDclInfoP(id.name, id.location.filename, "regFunctionDcl"{-nameToPath(dclInfo.fullName)-}))];
}

concrete production docFunctionDcl
top::AGDcl ::= comment::DclComment 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  local commentItem::CommentItem = dclCommentItem("function", id.name, ns.pp, id.location.filename, comment);
  top.docs := [commentItem];

  local dclInfo::DclInfo = head(getValueDclAll(id.name, top.env));
  top.docDcls := [pair(dclInfo.fullName, functionDocDclInfoP(id.name, id.location.filename, "docFunctionDcl"{-nameToPath(dclInfo.fullName)-}))];

  comment.docEnv = top.docEnv;

  forwards to functionDcl('function', id, ns, body, location=top.location);
}

concrete production noDocFunctionDcl
top::AGDcl ::= noDoc::NoDclComment_t 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.docs := [];
  top.docDcls := [];
  forwards to functionDcl('function', id, ns, body, location=top.location);
}

function nameToPath
String ::= name::String
{
  return substitute(":", "/", name);
}

