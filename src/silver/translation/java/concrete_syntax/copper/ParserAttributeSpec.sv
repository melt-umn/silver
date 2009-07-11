grammar silver:translation:java:concrete_syntax:copper;
import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:env;

import silver:analysis:typechecking:core;
import silver:translation:java:core;

synthesized attribute isParserAttrDeclaration :: Boolean;
synthesized attribute parserAttrDeclaration :: ParserAttrSpec;

attribute isParserAttrDeclaration occurs on EnvItem;

-- This adds  parser_attr n  entries
function addParserAttrDcl
Decorated Defs ::= n::String e::Decorated Defs
{
  return consDefs(parserAttrEnvItem(n), e);
}

function parserAttrEnvItem
Decorated EnvItem ::= n::String
{
  return decorate i_parserAttrEnvItem(n) with {};
}

abstract production i_parserAttrEnvItem
top::EnvItem ::= n::String
{
  top.itemName = n;
  top.unparse = "parserAttr('" ++ n ++ "')";
  top.isParserAttrDeclaration = true;

  forwards to i_defaultEnvItem();
}

synthesized attribute aName :: String;
synthesized attribute aType :: Type;

nonterminal ParserAttrSpec with aName,aType,actionCode;

abstract production parserAttrSpec
top::ParserAttrSpec ::= aname::String atype::Type acode::String
{
   top.aName = aname;
   top.aType = atype;
   top.actionCode = acode;

}

synthesized attribute parserAttrDcls :: [ParserAttrSpec];
attribute parserAttrDcls occurs on Root, RootSpec, AGDcls, AGDcl, ModuleStmts;


aspect production attributeDclParser
top::AGDcl ::= 'parser' 'attribute' a::Name '::' te::Type 'action' acode::ActionCode_c ';'
{
  top.parserAttrDcls = [parserAttrSpec(fName,te,acode.actionCode)];

  top.typeErrors = []; -- Finalize
  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];
}

aspect production lexerClassDcl
top::AGDcl ::= 'lexer' 'class' d::Name ';'
{
  top.parserAttrDcls = [];
}

aspect production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermPrecList acode::ActionCode_c
{
  top.parserAttrDcls = [];
}



aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name
{
  top.parserAttrDcls = [];			   
}

aspect production closeNonterminalDcl
top::AGDcl ::= 'close' 'nonterminal' q::QName ';'
{
  top.parserAttrDcls = [ ];			   
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

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts im::ImportStmts ags::AGDcls
{
  top.parserAttrDcls = ags.parserAttrDcls ++ ms.parserAttrDcls;
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

--attribute parserAttrDcls occurs on SyntaxStmt;
--
--aspect production syntaxStmtsOne 
--top::ImportExportStmts ::= syn::SyntaxStmt
--{
--  top.parserAttrDcls = syn.parserAttrDcls;
--}
--
--aspect production syntaxStmtsCons
--top::ImportExportStmts ::= h::SyntaxStmt t::ImportExportStmts
--{
--  top.parserAttrDcls = h.parserAttrDcls ++ t.parserAttrDcls;
--}
--
--aspect production syntaxAll
--top::SyntaxStmt ::= s::Syntax_kwd pkg::QName se::Semi_t
--{
--  top.parserAttrDcls = spec.parserAttrDcls;
--}
--
--aspect production syntaxHiding
--top::SyntaxStmt ::= s::Syntax_kwd pkg::QName h::Hiding_kwd ns::NameList se::Semi_t
--{
--  top.parserAttrDcls = spec.parserAttrDcls;
--}

aspect production terminalDcl
top::AGDcl ::= 'terminal' id::Name r::RegExpr ';'
{
  top.parserAttrDcls = [];
}
