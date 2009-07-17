grammar silver:translation:java:core;
import silver:definition:core;

attribute javaClasses occurs on Root, AGDcls, AGDcl, ModuleStmts;
attribute setupInh occurs on Root, AGDcls, AGDcl;
attribute initProd occurs on Root, AGDcls, AGDcl, ModuleStmts;
attribute initAspect occurs on Root, AGDcls, AGDcl;
attribute postInit occurs on Root, AGDcls, AGDcl;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.javaClasses = ags.javaClasses ++ ms.javaClasses;
  top.setupInh := ags.setupInh;
  top.initProd := ags.initProd ++ ms.initProd;
  top.initAspect := ags.initAspect;
  top.postInit := ags.postInit;
}

aspect production agDclNone
top::AGDcl ::=
{
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initAspect := "";
  top.postInit := "";
}

aspect production agDclsOne
top::AGDcls ::= ag::AGDcl
{
  top.javaClasses = ag.javaClasses;
  top.setupInh := ag.setupInh; 
  top.initProd := ag.initProd;
  top.initAspect := ag.initAspect;
  top.postInit := ag.postInit;
}

aspect production agDclsCons
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.javaClasses = h.javaClasses ++ t.javaClasses;
  top.setupInh := h.setupInh ++ t.setupInh;
  top.initProd := h.initProd ++ t.initProd;
  top.initAspect := h.initAspect ++ t.initAspect;
  top.postInit := h.postInit ++ t.postInit;
}

aspect production agDclsAppend
top::AGDcls ::= h::AGDcls t::AGDcls
{
  top.javaClasses = h.javaClasses ++ t.javaClasses;
  top.setupInh := h.setupInh ++ t.setupInh;
  top.initProd := h.initProd ++ t.initProd;
  top.initAspect := h.initAspect ++ t.initAspect;
  top.postInit := h.postInit ++ t.postInit;
}

aspect production agDclAppend
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.javaClasses = h.javaClasses ++ t.javaClasses;
  top.setupInh := h.setupInh ++ t.setupInh;
  top.initProd := h.initProd ++ t.initProd;
  top.initAspect := h.initAspect ++ t.initAspect;
  top.postInit := h.postInit ++ t.postInit;
}
