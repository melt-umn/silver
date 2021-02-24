imports doctest:nonsplit:childsplit;

@{-
  - Docs for A/foo
  -}
function foo
Integer ::= {return 0;}

@{- Aspect on foo -}
aspect function foo
Integer ::= {}


synthesized attribute someAttr :: Integer;
inherited attribute someInhAttr<a> :: a;
nonterminal SomeNT with someAttr;

production someAbstractProd
top::SomeNT ::=
{
	
}

concrete production someConcreteProd
top::SomeNT ::= Path_t
{
	
}

aspect production someAbstractProd
top::SomeNT ::=
{
	
}

terminal Path_t /[a-zA-Z0-9_\-\/\.]+/;

lexer class BLOCK_KWD;

parser someParser::SomeNT {
	doctest:nonsplit;
}