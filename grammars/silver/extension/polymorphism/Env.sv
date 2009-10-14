grammar silver:extension:polymorphism;

import core;
import silver:base;

synthesized attribute isPlmOccursDcl :: Boolean;
synthesized attribute isTypeConDcl :: Boolean ;
synthesized attribute arity :: Integer ;
synthesized attribute plmOccursAttParams :: [String];
synthesized attribute plmOccursTcParams :: [String];
synthesized attribute isPlmProdDcl :: Boolean ;
synthesized attribute isPlmAttrDcl :: Boolean ;
synthesized attribute isTypeVarDcl :: Boolean ;

attribute isPlmOccursDcl occurs on EnvItem ; 
attribute isTypeConDcl occurs on EnvItem ;
attribute arity occurs on EnvItem ;
attribute plmOccursAttParams occurs on EnvItem ;
attribute plmOccursTcParams occurs on EnvItem ;
attribute isPlmProdDcl occurs on EnvItem ;
attribute isPlmAttrDcl occurs on EnvItem ;
attribute isTypeVarDcl occurs on EnvItem ;

---------------------------------------------
-- Added EnvItems for polymorphic entities
---------------------------------------------

--  polymorphic OccursOn
abstract production internal_plmOccursEnvItem
i::EnvItem ::= att:: String params1::[String]  tc::String params2::[String]
{
  i.itemName = att ;
  i.decoratesName = tc ; 
  i.isPlmOccursDcl = true; 
  i.plmOccursAttParams = params1 ;
  i.plmOccursTcParams = params2 ;

  forwards to internal_defaultEnvItem() ;
}

function plmOccursEnvItem
Decorated EnvItem ::= att::String ps1::[String] tc::String ps2::[String]
{
  return decorate internal_plmOccursEnvItem(att, ps1, tc, ps2) with {} ;
}

-- Type constructors (polymorphic nonterminals)
abstract production internal_typeConEnvItem
i::EnvItem ::= n::String ar::Integer
{
  i.itemName = n;
  i.arity = ar ;
  i.isTypeConDcl = true;
  forwards to internal_defaultEnvItem();
}

function typeConEnvItem
Decorated EnvItem ::= n::String ar::Integer
{
  return decorate internal_typeConEnvItem(n, ar) with {} ;
}


-- Polymorphic Productions
abstract production internal_plmProdEnvItem
i::EnvItem ::= n::String ar::Integer lhs::String 
	rhs::[String] t::Decorated TypeRep
{
  i.itemName = n;
  i.pp = n ++ "::" ++ t.pp;

  i.typerep = t; 
  i.arity = ar; 
  i.lhsName = lhs;
  i.rhsNames = rhs;
  i.isPlmProdDcl = true;
  
  forwards to internal_defaultEnvItem();
}


function plmProdEnvItem
Decorated EnvItem ::= n::String ar::Integer lhs::String 
	rhs::[String] t::Decorated TypeRep
{
  return decorate internal_plmProdEnvItem(n, ar, lhs, rhs, t) with {};
}


-- Type Variables declared for the scope of a production
abstract production internal_typeVarEnvItem
i::EnvItem ::= n::String
{
  i.itemName = n;
  i.isTypeVarDcl = true;
  forwards to internal_defaultEnvItem();
}

function typeVarEnvItem
Decorated EnvItem ::= n::String 
{
  return decorate internal_typeVarEnvItem(n) with {} ;
}


-- Polymorphic Attributes
abstract production internal_plmAttrEnvItem
i::EnvItem ::= n::String ar::Integer t::Decorated TypeRep
{
  i.itemName = n;
  i.pp = "attr " ++ n ++ " :: " ++ t.pp;

  i.typerep = t;
  i.isPlmAttrDcl = true;
  i.arity = ar;

  forwards to internal_defaultEnvItem();
}

function plmAttrEnvItem
Decorated EnvItem ::= n::String ar::Integer t::Decorated TypeRep
{
  return decorate internal_plmAttrEnvItem(n, ar, t) with {};
}


