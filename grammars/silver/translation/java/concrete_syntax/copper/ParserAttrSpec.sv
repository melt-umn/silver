grammar silver:translation:java:concrete_syntax:copper;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;

nonterminal ParserAttrSpec with name, typerep, actionCode;

function parserAttrSpec
Decorated ParserAttrSpec ::= n::String t::TypeExp a::String
{
  return decorate i_parserAttrSpec(n, t, a) with {};
}

abstract production i_parserAttrSpec
top::ParserAttrSpec ::= n::String t::TypeExp a::String
{
  top.name = n;
  top.typerep = t;
  top.actionCode = a;
}

synthesized attribute parserAttrDcls :: [Decorated ParserAttrSpec];
attribute parserAttrDcls occurs on Root, RootSpec, AGDcls, AGDcl;

aspect production attributeDclParser
top::AGDcl ::= 'parser' 'attribute' a::Name '::' te::Type 'action' acode::ActionCode_c ';'
{
  top.parserAttrDcls = [parserAttrSpec(makeCopperName(fName), te.typerep, acode.actionCode)];
}

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.parserAttrDcls = [];
}

aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.parserAttrDcls = c1.parserAttrDcls;
 
}

aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.parserAttrDcls = c1.parserAttrDcls ++ c2.parserAttrDcls;
}

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.parserAttrDcls = ags.parserAttrDcls;
}

aspect production agDclsOne
top::AGDcls ::= ag::AGDcl
{
  top.parserAttrDcls = ag.parserAttrDcls ;
}

aspect production agDclsCons
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.parserAttrDcls = h.parserAttrDcls ++ t.parserAttrDcls;
}

aspect production agDclsAppend
top::AGDcls ::= ag1::AGDcls ag2::AGDcls
{
  top.parserAttrDcls = ag1.parserAttrDcls ++ ag2.parserAttrDcls;
}

aspect production agDclDefault
top::AGDcl ::=
{
  top.parserAttrDcls = [];
}

aspect production agDclAppend
top::AGDcl ::= ag1::AGDcl ag2::AGDcl
{
  top.parserAttrDcls = ag1.parserAttrDcls ++ ag2.parserAttrDcls;
}

