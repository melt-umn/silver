grammar tree;

nonterminal Item;
nonterminal ItemList;

terminal Id_t /[A-Za-z0-9]+/;

terminal Lp '(';
terminal Rp ')';

ignore terminal whitespace /[\t\ \n]+/;

concrete production iid
f::Item ::= Id_t
{
}

concrete production ilst
f::Item ::= '(' b::ItemList ')'
{
}

concrete production ilnil
b::ItemList ::=
{
}

concrete production ilcons
b::ItemList ::= f::Item b1::ItemList
{
}

parser parse :: Item {
  tree;
}

function main 
IOVal<Integer> ::= largs::[String] ioin::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  return ioval(print( hackUnparse( cast(parse(args), AnyType) ) ++ "\n",
                ioin), 0);
}
