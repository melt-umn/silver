grammar silver:compiler:translation:java:core;


import silver:compiler:driver only noOrigins;
import silver:compiler:definition:flow:ast;

{--
 - A translation string that will be a thunk instead of the raw value.
 - BUT, is permitted to be a raw value IF it's totally safe to do so.
 -}
synthesized attribute lazyTranslation :: String;

attribute lazyTranslation, translation occurs on Expr;
attribute lazyTranslation occurs on Exprs;

-- Record decoration sites for translation attributes.
monoid attribute initTransDecSites::String occurs on
  Expr, Exprs, AppExprs, AppExpr, ExprInhs, ExprInh;
propagate initTransDecSites on
  Expr, Exprs, AppExprs, AppExpr, ExprInhs, ExprInh;

-- `translation` should yield an expression of the appropriate Java type.
--   e.g. `NodeFactory<StringCatter>` for a (String ::= ...)
-- At the moment, this requires a lot of casts. Oh well.

-- `lazyTranslation` can yield any type, since it's only ever immediately used
--   to put values in a `new Object[]{...}`

synthesized attribute invokeTranslation :: String occurs on Expr;
synthesized attribute invokeLazyTranslation :: String occurs on Expr;
inherited attribute invokeIsUnique :: Boolean occurs on Expr;
inherited attribute invokeArgs :: Decorated AppExprs with {decorate, decSiteVertexInfo, alwaysDecorated, appProd} occurs on Expr;
inherited attribute invokeNamedArgs :: Decorated AnnoAppExprs occurs on Expr;
inherited attribute sameProdAsProductionDefinedOn :: Boolean occurs on Expr;

{--
 - A translation string where skolems in run-time type info should be generalized.
 - E.g. global id :: (a ::= a) = \ x::a -> x; it is safe and more general for the lambda
 - to have runtime type (var ::= var) rather than (skolem ::= skolem).
 -}
synthesized attribute generalizedTranslation :: String occurs on Expr;

aspect default production
top::Expr ::=
{
  top.invokeTranslation =
    -- dynamic method invocation
    s"${top.translation}.invoke(${makeOriginContextRef(top)}, new Object[]{${argsTranslation(top.invokeArgs)}}, ${namedargsTranslation(top.invokeNamedArgs)})";
  top.invokeLazyTranslation = wrapThunk(top.invokeTranslation, top.frame.lazyApplication);
  top.generalizedTranslation = top.translation;
}

aspect production errorExpr
top::Expr ::= msg::[Message]
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production errorReference
top::Expr ::= msg::[Message]  q::Decorated! QName
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production childReference
top::Expr ::= q::Decorated! QName
{
  local childIDref :: String =
    top.frame.className ++ ".i_" ++ q.lookupValue.fullName;

  top.translation =
    if !isDecorable(q.lookupValue.typeScheme.typerep, top.env) then
      -- Reference to a primitive (not decorated or undecorated):
      s"context.<${top.finalType.transType}>childAsIs(${childIDref})"
    else if !top.finalType.isDecorated then
      -- Undecorated reference to a nonterminal:
      -- the reason we do .childDecorated().undecorate() is that it's not safe to mix as-is/decorated accesses to the same child.
      -- this is a potential source of minor inefficiency for functions that do not decorate.
      s"((${top.finalType.transType})context.childDecorated(${childIDref}).undecorate())"
    else if top.finalType.isUniqueDecorated && top.alwaysDecorated then
      -- Unique reference to a child that is a remote decoration site:
      -- Note that this is not cached; uniqueness guarantees that it should only be demanded once.
      s"context.createDecoratedChild(${childIDref})"
    else
      -- Normal decorated reference:
      -- This may create the child, or demand it via the remote decoration site if the child has one.
      s"context.childDecorated(${childIDref})";

  -- Mirrors the above, but with lazyness:
  top.lazyTranslation =
    if !top.frame.lazyApplication then top.translation else
    if !isDecorable(q.lookupValue.typeScheme.typerep, top.env)
    then s"context.childAsIsLazy(${childIDref})"
    else if !top.finalType.isDecorated
    then s"common.Thunk.transformUndecorate(context.childDecoratedLazy(${childIDref}))"
    else if top.finalType.isUniqueDecorated && top.alwaysDecorated then
      wrapThunk(top.translation, true)
    else
      s"context.childDecoratedLazy(${childIDref})";
}

