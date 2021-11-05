grammar flow;

nonterminal FSExpr with env1, env2;

production fse
top::FSExpr ::= 
{}

flowtype FSExpr = forward {env1}, decorate {forward, env2};

wrongCode "Declaration of global missingRes with type Decorated flow:FSExpr2 with {flow:env1, :env2} has initialization expression with type Decorated flow:FSExpr with {}" {
  global missingRes::Decorated FSExpr2 = decorate fse() with {};
}

nonterminal FSExpr2 with env1, env2;

wrongCode "circularity in flow specification for decorate on flow:FSExpr2" {
  flowtype FSExpr2 = decorate {decorate};
}

{-  No way to actually test this, I think, since wrongCode doesn't bubble up flowDefs
wrongCode "circularity in flow specification for forward on flow:FSExpr2" {
  flowtype FSExpr2 = decorate {forward}, forward {decorate};
}
-}
