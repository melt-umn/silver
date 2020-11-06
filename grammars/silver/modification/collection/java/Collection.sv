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

inherited attribute leftOpTranslation::String occurs on Operation;
inherited attribute rightOpTranslation::String occurs on Operation;

attribute translation occurs on Operation;

aspect production functionOperation
top::Operation ::= e::Expr eTrans::String isRef::Boolean isFunction::Boolean
{
  top.translation =
    if isRef
    then s"${eTrans}.invoke(new Object[]{${top.leftOpTranslation}, ${top.rightOpTranslation}}, null)"
    else if isFunction
    then s"${eTrans}.invoke(${top.leftOpTranslation}, ${top.rightOpTranslation})"
    else s"new ${eTrans}(${top.leftOpTranslation}, ${top.rightOpTranslation})";
}
aspect production plusPlusOperationString
top::Operation ::= 
{
  top.translation = s"new common.StringCatter(${top.leftOpTranslation}, ${top.rightOpTranslation})";
}
aspect production plusPlusOperationList
top::Operation ::= 
{
  top.translation = s"common.AppendCell.append(${top.leftOpTranslation}, ${top.rightOpTranslation})";
}
aspect production borOperation
top::Operation ::= 
{
  top.translation = s"(${top.leftOpTranslation} || ${top.rightOpTranslation})";
}
aspect production bandOperation
top::Operation ::= 
{
  top.translation = s"(${top.leftOpTranslation} && ${top.rightOpTranslation})";
}
aspect production addOperation
top::Operation ::= 
{
  top.translation = s"(${top.leftOpTranslation} + ${top.rightOpTranslation})";
}
aspect production mulOperation
top::Operation ::= 
{
  top.translation = s"(${top.leftOpTranslation} * ${top.rightOpTranslation})";
}

--- Declarations ---------------------------------------------------------------

aspect production collectionAttributeDclProd
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{
  local attribute ugh_dcl_hack :: DclInfo;
  ugh_dcl_hack = head(getValueDclAll(fName, top.env)); -- TODO

  -- Unlike synthesized and inherited attributes, locals can cheat because we know exactly
  -- when the array we're indexing into was created: a couple of statements up from
  -- exactly here.
  
  -- So we'll create the collection attribute object here, and not worry.

  local o :: Operation = q.operation;
  o.leftOpTranslation = "result";
  o.rightOpTranslation = s"(${te.typerep.transType})this.getPieces().get(i).eval(context)";

  top.setupInh <-
        "\t\t" ++ top.frame.className ++ ".localAttributes[" ++ ugh_dcl_hack.attrOccursIndex ++ "] = new common.CollectionAttribute(){\n" ++ 
        "\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++ 
        "\t\t\t\t" ++ te.typerep.transType ++ " result = (" ++ te.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
        "\t\t\t\tfor(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
        "\t\t\t\t\tresult = " ++ o.translation ++ ";\n" ++ 
        "\t\t\t\t}\n" ++ 
        "\t\t\t\treturn result;\n" ++ 
        "\t\t\t}\n" ++ 
        "\t\t};\n";
}

