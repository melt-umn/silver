grammar silver:compiler:extension:abella_compilation:encoding;

imports silver:compiler:extension:tuple;


attribute
   encodedExpr, encodedFailure, encodingEnv, top
occurs on Expr;



aspect default production
top::Expr ::=
{
  top.encodedExpr = error("Default production (" ++ top.unparse ++ ")");
  top.encodedFailure = error("Default production (" ++ top.unparse ++ ")");
}



aspect production errorExpr
top::Expr ::= e::[Message]
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.encodedFailure =
      error("Abella encoding not defined in the presence of errors");
}

aspect production errorReference
top::Expr ::= msg::[Message]  q::PartiallyDecorated QName
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.encodedFailure =
      error("Abella encoding not defined in the presence of errors");
}

aspect production childReference
top::Expr ::= q::PartiallyDecorated QName
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
  top.encodedFailure = [];
}

aspect production lhsReference
top::Expr ::= q::PartiallyDecorated QName
{
  top.encodedExpr = [([],
      case findAssociated(q.name, top.encodingEnv) of
      | just((treename, nodename)) ->
        buildApplication(
           nameTerm(pairConstructorName),
           [treename, nodename])
      | nothing() -> error("LHS must exist")
      end)];
  top.encodedFailure = [];
}

aspect production localReference
top::Expr ::= q::PartiallyDecorated QName
{
  local localname::Term =
        varTerm(capitalize(shortestName(q.name)), genInt());
  local localnode::Term = varTerm("Node", genInt());
  local localchildlist::Term = varTerm("ChildList", genInt());
  local fullLocalTerm::Term =
        if isNonterminal(top.typerep)
        then buildApplication(nameTerm(pairConstructorName),
                [localname,
                 buildApplication(
                    nameTerm(nodeTreeConstructorName(top.typerep.abellaType)),
                    [localnode, localchildlist])])
        else localname;
  local resultLocalTerm::Term =
        if isNonterminal(top.typerep)
        then buildApplication(nameTerm(pairConstructorName),
                [localname, localnode])
        else localname;
  top.encodedExpr =
      [([ termMetaterm(
             buildApplication(
                nameTerm(localAccessRelationName(top.top.3,
                            shortestName(q.name), top.top.4)),
                [top.top.1, top.top.2,
                 buildApplication(nameTerm(attributeExistsName),
                                  [fullLocalTerm])])) ],
        new(resultLocalTerm))];
  --Only way to fail is local is undefined
  top.encodedFailure =
      [[termMetaterm(
           buildApplication(
              nameTerm(localAccessRelationName(top.top.3,
                          shortestName(q.name), top.top.4)),
              [top.top.1, top.top.2,
               nameTerm(attributeNotExistsName)]))]];
}

aspect production forwardReference
top::Expr ::= q::PartiallyDecorated QName
{
  top.encodedExpr = error("I don't know what forwardReference is");
  top.encodedFailure = error("I don't know what forwardReference is");
}

aspect production productionReference
top::Expr ::= q::PartiallyDecorated QName
{
  top.encodedExpr =
      if q.lookupValue.fullName == "silver:core:pair"
      then [([], nameTerm(pairConstructorName))]
      else [([], nameTerm(nameToProd(encodeName(q.lookupValue.fullName))))];
  top.encodedFailure = [];
}

aspect production functionReference
top::Expr ::= q::PartiallyDecorated QName
{
  top.encodedExpr =
      --error(<str>) cannot succeed, so don't translate it
      if q.lookupValue.fullName == "silver:core:error"
      then []
      --nothing special
      else [([], nameTerm(nameToFun(encodeName(q.lookupValue.fullName))))];
  top.encodedFailure =
      if q.lookupValue.fullName == "silver:core:error"
      then [[]]
      else [];
}

aspect production classMemberReference
top::Expr ::= q::PartiallyDecorated QName
{
  top.encodedExpr = error("classMemberReference not done yet:  " ++ q.name);
  top.encodedFailure = error("classMemberReference not done yet:  " ++ q.name);
}

aspect production globalValueReference
top::Expr ::= q::PartiallyDecorated QName
{
  top.encodedExpr = error("globalValueReference not done yet");
  top.encodedFailure = error("globalValueReference not done yet");
}

