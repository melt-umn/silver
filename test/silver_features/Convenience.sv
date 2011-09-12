
nonterminal ConcreteProductions with fst<ATerm>;

terminal ATerm //;

-- Just make sure the syntax is as expected.

concrete productions top::ConcreteProductions
(oneElem)  | s::ATerm { top.fst = s; }
(consElem) | s::ATerm ss::ConcreteProductions { top.fst = ss.fst; } action { print "hello"; }
(moreElem) | ss::ConcreteProductions precedence = 7 { top.fst = ss.fst; }
           | s::ATerm { top.fst = s; }

