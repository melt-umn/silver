grammar silver:extension:doc:core;

import silver:util;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.docs := [s"""production ${id.name}"""];
}


