grammar silver:compiler:extension:doc:core;

nonterminal DocDclInfo;

abstract production docDclInfo
top::DocDclInfo ::= id::String loc::Location grammar_::String
{

}

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
}
