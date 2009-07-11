grammar silver:definition:env;
import silver:util;

closed nonterminal Defs with typeList, valueList, attrList, nameList, restList, productionList, synthesizedList, occursList, inheritedList, size, unparse;

synthesized attribute typeList :: [Decorated EnvItem];
synthesized attribute valueList :: [Decorated EnvItem];
synthesized attribute nameList :: [Decorated EnvItem];
synthesized attribute attrList :: [Decorated EnvItem];
synthesized attribute productionList :: [Decorated EnvItem];
synthesized attribute restList :: [Decorated EnvItem];

synthesized attribute synthesizedList :: [Decorated EnvItem];
synthesized attribute inheritedList :: [Decorated EnvItem];
synthesized attribute occursList :: [Decorated EnvItem];
synthesized attribute size :: Integer;

----------------------------------------------------------------------------------------------------
--Defsironment creation functions--------------------------------------------------------------------
----------------------------------------------------------------------------------------------------
abstract production i_emptyDefs 
top::Defs ::= 
{
  top.unparse = "[]";

  top.typeList =  [::Decorated EnvItem];
  top.valueList = [::Decorated EnvItem];
  top.nameList =  [::Decorated EnvItem];
  top.attrList =  [::Decorated EnvItem];
  top.productionList = [::Decorated EnvItem];

  top.synthesizedList =  [::Decorated EnvItem];
  top.inheritedList =  [::Decorated EnvItem];
  top.occursList =  [::Decorated EnvItem];

  top.restList =  [::Decorated EnvItem];
  top.size = 0;
}

abstract production i_consDefs 
top::Defs ::= i::Decorated EnvItem e::Decorated Defs
{
  top.size = e.size + 1;

  local attribute index :: Integer;
  index = if      i.isTypeDeclaration then 0
	  else if i.isValueDeclaration then 1
	  else if i.isFullNameDeclaration then 2
	  else if i.isAttributeDeclaration then 3
	  else if i.isProductionDeclaration || i.isFunctionDeclaration then 4
	  else if i.isSynthesizedDeclaration then 5
	  else if i.isInheritedDeclaration then 6
	  else if i.isOccursDeclaration then 7
	  else 8;

  top.typeList =  if index == 0 then cons(i,e.typeList)  else e.typeList;
  top.valueList = if index == 1 then cons(i, e.valueList) else e.valueList;
  top.nameList =  if index == 2 then cons(i, e.nameList)  else e.nameList;
  top.attrList =  if index == 3 then cons(i, e.attrList)  else e.attrList;
  top.productionList =  if index == 4 then cons(i, e.productionList)  else e.productionList;

  top.synthesizedList =  if index == 5 then cons(i, e.synthesizedList)  else e.synthesizedList;
  top.inheritedList =  if index == 6 then cons(i, e.inheritedList)  else e.inheritedList;
  top.occursList =  if index == 7 then cons(i, e.occursList)  else e.occursList;

  top.restList =  if index == 8 then cons(i, e.restList)  else e.restList;

  top.unparse = unparseItems(top.typeList ++ 
				top.valueList ++ 
				top.nameList ++ 
				top.attrList ++ 
				top.productionList ++ 
				top.synthesizedList ++ 
				top.inheritedList ++ 
				top.occursList ++ 
				top.restList);

  forwards to i_emptyDefs();
}

abstract production i_appendDefs 
top::Defs ::= e1::Decorated Defs e2::Decorated Defs
{
  top.size = e1.size + e2.size;
  top.typeList =  e1.typeList ++ e2.typeList;
  top.valueList = e1.valueList ++ e2.valueList;
  top.nameList =  e1.nameList ++ e2.nameList;
  top.attrList =  e1.attrList ++ e2.attrList;
  top.productionList =  e1.productionList ++ e2.productionList;

  top.synthesizedList =  e1.synthesizedList ++ e2.synthesizedList;
  top.inheritedList =  e1.inheritedList ++ e2.inheritedList;
  top.occursList =  e1.occursList ++ e2.occursList;

  top.restList =  e1.restList ++ e2.restList;

  top.unparse = unparseItems(top.typeList ++ 
				top.valueList ++ 
				top.nameList ++ 
				top.attrList ++ 
				top.productionList ++ 
				top.synthesizedList ++ 
				top.inheritedList ++ 
				top.occursList ++ 
				top.restList);
  forwards to i_emptyDefs();
}

