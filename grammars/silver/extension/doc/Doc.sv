grammar silver:extension:doc;

import silver:util;

import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:env;
import silver:definition:regex;

nonterminal Doc with docMessage, docOrder;
synthesized attribute docMessage :: String;
synthesized attribute docOrder :: Integer;

function AP Integer ::={ return 10; }
function CP Integer ::={ return 20; }
function F Integer ::={ return 30; }
function T Integer ::={ return 40; }
function NT Integer ::={ return 50; }
function SA Integer ::={ return 60; }
function IA Integer ::={ return 70; }
function O Integer ::={ return 80; }
function P Integer ::={ return 90; }


--
synthesized attribute sectionStart :: String;
synthesized attribute sectionEnd :: String;
nonterminal DocSection with sectionStart, sectionEnd, docOrder;

function section
Decorated DocSection ::= f::Integer s::String e::String{
  return decorate i_section(f, s, e) with {};
}

abstract production i_section 
top::DocSection ::= f::Integer s::String e::String{
  top.sectionStart = s;
  top.sectionEnd = e;
  top.docOrder = f;
}

synthesized attribute docSection :: [Decorated DocSection];
nonterminal DocSectionList with docSection;

function GlobalDocSection
[Decorated DocSection] ::= {
  return (decorate i_GlobalDocSection() with {}).docSection;
}

abstract production i_GlobalDocSection
top::DocSectionList ::= {

  production attribute dSection :: [Decorated DocSection] with ++;
  dSection := [
	section(AP(), "<div class='section'><div class='row'><span class='header'>Abstract Productions</span></div>", "</div>"),
	section(CP(), "<div class='section'><div class='row'><span class='header'>Concrete Productions</span></div>", "</div>"),
	section(F(), "<div class='section'><div class='row'><span class='header'>Functions</span></div>", "</div>"),
	section(T(), "<div class='section'><div class='row'><span class='header'>Terminals</span></div>", "</div>"),
	section(NT(), "<div class='section'><div class='row'><span class='header'>Nonterminals</span></div>", "</div>"),
	section(SA(), "<div class='section'><div class='row'><span class='header'>Synthesized Attributes</span></div>", "</div>"),
	section(IA(), "<div class='section'><div class='row'><span class='header'>Inherited Attributes</span></div>", "</div>"),
	section(O(), "<div class='section'><div class='row'><span class='header'>Occurs</span></div>", "</div>"),
	section(P(), "<div class='section'><div class='row'><span class='header'>Parsers</span></div>", "</div>")
  ];

  top.docSection = dSection;
}

--


function doc
Decorated Doc ::= f::Integer s::String{
  return decorate i_doc(f, s) with {};
}

abstract production i_doc 
top::Doc ::= f::Integer s::String{
  top.docMessage = s;
  top.docOrder = f;
}

function getDocSection
Decorated DocSection ::= i::Integer d::[Decorated DocSection]{
  return if null(d) then section(0, "", "")
	else if head(d).docOrder == i then head(d)
	else getDocSection(i, tail(d));
}

function flattenDoc
[Decorated Doc] ::= d::[Decorated Doc]{
  return if null(d) then []
	else if null(tail(d)) then d
	else if head(d).docOrder == head(tail(d)).docOrder then flattenDoc([doc(head(d).docOrder, head(d).docMessage ++ head(tail(d)).docMessage)] ++ tail(tail(d)))
	else [head(d)] ++ flattenDoc(tail(d));
}

function makeFileName
String ::= str::String
{
  return substitute("/", ":", str);
}

function getGrammarName
String ::= s::String {
  return getGrammarNameHelp(split(":", s));
}

function getGrammarNameHelp
String ::= s::[String] {
  return if  null(s) || null(tail(s)) then "" else head(s) ++ ":" ++ getGrammarNameHelp(tail(s));
}

synthesized attribute documentation :: [Decorated Doc];
attribute documentation occurs on AGDcl, AGDcls, Root, RootSpec;

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.documentation = [];
}
aspect production i_consRootSpec
top::RootSpec ::= c1::Decorated Root c2::Decorated RootSpec
{
  top.documentation = c1.documentation ++ c2.documentation;
}
aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.documentation = c1.documentation ++ c2.documentation;
}
aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.documentation = ags.documentation;
}

aspect production agDclNone
top::AGDcl ::=
{
  top.documentation = [];
}

aspect production agDclsOne
top::AGDcls ::= ag::AGDcl
{
  top.documentation = ag.documentation;
}

aspect production agDclAppend
top::AGDcl ::= ag1::AGDcl ag2::AGDcl
{
  top.documentation = ag1.documentation ++ ag2.documentation;
}

aspect production agDclsCons
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.documentation = h.documentation ++ t.documentation;
}

