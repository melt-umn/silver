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

attribute actionCodeType occurs on ActionCode_c,ProductionStmts,ProductionStmt,Expr,Exprs, ExprInh, ExprInhs,ForwardInhs,ForwardInh, DefLHS;

aspect production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{
  stmts.actionCodeType = semanticBlockType();
}
