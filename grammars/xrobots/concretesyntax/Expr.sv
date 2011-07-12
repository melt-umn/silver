grammar xrobots:concretesyntax ;

import xrobots:terminals ;
import xrobots:abstractsyntax ;

nonterminal Expr_c with pp ;
	
synthesized attribute ast_Expr :: Expr occurs on Expr_c ;

concrete production add_c
sum::Expr_c ::= e::Expr_c  op::Plus_t  t::Expr_c
{
 sum.pp = e.pp ++ " " ++ op.lexeme ++ " " ++ t.pp ;
 sum.ast_Expr = add(e.ast_Expr, op, t.ast_Expr );
}

concrete production sub_c
dff::Expr_c ::= e::Expr_c  op::Dash_t  t::Expr_c
{
 dff.pp = e.pp ++ " " ++ op.lexeme ++ " " ++ t.pp ;
 dff.ast_Expr = sub(e.ast_Expr, op, t.ast_Expr);
}

concrete production mul_c
prd::Expr_c ::= t::Expr_c  op::Star_t  f::Expr_c
{
 prd.pp = t.pp ++ " " ++ op.lexeme ++ " " ++ f.pp ;
 prd.ast_Expr = mul(t.ast_Expr, op, f.ast_Expr);
}

concrete production div_c
d::Expr_c ::= t::Expr_c  op::Slash_t  f::Expr_c
{
 d.pp = t.pp ++ " " ++ op.lexeme ++ " " ++ f.pp ;
 d.ast_Expr = div(t.ast_Expr, op, f.ast_Expr);
}
concrete production power_c
p::Expr_c ::= t::Expr_c  op::Power_t  f::Expr_c
{
 p.pp = t.pp ++ " " ++ op.lexeme ++ " " ++ f.pp ;
 p.ast_Expr = power(t.ast_Expr, op, f.ast_Expr);
}

concrete production nested_c
e::Expr_c ::= '(' inner::Expr_c ')'
{
 e.pp = "(" ++ inner.pp ++ ")" ;
 e.ast_Expr = inner.ast_Expr ;
}

concrete production integerConstant_c
ic::Expr_c ::= i::IntLit_t
{
 ic.pp = i.lexeme ;
 ic.ast_Expr = integerConstant(i);
}

concrete production falseConstant_c
ic::Expr_c ::= i::False_t
{
 ic.pp = i.lexeme ;
 ic.ast_Expr = falseConstant(i);
}

concrete production trueConstant_c
ic::Expr_c ::= i::True_t
{
 ic.pp = i.lexeme ;
 ic.ast_Expr = trueConstant(i);
}


concrete production negIntegerConstant_c
ic::Expr_c ::= op::Dash_t i::IntLit_t
{
 ic.pp = "-" ++ i.lexeme ;
 ic.ast_Expr = negIntegerConstant(i); 
}

concrete production floatConstant_c
ic::Expr_c ::= i::IntLit_t '.' d::IntLit_t 
{
 ic.pp = i.lexeme ++ "." ++ d.lexeme ++ "f";
 ic.ast_Expr = floatConstant(i, d);
}

concrete production negFloatConstant_c
ic::Expr_c ::= op::Dash_t i::IntLit_t '.' d::IntLit_t 
{
 ic.pp = "-" ++ i.lexeme ++ "." ++ d.lexeme ++ "f";
 ic.ast_Expr = negFloatConstant(i, d);
}


concrete production varAccess_c
e::Expr_c ::= n::Id_t
{
	e.pp = "Accessing variable " ++ n.lexeme ++ "\n";
	e.ast_Expr = varAccess(n);
} 
{---
concrete production varAccessList_c
e::Expr_c ::= dd::DottedAccess
{
	e.pp = "Accessing variable " ++ dd.pp ++ "\n";
--	e.ast_Expr = varAccessList(dd.ids);
} 

concrete production arVarAccessList_c
e::Expr_c ::= dd::DottedAccess '[' index::Expr_c ']'
{
	e.pp = "Accessing variable " ++ dd.pp ++ "\n";
--	e.ast_Expr = arVarAccessList(dd.ids, index.ast_Expr);
} 

concrete production ar2dVarAccessList_c
e::Expr_c ::= dd::DottedAccess '[' index1::Expr_c ',' index2::Expr_c ']'
{
	e.pp = "Accessing variable " ++ dd.pp ++ "\n";
--	e.ast_Expr = ar2dVarAccessList(dd.ids, index1.ast_Expr, index2.ast_Expr);
} 


concrete production arAccess_c
e::Expr_c ::= n::Id_t '[' index::Expr_c ']'
{
	e.pp = "Accessing variable array " ++ n.lexeme ++ "\n";
--	e.ast_Expr = arAccess(n, index.ast_Expr	);
} 

concrete production ar2dAccess_c
e::Expr_c ::= n::Id_t '[' index1::Expr_c ',' index2::Expr_c ']'
{
	e.pp = "Accessing variable array " ++ n.lexeme ++ "\n";
--	e.ast_Expr = ar2dAccess(n, index1.ast_Expr, index2.ast_Expr	);
} 

concrete production rand_c
e::Expr_c ::= 'rand' '(' ')'
{
	e.pp = "the random expr";
--	e.ast_Expr = random();
}

concrete production sin_c
e::Expr_c ::= 'sin' '(' eIn::Expr_c ')'
{
	e.pp = "the sin expr";
--	e.ast_Expr = sin(eIn.ast_Expr);
}

concrete production cos_c
e::Expr_c ::= 'cos' '(' eIn::Expr_c ')'
{
	e.pp = "the cos expr";
--	e.ast_Expr = cos(eIn.ast_Expr);
}

concrete production sqrt_c
e::Expr_c ::= 'sqrt' '(' eIn::Expr_c ')'
{
	e.pp = "the sqrt expr";
--	e.ast_Expr = sqrt(eIn.ast_Expr);
}

concrete production pre_c
e::Expr_c ::= 'pre' '(' eIn::Expr_c ')'
{
	e.pp = "the pre expr";
--	e.ast_Expr = pre(eIn.ast_Expr);
}
---}