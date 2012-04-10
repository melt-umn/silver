grammar copper_features:mdatests:host;

parser doParse :: Root {
  copper_features:mdatests:host;
}

terminal A 'a';
terminal B 'b';

nonterminal Root;

concrete production aaa
top::Root ::= 'a' Root
{}

concrete production bbb
top::Root ::= 'b' Root
{}

concrete production noroot
top::Root ::=
{}

