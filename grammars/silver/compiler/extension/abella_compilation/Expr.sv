grammar silver:compiler:extension:abella_compilation;


attribute
   encodedExpr, result, usedNames, usedNames_down, encodingEnv, top
occurs on Expr;



aspect default production
top::Expr ::=
{
  top.encodedExpr = error("Default production");
  top.result = error("Default production");
  top.usedNames = error("Default production");
}



aspect production errorExpr
top::Expr ::= e::[Message]
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.result =
      error("Abella encoding not defined in the presence of errors");
  top.usedNames =
      error("Abella encoding not defined in the presence of errors");
}

aspect production errorReference
top::Expr ::= msg::[Message]  q::Decorated QName
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.result =
      error("Abella encoding not defined in the presence of errors");
  top.usedNames =
      error("Abella encoding not defined in the presence of errors");
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [[]];
  top.result =
      case findAssociated(q.name, top.encodingEnv) of
      | just((treename, nodename))
        when top.typerep matches nonterminalType(_, _, _) ->
        buildApplication(
           nameTerm(pairConstructorName),
           [nameTerm(treename), nameTerm(nodename)])
      | just((treename, nodename)) ->
        nameTerm(treename)
      | nothing() -> error("Child must exist")
      end;
  top.usedNames = top.usedNames_down;
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [[]];
  top.result =
      case findAssociated(q.name, top.encodingEnv) of
      | just((treename, nodename)) ->
        buildApplication(
           nameTerm(pairConstructorName),
           [nameTerm(treename), nameTerm(nodename)])
      | nothing() -> error("LHS must exist")
      end;
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
      [[ termMetaterm(
            buildApplication(
               nameTerm(localAccessRelationName(top.top.3,
                           shortestName(q.name), top.top.4)),
               [nameTerm(top.top.1), nameTerm(top.top.2),
                buildApplication(nameTerm(attributeExistsName),
                                 [localTerm])])) ]];
  top.result = localTerm;
  top.usedNames = localname::localnode::top.usedNames_down;
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = error("I don't know what forwardReference is");
  top.result = error("I don't know what forwardReference is");
  top.usedNames = error("I don't know what forwardReference is");
}

aspect production productionReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [[]];
  top.result = nameTerm(nameToProd(shortestName(q.name)));
  top.usedNames = top.usedNames_down;
}

aspect production functionReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = [[]];
  top.result = nameTerm(nameToFun(shortestName(q.name)));
  top.usedNames = top.usedNames_down;
}

aspect production classMemberReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = error("classMemberReference not done yet");
  top.result = error("classMemberReference not done yet");
  top.usedNames = error("classMemberReference not done yet");
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.encodedExpr = error("globalValueReference not done yet");
  top.result = error("globalValueReference not done yet");
  top.usedNames = error("globalValueReference not done yet");
}

aspect production errorApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.result =
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
  top.encodedExpr =
      flatMap(\ el::[Metaterm] ->
                map(\ esl::[Metaterm] ->
                      el ++ esl ++
                      [termMetaterm(
                          buildApplication(newe.result,
                             newes.resultArgs ++
                             [varTerm(resultName)]))],
                    newes.encodedArgs),
              newe.encodedExpr);
  top.result = varTerm(resultName);
  top.usedNames = resultName::newes.usedNames;
}

aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.encodedExpr = error("partialApplication not done yet");
  top.result = error("partialApplication not done yet");
  top.usedNames = error("partialApplication not done yet");
}

aspect production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  top.encodedExpr = error("I don't know what noteAttachment is");
  top.result = error("I don't know what noteAttachment is");
  top.usedNames = error("I don't know what noteAttachment is");
}

aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.encodedExpr = error("attributeSection not done yet");
  top.result = error("attributeSection not done yet");
  top.usedNames = error("attributeSection not done yet");
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  e.usedNames_down = top.usedNames_down;
  local fwdName::String = makeUniqueNameFromBase("Fwd", e.usedNames);
  local fwdNode::String = makeUniqueNameFromBase("Node", e.usedNames);
  local fwdTerm::Term =
        buildApplication(nameTerm(pairConstructorName),
           [nameTerm(fwdName), nameTerm(fwdNode)]);
  top.encodedExpr =
      map(\ el::[Metaterm] ->
            el ++
            [ termMetaterm(
                 buildApplication(
                    nameTerm(accessRelationName(top.top.3, "forward")),
                    [nameTerm(top.top.1), nameTerm(top.top.2),
                     buildApplication(nameTerm(attributeExistsName),
                                      [fwdTerm])])) ],
          e.encodedExpr);
  top.result = fwdTerm;
  top.usedNames = fwdName::fwdNode::top.usedNames_down;
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.result =
      error("Abella encoding not defined in the presence of errors");
  top.usedNames =
      error("Abella encoding not defined in the presence of errors");
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr = error("annoAccessHandler not done yet");
  top.result = error("annoAccessHandler not done yet");
  top.usedNames = error("annoAccessHandler not done yet");
}

aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr = error("terminalAccessHandler not done yet");
  top.result = error("terminalAccessHandler not done yet");
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
  local synname::String =
        makeUniqueNameFromBase(shortestName(q.name),
                               top.usedNames_down);
  local synnode::String =
         makeUniqueNameFromBase("Node", top.usedNames_down);
  local synTerm::Term =
        case top.typerep of
        | nonterminalType(_, _, _) ->
          buildApplication(nameTerm(pairConstructorName),
             [nameTerm(synname), nameTerm(synnode)])
        | _ -> varTerm(synname)
        end;
  local access::Metaterm =
        termMetaterm(
           buildApplication(
              nameTerm(localAccessRelationName(top.top.3,
                          shortestName(q.name), top.top.4)),
              [nameTerm(top.top.1), nameTerm(top.top.2),
               buildApplication(nameTerm(attributeExistsName),
                                [synTerm])]));
  top.encodedExpr =
      map(\ el::[Metaterm] -> el ++ [access], newe.encodedExpr);
  top.result = synTerm;
  top.usedNames = synname::synnode::top.usedNames_down;
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
        makeUniqueNameFromBase(shortestName(q.name),
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
  local access::Metaterm =
        termMetaterm(
           buildApplication(
              nameTerm(localAccessRelationName(top.top.3,
                          shortestName(q.name), top.top.4)),
              [nameTerm(top.top.1), nameTerm(top.top.2),
               buildApplication(nameTerm(attributeExistsName),
                                [inhTerm])]));
  top.encodedExpr =
      map(\ el::[Metaterm] -> el ++ [access], newe.encodedExpr);
  top.result = inhTerm;
  top.usedNames = inhname::inhnode::top.usedNames_down;
}

aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.result =
      error("Abella encoding not defined in the presence of errors");
  top.usedNames =
      error("Abella encoding not defined in the presence of errors");
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.encodedExpr = error("decorateExprWith not done");
  top.result = error("decoratedExprWith not done");
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
  top.encodedExpr = [[]];
  top.result = nameTerm(trueName);
  top.usedNames = top.usedNames_down;
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.encodedExpr = [[]];
  top.result = nameTerm(falseName);
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
  local resultName::String =
        makeUniqueNameFromBase("AndResult", e2.usedNames);
  top.encodedExpr =
      flatMap(\ el1::[Metaterm] ->
            flatMap(\ el2::[Metaterm] ->
                      [ el1 ++ el2 ++
                        [eqMetaterm(e1.result, nameTerm(trueName)),
                         eqMetaterm(e2.result, nameTerm(trueName)),
                         eqMetaterm(varTerm(resultName),
                                    nameTerm(trueName))],
                        el1 ++
                        [eqMetaterm(e1.result, nameTerm(falseName)),
                         eqMetaterm(varTerm(resultName),
                                    nameTerm(falseName))],
                        el1 ++ el2 ++
                        [eqMetaterm(e1.result, nameTerm(trueName)),
                         eqMetaterm(e2.result, nameTerm(falseName)),
                         eqMetaterm(varTerm(resultName),
                                    nameTerm(falseName))] ],
                    e2.encodedExpr),
              e1.encodedExpr);
  top.result = varTerm(resultName);
  top.usedNames = resultName::e2.usedNames;
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
  local resultName::String =
        makeUniqueNameFromBase("OrResult", e2.usedNames);
  top.encodedExpr =
      flatMap(\ el1::[Metaterm] ->
            flatMap(\ el2::[Metaterm] ->
                      [ el1 ++
                        [eqMetaterm(e1.result, nameTerm(trueName)),
                         eqMetaterm(varTerm(resultName),
                                    nameTerm(trueName))],
                        el1 ++ el2 ++
                        [eqMetaterm(e1.result, nameTerm(falseName)),
                         eqMetaterm(e2.result, nameTerm(trueName)),
                         eqMetaterm(varTerm(resultName),
                                    nameTerm(trueName))],
                        el1 ++ el2 ++
                        [eqMetaterm(e1.result, nameTerm(falseName)),
                         eqMetaterm(e2.result, nameTerm(falseName)),
                         eqMetaterm(varTerm(resultName),
                                    nameTerm(falseName))] ],
                    e2.encodedExpr),
              e1.encodedExpr);
  top.result = varTerm(resultName);
  top.usedNames = resultName::e2.usedNames;
}

