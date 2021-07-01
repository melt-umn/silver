grammar silver:compiler:extension:abella_compilation;


aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  body.encodingEnv = ns.encodingEnv_up;
  body.top = (ns.top_up.1, ns.top_up.2, ns.top_up.3, id.name);
  body.treeTerm =
       applicationTerm(nameTerm(nameToProd(id.name)), ns.treeTerm_up);
  body.nodetreeTerm = ns.nodetreeTerm_up;
  --
  top.localAttrDefs <-
      buildLocalEqRelations(ns.top_up.3, id.name, ns.argLength,
                       ns.top_up.1, body.treeTerm, ns.nodetreeTerm_up,
                       body.localAttrEqInfo);
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  body.encodingEnv = ns.encodingEnv_up;
  body.top = (ns.top_up.1, ns.top_up.2, ns.top_up.3, id.name);
  body.treeTerm =
       applicationTerm(nameTerm(nameToProd(id.name)), ns.treeTerm_up);
  body.nodetreeTerm = ns.nodetreeTerm_up;
  --
  top.localAttrDefs <-
      buildLocalEqRelations(ns.top_up.3, id.name, ns.argLength,
                       ns.top_up.1, body.treeTerm, ns.nodetreeTerm_up,
                       body.localAttrEqInfo);
}

aspect production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' 
               lhs::Name '::' te::TypeExpr '::=' body::ProductionBody 
{
  top.localAttrs := [];
  top.localAttrDefs := [];
  top.attrEqInfo := [];
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
                              bindingMetaterm(existsBinder(),
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
   attrEqInfo, localAttrEqInfo
occurs on ProductionBody;

attribute
   localAttrs, top, encodingEnv, treeTerm, nodetreeTerm,
   attrEqInfo, localAttrEqInfo
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
   attrEqInfo, localAttrEqInfo
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
  --Host is missing equation for onNt
  local newte::TypeExpr = te;
  newte.config = top.config;
  newte.onNt = error("I don't know what onNt is (localAttributeDcl)");
  newte.grammarName = top.grammarName;
  newte.env = top.env;
  newte.flowEnv = top.flowEnv;
  --
  top.localAttrs <-
      if isNonterminal(newte.typerep)
      then [(a.name, [(top.top.4,
                       functorAbellaType(
                       functorAbellaType(pairType,
                                         newte.typerep.abellaType),
                                         nodeTreeType))])]
      else [(a.name, [(top.top.4, newte.typerep.abellaType)])];
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
}

aspect production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
}

aspect production errorAttributeDef
top::ProductionStmt ::= msg::[Message] dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  local clauseHead::Term =
        buildApplication(
           nameTerm(equationName(shortestName(attr.name), top.top.3) ++
              "__" ++ shortestName(top.grammarName)),
           [top.top.1, top.treeTerm, top.nodetreeTerm]);
  --synthesized can only be defined on root
  top.attrEqInfo <-
      [ (shortestName(attr.name), top.top.3, top.top.4, clauseHead,
         map(\ l::[Metaterm] ->
               termMetaterm(
                  buildApplication(
                     nameTerm(accessRelationName(top.top.3,
                                           shortestName(attr.name))),
                     [top.top.1,
                      nodetreeToNode(top.nodetreeTerm),
                      nameTerm(attributeNotExistsName)]))::l,
             e.encodedFailure) ++
         map(\ p::([Metaterm], Term) ->
               termMetaterm(
                  buildApplication(
                     nameTerm(accessRelationName(top.top.3,
                                           shortestName(attr.name))),
                     [top.top.1,
                      nodetreeToNode(top.nodetreeTerm),
                      buildApplication(nameTerm(attributeExistsName),
                                       [p.2])]))::p.1,
             e.encodedExpr) )];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  local tree::(Term, Term) =
        findAssociated(dl.name, top.encodingEnv).fromJust;
  local treeTy::AbellaType = dl.typerep.abellaType;
  local clauseHead::Term =
        buildApplication(
           nameTerm(equationName(shortestName(attr.name), top.top.3) ++
              "__" ++ shortestName(top.grammarName)),
           [top.top.1, top.treeTerm, top.nodetreeTerm]);
  --attrs set on locals and forwards need to be handled differently
  top.attrEqInfo <-
      case dl of
      | localDefLHS(_) -> []
      | forwardDefLHS(_) -> []
      | _ ->
        [ (shortestName(attr.name), top.top.3, top.top.4, clauseHead,
           map(\ l::[Metaterm] ->
                 termMetaterm(
                    buildApplication(
                       nameTerm(accessRelationName(treeTy,
                                             shortestName(attr.name))),
                       [tree.1, tree.2,
                        nameTerm(attributeNotExistsName)]))::l,
               e.encodedFailure) ++
           map(\ p::([Metaterm], Term) ->
                 termMetaterm(
                    buildApplication(
                       nameTerm(accessRelationName(treeTy,
                                             shortestName(attr.name))),
                       [tree.1, tree.2,
                        buildApplication(nameTerm(attributeExistsName),
                                         [p.2])]))::p.1,
               e.encodedExpr) )]
      end;
  --
  local localStructureVar::Term =
        varTerm(capitalize(shortestName(dl.name)), genInt());
  local localNode::Term = varTerm("Node", genInt());
  local localNodetree::Term =
        buildApplication(
           nameTerm(nodeTreeConstructorName(treeTy)),
           [localNode, varTerm("CL", genInt())]);
  top.localAttrEqInfo <-
      case dl of
      | localDefLHS(_) ->
        [( shortestName(dl.name),
           --In order to get any cases where the local is not defined
           --   when we combine the definition and inh defs, we need
           --   to include a case where it is not defined here
           [ [termMetaterm(
                 buildApplication(
                    nameTerm(localAccessRelationName(top.top.3,
                       shortestName(dl.name), top.top.4)),
                    [top.top.1, top.top.2,
                     nameTerm(attributeNotExistsName)]))] ] ++
           map(\ l::[Metaterm] ->
                 termMetaterm(
                    buildApplication(
                       nameTerm(localAccessRelationName(top.top.3,
                          shortestName(dl.name), top.top.4)),
                       [top.top.1, top.top.2,
                        buildApplication(
                           nameTerm(attributeExistsName),
                           [buildApplication(
                               nameTerm(pairConstructorName),
                               [localStructureVar, localNodetree])])]))::
                 termMetaterm(
                    buildApplication(
                       nameTerm(accessRelationName(treeTy,
                                   shortestName(attr.name))),
                       [localStructureVar, localNode,
                        nameTerm(attributeNotExistsName)]))::l,
               e.encodedFailure) ++
           map(\ p::([Metaterm], Term) ->
                 termMetaterm(
                    buildApplication(
                       nameTerm(localAccessRelationName(top.top.3,
                          shortestName(dl.name), top.top.4)),
                       [top.top.1, top.top.2,
                        buildApplication(
                           nameTerm(attributeExistsName),
                           [buildApplication(
                               nameTerm(pairConstructorName),
                               [localStructureVar, localNodetree])])]))::
                 termMetaterm(
                    buildApplication(
                       nameTerm(accessRelationName(treeTy,
                                   shortestName(attr.name))),
                       [localStructureVar, localNode,
                        buildApplication(
                           nameTerm(attributeExistsName), [p.2])]))::
                 p.1,
               e.encodedExpr) )]
      | _ -> []
      end;
}

