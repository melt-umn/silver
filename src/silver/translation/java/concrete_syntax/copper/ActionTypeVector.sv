grammar silver:translation:java:concrete_syntax:copper;
import silver:definition:core;

synthesized attribute isSemanticBlock :: Boolean;
synthesized attribute isProductionAction :: Boolean;
synthesized attribute isDisambigGroupAction :: Boolean;
synthesized attribute isParserAttrAction :: Boolean;
synthesized attribute isTerminalAction :: Boolean;

nonterminal ActionTypeVector with isSemanticBlock,isProductionAction,isDisambigGroupAction,isParserAttrAction,isTerminalAction;

abstract production defaultActionType
top::ActionTypeVector ::=
{
  top.isSemanticBlock = false;
  top.isProductionAction = false;
  top.isDisambigGroupAction = false;
  top.isParserAttrAction = false;
  top.isTerminalAction = false;
}

abstract production semanticBlockType
top::ActionTypeVector ::=
{
  top.isSemanticBlock = true;
  forwards to defaultActionType();
}

abstract production productionActionType
top::ActionTypeVector ::=
{
  top.isProductionAction = true;
  forwards to defaultActionType();
}

abstract production disambigGroupActionType
top::ActionTypeVector ::=
{
  top.isDisambigGroupAction = true;
  forwards to defaultActionType();
}
 
abstract production parserAttrActionType
top::ActionTypeVector ::=
{
  top.isParserAttrAction = true;
  forwards to defaultActionType();
}

abstract production terminalActionType
top::ActionTypeVector ::=
{
  top.isTerminalAction = true;
  forwards to defaultActionType();
}

autocopy attribute actionCodeType :: ActionTypeVector;

attribute actionCodeType occurs on ActionCode_c,ProductionStmts,ProductionStmt,AttributeDef,LHSExpr,Expr,Exprs;

-- SEE modifications/dumb_fix_autocopy_temporary
--autocopy ActionCode_c.actionCodeType on silver:translation:java:concrete_syntax:copper *;
--autocopy ProductionStmts.actionCodeType on silver:translation:java:concrete_syntax:copper *;
--autocopy ProductionStmts.actionCodeType on silver:definition:core *;
--autocopy ProductionStmt.actionCodeType on silver:translation:java:concrete_syntax:copper *;
--autocopy ProductionStmt.actionCodeType on silver:definition:core *;
--autocopy AttributeDef.actionCodeType on silver:translation:java:concrete_syntax:copper *;
--autocopy AttributeDef.actionCodeType on silver:definition:core *;
--autocopy AttributeDef.actionCodeType on silver:modification:collection *;
--autocopy AttributeDef.actionCodeType on silver:extension:polymorphism:functions *;
--autocopy LHSExpr.actionCodeType on silver:translation:java:concrete_syntax:copper *;
--autocopy LHSExpr.actionCodeType on silver:definition:core *;
--autocopy LHSExpr.actionCodeType on silver:modification:collection *;
--autocopy LHSExpr.actionCodeType on silver:extension:polymorphism *;
--autocopy Expr.actionCodeType on silver:translation:java:concrete_syntax:copper *;
--autocopy Expr.actionCodeType on silver:definition:core *;
--autocopy Expr.actionCodeType on silver:modification:patternmatching *;
--autocopy Expr.actionCodeType on silver:modification:let_fix *;
--autocopy Expr.actionCodeType on silver:definition:type:io *;
--autocopy Expr.actionCodeType on silver:definition:type:anytype *;
--autocopy Expr.actionCodeType on silver:definition:concrete_syntax *;
--autocopy Expr.actionCodeType on silver:extension:polymorphism:functions *;
--autocopy Expr.actionCodeType on silver:extension:polymorphism *;
--autocopy Expr.actionCodeType on silver:extension:list *;
--autocopy Expr.actionCodeType on silver:extension:easyterminal *;
--autocopy Expr.actionCodeType on silver:translation:java:concrete_syntax *;
--autocopy Exprs.actionCodeType on silver:translation:java:concrete_syntax:copper *;
--autocopy Exprs.actionCodeType on silver:definition:core *;
--autocopy Exprs.actionCodeType on silver:extension:polymorphism:functions *;
--autocopy Exprs.actionCodeType on silver:extension:polymorphism *;
--autocopy Exprs.actionCodeType on silver:extension:list *;

aspect production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{
  stmts.actionCodeType = semanticBlockType();
}