aspect production not
top::Expr ::= '!' e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  e.usedNames_down = top.usedNames_down;
  local resultName::String =
        makeUniqueNameFromBase("NotResult", e.usedNames);
  top.encodedExpr =
      flatMap(\ el::[Metaterm] ->
                [ el ++
                  [eqMetaterm(e.result, nameTerm(trueName)),
                   eqMetaterm(varTerm(resultName),
                              nameTerm(falseName))],
                  el ++
                  [eqMetaterm(e.result, nameTerm(falseName)),
                   eqMetaterm(varTerm(resultName),
                              nameTerm(trueName))] ],
              e.encodedExpr);
  top.result = varTerm(resultName);
  top.usedNames = resultName::e.usedNames;
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
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [termMetaterm(
                                buildApplication(op,
                                   [newe1.result, newe2.result,
                                    varTerm(resultName)]))] ],
                        newe2.encodedExpr),
              newe1.encodedExpr);
  top.result = varTerm(resultName);
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
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [termMetaterm(
                                buildApplication(op,
                                   [newe1.result, newe2.result,
                                    varTerm(resultName)]))] ],
                        newe2.encodedExpr),
              newe1.encodedExpr);
  top.result = varTerm(resultName);
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
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [termMetaterm(
                                buildApplication(op,
                                   [newe1.result, newe2.result,
                                    varTerm(resultName)]))] ],
                        newe2.encodedExpr),
              newe1.encodedExpr);
  top.result = varTerm(resultName);
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
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [termMetaterm(
                                buildApplication(op,
                                   [newe1.result, newe2.result,
                                    varTerm(resultName)]))] ],
                        newe2.encodedExpr),
              newe1.encodedExpr);
  top.result = varTerm(resultName);
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
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [eqMetaterm(newe1.result, newe2.result),
                             eqMetaterm(varTerm(resultName),
                                        nameTerm(trueName))],
                            el1 ++ el2 ++
                            [impliesMetaterm(
                                eqMetaterm(newe1.result, newe2.result),
                                falseMetaterm()),
                             eqMetaterm(varTerm(resultName),
                                        nameTerm(falseName))] ],
                        newe2.encodedExpr),
              newe1.encodedExpr);
  top.result = varTerm(resultName);
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
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [impliesMetaterm(
                                eqMetaterm(newe1.result, newe2.result),
                                falseMetaterm()),
                             eqMetaterm(varTerm(resultName),
                                        nameTerm(trueName))],
                            el1 ++ el2 ++
                            [eqMetaterm(newe1.result, newe2.result),
                             eqMetaterm(varTerm(resultName),
                                        nameTerm(falseName))] ],
                        newe2.encodedExpr),
              newe1.encodedExpr);
  top.result = varTerm(resultName);
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
  local resultName::String =
        makeUniqueNameFromBase("IfResult", e3.usedNames);
  top.encodedExpr =
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++
                            [eqMetaterm(e1.result,
                                        nameTerm(trueName))] ++
                            el2 ++
                            [eqMetaterm(varTerm(resultName),
                                        e2.result)] ],
                        e2.encodedExpr) ++
                flatMap(\ el3::[Metaterm] ->
                          [ el1 ++
                            [eqMetaterm(e1.result,
                                        nameTerm(falseName))] ++
                            el3 ++
                            [eqMetaterm(varTerm(resultName),
                                        e3.result)] ],
                        e3.encodedExpr),
              e1.encodedExpr);
  top.result = varTerm(resultName);
  top.usedNames = resultName::e3.usedNames;
}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.encodedExpr = [[]];
  top.result = integerToIntegerTerm(toInteger(i.lexeme));
  top.usedNames = top.usedNames_down;
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.encodedExpr = error("floatConst not done yet");
  top.result = error("floatConst not done yet");
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
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [termMetaterm(
                                buildApplication(op,
                                   [e1.result, e2.result,
                                    varTerm(resultName)]))] ],
                        e2.encodedExpr),
              e1.encodedExpr);
  top.result = varTerm(resultName);
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
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [termMetaterm(
                                buildApplication(op,
                                   [e1.result, e2.result,
                                    varTerm(resultName)]))] ],
                        e2.encodedExpr),
              e1.encodedExpr);
  top.result = varTerm(resultName);
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
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [termMetaterm(
                                buildApplication(op,
                                   [e1.result, e2.result,
                                    varTerm(resultName)]))] ],
                        e2.encodedExpr),
              e1.encodedExpr);
  top.result = varTerm(resultName);
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
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [termMetaterm(
                                buildApplication(op,
                                   [e1.result, e2.result,
                                    varTerm(resultName)]))] ],
                        e2.encodedExpr),
              e1.encodedExpr);
  top.result = varTerm(resultName);
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
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [termMetaterm(
                                buildApplication(op,
                                   [e1.result, e2.result,
                                    varTerm(resultName)]))] ],
                        e2.encodedExpr),
              e1.encodedExpr);
  top.result = varTerm(resultName);
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
      flatMap(\ el::[Metaterm] ->
                [ el ++
                  [termMetaterm(
                      buildApplication(op,
                         [e.result, varTerm(resultName)]))] ],
              e.encodedExpr);
  top.result = varTerm(resultName);
  top.usedNames = resultName::e.usedNames;
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.encodedExpr = [[]];
  --remove quotation marks from start and end
  local contents::String =
        substring(1, length(s.lexeme) - 1, s.lexeme);
  top.result = stringToAbellaTerm(contents);
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
  top.encodedExpr =
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ++
                            [termMetaterm(
                                buildApplication(
                                   nameTerm(appendName),
                                   [newe1.result, newe2.result,
                                    varTerm(resultName)]))] ],
                        newe2.encodedExpr),
              newe1.encodedExpr);
  top.result = varTerm(resultName);
  top.usedNames = resultName::newe2.usedNames;
}




