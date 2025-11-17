grammar scopegraphs;

imports silver:compiler:extension:scopegraphs2;

--

synthesized attribute allScopes::[MyScope];

--

fun main IO<Integer> ::= args::[String] = do {

  let root::Root = root(child());

  let vizStr::String = vizStr(allLabs, root.allScopes);

  system("echo '" ++ vizStr ++ "' | dot -Tsvg > sg.svg");

  return 0;

};

--

scope MyScope edges { lex, var, imp, mod };

--

nonterminal Root with allScopes;

production root
top::Root ::= child::Child
{

  mkscope s1;
  mkscope s2;
  mkscope s3 -> "foo" : [1, 2, 3];

  s1 -[ lex ]-> s2;
  s2 -[ var ]-> s3;

  child.s = s2;

  top.allScopes = [s1, s2, s3];

}


--

-- todo, scope attribute
inherited attribute s::MyScope;

nonterminal Child with s;

production child
top::Child ::=
{

}