grammar silver:compiler:extension:abella_compilation:encoding;


type Defs = silver:compiler:extension:abella_compilation:abella:Defs;


aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  local fullProdName::String = buildEncodedName(top.grammarName, id.name);
  body.encodingEnv = ns.encodingEnv_up;
  body.top = (ns.top_up.1, ns.top_up.2, ns.top_up.3, fullProdName, ns.childNames);
  body.treeTerm =
       applicationTerm(nameTerm(nameToProd(fullProdName)), ns.treeTerm_up);
  body.nodetreeTerm = ns.nodetreeTerm_up;
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  local fullProdName::String = colonsToEncoded(id.lookupValue.fullName);
  body.encodingEnv = ns.encodingEnv_up;
  body.top = (ns.top_up.1, ns.top_up.2, ns.top_up.3, fullProdName, ns.childNames);
  body.treeTerm =
       applicationTerm(nameTerm(nameToProd(fullProdName)), ns.treeTerm_up);
  body.nodetreeTerm = ns.nodetreeTerm_up;
}

aspect production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production'
               ns::AspectDefaultProductionSignature body::ProductionBody
{
  top.localAttrs := [];
  top.localAttrDefs := [];
  top.synAttrEqInfo := [];
  top.inhAttrEqInfo := [];
}


--Process equation information into a set of local equation relations
--We can build the full relations at once because locals are local to
--   the current instance of the production and only the one production.
function buildLocalEqRelations
[Definition] ::= treeTy::AbellaType prod::String numChildren::Integer
                 tree::Term treeTerm::Term nodetreeTerm::Term
                 eqs::[(String, [[Metaterm]])]
{
  local localGroups::[(String, [[Metaterm]])] =
        let sorted::[(String, [[Metaterm]])] =
            sortBy(\ p1::(String, [[Metaterm]])
                     p2::(String, [[Metaterm]]) -> p1.1 <= p2.1,
                   eqs)
        in
        let grouped::[[(String, [[Metaterm]])]] =
            groupBy(\ p1::(String, [[Metaterm]])
                      p2::(String, [[Metaterm]]) -> p1.1 == p2.1,
                    sorted)
        in
          map(\ l::[(String, [[Metaterm]])] ->
                ( head(l).1,
                  combineBodies(map(snd(_), l)) ),
              grouped)
        end end;
  local cleanedLocals::[(String, [[Metaterm]])] =
        map(\ p::(String, [[Metaterm]]) ->
              ( p.1, unifyBodies(p.2) ),
            localGroups);
  local filledLocals::[(String, Term, [Metaterm])] =
        map(\ p::(String, [[Metaterm]]) ->
              let rel::String = localEquationName(p.1, prod)
              in
              let clauseHead::Term =
                  buildApplication(nameTerm(rel),
                    [tree, treeTerm, nodetreeTerm])
              in
              let filled::(Term, [Metaterm]) =
                  fillVars(clauseHead,
                           map(\ l::[Metaterm] ->
                                 foldl(andMetaterm(_, _), head(l),
                                       tail(l)),
                               p.2))
              in
                ( p.1, filled.1, filled.2 )
              end end end,
            cleanedLocals);
  return
      map(\ p::(String, Term, [Metaterm]) ->
            let rel::String = localEquationName(p.1, prod)
            in
              definition([( rel,
                            arrowAbellaType(treeTy,
                            arrowAbellaType(treeTy,
                            arrowAbellaType(nodeTreeType,
                                            nameAbellaType("prop")))) )],
                 --not-this-production clause
                 let childNames::[String] =
                     generateNames_n("C", numChildren)
                 in
                 let otherClause::DefClause =
                     ruleClause(
                        termMetaterm(
                           buildApplication(nameTerm(rel),
                              [nameTerm("TreeName"), nameTerm("Term"),
                               buildApplication(
                                  nameTerm(nodeTreeConstructorName(treeTy)),
                                  [nameTerm("Node"), nameTerm("CL")])])),
                        andMetaterm(
                           --tree = prod -> false
                           impliesMetaterm(
                              if null(childNames) --no children, no binders
                              then eqMetaterm(nameTerm("Term"),
                                      nameTerm(nameToProd(prod)))
                              else bindingMetaterm(existsBinder(),
                                      map(pair(_, nothing()), childNames),
                                      eqMetaterm(nameTerm("Term"),
                                         buildApplication(
                                            nameTerm(nameToProd(prod)),
                                            map(nameTerm(_), childNames)))),
                              falseMetaterm()),
                           --local has no value
                           termMetaterm(
                              buildApplication(
                                 nameTerm(
                                    localAccessRelationName(treeTy,
                                       p.1, prod)),
                                 [nameTerm("TreeName"), nameTerm("Node"),
                                  nameTerm(attributeNotExistsName)]))))
                 in
                    if null(p.3)
                    then singleAbellaDefs(otherClause)
                    else --build all clauses together
                       let clauses::[DefClause] =
                           map(ruleClause(termMetaterm(p.2), _), p.3)
                       in
                       let rev::[DefClause] = reverse(clauses)
                       in
                         foldr(consAbellaDefs(_, _),
                               singleAbellaDefs(head(rev)),
                               otherClause::reverse(tail(rev)))
                        end end
                 end end)
            end,
          filledLocals);
}




