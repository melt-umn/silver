grammar silver:modification:collection:java;
import silver:modification:collection;

import silver:util;

import silver:definition:core;
import silver:definition:env;

import silver:translation:java:core;
import silver:translation:java:type;
import silver:definition:type;
import silver:definition:type:syntax;

{-
  The initialization order is a bit scattered. There a several problems.
  
  ONE: Grammars can have cyclic dependencies. As a result,
  we can never rely on the declaration, or the base (:=), appearing before
  a contribution (<-).

  TWO: Production bodies are unordered. So even within one block of code,
  it's quite possible for an assignment to preceed a declaration.
  Or a contribution to preceed a base.
  
  For LOCALS, it's okay to create the CA object at declaration with setupInh.
  The array was created a couple of lines up.
  
  For SYN, it might be okay to? I'm not sure. Playing it safe for now.
  
  For INH, you can't for sure use setupInh. You might be defining an inherited
  attribute on a local that hasn't had it's inherited array created yet.
  e.g.  
    x.inh := ...
    local attribute x :: ....
    N.B. that's an ordinary local, we're talking about inherited collections here,
      not local collections.
-}

synthesized attribute frontTrans :: String;
synthesized attribute midTrans :: String;
synthesized attribute endTrans :: String;

attribute frontTrans, midTrans, endTrans occurs on Operation;

aspect production functionOperation
top::Operation ::= s::String
{
  top.frontTrans = "" ++ makeClassName(s) ++".invoke(";
  top.midTrans = ", ";
  top.endTrans = ")";
}
aspect production productionOperation
top::Operation ::= s::String
{
  top.frontTrans = "new " ++ makeClassName(s) ++"(";
  top.midTrans = ", ";
  top.endTrans = ")";
}
aspect production plusPlusOperationString
top::Operation ::= 
{
  top.frontTrans = "new common.StringCatter(";
  top.midTrans = ", ";
  top.endTrans = ")";
}
aspect production plusPlusOperationList
top::Operation ::= 
{
  top.frontTrans = "common.AppendCell.append(";
  top.midTrans = ", ";
  top.endTrans = ")";
}
aspect production borOperation
top::Operation ::= 
{
  top.frontTrans = "(";
  top.midTrans = " || ";
  top.endTrans = ")";
}
aspect production bandOperation
top::Operation ::= 
{
  top.frontTrans = "(";
  top.midTrans = " && ";
  top.endTrans = ")";
}

--- Declarations ---------------------------------------------------------------

aspect production collectionAttributeDclProd
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type 'with' q::NameOrBOperator ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  local attribute o :: Operation;
  o = q.operation;

  local attribute ugh_dcl_hack :: DclInfo;
  ugh_dcl_hack = head(getValueDclAll(fName, top.env)); -- TODO

  -- Unlike synthesized and inherited attributes, locals can cheat because we know exactly
  -- when the array we're indexing into was created: a couple of statements up from
  -- exactly here.
  
  -- So we'll create the collection attribute object here, and not worry.

  top.setupInh <-
        "\t\t" ++ className ++ ".localAttributes[" ++ ugh_dcl_hack.attrOccursIndex ++ "] = new common.CollectionAttribute(){\n" ++ 
        "\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++ 
        "\t\t\t\t" ++ te.typerep.transType ++ " result = (" ++ te.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
        "\t\t\t\tfor(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
        "\t\t\t\t\tresult = " ++ o.frontTrans ++ "result" ++ o.midTrans ++ "(" ++ te.typerep.transType ++ ")this.getPieces().get(i).eval(context)" ++ o.endTrans ++ ";\n" ++ 
        "\t\t\t\t}\n" ++ 
        "\t\t\t\treturn result;\n" ++ 
        "\t\t\t}\n" ++ 
        "\t\t};\n";
}