aspect production errorApplication
top::Expr ::= e::PartiallyDecorated Expr es::PartiallyDecorated AppExprs anns::PartiallyDecorated AnnoAppExprs
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.encodedFailure =
      error("Abella encoding not defined in the presence of errors");
}

aspect production functionInvocation
top::Expr ::= e::PartiallyDecorated Expr es::PartiallyDecorated AppExprs anns::PartiallyDecorated AnnoAppExprs
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
  top.encodedExpr =
      case e of
      --Treat building lists specially for simplicity in encoded spec
      | functionReference(q)
        when q.lookupValue.fullName == "silver:core:nil" ->
        [( [], nilTerm() )]
      | functionReference(q)
        when q.lookupValue.fullName == "silver:core:cons" ->
        map(\ ep::([Metaterm], [Term]) ->
              case ep.2 of
              | [hd, tl] -> (ep.1, consTerm(hd, tl))
              | _ -> error("Should not access encoding in " ++
                           "presence of errors")
              end,
            newes.encodedArgs)
      --Anything else encode into an application
      | _ ->
        foldr(\ ep::([Metaterm], Term) rest::[([Metaterm], Term)] ->
                foldr(\ argp::([Metaterm], [Term])
                        rest::[([Metaterm], Term)] ->
                        ( if termIsProd(ep.2) ||
                             ep.2.unparse == pairConstructorName
                          then ( ep.1 ++ argp.1,
                                 buildApplication(ep.2, argp.2) )
                          else ( ep.1 ++ argp.1 ++
                                 [termMetaterm(
                                     buildApplication(ep.2,
                                        argp.2 ++ [resultTerm]))],
                                 new(resultTerm) ) )::rest,
                      rest, newes.encodedArgs),
              [], newe.encodedExpr)
      end;
  top.encodedFailure =
      case e of
      --Treat building lists specially because they cannot fail as function calls
      | functionReference(q)
        when q.lookupValue.fullName == "silver:core:nil" ->
        [] --nothing can fail
      | functionReference(q)
        when q.lookupValue.fullName == "silver:core:cons" ->
        newes.encodedFailure --only args can fail
      --for each encoding of function and args, might not return
      | _ ->
        foldr(\ ep::([Metaterm], Term) rest::[[Metaterm]] ->
                foldr(\ argp::([Metaterm], [Term])
                        rest::[[Metaterm]] ->
                        if termIsProd(ep.2) ||
                           ep.2.unparse == pairConstructorName
                        then --productions always return
                             rest
                        else ( ep.1 ++ argp.1 ++
                               [impliesMetaterm(
                                   bindingMetaterm(existsBinder(),
                                      [("Res", nothing())],
                                      termMetaterm(
                                         buildApplication(ep.2,
                                            argp.2 ++ [nameTerm("Res")]))),
                                   falseMetaterm())] )::rest,
                      rest, newes.encodedArgs),
              [], newe.encodedExpr) ++
        newe.encodedFailure ++ newes.encodedFailure
      end;
}

aspect production partialApplication
top::Expr ::= e::PartiallyDecorated Expr es::PartiallyDecorated AppExprs anns::PartiallyDecorated AnnoAppExprs
{
  top.encodedExpr = error("partialApplication not done yet");
  top.encodedFailure = error("partialApplication not done yet");
}

aspect production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  top.encodedExpr = error("I don't know what noteAttachment is");
  top.encodedFailure = error("I don't know what noteAttachment is");
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
  top.encodedFailure =
      map(\ ep::([Metaterm], Term) ->
            case ep.2 of
            | applicationTerm(nameTerm("$pair_c"),
                 consTermList(tree, singleTermList(node))) ->
              ep.1 ++
              [ termMetaterm(
                   buildApplication(
                      nameTerm(accessRelationName(treeTy, "forward")),
                      [tree, node, nameTerm(attributeNotExistsName)])) ]
            | _ ->
              error("Is this possible, not to have a pair for an access?")
            end,
          e.encodedExpr) ++
      e.encodedFailure;
}

aspect production errorAccessHandler
top::Expr ::= e::PartiallyDecorated Expr  q::PartiallyDecorated QNameAttrOccur
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.encodedFailure =
      error("Abella encoding not defined in the presence of errors");
}

aspect production annoAccessHandler
top::Expr ::= e::PartiallyDecorated Expr  q::PartiallyDecorated QNameAttrOccur
{
  top.encodedExpr = error("annoAccessHandler not done yet");
  top.encodedFailure = error("annoAccessHandler not done yet");
}