-- Aspect for default EnvItem
aspect production internal_defaultEnvItem
i::EnvItem ::=
{
  i.arity = 0 ;
  i.isPlmOccursDcl = false ;
  i.isTypeConDcl = false;
  i.plmOccursAttParams = [];
  i.plmOccursTcParams = [];
  i.isPlmProdDcl = false;
  i.isPlmAttrDcl = false;
  i.isTypeVarDcl = false;
}



-------------------------------------------------------------
-- functions for adding newly defined items to Defs
-- and getting them from Env
-------------------------------------------------------------


-- Type constructors
function addTypeConDcl
Decorated Defs ::= n::String ar::Integer e::Decorated Defs
{
  return consDefs(typeConEnvItem(n, ar), e);
}

function getTypeConDcl
[Decorated EnvItem] ::= key::String e::Decorated Env
{
  return filterTypeConDcl(getDcl(key, e.restTree)) ;
}


function filterTypeConDcl
[Decorated EnvItem] ::= l::[Decorated EnvItem]
{
  return if null(l) 
	 then []  
         else if head(l).isTypeConDcl
	 then cons(head(l), filterTypeConDcl(tail(l)))
	 else filterTypeConDcl(tail(l)); 
}

-- Polymorphic OccursOn records
function addPlmOccursDcl
Decorated Defs ::= a::String ps1::[String] tc::String ps2::[String]
	e::Decorated Defs
{
  return consDefs(plmOccursEnvItem(a, ps1, tc, ps2), e) ;

}

function getPlmOccursDcl
[Decorated EnvItem] ::= attr::String tc::String e::Decorated Env
{
  return filterPlmOccursDcl(tc, getDcl(attr, e.restTree));
} 

function filterPlmOccursDcl
[Decorated EnvItem] ::= tc::String l::[Decorated EnvItem]
{
  return if null(l) 
	 then [] 
         else if head(l).isPlmOccursDcl
	 then (if tc == head(l).decoratesName 
	       then cons(head(l), filterPlmOccursDcl(tc, tail(l)))
	       else filterPlmOccursDcl(tc, tail(l)))
	 else filterPlmOccursDcl(tc, tail(l)); 
}


-- polymorphic productions
function addPlmProdDcl
Decorated Defs ::= n::String ar::Integer lhs::String rhs::[String] 
	t::Decorated TypeRep e::Decorated Defs
{
 return consDefs(plmProdEnvItem(n, ar, lhs, rhs, t), e);
}

function getPlmProdDcl
[Decorated EnvItem] ::= p::String e::Decorated Env
{
 return filterPlmProdDcl(getDcl(p, e.restTree));
}

function filterPlmProdDcl
[Decorated EnvItem] ::= l::[Decorated EnvItem]
{
  return if null(l) 
 	 then []
	 else if head(l).isPlmProdDcl
	 then cons(head(l), filterPlmProdDcl(tail(l)))
	 else filterPlmProdDcl(tail(l)); 
}


-- polymorphic attributes
function addPlmAttrDcl
Decorated Defs ::= n::String ar::Integer t::Decorated TypeRep e::Decorated Defs
{
 return consDefs(plmAttrEnvItem(n, ar, t), e);
}

function getPlmAttrDcl
[Decorated EnvItem] ::= a::String e::Decorated Env
{
 return filterPlmAttrDcl(getDcl(a, e.restTree));
}

function filterPlmAttrDcl
[Decorated EnvItem] ::= l::[Decorated EnvItem]
{
  return if null(l)
         then []
	 else if head(l).isPlmAttrDcl
	 then cons(head(l), filterPlmAttrDcl(tail(l)))
	 else filterPlmAttrDcl(tail(l)); 
}


-- type variables
function addTypeVarDcl
Decorated Defs ::= n::String e::Decorated Defs
{
  return consDefs(typeVarEnvItem(n), e); 
} 

function getTypeVarDcl
[Decorated EnvItem] ::= n::String e::Decorated Env
{
  return filterTypeVarDcl(getDcl(n, e.restTree));
}

function filterTypeVarDcl
[Decorated EnvItem] ::= l::[Decorated EnvItem]
{
  return if null(l) 
	 then []
	 else if head(l).isTypeVarDcl
	 then cons(head(l), filterTypeVarDcl(tail(l)))
	 else filterTypeVarDcl(tail(l));
}