--function toDefs
--Decorated Defs ::= e::Decorated Env {
--  return decorate i_toDefs(e) with {};
--}
--
--abstract production i_toDefs
--Defs ::= e::Decorated Env {
--
--  top.typeList =  flattenScope(e.typeTree);
--  top.valueList = flattenScope(e.valueTree);
--  top.nameList =  flattenScope(e.nameTree);
--  top.attrList =  flattenScope(e.attrTree);
--  top.productionList = flattenScope(e.productionTree);
--
--  top.synthesizedList =  flattenScope(e.synthesizedTree);
--  top.inheritedList =  flattenScope(e.inheritedTree);
--  top.occursList =  flattenScope(e.occursTree);
--
--  top.restList =  flattenScope(e.restTree);
--}

function emptyDefs
Decorated Defs ::=
{
  return decorate i_emptyDefs() with {};
}

function newDefs 
Decorated Defs ::= i::Decorated EnvItem
{
  return decorate i_consDefs(i, emptyDefs()) with {};
}

function consDefs 
Decorated Defs ::= i::Decorated EnvItem e::Decorated Defs
{
  return decorate i_consDefs(i, e) with {};
}

function appendDefs
Decorated Defs ::= e1::Decorated Defs e2::Decorated Defs
{
  return decorate i_appendDefs(e1, e2) with {};
}


----------------------------------------------------------------------------------------------------
--Defs Helper Functions------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

-- This adds  n :: T entries
function addValueDcl
Decorated Defs ::= n::String value::Decorated TypeRep e::Decorated Defs
{
  return consDefs(valueEnvItem(n, value), e);
}

-- This adds  n |-> T entries
function addTypeDcl
Decorated Defs ::= n::String type::Decorated TypeRep e::Decorated Defs
{
  return consDefs(typeEnvItem(n, type), e);
}

function addProductionDcl
Decorated Defs ::= ns::Decorated NamedSignature e::Decorated Defs
{
  return consDefs(productionEnvItem(ns), e);
}

function addFunctionDcl
Decorated Defs ::= ns::Decorated NamedSignature e::Decorated Defs
{
  return consDefs(functionEnvItem(ns), e);
}

-- This adds  attr n :: T entries
function addAttributeDcl
Decorated Defs ::= n::String type::Decorated TypeRep e::Decorated Defs
{
  return consDefs(attributeEnvItem(n, type), e);
}

-- This adds  n @ T entries
function addOccursDcl
Decorated Defs ::= n::String dn::String e::Decorated Defs
{
  return consDefs(occursEnvItem(n, dn), e);
}

-- This adds  inh n  entries
function addInheritedDcl
Decorated Defs ::= n::String e::Decorated Defs
{
  return consDefs(inheritedEnvItem(n), e);
}

-- This adds  syn n  entries
function addSynthesizedDcl
Decorated Defs ::= n::String e::Decorated Defs
{
  return consDefs(synthesizedEnvItem(n), e);
}

function addThisDcl
Decorated Defs ::= n::String e::Decorated Defs
{
  return consDefs(thisEnvItem(n), e);
}

function addFullNameDcl
Decorated Defs ::= n::String fname::String e::Decorated Defs
{
  return consDefs(fullNameEnvItem(n, fname), e);
}

function addCloseDcl
Decorated Defs ::= n::String e::Decorated Defs
{
  return consDefs(closeEnvItem(n), e);
}

--function addAspectDcl
--Decorated Defs ::= n::String stmts::[StmtRep] e::Decorated Defs
--{
--  return consDefs(aspectEnvItem(n, stmts''), e);
--}

function addProductionAttributesDcl
Decorated Defs ::= n::String d::Decorated Defs e::Decorated Defs
{
  return consDefs(productionAttributesEnvItem(n, d), e);
}

----------------------------------------------------------------------------------------------------
--Building the default Defs--------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function makeDefaultDefs
Decorated Defs ::= 
{
  return      addFullNameDcl("lexeme", "lexeme", 
  	      addSynthesizedDcl("lexeme", 
	      addAttributeDcl("lexeme",stringTypeRep(),
	      addFullNameDcl("line", "line", 
	      addSynthesizedDcl("line", 
	      addAttributeDcl("line", integerTypeRep(),
	      addFullNameDcl("column", "column", 
	      addSynthesizedDcl("column", 
	      addAttributeDcl("column", integerTypeRep(),
	      emptyDefs())))))))));
}

----------------------------------------------------------------------------------------------------
--Generic environment query functions---------------------------------------------------------------
----------------------------------------------------------------------------------------------------

