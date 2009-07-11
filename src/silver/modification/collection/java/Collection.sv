grammar silver:modification:collection:java;
import silver:modification:collection;

import silver:definition:core;
import silver:definition:env;

import silver:translation:java:core;
import silver:translation:java:env;

import silver:extension:list;

synthesized attribute frontTrans :: String;
synthesized attribute midTrans :: String;
synthesized attribute endTrans :: String;
inherited attribute inType :: Decorated TypeRep;

attribute frontTrans, midTrans, endTrans, inType occurs on Operation;

aspect production nameOperation
top::Operation ::= s::String{
  top.frontTrans = "new " ++ makeClassName(s) ++"(";
  top.midTrans = ", ";
  top.endTrans = ")";
}

aspect production plusPlusOperation
top::Operation ::= {
  top.frontTrans = if top.inType.isString then "" else if top.inType.isList then "new core.Pappend_AnyTypeList(" else error("Not Implemented");
  top.midTrans = if top.inType.isString then ".append(" else if top.inType.isList then ", " else error("Not Implemented");
  top.endTrans = if top.inType.isString then ")" else if top.inType.isList then ").doReturn()" else error("Not Implemented");
}

aspect production noOperation
top::Operation ::= {
  top.frontTrans = "BOOM(";
  top.midTrans = ", ";
  top.endTrans = ")";
}

aspect production collectionAttributeDclProd
top::ProductionAttributeDcl ::= 'production' 'attribute' a::Name '::' te::Type 'with' q::NameOrBOperator ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  local attribute o :: Operation;
  o = q.operation;
  o.inType = te.typerep;

  top.setupInh = 
	"		" ++ className ++ ".localAttributes.put(\"" ++ fName ++ "\", new common.CollectionAttribute(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++ 
	"				" ++ te.typerep.transType ++ " result = (" ++ te.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
	"				for(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
	"					result = " ++ o.frontTrans ++ "result" ++ o.midTrans ++ "(" ++ te.typerep.transType ++ ")this.getPieces().get(i).eval(context)" ++ o.endTrans ++ ";\n" ++ 
	"				}\n" ++ 
	"				return result;\n" ++ 
	"			}\n" ++ 
	"		});\n" ++ 
        if !te.typerep.isNonTerminal then  "" else
		 "		" ++ className ++ ".inheritedAttributes.put(\"" ++ fName ++ "\", " ++ "new java.util.HashMap<String, common.Lazy>());\n";

  top.translation = "";
}

aspect production attrContains
top::AttributeDef ::= lhs::LHSExpr '<-' e::Expr ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.setupInh = "";

  top.translation = if lhs.isLocalDcl then  
	"		((common.CollectionAttribute)" ++ className ++ ".localAttributes.get(\"" ++ lhs.nodeName ++ "\")).addPiece(new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++ 
 	"		});\n"
        else if lhs.isLocal then 
	"		((common.CollectionAttribute)" ++ className ++ ".inheritedAttributes.get(\"" ++ lhs.nodeName ++ "\").get(\"" ++ lhs.attrName ++ "\")).addPiece(new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++ 
 	"		});\n"
        else if lhs.isChild then 
	"		((common.CollectionAttribute)" ++ className ++ ".inheritedAttributes.get(" ++ className ++ ".i_" ++ lhs.nodeName ++ ").get(\"" ++ lhs.attrName ++ "\")).addPiece(new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++ 
 	"		});\n"
	else
	"		((common.CollectionAttribute)" ++ className ++ ".synthesizedAttributes.get(\"" ++ lhs.attrName ++ "\")).addPiece(new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++ 
 	"		});\n";

}

aspect production attrContainsBase
top::AttributeDef ::= lhs::LHSExpr ':=' e::Expr ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  local attribute o :: Operation;
  o = lhs.typerep.operation;
  o.inType = lhs.typerep;

  top.setupInh = if lhs.isLocalDcl then  ""
                 else if lhs.isLocal then 
	"		" ++ className ++ ".inheritedAttributes.get(\"" ++ lhs.nodeName ++ "\").put(\"" ++ lhs.attrName ++ "\",  new common.CollectionAttribute(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++ 
	"				" ++ lhs.typerep.transType ++ " result = (" ++ lhs.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
	"				for(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
	"					result = " ++ o.frontTrans ++ "result" ++ o.midTrans ++ "(" ++ lhs.typerep.transType ++ ")this.getPieces().get(i).eval(context)" ++ o.endTrans ++ ";\n" ++ 
	"				}\n" ++ 
	"				return result;\n" ++ 
	"			}\n" ++ 
	"		});\n"
        	else if lhs.isChild then 
	"		" ++ className ++ ".inheritedAttributes.get(" ++ className ++ ".i_" ++ lhs.nodeName ++ ").put(\"" ++ lhs.attrName ++ "\",  new common.CollectionAttribute(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++ 
	"				" ++ lhs.typerep.transType ++ " result = (" ++ lhs.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
	"				for(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
	"					result = " ++ o.frontTrans ++ "result" ++ o.midTrans ++ "(" ++ lhs.typerep.transType ++ ")this.getPieces().get(i).eval(context)" ++ o.endTrans ++ ";\n" ++ 
	"				}\n" ++ 
	"				return result;\n" ++ 
	"			}\n" ++ 
	"		});\n"
	else
	"		" ++ className ++ ".synthesizedAttributes.put(\"" ++ lhs.attrName ++ "\",  new common.CollectionAttribute(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++ 
	"				" ++ lhs.typerep.transType ++ " result = (" ++ lhs.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
	"				for(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
	"					result = " ++ o.frontTrans ++ "result" ++ o.midTrans ++ "(" ++ lhs.typerep.transType ++ ")this.getPieces().get(i).eval(context)" ++ o.endTrans ++ ";\n" ++ 
	"				}\n" ++ 
	"				return result;\n" ++ 
	"			}\n" ++ 
	"		});\n";


  top.translation = if lhs.isLocalDcl then  
	"		" ++ "((common.CollectionAttribute)" ++ className ++ ".localAttributes.get(\"" ++ lhs.nodeName ++ "\")).setBase(new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++ 
 	"		});\n"
	else if lhs.isLocal then
	"		" ++ "((common.CollectionAttribute)" ++ className ++ ".inheritedAttributes.get(\"" ++ lhs.nodeName ++ "\").get(\"" ++ lhs.attrName ++ "\")).setBase(new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++ 
 	"		});\n"
	else if lhs.isChild then
	"		" ++ "((common.CollectionAttribute)" ++ className ++ ".inheritedAttributes.get(" ++ className ++ ".i_" ++ lhs.nodeName ++ ").get(\"" ++ lhs.attrName ++ "\")).setBase(new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++ 
 	"		});\n"
	else 
	"		" ++ "((common.CollectionAttribute)" ++ className ++ ".synthesizedAttributes.get(\"" ++ lhs.attrName ++ "\")).setBase(new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++ 
 	"		});\n";
}