--------------------------------------------------------
-- Functions for checking polymorphic occursOn relations
-------------------------------------------------------- 

function plmDoesOccurOn
Boolean ::= attr::String args::[Decorated TypeRep] nttype::Decorated TypeRep 
	e::Decorated Env
{
  local attribute occs :: [Decorated EnvItem];
  occs = getPlmOccursDcl(attr, nttype.typecon, e);

  return if null(occs)
	 then false
	 else varBindingCompatible(args, nttype.constructArgs, head(occs)); 
}


function varBindingCompatible
Boolean ::= a_args::[Decorated TypeRep] nt_args::[Decorated TypeRep] 
	o::Decorated EnvItem
{
  return if length(a_args) != length(o.plmOccursAttParams) ||
	    length(nt_args) != length(o.plmOccursTcParams)
	 then false
	 else bindingsIn(getBindings(a_args, o.plmOccursAttParams),
		    getBindings(nt_args, o.plmOccursTcParams));
}

function getBindings
[VarBinding] ::= ts::[Decorated TypeRep] vs::[String]
{
  return if null(ts) 
	 then []
	 else cons(varBinding(head(vs), head(ts)), 
		   getBindings(tail(ts), tail(vs)));
}

function bindingsIn
Boolean ::= bs1::[VarBinding] bs2::[VarBinding]
{
  return if null(bs1) then true
	 else if !bindingIn(head(bs1), bs2) then false
	 else bindingsIn(tail(bs1), bs2);
}  

function bindingIn
Boolean ::= b::VarBinding bs::[VarBinding]
{
  return if null(bs) then false
	 else if varBindingEquals(b'', head(bs)) then true
 	 else bindingIn(b'', tail(bs));
}


-------------------------------------
synthesized attribute var :: String ;
synthesized attribute boundType :: Decorated TypeRep;

nonterminal VarBinding with {var, boundType} ;

abstract production varBinding
vb::VarBinding ::= v::String t::Decorated TypeRep
{
  vb.var = v;
  vb.boundType = t;
}

function varBindingEquals
Boolean ::= vb1::VarBinding vb2::VarBinding
{
  return vb1.var == vb2.var && 
	vb1.boundType.typeEquals(vb1.boundType, vb2.boundType).bValue; 
}



---------------------------------------------------
-- Functions for converting polymorphic environments
-- to monomorphic ones
----------------------------------------------------

function convertToMonoEnv
Decorated Env ::= e::Decorated Env
{
  return convertAndAdd(getOtherDcls(e), e);
}

-- TODO: needs to be modified to dispatch for convert and add
-- functions for better extensibility
function convertAndAdd
Decorated Env ::= l::[Decorated EnvItem] e::Decorated Env
{
  local attribute h :: Decorated EnvItem;
  h = head(l);

  local attribute t :: [Decorated EnvItem];
  t = tail(l);

  return if null(l)
	 then e
	 else if h.isTypeConDcl then 
	    convertAndAdd(t, toEnv(addTypeDcl(h.itemName, 
			     ntTypeRep(h.itemName), 
			     toDefs(e))))
	 else if h.isPlmOccursDcl then
	    convertAndAdd(t, toEnv(addOccursDcl(h.itemName,
			       h.decoratesName,
			       toDefs(e))))
	 else if h.isPlmProdDcl then
	    convertAndAdd(t, toEnv(addProductionDcl(h.itemName,
				   h.lhsName,
				   h.rhsNames,
				   toMonoTypeRep(h.typerep),
				   toDefs(e))))
	 else if h.isPlmAttrDcl then
	    convertAndAdd(t, toEnv(addAttributeDcl(h.itemName,
				  toMonoTypeRep(h.typerep),
				  toDefs(e))))
	 else if h.isTypeVarDcl then convertAndAdd(t, e)
	 else convertAndAdd(t, e) ;
--	 else error("Error: Unknown polymorphic EnvItem '" ++ h.pp ++ "'.\n"); 
			       
}