aspect production localReference
top::Expr ::= q::Decorated! QName
{
  top.translation =
    if !isDecorable(q.lookupValue.typeScheme.typerep, top.env)
    then s"context.<${top.finalType.transType}>localAsIs(${q.lookupValue.dcl.attrOccursIndex})"
    else if !top.finalType.isDecorated
    then s"((${top.finalType.transType})context.localDecorated(${q.lookupValue.dcl.attrOccursIndex}).undecorate())"
    else if top.finalType.isUniqueDecorated && top.alwaysDecorated then
      s"context.evalLocalDecorated(${q.lookupValue.dcl.attrOccursIndex})"
    else
      s"context.localDecorated(${q.lookupValue.dcl.attrOccursIndex})";
  -- reminder: look at comments for childReference

  top.lazyTranslation =
    if !top.frame.lazyApplication then top.translation else
    if !isDecorable(q.lookupValue.typeScheme.typerep, top.env)
    then s"context.localAsIsLazy(${q.lookupValue.dcl.attrOccursIndex})"
    else if !top.finalType.isDecorated
    then s"common.Thunk.transformUndecorate(context.localDecoratedLazy(${q.lookupValue.dcl.attrOccursIndex}))"
    else if top.finalType.isUniqueDecorated && top.alwaysDecorated then
      wrapThunk(top.translation, true)
    else
      s"context.localDecoratedLazy(${q.lookupValue.dcl.attrOccursIndex})";
}

aspect production lhsReference
top::Expr ::= q::Decorated! QName
{
  top.translation =
    if top.finalType.isDecorated
    then "context"
    else s"((${top.finalType.transType})context.undecorate())";

  top.lazyTranslation = top.translation;
}

aspect production forwardReference
top::Expr ::= q::Decorated! QName
{
  top.translation =
    if top.finalType.isDecorated
    then "context.forward()"
    else s"((${top.finalType.transType})context.forward().undecorate())";

  -- this might evaluate the forward equation, so suspend it as a thunk
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production productionReference
top::Expr ::= q::Decorated! QName
{
  local factory::String =
    if null(typeScheme.contexts)
    then makeProdName(q.lookupValue.fullName) ++ ".factory"
    else s"new ${makeProdName(q.lookupValue.fullName)}.Factory(${implode(", ", contexts.transContexts)})";
  local prodArity::Integer = length(q.lookupValue.dcl.namedSignature.inputElements);
  top.translation =
    case top.finalType.outputType of
    | dispatchType(ns) ->
      s"new common.DispatchNodeFactory<${q.lookupValue.dcl.namedSignature.outputElement.typerep.transType}>(${factory}, ${toString(length(ns.inputElements))})"
    | _ -> factory
    end;
  top.lazyTranslation = top.translation;
  top.invokeTranslation =
    case top.finalType.outputType of
    | dispatchType(fn) -> s"${top.translation}.invoke(${makeOriginContextRef(top)}, new Object[]{${argsTranslation(top.invokeArgs)}}, ${namedargsTranslation(top.invokeNamedArgs)})"
    | _ ->
      -- static constructor invocation
      s"new ${makeProdName(q.lookupValue.fullName)}(${implode(", ",
        makeNewConstructionOrigin(top, !top.sameProdAsProductionDefinedOn) ++
        toString(top.invokeIsUnique) ::
        contexts.transContexts ++
        map((.lazyTranslation), top.invokeArgs.exprs ++ reorderedAnnoAppExprs(top.invokeNamedArgs)))})"
    end;
  -- Safe to be eager here, since the only work being done is constructing a term.
  -- This means that large nested terms will be built eagerly, but we rarely define a term without
  -- demanding it, so overall this is a performance win.
  -- Note that this shouldn't create any cycles, since we still use lazyTranslation from the children;
  -- any function calls/references inside some complex nested term will still be done lazily.
  top.invokeLazyTranslation = top.invokeTranslation;
}

