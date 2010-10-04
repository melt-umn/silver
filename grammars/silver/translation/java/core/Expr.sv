grammar silver:translation:java:core;

import silver:util;

import silver:translation:java:concrete_syntax:copper; -- todo : part of wrapThunk hack

import silver:analysis:typechecking:core only finalSubst;

function finalType
TypeExp ::= e::Decorated Expr
{
  return performSubstitution(e.typerep, e.finalSubst);
}

-- These attributes help us generate slightly less awful code, by not going through reflection for direct function/production calls.
synthesized attribute isAppReference :: Boolean;
synthesized attribute appReference :: String;

attribute translation, isAppReference, appReference occurs on Expr, Exprs;

--Base Expressions
aspect production nestedExpr
top::Expr ::= '(' e::Expr ')'
{
  top.translation = "(" ++ e.translation ++ ")";
  top.isAppReference = e.isAppReference;
  top.appReference = e.appReference;
}

-- TODO: these go through the process of decorating them, just to undecorate.
--       we should maybe pass information to the runtime here to make it more
--       efficient.  We could even kill the runtime check to see if it's
--       a node, since we know.

aspect production childReference
top::Expr ::= q::Decorated QName
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation =
    if shouldUnDec
    then "(((common.DecoratedNode)context.child(" ++ makeClassName(top.signature.fullName) ++ ".i_" ++ q.lookupValue.fullName ++ ")).undecorate())"
    else "((" ++ finalType(top).transType ++ ")context.child(" ++ makeClassName(top.signature.fullName) ++ ".i_" ++ q.lookupValue.fullName  ++ "))";
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation =
    if shouldUnDec
    then "context.undecorate()"
    else "context";
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation =
    if shouldUnDec
    then "(((common.DecoratedNode)context.local(\"" ++ q.lookupValue.fullName ++ "\")).undecorate())"
    else "((" ++ finalType(top).transType ++ ")context.local(\"" ++ q.lookupValue.fullName ++ "\"))";
}

aspect production productionReference
top::Expr ::= q::Decorated QName
{
  top.isAppReference = true;
  top.appReference = makeClassName(q.lookupValue.fullName);

  top.translation = "common.Util.getConstruct(" ++ makeClassName(q.lookupValue.fullName) ++ ".class)";
}

aspect production functionReference
top::Expr ::= q::Decorated QName
{
  top.isAppReference = true;
  top.appReference = makeClassName(q.lookupValue.fullName);

  top.translation = "common.Util.getConstruct(" ++ makeClassName(q.lookupValue.fullName) ++ ".class)";
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.isAppReference = false;
  top.appReference = "";
  
  top.translation =
    if shouldUnDec
    then "context.forward().undecorate()"
    else "context.forward()";
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.isAppReference = false;
  top.appReference = "";
  
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ 
                      makeName(q.lookupValue.dcl.sourceGrammar) ++ ".Init." ++ fullNameToShort(q.lookupValue.fullName) ++ ".eval())";
}

aspect production productionApplicationDispatcher
top::Expr ::= e::Decorated Expr es::Exprs
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation = if e.isAppReference 
                    then "((" ++ finalType(top).transType ++ ")new " ++ e.appReference ++ "(" ++ es.translation ++ "))"
                    else "((" ++ finalType(top).transType ++ ")common.Util.construct(" ++ e.translation ++ ", new Object[]{" ++ es.translation ++ "}))";
}

aspect production functionApplicationDispatcher
top::Expr ::= e::Decorated Expr es::Exprs
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation = if e.isAppReference 
                    then "((" ++ finalType(top).transType ++ ")new " ++ e.appReference ++ "(" ++ es.translation ++ ").doReturn())"
                    else "((" ++ finalType(top).transType ++ ")((common.FunctionNode)common.Util.construct(" ++ e.translation ++ ", new Object[]{" ++ es.translation ++ "})).doReturn())";
}

aspect production synDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ ".synthesized(\"" ++ q.lookupAttribute.fullName ++ "\"))";
}

aspect production inhDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ ".inherited(\"" ++ q.lookupAttribute.fullName ++ "\"))";
}

aspect production terminalAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.isAppReference = false;
  top.appReference = "";

  -- TODO: we should maybe map the name properly to the field we access?
  top.translation = "((" ++ finalType(top).transType ++ ")" ++ e.translation ++ "." ++ q.name ++ ")";
}

aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation = "(" ++ e.translation ++ ".decorate(context" ++ 
                             (case inh of
                               exprInhsEmpty() -> ""
                              | _ ->
                                ", common.Util.populateMap(new String[]{" ++ folds(", ", inh.nameTrans) ++ "}, " ++ 
                                                        "new common.Lazy[]{" ++ folds(", ", inh.valueTrans) ++ "})"
                              end) ++ "))"; 

}
synthesized attribute nameTrans :: [String];
synthesized attribute valueTrans :: [String];

attribute nameTrans occurs on ExprInhs, ExprInh, ExprLHSExpr;
attribute valueTrans occurs on ExprInhs, ExprInh;

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  top.nameTrans = lhs.nameTrans;
  top.valueTrans = ["new common.Lazy(){public Object eval(common.DecoratedNode context) {return " ++ e.translation ++ ";}}"];
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
  top.nameTrans = ["\"" ++ q.lookupAttribute.fullName ++ "\""];
}