attribute
   encodedArgs, resultArgs, usedNames, usedNames_down, encodingEnv, top
occurs on Exprs;

aspect production exprsEmpty
top::Exprs ::=
{
  top.encodedArgs = [[]];
  top.resultArgs = [];
  top.usedNames = top.usedNames_down;
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  e.usedNames_down = top.usedNames_down;
  top.encodedArgs = e.encodedExpr;
  top.resultArgs = [e.result];
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
  top.encodedArgs =
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ],
                        e2.encodedArgs),
              e1.encodedExpr);
  top.resultArgs = e1.result::e2.resultArgs;
  top.usedNames = e2.usedNames;
}




attribute
   encodedArgs, resultArgs, usedNames, usedNames_down, encodingEnv, top
occurs on AppExprs;

attribute
   encodedExpr, result, usedNames, usedNames_down, encodingEnv, top
occurs on AppExpr;

aspect production missingAppExpr
top::AppExpr ::= '_'
{
  top.encodedExpr = error("missingAppExpr not done");
  top.result = error("missingAppExpr not done");
  top.usedNames = error("missingAppExpr not done");
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  e.usedNames_down = top.usedNames_down;
  top.encodedExpr = e.encodedExpr;
  top.result = e.result;
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
  top.encodedArgs =
      flatMap(\ el1::[Metaterm] ->
                flatMap(\ el2::[Metaterm] ->
                          [ el1 ++ el2 ],
                        e.encodedExpr),
              es.encodedArgs);
  top.resultArgs = es.resultArgs ++ [e.result];
  top.usedNames = e.usedNames;
}

aspect production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  e.usedNames_down = top.usedNames_down;
  top.encodedArgs = e.encodedExpr;
  top.resultArgs = [e.result];
  top.usedNames = e.usedNames;
}

aspect production emptyAppExprs
top::AppExprs ::=
{
  top.encodedArgs = [[]];
  top.resultArgs = [];
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
  top.result = newe.result;
  top.usedNames = newe.usedNames;
}

