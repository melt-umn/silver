grammar silver:compiler:modification:collection:java;
import silver:compiler:modification:collection;


import silver:compiler:definition:core;
import silver:compiler:definition:env;

import silver:compiler:translation:java:core;
import silver:compiler:translation:java:type;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;

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

function opTranslation
String ::= o::Operation lTrans::String rTrans::String
{
  return
    case o of
    | functionOperation(_, eTrans, trackConstruction) ->
      s"${eTrans}.invoke(context.originCtx, new Object[]{${lTrans}, ${rTrans}}, null)"
    | plusPlusOperationString() -> s"new common.StringCatter(${lTrans}, ${rTrans})"
    | plusPlusOperationList() -> s"common.AppendCell.append(${lTrans}, ${rTrans})"
    | borOperation() -> s"(${lTrans} || ${rTrans})"
    | bandOperation() -> s"(${lTrans} && ${rTrans})"
    | addOperation() -> s"(${lTrans} + ${rTrans})"
    | mulOperation() -> s"(${lTrans} * ${rTrans})"
    end;
}

--- Declarations ---------------------------------------------------------------

aspect production collectionAttributeDclProd
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{
  local attribute ugh_dcl_hack :: ValueDclInfo;
  ugh_dcl_hack = head(getValueDclAll(fName, top.env)); -- TODO

  -- Unlike synthesized and inherited attributes, locals can cheat because we know exactly
  -- when the array we're indexing into was created: a couple of statements up from
  -- exactly here.
  
  -- So we'll create the collection attribute object here, and not worry.

  local result::String = opTranslation(q.operation,
    s"(${te.typerep.transType})result",
    s"(${te.typerep.transType})this.getPieces().get(i).eval(context)");

  top.setupInh <- s"""
    ${top.frame.className}.localAttributes[${ugh_dcl_hack.attrOccursIndex}] = new common.CollectionAttribute() {
  @SuppressWarnings("unchecked")
      public Object eval(common.DecoratedNode context) {
        common.OriginContext originCtx = context.originCtx;
        common.Lazy base = this.getBase();
        if (base != null) {
          ${te.typerep.transType} result = (${te.typerep.transType})base.eval(context);
          for (int i = 0; i < this.getPieces().size(); i++) {
            result = ${result};
          }
          return result;
        } else {
          throw new common.exceptions.MissingDefinitionException("Production attribute '${a.name}' in '${top.frame.fullName}' has no base definition");
        }
      }
    };
""";
}

aspect production collectionAttributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{
  local attribute className :: String;
  className = "CA" ++ a.name;

  local result::String = opTranslation(q.operation,
    s"(${te.typerep.transType})result",
    s"(${te.typerep.transType})this.getPieces().get(i).eval(context)");

  top.genFiles := [(className ++ ".java",
s"""
package ${makeName(top.grammarName)};

public class ${className} extends common.CollectionAttribute {

  public ${className}(final int index) {
    super(index);
  }

  @SuppressWarnings("unchecked")
  public Object eval(common.DecoratedNode context) {
    common.OriginContext originCtx = context.originCtx;
    ${te.typerep.transType} result = (${te.typerep.transType})this.getBase().eval(context);
    for (int i = 0; i < this.getPieces().size(); i++) {
      result = ${result};
    }
    return result;
  }

}
""")];
}

aspect production collectionAttributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{
  local attribute className :: String;
  className = "CA" ++ a.name;

  local result::String = opTranslation(q.operation,
    s"(${te.typerep.transType})result",
    s"(${te.typerep.transType})this.getPieces().get(i).eval(context)");

  top.genFiles := [(className ++ ".java",
s"""
package ${makeName(top.grammarName)};

public class ${className} extends common.CollectionAttribute {

  public ${className}() {
    super();
  }

  @SuppressWarnings("unchecked")
  public Object eval(common.DecoratedNode context) {
    common.OriginContext originCtx = context.originCtx;
    ${te.typerep.transType} result = (${te.typerep.transType})this.getBase().eval(context);
    for (int i = 0; i < this.getPieces().size(); i++) {
      result = ${result};
    }
    return result;
  }

}
""")];
}

--- Use semantics translation --------------------------------------------------

---------- LOCALS ---
aspect production baseCollectionValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Decorated! Expr with {}
{
  -- for locals, the CA object was created already
  top.translation = s"""
    // ${val.unparse} := ${e.unparse}
    ((common.CollectionAttribute)${top.frame.className}.localAttributes[${val.lookupValue.dcl.attrOccursIndex}]).setBase(${wrapLazy(e)});
""";
}
aspect production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Decorated! Expr with {}
{
  -- for locals, the CA object was created already
  top.translation = s"""
    // ${val.unparse} <- ${e.unparse}
    ((common.CollectionAttribute)${top.frame.className}.localAttributes[${val.lookupValue.dcl.attrOccursIndex}]).addPiece(${wrapLazy(e)});
""";
}

---------- SYNTHESIZED ----
aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  {- := -} e::Decorated! Expr with {}
{
  top.translation = s"""
    // ${dl.unparse}.${attr.unparse} := ${e.unparse}
    if (${dl.translation}[${attr.attrOccursIndex}] == null)
      ${dl.translation}[${attr.attrOccursIndex}] = new ${makeCAClassName(attr.attrDcl.fullName)}(${attr.attrOccursIndex});
    ((common.CollectionAttribute)${dl.translation}[${attr.attrOccursIndex}]).setBase(${wrapLazy(e)});
""";
}
aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  {- <- -} e::Decorated! Expr with {}
{
  top.translation = s"""
    // ${dl.unparse}.${attr.unparse} <- ${e.unparse}
    if (${dl.translation}[${attr.attrOccursIndex}] == null)
      ${dl.translation}[${attr.attrOccursIndex}] = new ${makeCAClassName(attr.attrDcl.fullName)}(${attr.attrOccursIndex});
    ((common.CollectionAttribute)${dl.translation}[${attr.attrOccursIndex}]).addPiece(${wrapLazy(e)});
""";
}

---------- INHERITED ----
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  {- := -} e::Decorated! Expr with {}
{
  top.translation = s"""
    // ${dl.unparse}.${attr.unparse} := ${e.unparse}
    if (${dl.translation}[${attr.attrOccursIndex}] == null)
      ${dl.translation}[${attr.attrOccursIndex}] = new ${makeCAClassName(attr.attrDcl.fullName)}();
    ((common.CollectionAttribute)${dl.translation}[${attr.attrOccursIndex}]).setBase(${wrapLazy(e)});
""";
}
aspect production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  {- <- -} e::Decorated! Expr with {}
{
  top.translation = s"""
    // ${dl.unparse}.${attr.unparse} <- ${e.unparse}
    if (${dl.translation}[${attr.attrOccursIndex}] == null)
      ${dl.translation}[${attr.attrOccursIndex}] = new ${makeCAClassName(attr.attrDcl.fullName)}();
    ((common.CollectionAttribute)${dl.translation}[${attr.attrOccursIndex}]).addPiece(${wrapLazy(e)});
""";
}


function makeCAClassName
String ::= s::String
{
  return substituteLast(".", ".CA", makeName(s));
}

