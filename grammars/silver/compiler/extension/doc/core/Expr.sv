grammar silver:compiler:extension:doc:core;

--attribute doc occurs on Expr, Exprs; -- TODO: Implement?

aspect production errorReference
top::Expr ::= msg::[Message]  q::Decorated QName
{
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
}

aspect production productionReference
top::Expr ::= q::Decorated QName
{
}

aspect production functionReference
top::Expr ::= q::Decorated QName
{
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
}

aspect production errorApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs
{
}

aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs
{
}

aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs
{
}

aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
}

aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
}

aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
}

aspect production trueConst
top::Expr ::='true'
{
}

aspect production falseConst
top::Expr ::= 'false'
{
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
}

aspect production not
top::Expr ::= '!' e::Expr
{
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
}

aspect production intConst
top::Expr ::= i::Int_t
{
}

aspect production floatConst
top::Expr ::= f::Float_t
{
}

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
}
aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
}
aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
}
aspect production neg
top::Expr ::= '-' e::Expr
{
}

aspect production stringConst
top::Expr ::= s::String_t
{
}

aspect production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
}

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
}

aspect production exprsEmpty
top::Exprs ::=
{
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
}

aspect production exprRef
top::Expr ::= e::Decorated Expr
{
}