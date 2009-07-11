grammar silver:extension:polymorphism;

import core;
import silver:base;
import silver:analysis:typechecking;



------------------------------
-- Polymorphic Types
------------------------------

concrete production constructedType
t::Type ::= q::QName args::TypeArgs
{
  t.pp = q.pp ++ args.pp ;
  t.location = q.location ;
  
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDclOne(q.name, t.env);

  production attribute fName :: String;
  fName = if !null(fNames) then head(fNames).fullName else "FULL_NAME_NOT_DEFINED";

  production attribute tcs :: [Decorated EnvItem];
  tcs = getTypeConDcl(fName, t.env);


  local attribute er1 :: String ;
  er1 = if null(tcs)
	then t.location ++ "Error: '" ++ q.name ++ "' is not bound to a type constructor.\n"
	else "";

  local attribute er2 :: String ;
  er2 = if !null(tcs) && head(tcs).arity != args.size
	then t.location ++ "Error: Incorrect number of type arguments for polymorphic "
		++ "nonterminal '" ++ head(tcs).pp ++ "'.\n"
	else ""; 

  t.errors = er1 ++ er2 ++ args.errors;

  t.typerep = constructedTypeRep(fName, args.typereps) ;
}

-- redefined production for base language 
concrete production namedType
t::Type ::= n::QName
{
  t.pp = n.name ;
  t.location = n.location ;

  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDclOne(n.name, t.env);

  production attribute fName :: String;
  fName = if !null(fNames) then head(fNames).fullName else "FULL_NAME_NOT_DEFINED";

  production attribute types :: [Decorated EnvItem];
  types = getTypeDclOne(fName, t.env);

  production attribute isVarInScope :: Boolean ;
  isVarInScope = !null(getTypeVarDcl(n.name, t.env)) ; 

  local attribute er1 :: String ;
  er1 = if !null(fNames) && null(types) 
	then n.location ++ "Error: Name '" ++ n.name ++ "' is not a type.\n"
	else "";

  local attribute er2 :: String;
  er2 = if null(fNames) && !isVarInScope
	then n.location ++ "Error: Name '" ++ n.name ++ "' not declared.\n"
	else "";

  t.errors = er1 ++ er2;

  --
  local attribute fwd :: Type;
  fwd = if null(fNames) && isVarInScope
	then varType(n.name)
	else nttType(n'');

  forwards to fwd'' with {env = t.env;} ;
}


abstract production varType
t::Type ::= n::String
{
  t.typerep = typeVarTypeRep(n);
}



-- Auxilliary functions
-----------------------------------------------------
function toMonoType
Type ::= t::Decorated Type
{
  return case t of
	    constructedType(x, _) => nttType(new(x))  |
	    namedType(_) => (case t.typerep of
				internal_typeVarTypeRep(_) => anyType(terminal(AnyType_kwd, ""))   |
				_ => new(t) 
			  end)				|
	    prodType(_, _, z, _) => 
		prodType(terminal(CProduction_kwd, ""),
			 terminal(LParen_t, ""),			 
			 toMonoTypeSig(new(z)),
			 terminal(RParen_t, ""))               |
	    _   =>  new(t)
	 end;
}

function toMonoTypeSig
Signature ::= t::Signature
{
 return case t of 
 	    signatureEmptyRhs(x, _) => 
		signatureEmptyRhs(toMonoType(x), 
				  terminal(CCEQ_t, ""))       |
	    psignature(x, _, y) =>
		psignature(toMonoType(x),
			   terminal(CCEQ_t, ""),
			   toMonoTypeList(y))
        end;
}


function toMonoTypeList
TypeList ::= tl::Decorated TypeList
{
  return case tl of 
	    typeListSingle(x) => typeListSingle(toMonoType(x))  |
	    typeListCons(x, y) =>
		typeListCons(toMonoType(x), toMonoTypeList(y))
	 end;
}


------------------------------------------------------
-- Type Arguments
------------------------------------------------------
terminal DLT_t / \<\< / lexer precedence = 8 ;
terminal DGT_t / \>\> / lexer precedence = 8 ;

nonterminal TypeArgs with {pp, location, size, env, errors, typereps} ;
nonterminal TypeArgList with {pp, location, size, env, errors, typereps} ;

synthesized attribute typereps :: [Decorated TypeRep] ;

concrete production typeArgs
args::TypeArgs ::= l::DLT_t al::TypeArgList g::DGT_t 
{
  args.pp = "<<" ++ al.pp ++ ">>" ;
  args.location = "[line: " ++ toString(l.line) ++ ", column: " ++ 
	toString(l.column) ++ "] ";
  args.typereps = al.typereps;
  args.errors = al.errors;  
  args.size = al.size;
}

concrete production typeArgListOne
al::TypeArgList ::= t::Type
{
  al.pp = t.pp;
  al.location = t.location;
  al.typereps = [t.typerep];
  al.errors = t.errors;
  al.size = 1;
}

concrete production typeArgListMore
al::TypeArgList ::=  al1::TypeArgList c::Comma_t t::Type
{
  al.pp = al1.pp ++ ", " ++ t.pp;  
  al.location = al1.location;
  al.typereps = al1.typereps +++ [t.typerep] ;
  al.errors = al1.errors ++ t.errors;
  al.size = al1.size + 1; 
}