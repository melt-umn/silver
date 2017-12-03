grammar silver:extension:bidirtransform;

synthesized attribute ppDebug::String;
attribute ppDebug occurs on Expr;

aspect production errorExpr
top::Expr ::= e::[Message]
{
 top.ppDebug = "errorExpr";
}

aspect production nestedExpr
top::Expr ::= '(' e::Expr ')'
{
 top.ppDebug = "nestedExpr";
}

aspect production baseExpr
top::Expr ::= q::QName
{
 top.ppDebug = "baseExpr";
}

aspect production errorReference
top::Expr ::= msg::[Message]  q::Decorated QName
{
 top.ppDebug = "errorReference";
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
 top.ppDebug = "childReference";
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
 top.ppDebug = "lhsReference";
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
 top.ppDebug = "localReference";
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
 top.ppDebug = "forwardReference";
}

aspect production productionReference
top::Expr ::= q::Decorated QName
{
 top.ppDebug = "productionReference";
}

aspect production functionReference
top::Expr ::= q::Decorated QName
{
 top.ppDebug = "functionReference";
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
 top.ppDebug = "globalValueReference";
}

aspect production concreteForwardExpr
top::Expr ::= q::'forward'
{
 top.ppDebug = "concreteForwardExpr";
}

aspect production application
top::Expr ::= e::Expr '(' es::AppExprs ',' anns::AnnoAppExprs ')'
{
 top.ppDebug = "application";
}

aspect production applicationAnno
top::Expr ::= e::Expr '(' anns::AnnoAppExprs ')'
{
 top.ppDebug = "applicationAnno";
}

aspect production applicationExpr
top::Expr ::= e::Expr '(' es::AppExprs ')'
{
 top.ppDebug = "applicationExpr";
}

aspect production applicationEmpty
top::Expr ::= e::Expr '(' ')'
{
 top.ppDebug = "applicationEmpty";
}

aspect production errorApplication
top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs
{
 top.ppDebug = "errorApplication";
}

aspect production functionApplication
top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs
{
 top.ppDebug = "functionApplication";
}

aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
 top.ppDebug = "functionInvocation";
}

aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
 top.ppDebug = "partialApplication";
}

aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
 top.ppDebug = "attributeSection";
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
 top.ppDebug = "forwardAccess";
}

aspect production access
top::Expr ::= e::Expr '.' q::QNameAttrOccur
{
 top.ppDebug = "access";
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
 top.ppDebug = "errorAccessHandler";
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
 top.ppDebug = "annoAccessHandler";
}

aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
 top.ppDebug = "terminalAccessHandler";
}

aspect production undecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
 top.ppDebug = "undecoratedAccessHandler";
}

aspect production accessBouncer
top::Expr ::= target::(Expr ::= Decorated Expr  Decorated QNameAttrOccur  Location) e::Expr  q::Decorated QNameAttrOccur
{
 top.ppDebug = "accessBouncer";
}

aspect production decoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
 top.ppDebug = "decoratedAccessHandler";
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
 top.ppDebug = "synDecoratedAccessHandler";
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
 top.ppDebug = "inhDecoratedAccessHandler";
}

-- TODO: change name. really "unknownDclAccessHandler"
aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
 top.ppDebug = "errorDecoratedAccessHandler";
}


aspect production decorateExprWithEmpty
top::Expr ::= 'decorate' e::Expr 'with' '{' '}'
{
 top.ppDebug = "decorateExprWithEmpty";
}

aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
 top.ppDebug = "decorateExprWith";
}

aspect production trueConst
top::Expr ::= 'true'
{
 top.ppDebug = "trueConst";
}

aspect production falseConst
top::Expr ::= 'false'
{
 top.ppDebug = "falseConst";
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
 top.ppDebug = "and";
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
 top.ppDebug = "or";
}

aspect production not
top::Expr ::= '!' e::Expr
{
 top.ppDebug = "not";
}

aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
 top.ppDebug = "gt";
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
 top.ppDebug = "lt";
}

aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
 top.ppDebug = "gteq";
}

aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
 top.ppDebug = "lteq";
}

aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
 top.ppDebug = "eqeq";
}

aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
 top.ppDebug = "neq";
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
 top.ppDebug = "ifThenElse";
}

aspect production intConst
top::Expr ::= i::Int_t
{
 top.ppDebug = "intConst";
}

aspect production floatConst
top::Expr ::= f::Float_t
{
 top.ppDebug = "floatConst";
} 

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
 top.ppDebug = "plus";
}

aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
 top.ppDebug = "minus";
}

aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
 top.ppDebug = "multiply";
}

aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
 top.ppDebug = "divide";
}

aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
 top.ppDebug = "modulus";
}

aspect production neg
top::Expr ::= '-' e::Expr
{
 top.ppDebug = "neg";
}

aspect production stringConst
top::Expr ::= s::String_t
{
 top.ppDebug = "stringConst";
}

aspect production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
 top.ppDebug = "plusPlus";
}

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr   e2::Decorated Expr
{
 top.ppDebug = "stringPlusPlus";
}

aspect production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
 top.ppDebug = "errorPlusPlus";
}

aspect production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
 top.ppDebug = "lengthFunction";
}

aspect production errorLength
top::Expr ::= e::Decorated Expr
{
 top.ppDebug = "errorLength";
}

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
 top.ppDebug = "stringLength";
}

aspect production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
 top.ppDebug = "toIntFunction";
}

aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
 top.ppDebug = "toFloatFunction";
}

aspect production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
 top.ppDebug = "toStringFunction";
}

aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
 top.ppDebug = "newFunction";
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
 top.ppDebug = "terminalConstructor";
}

aspect production terminalConstructorTemporaryDispatcher
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
 top.ppDebug = "terminalConstructorTemporaryDispatcher";
}

aspect production terminalFunction
top::Expr ::= 'terminal' '(' t::TypeExpr ',' e::Expr ')'
{
 top.ppDebug = "terminalFunction";
}

aspect production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::TypeExpr ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
 top.ppDebug = "terminalFunctionLineCol";
}

aspect production terminalFunctionInherited
top::Expr ::= 'terminal' '(' t::TypeExpr ',' e1::Expr ',' e2::Expr ')'
{
 top.ppDebug = "terminalFunctionInherited";
}