grammar silver:definition:flow:env;

import silver:definition:type:syntax;
import silver:modification:defaultattr;
import silver:modification:collection;
import silver:modification:copper;

attribute flowDefs, flowEnv occurs on ProductionBody, ProductionStmts, ProductionStmt;


aspect production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{
  top.flowDefs = stmts.flowDefs;
}

----

aspect production productionStmtsNone
top::ProductionStmts ::= 
{
  top.flowDefs = [];
}

aspect production productionStmts
top::ProductionStmts ::= stmt::ProductionStmt
{
  top.flowDefs = stmt.flowDefs;
}

aspect production productionStmtsCons
top::ProductionStmts ::= h::ProductionStmt  t::ProductionStmts
{
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

aspect production productionStmtsAppend
top::ProductionStmts ::= h::ProductionStmts  t::ProductionStmts
{
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

----

aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt  t::ProductionStmt
{
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

aspect default production
top::ProductionStmt ::=
{
  top.flowDefs = [];
}

----

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.flowDefs = case top.blockContext of -- TODO: this may not be the bestest way to go about doing this....
                 | defaultAspectContext() -> [defEq(top.signature.outputElement.typerep.typeName, attr.lookupAttribute.fullName)]
                 | _ -> [synEq(top.signature.fullName, attr.lookupAttribute.fullName)]
                 end;
}


aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' {-That's really a <- -} e::Expr
{
  top.flowDefs = []; -- TODO [synContribEq(top.signature.fullName, attr.lookupAttribute.fullName)];
}

-----------

synthesized attribute flowEdges :: [Pair<FlowVertex FlowVertex>];

attribute flowEdges occurs on ProductionBody, ProductionStmts, ProductionStmt, ForwardInhs, ForwardInh;

aspect production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{
  top.flowEdges = stmts.flowEdges;
}
aspect production productionStmtsNone
top::ProductionStmts ::= 
{
  top.flowEdges = [];
}
aspect production productionStmts
top::ProductionStmts ::= stmt::ProductionStmt
{
  top.flowEdges = stmt.flowEdges;
}
aspect production productionStmtsCons
top::ProductionStmts ::= h::ProductionStmt t::ProductionStmts
{
  top.flowEdges = h.flowEdges ++ t.flowEdges;
}
aspect production productionStmtsAppend
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmts
{
  top.flowEdges = h.flowEdges ++ t.flowEdges;
}
aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  top.flowEdges = h.flowEdges ++ t.flowEdges;
}



aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.flowEdges = map(pair(forwardEqVertex(), _), e.flowDeps);
}
aspect production forwardsToWith
top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  top.flowEdges = map(pair(forwardEqVertex(), _), e.flowDeps) ++ inh.flowEdges;
}
aspect production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.flowEdges = inh.flowEdges;
}

aspect production forwardInhsOne
top::ForwardInhs ::= lhs::ForwardInh
{
  top.flowEdges = lhs.flowEdges;
}
aspect production forwardInhsCons
top::ForwardInhs ::= lhs::ForwardInh rhs::ForwardInhs
{
  top.flowEdges = lhs.flowEdges ++ rhs.flowEdges;
}
aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  top.flowEdges =
    case lhs of
    | forwardLhsExpr(q) -> map(pair(forwardVertex(q.lookupAttribute.fullName), _), e.flowDeps)
    end;
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  top.flowEdges = [];
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.flowEdges = []; -- we don't really care about function's flow info!
}

aspect production errorAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.flowEdges = [];
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.flowEdges = map(pair(dl.partialVertex(attr.lookupAttribute.fullName), _), e.flowDeps);
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.flowEdges = map(pair(dl.partialVertex(attr.lookupAttribute.fullName), _), e.flowDeps);
}

synthesized attribute partialVertex :: (FlowVertex ::= String) occurs on DefLHS;

aspect production childDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.partialVertex = rhsVertex(q.lookupValue.fullName, _);
}
aspect production localDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.partialVertex = localVertex(q.lookupValue.fullName, _);
}
aspect production lhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.partialVertex = lhsVertex;
}
aspect production forwardDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.partialVertex = forwardVertex;
}
aspect production errorDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.partialVertex = localVertex("unknown:vertex", _); -- TODO ??
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.flowEdges = map(pair(localEqVertex(val.lookupValue.fullName), _), e.flowDeps);
}
aspect production errorValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.flowEdges = [];
}


------ FROM COPPER TODO

aspect production pluckDef
top::ProductionStmt ::= 'pluck' e::Expr ';'
{
  top.flowEdges = error("Internal compiler error: flow edge information is invalid on action statements");
}

aspect production printStmt
top::ProductionStmt ::= 'print' e::Expr ';'
{
  top.flowEdges = error("Internal compiler error: flow edge information is invalid on action statements");
}

aspect production parserAttributeValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.flowEdges = error("Internal compiler error: flow edge information is invalid on action statements");
}

aspect production parserAttributeDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.partialVertex = error("Internal compiler error: flow edge information is invalid on action statements");
}

aspect production termAttrValueValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.flowEdges = error("Internal compiler error: flow edge information is invalid on action statements");
}


-- FROM DEFAULTATTR TODO

aspect production defaultLhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.partialVertex = lhsVertex;
}