aspect production functionReference
top::Expr ::= q::Decorated! QName
{
  -- functions, unlike productions, can return a type variable.
  -- as such, we have to cast it to the real inferred final type.
  top.translation =
    if top.typerep.transType != top.finalType.transType
    then s"common.Util.<${top.finalType.transType}>uncheckedCast(${top.lazyTranslation})"
    else top.lazyTranslation;
  top.lazyTranslation =
    if null(typeScheme.contexts)
    then makeProdName(q.lookupValue.fullName) ++ ".factory"
    else s"${makeProdName(q.lookupValue.fullName)}.getFactory(${implode(", ", contexts.transContexts)})";

  local invokeTrans::String =
    -- static method invocation
    s"${makeProdName(q.lookupValue.fullName)}.invoke(${implode(", ",
      [makeOriginContextRef(top)] ++
      contexts.transContexts ++
      map((.lazyTranslation), top.invokeArgs.exprs))})";
  top.invokeTranslation =
    if top.typerep.outputType.transType != top.finalType.outputType.transType
    then s"common.Util.<${top.finalType.outputType.transType}>uncheckedCast(${invokeTrans})"
    else invokeTrans;
}

aspect production classMemberReference
top::Expr ::= q::Decorated! QName
{
  local transContextMember::String =
    s"${instHead.transContext}.${makeInstanceMemberAccessorName(q.lookupValue.fullName)}(${implode(", ", contexts.transContexts)})";
  local resolvedDcl::InstDclInfo = head(instHead.resolved);
  top.translation =
    if !null(resolvedDcl.typeScheme.boundVars) || !contains(q.lookupValue.fullName, resolvedDcl.definedMembers)
    -- The resolved instance has a polymorphic implementation for the member,
    -- or relies on a default implementation, which may have a more general type.
    -- This means that we must insert a cast to the more specific inferred result type.
    then s"common.Util.<${top.finalType.transType}>uncheckedCast(${transContextMember})"
    else transContextMember;
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production globalValueReference
top::Expr ::= q::Decorated! QName
{
  local directThunk :: String =
    s"${makeName(q.lookupValue.dcl.sourceGrammar)}.Init.global_${fullNameToShort(q.lookupValue.fullName)}" ++
    if null(typeScheme.contexts) then ""
    else s"(${implode(", ", contexts.transContexts)})";

  top.translation = s"common.Util.<${top.finalType.transType}>uncheckedCast(${directThunk}.eval())";
  top.lazyTranslation = 
    if top.frame.lazyApplication
    then directThunk
    else s"${directThunk}.eval()";
}
aspect production errorApplication
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs annos::Decorated! AnnoAppExprs
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production functionInvocation
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs annos::Decorated! AnnoAppExprs
{
  top.translation = e.invokeTranslation;
  top.lazyTranslation = e.invokeLazyTranslation;

  e.invokeIsUnique = !null(top.uniqueRefs);
  e.invokeArgs = es;
  e.invokeNamedArgs = annos;
  e.sameProdAsProductionDefinedOn =
    case e of
    | baseExpr(qn) -> qn.name == last(explode(":", top.frame.fullName))
    | _ -> false
    end;
}

fun argsTranslation
String ::= e::Decorated AppExprs with {decorate, decSiteVertexInfo, alwaysDecorated, appProd} =
  implode(", ", map((.lazyTranslation), e.exprs));
fun namedargsTranslation String ::= e::Decorated AnnoAppExprs =
  if null(e.exprs) then "null"
  else s"new Object[]{${implode(", ", map((.lazyTranslation), reorderedAnnoAppExprs(e)))}}";
fun namedargsTranslationNOReorder String ::= e::Decorated AnnoAppExprs =
  if null(e.exprs) then "null"
  else s"new Object[]{${implode(", ", map((.lazyTranslation), e.exprs))}}";

aspect production partialApplication
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs annos::Decorated! AnnoAppExprs
{
  local step1 :: String = e.translation;
  -- Note: we check for nullity of the index lists instead of use
  -- isPartial here... Because we may supply ALL values (thus, NOT isPartial!)
  -- of one of the param lists, but that means we still need to apply it!!
  local step2 :: String =
    if !null(es.appExprIndicies) then
      step1 ++ ".invokePartial(" ++
      s"new int[]{${implode(", ", map(toString, es.appExprIndicies))}}, " ++
      s"new Object[]{${argsTranslation(es)}})"
    else step1;
  local step3 :: String =
    if !null(annos.annoIndexConverted) || !null(annos.annoIndexSupplied) then
      step2 ++ ".invokeNamedPartial(" ++
      (if null(annos.annoIndexConverted) then "null"
       else s"new int[]{${implode(", ", map(toString, annos.annoIndexConverted))}}") ++ ", " ++
      (if null(annos.annoIndexSupplied) then "null"
       else s"new int[]{${implode(", ", map(toString, annos.annoIndexSupplied))}}") ++ ", " ++
      namedargsTranslationNOReorder(annos) ++ ")"
    else step2;

  -- The theory is the `e.translation` we started with has the right type, so we don't need a cast here. In theory.
  top.translation = step3;

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production dispatchApplication
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs annos::Decorated! AnnoAppExprs
{
  top.translation = e.invokeTranslation;
  top.lazyTranslation = e.invokeLazyTranslation;

  e.invokeIsUnique = true;
  e.invokeArgs = es;
  e.invokeNamedArgs = annos;
  e.sameProdAsProductionDefinedOn =
    case e of
    | baseExpr(qn) -> qn.name == last(explode(":", top.frame.fullName))
    | _ -> false
    end;
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production inhUndecoratedAccessErrorHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production transUndecoratedAccessErrorHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production unknownDclAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  top.translation = s"((${top.finalType.transType})${e.translation}.forwardOrThis())";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.translation = wrapAccessWithOT(top, s"${e.translation}.<${top.finalType.transType}>synthesized(${q.attrOccursIndex})");

  top.lazyTranslation = 
    case e, top.frame.lazyApplication of
    | childReference(cqn), true -> 
        if isDecorable(cqn.lookupValue.typeScheme.typerep, top.env)
        then
          s"context.childDecoratedSynthesizedLazy(${top.frame.className}.i_${cqn.lookupValue.fullName}, ${q.attrOccursIndex})"
        else
          s"context.childAsIsSynthesizedLazy(${top.frame.className}.i_${cqn.lookupValue.fullName}, ${q.attrOccursIndex})"
    | lhsReference(_), true ->
        s"context.contextSynthesizedLazy(${q.attrOccursIndex})"
    | _, _ -> wrapThunk(top.translation, top.frame.lazyApplication)
    end;
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.translation = wrapAccessWithOT(top, s"${e.translation}.<${top.finalType.transType}>inherited(${q.attrOccursIndex})");

  top.lazyTranslation = 
    case e, top.frame.lazyApplication of
    | lhsReference(_), true -> s"context.contextInheritedLazy(${q.attrOccursIndex})"
    | _, _ -> wrapThunk(top.translation, top.frame.lazyApplication)
    end;
}

aspect production transDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  -- TODO: Origin tracking?
    top.translation =
    if !top.finalType.isDecorated then
      s"((${top.finalType.transType})${e.translation}.translation(${q.attrOccursIndex}, ${q.attrOccursIndex}_inhs, ${q.attrOccursIndex}_dec_site).undecorate())"
    else if top.finalType.isUniqueDecorated && top.alwaysDecorated &&
        case e of
        | childReference(cqn) -> true
        | localReference(lqn) -> true
        | _ -> false
        end then
      -- Unique reference to a translation attribute on a child or local that is a remote decoration site:
      -- Note that this is not cached; uniqueness guarantees that it should only be demanded once.
      s"${e.translation}.evalTrans(${q.attrOccursIndex}, ${q.attrOccursIndex}_inhs)"
    else
      -- Normal decorated reference:
      -- This may create the child, or demand it via the remote decoration site if the child has one.
      s"${e.translation}.translation(${q.attrOccursIndex}, ${q.attrOccursIndex}_inhs, ${q.attrOccursIndex}_dec_site)";

  -- TODO: Specialized thunks for accesses on child/local, for efficency
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);

  top.initTransDecSites <-
    case top.decSiteVertexInfo of
    | just(decSite) when top.finalType.isUniqueDecorated && top.alwaysDecorated ->
      case e of
      | childReference(cqn) ->
	      s"\t\t// Decoration site for ${q.unparse}: ${decSite.vertexName}\n" ++
        s"\t\t${top.frame.className}.childInheritedAttributes[${top.frame.className}.i_${cqn.lookupValue.fullName}][${q.attrOccursInitIndex}_dec_site] = " ++
        s"(context) -> ${refAccessTranslation(top.env, top.flowEnv, top.frame.lhsNtName, decSite)};\n"
      | localReference(lqn) ->
	      s"\t\t// Decoration site for ${q.unparse}: ${decSite.vertexName}\n" ++
        s"\t\t${top.frame.className}.localInheritedAttributes[${lqn.lookupValue.dcl.attrOccursIndex}][${q.attrOccursInitIndex}_dec_site] = " ++
        s"(context) -> ${refAccessTranslation(top.env, top.flowEnv, top.frame.lhsNtName, decSite)};\n"
      | _ -> ""
      end
    | _ -> ""
    end;
}