attribute
   localAttrs, top, encodingEnv, treeTerm, nodetreeTerm,
   synAttrEqInfo, inhAttrEqInfo, localAttrDefs
occurs on ProductionBody;

attribute
   localAttrs, top, encodingEnv, treeTerm, nodetreeTerm,
   synAttrEqInfo, inhAttrEqInfo, localAttrDefs
occurs on ProductionStmts;


aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  stmts.top = top.top;
  stmts.treeTerm = top.treeTerm;
  stmts.nodetreeTerm = top.nodetreeTerm;
}

aspect production productionStmtsNil
top::ProductionStmts ::= 
{
}

aspect production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmt
{
  h.top = top.top;
  h.treeTerm = top.treeTerm;
  h.nodetreeTerm = top.nodetreeTerm;
  t.top = top.top;
  t.treeTerm = top.treeTerm;
  t.nodetreeTerm = top.nodetreeTerm;
}

----------

attribute
   localAttrs, top, encodingEnv, treeTerm, nodetreeTerm,
   synAttrEqInfo, inhAttrEqInfo, localAttrDefs
occurs on ProductionStmt;


aspect default production
top::ProductionStmt ::=
{
}


aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  h.treeTerm = top.treeTerm;
  h.nodetreeTerm = top.nodetreeTerm;
  t.treeTerm = top.treeTerm;
  t.nodetreeTerm = top.nodetreeTerm;
}

aspect production errorProductionStmt
top::ProductionStmt ::= e::[Message]
{
}

----------------------------------------------------------------------

aspect production attachNoteStmt
top::ProductionStmt ::= 'attachNote' note::Expr ';'
{
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::TypeExpr ';'
{
  top.localAttrs <-
      if isNonterminal(te.typerep)
      then [(a.name, [(top.top.4,
                       functorAbellaType(
                       functorAbellaType(pairType,
                                         te.typerep.abellaType),
                                         nodeTreeType))])]
      else [(a.name, [(top.top.4, te.typerep.abellaType)])];
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  local clauseHead::Term =
        buildApplication(
           nameTerm(equationName("forward", top.top.3) ++ name_sep ++
              encodeName(top.grammarName)),
           [top.top.1, top.treeTerm, top.nodetreeTerm]);
  local accessFwd::(Metaterm ::= Term) =
        \ result::Term ->
          termMetaterm(
             buildApplication(
                nameTerm(accessRelationName(top.top.3, "forward")),
                [top.top.1, nodetreeToNode(top.nodetreeTerm),
                 result]));
  local fwdPair::([Metaterm] ::= Term) =
        \ structure::Term ->
          let nameTm::Term = varTerm("Fwd", genInt())
          in
            [
             --access forward:  (tree name, node tree)
             accessFwd(
                buildApplication(
                   nameTerm(attributeExistsName),
                   [buildApplication(
                       nameTerm(pairConstructorName),
                       [nameTm,
                        buildApplication(
                           nameTerm(nodeTreeConstructorName(top.top.3)),
                           [varTerm("FwdNode", genInt()),
                            varTerm("FwdCL", genInt())])])])),
             --forward name ~ structure
             termMetaterm(
                buildApplication(
                   nameTerm(typeToStructureEqName(top.top.3)),
                   [nameTm, structure]))
            ]
          end;
  top.synAttrEqInfo <-
      [ ( "forward", top.top.3, top.top.4, clauseHead,
          --Failure to define
          map(\ l::[Metaterm] ->
                accessFwd(nameTerm(attributeNotExistsName))::l,
              e.encodedFailure) ++
          --Successful definition
          map(\ p::([Metaterm], Term) ->
                case p.2 of
                  --decorated tree, coming out, so just take structure
                | applicationTerm(nameTerm(pairC), args)
                  when pairC == pairConstructorName ->
                  fwdPair(head(args.argList))
                  --undecorated tree
                | _ -> fwdPair(p.2)
                end ++ p.1,
              e.encodedExpr) )
      ];
}

aspect production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
}

