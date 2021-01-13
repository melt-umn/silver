grammar silver:compiler:extension:doc:core;

import silver:compiler:definition:concrete_syntax;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.docs := [bodilessDclCommentItem("abstract production", top.grammarName, id.name, ns.unparse, id.location)];

  top.docDcls := [pair(top.grammarName ++ ":" ++ id.name, docDclInfo(id.name, id.location, top.grammarName ++ ":" ++ id.name))];
}

concrete production docProductionDecl
top::AGDcl ::= comment::DocComment_t 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.docs := [dclCommentItem("abstract production", top.grammarName, id.name, ns.unparse, id.location, comment)];

  top.docDcls := [pair(top.grammarName ++ ":" ++ id.name, docDclInfo(id.name, id.location, top.grammarName ++ ":" ++ id.name))];

  forwards to productionDcl('abstract', 'production', id, ns, body, location=top.location);
}

aspect production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  top.docs := [bodilessDclCommentItem("concrete production", top.grammarName, id.name, ns.unparse, id.location)];

  top.docDcls := [pair(top.grammarName ++ ":" ++ id.name, docDclInfo(id.name, id.location, top.grammarName ++ ":" ++ id.name))];
}

concrete production docConcreteProductionDcl
top::AGDcl ::= comment::DocComment_t 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  top.docs := [dclCommentItem("concrete production", top.grammarName, id.name, ns.unparse, id.location, comment)];

  top.docDcls := [pair(top.grammarName ++ ":" ++ id.name, docDclInfo(id.name, id.location, top.grammarName ++ ":" ++ id.name))];

  forwards to concreteProductionDcl('concrete', 'production', id, ns, pm, body, location=top.location);
}
