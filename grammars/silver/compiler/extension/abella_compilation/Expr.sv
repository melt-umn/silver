grammar silver:compiler:extension:abella_compilation;


attribute
   encodedExpr, encodingEnv, top
occurs on Expr;



aspect default production
top::Expr ::=
{
  top.encodedExpr = error("Default production");
}



aspect production errorExpr
top::Expr ::= e::[Message]
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
}

aspect production errorReference
top::Expr ::= msg::[Message]  q::Decorated QName
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [([],
      case findAssociated(q.name, top.encodingEnv) of
      | just((tree, node)) when isNonterminal(top.typerep) ->
        buildApplication(
           nameTerm(pairConstructorName),
           [tree, node])
      | just((tree, node)) -> tree
      | nothing() -> error("Child must exist")
      end)];
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [([],
      case findAssociated(q.name, top.encodingEnv) of
      | just((treename, nodename)) ->
        buildApplication(
           nameTerm(pairConstructorName),
           [treename, nodename])
      | nothing() -> error("LHS must exist")
      end)];
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
  local localname::String = capitalize(shortestName(q.name));
  local localnode::String = "Node";
  local localTerm::Term =
        case top.typerep of
        | nonterminalType(_, _, _) ->
          buildApplication(nameTerm(pairConstructorName),
             [varTerm(localname, genInt()),
              varTerm(localnode, genInt())])
        | _ -> varTerm(localname, genInt())
        end;
  top.encodedExpr =
      [([ termMetaterm(
             buildApplication(
                nameTerm(localAccessRelationName(top.top.3,
                            shortestName(q.name), top.top.4)),
                [top.top.1, top.top.2,
                 buildApplication(nameTerm(attributeExistsName),
                                  [localTerm])])) ], new(localTerm))];
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = error("I don't know what forwardReference is");
}

aspect production productionReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [([], nameTerm(nameToProd(shortestName(q.name))))];
}

aspect production functionReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [([], nameTerm(nameToFun(shortestName(q.name))))];
}

aspect production classMemberReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = error("classMemberReference not done yet");
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = error("globalValueReference not done yet");
}

aspect production errorApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
}

aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  local attribute newe::Expr = new(e);
  newe.grammarName = top.grammarName;
  newe.config = top.config;
  newe.env = top.env;
  newe.flowEnv = top.flowEnv;
  newe.frame = top.frame;
  newe.isRoot = top.isRoot;
  newe.originRules = top.originRules;
  newe.compiledGrammars = top.compiledGrammars;
  newe.downSubst = top.downSubst;
  newe.finalSubst = top.finalSubst;
  newe.encodingEnv = top.encodingEnv;
  newe.top = top.top;
  local attribute newes::AppExprs = new(es);
  newes.grammarName = top.grammarName;
  newes.config = top.config;
  newes.env = top.env;
  newes.flowEnv = top.flowEnv;
  newes.frame = top.frame;
  newes.isRoot = top.isRoot;
  newes.originRules = top.originRules;
  newes.compiledGrammars = top.compiledGrammars;
  newes.appExprTypereps = es.appExprTypereps;
  newes.downSubst = newe.upSubst;
  newes.finalSubst = top.finalSubst;
  newes.encodingEnv = top.encodingEnv;
  newes.top = top.top;
  --
  local resultName::String = "FunResult";
  local resultTerm::Term = varTerm(resultName, genInt());
  local args::[([Metaterm], [Term])] =
        foldr(\ l::[([Metaterm], Term)] rest::[([Metaterm], [Term])] ->
                [ foldr(\ p::([Metaterm], Term)
                          rest::([Metaterm], [Term]) ->
                          (p.1 ++ rest.1, p.2::rest.2),
                        ([], []), l) ],
              [], newes.encodedArgs);
  top.encodedExpr =
      foldr(\ ep::([Metaterm], Term) rest::[([Metaterm], Term)] ->
              foldr(\ argp::([Metaterm], [Term])
                      rest::[([Metaterm], Term)] ->
                      ( [termMetaterm(
                            buildApplication(ep.2,
                               argp.2 ++ [resultTerm]))],
                        new(resultTerm) )::rest,
                    rest, args),
            [], newe.encodedExpr);
}

aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.encodedExpr = error("partialApplication not done yet");
}

aspect production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  top.encodedExpr = error("I don't know what noteAttachment is");
}

aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.encodedExpr = error("attributeSection not done yet");
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  local treeTy::AbellaType = e.typerep.abellaType;
  local fwdName::String = "Fwd";
  local fwdNode::String = "Node";
  local fwdTerm::Term =
        buildApplication(nameTerm(pairConstructorName),
           [varTerm(fwdName, genInt()), varTerm(fwdNode, genInt())]);
  top.encodedExpr =
      map(\ ep::([Metaterm], Term) ->
            case ep.2 of
            | applicationTerm(nameTerm("$pair_c"),
                 consTermList(tree, singleTermList(node))) ->
              ( ep.1 ++
                [ termMetaterm(
                     buildApplication(
                        nameTerm(accessRelationName(treeTy, "forward")),
                        [tree, node,
                         buildApplication(nameTerm(attributeExistsName),
                            [fwdTerm])])) ],
                new(fwdTerm) )
            | _ ->
              error("Is this possible, not to have a pair for an access?")
            end,
          e.encodedExpr);
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr = error("annoAccessHandler not done yet");
}

aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr = error("terminalAccessHandler not done yet");
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  local attribute newe::Expr = new(e);
  newe.grammarName = top.grammarName;
  newe.config = top.config;
  newe.env = top.env;
  newe.flowEnv = top.flowEnv;
  newe.frame = top.frame;
  newe.isRoot = top.isRoot;
  newe.originRules = top.originRules;
  newe.compiledGrammars = top.compiledGrammars;
  newe.downSubst = top.downSubst;
  newe.finalSubst = top.finalSubst;
  newe.encodingEnv = top.encodingEnv;
  newe.top = top.top;
  --
  local treename::String = "Tree";
  local treeterm::Term = varTerm(treename, genInt());
  local treenode::String = "TreeNode";
  local treenodeterm::Term = varTerm(treenode, genInt());
  local synname::String = capitalize(shortestName(q.name));
  local synnode::String = "Node";
  local synTerm::Term =
        case top.typerep of
        | nonterminalType(_, _, _) ->
          buildApplication(nameTerm(pairConstructorName),
             [varTerm(synname, genInt()),
              varTerm(synnode, genInt())])
        | _ -> varTerm(synname, genInt())
        end;
  local treeTy::AbellaType = e.typerep.abellaType;
  top.encodedExpr =
      map(\ ep::([Metaterm], Term) ->
            case ep.2 of
            | applicationTerm(nameTerm("$pair_c"),
                 consTermList(tree, singleTermList(node))) ->
              ( ep.1 ++
                [ termMetaterm(
                     buildApplication(
                        nameTerm(accessRelationName(treeTy,
                                    shortestName(q.name))),
                        [tree, node,
                         buildApplication(nameTerm(attributeExistsName),
                                          [synTerm])])) ],
                new(synTerm) )
            | _ ->
              ( ep.1 ++
                [ eqMetaterm(ep.2,
                     buildApplication(nameTerm("$pair_c"),
                        [treeterm, treenodeterm])),
                  termMetaterm(
                     buildApplication(
                        nameTerm(accessRelationName(treeTy,
                                    shortestName(q.name))),
                        [treeterm, treenodeterm,
                         buildApplication(nameTerm(attributeExistsName),
                                          [synTerm])])) ],
                new(synTerm) )
            end,
          newe.encodedExpr);
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  local attribute newe::Expr = new(e);
  newe.grammarName = top.grammarName;
  newe.config = top.config;
  newe.env = top.env;
  newe.flowEnv = top.flowEnv;
  newe.frame = top.frame;
  newe.isRoot = top.isRoot;
  newe.originRules = top.originRules;
  newe.compiledGrammars = top.compiledGrammars;
  newe.downSubst = top.downSubst;
  newe.finalSubst = top.finalSubst;
  newe.encodingEnv = top.encodingEnv;
  newe.top = top.top;
  --
  local inhname::String = capitalize(shortestName(q.name));
  local inhnode::String = "Node";
  local inhTerm::Term =
        case top.typerep of
        | nonterminalType(_, _, _) ->
          buildApplication(nameTerm(pairConstructorName),
             [varTerm(inhname, genInt()), varTerm(inhnode, genInt())])
        | _ -> varTerm(inhname, genInt())
        end;
  local treeTy::AbellaType = e.typerep.abellaType;
  top.encodedExpr =
      map(\ ep::([Metaterm], Term) ->
            case ep.2 of
            | applicationTerm(nameTerm("$pair_c"),
                 consTermList(tree, singleTermList(node))) ->
              ( ep.1 ++
                [ termMetaterm(
                     buildApplication(
                        nameTerm(accessRelationName(treeTy,
                                    shortestName(q.name))),
                        [tree, node,
                         buildApplication(nameTerm(attributeExistsName),
                                          [inhTerm])])) ],
                new(inhTerm) )
            | _ ->
              error("I don't think we can access on a non-pair")
            end,
          newe.encodedExpr);
}

aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.encodedExpr = error("decorateExprWith not done");
}

aspect production exprInhsEmpty
top::ExprInhs ::= 
{
}

aspect production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
}

aspect production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
}

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
}

aspect production exprLhsExpr
top::ExprLHSExpr ::= q::QNameAttrOccur
{
}

aspect production trueConst
top::Expr ::= 'true'
{
  top.encodedExpr = [([], nameTerm(trueName))];
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.encodedExpr = [([], nameTerm(falseName))];
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  top.encodedExpr =
      foldr(\ el1::([Metaterm], Term) rest::[([Metaterm], Term)] ->
              foldr(\ el2::([Metaterm], Term) rest::[([Metaterm], Term)] ->
                      case el1.2, el2.2 of
                      | nameTerm("$btrue"), nameTerm("$btrue") ->
                        [ ( el1.1 ++ el2.1,
                            nameTerm(trueName) ) ] ++ rest
                      | nameTerm("$btrue"), nameTerm("$bfalse") ->
                        [ ( el1.1 ++ el2.1,
                            nameTerm(falseName) ) ] ++ rest
                      | nameTerm("$btrue"), _ ->
                        [ ( el1.1 ++ el2.1 ++
                            [eqMetaterm(el2.2, nameTerm(trueName))],
                            nameTerm(trueName) ),
                          ( el1.1 ++ el2.1 ++
                            [eqMetaterm(el2.2, nameTerm(falseName))],
                            nameTerm(falseName) ) ] ++ rest
                      | nameTerm("$bfalse"), _ ->
                        [ ( el1.1,
                            nameTerm(falseName) ) ] ++ rest
                      | _, nameTerm("$btrue") ->
                        [ ( el1.1 ++ el2.1 ++
                            [eqMetaterm(el2.2, nameTerm(trueName))],
                            nameTerm(trueName) ),
                          ( el1.1 ++
                            [eqMetaterm(el1.2, nameTerm(falseName))],
                            nameTerm(falseName) ) ] ++ rest
                      | _, nameTerm("$bfalse") ->
                        [ ( el1.1 ++
                            [eqMetaterm(el1.2, nameTerm(falseName))],
                            nameTerm(falseName) ),
                          ( el1.1 ++ el2.1 ++
                            [eqMetaterm(el1.2, nameTerm(trueName))],
                            nameTerm(falseName) ) ] ++ rest
                      | _, _ ->
                        [ ( el1.1 ++ el2.1 ++
                            [eqMetaterm(el1.2, nameTerm(trueName)),
                             eqMetaterm(el2.2, nameTerm(trueName))],
                            nameTerm(trueName) ),
                          ( el1.1 ++
                            [eqMetaterm(el1.2, nameTerm(falseName))],
                            nameTerm(falseName) ),
                          ( el1.1 ++ el2.1 ++
                            [eqMetaterm(el1.2, nameTerm(trueName)),
                             eqMetaterm(el2.2, nameTerm(falseName))],
                            nameTerm(falseName) ) ] ++ rest
                      end,
                  [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  top.encodedExpr =
      foldr(\ el1::([Metaterm], Term) rest::[([Metaterm], Term)] ->
              foldr(\ el2::([Metaterm], Term) rest::[([Metaterm], Term)] ->
                      case el1.2, el2.2 of
                      | nameTerm("$bfalse"), nameTerm("$btrue") ->
                        [ ( el1.1,
                            nameTerm(trueName) ) ] ++ rest
                      | nameTerm("$bfalse"), nameTerm("$bfalse") ->
                        [ ( el1.1 ++ el2.1,
                            nameTerm(falseName) ) ] ++ rest
                      | nameTerm("$btrue"), _ ->
                        [ ( el1.1,
                            nameTerm(trueName) ) ] ++ rest
                      | nameTerm("$bfalse"), _ ->
                        [ ( el1.1 ++ el2.1 ++
                            [eqMetaterm(el2.2, nameTerm(trueName))],
                            nameTerm(trueName) ),
                          ( el1.1 ++ el2.1 ++
                            [eqMetaterm(el2.2, nameTerm(falseName))],
                            nameTerm(falseName) ) ] ++ rest
                      | _, nameTerm("$btrue") ->
                        [ ( el1.1 ++
                            [eqMetaterm(el1.2, nameTerm(trueName))],
                            nameTerm(trueName) ),
                          ( el1.1 ++
                            [eqMetaterm(el1.2, nameTerm(falseName))],
                            nameTerm(trueName) ) ] ++ rest
                      | _, nameTerm("$bfalse") ->
                        [ ( el1.1 ++
                            [eqMetaterm(el1.2, nameTerm(trueName))],
                            nameTerm(trueName) ),
                          ( el1.1 ++ el2.1 ++
                            [eqMetaterm(el1.2, nameTerm(falseName))],
                            nameTerm(falseName) ) ] ++ rest
                      | _, _ ->
                        [ ( el1.1 ++
                            [eqMetaterm(el1.2, nameTerm(trueName))],
                            nameTerm(trueName) ),
                          ( el1.1 ++
                            [eqMetaterm(el1.2, nameTerm(falseName)),
                             eqMetaterm(el2.2, nameTerm(trueName))],
                            nameTerm(trueName) ),
                          ( el1.1 ++ el2.1 ++
                            [eqMetaterm(el1.2, nameTerm(falseName)),
                             eqMetaterm(el2.2, nameTerm(falseName))],
                            nameTerm(falseName) ) ] ++ rest
                      end,
                  [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
}

aspect production not
top::Expr ::= '!' e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  top.encodedExpr =
      foldr(\ ep::([Metaterm], Term) rest::[([Metaterm], Term)] ->
              case ep.2 of
              | nameTerm("$btrue") ->
                [ (ep.1, nameTerm(falseName)) ]
              | nameTerm("$bfalse") ->
                [ (ep.1, nameTerm(trueName)) ]
              | _ ->
                [ ( ep.1 ++
                    [eqMetaterm(ep.2, nameTerm(trueName))],
                    nameTerm(falseName) ),
                  ( ep.1 ++
                    [eqMetaterm(ep.2, nameTerm(falseName))],
                    nameTerm(trueName) ) ]
              end,
            [], e.encodedExpr);
}

aspect production gtOp
top::Expr ::= e1::Expr '>' e2::Expr
{
  --Host isn't supplying some attrs
  local newe1::Expr = e1;
  local newe2::Expr = e2;
  newe1.config = top.config;
  newe1.grammarName = top.grammarName;
  newe1.env = top.env;
  newe1.flowEnv = top.flowEnv;
  newe1.frame = top.frame;
  newe1.isRoot = top.isRoot;
  newe1.originRules = top.originRules;
  newe1.compiledGrammars = top.compiledGrammars;
  newe2.config = top.config;
  newe2.grammarName = top.grammarName;
  newe2.env = top.env;
  newe2.flowEnv = top.flowEnv;
  newe2.frame = top.frame;
  newe2.isRoot = top.isRoot;
  newe2.originRules = top.originRules;
  newe2.compiledGrammars = top.compiledGrammars;
  newe1.downSubst = top.downSubst;
  newe2.downSubst = newe1.upSubst;
  newe1.finalSubst = top.finalSubst;
  newe2.finalSubst = top.finalSubst;
  --
  newe1.encodingEnv = top.encodingEnv;
  newe2.encodingEnv = top.encodingEnv;
  newe1.top = top.top;
  newe2.top = top.top;
  --
  local resultName::String = "GreaterResult";
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerGreaterName);
  local result::Term = varTerm(resultName, genInt());
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2, result]))],
                        new(result) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
}