aspect production trueConst
top::Expr ::='true'
{
  top.translation = "true";
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.translation = "false";
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.translation = "(" ++ e1.translation ++ " && " ++ e2.translation ++ ")";
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.translation = "(" ++ e1.translation ++ " || " ++ e2.translation ++ ")";
}

aspect production not
top::Expr ::= '!' e::Expr
{
  top.translation = "(!" ++ e.translation ++ ")";
}

-- TODO: again, here we're dispatching on type. Should we do this polymorphically?
aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.translation = case finalType(e1) of
                      intTypeExp() -> "(" ++ e1.translation ++ ".intValue() > " ++ e2.translation ++ ".intValue())"
                    | floatTypeExp() -> "(" ++ e1.translation ++ ".floatValue() > " ++ e2.translation ++ ".floatValue())"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString())) > 0"
                    | t -> error("INTERNAL ERROR: no > trans for type " ++ prettyType(t))
                    end;
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.translation = case finalType(e1) of
                      intTypeExp() -> "(" ++ e1.translation ++ ".intValue() < " ++ e2.translation ++ ".intValue())"
                    | floatTypeExp() -> "(" ++ e1.translation ++ ".floatValue() < " ++ e2.translation ++ ".floatValue())"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString())) < 0"
                    | t -> error("INTERNAL ERROR: no < trans for type " ++ prettyType(t))
                    end;
}

aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.translation = case finalType(e1) of
                      intTypeExp() -> "(" ++ e1.translation ++ ".intValue() >= " ++ e2.translation ++ ".intValue())"
                    | floatTypeExp() -> "(" ++ e1.translation ++ ".floatValue() >= " ++ e2.translation ++ ".floatValue())"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString())) >= 0"
                    | t -> error("INTERNAL ERROR: no >= trans for type " ++ prettyType(t))
                    end;
}

aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.translation = case finalType(e1) of
                      intTypeExp() -> "(" ++ e1.translation ++ ".intValue() <= " ++ e2.translation ++ ".intValue())"
                    | floatTypeExp() -> "(" ++ e1.translation ++ ".floatValue() <= " ++ e2.translation ++ ".floatValue())"
                    | stringTypeExp() -> "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString())) <= 0"
                    | t -> error("INTERNAL ERROR: no <= trans for type " ++ prettyType(t))
                    end;
}

aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.translation = e1.translation ++ ".equals(" ++ e2.translation ++ ")";
}

aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.translation = "!" ++ e1.translation ++ ".equals(" ++ e2.translation ++ ")";
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.translation = "(" ++ e1.translation ++ " ? " ++ e2.translation ++ " : " ++ e3.translation ++ ")";
}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.translation = "new Integer(" ++ i.lexeme ++ ")";
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.translation = "new Float(" ++ f.lexeme ++ ")";
} 

-- TODO: BUG: these aren't working for floats!
aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.translation = case finalType(top) of
                      intTypeExp() -> "new Integer(" ++ e1.translation ++ " + " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "new Float(" ++ e1.translation ++ " + " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no + trans for type " ++ prettyType(t))
                    end;
}
aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.translation = case finalType(top) of
                      intTypeExp() -> "new Integer(" ++ e1.translation ++ " - " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "new Float(" ++ e1.translation ++ " - " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no - trans for type " ++ prettyType(t))
                    end;
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.translation = case finalType(top) of
                      intTypeExp() -> "new Integer(" ++ e1.translation ++ " * " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "new Float(" ++ e1.translation ++ " * " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no * trans for type " ++ prettyType(t))
                    end;
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.translation = case finalType(top) of
                      intTypeExp() -> "new Integer(" ++ e1.translation ++ " / " ++ e2.translation ++ ")"
                    | floatTypeExp() -> "new Float(" ++ e1.translation ++ " / " ++ e2.translation ++ ")"
                    | t -> error("INTERNAL ERROR: no / trans for type " ++ prettyType(t))
                    end;
}
aspect production neg
top::Expr ::= '-' e::Expr
{
  top.translation = case finalType(top) of
                      intTypeExp() -> "new Integer(-" ++ e.translation ++ ".intValue())"
                    | floatTypeExp() -> "new Float(-" ++ e.translation ++ ".floatValue())"
                    | t -> error("INTERNAL ERROR: no unary - trans for type " ++ prettyType(t))
                    end;
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.translation = "(new common.StringCatter(" ++ s.lexeme ++ "))";
}

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  -- cast, rather than toString. Otherwise we don't gain anything with StringCatter
  -- literal here, rather than transType.  why not? Catch bugs, just in case.
  top.translation = "new common.StringCatter((common.StringCatter)" ++ e1.translation ++ ").append((common.StringCatter)" ++ e2.translation ++ ")";
}

aspect production exprsEmpty
top::Exprs ::=
{
  top.translation = "";
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.translation = wrapThunk(e, top.actionCodeType.isSemanticBlock);
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.translation = wrapThunk(e1, top.actionCodeType.isSemanticBlock) ++ ", " ++ e2.translation;
}

-- TODO: doit is a hack.  Right now every place that calls this is importing sil:trans:conc:copper to look at actionCodeType
function wrapThunk
String ::= original::Decorated Expr doit::Boolean
{
  return if doit
         then "new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return " ++ original.translation ++ "; } })"
         else original.translation;
}

