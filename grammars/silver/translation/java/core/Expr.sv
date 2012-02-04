grammar silver:translation:java:core;

import silver:util;

import silver:analysis:typechecking:core only finalSubst;

function finalType
TypeExp ::= e::Decorated Expr
{
  return performSubstitution(e.typerep, e.finalSubst);
}

-- These attributes help us generate slightly less awful code, by not going through reflection for direct function/production calls.
synthesized attribute appReference :: String;

{--
 - A translation string that will be a closure instead of the raw value.
 - BUT, is permitted to be a raw value IF it's totally safe to do so.
 -}
synthesized attribute lazyTranslation :: String;

attribute lazyTranslation, translation, appReference occurs on Expr;
attribute lazyTranslation occurs on Exprs;

aspect production defaultExpr
top::Expr ::=
{
  -- deliberately leave appReference undefined.
}

-- TODO: these go through the process of decorating them, just to undecorate.
--       we should maybe pass information to the runtime here to make it more
--       efficient.  We could even kill the runtime check to see if it's
--       a node, since we know.

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
         then "common.Closure.transformUndecorate(context.childDecoratedLazy(" ++ childIDref ++ "))"
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
         then "common.Closure.transformUndecorate(context.localDecoratedLazy(" ++ q.lookupValue.dcl.attrOccursIndex ++ "))"
         else "context.localDecoratedLazy(" ++ q.lookupValue.dcl.attrOccursIndex ++ ")"
    else "context.localAsIsLazy(" ++ q.lookupValue.dcl.attrOccursIndex ++ ")";
}

aspect production productionReference
top::Expr ::= q::Decorated QName
{
  top.appReference = makeClassName(q.lookupValue.fullName);

  top.translation = makeClassName(q.lookupValue.fullName) ++ ".factory";
  top.lazyTranslation = top.translation;
}

aspect production functionReference
top::Expr ::= q::Decorated QName
{
  top.appReference = makeClassName(q.lookupValue.fullName);

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

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ 
                      makeName(q.lookupValue.dcl.sourceGrammar) ++ ".Init." ++ fullNameToShort(q.lookupValue.fullName) ++ ".eval())";

  top.lazyTranslation = 
       if top.blockContext.lazyApplication
       then "new common.Closure.FromThunk(" ++ makeName(q.lookupValue.dcl.sourceGrammar) ++ ".Init." ++ fullNameToShort(q.lookupValue.fullName) ++ ")"
       else top.translation;
}

aspect production functionApplicationDispatcher
top::Expr ::= e::Decorated Expr es::Exprs
{
  top.translation = case e of 
                      functionReference ( _ ) ->
                         "((" ++ finalType(top).transType ++ ")" ++ e.appReference ++ ".invoke(new Object[]{" ++ es.lazyTranslation ++ "}))"

                    | productionReference ( _ ) -> 
                         "((" ++ finalType(top).transType ++ ")new " ++ e.appReference ++ "(" ++ es.lazyTranslation ++ "))"

                    | _ -> "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ ".invoke(new Object[]{" ++ es.lazyTranslation ++ "}))" 
                    end ;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.translation = if inputType.isDecorated
                    then "new common.AttributeSection(" ++ occursCheck.dcl.attrOccursIndex ++ ")"
                    else "new common.AttributeSection.Undecorated(" ++ occursCheck.dcl.attrOccursIndex ++ ")";

  top.lazyTranslation = top.translation;
}

aspect production synDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ ".synthesized(" ++ occursCheck.dcl.attrOccursIndex ++ "))";

  top.lazyTranslation = 
    case e, top.blockContext.lazyApplication of
    | childReference(cqn), true -> 
        "context.childSynthesizedLazy(" ++ makeClassName(top.signature.fullName) ++ ".i_" ++ cqn.lookupValue.fullName ++ ", " 
                                                                ++ occursCheck.dcl.attrOccursIndex ++ ")"
    | _, _ -> wrapClosure(top.translation, top.blockContext.lazyApplication)
    end;
}

aspect production inhDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ ".inherited(" ++ occursCheck.dcl.attrOccursIndex ++ "))";

  top.lazyTranslation = 
       case e, top.blockContext.lazyApplication of
         lhsReference(lqn), true -> "context.contextInheritedLazy(" ++ occursCheck.dcl.attrOccursIndex ++ ")"
       | _, _ -> wrapClosure(top.translation, top.blockContext.lazyApplication)
       end;
}