aspect production errorAttributeDef
top::ProductionStmt ::= msg::[Message] dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  local attrName::String =
        case attr of
        | qNameAttrOccur(at) ->
          encodeName(at.lookupAttribute.fullName)
        end;
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  local clauseHead::Term =
        buildApplication(
           nameTerm(equationName(attrName, top.top.3) ++
              name_sep ++ encodeName(top.grammarName)),
           [top.top.1, top.treeTerm, top.nodetreeTerm]);
  --synthesized can only be defined on root
  top.synAttrEqInfo <-
      [ (attrName, top.top.3, top.top.4, clauseHead,
         map(\ l::[Metaterm] ->
               termMetaterm(
                  buildApplication(
                     nameTerm(accessRelationName(top.top.3,
                                           attrName)),
                     [top.top.1,
                      nodetreeToNode(top.nodetreeTerm),
                      nameTerm(attributeNotExistsName)]))::l,
             e.encodedFailure) ++
         map(\ p::([Metaterm], Term) ->
               termMetaterm(
                  buildApplication(
                     nameTerm(accessRelationName(top.top.3,
                                           attrName)),
                     [top.top.1,
                      nodetreeToNode(top.nodetreeTerm),
                      buildApplication(nameTerm(attributeExistsName),
                                       [p.2])]))::p.1,
             e.encodedExpr) )];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  local attrName::String =
        case attr of
        | qNameAttrOccur(at) ->
          encodeName(at.lookupAttribute.fullName)
        end;
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  local tree::(Term, Term) =
        findAssociated(dl.name, top.encodingEnv).fromJust;
  local treeTy::AbellaType = dl.typerep.abellaType;
  local indexName::String =
      case dl of
      | localDefLHS(q) ->
        inhChildEquationName_localIndex(q.name)
      | forwardDefLHS(_) -> "forward"
      | childDefLHS(q) ->
        "child" ++ toString(positionOf(q.name, top.top.5))
      | _ -> error("Cannot set inh on anything else")
      end;
  local clauseHead::Term =
        buildApplication(
           nameTerm(inhChildEquationName(attrName, top.top.3,
                       top.top.4, indexName)),
           [top.top.1, top.treeTerm, top.nodetreeTerm]);
  --Create the access of the tree on which the attr is being set
  --Term arg is the (tree, node tree) which is the tree on which attr
  --   is being set
  --List result to handle exists or not like Maybe, but easier to use
  --   in other places
  local accessToSet::([Metaterm] ::= Term) =
        \ treePair::Term ->
          case dl of
          | localDefLHS(q) ->
            [termMetaterm(
                buildApplication(
                   nameTerm(localAccessRelationName(top.top.3,
                               q.name, top.top.4)),
                   [top.top.1, top.top.2, treePair]))]
          | forwardDefLHS(_) ->
            [termMetaterm(
                buildApplication(
                   nameTerm(accessRelationName(top.top.3, "forward")),
                   [top.top.1, top.top.2, treePair]))]
          | _ -> []
          end;
  --appropriate access of the attribute to "set" it
  --[Metaterm] because local/forward require accessing the local/forward
  --   before setting it
  --Term arg is the attribute value from the access
  local attrAccess::([Metaterm] ::= Term) =
        let intIndex::Integer = genInt()
        in
          \ result::Term ->
            case dl of
            | localDefLHS(q) ->
              accessToSet(
                 buildApplication(
                    nameTerm(attributeExistsName),
                       [buildApplication(
                           nameTerm(pairConstructorName),
                           [varTerm(capitalize(q.name), intIndex),
                            buildApplication(
                               nameTerm(nodeTreeConstructorName(treeTy)),
                               [varTerm(capitalize(q.name) ++ "Node",
                                        intIndex),
                                varTerm(capitalize(q.name) ++ "CL",
                                        intIndex)])])])) ++
              [termMetaterm(
                  buildApplication(
                     nameTerm(accessRelationName(treeTy, attrName)),
                     [varTerm(capitalize(q.name), intIndex),
                      varTerm(capitalize(q.name) ++ "Node", intIndex),
                      result]))]
            | forwardDefLHS(_) ->
              accessToSet(
                 buildApplication(
                    nameTerm(attributeExistsName),
                       [buildApplication(
                           nameTerm(pairConstructorName),
                           [varTerm("Fwd", intIndex),
                            buildApplication(
                               nameTerm(nodeTreeConstructorName(treeTy)),
                               [varTerm("FwdNode", intIndex),
                                varTerm("FwdCL", intIndex)])])])) ++
              [termMetaterm(
                  buildApplication(
                     nameTerm(accessRelationName(treeTy, attrName)),
                     [varTerm("Fwd", intIndex),
                      varTerm("FwdNode", intIndex), result]))]
            | childDefLHS(q) ->
              [termMetaterm(
                  buildApplication(
                     nameTerm(accessRelationName(treeTy, attrName)),
                     [tree.1, tree.2, result]))]
            | _ -> error("Cannot set inh on anything else")
            end
        end;
  top.inhAttrEqInfo <-
      [ (attrName, indexName, top.top.3, top.top.4, clauseHead,
         --tree on which attr is being set does not exist
         case dl of
         | childDefLHS(_) -> []
         | _ -> [accessToSet(nameTerm(attributeNotExistsName))]
         end ++
         --failure to create a value
         map(\ l::[Metaterm] ->
               attrAccess(nameTerm(attributeNotExistsName)) ++ l,
             e.encodedFailure) ++
         --actual value
         map(\ p::([Metaterm], Term) ->
               attrAccess(
                  buildApplication(nameTerm(attributeExistsName),
                                   [p.2])) ++ p.1,
             e.encodedExpr),
         --not this prod
         ruleClause(
            termMetaterm(
               buildApplication(
                  nameTerm(inhChildEquationName(attrName, top.top.3,
                              top.top.4, indexName)),
                  [nameTerm("TreeName"), nameTerm("Term"),
                   nameTerm("NodeTree")])),
            impliesMetaterm(
               if null(top.top.5) --no children, no bindings
               then eqMetaterm(nameTerm("Term"),
                       nameTerm(nameToProd(top.top.4)))
               else bindingMetaterm(existsBinder(),
                       map(pair(_, nothing()), top.top.5),
                       eqMetaterm(nameTerm("Term"),
                          buildApplication(
                             nameTerm(nameToProd(top.top.4)),
                             map(nameTerm, top.top.5)))),
               falseMetaterm()))) ];
}

