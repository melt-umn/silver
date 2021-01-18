grammar silver:compiler:extension:doc:core;

synthesized attribute docUnparse::String occurs on AGDcl;


aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
	top.docUnparse = "`function " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`) {#" ++ id.name ++ "}";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
	top.docUnparse = "`abstract production " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`) {#" ++ id.name ++ "}";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
}
