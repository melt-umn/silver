grammar silver:compiler:refactor;

import silver:compiler:extension:convenience;

import silver:util:treeset as set;

-- Note this only handles locals, not production attributes, since that would require
-- doing something with the environment to track all references to a prod attr.

synthesized attribute refactorNondecLocals :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.refactorNondecLocals = false;
}
abstract production refactorNondecLocalsFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.refactorNondecLocals = true;
  forwards to refactorFlag(@rest);
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [ flagSpec(name="--refactor-nondec-locals", paramString=nothing(),
               help="Change locals that are only referenced as undecorated to be nondecorated",
               flagParser=flag(refactorNondecLocalsFlag))
           ];
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::TypeExpr ';'
{
  top.transforms <-
    if top.config.refactorNondecLocals
    && isDecorable(te.typerep, top.env) && !set:contains(fName, top.allDecoratedLocalRefs)
    then
      rule on ProductionStmt of
      | localAttributeDcl(_, _, a1, _, te1, _) when a.name == a1.name -> Silver_ProductionStmt {
          nondecorated local attribute $Name{a1} :: $TypeExpr{te1};
        }
      | shortLocalDecl(_, a1, _, te1, _, v, _) when a.name == a1.name -> Silver_ProductionStmt {
          nondecorated local $Name{a1} :: $TypeExpr{te1} = $Expr{v};
        }
      | shortLocalDeclwKwds(_, _, a1, _, te1, _, v, _) when a.name == a1.name -> Silver_ProductionStmt {
          nondecorated local attribute $Name{a1} :: $TypeExpr{te1} = $Expr{v};
        }
      end <+
      rule on Expr of
      | applicationExpr(baseExpr(q1), _, oneAppExprs(presentAppExpr(baseExpr(q2))), _)
          when q1.name == "new" && q2.name == a.name->
        Silver_Expr { $QName{q2} }
      end
    else fail();
}

monoid attribute decoratedLocalRefs::set:Set<String> occurs on
  ProductionStmts, ProductionStmt,
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AnnoAppExprs, AppExpr, AssignExpr, PrimPatterns, PrimPattern;
propagate decoratedLocalRefs on
  ProductionStmts, ProductionStmt,
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AnnoAppExprs, AppExpr, AssignExpr, PrimPatterns, PrimPattern
  excluding functionApplication;

aspect decoratedLocalRefs on top::Expr using := of
| functionApplication(e, es, anns) ->
    case e, es of
    | functionReference(q), oneAppExprs(presentAppExpr(localReference(_)))
        when q.lookupValue.fullName == "silver:core:new" -> mempty
    | _, _ -> e.decoratedLocalRefs ++ es.decoratedLocalRefs ++ anns.decoratedLocalRefs
    end
end;

aspect decoratedLocalRefs on top::Expr using <- of
| localReference(q) -> set:fromList(if top.expectedUndecorated then [] else [q.lookupValue.fullName])
end;

inherited attribute allDecoratedLocalRefs::set:Set<String> occurs on
  ProductionStmts, ProductionStmt,
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AnnoAppExprs, AppExpr, AssignExpr, PrimPatterns, PrimPattern;
propagate allDecoratedLocalRefs on
  ProductionStmts, ProductionStmt,
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AnnoAppExprs, AppExpr, AssignExpr, PrimPatterns, PrimPattern;

aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  stmts.allDecoratedLocalRefs = stmts.decoratedLocalRefs;
}