aspect production errorDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
}

aspect production childDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
}

aspect production lhsDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
}

aspect production localDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
}

aspect production forwardDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
}

----- done with DefLHS

aspect production valueEq
top::ProductionStmt ::= val::QName '=' e::Expr ';'
{
}

aspect production errorValueDef
top::ProductionStmt ::= val::PartiallyDecorated QName  e::Expr
{
}

aspect production localValueDef
top::ProductionStmt ::= val::PartiallyDecorated QName  e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  local treeTy::AbellaType = e.typerep.abellaType;
  local localStructureVar::Term =
        varTerm(capitalize(val.name), genInt());
  local localNode::Term = varTerm("Node", genInt());
  local localNodetree::Term =
        buildApplication(nameTerm(nodeTreeConstructorName(treeTy)),
           [localNode, varTerm("ChildList", genInt())]);
  --
  local relStr::String =
        localEquationName(val.name, top.top.4);
  local rel::Term = nameTerm(relStr);
  --
  local wpd::Metaterm =
        termMetaterm(
           buildApplication(
              nameTerm(wpdTypeName(treeTy)),
              [localStructureVar, localNodetree]));
  --Build an access of the local, given the access result
  local accessMetaterm::(Metaterm ::= Term) =
        \ result::Term ->
          termMetaterm(
             buildApplication(
                 nameTerm(localAccessRelationName(top.top.3,
                    val.name, top.top.4)),
                 [top.top.1, top.top.2, result]));
  local failureDefs::[[Metaterm]] =
        map(\ l::[Metaterm] ->
              accessMetaterm(nameTerm(attributeNotExistsName))::l,
            e.encodedFailure);
  local successDefs::[[Metaterm]] =
        map(\ p::([Metaterm], Term) ->
              if isNonterminal(e.typerep)
              then accessMetaterm(
                      buildApplication(nameTerm(attributeExistsName),
                         [buildApplication(nameTerm(pairConstructorName),
                             [localStructureVar, localNodetree])]))::
                   --structure eq for local
                   termMetaterm(
                      buildApplication(
                         nameTerm(typeToStructureEqName(
                                     e.typerep.abellaType)),
                         [localStructureVar,
                          case p.2 of
                          | applicationTerm(nameTerm("$pair_c"),
                               consTermList(tree, _)) -> tree
                          | x -> x
                          end]))::wpd::p.1
              else accessMetaterm(
                      buildApplication(nameTerm(attributeExistsName),
                         [p.2]))::p.1,
            e.encodedExpr);
  --
  local jointFailureSuccess::[[Metaterm]] =
        failureDefs ++ successDefs;
  local cleanedFailureSuccess::[[Metaterm]] =
        unifyBodies(jointFailureSuccess);
  local andedBodies::[Metaterm] =
        map(\ l::[Metaterm] -> foldl(andMetaterm, head(l), tail(l)),
            cleanedFailureSuccess);
  local thisProdHead::Term =
        buildApplication(rel,
           [top.top.1, top.treeTerm, top.nodetreeTerm]);
  local filledHeadBodies::(Term, [Metaterm]) =
        fillVars(thisProdHead, andedBodies);
  local thisProdClauses::[DefClause] =
        map(\ body::Metaterm ->
              ruleClause(termMetaterm(filledHeadBodies.1), body),
            filledHeadBodies.2);
  --
  local notThisProd::Defs =
        singleAbellaDefs(
           ruleClause(
              termMetaterm(
                 buildApplication(rel,
                    [nameTerm("TreeName"), nameTerm("Term"),
                     buildApplication(
                        nameTerm(nodeTreeConstructorName(top.top.3)),
                        [nameTerm("Node"), nameTerm("CL")])])),
              andMetaterm(
                 --Not this prod
                 impliesMetaterm(
                    if null(top.top.5) --no children, no bindings
                    then eqMetaterm(nameTerm("Term"),
                            nameTerm(nameToProd(top.top.4)))
                    else bindingMetaterm(existsBinder(),
                            map(pair(_, nothing()), top.top.5),
                            eqMetaterm(nameTerm("Term"),
                               buildApplication(
                                  nameTerm(nameToProd(top.top.4)),
                                  map(nameTerm, top.top.5)))),
                    falseMetaterm()),
                 --No value in local
                 termMetaterm(
                    buildApplication(
                        nameTerm(localAccessRelationName(top.top.3,
                           val.name, top.top.4)),
                        [nameTerm("TreeName"), nameTerm("Node"),
                         nameTerm(attributeNotExistsName)])))));

  local allDefs::Defs =
        foldr(consAbellaDefs, notThisProd, thisProdClauses);
  local fullDef::Definition =
        definition(
           [(relStr, equationRelType(top.top.3))],
           allDefs);
  top.localAttrDefs <- [fullDef];
}