aspect production ltOp
top::Expr ::= e1::Expr '<' e2::Expr
{
  --Host isn't supplying some attrs
  local newe1::Expr = e1;
  local newe2::Expr = e2;
  newe1.config = top.config;
  newe1.grammarName = top.grammarName;
  newe1.env = top.env;
  newe1.flowEnv = top.flowEnv;
  newe1.frame = top.frame;
  newe1.isRoot = top.isRoot;
  newe1.originRules = top.originRules;
  newe1.compiledGrammars = top.compiledGrammars;
  newe2.config = top.config;
  newe2.grammarName = top.grammarName;
  newe2.env = top.env;
  newe2.flowEnv = top.flowEnv;
  newe2.frame = top.frame;
  newe2.isRoot = top.isRoot;
  newe2.originRules = top.originRules;
  newe2.compiledGrammars = top.compiledGrammars;
  newe1.downSubst = top.downSubst;
  newe2.downSubst = newe1.upSubst;
  newe1.finalSubst = top.finalSubst;
  newe2.finalSubst = top.finalSubst;
  --
  newe1.encodingEnv = top.encodingEnv;
  newe2.encodingEnv = top.encodingEnv;
  newe1.top = top.top;
  newe2.top = top.top;
  --
  local resultName::String = "LessResult";
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerLessName);
  local result::Term = varTerm(resultName, genInt());
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2, result]))],
                        new(result) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
}

