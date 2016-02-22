grammar silver:extension:doc:core;

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  top.docs := [toNoCommentMarkdown("aspect production", id.name, ns.pp)];
}

concrete production docAspectProductionDcl
top::AGDcl ::= comment::DocComment 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  top.docs := [toMarkdown("aspect production", id.name, ns.pp, comment)];

  forwards to aspectProductionDcl('aspect', 'production', id, ns, body, location=top.location);
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody
{
  top.docs := [toNoCommentMarkdown("aspect function", id.name, ns.pp)];
}

concrete production docAspectFunctionDcl
top::AGDcl ::= comment::DocComment 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody
{
  top.docs := [toMarkdown("aspect function", id.name, ns.pp, comment)];

  forwards to aspectFunctionDcl('aspect', 'function', id, ns, body, location=top.location);
}
