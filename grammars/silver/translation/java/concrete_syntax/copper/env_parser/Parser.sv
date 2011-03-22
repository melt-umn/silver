grammar silver:translation:java:concrete_syntax:copper:env_parser;

import silver:definition:env:env_parser;
import silver:definition:concrete_syntax:env_parser;

import silver:translation:java:concrete_syntax:copper hiding Disambiguation_kwd, Submits_t, Dominates_t, Action_kwd, Layout_kwd; -- TODO: hiding here is a hack of sorts...

import silver:definition:env;
import silver:definition:concrete_syntax;

import silver:definition:core only grammarName, location;

import silver:util;

--------------------------------------------------------------------------------
-- DclInfos

terminal LexerClassTerm 'lexer_class' lexer classes {C_1};
terminal ParseAttrTerm 'parse_attr' lexer classes {C_1};

concrete production aDclInfoLexerClass
top::IDclInfo ::= 'lexer_class' '(' l::ILocation ',' fn::Name ',' s::INames ',' d::INames ')'
{
  top.defs = addLexerClassDcl(top.grammarName, l.location, fn.aname, s.names, d.names, emptyDefs());
}

concrete production aDclInfoParseAttr
top::IDclInfo ::= 'parse_attr' '(' l::ILocation ',' fn::Name ',' t::ITypeRep ')'
{
  top.defs = addParserAttrDcl(top.grammarName, l.location, fn.aname, t.typerep, emptyDefs());
}

--------------------------------------------------------------------------------
-- RootSpec 

attribute disambiguationGroupDcls occurs on IRootSpecParts, IRootSpecPart;
attribute parserAttrDcls occurs on IRootSpecParts, IRootSpecPart;

aspect production parserRootSpec
top::RootSpec ::= p::IRootSpecParts _
{
  top.disambiguationGroupDcls = p.disambiguationGroupDcls;
  top.parserAttrDcls = p.parserAttrDcls;
}

aspect production aRoot1
top::IRootSpecParts ::= r::IRootSpecPart
{
  top.disambiguationGroupDcls = r.disambiguationGroupDcls;
  top.parserAttrDcls = r.parserAttrDcls;
}

aspect production aRoot2
top::IRootSpecParts ::= r1::IRootSpecPart r2::IRootSpecParts
{
  top.disambiguationGroupDcls = r1.disambiguationGroupDcls ++ r2.disambiguationGroupDcls;
  top.parserAttrDcls = r1.parserAttrDcls ++ r2.parserAttrDcls;
}

aspect production aRootSpecDefault
top::IRootSpecPart ::= {
  top.disambiguationGroupDcls = [];
  top.parserAttrDcls = [];
}

--------------------------------------------------------------------------------
-- RootSpecParts - new stuff

terminal DisambiguationTerm 'disambiguate' lexer classes {C_1};
terminal EscapedStringTerm /"([^\"\\]|\\.)*"/ lexer classes {C_1};

function decodeEscapedStringTerm
String ::= s::String
{
  return unescapeString(substring(1,length(s)-1,s));
}

concrete production aDisambiguationGroup
top::IRootSpecPart ::= 'disambiguate' '[' n::INames ',' s::EscapedStringTerm ']'
{
  top.disambiguationGroupDcls = [disambiguationGroupSpec(n.names, decodeEscapedStringTerm(s.lexeme))];
  
  forwards to aRootSpecDefault();
}

concrete production aParserAttribute
top::IRootSpecPart ::= 'parse_attr' '[' n::Name ',' t::ITypeRep ',' s::EscapedStringTerm ']'
{
  top.parserAttrDcls = [parserAttrSpec(n.aname, t.typerep, decodeEscapedStringTerm(s.lexeme))];
  
  forwards to aRootSpecDefault();
}

--------------------------------------------------------------------------------
-- Terminal Modifiers

terminal SubmitsTerm 'submits' lexer classes {C_1};
terminal DominatesTerm 'dominates' lexer classes {C_1};
terminal ActionTerm 'action' lexer classes {C_1};
terminal LayoutTerm 'layout' lexer classes {C_1};

concrete production aTerminalModifierSpecLexerClasses
top::ITerminalModifierSpec ::= 'lexer_class' n::INames {
  top.terminalModifiers = [lexerClassesTerminalModifierSpec(n.names)];
}

concrete production aTerminalModifierSpecSubmits
top::ITerminalModifierSpec ::= 'submits' n::INames {
  top.terminalModifiers = [submitsToTerminalModifierSpec(n.names)];
}

concrete production aTerminalModifierSpecDominates
top::ITerminalModifierSpec ::= 'dominates' n::INames {
  top.terminalModifiers = [dominatesTerminalModifierSpec(n.names)];
}

concrete production aTerminalModifierSpecAction
top::ITerminalModifierSpec ::= 'action' s::EscapedStringTerm {
  top.terminalModifiers = [actionCodeTerminalModifierSpec(decodeEscapedStringTerm(s.lexeme))];
}

--------------------------------------------------------------------------------
-- ProductionModifier

concrete production aProductionModifierSpecAction
top::IProductionModifierSpec ::= 'action' s::EscapedStringTerm {
  top.productionModifiers = [actionProductionModifierSpec(decodeEscapedStringTerm(s.lexeme))];
}

concrete production aProductionModifierSpecLayout
top::IProductionModifierSpec ::= 'layout' n::INames {
  top.productionModifiers = [layoutProductionModifierSpec(n.names)];
}


