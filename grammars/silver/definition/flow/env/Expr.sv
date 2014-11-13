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

-- flowDefs because expressions (decorate, patterns) can now generate stitchpoints
attribute flowDeps, flowDefs, flowEnv occurs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;
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
[FlowVertex] ::= f::VertexType  nt::String  flowEnv::Decorated FlowEnv
{
  return map(f.inhVertex, inhsForTakingRef(nt, flowEnv));  
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
top::Expr ::= msg::[Message]  q::Decorated QName
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production childReference
top::Expr ::= q::Decorated QName
{
  -- Note that q should find the actual type written in the signature, and so
  -- isDecorable on that indeed tells us whether it's something autodecorated.
  top.flowDeps =
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(rhsVertexType(q.lookupValue.fullName), q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
  top.flowVertexInfo = 
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then hasVertex(rhsVertexType(q.lookupValue.fullName))
    else noVertex();
  top.flowDefs = [];
}
aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  -- Always a decorable type, so just check how it's being used:
  top.flowDeps =
    if !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(lhsVertexType, q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
  top.flowVertexInfo = 
    if !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then hasVertex(lhsVertexType)
    else noVertex();
  top.flowDefs = [];
}
aspect production localReference
top::Expr ::= q::Decorated QName
{
  -- Again, q give the actual type written.
  top.flowDeps = [localEqVertex(q.lookupValue.fullName)] ++
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(localVertexType(q.lookupValue.fullName), q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
    
  top.flowVertexInfo =
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then hasVertex(localVertexType(q.lookupValue.fullName))
    else noVertex();
  top.flowDefs = [];
}
aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  -- Again, always a decorable type.
  top.flowDeps = [forwardEqVertex()]++
    if !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(forwardVertexType, q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
    
  top.flowVertexInfo =
    if !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then hasVertex(forwardVertexType)
    else noVertex();
  top.flowDefs = [];
}
aspect production productionReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production functionReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
  top.flowDefs = [];
}


aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs
{
  top.flowDeps = e.flowDeps ++ es.flowDeps ++ annos.flowDeps;
  top.flowDefs = e.flowDefs ++ es.flowDefs ++ annos.flowDefs;
}
aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs
{
  top.flowDeps = e.flowDeps ++ es.flowDeps ++ annos.flowDeps;
  top.flowDefs = e.flowDefs ++ es.flowDefs ++ annos.flowDefs;
}
aspect production errorApplication
top::Expr ::= e::Decorated Expr es::AppExprs annos::AnnoAppExprs
{
  top.flowDeps = [];
  top.flowDefs = [];
}


aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.flowDeps = [];
  top.flowDefs = [];
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  top.flowDeps = 
    case e.flowVertexInfo of
    | hasVertex(vertex) -> vertex.fwdVertex :: vertex.eqVertex
    | noVertex() -> e.flowDeps
    end;
  top.flowDefs = [];
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = [];
  top.flowDefs = e.flowDefs;
}
-- Note that below we IGNORE the flow deps of the lhs if we know what it is
-- this is because by default the lhs will have 'taking ref' flow deps (see above)
aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = 
    case e.flowVertexInfo of
    | hasVertex(vertex) -> vertex.synVertex(q.attrDcl.fullName) :: vertex.eqVertex
    | noVertex() -> e.flowDeps
    end;
  top.flowDefs = e.flowDefs;
}
aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = 
    case e.flowVertexInfo of
    | hasVertex(vertex) -> vertex.inhVertex(q.attrDcl.fullName) :: vertex.eqVertex
    | noVertex() -> e.flowDeps
    end;
  top.flowDefs = e.flowDefs;
}
aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = []; -- errors, who cares?
  top.flowDefs = e.flowDefs;
}
aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  -- The general theory:
  -- ... some expr ... decorate EXP1 with { ... inhs ... } ...
  -- is equivalent to:
  -- local ANON :: EXP1.typerep = EXP1;
  -- ANON.inhN = inhNexp; -- etc...
  -- an the expr is now ... ANON ...
  
  -- We don't actually do this transform, of course, but that's what we're representing
  -- this as to the flow analysis, and justifies all the choices below:

  -- First, generate our "anonymous" flow vertex name:
  inh.decorationVertex = "__decorate" ++ toString(genInt()) ++ ":line" ++ toString(top.location.line);

  -- Next, emit the "local equation" for this anonymous flow vertex.
  -- This means only the deps in 'e', see above conceptual transformation to see why.
  -- N.B. 'inh.flowDefs' will emit 'localInhEq's for this anonymous flow vertex.
  top.flowDefs = e.flowDefs ++ inh.flowDefs ++
    [anonEq(top.signature.fullName, inh.decorationVertex, performSubstitution(e.typerep, top.finalSubst).typeName, top.location, e.flowDeps)];

  -- Now, we represent ourselves to anything that might use us specially
  -- as though we were a reference to this anonymous local
  top.flowVertexInfo = hasVertex(anonVertexType(inh.decorationVertex));

  -- Finally, our standard flow deps mimic those of a local: "taking a reference"
  -- This are of course ignored when treated specially.
  top.flowDeps = [anonEqVertex(inh.decorationVertex)] ++
    depsForTakingRef(anonVertexType(inh.decorationVertex), performSubstitution(e.typerep, top.finalSubst).typeName, top.flowEnv);
}