aspect production collectionAttributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{
  local attribute className :: String;
  className = "CA" ++ a.name;

  local o :: Operation = q.operation;
  o.leftOpTranslation = "result";
  o.rightOpTranslation = s"(${te.typerep.transType})this.getPieces().get(i).eval(context)";

  top.genFiles := [pair(className ++ ".java",
                
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public class " ++ className ++ " extends common.CollectionAttribute {\n\n" ++

"\tpublic " ++ className ++ "(final int index) {\n" ++
"\t\tsuper(index);\n" ++
"\t}\n\n" ++

"\tpublic Object eval(common.DecoratedNode context) {\n" ++ 
"\t\t" ++ te.typerep.transType ++ " result = (" ++ te.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
"\t\tfor(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
"\t\t\tresult = " ++ o.translation ++ ";\n" ++ 
"\t\t}\n" ++ 
"\t\treturn result;\n" ++ 
"\t}\n\n" ++ 

"}\n")];
}

aspect production collectionAttributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{
  local attribute className :: String;
  className = "CA" ++ a.name;

  local o :: Operation = q.operation;
  o.leftOpTranslation = "result";
  o.rightOpTranslation = s"(${te.typerep.transType})this.getPieces().get(i).eval(context)";

  top.genFiles := [pair(className ++ ".java",
                
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public class " ++ className ++ " extends common.CollectionAttribute {\n\n" ++

"\tpublic " ++ className ++ "() {\n" ++
"\t\tsuper();\n" ++
"\t}\n\n" ++

"\tpublic Object eval(common.DecoratedNode context) {\n" ++ 
"\t\t" ++ te.typerep.transType ++ " result = (" ++ te.typerep.transType ++ ")this.getBase().eval(context);\n" ++ 
"\t\tfor(int i = 0; i < this.getPieces().size(); i++){\n" ++ 
"\t\t\tresult = " ++ o.translation ++ ";\n" ++ 
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
  -- for locals, the CA object was created already
  top.translation =
        "\t\t// " ++ val.unparse ++ " := " ++ e.unparse ++ "\n" ++
        "\t\t((common.CollectionAttribute)" ++ top.frame.className ++ ".localAttributes[" ++ val.lookupValue.dcl.attrOccursIndex ++ "]).setBase(" ++ wrapLazy(e) ++ ");\n";
}
aspect production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  -- for locals, the CA object was created already
  top.translation = 
        "\t\t// " ++ val.unparse ++ " <- " ++ e.unparse ++ "\n" ++
        "\t\t((common.CollectionAttribute)" ++ top.frame.className ++ ".localAttributes[" ++ val.lookupValue.dcl.attrOccursIndex ++ "]).addPiece(" ++ wrapLazy(e) ++ ");\n";
}

---------- SYNTHESIZED ----
aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  {- := -} e::Expr
{
  top.translation =
        "\t\t// " ++ dl.unparse ++ "." ++ attr.unparse ++ " := " ++ e.unparse ++ "\n" ++
        "\t\tif(" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] == null)\n" ++
        "\t\t\t" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] = new " ++ makeCAClassName(attr.attrDcl.fullName) ++"(" ++ attr.dcl.attrOccursIndex ++ ");\n" ++
        "\t\t((common.CollectionAttribute)" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "]).setBase(" ++ wrapLazy(e) ++ ");\n";
}
aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  {- <- -} e::Expr
{
  top.translation = 
	"\t\t// " ++ dl.unparse ++ "." ++ attr.unparse ++ " <- " ++ e.unparse ++ "\n" ++
        "\t\tif(" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] == null)\n" ++
        "\t\t\t" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] = new " ++ makeCAClassName(attr.attrDcl.fullName) ++"(" ++ attr.dcl.attrOccursIndex ++ ");\n" ++
        "\t\t((common.CollectionAttribute)" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "]).addPiece(" ++ wrapLazy(e) ++ ");\n";
}

---------- INHERITED ----
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  {- := -} e::Expr
{
  top.translation =
        "\t\t// " ++ dl.unparse ++ "." ++ attr.unparse ++ " := " ++ e.unparse ++ "\n" ++
        "\t\tif(" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] == null)\n" ++
        "\t\t\t" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] = new " ++ makeCAClassName(attr.attrDcl.fullName) ++ "();\n" ++
        "\t\t((common.CollectionAttribute)" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "]).setBase(" ++ wrapLazy(e) ++ ");\n";
}
aspect production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  {- <- -} e::Expr
{
  top.translation = 
	"\t\t// " ++ dl.unparse ++ "." ++ attr.unparse ++ " <- " ++ e.unparse ++ "\n" ++
        "\t\tif(" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] == null)\n" ++
        "\t\t\t" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] = new " ++ makeCAClassName(attr.attrDcl.fullName) ++ "();\n" ++
        "\t\t((common.CollectionAttribute)" ++ dl.translation ++"[" ++ attr.dcl.attrOccursIndex ++ "]).addPiece(" ++ wrapLazy(e) ++ ");\n";
}


function makeCAClassName
String ::= s::String
{
  return substituteLast(".", ".CA", makeName(s));
}

