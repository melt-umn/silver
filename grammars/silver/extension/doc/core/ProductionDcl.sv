grammar silver:extension:doc:core;

import silver:util;
import silver:definition:concrete_syntax;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.docs := [bodilessDclCommentItem("abstract production", id.name, ns.pp, id.location.filename)];
}

concrete production docProductionDecl
top::AGDcl ::= comment::DclComment 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.docs := [dclCommentItem("abstract production", id.name, ns.pp, id.location.filename, comment)];
  forwards to productionDcl('abstract', 'production', id, ns, body, location=top.location);
}

concrete production noDocProductionDecl
top::AGDcl ::= noDoc::NoDclComment_t 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.docs := [];
  forwards to productionDcl('abstract', 'production', id, ns, body, location=top.location);
}

aspect production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  top.docs := [bodilessDclCommentItem("concrete production", id.name, ns.pp, id.location.filename)];
}

concrete production docConcreteProductionDcl
top::AGDcl ::= comment::DclComment 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  top.docs := [dclCommentItem("concrete production", id.name, ns.pp, id.location.filename, comment)];
  forwards to concreteProductionDcl('concrete', 'production', id, ns, pm, body, location=top.location);
}

concrete production noDocConcreteProductionDcl
top::AGDcl ::= noDoc::NoDclComment_t 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  top.docs := [];
  forwards to concreteProductionDcl('concrete', 'production', id, ns, pm, body, location=top.location);
}