aspect production gteOp
top::Expr ::= e1::Expr '>=' e2::Expr
{
  --Host isn't supplying some attrs
  local newe1::Expr = e1;
  local newe2::Expr = e2;
  newe1.config = top.config;
  newe1.grammarName = top.grammarName;
  newe1.env = top.env;
  newe1.flowEnv = top.flowEnv;
  newe1.frame = top.frame;
  newe1.isRoot = top.isRoot;
  newe1.originRules = top.originRules;
  newe1.compiledGrammars = top.compiledGrammars;
  newe2.config = top.config;
  newe2.grammarName = top.grammarName;
  newe2.env = top.env;
  newe2.flowEnv = top.flowEnv;
  newe2.frame = top.frame;
  newe2.isRoot = top.isRoot;
  newe2.originRules = top.originRules;
  newe2.compiledGrammars = top.compiledGrammars;
  newe1.downSubst = top.downSubst;
  newe2.downSubst = newe1.upSubst;
  newe1.finalSubst = top.finalSubst;
  newe2.finalSubst = top.finalSubst;
  --
  newe1.encodingEnv = top.encodingEnv;
  newe2.encodingEnv = top.encodingEnv;
  newe1.top = top.top;
  newe2.top = top.top;
  --
  local resultName::String = "GreaterEqResult";
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerGreaterEqName);
  local result::Term = varTerm(resultName, genInt());
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2, result]))],
                        new(result) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
}

