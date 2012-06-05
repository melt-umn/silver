grammar silver:definition:flow:env;

import silver:definition:type:syntax;
import silver:modification:copper;
import silver:modification:patternmatching;
import silver:modification:let_fix;

synthesized attribute flowDeps :: [FlowVertex] occurs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr;
attribute flowEnv occurs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr;

--attribute upSubst, downSubst, finalSubst occurs on Expr, ForwardInhs, ForwardInh, ForwardLHSExpr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr;

aspect production errorReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}
aspect production childReference
top::Expr ::= q::Decorated QName
{
  -- Directly accessing a child should be a 'taking a reference' action
  top.flowDeps = [];
}
aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  -- Directly accessing the lhs should be a 'taking a reference' action
  -- Er, I suppose this will emit deps on all the reference inhs?
  top.flowDeps = [];
}
aspect production localReference
top::Expr ::= q::Decorated QName
{
  -- Directly accessing a local should be a 'taking a reference' action
  top.flowDeps = [localEqVertex(q.lookupValue.fullName)];
}
aspect production productionReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}
aspect production functionReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}
aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  -- Directly accessing the forward should be a 'taking a reference' action
  top.flowDeps = [forwardEqVertex()];
}
aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}


aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs
{
  top.flowDeps = e.flowDeps ++ es.flowDeps;
}
aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs
{
  top.flowDeps = e.flowDeps ++ es.flowDeps;
}
aspect production errorApplication
top::Expr ::= e::Decorated Expr es::AppExprs
{
  top.flowDeps = [];
}


aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.flowDeps = [];
}

aspect production errorAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.flowDeps = [];
}
aspect production synDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.flowDeps = 
    case e of
    | childReference(lq) -> [rhsVertex(lq.lookupValue.fullName, q.lookupAttribute.fullName)]
    | lhsReference(lq) -> [lhsVertex(q.lookupAttribute.fullName)]
    | localReference(lq) -> [localVertex(lq.lookupValue.fullName, q.lookupAttribute.fullName)]
    | forwardReference(lq) -> [forwardVertex(q.lookupAttribute.fullName)]
    | _ -> e.flowDeps
    end;
}
aspect production inhDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.flowDeps = 
    case e of
    | childReference(lq) -> [rhsVertex(lq.lookupValue.fullName, q.lookupAttribute.fullName)]
    | lhsReference(lq) -> [lhsVertex(q.lookupAttribute.fullName)]
    | localReference(lq) -> [localVertex(lq.lookupValue.fullName, q.lookupAttribute.fullName)]
    | forwardReference(lq) -> [forwardVertex(q.lookupAttribute.fullName)]
    | _ -> e.flowDeps
    end;
}
aspect production errorDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.flowDeps = [];
}
aspect production terminalAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.flowDeps = [];
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.flowDeps = e.flowDeps ++ inh.flowDeps;
}

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e1::Expr ';'
{
  top.flowDeps = e1.flowDeps;
}
aspect production exprInhsEmpty
top::ExprInhs ::= 
{
  top.flowDeps = [];
}
aspect production exprInhsOne
top::ExprInhs ::= h::ExprInh
{
  top.flowDeps = h.flowDeps;
}
aspect production exprInhsCons
top::ExprInhs ::= h::ExprInh  t::ExprInhs
{
  top.flowDeps = h.flowDeps ++ t.flowDeps;
}


aspect production trueConst
top::Expr ::= 'true'
{
  top.flowDeps = [];
}
aspect production falseConst
top::Expr ::= 'false'
{
  top.flowDeps = [];
}
aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production not
top::Expr ::= '!' e1::Expr
{
  top.flowDeps = e1.flowDeps;
}
aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps ++ e3.flowDeps;
}
aspect production intConst
top::Expr ::= i::Int_t
{
  top.flowDeps = [];
}
aspect production floatConst
top::Expr ::= f::Float_t
{
  top.flowDeps = [];
}
aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}
aspect production neg
top::Expr ::= '-' e1::Expr
{
  top.flowDeps = e1.flowDeps;
}
aspect production stringConst
top::Expr ::= s::String_t
{
  top.flowDeps = [];
}
aspect production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.flowDeps = [];
}
aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}


