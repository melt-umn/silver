synthesized attribute convValue<a>::a;
nonterminal ConcreteProductions with convValue<ATerm>;

terminal ATerm '';

-- Just make sure the syntax is as expected.

concrete productions top::ConcreteProductions
(oneElem)  | s::ATerm { top.convValue = s; }
(consElem) | s::ATerm ss::ConcreteProductions { top.convValue = ss.convValue; } action { print "hello"; }
(moreElem) | ss::ConcreteProductions precedence = 7 { top.convValue = ss.convValue; }
           | s::ATerm { top.convValue = s; }


wrongCode "$9" {
  abstract production dummyDontMatter
  top::ConcreteProductions ::=
  {
    top.convValue = $9;
  }
}
