grammar silver:definition:flow:env;

import silver:definition:type:syntax;
import silver:definition:type;
import silver:modification:copper;
import silver:modification:primitivepattern;
import silver:extension:patternmatching only Arrow_kwd, Vbar_kwd; -- TODO remove
import silver:modification:let_fix;

{--
 - Direct (potential) dependencies this expression has on nodes in the production flow graph.
 -}
synthesized attribute flowDeps :: [FlowVertex];
{--
 - Determines whether this expression corresponds to a node in the flow graph, and how
 - to treat it specially if so.
 -}
synthesized attribute flowVertexInfo :: ExprVertexInfo;

attribute flowDeps, flowEnv occurs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;
attribute flowVertexInfo occurs on Expr;


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

aspect default production
top::Expr ::=
{
  -- We go with using default here because
  -- (a) it's safe. vertexInfo is for being less conservative and more precise.
  -- (b) only a few productions actually provide it.
  top.flowVertexInfo = noVertex();
}

aspect production errorReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
}
aspect production childReference
top::Expr ::= q::Decorated QName
{
  -- Note that q should find the actual type written in the signature, and so
  -- isDecorable on that indeed tells us whether it's something autodecorated.
  top.flowDeps =
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(rhsVertex(q.lookupValue.fullName, _), q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
  top.flowVertexInfo = simpleHasVertex(rhsVertex(q.lookupValue.fullName, _));
}
aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  -- Always a decorable type, so just check how it's being used:
  top.flowDeps =
    if !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(lhsInhVertex, q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
  top.flowVertexInfo = hasVertex(lhsSynVertex, lhsInhVertex, []);
}
aspect production localReference
top::Expr ::= q::Decorated QName
{
  -- Again, q give the actual type written.
  top.flowDeps = [localEqVertex(q.lookupValue.fullName)] ++
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(localVertex(q.lookupValue.fullName, _), q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
    
  top.flowVertexInfo =
    hasVertex(
      localVertex(q.lookupValue.fullName, _),
      localVertex(q.lookupValue.fullName, _),
      [localEqVertex(q.lookupValue.fullName)]); -- aw, shucks. not a simpleHasVertex :(
}
aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  -- Again, always a decorable type.
  top.flowDeps = [forwardEqVertex()]++
    if !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(forwardVertex, q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
    
  top.flowVertexInfo =
    hasVertex(
      forwardVertex,
      forwardVertex,
      [forwardEqVertex()]); -- aw, shucks. not a simpleHasVertex :(
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
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = [];
}
-- Note that below we IGNORE the flow deps of the lhs if we know what it is
-- this is because by default the lhs will have 'taking ref' flow deps (see above)
aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = 
    case e.flowVertexInfo of
    | hasVertex(sVT, _, r) -> sVT(q.attrDcl.fullName) :: r
    | noVertex() -> e.flowDeps
    end;
}
aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = 
    case e.flowVertexInfo of
    | hasVertex(_, iVT, r) -> iVT(q.attrDcl.fullName) :: r
    | noVertex() -> e.flowDeps
    end;
}
aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = []; -- errors, who cares?
}
aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = e.flowDeps;
}
aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = e.flowDeps;
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.flowDeps = e.flowDeps ++ inh.flowDeps;
  -- TODO top.flowVertexInfo = simpleHasVertex(rhsVertex(q.lookupValue.fullName, _));
  -- anon vertex here
}
aspect production decorateExprWithIntention
top::Expr ::= e::Expr  inh::ExprInhs  intention::[String]
{
  top.flowDeps = e.flowDeps ++ inh.flowDeps;
  -- TODO decide if keeping this or not?? maybe remove this case now
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
  -- TODO: it's possible we could create an anon flow vertex type here
  -- that duplicates all vertexes to both 'then' and 'else'...
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
  top.flowDeps = []; -- error, so who cares?
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
top::AppExprs ::=
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
top::AnnoAppExprs ::=
{
  top.flowDeps = [];
}


aspect production exprRef
top::Expr ::= e::Decorated Expr
{
  -- This production is somewhat special, for example, error is := []
  -- That's because the errors should have already been appeared wherever it's anchored.
  
  -- But, here we DO pass flowDeps through because this affects wherever this expression
  -- is used, not just where it appears.
  
  -- So definitely don't consider making this []!
  
  top.flowDeps = e.flowDeps;
  top.flowVertexInfo = e.flowVertexInfo;
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
  -- Emit nothing except the keepDeps, for a vertex node
  top.flowDeps =
    case e1.flowVertexInfo of
    | hasVertex(sVT, _, r) -> r
    | noVertex() -> e1.flowDeps
    end;
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::Type ',' es::Expr ',' el::Expr ')'
{
  top.flowDeps = es.flowDeps ++ el.flowDeps;
}



---- FROM COPPER TODO
--grammar silver:modification:copper;

-- These are all errors, basically.

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
top::Expr ::= la::AssignExpr  e::Expr
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
  -- We should be doing something to store e.flowVertexInfo in 'id' in the env
}

aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
  -- TODO: should be setting flowVertexInfo to whatever that expr had
}


-- FROM PATTERN TODO
attribute flowDeps, flowEnv occurs on PrimPatterns, PrimPattern;

aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::Type pr::PrimPatterns f::Expr
{
  -- thanks to the decorateWithIntention hack, this works okay for
  -- matching on undecorated types (because e.flowDeps will be appropriate)

  -- Let's make sure for decorated types, we only demand what's necessary for forward
  -- evaluation.
  top.flowDeps = pr.flowDeps ++ f.flowDeps ++
    case e.flowVertexInfo of
    | hasVertex(sVT, _, r) -> sVT("forward") :: r
    | noVertex() -> e.flowDeps
    end;

  -- TODO: again, like 'if', we could be doing a kind of union flowVertexInfo
  -- thingy here.
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

