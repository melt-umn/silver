grammar silver:translation:java:concrete_syntax:copper;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;

synthesized attribute groupName :: String;
synthesized attribute groupMembers :: [String];

nonterminal DisambiguationGroupSpec with groupName,groupMembers,actionCode;

function disambiguationGroupSpec
Decorated DisambiguationGroupSpec ::= members::[String] acode::String {
  return decorate i_disambiguationGroupSpec(members, acode) with {};
}

abstract production i_disambiguationGroupSpec
top::DisambiguationGroupSpec ::= members::[String] acode::String
{
  -- this genInt is okay because it literally doesn't matter at all.
  -- Copper demands a name, but it's basically unused.
  top.groupName = "D" ++ toString(genInt());
  top.groupMembers = members;
  top.actionCode = acode;
}

synthesized attribute disambiguationGroupDcls :: [Decorated DisambiguationGroupSpec];
attribute disambiguationGroupDcls occurs on Root, RootSpec, AGDcls, AGDcl;

aspect production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermPrecList acode::ActionCode_c
{
  top.disambiguationGroupDcls = [disambiguationGroupSpec(terms.precTermList,acode.actionCode)];
}


aspect production attributeDclParser
top::AGDcl ::= 'parser' 'attribute' a::Name '::' te::Type 'action' acode::ActionCode_c ';'
{
  top.disambiguationGroupDcls = [];
}


aspect production lexerClassDclFull
top::AGDcl ::= id::Name t1::TermPrecList t2::TermPrecList
{
  top.disambiguationGroupDcls = [];
}


aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name tl::TypeList
{
  top.disambiguationGroupDcls = [];			   
}

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.disambiguationGroupDcls = [];
}

aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.disambiguationGroupDcls = c1.disambiguationGroupDcls;
 
}

aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.disambiguationGroupDcls = c1.disambiguationGroupDcls ++ c2.disambiguationGroupDcls;
}

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name '<' tl::TypeList '>' '::' te::Type ';'
{
  top.disambiguationGroupDcls = [];
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name '<' tl::TypeList '>' '::' te::Type ';'
{
  top.disambiguationGroupDcls = [];
}

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName '<' tlat::TypeList '>' 'occurs' 'on' nt::QName '<' tlnt::TypeList '>' ';'
{
  top.disambiguationGroupDcls = [];
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.disambiguationGroupDcls = [];
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.disambiguationGroupDcls = [];
}

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.disambiguationGroupDcls = [];
}

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.disambiguationGroupDcls = [];
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody
{
  top.disambiguationGroupDcls = [];
}

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.disambiguationGroupDcls = ags.disambiguationGroupDcls;
}

aspect production agDclNone
top::AGDcl ::=
{
  top.disambiguationGroupDcls = [];
}

aspect production agDclsOne
top::AGDcls ::= ag::AGDcl
{
  top.disambiguationGroupDcls = ag.disambiguationGroupDcls ;
}

aspect production agDclsCons
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.disambiguationGroupDcls = h.disambiguationGroupDcls ++ t.disambiguationGroupDcls;
}

aspect production agDclsAppend
top::AGDcls ::= ag1::AGDcls ag2::AGDcls
{
  top.disambiguationGroupDcls = ag1.disambiguationGroupDcls ++ ag2.disambiguationGroupDcls;
}

aspect production agDclAppend
top::AGDcl ::= ag1::AGDcl ag2::AGDcl
{
  top.disambiguationGroupDcls = ag1.disambiguationGroupDcls ++ ag2.disambiguationGroupDcls;
}

aspect production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers
{
  top.disambiguationGroupDcls = [];
}
