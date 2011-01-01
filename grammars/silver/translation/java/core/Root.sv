grammar silver:translation:java:core;

attribute javaClasses occurs on Root, AGDcls, AGDcl;
attribute setupInh occurs on Root, AGDcls, AGDcl;
attribute initProd occurs on Root, AGDcls, AGDcl;
attribute initValues occurs on Root, AGDcls, AGDcl;
attribute postInit occurs on Root, AGDcls, AGDcl;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.javaClasses = ags.javaClasses;
  top.setupInh := ags.setupInh;
  top.initProd := ags.initProd;
  top.initValues := ags.initValues;
  top.postInit := ags.postInit;
}

aspect production agDclsOne
top::AGDcls ::= ag::AGDcl
{
  top.javaClasses = ag.javaClasses;
  top.setupInh := ag.setupInh; 
  top.initProd := ag.initProd;
  top.initValues := ag.initValues;
  top.postInit := ag.postInit;
}

aspect production agDclsCons
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.javaClasses = h.javaClasses ++ t.javaClasses;
  top.setupInh := h.setupInh ++ t.setupInh;
  top.initProd := h.initProd ++ t.initProd;
  top.initValues := h.initValues ++ t.initValues;
  top.postInit := h.postInit ++ t.postInit;
}

aspect production agDclsAppend
top::AGDcls ::= h::AGDcls t::AGDcls
{
  top.javaClasses = h.javaClasses ++ t.javaClasses;
  top.setupInh := h.setupInh ++ t.setupInh;
  top.initProd := h.initProd ++ t.initProd;
  top.initValues := h.initValues ++ t.initValues;
  top.postInit := h.postInit ++ t.postInit;
}

aspect production agDclDefault
top::AGDcl ::=
{
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";
}

aspect production agDclAppend
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.javaClasses = h.javaClasses ++ t.javaClasses;
  top.setupInh := h.setupInh ++ t.setupInh;
  top.initProd := h.initProd ++ t.initProd;
  top.initValues := h.initValues ++ t.initValues;
  top.postInit := h.postInit ++ t.postInit;
}