aspect production lteOp
top::Expr ::= e1::Expr '<=' e2::Expr
{
  --Host isn't supplying some attrs
  local newe1::Expr = e1;
  local newe2::Expr = e2;
  newe1.config = top.config;
  newe1.grammarName = top.grammarName;
  newe1.env = top.env;
  newe1.flowEnv = top.flowEnv;
  newe1.frame = top.frame;
  newe1.isRoot = top.isRoot;
  newe1.originRules = top.originRules;
  newe1.compiledGrammars = top.compiledGrammars;
  newe2.config = top.config;
  newe2.grammarName = top.grammarName;
  newe2.env = top.env;
  newe2.flowEnv = top.flowEnv;
  newe2.frame = top.frame;
  newe2.isRoot = top.isRoot;
  newe2.originRules = top.originRules;
  newe2.compiledGrammars = top.compiledGrammars;
  newe1.downSubst = top.downSubst;
  newe2.downSubst = newe1.upSubst;
  newe1.finalSubst = top.finalSubst;
  newe2.finalSubst = top.finalSubst;
  --
  newe1.encodingEnv = top.encodingEnv;
  newe2.encodingEnv = top.encodingEnv;
  newe1.top = top.top;
  newe2.top = top.top;
  --
  local resultName::String = "LessEqResult";
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerLessEqName);
  local result::Term = varTerm(resultName, genInt());
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2, result]))],
                        new(result) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
}

