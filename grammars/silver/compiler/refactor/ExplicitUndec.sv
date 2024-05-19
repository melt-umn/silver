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
               help="Change (former) instances of implicit undecoration to use tree sharing or explicit new() calls",
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
      | baseExpr(q1) when q1.name == new(q).name && q1.nameLoc == new(q).nameLoc ->
        if top.decSiteVertexInfo.isJust
        then Silver_Expr { @$QName{q1} }
        else Silver_Expr { new($QName{q1}) }
      end
    else fail();
}

aspect production lhsReference
top::Expr ::= @q::QName
{
  top.transforms <-
    if top.config.refactorExplicitUndec
    && isDecorable(q.lookupValue.typeScheme.monoType, top.env) && top.expectedUndecorated
    then
      rule on Expr of
      | baseExpr(q1) when q1.name == new(q).name && q1.nameLoc == new(q).nameLoc ->
        Silver_Expr { new($QName{q1}) }
      end
    else fail();
}

aspect production localReference
top::Expr ::= @q::QName
{
  top.transforms <-
    if top.config.refactorExplicitUndec
    && isDecorable(q.lookupValue.typeScheme.monoType, top.env) && top.expectedUndecorated
    then
      rule on Expr of
      | baseExpr(q1) when q1.name == new(q).name && q1.nameLoc == new(q).nameLoc ->
        if top.decSiteVertexInfo.isJust
        then Silver_Expr { @$QName{q1} }
        else Silver_Expr { new($QName{q1}) }
      end
    else fail();
}

aspect production lexicalLocalReference
top::Expr ::= @q::QName _ _
{
  top.transforms <-
    if top.config.refactorExplicitUndec
    && q.lookupValue.typeScheme.monoType.isDecorated && top.expectedUndecorated
    then
      rule on Expr of
      | baseExpr(q1) when q1.name == new(q).name && q1.nameLoc == new(q).nameLoc ->
        Silver_Expr { new($QName{q1}) }
      end
    else fail();
}


inherited attribute expectedUndecorated :: Boolean occurs on Expr, Exprs, PrimPatterns, PrimPattern;
propagate @expectedUndecorated on Exprs, PrimPatterns, PrimPattern;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  e.expectedUndecorated = !t.typerep.isDecorated;
}

aspect production shortFunctionDcl
top::AGDcl ::= 'fun' id::Name ns::FunctionSignature '=' e::Expr ';'
{
  e.expectedUndecorated = typeIsUndecorated(namedSig.outputElement.typerep);
}

aspect production defaultConstraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr '=' e::Expr ';'
{
  e.expectedUndecorated = typeIsUndecorated(ty.typerep);
}

aspect production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  e.expectedUndecorated = typeIsUndecorated(id.lookupValue.typeScheme.typerep);
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  e.expectedUndecorated = true;
}

aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  e.expectedUndecorated = typeIsUndecorated(lhs.typerep);
}

aspect production attachNoteStmt
top::ProductionStmt ::= 'attachNote' e::Expr ';'
{
  e.expectedUndecorated = true;
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  e.expectedUndecorated = typeIsUndecorated(top.frame.signature.outputElement.typerep);
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = typeIsUndecorated(attr.typerep);
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = typeIsUndecorated(attr.typerep);
}

aspect production synBaseColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = typeIsUndecorated(attr.typerep);
}

aspect production synAppendColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = typeIsUndecorated(attr.typerep);
}

aspect production inhBaseColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = typeIsUndecorated(attr.typerep);
}

aspect production inhAppendColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  e.expectedUndecorated = typeIsUndecorated(attr.typerep);
}

aspect production errorValueDef
top::ProductionStmt ::= @val::QName e::Expr
{
  e.expectedUndecorated = typeIsUndecorated(val.lookupValue.typeScheme.monoType);
}

aspect production localValueDef
top::ProductionStmt ::= @val::QName e::Expr
{
  e.expectedUndecorated = typeIsUndecorated(val.lookupValue.typeScheme.monoType);
}

aspect production parserAttributeValueDef
top::ProductionStmt ::= @val::QName e::Expr
{
  e.expectedUndecorated = typeIsUndecorated(val.lookupValue.typeScheme.monoType);
}

aspect production termAttrValueValueDef
top::ProductionStmt ::= @val::QName e::Expr
{
  e.expectedUndecorated = typeIsUndecorated(val.lookupValue.typeScheme.monoType);
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
  e.expectedUndecorated = typeIsUndecorated(top.appExprTyperep);
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
  e1.expectedUndecorated = typeIsUndecorated(lhs.typerep);
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
  e.expectedUndecorated = typeIsUndecorated(t.typerep);
}

aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  e.expectedUndecorated = false;
  pr.expectedUndecorated = top.expectedUndecorated;
  f.expectedUndecorated = top.expectedUndecorated;
}

aspect production exprOperator
top::NameOrBOperator ::= e::Expr
{
  e.expectedUndecorated = false;
}

fun typeIsUndecorated Boolean ::= t::Type =
  case t of
  | decoratedType(_, _) -> false
  | varType(_) -> false
  | _ -> true
  end;
