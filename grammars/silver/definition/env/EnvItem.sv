grammar silver:definition:env;

function printEnvItems
String ::= i::[Decorated EnvItem]{
  return if null(i) then "" else head(i).unparse ++ "\n" ++ printEnvItems(tail(i));
}

closed nonterminal EnvItem 
  with 	unparse, itemName, fullName, decoratesName, typerep, 
	isValueDeclaration, isTypeDeclaration, isOccursDeclaration, isInheritedDeclaration,
        isSynthesizedDeclaration, isThisDeclaration, isAttributeDeclaration,
        isFullNameDeclaration, isCloseDeclaration, isProductionAttributesDeclaration, attributes,
	namedSignature, isProductionDeclaration, isFunctionDeclaration;


synthesized attribute itemName :: String;
synthesized attribute fullName :: String;
synthesized attribute decoratesName :: String;
synthesized attribute typerep :: Decorated TypeRep;
synthesized attribute namedSignature :: Decorated NamedSignature;
synthesized attribute attributes :: Decorated Defs;

synthesized attribute isValueDeclaration :: Boolean;
synthesized attribute isFullNameDeclaration :: Boolean;
synthesized attribute isTypeDeclaration :: Boolean;
synthesized attribute isAttributeDeclaration :: Boolean;
synthesized attribute isProductionDeclaration :: Boolean;
synthesized attribute isFunctionDeclaration :: Boolean;
synthesized attribute isOccursDeclaration :: Boolean;
synthesized attribute isInheritedDeclaration :: Boolean;
synthesized attribute isSynthesizedDeclaration :: Boolean;
synthesized attribute isThisDeclaration :: Boolean;
synthesized attribute isCloseDeclaration :: Boolean;
synthesized attribute isProductionAttributesDeclaration ::Boolean;

----------------------------------------------------------------------------------------------------
--item creation functions---------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------
function valueEnvItem
Decorated EnvItem ::= n::String value::Decorated TypeRep
{
  return decorate i_valueEnvItem(n, value) with {};
}
abstract production i_valueEnvItem
top::EnvItem ::= n::String v::Decorated TypeRep
{
  top.unparse = "value('" ++ n ++ "', " ++ v.unparse ++ ")";

  -- required to be defined.
  top.itemName = n;

  -- will be defined based on what type of item it is.  
  top.typerep = v;

  -- required to be defined.
  top.isValueDeclaration = true;

  forwards to i_defaultEnvItem();
}

function typeEnvItem
Decorated EnvItem ::= n::String type::Decorated TypeRep
{
  return decorate i_typeEnvItem(n, type) with {};
}

abstract production i_typeEnvItem
top::EnvItem ::= n::String value::Decorated TypeRep
{
  top.unparse = "type('" ++ n ++ "', " ++ value.unparse ++ ")";

  -- required to be defined.
  top.itemName = n;

  -- will be defined based on what type of item it is.  
  top.typerep = value ;

  top.isTypeDeclaration = true;

  forwards to i_defaultEnvItem();
}

function productionEnvItem
Decorated EnvItem ::= p::Decorated NamedSignature
{
  return decorate i_productionEnvItem(p) with {};
}

abstract production i_productionEnvItem
top::EnvItem ::= p::Decorated NamedSignature
{
  top.unparse = "prod(" ++ p.unparse ++  ")";

  -- required to be defined.
  top.itemName = p.fullName;
  top.namedSignature = p;
  -- will be defined based on what type of item it is.  

  -- required to be defined.
  top.isProductionDeclaration = true;

  forwards to i_defaultEnvItem();
}

function functionEnvItem
Decorated EnvItem ::= f::Decorated NamedSignature
{
  return decorate i_functionEnvItem(f) with {};
}

abstract production i_functionEnvItem
top::EnvItem ::= f::Decorated NamedSignature
{
  top.unparse = "fun(" ++ f.unparse ++  ")";

  -- required to be defined.
  top.itemName = f.fullName;
  top.namedSignature = f;
  -- will be defined based on what type of item it is.  

  -- required to be defined.
  top.isFunctionDeclaration = true;

  forwards to i_defaultEnvItem();
}

function attributeEnvItem
Decorated EnvItem ::= n::String value::Decorated TypeRep
{
  return decorate i_attributeEnvItem(n, value) with {};
}

abstract production i_attributeEnvItem
top::EnvItem ::= n::String value::Decorated TypeRep
{
  top.unparse = "attr('" ++ n ++ "', " ++ value.unparse ++ ")";

  -- required to be defined.
  top.itemName = n;

  -- will be defined based on what type of item it is.  
  top.typerep = value;

  top.isAttributeDeclaration = true;

  forwards to i_defaultEnvItem();
}


function occursEnvItem
Decorated EnvItem ::= n::String dn::String
{
  return decorate i_occursEnvItem(n, dn) with {};
}

abstract production i_occursEnvItem
top::EnvItem ::= n::String dn::String
{
  top.unparse = "@('" ++ n ++ "', '" ++ dn ++ "')";

  -- required to be defined.
  top.itemName = n;
  
  top.decoratesName = dn;

  top.isOccursDeclaration = true;

  forwards to i_defaultEnvItem();
}