--function printDefs
--String ::= e::Decorated Defs
--{
--  return "TYPES\n" ++
--	 printEnvItems(e.typeList) ++ "\nVALUES\n" ++
--	 printEnvItems(e.valueList) ++ "\nNAMES\n" ++
--	 printEnvItems(e.nameList) ++ "\nATTRS\n" ++
--	 printEnvItems(e.attrList) ++ "\nREST\n" ++
--	 printEnvItems(e.restList) ++ "\n\n";
--}

function mapDefs
Decorated Defs ::= mapper::EnvMap e::Decorated Defs
{
  return decorate i_mapDefs(mapper'', e) with {};
}

abstract production i_mapDefs
top::Defs ::= mapper::EnvMap e::Decorated Defs
{
  top.typeList =  mapEnvItems(mapper, e.typeList);
  top.valueList = mapEnvItems(mapper, e.valueList);
  top.nameList =  mapEnvItems(mapper, e.nameList);
  top.attrList =  mapEnvItems(mapper, e.attrList);
  top.productionList =  mapEnvItems(mapper, e.productionList);

  top.synthesizedList =  mapEnvItems(mapper, e.synthesizedList);
  top.inheritedList =  mapEnvItems(mapper, e.inheritedList);
  top.occursList =  mapEnvItems(mapper, e.occursList);

  top.restList =  mapEnvItems(mapper, e.restList);

  top.unparse = unparseItems(top.typeList ++ 
				top.valueList ++ 
				top.nameList ++ 
				top.attrList ++ 
				top.productionList ++ 
				top.synthesizedList ++ 
				top.inheritedList ++ 
				top.occursList ++ 
				top.restList);
}

function filterDefs
Decorated Defs ::= fil::EnvFilter e::Decorated Defs
{
  return decorate i_filterDefs(fil'', e) with {};
}

abstract production i_filterDefs
top::Defs ::= fil::EnvFilter e::Decorated Defs
{
  top.typeList =  filterEnvItems(fil, e.typeList);
  top.valueList = filterEnvItems(fil, e.valueList);
  top.nameList =  filterEnvItems(fil, e.nameList);
  top.attrList =  filterEnvItems(fil, e.attrList);
  top.productionList =  filterEnvItems(fil, e.productionList);

  top.synthesizedList =  filterEnvItems(fil, e.synthesizedList);
  top.inheritedList =  filterEnvItems(fil, e.inheritedList);
  top.occursList =  filterEnvItems(fil, e.occursList);

  top.restList =  filterEnvItems(fil, e.restList);

  top.unparse = unparseItems(top.typeList ++ 
				top.valueList ++ 
				top.nameList ++ 
				top.attrList ++ 
				top.productionList ++ 
				top.synthesizedList ++ 
				top.inheritedList ++ 
				top.occursList ++ 
				top.restList);
}

abstract production removeFilter
top::EnvFilter ::= r::[String] fr::[String]
{
  local attribute item :: Decorated EnvItem;
  item = top.inEnvItem;

  top.keep =    (item.isFullNameDeclaration && !contains(item.itemName, r))
	||	(!item.isFullNameDeclaration && !contains(item.itemName, fr));


}

abstract production keepFilter
top::EnvFilter ::= k::[String] fk::[String]
{
  local attribute item :: Decorated EnvItem;
  item = top.inEnvItem;

  top.keep = 	(item.isFullNameDeclaration && contains(item.itemName, k))
	||	(!item.isFullNameDeclaration && contains(item.itemName, fk));

}

abstract production renameMap
top::EnvMap ::= old::String n::String
{
  local attribute item :: Decorated EnvItem;
  item = top.inEnvItem;

  top.newEnvItem = if item.isFullNameDeclaration && item.itemName == old
		   then fullNameEnvItem(n, item.fullName)
		   else item;
}

abstract production prependMap
top::EnvMap ::= pref::String
{
  local attribute item :: Decorated EnvItem;
  item = top.inEnvItem;

  top.newEnvItem = if item.isFullNameDeclaration
		   then fullNameEnvItem(pref ++ ":" ++ item.itemName, item.fullName)
		   else item;
}
----------------------------------------------------------------------------------------------------
--General query functions---------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function toItems
[Decorated EnvItem] ::= e::Decorated Defs
{
  return e.typeList ++ e.valueList ++ e.attrList ++ e.productionList ++ e.nameList ++ e.synthesizedList ++ e.occursList ++ e.restList ++ e.inheritedList;
}