aspect production terminalAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  local accessor :: String =
    if q.name == "lexeme" || q.name == "location"
    then q.name
    else if q.name == "line"
    then "getLine()"
    else if q.name == "column"
    then "getColumn()"
    else if q.name == "filename"
    then "getFilename()"
    else error("Not possible -- an error should have been raised about " ++ q.unparse);

  top.translation = s"((${top.finalType.transType})${e.translation}.${accessor})";

  top.lazyTranslation = top.translation;
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  local accessTrans::String = s"((${makeAnnoName(q.attrDcl.fullName)})${e.translation}).getAnno_${makeIdName(q.attrDcl.fullName)}()";
  -- Note that the transType is specific to the nonterminal we're accessing from.
  top.translation =
    if q.attrDcl.typeScheme.typerep.transType != top.finalType.transType
    then s"common.Util.<${top.finalType.transType}>uncheckedCast(${accessTrans})"
    else accessTrans;
  
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production synDataAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.translation = wrapAccessWithOT(top, s"${e.translation}.<${top.finalType.transType}>synthesized(${q.attrOccursIndex})");
  
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.translation = s"((common.Decorable)${e.translation})" ++ 
    case inh of
    | exprInhsEmpty() -> ".decorate(context, (common.Lazy[])null)"
      -- Note: we don't NEED to pass context here, but it's good for error messages!
      -- When the user forgets to provide inherited attributes
      -- (especially important because we're implicitly inserted when accessing attributes
      --  from undecorated nodes, and this is a common error for new silverers.)
    | _ -> ".decorate(context, common.Util.populateInh(" ++
      case e.finalType of
      -- Don't know the actual number of attributes for skolems with occurs-on contexts,
      -- fall back to using the max index.
      | skolemType(_) -> foldr1(\ i1::String i2::String -> s"Math.max(${i1}, ${i2})", inh.nameTrans) ++ " + 1"
      | t -> s"${makeNTName(t.typeName)}.num_inh_attrs"
      end ++ ", " ++
      s"new int[]{${implode(", ", inh.nameTrans)}}, " ++ 
      s"new common.Lazy[]{${implode(", ", inh.valueTrans)}}))"
    end;

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