{-
  We assume that equality here is *actual* equality.  We're not
  handling type class definitions of equality.  Or inequality.
-}
aspect production eqOp
top::Expr ::= e1::Expr '==' e2::Expr
{
  --Host isn't supplying some attrs
  local newe1::Expr = e1;
  local newe2::Expr = e2;
  newe1.config = top.config;
  newe1.grammarName = top.grammarName;
  newe1.env = top.env;
  newe1.flowEnv = top.flowEnv;
  newe1.frame = top.frame;
  newe1.isRoot = top.isRoot;
  newe1.originRules = top.originRules;
  newe1.compiledGrammars = top.compiledGrammars;
  newe2.config = top.config;
  newe2.grammarName = top.grammarName;
  newe2.env = top.env;
  newe2.flowEnv = top.flowEnv;
  newe2.frame = top.frame;
  newe2.isRoot = top.isRoot;
  newe2.originRules = top.originRules;
  newe2.compiledGrammars = top.compiledGrammars;
  newe1.downSubst = top.downSubst;
  newe2.downSubst = newe1.upSubst;
  newe1.finalSubst = top.finalSubst;
  newe2.finalSubst = top.finalSubst;
  --
  newe1.encodingEnv = top.encodingEnv;
  newe2.encodingEnv = top.encodingEnv;
  newe1.top = top.top;
  newe2.top = top.top;
  --
  local resultName::String = "GreaterResult";
  top.encodedExpr =
      foldr(\ el1::([Metaterm], Term) rest::[([Metaterm], Term)] ->
              foldr(\ el2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( el1.1 ++ el2.1 ++
                        [eqMetaterm(el1.2, el2.2)],
                        nameTerm(trueName) )::
                      ( el1.1 ++ el2.1 ++
                        [impliesMetaterm(
                            eqMetaterm(el1.2, el2.2),
                            falseMetaterm())],
                        nameTerm(falseName) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
}

aspect production neqOp
top::Expr ::= e1::Expr '!=' e2::Expr
{
  --Host isn't supplying some attrs
  local newe1::Expr = e1;
  local newe2::Expr = e2;
  newe1.config = top.config;
  newe1.grammarName = top.grammarName;
  newe1.env = top.env;
  newe1.flowEnv = top.flowEnv;
  newe1.frame = top.frame;
  newe1.isRoot = top.isRoot;
  newe1.originRules = top.originRules;
  newe1.compiledGrammars = top.compiledGrammars;
  newe2.config = top.config;
  newe2.grammarName = top.grammarName;
  newe2.env = top.env;
  newe2.flowEnv = top.flowEnv;
  newe2.frame = top.frame;
  newe2.isRoot = top.isRoot;
  newe2.originRules = top.originRules;
  newe2.compiledGrammars = top.compiledGrammars;
  newe1.downSubst = top.downSubst;
  newe2.downSubst = newe1.upSubst;
  newe1.finalSubst = top.finalSubst;
  newe2.finalSubst = top.finalSubst;
  --
  newe1.encodingEnv = top.encodingEnv;
  newe2.encodingEnv = top.encodingEnv;
  newe1.top = top.top;
  newe2.top = top.top;
  --
  local resultName::String = "NotEqual";
  top.encodedExpr =
      foldr(\ el1::([Metaterm], Term) rest::[([Metaterm], Term)] ->
              foldr(\ el2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( el1.1 ++ el2.1 ++
                        [impliesMetaterm(
                            eqMetaterm(el1.2, el2.2),
                            falseMetaterm())],
                        nameTerm(falseName) )::
                      ( el1.1 ++ el2.1 ++
                        [eqMetaterm(el1.2, el2.2)],
                        nameTerm(trueName) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e3.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  e3.top = top.top;
  --
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)] ->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      case ep1.2 of
                      | nameTerm("$btrue") ->
                        ( ep1.1 ++ ep2.1, ep2.2 )::rest
                      | nameTerm("$bfalse") -> rest
                      | _ ->
                        ( ep1.1 ++ ep2.1 ++
                          [eqMetaterm(ep1.2, nameTerm(trueName))],
                          ep2.2 )::rest
                      end,
                    [], e2.encodedExpr) ++
              foldr(\ ep3::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      case ep1.2 of
                      | nameTerm("$bfalse") ->
                        ( ep1.1 ++ ep3.1, ep3.2 )::rest
                      | nameTerm("$btrue") -> rest
                      | _ ->
                        ( ep1.1 ++ ep3.1 ++
                          [eqMetaterm(ep1.2, nameTerm(falseName))],
                          ep3.2 )::rest
                      end,
                    [], e3.encodedExpr) ++ rest,
            [], e1.encodedExpr);
}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.encodedExpr = [([], integerToIntegerTerm(toInteger(i.lexeme)))];
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.encodedExpr = error("floatConst not done yet");
} 

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  local resultName::String = "PlusResult";
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerAdditionName);
  local result::Term = varTerm(resultName, genInt());
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2, result]))],
                        new(result) )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
}

aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  local resultName::String = "MinusResult";
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerSubtractionName);
  local result::Term = varTerm(resultName, genInt());
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2, result]))],
                        new(result) )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
}

aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  local resultName::String = "TimesResult";
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerMultiplicationName);
  local result::Term = varTerm(resultName, genInt());
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2, result]))],
                        new(result) )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
}

aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  local resultName::String = "DivideResult";
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerDivisionName);
  local result::Term = varTerm(resultName, genInt());
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2, result]))],
                        new(result) )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
}

aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  local resultName::String = "ModResult";
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerModulusName);
  local result::Term = varTerm(resultName, genInt());
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2, result]))],
                        new(result) )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
}

aspect production neg
top::Expr ::= '-' e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  local resultName::String = "NegResult";
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerNegateName);
  local result::Term = varTerm(resultName, genInt());
  top.encodedExpr =
      map(\ ep::([Metaterm], Term) ->
            ( ep.1 ++
              [termMetaterm(
                  buildApplication(op,
                     [ep.2, result]))],
              new(result) ),
          e.encodedExpr);
}

