grammar silver:extension:doc:core;

import silver:util;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.docs := [toNoCommentMarkdown("abstract production", id.name, ns.pp)];
}

concrete production docProductionDecl
top::AGDcl ::= comment::DocComment 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.docs := [toMarkdown("abstract production", id.name, ns.pp, comment)];

  forwards to productionDcl('abstract', 'production', id, ns, body, location=top.location);
}