synthesized attribute nameTrans :: [String];
synthesized attribute valueTrans :: [String];

attribute nameTrans occurs on ExprInhs, ExprInh, ExprLHSExpr;
attribute valueTrans occurs on ExprInhs, ExprInh;

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  top.nameTrans = lhs.nameTrans;
  top.valueTrans = [wrapLazy(e)]; -- TODO: this is another appearance of the nested lazy problem...
}

aspect production exprInhsEmpty
top::ExprInhs ::= 
{
  top.nameTrans = [];
  top.valueTrans = [];
}

aspect production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.nameTrans = lhs.nameTrans;
  top.valueTrans = lhs.valueTrans;
}

aspect production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.nameTrans = lhs.nameTrans ++ inh.nameTrans;
  top.valueTrans = lhs.valueTrans ++ inh.valueTrans;
}


aspect production exprLhsExpr
top::ExprLHSExpr ::= q::QNameAttrOccur
{
  top.nameTrans = [q.attrOccursIndex];
}



aspect production decorationSiteExpr
top::Expr ::= '@' e::Expr
{
  top.translation =
    s"new ${top.finalType.transType}.DecorationSiteWrapper(${
      if top.finalType.isTracked then makeOriginContextRef(top) ++ ".makeNewConstructionOrigin(true), " else ""}${e.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production trueConst
top::Expr ::='true'
{
  top.translation = "true";
  top.lazyTranslation = top.translation;
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.translation = "false";
  top.lazyTranslation = top.translation;
}
{- TODO: We should re-enable the specialized translations here for primitive types,
 - but that requires some attributes on the operands that we can't supply here.
 - See https://github.com/melt-umn/silver/issues/812
aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.translation = s"(${e1.translation} && ${e2.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.translation = s"(${e1.translation} || ${e2.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production notOp
top::Expr ::= '!' e::Expr
{
  top.translation = s"(!${e.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
-}
aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  {-
    We need to cast the else branch to the correct type, as otherwise
    Java tries to cast it to the type of the then branch, which
    doesn't always work.
  -}
  top.translation = s"(${e1.translation} ? ${e2.translation} : (${top.finalType.transType})${e3.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.translation = s"((int)${i.lexeme})";
  top.lazyTranslation = top.translation;
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.translation = s"((float)${f.lexeme})";
  top.lazyTranslation = top.translation;
}

aspect production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  top.translation = e.translation;
  top.lazyTranslation = e.lazyTranslation;
}
{-
aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.translation = s"(${e1.translation} + ${e2.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.translation = s"(${e1.translation} - ${e2.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.translation = s"(${e1.translation} * ${e2.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
aspect production divide
top::Expr ::= e1::Expr _ e2::Expr
{
  top.translation = s"(${e1.translation} / ${e2.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.translation = s"(${e1.translation} % ${e2.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
aspect production neg
top::Expr ::= '-' e::Expr
{
  top.translation = s"(-${e.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
-}
aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  top.translation = s"new ${makeTerminalName(t.typerep.typeName)}(${es.translation}, (silver.core.NLocation)${el.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.translation = s"(new common.StringCatter(${s.lexeme}))";
  top.lazyTranslation = top.translation;
}

aspect production exprsEmpty
top::Exprs ::=
{
  top.lazyTranslation = "";
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.lazyTranslation = e.lazyTranslation;
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.lazyTranslation = e1.lazyTranslation ++ ", " ++ e2.lazyTranslation;
}

fun wrapThunk String ::= exp::String  beLazy::Boolean =
  if beLazy then wrapThunkText(exp, "Object") else exp;
fun wrapThunkText String ::= exp::String  ty::String =
  s"new common.Thunk<${ty}>(new common.Thunk.Evaluable<${ty}>() { public final ${ty} eval() { return ${exp}; } })";
  --TODO: java lambdas are bugged
  --return s"new common.Thunk<${ty}>(() -> ${exp})";
function wrapLazy
String ::= e::Decorated Expr
{
  -- It *may* be wise to leave `Lazy`s as anon classes, rather than lambdas.
  -- This splits all the Thunk methods across each `Lazy` instead of concentrating
  -- them all on the top-level class, like `Init`
  -- We're *unlikely* to be close to hitting the 64K method limit, but
  -- we have hit the 64K bytecode limit in the past, which is why `Init` farms
  -- initialization code out across each production. So who knows.
  local swizzleOrigins::String = if e.config.noOrigins then "" else "final common.OriginContext originCtx = context.originCtx;";
  local loc::Location = getParsedOriginLocationOrFallback(e);
  local fileName::String =
    case searchEnvTree(e.grammarName, e.compiledGrammars) of
    | r :: _ -> r.grammarSource
    | [] -> ""
    end ++ loc.filename;
  local sourceLocationTrans::String = s"new silver.core.Ploc(new common.StringCatter(\"${fileName}\"), ${toString(loc.line)}, ${toString(loc.column)}, ${toString(loc.endLine)}, ${toString(loc.endColumn)}, ${toString(loc.index)}, ${toString(loc.endIndex)})";
  return s"new common.Lazy() { public final Object eval(final common.DecoratedNode context) { ${swizzleOrigins} return ${e.translation}; } public final silver.core.NLocation getSourceLocation() { return ${sourceLocationTrans}; } }";
}
