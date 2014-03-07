grammar silver:translation:java:core;

import silver:util;

import silver:analysis:typechecking:core only finalSubst;

function finalType
TypeExp ::= e::Decorated Expr
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

-- TODO: these go through the process of decorating them, just to undecorate.
--       we should maybe pass information to the runtime here to make it more
--       efficient.  We could even kill the runtime check to see if it's
--       a node, since we know.

aspect production errorReference
top::Expr ::= msg::[Message]  q::Decorated QName
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  local attribute childIDref :: String;
  childIDref = makeClassName(top.signature.fullName) ++ ".i_" ++ q.lookupValue.fullName;

  top.translation =
    if q.lookupValue.typerep.isDecorable
    then if finalType(top).isDecorable
         then {- type Node -} "context.childDecorated(" ++ childIDref ++ ").undecorate()"
         else {- type DecoratedNode -} "context.childDecorated(" ++ childIDref ++ ")"
    else "((" ++ finalType(top).transType ++ ")context.childAsIs(" ++ childIDref ++ "))";
  -- reminder: the reason we do .childDecorated().undecorate() is that it's not safe to mix asis/decorated accesses.

  top.lazyTranslation =
    if !top.blockContext.lazyApplication then top.translation else
    if q.lookupValue.typerep.isDecorable
    then if finalType(top).isDecorable
         then "common.Thunk.transformUndecorate(context.childDecoratedLazy(" ++ childIDref ++ "))"
         else "context.childDecoratedLazy(" ++ childIDref ++ ")"
    else "context.childAsIsLazy(" ++ childIDref ++ ")";
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  -- always a node/decoratednode, so there's no asis case to consider.

  top.translation =
    if finalType(top).isDecorable
    then "context.undecorate()"
    else "context";

  top.lazyTranslation = top.translation;
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
  top.translation =
    if q.lookupValue.typerep.isDecorable
    then if finalType(top).isDecorable
         then {- type Node -} "context.localDecorated(" ++ q.lookupValue.dcl.attrOccursIndex ++ ").undecorate()"
         else {- type DecoratedNode -} "context.localDecorated(" ++ q.lookupValue.dcl.attrOccursIndex ++ ")"
    else "((" ++ finalType(top).transType ++ ")context.localAsIs(" ++ q.lookupValue.dcl.attrOccursIndex ++ "))";
  -- reminder: the reason we do .localDecorated().undecorate() is that it's not safe to mix asis/decorated accesses.

  top.lazyTranslation =
    if !top.blockContext.lazyApplication then top.translation else
    if q.lookupValue.typerep.isDecorable
    then if finalType(top).isDecorable
         then "common.Thunk.transformUndecorate(context.localDecoratedLazy(" ++ q.lookupValue.dcl.attrOccursIndex ++ "))"
         else "context.localDecoratedLazy(" ++ q.lookupValue.dcl.attrOccursIndex ++ ")"
    else "context.localAsIsLazy(" ++ q.lookupValue.dcl.attrOccursIndex ++ ")";
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
  top.translation = makeClassName(q.lookupValue.fullName) ++ ".factory";
  top.lazyTranslation = top.translation;
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  -- always a node/decoratednode, so there's no asis case to consider.

  top.translation =
    if finalType(top).isDecorable
    then "context.forward().undecorate()"
    else "context.forward()";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ 
                      makeName(q.lookupValue.dcl.sourceGrammar) ++ ".Init." ++ fullNameToShort(q.lookupValue.fullName) ++ ".eval())";

  top.lazyTranslation = 
       if top.blockContext.lazyApplication
       then makeName(q.lookupValue.dcl.sourceGrammar) ++ ".Init." ++ fullNameToShort(q.lookupValue.fullName)
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
        "((" ++ finalType(top).transType ++ ")" ++ makeClassName(q.lookupValue.fullName) ++ ".invoke(" ++ argsTranslation(es) ++ "))"
    | productionReference(q) -> -- static constructor invocation
        "((" ++ finalType(top).transType ++ ")new " ++ makeClassName(q.lookupValue.fullName) ++ "(" ++ implode(", ", map((.lazyTranslation), es.exprs ++ reorderedAnnoAppExprs(annos))) ++ "))"
    | _ -> -- dynamic method invocation
        "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ ".invoke(new Object[]{" ++ argsTranslation(es) ++ "}, " ++ namedargsTranslation(annos) ++ "))" 
    end ;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
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
  else "new Object[]{" ++ implode(", ", map((.lazyTranslation), reorderedAnnoAppExprs(e))) ++ "}";
}
function namedargsTranslationNOReorder
String ::= e::Decorated AnnoAppExprs
{
  -- TODO: This is the ONLY use of .exprs  We could eliminate that, if we fix this.
  return if null(e.exprs) then "null"
  else "new Object[]{" ++ implode(", ", map((.lazyTranslation), e.exprs)) ++ "}";
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
      "new int[]{" ++ implode(", ", map(int2str, es.appExprIndicies)) ++ "}, " ++
      "new Object[]{" ++ argsTranslation(es) ++ "})"
    else step1;
  local step3 :: String =
    if !null(annos.annoIndexConverted) || !null(annos.annoIndexSupplied) then
      step2 ++ ".invokeNamedPartial(" ++
      (if null(annos.annoIndexConverted) then "null"
       else "new int[]{" ++ implode(", ", map(int2str, annos.annoIndexConverted)) ++ "}") ++ ", " ++
      (if null(annos.annoIndexSupplied) then "null"
       else "new int[]{" ++ implode(", ", map(int2str, annos.annoIndexSupplied)) ++ "}") ++ ", " ++
      namedargsTranslationNOReorder(annos) ++ ")"
    else step2;
    
  top.translation = step3;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.translation = if inputType.isDecorated
                    then "new common.AttributeSection(" ++ occursCheck.dcl.attrOccursIndex ++ ")"
                    else "new common.AttributeSection.Undecorated(" ++ occursCheck.dcl.attrOccursIndex ++ ")";

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

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ ".synthesized(" ++ q.dcl.attrOccursIndex ++ "))";

  top.lazyTranslation = 
    case e, top.blockContext.lazyApplication of
    | childReference(cqn), true -> 
        if cqn.lookupValue.typerep.isDecorable
        then
          "context.childDecoratedSynthesizedLazy(" ++ makeClassName(top.signature.fullName) ++ ".i_" ++ cqn.lookupValue.fullName ++ ", " ++ q.dcl.attrOccursIndex ++ ")"
        else
          "context.childAsIsSynthesizedLazy(" ++ makeClassName(top.signature.fullName) ++ ".i_" ++ cqn.lookupValue.fullName ++ ", " ++ q.dcl.attrOccursIndex ++ ")"
    | lhsReference(_), true ->
        "context.contextSynthesizedLazy(" ++ q.dcl.attrOccursIndex ++ ")"
    | _, _ -> wrapThunk(top.translation, top.blockContext.lazyApplication)
    end;
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ ".inherited(" ++ q.dcl.attrOccursIndex ++ "))";

  top.lazyTranslation = 
    case e, top.blockContext.lazyApplication of
    | lhsReference(_), true -> "context.contextInheritedLazy(" ++ q.dcl.attrOccursIndex ++ ")"
    | _, _ -> wrapThunk(top.translation, top.blockContext.lazyApplication)
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
    else error("Not possible -- an error should have been raised about " ++ q.pp);

  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ "." ++ accessor ++ ")";

  top.lazyTranslation = top.translation;
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  -- Note that the transType is specific to the nonterminal we're accessing from.
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ ".getAnno_" ++ makeIdName(q.attrDcl.fullName) ++ "())";
  
  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.translation = e.translation ++ 
    case inh of
      exprInhsEmpty() -> ".decorate(context, (common.Lazy[])null)" -- TODO: we don't NEED to pass context here, but it's good for error messages!
    | _ -> ".decorate(context, common.Util.populateInh(" ++
                                      makeNTClassName(finalType(e).typeName) ++ ".num_inh_attrs, " ++
                                      "new int[]{" ++ implode(", ", inh.nameTrans) ++ "}, " ++ 
                                      "new common.Lazy[]{" ++ implode(", ", inh.valueTrans) ++ "}))"
    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
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
  top.translation = "(" ++ e1.translation ++ " && " ++ e2.translation ++ ")";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.translation = "(" ++ e1.translation ++ " || " ++ e2.translation ++ ")";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production not
top::Expr ::= '!' e::Expr
{
  top.translation = "(!" ++ e.translation ++ ")";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

-- Some notes on numbers:
-- Use Integer.valueOf (et al) instead of new Integer. It's more efficient.
-- .intValue (et al) (and .valueOf) are done by autoboxing. (e.g. a < b  equiv to  a.intValue() < b.intValue() )
-- Let Java's autoboxing do the heavy lifting for us, why not? It's smarter.

-- TODO: again, here we're dispatching on type. Should we do this polymorphically?
aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.translation = case finalType(e1) of
                    | intTypeExp() -> "(" ++ e1.translation ++ " > " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "(" ++ e1.translation ++ " > " ++ e2.translation ++ ")"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString()) > 0)"
                    | t -> error("INTERNAL ERROR: no > trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.translation = case finalType(e1) of
                    | intTypeExp() -> "(" ++ e1.translation ++ " < " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "(" ++ e1.translation ++ " < " ++ e2.translation ++ ")"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString()) < 0)"
                    | t -> error("INTERNAL ERROR: no < trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.translation = case finalType(e1) of
                    | intTypeExp() -> "(" ++ e1.translation ++ " >= " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "(" ++ e1.translation ++ " >= " ++ e2.translation ++ ")"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString()) >= 0)"
                    | t -> error("INTERNAL ERROR: no >= trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.translation = case finalType(e1) of
                    | intTypeExp() -> "(" ++ e1.translation ++ " <= " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "(" ++ e1.translation ++ " <= " ++ e2.translation ++ ")"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString()) <= 0)"
                    | t -> error("INTERNAL ERROR: no <= trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.translation = e1.translation ++ ".equals(" ++ e2.translation ++ ")";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.translation = "!" ++ e1.translation ++ ".equals(" ++ e2.translation ++ ")";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.translation = "(" ++ e1.translation ++ " ? " ++ e2.translation ++ " : " ++ e3.translation ++ ")";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.translation = "Integer.valueOf((int)" ++ i.lexeme ++ ")";
  top.lazyTranslation = top.translation;
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.translation = "Float.valueOf((float)" ++ f.lexeme ++ ")";
  top.lazyTranslation = top.translation;
}

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.translation = case finalType(top) of
                    | intTypeExp() -> "Integer.valueOf(" ++ e1.translation ++ " + " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(" ++ e1.translation ++ " + " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no + trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}
aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.translation = case finalType(top) of
                    | intTypeExp() -> "Integer.valueOf(" ++ e1.translation ++ " - " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(" ++ e1.translation ++ " - " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no - trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.translation = case finalType(top) of
                    | intTypeExp() -> "Integer.valueOf(" ++ e1.translation ++ " * " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(" ++ e1.translation ++ " * " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no * trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.translation = case finalType(top) of
                    | intTypeExp() -> "Integer.valueOf(" ++ e1.translation ++ " / " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(" ++ e1.translation ++ " / " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no / trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}
aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.translation = case finalType(top) of
                    | intTypeExp() -> "Integer.valueOf(" ++ e1.translation ++ " % " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(" ++ e1.translation ++ " % " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no % trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}
aspect production neg
top::Expr ::= '-' e::Expr
{
  top.translation = case finalType(top) of
                    | intTypeExp() -> "Integer.valueOf(-" ++ e.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(-" ++ e.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no unary - trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.translation = "(new common.StringCatter(" ++ s.lexeme ++ "))";
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
  -- cast, rather than toString. Otherwise we don't gain anything with StringCatter
  -- literal here, rather than transType.  why not? Catch bugs, just in case.
  top.translation = "new common.StringCatter((common.StringCatter)" ++ e1.translation ++ ", (common.StringCatter)" ++ e2.translation ++ ")";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
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
  return if beLazy then wrapThunkText("context", exp, "Object") else exp;
}
function wrapThunkText
String ::= ct::String  exp::String  ty::String
{
  return "new common.Thunk<" ++ ty ++ ">(" ++ ct ++ ") { public final " ++ ty ++ " doEval() { return " ++ exp ++ "; } }";
}
function wrapLazy
String ::= e::Decorated Expr
{
  return "new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return " ++ e.translation ++ "; } }";
}

