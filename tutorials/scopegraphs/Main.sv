grammar scopegraphs;

imports silver:compiler:extension:scopegraphs2;

--

synthesized attribute allScopes::[MyScope];

--

-- should gen:
global allLabs::[Label<{lex, var, imp, mod}>] = 
  [label_lex(), label_var(), label_imp(), label_mod()];

fun main IO<Integer> ::= args::[String] = do {

  {-
    let 
      x = 1
      y = 2
    in
      let
        a = 3
        b = 4
        c = 5
      in
        x

  -}
  let root::Root = 
    root(
      letExpr(
        bindListCons(
          "x",
          intExpr(1),
          bindListOne(
            "y",
            intExpr(2)
          )
        ),
        letExpr(
          bindListCons(
            "a",
            intExpr(3),
            bindListCons(
              "b",
              intExpr(4),
              bindListOne(
                "c",
                intExpr(5)
              )
            )
          ),
          varExpr("x")
        )
      )
    );

  let vizStr::String = vizStr(allLabs, root.allScopes);
  system("echo '" ++ vizStr ++ "' | dot -Tsvg > sg.svg");
  return 0;
};

--

scope MyScope edges { lex, var, imp, mod };

--

nonterminal Root with allScopes;

production root
top::Root ::= e::Expr
{
  mkscope glob;

  inhscope glob as e.s;

  top.allScopes = e.allScopes;
}

--

nonterminal Expr with allScopes;
scope attribute s occurs on Expr, BindList;

production letExpr
top::Expr ::= binds::BindList e::Expr
{
  mkscope letScope;
  letScope -[ lex ]-> top.s;

  inhscope letScope as binds.s;
  inhscope letScope as e.s;

  top.allScopes = letScope::(binds.allScopes ++ e.allScopes);
}

production varExpr
top::Expr ::= name::String
{ top.allScopes = []; }

production intExpr
top::Expr ::= i::Integer
{ top.allScopes = []; }

--

nonterminal BindList with allScopes;

production bindListCons
top::BindList ::= name::String e::Expr rest::BindList
{
  mkscope bndScope -> name |-> e;
  mkscope seqScope;

  seqScope -[ var ]-> bndScope;
  seqScope -[ lex ]-> top.s;

  inhscope seqScope as rest.s;

  top.allScopes = bndScope::seqScope::rest.allScopes;
}

production bindListOne
top::BindList ::= name::String e::Expr
{
  mkscope bndScope -> name |-> e;
  mkscope seqScope;

  seqScope -[ var ]-> bndScope;
  seqScope -[ lex ]-> top.s;

  top.allScopes = [seqScope, bndScope];
}