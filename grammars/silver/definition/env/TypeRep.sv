grammar silver:definition:env;
import silver:definition:regex;

nonterminal TypeRep with typeName, inputTypes, outputType,
                          isPrimative, isTerminal, isNonTerminal, isDecorated, isProduction, unparse, typeEquals, decoratedType,
			  isInteger, isFloat, isString, isBoolean, isFunction, regularExpression, isSynthesized, isInherited;

synthesized attribute typeEquals :: Production (TypeEquals ::= Decorated TypeRep Decorated TypeRep);

synthesized attribute unparse :: String;
synthesized attribute typeName :: String;
synthesized attribute regularExpression :: Decorated Regex_R;
synthesized attribute decoratedType :: Decorated TypeRep;
synthesized attribute inputTypes :: [Decorated TypeRep];
synthesized attribute outputType :: Decorated TypeRep;

synthesized attribute isPrimative :: Boolean;
synthesized attribute isInteger :: Boolean;
synthesized attribute isFloat :: Boolean;
synthesized attribute isString :: Boolean;
synthesized attribute isBoolean :: Boolean;
synthesized attribute isTerminal :: Boolean;
synthesized attribute isNonTerminal :: Boolean;
synthesized attribute isDecorated :: Boolean;
synthesized attribute isProduction :: Boolean;
synthesized attribute isFunction :: Boolean;

synthesized attribute isSynthesized :: Boolean;
synthesized attribute isInherited :: Boolean;

function integerTypeRep
Decorated TypeRep ::= 
{
  return decorate i_integerTypeRep() with {};
}
abstract production i_integerTypeRep
top::TypeRep ::= 
{
  top.isInteger = true;
  top.typeName = "Integer";
  top.unparse = "int" ;
  top.isPrimative = true;
  forwards to i_defaultTypeRep();
}

function floatTypeRep
Decorated TypeRep ::= 
{
  return decorate i_floatTypeRep() with {};
}

abstract production i_floatTypeRep
top::TypeRep ::= 
{
  top.isFloat = true;
  top.typeName = "Float";
  top.unparse = "float" ;
  top.isPrimative = true;
  forwards to i_defaultTypeRep();
}

function stringTypeRep
Decorated TypeRep ::= 
{
  return decorate i_stringTypeRep() with {};
}
abstract production i_stringTypeRep
top::TypeRep ::= 
{
  top.isString = true;
  top.typeName = "String";
  top.unparse = "string" ;
  top.isPrimative = true;
  forwards to i_defaultTypeRep();
}

function booleanTypeRep
Decorated TypeRep ::= 
{
  return decorate i_booleanTypeRep() with {};
}
abstract production i_booleanTypeRep
top::TypeRep ::= 
{
  top.isBoolean = true;
  top.typeName = "Boolean";
  top.unparse = "bool" ;
  top.isPrimative = true;
  forwards to i_defaultTypeRep();
}

function termTypeRep
Decorated TypeRep ::= n::String r::Decorated Regex_R
{
  return decorate i_termTypeRep(n, r) with {};
}

abstract production i_termTypeRep
top::TypeRep ::= n::String r::Decorated Regex_R
{
  top.typeName = n;
  top.regularExpression = r;
  top.unparse = "term('" ++ n ++ "', /" ++ r.regString ++ "/)";
  top.isTerminal = true;
  forwards to i_defaultTypeRep();
}

function ntTypeRep
Decorated TypeRep ::= n::String
{
  return decorate i_ntTypeRep(n) with {};
}

abstract production i_ntTypeRep
top::TypeRep ::= n::String
{
  top.typeName = n;
  top.unparse = "nt('" ++ n ++ "')";
  top.isNonTerminal = true;

  forwards to i_defaultTypeRep();
}
--TODO change this to decorated
function refTypeRep
Decorated TypeRep ::= t::Decorated TypeRep
{
  return decorate i_refTypeRep(t) with {};
}

abstract production i_refTypeRep
top::TypeRep ::= t::Decorated TypeRep
{
  top.typeName = t.typeName;
  top.unparse = "decorated(" ++ t.unparse ++ ")" ;
  top.decoratedType = t;
  top.isDecorated = true;

  forwards to i_defaultTypeRep();
}

function prodTypeRep
Decorated TypeRep ::= it::[Decorated TypeRep] ot::Decorated TypeRep
{
  return decorate i_prodTypeRep(it,ot) with {};
}

abstract production i_prodTypeRep
top::TypeRep ::= it::[Decorated TypeRep] ot::Decorated TypeRep
{
  top.typeName = "Production";
  top.unparse = "prod(" ++ unparseTypes(it) ++ ", " ++ ot.unparse ++ ")"  ;
  top.isProduction = true;

  top.inputTypes = it;
  top.outputType = ot;
  forwards to i_defaultTypeRep();
}

