grammar scopegraphs;

imports silver:compiler:extension:scopegraphs2;

--

fun main IO<Integer> ::= largs::[String] = pure(0);

--

scope graph LMGraph with lex, var;

edge -[ lex::LMGraph ]->;

edge -[ var::LMGraph ]-> (String, Boolean);

--

nonterminal Thing;

production thing
top::Thing ::= 
{
  new myScope::LMGraph;

  new myDcl::LMGraph var -> ("x", true);
}