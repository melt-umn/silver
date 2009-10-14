grammar silver:analysis:typechecking:core;
import silver:definition:core;

synthesized attribute typeErrors :: [Decorated Message];
attribute typeErrors occurs on Root, AGDcls, AGDcl;
attribute typeErrors occurs on ProductionBody, ProductionStmts, ProductionStmt, AttributeDef, LHSExpr, ForwardInh, ForwardLHSExpr;
attribute typeErrors occurs on LocalAttributeDcl, ProductionAttributeDcl, ForwardsToDcl, ForwardingWithDcl, ReturnDef;
attribute typeErrors occurs on Expr, ForwardInhs;
attribute typeErrors occurs on ExprInhs, ExprInh, ExprLHSExpr;
attribute typeErrors occurs on Exprs;
