grammar silver:translation:java:concrete_syntax:copper;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;

nonterminal ParserAttrSpec with name, typerep, actionCode;

function parserAttrSpec
Decorated ParserAttrSpec ::= n::String t::Decorated TypeRep a::String {
  return decorate i_parserAttrSpec(n, t, a) with {};
}

abstract production i_parserAttrSpec
top::ParserAttrSpec ::= n::String t::Decorated TypeRep a::String
{
  top.name = n;
  top.typerep = t;
  top.actionCode = a;
}

synthesized attribute parserAttrDcls :: [Decorated ParserAttrSpec];
attribute parserAttrDcls occurs on Root, RootSpec, AGDcls, AGDcl;

aspect production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermPrecList acode::ActionCode_c
{
  top.parserAttrDcls = [];
}


aspect production attributeDclParser
top::AGDcl ::= 'parser' 'attribute' a::Name '::' te::Type 'action' acode::ActionCode_c ';'
{
  top.parserAttrDcls = [parserAttrSpec(makeCopperName(fName), te.typerep, acode.actionCode)];
}


aspect production lexerClassDclFull
top::AGDcl ::= id::Name t1::TermPrecList t2::TermPrecList
{
  top.parserAttrDcls = [];
}


aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name
{
  top.parserAttrDcls = [];			   
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

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name '::' te::Type ';'
{
  top.parserAttrDcls = [];
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name '::' te::Type  ';'
{
  top.parserAttrDcls = [];
}

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  top.parserAttrDcls = [];
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.parserAttrDcls = [];
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.parserAttrDcls = [];
}

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.parserAttrDcls = [];
}

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.parserAttrDcls = [];
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody
{
  top.parserAttrDcls = [];
}

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.parserAttrDcls = ags.parserAttrDcls;
}

aspect production agDclNone
top::AGDcl ::=
{
  top.parserAttrDcls = [];
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

aspect production agDclAppend
top::AGDcl ::= ag1::AGDcl ag2::AGDcl
{
  top.parserAttrDcls = ag1.parserAttrDcls ++ ag2.parserAttrDcls;
}

aspect production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers
{
  top.parserAttrDcls = [];
}
