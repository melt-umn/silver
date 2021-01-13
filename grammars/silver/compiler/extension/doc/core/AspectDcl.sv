grammar silver:compiler:extension:doc:core;

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  top.docs := [ bodilessDclCommentItem("aspect production", top.grammarName, id.name, ns.unparse, 
                                       id.location)];
}

concrete production docAspectProductionDcl
top::AGDcl ::= comment::DocComment_t 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  top.docs := [ dclCommentItem("aspect production", top.grammarName, id.name, ns.unparse, 
                               id.location, comment)];

  forwards to aspectProductionDcl('aspect', 'production', id, ns, body, location=top.location);
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody
{
  top.docs := [bodilessDclCommentItem("aspect function", top.grammarName, id.name, ns.unparse, id.location)];
}

concrete production docAspectFunctionDcl
top::AGDcl ::= comment::DocComment_t 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody
{
  top.docs := [dclCommentItem("aspect function", top.grammarName, id.name, ns.unparse, id.location, comment)];

  forwards to aspectFunctionDcl('aspect', 'function', id, ns, body, location=top.location);
}