autocopy attribute decorationVertex :: String occurs on ExprInhs, ExprInh;

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e1::Expr ';'
{
  top.flowDeps = e1.flowDeps;
  top.flowDefs = e1.flowDefs ++ 
    if !null(lhs.errors) then [] else
    case lhs of
    | exprLhsExpr(q) -> [anonInhEq(top.signature.fullName, top.decorationVertex, q.attrDcl.fullName, e1.flowDeps)]
    end;
    
}
aspect production exprInhsEmpty
top::ExprInhs ::= 
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production exprInhsOne
top::ExprInhs ::= h::ExprInh
{
  top.flowDeps = h.flowDeps;
  top.flowDefs = h.flowDefs;
}
aspect production exprInhsCons
top::ExprInhs ::= h::ExprInh  t::ExprInhs
{
  top.flowDeps = h.flowDeps ++ t.flowDeps;
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}


aspect production trueConst
top::Expr ::= 'true'
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production falseConst
top::Expr ::= 'false'
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production not
top::Expr ::= '!' e1::Expr
{
  top.flowDeps = e1.flowDeps;
  top.flowDefs = e1.flowDefs;
}
aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps ++ e3.flowDeps;
  -- TODO: it's possible we could create an anon flow vertex type here
  -- that duplicates all vertexes to both 'then' and 'else'...
  top.flowDefs = e1.flowDefs ++ e2.flowDefs ++ e3.flowDefs;
}
aspect production intConst
top::Expr ::= i::Int_t
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production floatConst
top::Expr ::= f::Float_t
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production neg
top::Expr ::= '-' e1::Expr
{
  top.flowDeps = e1.flowDeps;
  top.flowDefs = e1.flowDefs;
}
aspect production stringConst
top::Expr ::= s::String_t
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.flowDeps = []; -- error, so who cares?
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}
aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}


aspect production exprsEmpty
top::Exprs ::=
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.flowDeps = e1.flowDeps ++ e2.flowDeps;
  top.flowDefs = e1.flowDefs ++ e2.flowDefs;
}


aspect production missingAppExpr
top::AppExpr ::= '_'
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}

aspect production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  top.flowDeps = es.flowDeps ++ e.flowDeps;
  top.flowDefs = es.flowDefs ++ e.flowDefs;
}
aspect production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production emptyAppExprs
top::AppExprs ::=
{
  top.flowDeps = [];
  top.flowDefs = [];
}
aspect production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production snocAnnoAppExprs
top::AnnoAppExprs ::= es::AnnoAppExprs ',' e::AnnoExpr
{
  top.flowDeps = es.flowDeps ++ e.flowDeps;
  top.flowDefs = es.flowDefs ++ e.flowDefs;
}
aspect production oneAnnoAppExprs
top::AnnoAppExprs ::= e::AnnoExpr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production emptyAnnoAppExprs
top::AnnoAppExprs ::=
{
  top.flowDeps = [];
  top.flowDefs = [];
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
  top.flowDefs = e.flowDefs; -- I guess? I haven't thought about this exactly.
}


-- builtins

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production errorLength
top::Expr ::= e::Decorated Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}

