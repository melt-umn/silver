grammar scopegraphs;

imports silver:compiler:extension:scopegraphs2;

--

fun main IO<Integer> ::= largs::[String] = do {
  
  let ok::Boolean = thing().ok;

  return if ok then 0 else 1;

};

--

scope graph LMGraph with lex, var, mod, imp;

edge -[ lex::LMGraph ]->;
edge -[ var::LMGraph ]-> (String, Boolean);
edge -[ mod::LMGraph ]-> String;
edge -[ imp::LMGraph ]->;

--scope attribute LMGraph:lex s;

--

synthesized attribute ok::Boolean;
attribute ok occurs on Thing;

nonterminal Thing;

production thing
top::Thing ::= 
{
  new myScope::LMGraph;

  new myDcl::LMGraph var -> ("x", true);

  myScope -[ lex ]-> myScope;
  myScope -[ var ]-> myDcl;

  top.ok = true;
}