aspect production agDclsAppend
top::AGDcls ::= ag1::AGDcls ag2::AGDcls
{
  top.documentation = ag1.documentation ++ ag2.documentation;
}

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name '::' te::Type ';'
{
  local attribute t :: String;
  t = te.typerep.typeName;

  top.documentation = [doc(IA(), 
"<div class='row'>\n" ++ 
"  <a name='" ++ fName ++ "'></a>\n" ++ 
"  <span class='value'>" ++ a.name ++ "</span> :: <a href='" ++ makeFileName(getGrammarName(t)) ++ "#" ++ t ++ "' class='type'>" ++ last(split(":", t)) ++ "</a><br/>\n" ++ 
"  <span class='info'>inherited</span>\n" ++ 
"</div>\n"
)];
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name '::' te::Type ';'
{
  local attribute t :: String;
  t = te.typerep.typeName;

  top.documentation = [doc(SA(),
"<div class='row'>\n" ++ 
"  <a name='" ++ fName ++ "'></a>\n" ++ 
"  <span class='value'>" ++ a.name ++ "</span> :: <a href='" ++ makeFileName(getGrammarName(t)) ++ "#" ++ t ++ "' class='type'>" ++ last(split(":", t)) ++ "</a><br/>\n" ++ 
"  <span class='info'>synthesized</span>\n" ++ 
"</div>\n"
)];
}

aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name
{
  top.documentation = [doc(NT(),
"<div class='row'>" ++ 
"  <a name='" ++ fName ++ "'></a>\n" ++
"  <span class='type'>" ++ id.name ++ "</span>\n" ++ 
"</div>\n"
)];
}

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  top.documentation = [doc(O(), 
"<div class='row'>\n" ++
"  <a href='" ++ makeFileName(getGrammarName(a.lookupAttribute.fullName)) ++ "/SilverDoc.html#" ++ a.lookupAttribute.fullName ++ "'>" ++ a.name ++ "</a> @ <a href='" ++ makeFileName(getGrammarName(nt.lookupType.fullName)) ++ "#" ++ nt.lookupType.fullName++ "' class='type'>" ++ nt.name ++ "</a>\n" ++ 
"</div>\n"
)];
}

aspect production concreteProductionDclModifiers
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  local attribute t :: String;
  t = namedSig.outputElement.typerep.typeName;

  top.documentation = [doc(CP(),
"<div class='row'>\n" ++ 
"  <a name='" ++ fName ++ "'></a><span class='value'>" ++ id.name ++ "</span> " ++ 
		 "<a href='" ++ makeFileName(getGrammarName(t)) ++ "#" ++ t ++ "' class='type'>" ++ last(split(":", t)) ++ "</a> ::= " ++ 
		 foldRHS(getTypeNamesSignature(namedSig.inputElements)) ++ "<br/>\n" ++
"  <span class='info'>concrete</span></div>\n" ++
"</div>"
)];
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  local attribute t :: String;
  t = namedSig.outputElement.typerep.typeName;

  top.documentation = [doc(AP(),
"<div class='row'>\n" ++ 
"  <a name='" ++ fName ++ "'></a><span class='value'>" ++ id.name ++ "</span> " ++ 
		 "<a href='" ++ makeFileName(getGrammarName(t)) ++ "#" ++ t ++ "' class='type'>" ++ last(split(":", t)) ++ "</a> ::= " ++ 
		 foldRHS(getTypeNamesSignature(namedSig.inputElements)) ++ "\n" ++
"</div>"
)];
}

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
  local attribute t :: String;
  t = namedSig.outputElement.typerep.typeName;

  top.documentation = [doc(F(),
"<div class='row'>\n" ++ 
"  <a name='" ++ fName ++ "'></a><span class='value'>" ++ id.name ++ "</span> " ++ 
		 "<a href='" ++ makeFileName(getGrammarName(t)) ++ "#" ++ t ++ "' class='type'>" ++ last(split(":", t)) ++ "</a> ::= " ++ 
		 foldRHS(getTypeNamesSignature(namedSig.inputElements)) ++ "\n" ++
"</div>"
)];
}

aspect production parserDcl
top::AGDcl ::= p::ParserDcl{
  top.documentation = [doc(P(),
"<div class='parser'>\nparser::" ++ makeTypeLink(head(p.parserDcls).startName) ++ "\n" ++ 
makeRules(mergeRules(head(p.parserDcls).ruleDcls)) ++ "\n" ++
"</div>\n"
)];
}

function foldRHS
String ::= s::[String]{
  return if null(s) then "" else makeTypeLink(head(s)) ++ (if null(tail(s)) then "" else " ") ++ 
	foldRHS(tail(s));
}

function foldConcreteRHS
String ::= s::[String]{
  return if null(s) then "" else makeConcreteTypeLink(head(s)) ++ (if null(tail(s)) then "" else " ") ++ 
	foldConcreteRHS(tail(s));
}

function makeTypeLink
String ::= s::String{
  return "<a href='" ++ makeFileName(getGrammarName(s)) ++ "/SilverDoc.html#" ++ s ++ "' class='type'>" ++ last(split(":", s)) ++ "</a>";
}

function makeConcreteTypeLink
String ::= s::String{
  return "<a href='#C" ++ s ++ "' class='type'>" ++ last(split(":", s)) ++ "</a>";
}

function makeRules
String ::= s::[Decorated RuleSpec]{
  return if null(s) then ""
	else "<div class='row'>\n<a name='C" ++ head(s).ruleLHS ++ "'></a>" ++ makeTypeLink(head(s).ruleLHS) ++ "<br/>\n" ++ makeParserRHS(head(s).ruleRHSSpec) ++ "</div>\n" ++
		makeRules(tail(s));
}

function makeParserRHS
String ::= s::[Decorated RHSSpec]{
  return if null(s) then "" else "<span class='rhs'>" ++ foldConcreteRHS(head(s).ruleRHS) ++ "</span><br/>\n" ++ makeParserRHS(tail(s));
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.documentation = [];
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  top.documentation = [];
}

aspect production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers 
{
  top.documentation = [doc(T(),
"<div class='row'><a name='" ++ fName ++ "'></a>" ++ id.name ++ " " ++  head(top.terminalDcls).terminalRegExprSpec.regString ++ "<br/>\n" ++
					(if head(top.terminalDcls).ignoreTerminal then "  <span class='info'>ignore</span><br/>\n" else "") ++
					"  <span class='info'>parser precedence = " ++ toString(head(top.terminalDcls).parserPrecedence) ++ "</span><br/>\n"  ++
					"  <span class='info'>parser association = " ++ head(top.terminalDcls).parserAssociation ++ "</span>\n</div>\n"
)];
}


