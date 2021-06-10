grammar silver:compiler:extension:abella_compilation;


aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  body.encodingEnv = ns.encodingEnv_up;
  body.top = (ns.top_up.1, ns.top_up.2, ns.top_up.3, id.name);
  body.treeTerm =
       applicationTerm(nameTerm(nameToProd(id.name)), ns.treeTerm_up);
  body.nodetreeTerm = ns.nodetreeTerm_up;
}

aspect production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature
               pm::ProductionModifiers body::ProductionBody
{
  body.encodingEnv = ns.encodingEnv_up;
  body.top = (ns.top_up.1, ns.top_up.2, ns.top_up.3, id.name);
  body.treeTerm =
       applicationTerm(nameTerm(nameToProd(id.name)), ns.treeTerm_up);
  body.nodetreeTerm = ns.nodetreeTerm_up;
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  body.encodingEnv = ns.encodingEnv_up;
  body.top = (ns.top_up.1, ns.top_up.2, ns.top_up.3, id.name);
  body.treeTerm =
       applicationTerm(nameTerm(nameToProd(id.name)), ns.treeTerm_up);
  body.nodetreeTerm = ns.nodetreeTerm_up;
}

aspect production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' 
               lhs::Name '::' te::TypeExpr '::=' body::ProductionBody 
{
  top.localAttrs := [];
  top.attrEqClauses := [];
  top.attrEqInfo := [];
}




attribute
   localAttrs, top, encodingEnv, treeTerm,
   nodetreeTerm, attrEqClauses, attrEqInfo
occurs on ProductionBody;

attribute
   localAttrs, top, encodingEnv, treeTerm,
   nodetreeTerm, attrEqClauses, attrEqInfo
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
   localAttrs, top, encodingEnv,
   treeTerm, nodetreeTerm, attrEqClauses, attrEqInfo
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
      [(a.name, [(top.top.4, newte.typerep.abellaType)])];
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
           [top.top.1, top.treeTerm,
            nodetreeToNode(top.nodetreeTerm)]);
  --synthesized can only be defined on root
  top.attrEqInfo <-
      [ (shortestName(attr.name), top.top.3, top.top.4, clauseHead,
         map(\ p::([Metaterm], Term) ->
               termMetaterm(
                  buildApplication(
                     nameTerm(accessRelationName(top.top.3,
                                           shortestName(attr.name))),
                     [top.top.1,
                      nodetreeToNode(top.nodetreeTerm),
                      buildApplication(nameTerm(attributeExistsName),
                                       [p.2])]))::p.1,
             e.encodedExpr)) ];
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
           [top.top.1, top.treeTerm,
            nodetreeToNode(top.nodetreeTerm)]);
  --attrs set on locals and forwards need to be handled differently
  top.attrEqInfo <-
      case dl of
      | localDefLHS(_) -> []
      | forwardDefLHS(_) -> []
      | _ ->
        [ (shortestName(attr.name), top.top.3, top.top.4, clauseHead,
           map(\ p::([Metaterm], Term) ->
                 termMetaterm(
                    buildApplication(
                       nameTerm(accessRelationName(treeTy,
                                             shortestName(attr.name))),
                       [tree.1, tree.2,
                        buildApplication(nameTerm(attributeExistsName),
                                         [p.2])]))::p.1,
               e.encodedExpr)) ]
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
}