aspect production exprsEmpty
top::Exprs ::=
{
  top.flowDeps = [];
}
aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.flowDeps = e.flowDeps;
}
aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}


aspect production missingAppExpr
top::AppExpr ::= '_'
{
  top.flowDeps = [];
}
aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  top.flowDeps = e.flowDeps;
}
aspect production decoratedAppExpr
top::AppExpr ::= e::Decorated Expr
{
  top.flowDeps = e.flowDeps;
}

aspect production consAppExprs
top::AppExprs ::= e::AppExpr ',' es::AppExprs
{
  top.flowDeps = e.flowDeps ++ es.flowDeps;
}
aspect production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  top.flowDeps = e.flowDeps;
}
aspect production emptyAppExprs
top::AppExprs ::= l::Location
{
  top.flowDeps = [];
}


aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.flowDeps = e.flowDeps;
}
aspect production errorLength
top::Expr ::= e::Decorated Expr
{
  top.flowDeps = e.flowDeps;
}

aspect production toIntFunction
top::Expr ::= 'toInt' '(' e1::Expr ')'
{
  top.flowDeps = e1.flowDeps;
}

aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e1::Expr ')'
{
  top.flowDeps = e1.flowDeps;
}

aspect production toStringFunction
top::Expr ::= 'toString' '(' e1::Expr ')'
{
  top.flowDeps = e1.flowDeps;
}

aspect production newFunction
top::Expr ::= 'new' '(' e1::Expr ')'
{
  top.flowDeps = e1.flowDeps;
}

aspect production terminalFunction
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ')'
{
  top.flowDeps = e1.flowDeps;
}

aspect production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps ++ e3.flowDeps;
}

aspect production terminalFunctionInherited
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ')'
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
}




---- FROM COPPER TODO
--grammar silver:modification:copper;

aspect production actionChildReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}

aspect production pluckTerminalReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}

aspect production disambigLexemeReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}

aspect production parserAttributeReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}

aspect production termAttrValueReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}


-- FROM LET TODO
attribute flowDeps, flowEnv occurs on AssignExpr;

aspect production letp
top::Expr ::= l::Location  la::AssignExpr  e::Expr
{
  top.flowDeps = la.flowDeps ++ e.flowDeps;
}

aspect production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.flowDeps = a1.flowDeps ++ a2.flowDeps;
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::Type '=' e::Expr
{
  top.flowDeps = e.flowDeps;
}

aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}


-- FROM PATTERN TODO
attribute flowDeps, flowEnv occurs on PrimPatterns, PrimPattern;

aspect production matchPrimitiveReal
top::Expr ::= ll::Location e::Expr t::Type pr::PrimPatterns f::Expr
{
  top.flowDeps = e.flowDeps ++ pr.flowDeps ++ f.flowDeps;
}

aspect production onePattern
top::PrimPatterns ::= p::PrimPattern
{
  top.flowDeps = p.flowDeps;
}
aspect production consPattern
top::PrimPatterns ::= p::PrimPattern '|' ps::PrimPatterns
{
  top.flowDeps = p.flowDeps ++ ps.flowDeps;
}

aspect production prodPatternNormal
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.flowDeps = e.flowDeps;
}
aspect production prodPatternGadt
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.flowDeps = e.flowDeps;
}
aspect production integerPattern
top::PrimPattern ::= i::Int_t '->' e::Expr
{
  top.flowDeps = e.flowDeps;
}
aspect production stringPattern
top::PrimPattern ::= i::String_t '->' e::Expr
{
  top.flowDeps = e.flowDeps;
}
aspect production booleanPattern
top::PrimPattern ::= i::String '->' e::Expr
{
  top.flowDeps = e.flowDeps;
}
aspect production nilPattern
top::PrimPattern ::= e::Expr
{
  top.flowDeps = e.flowDeps;
}
aspect production conslstPattern
top::PrimPattern ::= h::String t::String e::Expr
{
  top.flowDeps = e.flowDeps;
}