aspect production stringConst
top::Expr ::= s::String_t
{
  --remove quotation marks from start and end
  local contents::String =
        substring(1, length(s.lexeme) - 1, s.lexeme);
  top.encodedExpr = [([], stringToAbellaTerm(contents))];
}

--We're not handling typeclasses, so this is just list append
--Works for both lists and strings
aspect production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
  --Host isn't supplying some attrs
  local newe1::Expr = e1;
  local newe2::Expr = e2;
  newe1.config = top.config;
  newe1.grammarName = top.grammarName;
  newe1.env = top.env;
  newe1.flowEnv = top.flowEnv;
  newe1.frame = top.frame;
  newe1.isRoot = top.isRoot;
  newe1.originRules = top.originRules;
  newe1.compiledGrammars = top.compiledGrammars;
  newe2.config = top.config;
  newe2.grammarName = top.grammarName;
  newe2.env = top.env;
  newe2.flowEnv = top.flowEnv;
  newe2.frame = top.frame;
  newe2.isRoot = top.isRoot;
  newe2.originRules = top.originRules;
  newe2.compiledGrammars = top.compiledGrammars;
  newe1.downSubst = top.downSubst;
  newe2.downSubst = newe1.upSubst;
  newe1.finalSubst = top.finalSubst;
  newe2.finalSubst = top.finalSubst;
  --
  newe1.encodingEnv = top.encodingEnv;
  newe2.encodingEnv = top.encodingEnv;
  newe1.top = top.top;
  newe2.top = top.top;
  --
  local resultName::String = "AppendResult";
  local op::Term = nameTerm(appendName);
  local result::Term = varTerm(resultName, genInt());
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2, result]))],
                        new(result) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
}




attribute
   encodedArgs, encodingEnv, top
occurs on Exprs;

aspect production exprsEmpty
top::Exprs ::=
{
  top.encodedArgs = [[]];
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  top.encodedArgs = [e.encodedExpr];
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  top.encodedArgs = e1.encodedExpr::e2.encodedArgs;
}




attribute
   encodedArgs, encodingEnv, top
occurs on AppExprs;

attribute
   encodedExpr, encodingEnv, top
occurs on AppExpr;

aspect production missingAppExpr
top::AppExpr ::= '_'
{
  top.encodedExpr = error("missingAppExpr not done");
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  top.encodedExpr = e.encodedExpr;
}

aspect production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  es.encodingEnv = top.encodingEnv;
  e.encodingEnv = top.encodingEnv;
  es.top = top.top;
  e.top = top.top;
  top.encodedArgs = es.encodedArgs ++ [e.encodedExpr];
}

aspect production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  top.encodedArgs = [e.encodedExpr];
}

aspect production emptyAppExprs
top::AppExprs ::=
{
  top.encodedArgs = [[]];
}



aspect production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
}

aspect production snocAnnoAppExprs
top::AnnoAppExprs ::= es::AnnoAppExprs ',' e::AnnoExpr
{
}

aspect production oneAnnoAppExprs
top::AnnoAppExprs ::= e::AnnoExpr
{
}

aspect production emptyAnnoAppExprs
top::AnnoAppExprs ::=
{
}



aspect production exprRef
top::Expr ::= e::Decorated Expr
{
  local attribute newe::Expr = new(e);
  newe.grammarName = top.grammarName;
  newe.config = top.config;
  newe.env = top.env;
  newe.flowEnv = top.flowEnv;
  newe.frame = top.frame;
  newe.isRoot = top.isRoot;
  newe.originRules = top.originRules;
  newe.compiledGrammars = top.compiledGrammars;
  newe.downSubst = top.downSubst;
  newe.finalSubst = top.finalSubst;
  newe.encodingEnv = top.encodingEnv;
  newe.top = top.top;
  --
  top.encodedExpr = newe.encodedExpr;
}