aspect production terminalAccessHandler
top::Expr ::= e::PartiallyDecorated Expr  q::PartiallyDecorated QNameAttrOccur
{
  top.encodedExpr = error("terminalAccessHandler not done yet");
  top.encodedFailure = error("terminalAccessHandler not done yet");
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::PartiallyDecorated Expr  q::PartiallyDecorated QNameAttrOccur
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
  local attrName::String =
        case q of
        | qNameAttrOccur(at) ->
          encodeName(at.lookupAttribute.fullName)
        end;
  local synname::String = capitalize(shortestName(q.name));
  local synnode::String = "Node";
  local synTerm::Term =
        if isNonterminal(top.typerep)
        then buildApplication(nameTerm(pairConstructorName),
                [varTerm(synname, genInt()),
                 varTerm(synnode, genInt())])
        else varTerm(synname, genInt());
  local treeTy::AbellaType = e.typerep.abellaType;
  top.encodedExpr =
      if isPair(e.typerep)
      then let p1::Term = varTerm("Fst", genInt())
           in
           let p2::Term = varTerm("Snd", genInt())
           in
           let pr::Term =
               buildApplication(nameTerm(pairConstructorName), [p1, p2])
           in
             if q.attrDcl.fullName == "silver:core:fst"
             then map(\ ep::([Metaterm], Term) ->
                        case ep.2 of
                        | applicationTerm(nameTerm("$pair_c"),
                             consTermList(fst, singleTermList(snd))) ->
                          ( ep.1, new(fst) )
                        | varTerm(n, i) ->
                          ( replaceVar_list((n, i), pr, ep.1), p1 )
                        | _ -> error("Impossible for pair-typed term")
                        end,
                      newe.encodedExpr)
             else if q.attrDcl.fullName == "silver:core:snd"
             then map(\ ep::([Metaterm], Term) ->
                        case ep.2 of
                        | applicationTerm(nameTerm("$pair_c"),
                             consTermList(fst, singleTermList(snd))) ->
                          ( ep.1, new(snd) )
                        | varTerm(n, i) ->
                          ( replaceVar_list((n, i), pr, ep.1), p2 )
                        | _ -> error("Impossible for pair-typed term")
                        end,
                      newe.encodedExpr)
             else error("Cannot handle accesses of attributes on " ++
                        "pairs other than fst and snd (tried to " ++
                        "access " ++ q.name ++ ")")
           end end end
      else map(\ ep::([Metaterm], Term) ->
                 case ep.2 of
                 | applicationTerm(nameTerm("$pair_c"),
                      consTermList(tree, singleTermList(node))) ->
                   ( ep.1 ++
                     [ termMetaterm(
                          buildApplication(
                             nameTerm(accessRelationName(treeTy,
                                         attrName)),
                             [tree, node,
                              buildApplication(nameTerm(attributeExistsName),
                                               [synTerm])])) ],
                     new(synTerm) )
                 | _ ->
                   {-
                     It is probably possible for this to occur.  In that case,
                     one would need to generate a node and WPD assumption for
                     this new node with the tree, but this would be accessing
                     an attribute on a tree which was *just constructed* (e.g.
                     plus(int(1), int(2)).value) which I think is unlikely.
                   -}
                   error("Must have a pair for an access in a well-typed grammar")
                 end,
               newe.encodedExpr);
  top.encodedFailure =
      if isPair(e.typerep)
      then newe.encodedFailure --no other failure points for pairs
      else map(\ ep::([Metaterm], Term) ->
                 case ep.2 of
                 | applicationTerm(nameTerm("$pair_c"),
                      consTermList(tree, singleTermList(node))) ->
                   ep.1 ++
                   [ termMetaterm(
                        buildApplication(
                           nameTerm(accessRelationName(treeTy,
                                       attrName)),
                           [tree, node,
                            nameTerm(attributeNotExistsName)])) ]
                 | _ ->
                   error("Must have a pair for an access in a well-typed grammar")
                 end,
               newe.encodedExpr) ++
           newe.encodedFailure;
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::PartiallyDecorated Expr  q::PartiallyDecorated QNameAttrOccur
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
  local attrName::String =
        case q of
        | qNameAttrOccur(at) ->
          encodeName(at.lookupAttribute.fullName)
        end;
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
                                    attrName)),
                        [tree, node,
                         buildApplication(nameTerm(attributeExistsName),
                                          [inhTerm])])) ],
                new(inhTerm) )
            | _ ->
              error("I don't think we can access on a non-pair")
            end,
          newe.encodedExpr);
  top.encodedFailure =
      map(\ ep::([Metaterm], Term) ->
            case ep.2 of
            | applicationTerm(nameTerm("$pair_c"),
                 consTermList(tree, singleTermList(node))) ->
              ep.1 ++
              [ termMetaterm(
                   buildApplication(
                      nameTerm(accessRelationName(treeTy,
                                  attrName)),
                      [tree, node,
                       nameTerm(attributeNotExistsName)])) ]
            | _ ->
              error("I don't think we can access on a non-pair")
            end,
          newe.encodedExpr) ++
      newe.encodedFailure;
}