aspect production terminalAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  -- TODO: we should maybe map the name properly to the field we access?
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ "." ++ q.name ++ ")";

  top.lazyTranslation = top.translation;
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

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
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
top::ExprLHSExpr ::= q::QName
{
  top.nameTrans = [occursCheck.dcl.attrOccursIndex];
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

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.translation = "(" ++ e1.translation ++ " || " ++ e2.translation ++ ")";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production not
top::Expr ::= '!' e::Expr
{
  top.translation = "(!" ++ e.translation ++ ")";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
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
                      intTypeExp() -> "(" ++ e1.translation ++ " > " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "(" ++ e1.translation ++ " > " ++ e2.translation ++ ")"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString()) > 0)"
                    | t -> error("INTERNAL ERROR: no > trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.translation = case finalType(e1) of
                      intTypeExp() -> "(" ++ e1.translation ++ " < " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "(" ++ e1.translation ++ " < " ++ e2.translation ++ ")"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString()) < 0)"
                    | t -> error("INTERNAL ERROR: no < trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.translation = case finalType(e1) of
                      intTypeExp() -> "(" ++ e1.translation ++ " >= " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "(" ++ e1.translation ++ " >= " ++ e2.translation ++ ")"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString()) >= 0)"
                    | t -> error("INTERNAL ERROR: no >= trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.translation = case finalType(e1) of
                      intTypeExp() -> "(" ++ e1.translation ++ " <= " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "(" ++ e1.translation ++ " <= " ++ e2.translation ++ ")"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString()) <= 0)"
                    | t -> error("INTERNAL ERROR: no <= trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.translation = e1.translation ++ ".equals(" ++ e2.translation ++ ")";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.translation = "!" ++ e1.translation ++ ".equals(" ++ e2.translation ++ ")";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.translation = "(" ++ e1.translation ++ " ? " ++ e2.translation ++ " : " ++ e3.translation ++ ")";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
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
                      intTypeExp() -> "Integer.valueOf(" ++ e1.translation ++ " + " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(" ++ e1.translation ++ " + " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no + trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}
aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.translation = case finalType(top) of
                      intTypeExp() -> "Integer.valueOf(" ++ e1.translation ++ " - " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(" ++ e1.translation ++ " - " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no - trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.translation = case finalType(top) of
                      intTypeExp() -> "Integer.valueOf(" ++ e1.translation ++ " * " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(" ++ e1.translation ++ " * " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no * trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.translation = case finalType(top) of
                      intTypeExp() -> "Integer.valueOf(" ++ e1.translation ++ " / " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(" ++ e1.translation ++ " / " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no / trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}
aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.translation = case finalType(top) of
                      intTypeExp() -> "Integer.valueOf(" ++ e1.translation ++ " % " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(" ++ e1.translation ++ " % " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no % trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}
aspect production neg
top::Expr ::= '-' e::Expr
{
  top.translation = case finalType(top) of
                      intTypeExp() -> "Integer.valueOf(-" ++ e.translation ++ ")"
                    | floatTypeExp() -> "Float.valueOf(-" ++ e.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no unary - trans for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.translation = "(new common.StringCatter(" ++ s.lexeme ++ "))";
  top.lazyTranslation = top.translation;
}

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  -- cast, rather than toString. Otherwise we don't gain anything with StringCatter
  -- literal here, rather than transType.  why not? Catch bugs, just in case.
  top.translation = "new common.StringCatter((common.StringCatter)" ++ e1.translation ++ ", (common.StringCatter)" ++ e2.translation ++ ")";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
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

aspect production exprsDecorated
top::Exprs ::= es::[Decorated Expr]
{
  top.lazyTranslation = implode(", ", map(getLazyTranslation, es));
}

function getLazyTranslation
String ::= e::Decorated Expr
{
  return e.lazyTranslation;
}
function wrapClosure
String ::= t::String beLazy::Boolean
{
  return if beLazy then wrapThunkText("context", t) else t;
}
function wrapThunkText
String ::= ct::String s::String
{
  return "new common.Closure(" ++ ct ++ ") { public final Object eval() { return " ++ s ++ "; } }";
}
function wrapLazy
String ::= e::Decorated Expr
{
  return "new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return " ++ e.translation ++ "; } }";
}
