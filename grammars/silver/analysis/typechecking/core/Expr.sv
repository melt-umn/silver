grammar silver:analysis:typechecking:core;
import silver:definition:core;
import silver:definition:env;

--Base Expressions
aspect production nestedExpr
top::Expr ::= '(' e::Expr ')'
{
  top.typeErrors = e.typeErrors;
}

aspect production productionReference
top::Expr ::= q::QName
{
  top.typeErrors = [];
}

aspect production functionReference
top::Expr ::= q::QName
{
  top.typeErrors = [];
}

aspect production dontDecorateExpr
top::Expr ::= q::QName
{
  top.typeErrors = [];
}

aspect production decorateExpr
top::Expr ::= q::QName
{
  local attribute er2 :: [Decorated Message];
  er2 = if q.lookupValue.typerep.typeName != "TOP" && !q.lookupValue.typerep.isNonTerminal
	then [err(top.location, "Ticked value '" ++ q.name ++ "' must be an undecorated nonterminal." )]
	else [];

  local attribute er3 :: [Decorated Message];
  er3 = if !in_sig && !in_locals
	then [err(top.location, "Ticked name '" ++ q.name ++ "'must be in the signature or declared local.")]
	else [];

  top.typeErrors = er2 ++ er3;
}

aspect production productionApplicationDispatcher
top::Expr ::= e::Expr es::Exprs
{
  local attribute er1 :: [Decorated Message];
  er1 = if  e.typerep.isProduction && genListEquals(e.typerep.inputTypes, getTypesExprs(es.exprs)) then []
	       else [err(top.location, "Parameters given do not match the signature of the production.\n" ++
		 	"(expected)\n   " ++ printTypes(e.typerep.inputTypes) ++ "(actual)\n   " ++ printTypes(getTypesExprs(es.exprs)))];
  
  local attribute er2 :: [Decorated Message];
  er2 = if e.typerep.isProduction then []
   	         else [err(top.location, e.pp ++ " does not have type Production.")];

  top.typeErrors = er1 ++ er2 ++ e.typeErrors ++ es.typeErrors;
}

aspect production functionApplicationDispatcher
top::Expr ::= e::Expr es::Exprs
{
  local attribute er1 :: [Decorated Message];
  er1 = if  e.typerep.isFunction && genListEquals(e.typerep.inputTypes, getTypesExprs(es.exprs)) then []
	       else [err(top.location, "Parameters given do not match the signature of the function.\n" ++
		 	"(expected)\n   " ++ printTypes(e.typerep.inputTypes) ++ "(actual)\n   " ++ printTypes(getTypesExprs(es.exprs)))];
  
  local attribute er2 :: [Decorated Message];
  er2 = if e.typerep.isFunction then []
   	         else [err(top.location, e.pp ++ " does not have type Function.")];

  top.typeErrors = er1 ++ er2 ++ e.typeErrors ++ es.typeErrors;
}

aspect production genericApplicationDispatcher
top::Expr ::= e::Expr es::Exprs
{
  top.typeErrors = []; -- TODO: this is an error production, but does it give errors properly?
}

function printTypes
String ::= ts::[Decorated TypeRep]
{
  return if null(ts) then ""
	 else head(ts).unparse ++ 
	 (if null(tail(ts)) then "\n"
	  else ",\n   " ++ printTypes(tail(ts)));
}

aspect production atAccess
top::Expr ::= e::Expr '@' q::QName
{
  local attribute occursOn :: Boolean;
  occursOn = doesOccurOn(q.lookupAttribute.fullName, e.typerep.typeName, top.env);

  local attribute er1 :: [Decorated Message];
  er1 = if occursOn then []
       else [err(top.location, "Attribute '" ++ q.name ++ "' does not decorate type '" ++ e.typerep.typeName ++ "'.")];

  local attribute er2 :: [Decorated Message];
  er2 = if e.typerep.isDecorated || e.typerep.isTerminal then []
	else [err(top.location, "@ value must be a decorated nonterminal or a terminal.")] ;

  top.typeErrors = er2 ++ er1 ++ e.typeErrors;
}

aspect production hashAccess
top::Expr ::= e::Expr '#' q::QName
{}
aspect production trueConst
top::Expr ::= 'true'
{
  top.typeErrors = [];
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.typeErrors = [];
}


aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if (e1.typerep.typeName == "Boolean") &&
	  (e2.typerep.typeName == "Boolean")
       then []
       else [err(top.location, "Operands to && must be of type Boolean.")];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if (e1.typerep.typeName == "Boolean") &&
	  (e2.typerep.typeName == "Boolean")
       then []
       else [err(top.location, "Operands to || must be of type Boolean.")];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}

aspect production not
top::Expr ::= '!' e::Expr
{
  local attribute er :: [Decorated Message];
  er = if (e.typerep.typeName == "Boolean")
       then []
       else [err(top.location, "Opperand to ! must be of type Boolean.")];

  top.typeErrors = er ++ e.typeErrors;
}

aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if ((e1.typerep.typeName == "Integer") &&
	   (e2.typerep.typeName == "Integer"))
	   ||
	  ((e1.typerep.typeName == "String") &&	
	   (e2.typerep.typeName == "String"))	
       then []
       else [err(top.location, "Operands to > must be of type Integer or String.")];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if ((e1.typerep.typeName == "Integer") &&
	   (e2.typerep.typeName == "Integer"))
	   ||
	  ((e1.typerep.typeName == "String") &&	
	   (e2.typerep.typeName == "String"))	
       then []
       else [err(top.location, "Operands to < must be of type Integer or String.")];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}


aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if ((e1.typerep.typeName == "Integer") &&
	   (e2.typerep.typeName == "Integer"))
	   ||
	  ((e1.typerep.typeName == "String") &&	
	   (e2.typerep.typeName == "String"))	
       then []
       else [err(top.location, "Operands to >= must be of type Integer or String.")];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}


aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if ((e1.typerep.typeName == "Integer") &&
	   (e2.typerep.typeName == "Integer"))
	   ||
	  ((e1.typerep.typeName == "String") &&	
	   (e2.typerep.typeName == "String"))	
       then []
       else [err(top.location, "Operands to <= must be of type Integer or String.")];
 
  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}


aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if (e1.typerep.isPrimative && 
	   e2.typerep.isPrimative && 
	   e1.typerep.typeEquals(e1.typerep, e2.typerep).bValue)
       then []
       else [err(top.location, "Operands to == must be primitive and of the same type.")];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}


aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if (e1.typerep.isPrimative && 
	   e2.typerep.isPrimative && 
	   e1.typerep.typeEquals(e1.typerep, e2.typerep).bValue)
       then []
       else [err(top.location, "Operands to == must be primitive and of the same type.")];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}


aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.typeErrors = er1 ++ er2 ++ e1.typeErrors ++ e2.typeErrors ++ e3.typeErrors;

  local attribute er1 :: [Decorated Message];
  er1 = if (e1.typerep.typeName == "Boolean") then []
	       else [err(top.location, e1.pp ++ " must have type boolean.")];


  local attribute er2 :: [Decorated Message];
  er2 = if e2.typerep.typeEquals(e2.typerep, e3.typerep).bValue then []
 	         else [err(top.location, "The 'then' and 'else' branches must evaluate to the same type.\n" ++
                                      "\tInstead they are '" ++ e2.typerep.unparse ++ "' and '" ++ e3.typerep.unparse ++ "'")];
}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.typeErrors = [];
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.typeErrors = [];
} 

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if ((e1.typerep.typeName == "Integer") && (e2.typerep.typeName == "Integer"))
	  ||
	  ((e1.typerep.typeName == "Float") && (e2.typerep.typeName == "Float"))
       then []
       else [err(top.location, "Operands to '+' must be of type Integer or Float and of the same type.")];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}
aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if ((e1.typerep.typeName == "Integer") && (e2.typerep.typeName == "Integer"))
	  ||
	  ((e1.typerep.typeName == "Float") && (e2.typerep.typeName == "Float"))
       then []
       else [err(top.location, "Operands to '-' must be of type Integer or Float and of the same type.")];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if ((e1.typerep.typeName == "Integer") && (e2.typerep.typeName == "Integer"))
	  ||
	  ((e1.typerep.typeName == "Float") && (e2.typerep.typeName == "Float"))
       then []
       else [err(top.location, "Operands to '*' must be of type Integer or Float and of the same type.")];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  local attribute er :: [Decorated Message];
  er = if ((e1.typerep.typeName == "Integer") && (e2.typerep.typeName == "Integer"))
	  ||
	  ((e1.typerep.typeName == "Float") && (e2.typerep.typeName == "Float"))
       then []
       else [err(top.location, "Operands to '/' must be of type Integer or Float and of the same type.")];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}
aspect production neg
top::Expr ::= '-' e::Expr
{
  local attribute er :: [Decorated Message];
  er = if ((e.typerep.typeName == "Integer") || (e.typerep.typeName == "Float"))
       then []
       else [err(top.location, "Opperand to unary '-' must be of type Integer or Float.")];

  top.typeErrors = er ++ e.typeErrors;
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.typeErrors = [];
}

aspect production defaultPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  local attribute er :: [Decorated Message];
  er = if ((e1.typerep.typeName == "String") && (e2.typerep.typeName == "String"))
       then []
       else [err(top.location, "Operands to overloaded operator '++' are not compatible.\n\t" ++
		e1.typerep.typeName ++ " ++ " ++ e2.typerep.typeName)];

  top.typeErrors = er ++ e1.typeErrors ++ e2.typeErrors;
}

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.typeErrors = e1.typeErrors ++ e2.typeErrors;
}


aspect production exprsEmpty
top::Exprs ::=
{
  top.typeErrors = [];
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.typeErrors = e.typeErrors;
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.typeErrors = e1.typeErrors ++ e2.typeErrors;
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  local attribute er :: [Decorated Message];
  er = if (e.typerep.isNonTerminal)
       then []
       else [err(top.location, "Operand to 'decorate/with' must have a non-terminal type.\n" ++
                " Instead it is " ++ e.typerep.typeName ++ ".")] ;

  top.typeErrors = er ++ e.typeErrors ++ inh.typeErrors;
}

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  local attribute valid :: Boolean;
  valid = lhs.typerep.typeEquals(lhs.typerep, e.typerep).bValue;

  local attribute er :: [Decorated Message];
  er = if valid 
       then [] 
       else [err(top.location, "The LHS and RHS of the assignment must be the same type.\n" ++
                                  "\tInstead they are '" ++ lhs.typerep.typeName ++ "' and '" ++ e.typerep.typeName ++ "'")]; 

  -- TODO: uhhh, check occurs?

  top.typeErrors = er ++ e.typeErrors;
}

aspect production exprInhsEmpty
top::ExprInhs ::= 
{
  top.typeErrors = [];
}

aspect production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.typeErrors = lhs.typeErrors;
}

aspect production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.typeErrors = lhs.typeErrors ++ inh.typeErrors;
}

