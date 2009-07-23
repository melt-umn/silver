grammar silver:translation:java:core;
import silver:translation:java:env;

import silver:definition:core;
import silver:definition:env;
import silver:util;

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

aspect production decorateExpr
top::Expr ::= q::QName
{
  top.isAppReference = false;
  top.appReference = "";

  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.translation = if in_sig && top.signature.outputElement.elementName == q.name 
		    then "context"
		    else if in_sig
		    then "((" ++ top.typerep.transType ++ ")context.child(" ++ className ++ ".i_" ++ fName  ++ "))" 
		    else if in_locals 
			 then "((" ++ top.typerep.transType ++ ")context.local(\"" ++ fName ++ "\"))" 
			 else "(error(\"BOOM\"))";
}

aspect production dontDecorateExpr
top::Expr ::= q::QName
{
  top.isAppReference = false;
  top.appReference = "";

  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  local attribute tr :: Decorated TypeRep;
  tr = top.typerep;

  top.translation = if in_sig && top.signature.outputElement.elementName == q.name
 		    then "(context.undecorate())" 
		    else if in_sig && tr.isNonTerminal	
		    then "(((common.DecoratedNode)context.child(" ++ className ++ ".i_" ++  fName ++ ")).undecorate())"
		    else if in_sig && !tr.isNonTerminal
		    then "((" ++ tr.transType ++ ")context.child(" ++ className ++ ".i_" ++  fName ++ "))" 
		    else if in_locals && tr.isNonTerminal
	    	    then "(((common.DecoratedNode)context.local(\"" ++ fName ++ "\")).undecorate())" 
		    else if in_locals && !tr.isNonTerminal
	    	    then "((" ++ tr.transType ++ ")context.local(\"" ++ fName ++ "\"))" 
--		    else if in_locals && (tr.isNonTerminal || tr.isDecorated)
--	    	    then "(((common.DecoratedNode)context.local(\"" ++ fName ++ "\")).undecorate())" 
--		    else if in_locals && !(tr.isNonTerminal || tr.isDecorated)
--	    	    then "((" ++ tr.transType ++ ")context.local(\"" ++ fName ++ "\"))" 
		    else "(error(\"BOOM\"))";
}

aspect production productionReference
top::Expr ::= q::QName
{
  top.isAppReference = true;
  top.appReference = makeClassName(fName);

  top.translation = "(" ++ makeClassName(fName) ++ ".class.getConstructors()[0])";
}

aspect production functionReference
top::Expr ::= q::QName
{
  top.isAppReference = true;
  top.appReference = makeClassName(fName);

  top.translation = "(" ++ makeClassName(fName) ++ ".class.getConstructors()[0])";
}

aspect production productionApplicationDispatcher
top::Expr ::= e::Expr es::Exprs
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation = if e.isAppReference 
		    then "(new " ++ e.appReference ++ "(" ++ es.translation ++ "))"
		    else "((" ++ top.typerep.transType ++ ")common.Util.construct(" ++ e.translation ++ ", new Object[]{" ++ es.translation ++ "}))";
}

aspect production functionApplicationDispatcher
top::Expr ::= e::Expr es::Exprs
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation = if e.isAppReference 
		    then "((" ++ e.typerep.outputType.transType ++ ")new " ++ e.appReference ++ "(" ++ es.translation ++ ").doReturn())"
		    else "((" ++ e.typerep.outputType.transType ++ ")((common.FunctionNode)common.Util.construct(" ++ e.translation ++ ", new Object[]{" ++ es.translation ++ "})).doReturn())";
}

aspect production atAccess
top::Expr ::= e::Expr '@' q::QName
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation = "((" ++ top.typerep.transType ++ ")" ++ e.translation ++ 
			(if isSynthesized(fName, top.env) 
	   		 then ".synthesized(\"" ++ fName ++ "\"))"  
 			 else ".inherited(\"" ++ fName ++ "\"))");
			
}

aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.isAppReference = false;
  top.appReference = "";

  top.translation = "(" ++ e.translation ++ ".decorate(context, " ++ 
				"common.Util.populateMap(new String[]{" ++ folds(", ", inh.nameTrans) ++ "}, " ++ 
						        "new common.Lazy[]{" ++ folds(", ", inh.valueTrans) ++ "})))"; 

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
  top.nameTrans = ["\"" ++ fName ++ "\""];
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

aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
top.translation =
  if e1.typerep.isInteger && e2.typerep.isInteger
         then "(" ++ e1.translation ++ ".intValue() > " ++ e2.translation ++ ".intValue())"
         else if e1.typerep.isFloat && e2.typerep.isFloat
         then "(" ++ e1.translation ++ ".floatValue() > " ++ e2.translation ++ ".floatValue())"
         else if e1.typerep.isString && e2.typerep.isString
         then "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString())) > 0"
         else error(top.location.pp ++ "Invalid >");
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
top.translation =
  if e1.typerep.isInteger && e2.typerep.isInteger
         then "(" ++ e1.translation ++ ".intValue() < " ++ e2.translation ++ ".intValue())"
         else if e1.typerep.isFloat && e2.typerep.isFloat
         then "(" ++ e1.translation ++ ".floatValue() < " ++ e2.translation ++ ".floatValue())"
         else if e1.typerep.isString && e2.typerep.isString
         then "(" ++ e1.translation ++ ".toString().compareTo(" ++ e2.translation ++ ".toString())) < 0"
         else error(top.location.pp ++ "Invalid <");
}

aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.translation = "(" ++ e1.translation ++ " >= " ++ e2.translation ++ ")";
}

aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.translation = "(" ++ e1.translation ++ " <= " ++ e2.translation ++ ")";
}


function eqTrans
String ::= e1::Decorated Expr e2::Decorated Expr{
  -- TODO: couldn't this JUST be .equals? (the last else)
  return if e1.typerep.isInteger && e2.typerep.isInteger
	 then "(" ++ e1.translation ++ ".intValue() == " ++ e2.translation ++ ".intValue())"
	 else if e1.typerep.isFloat && e2.typerep.isFloat
	 then "(" ++ e1.translation ++ ".floatValue() == " ++ e2.translation ++ ".floatValue())"
	 else if e1.typerep.isString && e2.typerep.isString
	 then "(" ++ e1.translation ++ ".toString().equals(" ++ e2.translation ++ ".toString()))"
	 else "(" ++ e1.translation ++ ".equals(" ++ e2.translation ++ "))";
}
aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.translation = eqTrans(e1, e2);
}

aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.translation = "(!" ++ eqTrans(e1, e2) ++ ")";
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

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.translation = "(new Integer(" ++ e1.translation ++ " + " ++ e2.translation ++ "))";
}
aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.translation = "(new Integer(" ++ e1.translation ++ " - " ++ e2.translation ++ "))";
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.translation = "(new Integer(" ++ e1.translation ++ " * " ++ e2.translation ++ "))";
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.translation = "(new Float(" ++ e1.translation ++ " / " ++ e2.translation ++ "))";
}
aspect production neg
top::Expr ::= '-' e::Expr
{
  top.translation = if e.typerep.isInteger then
		 "(new Integer(-" ++ e.translation ++ ".intValue()))"
                    else
                 "(new Float(-" ++ e.translation ++ ".floatValue()))";
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.translation = "(new StringBuffer(" ++ s.lexeme ++ "))";
}

aspect production defaultPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.translation = "(" ++ e1.translation ++ " + " ++ e2.translation ++ ")";
}

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.translation = "(new StringBuffer(" ++ e1.translation ++ ".toString()).append(" ++ e2.translation ++ "))";
}

aspect production exprsEmpty
top::Exprs ::=
{
  top.translation = "";
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.translation = wrapThunk(e.translation);
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.translation = wrapThunk(e1.translation) ++ ", " ++ e2.translation;
}

function wrapThunk
String ::= original::String
{
  return "new common.Thunk(context, new common.Lazy() { public Object eval(common.DecoratedNode context) { return " ++ original ++ "; } })";
}

