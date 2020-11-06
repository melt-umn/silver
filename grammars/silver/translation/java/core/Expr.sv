grammar silver:translation:java:core;

import silver:util;

import silver:analysis:typechecking:core only finalSubst;

function finalType
Type ::= e::Decorated Expr
{
  return performSubstitution(e.typerep, e.finalSubst);
}

{--
 - A translation string that will be a thunk instead of the raw value.
 - BUT, is permitted to be a raw value IF it's totally safe to do so.
 -}
synthesized attribute lazyTranslation :: String;

attribute lazyTranslation, translation occurs on Expr;
attribute lazyTranslation occurs on Exprs;

-- `translation` should yield an expression of the appropriate Java type.
--   e.g. `NodeFactory<StringCatter>` for a (String ::= ...)
-- At the moment, this requires a lot of casts. Oh well.

-- `lazyTranslation` can yield any type, since it's only ever immediately used
--   to put values in a `new Object[]{...}`

aspect production errorExpr
top::Expr ::= msg::[Message]
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production errorReference
top::Expr ::= msg::[Message]  q::Decorated QName
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  local childIDref :: String =
    top.frame.className ++ ".i_" ++ q.lookupValue.fullName;

  top.translation =
    if q.lookupValue.typerep.isDecorable
    then if finalType(top).isDecorable
         then s"((${finalType(top).transType})context.childDecorated(${childIDref}).undecorate())"
         else s"((${finalType(top).transType})context.childDecorated(${childIDref}))"
    else s"((${finalType(top).transType})context.childAsIs(${childIDref}))";
  -- the reason we do .childDecorated().undecorate() is that it's not safe to mix as-is/decorated accesses to the same child.
  -- this is a potential source of minor inefficiency for functions that do not decorate.

  top.lazyTranslation =
    if !top.frame.lazyApplication then top.translation else
    if q.lookupValue.typerep.isDecorable
    then if finalType(top).isDecorable
         then s"common.Thunk.transformUndecorate(context.childDecoratedLazy(${childIDref}))"
         else s"context.childDecoratedLazy(${childIDref})"
    else s"context.childAsIsLazy(${childIDref})";
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
  top.translation =
    if q.lookupValue.typerep.isDecorable
    then if finalType(top).isDecorable
         then s"((${finalType(top).transType})context.localDecorated(${q.lookupValue.dcl.attrOccursIndex}).undecorate())"
         else s"((${finalType(top).transType})context.localDecorated(${q.lookupValue.dcl.attrOccursIndex}))"
    else s"((${finalType(top).transType})context.localAsIs(${q.lookupValue.dcl.attrOccursIndex}))";
  -- reminder: look at comments for childReference

  top.lazyTranslation =
    if !top.frame.lazyApplication then top.translation else
    if q.lookupValue.typerep.isDecorable
    then if finalType(top).isDecorable
         then s"common.Thunk.transformUndecorate(context.localDecoratedLazy(${q.lookupValue.dcl.attrOccursIndex}))"
         else s"context.localDecoratedLazy(${q.lookupValue.dcl.attrOccursIndex})"
    else s"context.localAsIsLazy(${q.lookupValue.dcl.attrOccursIndex})";
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.translation =
    if finalType(top).isDecorable
    then s"((${finalType(top).transType})context.undecorate())"
    else "context";

  top.lazyTranslation = top.translation;
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.translation =
    if finalType(top).isDecorable
    then s"((${finalType(top).transType})context.forward().undecorate())"
    else "context.forward()";

  -- this might evaluate the forward equation, so suspend it as a thunk
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production productionReference
top::Expr ::= q::Decorated QName
{
  top.translation = makeClassName(q.lookupValue.fullName) ++ ".factory";
  top.lazyTranslation = top.translation;
}

aspect production functionReference
top::Expr ::= q::Decorated QName
{
  -- functions, unlike productions, can return a type variable.
  -- as such, we have to cast it to the real inferred final type.
  top.translation = s"((${finalType(top).transType})${top.lazyTranslation})";
  top.lazyTranslation = makeClassName(q.lookupValue.fullName) ++ ".factory";
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  local directThunk :: String =
    s"${makeName(q.lookupValue.dcl.sourceGrammar)}.Init.${fullNameToShort(q.lookupValue.fullName)}";

  top.translation = s"((${finalType(top).transType})${directThunk}.eval())";
  top.lazyTranslation = 
    if top.frame.lazyApplication
    then directThunk
    else top.translation;
}

