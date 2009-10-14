grammar silver:extension:polymorphism;

import core;
import silver:base;
import silver:extension:functions;

synthesized attribute isTypeVar :: Boolean ;
synthesized attribute isQuantifiedType :: Boolean ;
synthesized attribute isConstructedType :: Boolean ;
synthesized attribute typeVar :: String;
synthesized attribute typecon :: String;
synthesized attribute constructArgs :: [Decorated TypeRep];
synthesized attribute quantifiedBody :: Decorated TypeRep ;

attribute isTypeVar occurs on TypeRep ;
attribute isQuantifiedType occurs on TypeRep ;
attribute isConstructedType occurs on TypeRep ;
attribute typeVar occurs on TypeRep;
attribute typecon occurs on TypeRep;
attribute constructArgs occurs on TypeRep;
attribute quantifiedBody occurs on TypeRep;


-----------------------------------------------------
-- New type representations
-----------------------------------------------------

abstract production internal_typeVarTypeRep
t::TypeRep ::= v::String
{
  t.typeName = v ;
  t.pp = v;
  t.isTypeVar = true;
  t.typeEquals = typeVarTypeEquals;
  t.typeVar = v;
  t.isProduction = false;

  forwards to internal_anyTypeTypeRep();
}

function typeVarTypeRep
Decorated TypeRep ::= v::String
{
  return decorate internal_typeVarTypeRep(v) with {};
}


abstract production internal_constructedTypeRep
t::TypeRep ::= tc::String args::[Decorated TypeRep] 
{
  t.pp = tc ++ "<<" ++ printTypes(args) ++ ">>" ;
  t.isConstructedType = true;
  t.typeEquals = constructedTypeEquals;
  t.typecon = tc;
  t.constructArgs = args;

  forwards to internal_ntTypeRep(tc);
}

function constructedTypeRep
Decorated TypeRep ::= tc::String args::[Decorated TypeRep]
{
  return decorate internal_constructedTypeRep(tc, args) with {};
} 


abstract production internal_quantifiedTypeRep
t::TypeRep ::= v::String t1::Decorated TypeRep
{
  t.pp = "(FORALL " ++ v ++ "(" ++ t1.pp ++ "))";
  t.typeName = t.pp;
  t.isQuantifiedType = true ;
  t.typeEquals = quantifiedTypeEquals;
  t.typeVar = v ;
  t.quantifiedBody = t1 ; 

  forwards to internal_defaultTypeRep(); 
}

function quantifiedTypeRep
Decorated TypeRep ::= v::String t::Decorated TypeRep
{
  return decorate internal_quantifiedTypeRep(v, t) with {};
}


aspect production internal_defaultTypeRep
t::TypeRep ::=
{
 t.isTypeVar = false;
 t.isQuantifiedType = false;
 t.isConstructedType = false;
 t.typeVar = "";
 t.typecon = "";
 t.constructArgs = [];
 t.quantifiedBody = topTypeRep();
}


abstract production typeVarTypeEquals
t::TypeEquals ::= t1::Decorated TypeRep t2::Decorated TypeRep
{
  t.bValue = t1.typeName == "TOP" || t2.typeName == "TOP" ||
	t1.typeVar == t2.typeVar; 
}

abstract production constructedTypeEquals
t::TypeEquals ::= t1::Decorated TypeRep t2::Decorated TypeRep
{
  t.bValue = t1.typeName == "TOP" || t2.typeName == "TOP" ||
	(t1.typecon == t2.typecon && 
	genListEquals(t1.constructArgs, t2.constructArgs));
}



-- NOTE: the equivalence of quantifiedType ideally should require alpha 
-- equivalence, which is not currently supported since I think we don't
-- need to compare the equivalence of two quantified types anywhere
-- in current silver implementation

abstract production quantifiedTypeEquals
t::TypeEquals ::= t1::Decorated TypeRep t2::Decorated TypeRep
{
  t.bValue = t1.typeName == "TOP" || t2.typeName == "TOP" ||
	(t1.typeVar == t2.typeVar && 
	t1.quantifiedBody.typeEquals(t1.quantifiedBody, t2.quantifiedBody).bValue);
}


-------------------------------------------------------
-- Utility functions
-------------------------------------------------------

function printTypes
String ::= ts::[Decorated TypeRep]
{
  return if null(ts)
	 then ""
	 else if length(ts) == 1
	 then head(ts).pp
	 else head(ts).pp ++ ", " ++ printTypes(tail(ts));
}

function dequantifyType
Decorated TypeRep ::= t::Decorated TypeRep
{
  return case (t) of
     		internal_quantifiedTypeRep(_, t1) => dequantifyType(t1)  |
     		_ => t 
         end ;
}

function quantifyType
Decorated TypeRep ::= vs::[String] t::Decorated TypeRep
{
  return if null(vs) then t
	 else quantifyType(tail(vs), quantifiedTypeRep(head(vs), t));
}

function typeApplication
Decorated TypeRep ::= t::Decorated TypeRep args::[Decorated TypeRep]
{
  return if null(args)
	 then t
	 else if !t.isQuantifiedType
	 then error("Error: type application to non-polymorphic type '" ++ t.pp ++ "'.\n")
	 else typeApplication(replaceInType(t.quantifiedBody, 
					t.typeVar,
					head(args)), 
			      tail(args)); 
}

function reverseParamList
[String] ::= l::[String]
{
  return reverseParamListHelp(l, []);
}