aspect production collectionAttributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeList '::' te::Type 'with' q::NameOrBOperator ';'
{
  local attribute className :: String;
  className = "CA" ++ a.name;

  local attribute o :: Operation;
  o = q.operation;

  top.genFiles := [pair(className ++ ".java",
                
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public class " ++ className ++ " extends common.CollectionAttribute {\n\n" ++

"\tpublic " ++ className ++ "(final int index) {\n" ++
"\t\tsuper(index);\n" ++
"\t}\n\n" ++

"\tpublic Object eval(common.DecoratedNode context) {\n" ++ 
"\t\t" ++ te.typerep.transType ++ " result = (" ++ te.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
"\t\tfor(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
"\t\t\tresult = " ++ o.frontTrans ++ "result" ++ o.midTrans ++ "(" ++ te.typerep.transType ++ ")this.getPieces().get(i).eval(context)" ++ o.endTrans ++ ";\n" ++ 
"\t\t}\n" ++ 
"\t\treturn result;\n" ++ 
"\t}\n\n" ++ 

"}\n")];
}

aspect production collectionAttributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeList '::' te::Type 'with' q::NameOrBOperator ';'
{
  local attribute className :: String;
  className = "CA" ++ a.name;

  local attribute o :: Operation;
  o = q.operation;

  top.genFiles := [pair(className ++ ".java",
                
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public class " ++ className ++ " extends common.CollectionAttribute {\n\n" ++

"\tpublic " ++ className ++ "() {\n" ++
"\t\tsuper();\n" ++
"\t}\n\n" ++

"\tpublic Object eval(common.DecoratedNode context) {\n" ++ 
"\t\t" ++ te.typerep.transType ++ " result = (" ++ te.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
"\t\tfor(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
"\t\t\tresult = " ++ o.frontTrans ++ "result" ++ o.midTrans ++ "(" ++ te.typerep.transType ++ ")this.getPieces().get(i).eval(context)" ++ o.endTrans ++ ";\n" ++ 
"\t\t}\n" ++ 
"\t\treturn result;\n" ++ 
"\t}\n\n" ++ 

"}\n")];
}

--- Use semantics translation --------------------------------------------------

---------- LOCALS ---
aspect production baseCollectionValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  local className :: String = makeClassName(top.signature.fullName);

  -- for locals, the CA object was created already
  top.translation =
        "\t\t// " ++ val.pp ++ " := " ++ e.pp ++ "\n" ++
        "\t\t((common.CollectionAttribute)" ++ className ++ ".localAttributes[" ++ val.lookupValue.dcl.attrOccursIndex ++ "]).setBase(" ++ wrapLazy(e) ++ ");\n";
}
aspect production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  -- for locals, the CA object was created already
  top.translation = 
        "\t\t// " ++ val.pp ++ " <- " ++ e.pp ++ "\n" ++
        "\t\t((common.CollectionAttribute)" ++ className ++ ".localAttributes[" ++ val.lookupValue.dcl.attrOccursIndex ++ "]).addPiece(" ++ wrapLazy(e) ++ ");\n";
}

---------- SYNTHESIZED ----
aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  {- := -} e::Expr
{
  top.translation =
        "\t\t// " ++ dl.pp ++ "." ++ attr.pp ++ " := " ++ e.pp ++ "\n" ++
        "\t\tif(" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] == null)\n" ++
        "\t\t\t" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] = new " ++ makeCAClassName(attr.attrDcl.fullName) ++"(" ++ attr.dcl.attrOccursIndex ++ ");\n" ++
        "\t\t((common.CollectionAttribute)" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "]).setBase(" ++ wrapLazy(e) ++ ");\n";
}
aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  {- <- -} e::Expr
{
  top.translation = 
	"\t\t// " ++ dl.pp ++ "." ++ attr.pp ++ " <- " ++ e.pp ++ "\n" ++
        "\t\tif(" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] == null)\n" ++
        "\t\t\t" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] = new " ++ makeCAClassName(attr.attrDcl.fullName) ++"(" ++ attr.dcl.attrOccursIndex ++ ");\n" ++
        "\t\t((common.CollectionAttribute)" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "]).addPiece(" ++ wrapLazy(e) ++ ");\n";
}

---------- INHERITED ----
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  {- := -} e::Expr
{
  top.translation =
        "\t\t// " ++ dl.pp ++ "." ++ attr.pp ++ " := " ++ e.pp ++ "\n" ++
        "\t\tif(" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] == null)\n" ++
        "\t\t\t" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] = new " ++ makeCAClassName(attr.attrDcl.fullName) ++ "();\n" ++
        "\t\t((common.CollectionAttribute)" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "]).setBase(" ++ wrapLazy(e) ++ ");\n";
}
aspect production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  {- <- -} e::Expr
{
  top.translation = 
	"\t\t// " ++ dl.pp ++ "." ++ attr.pp ++ " <- " ++ e.pp ++ "\n" ++
        "\t\tif(" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] == null)\n" ++
        "\t\t\t" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] = new " ++ makeCAClassName(attr.attrDcl.fullName) ++ "();\n" ++
        "\t\t((common.CollectionAttribute)" ++ dl.translation ++"[" ++ attr.dcl.attrOccursIndex ++ "]).addPiece(" ++ wrapLazy(e) ++ ");\n";
}


function makeCAClassName
String ::= s::String
{
  return substituteLast(".", ".CA", makeName(s));
}