function funTypeRep
Decorated TypeRep ::= it::[Decorated TypeRep] ot::Decorated TypeRep
{
  return decorate i_funTypeRep(it,ot) with {};
}
abstract production i_funTypeRep
top::TypeRep ::= it::[Decorated TypeRep] ot::Decorated TypeRep
{
  top.typeName = "Function";
  top.unparse = "fun(" ++ unparseTypes(it) ++ ", " ++ ot.unparse ++ ")"  ;
  top.isFunction = true;

  top.inputTypes = it;
  top.outputType = ot;
  forwards to i_defaultTypeRep();
}

function synTypeRep
Decorated TypeRep ::= t::Decorated TypeRep
{
  return decorate i_synTypeRep(t) with {};
}
abstract production i_synTypeRep
top::TypeRep ::= t::Decorated TypeRep
{
  top.unparse = "syn(" ++ forward.unparse ++ ")";
  top.isSynthesized = true;

  forwards to new(t);
}

function inhTypeRep
Decorated TypeRep ::= t::Decorated TypeRep
{
  return decorate i_inhTypeRep(t) with {};
}
abstract production i_inhTypeRep
top::TypeRep ::= t::Decorated TypeRep
{
  top.unparse = "inh(" ++ forward.unparse ++ ")";
  top.isInherited = true;

  forwards to new(t);
}

function unparseTypes
String ::= l::[Decorated TypeRep]{
  return "[" ++ unparseTypesHelp(l) ++ "]";
}
function unparseTypesHelp
String ::= l::[Decorated TypeRep]{
  return if null(l) then "" else head(l).unparse ++ (if null(tail(l)) then "" else (", " ++ unparseTypesHelp(tail(l))));
}

function topTypeRep
Decorated TypeRep ::=
{
  return decorate i_topTypeRep() with {};
}

abstract production i_topTypeRep
top::TypeRep ::= 
{
  top.typeName = "TOP";
  top.unparse = "top";
  top.regularExpression = decorate Rtoeps() with {};
  top.isPrimative = true;
  top.isInteger = true;
  top.isFloat = true;
  top.isString = true;
  top.isBoolean = true;
  top.isTerminal = true;
  top.isNonTerminal = true;
  top.isDecorated = true;
  top.isProduction = true;
  top.isFunction = true;
  top.isSynthesized = true;
  top.isInherited = true;
  forwards to i_defaultTypeRep();
}

function defaultTypeRep
Decorated TypeRep ::=
{
  return decorate i_defaultTypeRep() with {};
}

abstract production i_defaultTypeRep
top::TypeRep ::= 
{
  top.typeName = "DEFAULT";
  top.unparse = "default";
  top.regularExpression = decorate Rtoeps() with {};
  top.isPrimative = false;
  top.isInteger = false;
  top.isFloat = false;
  top.isString = false;
  top.isBoolean = false;
  top.isTerminal = false;
  top.isNonTerminal = false;
  top.isDecorated = false;
  top.isProduction = false;
  top.isFunction = false;
  top.isSynthesized = false;
  top.isInherited = false;
  top.decoratedType = topTypeRep();
  top.typeEquals = genEquals;

  top.inputTypes = [];
  top.outputType = topTypeRep();
}

nonterminal TypeEquals with bValue;
abstract production genEquals
top::TypeEquals ::= t1::Decorated TypeRep t2::Decorated TypeRep
{
    top.bValue = t1.typeName == "TOP" || t2.typeName == "TOP" ||
   	   ((t1.typeName == t2.typeName) &&  
	    ((t1.isPrimative && t2.isPrimative) ||
	     (t1.isTerminal && t2.isTerminal) ||
	     (t1.isNonTerminal && t2.isNonTerminal) ||
	     (t1.isDecorated && t2.isDecorated) ||
	     (t1.isProduction && t2.isProduction &&
	      t1.outputType.typeEquals(t1.outputType, t2.outputType).bValue &&
	      genListEquals(t1.inputTypes, t2.inputTypes))
		||
	     (t1.isFunction && t2.isFunction &&
	      t1.outputType.typeEquals(t1.outputType, t2.outputType).bValue &&
	      genListEquals(t1.inputTypes, t2.inputTypes))
	));
}

function genListEquals
Boolean ::= t1::[Decorated TypeRep] t2::[Decorated TypeRep]
{
  return if null(t1) && null(t2)
	 then true
	 else if null(t1) || null(t2)
	      then false
	      else head(t1).typeEquals(head(t1), head(t2)).bValue
		   && genListEquals(tail(t1), tail(t2));
}