aspect production toIntFunction
top::Expr ::= 'toInt' '(' e1::Expr ')'
{
  top.flowDeps = e1.flowDeps;
  top.flowDefs = e1.flowDefs;
}

aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e1::Expr ')'
{
  top.flowDeps = e1.flowDeps;
  top.flowDefs = e1.flowDefs;
}

aspect production toStringFunction
top::Expr ::= 'toString' '(' e1::Expr ')'
{
  top.flowDeps = e1.flowDeps;
  top.flowDefs = e1.flowDefs;
}

aspect production newFunction
top::Expr ::= 'new' '(' e1::Expr ')'
{
  -- Emit nothing except the keepDeps, for a vertex node
  top.flowDeps =
    case e1.flowVertexInfo of
    | hasVertex(vertex) -> vertex.eqVertex
    | noVertex() -> e1.flowDeps
    end;
  top.flowDefs = e1.flowDefs;
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::Type ',' es::Expr ',' el::Expr ')'
{
  top.flowDeps = es.flowDeps ++ el.flowDeps;
  top.flowDefs = es.flowDefs ++ el.flowDefs;
}



---- FROM COPPER TODO
--grammar silver:modification:copper;

-- These are all errors, basically.

aspect production actionChildReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
  top.flowDefs = [];
}

aspect production pluckTerminalReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
  top.flowDefs = [];
}

aspect production disambigLexemeReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
  top.flowDefs = [];
}

aspect production parserAttributeReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
  top.flowDefs = [];
}

aspect production termAttrValueReference
top::Expr ::= q::Decorated QName
{
  top.flowDeps = [];
  top.flowDefs = [];
}


-- FROM LET TODO
attribute flowDefs, flowEnv occurs on AssignExpr;

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = la.flowDefs ++ e.flowDefs;
}

aspect production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.flowDefs = a1.flowDefs ++ a2.flowDefs;
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::Type '=' e::Expr
{
  top.flowDefs = e.flowDefs;
}

aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName  fi::ExprVertexInfo  fd::[FlowVertex]
{
  top.flowDeps = fd;
  top.flowDefs = [];
  top.flowVertexInfo = fi;
}


-- FROM PATTERN TODO
attribute flowDeps, flowDefs, flowEnv occurs on PrimPatterns, PrimPattern;

aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::Type pr::PrimPatterns f::Expr
{
  -- If we take e.flowDeps ++ f.flowDeps, look them all up in the production
  -- graph, and take the union, then filter down to just those on our anon vertex
  -- we can discover what's needed, and use that to raise errors.
  
  -- We do have to do the lookups, though: we can't just use those Deps directly.
  -- consider 'case e of prod(x) -> decorate x.syn with ...'
  -- that introduces the use of 'x.syn' in a flowDef, and then emits the anonEq in flowDep
  -- so we DO need to be transitive. Unfortunately.
  
  


  -- Let's make sure for decorated types, we only demand what's necessary for forward
  -- evaluation.
  top.flowDeps = pr.flowDeps ++ f.flowDeps ++
    case e.flowVertexInfo of
    | hasVertex(vertex) -> vertex.fwdVertex :: vertex.eqVertex
    | noVertex() -> e.flowDeps
    end;

  -- TODO: again, like 'if', we could be doing a kind of union flowVertexInfo
  -- thingy here.
  top.flowDefs = e.flowDefs ++ pr.flowDefs ++ f.flowDefs;
}

aspect production onePattern
top::PrimPatterns ::= p::PrimPattern
{
  top.flowDeps = p.flowDeps;
  top.flowDefs = p.flowDefs;
}
aspect production consPattern
top::PrimPatterns ::= p::PrimPattern '|' ps::PrimPatterns
{
  top.flowDeps = p.flowDeps ++ ps.flowDeps;
  top.flowDefs = p.flowDefs ++ ps.flowDefs;
}

aspect production prodPatternNormal
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production prodPatternGadt
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production integerPattern
top::PrimPattern ::= i::Int_t '->' e::Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production stringPattern
top::PrimPattern ::= i::String_t '->' e::Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production booleanPattern
top::PrimPattern ::= i::String '->' e::Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production nilPattern
top::PrimPattern ::= e::Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}
aspect production conslstPattern
top::PrimPattern ::= h::Name t::Name e::Expr
{
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
}

