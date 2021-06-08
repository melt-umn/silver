grammar silver:compiler:extension:abella_compilation;


attribute
   encodedExpr, usedNames, usedNames_down, encodingEnv, top
occurs on Expr;



aspect default production
top::Expr ::=
{
  top.encodedExpr = error("Default production");
  top.usedNames = error("Default production");
}



aspect production errorExpr
top::Expr ::= e::[Message]
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.usedNames =
      error("Abella encoding not defined in the presence of errors");
}

aspect production errorReference
top::Expr ::= msg::[Message]  q::Decorated QName
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.usedNames =
      error("Abella encoding not defined in the presence of errors");
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [([],
      case findAssociated(q.name, top.encodingEnv) of
      | just((treename, nodename)) when isNonterminal(top.typerep) ->
        buildApplication(
           nameTerm(pairConstructorName),
           [nameTerm(treename), nameTerm(nodename)])
      | just((treename, nodename)) ->
        nameTerm(treename)
      | nothing() -> error("Child must exist")
      end)];
  top.usedNames = top.usedNames_down;
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [([],
      case findAssociated(q.name, top.encodingEnv) of
      | just((treename, nodename)) ->
        buildApplication(
           nameTerm(pairConstructorName),
           [nameTerm(treename), nameTerm(nodename)])
      | nothing() -> error("LHS must exist")
      end)];
  top.usedNames = top.usedNames_down;
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
  local localname::String =
        makeUniqueNameFromBase(capitalize(shortestName(q.name)),
                               top.usedNames_down);
  local localnode::String =
         makeUniqueNameFromBase("Node", top.usedNames_down);
  local localTerm::Term =
        case top.typerep of
        | nonterminalType(_, _, _) ->
          buildApplication(nameTerm(pairConstructorName),
             [nameTerm(localname), nameTerm(localnode)])
        | _ -> varTerm(localname)
        end;
  top.encodedExpr =
      [([ termMetaterm(
             buildApplication(
                nameTerm(localAccessRelationName(top.top.3,
                            shortestName(q.name), top.top.4)),
                [nameTerm(top.top.1), nameTerm(top.top.2),
                 buildApplication(nameTerm(attributeExistsName),
                                  [localTerm])])) ], new(localTerm))];
  top.usedNames = localname::localnode::top.usedNames_down;
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = error("I don't know what forwardReference is");
  top.usedNames = error("I don't know what forwardReference is");
}

aspect production productionReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [([], nameTerm(nameToProd(shortestName(q.name))))];
  top.usedNames = top.usedNames_down;
}

aspect production functionReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [([], nameTerm(nameToFun(shortestName(q.name))))];
  top.usedNames = top.usedNames_down;
}

aspect production classMemberReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = error("classMemberReference not done yet");
  top.usedNames = error("classMemberReference not done yet");
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = error("globalValueReference not done yet");
  top.usedNames = error("globalValueReference not done yet");
}

aspect production errorApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.usedNames =
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
  newe.usedNames_down = top.usedNames_down;
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
  newes.usedNames_down = newe.usedNames;
  --
  local resultName::String =
        makeUniqueNameFromBase("FunResult", newes.usedNames);
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
                               argp.2 ++ [varTerm(resultName)]))],
                        varTerm(resultName) )::rest,
                    rest, args),
            [], newe.encodedExpr);
  top.usedNames = resultName::newes.usedNames;
}

aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.encodedExpr = error("partialApplication not done yet");
  top.usedNames = error("partialApplication not done yet");
}

aspect production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  top.encodedExpr = error("I don't know what noteAttachment is");
  top.usedNames = error("I don't know what noteAttachment is");
}

aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.encodedExpr = error("attributeSection not done yet");
  top.usedNames = error("attributeSection not done yet");
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  e.usedNames_down = top.usedNames_down;
  local treeTy::AbellaType = e.typerep.abellaType;
  local fwdName::String = makeUniqueNameFromBase("Fwd", e.usedNames);
  local fwdNode::String = makeUniqueNameFromBase("Node", e.usedNames);
  local fwdTerm::Term =
        buildApplication(nameTerm(pairConstructorName),
           [nameTerm(fwdName), nameTerm(fwdNode)]);
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
  top.usedNames = fwdName::fwdNode::top.usedNames_down;
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.usedNames =
      error("Abella encoding not defined in the presence of errors");
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr = error("annoAccessHandler not done yet");
  top.usedNames = error("annoAccessHandler not done yet");
}

aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr = error("terminalAccessHandler not done yet");
  top.usedNames = error("terminalAccessHandler not done yet");
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
  newe.usedNames_down = top.usedNames_down;
  --
  local treename::String =
        makeUniqueNameFromBase("Tree", newe.usedNames);
  local treenode::String =
        makeUniqueNameFromBase("TreeNode", newe.usedNames);
  local synname::String =
        makeUniqueNameFromBase(capitalize(shortestName(q.name)),
                               treename::treenode::newe.usedNames);
  local synnode::String =
         makeUniqueNameFromBase("Node", synname::newe.usedNames);
  local synTerm::Term =
        case top.typerep of
        | nonterminalType(_, _, _) ->
          buildApplication(nameTerm(pairConstructorName),
             [nameTerm(synname), nameTerm(synnode)])
        | _ -> varTerm(synname)
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
                        [varTerm(treename), varTerm(treenode)])),
                  termMetaterm(
                     buildApplication(
                        nameTerm(accessRelationName(treeTy,
                                    shortestName(q.name))),
                        [varTerm(treename), varTerm(treenode),
                         buildApplication(nameTerm(attributeExistsName),
                                          [synTerm])])) ],
                new(synTerm) )
            end,
          newe.encodedExpr);
  top.usedNames = treename::treenode::synname::synnode::newe.usedNames;
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
  newe.usedNames_down = top.usedNames_down;
  --
  local inhname::String =
        makeUniqueNameFromBase(capitalize(shortestName(q.name)),
                               top.usedNames_down);
  local inhnode::String =
         makeUniqueNameFromBase("Node", top.usedNames_down);
  local inhTerm::Term =
        case top.typerep of
        | nonterminalType(_, _, _) ->
          buildApplication(nameTerm(pairConstructorName),
             [nameTerm(inhname), nameTerm(inhnode)])
        | _ -> varTerm(inhname)
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
  top.usedNames = inhname::inhnode::top.usedNames_down;
}

aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.usedNames =
      error("Abella encoding not defined in the presence of errors");
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.encodedExpr = error("decorateExprWith not done");
  top.usedNames = error("decorateExprWith note done");
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
  top.usedNames = top.usedNames_down;
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.encodedExpr = [([], nameTerm(falseName))];
  top.usedNames = top.usedNames_down;
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  e1.usedNames_down = top.usedNames_down;
  e2.usedNames_down = e1.usedNames;
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
  top.usedNames = e2.usedNames;
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  e1.usedNames_down = top.usedNames_down;
  e2.usedNames_down = e1.usedNames;
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
  top.usedNames = e2.usedNames;
}

aspect production not
top::Expr ::= '!' e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  e.usedNames_down = top.usedNames_down;
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
  top.usedNames = e.usedNames;
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
  newe1.usedNames_down = top.usedNames_down;
  newe2.usedNames_down = newe1.usedNames;
  --
  local resultName::String =
        makeUniqueNameFromBase("GreaterResult", newe2.usedNames);
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerGreaterName);
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2,
                                varTerm(resultName)]))],
                        varTerm(resultName) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
  top.usedNames = resultName::newe2.usedNames;
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
  newe1.usedNames_down = top.usedNames_down;
  newe2.usedNames_down = newe1.usedNames;
  --
  local resultName::String =
        makeUniqueNameFromBase("LessResult", newe2.usedNames);
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerLessName);
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2,
                                varTerm(resultName)]))],
                        varTerm(resultName) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
  top.usedNames = resultName::newe2.usedNames;
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
  newe1.usedNames_down = top.usedNames_down;
  newe2.usedNames_down = newe1.usedNames;
  --
  local resultName::String =
        makeUniqueNameFromBase("GreaterEqResult", newe2.usedNames);
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerGreaterEqName);
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2,
                                varTerm(resultName)]))],
                        varTerm(resultName) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
  top.usedNames = resultName::newe2.usedNames;
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
  newe1.usedNames_down = top.usedNames_down;
  newe2.usedNames_down = newe1.usedNames;
  --
  local resultName::String =
        makeUniqueNameFromBase("LessEqResult", newe2.usedNames);
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerLessEqName);
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2,
                                varTerm(resultName)]))],
                        varTerm(resultName) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
  top.usedNames = resultName::newe2.usedNames;
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
  newe1.usedNames_down = top.usedNames_down;
  newe2.usedNames_down = newe1.usedNames;
  --
  local resultName::String =
        makeUniqueNameFromBase("GreaterResult", newe2.usedNames);
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
  top.usedNames = resultName::newe2.usedNames;
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
  newe1.usedNames_down = top.usedNames_down;
  newe2.usedNames_down = newe1.usedNames;
  --
  local resultName::String =
        makeUniqueNameFromBase("GreaterResult", newe2.usedNames);
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
  top.usedNames = resultName::newe2.usedNames;
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
  e1.usedNames_down = top.usedNames_down;
  e2.usedNames_down = e1.usedNames;
  e3.usedNames_down = e2.usedNames;
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
  top.usedNames = e3.usedNames;
}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.encodedExpr = [([], integerToIntegerTerm(toInteger(i.lexeme)))];
  top.usedNames = top.usedNames_down;
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.encodedExpr = error("floatConst not done yet");
  top.usedNames = error("floatConst not done yet");
} 

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  e1.usedNames_down = top.usedNames_down;
  e2.usedNames_down = e1.usedNames;
  local resultName::String =
        makeUniqueNameFromBase("PlusResult", e2.usedNames);
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerAdditionName);
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2,
                                varTerm(resultName)]))],
                        varTerm(resultName) )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
  top.usedNames = resultName::e2.usedNames;
}

aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  e1.usedNames_down = top.usedNames_down;
  e2.usedNames_down = e1.usedNames;
  local resultName::String =
        makeUniqueNameFromBase("MinusResult", e2.usedNames);
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerSubtractionName);
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2,
                                varTerm(resultName)]))],
                        varTerm(resultName) )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
  top.usedNames = resultName::e2.usedNames;
}

aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  e1.usedNames_down = top.usedNames_down;
  e2.usedNames_down = e1.usedNames;
  local resultName::String =
        makeUniqueNameFromBase("TimesResult", e2.usedNames);
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerMultiplicationName);
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2,
                                varTerm(resultName)]))],
                        varTerm(resultName) )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
  top.usedNames = resultName::e2.usedNames;
}

aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  e1.usedNames_down = top.usedNames_down;
  e2.usedNames_down = e1.usedNames;
  local resultName::String =
        makeUniqueNameFromBase("DivideResult", e2.usedNames);
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerDivisionName);
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2,
                                varTerm(resultName)]))],
                        varTerm(resultName) )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
  top.usedNames = resultName::e2.usedNames;
}

aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  e1.usedNames_down = top.usedNames_down;
  e2.usedNames_down = e1.usedNames;
  local resultName::String =
        makeUniqueNameFromBase("ModResult", e2.usedNames);
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerModulusName);
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2,
                                varTerm(resultName)]))],
                        varTerm(resultName) )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr);
  top.usedNames = resultName::e2.usedNames;
}

aspect production neg
top::Expr ::= '-' e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  e.usedNames_down = top.usedNames_down;
  local resultName::String =
        makeUniqueNameFromBase("NegResult", e.usedNames);
  --When we do this for more than integer, we need to change this
  local op::Term = nameTerm(integerNegateName);
  top.encodedExpr =
      map(\ ep::([Metaterm], Term) ->
            ( ep.1 ++
              [termMetaterm(
                  buildApplication(op,
                     [ep.2, varTerm(resultName)]))],
              varTerm(resultName) ),
          e.encodedExpr);
  top.usedNames = resultName::e.usedNames;
}

aspect production stringConst
top::Expr ::= s::String_t
{
  --remove quotation marks from start and end
  local contents::String =
        substring(1, length(s.lexeme) - 1, s.lexeme);
  top.encodedExpr = [([], stringToAbellaTerm(contents))];
  top.usedNames = top.usedNames_down;
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
  newe1.usedNames_down = top.usedNames_down;
  newe2.usedNames_down = newe1.usedNames;
  --
  local resultName::String =
        makeUniqueNameFromBase("AppendResult", newe2.usedNames);
  local op::Term = nameTerm(appendName);
  top.encodedExpr =
      foldr(\ ep1::([Metaterm], Term) rest::[([Metaterm], Term)]->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      ( ep1.1 ++ ep2.1 ++
                        [termMetaterm(
                            buildApplication(op,
                               [ep1.2, ep2.2,
                                varTerm(resultName)]))],
                        varTerm(resultName) )::rest,
                    [], newe2.encodedExpr) ++ rest,
            [], newe1.encodedExpr);
  top.usedNames = resultName::newe2.usedNames;
}




attribute
   encodedArgs, usedNames, usedNames_down, encodingEnv, top
occurs on Exprs;

aspect production exprsEmpty
top::Exprs ::=
{
  top.encodedArgs = [[]];
  top.usedNames = top.usedNames_down;
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  e.usedNames_down = top.usedNames_down;
  top.encodedArgs = [e.encodedExpr];
  top.usedNames = e.usedNames;
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  e1.usedNames_down = top.usedNames_down;
  e2.usedNames_down = e1.usedNames;
  top.encodedArgs = e1.encodedExpr::e2.encodedArgs;
  top.usedNames = e2.usedNames;
}




attribute
   encodedArgs, usedNames, usedNames_down, encodingEnv, top
occurs on AppExprs;

attribute
   encodedExpr, usedNames, usedNames_down, encodingEnv, top
occurs on AppExpr;

aspect production missingAppExpr
top::AppExpr ::= '_'
{
  top.encodedExpr = error("missingAppExpr not done");
  top.usedNames = error("missingAppExpr not done");
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  e.usedNames_down = top.usedNames_down;
  top.encodedExpr = e.encodedExpr;
  top.usedNames = e.usedNames;
}

aspect production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  es.encodingEnv = top.encodingEnv;
  e.encodingEnv = top.encodingEnv;
  es.top = top.top;
  e.top = top.top;
  es.usedNames_down = top.usedNames_down;
  e.usedNames_down = es.usedNames;
  top.encodedArgs = es.encodedArgs ++ [e.encodedExpr];
  top.usedNames = e.usedNames;
}

aspect production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  e.usedNames_down = top.usedNames_down;
  top.encodedArgs = [e.encodedExpr];
  top.usedNames = e.usedNames;
}

aspect production emptyAppExprs
top::AppExprs ::=
{
  top.encodedArgs = [[]];
  top.usedNames = top.usedNames_down;
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
  newe.usedNames_down = top.usedNames_down;
  --
  top.encodedExpr = newe.encodedExpr;
  top.usedNames = newe.usedNames;
}