function inheritedEnvItem
Decorated EnvItem ::= n::String
{
  return decorate i_inheritedEnvItem(n) with {};
}
abstract production i_inheritedEnvItem
top::EnvItem ::= n::String
{
  top.unparse = "inh('" ++ n ++ "')"; 

  -- required to be defined.
  top.itemName = n;

  top.isInheritedDeclaration = true;

  forwards to i_defaultEnvItem();
}

function synthesizedEnvItem
Decorated EnvItem ::= n::String
{
  return decorate i_synthesizedEnvItem(n) with {};
}
abstract production i_synthesizedEnvItem
top::EnvItem ::= n::String
{
  top.unparse = "syn('" ++ n ++ "')";

  -- required to be defined.
  top.itemName = n;

  top.isSynthesizedDeclaration = true;

  forwards to i_defaultEnvItem();
}
function thisEnvItem
Decorated EnvItem ::= n::String
{
  return decorate i_thisEnvItem(n) with {};
}
abstract production i_thisEnvItem
top::EnvItem ::= n::String
{
  top.unparse = "this('" ++ n ++ "')";

  -- required to be defined.
  top.itemName = n;

  top.isThisDeclaration = true;

  forwards to i_defaultEnvItem();
}

function fullNameEnvItem
Decorated EnvItem ::= n::String fname::String
{
  return decorate i_fullNameEnvItem(n, fname) with {};
}
abstract production i_fullNameEnvItem
top::EnvItem ::= n::String fname::String
{
  top.unparse = "name('" ++ n ++ "', '" ++ fname ++ "')";

  -- required to be defined.
  top.itemName = n;

  top.fullName = fname;
  top.isFullNameDeclaration = true;
  forwards to i_defaultEnvItem();
}


function closeEnvItem
Decorated EnvItem ::= n::String
{
  return decorate i_closeEnvItem(n) with {};
}
abstract production i_closeEnvItem
top::EnvItem ::= n::String
{
  top.unparse = "close('" ++ n ++ "')";

  -- required to be defined.
  top.itemName = n;

  top.isCloseDeclaration = true;

  forwards to i_defaultEnvItem();
}

--function aspectEnvItem
--Decorated EnvItem ::= n::String stmts::[Decorated StmtRep]
--{
--  return decorate i_aspectEnvItem(n, stmts) with {};
--}
--
--abstract production i_aspectEnvItem
--top::EnvItem ::= n::String stmts::[Decorated StmtRep]
--{
--  top.itemName = n;
--
--  top.stmtRepList = stmts;
--
--  top.isAspectDeclaration = true;
--
--  forwards to i_defaultEnvItem();
--}

function productionAttributesEnvItem
Decorated EnvItem ::= n::String d::Decorated Defs
{
  return decorate i_productionAttributesEnvItem(n, d) with {};
}

abstract production i_productionAttributesEnvItem
top::EnvItem ::= n::String d::Decorated Defs
{
  top.unparse = "pattrs('" ++ n ++ "', " ++ unparseItems(toItems(d)) ++ ")";

  top.itemName = n;

  top.attributes = d;

  top.isProductionAttributesDeclaration = true;

  forwards to i_defaultEnvItem();
}

function defaultEnvItem
Decorated EnvItem ::=
{
  return decorate i_defaultEnvItem() with {};
}
abstract production i_defaultEnvItem
top::EnvItem ::= 
{
  top.unparse = "default";

  top.itemName = "_DEFAULT_ENV_ITEM";

  top.typerep = defaultTypeRep();
  top.namedSignature = decorate namedSignatureDefault() with {};
  top.fullName = "_DEFAULT_ENV_ITEM";
  top.decoratesName = "_DEFAULT_ENV_ITEM";
  top.attributes = emptyDefs();

  -- required to be defined.
  top.isValueDeclaration = false;
  top.isTypeDeclaration = false;
  top.isProductionDeclaration = false;
  top.isFunctionDeclaration = false;
  top.isAttributeDeclaration = false;
  top.isOccursDeclaration = false;
  top.isInheritedDeclaration = false;
  top.isSynthesizedDeclaration = false;
  top.isThisDeclaration = false;
  top.isFullNameDeclaration = false;
  top.isCloseDeclaration = false;
  top.isProductionAttributesDeclaration = false;
}

function containsItem
Boolean ::= s::String e::[Decorated EnvItem]
{
  return if null(e)
	 then false
	 else head(e).itemName == s || containsItem(s, tail(e));
}

function mapEnvItems
[Decorated EnvItem] ::= mapper::EnvMap e::[Decorated EnvItem]
{
  mapper.inEnvItem = head(e);

  return if null(e)
	 then []
	 else cons(mapper.newEnvItem, mapEnvItems(mapper, tail(e)));
}

function filterEnvItems
[Decorated EnvItem] ::= fil::EnvFilter e::[Decorated EnvItem]
{
  fil.inEnvItem = head(e);

  local attribute recurse :: [Decorated EnvItem];
  recurse = filterEnvItems(fil, tail(e));

  return if null(e)
	 then []  
	 else if fil.keep
	      then cons(head(e), recurse)
         else recurse;
}

function unparseItems
String ::= s::[Decorated EnvItem]{
  return "[" ++ unparseItemsHelp(s) ++ "]";
}

function unparseItemsHelp
String ::= s::[Decorated EnvItem]{
  return if null(s) then "" else (head(s).unparse ++ if null(tail(s)) then "" else (", " ++ unparseItemsHelp(tail(s))));
}
