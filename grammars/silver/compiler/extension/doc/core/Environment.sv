grammar silver:compiler:extension:doc:core;

nonterminal DocDclInfo;

synthesized attribute scopedName::String occurs on DocDclInfo;

abstract production docDclInfo
top::DocDclInfo ::= id::String loc::Location grammar_::String
{
	top.scopedName = grammar_ ++ ":" ++ id;
}

