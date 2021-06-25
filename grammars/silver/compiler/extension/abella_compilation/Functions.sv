grammar silver:compiler:extension:abella_compilation;


aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
  top.localAttrs := [];
  top.localAttrDefs := [];
  top.attrEqInfo := [];
  --
  body.encodingEnv = ns.encodingEnv_up;
  body.top = error("Cannot need top in a function");
  body.treeTerm = error("Cannot need root tree in a function");
  body.nodetreeTerm = error("Cannot need root node tree in a function");
  --Put return statement first
  local sorted::[(Boolean, [([Metaterm], Term)])] =
        sortBy(\ p1::(Boolean, [([Metaterm], Term)])
                 p2::(Boolean, [([Metaterm], Term)]) -> p1.1,
               body.funRelInfo);
  local ret::[([Metaterm], Term)] = head(sorted).2;
  local rest::[ [[Metaterm]] ] =
        if null(sorted)
        then error("Did not find return for function " ++ id.name)
        else --"return value" for locals is not anything relevant
             map(\ p::(Boolean, [([Metaterm], Term)]) ->
                   map(\ l::([Metaterm], Term) -> l.1, p.2),
                 tail(sorted));
  local allRest::[[Metaterm]] = combineBodies(rest);
  --Put the return statement together with all definitions for locals
  local combined::[(Term, [[Metaterm]])] =
        map(\ p::([Metaterm], Term) ->
              ( buildApplication(nameTerm(nameToFun(id.name)),
                   ns.args ++ [p.2]),
                if null(allRest)
                then [p.1]
                else map(\ l::[Metaterm] -> p.1 ++ l, allRest) ),
            ret);
  --Unify equalities away
  local unified::[(Term, [Metaterm])] =
        flatMap(\ p::(Term, [[Metaterm]]) -> cleanFunction(p.1, p.2),
                combined);
  local defClauses::[DefClause] =
        map(\ p::(Term, [Metaterm]) ->
              let filled::(Term, [Metaterm]) =
                  fillVars(p.1,
                     [foldl(andMetaterm(_, _), head(p.2),
                            tail(p.2))])
              in
                if null(p.2)
                then factClause(termMetaterm(filled.1))
                else ruleClause(termMetaterm(filled.1),
                        head(filled.2))
              end,
            unified);
  top.funRelClauses <- [(id.name, ns.functionType, defClauses)];
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  top.localAttrs :=
      error("Abella encoding cannot handle aspect functions");
  top.localAttrDefs := [];
  top.attrEqInfo := [];
}

aspect production functionDclFFI
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}'
{
  top.localAttrs :=
      error("Abella encoding cannot handle foreign functions");
  top.localAttrDefs := [];
  top.attrEqInfo := [];
}



attribute funRelInfo occurs on
   ProductionStmt, ProductionStmts, ProductionBody;

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.funRelInfo <- [(true, e.encodedExpr)];
}

