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
	"\t\t" ++ className ++ ".localAttributes.put(\"" ++ fName ++ "\", new common.CollectionAttribute(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++ 
	"\t\t\t\t" ++ te.typerep.transType ++ " result = (" ++ te.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
	"\t\t\t\tfor(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
	"\t\t\t\t\tresult = " ++ o.frontTrans ++ "result" ++ o.midTrans ++ "(" ++ te.typerep.transType ++ ")this.getPieces().get(i).eval(context)" ++ o.endTrans ++ ";\n" ++ 
	"\t\t\t\t}\n" ++ 
	"\t\t\t\treturn result;\n" ++ 
	"\t\t\t}\n" ++ 
	"\t\t});\n" ++ 
        if !te.typerep.isNonTerminal then  "" else
		 "\t\t" ++ className ++ ".inheritedAttributes.put(\"" ++ fName ++ "\", " ++ "new java.util.HashMap<String, common.Lazy>());\n";

  top.translation = "";
}

aspect production attrContains
top::AttributeDef ::= lhs::LHSExpr '<-' e::Expr ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.setupInh = "";

  top.translation = if lhs.isLocalDcl then  
	"\t\t((common.CollectionAttribute)" ++ className ++ ".localAttributes.get(\"" ++ lhs.nodeName ++ "\")).addPiece(new common.Lazy(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++
	"\t\t\t\treturn " ++ e.translation ++ ";\n" ++
	"\t\t\t}\n" ++ 
 	"\t\t});\n"
        else if lhs.isLocal then 
	"\t\t((common.CollectionAttribute)" ++ className ++ ".inheritedAttributes.get(\"" ++ lhs.nodeName ++ "\").get(\"" ++ lhs.attrName ++ "\")).addPiece(new common.Lazy(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++
	"\t\t\t\treturn " ++ e.translation ++ ";\n" ++
	"\t\t\t}\n" ++ 
 	"\t\t});\n"
        else if lhs.isChild then 
	"\t\t((common.CollectionAttribute)" ++ className ++ ".inheritedAttributes.get(" ++ className ++ ".i_" ++ lhs.nodeName ++ ").get(\"" ++ lhs.attrName ++ "\")).addPiece(new common.Lazy(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++
	"\t\t\t\treturn " ++ e.translation ++ ";\n" ++
	"\t\t\t}\n" ++ 
 	"\t\t});\n"
	else
	"\t\t((common.CollectionAttribute)" ++ className ++ ".synthesizedAttributes.get(\"" ++ lhs.attrName ++ "\")).addPiece(new common.Lazy(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++
	"\t\t\t\treturn " ++ e.translation ++ ";\n" ++
	"\t\t\t}\n" ++ 
 	"\t\t});\n";

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
	"\t\t" ++ className ++ ".inheritedAttributes.get(\"" ++ lhs.nodeName ++ "\").put(\"" ++ lhs.attrName ++ "\",  new common.CollectionAttribute(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++ 
	"\t\t\t\t" ++ lhs.typerep.transType ++ " result = (" ++ lhs.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
	"\t\t\t\tfor(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
	"\t\t\t\t\tresult = " ++ o.frontTrans ++ "result" ++ o.midTrans ++ "(" ++ lhs.typerep.transType ++ ")this.getPieces().get(i).eval(context)" ++ o.endTrans ++ ";\n" ++ 
	"\t\t\t\t}\n" ++ 
	"\t\t\t\treturn result;\n" ++ 
	"\t\t\t}\n" ++ 
	"\t\t});\n"
        	else if lhs.isChild then 
	"\t\t" ++ className ++ ".inheritedAttributes.get(" ++ className ++ ".i_" ++ lhs.nodeName ++ ").put(\"" ++ lhs.attrName ++ "\",  new common.CollectionAttribute(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++ 
	"\t\t\t\t" ++ lhs.typerep.transType ++ " result = (" ++ lhs.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
	"\t\t\t\tfor(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
	"\t\t\t\t\tresult = " ++ o.frontTrans ++ "result" ++ o.midTrans ++ "(" ++ lhs.typerep.transType ++ ")this.getPieces().get(i).eval(context)" ++ o.endTrans ++ ";\n" ++ 
	"\t\t\t\t}\n" ++ 
	"\t\t\t\treturn result;\n" ++ 
	"\t\t\t}\n" ++ 
	"\t\t});\n"
	else
	"\t\t" ++ className ++ ".synthesizedAttributes.put(\"" ++ lhs.attrName ++ "\",  new common.CollectionAttribute(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++ 
	"\t\t\t\t" ++ lhs.typerep.transType ++ " result = (" ++ lhs.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
	"\t\t\t\tfor(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
	"\t\t\t\t\tresult = " ++ o.frontTrans ++ "result" ++ o.midTrans ++ "(" ++ lhs.typerep.transType ++ ")this.getPieces().get(i).eval(context)" ++ o.endTrans ++ ";\n" ++ 
	"\t\t\t\t}\n" ++ 
	"\t\t\t\treturn result;\n" ++ 
	"\t\t\t}\n" ++ 
	"\t\t});\n";


  top.translation = if lhs.isLocalDcl then  
	"\t\t((common.CollectionAttribute)" ++ className ++ ".localAttributes.get(\"" ++ lhs.nodeName ++ "\")).setBase(new common.Lazy(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++
	"\t\t\t\treturn " ++ e.translation ++ ";\n" ++
	"\t\t\t}\n" ++ 
 	"\t\t});\n"
	else if lhs.isLocal then
	"\t\t((common.CollectionAttribute)" ++ className ++ ".inheritedAttributes.get(\"" ++ lhs.nodeName ++ "\").get(\"" ++ lhs.attrName ++ "\")).setBase(new common.Lazy(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++
	"\t\t\t\treturn " ++ e.translation ++ ";\n" ++
	"\t\t\t}\n" ++ 
 	"\t\t});\n"
	else if lhs.isChild then
	"\t\t((common.CollectionAttribute)" ++ className ++ ".inheritedAttributes.get(" ++ className ++ ".i_" ++ lhs.nodeName ++ ").get(\"" ++ lhs.attrName ++ "\")).setBase(new common.Lazy(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++
	"\t\t\t\treturn " ++ e.translation ++ ";\n" ++
	"\t\t\t}\n" ++ 
 	"\t\t});\n"
	else 
	"\t\t((common.CollectionAttribute)" ++ className ++ ".synthesizedAttributes.get(\"" ++ lhs.attrName ++ "\")).setBase(new common.Lazy(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++
	"\t\t\t\treturn " ++ e.translation ++ ";\n" ++
	"\t\t\t}\n" ++ 
 	"\t\t});\n";
}
