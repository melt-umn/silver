grammar silver:compiler:refactor;

import silver:compiler:definition:flow:env;

synthesized attribute refactorExplicitUndec :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.refactorExplicitUndec = false;
}
abstract production refactorExplicitUndecFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.refactorExplicitUndec = true;
  forwards to refactorFlag(@rest);
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [ flagSpec(name="--refactor-explicit-undec", paramString=nothing(),
               help="Change ",
               flagParser=flag(refactorExplicitUndecFlag))
           ];
}

aspect production childReference
top::Expr ::= @q::QName
{
  top.transforms <-
    if top.config.refactorExplicitUndec
    && isDecorable(q.lookupValue.typeScheme.monoType, top.env) && top.expectedUndecorated
    then
      rule on Expr of
      | baseExpr(q1) when q1.name == q.name && q1.nameLoc == q.nameLoc ->
        if top.decSiteVertexInfo.isJust
        then Silver_Expr { @$QName{q1} }
        else Silver_Expr { new($QName{q1}) }
      end
    else fail();
}

aspect production localReference
top::Expr ::= @q::QName
{
  top.transforms <-
    if top.config.refactorExplicitUndec
    && isDecorable(q.lookupValue.typeScheme.monoType, top.env) && !top.finalType.isDecorated
    then
      rule on Expr of
      | baseExpr(q1) when q1.name == q.name && q1.nameLoc == q.nameLoc ->
        if top.decSiteVertexInfo.isJust
        then Silver_Expr { @$QName{q1} }
        else Silver_Expr { new($QName{q1}) }
      end
    else fail();
}


inherited attribute expectedUndecorated :: Boolean occurs on Expr, Exprs, PrimPatterns, PrimPattern;
propagate expectedUndecorated on Exprs, PrimPatterns, PrimPattern;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  e.expectedUndecorated = !t.typerep.isDecorated;
}

aspect production shortFunctionDcl
top::AGDcl ::= 'fun' id::Name ns::FunctionSignature '=' e::Expr ';'
{
  e.expectedUndecorated = !namedSig.outputElement.typerep.isDecorated;
}

aspect production defaultConstraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr '=' e::Expr ';'
{
  e.expectedUndecorated = !ty.typerep.isDecorated;
}

aspect production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  e.expectedUndecorated = !id.lookupValue.typeScheme.typerep.isDecorated;
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  e.expectedUndecorated = !top.frame.signature.outputElement.typerep.isDecorated;
}

aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  e.expectedUndecorated = !lhs.typerep.isDecorated;
}

aspect production attachNoteStmt
top::ProductionStmt ::= 'attachNote' e::Expr ';'
{
  e.expectedUndecorated = true;
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  e.expectedUndecorated = !top.frame.signature.outputElement.typerep.isDecorated;
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = !attr.typerep.isDecorated;
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = !attr.typerep.isDecorated;
}

aspect production synBaseColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = !attr.typerep.isDecorated;
}

aspect production synAppendColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = !attr.typerep.isDecorated;
}

aspect production inhBaseColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = !attr.typerep.isDecorated;
}

aspect production inhAppendColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = !attr.typerep.isDecorated;
}

aspect production errorValueDef
top::ProductionStmt ::= @val::QName e::Expr
{
  e.expectedUndecorated = !val.lookupValue.typeScheme.monoType.isDecorated;
}

aspect production localValueDef
top::ProductionStmt ::= @val::QName e::Expr
{
  e.expectedUndecorated = !val.lookupValue.typeScheme.monoType.isDecorated;
}

aspect production parserAttributeValueDef
top::ProductionStmt ::= @val::QName e::Expr
{
  e.expectedUndecorated = !val.lookupValue.typeScheme.monoType.isDecorated;
}

aspect production termAttrValueValueDef
top::ProductionStmt ::= @val::QName e::Expr
{
  e.expectedUndecorated = !val.lookupValue.typeScheme.monoType.isDecorated;
}

aspect production pluckDef
top::ProductionStmt ::= 'pluck' e::Expr ';'
{
  e.expectedUndecorated = false;
}

aspect production printStmt
top::ProductionStmt ::= 'print' e::Expr ';'
{
  e.expectedUndecorated = false;
}

aspect production pushTokenStmt
top::ProductionStmt ::= 'pushToken' '(' val::QName ',' lexeme::Expr ')' ';'
{
  lexeme.expectedUndecorated = false;
}

aspect production insertSemanticTokenStmt
top::ProductionStmt ::= 'insert' 'semantic' 'token' t::QNameType 'at' l::Expr ';'
{
  l.expectedUndecorated = true;
}

aspect production ifElseStmt
top::ProductionStmt ::= 'if' '(' c::Expr ')' t::ProductionStmt 'else' e::ProductionStmt
{
  c.expectedUndecorated = false;
}

aspect production application
top::Expr ::= e::Expr '(' es::AppExprs ',' anns::AnnoAppExprs ')'
{
  e.expectedUndecorated = false;
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  e.expectedUndecorated = !top.appExprTyperep.isDecorated;
}

aspect production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  note.expectedUndecorated = true;
  e.expectedUndecorated = top.expectedUndecorated;
}

aspect production synDecoratedAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.expectedUndecorated = false;
}

aspect production inhDecoratedAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.expectedUndecorated = false;
}

aspect production transDecoratedAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.expectedUndecorated = false;
}

aspect production annoAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.expectedUndecorated = true;
}

aspect production synDataAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.expectedUndecorated = true;
}

aspect production inhUndecoratedAccessErrorHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.expectedUndecorated = true;
}

aspect production transUndecoratedAccessErrorHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.expectedUndecorated = true;
}

aspect production unknownDclAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.expectedUndecorated = false;
}

aspect production errorAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.expectedUndecorated = false;
}

aspect production terminalAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.expectedUndecorated = false;
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  e.expectedUndecorated = false;
}

aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  e.expectedUndecorated = true;
}

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e1::Expr ';'
{
  e1.expectedUndecorated = !lhs.typerep.isDecorated;
}

aspect production decorationSiteExpr
top::Expr ::= '@' e::Expr
{
  e.expectedUndecorated = false;
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  e1.expectedUndecorated = false;
  e2.expectedUndecorated = top.expectedUndecorated;
  e3.expectedUndecorated = top.expectedUndecorated;
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  es.expectedUndecorated = false;
  el.expectedUndecorated = true;
}

aspect production lambdap
top::Expr ::= params::LambdaRHS e::Expr
{
  e.expectedUndecorated = top.expectedUndecorated;
}

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  e.expectedUndecorated = top.expectedUndecorated;
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  e.expectedUndecorated = !t.typerep.isDecorated;
}

aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  e.expectedUndecorated = e.finalType.isData;
  pr.expectedUndecorated = top.expectedUndecorated;
  f.expectedUndecorated = top.expectedUndecorated;
}

aspect production exprOperator
top::NameOrBOperator ::= e::Expr
{
  e.expectedUndecorated = false;
}