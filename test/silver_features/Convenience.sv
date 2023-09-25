
import silver:langutil;

nonterminal ConcreteProductions with ast<ATerm>;

terminal ATerm '';

-- Just make sure the syntax is as expected.

concrete productions top::ConcreteProductions
(oneElem)  | s::ATerm { top.ast = s; }
(consElem) | s::ATerm ss::ConcreteProductions { top.ast = ss.ast; } action { print "hello"; }
(moreElem) | ss::ConcreteProductions precedence = 7 { top.ast = ss.ast; }
           | s::ATerm { top.ast = s; }


wrongCode "$9" {
  abstract production dummyDontMatter
  top::ConcreteProductions ::=
  {
    top.ast = $9;
  }
}
