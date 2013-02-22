grammar silver:definition:flow:env;

import silver:definition:type:syntax;
import silver:definition:type;
import silver:modification:copper;
import silver:modification:patternmatching;
import silver:modification:let_fix;

synthesized attribute flowDeps :: [FlowVertex] occurs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;
attribute flowEnv occurs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;

function inhsForTakingRef
[String] ::= nt::String  flowEnv::Decorated FlowEnv
{
  -- TODO nasty expression
  local ds :: [FlowDef] = getInhsForNtRef(nt, flowEnv);
  local inhs :: [String] = if null(ds) then [] else case head(ds) of ntRefFlowDef(nt, inhs) -> inhs end;

  return inhs;
}

function depsForTakingRef
[FlowVertex] ::= f::(FlowVertex ::= String)  nt::String  flowEnv::Decorated FlowEnv
{
  return map(f, inhsForTakingRef(nt, flowEnv));  
}

aspect production errorReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}
aspect production childReference
top::Expr ::= q::Decorated QName
{
  -- TODO: there might be a problem! with this not detecting some decorations.
  
  -- Notes: q should find the actual type listed in the signature. Note that's different
  -- than childReference's reported typerep (which is that either/or type). So that's okay.

  top.flowDeps =
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(rhsVertex(q.lookupValue.fullName, _), q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
}
aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps =
    if {-always decorable-} !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(lhsInhVertex, q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
}
aspect production localReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [localEqVertex(q.lookupValue.fullName)] ++
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(localVertex(q.lookupValue.fullName, _), q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
}
aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [forwardEqVertex()]++
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(forwardVertex, q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
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
aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}


aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs
{
  top.flowDeps = e.flowDeps ++ es.flowDeps ++ annos.flowDeps;
}
aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs
{
  top.flowDeps = e.flowDeps ++ es.flowDeps ++ annos.flowDeps;
}
aspect production errorApplication
top::Expr ::= e::Decorated Expr es::AppExprs annos::AnnoAppExprs
{
  top.flowDeps = [];
}


aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.flowDeps = [];
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.flowDeps = [];
}
-- Note that below we IGNORE the flow deps of the lhs if we know what it is
-- this is because by default the lhs will have 'taking ref' flow deps (see above)
aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.flowDeps = 
    case e of
    | childReference(lq) -> [rhsVertex(lq.lookupValue.fullName, q.lookupAttribute.fullName)]
    | lhsReference(lq) -> [lhsSynVertex(q.lookupAttribute.fullName)]
    | localReference(lq) -> [localVertex(lq.lookupValue.fullName, q.lookupAttribute.fullName)]
    | forwardReference(lq) -> [forwardVertex(q.lookupAttribute.fullName)]
    | _ -> e.flowDeps
    end;
}
aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.flowDeps = 
    case e of
    | childReference(lq) -> [rhsVertex(lq.lookupValue.fullName, q.lookupAttribute.fullName)]
    | lhsReference(lq) -> [lhsInhVertex(q.lookupAttribute.fullName)]
    | localReference(lq) -> [localVertex(lq.lookupValue.fullName, q.lookupAttribute.fullName)]
    | forwardReference(lq) -> [forwardVertex(q.lookupAttribute.fullName)]
    | _ -> e.flowDeps
    end;
}
aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.flowDeps = []; -- errors, who cares?
}
aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.flowDeps = e.flowDeps;
}
aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.flowDeps = e.flowDeps;
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.flowDeps = e.flowDeps ++ inh.flowDeps;
}
aspect production decorateExprWithIntention
top::Expr ::= l::Location  e::Expr  inh::ExprInhs  intention::[String]
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

aspect production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  top.flowDeps = es.flowDeps ++ e.flowDeps;
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
aspect production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
  top.flowDeps = e.flowDeps;
}
aspect production snocAnnoAppExprs
top::AnnoAppExprs ::= es::AnnoAppExprs ',' e::AnnoExpr
{
  top.flowDeps = es.flowDeps ++ e.flowDeps;
}
aspect production oneAnnoAppExprs
top::AnnoAppExprs ::= e::AnnoExpr
{
  top.flowDeps = e.flowDeps;
}
aspect production emptyAnnoAppExprs
top::AnnoAppExprs ::= l::Location
{
  top.flowDeps = [];
}


aspect production exprRef
top::Expr ::= e::Decorated Expr
{
  -- There is a decision to make here.
  -- We DO refer to flowDeps here. Errors should have been already checked, but
  -- we shouldn't expect that of flowdeps!
  top.flowDeps = e.flowDeps;
}


-- builtins

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

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::Type ',' es::Expr ',' el::Expr ')'
{
  top.flowDeps = es.flowDeps ++ el.flowDeps;
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
  -- thanks to the decorateWithIntention hack, this works okay for
  -- matching on undecorated types (because e.flowDeps will be appropriate)

  -- Let's make sure for decorated types, we only demand what's necessary for forward
  -- evaluation.
  top.flowDeps = pr.flowDeps ++ f.flowDeps ++
    case (case e of exprRef(e1) -> e1 | _ -> e end) of -- TODO: Here we have a dumb hack to deal with exprRef!
    | childReference(lq) -> [rhsForwardVertex(lq.lookupValue.fullName)]
    | lhsReference(lq) -> [forwardEqVertex()] -- weirdos!
    | localReference(lq) -> [localForwardVertex(lq.lookupValue.fullName)]
    | forwardReference(lq) -> [forwardForwardVertex()] -- actually less weird!
    | _ -> e.flowDeps
    end;
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
top::PrimPattern ::= h::Name t::Name e::Expr
{
  top.flowDeps = e.flowDeps;
}