aspect production errorApplication
top::Expr ::= e::Decorated Expr es::AppExprs annos::AnnoAppExprs
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs
{
  top.translation = 
    case e of 
    | functionReference(q) -> -- static method invocation
        s"((${finalType(top).transType})${makeClassName(q.lookupValue.fullName)}.invoke(${argsTranslation(es)}))"
    | productionReference(q) -> -- static constructor invocation
        s"((${finalType(top).transType})new ${makeClassName(q.lookupValue.fullName)}(${implode(", ", map((.lazyTranslation), es.exprs ++ reorderedAnnoAppExprs(annos)))}))"
    | _ -> -- dynamic method invocation
        s"((${finalType(top).transType})${e.translation}.invoke(new Object[]{${argsTranslation(es)}}, ${namedargsTranslation(annos)}))" 
    end ;

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

function argsTranslation
String ::= e::Decorated AppExprs
{
  -- TODO: This is the ONLY use of .exprs  We could eliminate that, if we fix this.
  return implode(", ", map((.lazyTranslation), e.exprs));
}
function namedargsTranslation
String ::= e::Decorated AnnoAppExprs
{
  -- TODO: This is the ONLY use of .exprs  We could eliminate that, if we fix this.
  return if null(e.exprs) then "null"
  else s"new Object[]{${implode(", ", map((.lazyTranslation), reorderedAnnoAppExprs(e)))}}";
}
function namedargsTranslationNOReorder
String ::= e::Decorated AnnoAppExprs
{
  -- TODO: This is the ONLY use of .exprs  We could eliminate that, if we fix this.
  return if null(e.exprs) then "null"
  else s"new Object[]{${implode(", ", map((.lazyTranslation), e.exprs))}}";
}

function int2str String ::= i::Integer { return toString(i); }

aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs
{
  local step1 :: String = e.translation;
  -- Note: we check for nullity of the index lists instead of use
  -- isPartial here... Because we may supply ALL values (thus, NOT isPartial!)
  -- of one of the param lists, but that means we still need to apply it!!
  local step2 :: String =
    if !null(es.appExprIndicies) then
      step1 ++ ".invokePartial(" ++
      s"new int[]{${implode(", ", map(int2str, es.appExprIndicies))}}, " ++
      s"new Object[]{${argsTranslation(es)}})"
    else step1;
  local step3 :: String =
    if !null(annos.annoIndexConverted) || !null(annos.annoIndexSupplied) then
      step2 ++ ".invokeNamedPartial(" ++
      (if null(annos.annoIndexConverted) then "null"
       else s"new int[]{${implode(", ", map(int2str, annos.annoIndexConverted))}}") ++ ", " ++
      (if null(annos.annoIndexSupplied) then "null"
       else s"new int[]{${implode(", ", map(int2str, annos.annoIndexSupplied))}}") ++ ", " ++
      namedargsTranslationNOReorder(annos) ++ ")"
    else step2;

  -- The theory is the `e.translation` we started with has the right type, so we don't need a cast here. In theory.
  top.translation = step3;

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  local outTy :: String = finalType(top).outputType.transType;

  top.translation =
    if inputType.isDecorated then
      s"new common.AttributeSection<${outTy}>(${occursCheck.dcl.attrOccursIndex})"
    else
      -- Please note: context is not actually required here, we do so to make runtime error messages
      -- more comprehensible. This is a similar situation to the code for 'decorate E with {}'.
      -- Rather pin more memory than necessary than make errors bad. For now.
      -- TODO: This is a good candidate for removing if we make the well-definedness error check required, though!
      -- That error would be more comprehensible! (the trouble with this is that we're reporting as context the
      -- function/production we appear within here. The function *may* be applied elsewhere. However, the most common
      -- case is something like map((.attr), list) so, that's probably best to report here instead of within map.)
      s"new common.AttributeSection.Undecorated<${outTy}>(${occursCheck.dcl.attrOccursIndex}, context)";

  top.lazyTranslation = top.translation;
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  top.translation = s"((${finalType(top).transType})${e.translation}.forwardOrThis())";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.translation = s"((${finalType(top).transType})${e.translation}.synthesized(${q.dcl.attrOccursIndex}))";

  top.lazyTranslation = 
    case e, top.frame.lazyApplication of
    | childReference(cqn), true -> 
        if cqn.lookupValue.typerep.isDecorable
        then
          s"context.childDecoratedSynthesizedLazy(${top.frame.className}.i_${cqn.lookupValue.fullName}, ${q.dcl.attrOccursIndex})"
        else
          s"context.childAsIsSynthesizedLazy(${top.frame.className}.i_${cqn.lookupValue.fullName}, ${q.dcl.attrOccursIndex})"
    | lhsReference(_), true ->
        s"context.contextSynthesizedLazy(${q.dcl.attrOccursIndex})"
    | _, _ -> wrapThunk(top.translation, top.frame.lazyApplication)
    end;
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.translation = s"((${finalType(top).transType})${e.translation}.inherited(${q.dcl.attrOccursIndex}))";

  top.lazyTranslation = 
    case e, top.frame.lazyApplication of
    | lhsReference(_), true -> s"context.contextInheritedLazy(${q.dcl.attrOccursIndex})"
    | _, _ -> wrapThunk(top.translation, top.frame.lazyApplication)
    end;
}

aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
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

  top.translation = s"((${finalType(top).transType})${e.translation}.${accessor})";

  top.lazyTranslation = top.translation;
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  -- Note that the transType is specific to the nonterminal we're accessing from.
  top.translation = s"((${finalType(top).transType})${e.translation}.getAnno_${makeIdName(q.attrDcl.fullName)}())";
  
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.translation = e.translation ++ 
    case inh of
    | exprInhsEmpty() -> ".decorate(context, (common.Lazy[])null)"
      -- Note: we don't NEED to pass context here, but it's good for error messages!
      -- When the user forgets to provide inherited attributes
      -- (especially important because we're implicitly inserted when accessing attributes
      --  from undecorated nodes, and this is a common error for new silverers.)
    | _ -> ".decorate(context, common.Util.populateInh(" ++
             s"${makeNTClassName(finalType(e).typeName)}.num_inh_attrs, " ++
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
  top.nameTrans = [q.dcl.attrOccursIndex];
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

aspect production not
top::Expr ::= '!' e::Expr
{
  top.translation = s"(!${e.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

-- Some notes on numbers:
-- Use `Integer.valueOf` (et al) instead of `new Integer`. It's more efficient.
-- Let Java's autoboxing do the heavy lifting for us, why not? It's smarter.
-- Primitive casts ensure `Integer == Integer` will be value-eq, not reference-eq
function comparisonTranslation
String ::= e1::Decorated Expr  op::String  e2::Decorated Expr
{
  return case finalType(e1) of
  | intType() -> s"(${e1.translation} ${op} (int)${e2.translation})"
  | floatType() -> s"(${e1.translation} ${op} (float)${e2.translation})"
  | boolType() -> s"(${e1.translation} ${op} (boolean)${e2.translation})"
  | stringType() -> s"(${e1.translation}.toString().compareTo(${e2.translation}.toString()) ${op} 0)"
  | terminalIdType() -> s"(${e1.translation} ${op} (int)${e2.translation})"
  | t -> error(s"INTERNAL ERROR: no ${op} trans for type ${prettyType(t)}")
  end;
}

aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.translation = comparisonTranslation(e1, ">", e2);
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.translation = comparisonTranslation(e1, "<", e2);
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.translation = comparisonTranslation(e1, ">=", e2);
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.translation = comparisonTranslation(e1, "<=", e2);
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.translation = comparisonTranslation(e1, "==", e2);
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.translation = comparisonTranslation(e1, "!=", e2);
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.translation = s"(${e1.translation} ? ${e2.translation} : ${e3.translation})";

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
top::Expr ::= e1::Expr '/' e2::Expr
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

aspect production stringConst
top::Expr ::= s::String_t
{
  top.translation = s"(new common.StringCatter(${s.lexeme}))";
  top.lazyTranslation = top.translation;
}

aspect production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.translation = s"new common.StringCatter(${e1.translation}, ${e2.translation})";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
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

aspect production exprRef
top::Expr ::= e::Decorated Expr
{
  top.translation = e.translation;
  top.lazyTranslation = e.lazyTranslation;
}


function wrapThunk
String ::= exp::String  beLazy::Boolean
{
  return if beLazy then wrapThunkText(exp, "Object") else exp;
}
function wrapThunkText
String ::= exp::String  ty::String
{
  return s"new common.Thunk<${ty}>(new common.Thunk.Evaluable() { public final ${ty} eval() { return ${exp}; } })";
  --TODO: java lambdas are bugged
  --return s"new common.Thunk<${ty}>(() -> ${exp})";
}
function wrapLazy
String ::= e::Decorated Expr
{
  -- It *may* be wise to leave `Lazy`s as anon classes, rather than lambdas.
  -- This splits all the Thunk methods across each `Lazy` instead of concentrating
  -- them all on the top-level class, like `Init`
  -- We're *unlikely* to be close to hitting the 64K method limit, but
  -- we have hit the 64K bytecode limit in the past, which is why `Init` farms
  -- initialization code out across each production. So who knows.
  return s"new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ${e.translation}; } }";
}