function reverseParamListHelp
[String] ::= l::[String] r::[String]
{
  return if null(l) then r
	 else reverseParamListHelp(tail(l), cons(head(l), r));
}

function replaceInType
Decorated TypeRep ::= t::Decorated TypeRep v::String t1::Decorated TypeRep
{
  local attribute t2 :: Decorated TypeRep;
  t2 = decorate new(t1) with {};

  return case t'' of
		internal_typeVarTypeRep(x) => if x == v then t2 else t  |
		internal_quantifiedTypeRep(x, y) => 
			quantifiedTypeRep(x, replaceInType(y, v, t1))    |
		internal_constructedTypeRep(x, y) =>
			constructedTypeRep(x, replaceInTypes(y, v, t1))     |
		internal_prodTypeRep(x, y) =>
			prodTypeRep(replaceInTypes(x, v, t1),
				    replaceInType(y, v, t1))             |
		internal_refTypeRep(x) =>
			refTypeRep(replaceInType(x, v, t1))   		 |
		internal_functionTypeRep(x, y, z) =>
			functionTypeRep(x, replaceInTypes(y, v, t1), 
				replaceInType(z, v, t1))		|
		_  => t
	 end;
}

function replaceInTypes
[Decorated TypeRep] ::= ts::[Decorated TypeRep] v::String t1::Decorated TypeRep
{
  return if null(ts) then []
	 else cons(replaceInType(head(ts), v, t1), 
	 	   replaceInTypes(tail(ts), v, t1));
}


function toMonoTypeRep
Decorated TypeRep ::= t::Decorated TypeRep
{
  return case t of
	   internal_typeVarTypeRep(_) => anyTypeTypeRep()  |
	   internal_constructedTypeRep(x, _) => ntTypeRep(x)  |
	   internal_quantifiedTypeRep(_, x) => toMonoTypeRep(x) |
	   internal_prodTypeRep(x, y) => 
		prodTypeRep(toMonoTypeReps(x), toMonoTypeRep(y))  |
	   internal_refTypeRep(x) => refTypeRep(toMonoTypeRep(x)) |
	   internal_functionTypeRep(x, y, z) =>
		functionTypeRep(x, toMonoTypeReps(y), toMonoTypeRep(z)) |
	   _	=> t
	 end;
}

function toMonoTypeReps
[Decorated TypeRep] ::= ts::[Decorated TypeRep]
{
  return if null(ts) then []
	 else cons(toMonoTypeRep(head(ts)), toMonoTypeReps(tail(ts)));
} 



function toTypeRepWithAttVars
Decorated TypeRep ::= t::Decorated TypeRep
{
  return case t'' of
		internal_typeVarTypeRep(x) => 
			typeVarTypeRep("_ATT_" ++ x)                    |
		internal_quantifiedTypeRep(x, y) => 
			quantifiedTypeRep(x, toTypeRepWithAttVars(y))    |
		internal_constructedTypeRep(x, y) =>
			constructedTypeRep(x, toTypeRepsWithAttVars(y))     |
		internal_prodTypeRep(x, y) =>
			prodTypeRep(toTypeRepsWithAttVars(x),
				    toTypeRepWithAttVars(y))             |
		internal_refTypeRep(x) =>
			refTypeRep(toTypeRepWithAttVars(x))   		 |
		internal_functionTypeRep(x, y, z) => 
			functionTypeRep(x, toTypeRepsWithAttVars(y), 
				toTypeRepWithAttVars(z))	|
		_  => t
	 end;
}

function toTypeRepsWithAttVars
[Decorated TypeRep] ::= ts::[Decorated TypeRep]
{
  return if null(ts) then []
	 else cons(toTypeRepWithAttVars(head(ts)), 
	 	   toTypeRepsWithAttVars(tail(ts)));
}


function toTypeRepWithProdVars
Decorated TypeRep ::= t::Decorated TypeRep
{
  return case t'' of
		internal_typeVarTypeRep(x) => 
			typeVarTypeRep("_PROD_" ++ x)                    |
		internal_quantifiedTypeRep(x, y) => 
			quantifiedTypeRep(x, toTypeRepWithProdVars(y))    |
		internal_constructedTypeRep(x, y) =>
			constructedTypeRep(x, toTypeRepsWithProdVars(y))     |
		internal_prodTypeRep(x, y) =>
			prodTypeRep(toTypeRepsWithProdVars(x),
				    toTypeRepWithProdVars(y))             |
		internal_refTypeRep(x) =>
			refTypeRep(toTypeRepWithProdVars(x))   		 |
  		internal_functionTypeRep(x, y, z) => 
			functionTypeRep(x, toTypeRepsWithProdVars(y), 
				toTypeRepWithProdVars(z))	|
		_  => t
	 end;
}

function toTypeRepsWithProdVars
[Decorated TypeRep] ::= ts::[Decorated TypeRep]
{
  return if null(ts) then []
	 else cons(toTypeRepWithProdVars(head(ts)), 
	 	   toTypeRepsWithProdVars(tail(ts)));
}

function varsToTypeVarReps
[Decorated TypeRep] ::= vs::[String]
{
  return if null(vs) then []
	 else cons(typeVarTypeRep(head(vs)), varsToTypeVarReps(tail(vs))); 
}

-----------------------------------------------------------
synthesized attribute plmAppDispatcher :: Production (Expr ::= QName TypeArgs Exprs);
attribute plmAppDispatcher occurs on TypeRep;

aspect production internal_defaultTypeRep
t::TypeRep ::=
{
  t.plmAppDispatcher = plmProdApp;
}