aspect production errorDecoratedAccessHandler
top::Expr ::= e::PartiallyDecorated Expr  q::PartiallyDecorated QNameAttrOccur
{
  top.encodedExpr =
      error("Abella encoding not defined in the presence of errors");
  top.encodedFailure =
      error("Abella encoding not defined in the presence of errors");
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.encodedExpr = error("decorateExprWith not done " ++ top.location.filename ++ " " ++ toString(top.location.line) ++ ":" ++ toString(top.location.column));
  top.encodedFailure = error("decorateExprWith not done " ++ top.location.filename ++ " " ++ toString(top.location.line) ++ ":" ++ toString(top.location.column));
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
  top.encodedFailure = [];
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.encodedExpr = [([], nameTerm(falseName))];
  top.encodedFailure = [];
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
              case el1.2 of
              | nameTerm("$btrue") ->
                foldr(\ el2::([Metaterm], Term)
                        rest::[([Metaterm], Term)] ->
                        [( el1.1 ++ el2.1, el2.2 )] ++ rest,
                      [], e2.encodedExpr)
              | nameTerm("$bfalse") ->
                [(el1.1, nameTerm(falseName))]
              | varTerm(n, i) ->
                let trueReplaced::[Metaterm] =
                    replaceVar_list((n, i), nameTerm(trueName), el1.1)
                in
                  foldr(\ el2::([Metaterm], Term)
                          rest::[([Metaterm], Term)] ->
                          ( trueReplaced ++ el2.1, el2.2 )::rest,
                        [], e2.encodedExpr)
                end ++
                [( replaceVar_list((n, i), nameTerm(falseName), el1.1),
                   nameTerm(falseName) )]
              | _ ->
                error("Results of Boolean-typed encoding must be " ++
                      "either Boolean constants or varTerms")
              end ++ rest,
            [], e1.encodedExpr);
  top.encodedFailure =
      e1.encodedFailure ++
      foldr(\ el1::([Metaterm], Term) rest::[[Metaterm]] ->
              case el1.2 of
              | nameTerm("$bfalse") -> []
              | nameTerm("$btrue") ->
                map(\ l::[Metaterm] -> el1.1 ++ l, e2.encodedFailure)
              | varTerm(n, i) ->
                map(\ l::[Metaterm] ->
                      replaceVar_list((n, i), nameTerm(trueName), el1.1) ++ l,
                    e2.encodedFailure)
              | _ ->
                error("Results of Boolean-typed encoding must be " ++
                      "either Boolean constants or varTerms")
              end ++ rest,
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
              case el1.2 of
              | nameTerm("$bfalse") ->
                foldr(\ el2::([Metaterm], Term)
                        rest::[([Metaterm], Term)] ->
                        [( el1.1 ++ el2.1, el2.2 )] ++ rest,
                      [], e2.encodedExpr)
              | nameTerm("$btrue") ->
                [(el1.1, nameTerm(trueName))]
              | varTerm(n, i) ->
                let falseReplaced::[Metaterm] =
                    replaceVar_list((n, i), nameTerm(falseName), el1.1)
                in
                  foldr(\ el2::([Metaterm], Term)
                          rest::[([Metaterm], Term)] ->
                          ( falseReplaced ++ el2.1, el2.2 )::rest,
                        [], e2.encodedExpr)
                end ++
                [( replaceVar_list((n, i), nameTerm(trueName), el1.1),
                   nameTerm(trueName) )]
              | _ ->
                error("Results of Boolean-typed encoding must be " ++
                      "either Boolean constants or varTerms")
              end ++ rest,
            [], e1.encodedExpr);
  top.encodedFailure =
      e1.encodedFailure ++
      foldr(\ el1::([Metaterm], Term) rest::[[Metaterm]] ->
              case el1.2 of
              | nameTerm("$btrue") -> []
              | nameTerm("$bfalse") ->
                map(\l::[Metaterm] -> el1.1 ++ l, e2.encodedFailure)
              | varTerm(n, i) ->
                map(\ l::[Metaterm] ->
                      replaceVar_list((n, i), nameTerm(falseName), el1.1) ++ l,
                    e2.encodedFailure)
              | _ ->
                error("Results of Boolean-typed encoding must be " ++
                      "either Boolean constants or varTerms")
              end ++ rest,
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
              | varTerm(n, i) ->
                [ ( replaceVar_list((n, i), nameTerm(trueName), ep.1),
                    nameTerm(falseName) ),
                  ( replaceVar_list((n, i), nameTerm(falseName), ep.1),
                    nameTerm(trueName) ) ]
              | _ ->
                error("Results of Boolean-typed encoding must be " ++
                      "either Boolean constants or varTerms")
              end,
            [], e.encodedExpr);
  top.encodedFailure = e.encodedFailure;
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
  top.encodedFailure = newe1.encodedFailure ++ newe2.encodedFailure;
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
  top.encodedFailure = newe1.encodedFailure ++ newe2.encodedFailure;
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
  top.encodedFailure = newe1.encodedFailure ++ newe2.encodedFailure;
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
  top.encodedFailure = newe1.encodedFailure ++ newe2.encodedFailure;
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
  top.encodedFailure = newe1.encodedFailure ++ newe2.encodedFailure;
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
  top.encodedFailure = newe1.encodedFailure ++ newe2.encodedFailure;
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
                      | varTerm(n, i) ->
                        ( map(replaceVar((n, i), nameTerm("$btrue"), _),
                              ep1.1) ++ ep2.1, ep2.2 )::rest
                      | _ ->
                        error("Results of Boolean-typed encoding " ++
                              "must be either Boolean constants or" ++
                              " varTerms")
                      end,
                    [], e2.encodedExpr) ++
              foldr(\ ep3::([Metaterm], Term)
                      rest::[([Metaterm], Term)] ->
                      case ep1.2 of
                      | nameTerm("$bfalse") ->
                        ( ep1.1 ++ ep3.1, ep3.2 )::rest
                      | nameTerm("$btrue") -> rest
                      | varTerm(n, i) ->
                        ( map(replaceVar((n, i), nameTerm("$bfalse"), _),
                              ep1.1) ++ ep3.1, ep3.2 )::rest
                      | _ ->
                        error("Results of Boolean-typed encoding " ++
                              "must be either Boolean constants or" ++
                              " varTerms")
                      end,
                    [], e3.encodedExpr) ++ rest,
            [], e1.encodedExpr);
  top.encodedFailure =
      foldr(\ ep1::([Metaterm], Term) rest::[[Metaterm]] ->
              case ep1.2 of
              | nameTerm("$btrue") ->
                map(\ l::[Metaterm] -> ep1.1 ++ l, e2.encodedFailure)
              | nameTerm("$bfalse") ->
                map(\ l::[Metaterm] -> ep1.1 ++ l, e3.encodedFailure)
              | varTerm(n, i) ->
                let trueReplaced::[Metaterm] =
                    replaceVar_list((n, i), nameTerm(trueName), ep1.1)
                in
                let falseReplaced::[Metaterm] =
                    replaceVar_list((n, i), nameTerm(falseName), ep1.1)
                in
                  map(\ l::[Metaterm] -> trueReplaced ++ l,
                      e2.encodedFailure) ++
                  map(\ l::[Metaterm] -> falseReplaced ++ l,
                      e3.encodedFailure)
                end end
              | _ ->
                error("Results of Boolean-typed encoding must be " ++
                      "either Boolean constants or varTerms")
              end ++ rest,
            [], e1.encodedExpr) ++
      e1.encodedFailure;
}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.encodedExpr = [([], integerToIntegerTerm(toInteger(i.lexeme)))];
  top.encodedFailure = [];
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.encodedExpr = error("floatConst not done yet");
  top.encodedFailure = error("floatConst not done yet");
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
  top.encodedFailure =
      e1.encodedFailure ++
      foldr(\ ep1::([Metaterm], Term) rest::[[Metaterm]]->
              map(\ l::[Metaterm] -> ep1.1 ++ l,
                    e2.encodedFailure) ++ rest,
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
  top.encodedFailure =
      e1.encodedFailure ++
      foldr(\ ep1::([Metaterm], Term) rest::[[Metaterm]]->
              map(\ l::[Metaterm] -> ep1.1 ++ l,
                    e2.encodedFailure) ++ rest,
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
  top.encodedFailure =
      e1.encodedFailure ++
      foldr(\ ep1::([Metaterm], Term) rest::[[Metaterm]]->
              map(\ l::[Metaterm] -> ep1.1 ++ l,
                    e2.encodedFailure) ++ rest,
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
  top.encodedFailure =
      foldr(\ ep1::([Metaterm], Term) rest::[[Metaterm]] ->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[[Metaterm]] ->
                      ( ep1.1 ++ ep2.1 ++
                        [eqMetaterm(ep2.2,
                            integerToIntegerTerm(0))] )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr) ++
      e1.encodedFailure ++
      foldr(\ ep1::([Metaterm], Term) rest::[[Metaterm]] ->
              map(\ l::[Metaterm] -> ep1.1 ++ l,
                    e2.encodedFailure) ++ rest,
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
  top.encodedFailure =
      foldr(\ ep1::([Metaterm], Term) rest::[[Metaterm]] ->
              foldr(\ ep2::([Metaterm], Term)
                      rest::[[Metaterm]] ->
                      ( ep1.1 ++ ep2.1 ++
                        [eqMetaterm(ep2.2,
                            integerToIntegerTerm(0))] )::rest,
                    [], e2.encodedExpr) ++ rest,
            [], e1.encodedExpr) ++
      e1.encodedFailure ++
      foldr(\ ep1::([Metaterm], Term) rest::[[Metaterm]] ->
              map(\ l::[Metaterm] -> ep1.1 ++ l,
                    e2.encodedFailure) ++ rest,
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
  top.encodedFailure = e.encodedFailure;
}

aspect production stringConst
top::Expr ::= s::String_t
{
  --remove quotation marks from start and end
  local contents::String =
        substring(1, length(s.lexeme) - 1, s.lexeme);
  top.encodedExpr = [([], stringToAbellaTerm(contents))];
  top.encodedFailure = [];
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
  top.encodedFailure =
      newe1.encodedFailure ++
      foldr(\ ep1::([Metaterm], Term) rest::[[Metaterm]]->
              map(\ l::[Metaterm] -> ep1.1 ++ l,
                    newe2.encodedFailure) ++ rest,
            [], newe1.encodedExpr);
}

aspect production emptyList
top::Expr ::= '[' ']'
{
  top.encodedExpr = [ ([], nilTerm()) ];
  top.encodedFailure = [];
}

aspect production selector
top::Expr ::= tuple::Expr '.' a::IntConst
{
  local acc::Integer = toInteger(a.lexeme);
  -- Copied from original
  local typ::Type = performSubstitution(tuple.typerep, tuple.upSubst);
  local typlen::Integer = case typ of
    | decoratedType(t, _) -> length(t.tupleElems)
    | t -> length(typ.tupleElems)
    end;

  --Check if the current term has enough explicit members
  local longEnoughPair::(Maybe<Term> ::= Term Integer) =
        \ t::Term a::Integer ->
          case t of
          | applicationTerm(nameTerm("$pair_c"),
               consTermList(fst, singleTermList(snd))) ->
            if a == 1
            then just(fst)
            else longEnoughPair(snd, a - 1)
          | applicationTerm(nameTerm("$pair_c"),
               consTermList(fst, consTermList(snd,
                  nilTermList()))) ->
            if a == 1
            then just(fst)
            else longEnoughPair(snd, a - 1)
          | _ -> nothing()
          end;
  --Generate the correct length for the type and the result
  local generatePairTerm::((Term, Term) ::= Integer Integer) =
        \ tylen::Integer access::Integer ->
          let newvar::Term = varTerm("P", genInt())
          in
            if tylen == 1
            then --if access == 0, good, else ignored
                 (newvar, newvar)
            else let sub::(Term, Term) =
                     generatePairTerm(tylen - 1, access - 1)
                 in
                   ( buildApplication(nameTerm(pairConstructorName),
                        [newvar, sub.1]),
                     if access == 1
                     then newvar
                     else sub.2 )
                 end
          end;
  top.encodedExpr =
      map(\ p::([Metaterm], Term) ->
            case longEnoughPair(p.2, acc) of
            | just(tm) -> (p.1, tm)
            | nothing() ->
              case generatePairTerm(typlen, acc) of
              | (pr, res) -> ( p.1 ++ [eqMetaterm(p.2, pr)], res )
              end
            end,
          tuple.encodedExpr);
  top.encodedFailure = tuple.encodedFailure;
}




attribute
   encodedArgs, encodedFailure, encodingEnv, top
occurs on Exprs;

aspect production exprsEmpty
top::Exprs ::=
{
  top.encodedArgs = [([], [])];
  top.encodedFailure = [];
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  top.encodedArgs =
      map(\ ep::([Metaterm], Term) -> (ep.1, [ep.2]), e.encodedExpr);
  top.encodedFailure = e.encodedFailure;
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  e1.encodingEnv = top.encodingEnv;
  e2.encodingEnv = top.encodingEnv;
  e1.top = top.top;
  e2.top = top.top;
  top.encodedArgs =
      foldr(\ e1p::([Metaterm], Term) rest1::[([Metaterm], [Term])] ->
              foldr(\ e2p::([Metaterm], [Term])
                      rest2::[([Metaterm], [Term])] ->
                      ( e1p.1 ++ e2p.1, e1p.2::e2p.2 )::rest2,
                    rest1, e2.encodedArgs),
            [], e1.encodedExpr);
  top.encodedFailure =
      e1.encodedFailure ++
      foldr(\ e1p::([Metaterm], Term) rest::[[Metaterm]] ->
              map(\ l::[Metaterm] -> e1p.1 ++ l,
                    e2.encodedFailure) ++ rest,
            [], e1.encodedExpr);
}




attribute
   encodedArgs, encodedFailure, encodingEnv, top
occurs on AppExprs;

attribute
   encodedExpr, encodedFailure, encodingEnv, top
occurs on AppExpr;

aspect production missingAppExpr
top::AppExpr ::= '_'
{
  top.encodedExpr = error("missingAppExpr not done");
  top.encodedFailure = error("missingAppExpr not done");
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  top.encodedExpr =
      if isNonterminal(top.appExprTyperep)
      then --give only the term if not looking for decorated
           if isDecorated(top.appExprTyperep)
           then e.encodedExpr
           else map(\ p::([Metaterm], Term) ->
                      ( p.1,
                        case p.2 of
                        | applicationTerm(nameTerm("$pair_c"),
                             consTermList(tree, _)) ->
                          tree
                        | _ -> p.2
                        end ),
                    e.encodedExpr)
      else e.encodedExpr;
  top.encodedFailure = e.encodedFailure;
}

aspect production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  es.encodingEnv = top.encodingEnv;
  e.encodingEnv = top.encodingEnv;
  es.top = top.top;
  e.top = top.top;
  top.encodedArgs =
      foldr(\ ep::([Metaterm], Term) rest1::[([Metaterm], [Term])] ->
              foldr(\ esp::([Metaterm], [Term])
                      rest2::[([Metaterm], [Term])] ->
                      ( esp.1 ++ ep.1, esp.2 ++ [ep.2] )::rest2,
                    rest1, es.encodedArgs),
            [], e.encodedExpr);
  top.encodedFailure =
      es.encodedFailure ++
      foldr(\ ep::([Metaterm], [Term]) rest::[[Metaterm]] ->
              map(\ l::[Metaterm] -> ep.1 ++ l,
                    e.encodedFailure) ++ rest,
            [], es.encodedArgs);
}

aspect production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  top.encodedArgs =
      map(\ ep::([Metaterm], Term) -> (ep.1, [ep.2]), e.encodedExpr);
  top.encodedFailure = e.encodedFailure;
}

aspect production emptyAppExprs
top::AppExprs ::=
{
  top.encodedArgs = [([], [])];
  top.encodedFailure = [];
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
top::Expr ::= e::PartiallyDecorated Expr
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
  top.encodedFailure = newe.encodedFailure;
}

