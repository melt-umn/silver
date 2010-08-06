grammar silver:analysis:typechecking:core;
import silver:definition:core;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.typeErrors := ags.typeErrors;
}

aspect production agDclNone
top::AGDcl ::=
{
  top.typeErrors := [];
}

aspect production agDclsOne
top::AGDcls ::= ag::AGDcl
{
  top.typeErrors := ag.typeErrors;
}

aspect production agDclsCons
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.typeErrors := h.typeErrors ++ t.typeErrors;
}

aspect production agDclsAppend
top::AGDcls ::= h::AGDcls t::AGDcls
{
  top.typeErrors := h.typeErrors ++ t.typeErrors;
}

aspect production agDclAppend
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.typeErrors := h.typeErrors ++ t.typeErrors;
}