aspect production errorDefLHS
top::DefLHS ::= q::Decorated QName
{
}

aspect production childDefLHS
top::DefLHS ::= q::Decorated QName
{
}

aspect production lhsDefLHS
top::DefLHS ::= q::Decorated QName
{
}

aspect production localDefLHS
top::DefLHS ::= q::Decorated QName
{
}

aspect production forwardDefLHS
top::DefLHS ::= q::Decorated QName
{
}

----- done with DefLHS

aspect production valueEq
top::ProductionStmt ::= val::QName '=' e::Expr ';'
{
}

aspect production errorValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  e.encodingEnv = top.encodingEnv;
  e.top = top.top;
  local treeTy::AbellaType = e.typerep.abellaType;
  local localStructureVar::Term =
        varTerm(capitalize(shortestName(val.name)), genInt());
  local localNode::Term = varTerm("Node", genInt());
  local localNodetree::Term =
        buildApplication(nameTerm(nodeTreeConstructorName(treeTy)),
           [localNode, varTerm("ChildList", genInt())]);
  top.localAttrEqInfo <-
      [( shortestName(val.name),
         --Failure definitions
         map(\ l::[Metaterm] ->
               termMetaterm(
                  buildApplication(
                     nameTerm(localAccessRelationName(top.top.3,
                        shortestName(val.name), top.top.4)),
                     [top.top.1, top.top.2,
                      nameTerm(attributeNotExistsName)]))::l,
             e.encodedFailure) ++
         --Successful definitions
         map(\ p::([Metaterm], Term) ->
               if isNonterminal(e.typerep)
               then termMetaterm(
                       buildApplication(
                          nameTerm(localAccessRelationName(top.top.3,
                             shortestName(val.name), top.top.4)),
                          [top.top.1, top.top.2,
                           buildApplication(
                              nameTerm(attributeExistsName),
                              [buildApplication(
                                  nameTerm(pairConstructorName),
                                  [localStructureVar,
                                   localNodetree])])]))::
                    --structure eq for local
                    termMetaterm(
                       buildApplication(
                          nameTerm(typeToStructureEqName(
                                      e.typerep.abellaType)),
                          [localStructureVar,
                           case p.2 of
                           | applicationTerm(nameTerm("$pair_c"),
                                consTermList(tree, _)) -> tree
                           | _ -> p.2
                           end]))::
                    --WPD for local
                    termMetaterm(
                       buildApplication(
                          nameTerm(wpdTypeName(treeTy)),
                          [localStructureVar, localNodetree]))::p.1
               else termMetaterm(
                       buildApplication(
                          nameTerm(localAccessRelationName(top.top.3,
                             shortestName(val.name), top.top.4)),
                          [top.top.1, top.top.2,
                           buildApplication(
                              nameTerm(attributeExistsName),
                              [p.2])]))::p.1,
             e.encodedExpr) )];
}

