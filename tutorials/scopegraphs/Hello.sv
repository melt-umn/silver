grammar scopegraphs;

imports silver:compiler:extension:scopegraphs2;

--

fun main IO<Integer> ::= largs::[String] = do {
  
  let cool::Boolean = thing().ok;

  return if cool then 0 else 1;

};

--

scope graph LMGraph with lex, var;

edge -[ lex::LMGraph ]->;
edge -[ var::LMGraph ]-> (String, Boolean);

--

synthesized attribute ok::Boolean;

nonterminal Thing with ok;

production thing
top::Thing ::= 
{
  new myScope::LMGraph;

  new myDcl::LMGraph var -> ("x", true);

  myScope -[ lex ]-> myScope;
  myScope -[ var ]-> myDcl;

  top.ok = true;
}
